package com.example.demo.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration
//public class CORSFilterConfig {
//	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Bean
//	public FilterRegistrationBean corsFilterBean() {
//		final CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Collections.singletonList("*"));
//		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//		// setAllowCredentials(true) is important, otherwise:
//		// The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//		configuration.setAllowCredentials(true);
//		// setAllowedHeaders is important! Without it, OPTIONS preflight request
//		// will fail with 403 Invalid CORS request
//		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		FilterRegistrationBean corsFilter = new FilterRegistrationBean(new CorsFilter(source));
//		corsFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return corsFilter;
//	}
//	
//}
