package timesensitiveemisions;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Throttling {

	public static void main(String[] args) {

		Observable<String> obs = Observable.create(emitter -> {
			emitter.onNext("A");

			Thread.sleep(200);
			emitter.onNext("B");

			Thread.sleep(100);
			emitter.onNext("C");

			Thread.sleep(400);
			emitter.onNext("D");

			Thread.sleep(300);
			emitter.onNext("E");

			Thread.sleep(800);
			emitter.onNext("F");

			Thread.sleep(900);
			emitter.onNext("X");

			Thread.sleep(600);
			emitter.onNext("Y");

			Thread.sleep(1000);
			emitter.onNext("Z");

			emitter.onComplete();
		});

		
		obs
		.throttleFirst(1000, TimeUnit.MILLISECONDS) // va de un segundo en un segundo y se queda solo con la primera emision mas proxima a ese segundo, descartando las demas de ese intervalo de tiempo
		//.throttleLast(1000, TimeUnit.MILLISECONDS) // va de un segundo en un segundo y se queda solo con la ultima emision mas proxima a ese segundo, descartando las demas de ese intervalo de tiempo
		//.debounce(700, TimeUnit.MILLISECONDS) // descarta todas las emisiones hasta 700 miliseg y se queda con todas las demas a partir de 700 miliseg
		.subscribe(
				item -> System.out.println("onNext: " + item),
				Throwable::printStackTrace,
				() -> System.out.println("onComplete")
				);

	}

}