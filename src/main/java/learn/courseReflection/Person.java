package learn.courseReflection;

public class Person {
    public String name;
    private Integer age;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void display(String param){
        System.out.println("function display " + param);
    }
    public void show() {
        System.out.println("show{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        System.out.println("getName function");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName function");
        this.name = name;
    }

    public Integer getAge() {
        System.out.println("getAge function");
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("setAge function");
        this.age = age;
    }
}
