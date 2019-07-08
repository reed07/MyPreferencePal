package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

public final class d extends RecyclerView {
    public d(Context context) {
        super(context);
        setCarouselLayoutManager(context);
    }

    private void setCarouselLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, 0, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        super.setLayoutManager(linearLayoutManager);
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) super.getLayoutManager();
    }

    public void setLayoutManager(LayoutManager layoutManager) {
    }
}
