package com.ghaya.registry;

import com.ghaya.mybatis.pojo.URL;

import java.util.HashMap;
import java.util.Map;

public class NativeRegistry {

    // 注册中心 map
    // Map<服务接口全类名,Map<ip和port对象,具体实现类>>
    private static Map<String, Map<URL, Class>> registCenter = new HashMap<>();

    /**
     * 注册服务
     * 向map中存值
     */
    public static void regist(String interfaceName, URL url, Class impCLass) {
        HashMap<URL, Class> map = new HashMap<>();
        map.put(url, impCLass);
        registCenter.put(interfaceName, map);
    }


    /**
     * 获取服务信息
     */
    public static Class get(String interfaceName, URL url){

        Map<URL, Class> urlClassMap = registCenter.get(interfaceName);
        Class aClass = urlClassMap.get(url);
        return aClass;
    }
}
