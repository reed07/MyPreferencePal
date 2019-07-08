package com.myfitnesspal.feature.premium.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.premium.model.MfpUpsellConfig;
import com.myfitnesspal.feature.premium.model.MfpUpsellFeature;
import com.myfitnesspal.feature.premium.model.MfpUpsellGroup;
import com.myfitnesspal.feature.premium.model.MfpUpsellHero;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.UpsellService;
import com.myfitnesspal.feature.premium.util.PremiumSupportedFeatures;
import com.myfitnesspal.feature.premium.util.ProductUtils;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellSkus;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.WeeklyDigestFeature;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.WeeklyDigestUpsell;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PremiumUpsellNativeFragment extends PremiumUpsellFragment {
    protected static final String CONNECTION_FAILED_DIALOG_TAG = "PremiumUpsellFragment.NoConnectionDialog";
    protected static final int DEFAULT_HERO_IMAGE = 2131231181;
    protected static final String EXTRA_DISPLAY_MODE = "PremiumUpsellFragment.DISPLAY_MODE";
    private static final String EXTRA_URL = "url";
    protected static final Map<String, Integer> FEATURE_TO_DEFAULT_ICON_MAP = new HashMap();
    protected static final int MAX_BUY_BUTTONS = 3;
    protected static final String SIGN_UP_PROMOTED_FEATURE = "registration";
    protected static final Drawable TRANSPARENT = new ColorDrawable(Color.argb(0, 255, 255, 255));
    protected static final String USE_DEFAULT_IMAGE = "USE_DEFAULT_IMAGE";
    private static final String WEEKLY_DIGEST_FEATURE_NAME = "premium-weekly-digest";
    private static final String WEEKLY_DIGEST_URL = "http://www.myfitnesspal.com/reports/weekly-digest";
    @Nullable
    @BindView(2131364094)
    ViewGroup buttonContainer;
    @BindView(2131364101)
    ViewGroup groupContainer;
    @Nullable
    @BindView(2131364104)
    MfpImageView headerImage;
    @Nullable
    @BindView(2131364105)
    TextView headerSubtext;
    @Nullable
    @BindView(2131364103)
    TextView headerText;
    @Nullable
    @BindView(2131363292)
    View headerView;
    private LayoutInflater inflater;
    protected final DialogPositiveListener onConnectionFailedRetryClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            PremiumUpsellNativeFragment premiumUpsellNativeFragment = PremiumUpsellNativeFragment.this;
            premiumUpsellNativeFragment.upsellConfig = null;
            premiumUpsellNativeFragment.products = null;
            premiumUpsellNativeFragment.rendered = false;
            PremiumUpsellNativeFragment.this.ensureLoaded();
        }
    };
    protected OnClickListener onFeatureClickedListener = new OnClickListener() {
        public final void onClick(View view) {
            PremiumUpsellNativeFragment.lambda$new$1(PremiumUpsellNativeFragment.this, view);
        }
    };
    /* access modifiers changed from: private */
    public boolean rendered = false;

    static {
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.NutrientDashboard.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_nutrient_dashboard));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.TrackMacrosByGram.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_track_macros));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.MealGoals.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_meal_goals));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.FoodLists.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_food_lists));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.AssignExerciseCalories.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_assign_exercise));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.FoodTimestamps.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_food_timestamps));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.CustomDailyGoals.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_custom_daily_goals));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.QuickAddMacros.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_quick_add));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.MealMacros.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_meal_macros));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.FileExport.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_file_export));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.WeeklyDigest.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_weekly_digest));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.PrioritySupport.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_priority_support));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.AdFree.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_ad_free));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.Graphs.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_graphs));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.Content.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_content));
        FEATURE_TO_DEFAULT_ICON_MAP.put(PremiumFeature.VerifiedFoods.getFeatureId(), Integer.valueOf(R.drawable.ic_premium_verified_foods));
    }

    public static PremiumUpsellNativeFragment newInstance(UpsellDisplayMode upsellDisplayMode) {
        return newInstance(null, upsellDisplayMode);
    }

    public static PremiumUpsellNativeFragment newInstance(String str, UpsellDisplayMode upsellDisplayMode) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.EXTRA_PROMOTED_FEATURE, str);
        bundle.putSerializable(EXTRA_DISPLAY_MODE, upsellDisplayMode);
        PremiumUpsellNativeFragment premiumUpsellNativeFragment = new PremiumUpsellNativeFragment();
        premiumUpsellNativeFragment.setArguments(bundle);
        return premiumUpsellNativeFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.displayMode = (UpsellDisplayMode) BundleUtils.getSerializable(getArguments(), EXTRA_DISPLAY_MODE, null);
        if (this.displayMode == UpsellDisplayMode.SignUp) {
            ((LocalSettingsService) this.localSettings.get()).setPremiumAdDisplayed(true);
        }
        if (Strings.isEmpty(this.promotedFeature) && this.displayMode == UpsellDisplayMode.SignUp) {
            this.promotedFeature = SIGN_UP_PROMOTED_FEATURE;
        }
        if (bundle != null) {
            this.analyticsReported = BundleUtils.getBoolean(bundle, "PremiumUpsellFragment.ANALYTICS_REPORTED", false);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.inflater = layoutInflater;
        return layoutInflater.inflate(R.layout.fragment_premium_upsell, viewGroup, false);
    }

    public void onStart() {
        super.onStart();
        ensureLoaded();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        configureActionBarForDisplayMode();
    }

    private void configureViewsForDisplayMode(MfpUpsellConfig mfpUpsellConfig) {
        ViewUtils.setVisible(true, this.headerSubtext);
        if (this.displayMode != UpsellDisplayMode.FeatureScreen) {
            MfpUpsellHero hero = mfpUpsellConfig.getHero();
            ViewUtils.setVisible(true, this.headerView);
            this.headerView.setBackgroundColor(hero.getBackgroundColor());
            loadImage(this.headerImage, hero.getImageUrl(), R.drawable.ic_crown_on_blue);
            ViewUtils.setVisible(!ConfigUtils.showUpdatedUpsellFeatureListOrder(getConfigService()), this.headerSubtext);
            if (this.displayMode == UpsellDisplayMode.Normal) {
                bindHeaderTextView(this.headerText, hero.getTitleText(), hero.getTitleTextColor(), hero.getTitleTextSize());
                bindHeaderTextView(this.headerSubtext, hero.getSubtitleText(), hero.getSubtitleTextColor(), hero.getSubtitleTextSize());
                return;
            }
            bindHeaderTextView(this.headerText, hero.getSignupTitleText(), hero.getSignupTitleTextColor(), hero.getSignupTitleTextSize());
            bindHeaderTextView(this.headerSubtext, hero.getSignupSubtitleText(), hero.getSignupSubtitleTextColor(), hero.getSignupSubtitleTextSize());
        }
    }

    /* access modifiers changed from: protected */
    public View createGroupView(MfpUpsellGroup mfpUpsellGroup, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(this.displayMode == UpsellDisplayMode.FeatureScreen ? R.layout.premium_featurescreen_group : R.layout.premium_upsell_group, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) ViewUtils.findById(inflate, R.id.upsellFeatureContainer);
        if (this.displayMode != UpsellDisplayMode.FeatureScreen) {
            TextView textView = (TextView) ViewUtils.findById(inflate, R.id.upsellGroupTitle);
            textView.setText(mfpUpsellGroup.getHeadlineText());
            textView.setTextColor(mfpUpsellGroup.getHeadlineColor());
            setTextSize(textView, mfpUpsellGroup.getHeadlineTextSize());
        }
        int addFeaturesToLayout = addFeaturesToLayout(mfpUpsellGroup.getFeatures(), viewGroup2);
        showTouchAnimation(viewGroup2);
        if (addFeaturesToLayout > 0) {
            return inflate;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void ensureLoaded() {
        if (this.rendered) {
            return;
        }
        if (loaded()) {
            render();
        } else {
            load();
        }
    }

    /* access modifiers changed from: protected */
    public void load() {
        ViewUtils.setVisible(false, getView());
        setBusy(true);
        ((UpsellService) this.upsellService.get()).getUpsellConfig(new Function1() {
            public final void execute(Object obj) {
                PremiumUpsellNativeFragment.lambda$load$0(PremiumUpsellNativeFragment.this, (MfpUpsellConfig) obj);
            }
        }, null);
        if (this.displayMode != UpsellDisplayMode.FeatureScreen) {
            loadProducts(((ConfigService) this.configService.get()).getABTestProperties(UpsellSkus.NAME_STANDARD));
            return;
        }
        loadProducts(new HashMap());
        this.displayPrices = new HashMap();
    }

    public static /* synthetic */ void lambda$load$0(PremiumUpsellNativeFragment premiumUpsellNativeFragment, MfpUpsellConfig mfpUpsellConfig) throws RuntimeException {
        premiumUpsellNativeFragment.upsellConfig = mfpUpsellConfig;
        premiumUpsellNativeFragment.render();
    }

    /* access modifiers changed from: protected */
    public void render() {
        if (isEnabled() && this.upsellConfig != null && this.products != null && this.displayPrices != null) {
            setBusy(false);
            addGroupViewsToLayout(this.upsellConfig.getGroups(), this.groupContainer);
            configureViewsForDisplayMode(this.upsellConfig);
            promoteFeatureToTop(this.promotedFeature);
            ViewUtils.setVisible(true, getView());
            if (this.displayMode != UpsellDisplayMode.FeatureScreen) {
                if (CollectionUtils.isEmpty((Collection<?>) this.products) || CollectionUtils.isEmpty(this.displayPrices)) {
                    showErrorDialog();
                    reportProductServiceErrorAnalytics();
                } else {
                    this.products = ProductUtils.filterByAvailability(this.products, (GeoLocationService) this.geoLocationService.get(), (PaymentService) this.paymentService.get());
                    ProductUtils.sortProductsBySubscriptionDurationAscending(this.products);
                    addBuyButtonsToLayout(this.products, this.buttonContainer);
                    setBehaviorBasedOnFooterButtons();
                }
            }
            reportShowUpsellAnalytics(this.products);
            this.rendered = true;
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!CONNECTION_FAILED_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        alertDialogFragment.setPositiveListener(this.onConnectionFailedRetryClickListener);
        alertDialogFragment.setNegativeListener(this.onConnectionFailedSkipClickListener);
        return true;
    }

    /* access modifiers changed from: protected */
    public void setBehaviorBasedOnFooterButtons() {
        MaterialUtils.setFixedFooterScrollingBehavior(getActivity(), this.buttonContainer.getChildCount() > 0);
    }

    /* access modifiers changed from: protected */
    public View createFeatureView(MfpUpsellFeature mfpUpsellFeature, ViewGroup viewGroup) {
        int i = 0;
        View inflate = this.inflater.inflate(R.layout.premium_upsell_feature, viewGroup, false);
        MfpImageView mfpImageView = (MfpImageView) ViewUtils.findById(inflate, R.id.upsellFeatureIcon);
        TextView textView = (TextView) ViewUtils.findById(inflate, R.id.upsellFeatureTitle);
        TextView textView2 = (TextView) ViewUtils.findById(inflate, R.id.upsellFeatureDescription);
        if (FEATURE_TO_DEFAULT_ICON_MAP.containsKey(mfpUpsellFeature.getFeatureName())) {
            i = ((Integer) FEATURE_TO_DEFAULT_ICON_MAP.get(mfpUpsellFeature.getFeatureName())).intValue();
        }
        loadImage(mfpImageView, mfpUpsellFeature.getIcon(), i);
        textView.setText(mfpUpsellFeature.getTitleText());
        textView.setTextColor(mfpUpsellFeature.getTitleColor());
        setTextSize(textView, mfpUpsellFeature.getTitleSize());
        textView2.setText(mfpUpsellFeature.getDescriptionText());
        textView2.setTextColor(mfpUpsellFeature.getDescriptionColor());
        setTextSize(textView2, mfpUpsellFeature.getDescriptionSize());
        inflate.setTag(mfpUpsellFeature.getFeatureName());
        if (this.displayMode == UpsellDisplayMode.FeatureScreen) {
            textView.setTextColor(getResources().getColor(R.color.blue));
            inflate.setOnClickListener(this.onFeatureClickedListener);
            inflate.setBackgroundResource(R.drawable.list_item_bg_selector);
        }
        return inflate;
    }

    /* access modifiers changed from: protected */
    public View createBuyButtonView(final MfpProduct mfpProduct, String str, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.premium_upsell_buy_button, viewGroup, false);
        TextView textView = (TextView) ViewUtils.findById(inflate, R.id.upsellBuyButtonSubtext);
        ((TextView) ViewUtils.findById(inflate, R.id.upsellBuyButtonText)).setText(str);
        textView.setText(ProductUtils.formatSubscriptionDuration((Context) getActivity(), mfpProduct.getSubscriptionDetails()));
        inflate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PremiumUpsellNativeFragment.this.reportBuyButtonClickedAnalytics(mfpProduct);
                PremiumUpsellNativeFragment.this.getNavigationHelper().fromFragment(PremiumUpsellNativeFragment.this).withExtra(Extras.EXTRA_PROMOTED_FEATURE, PremiumUpsellNativeFragment.this.promotedFeature).withIntent(((PaymentService) PremiumUpsellNativeFragment.this.paymentService.get()).getStartIntent(PremiumUpsellNativeFragment.this.getActivity(), mfpProduct)).startActivity(140);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void promoteFeatureToTop(String str) {
        int i;
        int i2 = 0;
        loop0:
        while (true) {
            i = -1;
            if (i2 >= this.groupContainer.getChildCount()) {
                i2 = -1;
                break;
            }
            ViewGroup viewGroup = (ViewGroup) ViewUtils.findById(this.groupContainer.getChildAt(i2), R.id.upsellFeatureContainer);
            i = 0;
            while (i < viewGroup.getChildCount()) {
                if (Strings.equals((Object) str, viewGroup.getChildAt(i).getTag())) {
                    break loop0;
                }
                i++;
            }
            i2++;
        }
        if (i2 >= 0 && i >= 0) {
            View childAt = this.groupContainer.getChildAt(i2);
            this.groupContainer.removeView(childAt);
            this.groupContainer.addView(childAt, 0);
            ViewGroup viewGroup2 = (ViewGroup) ViewUtils.findById(childAt, R.id.upsellFeatureContainer);
            View childAt2 = viewGroup2.getChildAt(i);
            viewGroup2.removeView(childAt2);
            viewGroup2.addView(childAt2, 0);
        }
    }

    /* access modifiers changed from: protected */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleFeatureClick(java.lang.String r8) {
        /*
            r7 = this;
            int r0 = r8.hashCode()
            r1 = 2
            r2 = 0
            r3 = 1
            switch(r0) {
                case -1729847549: goto L_0x0098;
                case -1461782369: goto L_0x008e;
                case -980893513: goto L_0x0084;
                case -623700506: goto L_0x007a;
                case 123666330: goto L_0x006f;
                case 414757984: goto L_0x0065;
                case 548188956: goto L_0x005a;
                case 706876260: goto L_0x0050;
                case 1312601329: goto L_0x0045;
                case 1388065393: goto L_0x003a;
                case 1475560651: goto L_0x002f;
                case 1605362048: goto L_0x0024;
                case 1821817519: goto L_0x0018;
                case 1975437660: goto L_0x000c;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x00a2
        L_0x000c:
            java.lang.String r0 = "premium-priority-support"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 8
            goto L_0x00a3
        L_0x0018:
            java.lang.String r0 = "premium-file-export"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 10
            goto L_0x00a3
        L_0x0024:
            java.lang.String r0 = "premium-assign-exercise"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 2
            goto L_0x00a3
        L_0x002f:
            java.lang.String r0 = "premium-quick-add"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 0
            goto L_0x00a3
        L_0x003a:
            java.lang.String r0 = "premium-diary-meal-macros"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 9
            goto L_0x00a3
        L_0x0045:
            java.lang.String r0 = "premium-food-entry-timestamps"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 13
            goto L_0x00a3
        L_0x0050:
            java.lang.String r0 = "premium-nutrient-dashboard"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 3
            goto L_0x00a3
        L_0x005a:
            java.lang.String r0 = "premium-custom-meal-goals"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 11
            goto L_0x00a3
        L_0x0065:
            java.lang.String r0 = "premium-ad-free"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 6
            goto L_0x00a3
        L_0x006f:
            java.lang.String r0 = "premium-weekly-digest"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 12
            goto L_0x00a3
        L_0x007a:
            java.lang.String r0 = "premium-custom-daily-goals"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 4
            goto L_0x00a3
        L_0x0084:
            java.lang.String r0 = "premium-food-list"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 7
            goto L_0x00a3
        L_0x008e:
            java.lang.String r0 = "premium-track-macros"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 1
            goto L_0x00a3
        L_0x0098:
            java.lang.String r0 = "premium-content"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r0 = 5
            goto L_0x00a3
        L_0x00a2:
            r0 = -1
        L_0x00a3:
            switch(r0) {
                case 0: goto L_0x0209;
                case 1: goto L_0x01f5;
                case 2: goto L_0x01e1;
                case 3: goto L_0x01bd;
                case 4: goto L_0x01a9;
                case 5: goto L_0x0173;
                case 6: goto L_0x0155;
                case 7: goto L_0x0140;
                case 8: goto L_0x0122;
                case 9: goto L_0x010d;
                case 10: goto L_0x00f6;
                case 11: goto L_0x00e1;
                case 12: goto L_0x00bf;
                case 13: goto L_0x00a8;
                default: goto L_0x00a6;
            }
        L_0x00a6:
            goto L_0x021d
        L_0x00a8:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.content.Context r4 = r7.getContext()
            java.lang.String r5 = ""
            android.content.Intent r4 = com.myfitnesspal.feature.diary.ui.activity.DiarySettingsActivity.newStartIntent(r4, r5)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x00bf:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.content.Context r4 = r7.getContext()
            android.content.Context r5 = r7.getContext()
            r6 = 2131889600(0x7f120dc0, float:1.9413868E38)
            java.lang.String r5 = r5.getString(r6)
            java.lang.String r6 = "http://www.myfitnesspal.com/reports/weekly-digest"
            android.content.Intent r4 = com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView.newStartIntentForWeeklyDigest(r4, r5, r3, r6)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x00e1:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.goals.ui.activity.MealGoalsActivity.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x00f6:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            com.myfitnesspal.feature.fileexport.ui.activity.FileExport$ExportMode r5 = com.myfitnesspal.feature.fileexport.ui.activity.FileExport.ExportMode.Normal
            android.content.Intent r4 = com.myfitnesspal.feature.fileexport.ui.activity.FileExport.createIntentForFileExport(r4, r5)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x010d:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.settings.ui.activity.LegacyDiarySettingsActivity.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x0122:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            r5 = 107(0x6b, float:1.5E-43)
            r6 = 2131888779(0x7f120a8b, float:1.9412203E38)
            java.lang.String r6 = r7.getString(r6)
            android.content.Intent r4 = com.myfitnesspal.feature.help.ui.activity.Faq.newStartIntent(r4, r5, r6)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x0140:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.nutrition.ui.activity.Nutrition.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x0155:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            r5 = 104(0x68, float:1.46E-43)
            r6 = 2131886221(0x7f12008d, float:1.9407015E38)
            java.lang.String r6 = r7.getString(r6)
            android.content.Intent r4 = com.myfitnesspal.feature.help.ui.activity.Faq.newStartIntent(r4, r5, r6)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x0173:
            dagger.Lazy r0 = r7.apiUrlProvider
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.shared.api.ApiUrlProvider r0 = (com.myfitnesspal.shared.api.ApiUrlProvider) r0
            java.lang.String r4 = "/premium"
            java.lang.String r0 = r0.getBaseUrlForBlog(r4)
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.String r0 = r0.toString()
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r4 = r7.getNavigationHelper()
            java.lang.String r5 = "url"
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r4.withExtra(r5, r0)
            java.lang.String r4 = "premium_content"
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withExtra(r4, r3)
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.blog.ui.activity.BlogActivity.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x01a9:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.goals.ui.activity.EditCustomMacroGoalsActivity.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x01bd:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            java.lang.String r4 = "settings_parent"
            java.lang.String r5 = "home"
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withExtra(r4, r5)
            java.lang.String r4 = "go_to_home"
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withExtra(r4, r3)
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.dashboard.ui.activity.NutrientDashboardSettingsActivity.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r4 = 162(0xa2, float:2.27E-43)
            r0.startActivity(r4)
            goto L_0x021d
        L_0x01e1:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.goals.ui.activity.ExerciseCaloriesActivity.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x01f5:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.support.v4.app.FragmentActivity r4 = r7.getActivity()
            android.content.Intent r4 = com.myfitnesspal.feature.goals.ui.activity.EditCustomMacroGoalsActivity.newStartIntent(r4)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
            goto L_0x021d
        L_0x0209:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r7.getNavigationHelper()
            android.content.Context r4 = r7.getContext()
            r5 = 0
            android.content.Intent r4 = com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.newStartIntent(r4, r5)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r0 = r0.withIntent(r4)
            r0.startActivity()
        L_0x021d:
            dagger.Lazy r0 = r7.premiumService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.feature.premium.service.PremiumService r0 = (com.myfitnesspal.feature.premium.service.PremiumService) r0
            boolean r0 = r0.isPremiumSubscribed()
            if (r0 == 0) goto L_0x0244
            dagger.Lazy r0 = r7.analytics
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.shared.service.analytics.AnalyticsService r0 = (com.myfitnesspal.shared.service.analytics.AnalyticsService) r0
            java.lang.String r4 = "premium_feature_list"
            java.lang.String[] r1 = new java.lang.String[r1]
            java.lang.String r5 = "feature"
            r1[r2] = r5
            r1[r3] = r8
            java.util.Map r8 = com.uacf.core.util.MapUtil.createMap(r1)
            r0.reportEvent(r4, r8)
        L_0x0244:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.premium.ui.fragment.PremiumUpsellNativeFragment.handleFeatureClick(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public boolean loaded() {
        return (this.upsellConfig == null || this.products == null || this.displayPrices == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void addGroupViewsToLayout(List<MfpUpsellGroup> list, ViewGroup viewGroup) {
        viewGroup.removeAllViews();
        configureGroupViewForDisplayType(viewGroup);
        for (MfpUpsellGroup createGroupView : list) {
            View createGroupView2 = createGroupView(createGroupView, viewGroup);
            if (createGroupView2 != null) {
                viewGroup.addView(createGroupView2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int addFeaturesToLayout(List<MfpUpsellFeature> list, ViewGroup viewGroup) {
        viewGroup.removeAllViews();
        int i = 0;
        for (MfpUpsellFeature mfpUpsellFeature : list) {
            if (isFeatureSupportedForClient(mfpUpsellFeature.getFeatureName())) {
                if (Strings.equals(mfpUpsellFeature.getFeatureName(), "premium-weekly-digest")) {
                    boolean isRolloutBasedOnDisplayModeOn = isRolloutBasedOnDisplayModeOn();
                    if (showWeeklyDigestForFeatureScreen(isRolloutBasedOnDisplayModeOn) || showWeeklyDigestForUpsell(mfpUpsellFeature, isRolloutBasedOnDisplayModeOn)) {
                        i = createAndAddFeatureView(viewGroup, i, mfpUpsellFeature);
                    }
                } else if (this.displayMode == UpsellDisplayMode.FeatureScreen || ProductUtils.isFeatureAvailable(mfpUpsellFeature.getFeatureName(), this.products)) {
                    i = createAndAddFeatureView(viewGroup, i, mfpUpsellFeature);
                }
            }
        }
        return i;
    }

    private boolean isFeatureSupportedForClient(String str) {
        return PremiumSupportedFeatures.SUPPORTED_FEATURES_SET.contains(str) && ((PremiumService) this.premiumService.get()).isFeatureAvailable(PremiumFeature.getFeature(str));
    }

    private int createAndAddFeatureView(ViewGroup viewGroup, int i, MfpUpsellFeature mfpUpsellFeature) {
        viewGroup.addView(createFeatureView(mfpUpsellFeature, viewGroup));
        return i + 1;
    }

    private boolean showWeeklyDigestForUpsell(MfpUpsellFeature mfpUpsellFeature, boolean z) {
        return z && this.displayMode == UpsellDisplayMode.SignUp && ProductUtils.isFeatureAvailable(mfpUpsellFeature.getFeatureName(), this.products);
    }

    private boolean showWeeklyDigestForFeatureScreen(boolean z) {
        return z && this.displayMode == UpsellDisplayMode.FeatureScreen;
    }

    private boolean isRolloutBasedOnDisplayModeOn() {
        if (this.displayMode == UpsellDisplayMode.FeatureScreen) {
            return ((ConfigService) this.configService.get()).isVariantOnAndCountryAndLanguageSupported(WeeklyDigestFeature.NAME);
        }
        return ((ConfigService) this.configService.get()).isVariantOnAndCountryAndLanguageSupported(WeeklyDigestUpsell.NAME);
    }

    /* access modifiers changed from: protected */
    public void addBuyButtonsToLayout(List<MfpProduct> list, ViewGroup viewGroup) {
        viewGroup.removeAllViews();
        for (int i = 0; i < Math.min(3, list.size()); i++) {
            String str = (String) this.displayPrices.get(((MfpProduct) list.get(i)).getProductId());
            if (Strings.notEmpty(str)) {
                viewGroup.addView(createBuyButtonView((MfpProduct) list.get(i), str, viewGroup));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void loadImage(MfpImageView mfpImageView, String str, int i) {
        if (!USE_DEFAULT_IMAGE.equals(str)) {
            mfpImageView.setImageDrawable(null);
            mfpImageView.setPlaceholderDrawable(TRANSPARENT);
            if (i != 0) {
                mfpImageView.setErrorImageId(i);
            }
            mfpImageView.setUrl(str);
        } else if (i != 0) {
            mfpImageView.setImageResource(i);
        }
    }

    /* access modifiers changed from: protected */
    public void showErrorDialog() {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.premium_upsell_failure_body)).setTitle(R.string.premium_upsell_failure_title)).setNegativeText(R.string.skip, this.onConnectionFailedSkipClickListener)).setPositiveText(R.string.retry, this.onConnectionFailedRetryClickListener);
        alertDialogFragment.setCancelable(false);
        getFragmentManager().executePendingTransactions();
        showDialogFragment(alertDialogFragment, CONNECTION_FAILED_DIALOG_TAG);
    }

    public static /* synthetic */ void lambda$new$1(PremiumUpsellNativeFragment premiumUpsellNativeFragment, View view) {
        premiumUpsellNativeFragment.handleFeatureClick(Strings.toString(view.getTag()));
        if (premiumUpsellNativeFragment.displayMode == UpsellDisplayMode.FeatureScreen) {
            ((LocalSettingsService) premiumUpsellNativeFragment.localSettings.get()).setHasClickedOnPremiumFeature(true);
        }
    }

    public void onProductsLoaded() {
        loadPrices();
    }

    /* access modifiers changed from: protected */
    public void onPricesLoaded() {
        render();
    }
}
