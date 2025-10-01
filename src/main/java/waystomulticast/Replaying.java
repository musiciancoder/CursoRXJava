package waystomulticast;


import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class Replaying {

	public static void main(String[] args) throws InterruptedException {
		
		
		@NonNull
		Observable<@NonNull Long> src = Observable.interval(1, TimeUnit.SECONDS)
		.replay( //devuelve un ConnectableObservable, y por lo tanto no comienza a emitir cuando se subscribe sino cuando connect() o autoconnect() es invocado
				2,4,TimeUnit.SECONDS) //el nuevo observer (Observer 2 en este caso) va a tener acceso a 2 elementos emitidos antes de q Observer 2 se subscribiera
				.autoConnect(); //aca empieza la emision
		
		src.subscribe(e -> System.out.println("Observer 1 : "+e));
		
		Thread.sleep(5000); //para q no se subsciba todavia observer2
		
		src.subscribe(e -> System.out.println("Observer 2 : "+e));
		
		Thread.sleep(3000); //para q no termine el hilo principal antes q comience a emitir el observer2
	}

}