package com.hcb.xigou.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.hcb.xigou.util.Config;
import com.hcb.xigou.bean.base.OutHead;
import com.hcb.xigou.util.MD5Util;

import net.sf.json.JSONObject;

@Controller
public class AliyunOss {
	private String getEndPoint() {
		return Config.getString("endPoint");
	}

	private String getAccessKeyId() {
		return Config.getString("accessKeyId");
	}

	private String getAccessKeySecret() {
		return Config.getString("accessKeySecret");
	}

	private String getBucketName() {
		return Config.getString("bucketName");
	}
	// 上传文件
		@RequestMapping(value = "media/file_upload", method = RequestMethod.POST,produces="text/html")
		@ResponseBody
		public String fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

			JSONObject json = new JSONObject();
			// 判断文件是否为空
			if (!file.isEmpty()) {
				try {
					String fileName = file.getOriginalFilename();
					if (!fileName.toUpperCase().endsWith("PDF") && !fileName.toUpperCase().endsWith("DOC")
					        && !fileName.toUpperCase().endsWith("DOCX") && !fileName.toUpperCase().endsWith("PPTX")
					        && !fileName.toUpperCase().endsWith("TXT") && !fileName.toUpperCase().endsWith("XLS")
					        && !fileName.toUpperCase().endsWith("XLSX") && !fileName.toUpperCase().endsWith("PPT")&&!fileName.toUpperCase().endsWith("BMP") && !fileName.toUpperCase().endsWith("GIF")
					        && !fileName.toUpperCase().endsWith("JPEG") && !fileName.toUpperCase().endsWith("PSD")
					        && !fileName.toUpperCase().endsWith("PNG") && !fileName.toUpperCase().endsWith("RAW")
					        && !fileName.toUpperCase().endsWith("JPG") && !fileName.toUpperCase().endsWith("KEY") && !fileName.toUpperCase().endsWith("NUMBERS")
					        && !fileName.toUpperCase().endsWith("PAGES")&& !fileName.toUpperCase().endsWith("ZIP")&& !fileName.toUpperCase().endsWith("RAR")) {
						json.put("result", "1");
						json.put("description", "上传失败，文件格式错误");
						return buildReqJsonObject(json);
					}

					String[] types = fileName.split("\\.");
					String type = types[types.length - 1];

					String attachmentUuid = this.generateFileName(fileName);

					// 创建OSSClient实例
					OSSClient ossClient = new OSSClient(this.getEndPoint(), this.getAccessKeyId(),
					        this.getAccessKeySecret());
					// 上传文件
					ObjectMetadata metadata = new ObjectMetadata();

					metadata.setContentType(file.getContentType());

					ossClient.putObject(this.getBucketName(), attachmentUuid + "." + type,
					        file.getInputStream(), metadata);

					// 关闭client
					ossClient.shutdown();

					json.put("result", "0");
					json.put("description", "上传成功");
					json.put("attachment_uuid", attachmentUuid);
					json.put("attachment_url",
					        "http://"+this.getBucketName()+".oss-cn-hangzhou.aliyuncs.com/" + attachmentUuid + "." + type);
					return buildReqJsonObject(json);
				} catch (Exception e) {
					e.printStackTrace();
					json.put("result", "1");
					json.put("description", "上传失败，上传文件时发生未知错误");
					return buildReqJsonObject(json);
				}
			} else {
				json.put("result", "1");
				json.put("description", "上传失败，未获取到文件信息");
				return buildReqJsonObject(json);
			}
		}

		// 上传图片
		@RequestMapping(value = "media/image_upload", method = RequestMethod.POST,produces="text/html")
		@ResponseBody
		public String imageUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

			JSONObject json = new JSONObject();

			// 判断文件是否为空
			if (!file.isEmpty()) {
				try {
					//String contentType = file.getContentType();
					/*if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)
					        && !"image/vnd.adobe.photoshop".equals(contentType) && !"image/bmp".equals(contentType)
					        && !"image/gif".equals(contentType) && !"image/x-panasonic-raw".equals(contentType)) {
						json.put("result", "1");
						json.put("description", "请检查数据类型是否正确");
						return buildReqJsonObject(json);
					}*/
					// 校验文件类型 
					String fileName = file.getOriginalFilename();
					if (!fileName.toUpperCase().endsWith("BMP") && !fileName.toUpperCase().endsWith("GIF")
					        && !fileName.toUpperCase().endsWith("JPEG") && !fileName.toUpperCase().endsWith("PSD")
					        && !fileName.toUpperCase().endsWith("PNG") && !fileName.toUpperCase().endsWith("RAW")
					        && !fileName.toUpperCase().endsWith("JPG")) {
						json.put("result", "1");
						json.put("description", "上传失败，文件格式错误");
						return buildReqJsonObject(json);
					}

					String[] types = fileName.split("\\.");
					String type = types[types.length - 1];

					String attachmentUuid = this.generateFileName(fileName);

					// 创建OSSClient实例
					OSSClient ossClient = new OSSClient(this.getEndPoint(), this.getAccessKeyId(),
					        this.getAccessKeySecret());
					// 上传文件
					ObjectMetadata metadata = new ObjectMetadata();

					metadata.setContentType(file.getContentType());

					ossClient.putObject(this.getBucketName(), attachmentUuid + "." + type,
					        file.getInputStream(), metadata);

					// 关闭client
					ossClient.shutdown();
					
					json.put("result", "0");
					json.put("description", "上传成功");
					json.put("attachment_uuid", attachmentUuid);
					json.put("attachment_url",
					        "http://"+this.getBucketName()+".img-cn-hangzhou.aliyuncs.com/" + attachmentUuid + "." + type);
					return buildReqJsonObject(json);
				} catch (Exception e) {
					e.printStackTrace();
					json.put("result", "2");
					json.put("description", "上传失败，上传文件时发生未知错误");
					return buildReqJsonObject(json);
				}
			} else {
				json.put("result", "1");
				json.put("description", "上传失败，未获取到文件信息");
				return buildReqJsonObject(json);
			}

		}

		private String generateFileName(String originalName) {

			try {
				return MD5Util.md5Digest(originalName + String.valueOf(Calendar.getInstance().getTimeInMillis())
				        + RandomStringUtils.random(8));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		protected String buildReqJsonObject(Object msgs) {
			JSONObject jo = new JSONObject();
			jo.put("head", getDefaultOutHead());
			jo.put("body", msgs);
			return jo.toString();
		}

		protected OutHead getDefaultOutHead() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String time = format.format(date);
			return new OutHead().setReturnCode("000").setReturnDescription("验证通过").setSysTime(time);
		}
}
