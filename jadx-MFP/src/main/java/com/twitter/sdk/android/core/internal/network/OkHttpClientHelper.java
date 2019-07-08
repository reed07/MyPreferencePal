package com.twitter.sdk.android.core.internal.network;

import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

public class OkHttpClientHelper {
    public static OkHttpClient getOkHttpClient(GuestSessionProvider guestSessionProvider) {
        return addGuestAuth(new Builder(), guestSessionProvider).build();
    }

    public static OkHttpClient getOkHttpClient(Session<? extends TwitterAuthToken> session, TwitterAuthConfig twitterAuthConfig) {
        if (session != null) {
            return addSessionAuth(new Builder(), session, twitterAuthConfig).build();
        }
        throw new IllegalArgumentException("Session must not be null.");
    }

    static Builder addGuestAuth(Builder builder, GuestSessionProvider guestSessionProvider) {
        return builder.certificatePinner(getCertificatePinner()).authenticator(new GuestAuthenticator(guestSessionProvider)).addInterceptor(new GuestAuthInterceptor(guestSessionProvider)).addNetworkInterceptor(new GuestAuthNetworkInterceptor());
    }

    static Builder addSessionAuth(Builder builder, Session<? extends TwitterAuthToken> session, TwitterAuthConfig twitterAuthConfig) {
        return builder.certificatePinner(getCertificatePinner()).addInterceptor(new OAuth1aInterceptor(session, twitterAuthConfig));
    }

    public static CertificatePinner getCertificatePinner() {
        return new CertificatePinner.Builder().add("*.twitter.com", "sha1/I0PRSKJViZuUfUYaeX7ATP7RcLc=").add("*.twitter.com", "sha1/VRmyeKyygdftp6vBg5nDu2kEJLU=").add("*.twitter.com", "sha1/Eje6RRfurSkm/cHN/r7t8t7ZFFw=").add("*.twitter.com", "sha1/Wr7Fddyu87COJxlD/H8lDD32YeM=").add("*.twitter.com", "sha1/GiG0lStik84Ys2XsnA6TTLOB5tQ=").add("*.twitter.com", "sha1/IvGeLsbqzPxdI0b0wuj2xVTdXgc=").add("*.twitter.com", "sha1/7WYxNdMb1OymFMQp4xkGn5TBJlA=").add("*.twitter.com", "sha1/sYEIGhmkwJQf+uiVKMEkyZs0rMc=").add("*.twitter.com", "sha1/PANDaGiVHPNpKri0Jtq6j+ki5b0=").add("*.twitter.com", "sha1/u8I+KQuzKHcdrT6iTb30I70GsD0=").add("*.twitter.com", "sha1/wHqYaI2J+6sFZAwRfap9ZbjKzE4=").add("*.twitter.com", "sha1/cTg28gIxU0crbrplRqkQFVggBQk=").add("*.twitter.com", "sha1/sBmJ5+/7Sq/LFI9YRjl2IkFQ4bo=").add("*.twitter.com", "sha1/vb6nG6txV/nkddlU0rcngBqCJoI=").add("*.twitter.com", "sha1/nKmNAK90Dd2BgNITRaWLjy6UONY=").add("*.twitter.com", "sha1/h+hbY1PGI6MSjLD/u/VR/lmADiI=").add("*.twitter.com", "sha1/Xk9ThoXdT57KX9wNRW99UbHcm3s=").add("*.twitter.com", "sha1/1S4TwavjSdrotJWU73w4Q2BkZr0=").add("*.twitter.com", "sha1/gzF+YoVCU9bXeDGQ7JGQVumRueM=").add("*.twitter.com", "sha1/aDMOYTWFIVkpg6PI0tLhQG56s8E=").add("*.twitter.com", "sha1/Vv7zwhR9TtOIN/29MFI4cgHld40=").build();
    }
}
