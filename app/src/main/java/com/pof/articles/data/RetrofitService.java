package com.pof.articles.data;

import com.pof.articles.data.model.Article;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    String BaseURL = "https://5e99a9b1bc561b0016af3540.mockapi.io/";

    @GET("jet2/api/v1/blogs")
    Call<List<Article>> getArticles(@Query("page") String page, @Query("limit") String limit);

    @GET("jet2/api/v1/blogs")
    Single<List<Article>> getArticlesRx(@Query("page") String page, @Query("limit") String limit);
}
