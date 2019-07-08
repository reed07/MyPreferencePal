package com.myfitnesspal.feature.alexainterstitial.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: AlexaInterstitialActivity.kt */
final class AlexaInterstitialActivity$setupClickListeners$3 implements OnClickListener {
    final /* synthetic */ AlexaInterstitialActivity this$0;

    AlexaInterstitialActivity$setupClickListeners$3(AlexaInterstitialActivity alexaInterstitialActivity) {
        this.this$0 = alexaInterstitialActivity;
    }

    public final void onClick(View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://undrarmr.co/2V1GYOg"));
        if (intent.resolveActivity(this.this$0.getPackageManager()) != null) {
            this.this$0.getNavigationHelper().withIntent(intent).startActivity();
        }
    }
}
