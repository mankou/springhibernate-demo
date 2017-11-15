package mang.demo.springhibernate;

import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import mang.demo.springhibernate.bo.TestBO;
import mang.demo.springhibernate.util.RunInfo;
import mang.util.common.ConfigUtil;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		System.out.println("Hello World!");
		// 通过-D 参数传入启动参数 来判断是生产环境 还是开发环境 开发环境和生产环境所加载的配置文件不一样
		// 因为一般在开发环境中用eclipse启动 一般都不加启动参数 所以默认就是开发环境
		// java -Dservice.env=pro $JVM_OPT -jar $jarAbsolutePath
		String env = System.getProperty("service.env");
		System.out.println("env:" + env);

		loadLog4jConfig();
		loadSpringConfig(env);

		RunInfo runInfo = RunInfo.getInstance();
		System.out.println(runInfo.getRunInfoStr());
	}

	public static void loadSpringConfig(String env) {
		// 优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
		String springXmlParentPath = null;

		if ("pro".equalsIgnoreCase(env)) {
			System.out.println("***********production environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/pro/applicationContext-XX.xml",
					"classpath:config/pro/applicationContext-XX.xml");
		} else if ("test".equalsIgnoreCase(env)) {
			System.out.println("***********test environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/test/applicationContext.xml",
					"classpath:config/test/applicationContext.xml");
		} else {
			System.out.println("***********develop environment***************************");
			springXmlParentPath = ConfigUtil.getConfigPathFromDefault("config/dev/applicationContext.xml",
					"classpath:config/dev/applicationContext.xml");
		}

		System.out.println("spring xml path:" + springXmlParentPath);
		// XXX 对于maven打出的shade包 则用java -jar 启动就会报错 目前我是用分发包所以没有问题

		// 为什么加file:? 因为在pszxjob项目中测试在windows中正常，在linux上报找不到文件 后来网上搜了说加上这句就好了
		// fix 在linux下运行如果不加file 其会按相对路径处理 导致找不到配置文件
		if (springXmlParentPath.startsWith("/")) {
			springXmlParentPath = "file:" + springXmlParentPath;
		}
		ApplicationContext ctx = new FileSystemXmlApplicationContext(springXmlParentPath);

		logger.info("program start ok");

		TestBO testBO = (TestBO) ctx.getBean("testBO");
		List lis = testBO.testQuery();

		testBO.testSaveOrUpdate();
		List lis2 = testBO.testQuery();
	}

	public static void loadLog4jConfig() {
		// DOMConfigurator.configure(ClassLoader.getSystemResource("log4j.xml"));
		// 优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
		URL url = ConfigUtil.getUrlFromDefault("config/log4j.xml");
		DOMConfigurator.configure(url);
	}
}
