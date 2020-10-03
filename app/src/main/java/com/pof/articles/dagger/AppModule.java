package com.pof.articles.dagger;

import com.pof.articles.data.IRepository;
import com.pof.articles.data.Repository;
import com.pof.articles.data.RetrofitService;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {

    @Provides
    public static OkHttpClient getOkHttpClient(@Named("ctimeout") int ctimeout, @Named("rtimeout") int rtimeout, @Named("ctimeout") int wtimout) {
        return new OkHttpClient().newBuilder()
                .connectTimeout(ctimeout, TimeUnit.SECONDS)
                .readTimeout(rtimeout, TimeUnit.SECONDS)
                .writeTimeout(wtimout, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    public static Retrofit getRetrofit (OkHttpClient okHttpClient) {
        //initialise retrofit and instantiate RetrofitService
        return new Retrofit.Builder()
                .baseUrl(RetrofitService.BaseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public static RetrofitService getRetrofitService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @Binds
    public abstract IRepository getRepository(Repository repository);

}
