package com.brightcove.player.mediacontroller;

public interface ShowHideController {
    public static final String ANIMATION_STYLE_FADE = "fade";
    public static final String ANIMATION_STYLE_SLIDE = "slide";
    public static final String CONTROLS_HEIGHT = "controlsHeight";
    public static final String DID_HIDE_MEDIA_CONTROLS = "didHideMediaControls";
    public static final String DID_SHOW_MEDIA_CONTROLS = "didShowMediaControls";
    public static final String HIDE_MEDIA_CONTROLS = "hideMediaControls";
    public static final String SHOW_HIDE_ANIMATION_STYLE = "showHideAnimationStyle";
    public static final String SHOW_HIDE_RESOURCES = "showHideResources";
    public static final String SHOW_HIDE_TIMEOUT = "showHideTimeout";
    public static final String SHOW_MEDIA_CONTROLS = "showMediaControls";

    void hide();

    boolean isShowing();

    void show();
}
