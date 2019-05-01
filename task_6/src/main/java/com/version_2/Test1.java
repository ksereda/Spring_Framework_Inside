package com.version_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Test1 {

    @Autowired
    private Test2 test2;

    private String a;

    public String getA() {
        return a;
    }

    @Main
    public void action() {
        init();
        System.out.println(test2 + a);
    }

    @PostConstruct
    private void init() {
        a = "5";
    }
}
