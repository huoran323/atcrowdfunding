package com.hr.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.atcrowdfunding.bean.TRole;
import com.hr.atcrowdfunding.service.TRoleService;

@Controller
public class TRoleController {
	
	@Autowired
	TRoleService roleService;
	
	@ResponseBody
	@RequestMapping("/role/doDelete")
	public String doDelete(Integer id) {
		
		roleService.deleteTRole(id);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/doUpdate")
	public String doUpdate(TRole role) {
		
		roleService.updateTRole(role);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/getRoleById")
	public TRole getRoleById(Integer id) {
		
		TRole role = roleService.getRoleById(id);
		return role;
	}
	
	@ResponseBody
	@RequestMapping("/role/doAdd")
	public String doAdd(TRole role) {
		
		roleService.saveTRole(role);
		return "ok";
	}
	
	@RequestMapping("/role/index")
	public String index() {
		
		return "role/index";
	}
	
	@ResponseBody
	@RequestMapping("/role/loadData")
	public PageInfo<TRole> loadData(@RequestParam(value="pageNum",required=false,defaultValue="1") Integer pageNum, 
									@RequestParam(value="pageSize",required=false,defaultValue="2") Integer pageSize,
									@RequestParam(value="condition",required=false,defaultValue="") String condition) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		//添加查询条件
		paramMap.put("condition", condition);
		
		PageInfo<TRole> page = roleService.listRolePage(paramMap);
		
		return page; //转换为json串返回
		
	}

}
