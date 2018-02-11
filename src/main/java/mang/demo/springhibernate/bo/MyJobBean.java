package mang.demo.springhibernate.bo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyJobBean {
	private static final Logger log = LoggerFactory.getLogger(MyJobBean.class);
    private void printMessage(){
        Date date =new Date();
        log.info("hello quartz,current date is {}",date);
    }
}
