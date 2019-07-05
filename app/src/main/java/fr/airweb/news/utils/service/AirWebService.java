package fr.airweb.news.utils.service;

import fr.airweb.news.model.Item;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static fr.airweb.news.utils.service.ConstantUrl.BASE_URL;

public final class AirWebService {
    private static volatile AirWebService  instance = null;
    private AirWebGet airWebGet;


    private AirWebService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .build();
            return chain.proceed(newRequest);
        }).build();


        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            airWebGet = retrofit.create(AirWebGet.class);

    }



    public  static AirWebService getInstance() {
        if(instance == null) {
            synchronized (AirWebService.class) {
                if (instance == null) {
                    instance = new AirWebService();
                }

            }
        }
        return  instance;
    }

    public AirWebGet provideClient() {
        return airWebGet;
    }


 public interface  AirWebGet {

    @GET("psg/psg.json")
    Observable<Item> getItems();

 }


}


