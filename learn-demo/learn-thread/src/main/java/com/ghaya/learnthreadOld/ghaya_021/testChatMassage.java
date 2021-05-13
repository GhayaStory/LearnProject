/**
 *  菜逼代码
 *  用于实现聊天室实时响应
 */

package com.ghaya.learnthreadOld.ghaya_021;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testChatMassage<T> {
    final private static LinkedList<Map<String,Object>> lists = new LinkedList<Map<String,Object>>();
    final private int MAX = 10000;//最多元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(Map<String,Object> t){
        try {
            lock.lock();
            while(lists.size() == MAX){
                System.out.println("lists null");
                producer.await();//lock锁分组调用  生产者await
            }
            lists.add(t);
            count++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public List<Map<String,Object>> get(){
        List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
        try {
            lock.lock();
            while(lists.size() == 0){
                System.out.println("lists null");
                consumer.await();//lock锁分组调用  消费者await
            }
            while(lists.size()>0){
                list.add(lists.removeFirst());
            }
            count = 0;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return list;
    }

    public static void main(String[] args) {
        testChatMassage<String> c = new testChatMassage<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//显示的时间格式
        SimpleDateFormat simpleDateFormatOrder = new SimpleDateFormat("yyyyMMddHHmmss");//以防万一用于时间排序

        //启动消费者线程   模拟用户刷新聊天信息  1个人在这里每隔一秒获取消息

        new Thread(()->{
            for(int i=0;i<10000;i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("来获取消息次数"+i);
                List<Map<String,Object>> messageMapList = c.get();
                //messageMapList 在前端后者祸端做个排序再输出
                for(Map messageMap : messageMapList){
                    System.out.println("c-"+ messageMap.get("username")+" say at "+messageMap.get("sendtime"));
                    System.out.println("    "+messageMap.get("massageText"));
                }

            }
         },"c").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启用生产者线程  模拟用户发送聊天消息进来  10个人每个人每1秒次说1句话
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<100;j++){
                    try {
                        TimeUnit.SECONDS.sleep(1);//前端1秒发送一条信息
                        //给消息集合设置信息
                        String tStr = Thread.currentThread().getName()+"-"+j;
                        Map<String,Object> massageMap = new HashMap<String,Object>();
                        Date date = new Date();
                        massageMap.put("username","用户" + j);//用户名
                        massageMap.put("sendtime",simpleDateFormat.format(date));//当前时间
                        massageMap.put("timeOrderNum",Long.valueOf(simpleDateFormatOrder.format(date)));//用于时间排序
                        massageMap.put("massageText","this is message "+ tStr);
                        massageMap.put("uid","");
                        massageMap.put("mid","");
                        //调用添加方法  添加到集合
                        c.put(massageMap);
                        System.out.println("p:"+ tStr);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"p"+i).start();
        }
    }
}
