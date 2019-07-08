package com.brightcove.player.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.brightcove.player.model.Video.ProjectionFormat;

public interface RenderView {
    Context getContext();

    int getHeight();

    SurfaceHolder getHolder();

    LayoutParams getLayoutParams();

    int getMeasuredHeight();

    int getMeasuredVideoHeight();

    int getMeasuredVideoWidth();

    ViewParent getParent();

    @NonNull
    ProjectionFormat getProjectionFormat();

    Surface getSurface();

    int getVideoHeight();

    int getVideoWidth();

    int getWidth();

    void invalidate();

    boolean isShown();

    boolean isVrMode();

    void release();

    void setLayoutParams(LayoutParams layoutParams);

    void setProjectionFormat(@Nullable ProjectionFormat projectionFormat);

    void setSurface(Surface surface);

    void setVideoSize(int i, int i2);

    void setVisibility(int i);

    void setVrMode(boolean z);
}
