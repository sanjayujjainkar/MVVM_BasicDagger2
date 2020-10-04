package com.pof.articles.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.pof.articles.ArticleApplication;
import com.pof.articles.R;
import com.pof.articles.dagger.ActivityComponent;
import com.pof.articles.dagger.DaggerActivityComponent;

public class MainActivity extends FragmentActivity implements ArticleListFragment.Callback {
    private ActivityComponent activityComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ArticleListFragment fragment = new ArticleListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ArticleListFragment.TAG).commit();
        }

        activityComponent = DaggerActivityComponent.builder().fragmentActivity(this)
                                            .appComponent(((ArticleApplication)getApplication()).getAppComponent())
                                            .Build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void call(Bundle bundle) {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        transaction.addToBackStack("detail");
        transaction.add(R.id.fragment_container, detailFragment,"detail");
        transaction.commit();
    }

    //reset the selected row to original color.
    public void onDetailFragmentDismiss() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ArticleListFragment.TAG);
        ((ArticleListFragment)fragment).resetListView();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
