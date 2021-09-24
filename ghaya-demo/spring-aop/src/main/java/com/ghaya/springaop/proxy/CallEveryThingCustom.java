package com.ghaya.springaop.proxy;

import com.ghaya.springaop.proxy.impl.ProductImpl;

import java.util.function.Predicate;

@FunctionalInterface
public interface CallEveryThingCustom {

	void content() throws Exception;
	default void call(){
		try {
			content();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	static void main(String[] args) {
		int i = 1;
		CallEveryThingCustom callEveryThingCustom = () -> {
			System.out.println(i/0);
			throw new Exception("1111");
		};
		callEveryThingCustom.call();
	}
}
