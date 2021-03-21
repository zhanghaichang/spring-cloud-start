package com.dwring.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springcloud.api.HelloServiceClient;

/** 
 * @ClassName TestController
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2017年11月20日 下午12:27:01
 *
 */
@RestController
public class HelloController {

	@Autowired
	private HelloServiceClient helloServiceClient;

	@GetMapping("/test/{name}")
	public String test(@PathVariable("name") String name) {
		return helloServiceClient.hello(name);
	}
}
