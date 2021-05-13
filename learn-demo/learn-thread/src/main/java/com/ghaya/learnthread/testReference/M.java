package com.ghaya.learnthread.testReference;

public class M {
    /**
     * finalize()是Object中的方法，当垃圾回收器将要回收对象所占内存之前被调用，
     * 即当一个对象被虚拟机宣告死亡时会先调用它finalize()方法，让此对象处理它
     * 生前的最后事情（这个对象可以趁这个时机挣脱死亡的命运）。
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
