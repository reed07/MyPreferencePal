package com.brightcove.player.network;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.network.DownloadManager.Listener;
import com.brightcove.player.offline.RequestConfig;
import com.brightcove.player.store.DownloadRequestSet;
import io.reactivex.Observable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

public interface IDownloadManager {

    public interface IRequest {

        @Retention(RetentionPolicy.SOURCE)
        public @interface Visibility {
        }

        @Nullable
        String getDescription();

        long getEstimatedSize();

        @Nullable
        Map<String, String> getHeaders();

        @Nullable
        Uri getLocalUri();

        @Nullable
        String getMimeType();

        int getNotificationVisibility();

        @NonNull
        Uri getRemoteUri();

        @Nullable
        String getTitle();

        boolean isAllowScanningByMediaScanner();

        boolean isAllowedOverBluetooth();

        boolean isAllowedOverMetered();

        boolean isAllowedOverMobile();

        boolean isAllowedOverRoaming();

        boolean isAllowedOverWifi();

        boolean isVisibleInDownloadsUi();
    }

    @NonNull
    Observable<DownloadRequestSet> createDownloadRequestSet(@Nullable RequestConfig requestConfig, long j, @NonNull Listener listener);

    boolean deleteDownload(@Nullable DownloadRequestSet downloadRequestSet);

    boolean deleteDownload(@NonNull Long l);

    @NonNull
    Observable<DownloadRequestSet> enqueueDownload(@NonNull DownloadRequestSet downloadRequestSet, @NonNull IRequest... iRequestArr);

    @NonNull
    DownloadStatus getDownloadStatus(@Nullable DownloadRequestSet downloadRequestSet);

    @NonNull
    DownloadStatus getDownloadStatus(@NonNull Long l);

    @NonNull
    DownloadStatus pauseDownload(@Nullable DownloadRequestSet downloadRequestSet);

    @NonNull
    DownloadStatus pauseDownload(@NonNull Long l);

    @NonNull
    DownloadStatus resumeDownload(@Nullable DownloadRequestSet downloadRequestSet);

    @NonNull
    DownloadStatus resumeDownload(@NonNull Long l);
}
