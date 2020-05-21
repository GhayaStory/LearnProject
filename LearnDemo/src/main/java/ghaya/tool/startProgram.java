package ghaya.tool;

import org.junit.Test;

import java.io.IOException;

public class startProgram {

    @Test
    public void startProgram() throws IOException {
        String url = toolProperties.getProperty("redis");
        System.out.println(url);
        url = url.replace("\\","/");
        System.out.println(url);
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start " + url); // 通过cmd窗口执行命令
    }

    @Test
    public void test1() throws IOException {
        // 直接打开应用程序
        Runtime.getRuntime().exec("C:/Users/liqiang/Desktop/开机后点它.bat"); // 打开一个批处理文件
        Runtime.getRuntime().exec("E:/酷狗/KGMusic/KuGou.exe"); // 打开酷狗

        /******** 可以通过cmd命令打开软件或者是做其他 *****/
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start E:/酷狗/KGMusic/KuGou.exe"); // 通过cmd窗口执行命令
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start E:/php/Test/第一个html/界面.html"); // 通过cmd命令打开一个网页
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k mkdir C:\\Users\\liqiang\\Desktop\\java键的1"); // 通过cmd创建目录用两个反斜杠
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k mkdir C:\\Users\\liqiang\\Desktop\\java键的2"); // 通过cmd创建目录用两个反斜杠
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /c calc ");// 通过cmd打开计算器
    }

    @Test
    public void test2() throws IOException {
        /******** 可以通过cmd命令打开软件或者是做其他 *****/
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /c osk");// 通过屏幕软键盘
    }

}
