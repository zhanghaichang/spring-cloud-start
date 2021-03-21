package com.dwring.application.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;

/**
* @ClassName: RequestTimeGatewayFilterFactory
* @Description: 自定义过滤器工厂
* @author zhanghaichang
* @date 2021年3月21日
*
*/
public class RequestTimeGatewayFilterFactory
		extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

	private static final Logger log = LoggerFactory.getLogger(RequestTimeGatewayFilterFactory.class);
	private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
	private static final String KEY = "withParams";

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList(KEY);
	}

	public RequestTimeGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
				if (startTime != null) {
					StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath()).append(": ")
							.append(System.currentTimeMillis() - startTime).append("ms");
					if (config.isWithParams()) {
						sb.append(" params:").append(exchange.getRequest().getQueryParams());
					}
					log.info("===> elapsed time：{}",sb.toString());
				}
			}));
		};
	}

	public static class Config {

		private boolean withParams;

		public boolean isWithParams() {
			return withParams;
		}

		public void setWithParams(boolean withParams) {
			this.withParams = withParams;
		}

	}

}
