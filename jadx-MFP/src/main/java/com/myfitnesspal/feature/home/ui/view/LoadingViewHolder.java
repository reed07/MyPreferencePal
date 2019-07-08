package com.myfitnesspal.feature.home.ui.view;

import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;

public class LoadingViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    public void setData(NewsFeedItem newsFeedItem, int i) {
    }

    public LoadingViewHolder(ViewGroup viewGroup) {
        super(R.layout.newsfeed_loading, viewGroup);
    }
}
