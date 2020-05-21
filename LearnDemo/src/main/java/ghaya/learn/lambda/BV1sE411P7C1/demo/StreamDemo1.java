package ghaya.learn.lambda.BV1sE411P7C1.demo;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream Api
 */
public class StreamDemo1 {

    public static void main(String[] args) {

        /**
         * 过滤L开头，
         * 全转大写
         *
         */
        List<String> players = Arrays.asList("Oqpw", "La", "Ghaya", "Lbc", "lhg","lqT","Amd","TLd");

        /*for (String player: players){
            if(player.startsWith("L")){
                String temp = player.toUpperCase();
            }
        }*/

        //List转换流
        List<String> l = players.stream()//转换成流
                .filter(s -> s.startsWith("l")||s.startsWith("L"))//找出l 开头
                .map(String::toUpperCase)//对每个数据进行处理，转化大写
                .sorted()//自然排序
                .collect(Collectors.toList());//转换为List
        System.out.println(l);


//        String::toUpperCase  ::函数引用
//        "str".toUpperCase()

        //Set
        HashSet<Object> set = new HashSet<>();
        Stream<Object> stream = set.stream();
        //数组
        String[] arrPlayers = {"1","2","abc"};
        String arrPlayersStr = Stream.of(arrPlayers).filter(s -> !s.equals("")).map(String::toUpperCase).collect(Collectors.joining(","));
        System.out.println(arrPlayersStr);

        //文件
        try {
            Path path = Paths.get("file.txt");
            Stream<String> lines = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
