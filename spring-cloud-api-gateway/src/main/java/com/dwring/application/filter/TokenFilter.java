package com.dwring.application.filter;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
* @ClassName: TokenFilter
* @Description: 全局身份认证Token
* @author zhanghaichang
* @date 2021年3月21日
*
*/
public class TokenFilter implements GlobalFilter, Ordered {

	private Logger logger = LoggerFactory.getLogger(TokenFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String token = exchange.getRequest().getHeaders().getFirst("token");
		if (Strings.isEmpty(token)) {
			logger.info("token is empty...");
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		// 验证用户api key
		if ("topcheer".equals(token)) {
			return chain.filter(exchange);
		}
		logger.info("token is error...");
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		return exchange.getResponse().setComplete();

	}

	@Override
	public int getOrder() {
		return -100;
	}

}
