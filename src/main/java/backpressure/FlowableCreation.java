package backpressure;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FlowableCreation {

	public static void main(String[] args) throws InterruptedException {
		
		Flowable.create(emitter -> {
		for(int i = 0; i <= 5000; i++ ) {
			if(emitter.isCancelled())
				return;
			emitter.onNext(i);
		}
		
		emitter.onComplete();
		}, BackpressureStrategy.BUFFER) //existen otros metodos aqui como .DROP .LATEST y otros mas
		.observeOn(Schedulers.io())
		.subscribe(System.out::println);
			
		Thread.sleep(2000);
		
		/*

		 * so generally speaking we push with BackpressureStrategy and pull with the annonymous class we pass in as argument to .subscribe() method ? is that it ?
You're circling the core beautifully, Andres—let’s refine it just a bit:
		 * General Principle
Yes: in RxJava with Flowable, the producer pushes using BackpressureStrategy, and the consumer pulls using request(n)—which is typically triggered inside the anonymous Subscriber passed to .subscribe().

But here’s the nuance:

✅ Push: BackpressureStrategy in Flowable.create()
The emitter pushes items as fast as it wants.

The strategy (BUFFER, DROP, LATEST, etc.) defines how to handle overflow if the consumer is slower.

java
Flowable.create(emitter -> {
    for (int i = 0; i < 1000; i++) {
        emitter.onNext(i); // push
    }
}, BackpressureStrategy.BUFFER)
✅ Pull: request(n) in Subscriber
The subscriber controls demand by calling request(n).

This is the pull signal that tells upstream how many items it can emit.

java
.subscribe(new Subscriber<Integer>() {
    Subscription s;

    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        s.request(20); // pull 20 items
    }

    @Override public void onNext(Integer item) { ... }
    @Override public void onError(Throwable t) { ... }
    @Override public void onComplete() { ... }
});
🧩 Summary
Role	Mechanism	Direction
Producer	Flowable.create() + onNext()	Push
Strategy	BackpressureStrategy	Push control
Consumer	request(n) in Subscriber	Pull
		 */
		
	}

}