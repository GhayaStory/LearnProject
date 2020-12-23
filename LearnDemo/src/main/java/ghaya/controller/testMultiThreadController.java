package ghaya.controller;


import ghaya.service.testMultiThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 多线程测试
 */
@RestController
@RequestMapping("/thread")
public class testMultiThreadController {

    private Object o = new Object();//单例，默认就一份

    @Autowired
    private testMultiThreadService service;//单例，默认就一份

    public Long num = 1L;

    @GetMapping("/moreToOne")
    public String test(){
        String s = "";
        Object iii = new Object();//每个线程进来都不一样
        long id = Thread.currentThread().getId();
        System.out.println("start--------"+id);
        System.out.println(o);
//        System.out.println(service.o.hashCode());
//        setNum(id);
        s = syncTest2(id);
        return s;
    }

    /**
     * 设置num
     */
    public synchronized void setNum(Long l){
        try {
            TimeUnit.SECONDS.sleep(1);
            num = l;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String syncTest(long id){
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(id+"/"+(i+1));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id+"";
    }


    public String syncTest2(long id){
        synchronized(o){

            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(id+"/"+(i+1)  + "-------" + num);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return id+"";
        }

    }

}
