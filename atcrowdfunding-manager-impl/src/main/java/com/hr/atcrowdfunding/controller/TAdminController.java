package com.hr.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.atcrowdfunding.bean.TAdmin;
import com.hr.atcrowdfunding.service.TAdminService;

@Controller
public class TAdminController {
	
	@Autowired
	TAdminService adminService;
	
	@RequestMapping("/admin/index")
	public String index(@RequestParam(value="pageNum", required=false, defaultValue="1")Integer pageNum, 
						@RequestParam(value="pageSize", required=false, defaultValue="10")Integer pageSize,
						Model model) {
		
		PageHelper.startPage(pageNum, pageSize); //线程绑定，方便后面调用，不需要传参了
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);
		
		model.addAttribute("page", page);
		
		return "admin/index";
	}

}
