package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListenerImpl;

public class InternalAvidManagedVideoAdSession extends InternalAvidManagedAdSession {
    private AvidVideoPlaybackListenerImpl avidVideoPlaybackListener = new AvidVideoPlaybackListenerImpl(this, getAvidBridgeManager());

    public InternalAvidManagedVideoAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    public AvidVideoPlaybackListenerImpl getAvidVideoPlaybackListener() {
        return this.avidVideoPlaybackListener;
    }

    public SessionType getSessionType() {
        return SessionType.MANAGED_VIDEO;
    }

    public MediaType getMediaType() {
        return MediaType.VIDEO;
    }

    public void onEnd() {
        this.avidVideoPlaybackListener.destroy();
        super.onEnd();
    }
}
