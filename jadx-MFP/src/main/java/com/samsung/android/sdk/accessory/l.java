package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class l {
    private static l a;
    private static byte[] b;
    private Context c;

    private l(Context context) {
        if (context != null) {
            this.c = context;
            return;
        }
        StringBuilder sb = new StringBuilder("Invalid context:");
        sb.append(null);
        throw new IllegalArgumentException(sb.toString());
    }

    static synchronized l a(Context context) {
        l lVar;
        synchronized (l.class) {
            if (a == null) {
                b = null;
                a = new l(context);
            }
            lVar = a;
        }
        return lVar;
    }

    private synchronized byte[] a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        StringBuffer stringBuffer;
        String trim;
        stringBuffer = new StringBuffer();
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 0) {
                Log.v("[SA_SDK]ServiceXmlReader", "Start document");
            } else {
                if (eventType == 2) {
                    stringBuffer.append(String.format("<%s ", new Object[]{xmlPullParser.getName().trim()}));
                    int attributeCount = xmlPullParser.getAttributeCount();
                    if (attributeCount > 0) {
                        for (int i = 0; i < attributeCount; i++) {
                            stringBuffer.append(String.format("%s=\"%s\"", new Object[]{xmlPullParser.getAttributeName(i).trim(), xmlPullParser.getAttributeValue(i).trim()}));
                        }
                    }
                    trim = ">";
                } else if (eventType == 3) {
                    trim = String.format("</%s>", new Object[]{xmlPullParser.getName()});
                } else if (eventType == 4) {
                    trim = xmlPullParser.getText().trim();
                }
                stringBuffer.append(trim);
            }
            if (stringBuffer.length() < 65529) {
                eventType = xmlPullParser.next();
            } else {
                throw new RuntimeException("Accessory Service XML is too long! Services XML cannot be more than 64k in size");
            }
        }
        return stringBuffer.toString().getBytes(i.f());
    }

    private String b() {
        try {
            Bundle bundle = this.c.getApplicationContext().getPackageManager().getApplicationInfo(this.c.getApplicationContext().getPackageName(), 128).metaData;
            if (bundle != null) {
                String string = bundle.getString("AccessoryServicesLocation", null);
                if (string != null) {
                    StringBuilder sb = new StringBuilder("Service description(s) file Location:");
                    sb.append(string);
                    Log.i("[SA_SDK]ServiceXmlReader", sb.toString());
                    return string;
                }
                Log.e("[SA_SDK]ServiceXmlReader", "No meta data found with key:AccessoryServicesLocation");
                throw new RuntimeException("No meta data found with key:AccessoryServicesLocation");
            }
            Log.e("[SA_SDK]ServiceXmlReader", "No meta data present in the manifest");
            throw new RuntimeException("No meta data present in the manifest");
        } catch (NameNotFoundException e) {
            StringBuilder sb2 = new StringBuilder("Unable to fetch metadata from teh manifest");
            sb2.append(e.getMessage());
            Log.e("[SA_SDK]ServiceXmlReader", sb2.toString());
            throw new RuntimeException("Unable to fetch metadata from teh manifest", e);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0071, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4 = new java.lang.StringBuilder("Parsing Accessory service configuration failed from:");
        r4.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0085, code lost:
        throw new java.lang.RuntimeException(r4.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0086, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        r4 = new java.lang.StringBuilder("Parsing Accessory service configuration failed from:");
        r4.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009a, code lost:
        throw new java.lang.RuntimeException(r4.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009c, code lost:
        r4 = new java.lang.StringBuilder("Accessory services configuration XML file not found at:");
        r4.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00af, code lost:
        throw new java.lang.RuntimeException(r4.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b0, code lost:
        if (r2 != null) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b5, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0115, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0116, code lost:
        r3 = new java.lang.StringBuilder("Parsing Accessory service configuration failed from:");
        r3.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0129, code lost:
        throw new java.lang.RuntimeException(r3.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x012a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x012b, code lost:
        r3 = new java.lang.StringBuilder("Parsing Accessory service configuration failed from:");
        r3.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x013e, code lost:
        throw new java.lang.RuntimeException(r3.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x013f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0140, code lost:
        r3 = new java.lang.StringBuilder("Unable to read the service XML file from:");
        r3.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0153, code lost:
        throw new java.lang.RuntimeException(r3.toString(), r1);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:8:0x002b, B:20:0x0072, B:36:0x00c5, B:38:0x00d5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized byte[] a() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.String r0 = r6.b()     // Catch:{ all -> 0x0160 }
            byte[] r1 = b     // Catch:{ all -> 0x0160 }
            if (r1 != 0) goto L_0x015c
            java.lang.String r1 = "/res/"
            boolean r1 = r0.startsWith(r1)     // Catch:{ all -> 0x0160 }
            r2 = 1
            if (r1 == 0) goto L_0x00b6
            java.lang.String r1 = "[SA_SDK]ServiceXmlReader"
            java.lang.String r3 = "Fetching xml from /res/xml"
            android.util.Log.d(r1, r3)     // Catch:{ all -> 0x0160 }
            java.lang.String r1 = "."
            int r1 = r0.lastIndexOf(r1)     // Catch:{ all -> 0x0160 }
            java.lang.String r3 = java.io.File.separator     // Catch:{ all -> 0x0160 }
            int r3 = r0.lastIndexOf(r3)     // Catch:{ all -> 0x0160 }
            int r3 = r3 + r2
            java.lang.String r1 = r0.substring(r3, r1)     // Catch:{ all -> 0x0160 }
            r2 = 0
            android.content.Context r3 = r6.c     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            java.lang.String r4 = "xml"
            android.content.Context r5 = r6.c     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            int r1 = r3.getIdentifier(r1, r4, r5)     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            android.content.Context r3 = r6.c     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            android.content.res.XmlResourceParser r2 = r3.getXml(r1)     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            if (r2 == 0) goto L_0x0056
            byte[] r1 = r6.a(r2)     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            b = r1     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            if (r2 == 0) goto L_0x00ed
            r2.close()     // Catch:{ all -> 0x0160 }
            goto L_0x00ed
        L_0x0056:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            java.lang.String r4 = "Unable to read the service XML file from:"
            r3.<init>(r4)     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            r3.append(r0)     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            java.lang.String r4 = " resource parser is null"
            r3.append(r4)     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            java.lang.String r3 = r3.toString()     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            r1.<init>(r3)     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
            throw r1     // Catch:{ NotFoundException -> 0x009b, XmlPullParserException -> 0x0086, IOException -> 0x0071 }
        L_0x006f:
            r0 = move-exception
            goto L_0x00b0
        L_0x0071:
            r1 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            java.lang.String r5 = "Parsing Accessory service configuration failed from:"
            r4.<init>(r5)     // Catch:{ all -> 0x006f }
            r4.append(r0)     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x006f }
            r3.<init>(r0, r1)     // Catch:{ all -> 0x006f }
            throw r3     // Catch:{ all -> 0x006f }
        L_0x0086:
            r1 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            java.lang.String r5 = "Parsing Accessory service configuration failed from:"
            r4.<init>(r5)     // Catch:{ all -> 0x006f }
            r4.append(r0)     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x006f }
            r3.<init>(r0, r1)     // Catch:{ all -> 0x006f }
            throw r3     // Catch:{ all -> 0x006f }
        L_0x009b:
            r1 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            java.lang.String r5 = "Accessory services configuration XML file not found at:"
            r4.<init>(r5)     // Catch:{ all -> 0x006f }
            r4.append(r0)     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x006f }
            r3.<init>(r0, r1)     // Catch:{ all -> 0x006f }
            throw r3     // Catch:{ all -> 0x006f }
        L_0x00b0:
            if (r2 == 0) goto L_0x00b5
            r2.close()     // Catch:{ all -> 0x0160 }
        L_0x00b5:
            throw r0     // Catch:{ all -> 0x0160 }
        L_0x00b6:
            java.lang.String r1 = "/assets/"
            boolean r1 = r0.startsWith(r1)     // Catch:{ all -> 0x0160 }
            if (r1 == 0) goto L_0x0154
            java.lang.String r1 = "[SA_SDK]ServiceXmlReader"
            java.lang.String r3 = "Fetching xml from /assets"
            android.util.Log.d(r1, r3)     // Catch:{ all -> 0x0160 }
            android.content.Context r1 = r6.c     // Catch:{ IOException -> 0x013f }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x013f }
            r3 = 8
            java.lang.String r3 = r0.substring(r3)     // Catch:{ IOException -> 0x013f }
            java.io.InputStream r1 = r1.open(r3)     // Catch:{ IOException -> 0x013f }
            org.xmlpull.v1.XmlPullParserFactory r3 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ XmlPullParserException -> 0x012a, IOException -> 0x0115 }
            r3.setNamespaceAware(r2)     // Catch:{ XmlPullParserException -> 0x012a, IOException -> 0x0115 }
            org.xmlpull.v1.XmlPullParser r2 = r3.newPullParser()     // Catch:{ XmlPullParserException -> 0x012a, IOException -> 0x0115 }
            java.lang.String r3 = com.samsung.android.sdk.accessory.i.f()     // Catch:{ XmlPullParserException -> 0x012a, IOException -> 0x0115 }
            r2.setInput(r1, r3)     // Catch:{ XmlPullParserException -> 0x012a, IOException -> 0x0115 }
            byte[] r1 = r6.a(r2)     // Catch:{ XmlPullParserException -> 0x012a, IOException -> 0x0115 }
            b = r1     // Catch:{ XmlPullParserException -> 0x012a, IOException -> 0x0115 }
        L_0x00ed:
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x0160 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0160 }
            java.lang.String r2 = r0.getPackageName()     // Catch:{ all -> 0x0160 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0160 }
            r1.<init>(r2)     // Catch:{ all -> 0x0160 }
            java.lang.String r2 = "#9#2.5.3#"
            r1.append(r2)     // Catch:{ all -> 0x0160 }
            int r2 = com.samsung.android.sdk.accessory.i.e()     // Catch:{ all -> 0x0160 }
            r1.append(r2)     // Catch:{ all -> 0x0160 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0160 }
            r1.append(r2)     // Catch:{ all -> 0x0160 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0160 }
            com.samsung.android.sdk.accessory.f.a(r0, r1)     // Catch:{ all -> 0x0160 }
            goto L_0x015c
        L_0x0115:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0160 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0160 }
            java.lang.String r4 = "Parsing Accessory service configuration failed from:"
            r3.<init>(r4)     // Catch:{ all -> 0x0160 }
            r3.append(r0)     // Catch:{ all -> 0x0160 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0160 }
            r2.<init>(r0, r1)     // Catch:{ all -> 0x0160 }
            throw r2     // Catch:{ all -> 0x0160 }
        L_0x012a:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0160 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0160 }
            java.lang.String r4 = "Parsing Accessory service configuration failed from:"
            r3.<init>(r4)     // Catch:{ all -> 0x0160 }
            r3.append(r0)     // Catch:{ all -> 0x0160 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0160 }
            r2.<init>(r0, r1)     // Catch:{ all -> 0x0160 }
            throw r2     // Catch:{ all -> 0x0160 }
        L_0x013f:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0160 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0160 }
            java.lang.String r4 = "Unable to read the service XML file from:"
            r3.<init>(r4)     // Catch:{ all -> 0x0160 }
            r3.append(r0)     // Catch:{ all -> 0x0160 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0160 }
            r2.<init>(r0, r1)     // Catch:{ all -> 0x0160 }
            throw r2     // Catch:{ all -> 0x0160 }
        L_0x0154:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0160 }
            java.lang.String r1 = "Accssory Service profile xml must be in /res or /assets directory."
            r0.<init>(r1)     // Catch:{ all -> 0x0160 }
            throw r0     // Catch:{ all -> 0x0160 }
        L_0x015c:
            byte[] r0 = b     // Catch:{ all -> 0x0160 }
            monitor-exit(r6)
            return r0
        L_0x0160:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.accessory.l.a():byte[]");
    }
}
