package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.DocumentData;

public class DocumentDataParser implements ValueParser<DocumentData> {
    public static final DocumentDataParser INSTANCE = new DocumentDataParser();

    private DocumentDataParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.airbnb.lottie.model.DocumentData parse(android.util.JsonReader r22, float r23) throws java.io.IOException {
        /*
            r21 = this;
            r22.beginObject()
            r0 = 1
            r1 = 0
            r2 = 0
            r4 = 0
            r6 = r1
            r7 = r6
            r8 = r2
            r12 = r8
            r14 = r12
            r18 = r14
            r10 = 0
            r11 = 0
            r16 = 0
            r17 = 0
            r20 = 1
        L_0x0017:
            boolean r1 = r22.hasNext()
            if (r1 == 0) goto L_0x011b
            java.lang.String r1 = r22.nextName()
            r2 = -1
            int r3 = r1.hashCode()
            r5 = 102(0x66, float:1.43E-43)
            if (r3 == r5) goto L_0x00b7
            r5 = 106(0x6a, float:1.49E-43)
            if (r3 == r5) goto L_0x00ad
            r5 = 3261(0xcbd, float:4.57E-42)
            if (r3 == r5) goto L_0x00a3
            r5 = 3452(0xd7c, float:4.837E-42)
            if (r3 == r5) goto L_0x0099
            r5 = 3463(0xd87, float:4.853E-42)
            if (r3 == r5) goto L_0x008f
            r5 = 3543(0xdd7, float:4.965E-42)
            if (r3 == r5) goto L_0x0084
            r5 = 3664(0xe50, float:5.134E-42)
            if (r3 == r5) goto L_0x0079
            r5 = 3684(0xe64, float:5.162E-42)
            if (r3 == r5) goto L_0x006e
            r5 = 3710(0xe7e, float:5.199E-42)
            if (r3 == r5) goto L_0x0064
            switch(r3) {
                case 115: goto L_0x005a;
                case 116: goto L_0x004f;
                default: goto L_0x004d;
            }
        L_0x004d:
            goto L_0x00c1
        L_0x004f:
            java.lang.String r3 = "t"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 0
            goto L_0x00c2
        L_0x005a:
            java.lang.String r3 = "s"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 2
            goto L_0x00c2
        L_0x0064:
            java.lang.String r3 = "tr"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 4
            goto L_0x00c2
        L_0x006e:
            java.lang.String r3 = "sw"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 9
            goto L_0x00c2
        L_0x0079:
            java.lang.String r3 = "sc"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 8
            goto L_0x00c2
        L_0x0084:
            java.lang.String r3 = "of"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 10
            goto L_0x00c2
        L_0x008f:
            java.lang.String r3 = "ls"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 6
            goto L_0x00c2
        L_0x0099:
            java.lang.String r3 = "lh"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 5
            goto L_0x00c2
        L_0x00a3:
            java.lang.String r3 = "fc"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 7
            goto L_0x00c2
        L_0x00ad:
            java.lang.String r3 = "j"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 3
            goto L_0x00c2
        L_0x00b7:
            java.lang.String r3 = "f"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c1
            r1 = 1
            goto L_0x00c2
        L_0x00c1:
            r1 = -1
        L_0x00c2:
            switch(r1) {
                case 0: goto L_0x0114;
                case 1: goto L_0x010d;
                case 2: goto L_0x0106;
                case 3: goto L_0x00ff;
                case 4: goto L_0x00f8;
                case 5: goto L_0x00f1;
                case 6: goto L_0x00ea;
                case 7: goto L_0x00e2;
                case 8: goto L_0x00da;
                case 9: goto L_0x00d2;
                case 10: goto L_0x00ca;
                default: goto L_0x00c5;
            }
        L_0x00c5:
            r22.skipValue()
            goto L_0x0017
        L_0x00ca:
            boolean r1 = r22.nextBoolean()
            r20 = r1
            goto L_0x0017
        L_0x00d2:
            double r1 = r22.nextDouble()
            r18 = r1
            goto L_0x0017
        L_0x00da:
            int r1 = com.airbnb.lottie.parser.JsonUtils.jsonToColor(r22)
            r17 = r1
            goto L_0x0017
        L_0x00e2:
            int r1 = com.airbnb.lottie.parser.JsonUtils.jsonToColor(r22)
            r16 = r1
            goto L_0x0017
        L_0x00ea:
            double r1 = r22.nextDouble()
            r14 = r1
            goto L_0x0017
        L_0x00f1:
            double r1 = r22.nextDouble()
            r12 = r1
            goto L_0x0017
        L_0x00f8:
            int r1 = r22.nextInt()
            r11 = r1
            goto L_0x0017
        L_0x00ff:
            int r1 = r22.nextInt()
            r10 = r1
            goto L_0x0017
        L_0x0106:
            double r1 = r22.nextDouble()
            r8 = r1
            goto L_0x0017
        L_0x010d:
            java.lang.String r1 = r22.nextString()
            r7 = r1
            goto L_0x0017
        L_0x0114:
            java.lang.String r1 = r22.nextString()
            r6 = r1
            goto L_0x0017
        L_0x011b:
            r22.endObject()
            com.airbnb.lottie.model.DocumentData r0 = new com.airbnb.lottie.model.DocumentData
            r5 = r0
            r5.<init>(r6, r7, r8, r10, r11, r12, r14, r16, r17, r18, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.DocumentDataParser.parse(android.util.JsonReader, float):com.airbnb.lottie.model.DocumentData");
    }
}
