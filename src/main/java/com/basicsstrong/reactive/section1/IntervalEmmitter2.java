package com.basicsstrong.reactive.section1;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class IntervalEmmitter2 { //ejemplo clasico de codigo Asincrono con observables
	
	/*why in this last snippets do we need a Thread.sleep() ?
     Great question, Andres. The Thread.sleep() is necessary because RxJava runs asynchronously, and without it, your main() method would exit before any emissions occur.
	 * so the emmitions do not ocurr in the main thread ?
       Correct, they do not occur in the main thread. RxJava’s emissions from operators like interval(), intervalRange(), timer(), etc., are handled by background threads, specifically from the Schedulers.computation() pool by default.
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
        Observable.intervalRange( //en esta linea, con esta sintaxis estamos ocupando el scheduler por defecto Schedulers.computation() y ya q estamos usando un scheduler el codigo es asincrono
                7,     // start value
                5,     // count of emissions --> en total obtenemos 5 Emmited: en el output
                0,     // initial delay
                3,     // period between emissions
                TimeUnit.SECONDS
        ).subscribe(i -> System.out.println("Emitted: " + i));

        Thread.sleep(16000); // Keep JVM alive long enough. a daemon thread is a background thread that runs behind the scenes and does not prevent the JVM from exiting when the main thread finishes.

	}

}
