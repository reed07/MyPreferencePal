package com.mopub.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.BuildConfig;
import com.mopub.common.MoPub.BrowserAgent;
import com.mopub.common.UrlHandler.MoPubSchemeListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import com.mopub.network.TrackingRequest;
import com.myfitnesspal.shared.constants.Constants;
import java.net.URISyntaxException;
import java.util.List;

public enum UrlAction {
    HANDLE_MOPUB_SCHEME(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return BuildConfig.SDK_NAME.equalsIgnoreCase(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            String host = uri.getHost();
            MoPubSchemeListener moPubSchemeListener = urlHandler.getMoPubSchemeListener();
            if ("finishLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFinishLoad();
            } else if ("close".equalsIgnoreCase(host)) {
                moPubSchemeListener.onClose();
            } else if ("failLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFailLoad();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not handle MoPub Scheme url: ");
                sb.append(uri);
                throw new IntentNotResolvableException(sb.toString());
            }
        }
    },
    IGNORE_ABOUT_SCHEME(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "about".equalsIgnoreCase(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            MoPubLog.d("Link to about page ignored.");
        }
    },
    HANDLE_PHONE_SCHEME(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            return "tel".equalsIgnoreCase(scheme) || "voicemail".equalsIgnoreCase(scheme) || "sms".equalsIgnoreCase(scheme) || "mailto".equalsIgnoreCase(scheme) || "geo".equalsIgnoreCase(scheme) || "google.streetview".equalsIgnoreCase(scheme);
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not handle intent with URI: ");
            sb.append(uri);
            sb.append("\n\tIs this intent supported on your phone?");
            Intents.launchActionViewIntent(context, uri, sb.toString());
        }
    },
    OPEN_NATIVE_BROWSER(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            if (!Constants.HTTP.equalsIgnoreCase(scheme) && !Constants.HTTPS.equalsIgnoreCase(scheme)) {
                return "mopubnativebrowser".equalsIgnoreCase(scheme);
            }
            return MoPub.getBrowserAgent() == BrowserAgent.NATIVE;
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to load mopub native browser url: ");
            sb.append(uri);
            String sb2 = sb.toString();
            try {
                Intents.launchIntentForUserClick(context, Intents.intentForNativeBrowserScheme(uri), sb2);
            } catch (UrlParseException e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append("\n\t");
                sb3.append(e.getMessage());
                throw new IntentNotResolvableException(sb3.toString());
            }
        }
    },
    OPEN_APP_MARKET(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            String host = uri.getHost();
            return Constants.Uri.GOOGLE_PLAY.equalsIgnoreCase(host) || "market.android.com".equalsIgnoreCase(host) || "market".equalsIgnoreCase(scheme) || uri.toString().toLowerCase().startsWith("play.google.com/") || uri.toString().toLowerCase().startsWith("market.android.com/");
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            Intents.launchApplicationUrl(context, uri);
        }
    },
    OPEN_IN_APP_BROWSER(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            return Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme);
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            if (!urlHandler.shouldSkipShowMoPubBrowser()) {
                Intents.showMoPubBrowserForUrl(context, uri, str);
            }
        }
    },
    HANDLE_SHARE_TWEET(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            Preconditions.checkNotNull(uri);
            return "mopubshare".equalsIgnoreCase(uri.getScheme()) && "tweet".equalsIgnoreCase(uri.getHost());
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(uri);
            StringBuilder sb = new StringBuilder();
            sb.append("Could not handle share tweet intent with URI ");
            sb.append(uri);
            String sb2 = sb.toString();
            try {
                Intents.launchIntentForUserClick(context, Intent.createChooser(Intents.intentForShareTweet(uri), "Share via"), sb2);
            } catch (UrlParseException e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append("\n\t");
                sb3.append(e.getMessage());
                throw new IntentNotResolvableException(sb3.toString());
            }
        }
    },
    FOLLOW_DEEP_LINK_WITH_FALLBACK(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "deeplink+".equalsIgnoreCase(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            if ("navigate".equalsIgnoreCase(uri.getHost())) {
                try {
                    String queryParameter = uri.getQueryParameter("primaryUrl");
                    List queryParameters = uri.getQueryParameters("primaryTrackingUrl");
                    String queryParameter2 = uri.getQueryParameter("fallbackUrl");
                    List queryParameters2 = uri.getQueryParameters("fallbackTrackingUrl");
                    if (queryParameter != null) {
                        Uri parse = Uri.parse(queryParameter);
                        if (!shouldTryHandlingUrl(parse)) {
                            try {
                                Intents.launchApplicationUrl(context, parse);
                                TrackingRequest.makeTrackingHttpRequest((Iterable<String>) queryParameters, context);
                            } catch (IntentNotResolvableException unused) {
                                if (queryParameter2 == null) {
                                    throw new IntentNotResolvableException("Unable to handle 'primaryUrl' for Deeplink+ and 'fallbackUrl' was missing.");
                                } else if (!shouldTryHandlingUrl(Uri.parse(queryParameter2))) {
                                    urlHandler.handleUrl(context, queryParameter2, true, queryParameters2);
                                } else {
                                    throw new IntentNotResolvableException("Deeplink+ URL had another Deeplink+ URL as the 'fallbackUrl'.");
                                }
                            }
                        } else {
                            throw new IntentNotResolvableException("Deeplink+ had another Deeplink+ as the 'primaryUrl'.");
                        }
                    } else {
                        throw new IntentNotResolvableException("Deeplink+ did not have 'primaryUrl' query param.");
                    }
                } catch (UnsupportedOperationException unused2) {
                    throw new IntentNotResolvableException("Deeplink+ URL was not a hierarchical URI.");
                }
            } else {
                throw new IntentNotResolvableException("Deeplink+ URL did not have 'navigate' as the host.");
            }
        }
    },
    FOLLOW_DEEP_LINK(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return !TextUtils.isEmpty(uri.getScheme());
        }

        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
            if (Constants.INTENT_SCHEME.equalsIgnoreCase(uri.getScheme())) {
                try {
                    Intents.launchApplicationIntent(context, Intent.parseUri(uri.toString(), 1));
                } catch (URISyntaxException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Intent uri had invalid syntax: ");
                    sb.append(uri.toString());
                    throw new IntentNotResolvableException(sb.toString());
                }
            } else {
                Intents.launchApplicationUrl(context, uri);
            }
        }
    },
    NOOP(false) {
        /* access modifiers changed from: protected */
        public void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException {
        }

        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return false;
        }
    };
    
    private final boolean mRequiresUserInteraction;

    /* access modifiers changed from: protected */
    public abstract void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) throws IntentNotResolvableException;

    public abstract boolean shouldTryHandlingUrl(@NonNull Uri uri);

    public void handleUrl(UrlHandler urlHandler, @NonNull Context context, @NonNull Uri uri, boolean z, @Nullable String str) throws IntentNotResolvableException {
        StringBuilder sb = new StringBuilder();
        sb.append("Ad event URL: ");
        sb.append(uri);
        MoPubLog.d(sb.toString());
        if (!this.mRequiresUserInteraction || z) {
            performAction(context, uri, urlHandler, str);
            return;
        }
        throw new IntentNotResolvableException("Attempted to handle action without user interaction.");
    }

    private UrlAction(boolean z) {
        this.mRequiresUserInteraction = z;
    }
}
