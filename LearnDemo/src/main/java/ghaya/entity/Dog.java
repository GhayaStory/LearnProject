package ghaya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Dog extends Animal implements Ikimono{
    private String color;
    public String name;

    public Dog(){
        System.out.println("this is dog");
    }

}
