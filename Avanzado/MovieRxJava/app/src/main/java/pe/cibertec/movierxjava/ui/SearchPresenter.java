package pe.cibertec.movierxjava.ui;

import android.support.v7.widget.SearchView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import pe.cibertec.movierxjava.models.RespuestaPelicula;
import pe.cibertec.movierxjava.network.ClienteNetwork;
import pe.cibertec.movierxjava.network.InterfaceNetwork;

class SearchPresenter implements SearchPresenterInterface {

    SearchViewInterface svi;

    public SearchPresenter(SearchViewInterface svi) {
        this.svi = svi;
    }

    @Override
    public void obtenerPeliculas(SearchView searchView) {


        getObservable(searchView)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        if (s.equals("")) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .debounce(1, TimeUnit.SECONDS)
                .switchMap(new Function<String, Observable<RespuestaPelicula>>() {
                    @Override
                    public Observable<RespuestaPelicula> apply(String s) throws Exception {
                        return ClienteNetwork
                                .getRetrofit()
                                .create(InterfaceNetwork.class)
                                .getPeliculasPorNombre("3cae426b920b29ed2fb1c0749f258325", s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getObservable(SearchView searchView) {

        final PublishSubject<String> publishSubject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                publishSubject.onNext(newText);

                return true;
            }
        });
        return publishSubject;
    }

    private DisposableObserver<RespuestaPelicula> getObserver() {
        return new DisposableObserver<RespuestaPelicula>() {
            @Override
            public void onNext(RespuestaPelicula respuestaPelicula) {
                svi.mostrarPeliculas(respuestaPelicula);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


}
