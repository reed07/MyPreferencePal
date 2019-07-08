package com.myfitnesspal.feature.goals.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class PremiumCtaItem implements ListItem {
    private Context context;
    private Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    private boolean reportedEvent;
    private Lazy<UserWeightService> userWeightService;

    public PremiumCtaItem(Context context2, Lazy<PremiumAnalyticsHelper> lazy, Lazy<UserWeightService> lazy2) {
        this.context = context2;
        this.premiumAnalyticsHelper = lazy;
        this.userWeightService = lazy2;
    }

    public int getViewType() {
        return PremiumCtaItem.class.getCanonicalName().hashCode();
    }

    public View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.goal_premium_cta, viewGroup, false);
        }
        float goalPerWeekWeight = ((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight();
        TextView textView = (TextView) ViewUtils.findById(view, R.id.subtext);
        if (goalPerWeekWeight == BitmapDescriptorFactory.HUE_RED) {
            textView.setText(this.context.getString(R.string.goals_premium_cta_subtitle_maintain));
        } else {
            textView.setText(this.context.getString(goalPerWeekWeight < BitmapDescriptorFactory.HUE_RED ? R.string.goals_premium_cta_subtitle_gain : R.string.goals_premium_cta_subtitle_loss));
        }
        if (!this.reportedEvent) {
            ((PremiumAnalyticsHelper) this.premiumAnalyticsHelper.get()).reportGoalsScreenPremiumCtaViewed();
            this.reportedEvent = true;
        }
        return view;
    }
}
