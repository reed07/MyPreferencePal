package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.nio.ByteBuffer;

/* compiled from: IMASDK */
public final class da {
    private static final int[] a = {1, 2, 3, 6};
    private static final int[] b = {48000, 44100, 32000};
    private static final int[] c = {24000, 22050, 16000};
    private static final int[] d = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] e = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, ModuleDescriptor.MODULE_VERSION, 384, 448, 512, 576, AmazonAdTask.DEFAULT_AD_WIDTH};
    private static final int[] f = {69, 87, 104, PacketTypes.RetrieveFriendList, PacketTypes.CheckFriendsByEmailResponse, RequestCodes.JOIN_CHALLENGE, RequestCodes.PREMIUM_UPSELL, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static int a() {
        return 1536;
    }

    public static bs a(ut utVar, String str, String str2, fa faVar) {
        int i = b[(utVar.e() & 192) >> 6];
        int e2 = utVar.e();
        int i2 = d[(e2 & 56) >> 3];
        return bs.a(str, MimeTypes.AUDIO_AC3, null, -1, -1, (e2 & 4) != 0 ? i2 + 1 : i2, i, null, faVar, 0, str2);
    }

    public static bs b(ut utVar, String str, String str2, fa faVar) {
        ut utVar2 = utVar;
        utVar.d(2);
        int i = b[(utVar.e() & 192) >> 6];
        int e2 = utVar.e();
        int i2 = d[(e2 & 14) >> 1];
        if ((e2 & 1) != 0) {
            i2++;
        }
        return bs.a(str, (utVar.b() <= 0 || (utVar.e() & 1) == 0) ? MimeTypes.AUDIO_E_AC3 : MimeTypes.AUDIO_E_AC3_JOC, null, -1, -1, (((utVar.e() & 30) >> 1) <= 0 || (2 & utVar.e()) == 0) ? i2 : i2 + 2, i, null, faVar, 0, str2);
    }

    public static db a(us usVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        int i6;
        int i7;
        int i8;
        int i9;
        us usVar2 = usVar;
        int b2 = usVar.b();
        usVar2.b(40);
        boolean z = usVar2.c(5) == 16;
        usVar2.a(b2);
        int i10 = -1;
        if (z) {
            usVar2.b(16);
            switch (usVar2.c(2)) {
                case 0:
                    i10 = 0;
                    break;
                case 1:
                    i10 = 1;
                    break;
                case 2:
                    i10 = 2;
                    break;
            }
            usVar2.b(3);
            int c2 = (usVar2.c(11) + 1) << 1;
            int c3 = usVar2.c(2);
            if (c3 == 3) {
                i6 = c[usVar2.c(2)];
                i8 = 3;
                i7 = 6;
            } else {
                i8 = usVar2.c(2);
                i7 = a[i8];
                i6 = b[c3];
            }
            int i11 = i7 * 256;
            int c4 = usVar2.c(3);
            boolean d2 = usVar.d();
            int i12 = d[c4] + (d2 ? 1 : 0);
            usVar2.b(10);
            if (usVar.d()) {
                usVar2.b(8);
            }
            if (c4 == 0) {
                usVar2.b(5);
                if (usVar.d()) {
                    usVar2.b(8);
                }
            }
            if (i10 == 1 && usVar.d()) {
                usVar2.b(16);
            }
            if (usVar.d()) {
                if (c4 > 2) {
                    usVar2.b(2);
                }
                if ((c4 & 1) != 0 && c4 > 2) {
                    usVar2.b(6);
                }
                if ((c4 & 4) != 0) {
                    usVar2.b(6);
                }
                if (d2 && usVar.d()) {
                    usVar2.b(5);
                }
                if (i10 == 0) {
                    if (usVar.d()) {
                        usVar2.b(6);
                    }
                    if (c4 == 0 && usVar.d()) {
                        usVar2.b(6);
                    }
                    if (usVar.d()) {
                        usVar2.b(6);
                    }
                    int c5 = usVar2.c(2);
                    if (c5 == 1) {
                        usVar2.b(5);
                    } else if (c5 == 2) {
                        usVar2.b(12);
                    } else if (c5 == 3) {
                        int c6 = usVar2.c(5);
                        if (usVar.d()) {
                            usVar2.b(5);
                            if (usVar.d()) {
                                usVar2.b(4);
                            }
                            if (usVar.d()) {
                                usVar2.b(4);
                            }
                            if (usVar.d()) {
                                usVar2.b(4);
                            }
                            if (usVar.d()) {
                                usVar2.b(4);
                            }
                            if (usVar.d()) {
                                usVar2.b(4);
                            }
                            if (usVar.d()) {
                                usVar2.b(4);
                            }
                            if (usVar.d()) {
                                usVar2.b(4);
                            }
                            if (usVar.d()) {
                                if (usVar.d()) {
                                    usVar2.b(4);
                                }
                                if (usVar.d()) {
                                    usVar2.b(4);
                                }
                            }
                        }
                        if (usVar.d()) {
                            usVar2.b(5);
                            if (usVar.d()) {
                                usVar2.b(7);
                                if (usVar.d()) {
                                    usVar2.b(8);
                                }
                            }
                        }
                        usVar2.b((c6 + 2) * 8);
                        usVar.e();
                    }
                    if (c4 < 2) {
                        if (usVar.d()) {
                            usVar2.b(14);
                        }
                        if (c4 == 0 && usVar.d()) {
                            usVar2.b(14);
                        }
                    }
                    if (usVar.d()) {
                        if (i8 == 0) {
                            usVar2.b(5);
                        } else {
                            for (int i13 = 0; i13 < i7; i13++) {
                                if (usVar.d()) {
                                    usVar2.b(5);
                                }
                            }
                        }
                    }
                }
            }
            if (usVar.d()) {
                usVar2.b(5);
                if (c4 == 2) {
                    usVar2.b(4);
                }
                if (c4 >= 6) {
                    usVar2.b(2);
                }
                if (usVar.d()) {
                    usVar2.b(8);
                }
                if (c4 != 0 || !usVar.d()) {
                    i9 = 3;
                } else {
                    usVar2.b(8);
                    i9 = 3;
                }
                if (c3 < i9) {
                    usVar.c();
                }
            } else {
                i9 = 3;
            }
            if (i10 == 0 && i8 != i9) {
                usVar.c();
            }
            if (i10 == 2 && (i8 == i9 || usVar.d())) {
                usVar2.b(6);
            }
            String str2 = MimeTypes.AUDIO_E_AC3;
            if (usVar.d() && usVar2.c(6) == 1 && usVar2.c(8) == 1) {
                str2 = MimeTypes.AUDIO_E_AC3_JOC;
            }
            i5 = i10;
            str = str2;
            i2 = c2;
            i3 = i6;
            i = i11;
            i4 = i12;
        } else {
            String str3 = MimeTypes.AUDIO_AC3;
            usVar2.b(32);
            int c7 = usVar2.c(2);
            int a2 = a(c7, usVar2.c(6));
            usVar2.b(8);
            int c8 = usVar2.c(3);
            if (!((c8 & 1) == 0 || c8 == 1)) {
                usVar2.b(2);
            }
            if ((c8 & 4) != 0) {
                usVar2.b(2);
            }
            if (c8 == 2) {
                usVar2.b(2);
            }
            str = str3;
            i2 = a2;
            i3 = b[c7];
            i4 = d[c8] + (usVar.d() ? 1 : 0);
            i5 = -1;
            i = 1536;
        }
        db dbVar = new db(str, i5, i4, i3, i2, i, 0);
        return dbVar;
    }

    public static int a(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (!(((bArr[5] & 255) >> 3) == 16)) {
            return a((bArr[4] & 192) >> 6, (int) bArr[4] & 63);
        }
        return (((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1) << 1;
    }

    public static int a(ByteBuffer byteBuffer) {
        int i = 6;
        if (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3) {
            i = a[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4];
        }
        return i * 256;
    }

    public static int b(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - 10;
        for (int i = position; i <= limit; i++) {
            if ((byteBuffer.getInt(i + 4) & -16777217) == -1167101192) {
                return i - position;
            }
        }
        return -1;
    }

    public static int b(byte[] bArr) {
        boolean z = false;
        if (bArr[4] != -8 || bArr[5] != 114 || bArr[6] != 111 || (bArr[7] & 254) != 186) {
            return 0;
        }
        if ((bArr[7] & 255) == 187) {
            z = true;
        }
        return 40 << ((bArr[z ? (char) 9 : 8] >> 4) & 7);
    }

    public static int a(ByteBuffer byteBuffer, int i) {
        return 40 << ((byteBuffer.get((byteBuffer.position() + i) + ((byteBuffer.get((byteBuffer.position() + i) + 7) & 255) == 187 ? 9 : 8)) >> 4) & 7);
    }

    private static int a(int i, int i2) {
        int i3 = i2 / 2;
        if (i >= 0) {
            int[] iArr = b;
            if (i < iArr.length && i2 >= 0) {
                int[] iArr2 = f;
                if (i3 < iArr2.length) {
                    int i4 = iArr[i];
                    if (i4 == 44100) {
                        return (iArr2[i3] + (i2 % 2)) * 2;
                    }
                    int i5 = e[i3];
                    return i4 == 32000 ? i5 * 6 : i5 * 4;
                }
            }
        }
        return -1;
    }
}
