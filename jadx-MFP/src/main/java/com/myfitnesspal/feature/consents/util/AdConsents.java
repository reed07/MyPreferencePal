package com.myfitnesspal.feature.consents.util;

import com.myfitnesspal.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/feature/consents/util/AdConsents;", "", "id", "", "alertStringId", "", "alertTitleStringId", "(Ljava/lang/String;ILjava/lang/String;II)V", "getAlertStringId", "()I", "getAlertTitleStringId", "getId", "()Ljava/lang/String;", "PERSONALIZED_ADS", "LOCALIZED_ADS", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentUtils.kt */
public enum AdConsents {
    PERSONALIZED_ADS("personalized_ads", R.string.alert_skip_relevant_or_all_consent, R.string.alert_title_skip_relevant_advertising),
    LOCALIZED_ADS("localized_ads", R.string.alert_skip_location_consent, R.string.alert_title_skip_location_consent);
    
    private final int alertStringId;
    private final int alertTitleStringId;
    @NotNull
    private final String id;

    protected AdConsents(String str, int i, @NotNull int i2) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        this.id = str;
        this.alertStringId = i;
        this.alertTitleStringId = i2;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final int getAlertStringId() {
        return this.alertStringId;
    }

    public final int getAlertTitleStringId() {
        return this.alertTitleStringId;
    }
}
