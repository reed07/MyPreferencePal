package com.google.ads.interactivemedia.v3.internal;

import java.util.TimeZone;

/* compiled from: IMASDK */
public final class abp {
    private static final TimeZone a = TimeZone.getTimeZone("UTC");

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b1, code lost:
        r11 = r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date a(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        /*
            r1 = r17
            r2 = r18
            int r0 = r18.getIndex()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r3 = r0 + 4
            int r0 = a(r1, r0, r3)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r4 = 45
            boolean r5 = a(r1, r3, r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r5 == 0) goto L_0x0018
            int r3 = r3 + 1
        L_0x0018:
            int r5 = r3 + 2
            int r3 = a(r1, r3, r5)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            boolean r6 = a(r1, r5, r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r6 == 0) goto L_0x0026
            int r5 = r5 + 1
        L_0x0026:
            int r6 = r5 + 2
            int r5 = a(r1, r5, r6)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r7 = 84
            boolean r7 = a(r1, r6, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r8 = 1
            if (r7 != 0) goto L_0x0049
            int r9 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r9 > r6) goto L_0x0049
            java.util.GregorianCalendar r4 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r3 = r3 - r8
            r4.<init>(r0, r3, r5)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r2.setIndex(r6)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.util.Date r0 = r4.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            return r0
        L_0x0049:
            r9 = 43
            r10 = 90
            if (r7 == 0) goto L_0x00dd
            int r6 = r6 + 1
            int r7 = r6 + 2
            int r6 = a(r1, r6, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r12 = 58
            boolean r13 = a(r1, r7, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r13 == 0) goto L_0x0061
            int r7 = r7 + 1
        L_0x0061:
            int r13 = r7 + 2
            int r7 = a(r1, r7, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            boolean r12 = a(r1, r13, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r12 == 0) goto L_0x006f
            int r13 = r13 + 1
        L_0x006f:
            int r12 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r12 <= r13) goto L_0x00d7
            char r12 = r1.charAt(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r12 == r10) goto L_0x00d7
            if (r12 == r9) goto L_0x00d7
            if (r12 == r4) goto L_0x00d7
            int r12 = r13 + 2
            int r13 = a(r1, r13, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r14 = 59
            if (r13 <= r14) goto L_0x008e
            r15 = 63
            if (r13 >= r15) goto L_0x008e
            goto L_0x008f
        L_0x008e:
            r14 = r13
        L_0x008f:
            r13 = 46
            boolean r13 = a(r1, r12, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r13 == 0) goto L_0x00d2
            int r12 = r12 + 1
            int r13 = r12 + 1
        L_0x009b:
            int r15 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r13 >= r15) goto L_0x00b3
            char r15 = r1.charAt(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r11 = 48
            if (r15 < r11) goto L_0x00b1
            r11 = 57
            if (r15 <= r11) goto L_0x00ae
            goto L_0x00b1
        L_0x00ae:
            int r13 = r13 + 1
            goto L_0x009b
        L_0x00b1:
            r11 = r13
            goto L_0x00b7
        L_0x00b3:
            int r11 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
        L_0x00b7:
            int r13 = r12 + 3
            int r13 = java.lang.Math.min(r11, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r15 = a(r1, r12, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r13 = r13 - r12
            switch(r13) {
                case 1: goto L_0x00c9;
                case 2: goto L_0x00c6;
                default: goto L_0x00c5;
            }     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
        L_0x00c5:
            goto L_0x00cb
        L_0x00c6:
            int r15 = r15 * 10
            goto L_0x00cb
        L_0x00c9:
            int r15 = r15 * 100
        L_0x00cb:
            r16 = r7
            r7 = r6
            r6 = r11
            r11 = r16
            goto L_0x00e1
        L_0x00d2:
            r11 = r7
            r15 = 0
            r7 = r6
            r6 = r12
            goto L_0x00e1
        L_0x00d7:
            r11 = r7
            r14 = 0
            r15 = 0
            r7 = r6
            r6 = r13
            goto L_0x00e1
        L_0x00dd:
            r7 = 0
            r11 = 0
            r14 = 0
            r15 = 0
        L_0x00e1:
            int r12 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r12 <= r6) goto L_0x01c3
            char r12 = r1.charAt(r6)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r13 = 5
            if (r12 != r10) goto L_0x00f3
            java.util.TimeZone r4 = a     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r6 = r6 + r8
            goto L_0x0192
        L_0x00f3:
            if (r12 == r9) goto L_0x0111
            if (r12 != r4) goto L_0x00f8
            goto L_0x0111
        L_0x00f8:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r4 = "Invalid time zone indicator '"
            r3.<init>(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r3.append(r12)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r4 = "'"
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r3 = r3.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
        L_0x0111:
            java.lang.String r4 = r1.substring(r6)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r9 = r4.length()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r9 < r13) goto L_0x011c
            goto L_0x012d
        L_0x011c:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r9.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r9.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r4 = "00"
            r9.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r4 = r9.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
        L_0x012d:
            int r9 = r4.length()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r6 = r6 + r9
            java.lang.String r9 = "+0000"
            boolean r9 = r9.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r9 != 0) goto L_0x0190
            java.lang.String r9 = "+00:00"
            boolean r9 = r9.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r9 == 0) goto L_0x0143
            goto L_0x0190
        L_0x0143:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r10 = "GMT"
            r9.<init>(r10)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r9.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r4 = r9.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.util.TimeZone r9 = java.util.TimeZone.getTimeZone(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r10 = r9.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            boolean r12 = r10.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r12 != 0) goto L_0x018e
            java.lang.String r12 = ":"
            java.lang.String r13 = ""
            java.lang.String r10 = r10.replace(r12, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            boolean r10 = r10.equals(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            if (r10 == 0) goto L_0x016e
            goto L_0x018e
        L_0x016e:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r5 = "Mismatching time zone indicator: "
            r3.<init>(r5)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r4 = " given, resolves to "
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r4 = r9.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r3 = r3.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
        L_0x018e:
            r4 = r9
            goto L_0x0192
        L_0x0190:
            java.util.TimeZone r4 = a     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
        L_0x0192:
            java.util.GregorianCalendar r9 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r9.<init>(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r4 = 0
            r9.setLenient(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r9.set(r8, r0)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            int r3 = r3 - r8
            r0 = 2
            r9.set(r0, r3)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r0 = 5
            r9.set(r0, r5)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r0 = 11
            r9.set(r0, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r0 = 12
            r9.set(r0, r11)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r0 = 13
            r9.set(r0, r14)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r0 = 14
            r9.set(r0, r15)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            r2.setIndex(r6)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.util.Date r0 = r9.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            return r0
        L_0x01c3:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            java.lang.String r3 = "No time zone indicator"
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01cf, NumberFormatException -> 0x01cd, IllegalArgumentException -> 0x01cb }
        L_0x01cb:
            r0 = move-exception
            goto L_0x01d0
        L_0x01cd:
            r0 = move-exception
            goto L_0x01d0
        L_0x01cf:
            r0 = move-exception
        L_0x01d0:
            if (r1 != 0) goto L_0x01d4
            r1 = 0
            goto L_0x01e7
        L_0x01d4:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "\""
            r3.<init>(r4)
            r3.append(r1)
            r1 = 34
            r3.append(r1)
            java.lang.String r1 = r3.toString()
        L_0x01e7:
            java.lang.String r3 = r0.getMessage()
            if (r3 == 0) goto L_0x01f3
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x020e
        L_0x01f3:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "("
            r3.<init>(r4)
            java.lang.Class r4 = r0.getClass()
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r4 = ")"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
        L_0x020e:
            java.text.ParseException r4 = new java.text.ParseException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed to parse date ["
            r5.<init>(r6)
            r5.append(r1)
            java.lang.String r1 = "]: "
            r5.append(r1)
            r5.append(r3)
            java.lang.String r1 = r5.toString()
            int r2 = r18.getIndex()
            r4.<init>(r1, r2)
            r4.initCause(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.abp.a(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean a(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int a(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                StringBuilder sb = new StringBuilder("Invalid number: ");
                sb.append(str.substring(i, i2));
                throw new NumberFormatException(sb.toString());
            }
        } else {
            i4 = i;
            i3 = 0;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i4 = i5;
            } else {
                StringBuilder sb2 = new StringBuilder("Invalid number: ");
                sb2.append(str.substring(i, i2));
                throw new NumberFormatException(sb2.toString());
            }
        }
        return -i3;
    }
}
