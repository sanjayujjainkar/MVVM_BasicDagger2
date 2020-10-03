package com.pof.articles.data;

import android.arch.lifecycle.LiveData;

import com.pof.articles.data.model.Article;

import java.util.List;

public interface IRepository {

    LiveData<List<Article>> getArticles();

    LiveData<List<Article>> getArticlesByRxJava();

}
