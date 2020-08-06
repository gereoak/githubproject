package com.javatechie.spring.soap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.soap.api.bean.LimitConfiguraion;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	public Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguraion retrieveLimitFromConfiguration() {
		return new LimitConfiguraion(configuration.getMinimum(),configuration.getMaximum());
	}
}
