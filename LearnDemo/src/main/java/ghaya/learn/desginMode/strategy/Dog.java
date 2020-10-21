package ghaya.learn.desginMode.strategy;

class Dog implements Comparable<Dog>{
    public int size;
    public int size2;

    public Dog(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(Dog o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "size=" + size +
                ", size2=" + size2 +
                '}';
    }
}
