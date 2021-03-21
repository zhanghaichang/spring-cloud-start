package com.dwring.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @ClassName: ApplicationConfiguration
 * @Description: 配置类
 * @author zhanghaichang
 * @date 2021年3月21日
 *
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}
}
