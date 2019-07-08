package com.myfitnesspal.feature.debug.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.debug.ui.fragment.AnalyticsEventsFragment;
import com.myfitnesspal.shared.service.analytics.AmplitudeService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.AnalyticsServiceWithHistory;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.analytics.MultiAnalyticsService;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity.EditablePagerAdapter;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity.EditablePagerAdapter.FragmentEntry;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction2;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class AnalyticsEventsActivity extends MfpPagedEditableActivity {
    @Inject
    Lazy<AnalyticsService> analyticsService;

    static class AnalyticsEventsPagerAdapter extends EditablePagerAdapter {
        private final Lazy<AnalyticsService> analyticsService;

        public AnalyticsEventsPagerAdapter(FragmentManager fragmentManager, Context context, Lazy<AnalyticsService> lazy) {
            super(fragmentManager, context);
            this.analyticsService = lazy;
        }

        /* access modifiers changed from: protected */
        public List<FragmentEntry> createFragmentList() {
            List list;
            AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
            if (analyticsService2 instanceof MultiAnalyticsService) {
                list = ((MultiAnalyticsService) analyticsService2).getServices();
            } else {
                list = Arrays.asList(new AnalyticsService[]{analyticsService2});
            }
            return Enumerable.select((Collection<T>) list, false, (ReturningFunction2<U, T, Integer>) new ReturningFunction2<FragmentEntry, AnalyticsService, Integer>() {
                public FragmentEntry execute(AnalyticsService analyticsService, Integer num) {
                    String str = "????";
                    if (analyticsService instanceof AmplitudeService) {
                        str = "Amplitude";
                    } else if (analyticsService instanceof MfpAnalyticsService) {
                        str = "Leolytics";
                    }
                    if (analyticsService instanceof AnalyticsServiceWithHistory) {
                        return new FragmentEntry((MfpEditableFragmentBase) AnalyticsEventsFragment.newInstance(num.intValue()), str);
                    }
                    return null;
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public int getContentViewId() {
        return R.layout.analytics_events;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AnalyticsEventsActivity.class);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public EditablePagerAdapter recreateAdapter() {
        return new AnalyticsEventsPagerAdapter(getSupportFragmentManager(), this, this.analyticsService);
    }
}
