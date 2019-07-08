package com.myfitnesspal.shared.ui.tooltip;

import android.view.ViewTreeObserver.OnScrollChangedListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "onScrollChanged", "com/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$2$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: FeatureHighlightView.kt */
final class FeatureHighlightView$$special$$inlined$onLaidOut$1$lambda$1 implements OnScrollChangedListener {
    final /* synthetic */ FeatureHighlightView$$special$$inlined$onLaidOut$1 this$0;

    FeatureHighlightView$$special$$inlined$onLaidOut$1$lambda$1(FeatureHighlightView$$special$$inlined$onLaidOut$1 featureHighlightView$$special$$inlined$onLaidOut$1) {
        this.this$0 = featureHighlightView$$special$$inlined$onLaidOut$1;
    }

    public final void onScrollChanged() {
        this.this$0.this$0.calculateViewBounds();
        this.this$0.this$0.invalidate();
    }
}
