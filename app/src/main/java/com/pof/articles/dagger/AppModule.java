package com.pof.articles.dagger;

import com.pof.articles.data.RetrofitService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    public Retrofit getRetrofit (OkHttpClient okHttpClient) {
        //initialise retrofit and instantiate RetrofitService
        return new Retrofit.Builder()
                .baseUrl(RetrofitService.BaseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public RetrofitService getRetrofitService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

}
