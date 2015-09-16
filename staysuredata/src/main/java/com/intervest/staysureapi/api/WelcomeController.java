package com.intervest.staysureapi.api;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intervest.staysureapi.model.User;

@RestController
public class WelcomeController {

	@RequestMapping("/whoami")
	public String greeting(@AuthenticationPrincipal User user) {
		return user.getName();
	}

}
