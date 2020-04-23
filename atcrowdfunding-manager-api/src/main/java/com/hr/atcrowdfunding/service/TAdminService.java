package com.hr.atcrowdfunding.service;

import java.util.Map;

import com.hr.atcrowdfunding.bean.TAdmin;

public interface TAdminService {

	TAdmin getTAdminByLogin(Map<String, Object> paramMap);

}
