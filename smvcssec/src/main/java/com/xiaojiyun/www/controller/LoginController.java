package com.xiaojiyun.www.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class LoginController {
	
	@Resource
	private HttpServletRequest request;

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model){        
        return "login";
    }
	
}
