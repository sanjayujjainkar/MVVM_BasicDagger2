package com.pof.articles.dagger;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.pof.articles.controller.ArticleViewModel;
import com.pof.articles.controller.ArticleViewModelFactory;
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
public abstract class ActivityModule {

    @PerActivity
    @Provides
    public static FragmentActivity getFragmentActivity(@Named("fragmentActivity") FragmentActivity fragmentActivity) {
        return fragmentActivity;
    }

    @PerActivity
    @Provides
    public static ArticleViewModel getArticleViewModel(@Named("fragmentActivity") FragmentActivity fragmentActivity, ArticleViewModelFactory articleViewModelFactory) {
        return ViewModelProviders.of(fragmentActivity, articleViewModelFactory).get(ArticleViewModel.class);
    }
}
