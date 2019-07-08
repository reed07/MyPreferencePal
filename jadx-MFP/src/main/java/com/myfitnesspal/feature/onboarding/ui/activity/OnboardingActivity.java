package com.myfitnesspal.feature.onboarding.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.ui.fragment.EditCustomMacroGoalsFragment;
import com.myfitnesspal.feature.onboarding.navigation.Navigator;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingViewModelFactory;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingWelcomeViewModel;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0012\u0010!\u001a\u00020\u001a2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\u001aH\u0002J\b\u0010(\u001a\u00020)H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006+"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/activity/OnboardingActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "navigator", "Lcom/myfitnesspal/feature/onboarding/navigation/Navigator;", "getNavigator", "()Lcom/myfitnesspal/feature/onboarding/navigation/Navigator;", "setNavigator", "(Lcom/myfitnesspal/feature/onboarding/navigation/Navigator;)V", "nutrientGoalService", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/goals/service/NutrientGoalService;", "getNutrientGoalService", "()Ldagger/Lazy;", "setNutrientGoalService", "(Ldagger/Lazy;)V", "premiumOnboardingWelcomeViewModel", "Lkotlin/Lazy;", "Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingWelcomeViewModel;", "vmFactory", "Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;", "getVmFactory", "()Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;", "setVmFactory", "(Lcom/myfitnesspal/feature/onboarding/ui/viewmodel/PremiumOnboardingViewModelFactory;)V", "forcePhoneToPortraitOrientation", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "recalculateAndUpdateGoals", "event", "", "setupStatusBar", "showToolbar", "", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnboardingActivity.kt */
public final class OnboardingActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public Navigator navigator;
    @Inject
    @NotNull
    public Lazy<NutrientGoalService> nutrientGoalService;
    private final kotlin.Lazy<PremiumOnboardingWelcomeViewModel> premiumOnboardingWelcomeViewModel = LazyKt.lazy(new OnboardingActivity$premiumOnboardingWelcomeViewModel$1(this));
    @Inject
    @NotNull
    public PremiumOnboardingViewModelFactory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/ui/activity/OnboardingActivity$Companion;", "", "()V", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnboardingActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent newStartIntent(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent(context, OnboardingActivity.class);
        }
    }

    @JvmStatic
    @NotNull
    public static final Intent newStartIntent(@NotNull Context context) {
        return Companion.newStartIntent(context);
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

    public boolean showToolbar() {
        return false;
    }

    @NotNull
    public final Lazy<NutrientGoalService> getNutrientGoalService() {
        Lazy<NutrientGoalService> lazy = this.nutrientGoalService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nutrientGoalService");
        }
        return lazy;
    }

    public final void setNutrientGoalService(@NotNull Lazy<NutrientGoalService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.nutrientGoalService = lazy;
    }

    @NotNull
    public final Navigator getNavigator() {
        Navigator navigator2 = this.navigator;
        if (navigator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigator");
        }
        return navigator2;
    }

    public final void setNavigator(@NotNull Navigator navigator2) {
        Intrinsics.checkParameterIsNotNull(navigator2, "<set-?>");
        this.navigator = navigator2;
    }

    @NotNull
    public final PremiumOnboardingViewModelFactory getVmFactory() {
        PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory = this.vmFactory;
        if (premiumOnboardingViewModelFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        return premiumOnboardingViewModelFactory;
    }

    public final void setVmFactory(@NotNull PremiumOnboardingViewModelFactory premiumOnboardingViewModelFactory) {
        Intrinsics.checkParameterIsNotNull(premiumOnboardingViewModelFactory, "<set-?>");
        this.vmFactory = premiumOnboardingViewModelFactory;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        forcePhoneToPortraitOrientation();
        setupStatusBar();
        setContentView((int) R.layout.activity_onboarding);
        ((ImageView) _$_findCachedViewById(R.id.btnCloseOnboarding)).setOnClickListener(new OnboardingActivity$onCreate$1(this));
        Navigator navigator2 = this.navigator;
        if (navigator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigator");
        }
        navigator2.register(this);
        if (bundle == null) {
            ((PremiumOnboardingWelcomeViewModel) this.premiumOnboardingWelcomeViewModel.getValue()).showOnboardingWelcome();
        }
    }

    private final void forcePhoneToPortraitOrientation() {
        setRequestedOrientation(getResources().getBoolean(R.bool.isTablet) ? -1 : 1);
    }

    private final void setupStatusBar() {
        if (VERSION.SDK_INT >= 23) {
            Window window = getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(-1);
            View decorView = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "decorView");
            View decorView2 = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView2, "decorView");
            decorView.setSystemUiVisibility(decorView2.getSystemUiVisibility() | 8192);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i == 149 && i2 == -1 && intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Object obj = extras.get(Extras.EVENT_SOURCE);
                if (obj != null) {
                    recalculateAndUpdateGoals(obj);
                }
            }
        }
    }

    private final void recalculateAndUpdateGoals(Object obj) {
        if (obj instanceof MacroGoalsUpdatedEvent) {
            EditCustomMacroGoalsFragment newInstance = EditCustomMacroGoalsFragment.newInstance();
            Fragment fragment = newInstance;
            getSupportFragmentManager().beginTransaction().add(fragment, "dummy_tag").hide(fragment).commitNowAllowingStateLoss();
            newInstance.recalculateAndUpdateGoals(BitmapDescriptorFactory.HUE_RED, (MacroGoalsUpdatedEvent) obj);
        }
    }
}
