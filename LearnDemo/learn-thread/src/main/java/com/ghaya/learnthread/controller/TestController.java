package com.ghaya.learnthread.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 *
 */
@RestController
public class TestController {

    static

    @RequestMapping("/thread1")
    public String getLock1() {


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("---------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        return "back";
    }



}
