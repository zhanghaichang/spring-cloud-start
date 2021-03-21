package com.dwring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import com.dwring.application.filter.IpRateLimitFilter;
import com.dwring.application.filter.RequestTimeGatewayFilterFactory;
import com.dwring.application.filter.TokenFilter;
import java.time.Duration;

/**
 * @ClassName: Application
 * @Description:
 * @author haichangzhang
 * @date 2021年3月15日 下午3:52:10
 * 
 */
@SpringCloudApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/**
	* @Title: customerRouteLocator
	* @Description: 自定义路由规则 
	* @param @param builder
	* @param @return    参数
	* @return RouteLocator    返回类型
	* @throws
	*/
	@Bean
	public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/test/rateLimit")
						.filters(f -> f.filter(new IpRateLimitFilter(2, 1, Duration.ofSeconds(10))))
						.uri("https://www.baidu.com").id("rateLimit_route"))
				.build();
	}

	/**
	* @Title: tokenFilter
	* @Description: 密钥验证
	* @param @return    参数
	* @return TokenFilter    返回类型
	* @throws
	*/
//	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}

	@Bean
	public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}

}
