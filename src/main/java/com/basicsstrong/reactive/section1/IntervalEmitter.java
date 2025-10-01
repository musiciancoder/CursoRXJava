package com.basicsstrong.reactive.section1;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;

import java.util.concurrent.TimeUnit;

public class IntervalEmitter {

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source = Observable.interval(2, 3, TimeUnit.SECONDS); //starting in two seconds, emit each 3 seconds un long, q por defecto en rxjava es 0

        source.subscribe(new DisposableObserver<Long>() {
            @Override
            public void onNext(Long value) {
                System.out.println("Received: " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });

        // Keep the program alive for demo purposes
        Thread.sleep(15000); // 15 seconds
    }
}
