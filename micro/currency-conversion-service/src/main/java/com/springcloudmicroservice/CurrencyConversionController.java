package com.springcloudmicroservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency_conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retreiveCurrencyConversion(@PathVariable String from ,@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		Map<String,String> urlVariable=new HashMap<>();
		urlVariable.put("from", from); 
		urlVariable.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity=new RestTemplate()
				.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversionBean.class,urlVariable);
		CurrencyConversionBean response= responseEntity.getBody();
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,
				quantity.multiply(response.getConversionMultiple()),response.getPort());
		
	}

	@GetMapping("/currency_conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retreiveCurrencyConversionFeign(@PathVariable String from ,@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean response= proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,
				quantity.multiply(response.getConversionMultiple()),response.getPort());
		
	}
	
}
