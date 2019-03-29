package pe.cibertec.programacionreactiva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class Ejemplo03Activity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo03);

        Observable<String> observableAnimales = getObservableAnimales();

        Observer<String> observerAnimales = getObserverAnimales();

        observableAnimales.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s.toLowerCase().startsWith("z");
            }
        }).subscribe(observerAnimales);
    }

    private Observer<String> getObserverAnimales() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Ejemplo03", "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d("Ejemplo03", "Nombre: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Ejemplo03", "Error: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("Ejemplo03", "Todos los items han sido emitidos");
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
