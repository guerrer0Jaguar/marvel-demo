package org.julio.marvel.demo.api.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class DemoClientApplication {


	
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
