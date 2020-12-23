package ghaya.注解的使用;


/**
 * neizhiAnnotation
 */
public class neizhiAnnotation {

    //重写
    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public void test(){
        System.out.println("不推荐使用，但是可以使用");
    }

    @SuppressWarnings("")
    public void test2(){
        System.out.println("抑制编译时的警告信息");
    }

    public static void main(String[] args) {
        neizhiAnnotation demo = new neizhiAnnotation();
        demo.test();
        demo.test2();
    }
}
