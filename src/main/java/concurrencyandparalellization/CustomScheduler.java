package concurrencyandparalellization;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CustomScheduler { //notar q aqui no hay Thread.sleep() como en los casos anteriores, pq cuando creamos threads con Executorservice no se crean daemon threads
	//a daemon thread is a background thread that runs behind the scenes and does not prevent the JVM from exiting when the main thread finishes.

	public static void main(String[] args) throws InterruptedException {
		
		
		ExecutorService executor = Executors.newFixedThreadPool(20); //creamos 20 threads...
		
		@NonNull
		Scheduler scheduler = Schedulers.from(executor);


		Observable<String> src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
								.subscribeOn(scheduler)
								.doFinally(executor :: shutdown);

		//...pero de esos 20 threads solo ocupamos 6 pq solo hay 6 tareas aqui
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());

	}

	public static void compute() throws InterruptedException {

			Thread.sleep(1000);
			System.out.println("Computation Done By : " + Thread.currentThread().getName());
	}

}