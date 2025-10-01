package waystomulticast;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class UsingSubjectsToAddEmisions {

		public static void main(String[] args) {
			
			//No es mu bueno usar subjects para emitir elementos.
			/*
			 * ⚠️ When to Avoid It
Avoid using Subject.onNext() as your default emission strategy in production flows because:

It breaks the reactive contract—you’re manually pushing instead of composing.

It bypasses backpressure, error handling, and lifecycle guarantees.

It’s harder to test and reason about, especially in concurrent flows.

It can lead to memory leaks or unexpected behavior if not carefully managed.

🧠 Better Alternatives
Use Observable.create() if you need fine-grained control but want to stay reactive.

Use Processor (in Reactor) or Emitter with Flowable.create() for backpressure-aware flows.

Use flatMap(), concatMap(), or switchMap() to transform and emit reactively.
			 */

			@NonNull
			PublishSubject<String> subject = PublishSubject.create();
			@NonNull
			Subject<String> serialized = subject.toSerialized();  //Thread safe
			

			
			serialized.subscribe(System.out::println);
			serialized.subscribe(e -> System.out.println("Observer 2 "+ e));
			
			serialized.onNext("Hello");
			serialized.onNext("BasicsStrong");
			serialized.onComplete();
			serialized.onNext("BasicsStrong");
			
			
		}

	}
