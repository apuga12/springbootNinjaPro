package com.industries.stark.springbootNinja.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// CLASE que permite interceptar cada peticion que nos hagan
// Resta tambien cargarlo (crearla) en la clase Configuration.WebMvcConfiguration para que Spring levante esta config al arrancar.
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter{
	
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);
	
	// 1. Se ejecuta al llegar la peticion al server
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub
			//return super.preHandle(request, response, handler);
			
			request.setAttribute("startTime", System.currentTimeMillis());
			return true;
		}	
	
	// 2. Se ejecuta justo antes de la vista del Navegador
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		//super.afterCompletion(request, response, handler, ex);
		
		long startTime = (long)request.getAttribute("startTime");
		LOG.info("===> *** URL to: "+ request.getRequestURL().toString()+"  in : "+ (System.currentTimeMillis() - startTime) + " 'ms");
	}

}
