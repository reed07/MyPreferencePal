package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import com.facebook.ads.AudienceNetworkActivity;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.ViewGestureDetector.UserClickListener;
import com.mopub.network.Networking;

public class BaseHtmlWebView extends BaseWebView implements UserClickListener {
    private boolean mClicked;
    /* access modifiers changed from: private */
    public final ViewGestureDetector mViewGestureDetector;

    public BaseHtmlWebView(Context context, AdReport adReport) {
        super(context);
        disableScrollingAndZoom();
        getSettings().setJavaScriptEnabled(true);
        this.mViewGestureDetector = new ViewGestureDetector(context, (View) this, adReport);
        this.mViewGestureDetector.setUserClickListener(this);
        enablePlugins(true);
        setBackgroundColor(0);
    }

    public void init() {
        initializeOnTouchListener();
    }

    public void loadUrl(@Nullable String str) {
        if (str != null) {
            if (str.startsWith("javascript:")) {
                super.loadUrl(str);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Loading url: ");
            sb.append(str);
            MoPubLog.d(sb.toString());
        }
    }

    public void stopLoading() {
        if (this.mIsDestroyed) {
            StringBuilder sb = new StringBuilder();
            sb.append(BaseHtmlWebView.class.getSimpleName());
            sb.append("#stopLoading() called after destroy()");
            MoPubLog.w(sb.toString());
            return;
        }
        WebSettings settings = getSettings();
        if (settings == null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(BaseHtmlWebView.class.getSimpleName());
            sb2.append("#getSettings() returned null");
            MoPubLog.w(sb2.toString());
            return;
        }
        settings.setJavaScriptEnabled(false);
        super.stopLoading();
        settings.setJavaScriptEnabled(true);
    }

    private void disableScrollingAndZoom() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
    }

    /* access modifiers changed from: 0000 */
    public void loadHtmlResponse(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(Networking.getBaseUrlScheme());
        sb.append("://");
        sb.append(Constants.HOST);
        sb.append("/");
        loadDataWithBaseURL(sb.toString(), str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
    }

    /* access modifiers changed from: 0000 */
    public void initializeOnTouchListener() {
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BaseHtmlWebView.this.mViewGestureDetector.sendTouchEvent(motionEvent);
                return motionEvent.getAction() == 2;
            }
        });
    }

    public void onUserClick() {
        this.mClicked = true;
    }

    public void onResetUserClick() {
        this.mClicked = false;
    }

    public boolean wasClicked() {
        return this.mClicked;
    }
}
