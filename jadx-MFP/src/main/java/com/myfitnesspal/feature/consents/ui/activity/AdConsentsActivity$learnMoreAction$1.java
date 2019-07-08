package com.myfitnesspal.feature.consents.ui.activity;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.ui.activity.LearnMoreActivity.Companion;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Strings;
import io.uacf.consentservices.sdk.UacfConsent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "consent", "Lio/uacf/consentservices/sdk/UacfConsent;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$learnMoreAction$1 extends Lambda implements Function1<UacfConsent, Unit> {
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$learnMoreAction$1(AdConsentsActivity adConsentsActivity) {
        this.this$0 = adConsentsActivity;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((UacfConsent) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull UacfConsent uacfConsent) {
        Intrinsics.checkParameterIsNotNull(uacfConsent, "consent");
        AdConsentsAnalyticsHelper adConsentsAnalyticsHelper = (AdConsentsAnalyticsHelper) this.this$0.getAdConsentsAnalyticsHelper().get();
        String id = uacfConsent.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "consent.id");
        adConsentsAnalyticsHelper.reportLearnMoreTapped(id);
        NavigationHelper navigationHelper = this.this$0.getNavigationHelper();
        Companion companion = LearnMoreActivity.Companion;
        Context context = this.this$0;
        String strings = Strings.toString(uacfConsent.getContentUrl());
        Intrinsics.checkExpressionValueIsNotNull(strings, "Strings.toString(consent.contentUrl)");
        String string = this.this$0.getString(R.string.learn_more);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.learn_more)");
        navigationHelper.withIntent(companion.newStartIntent(context, strings, string)).startActivity();
    }
}
