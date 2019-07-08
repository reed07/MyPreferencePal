package com.brightcove.player.dash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.MediaFormat;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.VideoFormatSelectorUtil;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DashUtil {
    private static final String TAG = "DashUtil";

    public static double ceilDivide(double d, double d2) {
        return ((d + d2) - 1.0d) / d2;
    }

    private DashUtil() {
    }

    @Nullable
    public static Representation getHighestRepresentation(@NonNull AdaptationSet adaptationSet) {
        return getHighestRepresentation(adaptationSet.representations);
    }

    public static List<Representation> getVideoRepresentationList(@NonNull Context context, AdaptationSet adaptationSet) {
        List<Representation> emptyList = Collections.emptyList();
        if (adaptationSet.type != 2) {
            return emptyList;
        }
        int[] iArr = null;
        try {
            iArr = VideoFormatSelectorUtil.selectVideoFormatsForDefaultDisplay(context, adaptationSet.representations, null, false);
        } catch (DecoderQueryException e) {
            Log.w(TAG, "Decoder query exception while getting highest rendition possible for default display.", e);
        }
        if (iArr != null && iArr.length > 0) {
            emptyList = new ArrayList<>();
            List<Representation> list = adaptationSet.representations;
            for (int i : iArr) {
                emptyList.add(list.get(i));
            }
        }
        return emptyList;
    }

    @Nullable
    public static Representation getHighestRepresentation(@NonNull List<Representation> list) {
        Representation representation = null;
        for (Representation representation2 : list) {
            if (representation == null || representation2.format.bitrate > representation.format.bitrate) {
                representation = representation2;
            }
        }
        return representation;
    }

    @Nullable
    public static Representation findRepresentationByBitrate(@NonNull List<Representation> list, int i) {
        Collections.sort(list, new Comparator<Representation>() {
            public int compare(Representation representation, Representation representation2) {
                if (representation.format == null || representation2.format == null) {
                    return 0;
                }
                return representation.format.bitrate - representation2.format.bitrate;
            }
        });
        Representation representation = null;
        for (Representation representation2 : list) {
            if (representation2.format.bitrate > i) {
                return representation == null ? representation2 : representation;
            }
            representation = representation2;
        }
        return representation;
    }

    public static void replaceVideoSourceUri(@NonNull Video video, @NonNull String str) {
        for (Source properties : ((SourceCollection) video.getSourceCollections().get(DeliveryType.DASH)).getSources()) {
            properties.getProperties().put("url", str);
        }
    }

    @Nullable
    public static MediaFormat getTrackFormat(int i, Format format, String str, long j) {
        switch (i) {
            case 1:
                return MediaFormat.createAudioFormat(format.id, str, format.bitrate, -1, j, format.channelCount, format.sampleRate, format.initializationData, format.language);
            case 2:
                return MediaFormat.createVideoFormat(format.id, str, format.bitrate, -1, j, format.width, format.height, format.initializationData);
            case 3:
                return MediaFormat.createTextFormat(format.id, str, format.bitrate, j, format.language);
            default:
                return null;
        }
    }

    public static String getMediaMimeType(@Nullable Format format) {
        if (format != null) {
            String str = format.containerMimeType;
            if (MimeTypes.isAudio(str)) {
                return MimeTypes.getAudioMediaMimeType(format.codecs);
            }
            if (MimeTypes.isVideo(str)) {
                return MimeTypes.getVideoMediaMimeType(format.codecs);
            }
            if (MimeTypes.TEXT_VTT.equals(str) || MimeTypes.APPLICATION_TTML.equals(str)) {
                return str;
            }
            if (MimeTypes.APPLICATION_MP4.equals(str)) {
                if ("stpp".equals(format.codecs)) {
                    return MimeTypes.APPLICATION_TTML;
                }
                if ("wvtt".equals(format.codecs)) {
                    return MimeTypes.APPLICATION_MP4VTT;
                }
            }
        }
        return null;
    }
}
