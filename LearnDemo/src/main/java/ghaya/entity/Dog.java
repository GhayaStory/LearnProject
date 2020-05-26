package ghaya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
public class Dog extends Animal implements Ikimono {
    public String name;
    private String color;


    public Dog() {
        System.out.println("this is dog");
    }

    public Dog(String name) {
        this.name = name;
        System.out.println("this is dog " + name);
    }

    public Dog(String name, String color) {
        this.name = name;
        this.color = color;
        System.out.println("this is dog " + color + " " + name);
    }


    public void eat() {
        System.out.println("dog eating!");
    }

    public void eat(String some) {
        System.out.println("dog eating" + some);
    }

    public void eat(String some, int index) {
        System.out.println("dog eating" + some + "/" + index);
    }

}
