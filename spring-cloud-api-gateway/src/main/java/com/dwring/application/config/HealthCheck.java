package com.dwring.application.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: HealthCheck
 * @Description: 监控检查
 * @author zhanghaichang
 * @date 2021年3月21日
 *
 */
public class HealthCheck implements IPing {

	private final Logger log = LoggerFactory.getLogger(HealthCheck.class);
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean isAlive(Server server) {
		String url = "http://" + server.getId() + "/actuator/health";
		try {
			ResponseEntity<String> heath = restTemplate.getForEntity(url, String.class);
			if (heath.getStatusCode() == HttpStatus.OK) {
				log.info("ping " + url + " success and response is " + heath.getBody());
				String result = heath.getBody();
				if (result.contains("up")) {
					return true;
				}
			}
			log.info("ping " + url + " error and response is " + heath.getBody());
			return false;
		} catch (Exception e) {
			log.error("ping " + url + " failed", e);
			return false;
		}
	}

}
