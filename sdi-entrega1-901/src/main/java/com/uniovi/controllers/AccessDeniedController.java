package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;

@Controller
public class AccessDeniedController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/accesDenied")
	public String index() {
		log.info("{} ha intentado realizar una acción prohibida",((User) httpSession.getAttribute("loggedUser")).getMail());
		return "accesDenied";
	}

}
