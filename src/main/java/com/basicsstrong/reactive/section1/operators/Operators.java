package com.basicsstrong.reactive.section1.operators;


import io.reactivex.rxjava3.core.Observable;

public class Operators {

	public static void main(String[] args) {
		
		Observable.just(60,57,89,90,23, 100, 98)
			.filter(e -> e > 75) //el operator no es solo a expresion ambda, sino toda esta linea, incuido el filter. En este sentido es diferente 
			//a un observer, porq justamente un operator no es ni un observable ni un observer
			.sorted()
			.subscribe(e -> System.out.println("Grade A with "+ e));

	}

}