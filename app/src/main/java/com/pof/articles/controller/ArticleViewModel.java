package com.pof.articles.controller;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.pof.articles.data.IRepository;
import com.pof.articles.data.model.Article;

import java.util.List;

import javax.inject.Inject;

public class ArticleViewModel extends ViewModel {

    private final LiveData<List<Article>> liveDataArticles;
    private IRepository repository;

    @Inject
    public ArticleViewModel(@NonNull IRepository repository) {
        this.repository = repository;
        liveDataArticles = this.repository.getArticlesByRxJava(); //getArticlesByRxJava() //getArticles();
    }

    /*public LiveData<List<Article>> getArticleLiveData() {
        return liveDataArticles;
    }*/

    public LiveData<List<Article>> getArticleLiveDataRx() {
        return liveDataArticles;
    }
}
