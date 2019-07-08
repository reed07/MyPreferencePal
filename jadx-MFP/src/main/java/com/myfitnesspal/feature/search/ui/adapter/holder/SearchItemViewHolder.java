package com.myfitnesspal.feature.search.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.ui.view.ViewHolder;

public class SearchItemViewHolder extends ViewHolder<DiaryEntryCellModel> implements RequiresSearchQuery {
    private final Context context;
    private String queryString;
    private final TextView txtTypedAheadFilter = ((TextView) findById(R.id.txtTypedAheadFilter));

    public SearchItemViewHolder(View view) {
        super(view);
        this.context = view.getContext().getApplicationContext();
    }

    public void setData(DiaryEntryCellModel diaryEntryCellModel, int i) {
        this.txtTypedAheadFilter.setText(this.context.getString(R.string.search_all_foods, new Object[]{this.queryString}));
    }

    public void setSearchQuery(String str) {
        this.queryString = str;
    }
}
