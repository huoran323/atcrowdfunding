package com.hr.atcrowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hr.atcrowdfunding.util.Const;

/**
 * 监听application对象的创建和销毁
 * @author zhengdayong
 *
 */
public class SystemUpInitListener implements ServletContextListener {
	
	Logger log = LoggerFactory.getLogger(SystemUpInitListener.class);

	//当application销毁时执行销毁方法
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	//当application创建时执行初始化方法
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ServletContext application = arg0.getServletContext();
		String contextPath = application.getContextPath();
		log.debug("当前应用上下文路径：{}",contextPath);
		//将上下文路径PATH存储到域中
		application.setAttribute(Const.PATH, contextPath);
	}

}
