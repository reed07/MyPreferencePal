package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.myfitnesspal.android.R;

public class DividerItemDecorator extends ItemDecoration {
    private Drawable divider;

    public DividerItemDecorator(Context context) {
        this.divider = context.getResources().getDrawable(R.drawable.recycling_view_divider_line);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount() - 1;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((LayoutParams) childAt.getLayoutParams()).bottomMargin;
            this.divider.setBounds(paddingLeft, bottom, width, this.divider.getIntrinsicHeight() + bottom);
            this.divider.draw(canvas);
        }
    }
}
