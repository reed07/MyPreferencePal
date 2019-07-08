package com.mopub.mraid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.common.CloseableLayout.OnCloseListener;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.common.util.Views;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import com.mopub.mobileads.WebViewCacheService;
import com.mopub.mobileads.WebViewCacheService.Config;
import com.mopub.mobileads.util.WebViews;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import com.mopub.mraid.MraidBridge.MraidWebView;
import java.lang.ref.WeakReference;
import java.net.URI;

public class MraidController {
    private final AdReport mAdReport;
    private boolean mAllowOrientationChange;
    @NonNull
    private final CloseableLayout mCloseableAdContainer;
    /* access modifiers changed from: private */
    @NonNull
    public final Context mContext;
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    /* access modifiers changed from: private */
    @NonNull
    public final FrameLayout mDefaultAdContainer;
    private MraidOrientation mForceOrientation;
    private boolean mIsPaused;
    /* access modifiers changed from: private */
    @NonNull
    public final MraidBridge mMraidBridge;
    private final MraidBridgeListener mMraidBridgeListener;
    /* access modifiers changed from: private */
    @Nullable
    public MraidListener mMraidListener;
    /* access modifiers changed from: private */
    public final MraidNativeCommandHandler mMraidNativeCommandHandler;
    @Nullable
    private MraidWebView mMraidWebView;
    @Nullable
    private UseCustomCloseListener mOnCloseButtonListener;
    @NonNull
    private OrientationBroadcastReceiver mOrientationBroadcastReceiver;
    @Nullable
    private Integer mOriginalActivityOrientation;
    /* access modifiers changed from: private */
    @NonNull
    public final PlacementType mPlacementType;
    @Nullable
    private ViewGroup mRootView;
    /* access modifiers changed from: private */
    @NonNull
    public final MraidScreenMetrics mScreenMetrics;
    @NonNull
    private final ScreenMetricsWaiter mScreenMetricsWaiter;
    /* access modifiers changed from: private */
    @NonNull
    public final MraidBridge mTwoPartBridge;
    private final MraidBridgeListener mTwoPartBridgeListener;
    @Nullable
    private MraidWebView mTwoPartWebView;
    /* access modifiers changed from: private */
    @NonNull
    public ViewState mViewState;
    @NonNull
    private final WeakReference<Activity> mWeakActivity;

    public interface MraidListener {
        void onClose();

        void onExpand();

        void onFailedToLoad();

        void onLoaded(View view);

        void onOpen();
    }

    public interface MraidWebViewCacheListener {
        void onReady(MraidWebView mraidWebView, ExternalViewabilitySessionManager externalViewabilitySessionManager);
    }

    @VisibleForTesting
    class OrientationBroadcastReceiver extends BroadcastReceiver {
        @Nullable
        private Context mContext;
        private int mLastRotation = -1;

        OrientationBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (this.mContext != null && "android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction())) {
                int access$1400 = MraidController.this.getDisplayRotation();
                if (access$1400 != this.mLastRotation) {
                    this.mLastRotation = access$1400;
                    MraidController.this.handleOrientationChange(this.mLastRotation);
                }
            }
        }

        public void register(@NonNull Context context) {
            Preconditions.checkNotNull(context);
            this.mContext = context.getApplicationContext();
            Context context2 = this.mContext;
            if (context2 != null) {
                context2.registerReceiver(this, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
            }
        }

        public void unregister() {
            Context context = this.mContext;
            if (context != null) {
                context.unregisterReceiver(this);
                this.mContext = null;
            }
        }
    }

    @VisibleForTesting
    static class ScreenMetricsWaiter {
        @NonNull
        private final Handler mHandler = new Handler();
        @Nullable
        private WaitRequest mLastWaitRequest;

        static class WaitRequest {
            @NonNull
            private final Handler mHandler;
            @Nullable
            private Runnable mSuccessRunnable;
            /* access modifiers changed from: private */
            @NonNull
            public final View[] mViews;
            int mWaitCount;
            private final Runnable mWaitingRunnable;

            private WaitRequest(@NonNull Handler handler, @NonNull View[] viewArr) {
                this.mWaitingRunnable = new Runnable() {
                    public void run() {
                        View[] access$300;
                        for (final View view : WaitRequest.this.mViews) {
                            if (view.getHeight() > 0 || view.getWidth() > 0) {
                                WaitRequest.this.countDown();
                            } else {
                                view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                                    public boolean onPreDraw() {
                                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                                        WaitRequest.this.countDown();
                                        return true;
                                    }
                                });
                            }
                        }
                    }
                };
                this.mHandler = handler;
                this.mViews = viewArr;
            }

            /* access modifiers changed from: private */
            public void countDown() {
                this.mWaitCount--;
                if (this.mWaitCount == 0) {
                    Runnable runnable = this.mSuccessRunnable;
                    if (runnable != null) {
                        runnable.run();
                        this.mSuccessRunnable = null;
                    }
                }
            }

            /* access modifiers changed from: 0000 */
            public void start(@NonNull Runnable runnable) {
                this.mSuccessRunnable = runnable;
                this.mWaitCount = this.mViews.length;
                this.mHandler.post(this.mWaitingRunnable);
            }

            /* access modifiers changed from: 0000 */
            public void cancel() {
                this.mHandler.removeCallbacks(this.mWaitingRunnable);
                this.mSuccessRunnable = null;
            }
        }

        ScreenMetricsWaiter() {
        }

        /* access modifiers changed from: 0000 */
        public WaitRequest waitFor(@NonNull View... viewArr) {
            this.mLastWaitRequest = new WaitRequest(this.mHandler, viewArr);
            return this.mLastWaitRequest;
        }

        /* access modifiers changed from: 0000 */
        public void cancelLastRequest() {
            WaitRequest waitRequest = this.mLastWaitRequest;
            if (waitRequest != null) {
                waitRequest.cancel();
                this.mLastWaitRequest = null;
            }
        }
    }

    public interface UseCustomCloseListener {
        void useCustomCloseChanged(boolean z);
    }

    public MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType) {
        this(context, adReport, placementType, new MraidBridge(adReport, placementType), new MraidBridge(adReport, PlacementType.INTERSTITIAL), new ScreenMetricsWaiter());
    }

    @VisibleForTesting
    MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType, @NonNull MraidBridge mraidBridge, @NonNull MraidBridge mraidBridge2, @NonNull ScreenMetricsWaiter screenMetricsWaiter) {
        this.mViewState = ViewState.LOADING;
        this.mOrientationBroadcastReceiver = new OrientationBroadcastReceiver();
        this.mAllowOrientationChange = true;
        this.mForceOrientation = MraidOrientation.NONE;
        this.mMraidBridgeListener = new MraidBridgeListener() {
            public void onPageLoaded() {
                MraidController.this.handlePageLoad();
            }

            public void onPageFailedToLoad() {
                if (MraidController.this.mMraidListener != null) {
                    MraidController.this.mMraidListener.onFailedToLoad();
                }
            }

            public void onVisibilityChanged(boolean z) {
                if (!MraidController.this.mTwoPartBridge.isAttached()) {
                    MraidController.this.mMraidBridge.notifyViewability(z);
                }
            }

            public boolean onJsAlert(@NonNull String str, @NonNull JsResult jsResult) {
                return MraidController.this.handleJsAlert(str, jsResult);
            }

            public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
                return MraidController.this.handleConsoleMessage(consoleMessage);
            }

            public void onClose() {
                MraidController.this.handleClose();
            }

            public void onResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) throws MraidCommandException {
                MraidController.this.handleResize(i, i2, i3, i4, closePosition, z);
            }

            public void onExpand(@Nullable URI uri, boolean z) throws MraidCommandException {
                MraidController.this.handleExpand(uri, z);
            }

            public void onUseCustomClose(boolean z) {
                MraidController.this.handleCustomClose(z);
            }

            public void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException {
                MraidController.this.handleSetOrientationProperties(z, mraidOrientation);
            }

            public void onOpen(@NonNull URI uri) {
                MraidController.this.handleOpen(uri.toString());
            }

            public void onPlayVideo(@NonNull URI uri) {
                MraidController.this.handleShowVideo(uri.toString());
            }
        };
        this.mTwoPartBridgeListener = new MraidBridgeListener() {
            public void onExpand(@Nullable URI uri, boolean z) {
            }

            public void onPageFailedToLoad() {
            }

            public void onPageLoaded() {
                MraidController.this.handleTwoPartPageLoad();
            }

            public void onVisibilityChanged(boolean z) {
                MraidController.this.mMraidBridge.notifyViewability(z);
                MraidController.this.mTwoPartBridge.notifyViewability(z);
            }

            public boolean onJsAlert(@NonNull String str, @NonNull JsResult jsResult) {
                return MraidController.this.handleJsAlert(str, jsResult);
            }

            public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
                return MraidController.this.handleConsoleMessage(consoleMessage);
            }

            public void onResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) throws MraidCommandException {
                throw new MraidCommandException("Not allowed to resize from an expanded state");
            }

            public void onClose() {
                MraidController.this.handleClose();
            }

            public void onUseCustomClose(boolean z) {
                MraidController.this.handleCustomClose(z);
            }

            public void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException {
                MraidController.this.handleSetOrientationProperties(z, mraidOrientation);
            }

            public void onOpen(URI uri) {
                MraidController.this.handleOpen(uri.toString());
            }

            public void onPlayVideo(@NonNull URI uri) {
                MraidController.this.handleShowVideo(uri.toString());
            }
        };
        this.mContext = context.getApplicationContext();
        Preconditions.checkNotNull(this.mContext);
        this.mAdReport = adReport;
        if (context instanceof Activity) {
            this.mWeakActivity = new WeakReference<>((Activity) context);
        } else {
            this.mWeakActivity = new WeakReference<>(null);
        }
        this.mPlacementType = placementType;
        this.mMraidBridge = mraidBridge;
        this.mTwoPartBridge = mraidBridge2;
        this.mScreenMetricsWaiter = screenMetricsWaiter;
        this.mViewState = ViewState.LOADING;
        this.mScreenMetrics = new MraidScreenMetrics(this.mContext, this.mContext.getResources().getDisplayMetrics().density);
        this.mDefaultAdContainer = new FrameLayout(this.mContext);
        this.mCloseableAdContainer = new CloseableLayout(this.mContext);
        this.mCloseableAdContainer.setOnCloseListener(new OnCloseListener() {
            public void onClose() {
                MraidController.this.handleClose();
            }
        });
        View view = new View(this.mContext);
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.mCloseableAdContainer.addView(view, new LayoutParams(-1, -1));
        this.mOrientationBroadcastReceiver.register(this.mContext);
        this.mMraidBridge.setMraidBridgeListener(this.mMraidBridgeListener);
        this.mTwoPartBridge.setMraidBridgeListener(this.mTwoPartBridgeListener);
        this.mMraidNativeCommandHandler = new MraidNativeCommandHandler();
    }

    public void setMraidListener(@Nullable MraidListener mraidListener) {
        this.mMraidListener = mraidListener;
    }

    public void setUseCustomCloseListener(@Nullable UseCustomCloseListener useCustomCloseListener) {
        this.mOnCloseButtonListener = useCustomCloseListener;
    }

    public void setDebugListener(@Nullable MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
    }

    public void fillContent(@Nullable Long l, @NonNull String str, @Nullable MraidWebViewCacheListener mraidWebViewCacheListener) {
        Preconditions.checkNotNull(str, "htmlData cannot be null");
        boolean hydrateMraidWebView = hydrateMraidWebView(l, mraidWebViewCacheListener);
        NoThrow.checkNotNull(this.mMraidWebView, "mMraidWebView cannot be null");
        this.mMraidBridge.attachView(this.mMraidWebView);
        this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
        if (hydrateMraidWebView) {
            handlePageLoad();
        } else {
            this.mMraidBridge.setContentHtml(str);
        }
    }

    private boolean hydrateMraidWebView(@Nullable Long l, @Nullable MraidWebViewCacheListener mraidWebViewCacheListener) {
        if (l != null) {
            Config popWebViewConfig = WebViewCacheService.popWebViewConfig(l);
            if (popWebViewConfig != null && (popWebViewConfig.getWebView() instanceof MraidWebView)) {
                this.mMraidWebView = (MraidWebView) popWebViewConfig.getWebView();
                this.mMraidWebView.enablePlugins(true);
                if (mraidWebViewCacheListener != null) {
                    mraidWebViewCacheListener.onReady(this.mMraidWebView, popWebViewConfig.getViewabilityManager());
                }
                return true;
            }
        }
        MoPubLog.d("WebView cache miss. Creating a new MraidWebView.");
        this.mMraidWebView = new MraidWebView(this.mContext);
        if (mraidWebViewCacheListener != null) {
            mraidWebViewCacheListener.onReady(this.mMraidWebView, null);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public int getDisplayRotation() {
        return ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean handleConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        MraidWebViewDebugListener mraidWebViewDebugListener = this.mDebugListener;
        if (mraidWebViewDebugListener != null) {
            return mraidWebViewDebugListener.onConsoleMessage(consoleMessage);
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean handleJsAlert(@NonNull String str, @NonNull JsResult jsResult) {
        MraidWebViewDebugListener mraidWebViewDebugListener = this.mDebugListener;
        if (mraidWebViewDebugListener != null) {
            return mraidWebViewDebugListener.onJsAlert(str, jsResult);
        }
        jsResult.confirm();
        return true;
    }

    @Nullable
    public MraidWebView getCurrentWebView() {
        return this.mTwoPartBridge.isAttached() ? this.mTwoPartWebView : this.mMraidWebView;
    }

    /* access modifiers changed from: private */
    public boolean isInlineVideoAvailable() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || getCurrentWebView() == null) {
            return false;
        }
        return this.mMraidNativeCommandHandler.isInlineVideoAvailable(activity, getCurrentWebView());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void handlePageLoad() {
        setViewState(ViewState.DEFAULT, new Runnable() {
            public void run() {
                MraidController.this.mMraidBridge.notifySupports(MraidController.this.mMraidNativeCommandHandler.isSmsAvailable(MraidController.this.mContext), MraidController.this.mMraidNativeCommandHandler.isTelAvailable(MraidController.this.mContext), MraidNativeCommandHandler.isCalendarAvailable(MraidController.this.mContext), MraidNativeCommandHandler.isStorePictureSupported(MraidController.this.mContext), MraidController.this.isInlineVideoAvailable());
                MraidController.this.mMraidBridge.notifyPlacementType(MraidController.this.mPlacementType);
                MraidController.this.mMraidBridge.notifyViewability(MraidController.this.mMraidBridge.isViewable());
                MraidController.this.mMraidBridge.notifyReady();
            }
        });
        MraidListener mraidListener = this.mMraidListener;
        if (mraidListener != null) {
            mraidListener.onLoaded(this.mDefaultAdContainer);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void handleTwoPartPageLoad() {
        updateScreenMetricsAsync(new Runnable() {
            public void run() {
                MraidBridge access$100 = MraidController.this.mTwoPartBridge;
                boolean isSmsAvailable = MraidController.this.mMraidNativeCommandHandler.isSmsAvailable(MraidController.this.mContext);
                boolean isTelAvailable = MraidController.this.mMraidNativeCommandHandler.isTelAvailable(MraidController.this.mContext);
                MraidController.this.mMraidNativeCommandHandler;
                boolean isCalendarAvailable = MraidNativeCommandHandler.isCalendarAvailable(MraidController.this.mContext);
                MraidController.this.mMraidNativeCommandHandler;
                access$100.notifySupports(isSmsAvailable, isTelAvailable, isCalendarAvailable, MraidNativeCommandHandler.isStorePictureSupported(MraidController.this.mContext), MraidController.this.isInlineVideoAvailable());
                MraidController.this.mTwoPartBridge.notifyViewState(MraidController.this.mViewState);
                MraidController.this.mTwoPartBridge.notifyPlacementType(MraidController.this.mPlacementType);
                MraidController.this.mTwoPartBridge.notifyViewability(MraidController.this.mTwoPartBridge.isViewable());
                MraidController.this.mTwoPartBridge.notifyReady();
            }
        });
    }

    private void updateScreenMetricsAsync(@Nullable final Runnable runnable) {
        this.mScreenMetricsWaiter.cancelLastRequest();
        final MraidWebView currentWebView = getCurrentWebView();
        if (currentWebView != null) {
            this.mScreenMetricsWaiter.waitFor(this.mDefaultAdContainer, currentWebView).start(new Runnable() {
                public void run() {
                    DisplayMetrics displayMetrics = MraidController.this.mContext.getResources().getDisplayMetrics();
                    MraidController.this.mScreenMetrics.setScreenSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
                    int[] iArr = new int[2];
                    ViewGroup access$1200 = MraidController.this.getRootView();
                    access$1200.getLocationOnScreen(iArr);
                    MraidController.this.mScreenMetrics.setRootViewPosition(iArr[0], iArr[1], access$1200.getWidth(), access$1200.getHeight());
                    MraidController.this.mDefaultAdContainer.getLocationOnScreen(iArr);
                    MraidController.this.mScreenMetrics.setDefaultAdPosition(iArr[0], iArr[1], MraidController.this.mDefaultAdContainer.getWidth(), MraidController.this.mDefaultAdContainer.getHeight());
                    currentWebView.getLocationOnScreen(iArr);
                    MraidController.this.mScreenMetrics.setCurrentAdPosition(iArr[0], iArr[1], currentWebView.getWidth(), currentWebView.getHeight());
                    MraidController.this.mMraidBridge.notifyScreenMetrics(MraidController.this.mScreenMetrics);
                    if (MraidController.this.mTwoPartBridge.isAttached()) {
                        MraidController.this.mTwoPartBridge.notifyScreenMetrics(MraidController.this.mScreenMetrics);
                    }
                    Runnable runnable = runnable;
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleOrientationChange(int i) {
        updateScreenMetricsAsync(null);
    }

    public void pause(boolean z) {
        this.mIsPaused = true;
        MraidWebView mraidWebView = this.mMraidWebView;
        if (mraidWebView != null) {
            WebViews.onPause(mraidWebView, z);
        }
        MraidWebView mraidWebView2 = this.mTwoPartWebView;
        if (mraidWebView2 != null) {
            WebViews.onPause(mraidWebView2, z);
        }
    }

    public void resume() {
        this.mIsPaused = false;
        MraidWebView mraidWebView = this.mMraidWebView;
        if (mraidWebView != null) {
            mraidWebView.onResume();
        }
        MraidWebView mraidWebView2 = this.mTwoPartWebView;
        if (mraidWebView2 != null) {
            mraidWebView2.onResume();
        }
    }

    public void destroy() {
        this.mScreenMetricsWaiter.cancelLastRequest();
        try {
            this.mOrientationBroadcastReceiver.unregister();
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                throw e;
            }
        }
        if (!this.mIsPaused) {
            pause(true);
        }
        Views.removeFromParent(this.mCloseableAdContainer);
        detachMraidWebView();
        detachTwoParWebView();
    }

    private void detachMraidWebView() {
        this.mMraidBridge.detach();
        this.mMraidWebView = null;
    }

    private void detachTwoParWebView() {
        this.mTwoPartBridge.detach();
        this.mTwoPartWebView = null;
    }

    private void setViewState(@NonNull ViewState viewState) {
        setViewState(viewState, null);
    }

    private void setViewState(@NonNull ViewState viewState, @Nullable Runnable runnable) {
        StringBuilder sb = new StringBuilder();
        sb.append("MRAID state set to ");
        sb.append(viewState);
        MoPubLog.d(sb.toString());
        ViewState viewState2 = this.mViewState;
        this.mViewState = viewState;
        this.mMraidBridge.notifyViewState(viewState);
        if (this.mTwoPartBridge.isLoaded()) {
            this.mTwoPartBridge.notifyViewState(viewState);
        }
        if (this.mMraidListener != null) {
            if (viewState == ViewState.EXPANDED) {
                this.mMraidListener.onExpand();
            } else if (viewState2 == ViewState.EXPANDED && viewState == ViewState.DEFAULT) {
                this.mMraidListener.onClose();
            } else if (viewState == ViewState.HIDDEN) {
                this.mMraidListener.onClose();
            }
        }
        updateScreenMetricsAsync(runnable);
    }

    /* access modifiers changed from: 0000 */
    public int clampInt(int i, int i2, int i3) {
        return Math.max(i, Math.min(i2, i3));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void handleResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) throws MraidCommandException {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to resize after the WebView is destroyed");
        } else if (this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED) {
                throw new MraidCommandException("Not allowed to resize from an already expanded ad");
            } else if (this.mPlacementType != PlacementType.INTERSTITIAL) {
                int dipsToIntPixels = Dips.dipsToIntPixels((float) i, this.mContext);
                int dipsToIntPixels2 = Dips.dipsToIntPixels((float) i2, this.mContext);
                int dipsToIntPixels3 = Dips.dipsToIntPixels((float) i3, this.mContext);
                int i5 = this.mScreenMetrics.getDefaultAdRect().left + dipsToIntPixels3;
                int dipsToIntPixels4 = this.mScreenMetrics.getDefaultAdRect().top + Dips.dipsToIntPixels((float) i4, this.mContext);
                Rect rect = new Rect(i5, dipsToIntPixels4, dipsToIntPixels + i5, dipsToIntPixels4 + dipsToIntPixels2);
                if (!z) {
                    Rect rootViewRect = this.mScreenMetrics.getRootViewRect();
                    if (rect.width() > rootViewRect.width() || rect.height() > rootViewRect.height()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("resizeProperties specified a size (");
                        sb.append(i);
                        sb.append(", ");
                        sb.append(i2);
                        sb.append(") and offset (");
                        sb.append(i3);
                        sb.append(", ");
                        sb.append(i4);
                        sb.append(") that doesn't allow the ad to appear within the max allowed size (");
                        sb.append(this.mScreenMetrics.getRootViewRectDips().width());
                        sb.append(", ");
                        sb.append(this.mScreenMetrics.getRootViewRectDips().height());
                        sb.append(")");
                        throw new MraidCommandException(sb.toString());
                    }
                    rect.offsetTo(clampInt(rootViewRect.left, rect.left, rootViewRect.right - rect.width()), clampInt(rootViewRect.top, rect.top, rootViewRect.bottom - rect.height()));
                }
                Rect rect2 = new Rect();
                this.mCloseableAdContainer.applyCloseRegionBounds(closePosition, rect, rect2);
                if (!this.mScreenMetrics.getRootViewRect().contains(rect2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("resizeProperties specified a size (");
                    sb2.append(i);
                    sb2.append(", ");
                    sb2.append(i2);
                    sb2.append(") and offset (");
                    sb2.append(i3);
                    sb2.append(", ");
                    sb2.append(i4);
                    sb2.append(") that doesn't allow the close region to appear within the max allowed size (");
                    sb2.append(this.mScreenMetrics.getRootViewRectDips().width());
                    sb2.append(", ");
                    sb2.append(this.mScreenMetrics.getRootViewRectDips().height());
                    sb2.append(")");
                    throw new MraidCommandException(sb2.toString());
                } else if (rect.contains(rect2)) {
                    this.mCloseableAdContainer.setCloseVisible(false);
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    LayoutParams layoutParams = new LayoutParams(rect.width(), rect.height());
                    layoutParams.leftMargin = rect.left - this.mScreenMetrics.getRootViewRect().left;
                    layoutParams.topMargin = rect.top - this.mScreenMetrics.getRootViewRect().top;
                    if (this.mViewState == ViewState.DEFAULT) {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                        getAndMemoizeRootView().addView(this.mCloseableAdContainer, layoutParams);
                    } else if (this.mViewState == ViewState.RESIZED) {
                        this.mCloseableAdContainer.setLayoutParams(layoutParams);
                    }
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    setViewState(ViewState.RESIZED);
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("resizeProperties specified a size (");
                    sb3.append(i);
                    sb3.append(", ");
                    sb3.append(dipsToIntPixels2);
                    sb3.append(") and offset (");
                    sb3.append(i3);
                    sb3.append(", ");
                    sb3.append(i4);
                    sb3.append(") that don't allow the close region to appear within the resized ad.");
                    throw new MraidCommandException(sb3.toString());
                }
            } else {
                throw new MraidCommandException("Not allowed to resize from an interstitial ad");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleExpand(@Nullable URI uri, boolean z) throws MraidCommandException {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to expand after the WebView is destroyed");
        } else if (this.mPlacementType != PlacementType.INTERSTITIAL) {
            if (this.mViewState == ViewState.DEFAULT || this.mViewState == ViewState.RESIZED) {
                applyOrientation();
                boolean z2 = uri != null;
                if (z2) {
                    this.mTwoPartWebView = new MraidWebView(this.mContext);
                    this.mTwoPartBridge.attachView(this.mTwoPartWebView);
                    this.mTwoPartBridge.setContentUrl(uri.toString());
                }
                LayoutParams layoutParams = new LayoutParams(-1, -1);
                if (this.mViewState == ViewState.DEFAULT) {
                    if (z2) {
                        this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                    } else {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, layoutParams);
                    }
                    getAndMemoizeRootView().addView(this.mCloseableAdContainer, new LayoutParams(-1, -1));
                } else if (this.mViewState == ViewState.RESIZED && z2) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, layoutParams);
                    this.mDefaultAdContainer.setVisibility(4);
                    this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                }
                this.mCloseableAdContainer.setLayoutParams(layoutParams);
                handleCustomClose(z);
                setViewState(ViewState.EXPANDED);
            }
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void handleClose() {
        if (this.mMraidWebView != null && this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                unApplyOrientation();
            }
            if (this.mViewState == ViewState.RESIZED || this.mViewState == ViewState.EXPANDED) {
                if (this.mTwoPartBridge.isAttached()) {
                    MraidWebView mraidWebView = this.mTwoPartWebView;
                    if (mraidWebView != null) {
                        detachTwoParWebView();
                        this.mCloseableAdContainer.removeView(mraidWebView);
                        Views.removeFromParent(this.mCloseableAdContainer);
                        setViewState(ViewState.DEFAULT);
                    }
                }
                this.mCloseableAdContainer.removeView(this.mMraidWebView);
                this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                this.mDefaultAdContainer.setVisibility(0);
                Views.removeFromParent(this.mCloseableAdContainer);
                setViewState(ViewState.DEFAULT);
            } else if (this.mViewState == ViewState.DEFAULT) {
                this.mDefaultAdContainer.setVisibility(4);
                setViewState(ViewState.HIDDEN);
            }
        }
    }

    /* access modifiers changed from: private */
    @NonNull
    public ViewGroup getRootView() {
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            return viewGroup;
        }
        View topmostView = Views.getTopmostView((Context) this.mWeakActivity.get(), this.mDefaultAdContainer);
        return topmostView instanceof ViewGroup ? (ViewGroup) topmostView : this.mDefaultAdContainer;
    }

    @NonNull
    private ViewGroup getAndMemoizeRootView() {
        if (this.mRootView == null) {
            this.mRootView = getRootView();
        }
        return this.mRootView;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void handleShowVideo(@NonNull String str) {
        MraidVideoPlayerActivity.startMraid(this.mContext, str);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void lockOrientation(int i) throws MraidCommandException {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || !shouldAllowForceOrientation(this.mForceOrientation)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attempted to lock orientation to unsupported value: ");
            sb.append(this.mForceOrientation.name());
            throw new MraidCommandException(sb.toString());
        }
        if (this.mOriginalActivityOrientation == null) {
            this.mOriginalActivityOrientation = Integer.valueOf(activity.getRequestedOrientation());
        }
        activity.setRequestedOrientation(i);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void applyOrientation() throws MraidCommandException {
        if (this.mForceOrientation != MraidOrientation.NONE) {
            lockOrientation(this.mForceOrientation.getActivityInfoOrientation());
        } else if (this.mAllowOrientationChange) {
            unApplyOrientation();
        } else {
            Activity activity = (Activity) this.mWeakActivity.get();
            if (activity != null) {
                lockOrientation(DeviceUtils.getScreenOrientation(activity));
                return;
            }
            throw new MraidCommandException("Unable to set MRAID expand orientation to 'none'; expected passed in Activity Context.");
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void unApplyOrientation() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity != null) {
            Integer num = this.mOriginalActivityOrientation;
            if (num != null) {
                activity.setRequestedOrientation(num.intValue());
            }
        }
        this.mOriginalActivityOrientation = null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean shouldAllowForceOrientation(MraidOrientation mraidOrientation) {
        boolean z = true;
        if (mraidOrientation == MraidOrientation.NONE) {
            return true;
        }
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int i = activityInfo.screenOrientation;
            if (i != -1) {
                if (i != mraidOrientation.getActivityInfoOrientation()) {
                    z = false;
                }
                return z;
            }
            if (!Utils.bitMaskContainsFlag(activityInfo.configChanges, 128) || !Utils.bitMaskContainsFlag(activityInfo.configChanges, 1024)) {
                z = false;
            }
            return z;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void handleCustomClose(boolean z) {
        if (z != (!this.mCloseableAdContainer.isCloseVisible())) {
            this.mCloseableAdContainer.setCloseVisible(!z);
            UseCustomCloseListener useCustomCloseListener = this.mOnCloseButtonListener;
            if (useCustomCloseListener != null) {
                useCustomCloseListener.useCustomCloseChanged(z);
            }
        }
    }

    @NonNull
    public FrameLayout getAdContainer() {
        return this.mDefaultAdContainer;
    }

    public void loadJavascript(@NonNull String str) {
        this.mMraidBridge.injectJavaScript(str);
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public WeakReference<Activity> getWeakActivity() {
        return this.mWeakActivity;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void handleSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) throws MraidCommandException {
        if (shouldAllowForceOrientation(mraidOrientation)) {
            this.mAllowOrientationChange = z;
            this.mForceOrientation = mraidOrientation;
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                applyOrientation();
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to force orientation to ");
        sb.append(mraidOrientation);
        throw new MraidCommandException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void handleOpen(@NonNull String str) {
        MraidListener mraidListener = this.mMraidListener;
        if (mraidListener != null) {
            mraidListener.onOpen();
        }
        Builder builder = new Builder();
        AdReport adReport = this.mAdReport;
        if (adReport != null) {
            builder.withDspCreativeId(adReport.getDspCreativeId());
        }
        builder.withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).build().handleUrl(this.mContext, str);
    }
}
