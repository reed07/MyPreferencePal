package com.myfitnesspal.feature.home.ui.view;

import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.model.EmptyListDisplayItem;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;

public class EmptyItemViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    @BindView(2131363056)
    TextView messageTextView;

    public EmptyItemViewHolder(ViewGroup viewGroup) {
        super(R.layout.newsfeed_empty_text, viewGroup);
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        int i2;
        EmptyListDisplayItem emptyListDisplayItem = (EmptyListDisplayItem) newsFeedItem;
        switch (emptyListDisplayItem.getItemType()) {
            case EmptyTimeline:
                i2 = R.string.noNewsFeeds;
                break;
            case ErrorFetchingTimeline:
                i2 = R.string.no_internet_connection_available;
                break;
            case PrivateTimeline:
                i2 = R.string.feed_is_private;
                break;
            default:
                throw new IllegalStateException("Unknown empty item type!");
        }
        this.messageTextView.setText(i2);
    }
}
