package ghaya.learn.desginMode.strategy;

import java.util.Comparator;

public class DogSizeComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog o1, Dog o2) {
        if (o1.size < o2.size) return -1;
        else if (o1.size > o2.size) return 1;
        return 0;
    }
}
