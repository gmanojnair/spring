package com.manoj.spring.si;

import org.springframework.stereotype.Service;

@Service
public class Shouter {
	public String shout(String s) {
		System.out.println("Message Received : "+ s);
        return s.toUpperCase().concat("!!!");
    }
}
