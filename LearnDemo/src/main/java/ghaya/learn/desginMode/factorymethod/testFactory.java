package ghaya.learn.desginMode.factorymethod;

/**
 * 工厂模式
 *
 */
public class testFactory {
    public static void main(String[] args) {
//        Moveable m = new Broom();
        Moveable m = new CarFactory().createCar();
        m.go();
    }
}
