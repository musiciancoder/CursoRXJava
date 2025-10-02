package concurrencyandparalellization;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IOScheduler {

	public static void main(String[] args) throws InterruptedException {
		
		Observable<String> src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
				.subscribeOn(Schedulers.io());

		src.subscribe(e -> ioOeration());
		
		Thread.sleep(6000); //hasta aqui necesita solo un hilo pq tiene una subscripcion solamente
		//desde este punto en adelante se necesitan mas hilos, asi que se van creando a medida q se necesitan, a diferencia de computation scheduler donde la cntidad de hilos es fija.
		src.subscribe(e -> ioOeration());
		src.subscribe(e -> ioOeration());
		src.subscribe(e -> ioOeration());
		src.subscribe(e -> ioOeration());

		Thread.sleep(500000);
}


	public static void ioOeration() throws InterruptedException {
	
		Thread.sleep(1000);
		System.out.println("Computation Done By : "+ Thread.currentThread().getName());
}

}