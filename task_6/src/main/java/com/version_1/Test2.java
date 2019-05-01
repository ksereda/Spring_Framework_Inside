package com.version_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Test2 {

    @Autowired
    private Test1 test1;

    private String c;

    public String getC() {
        return c;
    }

    @PostConstruct
    public void doWork() {
        init();
        System.out.println(test1.getA().toUpperCase() + c);
    }

    private void init() {
        c = "hello";
    }

}
