package com.dwring.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/** 
 * @ClassName FeignClientApplication
 * @Description 启动类
 * @author zhanghaichang 
 * @date: 2020年11月20日 下午12:11:19
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}
}
