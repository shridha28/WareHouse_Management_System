/**
 * 
 */
package com.warehouse.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.warehouse.interceptor.LogRequestInterceptor;

/**
 * @author Shridha S Jalihal
 * WebMVC configuration for WMS application to add custom intercepter for logging
 */
@Configuration
public class WmsAppConfig implements WebMvcConfigurer {
	
	
	 @Autowired
	 private LogRequestInterceptor logRequestInterceptor;

	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(logRequestInterceptor)
	   .addPathPatterns("/api/**");;
	 }

}
