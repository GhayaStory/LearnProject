package ghaya.learn.courseList;

import java.util.*;

public class ListDemo1 {
    private String[] myArray;

    public static void main(String[] args) {
        //Boolean、Character、Integer、Folat
        Object[] objAttr = {true,"啊",12,3,14F};//原始数组长度固定

        ArrayList alist = new ArrayList();//默认10个空列表
        ArrayList blist = new ArrayList(100);//默认100个空列表
        ArrayList clist = new ArrayList();//自动扩容
        System.out.println(alist.size());
        System.out.println(blist.size());
        System.out.println(clist.size());
        LinkedList linkedList = new LinkedList();
        Vector vector = new Vector();
        System.out.println(linkedList.toArray());
        List list = Collections.synchronizedList(clist);

    }
    public void setMyArray1(String [] myArray){
        this.myArray = myArray;
    }

    public void setMyArray2(String[] newMyArray){
        if(newMyArray == null){
            this.myArray = new String[0];
        }else{
            this.myArray = Arrays.copyOf(newMyArray,newMyArray.length);
        }
    }

}
