package mang.demo.springhibernate;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import mang.demo.springhibernate.bo.TestBO;
import mang.util.common.ConfigUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        
//        DOMConfigurator.configure(ClassLoader.getSystemResource("log4j.xml"));

        //优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
        URL url=ConfigUtil.getUrlFromDefault("config/log4j.xml");
        DOMConfigurator.configure(url);
        
        
        
        //优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
        String springXmlParentPath=ConfigUtil.getConfigPathFromDefault("config/applicationContext.xml","classpath:config/applicationContext.xml");
		ApplicationContext ctx = new FileSystemXmlApplicationContext(springXmlParentPath);
		
		TestBO testBO=(TestBO) ctx.getBean("testBO");
		List lis=testBO.testQuery();
		
		
		testBO.testSaveOrUpdate();
		List lis2=testBO.testQuery();
		
		System.out.println("ok");
    }
}
