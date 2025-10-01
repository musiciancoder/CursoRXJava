package com.basicsstrong.reactive.section1;


import java.util.LinkedList;
import java.util.Queue;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/*
 * RxJava offers several stream types, each tailored to different use cases and semantics. These are often referred to as RxJava variants or reactive types. Here's a breakdown of the main ones:

🔹 1. Observable<T>
Emits 0 or more items

No backpressure support

Ideal for UI events, small data sets, or slow producers

🔹 2. Flowable<T>
Emits 0 or more items

Backpressure-aware (Reactive Streams compliant)

Use for high-throughput or unbounded sources

🔹 3. Single<T>
Emits exactly one item or an error

Great for one-shot operations like network calls or DB queries

🔹 4. Maybe<T>
Emits 0 or 1 item, or an error

Useful when a result may or may not be present (e.g., optional DB lookup)

🔹 5. Completable
Emits no items, just a completion or error

Perfect for actions like saving data, logging out, or side effects

🧠 Summary Table
Type	Emits	Backpressure	Use Case Example
Observable	0..N items	❌ No	UI events, finite lists
Flowable	0..N items	✅ Yes	Kafka streams, large datasets
Single	Exactly 1 item	❌ No	API call returning one result
Maybe	0 or 1 item	❌ No	Optional DB lookup
Completable	No items	❌ No	Save operation, logout flow
 */

public class Variants {

	public static void main(String[] args) {
		
		Observable<String> source = Observable.just("Alex", "Justin", "Jack");
		Observable<String> source1 = Observable.empty();
		
		
		
		source1
		.first("Name")
		.subscribe(System.out::println);
		
		Single.just("Alex") //returns a Single
		.subscribe(System.out::println);
		
		
		source1
		.firstElement() //returns a Maybe
		.subscribe(System.out::println, e-> e.printStackTrace(), () -> System.out.println("Completed"));
		
		Completable completable = Completable.complete();
		
		System.out.println();
		
		completable.subscribe(() -> System.out.println("Completed"));
		
		Completable
		.fromRunnable(() -> System.out.println("Some process executing")) //esto es soo para informar q esta corriendo
		.subscribe(()-> System.out.println("The process executed succesfully"));//esta es a ejecucion de subscribe, q solo se ejecuta cuando ha sido competado
		
	}

}