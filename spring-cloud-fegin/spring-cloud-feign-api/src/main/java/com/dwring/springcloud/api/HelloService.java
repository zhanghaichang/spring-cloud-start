package com.dwring.springcloud.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/** 
 * @ClassName HelloService
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2017年11月20日 下午12:09:44
 *
 */
public interface HelloService {

    @GetMapping("/hello/{name}")
    String hello(@PathVariable(value = "name") String name);

}