package timesensitiveemisions;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class Buffering {

	public static void main(String[] args) throws InterruptedException {
	/*
		//sincono
		Observable.range(1, 30)
		.buffer(4, 7)
		.subscribe(System.out::println);
			*/
		
		//asincrono
		Observable.interval(1000, TimeUnit.MILLISECONDS) //emite cada 1 s
		.buffer(4, TimeUnit.SECONDS) //each buffer lasting 4s
		.subscribe(System.out::println);
		
		Thread.sleep(12000);
		
	/*	
		@NonNull
		Observable<Long> interval = Observable.interval(500, TimeUnit.MILLISECONDS);
		
		Observable.interval(1000, TimeUnit.MILLISECONDS)
		.window(interval)
		.subscribe(System.out::println);
		
		Thread.sleep(8000);
		*/

	}

}