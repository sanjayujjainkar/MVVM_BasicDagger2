package com.pof.articles.ui;

import android.view.View;

import com.pof.articles.data.model.GitCommit;

public interface ItemClickListener {
    void onListItemClick(View view, GitCommit gitCommit);
}
