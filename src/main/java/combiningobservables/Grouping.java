package combiningobservables;



import io.reactivex.rxjava3.core.Observable;
import com.basicsstrong.reactive.section1.operators.Employee;

public class Grouping {

	public static void main(String[] args) {
		
		Observable<Employee> obs = Observable.just(
				new Employee(101,"Alexa",60000,4.0),
				new Employee(123,"Dhwanit",94000,4.7),
				new Employee(236,"Mike",65000,4.0),
				new Employee(155,"Ella",85000,4.4),
				new Employee(443,"George",50000,3.6),
				new Employee(127,"Shreeja",85000,4.5),
				new Employee(509,"Daniel",60000,4.0),
				new Employee(344,"Lucy",94000,4.7),
				new Employee(509,"Harry",75000,4.3),
				new Employee(344,"Emma",55000,3.7)
				);
		
		obs.groupBy(e -> e.getRating()) //ordena por rating devolviendo un observable con todos los GroupsObservables (en este caso Alexa, Mike y Daniel son un GroupObservable y cada otro empleado otro GroupObservable). This groups the emitted employees into buckets based on their rating.
//It returns an Observable<GroupedObservable<Double, Employee>>, where:
//Each GroupedObservable represents one rating group.
//The key is the rating (e.g. 4.0, 4.7, etc.).//The values are the employees with that rating.
		.flatMapSingle( //flatMapSingle() is a specialized operator that works on an Observable<T> and transforms each item into a Single<R>, then flattens all those Single<R> emissions into a single Observable<R>.
				e -> e.toMultimap(key->e.getKey(), emp->emp.getName()))
		.subscribe(System.out::println);
	}

}