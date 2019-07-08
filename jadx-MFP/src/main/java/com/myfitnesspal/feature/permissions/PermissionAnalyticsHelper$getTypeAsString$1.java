package com.myfitnesspal.feature.permissions;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: PermissionAnalyticsHelper.kt */
final class PermissionAnalyticsHelper$getTypeAsString$1 extends Lambda implements Function1<String, CharSequence> {
    public static final PermissionAnalyticsHelper$getTypeAsString$1 INSTANCE = new PermissionAnalyticsHelper$getTypeAsString$1();

    PermissionAnalyticsHelper$getTypeAsString$1() {
        super(1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        if (r2.equals("android.permission.ACCESS_COARSE_LOCATION") != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (r2.equals("android.permission.ACCESS_FINE_LOCATION") != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return "location";
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.CharSequence invoke(@org.jetbrains.annotations.NotNull java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            int r0 = r2.hashCode()
            switch(r0) {
                case -1888586689: goto L_0x003d;
                case -63024214: goto L_0x0034;
                case 463403621: goto L_0x0027;
                case 1365911975: goto L_0x001a;
                case 1977429404: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x004a
        L_0x000d:
            java.lang.String r0 = "android.permission.READ_CONTACTS"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004a
            java.lang.String r2 = "contacts"
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            goto L_0x004e
        L_0x001a:
            java.lang.String r0 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004a
            java.lang.String r2 = "storage"
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            goto L_0x004e
        L_0x0027:
            java.lang.String r0 = "android.permission.CAMERA"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004a
            java.lang.String r2 = "camera"
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            goto L_0x004e
        L_0x0034:
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004a
            goto L_0x0045
        L_0x003d:
            java.lang.String r0 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004a
        L_0x0045:
            java.lang.String r2 = "location"
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            goto L_0x004e
        L_0x004a:
            java.lang.String r2 = ""
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L_0x004e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper$getTypeAsString$1.invoke(java.lang.String):java.lang.CharSequence");
    }
}
