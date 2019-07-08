package com.myfitnesspal.feature.consents.ui.activity;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "adConsentId", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$getAlertStringAndTitle$consentIdMatchesOrDidNotAccept$1 extends Lambda implements Function1<String, Boolean> {
    final /* synthetic */ String $consentId;
    final /* synthetic */ AdConsentsActivity this$0;

    AdConsentsActivity$getAlertStringAndTitle$consentIdMatchesOrDidNotAccept$1(AdConsentsActivity adConsentsActivity, String str) {
        this.this$0 = adConsentsActivity;
        this.$consentId = str;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((String) obj));
    }

    public final boolean invoke(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "adConsentId");
        if (!Intrinsics.areEqual((Object) this.$consentId, (Object) str)) {
            if (!(this.$consentId.length() == 0) || !this.this$0.getViewModel().didNotAcceptConsent(str)) {
                return false;
            }
        }
        return true;
    }
}
