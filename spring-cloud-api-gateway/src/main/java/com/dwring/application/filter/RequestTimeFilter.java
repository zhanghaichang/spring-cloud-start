package com.dwring.application.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName: RequestTimeFilter
 * @Description: 请求耗时
 * @author zhanghaichang
 * @date 2021年3月21日
 *
 */
public class RequestTimeFilter implements GatewayFilter, Ordered {

	private static final Logger log = LoggerFactory.getLogger(RequestTimeFilter.class);
	
	private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
			if (startTime != null) {
				log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime)+ "ms");
			}
		}));

	}

	@Override
	public int getOrder() {
		return 0;
	}

}
