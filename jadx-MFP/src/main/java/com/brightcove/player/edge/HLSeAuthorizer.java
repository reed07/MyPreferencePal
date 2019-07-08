package com.brightcove.player.edge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.Objects;
import java.util.HashSet;
import java.util.Set;

final class HLSeAuthorizer implements Authorizer {
    private static final String DEFAULT_HLSE_ENCRYPTION = "aes128";

    HLSeAuthorizer() {
    }

    @NonNull
    public Video configure(@NonNull Video video, @NonNull String str) {
        Objects.requireNonNull(video, "Video cannot be null");
        Objects.requireNonNull(str, "The Brightcove Authorization token cannot be null");
        for (Source source : findHLSeSource(video)) {
            if (isHLSeSource(source)) {
                source.getProperties().put("url", updateAuthorizationTokenToUrl(source.getUrl(), str));
            }
        }
        return video;
    }

    @Nullable
    public String findAuthorizationToken(@NonNull Video video) {
        Objects.requireNonNull(video, "Video cannot be null");
        String str = null;
        for (Source url : findHLSeSource(video)) {
            str = findBcovAuthToken(url.getUrl());
            if (!TextUtils.isEmpty(str)) {
                break;
            }
        }
        return str;
    }

    static String updateAuthorizationTokenToUrl(@NonNull String str, @NonNull String str2) {
        Objects.requireNonNull(str, "The URL cannot be null");
        Objects.requireNonNull(str2, "The Brightcove Authorization token cannot be null");
        if (hasBrightcoveAuthorizationQueryParam(str)) {
            return replaceAuthorizationTokenToUrl(str, str2);
        }
        return appendAuthorizationTokenToUrl(str, str2);
    }

    @NonNull
    private static Set<Source> findHLSeSource(@NonNull Video video) {
        Objects.requireNonNull(video, "Video cannot be null");
        SourceCollection sourceCollectionByDeliveryType = video.getSourceCollectionByDeliveryType(DeliveryType.HLS);
        HashSet hashSet = new HashSet();
        if (sourceCollectionByDeliveryType != null) {
            for (Source source : sourceCollectionByDeliveryType.getSources()) {
                if (isHLSeSource(source)) {
                    hashSet.add(source);
                }
            }
        }
        return hashSet;
    }

    private static boolean isHLSeSource(@NonNull Source source) {
        Objects.requireNonNull(source, "Source cannot be null");
        return source.getUrl().contains(DEFAULT_HLSE_ENCRYPTION);
    }

    private static boolean hasBrightcoveAuthorizationQueryParam(@NonNull String str) {
        Objects.requireNonNull(str, "The URL cannot be null");
        return str.contains(Authorizer.BRIGHTCOVE_AUTHORIZATION_QUERY_PARAM_KEY);
    }

    private static String appendAuthorizationTokenToUrl(@NonNull String str, @NonNull String str2) {
        Objects.requireNonNull(str, "The URL cannot be null");
        Objects.requireNonNull(str2, "The Brightcove Authorization token cannot be null");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (str.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        sb.append(Authorizer.BRIGHTCOVE_AUTHORIZATION_QUERY_PARAM_KEY);
        sb.append("=");
        sb.append(str2);
        return sb.toString();
    }

    private static String replaceAuthorizationTokenToUrl(@NonNull String str, @NonNull String str2) {
        Objects.requireNonNull(str, "The URL cannot be null");
        Objects.requireNonNull(str2, "The Brightcove Authorization token cannot be null");
        StringBuilder sb = new StringBuilder();
        String[] split = str.split("\\?");
        if (split.length == 2) {
            sb.append(split[0]);
            sb.append("?");
            String[] split2 = split[1].split("&");
            for (int i = 0; i < split2.length; i++) {
                if (i != 0) {
                    sb.append("&");
                }
                if (split2[i].contains(Authorizer.BRIGHTCOVE_AUTHORIZATION_QUERY_PARAM_KEY)) {
                    sb.append(Authorizer.BRIGHTCOVE_AUTHORIZATION_QUERY_PARAM_KEY);
                    sb.append("=");
                    sb.append(str2);
                } else {
                    sb.append(split2[i]);
                }
            }
        }
        return sb.toString();
    }

    @Nullable
    private static String findBcovAuthToken(@NonNull String str) {
        String[] split;
        Objects.requireNonNull(str, "The URL cannot be null");
        String[] split2 = str.split("\\?");
        if (split2.length == 2) {
            for (String str2 : split2[1].split("&")) {
                if (str2.contains(Authorizer.BRIGHTCOVE_AUTHORIZATION_QUERY_PARAM_KEY)) {
                    return str2.split("=")[1];
                }
            }
        }
        return null;
    }
}
