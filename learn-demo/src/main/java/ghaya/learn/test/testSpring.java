package ghaya.learn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testSpring {

    public static void main(String[] args) {
        ApplicationContext centext = new ClassPathXmlApplicationContext("tx.xml");
        B bean = centext.getBean(B.class);
        bean.show();
    }
}
