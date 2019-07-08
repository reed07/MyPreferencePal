package com.twitter.sdk.android.tweetcomposer.internal.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    ScrollViewListener scrollViewListener;

    public interface ScrollViewListener {
        void onScrollChanged(int i);
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ScrollViewListener scrollViewListener2 = this.scrollViewListener;
        if (scrollViewListener2 != null) {
            scrollViewListener2.onScrollChanged(i2);
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener2) {
        this.scrollViewListener = scrollViewListener2;
    }
}
