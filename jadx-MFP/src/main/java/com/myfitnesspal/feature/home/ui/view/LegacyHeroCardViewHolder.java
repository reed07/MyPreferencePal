package com.myfitnesspal.feature.home.ui.view;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.MfpNewsFeedLinkDesc;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.shared.model.v2.LegacyMfpNewsFeedHeroCardEntry;
import com.myfitnesspal.shared.model.v2.LegacyMfpNewsFeedHeroCardEntry.CardTypes;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
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

public class LegacyHeroCardViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    private static final String EMAIL_SETTINGS_DEEPLINK_INDENTIFIER = "email_settings";
    private static final String RESEND_EMAIL_VERIFICATION_DEEPLINK_INDENTIFIER = "resend_email_verification";
    private static String resentEmailVerificationForUserName;
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
    private final String userName;

    public LegacyHeroCardViewHolder(ViewGroup viewGroup, NavigationHelper navigationHelper2, NewsFeedItemActionListener newsFeedItemActionListener2, Lazy<NewsFeedAnalyticsHelper> lazy, String str) {
        super(R.layout.hero_card, viewGroup);
        this.navigationHelper = navigationHelper2;
        this.newsFeedItemActionListener = newsFeedItemActionListener2;
        this.newsFeedAnalyticsHelper = lazy;
        this.userName = str;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) newsFeedItem;
        MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
        if (entryData instanceof LegacyMfpNewsFeedHeroCardEntry) {
            LegacyMfpNewsFeedHeroCardEntry legacyMfpNewsFeedHeroCardEntry = (LegacyMfpNewsFeedHeroCardEntry) entryData;
            String description = legacyMfpNewsFeedHeroCardEntry.getDescription();
            final String cardType = legacyMfpNewsFeedHeroCardEntry.getCardType();
            this.contentTextView.setText(description);
            this.titleTextView.setText(legacyMfpNewsFeedHeroCardEntry.getTitle());
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportHeroCardDisplayed(cardType);
            this.ctaContainer.removeAllViews();
            setupHeroCardButtons(legacyMfpNewsFeedHeroCardEntry, mfpNewsFeedActivityEntry);
            ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.news_feed_icon_button_padding), this.closeBtn);
            this.imageView.usePlaceholder(false).setTransformCircular(legacyMfpNewsFeedHeroCardEntry.getAsset().isRoundAsset()).setUrl(legacyMfpNewsFeedHeroCardEntry.getAsset().getUrl());
            ViewUtils.setVisible(!isCardEmailVerification(legacyMfpNewsFeedHeroCardEntry), this.imageView);
            ViewUtils.setVisible(!isCardEmailVerification(legacyMfpNewsFeedHeroCardEntry), this.closeBtn);
            this.closeBtn.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((NewsFeedAnalyticsHelper) LegacyHeroCardViewHolder.this.newsFeedAnalyticsHelper.get()).reportHeroCardClosed(cardType);
                    LegacyHeroCardViewHolder.this.onCardCloseClick(mfpNewsFeedActivityEntry);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        this.newsFeedItemActionListener.onCardCloseClick(mfpNewsFeedActivityEntry);
    }

    private void setupHeroCardButtons(LegacyMfpNewsFeedHeroCardEntry legacyMfpNewsFeedHeroCardEntry, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        List buttons = legacyMfpNewsFeedHeroCardEntry.getButtons();
        LayoutInflater from = LayoutInflater.from(this.context);
        for (int i = 0; i < CollectionUtils.size((Collection<?>) buttons); i++) {
            MfpNewsFeedLinkDesc mfpNewsFeedLinkDesc = (MfpNewsFeedLinkDesc) buttons.get(i);
            if (mfpNewsFeedLinkDesc != null) {
                final String deepLink = mfpNewsFeedLinkDesc.getDeepLink();
                if (!Strings.isEmpty(mfpNewsFeedLinkDesc.getText()) && !Strings.isEmpty(deepLink)) {
                    TextView textView = (TextView) from.inflate(isCardEmailVerification(legacyMfpNewsFeedHeroCardEntry) ? R.layout.legacy_hero_card_cta_weighted : R.layout.legacy_hero_card_cta, this.ctaContainer, false);
                    this.ctaContainer.addView(textView);
                    textView.setText(mfpNewsFeedLinkDesc.getText());
                    final boolean contains = deepLink.contains(RESEND_EMAIL_VERIFICATION_DEEPLINK_INDENTIFIER);
                    if (contains && !Strings.equals(this.userName, resentEmailVerificationForUserName)) {
                        verificationEmailHasBeenResent(textView);
                    }
                    if (deepLink.contains(EMAIL_SETTINGS_DEEPLINK_INDENTIFIER)) {
                        textView.setLayoutParams(new LayoutParams(-1, -2, 1.0f));
                    }
                    final LegacyMfpNewsFeedHeroCardEntry legacyMfpNewsFeedHeroCardEntry2 = legacyMfpNewsFeedHeroCardEntry;
                    final int i2 = i;
                    final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry2 = mfpNewsFeedActivityEntry;
                    final TextView textView2 = textView;
                    AnonymousClass2 r0 = new OnClickListener() {
                        public void onClick(View view) {
                            ((NewsFeedAnalyticsHelper) LegacyHeroCardViewHolder.this.newsFeedAnalyticsHelper.get()).reportHeroCardTapped(legacyMfpNewsFeedHeroCardEntry2.getCardType(), i2);
                            LegacyHeroCardViewHolder.this.onCardCloseClick(mfpNewsFeedActivityEntry2);
                            String str = deepLink;
                            if (contains) {
                                textView2.setEnabled(false);
                            } else if (LegacyHeroCardViewHolder.this.isCardEmailVerification(legacyMfpNewsFeedHeroCardEntry2)) {
                                str = Uri.parse(str).buildUpon().appendQueryParameter("edit", AppEventsConstants.EVENT_PARAM_VALUE_YES).toString();
                            }
                            try {
                                LegacyHeroCardViewHolder.this.navigationHelper.withIntent(SharedIntents.newUriIntent(str)).startActivity();
                            } catch (ActivityNotFoundException e) {
                                Ln.e(e);
                            }
                        }
                    };
                    textView.setOnClickListener(r0);
                }
            }
        }
    }

    private void verificationEmailHasBeenResent(TextView textView) {
        resentEmailVerificationForUserName = this.userName;
        textView.setEnabled(false);
        textView.setText(R.string.sentBtn);
        textView.setBackgroundResource(R.drawable.grey_send_btn);
    }

    /* access modifiers changed from: private */
    public boolean isCardEmailVerification(LegacyMfpNewsFeedHeroCardEntry legacyMfpNewsFeedHeroCardEntry) {
        return Strings.equals(legacyMfpNewsFeedHeroCardEntry.getCardType(), CardTypes.EMAIL_VERIFICATION);
    }
}
