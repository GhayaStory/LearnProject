package ghaya.learn.courseThread.ghaya_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();//没有锁
        List<String> strsSync2 = Collections.synchronizedList(strs);//传进去加锁
    }
}
