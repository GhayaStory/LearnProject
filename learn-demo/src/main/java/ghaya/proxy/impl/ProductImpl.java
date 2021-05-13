package ghaya.proxy.impl;


import ghaya.proxy.Iproduct;

public class ProductImpl implements Iproduct {
    @Override
    public void show(String productName) {
        System.out.println("product is " + productName);
    }
}
