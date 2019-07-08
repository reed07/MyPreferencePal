package com.brightcove.player.util;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.source.hls.HlsManifest;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.List;

public final class MediaSourceUtil {
    public static String getBrightcoveRoleValue(int i) {
        return (i & 4096) != 0 ? C.DASH_ROLE_MAIN_VALUE : (i & 8192) != 0 ? C.DASH_ROLE_ALTERNATE_VALUE : (i & C.DASH_ROLE_CAPTION_FLAG) != 0 ? "caption" : (32768 & i) != 0 ? "subtitle" : (65536 & i) != 0 ? C.DASH_ROLE_SUPPLEMENTARY_VALUE : (131072 & i) != 0 ? C.DASH_ROLE_COMMENTARY_VALUE : (i & C.DASH_ROLE_SUB_FLAG) != 0 ? C.DASH_ROLE_SUB_VALUE : "";
    }

    public static int findTrackType(@NonNull Format format) {
        String str = format.containerMimeType;
        if (MimeTypes.isVideo(str)) {
            return 2;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        return MimeTypes.isText(str) ? 3 : -1;
    }

    @Nullable
    public static String findRenditionUrl(@Nullable Object obj, int i, @NonNull Format format) {
        if (obj instanceof DashManifest) {
            return findRenditionUrl((DashManifest) obj, i, format);
        }
        if (obj instanceof HlsManifest) {
            HlsMasterPlaylist hlsMasterPlaylist = ((HlsManifest) obj).masterPlaylist;
            if (hlsMasterPlaylist != null) {
                return findRenditionUrl(hlsMasterPlaylist, i, format);
            }
        }
        return null;
    }

    @Nullable
    public static String findRenditionUrl(@Nullable Object obj, @NonNull Format format) {
        return findRenditionUrl(obj, findTrackType(format), format);
    }

    @Nullable
    public static String findRenditionUrl(@NonNull DashManifest dashManifest, int i, @NonNull Format format) {
        int periodCount = dashManifest.getPeriodCount();
        for (int i2 = 0; i2 < periodCount; i2++) {
            for (AdaptationSet adaptationSet : dashManifest.getPeriod(i2).adaptationSets) {
                if (adaptationSet.type == i) {
                    for (Representation representation : adaptationSet.representations) {
                        if (format == representation.format) {
                            return findInitializationUri(representation).toString();
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    @NonNull
    public static Uri findInitializationUri(@NonNull Representation representation) {
        String str = representation.baseUrl;
        RangedUri indexUri = representation.getIndexUri();
        RangedUri initializationUri = representation.getInitializationUri();
        if (initializationUri != null) {
            indexUri = initializationUri.attemptMerge(indexUri, str);
            if (indexUri == null) {
                indexUri = initializationUri;
            }
        }
        return indexUri == null ? Uri.parse(representation.baseUrl) : indexUri.resolveUri(str);
    }

    @Nullable
    public static String findRenditionUrl(@NonNull HlsMasterPlaylist hlsMasterPlaylist, int i, @NonNull Format format) {
        List<HlsUrl> list;
        switch (i) {
            case 0:
            case 2:
                list = hlsMasterPlaylist.variants;
                break;
            case 1:
                list = hlsMasterPlaylist.audios;
                break;
            case 3:
                list = hlsMasterPlaylist.subtitles;
                break;
            default:
                List arrayList = new ArrayList();
                arrayList.addAll(hlsMasterPlaylist.variants);
                arrayList.addAll(hlsMasterPlaylist.audios);
                arrayList.addAll(hlsMasterPlaylist.subtitles);
                list = arrayList;
                break;
        }
        for (HlsUrl hlsUrl : list) {
            if (hlsUrl.format == format) {
                return hlsUrl.url;
            }
        }
        return null;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getBrightcoveRoleFlag(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = 0
            switch(r0) {
                case -2060497896: goto L_0x0045;
                case -1408024454: goto L_0x003b;
                case 114240: goto L_0x0031;
                case 3343801: goto L_0x0027;
                case 552573414: goto L_0x001d;
                case 899152809: goto L_0x0013;
                case 1855372047: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x004f
        L_0x0009:
            java.lang.String r0 = "supplementary"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 4
            goto L_0x0050
        L_0x0013:
            java.lang.String r0 = "commentary"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 5
            goto L_0x0050
        L_0x001d:
            java.lang.String r0 = "caption"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 2
            goto L_0x0050
        L_0x0027:
            java.lang.String r0 = "main"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 0
            goto L_0x0050
        L_0x0031:
            java.lang.String r0 = "sub"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 6
            goto L_0x0050
        L_0x003b:
            java.lang.String r0 = "alternate"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 1
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = "subtitle"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004f
            r2 = 3
            goto L_0x0050
        L_0x004f:
            r2 = -1
        L_0x0050:
            switch(r2) {
                case 0: goto L_0x0067;
                case 1: goto L_0x0064;
                case 2: goto L_0x0061;
                case 3: goto L_0x005d;
                case 4: goto L_0x005a;
                case 5: goto L_0x0057;
                case 6: goto L_0x0054;
                default: goto L_0x0053;
            }
        L_0x0053:
            goto L_0x0069
        L_0x0054:
            r1 = 262144(0x40000, float:3.67342E-40)
            goto L_0x0069
        L_0x0057:
            r1 = 131072(0x20000, float:1.83671E-40)
            goto L_0x0069
        L_0x005a:
            r1 = 65536(0x10000, float:9.18355E-41)
            goto L_0x0069
        L_0x005d:
            r1 = 32768(0x8000, float:4.5918E-41)
            goto L_0x0069
        L_0x0061:
            r1 = 16384(0x4000, float:2.2959E-41)
            goto L_0x0069
        L_0x0064:
            r1 = 8192(0x2000, float:1.14794E-41)
            goto L_0x0069
        L_0x0067:
            r1 = 4096(0x1000, float:5.74E-42)
        L_0x0069:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.util.MediaSourceUtil.getBrightcoveRoleFlag(java.lang.String):int");
    }
}
