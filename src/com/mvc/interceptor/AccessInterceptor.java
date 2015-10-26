package com.mvc.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvc.HasAccess;

/**
 * 
 * @author Marco
 *
 */
public class AccessInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HandlerMethod method = (HandlerMethod)handler;
		System.out.println("Method: "+method.getMethod().getName());
		try {
			parse(method.getMethod());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void parse(Method method) throws Exception {
		if (method.isAnnotationPresent(HasAccess.class)) {
			HasAccess access = method.getAnnotation(HasAccess.class);
			String info = access.value().name();
			System.out.println("This is an "+info+" user.");
			if(info.equals("ANONYMOUS")){
				throw new Exception(info+" user does not have access to this function.");
			}			
		}
	}



}
