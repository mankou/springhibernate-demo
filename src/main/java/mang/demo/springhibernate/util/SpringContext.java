package mang.demo.springhibernate.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware {
	protected static ApplicationContext context;
	

	public static ApplicationContext getContext() {
		return context;
	}

	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context =applicationContext;
	}

}
