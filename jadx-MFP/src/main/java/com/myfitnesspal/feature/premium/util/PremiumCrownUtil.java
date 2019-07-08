package com.myfitnesspal.feature.premium.util;

import android.app.Activity;
import android.view.View;
import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlight;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlight.HighlightShape;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightSequence;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/feature/premium/util/PremiumCrownUtil;", "", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumCrownUtil.kt */
public final class PremiumCrownUtil {
    public static final Companion Companion = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0007¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/premium/util/PremiumCrownUtil$Companion;", "", "()V", "prepareTooltip", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightSequence;", "activity", "Landroid/app/Activity;", "anchor", "Landroid/view/View;", "onFinishCallback", "Lkotlin/Function0;", "", "onCancelCallback", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: PremiumCrownUtil.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FeatureHighlightSequence prepareTooltip(@NotNull Activity activity, @NotNull View view, @Nullable Function0<Unit> function0, @Nullable Function0<Unit> function02) {
            Activity activity2 = activity;
            View view2 = view;
            Intrinsics.checkParameterIsNotNull(activity2, AbstractEvent.ACTIVITY);
            Intrinsics.checkParameterIsNotNull(view, "anchor");
            LinkedList linkedList = new LinkedList();
            String string = activity2.getString(R.string.premium_crown_tooltip);
            String str = string;
            Intrinsics.checkExpressionValueIsNotNull(string, "activity.getString(R.string.premium_crown_tooltip)");
            FeatureHighlight featureHighlight = r2;
            FeatureHighlight featureHighlight2 = new FeatureHighlight(view2, str, null, 0, 0, 0, 0, null, 0, null, 0, 0, null, null, true, HighlightShape.CIRCLE, 16380, null);
            linkedList.add(featureHighlight);
            FeatureHighlightSequence featureHighlightSequence = new FeatureHighlightSequence(activity2, linkedList, false, null, function0, function02, 12, null);
            return featureHighlightSequence;
        }
    }

    @JvmStatic
    @NotNull
    public static final FeatureHighlightSequence prepareTooltip(@NotNull Activity activity, @NotNull View view, @Nullable Function0<Unit> function0, @Nullable Function0<Unit> function02) {
        return Companion.prepareTooltip(activity, view, function0, function02);
    }
}
