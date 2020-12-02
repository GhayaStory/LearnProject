package com.common.entity;

import java.util.ArrayList;
import java.util.List;

public class testLambda {
    public static void main(String[] args) {
        String[] a = {""};
        int[] i = {0};
        List<String> arr = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            arr.add(j+"");
        }

        GreetingService abc = message -> {
            a[0] =message;
            i[0]++;
        };        
        abc.doSomeThing("Ghaya");
        System.out.println(a[0]);
        
    }

    interface GreetingService{
        void doSomeThing(String message);
    }
}
