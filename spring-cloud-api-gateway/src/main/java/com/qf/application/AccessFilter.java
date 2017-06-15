package com.qf.application;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.HttpHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @ClassName: AccessFilter
 * @Description: 权限验证
 * @author haichangzhang
 * @date 2017年6月15日 下午3:51:57
 * 
 */
public class AccessFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
	private static final String API_KEY_VALUE = "123456";
	private static final String ERROR_BODY="{\"error\":\"apiKey error\"}";

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		// http header add key "apiKey" value is "123456"
		String apiKey = request.getHeader("apiKey");
		logger.info("request apiKey:{}", apiKey);
		if (!checkApiKey(apiKey)) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			ctx.getResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			ctx.setResponseBody(ERROR_BODY);
			return null;
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}
	
	private boolean checkApiKey(String apiKey){
		if(apiKey==null){
			return false;
		}
		return API_KEY_VALUE.equals(apiKey);
	}

}
