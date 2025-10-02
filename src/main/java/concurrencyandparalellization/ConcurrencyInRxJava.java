package concurrencyandparalellization;


import io.reactivex.rxjava3.core.Observable;

public class ConcurrencyInRxJava {
//observable contract: "the emissions should be passed sequentially and one at a time"
	//la india dijo q aunq este codigo es Asincrono, para la emision del oservable sigue siendo sincrono. La asincronia viene en el lado del observer,
	//ya que este recibe en forma asincrona. Sin embargo, copilot lo corrigió ya q el observer es pasivo en rxjava.
	//es mas correcto decir que "para cada thread la emision del observable es sequencial y por lo tanto la recepcion para ese thread tambien es secuencial	
	// para elementos de ese thread, pero logicamente elementos de diferentes thread se intercalan, por ello es asincrono "
	public static void main(String[] args) {
		
		Observable<String> source = Observable.create(
				
				e -> {
					new Thread( () ->
					{
						e.onNext("Hello");
						e.onNext("RxJava");
					}
						).start();
				}
				);
		
		source
		.subscribe(e -> System.out.println("Observer 1 :"+e + " Thread is :" +Thread.currentThread().getName()));
		
		source
		.subscribe(e -> System.out.println("Observer 2 :"+e+ " Thread is :" +Thread.currentThread().getName()));
		
	}

}