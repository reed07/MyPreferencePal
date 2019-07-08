package com.myfitnesspal.feature.explore.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics;
import com.myfitnesspal.shared.ui.view.GenericCardBase;

public abstract class ExploreCardBase extends GenericCardBase {
    private ExploreAnalytics analytics = new ExploreAnalytics();

    public ExploreCardBase(Context context) {
        super(context);
    }

    public ExploreCardBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ExploreCardBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public ExploreAnalytics getAnalytics() {
        return this.analytics;
    }

    /* access modifiers changed from: protected */
    public void reportCardTapped() {
        this.analytics.reportCardTapped(getAnalyticsType());
    }
}
