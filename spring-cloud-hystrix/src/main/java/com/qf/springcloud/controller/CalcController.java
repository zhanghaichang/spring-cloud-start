package com.qf.springcloud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @ClassName: CalcController
 * @Description:熔断器
 * @author haichangzhang
 * @date 2017年6月15日 下午3:43:17
 * 
 */
@RestController
@EnableDiscoveryClient
public class CalcController {

	private final Logger logger = LoggerFactory.getLogger(CalcController.class);

	@Bean
	@LoadBalanced
	public RestTemplate resttemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate resttemplate;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String add() {
		logger.info("get.");
		return resttemplate.getForEntity("http://service-A/add?a=1&b=2", String.class).getBody();
	}

	public String addServiceFallback() {
		return "server error";
	}
}
