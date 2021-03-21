package com.dwring.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/** 
 * @ClassName HelloServiceClient
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2017年11月20日 下午12:27:50
 *
 */
@FeignClient(value="eureka-feign-client",fallback=HelloServiceFallback.class)
@Service
public interface HelloServiceClient extends HelloService{


}
