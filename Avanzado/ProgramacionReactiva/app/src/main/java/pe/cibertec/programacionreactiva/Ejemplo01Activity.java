package pe.cibertec.programacionreactiva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Ejemplo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo01);

        //Observable
        Observable<String> observableAnimales = getObservableAnimales();

        //Observer
        Observer<String> observerAnimales = getObserverAnimales();

        observableAnimales.subscribe(observerAnimales);
    }

    private Observer<String> getObserverAnimales() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Ejemplo01", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("Ejemplo01", "Nombre: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Ejemplo01", "Error: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("Ejemplo01", "Todos los items han sido emitidos");
            }
        };
    }

    private Observable<String> getObservableAnimales() {
        return Observable.just("Perro", "Gato", "Zorro");
    }


}
