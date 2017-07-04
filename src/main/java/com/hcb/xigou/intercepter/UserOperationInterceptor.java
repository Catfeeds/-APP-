package com.hcb.xigou.intercepter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hcb.xigou.util.LogUtil;


public class UserOperationInterceptor extends HandlerInterceptorAdapter {
//	private static Logger logger = LoggerFactory.getLogger(UserOperationInterceptor.class);
	
    //重写 preHandle()方法，在业务处理器处理请求之前对该请求进行拦截处理  
	public boolean preHandle(  
            HttpServletRequest request,  
            HttpServletResponse response,  
            Object handler)  
            		throws Exception {  
    	preparedSessionUser(request, response);
    	return true;
    }
    
    /**
     * 查看用户是否登录，如未登录到cookie中找用户信息
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void preparedSessionUser(HttpServletRequest request, 
    		HttpServletResponse response) throws UnsupportedEncodingException {
//    	String uri = request.getRequestURI();
//    	if(uri.indexOf("static") > 0 || uri.indexOf("favicon.ico") > 0)
//    		return;
    	
//    	System.out.println("=========日志记录方法1==============");
    	
		
    }
    
    /**
     * 用户操作记载日志
     */
    private void logUerOperation(HttpServletRequest request, 
    		HttpServletResponse response, Exception ex) {
//    	if(!request.getRequestURI().startsWith("/entrance")){
//    		LogUtil.writeVisitLog(request);
//    	}//日志记录方法
//    	System.out.println("=========日志记录方法==============");
//    	System.out.println(request.getHeaders("title"));
    }
    
    @Override
    public void afterCompletion(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		Object handler, 
    		Exception ex)
    				throws Exception {
    	logUerOperation(request, response, ex);
    }

}
