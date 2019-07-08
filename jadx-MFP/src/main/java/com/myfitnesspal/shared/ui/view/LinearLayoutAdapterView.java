package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public abstract class LinearLayoutAdapterView extends LinearLayout {
    private static Object DIVIDER_TAG = new Object();
    private ItemDecorator decorator;
    private Drawable divider;
    private float dividerHeight;
    private float dividerHorizPaddingPx;
    private OnClickListener internalClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (LinearLayoutAdapterView.this.listener != null) {
                LinearLayoutAdapterView.this.listener.onClick(view, LinearLayoutAdapterView.this.getIndexOfView(view));
            }
        }
    };
    /* access modifiers changed from: private */
    public OnItemClickListener listener;
    private boolean showTrailingDivider;

    public interface ItemDecorator {
        void decorate(LinearLayoutAdapterView linearLayoutAdapterView, View view, int i);
    }

    public interface OnItemClickListener {
        void onClick(View view, int i);
    }

    /* access modifiers changed from: protected */
    public abstract View createView(int i);

    /* access modifiers changed from: protected */
    public abstract int getCount();

    public LinearLayoutAdapterView(Context context) {
        super(context);
    }

    public LinearLayoutAdapterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LinearLayoutAdapterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDecorator(ItemDecorator itemDecorator) {
        this.decorator = itemDecorator;
        recreateChildren();
    }

    public void setDivider(Drawable drawable, float f) {
        this.divider = drawable;
        this.dividerHeight = TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    public void setDivider(int i, float f) {
        setDivider((Drawable) new ColorDrawable(getResources().getColor(i)), f);
    }

    public void setDividerHorizontalPadding(int i) {
        if (i == 0) {
            this.dividerHorizPaddingPx = BitmapDescriptorFactory.HUE_RED;
        } else {
            this.dividerHorizPaddingPx = (float) getContext().getResources().getDimensionPixelSize(i);
        }
        recreateChildren();
    }

    public void setShowDividerAfterLastItem(boolean z) {
        this.showTrailingDivider = z;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener != this.listener) {
            this.listener = onItemClickListener;
            recreateChildren();
        }
    }

    /* access modifiers changed from: private */
    public int getIndexOfView(View view) {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt == view) {
                return i;
            }
            i += childAt.getTag() == DIVIDER_TAG ? 0 : 1;
        }
        return -1;
    }

    private View createDivider() {
        View view = new View(getContext());
        LayoutParams layoutParams = new LayoutParams(-1, (int) this.dividerHeight);
        float f = this.dividerHorizPaddingPx;
        if (((double) f) > 0.0d) {
            layoutParams.leftMargin = Math.round(f);
            layoutParams.rightMargin = Math.round(this.dividerHorizPaddingPx);
        }
        view.setTag(DIVIDER_TAG);
        view.setBackgroundDrawable(this.divider);
        view.setLayoutParams(layoutParams);
        return view;
    }

    /* access modifiers changed from: protected */
    public void recreateChildren() {
        removeAllViews();
        int count = getCount();
        for (int i = 0; i < count; i++) {
            View createView = createView(i);
            ItemDecorator itemDecorator = this.decorator;
            if (itemDecorator != null) {
                itemDecorator.decorate(this, createView, i);
            }
            addView(createView);
            if (this.divider != null && (this.showTrailingDivider || i < count - 1)) {
                addView(createDivider());
            }
            if (this.listener != null) {
                createView.setOnClickListener(this.internalClickListener);
            }
        }
    }
}
