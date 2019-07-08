package com.mopub.mraid;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.C;
import com.integralads.avid.library.mopub.BuildConfig;
import com.mopub.common.AdReport;
import com.mopub.common.AdType;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibilityTracker;
import com.mopub.common.VisibilityTracker.VisibilityTrackerListener;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BaseWebView;
import com.mopub.mobileads.ViewGestureDetector;
import com.mopub.mobileads.ViewGestureDetector.UserClickListener;
import com.mopub.network.Networking;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

public class MraidBridge {
    private final AdReport mAdReport;
    private boolean mHasLoaded;
    /* access modifiers changed from: private */
    public boolean mIsClicked;
    /* access modifiers changed from: private */
    @Nullable
    public MraidBridgeListener mMraidBridgeListener;
    @NonNull
    private final MraidNativeCommandHandler mMraidNativeCommandHandler;
    @Nullable
    private MraidWebView mMraidWebView;
    private final WebViewClient mMraidWebViewClient;
    @NonNull
    private final PlacementType mPlacementType;

    public interface MraidBridgeListener {
        void onClose();

        boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage);

        void onExpand(URI uri, boolean z) throws MraidCommandException;

        boolean onJsAlert(@NonNull String str, @NonNull JsResult jsResult);

        void onOpen(URI uri);

        void onPageFailedToLoad();

        void onPageLoaded();

        void onPlayVideo(URI uri);

        void onResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) throws MraidCommandException;

        void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException;

        void onUseCustomClose(boolean z);

        void onVisibilityChanged(boolean z);
    }

    public static class MraidWebView extends BaseWebView {
        private boolean mMraidViewable;
        @Nullable
        private OnVisibilityChangedListener mOnVisibilityChangedListener;
        @Nullable
        private VisibilityTracker mVisibilityTracker;

        public interface OnVisibilityChangedListener {
            void onVisibilityChanged(boolean z);
        }

        public MraidWebView(Context context) {
            super(context);
            if (VERSION.SDK_INT <= 22) {
                this.mMraidViewable = getVisibility() == 0;
                return;
            }
            this.mVisibilityTracker = new VisibilityTracker(context);
            this.mVisibilityTracker.setVisibilityTrackerListener(new VisibilityTrackerListener() {
                public void onVisibilityChanged(@NonNull List<View> list, @NonNull List<View> list2) {
                    Preconditions.checkNotNull(list);
                    Preconditions.checkNotNull(list2);
                    MraidWebView mraidWebView = MraidWebView.this;
                    mraidWebView.setMraidViewable(list.contains(mraidWebView));
                }
            });
        }

        /* access modifiers changed from: 0000 */
        public void setVisibilityChangedListener(@Nullable OnVisibilityChangedListener onVisibilityChangedListener) {
            this.mOnVisibilityChangedListener = onVisibilityChangedListener;
        }

        /* access modifiers changed from: protected */
        public void onVisibilityChanged(@NonNull View view, int i) {
            super.onVisibilityChanged(view, i);
            VisibilityTracker visibilityTracker = this.mVisibilityTracker;
            boolean z = true;
            if (visibilityTracker == null) {
                if (i != 0) {
                    z = false;
                }
                setMraidViewable(z);
                return;
            }
            if (i == 0) {
                visibilityTracker.clear();
                this.mVisibilityTracker.addView(view, this, 0, 0, Integer.valueOf(1));
            } else {
                visibilityTracker.removeView(this);
                setMraidViewable(false);
            }
        }

        /* access modifiers changed from: private */
        public void setMraidViewable(boolean z) {
            if (this.mMraidViewable != z) {
                this.mMraidViewable = z;
                OnVisibilityChangedListener onVisibilityChangedListener = this.mOnVisibilityChangedListener;
                if (onVisibilityChangedListener != null) {
                    onVisibilityChangedListener.onVisibilityChanged(z);
                }
            }
        }

        public boolean isMraidViewable() {
            return this.mMraidViewable;
        }

        public void destroy() {
            super.destroy();
            this.mVisibilityTracker = null;
            this.mOnVisibilityChangedListener = null;
        }
    }

    MraidBridge(@Nullable AdReport adReport, @NonNull PlacementType placementType) {
        this(adReport, placementType, new MraidNativeCommandHandler());
    }

    @VisibleForTesting
    MraidBridge(@Nullable AdReport adReport, @NonNull PlacementType placementType, @NonNull MraidNativeCommandHandler mraidNativeCommandHandler) {
        this.mMraidWebViewClient = new MraidWebViewClient() {
            public boolean shouldOverrideUrlLoading(@NonNull WebView webView, @NonNull String str) {
                return MraidBridge.this.handleShouldOverrideUrl(str);
            }

            public void onPageFinished(@NonNull WebView webView, @NonNull String str) {
                MraidBridge.this.handlePageFinished();
            }

            public void onReceivedError(@NonNull WebView webView, int i, @NonNull String str, @NonNull String str2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error: ");
                sb.append(str);
                MoPubLog.d(sb.toString());
                super.onReceivedError(webView, i, str, str2);
            }
        };
        this.mAdReport = adReport;
        this.mPlacementType = placementType;
        this.mMraidNativeCommandHandler = mraidNativeCommandHandler;
    }

    /* access modifiers changed from: 0000 */
    public void setMraidBridgeListener(@Nullable MraidBridgeListener mraidBridgeListener) {
        this.mMraidBridgeListener = mraidBridgeListener;
    }

    /* access modifiers changed from: 0000 */
    public void attachView(@NonNull MraidWebView mraidWebView) {
        this.mMraidWebView = mraidWebView;
        this.mMraidWebView.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 17 && this.mPlacementType == PlacementType.INTERSTITIAL) {
            mraidWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.mMraidWebView.setScrollContainer(false);
        this.mMraidWebView.setVerticalScrollBarEnabled(false);
        this.mMraidWebView.setHorizontalScrollBarEnabled(false);
        this.mMraidWebView.setBackgroundColor(-16777216);
        this.mMraidWebView.setWebViewClient(this.mMraidWebViewClient);
        this.mMraidWebView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                if (MraidBridge.this.mMraidBridgeListener != null) {
                    return MraidBridge.this.mMraidBridgeListener.onJsAlert(str2, jsResult);
                }
                return super.onJsAlert(webView, str, str2, jsResult);
            }

            public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
                if (MraidBridge.this.mMraidBridgeListener != null) {
                    return MraidBridge.this.mMraidBridgeListener.onConsoleMessage(consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }

            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, customViewCallback);
            }
        });
        final ViewGestureDetector viewGestureDetector = new ViewGestureDetector(this.mMraidWebView.getContext(), (View) this.mMraidWebView, this.mAdReport);
        viewGestureDetector.setUserClickListener(new UserClickListener() {
            public void onUserClick() {
                MraidBridge.this.mIsClicked = true;
            }

            public void onResetUserClick() {
                MraidBridge.this.mIsClicked = false;
            }

            public boolean wasClicked() {
                return MraidBridge.this.mIsClicked;
            }
        });
        this.mMraidWebView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                viewGestureDetector.sendTouchEvent(motionEvent);
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        this.mMraidWebView.setVisibilityChangedListener(new OnVisibilityChangedListener() {
            public void onVisibilityChanged(boolean z) {
                if (MraidBridge.this.mMraidBridgeListener != null) {
                    MraidBridge.this.mMraidBridgeListener.onVisibilityChanged(z);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void detach() {
        MraidWebView mraidWebView = this.mMraidWebView;
        if (mraidWebView != null) {
            mraidWebView.destroy();
            this.mMraidWebView = null;
        }
    }

    public void setContentHtml(@NonNull String str) {
        MraidWebView mraidWebView = this.mMraidWebView;
        if (mraidWebView == null) {
            MoPubLog.d("MRAID bridge called setContentHtml before WebView was attached");
            return;
        }
        this.mHasLoaded = false;
        StringBuilder sb = new StringBuilder();
        sb.append(Networking.getBaseUrlScheme());
        sb.append("://");
        sb.append(Constants.HOST);
        sb.append("/");
        mraidWebView.loadDataWithBaseURL(sb.toString(), str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
    }

    public void setContentUrl(String str) {
        MraidWebView mraidWebView = this.mMraidWebView;
        if (mraidWebView == null) {
            MoPubLog.d("MRAID bridge called setContentHtml while WebView was not attached");
            return;
        }
        this.mHasLoaded = false;
        mraidWebView.loadUrl(str);
    }

    /* access modifiers changed from: 0000 */
    public void injectJavaScript(@NonNull String str) {
        if (this.mMraidWebView == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t");
            sb.append(str);
            MoPubLog.d(sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Injecting Javascript into MRAID WebView:\n\t");
        sb2.append(str);
        MoPubLog.d(sb2.toString());
        MraidWebView mraidWebView = this.mMraidWebView;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("javascript:");
        sb3.append(str);
        mraidWebView.loadUrl(sb3.toString());
    }

    /* access modifiers changed from: private */
    public void fireErrorEvent(@NonNull MraidJavascriptCommand mraidJavascriptCommand, @NonNull String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("window.mraidbridge.notifyErrorEvent(");
        sb.append(JSONObject.quote(mraidJavascriptCommand.toJavascriptString()));
        sb.append(", ");
        sb.append(JSONObject.quote(str));
        sb.append(")");
        injectJavaScript(sb.toString());
    }

    private void fireNativeCommandCompleteEvent(@NonNull MraidJavascriptCommand mraidJavascriptCommand) {
        StringBuilder sb = new StringBuilder();
        sb.append("window.mraidbridge.nativeCallComplete(");
        sb.append(JSONObject.quote(mraidJavascriptCommand.toJavascriptString()));
        sb.append(")");
        injectJavaScript(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean handleShouldOverrideUrl(@NonNull String str) {
        try {
            URI uri = new URI(str);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if (BuildConfig.SDK_NAME.equals(scheme)) {
                if ("failLoad".equals(host) && this.mPlacementType == PlacementType.INLINE) {
                    MraidBridgeListener mraidBridgeListener = this.mMraidBridgeListener;
                    if (mraidBridgeListener != null) {
                        mraidBridgeListener.onPageFailedToLoad();
                    }
                }
                return true;
            } else if (AdType.MRAID.equals(scheme)) {
                HashMap hashMap = new HashMap();
                for (NameValuePair nameValuePair : URLEncodedUtils.parse(uri, "UTF-8")) {
                    hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
                }
                MraidJavascriptCommand fromJavascriptString = MraidJavascriptCommand.fromJavascriptString(host);
                try {
                    runCommand(fromJavascriptString, hashMap);
                } catch (MraidCommandException e) {
                    fireErrorEvent(fromJavascriptString, e.getMessage());
                }
                fireNativeCommandCompleteEvent(fromJavascriptString);
                return true;
            } else if (!this.mIsClicked) {
                return false;
            } else {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                try {
                    if (this.mMraidWebView == null) {
                        MoPubLog.d("WebView was detached. Unable to load a URL");
                        return true;
                    }
                    this.mMraidWebView.getContext().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("No activity found to handle this URL ");
                    sb.append(str);
                    MoPubLog.d(sb.toString());
                    return false;
                }
            }
        } catch (URISyntaxException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid MRAID URL: ");
            sb2.append(str);
            MoPubLog.d(sb2.toString());
            fireErrorEvent(MraidJavascriptCommand.UNSPECIFIED, "Mraid command sent an invalid URL");
            return true;
        }
    }

    /* access modifiers changed from: private */
    @VisibleForTesting
    public void handlePageFinished() {
        if (!this.mHasLoaded) {
            this.mHasLoaded = true;
            MraidBridgeListener mraidBridgeListener = this.mMraidBridgeListener;
            if (mraidBridgeListener != null) {
                mraidBridgeListener.onPageLoaded();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void runCommand(@NonNull final MraidJavascriptCommand mraidJavascriptCommand, @NonNull Map<String, String> map) throws MraidCommandException {
        if (mraidJavascriptCommand.requiresClick(this.mPlacementType) && !this.mIsClicked) {
            throw new MraidCommandException("Cannot execute this command unless the user clicks");
        } else if (this.mMraidBridgeListener == null) {
            throw new MraidCommandException("Invalid state to execute this command");
        } else if (this.mMraidWebView != null) {
            switch (mraidJavascriptCommand) {
                case CLOSE:
                    this.mMraidBridgeListener.onClose();
                    return;
                case RESIZE:
                    this.mMraidBridgeListener.onResize(checkRange(parseSize((String) map.get("width")), 0, 100000), checkRange(parseSize((String) map.get("height")), 0, 100000), checkRange(parseSize((String) map.get("offsetX")), -100000, 100000), checkRange(parseSize((String) map.get("offsetY")), -100000, 100000), parseClosePosition((String) map.get("customClosePosition"), ClosePosition.TOP_RIGHT), parseBoolean((String) map.get("allowOffscreen"), true));
                    return;
                case EXPAND:
                    this.mMraidBridgeListener.onExpand(parseURI((String) map.get("url"), null), parseBoolean((String) map.get("shouldUseCustomClose"), false));
                    return;
                case USE_CUSTOM_CLOSE:
                    this.mMraidBridgeListener.onUseCustomClose(parseBoolean((String) map.get("shouldUseCustomClose"), false));
                    return;
                case OPEN:
                    this.mMraidBridgeListener.onOpen(parseURI((String) map.get("url")));
                    return;
                case SET_ORIENTATION_PROPERTIES:
                    this.mMraidBridgeListener.onSetOrientationProperties(parseBoolean((String) map.get("allowOrientationChange")), parseOrientation((String) map.get("forceOrientation")));
                    return;
                case PLAY_VIDEO:
                    this.mMraidBridgeListener.onPlayVideo(parseURI((String) map.get(ShareConstants.MEDIA_URI)));
                    return;
                case STORE_PICTURE:
                    this.mMraidNativeCommandHandler.storePicture(this.mMraidWebView.getContext(), parseURI((String) map.get(ShareConstants.MEDIA_URI)).toString(), new MraidCommandFailureListener() {
                        public void onFailure(MraidCommandException mraidCommandException) {
                            MraidBridge.this.fireErrorEvent(mraidJavascriptCommand, mraidCommandException.getMessage());
                        }
                    });
                    return;
                case CREATE_CALENDAR_EVENT:
                    this.mMraidNativeCommandHandler.createCalendarEvent(this.mMraidWebView.getContext(), map);
                    return;
                case UNSPECIFIED:
                    throw new MraidCommandException("Unspecified MRAID Javascript command");
                default:
                    return;
            }
        } else {
            throw new MraidCommandException("The current WebView is being destroyed");
        }
    }

    private ClosePosition parseClosePosition(@NonNull String str, @NonNull ClosePosition closePosition) throws MraidCommandException {
        if (TextUtils.isEmpty(str)) {
            return closePosition;
        }
        if (str.equals("top-left")) {
            return ClosePosition.TOP_LEFT;
        }
        if (str.equals("top-right")) {
            return ClosePosition.TOP_RIGHT;
        }
        if (str.equals(TtmlNode.CENTER)) {
            return ClosePosition.CENTER;
        }
        if (str.equals("bottom-left")) {
            return ClosePosition.BOTTOM_LEFT;
        }
        if (str.equals("bottom-right")) {
            return ClosePosition.BOTTOM_RIGHT;
        }
        if (str.equals("top-center")) {
            return ClosePosition.TOP_CENTER;
        }
        if (str.equals("bottom-center")) {
            return ClosePosition.BOTTOM_CENTER;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid close position: ");
        sb.append(str);
        throw new MraidCommandException(sb.toString());
    }

    private int parseSize(@NonNull String str) throws MraidCommandException {
        try {
            return Integer.parseInt(str, 10);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid numeric parameter: ");
            sb.append(str);
            throw new MraidCommandException(sb.toString());
        }
    }

    private MraidOrientation parseOrientation(String str) throws MraidCommandException {
        if (Extras.ORIENTATION_PORTRAIT.equals(str)) {
            return MraidOrientation.PORTRAIT;
        }
        if (Extras.ORIENTATION_LANDSCAPE.equals(str)) {
            return MraidOrientation.LANDSCAPE;
        }
        if ("none".equals(str)) {
            return MraidOrientation.NONE;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid orientation: ");
        sb.append(str);
        throw new MraidCommandException(sb.toString());
    }

    private int checkRange(int i, int i2, int i3) throws MraidCommandException {
        if (i >= i2 && i <= i3) {
            return i;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Integer parameter out of range: ");
        sb.append(i);
        throw new MraidCommandException(sb.toString());
    }

    private boolean parseBoolean(@Nullable String str, boolean z) throws MraidCommandException {
        return str == null ? z : parseBoolean(str);
    }

    private boolean parseBoolean(String str) throws MraidCommandException {
        if ("true".equals(str)) {
            return true;
        }
        if ("false".equals(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid boolean parameter: ");
        sb.append(str);
        throw new MraidCommandException(sb.toString());
    }

    @NonNull
    private URI parseURI(@Nullable String str, URI uri) throws MraidCommandException {
        return str == null ? uri : parseURI(str);
    }

    @NonNull
    private URI parseURI(@Nullable String str) throws MraidCommandException {
        if (str != null) {
            try {
                return new URI(str);
            } catch (URISyntaxException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid URL parameter: ");
                sb.append(str);
                throw new MraidCommandException(sb.toString());
            }
        } else {
            throw new MraidCommandException("Parameter cannot be null");
        }
    }

    /* access modifiers changed from: 0000 */
    public void notifyViewability(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.setIsViewable(");
        sb.append(z);
        sb.append(")");
        injectJavaScript(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public void notifyPlacementType(PlacementType placementType) {
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.setPlacementType(");
        sb.append(JSONObject.quote(placementType.toJavascriptString()));
        sb.append(")");
        injectJavaScript(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public void notifyViewState(ViewState viewState) {
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.setState(");
        sb.append(JSONObject.quote(viewState.toJavascriptString()));
        sb.append(")");
        injectJavaScript(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public void notifySupports(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.setSupports(");
        sb.append(z);
        sb.append(",");
        sb.append(z2);
        sb.append(",");
        sb.append(z3);
        sb.append(",");
        sb.append(z4);
        sb.append(",");
        sb.append(z5);
        sb.append(")");
        injectJavaScript(sb.toString());
    }

    @NonNull
    private String stringifyRect(Rect rect) {
        StringBuilder sb = new StringBuilder();
        sb.append(rect.left);
        sb.append(",");
        sb.append(rect.top);
        sb.append(",");
        sb.append(rect.width());
        sb.append(",");
        sb.append(rect.height());
        return sb.toString();
    }

    @NonNull
    private String stringifySize(Rect rect) {
        StringBuilder sb = new StringBuilder();
        sb.append(rect.width());
        sb.append(",");
        sb.append(rect.height());
        return sb.toString();
    }

    public void notifyScreenMetrics(@NonNull MraidScreenMetrics mraidScreenMetrics) {
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.setScreenSize(");
        sb.append(stringifySize(mraidScreenMetrics.getScreenRectDips()));
        sb.append(");mraidbridge.setMaxSize(");
        sb.append(stringifySize(mraidScreenMetrics.getRootViewRectDips()));
        sb.append(");mraidbridge.setCurrentPosition(");
        sb.append(stringifyRect(mraidScreenMetrics.getCurrentAdRectDips()));
        sb.append(");mraidbridge.setDefaultPosition(");
        sb.append(stringifyRect(mraidScreenMetrics.getDefaultAdRectDips()));
        sb.append(")");
        injectJavaScript(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("mraidbridge.notifySizeChangeEvent(");
        sb2.append(stringifySize(mraidScreenMetrics.getCurrentAdRectDips()));
        sb2.append(")");
        injectJavaScript(sb2.toString());
    }

    /* access modifiers changed from: 0000 */
    public void notifyReady() {
        injectJavaScript("mraidbridge.notifyReadyEvent();");
    }

    /* access modifiers changed from: 0000 */
    public boolean isViewable() {
        MraidWebView mraidWebView = this.mMraidWebView;
        return mraidWebView != null && mraidWebView.isMraidViewable();
    }

    /* access modifiers changed from: 0000 */
    public boolean isAttached() {
        return this.mMraidWebView != null;
    }

    /* access modifiers changed from: 0000 */
    public boolean isLoaded() {
        return this.mHasLoaded;
    }
}
