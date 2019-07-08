package com.myfitnesspal.shared.api;

import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MfpApiUtil {
    private static final Pattern NEXT_URL_PATTERN = Pattern.compile(NEXT_URL_REGEX);
    private static final String NEXT_URL_REGEX = ".*<(.*)>;\\s*rel=next.*$";

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.myfitnesspal.shared.api.ApiResponseBase mapException(com.myfitnesspal.shared.api.ApiException r2) {
        /*
            com.myfitnesspal.shared.model.mapper.ApiJsonMapper r0 = new com.myfitnesspal.shared.model.mapper.ApiJsonMapper     // Catch:{ IOException -> 0x001f, Exception -> 0x001a }
            r0.<init>()     // Catch:{ IOException -> 0x001f, Exception -> 0x001a }
            java.lang.Class<com.myfitnesspal.shared.api.ApiResponseBase> r1 = com.myfitnesspal.shared.api.ApiResponseBase.class
            com.myfitnesspal.shared.model.mapper.ApiJsonMapper r0 = r0.withType(r1)     // Catch:{ IOException -> 0x001f, Exception -> 0x001a }
            java.lang.String r1 = r2.getBody()     // Catch:{ IOException -> 0x001f, Exception -> 0x001a }
            java.lang.String r1 = com.uacf.core.util.Strings.toString(r1)     // Catch:{ IOException -> 0x001f, Exception -> 0x001a }
            java.lang.Object r0 = r0.mapFrom(r1)     // Catch:{ IOException -> 0x001f, Exception -> 0x001a }
            com.myfitnesspal.shared.api.ApiResponseBase r0 = (com.myfitnesspal.shared.api.ApiResponseBase) r0     // Catch:{ IOException -> 0x001f, Exception -> 0x001a }
            goto L_0x0024
        L_0x001a:
            r0 = move-exception
            com.uacf.core.util.Ln.e(r0)
            goto L_0x0023
        L_0x001f:
            r0 = move-exception
            com.uacf.core.util.Ln.e(r0)
        L_0x0023:
            r0 = 0
        L_0x0024:
            if (r0 != 0) goto L_0x0045
            com.myfitnesspal.shared.api.ApiResponseBase r0 = new com.myfitnesspal.shared.api.ApiResponseBase
            r0.<init>()
            int r1 = r2.getStatusCode()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r1 = com.uacf.core.util.Strings.toString(r1)
            r0.setError(r1)
            java.lang.String r2 = r2.getBody()
            java.lang.String r2 = com.uacf.core.util.Strings.toString(r2)
            r0.setErrorDescription(r2)
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.api.MfpApiUtil.mapException(com.myfitnesspal.shared.api.ApiException):com.myfitnesspal.shared.api.ApiResponseBase");
    }

    public static String getNextUrlFromLink(String str) {
        if (Strings.notEmpty(str)) {
            for (String trimmed : str.split(",")) {
                Matcher matcher = NEXT_URL_PATTERN.matcher(Strings.trimmed((Object) trimmed));
                if (matcher.matches()) {
                    return matcher.group(1);
                }
            }
        }
        return null;
    }

    public static String getNextUrlFromLink(MfpV2Api mfpV2Api) {
        List<String> list = (List) mfpV2Api.getResponseHeaders().get("link");
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (String nextUrlFromLink : list) {
                String nextUrlFromLink2 = getNextUrlFromLink(nextUrlFromLink);
                if (Strings.notEmpty(nextUrlFromLink2)) {
                    return nextUrlFromLink2;
                }
            }
        }
        return null;
    }
}
