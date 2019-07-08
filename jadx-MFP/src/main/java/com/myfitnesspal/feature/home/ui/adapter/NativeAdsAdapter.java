package com.myfitnesspal.feature.home.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter.AdapterOperationListener;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import dagger.Lazy;
import java.util.List;

public abstract class NativeAdsAdapter<T extends ViewHolder> extends Adapter<T> {
    private boolean isStopped;
    private final Lazy<PremiumService> premiumService;

    public void destroy() {
    }

    public abstract void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry);

    public void onCreate() {
    }

    public abstract void onPause();

    public abstract void onResume(MfpFragment mfpFragment);

    public abstract void refreshItems(List<NewsFeedItem> list);

    public abstract void resetDailySummary();

    public abstract void setAdapterOperationListener(AdapterOperationListener adapterOperationListener);

    public abstract void setNewsFeedItemActionListener(NewsFeedItemActionListener newsFeedItemActionListener);

    public NativeAdsAdapter(Lazy<PremiumService> lazy) {
        this.premiumService = lazy;
    }

    public void setStopped(boolean z) {
        if (!((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AdFree)) {
            this.isStopped = z;
            notifyDataSetChanged();
        }
    }

    public boolean isStopped() {
        return this.isStopped;
    }
}
