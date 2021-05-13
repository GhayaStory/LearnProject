package com.ghaya.springaop.proxy.impl;

import com.ghaya.springaop.proxy.Iproduct;

public class ProductImpl implements Iproduct {
    @Override
    public void show(String productName) {
        System.out.println("product is " + productName);
    }
}
