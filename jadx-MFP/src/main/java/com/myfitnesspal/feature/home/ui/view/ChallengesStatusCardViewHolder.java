package com.myfitnesspal.feature.home.ui.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.MfpNewsFeedLinkDesc;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.util.InternalURLSpan;
import com.myfitnesspal.feature.home.util.NewsFeedCardUtils;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedAssetDesc;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedChallengeLink;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedChallengesStatusCardEntry;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;

public class ChallengesStatusCardViewHolder extends RecyclerViewHolder<NewsFeedItem> {
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

    public ChallengesStatusCardViewHolder(ViewGroup viewGroup, NavigationHelper navigationHelper2, NewsFeedItemActionListener newsFeedItemActionListener2, Lazy<NewsFeedAnalyticsHelper> lazy) {
        super(R.layout.hero_card, viewGroup);
        this.navigationHelper = navigationHelper2;
        this.newsFeedItemActionListener = newsFeedItemActionListener2;
        this.newsFeedAnalyticsHelper = lazy;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) newsFeedItem;
        MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
        if (entryData instanceof MfpNewsFeedChallengesStatusCardEntry) {
            MfpNewsFeedChallengesStatusCardEntry mfpNewsFeedChallengesStatusCardEntry = (MfpNewsFeedChallengesStatusCardEntry) entryData;
            String description = mfpNewsFeedChallengesStatusCardEntry.getDescription();
            final String cardType = mfpNewsFeedChallengesStatusCardEntry.getCardType();
            addLinksToChallengesCardOrSetStatusText(this.context, description, this.contentTextView, mfpNewsFeedChallengesStatusCardEntry, this.navigationHelper);
            this.titleTextView.setText(mfpNewsFeedChallengesStatusCardEntry.getTitle());
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportHeroCardDisplayed(cardType);
            this.ctaContainer.removeAllViews();
            setupCardButtons(mfpNewsFeedChallengesStatusCardEntry, mfpNewsFeedActivityEntry);
            ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.news_feed_icon_button_padding), this.closeBtn);
            MfpNewsFeedAssetDesc asset = mfpNewsFeedChallengesStatusCardEntry.getAsset();
            boolean isRoundAsset = asset.isRoundAsset();
            if (isRoundAsset) {
                LayoutParams layoutParams = this.imageView.getLayoutParams();
                layoutParams.width = mfpNewsFeedChallengesStatusCardEntry.getAsset().getWidth();
                layoutParams.height = mfpNewsFeedChallengesStatusCardEntry.getAsset().getHeight();
                this.imageView.setLayoutParams(layoutParams);
            }
            this.imageView.usePlaceholder(false).setTransformCircular(isRoundAsset).setUrl(asset.getUrl());
            ViewUtils.setVisible(this.imageView);
            ViewUtils.setVisible(this.closeBtn);
            this.closeBtn.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((NewsFeedAnalyticsHelper) ChallengesStatusCardViewHolder.this.newsFeedAnalyticsHelper.get()).reportHeroCardClosed(cardType);
                    ChallengesStatusCardViewHolder.this.onCardCloseClick(mfpNewsFeedActivityEntry);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        this.newsFeedItemActionListener.onCardCloseClick(mfpNewsFeedActivityEntry);
    }

    private void setupCardButtons(MfpNewsFeedChallengesStatusCardEntry mfpNewsFeedChallengesStatusCardEntry, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        List buttons = mfpNewsFeedChallengesStatusCardEntry.getButtons();
        LayoutInflater from = LayoutInflater.from(this.context);
        for (int i = 0; i < CollectionUtils.size((Collection<?>) buttons); i++) {
            MfpNewsFeedLinkDesc mfpNewsFeedLinkDesc = (MfpNewsFeedLinkDesc) buttons.get(i);
            if (mfpNewsFeedLinkDesc != null) {
                final String deepLink = mfpNewsFeedLinkDesc.getDeepLink();
                if (!Strings.isEmpty(mfpNewsFeedLinkDesc.getText()) && !Strings.isEmpty(deepLink)) {
                    TextView textView = (TextView) from.inflate(R.layout.single_button, this.ctaContainer, false);
                    this.ctaContainer.addView(textView);
                    textView.setText(mfpNewsFeedLinkDesc.getText());
                    final MfpNewsFeedChallengesStatusCardEntry mfpNewsFeedChallengesStatusCardEntry2 = mfpNewsFeedChallengesStatusCardEntry;
                    final int i2 = i;
                    final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry2 = mfpNewsFeedActivityEntry;
                    AnonymousClass2 r3 = new OnClickListener() {
                        public void onClick(View view) {
                            ((NewsFeedAnalyticsHelper) ChallengesStatusCardViewHolder.this.newsFeedAnalyticsHelper.get()).reportHeroCardTapped(mfpNewsFeedChallengesStatusCardEntry2.getCardType(), i2);
                            ChallengesStatusCardViewHolder.this.onCardCloseClick(mfpNewsFeedActivityEntry2);
                            try {
                                ChallengesStatusCardViewHolder.this.navigationHelper.withIntent(SharedIntents.newUriIntent(deepLink)).startActivity();
                            } catch (ActivityNotFoundException e) {
                                Ln.e(e);
                            }
                        }
                    };
                    textView.setOnClickListener(r3);
                }
            }
        }
    }

    private void addLinksToChallengesCardOrSetStatusText(Context context, String str, TextView textView, MfpNewsFeedChallengesStatusCardEntry mfpNewsFeedChallengesStatusCardEntry, final NavigationHelper navigationHelper2) {
        List<MfpNewsFeedChallengeLink> links = mfpNewsFeedChallengesStatusCardEntry.getLinks();
        if (CollectionUtils.notEmpty((Collection<?>) links)) {
            SpannableString spannableString = new SpannableString(str);
            for (final MfpNewsFeedChallengeLink mfpNewsFeedChallengeLink : links) {
                if (mfpNewsFeedChallengeLink != null && Strings.notEmpty(mfpNewsFeedChallengeLink.getText())) {
                    String text = mfpNewsFeedChallengeLink.getText();
                    int indexOf = str.indexOf(text);
                    if (indexOf >= 0) {
                        spannableString.setSpan(new InternalURLSpan(new OnClickListener() {
                            public void onClick(View view) {
                                NewsFeedCardUtils.handleDeepLink(navigationHelper2, mfpNewsFeedChallengeLink);
                            }
                        }, context.getResources().getColor(R.color.hyperlink_fg_selector)), indexOf, Strings.length(text) + indexOf, 33);
                    }
                }
            }
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setOnClickListener(null);
            return;
        }
        textView.setText(str);
    }
}
