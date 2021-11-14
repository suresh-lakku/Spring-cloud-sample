package com.learning.photoapp.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class PhotoAppApiZuulApiGatewayApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(PhotoAppApiZuulApiGatewayApplication.class, args);
		
		//ctx.getBean(JmsTemplate.)
	}

//	@Bean
//	public HttpTraceRepository htttpTraceRepository()
//	{
//	  return new InMemoryHttpTraceRepository();
//	}
}
