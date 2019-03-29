package pe.cibertec.movierxjava.network;

import io.reactivex.android.plugins.RxAndroidPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteNetwork {


    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build();
        }
        return retrofit;
    }
}
