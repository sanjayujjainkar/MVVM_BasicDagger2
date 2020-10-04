package com.pof.articles.dagger;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.pof.articles.ui.ArticleListFragment;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    @Component.Builder
    interface Builder {
        ActivityComponent Build();
        @BindsInstance
        Builder fragmentActivity(@Named("fragmentActivity") FragmentActivity fragmentActivity);

        Builder appComponent(AppComponent appComponent);
    }
    void inject(ArticleListFragment fragment);

}
