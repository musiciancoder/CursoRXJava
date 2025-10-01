package combiningobservables;



import io.reactivex.rxjava3.*;
import io.reactivex.rxjava3.core.*;
import java.util.concurrent.TimeUnit;

/*
in rxjava, difference between merge and concat
Great question, Andres. In RxJava, merge() and concat() are both used to combine multiple Observables, but they differ in timing, ordering, and concurrency.

🔀 merge() — Concurrent Interleaving
Emits items from all sources as they arrive

Does not wait for one Observable to complete before subscribing to the next

Ideal for parallel or asynchronous sources


*/

public class MergeConcat{
	
	public static void main(String[] args) {
		
	
	
Observable<Long> o1 = Observable.interval(2,TimeUnit.SECONDS);
Observable<Long> o2 = Observable.interval(2,TimeUnit.SECONDS);

Observable.merge(o1, o2)
    .subscribe(System.out::println);

/*
🔹 Output: Items from o1 and o2 interleave based on timing.

📚 concat() — Sequential Composition
Subscribes to one Observable at a time

Waits for the first to complete before moving to the next

Guarantees order of emission
*/
Observable<Integer> o3 = Observable.just(1, 2, 3);
Observable<Integer> o4 = Observable.just(100, 200, 300);

Observable.concat(o3, o4)
    .subscribe(System.out::println);

/*🔹 Output: 1, 2, 3, 100, 200, 300 — strictly ordered

🧠 Summary Table
Feature	merge()	concat()
Emission order	Interleaved (non-deterministic)	Sequential (deterministic)
Concurrency	✅ Concurrent	❌ Sequential
Waits for completion	❌ No	✅ Yes
Use case	Parallel APIs, async sources	Ordered tasks, batch pipelines
So if you're building an ETL pipeline and want to preserve order, use concat(). 
*/
}
	
}

