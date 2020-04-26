package com.hr.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger log = LoggerFactory.getLogger(TAdminController.class);
	
	@Autowired
	TAdminService adminService;
	
	@RequestMapping("/admin/doDelete")
	public String doDelete(Integer id, Integer pageNum) {
		
		adminService.deleteTAdmin(id);
		
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	//跳转修改页面
	@RequestMapping("/admin/toUpdate")
	public String toUpdate(Integer id, Model model) {
		
		TAdmin admin = adminService.getTAdminById(id);
		//向请求域中设置值
		model.addAttribute("admin", admin);
		
		return "admin/update";
	}
	
	@RequestMapping("/admin/doUpdate")
	public String doUpdate(TAdmin admin, Integer pageNum) {
		
		adminService.updateTAdmin(admin);
		
		//修改完之后，直接跳到数据所在的页面
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	//跳转添加页面
	@RequestMapping("/admin/toAdd")
	public String toAdd() {
		
		return "admin/add";
	}
	
	@RequestMapping("/admin/doAdd")
	public String doAdd(TAdmin admin) {
		
		adminService.saveTAdmin(admin);
		
//		return "redirect:/admin/index";
		return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
	}
	
	//获取管理员列表
	@RequestMapping("/admin/index")
	public String index(@RequestParam(value="pageNum", required=false, defaultValue="1")Integer pageNum, 
						@RequestParam(value="pageSize", required=false, defaultValue="2")Integer pageSize,
						Model model) {
		
		log.debug("获取管理员列表");
		
		PageHelper.startPage(pageNum, pageSize); //线程绑定，方便后面调用，不需要传参了
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);
		
		//向请求域中设置值
		model.addAttribute("page", page);
		
		return "admin/index";
	}

}
