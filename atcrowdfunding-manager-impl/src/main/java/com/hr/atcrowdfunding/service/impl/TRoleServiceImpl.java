package com.hr.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageInfo;
import com.hr.atcrowdfunding.bean.TRole;
import com.hr.atcrowdfunding.bean.TRoleExample;
import com.hr.atcrowdfunding.mapper.TRoleMapper;
import com.hr.atcrowdfunding.service.TRoleService;

@Service
public class TRoleServiceImpl implements TRoleService {
	
	@Autowired
	TRoleMapper roleMapper;

	@Override
	public PageInfo<TRole> listRolePage(Map<String, Object> paramMap) {
		
		String condition = (String)paramMap.get("condition");
		List<TRole> list = null;
		if (StringUtils.isEmpty(condition)) {
			list = roleMapper.selectByExample(null);
		}
		else {
			TRoleExample example = new TRoleExample();
			//添加查询条件
			example.createCriteria().andNameLike("%"+condition+"%");
			list = roleMapper.selectByExample(example);
		}
		
		PageInfo<TRole> page = new PageInfo<TRole>(list, 5);
		
		return page;
	}

	@Override
	public void saveTRole(TRole role) {
		roleMapper.insertSelective(role);
	}

	@Override
	public TRole getRoleById(Integer id) {
		
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateTRole(TRole role) {
		
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public void deleteTRole(Integer id) {
		
		roleMapper.deleteByPrimaryKey(id);
	}

}
