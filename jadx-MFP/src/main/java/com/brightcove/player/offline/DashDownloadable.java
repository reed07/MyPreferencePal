package com.brightcove.player.offline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.dash.BrightcoveDashManifestParser;
import com.brightcove.player.dash.DashUtil;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.MediaFormat;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.network.DownloadManager.Request;
import com.brightcove.player.network.DownloadStatus;
import com.brightcove.player.offline.MediaDownloadable.DownloadEventListener;
import com.brightcove.player.offline.MediaDownloadable.MediaFormatListener;
import com.brightcove.player.offline.MediaDownloadable.OnVideoSizeCallback;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.FileUtil;
import com.brightcove.player.util.MediaSourceUtil;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.source.dash.manifest.Representation.MultiSegmentRepresentation;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.Loader.LoadErrorAction;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DashDownloadable extends MediaDownloadable implements Callback<MediaPresentationDescriptionLoadable> {
    private static final String DEFAULT_AUDIO_ROLE = "MAIN";
    private static final String TAG = "DashDownloadable";
    private static final int TRY_TO_LOAD_ONCE = 1;
    private final List<String> availableAudioLanguageRoles = new ArrayList();
    private final List<MediaFormat> availableAudioLanguages = new ArrayList();
    private final List<MediaFormat> availableCaptions = new ArrayList();
    private final List<MediaFormat> availableVideoRenditions = new ArrayList();
    private final Map<String, Representation> cachedRepresentationMap = new HashMap();
    private Representation defaultVideoRepresentation;
    private File manifestFile;
    /* access modifiers changed from: private */
    public DashManifest mediaPresentationDescription;
    private String mediaUrl;
    private final DashManifestParser mpdParser = new BrightcoveDashManifestParser();
    private String stillImageLocalPath;
    private String thumbnailLocalPath;

    public void onLoadCanceled(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2, boolean z) {
    }

    public void getMediaFormatTracksAvailable(@NonNull final MediaFormatListener mediaFormatListener) {
        if (this.mediaPresentationDescription == null) {
            new Loader("dashDownloader").startLoading(getMPDDownloadable(), new Callback<MediaPresentationDescriptionLoadable>() {
                public void onLoadCanceled(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2, boolean z) {
                }

                public LoadErrorAction onLoadError(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2, IOException iOException, int i) {
                    return Loader.createRetryAction(false, -9223372036854775807L);
                }

                public void onLoadCompleted(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2) {
                    if (DashDownloadable.this.setupManifestFromLoadable(mediaPresentationDescriptionLoadable)) {
                        DashDownloadable.this.onMediaFormatListenerSuccess(mediaFormatListener);
                    }
                }
            }, 1);
            return;
        }
        onMediaFormatListenerSuccess(mediaFormatListener);
    }

    /* access modifiers changed from: private */
    public void onMediaFormatListenerSuccess(@NonNull MediaFormatListener mediaFormatListener) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MediaDownloadable.VIDEO_RENDITIONS, (ArrayList) this.availableVideoRenditions);
        bundle.putParcelableArrayList(MediaDownloadable.AUDIO_LANGUAGES, (ArrayList) this.availableAudioLanguages);
        bundle.putStringArrayList(MediaDownloadable.AUDIO_LANGUAGE_ROLES, (ArrayList) this.availableAudioLanguageRoles);
        bundle.putParcelableArrayList(MediaDownloadable.CAPTIONS, (ArrayList) this.availableCaptions);
        mediaFormatListener.onResult(this, bundle);
    }

    public DashDownloadable(@NonNull Context context, @NonNull Video video, @Nullable DownloadEventListener downloadEventListener, @Nullable RequestConfig requestConfig) {
        super(context, video, downloadEventListener, requestConfig);
    }

    @Nullable
    private Format getFormat() {
        Representation representation = this.defaultVideoRepresentation;
        if (representation != null) {
            return representation.format;
        }
        return null;
    }

    public boolean requestDownload() {
        super.requestDownload();
        DashManifest dashManifest = this.mediaPresentationDescription;
        if (dashManifest == null) {
            MediaPresentationDescriptionLoadable mPDDownloadable = getMPDDownloadable();
            if (mPDDownloadable == null) {
                return false;
            }
            new Loader("dashDownloader").startLoading(mPDDownloadable, this, 1);
            return true;
        }
        requestDownload(dashManifest, this.manifestFile);
        return false;
    }

    private MediaPresentationDescriptionLoadable getMPDDownloadable() {
        Iterator it = ((SourceCollection) this.video.getSourceCollections().get(DeliveryType.DASH)).getSources().iterator();
        if (it.hasNext()) {
            this.mediaUrl = ((Source) it.next()).getUrl();
        }
        if (!TextUtils.isEmpty(this.mediaUrl)) {
            return new MediaPresentationDescriptionLoadable(this.mpdParser, this.mediaUrl, getDownloadDirectory());
        }
        return null;
    }

    private void requestDownload(@NonNull DashManifest dashManifest, @NonNull File file) {
        if (this.mediaPresentationDescription == null && this.manifestFile == null) {
            this.mediaPresentationDescription = dashManifest;
            this.manifestFile = file;
            cacheManifestValues(this.mediaPresentationDescription);
        }
        enqueueDownloadRequest((Request[]) getRequestList(dashManifest).toArray(new Request[0]));
    }

    public void estimatedSize(final OnVideoSizeCallback onVideoSizeCallback) {
        DashManifest dashManifest = this.mediaPresentationDescription;
        if (dashManifest == null) {
            new Loader("dashDownloader").startLoading(getMPDDownloadable(), new Callback<MediaPresentationDescriptionLoadable>() {
                public void onLoadCompleted(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2) {
                    if (DashDownloadable.this.setupManifestFromLoadable(mediaPresentationDescriptionLoadable)) {
                        DashDownloadable dashDownloadable = DashDownloadable.this;
                        dashDownloadable.estimatedSize = dashDownloadable.estimateSizeFromManifest(dashDownloadable.mediaPresentationDescription);
                    } else {
                        DashDownloadable.this.estimatedSize = 0;
                    }
                    onVideoSizeCallback.onVideoSizeEstimated(DashDownloadable.this.estimatedSize);
                }

                public void onLoadCanceled(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2, boolean z) {
                    onVideoSizeCallback.onVideoSizeEstimated(0);
                }

                public LoadErrorAction onLoadError(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2, IOException iOException, int i) {
                    onVideoSizeCallback.onVideoSizeEstimated(0);
                    return Loader.createRetryAction(false, -9223372036854775807L);
                }
            }, 1);
            return;
        }
        cacheManifestValues(dashManifest);
        this.estimatedSize = estimateSizeFromManifest(this.mediaPresentationDescription);
        onVideoSizeCallback.onVideoSizeEstimated(this.estimatedSize);
    }

    /* access modifiers changed from: private */
    public boolean setupManifestFromLoadable(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable) {
        if (mediaPresentationDescriptionLoadable != null) {
            this.manifestFile = mediaPresentationDescriptionLoadable.getManifestFile();
            this.mediaPresentationDescription = mediaPresentationDescriptionLoadable.getResult();
            DashManifest dashManifest = this.mediaPresentationDescription;
            if (dashManifest != null) {
                cacheManifestValues(dashManifest);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public long estimateSizeFromManifest(@NonNull DashManifest dashManifest) {
        this.estimatedSize = 0;
        List<Representation> renditionsToDownload = getRenditionsToDownload();
        long periodDurationMs = dashManifest.getPeriodDurationMs(0);
        if (periodDurationMs > 0) {
            double micros = ((double) TimeUnit.MILLISECONDS.toMicros(periodDurationMs)) / 8000000.0d;
            for (Representation representation : renditionsToDownload) {
                if (representation != null) {
                    this.estimatedSize = (long) (((double) this.estimatedSize) + (((double) representation.format.bitrate) * micros));
                }
            }
        }
        return this.estimatedSize;
    }

    private void cacheManifestValues(@NonNull DashManifest dashManifest) {
        int periodCount = dashManifest.getPeriodCount();
        this.availableVideoRenditions.clear();
        this.availableAudioLanguages.clear();
        this.availableAudioLanguageRoles.clear();
        this.availableCaptions.clear();
        this.cachedRepresentationMap.clear();
        int i = -1;
        for (int i2 = 0; i2 < periodCount; i2++) {
            Period period = dashManifest.getPeriod(i2);
            long periodDurationMs = dashManifest.getPeriodDurationMs(i2);
            long j = periodDurationMs == -1 ? -9223372036854775807L : periodDurationMs * 1000;
            int i3 = 0;
            for (AdaptationSet adaptationSet : period.adaptationSets) {
                cacheRepresentations(adaptationSet);
                if (adaptationSet.type == 2) {
                    for (Representation representation : DashUtil.getVideoRepresentationList(this.context, adaptationSet)) {
                        String mediaMimeType = DashUtil.getMediaMimeType(representation.format);
                        if (mediaMimeType != null) {
                            this.availableVideoRenditions.add(DashUtil.getTrackFormat(2, representation.format, mediaMimeType, j));
                        }
                    }
                } else {
                    Representation highestRepresentation = DashUtil.getHighestRepresentation(adaptationSet);
                    String mediaMimeType2 = DashUtil.getMediaMimeType(highestRepresentation != null ? highestRepresentation.format : null);
                    if (mediaMimeType2 != null) {
                        if (adaptationSet.type == 1) {
                            String str = "";
                            if (DEFAULT_AUDIO_ROLE.equalsIgnoreCase(str) && i == -1) {
                                i = i3;
                            }
                            this.availableAudioLanguages.add(DashUtil.getTrackFormat(1, highestRepresentation.format, mediaMimeType2, j));
                            this.availableAudioLanguageRoles.add(str);
                            i3++;
                        } else if (adaptationSet.type == 3) {
                            this.availableCaptions.add(DashUtil.getTrackFormat(3, highestRepresentation.format, mediaMimeType2, j));
                        }
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        int size = this.availableAudioLanguages.size();
        if (i == -1 && size != 0) {
            arrayList.add(this.availableAudioLanguages.get(0));
        } else if (size > 0 && i > -1 && i < size) {
            arrayList.add(this.availableAudioLanguages.get(i));
        }
        setConfigurationToBundle(arrayList, 1);
    }

    public void onLoadCompleted(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2) {
        if (setupManifestFromLoadable(mediaPresentationDescriptionLoadable)) {
            enqueueDownloadRequest((Request[]) getRequestList(this.mediaPresentationDescription).toArray(new Request[0]));
        }
    }

    public LoadErrorAction onLoadError(MediaPresentationDescriptionLoadable mediaPresentationDescriptionLoadable, long j, long j2, IOException iOException, int i) {
        return Loader.createRetryAction(false, -9223372036854775807L);
    }

    private void setConfigurationToBundle(ArrayList<MediaFormat> arrayList, int i) {
        if (i == 1) {
            this.configurationBundle.putParcelableArrayList(MediaDownloadable.AUDIO_LANGUAGES, arrayList);
        } else if (i == 3) {
            this.configurationBundle.putParcelableArrayList(MediaDownloadable.CAPTIONS, arrayList);
        }
    }

    private void cacheRepresentations(@NonNull AdaptationSet adaptationSet) {
        for (Representation representation : adaptationSet.representations) {
            this.cachedRepresentationMap.put(representation.format.id, representation);
        }
    }

    @NonNull
    private List<Representation> getRenditionsToDownload() {
        ArrayList arrayList = new ArrayList();
        if (this.defaultVideoRepresentation == null) {
            this.defaultVideoRepresentation = DashUtil.findRepresentationByBitrate(findCachedRepresentations((ArrayList) this.availableVideoRenditions), this.requestConfig.getVideoBitrate());
        }
        Representation representation = this.defaultVideoRepresentation;
        if (representation != null) {
            arrayList.add(representation);
        }
        ArrayList parcelableArrayList = this.configurationBundle.getParcelableArrayList(MediaDownloadable.AUDIO_LANGUAGES);
        ArrayList parcelableArrayList2 = this.configurationBundle.getParcelableArrayList(MediaDownloadable.CAPTIONS);
        if (parcelableArrayList2 == null || parcelableArrayList2.isEmpty()) {
            parcelableArrayList2 = getDefaultVideoCaptions();
        }
        arrayList.addAll(findCachedRepresentations(parcelableArrayList));
        arrayList.addAll(findCachedRepresentations(parcelableArrayList2));
        return arrayList;
    }

    private ArrayList<MediaFormat> getDefaultVideoCaptions() {
        ArrayList<MediaFormat> arrayList = new ArrayList<>();
        List<Pair> list = (List) this.video.getProperties().get(Fields.CAPTION_SOURCES);
        HashSet<String> hashSet = new HashSet<>();
        if (list != null && !list.isEmpty()) {
            for (Pair pair : list) {
                BrightcoveCaptionFormat brightcoveCaptionFormat = (BrightcoveCaptionFormat) pair.second;
                if (brightcoveCaptionFormat.hasInBandMetadataTrackDispatchType() && brightcoveCaptionFormat.isDefault()) {
                    hashSet.add(brightcoveCaptionFormat.language());
                }
            }
        }
        for (String str : hashSet) {
            Iterator it = this.availableCaptions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MediaFormat mediaFormat = (MediaFormat) it.next();
                if (str.equals(mediaFormat.language)) {
                    arrayList.add(mediaFormat);
                    break;
                }
            }
        }
        return arrayList;
    }

    @NonNull
    private List<Representation> findCachedRepresentations(@Nullable ArrayList<MediaFormat> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Representation representation = (Representation) this.cachedRepresentationMap.get(((MediaFormat) it.next()).trackId);
                if (representation != null) {
                    arrayList2.add(representation);
                }
            }
        }
        return arrayList2;
    }

    private long getRepresentationDurationUs(DashManifest dashManifest, Representation representation) {
        if (representation != null) {
            int periodCount = dashManifest.getPeriodCount();
            for (int i = 0; i < periodCount; i++) {
                for (AdaptationSet adaptationSet : dashManifest.getPeriod(i).adaptationSets) {
                    Iterator it = adaptationSet.representations.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (((Representation) it.next()) == representation) {
                                long periodDurationMs = dashManifest.getPeriodDurationMs(i);
                                if (periodDurationMs > 0) {
                                    return TimeUnit.MILLISECONDS.toMicros(periodDurationMs);
                                }
                            }
                        }
                    }
                }
            }
        }
        return -9223372036854775807L;
    }

    @NonNull
    private List<Request> getRequestList(@NonNull DashManifest dashManifest) {
        ArrayList arrayList = new ArrayList();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList<Future> arrayList2 = new ArrayList<>();
        this.estimatedSize = 0;
        for (Representation representation : getRenditionsToDownload()) {
            if (representation != null) {
                long representationDurationUs = getRepresentationDurationUs(dashManifest, representation);
                arrayList2.add(newCachedThreadPool.submit(new Callable(representation, representationDurationUs) {
                    private final /* synthetic */ Representation f$1;
                    private final /* synthetic */ long f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final Object call() {
                        return DashDownloadable.this.createDashDownloadRequest(this.f$1, this.f$2);
                    }
                }));
                this.estimatedSize += (representationDurationUs * ((long) representation.format.bitrate)) / 8000000;
            }
        }
        for (Future future : arrayList2) {
            try {
                arrayList.addAll((Collection) future.get());
            } catch (InterruptedException | ExecutionException e) {
                Log.w(TAG, "Exception creating video request list", e);
            }
        }
        newCachedThreadPool.shutdownNow();
        Object obj = this.video.getProperties().get(Fields.THUMBNAIL);
        if (obj != null) {
            Uri thumbnailLocalUri = getThumbnailLocalUri();
            this.thumbnailLocalPath = thumbnailLocalUri.toString();
            Request createDownloadRequest = createDownloadRequest(Uri.parse(obj.toString()), thumbnailLocalUri);
            StringBuilder sb = new StringBuilder();
            sb.append(this.video.getName());
            sb.append(" - Thumbnail Image");
            String sb2 = sb.toString();
            createDownloadRequest.setTitle(sb2);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Offline copy of ");
            sb3.append(sb2);
            createDownloadRequest.setDescription(sb3.toString());
            arrayList.add(createDownloadRequest);
        }
        Object obj2 = this.video.getProperties().get(Fields.STILL_IMAGE_URI);
        if (obj2 != null) {
            Uri posterLocalUri = getPosterLocalUri();
            this.stillImageLocalPath = posterLocalUri.toString();
            Request createDownloadRequest2 = createDownloadRequest(Uri.parse(obj2.toString()), posterLocalUri);
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.video.getName());
            sb4.append(" - Still Image");
            String sb5 = sb4.toString();
            createDownloadRequest2.setTitle(sb5);
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Offline copy of ");
            sb6.append(sb5);
            createDownloadRequest2.setDescription(sb6.toString());
            arrayList.add(createDownloadRequest2);
        }
        return arrayList;
    }

    private Uri getThumbnailLocalUri() {
        Object obj = this.video.getProperties().get(Fields.THUMBNAIL);
        if (obj == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("thumbnail_");
        sb.append(FileUtil.getFileName(obj.toString()));
        return Uri.fromFile(new File(getDownloadDirectory(), sb.toString()));
    }

    private Uri getPosterLocalUri() {
        Object obj = this.video.getProperties().get(Fields.STILL_IMAGE_URI);
        if (obj == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("poster_");
        sb.append(FileUtil.getFileName(obj.toString()));
        return Uri.fromFile(new File(getDownloadDirectory(), sb.toString()));
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadComplete(@NonNull DownloadStatus downloadStatus) {
        if (this.manifestFile == null) {
            this.manifestFile = new File(getDownloadDirectory(), MediaDownloadable.DEFAULT_MPD_NAME);
            Uri thumbnailLocalUri = getThumbnailLocalUri();
            if (thumbnailLocalUri != null) {
                this.thumbnailLocalPath = thumbnailLocalUri.toString();
            }
            Uri posterLocalUri = getPosterLocalUri();
            if (posterLocalUri != null) {
                this.stillImageLocalPath = posterLocalUri.toString();
            }
        }
        if (this.manifestFile != null) {
            DashUtil.replaceVideoSourceUri(this.video, this.manifestFile.toString());
        }
        if (this.thumbnailLocalPath != null) {
            this.video.getProperties().put(Fields.THUMBNAIL, this.thumbnailLocalPath);
        }
        if (this.stillImageLocalPath != null) {
            this.video.getProperties().put(Fields.STILL_IMAGE_URI, URI.create(this.stillImageLocalPath));
        }
        super.onMediaDownloadComplete(downloadStatus);
    }

    @NonNull
    private Request createInitializationFileDownloadRequest(@NonNull Representation representation, @NonNull File file, @NonNull File file2) {
        File file3;
        Uri findInitializationUri = MediaSourceUtil.findInitializationUri(representation);
        if (representation instanceof MultiSegmentRepresentation) {
            file3 = new File(file2, findInitializationUri.getLastPathSegment());
        } else {
            file3 = new File(file, findInitializationUri.getLastPathSegment());
        }
        return appendTitleAndDescription(createDownloadRequest(findInitializationUri, Uri.fromFile(file3)), representation);
    }

    /* access modifiers changed from: private */
    @NonNull
    public List<Request> createDashDownloadRequest(@NonNull Representation representation, long j) {
        ArrayList arrayList = new ArrayList();
        File downloadDirectory = getDownloadDirectory();
        File file = new File(downloadDirectory, representation.format.id);
        arrayList.add(createInitializationFileDownloadRequest(representation, downloadDirectory, file));
        if (representation instanceof MultiSegmentRepresentation) {
            MultiSegmentRepresentation multiSegmentRepresentation = (MultiSegmentRepresentation) representation;
            long firstSegmentNum = multiSegmentRepresentation.getFirstSegmentNum();
            long segmentCount = (((long) multiSegmentRepresentation.getSegmentCount(j)) + firstSegmentNum) - 1;
            while (firstSegmentNum <= segmentCount) {
                Uri resolveUri = multiSegmentRepresentation.getSegmentUrl(firstSegmentNum).resolveUri(representation.baseUrl);
                arrayList.add(appendTitleAndDescription(createDownloadRequest(resolveUri, Uri.fromFile(new File(file, resolveUri.getLastPathSegment()))), representation));
                firstSegmentNum++;
            }
        }
        return arrayList;
    }

    @NonNull
    private Request appendTitleAndDescription(@NonNull Request request, @NonNull Representation representation) {
        StringBuilder sb = new StringBuilder(Convert.toString(this.video.getName()));
        String str = representation.format.containerMimeType;
        if (MimeTypes.isVideo(str)) {
            sb.append(" - Video Track");
        } else if (MimeTypes.isAudio(str)) {
            sb.append(" - Audio Track");
        } else if (MimeTypes.isText(str)) {
            sb.append(" - Text Track");
        } else {
            sb.append(" - Other Track");
        }
        String sb2 = sb.toString();
        request.setTitle(sb2);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Offline copy of ");
        sb3.append(sb2);
        request.setDescription(sb3.toString());
        return request;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public HashMap<String, Serializable> getMediaProperties() {
        HashMap<String, Serializable> hashMap = new HashMap<>();
        Format format = getFormat();
        if (format != null) {
            hashMap.put(AbstractEvent.RENDITION_URL, MediaSourceUtil.findRenditionUrl(this.mediaPresentationDescription, format));
            hashMap.put(AbstractEvent.RENDITION_WIDTH, Integer.valueOf(format.width));
            hashMap.put(AbstractEvent.RENDITION_HEIGHT, Integer.valueOf(format.height));
            hashMap.put(AbstractEvent.RENDITION_MIME_TYPE, format.containerMimeType);
            hashMap.put(AbstractEvent.RENDITION_INDICATED_BPS, Integer.valueOf(format.bitrate));
        }
        return hashMap;
    }
}
