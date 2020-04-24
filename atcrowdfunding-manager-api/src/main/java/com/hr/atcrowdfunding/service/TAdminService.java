package com.hr.atcrowdfunding.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hr.atcrowdfunding.bean.TAdmin;

public interface TAdminService {

	TAdmin getTAdminByLogin(Map<String, Object> paramMap);

	PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap);

}
