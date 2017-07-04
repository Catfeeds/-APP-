package com.hcb.xigou.util;

import javax.servlet.http.HttpServletRequest;



public class LogUtil extends Thread{
/**
 * 记录用户上下行日志
 * @param userActor 用户上下行DTO
 */
//	public static void writeUserActorLog(UserActorDTO userActor){
//		ILogService logService = SpringContextHolder.getBean("logService");
//		String tableName="wy_platform_request_replay_log_"+DateUtil.getDate("yyyy");
//		userActor.setTableName(tableName);
//		logService.writeUserActorLog(userActor);
//	}
/**
 * 记录
 */
	public static void writeVisitLog(HttpServletRequest request){
//		ILogService logService = SpringContextHolder.getBean("logService");
//		String tableName="wy_user_visit_log_"+DateUtil.getDate("yyyy");
//		if(request!=null){
//			try {
//			String platformUserName=request.getParameter("platformUserName");
//			String platformType=request.getParameter("platformType");
//			if(platformType!=null&&!platformType.equals("")){
//				String userTel = (String)request.getHeader( "x-up-calling-line-id" );
//				String loginWay="";
//				if(PatternUtil.verifyTelecomTel(userTel)!=null){
//					loginWay="ctwap";
//				}else{
//					String ctel = DES.decrypt(CookieUtil.cookie("phoneNumber",request));
//					userTel=ctel;
//					if(PatternUtil.verifyTelecomTel(ctel)!=null){
//						loginWay="login";
//					}
//				}
//				VisitDTO visit=new VisitDTO();
//				visit.setIp("'"+request.getRemoteAddr()+"'");
//				visit.setLoginWay("'"+loginWay+"'");
//				visit.setPhone("'"+userTel+"'");
//				visit.setPlatformType(Integer.valueOf(platformType));
//				visit.setPlatformUserName("'"+platformUserName+"'");
//				visit.setUrl("'"+request.getRequestURL ( )+"?"+request.getQueryString ( )+"'");
//				visit.setUserAgent("'"+request.getHeader ( "user-agent" )+"'");
//				visit.setTableName(tableName);
//				logService.writeVisitLog(visit);
//			}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
	}
}
