package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PhonebookController {

	@RequestMapping (value="/writeform", method = {RequestMethod.GET,RequestMethod.POST})
	public String writeform() {
		System.out.println("dddd");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
}
