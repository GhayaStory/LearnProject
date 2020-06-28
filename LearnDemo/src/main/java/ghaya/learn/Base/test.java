package ghaya.learn.Base;

import java.math.BigDecimal;
import java.util.*;

public class test {
    public static test Gtest = new test();
    public static void main(String[] args) {
        String fileName = "s31ad534a3s6576d5a5da.jpeg";
        int length = fileName.length();
        System.out.println(fileName.indexOf(".jpeg"));
        System.out.println((length-".jpeg".length()));
        System.out.println(fileName.indexOf(".jpeg")==length-".jpeg".length());
    }

    public List<Map<String,Object>> getDemoListMap(){
        List<Map<String, Object>> list = new ArrayList<>();
        Random random = new Random(1);
        int max = 100;
        for (int i = 0; i < max; i++) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("name",i+"早姬");
            tempMap.put("score",random.nextInt(max));
            list.add(tempMap);
        }
        return list;
    }

    public List<Player> getDemoListEntity(){
        List<Player> list = new ArrayList<>();
        Random random = new Random(1);
        int max = 100;
        for (int i = 0; i < max; i++) {
            Player player = new Player();
            player.setName(i+"早姬");
            player.setScore(new BigDecimal(random.nextInt(max)));
            list.add(player);
        }
        return list;
    }

}
