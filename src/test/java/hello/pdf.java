package hello;

import com.hcb.xigou.util.DES;


public class pdf {

	public static void main(String[] args) {
//		Document document = new Document(PageSize.A4, 10, 10, 10, 10); 
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setHeader("Content-Disposition",
//				"attachment; filename=export.pdf");
//		response.setContentType("text/plain;charset=GB2312");
//		response.setContentType("application/pdf");    
//		PdfPTable  table = new PdfPTable(12);//设置列数为3列
//		table.setWidthPercentage(100);
//		   table.setWidthPercentage(100);
//		   table.addCell(new Paragraph("学号"));
//			    			//上面两个代码是设置单元格的间距    			
//			    			/*Cell cell = new Cell(new Paragraph("案例信息"));   //设置表头的名称
//			    			cell.setHeader(true);//是将该单元格作为表头信息显示
//			    			cell.setColspan(12);//指定了该单元格占4列，为表格添加表头信息时
//			    			table.addCell(cell);
//			    			table.endHeaders();	*/		
//			    			table.addCell(new Paragraph("案例代号"));
//			    			table.addCell(new Paragraph("案例名称"));
//			    			table.addCell(new Paragraph("案例状态"));
//			    			table.addCell(new Paragraph("开启日期"));
//			    			table.addCell(new Paragraph("产品线名称"));
//			    			table.addCell(new Paragraph("通路名称"));
//			    			table.addCell(new Paragraph("销售区域名称"));
//			    			table.addCell(new Paragraph("联系人"));
//			    			table.addCell(new Paragraph("公司"));
//			    			table.addCell(new Paragraph("案例型态"));
//			    			table.addCell(new Paragraph("附件数量"));
//			    			table.addCell(new Paragraph("备注"));
//		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//		    			PdfWriter.getInstance(document, buffer);   					
//		    			document.open();
//		    			document.add(table);
//		    			document.close();
//		    			response.setContentLength(buffer.size());//测试
//		    			ServletOutputStream out = response.getOutputStream();
//		    			buffer.writeTo(out);
//		        		out.flush();  
//		        		out.close();
		try {
//			System.out.println(DES.encrypt("gc_conan@163.com"));
			System.out.println(java.net.URLEncoder.encode("hY7XinZXzWwf2LvFSnn24+G61jgypgj/"));
			System.out.println(java.net.URLDecoder.decode("hY7XinZXzWwf2LvFSnn24+G61jgypgj/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void exportCVSFile(String str) throws IOException {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setHeader("Content-Disposition",
//				"attachment; filename=export.pdf");
//		response.setContentType("text/plain;charset=GB2312");
//		PrintWriter out = response.getWriter();
//		out.write(str);
//		out.flush();
//		out.close();
//	}

}
