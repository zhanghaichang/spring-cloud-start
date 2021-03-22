package com.dwring.springcloud.api;

import org.springframework.stereotype.Component;

/** 
 * @ClassName HelloServiceFallback
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2021年3月20日 上午10:31:14
 *
 */
@Component
public class HelloFeignHystrix implements HelloFeignApi{

	@Override
	public String hello(String name) {
		return "hello service error.";
	}

}
