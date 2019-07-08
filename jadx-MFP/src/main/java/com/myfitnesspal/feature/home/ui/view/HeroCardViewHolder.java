package com.myfitnesspal.feature.home.ui.view;

import android.content.ActivityNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.HeroCardLayoutBase;
import com.myfitnesspal.feature.home.model.HeroCardSingleButtonAndImageLayout;
import com.myfitnesspal.feature.home.model.HeroCardTwoButtonLayout;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedHeroCardEntry;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class HeroCardViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    @BindView(2131362146)
    View closeBtn;
    @BindView(2131362741)
    TextView contentTextView;
    @BindView(2131362245)
    ViewGroup ctaContainer;
    @BindView(2131362742)
    MfpImageView imageView;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;
    /* access modifiers changed from: private */
    public final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private final NewsFeedItemActionListener newsFeedItemActionListener;
    @BindView(2131362743)
    TextView titleTextView;

    public HeroCardViewHolder(ViewGroup viewGroup, NavigationHelper navigationHelper2, NewsFeedItemActionListener newsFeedItemActionListener2, Lazy<NewsFeedAnalyticsHelper> lazy) {
        super(R.layout.hero_card, viewGroup);
        this.navigationHelper = navigationHelper2;
        this.newsFeedItemActionListener = newsFeedItemActionListener2;
        this.newsFeedAnalyticsHelper = lazy;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) newsFeedItem;
        MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
        if (entryData instanceof MfpNewsFeedHeroCardEntry) {
            MfpNewsFeedHeroCardEntry mfpNewsFeedHeroCardEntry = (MfpNewsFeedHeroCardEntry) entryData;
            HeroCardLayoutBase cardLayout = mfpNewsFeedHeroCardEntry.getCardLayout();
            String description = cardLayout.getDescription();
            final String heroCardName = mfpNewsFeedHeroCardEntry.getHeroCardName();
            this.contentTextView.setText(description);
            this.titleTextView.setText(cardLayout.getTitle());
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportHeroCardDisplayed(heroCardName);
            ViewUtils.setVisible(cardLayout.hasImage(), this.imageView);
            if (cardLayout instanceof HeroCardSingleButtonAndImageLayout) {
                this.imageView.usePlaceholder(false).setUrl(((HeroCardSingleButtonAndImageLayout) cardLayout).getAssetUrl());
            }
            setupHeroCardButtons(cardLayout, mfpNewsFeedActivityEntry, heroCardName);
            ViewUtils.setVisible(mfpNewsFeedHeroCardEntry.isDismissibleByUser(), this.closeBtn);
            ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.news_feed_icon_button_padding), this.closeBtn);
            this.closeBtn.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((NewsFeedAnalyticsHelper) HeroCardViewHolder.this.newsFeedAnalyticsHelper.get()).reportHeroCardClosed(heroCardName);
                    HeroCardViewHolder.this.onCardCloseClick(mfpNewsFeedActivityEntry);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        this.newsFeedItemActionListener.onCardCloseClick(mfpNewsFeedActivityEntry);
    }

    private void setupHeroCardButtons(HeroCardLayoutBase heroCardLayoutBase, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str) {
        this.ctaContainer.removeAllViews();
        LayoutInflater from = LayoutInflater.from(this.context);
        if (heroCardLayoutBase instanceof HeroCardSingleButtonAndImageLayout) {
            HeroCardSingleButtonAndImageLayout heroCardSingleButtonAndImageLayout = (HeroCardSingleButtonAndImageLayout) heroCardLayoutBase;
            setButtonProperties((TextView) ViewUtils.findById(from.inflate(R.layout.single_button, this.ctaContainer), R.id.button), mfpNewsFeedActivityEntry, heroCardSingleButtonAndImageLayout.getButtonText(), heroCardSingleButtonAndImageLayout.getButtonDeeplink(), str);
        } else if (heroCardLayoutBase instanceof HeroCardTwoButtonLayout) {
            HeroCardTwoButtonLayout heroCardTwoButtonLayout = (HeroCardTwoButtonLayout) heroCardLayoutBase;
            View inflate = from.inflate(R.layout.double_buttons, this.ctaContainer);
            TextView textView = (TextView) ViewUtils.findById(inflate, R.id.first_button);
            TextView textView2 = (TextView) ViewUtils.findById(inflate, R.id.second_button);
            setButtonProperties(textView, mfpNewsFeedActivityEntry, heroCardTwoButtonLayout.getButtonText1(), heroCardTwoButtonLayout.getButtonDeeplink1(), str);
            setButtonProperties(textView2, mfpNewsFeedActivityEntry, heroCardTwoButtonLayout.getButtonText2(), heroCardTwoButtonLayout.getButtonDeeplink2(), str);
        }
    }

    private void setButtonProperties(TextView textView, final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str, final String str2, final String str3) {
        textView.setText(str);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((NewsFeedAnalyticsHelper) HeroCardViewHolder.this.newsFeedAnalyticsHelper.get()).reportHeroCardTapped(str3, -1);
                HeroCardViewHolder.this.onCardCloseClick(mfpNewsFeedActivityEntry);
                try {
                    HeroCardViewHolder.this.navigationHelper.withIntent(SharedIntents.newUriIntent(str2)).startActivity();
                } catch (ActivityNotFoundException e) {
                    Ln.e(e);
                }
            }
        });
    }
}
