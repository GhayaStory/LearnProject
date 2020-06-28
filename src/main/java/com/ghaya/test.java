package com.ghaya;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class test extends ab {


    @Test
    public void testList(){
        HashMap<Object, Object> map = new HashMap<>();
        //
        long startTime = System.currentTimeMillis();
        ArrayList<HashMap> array = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            array.add(map);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ArrayList运行时间:" + (endTime - startTime) + "ms");


        long startTime2 = System.currentTimeMillis();


        LinkedList<HashMap> linked = new LinkedList<>();
        for (int i = 0; i < 10000000; i++) {
            linked.add(map);
        }


        long endTime2 = System.currentTimeMillis();
        System.out.println("LinkedList运行时间:" + (endTime2 - startTime2) + "ms");

    }

}
