package mang.demo.springhibernate.util;

import mang.util.common.ConfigUtil;

public class ConfUtil {

	public static String getSpringXml(String env) {
		String springXmlParentPath = null;
		if ("pro".equalsIgnoreCase(env)) {
			// 生产环境
			System.out.println("***********production environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/pro/applicationContext.xml",
					"classpath:config/pro/applicationContext.xml", false);
		} else if ("debug".equals(env)) {
			//生产环境的调试环境 一般用于在生产环境上调试
			System.out.println("***********debug environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/debug/applicationContext.xml",
					"classpath:config/debug/applicationContext.xml", false);
		} else if ("test".equalsIgnoreCase(env)) {
			//本地测试环境
			System.out.println("***********test environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/test/applicationContext.xml",
					"classpath:config/test/applicationContext.xml", false);
		} else if("".equals(env)|| env==null ||"dev".equals(env)) {
			//开发环境
			System.out.println("***********develop environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/dev/applicationContext.xml",
					"classpath:config/dev/applicationContext.xml", false);
		}else{
			//其它环境
			System.out.println("***********"+env+"environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/"+env+"/applicationContext.xml",
					"classpath:config/"+env+"/applicationContext.xml", false);
		}
		return springXmlParentPath;
	}
}
