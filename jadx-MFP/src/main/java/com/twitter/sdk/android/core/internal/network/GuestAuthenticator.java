package com.twitter.sdk.android.core.internal.network;

import com.twitter.sdk.android.core.GuestSession;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Route;

public class GuestAuthenticator implements Authenticator {
    final GuestSessionProvider guestSessionProvider;

    public GuestAuthenticator(GuestSessionProvider guestSessionProvider2) {
        this.guestSessionProvider = guestSessionProvider2;
    }

    public Request authenticate(Route route, Response response) throws IOException {
        return reauth(response);
    }

    /* access modifiers changed from: 0000 */
    public Request reauth(Response response) {
        GuestAuthToken guestAuthToken;
        if (canRetry(response)) {
            GuestSession refreshCurrentSession = this.guestSessionProvider.refreshCurrentSession(getExpiredSession(response));
            if (refreshCurrentSession == null) {
                guestAuthToken = null;
            } else {
                guestAuthToken = (GuestAuthToken) refreshCurrentSession.getAuthToken();
            }
            if (guestAuthToken != null) {
                return resign(response.request(), guestAuthToken);
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public GuestSession getExpiredSession(Response response) {
        Headers headers = response.request().headers();
        String str = headers.get("Authorization");
        String str2 = headers.get("x-guest-token");
        if (str == null || str2 == null) {
            return null;
        }
        return new GuestSession(new GuestAuthToken("bearer", str.replace("bearer ", ""), str2));
    }

    /* access modifiers changed from: 0000 */
    public Request resign(Request request, GuestAuthToken guestAuthToken) {
        Builder newBuilder = request.newBuilder();
        GuestAuthInterceptor.addAuthHeaders(newBuilder, guestAuthToken);
        return newBuilder.build();
    }

    /* access modifiers changed from: 0000 */
    public boolean canRetry(Response response) {
        int i = 1;
        while (true) {
            response = response.priorResponse();
            if (response == null) {
                break;
            }
            i++;
        }
        return i < 2;
    }
}
