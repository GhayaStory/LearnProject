package ghaya.learn.lambda.BV1sE411P7C1.demo;


import ghaya.learn.lambda.BV1sE411P7C1.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ghaya.learn.Base.test.Gtest;

/**
 * Stream Api
 * 用filter 筛选list集合demo
 */
public class StreamDemo3 {

    public static void main(String[] args) {
        List<Map<String, Object>> demoListMap = Gtest.getDemoListMap();
        System.out.println(demoListMap);
//        demoListMap.sort((a,b) -> Integer.parseInt(a.get("score").toString()) - Integer.parseInt(b.get("score").toString()));
        demoListMap.sort(Comparator.comparingInt(a -> Integer.parseInt(a.get("score").toString())));//升序
        System.out.println(demoListMap);
        demoListMap.sort(Comparator.comparingInt(a -> Integer.parseInt(((Map<String, Object>)a).get("score").toString())).reversed());//降序
        demoListMap.sort(Comparator.comparing(a -> new BigDecimal(((Map<String, Object>)a).get("score").toString())).reversed());//降序
        demoListMap.sort(Comparator.comparingInt(a -> new StreamDemo3().mapVal2Int((Map<String, Object>) a,"score")).reversed());
        System.out.println(demoListMap);

    }

    public int mapVal2Int(Map<String,Object> map,String name){
        return Integer.parseInt(map.get(name).toString());
    }

}
