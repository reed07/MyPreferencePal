package com.myfitnesspal.shared.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public final class HorizontalRecyclerViewUtil {
    public static int findCenteredItemPosition(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, int i, int i2) {
        int width = recyclerView.getWidth() / 2;
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
            View findViewByPosition = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition != null) {
                float decoratedLeft = (float) linearLayoutManager.getDecoratedLeft(findViewByPosition);
                float decoratedRight = (float) linearLayoutManager.getDecoratedRight(findViewByPosition);
                float f = (float) width;
                if (decoratedLeft < f && decoratedRight >= f) {
                    return Math.max(i, Math.min(i2, findFirstVisibleItemPosition));
                }
            }
        }
        return i;
    }
}
