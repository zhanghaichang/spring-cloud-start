package com.dwring.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName HelloServiceClient
 * @Description TODO
 * @author zhanghaichang
 * @date: 2021年3月20日 上午10:31:14
 *
 */
@Service
@FeignClient(value = "openfeign-client", fallback = HelloFeignHystrix.class)
public interface HelloFeignApi {

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable(value = "name") String name);

}
