package com.hr.atcrowdfunding.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hr.atcrowdfunding.bean.TRole;

public interface TRoleService {

	PageInfo<TRole> listRolePage(Map<String, Object> paramMap);

	void saveTRole(TRole role);

	TRole getRoleById(Integer id);

	void updateTRole(TRole role);

	void deleteTRole(Integer id);



}
