package com.pof.articles.dagger;

import com.pof.articles.ui.ArticleListFragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component (modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        AppComponent Build();
        @BindsInstance
        Builder connectionTimeOut(@Named("ctimeout") int connection_time_out);
        @BindsInstance
        Builder readTimeOut(@Named("rtimeout") int read_time_out);
        @BindsInstance
        Builder writeTimeOut(@Named("wtimeout") int write_time_out);

    }

    public void inject(ArticleListFragment fragment);

}
