package ghaya.learn.desginMode.factorymethod;

public class CarFactory {
    public Moveable createCar(){
        System.out.println("create a Car");
        return new Car();
    }
}
