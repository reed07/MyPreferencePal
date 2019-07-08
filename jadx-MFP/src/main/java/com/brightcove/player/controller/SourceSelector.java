package com.brightcove.player.controller;

import android.support.annotation.NonNull;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;

public interface SourceSelector {
    @NonNull
    Source selectSource(Video video) throws NoSourceFoundException;
}
