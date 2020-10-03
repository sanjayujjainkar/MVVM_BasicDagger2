package com.pof.articles;

import android.app.Application;

import com.pof.articles.dagger.AppComponent;
import com.pof.articles.dagger.DaggerAppComponent;

import javax.inject.Named;

public class ArticleApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
                        /*.connectionTimeOut(10)
                        .readTimeOut(10)
                        .writeTimeOut(11)
                        .Build();*/
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
