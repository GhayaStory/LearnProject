package ghaya.learn.desginMode.strategy;

class Cat implements  Comparable<Cat>{
    public int size;
    public int size2;

    public Cat(int size, int size2) {
        this.size = size;
        this.size2 = size2;
    }

    @Override
    public int compareTo(Cat o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "size=" + size +
                ", size2=" + size2 +
                '}';
    }
}
