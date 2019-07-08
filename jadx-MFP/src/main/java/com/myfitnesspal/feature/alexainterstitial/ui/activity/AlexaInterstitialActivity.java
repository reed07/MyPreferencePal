package com.myfitnesspal.feature.alexainterstitial.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.Constants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.databinding.ActivityAlexaInterstitialBinding;
import com.myfitnesspal.feature.alexainterstitial.analytics.AlexaInterstitialAnalyticsHelper;
import com.myfitnesspal.feature.alexainterstitial.ui.databinding.ContentTextCreator;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import java.io.Serializable;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "analyticsHelper", "Lcom/myfitnesspal/feature/alexainterstitial/analytics/AlexaInterstitialAnalyticsHelper;", "getAnalyticsHelper", "()Lcom/myfitnesspal/feature/alexainterstitial/analytics/AlexaInterstitialAnalyticsHelper;", "setAnalyticsHelper", "(Lcom/myfitnesspal/feature/alexainterstitial/analytics/AlexaInterstitialAnalyticsHelper;)V", "userApplicationSettings", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/userapplicationsettings/service/UserApplicationSettingsService;", "getUserApplicationSettings", "()Ldagger/Lazy;", "setUserApplicationSettings", "(Ldagger/Lazy;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setAppropriateSeenFlag", "mode", "Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;", "setupClickListeners", "shouldShowTitle", "", "showToolbar", "Companion", "Mode", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AlexaInterstitialActivity.kt */
public final class AlexaInterstitialActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String ENABLE_NOW_URL = "https://undrarmr.co/2V1GYOg";
    private static final String EXTRA_MODE = "extra_mode";
    private static final String LEARN_MORE_URL = "https://blog.myfitnesspal.com/say-hello-to-myfitnesspal-on-alexa?utm_campaign=interstitial&utm_source=android";
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public AlexaInterstitialAnalyticsHelper analyticsHelper;
    @Inject
    @NotNull
    public Lazy<UserApplicationSettingsService> userApplicationSettings;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Companion;", "", "()V", "ENABLE_NOW_URL", "", "EXTRA_MODE", "LEARN_MORE_URL", "newInstance", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "mode", "Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AlexaInterstitialActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent newInstance(@NotNull Context context, @NotNull Mode mode) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
            Intent putExtra = new Intent(context, AlexaInterstitialActivity.class).putExtra("extra_mode", mode);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(context, AlexaInt…utExtra(EXTRA_MODE, mode)");
            return putExtra;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;", "", "(Ljava/lang/String;I)V", "LOG_WEIGHT", "LOG_WATER", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AlexaInterstitialActivity.kt */
    public enum Mode {
        LOG_WEIGHT,
        LOG_WATER
    }

    @JvmStatic
    @NotNull
    public static final Intent newInstance(@NotNull Context context, @NotNull Mode mode) {
        return Companion.newInstance(context, mode);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public boolean shouldShowTitle() {
        return false;
    }

    public boolean showToolbar() {
        return false;
    }

    @NotNull
    public final Lazy<UserApplicationSettingsService> getUserApplicationSettings() {
        Lazy<UserApplicationSettingsService> lazy = this.userApplicationSettings;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userApplicationSettings");
        }
        return lazy;
    }

    public final void setUserApplicationSettings(@NotNull Lazy<UserApplicationSettingsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.userApplicationSettings = lazy;
    }

    @NotNull
    public final AlexaInterstitialAnalyticsHelper getAnalyticsHelper() {
        AlexaInterstitialAnalyticsHelper alexaInterstitialAnalyticsHelper = this.analyticsHelper;
        if (alexaInterstitialAnalyticsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
        }
        return alexaInterstitialAnalyticsHelper;
    }

    public final void setAnalyticsHelper(@NotNull AlexaInterstitialAnalyticsHelper alexaInterstitialAnalyticsHelper) {
        Intrinsics.checkParameterIsNotNull(alexaInterstitialAnalyticsHelper, "<set-?>");
        this.analyticsHelper = alexaInterstitialAnalyticsHelper;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_alexa_interstitial);
        Intrinsics.checkExpressionValueIsNotNull(contentView, "DataBindingUtil.setConte…ivity_alexa_interstitial)");
        ActivityAlexaInterstitialBinding activityAlexaInterstitialBinding = (ActivityAlexaInterstitialBinding) contentView;
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, Constants.INTENT_SCHEME);
        Serializable serializable = BundleUtils.getSerializable(intent.getExtras(), "extra_mode", Mode.class.getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(serializable, "BundleUtils.getSerializa…::class.java.classLoader)");
        Mode mode = (Mode) serializable;
        activityAlexaInterstitialBinding.setTextCreator(new ContentTextCreator(this, mode));
        setAppropriateSeenFlag(mode);
        setupClickListeners(mode);
    }

    private final void setupClickListeners(Mode mode) {
        ((ImageView) _$_findCachedViewById(R.id.close_button)).setOnClickListener(new AlexaInterstitialActivity$setupClickListeners$1(this));
        ((TextView) _$_findCachedViewById(R.id.learn_more)).setOnClickListener(new AlexaInterstitialActivity$setupClickListeners$2(this, mode));
        ((Button) _$_findCachedViewById(R.id.enable_now_button)).setOnClickListener(new AlexaInterstitialActivity$setupClickListeners$3(this));
    }

    private final void setAppropriateSeenFlag(Mode mode) {
        AlexaInterstitialAnalyticsHelper alexaInterstitialAnalyticsHelper = this.analyticsHelper;
        if (alexaInterstitialAnalyticsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
        }
        alexaInterstitialAnalyticsHelper.reportInterstitialSeen(mode);
        Lazy<UserApplicationSettingsService> lazy = this.userApplicationSettings;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userApplicationSettings");
        }
        UserApplicationSettingsService userApplicationSettingsService = (UserApplicationSettingsService) lazy.get();
        if (mode == Mode.LOG_WATER) {
            userApplicationSettingsService.setDidSeeAlexaInterstitialForLogWater(true);
        } else {
            userApplicationSettingsService.setDidSeeAlexaInterstitialForLogWeight(true);
        }
    }
}
