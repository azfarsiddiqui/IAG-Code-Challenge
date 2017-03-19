package au.com.iag.dal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import au.com.iag.network.deserializer.DateTimeDeserializer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siddiquim on 3/19/17.
 */

public abstract class BaseDataLayer {

    protected abstract String getBaseUrl();

    Retrofit mRetrofit;

    BaseDataLayer() {
        setupRetrofitInstance();
    }

    private void setupRetrofitInstance() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(getGsonConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getBaseUrl())
                .build();
    }

    protected Retrofit getRetrofit() {
        return mRetrofit;
    }

    private GsonConverterFactory getGsonConverterFactory() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTimeDeserializer()).create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        return gsonConverterFactory;
    }
}
