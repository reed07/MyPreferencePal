package okhttp3.internal.cache;

import com.google.common.net.HttpHeaders;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;

public final class CacheStrategy {
    @Nullable
    public final Response cacheResponse;
    @Nullable
    public final Request networkRequest;

    public static class Factory {
        private int ageSeconds = -1;
        final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        final long nowMillis;
        private long receivedResponseMillis;
        final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long j, Request request2, Response response) {
            this.nowMillis = j;
            this.request = request2;
            this.cacheResponse = response;
            if (response != null) {
                this.sentRequestMillis = response.sentRequestAtMillis();
                this.receivedResponseMillis = response.receivedResponseAtMillis();
                Headers headers = response.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    String name = headers.name(i);
                    String value = headers.value(i);
                    if (HttpHeaders.DATE.equalsIgnoreCase(name)) {
                        this.servedDate = HttpDate.parse(value);
                        this.servedDateString = value;
                    } else if (HttpHeaders.EXPIRES.equalsIgnoreCase(name)) {
                        this.expires = HttpDate.parse(value);
                    } else if (HttpHeaders.LAST_MODIFIED.equalsIgnoreCase(name)) {
                        this.lastModified = HttpDate.parse(value);
                        this.lastModifiedString = value;
                    } else if (HttpHeaders.ETAG.equalsIgnoreCase(name)) {
                        this.etag = value;
                    } else if (HttpHeaders.AGE.equalsIgnoreCase(name)) {
                        this.ageSeconds = okhttp3.internal.http.HttpHeaders.parseSeconds(value, -1);
                    }
                }
            }
        }

        public CacheStrategy get() {
            CacheStrategy candidate = getCandidate();
            return (candidate.networkRequest == null || !this.request.cacheControl().onlyIfCached()) ? candidate : new CacheStrategy(null, null);
        }

        private CacheStrategy getCandidate() {
            String str;
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, null);
            }
            if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, null);
            }
            if (!CacheStrategy.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl cacheControl = this.request.cacheControl();
            if (cacheControl.noCache() || hasConditions(this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl cacheControl2 = this.cacheResponse.cacheControl();
            if (cacheControl2.immutable()) {
                return new CacheStrategy(null, this.cacheResponse);
            }
            long cacheResponseAge = cacheResponseAge();
            long computeFreshnessLifetime = computeFreshnessLifetime();
            if (cacheControl.maxAgeSeconds() != -1) {
                computeFreshnessLifetime = Math.min(computeFreshnessLifetime, TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds()));
            }
            long j = 0;
            long millis = cacheControl.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis((long) cacheControl.minFreshSeconds()) : 0;
            if (!cacheControl2.mustRevalidate() && cacheControl.maxStaleSeconds() != -1) {
                j = TimeUnit.SECONDS.toMillis((long) cacheControl.maxStaleSeconds());
            }
            if (!cacheControl2.noCache()) {
                long j2 = millis + cacheResponseAge;
                if (j2 < j + computeFreshnessLifetime) {
                    Builder newBuilder = this.cacheResponse.newBuilder();
                    if (j2 >= computeFreshnessLifetime) {
                        newBuilder.addHeader(HttpHeaders.WARNING, "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (cacheResponseAge > 86400000 && isFreshnessLifetimeHeuristic()) {
                        newBuilder.addHeader(HttpHeaders.WARNING, "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new CacheStrategy(null, newBuilder.build());
                }
            }
            String str2 = this.etag;
            if (str2 != null) {
                str = HttpHeaders.IF_NONE_MATCH;
            } else if (this.lastModified != null) {
                str = HttpHeaders.IF_MODIFIED_SINCE;
                str2 = this.lastModifiedString;
            } else if (this.servedDate == null) {
                return new CacheStrategy(this.request, null);
            } else {
                str = HttpHeaders.IF_MODIFIED_SINCE;
                str2 = this.servedDateString;
            }
            Headers.Builder newBuilder2 = this.request.headers().newBuilder();
            Internal.instance.addLenient(newBuilder2, str, str2);
            return new CacheStrategy(this.request.newBuilder().headers(newBuilder2.build()).build(), this.cacheResponse);
        }

        private long computeFreshnessLifetime() {
            long j;
            long j2;
            CacheControl cacheControl = this.cacheResponse.cacheControl();
            if (cacheControl.maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds());
            }
            long j3 = 0;
            if (this.expires != null) {
                Date date = this.servedDate;
                if (date != null) {
                    j2 = date.getTime();
                } else {
                    j2 = this.receivedResponseMillis;
                }
                long time = this.expires.getTime() - j2;
                if (time > 0) {
                    j3 = time;
                }
                return j3;
            } else if (this.lastModified == null || this.cacheResponse.request().url().query() != null) {
                return 0;
            } else {
                Date date2 = this.servedDate;
                if (date2 != null) {
                    j = date2.getTime();
                } else {
                    j = this.sentRequestMillis;
                }
                long time2 = j - this.lastModified.getTime();
                if (time2 > 0) {
                    j3 = time2 / 10;
                }
                return j3;
            }
        }

        private long cacheResponseAge() {
            Date date = this.servedDate;
            long j = 0;
            if (date != null) {
                j = Math.max(0, this.receivedResponseMillis - date.getTime());
            }
            if (this.ageSeconds != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.ageSeconds));
            }
            long j2 = this.receivedResponseMillis;
            return j + (j2 - this.sentRequestMillis) + (this.nowMillis - j2);
        }

        private boolean isFreshnessLifetimeHeuristic() {
            return this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }

        private static boolean hasConditions(Request request2) {
            return (request2.header(HttpHeaders.IF_MODIFIED_SINCE) == null && request2.header(HttpHeaders.IF_NONE_MATCH) == null) ? false : true;
        }
    }

    CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (r3.cacheControl().noStore() != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        if (r4.cacheControl().noStore() != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r3.cacheControl().isPrivate() == false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isCacheable(okhttp3.Response r3, okhttp3.Request r4) {
        /*
            int r0 = r3.code()
            r1 = 0
            switch(r0) {
                case 200: goto L_0x0030;
                case 203: goto L_0x0030;
                case 204: goto L_0x0030;
                case 300: goto L_0x0030;
                case 301: goto L_0x0030;
                case 302: goto L_0x0009;
                case 307: goto L_0x0009;
                case 308: goto L_0x0030;
                case 404: goto L_0x0030;
                case 405: goto L_0x0030;
                case 410: goto L_0x0030;
                case 414: goto L_0x0030;
                case 501: goto L_0x0030;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0046
        L_0x0009:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.header(r0)
            if (r0 != 0) goto L_0x0030
            okhttp3.CacheControl r0 = r3.cacheControl()
            int r0 = r0.maxAgeSeconds()
            r2 = -1
            if (r0 != r2) goto L_0x0030
            okhttp3.CacheControl r0 = r3.cacheControl()
            boolean r0 = r0.isPublic()
            if (r0 != 0) goto L_0x0030
            okhttp3.CacheControl r0 = r3.cacheControl()
            boolean r0 = r0.isPrivate()
            if (r0 == 0) goto L_0x0046
        L_0x0030:
            okhttp3.CacheControl r3 = r3.cacheControl()
            boolean r3 = r3.noStore()
            if (r3 != 0) goto L_0x0045
            okhttp3.CacheControl r3 = r4.cacheControl()
            boolean r3 = r3.noStore()
            if (r3 != 0) goto L_0x0045
            r1 = 1
        L_0x0045:
            return r1
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheStrategy.isCacheable(okhttp3.Response, okhttp3.Request):boolean");
    }
}
