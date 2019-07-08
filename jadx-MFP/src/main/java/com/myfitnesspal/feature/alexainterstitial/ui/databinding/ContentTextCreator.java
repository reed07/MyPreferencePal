package com.myfitnesspal.feature.alexainterstitial.ui.databinding;

import android.content.Context;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity.Mode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/feature/alexainterstitial/ui/databinding/ContentTextCreator;", "", "context", "Landroid/content/Context;", "mode", "Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;", "(Landroid/content/Context;Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;)V", "getContext", "()Landroid/content/Context;", "getMode", "()Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;", "getHintText", "", "getMainText", "getSubText", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ContentTextCreator.kt */
public final class ContentTextCreator {
    @NotNull
    private final Context context;
    @NotNull
    private final Mode mode;

    public ContentTextCreator(@NotNull Context context2, @NotNull Mode mode2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(mode2, InternalAvidAdSessionContext.CONTEXT_MODE);
        this.context = context2;
        this.mode = mode2;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final Mode getMode() {
        return this.mode;
    }

    @NotNull
    public final String getHintText() {
        String string = this.context.getResources().getString(this.mode == Mode.LOG_WATER ? R.string.log_water_hint : R.string.log_weight_hint);
        return string != null ? string : "";
    }

    @NotNull
    public final String getMainText() {
        String string = this.context.getResources().getString(this.mode == Mode.LOG_WATER ? R.string.log_water_main_text : R.string.log_weight_main_text);
        return string != null ? string : "";
    }

    @NotNull
    public final String getSubText() {
        String string = this.context.getResources().getString(this.mode == Mode.LOG_WATER ? R.string.log_water_sub_text : R.string.log_weight_sub_text);
        return string != null ? string : "";
    }
}
