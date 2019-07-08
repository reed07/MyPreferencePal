package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.ads.interactivemedia.v3.internal.js.a;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.common.net.HttpHeaders;
import java.nio.ByteBuffer;

/* compiled from: IMASDK */
final class hl {
    private static final int A = vf.h("sosn");
    private static final int B = vf.h("tvsh");
    private static final int C = vf.h(InternalFrame.ID);
    private static final String[] D = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    private static final int a = vf.h("nam");
    private static final int b = vf.h("trk");
    private static final int c = vf.h("cmt");
    private static final int d = vf.h("day");
    private static final int e = vf.h("ART");
    private static final int f = vf.h("too");
    private static final int g = vf.h("alb");
    private static final int h = vf.h("com");
    private static final int i = vf.h("wrt");
    private static final int j = vf.h("lyr");
    private static final int k = vf.h("gen");
    private static final int l = vf.h("covr");
    private static final int m = vf.h("gnre");
    private static final int n = vf.h("grp");
    private static final int o = vf.h("disk");
    private static final int p = vf.h("trkn");
    private static final int q = vf.h("tmpo");
    private static final int r = vf.h("cpil");
    private static final int s = vf.h("aART");
    private static final int t = vf.h("sonm");
    private static final int u = vf.h("soal");
    private static final int v = vf.h("soar");
    private static final int w = vf.h("soaa");
    private static final int x = vf.h("soco");
    private static final int y = vf.h("rtng");
    private static final int z = vf.h("pgap");

    public static bs a(int i2, bs bsVar, js jsVar, js jsVar2, fu fuVar) {
        if (i2 == 1) {
            if (fuVar.a()) {
                bsVar = bsVar.a(fuVar.a, fuVar.b);
            }
            if (jsVar != null) {
                return bsVar.a(jsVar);
            }
            return bsVar;
        } else if (i2 != 2 || jsVar2 == null) {
            return bsVar;
        } else {
            bs bsVar2 = bsVar;
            for (int i3 = 0; i3 < jsVar2.a(); i3++) {
                a a2 = jsVar2.a(i3);
                if (a2 instanceof hj) {
                    hj hjVar = (hj) a2;
                    if ("com.android.capture.fps".equals(hjVar.a) && hjVar.c == 23) {
                        try {
                            bsVar2 = bsVar2.a(ByteBuffer.wrap(hjVar.b).asFloatBuffer().get()).a(new js(hjVar));
                        } catch (NumberFormatException unused) {
                            Log.w("MetadataUtil", "Ignoring invalid framerate");
                        }
                    }
                }
            }
            return bsVar2;
        }
    }

    public static a a(ut utVar) {
        a aVar;
        int d2 = utVar.d() + utVar.l();
        int l2 = utVar.l();
        int i2 = l2 >>> 24;
        a aVar2 = null;
        if (i2 == 169 || i2 == 253) {
            int i3 = 16777215 & l2;
            if (i3 == c) {
                int l3 = utVar.l();
                if (utVar.l() == gu.aQ) {
                    utVar.d(8);
                    String f2 = utVar.f(l3 - 16);
                    aVar2 = new ki(C.LANGUAGE_UNDETERMINED, f2, f2);
                } else {
                    String str = "MetadataUtil";
                    String str2 = "Failed to parse comment attribute: ";
                    String valueOf = String.valueOf(gu.c(l2));
                    Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
                utVar.c(d2);
                return aVar2;
            }
            if (i3 != a) {
                if (i3 != b) {
                    if (i3 != h) {
                        if (i3 != i) {
                            if (i3 == d) {
                                kw a2 = a(l2, "TDRC", utVar);
                                utVar.c(d2);
                                return a2;
                            } else if (i3 == e) {
                                kw a3 = a(l2, "TPE1", utVar);
                                utVar.c(d2);
                                return a3;
                            } else if (i3 == f) {
                                kw a4 = a(l2, "TSSE", utVar);
                                utVar.c(d2);
                                return a4;
                            } else if (i3 == g) {
                                kw a5 = a(l2, "TALB", utVar);
                                utVar.c(d2);
                                return a5;
                            } else if (i3 == j) {
                                kw a6 = a(l2, "USLT", utVar);
                                utVar.c(d2);
                                return a6;
                            } else if (i3 == k) {
                                kw a7 = a(l2, "TCON", utVar);
                                utVar.c(d2);
                                return a7;
                            } else if (i3 == n) {
                                kw a8 = a(l2, "TIT1", utVar);
                                utVar.c(d2);
                                return a8;
                            }
                        }
                    }
                    kw a9 = a(l2, "TCOM", utVar);
                    utVar.c(d2);
                    return a9;
                }
            }
            kw a10 = a(l2, "TIT2", utVar);
            utVar.c(d2);
            return a10;
        }
        try {
            if (l2 == m) {
                int b2 = b(utVar);
                String str3 = (b2 <= 0 || b2 > D.length) ? null : D[b2 - 1];
                if (str3 != null) {
                    aVar = new kw("TCON", null, str3);
                } else {
                    Log.w("MetadataUtil", "Failed to parse standard genre code");
                    aVar = null;
                }
                return aVar;
            } else if (l2 == o) {
                kw b3 = b(l2, "TPOS", utVar);
                utVar.c(d2);
                return b3;
            } else if (l2 == p) {
                kw b4 = b(l2, "TRCK", utVar);
                utVar.c(d2);
                return b4;
            } else if (l2 == q) {
                kp a11 = a(l2, "TBPM", utVar, true, false);
                utVar.c(d2);
                return a11;
            } else if (l2 == r) {
                kp a12 = a(l2, "TCMP", utVar, true, true);
                utVar.c(d2);
                return a12;
            } else if (l2 == l) {
                int l4 = utVar.l();
                if (utVar.l() == gu.aQ) {
                    int b5 = gu.b(utVar.l());
                    String str4 = b5 == 13 ? "image/jpeg" : b5 == 14 ? "image/png" : null;
                    if (str4 == null) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append("Unrecognized cover art flags: ");
                        sb.append(b5);
                        Log.w("MetadataUtil", sb.toString());
                    } else {
                        utVar.d(4);
                        byte[] bArr = new byte[(l4 - 16)];
                        utVar.a(bArr, 0, bArr.length);
                        aVar2 = new ka(str4, null, 3, bArr);
                    }
                } else {
                    Log.w("MetadataUtil", "Failed to parse cover art attribute");
                }
                utVar.c(d2);
                return aVar2;
            } else if (l2 == s) {
                kw a13 = a(l2, "TPE2", utVar);
                utVar.c(d2);
                return a13;
            } else if (l2 == t) {
                kw a14 = a(l2, "TSOT", utVar);
                utVar.c(d2);
                return a14;
            } else if (l2 == u) {
                kw a15 = a(l2, "TSO2", utVar);
                utVar.c(d2);
                return a15;
            } else if (l2 == v) {
                kw a16 = a(l2, "TSOA", utVar);
                utVar.c(d2);
                return a16;
            } else if (l2 == w) {
                kw a17 = a(l2, "TSOP", utVar);
                utVar.c(d2);
                return a17;
            } else if (l2 == x) {
                kw a18 = a(l2, "TSOC", utVar);
                utVar.c(d2);
                return a18;
            } else if (l2 == y) {
                kp a19 = a(l2, "ITUNESADVISORY", utVar, false, false);
                utVar.c(d2);
                return a19;
            } else if (l2 == z) {
                kp a20 = a(l2, "ITUNESGAPLESS", utVar, false, true);
                utVar.c(d2);
                return a20;
            } else if (l2 == A) {
                kw a21 = a(l2, "TVSHOWSORT", utVar);
                utVar.c(d2);
                return a21;
            } else if (l2 == B) {
                kw a22 = a(l2, "TVSHOW", utVar);
                utVar.c(d2);
                return a22;
            } else if (l2 == C) {
                String str5 = null;
                String str6 = null;
                int i4 = -1;
                int i5 = -1;
                while (utVar.d() < d2) {
                    int d3 = utVar.d();
                    int l5 = utVar.l();
                    int l6 = utVar.l();
                    utVar.d(4);
                    if (l6 == gu.aO) {
                        str5 = utVar.f(l5 - 12);
                    } else if (l6 == gu.aP) {
                        str6 = utVar.f(l5 - 12);
                    } else {
                        if (l6 == gu.aQ) {
                            i4 = d3;
                            i5 = l5;
                        }
                        utVar.d(l5 - 12);
                    }
                }
                if (!(str5 == null || str6 == null)) {
                    if (i4 != -1) {
                        utVar.c(i4);
                        utVar.d(16);
                        aVar2 = new kq(str5, str6, utVar.f(i5 - 16));
                    }
                }
                utVar.c(d2);
                return aVar2;
            }
        } finally {
            utVar.c(d2);
        }
        String str7 = "MetadataUtil";
        String str8 = "Skipped unknown metadata entry: ";
        String valueOf2 = String.valueOf(gu.c(l2));
        Log.d(str7, valueOf2.length() != 0 ? str8.concat(valueOf2) : new String(str8));
        utVar.c(d2);
        return null;
    }

    public static hj a(ut utVar, int i2, String str) {
        while (true) {
            int d2 = utVar.d();
            if (d2 >= i2) {
                return null;
            }
            int l2 = utVar.l();
            if (utVar.l() == gu.aQ) {
                int l3 = utVar.l();
                int l4 = utVar.l();
                int i3 = l2 - 16;
                byte[] bArr = new byte[i3];
                utVar.a(bArr, 0, i3);
                return new hj(str, bArr, l4, l3);
            }
            utVar.c(d2 + l2);
        }
    }

    private static kw a(int i2, String str, ut utVar) {
        int l2 = utVar.l();
        if (utVar.l() == gu.aQ) {
            utVar.d(8);
            return new kw(str, null, utVar.f(l2 - 16));
        }
        String str2 = "MetadataUtil";
        String str3 = "Failed to parse text attribute: ";
        String valueOf = String.valueOf(gu.c(i2));
        Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return null;
    }

    private static kp a(int i2, String str, ut utVar, boolean z2, boolean z3) {
        int b2 = b(utVar);
        if (z3) {
            b2 = Math.min(1, b2);
        }
        if (b2 < 0) {
            String str2 = "MetadataUtil";
            String str3 = "Failed to parse uint8 attribute: ";
            String valueOf = String.valueOf(gu.c(i2));
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        } else if (z2) {
            return new kw(str, null, Integer.toString(b2));
        } else {
            return new ki(C.LANGUAGE_UNDETERMINED, str, Integer.toString(b2));
        }
    }

    private static kw b(int i2, String str, ut utVar) {
        int l2 = utVar.l();
        if (utVar.l() == gu.aQ && l2 >= 22) {
            utVar.d(10);
            int f2 = utVar.f();
            if (f2 > 0) {
                StringBuilder sb = new StringBuilder(11);
                sb.append(f2);
                String sb2 = sb.toString();
                int f3 = utVar.f();
                if (f3 > 0) {
                    String valueOf = String.valueOf(sb2);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 12);
                    sb3.append(valueOf);
                    sb3.append("/");
                    sb3.append(f3);
                    sb2 = sb3.toString();
                }
                return new kw(str, null, sb2);
            }
        }
        String str2 = "MetadataUtil";
        String str3 = "Failed to parse index/count attribute: ";
        String valueOf2 = String.valueOf(gu.c(i2));
        Log.w(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
        return null;
    }

    private static int b(ut utVar) {
        utVar.d(4);
        if (utVar.l() == gu.aQ) {
            utVar.d(8);
            return utVar.e();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
