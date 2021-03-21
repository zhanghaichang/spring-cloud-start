package com.dwring.application.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
* @ClassName: AuthFilter
* @Description: 身份认证
* @author zhanghaichang
* @date 2021年3月21日
*
*/
@Component
public class AuthFilter extends AbstractGatewayFilterFactory {

	private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {

			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();

			// 1. 获取token
			String token = request.getHeaders().getFirst("token");
			log.info("当前请求的url:{}, method:{}", request.getURI().getPath(), request.getMethodValue());
			if (Strings.isEmpty(token)) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}
			// 2. 验证用户api key
			if (!"topcheer".equals(token)) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}
			// 3. 将用户名传递给后端服务
			ServerWebExchange build;
			try {
				ServerHttpRequest host = exchange.getRequest().mutate().header("X-User-Name", token)
						// 中文字符需要编码
						.header("X-Real-Name", URLEncoder.encode(token, "utf-8")).build();
				build = exchange.mutate().request(host).build();
			} catch (UnsupportedEncodingException e) {
				build = exchange;
			}

			return chain.filter(build);
		};

	}
}