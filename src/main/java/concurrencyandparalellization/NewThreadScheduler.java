package concurrencyandparalellization;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewThreadScheduler {

	public static void main(String[] args) throws InterruptedException {
		
		Observable<String> src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
								.subscribeOn(Schedulers.newThread());
		
		src.subscribe(e -> task()); //esta tarea se ejecuta en  RxNewThreadScheduler-1
		
		Thread.sleep(6000); //como terminó la tarea antes de los 6 segundos,  RxNewThreadScheduler-1 se elimina
		//notar q a partir de este punto ya no hay mas RxNewThreadScheduler-1 pq se eliminó 
		src.subscribe(e -> task());
		src.subscribe(e -> task());
		src.subscribe(e -> task());
		src.subscribe(e -> task());
		
		Thread.sleep(500000);
	}
	
	
	public static void task() throws InterruptedException {
		
			Thread.sleep(1000);
			System.out.println("Computation Done By : "+ Thread.currentThread().getName());
	}

}