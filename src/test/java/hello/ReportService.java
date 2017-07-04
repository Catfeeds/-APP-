/*package hello;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import com.opensymphony.xwork2.ActionContext;
import com.ponshine.site.system.BaseDAO;

public class ReportService extends BaseDAO{
	final static Logger log=Logger.getLogger(ReportService.class);
	private String JASPER_PATH = ServletActionContext.getRequest().getRealPath("/");
	private String jrxmlPath = "";
	private String jasperPath = "";
	private String reporti18n = "zh_CN";
	private String contentType = "";

	*//**
	 * export report
	 * 
	 * @return
	 * @throws JRException
	 * @throws SQLException
	 * @throws ParseException
	 *//*
	public InputStream exportReport(String fileName, Map<String, Object> map,
			String format) throws JRException, SQLException, ParseException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap = map;
		jrxmlPath = JASPER_PATH + "/reportFile/" + fileName + ".jrxml";
		jasperPath = JASPER_PATH + "/reportFile/" + fileName + ".jasper";
		JasperPrint jasperPrint = null;
		Locale locale = new Locale(reporti18n.substring(0, 2),
				reporti18n.substring(3, 5));
		paramMap.put(JRParameter.REPORT_LOCALE, locale);
		Connection conn = ds.getConnection();
		jasperPrint = JasperFillManager.fillReport(jasperPath, paramMap, conn);
		String encoding = "UTF-8";
		JRExporter exporter = null;
		if ("pdf".equals(format)) {
			exporter = new JRPdfExporter();
		} else if ("html".equals(format)) {
			exporter = new JRHtmlExporter();
		} else if ("csv".equals(format)) {
			exporter = new JRCsvExporter();
			encoding = "GBK";
		} else if ("xls".equals(format)) {
			exporter = new JExcelApiExporter();
		}
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outstream);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, encoding);
		exporter.exportReport();
		conn.close();
		InputStream exportStream = new ByteArrayInputStream(outstream.toByteArray());
		return exportStream;
	}

	*//**
	 * 导出word
	 *//*
	public InputStream exportWord(String fileName, Map<String, Object> map)
			throws JRException, SQLException, ParseException, IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap = map;
		jrxmlPath = JASPER_PATH + "/reportFile/" + fileName + ".jrxml";
		jasperPath = JASPER_PATH + "/reportFile/" + fileName + ".jasper";
		JasperPrint jasperPrint = null;
		Locale locale = new Locale(reporti18n.substring(0, 2),
				reporti18n.substring(3, 5));
		paramMap.put(JRParameter.REPORT_LOCALE, locale);
		Connection conn = ds.getConnection();
		String encoding = "UTF-8";
		jasperPrint = JasperFillManager.fillReport(jasperPath, paramMap, conn);
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		JRExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outstream);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, encoding);
		exporter.exportReport();
		conn.close();
		InputStream exportStream = new ByteArrayInputStream(outstream.toByteArray());
		return exportStream;
	}
	*//**
	 * 导出报表。
	 * @return
	 * @throws JRException
	 *//*
    public InputStream exportReport(String fileName,Map<String, Object> map) {
    	try{
    		Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		jrxmlPath = JASPER_PATH + "/reportFile/" + fileName + ".jrxml";
    		jasperPath = JASPER_PATH + "/reportFile/" + fileName + ".jasper";
			JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
			JasperPrint jasperPrint=null;
			jasperPrint = JasperFillManager.fillReport(jasperPath, map, conn);
	        String encoding="UTF-8";
	        JRExporter exporter = new JExcelApiExporter();
	
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outstream);
	        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,encoding);
	        exporter.exportReport();
	        try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        InputStream exportStream = new ByteArrayInputStream(outstream.toByteArray());
	        return exportStream;
	        
    	}catch(JRException ex){
    		
    		ex.printStackTrace();
    		return null;
    	}
    }
    *//**
	 * preview report
	 * 
	 * @return String 对前台传来的参数进行转化成报表参数
	 * @throws ParseException
	 * @throws SQLException
	 * @throws JRException
	 *//*
	public String previewReport(String fileName,String task_id,String yewu_name){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			paramMap = reportRead(fileName,task_id,yewu_name);
			return commonPreviewReport(fileName, paramMap);
		} catch (ParseException e) {
			log.error(e);
			e.printStackTrace();
		}catch (JRException e) {
			log.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	*//**
	 * Martin 2012-08-03
	 * 
	 * @return Map<String, Object>
	 * @throws ParseException
	 *//*
	public Map<String, Object> reportRead(String fileName,String task_id,String yewu_name) throws ParseException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(fileName.equals("task_AllInfo_statistics") || fileName.equals("taskRklAllInfo")){
			paramMap.put("TASK_ID", task_id);
		}
			
		if (fileName.equals("STD_DEATIL") || fileName.equals("taskRklOrgDetailInfo")) {
			paramMap.put("TASK_ID", task_id);
			paramMap.put("YEWU_ID", yewu_name);
		}
		return paramMap;
	}
	*//**
	 * preview report
	 * 
	 * @return String
	 * @throws JRException
	 * @throws SQLException
	 *//*

	public String commonPreviewReport(String fileName,
			Map<String, Object> params) throws JRException, SQLException {
		String folderName = "report_" + Math.random();
		jrxmlPath = JASPER_PATH + "/reportFile/" + fileName + ".jrxml";
		jasperPath = JASPER_PATH + "/reportFile/" + fileName + ".jasper";
		Connection conn = ds.getConnection();
		Locale locale = new Locale(reporti18n.substring(0, 2),
				reporti18n.substring(3, 5));
		params.put(JRParameter.REPORT_LOCALE, locale);
		JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath,
				params, conn);
		ActionContext.getContext().getSession()
				.put("currentReportPrint", jasperPrint);
		StringBuffer sbuffer = new StringBuffer();
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");// 不指定编码
																				// 可能中文就成乱码

		// 设置允许输出图片
		// String
		// fixedPath=JASPER_PATH.substring(JASPER_PATH.indexOf("jasper"),JASPER_PATH.length()).replace("\\",
		// "/");
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				ServletActionContext.getRequest().getContextPath()
						+ "/reportFile/images/" + folderName + "/");
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
				JASPER_PATH + "/reportFile/images/" + folderName + "/");
		// 设置允许输出图片
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
				Boolean.TRUE);
		exporter.exportReport();
		conn.close();
		if (sbuffer.toString().indexOf("JR_PAGE_ANCHOR_0_1") != -1) {
			return sbuffer.toString();
		} else {
			return null;
		}

	}
}
*/