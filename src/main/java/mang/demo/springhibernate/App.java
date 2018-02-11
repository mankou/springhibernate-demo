package mang.demo.springhibernate;

import java.net.URISyntaxException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import mang.demo.springhibernate.bo.TestBO;
import mang.demo.springhibernate.util.ConfUtil;
import mang.demo.springhibernate.util.runinfo.RunInfoUtil;
import mang.demo.springhibernate.util.runinfo.SimpleRunInfo;
import mang.util.common.ConfigUtil;

/**
 * Hello world!
 *
 */
public class App {
	private static final transient Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		System.out.println("Hello World!");
		String env = System.getProperty("service.env");
		System.out.println("env:" + env);

//		loadLog4j2(env);
		loadSpringXml(env);

		log.info("program start ok");

		// TestBO testBO = (TestBO) ctx.getBean("testBO");
		// List lis = testBO.testQuery();
		//
		// testBO.testSaveOrUpdate();
		// List lis2 = testBO.testQuery();

		
		//TODO 这块感觉不好 想想用装饰器模式修改 2018-02-11
		RunInfoUtil.runinfo=new SimpleRunInfo();
		log.info(RunInfoUtil.getRunInfoStr());
	}

	public static void loadSpringXml(String env) {
		// 优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
		String springXmlParentPath = ConfUtil.getSpringXml(env);
		System.out.println("spring xml path:" + springXmlParentPath);
		// XXX 对于maven打出的shade包 则用java -jar 启动就会报错 目前我是用分发包所以没有问题

		// 为什么加file:? 因为在pszxjob项目中测试在windows中正常，在linux上报找不到文件 后来网上搜了说加上这句就好了
		// fix 在linux下运行如果不加file 其会按相对路径处理 导致找不到配置文件
		if (springXmlParentPath.startsWith("/")) {
			springXmlParentPath = "file:" + springXmlParentPath;
		}
		ApplicationContext ctx = new FileSystemXmlApplicationContext(springXmlParentPath);
	}
	

	//加载log4j的配置,现在已经不用了
	public static void loadLog4j(String env) {
		// DOMConfigurator.configure(ClassLoader.getSystemResource("log4j.xml"));

		// 优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
		// URL url = ConfigUtil.getUrlFromDefault("config/log4j.xml");
		// DOMConfigurator.configure(url);
	}
	
	/**
	 * 加载log4j2的配置
	 * 因一般把log4j2.xml放到classpath中就能自动加载,除非你放到自定义的路径才需要使用代码主动加载,所以这部分代码一般也不用
	 * */
	public static void loadLog4j2(String env){
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		URL url = ConfigUtil.getUrlFromDefault("config/log4j2.xml",false);
		try {
			context.setConfigLocation(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
