package com.myfitnesspal.feature.friends.util;

import android.content.Intent;
import android.provider.ContactsContract.Contacts;

public class ContactAccessor {
    private static ContactAccessor sInstance;

    private ContactAccessor() {
    }

    public static ContactAccessor getInstance() {
        if (sInstance == null) {
            sInstance = new ContactAccessor();
        }
        return sInstance;
    }

    public Intent getPickContactIntent() {
        return new Intent("android.intent.action.PICK", Contacts.CONTENT_URI);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        if (r1 != null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0065, code lost:
        if (r1 == null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.feature.friends.model.ContactInfo loadContact(android.content.ContentResolver r10, android.net.Uri r11) {
        /*
            r9 = this;
            com.myfitnesspal.feature.friends.model.ContactInfo r0 = new com.myfitnesspal.feature.friends.model.ContactInfo
            r0.<init>()
            java.lang.String r1 = "_id"
            java.lang.String r2 = "display_name"
            java.lang.String[] r5 = new java.lang.String[]{r1, r2}
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r10
            r4 = r11
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)
            r2 = 1
            boolean r3 = r1.moveToFirst()     // Catch:{ Exception -> 0x002a }
            if (r3 == 0) goto L_0x0024
            java.lang.String r3 = r1.getString(r2)     // Catch:{ Exception -> 0x002a }
            r0.setDisplayName(r3)     // Catch:{ Exception -> 0x002a }
        L_0x0024:
            r1.close()
            goto L_0x002f
        L_0x0028:
            r10 = move-exception
            goto L_0x0071
        L_0x002a:
            r3 = move-exception
            com.uacf.core.util.Ln.w(r3)     // Catch:{ all -> 0x0028 }
            goto L_0x0024
        L_0x002f:
            java.lang.String r11 = r11.getLastPathSegment()     // Catch:{ Exception -> 0x0061 }
            android.net.Uri r4 = android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_URI     // Catch:{ Exception -> 0x0061 }
            r5 = 0
            java.lang.String r6 = "contact_id=?"
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0061 }
            r2 = 0
            r7[r2] = r11     // Catch:{ Exception -> 0x0061 }
            r8 = 0
            r3 = r10
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0061 }
            java.lang.String r10 = "data1"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ Exception -> 0x0061 }
            boolean r11 = r1.moveToFirst()     // Catch:{ Exception -> 0x0061 }
            if (r11 == 0) goto L_0x0057
            java.lang.String r10 = r1.getString(r10)     // Catch:{ Exception -> 0x0061 }
            r0.setEmail(r10)     // Catch:{ Exception -> 0x0061 }
            goto L_0x005c
        L_0x0057:
            java.lang.String r10 = " "
            r0.setEmail(r10)     // Catch:{ Exception -> 0x0061 }
        L_0x005c:
            if (r1 == 0) goto L_0x006a
            goto L_0x0067
        L_0x005f:
            r10 = move-exception
            goto L_0x006b
        L_0x0061:
            r10 = move-exception
            com.uacf.core.util.Ln.w(r10)     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x006a
        L_0x0067:
            r1.close()
        L_0x006a:
            return r0
        L_0x006b:
            if (r1 == 0) goto L_0x0070
            r1.close()
        L_0x0070:
            throw r10
        L_0x0071:
            r1.close()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.friends.util.ContactAccessor.loadContact(android.content.ContentResolver, android.net.Uri):com.myfitnesspal.feature.friends.model.ContactInfo");
    }
}
