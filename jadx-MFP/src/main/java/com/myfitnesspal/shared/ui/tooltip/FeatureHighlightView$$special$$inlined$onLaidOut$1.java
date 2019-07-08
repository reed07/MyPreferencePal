package com.myfitnesspal.shared.ui.tooltip;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ScrollView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "onGlobalLayout", "com/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$onLaidOut$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: FeatureHighlightView.kt */
public final class FeatureHighlightView$$special$$inlined$onLaidOut$1 implements OnGlobalLayoutListener {
    final /* synthetic */ FeatureHighlightView this$0;

    public FeatureHighlightView$$special$$inlined$onLaidOut$1(FeatureHighlightView featureHighlightView) {
        this.this$0 = featureHighlightView;
    }

    public final void onGlobalLayout() {
        FeatureHighlightView featureHighlightView = this.this$0;
        if (!featureHighlightView.isVisibleInScrollView(featureHighlightView.featureHighlight.getView())) {
            FeatureHighlightView featureHighlightView2 = this.this$0;
            ScrollView access$getParentScrollView$p = featureHighlightView2.getParentScrollView(featureHighlightView2.featureHighlight.getView());
            if (access$getParentScrollView$p != null) {
                int bottom = this.this$0.featureHighlight.getView().getBottom();
                FeatureHighlightView featureHighlightView3 = this.this$0;
                ScrollView access$getParentScrollView$p2 = featureHighlightView3.getParentScrollView(featureHighlightView3.featureHighlight.getView());
                access$getParentScrollView$p.scrollTo(0, (bottom - (access$getParentScrollView$p2 != null ? access$getParentScrollView$p2.getHeight() : 0)) + 75);
            }
            if (access$getParentScrollView$p != null) {
                ViewTreeObserver viewTreeObserver = access$getParentScrollView$p.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.addOnScrollChangedListener(new FeatureHighlightView$$special$$inlined$onLaidOut$1$lambda$1(this));
                    return;
                }
                return;
            }
            return;
        }
        this.this$0.calculateViewBounds();
        this.this$0.invalidate();
    }
}
