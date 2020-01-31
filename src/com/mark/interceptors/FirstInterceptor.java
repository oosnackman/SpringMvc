package com.mark.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:
 * @author MarkLin
 * @Date:2019年11月5日下午9:58:52
 * @Remarks:
 */

public class FirstInterceptor implements HandlerInterceptor {

	/**
	    *    該方法在目標方法之前被調用
	    *    若返回值為true,則繼續調用後續的攔截器與目標方法
	    *    若返回值為false,則不會繼續調用後續的攔截器與目標方法
	    *    可以考慮做做權限,日誌,事務...等
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("[FirstInterceptor] preHandle");
		return true;
	}

	
	/**
	    *    該方法在目標方法之後被調用
	  *      可以對請求的属性或VIEW做出修改. 
	 * */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("[FirstInterceptor] postHandle");
	}
	
	/**
	    *    該方法在VIEW之後被調用.釋放資源
	  *      
	 * */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("[FirstInterceptor] afterCompletion");
	}

}
