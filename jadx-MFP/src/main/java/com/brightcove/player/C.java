package com.brightcove.player;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class C extends Constants {
    public static final int DASH_ROLE_ALTERNATE_FLAG = 8192;
    public static final String DASH_ROLE_ALTERNATE_VALUE = "alternate";
    public static final int DASH_ROLE_CAPTION_FLAG = 16384;
    public static final String DASH_ROLE_CAPTION_VALUE = "caption";
    public static final int DASH_ROLE_COMMENTARY_FLAG = 131072;
    public static final String DASH_ROLE_COMMENTARY_VALUE = "commentary";
    public static final int DASH_ROLE_MAIN_FLAG = 4096;
    public static final String DASH_ROLE_MAIN_VALUE = "main";
    public static final int DASH_ROLE_SUBTITLE_FLAG = 32768;
    public static final String DASH_ROLE_SUBTITLE_VALUE = "subtitle";
    public static final int DASH_ROLE_SUB_FLAG = 262144;
    public static final String DASH_ROLE_SUB_VALUE = "sub";
    public static final int DASH_ROLE_SUPPLEMENTARY_FLAG = 65536;
    public static final String DASH_ROLE_SUPPLEMENTARY_VALUE = "supplementary";
    public static String HTTP_USER_AGENT = Sdk.makeHttpUserAgent("BrightcoveExoPlayer", ExoPlayerLibraryInfo.VERSION_SLASHY);
    public static final int TRACK_TYPE_AUDIO = 1;
    public static final int TRACK_TYPE_DEFAULT = 0;
    public static final int TRACK_TYPE_METADATA = 4;
    public static final int TRACK_TYPE_TEXT = 3;
    public static final int TRACK_TYPE_UNKNOWN = -1;
    public static final int TRACK_TYPE_VIDEO = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DashRoleFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DashRoleScheme {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TrackType {
    }

    private C() {
    }
}
