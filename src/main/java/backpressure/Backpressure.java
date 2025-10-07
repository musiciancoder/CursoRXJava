package backpressure;

import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Backpressure {

	public static void main(String[] args) {
		
		Flowable.range(1, 1000000)
		.map(e -> {
			System.out.println("Produced item is : "+ e + " : "+ Thread.currentThread().getName());
			return e;
		})
		.observeOn(Schedulers.io())
		.subscribe( new Subscriber<Integer>() {
			
			Subscription s;
			/*
			 * An AtomicInteger in Java is a thread-safe, lock-free class used to manage an int value that can be safely updated by multiple threads without synchronization.
			It is not an atomic and thread safe.
			 */
			AtomicInteger count = new AtomicInteger(0); 

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				System.out.println("Asking for 20 items onSubscribe");
				s.request(20);
				
			}

			@Override
			public void onNext(Integer t) {
				//ojo, q segun copilot si bien ount.getAndIncrement() es atomico, todo el bloque if no lo es ni tampoco necesariamente el bloque if es thread-safe
				
				if(count.getAndIncrement() % 20 ==0) {
					System.out.println("Asking for next 20 items onNext");
					s.request(20);
				}
				
				System.out.println("The subscriber consumed : "+ t);
				sleep(100);
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				
			}

			@Override
			public void onComplete() {
				System.out.println("Completed");
				
			}
		}
				
				
				
				);
	
		sleep(100000000);
		

	}

	private static void sleep(long milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}