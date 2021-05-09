package org.padmini.railway.ZuulAPIGateway;

import org.padmini.railway.filters.ErrorFilter;
import org.padmini.railway.filters.PostFilter;
import org.padmini.railway.filters.PreFilter;
import org.padmini.railway.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
public class ZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayApplication.class, args);
	}
	@Bean 
	public PreFilter preFilter() { return new PreFilter(); }
	  
	@Bean 
	public PostFilter postFilter() { return new PostFilter(); }
	  
	@Bean 
	public RouteFilter routeFilter() { return new RouteFilter(); }
	  
	@Bean 
	public ErrorFilter errorFilter() { return new ErrorFilter(); }
	 
}
