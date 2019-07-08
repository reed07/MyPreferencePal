package com.myfitnesspal.feature.home.ui.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.ui.view.StyledTextView;
import com.myfitnesspal.shared.util.BasicDfpPublisherAdRequest.Builder;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class DfpNativeViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    private static final int AD_INTERVAL_TYPE_A = 2;
    private static final int AD_INTERVAL_TYPE_B = 12;
    @BindView(2131363096)
    CardView adContainer;
    private AdListener adListener = new AdListener() {
        public void onAdImpression() {
            super.onAdImpression();
            ((NewsFeedAnalyticsHelper) DfpNativeViewHolder.this.newsFeedAnalyticsHelper.get()).reportNativeAdViewed(DfpNativeViewHolder.this.getAdState(), DfpNativeViewHolder.this.adSource, DfpNativeViewHolder.this.getTimeToResult());
        }

        public void onAdClicked() {
            super.onAdClicked();
            ((NewsFeedAnalyticsHelper) DfpNativeViewHolder.this.newsFeedAnalyticsHelper.get()).reportNativeAdClicked(DfpNativeViewHolder.this.getAdState(), DfpNativeViewHolder.this.adSource);
        }

        public void onAdFailedToLoad(int i) {
            super.onAdFailedToLoad(i);
            ((NewsFeedAnalyticsHelper) DfpNativeViewHolder.this.newsFeedAnalyticsHelper.get()).reportNativeAdFailed(DfpNativeViewHolder.this.getAdState(), DfpNativeViewHolder.this.getContext().getString(R.string.ad_source_dfp), DfpNativeViewHolder.this.getTimeToResult());
        }
    };
    /* access modifiers changed from: private */
    public String adSource;
    private final Lazy<AdUnitService> adUnitService;
    private final Lazy<AdsSettings> adsSettings;
    private final Lazy<ConfigService> configService;
    @BindView(2131363097)
    RelativeLayout customAdView;
    private NativeCustomTemplateAd customTemplateAd;
    private boolean isCountryUS;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<LocationService> locationService;
    /* access modifiers changed from: private */
    public final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private long startLoadTimestamp;
    private UnifiedNativeAd unifiedAd;
    @BindView(2131363099)
    UnifiedNativeAdView unifiedAdView;

    private String getTargetingForPosition(int i) {
        return i == 2 ? Keywords.POSITION_TOP : "bottom";
    }

    public DfpNativeViewHolder(ViewGroup viewGroup, Lazy<AdsSettings> lazy, Lazy<ConfigService> lazy2, Lazy<LocalSettingsService> lazy3, Lazy<LocationService> lazy4, Lazy<NewsFeedAnalyticsHelper> lazy5, Lazy<AdUnitService> lazy6, boolean z) {
        super(R.layout.dfp_native_ad_card_view, viewGroup);
        this.adsSettings = lazy;
        this.configService = lazy2;
        this.localSettingsService = lazy3;
        this.locationService = lazy4;
        this.newsFeedAnalyticsHelper = lazy5;
        this.adUnitService = lazy6;
        this.isCountryUS = z;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        if (i == 2 || i == 12) {
            requestAd(i);
        }
    }

    private void requestAd(int i) {
        this.startLoadTimestamp = System.currentTimeMillis();
        String targetingForPosition = getTargetingForPosition(i);
        String gaid = ((LocalSettingsService) this.localSettingsService.get()).getGAID();
        Builder builder = new Builder(this.adsSettings, this.configService, this.localSettingsService, this.locationService, this.isCountryUS);
        PublisherAdRequest request = builder.setCustomTargeting(new MapUtil.Builder().put(Keywords.POSITION, targetingForPosition).put("did", gaid).build()).getRequest();
        String adUnitIdByPosition = getAdUnitIdByPosition(i);
        if (adUnitIdByPosition != null) {
            new AdLoader.Builder(this.context, adUnitIdByPosition).forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
                public final void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                    DfpNativeViewHolder.this.showUnifiedNativeAd(unifiedNativeAd, DfpNativeViewHolder.this.unifiedAdView);
                }
            }).forCustomTemplateAd(this.context.getString(R.string.nativeDfpTemplateAdCampaingsId), new OnCustomTemplateAdLoadedListener() {
                public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd) {
                    DfpNativeViewHolder.this.showCustomizedTemplateAd(nativeCustomTemplateAd);
                }
            }, new OnCustomClickListener() {
                public final void onCustomClick(NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
                    DfpNativeViewHolder.this.onCustomAdClick(nativeCustomTemplateAd);
                }
            }).withAdListener(this.adListener).build().loadAd(request);
        }
        ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportNativeAdRequested(getAdState(), this.adSource, getTargetingForPosition(i));
    }

    private String getAdUnitIdByPosition(int i) {
        if (i == 2) {
            return ((AdUnitService) this.adUnitService.get()).getNewsFeedDfpAdUnitValue(true).getDfpAdUnitId();
        }
        if (i == 12) {
            return ((AdUnitService) this.adUnitService.get()).getNewsFeedDfpAdUnitValue(false).getDfpAdUnitId();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void showUnifiedNativeAd(@NonNull UnifiedNativeAd unifiedNativeAd, UnifiedNativeAdView unifiedNativeAdView) {
        prepareLoadedAd();
        getMediationSourceAdapter(unifiedNativeAd.getMediationAdapterClassName());
        ViewUtils.setVisible(true, this.unifiedAdView);
        UnifiedNativeAd unifiedNativeAd2 = this.unifiedAd;
        if (unifiedNativeAd2 != null) {
            unifiedNativeAd2.destroy();
        }
        this.unifiedAd = unifiedNativeAd;
        MediaView mediaView = (MediaView) unifiedNativeAdView.findViewById(R.id.thumbnail_media_view);
        ((TextView) unifiedNativeAdView.findViewById(R.id.sponsored_by)).setText(Strings.isEmpty(unifiedNativeAd.getAdvertiser()) ? R.string.ad_promoted : R.string.ad_promoted_by);
        mediaView.setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
            public void onChildViewRemoved(View view, View view2) {
            }

            public void onChildViewAdded(View view, View view2) {
                if (view2 instanceof ImageView) {
                    ((ImageView) view2).setAdjustViewBounds(true);
                }
            }
        });
        unifiedNativeAdView.setMediaView(mediaView);
        StyledTextView styledTextView = (StyledTextView) unifiedNativeAdView.findViewById(R.id.title);
        styledTextView.setText(this.unifiedAd.getHeadline());
        unifiedNativeAdView.setHeadlineView(styledTextView);
        StyledTextView styledTextView2 = (StyledTextView) unifiedNativeAdView.findViewById(R.id.description);
        styledTextView2.setText(this.unifiedAd.getBody());
        unifiedNativeAdView.setBodyView(styledTextView2);
        StyledTextView styledTextView3 = (StyledTextView) unifiedNativeAdView.findViewById(R.id.advertiser);
        styledTextView3.setText(this.unifiedAd.getAdvertiser());
        unifiedNativeAdView.setAdvertiserView(styledTextView3);
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        if (unifiedNativeAd.getBody() == null) {
            unifiedNativeAdView.getBodyView().setVisibility(4);
        } else {
            unifiedNativeAdView.getBodyView().setVisibility(0);
            ((TextView) unifiedNativeAdView.getBodyView()).setText(unifiedNativeAd.getBody());
        }
        if (unifiedNativeAd.getAdvertiser() == null) {
            unifiedNativeAdView.getAdvertiserView().setVisibility(4);
        } else {
            ((TextView) unifiedNativeAdView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
            unifiedNativeAdView.getAdvertiserView().setVisibility(0);
        }
        unifiedNativeAdView.setNativeAd(unifiedNativeAd);
    }

    /* access modifiers changed from: private */
    public void showCustomizedTemplateAd(@NonNull NativeCustomTemplateAd nativeCustomTemplateAd) {
        prepareLoadedAd();
        getMediationSourceAdapter(getContext().getString(R.string.ad_source_dfp));
        ViewUtils.setVisible(true, this.customAdView);
        NativeCustomTemplateAd nativeCustomTemplateAd2 = this.customTemplateAd;
        if (nativeCustomTemplateAd2 != null) {
            nativeCustomTemplateAd2.destroy();
        }
        this.customTemplateAd = nativeCustomTemplateAd;
        this.customTemplateAd.recordImpression();
        Context context = getContext();
        ((StyledTextView) this.customAdView.findViewById(R.id.title)).setText(this.customTemplateAd.getText(context.getString(R.string.native_template_ad_title)));
        ((StyledTextView) this.customAdView.findViewById(R.id.description)).setText(this.customTemplateAd.getText(context.getString(R.string.native_template_ad_description)));
        ((StyledTextView) this.customAdView.findViewById(R.id.advertiser)).setText(this.customTemplateAd.getText(context.getString(R.string.native_template_ad_advertiser)));
        ImageView imageView = (ImageView) this.customAdView.findViewById(R.id.thumbnail);
        FrameLayout frameLayout = (FrameLayout) this.customAdView.findViewById(R.id.media_frame);
        if (nativeCustomTemplateAd.getVideoController().hasVideoContent()) {
            ViewUtils.setVisible(true, frameLayout);
            ViewUtils.setVisible(false, imageView);
            try {
                frameLayout.removeAllViews();
            } catch (Exception unused) {
            }
            frameLayout.addView(nativeCustomTemplateAd.getVideoMediaView());
        } else {
            ViewUtils.setVisible(false, frameLayout);
            ViewUtils.setVisible(true, imageView);
            Image image = this.customTemplateAd.getImage(context.getString(R.string.native_template_ad_main_image));
            if (image == null) {
                image = this.customTemplateAd.getImage(context.getString(R.string.native_template_ad_tumbnail));
            }
            if (!(image == null || image.getDrawable() == null)) {
                imageView.setImageDrawable(image.getDrawable());
            }
        }
        this.adContainer.setOnClickListener(new OnClickListener(context) {
            private final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                DfpNativeViewHolder.this.customTemplateAd.performClick(this.f$1.getString(R.string.native_template_ad_main_image));
            }
        });
    }

    /* access modifiers changed from: private */
    public void onCustomAdClick(NativeCustomTemplateAd nativeCustomTemplateAd) {
        if (Strings.equals(getContext().getString(R.string.nativeDfpTemplateAdCampaingsId), nativeCustomTemplateAd.getCustomTemplateId())) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Strings.toString(nativeCustomTemplateAd.getText(this.context.getString(R.string.native_template_ad_click_through_url)))));
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                getContext().startActivity(intent);
                return;
            }
            return;
        }
        FullScreenWebViewIntentExtras fullScreenWebViewIntentExtras = new FullScreenWebViewIntentExtras();
        fullScreenWebViewIntentExtras.setTitle(Strings.toString(nativeCustomTemplateAd.getText(this.context.getString(R.string.native_template_ad_title))));
        fullScreenWebViewIntentExtras.setUrl(Strings.toString(nativeCustomTemplateAd.getText(this.context.getString(R.string.native_template_ad_click_through_url))));
        getContext().startActivity(FullScreenWebView.newStartIntent(getContext(), fullScreenWebViewIntentExtras));
    }

    private void prepareLoadedAd() {
        this.adContainer.getLayoutParams().height = -2;
        ViewUtils.setVisible(false, this.customAdView);
        ViewUtils.setVisible(false, this.unifiedAdView);
    }

    private void getMediationSourceAdapter(CharSequence charSequence) {
        this.adSource = !TextUtils.isEmpty(charSequence) ? String.valueOf(charSequence) : getContext().getString(R.string.ad_source_dfp);
    }

    /* access modifiers changed from: private */
    public long getTimeToResult() {
        return (long) Math.round((float) ((System.currentTimeMillis() - this.startLoadTimestamp) / 1000));
    }

    /* access modifiers changed from: private */
    public NewsFeedNativeAdState getAdState() {
        return NewsFeedNativeAdState.STATIC;
    }
}
