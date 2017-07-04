package com.hcb.xigou.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginFilter  extends HttpServlet  implements Filter{

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
    	HttpServletRequest req=(HttpServletRequest)request;
    	HttpServletResponse res=(HttpServletResponse) response;
    	try {
			chain.doFilter(req, res);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
//        String path = req.getContextPath();
//		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
//		try{
//        	chain.doFilter(req, res);        
//    	}catch(Exception e){
//    		e.printStackTrace();
//    		try {
//				res.sendRedirect(basePath+"images/404.html");
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//    	}
    }
    public void init(FilterConfig arg0) throws ServletException {
        
    }
}
