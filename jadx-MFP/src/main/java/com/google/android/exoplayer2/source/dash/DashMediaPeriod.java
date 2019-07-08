package com.google.android.exoplayer2.source.dash;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.SequenceableLoader.Callback;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream.EmbeddedSampleStream;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream.ReleaseCallback;
import com.google.android.exoplayer2.source.dash.DashChunkSource.Factory;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler.PlayerEmsgCallback;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler.PlayerTrackEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.Descriptor;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

final class DashMediaPeriod implements MediaPeriod, Callback<ChunkSampleStream<DashChunkSource>>, ReleaseCallback<DashChunkSource> {
    private final Allocator allocator;
    @Nullable
    private MediaPeriod.Callback callback;
    private final Factory chunkSourceFactory;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final long elapsedRealtimeOffset;
    private final EventDispatcher eventDispatcher;
    private EventSampleStream[] eventSampleStreams = new EventSampleStream[0];
    private List<EventStream> eventStreams;
    final int id;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private boolean notifiedReadingStarted;
    private int periodIndex;
    private final PlayerEmsgHandler playerEmsgHandler;
    private ChunkSampleStream<DashChunkSource>[] sampleStreams = newSampleStreamArray(0);
    private final IdentityHashMap<ChunkSampleStream<DashChunkSource>, PlayerTrackEmsgHandler> trackEmsgHandlerBySampleStream = new IdentityHashMap<>();
    private final TrackGroupInfo[] trackGroupInfos;
    private final TrackGroupArray trackGroups;
    @Nullable
    private final TransferListener transferListener;

    private static final class TrackGroupInfo {
        private static final int CATEGORY_EMBEDDED = 1;
        private static final int CATEGORY_MANIFEST_EVENTS = 2;
        private static final int CATEGORY_PRIMARY = 0;
        public final int[] adaptationSetIndices;
        public final int embeddedCea608TrackGroupIndex;
        public final int embeddedEventMessageTrackGroupIndex;
        public final int eventStreamGroupIndex;
        public final int primaryTrackGroupIndex;
        public final int trackGroupCategory;
        public final int trackType;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface TrackGroupCategory {
        }

        public static TrackGroupInfo primaryTrack(int i, int[] iArr, int i2, int i3, int i4) {
            TrackGroupInfo trackGroupInfo = new TrackGroupInfo(i, 0, iArr, i2, i3, i4, -1);
            return trackGroupInfo;
        }

        public static TrackGroupInfo embeddedEmsgTrack(int[] iArr, int i) {
            TrackGroupInfo trackGroupInfo = new TrackGroupInfo(4, 1, iArr, i, -1, -1, -1);
            return trackGroupInfo;
        }

        public static TrackGroupInfo embeddedCea608Track(int[] iArr, int i) {
            TrackGroupInfo trackGroupInfo = new TrackGroupInfo(3, 1, iArr, i, -1, -1, -1);
            return trackGroupInfo;
        }

        public static TrackGroupInfo mpdEventTrack(int i) {
            TrackGroupInfo trackGroupInfo = new TrackGroupInfo(4, 2, null, -1, -1, -1, i);
            return trackGroupInfo;
        }

        private TrackGroupInfo(int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) {
            this.trackType = i;
            this.adaptationSetIndices = iArr;
            this.trackGroupCategory = i2;
            this.primaryTrackGroupIndex = i3;
            this.embeddedEventMessageTrackGroupIndex = i4;
            this.embeddedCea608TrackGroupIndex = i5;
            this.eventStreamGroupIndex = i6;
        }
    }

    public DashMediaPeriod(int i, DashManifest dashManifest, int i2, Factory factory, @Nullable TransferListener transferListener2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, EventDispatcher eventDispatcher2, long j, LoaderErrorThrower loaderErrorThrower, Allocator allocator2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, PlayerEmsgCallback playerEmsgCallback) {
        this.id = i;
        this.manifest = dashManifest;
        this.periodIndex = i2;
        this.chunkSourceFactory = factory;
        this.transferListener = transferListener2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher2;
        this.elapsedRealtimeOffset = j;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.allocator = allocator2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.playerEmsgHandler = new PlayerEmsgHandler(dashManifest, playerEmsgCallback, allocator2);
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.createCompositeSequenceableLoader(this.sampleStreams);
        Period period = dashManifest.getPeriod(i2);
        this.eventStreams = period.eventStreams;
        Pair buildTrackGroups = buildTrackGroups(period.adaptationSets, this.eventStreams);
        this.trackGroups = (TrackGroupArray) buildTrackGroups.first;
        this.trackGroupInfos = (TrackGroupInfo[]) buildTrackGroups.second;
        eventDispatcher2.mediaPeriodCreated();
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        EventSampleStream[] eventSampleStreamArr;
        this.manifest = dashManifest;
        this.periodIndex = i;
        this.playerEmsgHandler.updateManifest(dashManifest);
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr = this.sampleStreams;
        if (chunkSampleStreamArr != null) {
            for (ChunkSampleStream<DashChunkSource> chunkSource : chunkSampleStreamArr) {
                ((DashChunkSource) chunkSource.getChunkSource()).updateManifest(dashManifest, i);
            }
            this.callback.onContinueLoadingRequested(this);
        }
        this.eventStreams = dashManifest.getPeriod(i).eventStreams;
        for (EventSampleStream eventSampleStream : this.eventSampleStreams) {
            Iterator it = this.eventStreams.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EventStream eventStream = (EventStream) it.next();
                if (eventStream.id().equals(eventSampleStream.eventStreamId())) {
                    boolean z = true;
                    int periodCount = dashManifest.getPeriodCount() - 1;
                    if (!dashManifest.dynamic || i != periodCount) {
                        z = false;
                    }
                    eventSampleStream.updateEventStream(eventStream, z);
                }
            }
        }
    }

    public void release() {
        this.playerEmsgHandler.release();
        for (ChunkSampleStream<DashChunkSource> release : this.sampleStreams) {
            release.release(this);
        }
        this.callback = null;
        this.eventDispatcher.mediaPeriodReleased();
    }

    public synchronized void onSampleStreamReleased(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        PlayerTrackEmsgHandler playerTrackEmsgHandler = (PlayerTrackEmsgHandler) this.trackEmsgHandlerBySampleStream.remove(chunkSampleStream);
        if (playerTrackEmsgHandler != null) {
            playerTrackEmsgHandler.release();
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        callback2.onPrepared(this);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int[] streamIndexToTrackGroupIndex = getStreamIndexToTrackGroupIndex(trackSelectionArr);
        releaseDisabledStreams(trackSelectionArr, zArr, sampleStreamArr);
        releaseOrphanEmbeddedStreams(trackSelectionArr, sampleStreamArr, streamIndexToTrackGroupIndex);
        selectNewStreams(trackSelectionArr, sampleStreamArr, zArr2, j, streamIndexToTrackGroupIndex);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ChunkSampleStream chunkSampleStream : sampleStreamArr) {
            if (chunkSampleStream instanceof ChunkSampleStream) {
                arrayList.add(chunkSampleStream);
            } else if (chunkSampleStream instanceof EventSampleStream) {
                arrayList2.add((EventSampleStream) chunkSampleStream);
            }
        }
        this.sampleStreams = newSampleStreamArray(arrayList.size());
        arrayList.toArray(this.sampleStreams);
        this.eventSampleStreams = new EventSampleStream[arrayList2.size()];
        arrayList2.toArray(this.eventSampleStreams);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.sampleStreams);
        return j;
    }

    public void discardBuffer(long j, boolean z) {
        for (ChunkSampleStream<DashChunkSource> discardBuffer : this.sampleStreams) {
            discardBuffer.discardBuffer(j, z);
        }
    }

    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    public boolean continueLoading(long j) {
        return this.compositeSequenceableLoader.continueLoading(j);
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public long readDiscontinuity() {
        if (!this.notifiedReadingStarted) {
            this.eventDispatcher.readingStarted();
            this.notifiedReadingStarted = true;
        }
        return -9223372036854775807L;
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long seekToUs(long j) {
        for (ChunkSampleStream<DashChunkSource> seekToUs : this.sampleStreams) {
            seekToUs.seekToUs(j);
        }
        for (EventSampleStream seekToUs2 : this.eventSampleStreams) {
            seekToUs2.seekToUs(j);
        }
        return j;
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr;
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            if (chunkSampleStream.primaryTrackType == 2) {
                return chunkSampleStream.getAdjustedSeekPositionUs(j, seekParameters);
            }
        }
        return j;
    }

    public void onContinueLoadingRequested(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        this.callback.onContinueLoadingRequested(this);
    }

    private int[] getStreamIndexToTrackGroupIndex(TrackSelection[] trackSelectionArr) {
        int[] iArr = new int[trackSelectionArr.length];
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (trackSelectionArr[i] != null) {
                iArr[i] = this.trackGroups.indexOf(trackSelectionArr[i].getTrackGroup());
            } else {
                iArr[i] = -1;
            }
        }
        return iArr;
    }

    private void releaseDisabledStreams(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr) {
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (trackSelectionArr[i] == null || !zArr[i]) {
                if (sampleStreamArr[i] instanceof ChunkSampleStream) {
                    sampleStreamArr[i].release(this);
                } else if (sampleStreamArr[i] instanceof EmbeddedSampleStream) {
                    sampleStreamArr[i].release();
                }
                sampleStreamArr[i] = null;
            }
        }
    }

    private void releaseOrphanEmbeddedStreams(TrackSelection[] trackSelectionArr, SampleStream[] sampleStreamArr, int[] iArr) {
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if ((sampleStreamArr[i] instanceof EmptySampleStream) || (sampleStreamArr[i] instanceof EmbeddedSampleStream)) {
                int primaryStreamIndex = getPrimaryStreamIndex(i, iArr);
                boolean z = primaryStreamIndex == -1 ? sampleStreamArr[i] instanceof EmptySampleStream : (sampleStreamArr[i] instanceof EmbeddedSampleStream) && sampleStreamArr[i].parent == sampleStreamArr[primaryStreamIndex];
                if (!z) {
                    if (sampleStreamArr[i] instanceof EmbeddedSampleStream) {
                        sampleStreamArr[i].release();
                    }
                    sampleStreamArr[i] = null;
                }
            }
        }
    }

    private void selectNewStreams(TrackSelection[] trackSelectionArr, SampleStream[] sampleStreamArr, boolean[] zArr, long j, int[] iArr) {
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (sampleStreamArr[i] == null && trackSelectionArr[i] != null) {
                zArr[i] = true;
                TrackGroupInfo trackGroupInfo = this.trackGroupInfos[iArr[i]];
                if (trackGroupInfo.trackGroupCategory == 0) {
                    sampleStreamArr[i] = buildSampleStream(trackGroupInfo, trackSelectionArr[i], j);
                } else if (trackGroupInfo.trackGroupCategory == 2) {
                    sampleStreamArr[i] = new EventSampleStream((EventStream) this.eventStreams.get(trackGroupInfo.eventStreamGroupIndex), trackSelectionArr[i].getTrackGroup().getFormat(0), this.manifest.dynamic);
                }
            }
        }
        for (int i2 = 0; i2 < trackSelectionArr.length; i2++) {
            if (sampleStreamArr[i2] == null && trackSelectionArr[i2] != null) {
                TrackGroupInfo trackGroupInfo2 = this.trackGroupInfos[iArr[i2]];
                if (trackGroupInfo2.trackGroupCategory == 1) {
                    int primaryStreamIndex = getPrimaryStreamIndex(i2, iArr);
                    if (primaryStreamIndex == -1) {
                        sampleStreamArr[i2] = new EmptySampleStream();
                    } else {
                        sampleStreamArr[i2] = sampleStreamArr[primaryStreamIndex].selectEmbeddedTrack(j, trackGroupInfo2.trackType);
                    }
                }
            }
        }
    }

    private int getPrimaryStreamIndex(int i, int[] iArr) {
        int i2 = iArr[i];
        if (i2 == -1) {
            return -1;
        }
        int i3 = this.trackGroupInfos[i2].primaryTrackGroupIndex;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            int i5 = iArr[i4];
            if (i5 == i3 && this.trackGroupInfos[i5].trackGroupCategory == 0) {
                return i4;
            }
        }
        return -1;
    }

    private static Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups(List<AdaptationSet> list, List<EventStream> list2) {
        int[][] groupedAdaptationSetIndices = getGroupedAdaptationSetIndices(list);
        int length = groupedAdaptationSetIndices.length;
        boolean[] zArr = new boolean[length];
        boolean[] zArr2 = new boolean[length];
        int identifyEmbeddedTracks = identifyEmbeddedTracks(length, list, groupedAdaptationSetIndices, zArr, zArr2) + length + list2.size();
        TrackGroup[] trackGroupArr = new TrackGroup[identifyEmbeddedTracks];
        TrackGroupInfo[] trackGroupInfoArr = new TrackGroupInfo[identifyEmbeddedTracks];
        buildManifestEventTrackGroupInfos(list2, trackGroupArr, trackGroupInfoArr, buildPrimaryAndEmbeddedTrackGroupInfos(list, groupedAdaptationSetIndices, length, zArr, zArr2, trackGroupArr, trackGroupInfoArr));
        return Pair.create(new TrackGroupArray(trackGroupArr), trackGroupInfoArr);
    }

    private static int[][] getGroupedAdaptationSetIndices(List<AdaptationSet> list) {
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        for (int i = 0; i < size; i++) {
            sparseIntArray.put(((AdaptationSet) list.get(i)).id, i);
        }
        int[][] iArr = new int[size][];
        boolean[] zArr = new boolean[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (!zArr[i3]) {
                zArr[i3] = true;
                Descriptor findAdaptationSetSwitchingProperty = findAdaptationSetSwitchingProperty(((AdaptationSet) list.get(i3)).supplementalProperties);
                if (findAdaptationSetSwitchingProperty == null) {
                    int i4 = i2 + 1;
                    iArr[i2] = new int[]{i3};
                    i2 = i4;
                } else {
                    String[] split = Util.split(findAdaptationSetSwitchingProperty.value, ",");
                    int[] iArr2 = new int[(split.length + 1)];
                    iArr2[0] = i3;
                    int i5 = 1;
                    for (String parseInt : split) {
                        int i6 = sparseIntArray.get(Integer.parseInt(parseInt), -1);
                        if (i6 != -1) {
                            zArr[i6] = true;
                            iArr2[i5] = i6;
                            i5++;
                        }
                    }
                    if (i5 < iArr2.length) {
                        iArr2 = Arrays.copyOf(iArr2, i5);
                    }
                    int i7 = i2 + 1;
                    iArr[i2] = iArr2;
                    i2 = i7;
                }
            }
        }
        return i2 < size ? (int[][]) Arrays.copyOf(iArr, i2) : iArr;
    }

    private static int identifyEmbeddedTracks(int i, List<AdaptationSet> list, int[][] iArr, boolean[] zArr, boolean[] zArr2) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (hasEventMessageTrack(list, iArr[i3])) {
                zArr[i3] = true;
                i2++;
            }
            if (hasCea608Track(list, iArr[i3])) {
                zArr2[i3] = true;
                i2++;
            }
        }
        return i2;
    }

    private static int buildPrimaryAndEmbeddedTrackGroupInfos(List<AdaptationSet> list, int[][] iArr, int i, boolean[] zArr, boolean[] zArr2, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr) {
        int i2;
        int i3;
        List<AdaptationSet> list2 = list;
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i4) {
            int[] iArr2 = iArr[i5];
            ArrayList arrayList = new ArrayList();
            for (int i7 : iArr2) {
                arrayList.addAll(((AdaptationSet) list2.get(i7)).representations);
            }
            Format[] formatArr = new Format[arrayList.size()];
            for (int i8 = 0; i8 < formatArr.length; i8++) {
                formatArr[i8] = ((Representation) arrayList.get(i8)).format;
            }
            AdaptationSet adaptationSet = (AdaptationSet) list2.get(iArr2[0]);
            int i9 = i6 + 1;
            if (zArr[i5]) {
                i2 = i9 + 1;
            } else {
                i2 = i9;
                i9 = -1;
            }
            if (zArr2[i5]) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
                i2 = -1;
            }
            trackGroupArr[i6] = new TrackGroup(formatArr);
            trackGroupInfoArr[i6] = TrackGroupInfo.primaryTrack(adaptationSet.type, iArr2, i6, i9, i2);
            if (i9 != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(adaptationSet.id);
                sb.append(":emsg");
                trackGroupArr[i9] = new TrackGroup(Format.createSampleFormat(sb.toString(), MimeTypes.APPLICATION_EMSG, null, -1, null));
                trackGroupInfoArr[i9] = TrackGroupInfo.embeddedEmsgTrack(iArr2, i6);
            }
            if (i2 != -1) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(adaptationSet.id);
                sb2.append(":cea608");
                trackGroupArr[i2] = new TrackGroup(Format.createTextSampleFormat(sb2.toString(), MimeTypes.APPLICATION_CEA608, 0, null));
                trackGroupInfoArr[i2] = TrackGroupInfo.embeddedCea608Track(iArr2, i6);
            }
            i5++;
            i6 = i3;
        }
        return i6;
    }

    private static void buildManifestEventTrackGroupInfos(List<EventStream> list, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr, int i) {
        int i2 = i;
        int i3 = 0;
        while (i3 < list.size()) {
            trackGroupArr[i2] = new TrackGroup(Format.createSampleFormat(((EventStream) list.get(i3)).id(), MimeTypes.APPLICATION_EMSG, null, -1, null));
            int i4 = i2 + 1;
            trackGroupInfoArr[i2] = TrackGroupInfo.mpdEventTrack(i3);
            i3++;
            i2 = i4;
        }
    }

    private ChunkSampleStream<DashChunkSource> buildSampleStream(TrackGroupInfo trackGroupInfo, TrackSelection trackSelection, long j) {
        int i;
        Format[] formatArr;
        int[] iArr;
        TrackGroupInfo trackGroupInfo2 = trackGroupInfo;
        int[] iArr2 = new int[2];
        Format[] formatArr2 = new Format[2];
        boolean z = trackGroupInfo2.embeddedEventMessageTrackGroupIndex != -1;
        if (z) {
            formatArr2[0] = this.trackGroups.get(trackGroupInfo2.embeddedEventMessageTrackGroupIndex).getFormat(0);
            iArr2[0] = 4;
            i = 1;
        } else {
            i = 0;
        }
        boolean z2 = trackGroupInfo2.embeddedCea608TrackGroupIndex != -1;
        if (z2) {
            formatArr2[i] = this.trackGroups.get(trackGroupInfo2.embeddedCea608TrackGroupIndex).getFormat(0);
            int i2 = i + 1;
            iArr2[i] = 3;
            i = i2;
        }
        if (i < iArr2.length) {
            formatArr = (Format[]) Arrays.copyOf(formatArr2, i);
            iArr = Arrays.copyOf(iArr2, i);
        } else {
            formatArr = formatArr2;
            iArr = iArr2;
        }
        PlayerTrackEmsgHandler newPlayerTrackEmsgHandler = (!this.manifest.dynamic || !z) ? null : this.playerEmsgHandler.newPlayerTrackEmsgHandler();
        PlayerTrackEmsgHandler playerTrackEmsgHandler = newPlayerTrackEmsgHandler;
        ChunkSampleStream chunkSampleStream = new ChunkSampleStream(trackGroupInfo2.trackType, iArr, formatArr, this.chunkSourceFactory.createDashChunkSource(this.manifestLoaderErrorThrower, this.manifest, this.periodIndex, trackGroupInfo2.adaptationSetIndices, trackSelection, trackGroupInfo2.trackType, this.elapsedRealtimeOffset, z, z2, newPlayerTrackEmsgHandler, this.transferListener), (Callback<ChunkSampleStream<T>>) this, this.allocator, j, this.loadErrorHandlingPolicy, this.eventDispatcher);
        synchronized (this) {
            this.trackEmsgHandlerBySampleStream.put(chunkSampleStream, playerTrackEmsgHandler);
        }
        return chunkSampleStream;
    }

    private static Descriptor findAdaptationSetSwitchingProperty(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = (Descriptor) list.get(i);
            if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(descriptor.schemeIdUri)) {
                return descriptor;
            }
        }
        return null;
    }

    private static boolean hasEventMessageTrack(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            List<Representation> list2 = ((AdaptationSet) list.get(i)).representations;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (!((Representation) list2.get(i2)).inbandEventStreams.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCea608Track(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            List<Descriptor> list2 = ((AdaptationSet) list.get(i)).accessibilityDescriptors;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if ("urn:scte:dash:cc:cea-608:2015".equals(((Descriptor) list2.get(i2)).schemeIdUri)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static ChunkSampleStream<DashChunkSource>[] newSampleStreamArray(int i) {
        return new ChunkSampleStream[i];
    }
}
