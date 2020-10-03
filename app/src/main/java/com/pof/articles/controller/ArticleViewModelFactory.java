package com.pof.articles.controller;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pof.articles.data.IRepository;
import com.pof.articles.data.Repository;

import javax.inject.Inject;

public class ArticleViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    IRepository repository;
    @Inject
    public ArticleViewModelFactory(IRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticleViewModel.class)) {
            return (T) new ArticleViewModel(repository);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
