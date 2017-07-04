package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.api.AlipayApiException;
import com.hcb.xigou.util.AlipayCore;
import com.hcb.xigou.util.AlipaySignature;
import com.hcb.xigou.util.JsonMap;
import com.hcb.xigou.util.RandomStringGenerator;
import com.hcb.xigou.util.SignUtil;
import com.hcb.xigou.util.Signature;
import com.hcb.xigou.util.StringToDate;
import com.itextpdf.text.log.SysoCounter;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class TestPost {

	public String testPost(String urlStr, String xmlObj) {
		String result = "";
		try {
			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			// con.setRequestProperty("Pragma:", "no-cache");
			// con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			String xmlInfo = xmlObj;
			// System.out.println("urlStr=" + urlStr);
			// System.out.println("xmlInfo=" + xmlInfo);
			out.write(new String(xmlInfo.getBytes("UTF-8")));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				result += line;

				// System.out.println(line);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
		return result;
	}

	/*
	 * StringBuilder sb = new StringBuilder(); String nonce_str =
	 * "c6y5bxeo48xlnhhy9u17ikoqahlngcyl"; sb.append("<xml>");
	 * sb.append("<appid>wxe6c31febbd05d58d</appid>");
	 * sb.append("<body>APP支付测试</body>");
	 * sb.append("<mch_id>1238621002</mch_id>");
	 * sb.append("<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>");
	 * sb.append(
	 * "<notify_url>http://wxpay.apparel.hcb.com/pub_v2/pay/notify.v2.php</notify_url>"
	 * ); sb.append("<out_trade_no>1415659990</out_trade_no>");
	 * sb.append("<spbill_create_ip>14.23.150.211</spbill_create_ip>");
	 * sb.append("<total_fee>1</total_fee>");
	 * sb.append("<trade_type>APP</trade_type>");
	 * sb.append("<sign>72C55F8573329DD6C349C4756247E39B</sign>");
	 * sb.append("</xml>"); return sb.toString();
	 */

	public static void main(String[] args) {
	  
		try {
			Map<String, String> mop = new HashMap<String,String>();
			//String aa = money.toString();
			String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
			Map<String, String> mopp = new HashMap<String,String>();
			mopp.put("\"body\"", "\"测试\"");
			mopp.put("\"subject\"", "\"测试测试\"");
			mopp.put("\"out_trade_no\"", "\""+nonce_str+"\"");
			mopp.put("\"total_amount\"", "\""+nonce_str+"\"");
			mopp.put("\"seller_id\"", "\"huancheclub_zfb@163.com\"");
			mopp.put("\"product_code\"", "\"QUICK_MSECURITY_PAY\"");
			String str = AlipayCore.check(mopp);
			String strr = "{"+ str+"}";
			//System.out.println(strr);
			String bb = URLEncoder.encode(strr, "UTF-8");
		//	System.out.println(bb);
			/*Integer score = 800;
			Integer money = score%100;
			Integer count =score/100;
			Integer over = score -money;
			System.out.println(count);
	    	System.out.println(money);
			System.out.println(over);
			
			String moneys = String.valueOf(money); 
			//System.out.println(moneys);
			BigDecimal balance2 = new BigDecimal("11.3");
			BigDecimal balance1 = new BigDecimal("10.35");
			BigDecimal take = new BigDecimal(0);
			if((balance2.subtract(balance1)).doubleValue()>=0.0){
				
				//System.out.println(true);
				//System.out.println(balance2.subtract(balance1));
			}else{
			//	System.out.println(false);
				//System.out.println(balance2.subtract(balance1));
			}*/
			/* SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		       Date time1 = new Date();
		       Date time2 = new Date();
		       String tamp1 = sdf.format(time1);
		   	Calendar calendar = Calendar.getInstance();// 日历对象
			calendar.setTime(time2);// 设置到期日期
			calendar.add(Calendar.MINUTE, +20);// 分钟减一
			Date pushDate = calendar.getTime();
			String tamp2 = sdf.format(pushDate);
		    try {
				Long   timestamp1 = sdf.parse(tamp1).getTime() / 1000;
				Long   timestamp2 = sdf.parse(tamp2).getTime() / 1000;
				
				System.out.println(timestamp2-timestamp1);
				
				
				
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			JSONObject json = new JSONObject();
			json.put("code", "10000");
			
			String strss = json.toString();
		      
			JsonMap.toMap(strss);
		//	System.out.println(JsonMap.toMap(strss));
			BigDecimal bid1 = new BigDecimal(3500);
			BigDecimal bid2_amount2 = new BigDecimal(3500.00);
			System.out.println(bid1.subtract(bid2_amount2).doubleValue()!=0.0);
			
			//System.out.println(strr);
			Date timestamp1 = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tamp = time.format(timestamp1);
			mop.put("app_id", "mobile.securitypay.pay");
			mop.put("method", "2088911200563043");
			mop.put("seller_id", "huancheclub_zfb@163.com");
			mop.put("charset", "utf-8");
			mop.put("notify_url", "http://notify.msp.hk/notify.htm");
			mop.put("sign_type", "1");
			mop.put("version","1.0");
			mop.put("timestamp",tamp);
			mop.put("biz_content", bb);
			Map<String, String> mapp = AlipayCore.paraFilter(mop);
			String  service = AlipaySignature.getSignContent(mapp);
		//	System.out.println(service);
			//String key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALbMt4irl+VodB4vhDo+ONXNu6NciBJD6KkomCp9KWcXixX+OZFVjfSndsO6KdC4kR4FOPUC1LXAOy/on5BYeTtrj7DXWsDeBwrR2IM15n4g1YFqwaXMOBuWoiRpmKrpt/W9BU/ORXij0j6rtku56AgLtkBB+MoXQ36Paa+UBiTDAgMBAAECgYAyTpyj7DVdWSW7uO/253DUxk3BY5G/FRlOlHFsIA3o5T3Ny60kJhtvHTc+Hlmrq1+kl2NZanIHouOnB6oCG5chH/mqO5KqDoIvTEZHIIMiIJDonPTn+Na8OyRabxYpCQznxiWdFxLmy6e3trWPg4rNT/f9WmyqblC5CRZ5Vpv6wQJBAOPuHtEtP+1GDAN37+5VVKNmjk2hoWCYlv2YCwuj8vRc6FQEzxERiUvpWtc81ab8zecmZXRnKiOyhB0X1KzJ67sCQQDNT8hXLvTqcspBCoMsK1OQlJMxmXJQDHRj47JT5fSMhVabZhW1MppB9XI+rtryscIjH8LsT/r6DPwfxn51J6aZAkBONQs/7M3NhUZj/khGN+M1ud/EBVyQ/2p3ky7fDJ81d5eEFK5UBfddI7G2vrn0dTPVR1hya1+LJhqsvNuNei83AkBl7W3wmodMvaBbmfR1QS1DYf+RaDSwOP6veKNXs5otCSVuEMhGJNEgXdJR/E0Gn+lZtrL2zt4yta+Vtt2hHAZBAkAn3a8WoQi6HnNDRclMDwVL0vnDA/mLwCZN+LKEXYZSB8FAfqYZWkazLlqmTBlquPWWp4lfPRy5hkZavQOveJPk";
		//	String sign = AlipaySignature.rsaEncrypt(service, key, "UTF-8");
			//System.out.println(sign);
			//System.out.println(SignUtil.sign(service, key));
			//String aliSignedOrder = service + "&sign=" + sign ;
	      //System.out.println(aliSignedOrder);
		} //String sign = SignUtil.sign(service, key);
	//	System.out.println(sign);
          catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Map<String, Object> mapp = new HashMap<String,Object>();
	        JSONObject jo = new JSONObject();
	        JSONObject jo1 = new JSONObject();
	        jo1.put("code", "10000");
	        jo1.put("msg", "Success");
	        jo1.put("app_id", "2015102200511864");
	        jo1.put("auth_app_id", "2015102200511864");
	        jo1.put("charset", "utf-8");
	        jo1.put("timestamp", "2016-08-29 15:34:40");
	        jo1.put("total_amount", "0.01");
	        jo1.put("trade_no", "2016082921001004970211882643");
	        jo1.put("seller_id", "2088911200563043");
	        jo1.put("out_trade_no", "d0d2b39171f44253516bc3cf2ff1e819");

	        String b = jo1.toString();
	        b = b.replaceAll("\\\\", "");
	        jo.put("alipay_trade_app_pay_response",b);
	        jo.put("sign_type", "RSA");
	        jo.put("sign", "uhKb/Z60B6xPP5eHtmvi9GNtSf5nE9IQ17SaYFGi81m46tXWnrQ6fPKcWn78I2aVQsFSLCfC6MMSlULV9KPUy7CxsnV2t1sgcegFUja9DRUhXEKUAvNwfaU+NvNSoy7xgMBH3TaH0UBcFJplb0E3WJyymGwGrerXBADy0ImAix4=");
	        String a = jo.toString();
			a = a.replaceAll("\\\\", "");
	        mapp.put("memo", "");
	        mapp.put("result", a);
	        mapp.put("resultStatus", "9000");
	        String sign = "uhKb/Z60B6xPP5eHtmvi9GNtSf5nE9IQ17SaYFGi81m46tXWnrQ6fPKcWn78I2aVQsFSLCfC6MMSlULV9KPUy7CxsnV2t1sgcegFUja9DRUhXEKUAvNwfaU+NvNSoy7xgMBH3TaH0UBcFJplb0E3WJyymGwGrerXBADy0ImAix4=";
			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	        String charset = "UTF-8";
	        JSONObject map = JSONObject.fromObject(	jo1);
	        String content = b;
	        System.out.println(b);
	        String type = "RSA";
		       
	        try {
				boolean flag = AlipaySignature.rsaCheck(content, sign, publicKey, charset, type);
				System.out.println(flag);
			} catch (com.hcb.xigou.util.AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
			
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM");
	        Date d = null;
	        String str="2016-01";
	        Date pushDate = null;
	        try {
				 d=time.parse(str);
				 Calendar calendar = Calendar.getInstance();// 日历对象
				 calendar.setTime(d);// 设置到期日期
				 calendar.add(Calendar.MONTH, 1);// 月份加一
				 pushDate = calendar.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        //获取前月的第一天
	        Calendar   cal_1=Calendar.getInstance();//获取当前日期 
	       
	        cal_1.setTime(pushDate);
	        cal_1.add(Calendar.MONTH, -1);
	        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        String  firstDay = format.format(cal_1.getTime())+" 00:00:00";
	        //System.out.println("-----1------firstDay:"+firstDay);
	        //获取前月的最后一天
	        Calendar cale = Calendar.getInstance();
	        cale.setTime(pushDate);
	        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
	        String lastDay = format.format(cale.getTime())+" 23:59:59";
	        //System.out.println("-----2------lastDay:"+lastDay);
	        
	    
	       SimpleDateFormat tiems = new SimpleDateFormat("yyyy-MM");
           Date date = new Date();
           String day = tiems.format(date);
         // System.out.println(day);
	       
		/*	Date date = new Date();
			String orderTime = tiems.format(date);
			orderTime = orderTime.replaceAll("-", "");
			orderTime = orderTime.replaceAll(" ", "");
			orderTime = orderTime.replaceAll(":", "");*/
	   
		//System.out.println(timestamp1);
		
        	  Image img = null;
			try {
				img = Image.getInstance(new URL("http://living-2016.oss-cn-hangzhou.aliyuncs.com/4ebdbd20432024b1b2d1d6dcb8568d91.png"));
			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        	 // System.out.println("img.width="+img.width()+" img.hight="+img.height());  
          
        	
        	  
        		BigDecimal totalMoney1 = new BigDecimal(10000);
				BigDecimal flag1 = new BigDecimal(3000);
				BigDecimal flag2 = new BigDecimal(5000);
				BigDecimal flag3 = new BigDecimal(10000);
				
				if(totalMoney1.subtract(flag1).doubleValue()>=0.0 && totalMoney1.subtract(flag2).doubleValue()<0.0){
				    System.out.println(1);
				}else if(totalMoney1.subtract(flag2).doubleValue()>=0.0 && totalMoney1.subtract(flag3).doubleValue()<0.0){
					System.out.println(2);
				}else if(totalMoney1.subtract(flag3).doubleValue()>=0.0){
					System.out.println(3);
				}else{
					System.out.println("没有优惠券");
				}
              
		//System.out.println(service);
     /*   String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
     	 BigDecimal big = new BigDecimal(100);
    	 BigDecimal bigg = new BigDecimal(100);
 		Map<String, Object> mapp = new HashMap<String, Object>();
 		String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
 		mapp.put("appid", "wxe6c31febbd05d58d");
 		mapp.put("body", "APP支付测试");
 		mapp.put("mch_id", "1238621002");
 		mapp.put("nonce_str", nonce_str);
 		mapp.put("notify_url", "http://wxpay.apparel.hcb.com/pub_v2/pay/notify.v2.php");
 		mapp.put("out_trade_no",nonce_str );
 		mapp.put("spbill_create_ip", "14.23.150.211");
 		mapp.put("total_fee",big.multiply(bigg).intValue());
 		mapp.put("trade_type", "APP");
 		String sign = Signature.getSign(mapp);
 		//System.out.println(sign);

 		StringBuilder sb = new StringBuilder();
 		sb.append("<xml>");
 		sb.append("<appid>wxe6c31febbd05d58d</appid>");
 		sb.append("<body>APP支付测试</body>");
 		sb.append("<mch_id>1238621002</mch_id>");
 		sb.append("<nonce_str>"+nonce_str+"</nonce_str>");
 		sb.append("<notify_url>http://wxpay.apparel.hcb.com/pub_v2/pay/notify.v2.php</notify_url>");
 		sb.append("<out_trade_no>"+nonce_str+"</out_trade_no>");
 		sb.append("<spbill_create_ip>14.23.150.211</spbill_create_ip>");
 		sb.append("<total_fee>"+big.multiply(bigg).intValue()+"</total_fee>");
 		sb.append("<trade_type>APP</trade_type>");
 		sb.append("<sign>"+sign+"</sign>");
 		sb.append("</xml>");
 		String info = sb.toString();
        String obj = new TestPost().testPost(url,info);
        System.out.println(obj);*/
/*	  String aa = "e9593a58d0397ac0451a0641236fc9d4";
		Map<String, Object> mapp = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		mapp.put("appid", "wxe6c31febbd05d58d");
		mapp.put("mch_id", "1238621002");
		mapp.put("nonce_str", nonce_str);
		mapp.put("out_trade_no", aa);
		String sign = Signature.getSign(mapp);
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<appid>wxe6c31febbd05d58d</appid>");
		sb.append("<mch_id>1238621002</mch_id>");
		sb.append("<out_trade_no>"+aa+"</out_trade_no>");
		sb.append("<nonce_str>"+nonce_str+"</nonce_str>");
		sb.append("<sign>"+sign+"</sign>");
		sb.append("</xml>");
		String url = "https://api.mch.weixin.qq.com/pay/orderquery";
		String objXml = sb.toString();
		String mapFromXml = new HttpsRequest().testPost(url,objXml);
	  
	 // System.out.println(mapFromXml);
	  
	  try {
			Map<String, Object> flag = XMLParser.getMapFromXML(mapFromXml);
			System.out.println(flag);
			Iterator it = flag.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if(key.equals("trade_state")){
					map.put("trade_state", value);
					System.out.println(map);
				}
				//System.out.println("key=" + key + " value=" + value);
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		}
	  */
	  
	  
	  
    /*  try {
			Map<String, Object> map = XMLParser.getMapFromXML(obj);
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if(key.equals("prepay_id")){
					System.out.println(value);
				}
		}
        }catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}*/
    }
	
	  public static Date stringToDate(String fffgg){
		    
		     Date rcreate=new Date();
		     java.text.SimpleDateFormat newstr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		       
		 try {
		   rcreate=newstr.parse(fffgg);
		} catch (ParseException e) {
		  
		   e.printStackTrace();
		}

		return   rcreate;

		}

}
