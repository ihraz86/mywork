package com.intervest.mystaysure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PropertySource("classpath:system.properties")
public class WelcomeController {
	
	@Autowired
	private OAuth2RestOperations oauth2RestTemplate;
	
	@Value("${staysure.api.url}")
	private String staysureApiUrl;
	
	@RequestMapping(value = { "/" , "/welcome.htm" }, method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		String url = staysureApiUrl + "/whoami";
		
		System.out.println("********* URL : " + url);
		
		String response = oauth2RestTemplate.getForObject(url, String.class);
		
		System.out.println("********* RESPONSE : " + response);
		
		model.put("msg", response);
 
		return "index";
	}
	
}
