package combiningobservables;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class FlatMapConcatMap {
	
	/*
	 * 
Feature	merge()	flatMap()
Input	Multiple Observables	One Observable (users en el caso de mas abajo) + mapping function (getAlertsForUser() en caso de mas abajo)
Purpose	Combine emissions	Transform + combine
Interleaving	✅ Yes (if async)	✅ Yes (if async)
Order preservation	❌ No	❌ No (use concatMap() for order)
Use case	Combine known streams	Dynamic stream generation per item
	 */
	
	
		
	    public static void main(String[] args) throws InterruptedException {
	        Observable<String> users = Observable.just("Alice", "Bob", "Charlie");

	        users
	            .flatMap(user -> getAlertsForUser(user))
	            .subscribe(alert -> System.out.println("Received alert: " + alert));
	        
	        Thread.sleep(150000);
	        
	        /*
	         * output: 
Received alert: Alice - Alert 1
Received alert: Bob - Alert 1
Received alert: Charlie - Alert 1
Received alert: Bob - Alert 2
Received alert: Alice - Alert 2
Received alert: Charlie - Alert 2
Notar q estan intercalados para "Alice", "Bob", "Charlie"
	         */

	}
	
	static Observable<String> getAlertsForUser(String user) {
	    return Observable
	        .intervalRange(1, 2, 0, 1, TimeUnit.SECONDS)
	        .map(i -> user + " - Alert " + i);
	}

}
