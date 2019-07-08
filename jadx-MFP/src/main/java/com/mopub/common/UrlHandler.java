package com.mopub.common;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.logging.MoPubLog;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.util.EnumSet;
import java.util.Iterator;

public class UrlHandler {
    /* access modifiers changed from: private */
    public static final ResultActions EMPTY_CLICK_LISTENER = new ResultActions() {
        public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
        }

        public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
        }
    };
    /* access modifiers changed from: private */
    public static final MoPubSchemeListener EMPTY_MOPUB_SCHEME_LISTENER = new MoPubSchemeListener() {
        public void onClose() {
        }

        public void onFailLoad() {
        }

        public void onFinishLoad() {
        }
    };
    private boolean mAlreadySucceeded;
    @Nullable
    private String mDspCreativeId;
    @NonNull
    private MoPubSchemeListener mMoPubSchemeListener;
    @NonNull
    private ResultActions mResultActions;
    private boolean mSkipShowMoPubBrowser;
    @NonNull
    private EnumSet<UrlAction> mSupportedUrlActions;
    /* access modifiers changed from: private */
    public boolean mTaskPending;

    public static class Builder {
        @Nullable
        private String creativeId;
        @NonNull
        private MoPubSchemeListener moPubSchemeListener = UrlHandler.EMPTY_MOPUB_SCHEME_LISTENER;
        @NonNull
        private ResultActions resultActions = UrlHandler.EMPTY_CLICK_LISTENER;
        private boolean skipShowMoPubBrowser = false;
        @NonNull
        private EnumSet<UrlAction> supportedUrlActions = EnumSet.of(UrlAction.NOOP);

        public Builder withSupportedUrlActions(@NonNull UrlAction urlAction, @Nullable UrlAction... urlActionArr) {
            this.supportedUrlActions = EnumSet.of(urlAction, urlActionArr);
            return this;
        }

        public Builder withSupportedUrlActions(@NonNull EnumSet<UrlAction> enumSet) {
            this.supportedUrlActions = EnumSet.copyOf(enumSet);
            return this;
        }

        public Builder withResultActions(@NonNull ResultActions resultActions2) {
            this.resultActions = resultActions2;
            return this;
        }

        public Builder withMoPubSchemeListener(@NonNull MoPubSchemeListener moPubSchemeListener2) {
            this.moPubSchemeListener = moPubSchemeListener2;
            return this;
        }

        public Builder withoutMoPubBrowser() {
            this.skipShowMoPubBrowser = true;
            return this;
        }

        public Builder withDspCreativeId(@Nullable String str) {
            this.creativeId = str;
            return this;
        }

        public UrlHandler build() {
            UrlHandler urlHandler = new UrlHandler(this.supportedUrlActions, this.resultActions, this.moPubSchemeListener, this.skipShowMoPubBrowser, this.creativeId);
            return urlHandler;
        }
    }

    public interface MoPubSchemeListener {
        void onClose();

        void onFailLoad();

        void onFinishLoad();
    }

    public interface ResultActions {
        void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction);

        void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction);
    }

    private UrlHandler(@NonNull EnumSet<UrlAction> enumSet, @NonNull ResultActions resultActions, @NonNull MoPubSchemeListener moPubSchemeListener, boolean z, @Nullable String str) {
        this.mSupportedUrlActions = EnumSet.copyOf(enumSet);
        this.mResultActions = resultActions;
        this.mMoPubSchemeListener = moPubSchemeListener;
        this.mSkipShowMoPubBrowser = z;
        this.mDspCreativeId = str;
        this.mAlreadySucceeded = false;
        this.mTaskPending = false;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public MoPubSchemeListener getMoPubSchemeListener() {
        return this.mMoPubSchemeListener;
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldSkipShowMoPubBrowser() {
        return this.mSkipShowMoPubBrowser;
    }

    public void handleUrl(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        handleUrl(context, str, true);
    }

    public void handleUrl(@NonNull Context context, @NonNull String str, boolean z) {
        Preconditions.checkNotNull(context);
        handleUrl(context, str, z, null);
    }

    public void handleUrl(@NonNull Context context, @NonNull String str, boolean z, @Nullable Iterable<String> iterable) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(str)) {
            failUrlHandling(str, null, "Attempted to handle empty url.", null);
            return;
        }
        final Context context2 = context;
        final boolean z2 = z;
        final Iterable<String> iterable2 = iterable;
        final String str2 = str;
        AnonymousClass3 r0 = new UrlResolutionListener() {
            public void onSuccess(@NonNull String str) {
                UrlHandler.this.mTaskPending = false;
                UrlHandler.this.handleResolvedUrl(context2, str, z2, iterable2);
            }

            public void onFailure(@NonNull String str, @Nullable Throwable th) {
                UrlHandler.this.mTaskPending = false;
                UrlHandler.this.failUrlHandling(str2, null, str, th);
            }
        };
        UrlResolutionTask.getResolvedUrl(str, r0);
        this.mTaskPending = true;
    }

    public boolean handleResolvedUrl(@NonNull Context context, @NonNull String str, boolean z, @Nullable Iterable<String> iterable) {
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            failUrlHandling(str2, null, "Attempted to handle empty url.", null);
            return false;
        }
        UrlAction urlAction = UrlAction.NOOP;
        Uri parse = Uri.parse(str);
        Iterator it = this.mSupportedUrlActions.iterator();
        while (it.hasNext()) {
            UrlAction urlAction2 = (UrlAction) it.next();
            if (urlAction2.shouldTryHandlingUrl(parse)) {
                try {
                    urlAction2.handleUrl(this, context, parse, z, this.mDspCreativeId);
                    if (!this.mAlreadySucceeded && !this.mTaskPending && !UrlAction.IGNORE_ABOUT_SCHEME.equals(urlAction2) && !UrlAction.HANDLE_MOPUB_SCHEME.equals(urlAction2)) {
                        Context context2 = context;
                        try {
                            TrackingRequest.makeTrackingHttpRequest(iterable, context);
                            this.mResultActions.urlHandlingSucceeded(parse.toString(), urlAction2);
                            this.mAlreadySucceeded = true;
                        } catch (IntentNotResolvableException e) {
                            e = e;
                        }
                    }
                    return true;
                } catch (IntentNotResolvableException e2) {
                    e = e2;
                    Context context3 = context;
                    Iterable<String> iterable2 = iterable;
                    MoPubLog.d(e.getMessage(), e);
                    urlAction = urlAction2;
                }
            } else {
                Context context4 = context;
                Iterable<String> iterable3 = iterable;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Link ignored. Unable to handle url: ");
        sb.append(str2);
        failUrlHandling(str2, urlAction, sb.toString(), null);
        return false;
    }

    /* access modifiers changed from: private */
    public void failUrlHandling(@Nullable String str, @Nullable UrlAction urlAction, @NonNull String str2, @Nullable Throwable th) {
        Preconditions.checkNotNull(str2);
        if (urlAction == null) {
            urlAction = UrlAction.NOOP;
        }
        MoPubLog.d(str2, th);
        this.mResultActions.urlHandlingFailed(str, urlAction);
    }
}
