package com.hr.atcrowdfunding.service;

import java.util.List;

import com.hr.atcrowdfunding.bean.TMenu;

public interface TMenuService {

	List<TMenu> listMenuAll(); //组合了父子关系
	
	List<TMenu> listMenuAllTree(); //不用组合父子关系

}
