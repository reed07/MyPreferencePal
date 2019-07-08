package com.myfitnesspal.feature.consents.ui.activity;

import com.myfitnesspal.feature.consents.util.AdConsents;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "adConsent", "Lcom/myfitnesspal/feature/consents/util/AdConsents;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$getAlertStringAndTitle$getPair$1 extends Lambda implements Function1<AdConsents, Pair<? extends Integer, ? extends Integer>> {
    public static final AdConsentsActivity$getAlertStringAndTitle$getPair$1 INSTANCE = new AdConsentsActivity$getAlertStringAndTitle$getPair$1();

    AdConsentsActivity$getAlertStringAndTitle$getPair$1() {
        super(1);
    }

    @NotNull
    public final Pair<Integer, Integer> invoke(@NotNull AdConsents adConsents) {
        Intrinsics.checkParameterIsNotNull(adConsents, "adConsent");
        return new Pair<>(Integer.valueOf(adConsents.getAlertStringId()), Integer.valueOf(adConsents.getAlertTitleStringId()));
    }
}
