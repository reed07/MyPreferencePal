package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class HlsMasterPlaylist extends HlsPlaylist {
    public static final HlsMasterPlaylist EMPTY;
    public static final int GROUP_INDEX_AUDIO = 1;
    public static final int GROUP_INDEX_SUBTITLE = 2;
    public static final int GROUP_INDEX_VARIANT = 0;
    public final List<HlsUrl> audios;
    public final Format muxedAudioFormat;
    public final List<Format> muxedCaptionFormats;
    public final List<HlsUrl> subtitles;
    public final Map<String, String> variableDefinitions;
    public final List<HlsUrl> variants;

    public static final class HlsUrl {
        public final Format format;
        public final String url;

        public static HlsUrl createMediaPlaylistHlsUrl(String str) {
            return new HlsUrl(str, Format.createContainerFormat("0", null, MimeTypes.APPLICATION_M3U8, null, null, -1, 0, null));
        }

        public HlsUrl(String str, Format format2) {
            this.url = str;
            this.format = format2;
        }
    }

    static {
        HlsMasterPlaylist hlsMasterPlaylist = new HlsMasterPlaylist("", Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, Collections.emptyList(), false, Collections.emptyMap());
        EMPTY = hlsMasterPlaylist;
    }

    public HlsMasterPlaylist(String str, List<String> list, List<HlsUrl> list2, List<HlsUrl> list3, List<HlsUrl> list4, Format format, List<Format> list5, boolean z, Map<String, String> map) {
        super(str, list, z);
        this.variants = Collections.unmodifiableList(list2);
        this.audios = Collections.unmodifiableList(list3);
        this.subtitles = Collections.unmodifiableList(list4);
        this.muxedAudioFormat = format;
        this.muxedCaptionFormats = list5 != null ? Collections.unmodifiableList(list5) : null;
        this.variableDefinitions = Collections.unmodifiableMap(map);
    }

    public HlsMasterPlaylist copy(List<StreamKey> list) {
        HlsMasterPlaylist hlsMasterPlaylist = new HlsMasterPlaylist(this.baseUri, this.tags, copyRenditionsList(this.variants, 0, list), copyRenditionsList(this.audios, 1, list), copyRenditionsList(this.subtitles, 2, list), this.muxedAudioFormat, this.muxedCaptionFormats, this.hasIndependentSegments, this.variableDefinitions);
        return hlsMasterPlaylist;
    }

    public static HlsMasterPlaylist createSingleVariantMasterPlaylist(String str) {
        List singletonList = Collections.singletonList(HlsUrl.createMediaPlaylistHlsUrl(str));
        List emptyList = Collections.emptyList();
        HlsMasterPlaylist hlsMasterPlaylist = new HlsMasterPlaylist(null, Collections.emptyList(), singletonList, emptyList, emptyList, null, null, false, Collections.emptyMap());
        return hlsMasterPlaylist;
    }

    private static List<HlsUrl> copyRenditionsList(List<HlsUrl> list, int i, List<StreamKey> list2) {
        ArrayList arrayList = new ArrayList(list2.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            HlsUrl hlsUrl = (HlsUrl) list.get(i2);
            int i3 = 0;
            while (true) {
                if (i3 >= list2.size()) {
                    break;
                }
                StreamKey streamKey = (StreamKey) list2.get(i3);
                if (streamKey.groupIndex == i && streamKey.trackIndex == i2) {
                    arrayList.add(hlsUrl);
                    break;
                }
                i3++;
            }
        }
        return arrayList;
    }
}
