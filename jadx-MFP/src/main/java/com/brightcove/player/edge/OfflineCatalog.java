package com.brightcove.player.edge;

import android.content.Context;
import android.support.annotation.NonNull;
import com.brightcove.player.OfflinePlaybackPlugin;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.offline.DaggerOfflinePlaybackSupport;
import com.brightcove.player.offline.DashDownloadable;
import com.brightcove.player.offline.DownloadFileCreator;
import com.brightcove.player.offline.MediaDownloadable;

public class OfflineCatalog extends AbstractOfflineCatalog {
    static {
        MediaDownloadable.registerDownloadable(DeliveryType.DASH, DashDownloadable.class);
        OfflinePlaybackPlugin.getInstance().initialize(DaggerOfflinePlaybackSupport.create());
    }

    public OfflineCatalog(Context context, EventEmitter eventEmitter, String str, String str2) {
        super(context, eventEmitter, str, str2, Catalog.DEFAULT_EDGE_BASE_URL);
    }

    public OfflineCatalog(Context context, EventEmitter eventEmitter, String str, String str2, String str3) {
        super(context, eventEmitter, str, str2, str3);
    }

    public OfflineCatalog(Context context, EventEmitter eventEmitter, String str, String str2, String str3, @NonNull DownloadFileCreator downloadFileCreator) {
        super(context, eventEmitter, str, str2, str3, downloadFileCreator);
    }
}
