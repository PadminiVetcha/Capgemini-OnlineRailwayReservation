package org.padmini.railway.ZuulAPIGateway;

import org.padmini.railway.filters.ErrorFilter;
import org.padmini.railway.filters.PostFilter;
import org.padmini.railway.filters.PreFilter;
import org.padmini.railway.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@SpringBootApplication
@RestController
@EnableZuulProxy
@EnableHystrix
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
	 
	@RequestMapping(value = {"/","/user-service*","/admin-service*"})
	@HystrixCommand(fallbackMethod = "fallback_hello", 
	commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
	public String hello() throws InterruptedException {
	    Thread.sleep(3000);
	    return "Welcome Hystrix";
	}
	
	private String fallback_hello() {
	  return "Request fails. It takes long time to response";
	}
}
