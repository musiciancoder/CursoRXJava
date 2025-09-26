package com.basicsstrong.reactive.section1;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;

//Ver el codigo en rxjava2 q escribía al final !!
public class ObservableAndObserver {

	public static void main(String[] args) {
		//esta sintaxis es mas facil con expresiones lambda, de hecho la misma india lo dijo.
		Observable<Integer> source = new ObservableCreate<Integer>(new ObservableOnSubscribe<Integer>() {

			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
				try {
				emitter.onNext(10);
				emitter.onNext(11);
				emitter.onComplete();
				}catch(Throwable t) {
					emitter.onError(t);
				}
				
			}
		});
		
		
		Observer<Integer> observer = new Observer<Integer>() {

			@Override
			public void onSubscribe(@NonNull Disposable d) {
				System.out.println(" Subscribed ");
				
			}

			@Override
			public void onNext(@NonNull Integer t) {
				System.out.println("On Next : " + t);
				
			}

			@Override
			public void onError(@NonNull Throwable e) {
				e.printStackTrace();
				
			}

			@Override
			public void onComplete() {
				System.out.println("Completed !");
				
			}
		};
		
		
		source.subscribe(observer);
		
		
	}

}



/*
 * is this syntax valid in rxjava version 2 ?
 * The syntax you've written is almost correct, but there's a key issue: you're trying to instantiate ObservableCreate directly, which is not public API in RxJava 2. Instead, you should use the factory method Observable.create().
 Observable<Integer> source = Observable.create(new ObservableOnSubscribe<Integer>() {
    @Override
    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
        try {
            emitter.onNext(10);
            emitter.onNext(11);
            emitter.onComplete();
        } catch (Throwable t) {
            emitter.onError(t);
        }
    }
});

Observer<Integer> observer = new Observer<Integer>() {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        System.out.println("Subscribed");
    }

    @Override
    public void onNext(@NonNull Integer t) {
        System.out.println("On Next: " + t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Completed!");
    }
};

source.subscribe(observer);

 */


