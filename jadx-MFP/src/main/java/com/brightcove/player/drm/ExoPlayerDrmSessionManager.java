package com.brightcove.player.drm;

import android.annotation.TargetApi;
import android.os.Handler;
import com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.drm.MediaDrmCallback;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
public class ExoPlayerDrmSessionManager<T extends ExoMediaCrypto> extends DefaultDrmSessionManager<T> implements DrmSession<T> {
    @Deprecated
    public final DrmException getDrmError() {
        return null;
    }

    public ExoPlayerDrmSessionManager(UUID uuid, ExoMediaDrm<T> exoMediaDrm, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, Handler handler, DefaultDrmSessionEventListener defaultDrmSessionEventListener) {
        super(uuid, exoMediaDrm, mediaDrmCallback, hashMap);
        addListener(handler, defaultDrmSessionEventListener);
    }

    @Deprecated
    public Map<String, String> queryKeyStatus() {
        return Collections.emptyMap();
    }

    @Deprecated
    public byte[] getOfflineLicenseKeySetId() {
        return new byte[0];
    }
}
