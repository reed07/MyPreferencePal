package com.myfitnesspal.feature.onboarding.ui.fragment;

import android.content.Context;
import android.view.View;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.ui.view.BlueClickableSpanNoUnderline;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, d2 = {"com/myfitnesspal/feature/onboarding/ui/fragment/WelcomeScreenFragment$setupGooglePlaySubscriptionLink$spannable$1$1", "Lcom/myfitnesspal/shared/ui/view/BlueClickableSpanNoUnderline;", "onClick", "", "widget", "Landroid/view/View;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: WelcomeScreenFragment.kt */
public final class WelcomeScreenFragment$setupGooglePlaySubscriptionLink$$inlined$apply$lambda$1 extends BlueClickableSpanNoUnderline {
    final /* synthetic */ String $linkText$inlined;
    final /* synthetic */ View $root$inlined;
    final /* synthetic */ int $start$inlined;
    final /* synthetic */ WelcomeScreenFragment this$0;

    WelcomeScreenFragment$setupGooglePlaySubscriptionLink$$inlined$apply$lambda$1(Context context, WelcomeScreenFragment welcomeScreenFragment, int i, View view, String str) {
        this.this$0 = welcomeScreenFragment;
        this.$start$inlined = i;
        this.$root$inlined = view;
        this.$linkText$inlined = str;
        super(context);
    }

    public void onClick(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, MPFAppWidgetProvider.WIDGET_REFERRER_ID);
        WelcomeScreenFragment.access$getPremiumOnboardingWelcomeViewModel$p(this.this$0).goToSubscriptions();
    }
}
