package mang.demo.springhibernate.bo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyJobBean {
    private void printMessage(){
        Date date =new Date();
        System.out.println(date+"hello quartz");
    }
}
