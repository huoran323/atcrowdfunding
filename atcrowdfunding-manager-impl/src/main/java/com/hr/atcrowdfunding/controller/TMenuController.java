package com.hr.atcrowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hr.atcrowdfunding.bean.TMenu;
import com.hr.atcrowdfunding.service.TMenuService;

@Controller
public class TMenuController {
	
	@Autowired
	TMenuService menuService;

	@RequestMapping("/menu/index")
	public String index() {
		
		return "menu/index";
	}
	
	@ResponseBody
	@RequestMapping("/menu/loadTree")
	public List<TMenu> loadTree() {
		
		List<TMenu> list = menuService.listMenuAllTree();
		return list;
	}
}
