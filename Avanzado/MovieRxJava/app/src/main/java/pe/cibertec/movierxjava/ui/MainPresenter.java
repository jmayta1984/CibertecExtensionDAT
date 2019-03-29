package pe.cibertec.movierxjava.ui;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pe.cibertec.movierxjava.models.RespuestaPelicula;
import pe.cibertec.movierxjava.network.ClienteNetwork;
import pe.cibertec.movierxjava.network.InterfaceNetwork;

class MainPresenter implements MainPresenterInterface {
    MainViewInterface mvi;

    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;
    }

    @Override
    public void obtenerPeliculas() {

        getObservable().subscribe(getObserver());

    }

    public Observable<RespuestaPelicula> getObservable() {
        return ClienteNetwork
                .getRetrofit()
                .create(InterfaceNetwork.class)
                .getPeliculas("3cae426b920b29ed2fb1c0749f258325")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observer<RespuestaPelicula> getObserver() {
        return new Observer<RespuestaPelicula>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RespuestaPelicula respuestaPelicula) {
                mvi.mostrarPeliculas(respuestaPelicula);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("MainPresenter", "Completado");
            }
        };
    }
}
