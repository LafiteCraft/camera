package com.lafite.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {
	
	private static ApplicationContext context;
	
	static{		
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	

}
