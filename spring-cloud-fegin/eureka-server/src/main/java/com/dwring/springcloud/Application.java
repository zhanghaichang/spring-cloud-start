package com.dwring.springcloud;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
 * @ClassName Application
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2017年11月20日 下午12:05:27
 *
 */

@EnableEurekaServer
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}