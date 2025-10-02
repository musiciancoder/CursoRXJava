package com.basicsstrong.reactive.section1;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class HelloRxJava { //este es un ejemplo clasico de codigo asincrono con observables (los observables per se no son ni sincronos ni asincronos segun
	//copilot). Para un ejemplo clasico de codigo Sincrono con observables ver clase IntervalEmmiter2

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Observable<String> source = Observable.create(
				e-> {
					e.onNext("Hello");
					e.onNext("RxJava");
				});
		 source.subscribe(e->System.out.println("Observer 1: " + e)); // la instructora dice q esta linea es un observer, pero en realidad solo la expresion 
		 //lambda es el observer, y lo q devuelve el source.subscibe es en realidad un Disposable
		 source.subscribe(e->System.out.println("Observer 2: " + e));
		 
		 /*
		  * This is syntactic sugar for something like:

Observer<String> observer = new Observer<String>() {
    @Override
    public void onSubscribe(Disposable d) { }

    @Override
    public void onNext(String value) {
        System.out.println("Observer 1: " + value);
    }

    @Override
    public void onError(Throwable e) { }

    @Override
    public void onComplete() { }
};


But when you pass just a lambda like this:

e -> System.out.println("Observer 1: " + e)


You're only providing an onNext() handler. Internally, RxJava creates a temporary Observer implementation that fills in defaults for onComplete() and onError().
		  */

	}

}
