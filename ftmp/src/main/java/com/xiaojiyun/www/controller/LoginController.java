package com.xiaojiyun.www.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaojiyun.www.dto.LoginDto;
import com.xiaojiyun.www.dto.UserDto;
import com.xiaojiyun.www.exception.ServiceException;
import com.xiaojiyun.www.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Resource
	private HttpServletRequest request;

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(@ModelAttribute("user") UserDto user){        
        return "user/login";
    }
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String login1(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult){
		
		try {
			if(bindingResult.hasFieldErrors()){
	            List<FieldError> list = bindingResult.getFieldErrors();  
	            for (FieldError fieldError : list) {  
	                System.out.println(fieldError.getDefaultMessage());  
	            }     
				return "user/login";
			}
			LoginDto loginDto = this.userService.login(user.getUserName(), user.getPassword());
			if(StringUtils.isEmpty(loginDto.getErrorMsg())){
				request.getSession().setAttribute("sessionUser", loginDto);
				return "redirect:/home";
			}else{
				bindingResult.addError(new FieldError("user",loginDto.getField(),loginDto.getErrorMsg()));
				return "user/login";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "redirect:/login";
        
    }
	
}
