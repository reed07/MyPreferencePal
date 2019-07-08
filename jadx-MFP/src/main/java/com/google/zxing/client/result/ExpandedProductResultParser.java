package com.google.zxing.client.result;

public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0267  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0285  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0299  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.ExpandedProductParsedResult parse(com.google.zxing.Result r25) {
        /*
            r24 = this;
            com.google.zxing.BarcodeFormat r0 = r25.getBarcodeFormat()
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.RSS_EXPANDED
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r4 = getMassagedText(r25)
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r3 = 0
        L_0x0023:
            int r1 = r4.length()
            if (r3 >= r1) goto L_0x02a3
            java.lang.String r1 = findAIvalue(r3, r4)
            if (r1 != 0) goto L_0x0030
            return r2
        L_0x0030:
            int r18 = r1.length()
            r19 = 2
            int r18 = r18 + 2
            int r3 = r3 + r18
            java.lang.String r2 = findValue(r3, r4)
            int r20 = r2.length()
            int r3 = r3 + r20
            r20 = -1
            r21 = r3
            int r3 = r1.hashCode()
            r22 = r15
            r15 = 1570(0x622, float:2.2E-42)
            r23 = r14
            r14 = 3
            if (r3 == r15) goto L_0x01f9
            r15 = 1572(0x624, float:2.203E-42)
            if (r3 == r15) goto L_0x01ef
            r15 = 1574(0x626, float:2.206E-42)
            if (r3 == r15) goto L_0x01e5
            switch(r3) {
                case 1536: goto L_0x01db;
                case 1537: goto L_0x01d1;
                default: goto L_0x0060;
            }
        L_0x0060:
            switch(r3) {
                case 1567: goto L_0x01c7;
                case 1568: goto L_0x01bd;
                default: goto L_0x0063;
            }
        L_0x0063:
            switch(r3) {
                case 1567966: goto L_0x01b3;
                case 1567967: goto L_0x01a8;
                case 1567968: goto L_0x019d;
                case 1567969: goto L_0x0191;
                case 1567970: goto L_0x0185;
                case 1567971: goto L_0x0179;
                case 1567972: goto L_0x016d;
                case 1567973: goto L_0x0161;
                case 1567974: goto L_0x0155;
                case 1567975: goto L_0x0149;
                default: goto L_0x0066;
            }
        L_0x0066:
            switch(r3) {
                case 1568927: goto L_0x013d;
                case 1568928: goto L_0x0131;
                case 1568929: goto L_0x0125;
                case 1568930: goto L_0x0119;
                case 1568931: goto L_0x010d;
                case 1568932: goto L_0x0101;
                case 1568933: goto L_0x00f5;
                case 1568934: goto L_0x00e9;
                case 1568935: goto L_0x00dd;
                case 1568936: goto L_0x00d1;
                default: goto L_0x0069;
            }
        L_0x0069:
            switch(r3) {
                case 1575716: goto L_0x00c5;
                case 1575717: goto L_0x00b9;
                case 1575718: goto L_0x00ad;
                case 1575719: goto L_0x00a1;
                default: goto L_0x006c;
            }
        L_0x006c:
            switch(r3) {
                case 1575747: goto L_0x0095;
                case 1575748: goto L_0x0089;
                case 1575749: goto L_0x007d;
                case 1575750: goto L_0x0071;
                default: goto L_0x006f;
            }
        L_0x006f:
            goto L_0x0203
        L_0x0071:
            java.lang.String r3 = "3933"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 34
            goto L_0x0204
        L_0x007d:
            java.lang.String r3 = "3932"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 33
            goto L_0x0204
        L_0x0089:
            java.lang.String r3 = "3931"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 32
            goto L_0x0204
        L_0x0095:
            java.lang.String r3 = "3930"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 31
            goto L_0x0204
        L_0x00a1:
            java.lang.String r3 = "3923"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 30
            goto L_0x0204
        L_0x00ad:
            java.lang.String r3 = "3922"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 29
            goto L_0x0204
        L_0x00b9:
            java.lang.String r3 = "3921"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 28
            goto L_0x0204
        L_0x00c5:
            java.lang.String r3 = "3920"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 27
            goto L_0x0204
        L_0x00d1:
            java.lang.String r3 = "3209"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 26
            goto L_0x0204
        L_0x00dd:
            java.lang.String r3 = "3208"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 25
            goto L_0x0204
        L_0x00e9:
            java.lang.String r3 = "3207"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 24
            goto L_0x0204
        L_0x00f5:
            java.lang.String r3 = "3206"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 23
            goto L_0x0204
        L_0x0101:
            java.lang.String r3 = "3205"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 22
            goto L_0x0204
        L_0x010d:
            java.lang.String r3 = "3204"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 21
            goto L_0x0204
        L_0x0119:
            java.lang.String r3 = "3203"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 20
            goto L_0x0204
        L_0x0125:
            java.lang.String r3 = "3202"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 19
            goto L_0x0204
        L_0x0131:
            java.lang.String r3 = "3201"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 18
            goto L_0x0204
        L_0x013d:
            java.lang.String r3 = "3200"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 17
            goto L_0x0204
        L_0x0149:
            java.lang.String r3 = "3109"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 16
            goto L_0x0204
        L_0x0155:
            java.lang.String r3 = "3108"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 15
            goto L_0x0204
        L_0x0161:
            java.lang.String r3 = "3107"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 14
            goto L_0x0204
        L_0x016d:
            java.lang.String r3 = "3106"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 13
            goto L_0x0204
        L_0x0179:
            java.lang.String r3 = "3105"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 12
            goto L_0x0204
        L_0x0185:
            java.lang.String r3 = "3104"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 11
            goto L_0x0204
        L_0x0191:
            java.lang.String r3 = "3103"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 10
            goto L_0x0204
        L_0x019d:
            java.lang.String r3 = "3102"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 9
            goto L_0x0204
        L_0x01a8:
            java.lang.String r3 = "3101"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 8
            goto L_0x0204
        L_0x01b3:
            java.lang.String r3 = "3100"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 7
            goto L_0x0204
        L_0x01bd:
            java.lang.String r3 = "11"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 3
            goto L_0x0204
        L_0x01c7:
            java.lang.String r3 = "10"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 2
            goto L_0x0204
        L_0x01d1:
            java.lang.String r3 = "01"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 1
            goto L_0x0204
        L_0x01db:
            java.lang.String r3 = "00"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 0
            goto L_0x0204
        L_0x01e5:
            java.lang.String r3 = "17"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 6
            goto L_0x0204
        L_0x01ef:
            java.lang.String r3 = "15"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 5
            goto L_0x0204
        L_0x01f9:
            java.lang.String r3 = "13"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0203
            r3 = 4
            goto L_0x0204
        L_0x0203:
            r3 = -1
        L_0x0204:
            switch(r3) {
                case 0: goto L_0x0299;
                case 1: goto L_0x028f;
                case 2: goto L_0x0285;
                case 3: goto L_0x027b;
                case 4: goto L_0x0271;
                case 5: goto L_0x0267;
                case 6: goto L_0x025d;
                case 7: goto L_0x024e;
                case 8: goto L_0x024e;
                case 9: goto L_0x024e;
                case 10: goto L_0x024e;
                case 11: goto L_0x024e;
                case 12: goto L_0x024e;
                case 13: goto L_0x024e;
                case 14: goto L_0x024e;
                case 15: goto L_0x024e;
                case 16: goto L_0x024e;
                case 17: goto L_0x023f;
                case 18: goto L_0x023f;
                case 19: goto L_0x023f;
                case 20: goto L_0x023f;
                case 21: goto L_0x023f;
                case 22: goto L_0x023f;
                case 23: goto L_0x023f;
                case 24: goto L_0x023f;
                case 25: goto L_0x023f;
                case 26: goto L_0x023f;
                case 27: goto L_0x0232;
                case 28: goto L_0x0232;
                case 29: goto L_0x0232;
                case 30: goto L_0x0232;
                case 31: goto L_0x0214;
                case 32: goto L_0x0214;
                case 33: goto L_0x0214;
                case 34: goto L_0x0214;
                default: goto L_0x0207;
            }
        L_0x0207:
            r3 = 0
            r0.put(r1, r2)
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x0214:
            int r3 = r2.length()
            r15 = 4
            if (r3 >= r15) goto L_0x021d
            r3 = 0
            return r3
        L_0x021d:
            r3 = 0
            java.lang.String r15 = r2.substring(r14)
            r3 = 0
            java.lang.String r17 = r2.substring(r3, r14)
            java.lang.String r16 = r1.substring(r14)
            r3 = r21
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x0232:
            r3 = 0
            java.lang.String r16 = r1.substring(r14)
            r15 = r2
            r3 = r21
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x023f:
            r3 = 0
            java.lang.String r13 = "LB"
            java.lang.String r14 = r1.substring(r14)
            r12 = r2
            r3 = r21
            r15 = r22
            r2 = 0
            goto L_0x0023
        L_0x024e:
            r3 = 0
            java.lang.String r13 = "KG"
            java.lang.String r14 = r1.substring(r14)
            r12 = r2
            r3 = r21
            r15 = r22
            r2 = 0
            goto L_0x0023
        L_0x025d:
            r11 = r2
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x0267:
            r10 = r2
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x0271:
            r9 = r2
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x027b:
            r8 = r2
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x0285:
            r7 = r2
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x028f:
            r5 = r2
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x0299:
            r6 = r2
            r3 = r21
            r15 = r22
            r14 = r23
            r2 = 0
            goto L_0x0023
        L_0x02a3:
            r23 = r14
            r22 = r15
            com.google.zxing.client.result.ExpandedProductParsedResult r1 = new com.google.zxing.client.result.ExpandedProductParsedResult
            r3 = r1
            r18 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ExpandedProductResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.ExpandedProductParsedResult");
    }

    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String substring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (findAIvalue(i2, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
