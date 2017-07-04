package com.hcb.xigou.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Http与Servlet工具类.
 * 
 */
public class Servlets {
	private static final String DEFAULT_URL_ENCODING = "utf-8";

	// -- Content Type 定义 --//
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String TEXT_TYPE = "text/plain";
	// -- Header 定义 --//
	public static final String AUTHENTICATION_HEADER = "Authorization";

	// -- 常用数值定义 --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	private Servlets() {
	}

	/**
	 * 设置客户端缓存过期时间 的Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000);
		// Http 1.1 header, set a time after now.
		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified
	 *            内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag
	 *            内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request,
			HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(
						headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes(),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 取得带相同前缀的Request Parameters, copy from spring.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 * 
	 * 比如有m2
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getParametersStartingWith(
			ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	public static String appendURLParam(String src, String key, String param) {
		boolean hasQM = src.indexOf("?") > 0;
		boolean hasET = src.indexOf("=") > 0;
		src = hasQM ? (hasET ? src : src.substring(0, src.indexOf("?"))) : src;

		return src
				+ (hasQM && hasET ? ("&" + key + "=" + param) : ("?" + key
						+ "=" + param));
	}

	public static void writeResponse(HttpServletResponse response, String str)
			throws IOException {
		OutputStream os = response.getOutputStream();
		os.write(str.getBytes(DEFAULT_URL_ENCODING));
		os.flush();
	}

	public static String getRelativeUrl(HttpServletRequest request)
			throws IOException {
		String result = request.getRequestURI().substring(
				request.getContextPath().length())
				+ (request.getQueryString() == null ? "" : "?"
						+ request.getQueryString());
		return result;
	}

	public static String getCompleteUrl(HttpServletRequest request) {
		return getDomainUrl(request) + request.getRequestURI();
	}

	public static String getDomainUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort();
	}

	/**
	 * urlDecode 解码一次
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> parseUrls(String url) {
		Map<String, String> result = new HashMap<String, String>();
		if (url.indexOf("?") == -1) {
			return result;
		}
		String paramsStr = url.substring(url.indexOf("?") + 1);
		String[] params = paramsStr.split("&");
		for (String param : params) {
			try {
				String[] keyValues = param.split("=");
				result.put(keyValues[0], keyValues[1]);
			} catch (IndexOutOfBoundsException e) {
				System.err.println(String.format(
						"url %s parse wrong whith: %s", url, param));
			}
		}
		return result;
	}

	public static boolean isUrlCompleted(String url) {
		return url.startsWith("http") || url.startsWith("www");
	}

	/**
	 * 输出
	 * 
	 * @param obj
	 * @param flash
	 *            是否立即提交
	 */
	public static void write(Object obj, boolean flash,
			HttpServletResponse response) {
		try {
			response.getWriter().print(obj);
			if (flash) {
				response.getWriter().flush();
			}
		} catch (IOException e) {

		}
	}

	/**
	 * 输出
	 */
	public static void write(Object obj, HttpServletResponse response) {
		write(obj, false, response);
	}

	/**
	 * 输出
	 */
	public static void writeString(String json, HttpServletResponse response) {
		JSONObject jsonobject = JSONObject.fromObject(json);
		write(jsonobject, false, response);
	}

	public static void main(String[] args) {
		System.out.println(appendURLParam("/app/123?", "param", "test"));
		System.out.println(appendURLParam("/app/123?abc", "param", "test"));
		System.out.println(appendURLParam("/app/123?abc=123", "param", "test"));
	}
	
}
