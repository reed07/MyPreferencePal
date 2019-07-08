package com.twitter.sdk.android.tweetcomposer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.network.UrlUtils;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import java.net.URL;

public class TweetComposer {
    @SuppressLint({"StaticFieldLeak"})
    static volatile TweetComposer instance;
    Context context = Twitter.getInstance().getContext(getIdentifier());
    GuestSessionProvider guestSessionProvider = TwitterCore.getInstance().getGuestSessionProvider();
    ScribeClient scribeClient = new ScribeClientImpl(null);
    SessionManager<TwitterSession> sessionManager = TwitterCore.getInstance().getSessionManager();

    public static class Builder {
        private final Context context;
        private Uri imageUri;
        private String text;
        private URL url;

        public Builder(Context context2) {
            if (context2 != null) {
                this.context = context2;
                return;
            }
            throw new IllegalArgumentException("Context must not be null.");
        }

        public Builder text(String str) {
            if (str == null) {
                throw new IllegalArgumentException("text must not be null.");
            } else if (this.text == null) {
                this.text = str;
                return this;
            } else {
                throw new IllegalStateException("text already set.");
            }
        }

        public Intent createIntent() {
            Intent createTwitterIntent = createTwitterIntent();
            return createTwitterIntent == null ? createWebIntent() : createTwitterIntent;
        }

        /* access modifiers changed from: 0000 */
        public Intent createTwitterIntent() {
            Intent intent = new Intent("android.intent.action.SEND");
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.text)) {
                sb.append(this.text);
            }
            if (this.url != null) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(this.url.toString());
            }
            intent.putExtra("android.intent.extra.TEXT", sb.toString());
            intent.setType("text/plain");
            Uri uri = this.imageUri;
            if (uri != null) {
                intent.putExtra("android.intent.extra.STREAM", uri);
                intent.setType("image/jpeg");
            }
            for (ResolveInfo resolveInfo : this.context.getPackageManager().queryIntentActivities(intent, 65536)) {
                if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                    intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                    return intent;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public Intent createWebIntent() {
            URL url2 = this.url;
            return new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://twitter.com/intent/tweet?text=%s&url=%s", new Object[]{UrlUtils.urlEncode(this.text), UrlUtils.urlEncode(url2 == null ? "" : url2.toString())})));
        }

        public void show() {
            this.context.startActivity(createIntent());
        }
    }

    public String getIdentifier() {
        return "com.twitter.sdk.android:tweet-composer";
    }

    public String getVersion() {
        return "3.1.1.9";
    }

    public static TweetComposer getInstance() {
        if (instance == null) {
            synchronized (TweetComposer.class) {
                if (instance == null) {
                    instance = new TweetComposer();
                }
            }
        }
        return instance;
    }

    TweetComposer() {
        setUpScribeClient();
    }

    private void setUpScribeClient() {
        DefaultScribeClient defaultScribeClient = new DefaultScribeClient(this.context, this.sessionManager, this.guestSessionProvider, Twitter.getInstance().getIdManager(), DefaultScribeClient.getScribeConfig("TweetComposer", getVersion()));
        this.scribeClient = new ScribeClientImpl(defaultScribeClient);
    }

    /* access modifiers changed from: 0000 */
    public ScribeClient getScribeClient() {
        return this.scribeClient;
    }
}
