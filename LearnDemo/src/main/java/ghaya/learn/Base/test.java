package ghaya.learn.Base;

public class test {
    public static void main(String[] args) {
        String fileName = "s31ad534a3s6576d5a5da.jpeg";
        int length = fileName.length();
        System.out.println(fileName.indexOf(".jpeg"));
        System.out.println((length-".jpeg".length()));
        System.out.println(fileName.indexOf(".jpeg")==length-".jpeg".length());
    }
}
