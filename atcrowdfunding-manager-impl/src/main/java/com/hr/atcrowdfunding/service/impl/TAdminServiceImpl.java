package com.hr.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.hr.atcrowdfunding.bean.TAdmin;
import com.hr.atcrowdfunding.bean.TAdminExample;
import com.hr.atcrowdfunding.bean.TAdminExample.Criteria;
import com.hr.atcrowdfunding.exception.LoginException;
import com.hr.atcrowdfunding.mapper.TAdminMapper;
import com.hr.atcrowdfunding.service.TAdminService;
import com.hr.atcrowdfunding.util.AppDateUtils;
import com.hr.atcrowdfunding.util.Const;
import com.hr.atcrowdfunding.util.MD5Util;

@Service
public class TAdminServiceImpl implements TAdminService {

	@Autowired
	TAdminMapper adminMapper;

	@Override
	public TAdmin getTAdminByLogin(Map<String, Object> paramMap) {
		
		//1.密码加密
		
		//2.查询用户
		String loginacct = (String)paramMap.get("loginacct");
		String userpswd = (String)paramMap.get("userpswd");
		
		//3.判断用户是否为null
		TAdminExample example = new TAdminExample();
		example.createCriteria().andLoginacctEqualTo(loginacct);
		List<TAdmin> list = adminMapper.selectByExample(example);
		
		if (list == null || list.size() == 0) {
			throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
		}
		
		TAdmin admin = list.get(0);
		//4.判断密码是否一致
		if (!admin.getUserpswd().equals(MD5Util.digest(userpswd))) {
			throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
		}
		
		//5.返回结果
		return admin;
		
//		if (list != null && list.size() == 1) {
//			
//			TAdmin admin = list.get(0);
//			//4.判断密码是否一致
//			if (admin.getUserpswd().equals(userpswd)) {
//				
//				//5.返回结果
//				return admin;
//			} else {
//				throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
//			}
//			
//		} else {
//			throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
//		}
	}

	//获取管理员列表
	@Override
	public PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap) {
		
		TAdminExample example = new TAdminExample();
		String condition = (String)paramMap.get("condition");
		
		if (!"".equals(condition)) {
			//按照登录名模糊查询
			example.createCriteria().andLoginacctLike("%"+condition+"%");
			
			//按照用户名模糊查询
			Criteria criteria = example.createCriteria();
			criteria.andUsernameLike("%"+condition+"%");
			
			//按照邮箱模糊查询
			Criteria criteria2 = example.createCriteria();
			criteria2.andEmailLike("%"+condition+"%");
			
			example.or(criteria);
			example.or(criteria2);
		}
		
		//根据时间进行倒序排列
//		example.setOrderByClause("createtime desc");
		
		List<TAdmin> list = adminMapper.selectByExample(example);
		
		PageInfo<TAdmin> page = new PageInfo<TAdmin>(list, 5);
		
		return page;
	}

	@Override
	public void saveTAdmin(TAdmin admin) {
		
		admin.setUserpswd(MD5Util.digest(Const.DEFAULT_USERPSWD));
		admin.setCreatetime(AppDateUtils.getFormatTime());
		
		adminMapper.insertSelective(admin); //动态sql，有选择行保存
	}

	@Override
	public TAdmin getTAdminById(Integer id) {
		
		
		return adminMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateTAdmin(TAdmin admin) {
		
		adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void deleteTAdmin(Integer id) {
		adminMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteBatch(List<Integer> idList) {

		adminMapper.deleteBatch(idList);
		
	}
}
