package com.myfitnesspal.feature.blog.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.myfitnesspal.shared.ui.view.MfpWebView;

public class MfpBlogWebView extends MfpWebView {
    private final String GOOGLE = "google";
    private final String GOOGLE_CAPS = "Google";

    public MfpBlogWebView(Context context) {
        super(context);
    }

    public MfpBlogWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MfpBlogWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getUserAgentString(String str) {
        return super.getUserAgentString(str).replaceAll("google", "").replaceAll("Google", "");
    }
}
