package pe.cibertec.programacionreactiva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Ejemplo02Activity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo02);

        Observable<String> observableAnimales = getObservableAnimales();

        Observer<String> observerAnimales = getObserverAnimales();

        observableAnimales.subscribe(observerAnimales);
    }

    private Observer<String> getObserverAnimales() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Ejemplo02", "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d("Ejemplo02", "Nombre: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Ejemplo02", "Error: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("Ejemplo02", "Todos los items han sido emitidos");
            }
        };
    }

    private Observable<String> getObservableAnimales() {
        return Observable.just("Perro", "Gato", "Zorro");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
