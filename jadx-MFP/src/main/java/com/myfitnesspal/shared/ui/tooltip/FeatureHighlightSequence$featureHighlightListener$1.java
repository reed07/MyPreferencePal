package com.myfitnesspal.shared.ui.tooltip;

import android.view.WindowManager;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView.Listener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/myfitnesspal/shared/ui/tooltip/FeatureHighlightSequence$featureHighlightListener$1", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Listener;", "onBackPressed", "", "view", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView;", "onContentClick", "onFeatureAbsent", "onFeatureClick", "onOutsideClick", "onPositiveButtonClick", "onViewShow", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FeatureHighlightSequence.kt */
public final class FeatureHighlightSequence$featureHighlightListener$1 extends Listener {
    final /* synthetic */ FeatureHighlightSequence this$0;

    FeatureHighlightSequence$featureHighlightListener$1(FeatureHighlightSequence featureHighlightSequence) {
        this.this$0 = featureHighlightSequence;
    }

    public void onViewShow(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        this.this$0.featureHighlightView = featureHighlightView;
    }

    public void onPositiveButtonClick(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        this.this$0.showNext();
    }

    public void onFeatureClick(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        this.this$0.showNext();
    }

    public void onContentClick(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        this.this$0.showNext();
    }

    public void onOutsideClick(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        this.this$0.showNext();
    }

    public void onFeatureAbsent(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        this.this$0.showNext();
    }

    public void onBackPressed(@NotNull FeatureHighlightView featureHighlightView) {
        Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        WindowManager access$getWindowManager$p = this.this$0.windowManager;
        if (access$getWindowManager$p != null) {
            access$getWindowManager$p.removeView(this.this$0.overlayView);
        }
        Function0 access$getOnSequenceCancelled$p = this.this$0.onSequenceCancelled;
        if (access$getOnSequenceCancelled$p != null) {
            Unit unit = (Unit) access$getOnSequenceCancelled$p.invoke();
        }
    }
}
