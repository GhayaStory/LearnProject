package learn.Base;

public class test {
    public static void main(String[] args) {
        System.out.println(Object.class);
        try {
            System.out.println(Class.forName("learn.Base.test"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Integer a = new Integer(268);
//        int b = 268;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(a.equals(b));
//        int c = a;
//        System.out.println(c);
    }
}
