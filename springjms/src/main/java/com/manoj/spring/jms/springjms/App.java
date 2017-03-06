package com.manoj.spring.jms.springjms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manoj.spring.jms.MessageSender;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springjms-context.xml");
        MessageSender sender = (MessageSender) context.getBean("messageSender");
        Map map = new HashMap();
        map.put("Name", "MYNAME");
        sender.send(map);
    }
}
