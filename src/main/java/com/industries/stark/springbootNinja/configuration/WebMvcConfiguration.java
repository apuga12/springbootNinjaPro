package com.industries.stark.springbootNinja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.industries.stark.springbootNinja.component.RequestTimeInterceptor;

// Configuraciones que se le indican a Spring para el momento de arrancar el proyecto.
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor requestTimeInterceptor;

	// Agregarle el interceptor que creamos: component.RequestTimeInterceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//super.addInterceptors(registry);
		
		registry.addInterceptor(requestTimeInterceptor);
	}

}
