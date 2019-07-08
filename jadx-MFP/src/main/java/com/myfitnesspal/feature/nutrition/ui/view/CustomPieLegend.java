package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.LegendData;
import com.myfitnesspal.shared.util.DrawableUtils;
import java.util.List;

public abstract class CustomPieLegend implements SeriesListener {
    private final ViewGroup container;
    private final Context context;

    public abstract void registerListener(LegendListener legendListener);

    public abstract void setLegendData(List<LegendData> list);

    public CustomPieLegend(Context context2, ViewGroup viewGroup) {
        this.context = context2;
        this.container = viewGroup;
    }

    public Context getContext() {
        return this.context;
    }

    public ViewGroup getContainer() {
        return this.container;
    }

    public Resources getResources() {
        return this.context.getResources();
    }

    /* access modifiers changed from: protected */
    public String getString(int i, String... strArr) {
        return getContext().getString(i, strArr);
    }

    private int getLegendDrawableSize() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.legend_label_size);
    }

    /* access modifiers changed from: protected */
    public Drawable getLegendDrawable(int i) {
        return DrawableUtils.getSquareDrawable(getLegendDrawableSize(), i);
    }
}
