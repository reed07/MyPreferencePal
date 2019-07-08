package com.myfitnesspal.feature.consents.ui.activity;

import android.content.Context;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel.Mode;
import com.myfitnesspal.feature.consents.ui.activity.LearnMoreActivity.Companion;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Strings;
import io.uacf.consentservices.sdk.UacfConsent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "consent", "Lio/uacf/consentservices/sdk/UacfConsent;", "<anonymous parameter 1>", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsActivity.kt */
final class ConsentsActivity$learnMoreAction$1 extends Lambda implements Function2<UacfConsent, View, Unit> {
    final /* synthetic */ ConsentsActivity this$0;

    ConsentsActivity$learnMoreAction$1(ConsentsActivity consentsActivity) {
        this.this$0 = consentsActivity;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((UacfConsent) obj, (View) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull UacfConsent uacfConsent, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(uacfConsent, "consent");
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
        ConsentsAnalyticsHelper consentsAnalyticsHelper = (ConsentsAnalyticsHelper) this.this$0.getConsentsAnalyticsHelper().get();
        Mode mode = this.this$0.getViewModel().getMode();
        String id = uacfConsent.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "consent.id");
        consentsAnalyticsHelper.reportLearnMoreSee(mode, id);
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
