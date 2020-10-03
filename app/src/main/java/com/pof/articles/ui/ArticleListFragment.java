package com.pof.articles.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pof.articles.ArticleApplication;
import com.pof.articles.R;
import com.pof.articles.controller.ArticleViewModel;
import com.pof.articles.controller.ArticleViewModelFactory;
import com.pof.articles.dagger.AppComponent;
import com.pof.articles.data.model.Article;
import com.pof.articles.data.model.GitCommit;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ArticleListFragment extends Fragment implements ItemClickListener {

    private GitCommitListAdapter gitCommitListAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtErrorMsg;
    private Callback callback;
    public static String TAG = "GitCommitListFragment";

    @Inject
    ArticleViewModelFactory articleViewModelFactory;
    //private View selectedRow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commit_list, container, false);
        gitCommitListAdapter = new GitCommitListAdapter(getActivity(),this);
        progressBar = view.findViewById(R.id.progressBar);
        txtErrorMsg = view.findViewById(R.id.txtError);
        recyclerView = view.findViewById(R.id.commit_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(gitCommitListAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppComponent component = ((ArticleApplication)(getActivity().getApplication())).getAppComponent();
        component.inject(this);
        final ArticleViewModel viewModel = ViewModelProviders.of(this, articleViewModelFactory).get(ArticleViewModel.class);

        viewModel.getArticleLiveDataRx().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> gitCommits) {
                if(gitCommits!=null) {
                    gitCommitListAdapter.updateGitCommitList(gitCommits);
                    Log.d("GitCommitListFragment", "adapter updated with new list");
                } else {
                    ErrorHandling.showToast(getActivity(),"Something went wrong...Try again later");
                    txtErrorMsg.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity)context;
        callback = (Callback)mainActivity;
    }

    @Override
    public void onListItemClick(View view, GitCommit gitCommit) {
        showDetail(gitCommit);
    }

    public void resetListView() {
        gitCommitListAdapter.resetListView();
    }

    private void showDetail(GitCommit gitCommit) {
        Bundle bundle = new Bundle();
        bundle.putString("sha", gitCommit.getSha());
        bundle.putString("message",gitCommit.getCommit().getMessage());
        bundle.putString("date",gitCommit.getCommit().getCommitter().getDate());
        bundle.putString("login",gitCommit.getCommitter().getLogin());
        bundle.putString("avtar",gitCommit.getCommitter().getAvatar_url());
        bundle.putString("html_url",gitCommit.getCommitter().getHtml_url());
        bundle.putString("url",gitCommit.getCommitter().getUrl());

        callback.call(bundle);

    }

    public interface Callback {
        void call(Bundle bundle);
    }

}
