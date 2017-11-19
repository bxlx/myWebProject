package com.xiaojiyun.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping("/home")
    public String hello(){        
        return "home";
    }
	
	@RequestMapping("/test")
    public String test(){        
        return "test";
    }
}
