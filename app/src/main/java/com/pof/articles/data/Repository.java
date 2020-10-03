package com.pof.articles.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.pof.articles.data.model.Article;
import com.pof.articles.data.model.CommitterInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static Repository repository;
    private RetrofitService retrofitService;
    private String TAG = Repository.class.getSimpleName();
    final MutableLiveData<List<Article>> liveData = new MutableLiveData<>();
    private List<Article> articles = new ArrayList<Article>();

    @Inject
    public Repository(RetrofitService retrofitService){
        this.retrofitService = retrofitService;
    }

    public LiveData<List<Article>> getArticles() {

        retrofitService.getArticles("1","10").enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.d(TAG, t.getStackTrace().toString());
                liveData.setValue(null);
            }
        });
        return liveData;
    }

    public LiveData<List<Article>> getArticlesByRxJava() {
        //final MutableLiveData<List<Article>> liveData = new MutableLiveData<>();

        retrofitService.getArticlesRx("1", "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("abcdoFinallyCalled");
                    }
                })
                .subscribe(new SingleObserver<List<Article>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("abconSubscribe called.");
                    }

                    @Override
                    public void onSuccess(List<Article> articles) {
                        liveData.setValue(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.setValue(null);
                    }
                });
        return liveData;

    }

    /*public Single<List<Article>> getArticlesByRxJava() {
       Single single =  Single.create(new SingleOnSubscribe() {
           @Override
           public void subscribe(SingleEmitter emitter) throws Exception {

           }
       });
        retrofitService.getArticlesRx("1", "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Article>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe called.");
                    }

                    @Override
                    public void onSuccess(List<Article> articls) {
                        articles.addAll(articls);
                    }

                    @Override
                    public void onError(Throwable e) { }
                });
        return Single.just(articles);

    }*/





}
