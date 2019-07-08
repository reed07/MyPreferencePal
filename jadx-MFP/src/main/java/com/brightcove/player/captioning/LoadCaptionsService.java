package com.brightcove.player.captioning;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.brightcove.player.captioning.tasks.LoadCaptionsTask;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.CaptionType;
import com.google.android.exoplayer2.source.hls.DefaultHlsExtractorFactory;

@ListensFor(events = {})
@Emits(events = {})
public class LoadCaptionsService extends AbstractComponent {
    private ContentResolver contentResolver;

    public LoadCaptionsService(EventEmitter eventEmitter, ContentResolver contentResolver2) {
        super(eventEmitter, LoadCaptionsService.class);
        if (eventEmitter != null) {
            this.contentResolver = contentResolver2;
            return;
        }
        throw new IllegalArgumentException("must provide an EventEmitter");
    }

    @SuppressLint({"NewApi"})
    public void loadCaptions(Uri uri, String str) {
        if (uri != null) {
            CaptionType fromString = CaptionType.fromString(str);
            if (fromString == null) {
                fromString = determineCaptionType(uri);
            }
            if (fromString != null) {
                LoadCaptionsTask loadCaptionsTask = new LoadCaptionsTask(this.eventEmitter, this.contentResolver, fromString);
                if (VERSION.SDK_INT >= 11) {
                    loadCaptionsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{uri});
                    return;
                }
                loadCaptionsTask.execute(new Uri[]{uri});
                return;
            }
            throw new IllegalArgumentException("unknown caption type");
        }
        throw new IllegalArgumentException("must provide a valid Uri");
    }

    private CaptionType determineCaptionType(Uri uri) {
        String path = uri.getPath();
        if (path.endsWith(".ttml") || path.endsWith(".dfxp") || path.endsWith(".xml")) {
            return CaptionType.TTML;
        }
        if (path.endsWith(DefaultHlsExtractorFactory.VTT_FILE_EXTENSION)) {
            return CaptionType.WEBVTT;
        }
        return CaptionType.UNKNOWN;
    }
}
