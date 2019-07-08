package com.google.android.exoplayer2.trackselection;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public abstract class MappingTrackSelector extends TrackSelector {
    @Nullable
    private MappedTrackInfo currentMappedTrackInfo;

    public static final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        @Deprecated
        public final int length = this.rendererCount;
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        MappedTrackInfo(int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererTrackTypes = iArr;
            this.rendererTrackGroups = trackGroupArrayArr;
            this.rendererFormatSupports = iArr3;
            this.rendererMixedMimeTypeAdaptiveSupports = iArr2;
            this.unmappedTrackGroups = trackGroupArray;
            this.rendererCount = iArr.length;
        }

        public int getRendererCount() {
            return this.rendererCount;
        }

        public int getRendererType(int i) {
            return this.rendererTrackTypes[i];
        }

        public TrackGroupArray getTrackGroups(int i) {
            return this.rendererTrackGroups[i];
        }

        public int getRendererSupport(int i) {
            int i2;
            int[][] iArr = this.rendererFormatSupports[i];
            int i3 = 0;
            int i4 = 0;
            while (i3 < iArr.length) {
                int i5 = i4;
                for (int i6 : iArr[i3]) {
                    switch (i6 & 7) {
                        case 3:
                            i2 = 2;
                            break;
                        case 4:
                            return 3;
                        default:
                            i2 = 1;
                            break;
                    }
                    i5 = Math.max(i5, i2);
                }
                i3++;
                i4 = i5;
            }
            return i4;
        }

        @Deprecated
        public int getTrackTypeRendererSupport(int i) {
            return getTypeSupport(i);
        }

        public int getTypeSupport(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.rendererCount; i3++) {
                if (this.rendererTrackTypes[i3] == i) {
                    i2 = Math.max(i2, getRendererSupport(i3));
                }
            }
            return i2;
        }

        @Deprecated
        public int getTrackFormatSupport(int i, int i2, int i3) {
            return getTrackSupport(i, i2, i3);
        }

        public int getTrackSupport(int i, int i2, int i3) {
            return this.rendererFormatSupports[i][i2][i3] & 7;
        }

        public int getAdaptiveSupport(int i, int i2, boolean z) {
            int i3 = this.rendererTrackGroups[i].get(i2).length;
            int[] iArr = new int[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                int trackSupport = getTrackSupport(i, i2, i5);
                if (trackSupport == 4 || (z && trackSupport == 3)) {
                    int i6 = i4 + 1;
                    iArr[i4] = i5;
                    i4 = i6;
                }
            }
            return getAdaptiveSupport(i, i2, Arrays.copyOf(iArr, i4));
        }

        public int getAdaptiveSupport(int i, int i2, int[] iArr) {
            int i3 = 0;
            Object obj = null;
            boolean z = false;
            int i4 = 0;
            int i5 = 16;
            while (i3 < iArr.length) {
                String str = this.rendererTrackGroups[i].get(i2).getFormat(iArr[i3]).sampleMimeType;
                int i6 = i4 + 1;
                if (i4 == 0) {
                    obj = str;
                } else {
                    z |= !Util.areEqual(obj, str);
                }
                i5 = Math.min(i5, this.rendererFormatSupports[i][i2][i3] & 24);
                i3++;
                i4 = i6;
            }
            return z ? Math.min(i5, this.rendererMixedMimeTypeAdaptiveSupports[i]) : i5;
        }

        @Deprecated
        public TrackGroupArray getUnassociatedTrackGroups() {
            return getUnmappedTrackGroups();
        }

        public TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Pair<RendererConfiguration[], TrackSelection[]> selectTracks(MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2) throws ExoPlaybackException;

    @Nullable
    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray) throws ExoPlaybackException {
        int[] iArr;
        int[] iArr2 = new int[(rendererCapabilitiesArr.length + 1)];
        TrackGroup[][] trackGroupArr = new TrackGroup[(rendererCapabilitiesArr.length + 1)][];
        int[][][] iArr3 = new int[(rendererCapabilitiesArr.length + 1)][][];
        for (int i = 0; i < trackGroupArr.length; i++) {
            trackGroupArr[i] = new TrackGroup[trackGroupArray.length];
            iArr3[i] = new int[trackGroupArray.length][];
        }
        int[] mixedMimeTypeAdaptationSupports = getMixedMimeTypeAdaptationSupports(rendererCapabilitiesArr);
        for (int i2 = 0; i2 < trackGroupArray.length; i2++) {
            TrackGroup trackGroup = trackGroupArray.get(i2);
            int findRenderer = findRenderer(rendererCapabilitiesArr, trackGroup);
            if (findRenderer == rendererCapabilitiesArr.length) {
                iArr = new int[trackGroup.length];
            } else {
                iArr = getFormatSupport(rendererCapabilitiesArr[findRenderer], trackGroup);
            }
            int i3 = iArr2[findRenderer];
            trackGroupArr[findRenderer][i3] = trackGroup;
            iArr3[findRenderer][i3] = iArr;
            iArr2[findRenderer] = iArr2[findRenderer] + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr.length];
        int[] iArr4 = new int[rendererCapabilitiesArr.length];
        for (int i4 = 0; i4 < rendererCapabilitiesArr.length; i4++) {
            int i5 = iArr2[i4];
            trackGroupArrayArr[i4] = new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[i4], i5));
            iArr3[i4] = (int[][]) Util.nullSafeArrayCopy(iArr3[i4], i5);
            iArr4[i4] = rendererCapabilitiesArr[i4].getTrackType();
        }
        int[] iArr5 = mixedMimeTypeAdaptationSupports;
        int[][][] iArr6 = iArr3;
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(iArr4, trackGroupArrayArr, iArr5, iArr6, new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[rendererCapabilitiesArr.length], iArr2[rendererCapabilitiesArr.length])));
        Pair selectTracks = selectTracks(mappedTrackInfo, iArr3, mixedMimeTypeAdaptationSupports);
        return new TrackSelectorResult((RendererConfiguration[]) selectTracks.first, (TrackSelection[]) selectTracks.second, mappedTrackInfo);
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int i = 0;
        int i2 = 0;
        while (i < rendererCapabilitiesArr.length) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i];
            int i3 = i2;
            int i4 = length;
            for (int i5 = 0; i5 < trackGroup.length; i5++) {
                int supportsFormat = rendererCapabilities.supportsFormat(trackGroup.getFormat(i5)) & 7;
                if (supportsFormat > i3) {
                    if (supportsFormat == 4) {
                        return i;
                    }
                    i4 = i;
                    i3 = supportsFormat;
                }
            }
            i++;
            length = i4;
            i2 = i3;
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] iArr = new int[trackGroup.length];
        for (int i = 0; i < trackGroup.length; i++) {
            iArr[i] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int[] iArr = new int[rendererCapabilitiesArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = rendererCapabilitiesArr[i].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }
}
