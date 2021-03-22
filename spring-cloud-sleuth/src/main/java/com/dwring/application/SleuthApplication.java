package com.dwring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;



/**
 * @ClassName: SleuthApplication
 * @Description: zipkin
 * @author haichangzhang
 * @date 2021年3月15日 下午3:52:10
 * 
 */
@EnableZipkinServer
@EnableEurekaClient
@SpringBootApplication
public class SleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthApplication.class, args);
	}

}
