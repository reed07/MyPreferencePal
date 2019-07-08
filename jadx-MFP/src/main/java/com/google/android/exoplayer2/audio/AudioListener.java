package com.google.android.exoplayer2.audio;

public interface AudioListener {

    /* renamed from: com.google.android.exoplayer2.audio.AudioListener$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onAudioAttributesChanged(AudioListener audioListener, AudioAttributes audioAttributes) {
        }

        public static void $default$onAudioSessionId(AudioListener audioListener, int i) {
        }

        public static void $default$onVolumeChanged(AudioListener audioListener, float f) {
        }
    }

    void onAudioAttributesChanged(AudioAttributes audioAttributes);

    void onAudioSessionId(int i);

    void onVolumeChanged(float f);
}
