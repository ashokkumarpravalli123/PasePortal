package com.aldrich.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PortalController 
{
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home()
	{
		return "input";
	}

}
