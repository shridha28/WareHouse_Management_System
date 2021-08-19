/**
 * 
 */
package com.warehouse.interceptor;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Custom LogInterceptor for incoming requests.
 * 
 * @author Shridha S Jalihal
 */
@Component
public class LogRequestInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LogRequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + Instant.now());
		request.setAttribute("startTime", startTime);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		long startTime = (Long) request.getAttribute("startTime");

		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Time Taken in ms ="
				+ (Instant.now().toEpochMilli() - startTime));
	}
}
