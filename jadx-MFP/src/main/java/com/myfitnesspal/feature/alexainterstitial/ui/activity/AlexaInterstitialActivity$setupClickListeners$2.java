package com.myfitnesspal.feature.alexainterstitial.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity.Mode;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: AlexaInterstitialActivity.kt */
final class AlexaInterstitialActivity$setupClickListeners$2 implements OnClickListener {
    final /* synthetic */ Mode $mode;
    final /* synthetic */ AlexaInterstitialActivity this$0;

    AlexaInterstitialActivity$setupClickListeners$2(AlexaInterstitialActivity alexaInterstitialActivity, Mode mode) {
        this.this$0 = alexaInterstitialActivity;
        this.$mode = mode;
    }

    public final void onClick(View view) {
        this.this$0.getAnalyticsHelper().reportInterstitialLearnMoreTapped(this.$mode);
        this.this$0.getNavigationHelper().withIntent(FullScreenWebView.newStartIntent(this.this$0, "https://blog.myfitnesspal.com/say-hello-to-myfitnesspal-on-alexa?utm_campaign=interstitial&utm_source=android", "", false, true, false)).startActivity();
    }
}
