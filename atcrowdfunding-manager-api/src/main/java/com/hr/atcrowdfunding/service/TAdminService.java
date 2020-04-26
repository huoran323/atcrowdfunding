package com.hr.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hr.atcrowdfunding.bean.TAdmin;

public interface TAdminService {

	TAdmin getTAdminByLogin(Map<String, Object> paramMap);

	//获取管理员列表
	PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap);
	
	void saveTAdmin(TAdmin admin);

	TAdmin getTAdminById(Integer id);

	void updateTAdmin(TAdmin admin);

	void deleteTAdmin(Integer id);

	void deleteBatch(List<Integer> idList);

}
