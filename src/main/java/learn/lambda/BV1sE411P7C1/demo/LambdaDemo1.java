package learn.lambda.BV1sE411P7C1.demo;


/**
 * 基础lambda表达式
 */
public class LambdaDemo1 {


    public static void main(String[] args) {
//        oldDemo1();
        lambdaDemo1();
    }

    interface Printer {
        void printer(String val);
    }
    public void pringSomething(String something, Printer printer) {
        printer.printer(something);
    }

    interface Printer2 {
        void printer();
    }
    public void pringSomething(String something, Printer2 printer) {
        printer.printer();
    }

    //原始调用
    public static void oldDemo1() {
        LambdaDemo1 lambdaDemo1 = new LambdaDemo1();
        String some = "abcdefghijklmnopqrstuvwxyz";
        Printer printer = new Printer() {
            @Override
            public void printer(String val) {
                System.out.println(val);
            }
        };
        lambdaDemo1.pringSomething(some, printer);
    }
    //lambda调用
    //原始调用
    public static void lambdaDemo1() {
        LambdaDemo1 lambdaDemo1 = new LambdaDemo1();
        String some = "abcdefghijklmnopqrstuvwxyz";
        /*
        Printer printer = (String val) -> {
            System.out.println(val);
        };
        */
        /*
        Printer printer = (val) -> {
            System.out.println(val);
        };
        */
        /*
        Printer printer = val -> {
            System.out.println(val);
        };
        */
        /*
        Printer printer = val -> System.out.println(val);//只有一行
        */
        //lambdaDemo1.pringSomething(some, printer);

        lambdaDemo1.pringSomething(some, val -> System.out.println(val));
        //无参

//        lambdaDemo1.pringSomething(some, );

        Printer2 aaa = () -> System.out.println("aaa");
        aaa.printer();


    }

}
