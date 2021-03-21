package com.dwring.springcloud.api;

/** 
 * @ClassName HelloServiceFallback
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2017年11月30日 上午10:31:14
 *
 */
public class HelloServiceFallback implements HelloService{

	@Override
	public String hello(String name) {
		return "hello service error.";
	}

}
