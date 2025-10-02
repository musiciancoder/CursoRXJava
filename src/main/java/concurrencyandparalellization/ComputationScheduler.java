package concurrencyandparalellization;



import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ComputationScheduler {

	public static void main(String[] args) throws InterruptedException {
		
		
		
		Observable<String> src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
				.subscribeOn(Schedulers.computation()); //los threads q se crean aqui son reusados.
		
		//el output da seis hilos pq dispongo de 6 cores libres (uno por subscripcion), sin embargo a la instructora q tiene un notebook pajero le da cuatro hilos
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		src.subscribe(e -> compute());
		
		Thread.sleep(50000);
	
	}
	
	
	public static void compute() throws InterruptedException {
		
			Thread.sleep(1000);
			System.out.println("Computation Done By : "+ Thread.currentThread().getName());
	}

}