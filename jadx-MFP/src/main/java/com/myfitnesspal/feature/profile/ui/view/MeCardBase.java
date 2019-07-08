package com.myfitnesspal.feature.profile.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics;
import com.myfitnesspal.shared.ui.view.GenericCardBase;

public abstract class MeCardBase extends GenericCardBase {
    private MeAnalytics analytics = new MeAnalytics();

    public MeCardBase(Context context) {
        super(context);
    }

    public MeCardBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MeCardBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public final MeAnalytics getAnalytics() {
        return this.analytics;
    }

    /* access modifiers changed from: protected */
    public void reportCardTapped() {
        this.analytics.reportCardTapped(getAnalyticsType());
    }
}
