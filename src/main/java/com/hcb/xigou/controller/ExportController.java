package com.hcb.xigou.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.dto.Coupons;
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.dto.Managers;
import com.hcb.xigou.dto.Orders;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.dto.UserOrders;
import com.hcb.xigou.dto.UserRechargers;
import com.hcb.xigou.pojo.ActivityExcelport;
import com.hcb.xigou.pojo.BannersExcelport;
import com.hcb.xigou.pojo.UserActivityExcelport;
import com.hcb.xigou.pojo.popularActivityExcelport;
import com.hcb.xigou.pojo.GoodsExcelport;
import com.hcb.xigou.pojo.GoodsWithBLOBs;
import com.hcb.xigou.pojo.CouponExcelport;
import com.hcb.xigou.pojo.CategoryExcelport;
import com.hcb.xigou.pojo.UserRechargersExcelport;
import com.hcb.xigou.pojo.Users;
import com.hcb.xigou.pojo.UserListExcelport;
import com.hcb.xigou.pojo.MemberExcelport;
import com.hcb.xigou.pojo.OrderExcelport;
import com.hcb.xigou.pojo.ManagersExcelport;
import com.hcb.xigou.pojo.CouponsActivityExcelport;
import com.hcb.xigou.pojo.PackagesExcelport;
import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IActivityZonesService;
import com.hcb.xigou.service.IBannersService;
import com.hcb.xigou.service.ICouponsService;
import com.hcb.xigou.service.IFirstCategorysService;
import com.hcb.xigou.service.IManagersService;
import com.hcb.xigou.service.IOrdersService;
import com.hcb.xigou.service.IPackagesService;
import com.hcb.xigou.service.IUserService;
import com.hcb.xigou.service.UserActivityService;
import com.hcb.xigou.service.UserOrdersService;
import com.hcb.xigou.service.UserRechargersService;
import com.hcb.xigou.util.EmojiConvert;
import com.hcb.xigou.util.StringToDate;

/** 
 * 利用开源组件POI3.0.2动态导出EXCEL文档 转载时请保留以下信息，注明出处！ 
 *  
 * @author leno 
 * @version v1.0 
 * @param <T> 
 *            应用泛型，代表任意一个符合javabean风格的类 
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx() 
 *            byte[]表jpg格式的图片数据 
 */  

@RestController
@CrossOrigin
@RequestMapping("export")
public class ExportController<T> {
	@Autowired
	GoodsService goodsService;
	@Autowired
	IBannersService bannersService;
	@Autowired
	IActivityZonesService activityZonesService; 
	@Autowired
	IUserService userService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	IFirstCategorysService firstCategorysService;
	@Autowired
	ICouponsService couponsService;
	@Autowired
	IPackagesService packagesService;
	@Autowired
	IOrdersService ordersService;
	@Autowired
	UserOrdersService userOrdersService;
	@Autowired
	UserRechargersService userRechargersService;
	@Autowired
	IManagersService managersService;
	
	//商品管理
	@RequestMapping("GoodsExcelport")
	@ResponseBody
	public void GoodsExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model,@RequestParam(required = false) String good_name,
			@RequestParam(required = false) String firt_category_name,@RequestParam(required = false) String good_status,
			@RequestParam(required = false) String minPrice,@RequestParam(required = false) String maxPrice){
		Map<String, Object> map = new HashMap<String,Object>();
		List<GoodsWithBLOBs> list = new ArrayList<GoodsWithBLOBs>();
		if(!"".equals(good_name) && good_name != null){
			map.put("goodName", good_name);
		}
		if(!"".equals(firt_category_name) && firt_category_name != null){
			map.put("firtCategoryName", firt_category_name);
		}
		if(!"".equals(good_status) && good_status != null){
			map.put("goodStatus", good_status);
		}
		if(!"".equals(minPrice) && minPrice != null){
			map.put("minPrice", minPrice);
		}
		if(!"".equals(maxPrice) && maxPrice != null){
			map.put("maxPrice", maxPrice);
		}
		list = goodsService.searchGoodsExcelByMap(map);
		List<GoodsExcelport> exportList= new ArrayList<GoodsExcelport>();
		for(GoodsWithBLOBs exList:list ){
			if(exList != null){
				GoodsExcelport excelport= new GoodsExcelport();
				try {
					excelport.setGoodName(EmojiConvert.emojiRecovery2(exList.getGoodName()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				excelport.setGoodUuid(exList.getGoodUuid());
				excelport.setUnitPrice(exList.getUnitPrice());
				excelport.setModels(exList.getModels());
				excelport.setFirtCategoryName(exList.getFirtCategoryName());
				if(exList.getGoodStatus().equals("up")){
					excelport.setGoodStatus("上架");	
				}else{
					excelport.setGoodStatus("下架");
				}
				exportList.add(excelport);
			}
		}
		ExportController<GoodsExcelport> ex =new ExportController<GoodsExcelport>();
		 String[] headers =  
		        { "商品ID", "名称", "规格", "分类", "价格/单位", "是否上架"}; 
		 List<GoodsExcelport> dataset = new ArrayList<GoodsExcelport>();
		 for(GoodsExcelport export:exportList){
	        	dataset.add(export);  
	        } 
	        try  
	        {         
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = new Date();
	    		String fileName = format.format(date);
	    		
	    		String path = "C:/Users/俞旭东/Desktop/xigou/file/"+fileName+".xls";
	            OutputStream out = new FileOutputStream(path);   
	            ex.exportExcel(headers, dataset, out);  
	            out.close();   
	            File file = new File(path);  
	            // 取得文件名。  
	            String filename = file.getName();  
	            // 以流的形式下载文件。  
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	            byte[] buffer = new byte[fis.available()];  
	            fis.read(buffer);  
	            fis.close();  
	            // 清空response  
	            res.reset();  
	            // 设置response的Header  
	            res.addHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes()));  
	            res.addHeader("Content-Length", "" + file.length());  
	            OutputStream toClient = new BufferedOutputStream(  
	            		res.getOutputStream());  
	            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
	            toClient.write(buffer);  
	            toClient.flush();  
	            toClient.close();
	        	if (file.isFile() && file.exists()) {
					file.delete();
				}
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	}
	//banner管理	
	@RequestMapping("BannersExcelport")
	@ResponseBody
	public void BannersExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model,@RequestParam(required = false) String banner_name){
		Map<String, Object> map = new HashMap<String,Object>();
		List<Banners> list = new ArrayList<Banners>();
		if(!"".equals(banner_name) && banner_name != null){
			map.put("bannerName", banner_name);
		}
		list = bannersService.searchBannerExcelByMap(map);
		List<BannersExcelport> exportList= new ArrayList<BannersExcelport>();
		for(Banners exList:list ){
			if(exList != null){
				BannersExcelport excelport= new BannersExcelport();
				try {
					excelport.setBannerName(EmojiConvert.emojiRecovery2(exList.getBannerName()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				excelport.setBannerUuid(exList.getBannerUuid());
				if(exList.getBannerStatus().equals(2)){
					excelport.setBannerStatus("未使用");	
				}else{
					excelport.setBannerStatus("已使用");
				}
				exportList.add(excelport);
			}
		}
		ExportController<BannersExcelport> ex =new ExportController<BannersExcelport>();
		 String[] headers =  
		        { "bannerID", "名称", "状态"}; 
		 List<BannersExcelport> dataset = new ArrayList<BannersExcelport>();
		 for(BannersExcelport export:exportList){
	        	dataset.add(export);  
	        } 
	        try  
	        {         
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = new Date();
	    		String fileName = format.format(date);
	    		
	    		String path = "C:/Users/俞旭东/Desktop/xigou/file/"+fileName+".xls";
	            OutputStream out = new FileOutputStream(path);   
	            ex.exportExcel(headers, dataset, out);  
	            out.close();   
	            File file = new File(path);  
	            // 取得文件名。  
	            String filename = file.getName();  
	            // 以流的形式下载文件。  
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	            byte[] buffer = new byte[fis.available()];  
	            fis.read(buffer);  
	            fis.close();  
	            // 清空response  
	            res.reset();  
	            // 设置response的Header  
	            res.addHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes()));  
	            res.addHeader("Content-Length", "" + file.length());  
	            OutputStream toClient = new BufferedOutputStream(  
	            		res.getOutputStream());  
	            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
	            toClient.write(buffer);  
	            toClient.flush();  
	            toClient.close();
	        	if (file.isFile() && file.exists()) {
					file.delete();
				}
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	}
	//人气推荐
	@RequestMapping("popularActivityExcelport")
	@ResponseBody
	public void popularActivityExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model,@RequestParam(required = false) String good_name,
			@RequestParam(required = false) String good_code,@RequestParam(required = false) String good_status){
		Map<String, Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		if(!"".equals(good_name) && good_name != null){
			map.put("goodName", good_name);
		}
		if(!"".equals(good_code) && good_code != null){
			map.put("goodCode", good_code);
		}
		if(!"".equals(good_status) && good_status != null){
			map.put("goodStatus", good_status);
		}
		list = activityZonesService.selectBypopularActivityExcelport(map);
		List<popularActivityExcelport> exportList= new ArrayList<popularActivityExcelport>();
		for(Map<String, Object> exList:list ){
			if(exList != null){
				popularActivityExcelport excelport= new popularActivityExcelport();
				excelport.setGoodName(String.valueOf(exList.get("good_name")));
				if(exList.get("is_stop").equals("1")){
					excelport.setIs_stop("上线");
				}else{
					excelport.setIs_stop("下线");
				}
				exportList.add(excelport);
			}
		}
		ExportController<popularActivityExcelport> ex =new ExportController<popularActivityExcelport>();
		 String[] headers =  
		        { "名称", "状态"}; 
		 List<popularActivityExcelport> dataset = new ArrayList<popularActivityExcelport>();
		 for(popularActivityExcelport export:exportList){
	        	dataset.add(export);  
	        } 
	        try  
	        {         
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = new Date();
	    		String fileName = format.format(date);
	    		
	    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
	            OutputStream out = new FileOutputStream(path);   
	            ex.exportExcel(headers, dataset, out);  
	            out.close();   
	            File file = new File(path);  
	            // 取得文件名。  
	            String filename = file.getName();  
	            // 以流的形式下载文件。  
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	            byte[] buffer = new byte[fis.available()];  
	            fis.read(buffer);  
	            fis.close();  
	            // 清空response  
	            res.reset();  
	            // 设置response的Header  
	            res.addHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes()));  
	            res.addHeader("Content-Length", "" + file.length());  
	            OutputStream toClient = new BufferedOutputStream(  
	            		res.getOutputStream());  
	            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
	            toClient.write(buffer);  
	            toClient.flush();  
	            toClient.close();
	        	if (file.isFile() && file.exists()) {
					file.delete();
				}
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	}
	//活动模块..
	@RequestMapping("ActivityExcelport")
	@ResponseBody
	public void ActivityExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model){
		Map<String, Object> map = new HashMap<String,Object>();
		List<ActivityZones> list = new ArrayList<ActivityZones>();
		list = activityZonesService.searchActivityExcelport(map);
		List<ActivityExcelport> exportList= new ArrayList<ActivityExcelport>();
		for(ActivityZones exList:list ){
			if(exList != null){
				ActivityExcelport excelport= new ActivityExcelport();
				if(exList.getTitle() != null){
					excelport.setTitle(exList.getTitle());
				}else{
					excelport.setTitle("");
				}		
				SimpleDateFormat simpleDateFormat;
				simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String startTime = simpleDateFormat.format(exList.getStartTime());
				String endTime = simpleDateFormat.format(exList.getEndTime());
				String openTime = "";
				if(exList.getOpenTime() != null ){
					openTime = simpleDateFormat.format(exList.getOpenTime());
				}
				excelport.setStartTime(startTime);
				excelport.setEndTime(endTime);
				excelport.setOpenTime(openTime);
				if(exList.getIsOpen() != null){
					if(exList.getIsOpen().equals(1)){
						excelport.setIsOpen("开始");	
					}else{
						excelport.setIsOpen("关闭");
					}
				}else{
					excelport.setIsOpen("");
				}
				
				SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				if(exList.getEndTime()!=null&&exList.getOpenTime()!=null){
					String fromDate = simpleFormat.format(exList.getOpenTime());  
					String toDate = simpleFormat.format(exList.getEndTime());  
					try {
						long from = simpleFormat.parse(fromDate).getTime();
						long to = simpleFormat.parse(toDate).getTime();
						if(to>from){
							int m = (int) ((to - from)/(1000)); 
							int day = m/60/60/24;
							int hh = (m - day*60*60*24)/60/60;
							int mm =((m - day*60*60*24) - hh*60*60)/60;
							String str =day+"/"+hh+"/"+mm;
							exList.setSurplusTime(str);
						}else{
							fromDate = simpleFormat.format(exList.getStartTime());  
							toDate = simpleFormat.format(exList.getEndTime());  
							try {
								from = simpleFormat.parse(fromDate).getTime();
								to = simpleFormat.parse(toDate).getTime();  
								int m = (int) ((to - from)/(1000)); 
								int day = m/60/60/24;
								int hh = (m - day*60*60*24)/60/60;
								int mm =((m - day*60*60*24) - hh*60*60)/60;
								String str =day+"/"+hh+"/"+mm;
								excelport.setCountdown(str);;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					String fromDate = simpleFormat.format(exList.getStartTime());  
					String toDate = simpleFormat.format(exList.getEndTime());  
					try {
						long from = simpleFormat.parse(fromDate).getTime();
						long to = simpleFormat.parse(toDate).getTime();  
						int m = (int) ((to - from)/(1000)); 
						int day = m/60/60/24;
						int hh = (m - day*60*60*24)/60/60;
						int mm =((m - day*60*60*24) - hh*60*60)/60;
						String str =day+"/"+hh+"/"+mm;
						excelport.setCountdown(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				excelport.setPosition(String.valueOf(exList.getPosition()));
				if(exList.getIsStop().equals("1")){
					excelport.setIsStop("上线");
				}else{
					excelport.setIsStop("下线");
				}
				exportList.add(excelport);
			}
		}
		ExportController<ActivityExcelport> ex =new ExportController<ActivityExcelport>();
		 String[] headers =  
		        { "活动名称", "活动开始时间", "活动结束时间", "倒计时开始时间", "活动倒计时是否开启", "活动倒计时结束", "活动位置", "状态"}; 
		 List<ActivityExcelport> dataset = new ArrayList<ActivityExcelport>();
		 for(ActivityExcelport export:exportList){
	        	dataset.add(export);  
	        } 
	        try  
	        {         
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = new Date();
	    		String fileName = format.format(date);
	    		
	    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
	            OutputStream out = new FileOutputStream(path);   
	            ex.exportExcel(headers, dataset, out);  
	            out.close();   
	            File file = new File(path);  
	            // 取得文件名。  
	            String filename = file.getName();  
	            // 以流的形式下载文件。  
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	            byte[] buffer = new byte[fis.available()];  
	            fis.read(buffer);  
	            fis.close();  
	            // 清空response  
	            res.reset();  
	            // 设置response的Header  
	            res.addHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes()));  
	            res.addHeader("Content-Length", "" + file.length());  
	            OutputStream toClient = new BufferedOutputStream(  
	            		res.getOutputStream());  
	            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
	            toClient.write(buffer);  
	            toClient.flush();  
	            toClient.close();
	        	if (file.isFile() && file.exists()) {
					file.delete();
				}
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	}
	//热销精选
	@RequestMapping("UserActivityExcelport")
	@ResponseBody
	public void UserActivityExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model){
		Map<String, Object> map = new HashMap<String,Object>();
		List<UserActivity> list = new ArrayList<UserActivity>();
		list = userActivityService.searchUserActivityExcelportByMap(map);
		List<UserActivityExcelport> exportList= new ArrayList<UserActivityExcelport>();
		for(UserActivity exList:list ){
			if(exList != null){
				UserActivityExcelport excelport= new UserActivityExcelport();
				
				excelport.setTitle(exList.getTitle());

				if(exList.getIsStop().equals("1")){
					excelport.setIsStop("上线");
				}else{
					excelport.setIsStop("下线");
				}
				exportList.add(excelport);
			}
		}
		ExportController<UserActivityExcelport> ex =new ExportController<UserActivityExcelport>();
		 String[] headers =  
		        { "标题", "状态"}; 
		 List<UserActivityExcelport> dataset = new ArrayList<UserActivityExcelport>();
		 for(UserActivityExcelport export:exportList){
	        	dataset.add(export);  
	        } 
	        try  
	        {         
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = new Date();
	    		String fileName = format.format(date);
	    		
	    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
	            OutputStream out = new FileOutputStream(path);   
	            ex.exportExcel(headers, dataset, out);  
	            out.close();   
	            File file = new File(path);  
	            // 取得文件名。  
	            String filename = file.getName();  
	            // 以流的形式下载文件。  
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	            byte[] buffer = new byte[fis.available()];  
	            fis.read(buffer);  
	            fis.close();  
	            // 清空response  
	            res.reset();  
	            // 设置response的Header  
	            res.addHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes()));  
	            res.addHeader("Content-Length", "" + file.length());  
	            OutputStream toClient = new BufferedOutputStream(  
	            		res.getOutputStream());  
	            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
	            toClient.write(buffer);  
	            toClient.flush();  
	            toClient.close();
	        	if (file.isFile() && file.exists()) {
					file.delete();
				}
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	}
	//商品分类
	@RequestMapping("CategoryExcelport")
	@ResponseBody
	public void CategoryExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model){
		Map<String, Object> map = new HashMap<String,Object>();
		List<FirstCategorys> list = new ArrayList<FirstCategorys>();
		list = firstCategorysService.searchCategoryExcelportByMap(map);
		List<CategoryExcelport> exportList= new ArrayList<CategoryExcelport>();
		for(FirstCategorys exList:list ){
			if(exList != null){
				CategoryExcelport excelport= new CategoryExcelport();
			    excelport.setCategory_name(exList.getCategoryName());

				exportList.add(excelport);
			}
		}
		ExportController<CategoryExcelport> ex =new ExportController<CategoryExcelport>();
		 String[] headers =  
		        { "类别"}; 
		 List<CategoryExcelport> dataset = new ArrayList<CategoryExcelport>();
		 for(CategoryExcelport export:exportList){
	        	dataset.add(export);  
	        } 
	        try  
	        {         
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = new Date();
	    		String fileName = format.format(date);
	    		
	    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
	            OutputStream out = new FileOutputStream(path);   
	            ex.exportExcel(headers, dataset, out);  
	            out.close();   
	            File file = new File(path);  
	            // 取得文件名。  
	            String filename = file.getName();  
	            // 以流的形式下载文件。  
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	            byte[] buffer = new byte[fis.available()];  
	            fis.read(buffer);  
	            fis.close();  
	            // 清空response  
	            res.reset();  
	            // 设置response的Header  
	            res.addHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes()));  
	            res.addHeader("Content-Length", "" + file.length());  
	            OutputStream toClient = new BufferedOutputStream(  
	            		res.getOutputStream());  
	            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
	            toClient.write(buffer);  
	            toClient.flush();  
	            toClient.close();
	        	if (file.isFile() && file.exists()) {
					file.delete();
				}
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	}
	//首页优惠活动区
	@RequestMapping("CouponsActivityExcelport")
	@ResponseBody
	public void CouponsActivityExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model){
			Map<String, Object> map = new HashMap<String,Object>();
			List<ActivityZones> list = new ArrayList<ActivityZones>();
			list = activityZonesService.searchActivityExcelportByMap(map);
			List<CouponsActivityExcelport> exportList= new ArrayList<CouponsActivityExcelport>();
			for(ActivityZones exList:list ){
				if(exList != null){
					CouponsActivityExcelport excelport= new CouponsActivityExcelport();
					excelport.setTitle(exList.getTitle());

					if(exList.getIsStop() == "1"){
						excelport.setIsStop("上线");
					}else{
						excelport.setIsStop("下线");
					}
					exportList.add(excelport);
				}
			}
			ExportController<CouponsActivityExcelport> ex =new ExportController<CouponsActivityExcelport>();
			 String[] headers =  
			        { "类别名称","状态"}; 
			 List<CouponsActivityExcelport> dataset = new ArrayList<CouponsActivityExcelport>();
			 for(CouponsActivityExcelport export:exportList){
		        	dataset.add(export);  
		        } 
		        try  
		        {         
		        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    		Date date = new Date();
		    		String fileName = format.format(date);
		    		
		    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
		            OutputStream out = new FileOutputStream(path);   
		            ex.exportExcel(headers, dataset, out);  
		            out.close();   
		            File file = new File(path);  
		            // 取得文件名。  
		            String filename = file.getName();  
		            // 以流的形式下载文件。  
		            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
		            byte[] buffer = new byte[fis.available()];  
		            fis.read(buffer);  
		            fis.close();  
		            // 清空response  
		            res.reset();  
		            // 设置response的Header  
		            res.addHeader("Content-Disposition", "attachment;filename="  
		                    + new String(filename.getBytes()));  
		            res.addHeader("Content-Length", "" + file.length());  
		            OutputStream toClient = new BufferedOutputStream(  
		            		res.getOutputStream());  
		            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
		            toClient.write(buffer);  
		            toClient.flush();  
		            toClient.close();
		        	if (file.isFile() && file.exists()) {
						file.delete();
					}
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        } 
		}
	//优惠劵管理
	@RequestMapping("CouponExcelport")
	@ResponseBody
	public void CouponExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model){
				Map<String, Object> map = new HashMap<String,Object>();
				List<Coupons> list = new ArrayList<Coupons>();
				list = couponsService.searchCouponExcelportByMap(map);
				List<CouponExcelport> exportList= new ArrayList<CouponExcelport>();
				for(Coupons exList:list ){
					if(exList != null){
						CouponExcelport excelport= new CouponExcelport();

						excelport.setTitle(exList.getTitle());

						excelport.setPlayTime(exList.getGrantTime()+"—"+exList.getFailTime());
						if(exList.getType() != null){
							if(exList.getType().equals("1")){
								excelport.setType("app专用");
							}	
						}
						else{
							excelport.setType("");
						}
						excelport.setAmount(exList.getAmount());
						excelport.setUrl(exList.getUrl());
						if(exList.getCouponStatus().equals("0")){
							excelport.setCouponStatus("未使用");
						}else if(exList.getCouponStatus().equals("1")){
							excelport.setCouponStatus("已使用");
						}else if(exList.getCouponStatus().equals("2")){
							excelport.setCouponStatus("已使用");
						}else{
							excelport.setCouponStatus("");
						}
						if(exList.getIsGrant().equals("1")){
							excelport.setIsGrant("未发放");
						}else if(exList.getIsGrant().equals("2")){
							excelport.setIsGrant("已发放");
						}else{
							excelport.setIsGrant("");
						}
						excelport.setCouponStock(exList.getCouponStock());
						exportList.add(excelport);
					}
				}
				ExportController<CouponExcelport> ex =new ExportController<CouponExcelport>();
				 String[] headers =  
				        { "优惠券名称","使用时间","使用条件","面额","领取链接","状态","是否发放","库存"}; 
				 List<CouponExcelport> dataset = new ArrayList<CouponExcelport>();
				 for(CouponExcelport export:exportList){
			        	dataset.add(export);  
			        } 
			        try  
			        {         
			        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    		Date date = new Date();
			    		String fileName = format.format(date);
			    		
			    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
			            OutputStream out = new FileOutputStream(path);   
			            ex.exportExcel(headers, dataset, out);  
			            out.close();   
			            File file = new File(path);  
			            // 取得文件名。  
			            String filename = file.getName();  
			            // 以流的形式下载文件。  
			            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
			            byte[] buffer = new byte[fis.available()];  
			            fis.read(buffer);  
			            fis.close();  
			            // 清空response  
			            res.reset();  
			            // 设置response的Header  
			            res.addHeader("Content-Disposition", "attachment;filename="  
			                    + new String(filename.getBytes()));  
			            res.addHeader("Content-Length", "" + file.length());  
			            OutputStream toClient = new BufferedOutputStream(  
			            		res.getOutputStream());  
			            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
			            toClient.write(buffer);  
			            toClient.flush();  
			            toClient.close();
			        	if (file.isFile() && file.exists()) {
							file.delete();
						}
			        } catch (FileNotFoundException e) {  
			            e.printStackTrace();  
			        } catch (IOException e) {  
			            e.printStackTrace();  
			        } 
			}
	//新人礼包
	@RequestMapping("PackagesExcelport")
	@ResponseBody
	public void PackagesExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model){
					Map<String, Object> map = new HashMap<String,Object>();
					List<Map<String, Object>> list = new ArrayList<>();
					list = packagesService.selectByPackagesExcelport(map);
					List<PackagesExcelport> exportList= new ArrayList<PackagesExcelport>();
					for(Map<String, Object> exList:list ){
						if(exList != null){
							PackagesExcelport excelport= new PackagesExcelport();
							excelport.setTitle(String.valueOf(exList.get("title")));
							excelport.setCouponNumbers(String.valueOf(exList.get("coupon_numbers")));
							excelport.setPackageStatus(String.valueOf(exList.get("packeage_stock")));
							excelport.setPackageStatus(String.valueOf(exList.get("package_status")));
							exportList.add(excelport);
						}
					}
					ExportController<PackagesExcelport> ex =new ExportController<PackagesExcelport>();
					 String[] headers =  
					        { "礼包名称","劵数","总余量","状态"}; 
					 List<PackagesExcelport> dataset = new ArrayList<PackagesExcelport>();
					 for(PackagesExcelport export:exportList){
				        	dataset.add(export);  
				        } 
				        try  
				        {         
				        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				    		Date date = new Date();
				    		String fileName = format.format(date);
				    		
				    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
				            OutputStream out = new FileOutputStream(path);   
				            ex.exportExcel(headers, dataset, out);  
				            out.close();   
				            File file = new File(path);  
				            // 取得文件名。  
				            String filename = file.getName();  
				            // 以流的形式下载文件。  
				            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
				            byte[] buffer = new byte[fis.available()];  
				            fis.read(buffer);  
				            fis.close();  
				            // 清空response  
				            res.reset();  
				            // 设置response的Header  
				            res.addHeader("Content-Disposition", "attachment;filename="  
				                    + new String(filename.getBytes()));  
				            res.addHeader("Content-Length", "" + file.length());  
				            OutputStream toClient = new BufferedOutputStream(  
				            		res.getOutputStream());  
				            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
				            toClient.write(buffer);  
				            toClient.flush();  
				            toClient.close();
				        	if (file.isFile() && file.exists()) {
								file.delete();
							}
				        } catch (FileNotFoundException e) {  
				            e.printStackTrace();  
				        } catch (IOException e) {  
				            e.printStackTrace();  
				        } 
				}
	//app订单管理
	@RequestMapping("OrderExcelport")
	@ResponseBody
	public void OrderExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model,
			@RequestParam(required = false) String order_number,@RequestParam(required = false) String nickname,
			@RequestParam(required = false) String startTime,@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String pay_status,@RequestParam(required = false) String member_card_number){
						Map<String, Object> map = new HashMap<String,Object>();
						List<UserOrders> list = new ArrayList<UserOrders>();
						if(!"".equals(order_number) && order_number != null){
							map.put("orderNumber", order_number);
						}
						if(!"".equals(member_card_number) && member_card_number != null){
							map.put("memberCardNumber", member_card_number);
						}
						if(!"".equals(nickname) && nickname != null){
							map.put("nickname", nickname);
						}
						if(!"".equals(startTime) && startTime != null){
							map.put("startTime", startTime);
						}
						if(!"".equals(endTime) && endTime != null){
							map.put("endTime", endTime);
						}
						if(!"".equals(pay_status) && pay_status != null){
							map.put("payStatus", pay_status);
						}
						list = userOrdersService.searchOrderExcelportByMap(map);
						List<OrderExcelport> exportList= new ArrayList<OrderExcelport>();
						for(UserOrders exList:list ){
							if(exList != null){
								OrderExcelport excelport= new OrderExcelport();
								excelport.setMemberCardNumber(String.valueOf(exList.getMemberCardNumber()));
								excelport.setOrderNumber(String.valueOf(exList.getOrderNumber()));
								excelport.setCreateDatetime(String.valueOf(exList.getCreateDatetime()));
								excelport.setTotalMoney(String.valueOf(exList.getTotalMoney()));
								if(exList.getPayStatus().equals("1")){
									excelport.setPayStatus("待付款");
								}else if(exList.getPayStatus().equals("2")){
									excelport.setPayStatus("待配送");
								}else if(exList.getPayStatus().equals("3")){
									excelport.setPayStatus("配送中");
								}else if(exList.getPayStatus().equals("4")){
									excelport.setPayStatus("退换货");
								}else if(exList.getPayStatus().equals("5")){
									excelport.setPayStatus("删除");
								}else if(exList.getPayStatus().equals("6")){
									excelport.setPayStatus("交易关闭");
								}else{
									excelport.setPayStatus("已完成");
								}
								excelport.setPaidMoney(String.valueOf(exList.getPaidMoney()));
								if(exList.getPayWay().equals("alipay")){
									excelport.setPayWay("支付宝");
								}else if(exList.getPayWay().equals("wechat")){
									excelport.setPayWay("微信");
								}else if(exList.getPayWay().equals("balance")){
									excelport.setPayWay("余额");
								}else if(exList.getPayWay().equals("toPay")){
									excelport.setPayWay("到付");
								}else if(exList.getPayWay().equals("cash")){
									excelport.setPayWay("现金");
								}else if(exList.getPayWay().equals("card")){
									excelport.setPayWay("刷卡");
								}
								if(exList.getType().equals("shopping")){
									excelport.setType("商城购");
								}else if(exList.getType().equals("trust")){
									excelport.setType("信任购");
								}else if(exList.getType().equals("scan")){
									excelport.setType("扫码");
								}
								excelport.setUserUuid(String.valueOf(exList.getUserUuid()));
								excelport.setNickname(String.valueOf(exList.getNickname()));
								exportList.add(excelport);
							}
						}
						ExportController<OrderExcelport> ex =new ExportController<OrderExcelport>();
						 String[] headers =  
						        { "会员卡","订单号","创建时间","订单总金额","订单状态","实付金额","支付方式","分类","用户uuid","用户昵称"}; 
						 List<OrderExcelport> dataset = new ArrayList<OrderExcelport>();
						 for(OrderExcelport export:exportList){
					        	dataset.add(export);  
					        } 
					        try  
					        {         
					        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					    		Date date = new Date();
					    		String fileName = format.format(date);
					    		
					    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
					            OutputStream out = new FileOutputStream(path);   
					            ex.exportExcel(headers, dataset, out);  
					            out.close();   
					            File file = new File(path);  
					            // 取得文件名。  
					            String filename = file.getName();  
					            // 以流的形式下载文件。  
					            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
					            byte[] buffer = new byte[fis.available()];  
					            fis.read(buffer);  
					            fis.close();  
					            // 清空response  
					            res.reset();  
					            // 设置response的Header  
					            res.addHeader("Content-Disposition", "attachment;filename="  
					                    + new String(filename.getBytes()));  
					            res.addHeader("Content-Length", "" + file.length());  
					            OutputStream toClient = new BufferedOutputStream(  
					            		res.getOutputStream());  
					            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
					            toClient.write(buffer);  
					            toClient.flush();  
					            toClient.close();
					        	if (file.isFile() && file.exists()) {
									file.delete();
								}
					        } catch (FileNotFoundException e) {  
					            e.printStackTrace();  
					        } catch (IOException e) {  
					            e.printStackTrace();  
					        } 
					}
	//充值管理
	@RequestMapping("UserRechargersExcelport")
	@ResponseBody
	public void UserRechargersExcelport(HttpServletRequest req, HttpServletResponse res, ModelMap model,
			@RequestParam(required = false) String name,@RequestParam(required = false) String nickname,
			@RequestParam(required = false) String phone,@RequestParam(required = false) String third_way,
			@RequestParam(required = false) String startTime,@RequestParam(required = false) String endTime){
					Map<String, Object> map = new HashMap<String,Object>();
					List<UserRechargers> list = new ArrayList<UserRechargers>();
					if(!"".equals(name) && name != null){
						map.put("name", name);
					}
					if(!"".equals(nickname) && nickname != null){
						map.put("nickname", nickname);
					}
					if(!"".equals(third_way) && third_way != null){
						map.put("third_way", third_way);
					}
					if(!"".equals(startTime) && startTime != null){
						map.put("startTime", startTime);
					}
					if(!"".equals(endTime) && endTime != null){
						map.put("endTime", endTime);
					}
					if(!"".equals(phone) && phone != null){
						map.put("phone", phone);
					}
					list = userRechargersService.searchUserRechargersExcelportByMap(map);
					List<UserRechargersExcelport> exportList= new ArrayList<UserRechargersExcelport>();
					for(UserRechargers exList:list ){
						if(exList != null){
							UserRechargersExcelport excelport= new UserRechargersExcelport();
							excelport.setPayTime(String.valueOf(exList.getPayTime()));
							excelport.setName(String.valueOf(exList.getName()));
							excelport.setNickname(String.valueOf(exList.getNickname()));
							excelport.setPhone(String.valueOf(exList.getPhone()));
							excelport.setAmount(String.valueOf(exList.getAmount()));
							excelport.setThirdWay(String.valueOf(exList.getThirdWay()));
							exportList.add(excelport);
						}
					}
					ExportController<UserRechargersExcelport> ex =new ExportController<UserRechargersExcelport>();
					 String[] headers =  
					        { "充值时间","真实姓名","昵称","手机号码","充值金额","充值方式"}; 
					 List<UserRechargersExcelport> dataset = new ArrayList<UserRechargersExcelport>();
					 for(UserRechargersExcelport export:exportList){
				        	dataset.add(export);  
				        } 
				        try  
				        {         
				        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				    		Date date = new Date();
				    		String fileName = format.format(date);
				    		
				    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
				            OutputStream out = new FileOutputStream(path);   
				            ex.exportExcel(headers, dataset, out);  
				            out.close();   
				            File file = new File(path);  
				            // 取得文件名。  
				            String filename = file.getName();  
				            // 以流的形式下载文件。  
				            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
				            byte[] buffer = new byte[fis.available()];  
				            fis.read(buffer);  
				            fis.close();  
				            // 清空response  
				            res.reset();  
				            // 设置response的Header  
				            res.addHeader("Content-Disposition", "attachment;filename="  
				                    + new String(filename.getBytes()));  
				            res.addHeader("Content-Length", "" + file.length());  
				            OutputStream toClient = new BufferedOutputStream(  
				            		res.getOutputStream());  
				            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
				            toClient.write(buffer);  
				            toClient.flush();  
				            toClient.close();
				        	if (file.isFile() && file.exists()) {
								file.delete();
							}
				        } catch (FileNotFoundException e) {  
				            e.printStackTrace();  
				        } catch (IOException e) {  
				            e.printStackTrace();  
				        } 
				}
	//用户管理
	@RequestMapping("UserListExcelport")
	@ResponseBody
	public void UserListExcelport(HttpServletRequest req, HttpServletResponse res,@RequestParam(required = false) String name,
			@RequestParam(required = false) String nickname,@RequestParam(required = false) String phone,
			@RequestParam(required = false) String money_start,@RequestParam(required = false) String money_end,
			@RequestParam(required = false) String register_time,@RequestParam(required = false) Integer number_start,
			@RequestParam(required = false) Integer number_end)throws UnsupportedEncodingException{
		    req.setCharacterEncoding("UTF-8");
						Map<String, Object> map = new HashMap<String,Object>();
						List<Users> list = new ArrayList<Users>();
						if(name != null && !name.equals("")){
						    map.put("name", name);	
						}
						if(nickname != null && !nickname.equals("")){
							map.put("nickname", nickname);			
						}
						if(phone != null && !phone.equals("")){
							map.put("phone", phone);
						}
						if(money_start != null && !money_start.equals("")){
							map.put("moneyStart", money_start);
						}
						if(money_end != null && !money_end.equals("")){
							map.put("moneyEnd", money_end);
						}
						if(register_time != null && !register_time.equals("")){
							map.put("firstDay", StringToDate.dateToString(Timestamp.from(Instant.now()))+" "+"00:00:00");
							map.put("lastDay", StringToDate.dateToString(Timestamp.from(Instant.now()))+" "+"23:59:59");
						}
						if(number_start != null){
							map.put("numberStart", number_start);
						}
						if(number_end != null){
							map.put("numberEnd", number_end);
						}
						list = userService.selectByUserListExcelport(map);
						List<UserListExcelport> exportList= new ArrayList<UserListExcelport>();
						for(Users exList:list ){
							if(exList != null){
								UserListExcelport excelport= new UserListExcelport();
								excelport.setNickname(exList.getNickname());
								excelport.setName(exList.getName());
								excelport.setPhone(exList.getPhone());
								excelport.setOrderNumber(exList.getOrderNumber());
								excelport.setTotalMoney(String.valueOf(exList.getTotalMoney()));
								excelport.setBalance(String.valueOf(exList.getBalance()));
								excelport.setLatelyTime(String.valueOf(exList.getLatelyTime()));
								excelport.setRegisterTime(String.valueOf(exList.getRegisterTime()));
								if(exList.getMemberCardNumber() != null){
									excelport.setMember("是");
									excelport.setMemberCardNumber(exList.getMemberCardNumber());
								}else{
									excelport.setMember("不是");
									excelport.setMemberCardNumber("");
								}
								excelport.setBirthday(String.valueOf(exList.getBirthday()));
								excelport.setArea(exList.getCountry()+""+exList.getProvince()+""+exList.getCity());
								excelport.setAddress(exList.getAddress());
								exportList.add(excelport);
							}
						}
						ExportController<UserListExcelport> ex =new ExportController<UserListExcelport>();
						 String[] headers =  
						{ "昵称","真实姓名","手机号码","购买次数","购买金额","账户余额","最近购物时间","注册时间","是否会员","会员卡号","生日","地区","收货地址"}; 
						 List<UserListExcelport> dataset = new ArrayList<UserListExcelport>();
						 for(UserListExcelport export:exportList){
					        	dataset.add(export);  
					        } 
					        try  
					        {         
					        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					    		Date date = new Date();
					    		String fileName = format.format(date);
					    		
					    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
					            OutputStream out = new FileOutputStream(path);   
					            ex.exportExcel(headers, dataset, out);  
					            out.close();   
					            File file = new File(path);  
					            // 取得文件名。  
					            String filename = file.getName();  
					            // 以流的形式下载文件。  
					            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
					            byte[] buffer = new byte[fis.available()];  
					            fis.read(buffer);  
					            fis.close();  
					            // 清空response  
					            res.reset();  
					            // 设置response的Header  
					            res.addHeader("Content-Disposition", "attachment;filename="  
					                    + new String(filename.getBytes()));  
					            res.addHeader("Content-Length", "" + file.length());  
					            OutputStream toClient = new BufferedOutputStream(  
					            		res.getOutputStream());  
					            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
					            toClient.write(buffer);  
					            toClient.flush();  
					            toClient.close();
					        	if (file.isFile() && file.exists()) {
									file.delete();
								}
					        } catch (FileNotFoundException e) {  
					            e.printStackTrace();  
					        } catch (IOException e) {  
					            e.printStackTrace();  
					        } 
					}
	//会员管理
	@RequestMapping("MemberExcelport")
	@ResponseBody
	public void MemberExcelport(HttpServletRequest req, HttpServletResponse res, 
			    @RequestParam(required = false) String name,@RequestParam(required = false) String nickname,
			    @RequestParam(required = false) String phone,@RequestParam(required = false) String grade,
				@RequestParam(required = false) Integer member_card_number)throws UnsupportedEncodingException{
			    req.setCharacterEncoding("UTF-8");
							Map<String, Object> map = new HashMap<String,Object>();
							List<Users> list = new ArrayList<Users>();
							if(name != null && !name.equals("")){
							    map.put("name", name);	
							}
							if(nickname != null && !nickname.equals("")){
								map.put("nickname", nickname);			
							}
							if(phone != null && !phone.equals("")){
								map.put("phone", phone);
							}
							if(grade != null && !grade.equals("")){
								map.put("grade", grade);
							}
							if(member_card_number != null && !member_card_number.equals("")){
								map.put("memberCardNumber", member_card_number);
							}
							list = userService.selectByMemberExcelport(map);
							List<MemberExcelport> exportList= new ArrayList<MemberExcelport>();
							for(Users exList:list ){
								if(exList != null){
									MemberExcelport excelport= new MemberExcelport();
									excelport.setNickname(exList.getNickname());
									excelport.setName(exList.getName());
									excelport.setPhone(exList.getPhone());
									excelport.setBalance(String.valueOf(exList.getBalance()));
									excelport.setRegisterTime(String.valueOf(exList.getRegisterTime()));
									excelport.setMember("是");
									excelport.setGrade(exList.getGrade());
									excelport.setMemberCardNumber(exList.getMemberCardNumber());
									excelport.setBirthday(String.valueOf(exList.getBirthday()));
									excelport.setArea(exList.getCountry()+""+exList.getProvince()+""+exList.getCity());
									excelport.setAddress(exList.getAddress());
									exportList.add(excelport);
								}
							}
							ExportController<MemberExcelport> ex =new ExportController<MemberExcelport>();
							 String[] headers =  
							{ "昵称","真实姓名","手机号码","账户余额","注册时间","是否会员","会员等级","会员卡号","生日","地区","收货地址"}; 
							 List<MemberExcelport> dataset = new ArrayList<MemberExcelport>();
							 for(MemberExcelport export:exportList){
						        	dataset.add(export);  
						        } 
						        try  
						        {         
						        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						    		Date date = new Date();
						    		String fileName = format.format(date);
						    		
						    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
						            OutputStream out = new FileOutputStream(path);   
						            ex.exportExcel(headers, dataset, out);  
						            out.close();   
						            File file = new File(path);  
						            // 取得文件名。  
						            String filename = file.getName();  
						            // 以流的形式下载文件。  
						            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
						            byte[] buffer = new byte[fis.available()];  
						            fis.read(buffer);  
						            fis.close();  
						            // 清空response  
						            res.reset();  
						            // 设置response的Header  
						            res.addHeader("Content-Disposition", "attachment;filename="  
						                    + new String(filename.getBytes()));  
						            res.addHeader("Content-Length", "" + file.length());  
						            OutputStream toClient = new BufferedOutputStream(  
						            		res.getOutputStream());  
						            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
						            toClient.write(buffer);  
						            toClient.flush();  
						            toClient.close();
						        	if (file.isFile() && file.exists()) {
										file.delete();
									}
						        } catch (FileNotFoundException e) {  
						            e.printStackTrace();  
						        } catch (IOException e) {  
						            e.printStackTrace();  
						        } 
						}	
	//权限组列表
	@RequestMapping("ManagersExcelport")
	@ResponseBody
	public void ManagersExcelport(HttpServletRequest req, HttpServletResponse res, 
				    @RequestParam(required = false) String group_name,@RequestParam(required = false) String nickname
				    )throws UnsupportedEncodingException{
				    req.setCharacterEncoding("UTF-8");
								Map<String, Object> map = new HashMap<String,Object>();
								List<Managers> list = new ArrayList<Managers>();
								if(group_name != null && !group_name.equals("")){
								    map.put("groupName", group_name);	
								}
								if(nickname != null && !nickname.equals("")){
									map.put("nickname", nickname);			
								}
								map.put("grade", "3");
								list = managersService.selectByManagersExcelport(map);
								List<ManagersExcelport> exportList= new ArrayList<ManagersExcelport>();
								for(Managers exList:list ){
									if(exList != null){
										ManagersExcelport excelport= new ManagersExcelport();
										excelport.setNickname(exList.getNickname());
										excelport.setCreateDatetime(String.valueOf(exList.getCreateDatetime()));
										excelport.setGroupName(exList.getGroupName());
										exportList.add(excelport);
									}
								}
								ExportController<ManagersExcelport> ex =new ExportController<ManagersExcelport>();
								 String[] headers =  
								{ "时间","账号","小组名称"}; 
								 List<ManagersExcelport> dataset = new ArrayList<ManagersExcelport>();
								 for(ManagersExcelport export:exportList){
							        	dataset.add(export);  
							        } 
							        try  
							        {         
							        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							    		Date date = new Date();
							    		String fileName = format.format(date);
							    		
							    		String path = "C:/Users/俞旭东/Desktop/xigou/file"+fileName+".xls";
							            OutputStream out = new FileOutputStream(path);   
							            ex.exportExcel(headers, dataset, out);  
							            out.close();   
							            File file = new File(path);  
							            // 取得文件名。  
							            String filename = file.getName();  
							            // 以流的形式下载文件。  
							            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
							            byte[] buffer = new byte[fis.available()];  
							            fis.read(buffer);  
							            fis.close();  
							            // 清空response  
							            res.reset();  
							            // 设置response的Header  
							            res.addHeader("Content-Disposition", "attachment;filename="  
							                    + new String(filename.getBytes()));  
							            res.addHeader("Content-Length", "" + file.length());  
							            OutputStream toClient = new BufferedOutputStream(  
							            		res.getOutputStream());  
							            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
							            toClient.write(buffer);  
							            toClient.flush();  
							            toClient.close();
							        	if (file.isFile() && file.exists()) {
											file.delete();
										}
							        } catch (FileNotFoundException e) {  
							            e.printStackTrace();  
							        } catch (IOException e) {  
							            e.printStackTrace();  
							        } 
							}	
	
	
	
    public void exportExcel(Collection<T> dataset, OutputStream out)  
    {  
        exportExcel("导出记录", null, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
        exportExcel("导出记录", headers, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out, String pattern)  
    {  
        exportExcel("导出记录", headers, dataset, out, pattern);  
    }  
  
    /** 
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上 
     *  
     * @param title 
     *            表格标题名 
     * @param headers 
     *            表格属性列名数组 
     * @param dataset 
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据) 
     * @param out 
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中 
     * @param pattern 
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd" 
     */  
    @SuppressWarnings("unchecked")  
    public void exportExcel(String title, String[] headers,  
            Collection<T> dataset, OutputStream out, String pattern)  
    {  
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        // 设置表格默认列宽度为15个字节  
        //sheet.setDefaultColumnWidth((short) 15);  
        sheet.setColumnWidth(0, 25*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 15*256);
        sheet.setColumnWidth(3, 40*256);
        sheet.setColumnWidth(4, 15*256);
        sheet.setColumnWidth(5, 15*256);
        sheet.setColumnWidth(6, 15*256);
        sheet.setColumnWidth(7, 15*256);
        sheet.setColumnWidth(8, 18*256);
        sheet.setColumnWidth(9, 15*256);
        sheet.setColumnWidth(10, 20*256);
        sheet.setColumnWidth(11, 20*256);
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        font.setFontName("Times");
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        font2.setFontName("Times");
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 声明一个画图的顶级管理器  
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        // 定义注释的大小和位置,详见文档  
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,  
                0, 0, 0, (short) 4, 2, (short) 6, 5));  
        // 设置注释内容  
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));  
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.  
        comment.setAuthor("leno");  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (short i = 0; i < headers.length; i++)  
        {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text);  
        }  
  
        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();  
        int index = 0;  
        while (it.hasNext())  
        {  
            index++;  
            row = sheet.createRow(index);  
            T t = (T) it.next();  
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
            Field[] fields = t.getClass().getDeclaredFields();  
            for (short i = 0; i < fields.length; i++)  
            {  
                HSSFCell cell = row.createCell(i);  
                cell.setCellStyle(style2);  
                Field field = fields[i];  
                String fieldName = field.getName();  
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
                try  
                {  
                    Class tCls = t.getClass();  
                    Method getMethod = tCls.getMethod(getMethodName,  
                            new Class[]  
                            {});  
                    Object value = getMethod.invoke(t, new Object[]  
                    {});  
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;  
                    if(value==null){
                    	 textValue = ""; 
                    }else{
                    	 textValue = value.toString(); 
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null)  
                    {  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches())  
                        {  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }  
                        else  
                        {  
                            HSSFRichTextString richString = new HSSFRichTextString(  
                                    textValue);  
                            HSSFFont font3 = workbook.createFont();  
                            font3.setColor(HSSFColor.BLACK.index);  
                            richString.applyFont(font3);  
                            cell.setCellValue(richString);  
                        }  
                    }  
                }  
                catch (Exception e)  
                {  
                    e.printStackTrace();  
                }  
                finally  
                {  
                    // 清理资源  
                }  
            }  
        }  
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
	
}
