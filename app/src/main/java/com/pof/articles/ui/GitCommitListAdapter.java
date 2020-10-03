package com.pof.articles.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pof.articles.R;
import com.pof.articles.data.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GitCommitListAdapter extends RecyclerView.Adapter<GitCommitListAdapter.ViewHolder>{

    private List<Article> listArticles = new ArrayList<Article>();
    private Context context;
    private ItemClickListener itemClickListener;
    private boolean isDetailViewOpened;
    private View itemSelected;

    public GitCommitListAdapter(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }


    public void updateGitCommitList(List<Article> articles) {
        listArticles = articles;
        notifyDataSetChanged();
    }

    public void resetListView() {
        this.isDetailViewOpened = false;
        this.itemSelected.setBackgroundColor(context.getResources().getColor(R.color.colorWhile));
    }


    @NonNull
    @Override
    public GitCommitListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.article_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        Log.d("Adapter", "Viewholder created");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GitCommitListAdapter.ViewHolder viewHolder, int position) {
        final Article article = listArticles.get(position);
        Picasso.with(context)
                .load(article.media.get(0).image)
                .fit().centerCrop()
                .error(R.drawable.defaultimage)
                .into(viewHolder.imageArticle);
        Picasso.with(context)
                .load(article.user.get(0).avatar)
                .fit().centerCrop()
                .error(R.drawable.defaultimage)
                .into(viewHolder.imageUser);
        viewHolder.username.setText(article.user.get(0).name+" "+article.user.get(0).lastname);
        viewHolder.designation.setText(article.user.get(0).designation);
        viewHolder.content.setText(article.content);
        viewHolder.title.setText(article.media.get(0).title);
        viewHolder.articleURL.setText(article.media.get(0).url);
    }

    @Override
    public int getItemCount() {
        return listArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageUser, imageArticle;
        private TextView username, designation, content, title, articleURL;

        private ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageUser = itemView.findViewById(R.id.userimage);
            imageArticle = itemView.findViewById(R.id.articleimage);
            username = itemView.findViewById(R.id.username);
            designation = itemView.findViewById(R.id.designation);
            content = itemView.findViewById(R.id.content);
            title = itemView.findViewById(R.id.title);
            articleURL = itemView.findViewById(R.id.url);
        }
    }

}
