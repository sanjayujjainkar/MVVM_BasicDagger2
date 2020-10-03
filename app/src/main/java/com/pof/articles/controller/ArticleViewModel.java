package com.pof.articles.controller;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.pof.articles.data.Repository;
import com.pof.articles.data.model.Article;

import java.util.List;

import io.reactivex.Single;

public class ArticleViewModel extends ViewModel {

    private final LiveData<List<Article>> liveDataArticles;
    private Repository repository;

    public ArticleViewModel(@NonNull Repository repository) {
        this.repository = repository;
        liveDataArticles = repository.getArticlesByRxJava(); //getArticlesByRxJava() //getArticles();
    }

    /*public LiveData<List<Article>> getArticleLiveData() {
        return liveDataArticles;
    }*/

    public LiveData<List<Article>> getArticleLiveDataRx() {
        return liveDataArticles;
    }
}
