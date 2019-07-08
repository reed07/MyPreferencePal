package com.brightcove.player.offline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.network.DownloadManager.Request;
import com.brightcove.player.network.DownloadStatus;
import com.brightcove.player.offline.MediaDownloadable.DownloadEventListener;
import com.brightcove.player.offline.MediaDownloadable.MediaFormatListener;
import com.brightcove.player.offline.MediaDownloadable.OnVideoSizeCallback;
import com.brightcove.player.util.FileUtil;
import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Mp4Downloadable extends MediaDownloadable {
    private static final String TAG = MediaDownloadable.class.getSimpleName();
    private String mediaUrl;
    private String stillImageLocalPath;
    private String thumbnailLocalPath;
    private String videoLocalPath;

    public Mp4Downloadable(@NonNull Context context, @NonNull Video video, @Nullable DownloadEventListener downloadEventListener, @Nullable RequestConfig requestConfig) {
        super(context, video, downloadEventListener, requestConfig);
    }

    public void getMediaFormatTracksAvailable(@NonNull MediaFormatListener mediaFormatListener) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MediaDownloadable.VIDEO_RENDITIONS, new ArrayList());
        bundle.putParcelableArrayList(MediaDownloadable.AUDIO_LANGUAGES, new ArrayList());
        bundle.putStringArrayList(MediaDownloadable.AUDIO_LANGUAGE_ROLES, new ArrayList());
        bundle.putParcelableArrayList(MediaDownloadable.CAPTIONS, new ArrayList());
        mediaFormatListener.onResult(this, bundle);
    }

    public boolean requestDownload() {
        super.requestDownload();
        Iterator it = ((SourceCollection) this.video.getSourceCollections().get(DeliveryType.MP4)).getSources().iterator();
        if (it.hasNext()) {
            this.mediaUrl = ((Source) it.next()).getUrl();
        }
        boolean z = !TextUtils.isEmpty(this.mediaUrl);
        if (z) {
            enqueueDownloadRequest((Request[]) getRequestList().toArray(new Request[0]));
        }
        return z;
    }

    public void estimatedSize(OnVideoSizeCallback onVideoSizeCallback) {
        onVideoSizeCallback.onVideoSizeEstimated(this.estimatedSize);
    }

    @NonNull
    private List<Request> getRequestList() {
        ArrayList arrayList = new ArrayList();
        Uri parse = Uri.parse(this.mediaUrl);
        Uri fromFile = Uri.fromFile(new File(getDownloadDirectory(), FileUtil.getFileName(this.mediaUrl)));
        this.videoLocalPath = fromFile.toString();
        arrayList.add(appendTitleAndDescription(createDownloadRequest(parse, fromFile)));
        Object obj = this.video.getProperties().get(Fields.THUMBNAIL);
        if (obj != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("thumbnail_");
            sb.append(FileUtil.getFileName(obj.toString()));
            Uri fromFile2 = Uri.fromFile(new File(getDownloadDirectory(), sb.toString()));
            this.thumbnailLocalPath = fromFile2.toString();
            Request createDownloadRequest = createDownloadRequest(Uri.parse(obj.toString()), fromFile2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.video.getName());
            sb2.append(" - Thumbnail Image");
            String sb3 = sb2.toString();
            createDownloadRequest.setTitle(sb3);
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Offline copy of ");
            sb4.append(sb3);
            createDownloadRequest.setDescription(sb4.toString());
            arrayList.add(createDownloadRequest);
        }
        Object obj2 = this.video.getProperties().get(Fields.STILL_IMAGE_URI);
        if (obj2 != null) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("poster_");
            sb5.append(FileUtil.getFileName(obj2.toString()));
            Uri fromFile3 = Uri.fromFile(new File(getDownloadDirectory(), sb5.toString()));
            this.stillImageLocalPath = fromFile3.toString();
            Request createDownloadRequest2 = createDownloadRequest(Uri.parse(obj2.toString()), fromFile3);
            StringBuilder sb6 = new StringBuilder();
            sb6.append(this.video.getName());
            sb6.append(" - Still Image");
            String sb7 = sb6.toString();
            createDownloadRequest2.setTitle(sb7);
            StringBuilder sb8 = new StringBuilder();
            sb8.append("Offline copy of ");
            sb8.append(sb7);
            createDownloadRequest2.setDescription(sb8.toString());
            arrayList.add(createDownloadRequest2);
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onMediaDownloadComplete(@NonNull DownloadStatus downloadStatus) {
        if (this.videoLocalPath != null) {
            for (Source properties : ((SourceCollection) this.video.getSourceCollections().get(DeliveryType.MP4)).getSources()) {
                properties.getProperties().put("url", this.videoLocalPath);
            }
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
    private Request appendTitleAndDescription(@NonNull Request request) {
        String name = this.video.getName() == null ? "" : this.video.getName();
        request.setTitle(name);
        StringBuilder sb = new StringBuilder();
        sb.append("Offline copy of ");
        sb.append(name);
        request.setDescription(sb.toString());
        return request;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Serializable> getMediaProperties() {
        return (HashMap) Collections.EMPTY_MAP;
    }
}
