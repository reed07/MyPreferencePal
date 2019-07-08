package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.android.gms.internal.ads.zzki.zza;
import com.google.common.net.HttpHeaders;

final class zzjm {
    private static final int zzarb = zzqe.zzam("nam");
    private static final int zzarc = zzqe.zzam("trk");
    private static final int zzard = zzqe.zzam("cmt");
    private static final int zzare = zzqe.zzam("day");
    private static final int zzarf = zzqe.zzam("ART");
    private static final int zzarg = zzqe.zzam("too");
    private static final int zzarh = zzqe.zzam("alb");
    private static final int zzari = zzqe.zzam("com");
    private static final int zzarj = zzqe.zzam("wrt");
    private static final int zzark = zzqe.zzam("lyr");
    private static final int zzarl = zzqe.zzam("gen");
    private static final int zzarm = zzqe.zzam("covr");
    private static final int zzarn = zzqe.zzam("gnre");
    private static final int zzaro = zzqe.zzam("grp");
    private static final int zzarp = zzqe.zzam("disk");
    private static final int zzarq = zzqe.zzam("trkn");
    private static final int zzarr = zzqe.zzam("tmpo");
    private static final int zzars = zzqe.zzam("cpil");
    private static final int zzart = zzqe.zzam("aART");
    private static final int zzaru = zzqe.zzam("sonm");
    private static final int zzarv = zzqe.zzam("soal");
    private static final int zzarw = zzqe.zzam("soar");
    private static final int zzarx = zzqe.zzam("soaa");
    private static final int zzary = zzqe.zzam("soco");
    private static final int zzarz = zzqe.zzam("rtng");
    private static final int zzasa = zzqe.zzam("pgap");
    private static final int zzasb = zzqe.zzam("sosn");
    private static final int zzasc = zzqe.zzam("tvsh");
    private static final int zzasd = zzqe.zzam(InternalFrame.ID);
    private static final String[] zzase = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    public static zza zzd(zzpx zzpx) {
        zza zza;
        int position = zzpx.getPosition() + zzpx.readInt();
        int readInt = zzpx.readInt();
        int i = readInt >>> 24;
        zza zza2 = null;
        if (i == 169 || i == 65533) {
            int i2 = 16777215 & readInt;
            if (i2 == zzard) {
                int readInt2 = zzpx.readInt();
                if (zzpx.readInt() == zziv.zzaof) {
                    zzpx.zzbl(8);
                    String zzbm = zzpx.zzbm(readInt2 - 16);
                    zza2 = new zzkm(C.LANGUAGE_UNDETERMINED, zzbm, zzbm);
                } else {
                    String str = "MetadataUtil";
                    String str2 = "Failed to parse comment attribute: ";
                    String valueOf = String.valueOf(zziv.zzah(readInt));
                    Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
                zzpx.setPosition(position);
                return zza2;
            }
            if (i2 != zzarb) {
                if (i2 != zzarc) {
                    if (i2 != zzari) {
                        if (i2 != zzarj) {
                            if (i2 == zzare) {
                                zzkq zza3 = zza(readInt, "TDRC", zzpx);
                                zzpx.setPosition(position);
                                return zza3;
                            } else if (i2 == zzarf) {
                                zzkq zza4 = zza(readInt, "TPE1", zzpx);
                                zzpx.setPosition(position);
                                return zza4;
                            } else if (i2 == zzarg) {
                                zzkq zza5 = zza(readInt, "TSSE", zzpx);
                                zzpx.setPosition(position);
                                return zza5;
                            } else if (i2 == zzarh) {
                                zzkq zza6 = zza(readInt, "TALB", zzpx);
                                zzpx.setPosition(position);
                                return zza6;
                            } else if (i2 == zzark) {
                                zzkq zza7 = zza(readInt, "USLT", zzpx);
                                zzpx.setPosition(position);
                                return zza7;
                            } else if (i2 == zzarl) {
                                zzkq zza8 = zza(readInt, "TCON", zzpx);
                                zzpx.setPosition(position);
                                return zza8;
                            } else if (i2 == zzaro) {
                                zzkq zza9 = zza(readInt, "TIT1", zzpx);
                                zzpx.setPosition(position);
                                return zza9;
                            }
                        }
                    }
                    zzkq zza10 = zza(readInt, "TCOM", zzpx);
                    zzpx.setPosition(position);
                    return zza10;
                }
            }
            zzkq zza11 = zza(readInt, "TIT2", zzpx);
            zzpx.setPosition(position);
            return zza11;
        }
        try {
            if (readInt == zzarn) {
                int zze = zze(zzpx);
                String str3 = (zze <= 0 || zze > zzase.length) ? null : zzase[zze - 1];
                if (str3 != null) {
                    zza = new zzkq("TCON", null, str3);
                } else {
                    Log.w("MetadataUtil", "Failed to parse standard genre code");
                    zza = null;
                }
                return zza;
            } else if (readInt == zzarp) {
                zzkq zzb = zzb(readInt, "TPOS", zzpx);
                zzpx.setPosition(position);
                return zzb;
            } else if (readInt == zzarq) {
                zzkq zzb2 = zzb(readInt, "TRCK", zzpx);
                zzpx.setPosition(position);
                return zzb2;
            } else if (readInt == zzarr) {
                zzkp zza12 = zza(readInt, "TBPM", zzpx, true, false);
                zzpx.setPosition(position);
                return zza12;
            } else if (readInt == zzars) {
                zzkp zza13 = zza(readInt, "TCMP", zzpx, true, true);
                zzpx.setPosition(position);
                return zza13;
            } else if (readInt == zzarm) {
                int readInt3 = zzpx.readInt();
                if (zzpx.readInt() == zziv.zzaof) {
                    int zzag = zziv.zzag(zzpx.readInt());
                    String str4 = zzag == 13 ? "image/jpeg" : zzag == 14 ? "image/png" : null;
                    if (str4 == null) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append("Unrecognized cover art flags: ");
                        sb.append(zzag);
                        Log.w("MetadataUtil", sb.toString());
                    } else {
                        zzpx.zzbl(4);
                        byte[] bArr = new byte[(readInt3 - 16)];
                        zzpx.zze(bArr, 0, bArr.length);
                        zza2 = new zzkk(str4, null, 3, bArr);
                    }
                } else {
                    Log.w("MetadataUtil", "Failed to parse cover art attribute");
                }
                zzpx.setPosition(position);
                return zza2;
            } else if (readInt == zzart) {
                zzkq zza14 = zza(readInt, "TPE2", zzpx);
                zzpx.setPosition(position);
                return zza14;
            } else if (readInt == zzaru) {
                zzkq zza15 = zza(readInt, "TSOT", zzpx);
                zzpx.setPosition(position);
                return zza15;
            } else if (readInt == zzarv) {
                zzkq zza16 = zza(readInt, "TSO2", zzpx);
                zzpx.setPosition(position);
                return zza16;
            } else if (readInt == zzarw) {
                zzkq zza17 = zza(readInt, "TSOA", zzpx);
                zzpx.setPosition(position);
                return zza17;
            } else if (readInt == zzarx) {
                zzkq zza18 = zza(readInt, "TSOP", zzpx);
                zzpx.setPosition(position);
                return zza18;
            } else if (readInt == zzary) {
                zzkq zza19 = zza(readInt, "TSOC", zzpx);
                zzpx.setPosition(position);
                return zza19;
            } else if (readInt == zzarz) {
                zzkp zza20 = zza(readInt, "ITUNESADVISORY", zzpx, false, false);
                zzpx.setPosition(position);
                return zza20;
            } else if (readInt == zzasa) {
                zzkp zza21 = zza(readInt, "ITUNESGAPLESS", zzpx, false, true);
                zzpx.setPosition(position);
                return zza21;
            } else if (readInt == zzasb) {
                zzkq zza22 = zza(readInt, "TVSHOWSORT", zzpx);
                zzpx.setPosition(position);
                return zza22;
            } else if (readInt == zzasc) {
                zzkq zza23 = zza(readInt, "TVSHOW", zzpx);
                zzpx.setPosition(position);
                return zza23;
            } else if (readInt == zzasd) {
                Object obj = null;
                String str5 = null;
                int i3 = -1;
                int i4 = -1;
                while (zzpx.getPosition() < position) {
                    int position2 = zzpx.getPosition();
                    int readInt4 = zzpx.readInt();
                    int readInt5 = zzpx.readInt();
                    zzpx.zzbl(4);
                    if (readInt5 == zziv.zzaod) {
                        obj = zzpx.zzbm(readInt4 - 12);
                    } else if (readInt5 == zziv.zzaoe) {
                        str5 = zzpx.zzbm(readInt4 - 12);
                    } else {
                        if (readInt5 == zziv.zzaof) {
                            i3 = position2;
                            i4 = readInt4;
                        }
                        zzpx.zzbl(readInt4 - 12);
                    }
                }
                if ("com.apple.iTunes".equals(obj) && "iTunSMPB".equals(str5)) {
                    if (i3 != -1) {
                        zzpx.setPosition(i3);
                        zzpx.zzbl(16);
                        zza2 = new zzkm(C.LANGUAGE_UNDETERMINED, str5, zzpx.zzbm(i4 - 16));
                    }
                }
                zzpx.setPosition(position);
                return zza2;
            }
        } finally {
            zzpx.setPosition(position);
        }
        String str6 = "MetadataUtil";
        String str7 = "Skipped unknown metadata entry: ";
        String valueOf2 = String.valueOf(zziv.zzah(readInt));
        Log.d(str6, valueOf2.length() != 0 ? str7.concat(valueOf2) : new String(str7));
        zzpx.setPosition(position);
        return null;
    }

    private static zzkq zza(int i, String str, zzpx zzpx) {
        int readInt = zzpx.readInt();
        if (zzpx.readInt() == zziv.zzaof) {
            zzpx.zzbl(8);
            return new zzkq(str, null, zzpx.zzbm(readInt - 16));
        }
        String str2 = "MetadataUtil";
        String str3 = "Failed to parse text attribute: ";
        String valueOf = String.valueOf(zziv.zzah(i));
        Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return null;
    }

    private static zzkp zza(int i, String str, zzpx zzpx, boolean z, boolean z2) {
        int zze = zze(zzpx);
        if (z2) {
            zze = Math.min(1, zze);
        }
        if (zze < 0) {
            String str2 = "MetadataUtil";
            String str3 = "Failed to parse uint8 attribute: ";
            String valueOf = String.valueOf(zziv.zzah(i));
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        } else if (z) {
            return new zzkq(str, null, Integer.toString(zze));
        } else {
            return new zzkm(C.LANGUAGE_UNDETERMINED, str, Integer.toString(zze));
        }
    }

    private static zzkq zzb(int i, String str, zzpx zzpx) {
        int readInt = zzpx.readInt();
        if (zzpx.readInt() == zziv.zzaof && readInt >= 22) {
            zzpx.zzbl(10);
            int readUnsignedShort = zzpx.readUnsignedShort();
            if (readUnsignedShort > 0) {
                StringBuilder sb = new StringBuilder(11);
                sb.append(readUnsignedShort);
                String sb2 = sb.toString();
                int readUnsignedShort2 = zzpx.readUnsignedShort();
                if (readUnsignedShort2 > 0) {
                    String valueOf = String.valueOf(sb2);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 12);
                    sb3.append(valueOf);
                    sb3.append("/");
                    sb3.append(readUnsignedShort2);
                    sb2 = sb3.toString();
                }
                return new zzkq(str, null, sb2);
            }
        }
        String str2 = "MetadataUtil";
        String str3 = "Failed to parse index/count attribute: ";
        String valueOf2 = String.valueOf(zziv.zzah(i));
        Log.w(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
        return null;
    }

    private static int zze(zzpx zzpx) {
        zzpx.zzbl(4);
        if (zzpx.readInt() == zziv.zzaof) {
            zzpx.zzbl(8);
            return zzpx.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
