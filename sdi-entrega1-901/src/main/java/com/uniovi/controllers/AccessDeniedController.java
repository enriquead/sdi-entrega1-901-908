package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {
	
	@RequestMapping("/accesDenied")
	public String index() {
		return "accesDenied";
	}

}
