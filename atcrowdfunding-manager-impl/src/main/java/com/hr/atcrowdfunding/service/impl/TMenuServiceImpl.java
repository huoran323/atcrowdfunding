package com.hr.atcrowdfunding.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.atcrowdfunding.bean.TMenu;
import com.hr.atcrowdfunding.mapper.TMenuMapper;
import com.hr.atcrowdfunding.service.TMenuService;

@Service
public class TMenuServiceImpl implements TMenuService {
	
	Logger log = LoggerFactory.getLogger(TMenuServiceImpl.class);

	@Autowired
	TMenuMapper menuMapper;

	@Override
	public List<TMenu> listMenuAll() {
		
		List<TMenu> menuList = new ArrayList<TMenu>(); // 只存放父菜单，但是将children属性赋值
		Map<Integer, TMenu> cache = new HashMap<Integer, TMenu>();

		List<TMenu> allList = menuMapper.selectByExample(null);

		for (TMenu tMenu : allList) {
			if (tMenu.getPid() == 0) {
				menuList.add(tMenu);
				cache.put(tMenu.getId(), tMenu);
			}
		}

		for (TMenu tMenu : allList) {
			if (tMenu.getPid() != 0) {
				Integer pid = tMenu.getPid();
				TMenu parent = cache.get(pid);
				parent.getChildren().add(tMenu); // 根据子菜单pid查找父菜单id，然后根据父菜单children属性进行父子关系组合。
			}
		}
		
		log.debug("菜单：{}",menuList);
		
		return menuList;
	}
}
