package com.myfitnesspal.feature.challenges.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.uacf.core.util.ViewUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r¸\u0006\u0000"}, d2 = {"com/myfitnesspal/feature/challenges/ui/fragment/ChallengeWebViewFragment$onCreateView$1$1", "Landroid/webkit/WebViewClient;", "onPageFinished", "", "webView", "Landroid/webkit/WebView;", "url", "", "shouldOverrideUrlLoading", "", "view", "resourceRequest", "Landroid/webkit/WebResourceRequest;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ChallengeWebViewFragment.kt */
public final class ChallengeWebViewFragment$onCreateView$$inlined$let$lambda$1 extends WebViewClient {
    final /* synthetic */ View $view$inlined;
    final /* synthetic */ ChallengeWebViewFragment this$0;

    ChallengeWebViewFragment$onCreateView$$inlined$let$lambda$1(ChallengeWebViewFragment challengeWebViewFragment, View view) {
        this.this$0 = challengeWebViewFragment;
        this.$view$inlined = view;
    }

    public boolean shouldOverrideUrlLoading(@NotNull WebView webView, @NotNull WebResourceRequest webResourceRequest) {
        Intrinsics.checkParameterIsNotNull(webView, Promotion.ACTION_VIEW);
        Intrinsics.checkParameterIsNotNull(webResourceRequest, "resourceRequest");
        Uri url = webResourceRequest.getUrl();
        if (!ChallengesUtil.isUrlForPDF(url != null ? url.toString() : null)) {
            return false;
        }
        this.this$0.startActivity(new Intent("android.intent.action.VIEW", webResourceRequest.getUrl()));
        return true;
    }

    public void onPageFinished(@NotNull WebView webView, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(webView, "webView");
        Intrinsics.checkParameterIsNotNull(str, "url");
        super.onPageFinished(webView, str);
        View view = this.$view$inlined;
        Intrinsics.checkExpressionValueIsNotNull(view, Promotion.ACTION_VIEW);
        ViewUtils.setGone((ProgressBar) view.findViewById(R.id.rulesProgressBar));
    }
}
