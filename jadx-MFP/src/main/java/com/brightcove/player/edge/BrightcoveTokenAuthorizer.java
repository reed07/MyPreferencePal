package com.brightcove.player.edge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.Objects;

public final class BrightcoveTokenAuthorizer implements Authorizer {
    private final DashWidevineAuthorizer mDashWidevineAuthorizer = new DashWidevineAuthorizer();
    private final HLSeAuthorizer mHlseAuthorizer = new HLSeAuthorizer();

    @NonNull
    public Video configure(@NonNull Video video, @NonNull String str) {
        Objects.requireNonNull(video, "Video cannot be null");
        Objects.requireNonNull(str, "The Brightcove Authorization token cannot be null");
        if (TextUtils.isEmpty(str)) {
            return video;
        }
        return this.mDashWidevineAuthorizer.configure(this.mHlseAuthorizer.configure(video, str), str);
    }

    @Nullable
    public String findAuthorizationToken(@NonNull Video video) {
        Objects.requireNonNull(video, "Video cannot be null");
        String findAuthorizationToken = new DashWidevineAuthorizer().findAuthorizationToken(video);
        return findAuthorizationToken == null ? new HLSeAuthorizer().findAuthorizationToken(video) : findAuthorizationToken;
    }
}
