package com.brightcove.player.edge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.model.Video;

interface Authorizer {
    public static final String BRIGHTCOVE_AUTHORIZATION_HEADER_KEY = "BCOV-Auth";
    public static final String BRIGHTCOVE_AUTHORIZATION_QUERY_PARAM_KEY = "bcov_auth";

    @NonNull
    Video configure(@NonNull Video video, @NonNull String str);

    @Nullable
    String findAuthorizationToken(@NonNull Video video);
}
