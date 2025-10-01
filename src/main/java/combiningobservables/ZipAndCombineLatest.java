package combiningobservables;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class ZipAndCombineLatest {
	
	//notar q hasta q src2 empieza a emitir corre/combina mas rapido (no espera) y despues corre cada 1 segundo

	public static void main(String[] args) throws InterruptedException {
		
		Observable<Long> src1 = Observable.interval(200, TimeUnit.MILLISECONDS).take(10);

        Observable<Long> src2 = Observable.interval(1, TimeUnit.SECONDS).take(10);
        
        Observable.combineLatest(src1, src2, (e1, e2) -> "Source 1 : "+e1+" Source 2: " + e2)
        .subscribe(System.out::println);
        
        Thread.sleep(20000);


	}

}