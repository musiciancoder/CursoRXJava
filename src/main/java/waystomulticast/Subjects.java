package waystomulticast;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class Subjects {

	public static void main(String[] args) throws InterruptedException {
		
		//En este ejemplo solo se explica un caso no asincrono con varios observers a varios observables, 
		//pero los subject tambien se usan mucho con casos asincronos (la sintaxis es secomentando las lineas .subscribeOn(Schedulers.computation());)
		//y con un observer para varios observables y viceversa (multicasting)
		
		@NonNull
		Observable<Integer> src1 = Observable.just(5,10,15,20);  //Observable 1
		//	.subscribeOn(Schedulers.computation());
		
		Observable<Integer> src2 = Observable.just(50,100,150,200); //Observable 2
		//.subscribeOn(Schedulers.computation());
		
		
		@NonNull
		Subject<Object> subject = PublishSubject.create();
		
		subject.subscribe(e -> System.out.println(e));  //Observer 1
		subject.subscribe(e -> System.out.println("The element is "+ e)); //Observer 2
		
		src1.subscribe(subject);   //source 1
		src2.subscribe(subject);	//source 2
		
		//Thread.sleep(9000);
		
	}

}