package com.brightcove.player.edge;

import com.brightcove.player.model.Video;

public abstract class VideoListener extends ErrorListener {
    public abstract void onVideo(Video video);
}
