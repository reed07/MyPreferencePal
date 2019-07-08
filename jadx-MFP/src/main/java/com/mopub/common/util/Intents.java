package com.mopub.common.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;
import com.mopub.common.MoPub.BrowserAgent;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.logging.MoPubLog;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Intents {
    private static final Map<String, String> STORE_SCHEME_TO_URL_MAP;

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri) {
        return false;
    }

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri, boolean z) {
        return false;
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("market", "https://play.google.com/store/apps/details?%s");
        hashMap.put("amzn", "http://www.amazon.com/gp/mas/dl/android?%s");
        STORE_SCHEME_TO_URL_MAP = Collections.unmodifiableMap(hashMap);
    }

    private Intents() {
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            throw new IntentNotResolvableException((Throwable) e);
        }
    }

    public static Intent getStartActivityIntent(@NonNull Context context, @NonNull Class cls, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }

    public static boolean deviceCanHandleIntent(@NonNull Context context, @NonNull Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static Intent intentForNativeBrowserScheme(@NonNull Uri uri) throws UrlParseException {
        Preconditions.checkNotNull(uri);
        if (!UrlAction.OPEN_NATIVE_BROWSER.shouldTryHandlingUrl(uri)) {
            String str = "mopubnativebrowser://";
            if (MoPub.getBrowserAgent() == BrowserAgent.NATIVE) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(", http://, or https://");
                str = sb.toString();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("URI does not have ");
            sb2.append(str);
            sb2.append(" scheme.");
            throw new UrlParseException(sb2.toString());
        } else if ("mopubnativebrowser".equalsIgnoreCase(uri.getScheme())) {
            return new Intent("android.intent.action.VIEW", parseMoPubNativeBrowserUri(uri));
        } else if (MoPub.getBrowserAgent() == BrowserAgent.NATIVE) {
            return new Intent("android.intent.action.VIEW", uri);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Invalid URI: ");
            sb3.append(uri.toString());
            throw new UrlParseException(sb3.toString());
        }
    }

    private static Uri parseMoPubNativeBrowserUri(@NonNull Uri uri) throws UrlParseException {
        Preconditions.checkNotNull(uri);
        if ("navigate".equals(uri.getHost())) {
            try {
                String queryParameter = uri.getQueryParameter("url");
                if (queryParameter != null) {
                    return Uri.parse(queryParameter);
                }
                throw new UrlParseException("URL missing 'url' query parameter.");
            } catch (UnsupportedOperationException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not handle url: ");
                sb.append(uri);
                MoPubLog.w(sb.toString());
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        } else {
            throw new UrlParseException("URL missing 'navigate' host parameter.");
        }
    }

    public static Intent intentForShareTweet(@NonNull Uri uri) throws UrlParseException {
        if (UrlAction.HANDLE_SHARE_TWEET.shouldTryHandlingUrl(uri)) {
            try {
                String queryParameter = uri.getQueryParameter("screen_name");
                String queryParameter2 = uri.getQueryParameter("tweet_id");
                if (TextUtils.isEmpty(queryParameter)) {
                    throw new UrlParseException("URL missing non-empty 'screen_name' query parameter.");
                } else if (!TextUtils.isEmpty(queryParameter2)) {
                    String format = String.format("Check out @%s's Tweet: %s", new Object[]{queryParameter, String.format("https://twitter.com/%s/status/%s", new Object[]{queryParameter, queryParameter2})});
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", format);
                    intent.putExtra("android.intent.extra.TEXT", format);
                    return intent;
                } else {
                    throw new UrlParseException("URL missing non-empty 'tweet_id' query parameter.");
                }
            } catch (UnsupportedOperationException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not handle url: ");
                sb.append(uri);
                MoPubLog.w(sb.toString());
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        } else {
            throw new UrlParseException("URL does not have mopubshare://tweet? format.");
        }
    }

    public static void showMoPubBrowserForUrl(@NonNull Context context, @NonNull Uri uri, @Nullable String str) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        StringBuilder sb = new StringBuilder();
        sb.append("Final URI to show in browser: ");
        sb.append(uri);
        MoPubLog.d(sb.toString());
        Bundle bundle = new Bundle();
        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, uri.toString());
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, str);
        }
        Intent startActivityIntent = getStartActivityIntent(context, MoPubBrowser.class, bundle);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Could not show MoPubBrowser for url: ");
        sb2.append(uri);
        sb2.append("\n\tPerhaps you forgot to declare com.mopub.common.MoPubBrowser in your Android manifest file.");
        launchIntentForUserClick(context, startActivityIntent, sb2.toString());
    }

    public static void launchIntentForUserClick(@NonNull Context context, @NonNull Intent intent, @Nullable String str) throws IntentNotResolvableException {
        NoThrow.checkNotNull(context);
        NoThrow.checkNotNull(intent);
        try {
            startActivity(context, intent);
        } catch (IntentNotResolvableException e) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("\n");
            sb.append(e.getMessage());
            throw new IntentNotResolvableException(sb.toString());
        }
    }

    public static void launchApplicationUrl(@NonNull Context context, @NonNull Uri uri) throws IntentNotResolvableException {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        if (deviceCanHandleIntent(context, intent)) {
            launchApplicationIntent(context, intent);
        } else if (!STORE_SCHEME_TO_URL_MAP.containsKey(intent.getScheme()) || intent.getData() == null || TextUtils.isEmpty(intent.getData().getQuery())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not handle application specific action: ");
            sb.append(uri);
            sb.append("\n\tYou may be running in the emulator or another device which does not have the required application.");
            throw new IntentNotResolvableException(sb.toString());
        } else {
            launchApplicationIntent(context, new Intent("android.intent.action.VIEW", Uri.parse(String.format((String) STORE_SCHEME_TO_URL_MAP.get(intent.getScheme()), new Object[]{intent.getData().getQuery()}))));
        }
    }

    public static void launchApplicationIntent(@NonNull Context context, @NonNull Intent intent) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (deviceCanHandleIntent(context, intent)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to open intent: ");
            sb.append(intent);
            String sb2 = sb.toString();
            if (!(context instanceof Activity)) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            launchIntentForUserClick(context, intent, sb2);
            return;
        }
        String stringExtra = intent.getStringExtra("browser_fallback_url");
        if (!TextUtils.isEmpty(stringExtra)) {
            Uri parse = Uri.parse(stringExtra);
            String scheme = parse.getScheme();
            if (Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme)) {
                showMoPubBrowserForUrl(context, parse, null);
            } else {
                launchApplicationUrl(context, parse);
            }
        } else if (!STORE_SCHEME_TO_URL_MAP.containsKey(intent.getScheme())) {
            launchApplicationUrl(context, getPlayStoreUri(intent));
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Device could not handle neither intent nor market url.\nIntent: ");
            sb3.append(intent.toString());
            throw new IntentNotResolvableException(sb3.toString());
        }
    }

    @NonNull
    public static Uri getPlayStoreUri(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(intent.getPackage());
        return Uri.parse(sb.toString());
    }

    public static void launchActionViewIntent(@NonNull Context context, @NonNull Uri uri, @Nullable String str) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        launchIntentForUserClick(context, intent, str);
    }
}
