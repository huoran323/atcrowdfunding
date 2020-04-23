package com.hr.atcrowdfunding.exception;

//为什么继承RuntimeException父类，而不是Exception类
//原因：业务层事务回滚，spring声明式事务默认只对RuntimeException类型异常进行事务回滚。
public class LoginException extends RuntimeException {

	public LoginException() {}
	
	public LoginException(String message) {
		super(message);
	}
}
