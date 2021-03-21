package com.dwring.springcloud.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/** 
 * @ClassName HelloController
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2017年11月20日 下午12:12:23
 *
 */
@RestController
public class HelloController implements HelloService {

	@Override
	public String hello(@PathVariable("name") String name) {
		System.out.println("================name:"+name);
		return "hello " + name;
	}

}
