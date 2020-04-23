package com.hr.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hr.atcrowdfunding.bean.TAdmin;
import com.hr.atcrowdfunding.service.TAdminService;
import com.hr.atcrowdfunding.util.Const;

@Controller
public class DispatcherController {

	Logger log = LoggerFactory.getLogger(DispatcherController.class);
	
	@Autowired
	TAdminService adminService;
	
	@RequestMapping("/index")
	public String index() {
		
		log.debug("跳转到系统主页面...");
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		
		log.debug("跳转到登录页面...");
		return "login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct, String userpswd, HttpSession session, Model model) {
		
		log.debug("开始登录...");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", userpswd);
		
		try {
			TAdmin admin = adminService.getTAdminByLogin(paramMap);
			session.setAttribute(Const.LOGIN_ADMIN, admin);
			
			log.debug("登录成功...");
			return "main";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("登录失败={}...",e.getMessage());
			model.addAttribute("message", e.getMessage());
			
			return "login";
		}
		
		
	}
}
