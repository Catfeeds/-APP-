package com.hcb.xigou.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringBufferInputStream;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class XmlHelper {
	
	/**
	 * 保存文档
	 * @param doco
	 * @param path
	 */
	public static void saveDoc(Document doc, String path) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(path)),format);
			writer.write(doc);
			writer.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取当前项目的类根目录
	 * @return
	 */
	public static String getClassRootPath() {
		URL url = XmlHelper.class.getResource("/");
		return url.getPath();
	}
	
	public static String getWebRootPath() {
		String path = XmlHelper.getClassRootPath();
		System.out.println(path);
		return path.substring(0, path.indexOf("WEB-INF/"));
	}
	
	/**
	 * 获取当前项目的根目录，即src目录下
	 * 如果已知xmlClassName 则返回类映射文件路径
	 * @param xmlClassName
	 * @return
	 */
	public static String getXmlPath(String xmlClassName) {
		String path = getClassRootPath() ;//+ domainClassPath;
		if(xmlClassName != null) {
			path += xmlClassName + ".xml";
		}
		return path;
	}
	
	
	/**
	 * 读取XML文档
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Document readDoc(String filePath) {
		SAXReader reader = new SAXReader();
		reader.setEntityResolver(new MyEntityResolver());
		try {
			return reader.read(new File(filePath));
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getInstructionValue(String name){
		return getValue(name,XmlHelper.getClassRootPath()+"platform/"+"instruction.xml");
	}
	
	public static String getValue(String name,String path){
		Document doc= XmlHelper.readDoc(path);
		Element root = doc.getRootElement();
		String v= root.element(name).getText();
		return v;
	}
	
	public static void main(String[] args) throws Exception {
		
		//String path = XmlHelper.getWebRootPath();
		System.out.println( XmlHelper.getClassRootPath());
		//path = XmlHelper.getClassRootPath();
		/*System.out.println(readDoc(getWebRootPath()+"WEB-INF/"+"instruction.xml"));
		
		Document doc= XmlHelper.readDoc(XmlHelper.getWebRootPath()+"WEB-INF/"+"instruction.xml");
		Element root = doc.getRootElement();
		//Element jcNode = (Element)root.selectSingleNode(".//app");
		String jcmonth= root.element("version").getText();
		System.out.println(jcmonth);*/
	}
}

class MyEntityResolver implements EntityResolver {
    public InputSource resolveEntity(String publicId, String systemId) {
        return new InputSource(new StringBufferInputStream(""));
    }
}
