package com.facebook.ads.internal.view.i.d;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.i.a.a;

public interface c {
    void a();

    void a(int i);

    void a(a aVar);

    void a(boolean z);

    void b();

    void c();

    boolean d();

    void e();

    int getCurrentPosition();

    int getDuration();

    long getInitialBufferTime();

    a getStartReason();

    d getState();

    int getVideoHeight();

    int getVideoWidth();

    View getView();

    float getVolume();

    void setBackgroundPlaybackEnabled(boolean z);

    void setControlsAnchorView(View view);

    void setFullScreen(boolean z);

    void setRequestedVolume(float f);

    void setVideoMPD(@Nullable String str);

    void setVideoStateChangeListener(e eVar);

    void setup(Uri uri);
}
