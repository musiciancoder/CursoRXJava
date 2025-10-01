package com.basicsstrong.reactive.section1;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class ConnectableObservable {

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * A ConnectableObservable resembles an ordinary Observable, except that it does not beginemitting items when it is subscribed to, but only when its connect method is called. In this way youcan wait for all intended Observers to Observable.subscribe to the Observablebefore the Observable begins emitting items. 
		 */
		io.reactivex.rxjava3.observables.@NonNull ConnectableObservable<@NonNull Long> source = Observable.interval(2,TimeUnit.SECONDS).publish(); // cad 2 segundos
		
		source.connect();
		
		source.subscribe(System.out::println); //observer1
		
		
		
	Thread.sleep(15000); //parar a ejecucion de hilo principal por 15 segundos y despues seguir leyendo el codigo
	System.out.println("hola");
		
		source.subscribe(System.out::println); //observer2
		
		Thread.sleep(15000);
		
	}
}