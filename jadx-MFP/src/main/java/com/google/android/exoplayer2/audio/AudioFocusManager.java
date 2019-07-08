package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioFocusRequest.Builder;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class AudioFocusManager {
    private static final int AUDIO_FOCUS_STATE_HAVE_FOCUS = 1;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT = 2;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT_DUCK = 3;
    private static final int AUDIO_FOCUS_STATE_LOST_FOCUS = -1;
    private static final int AUDIO_FOCUS_STATE_NO_FOCUS = 0;
    public static final int PLAYER_COMMAND_DO_NOT_PLAY = -1;
    public static final int PLAYER_COMMAND_PLAY_WHEN_READY = 1;
    public static final int PLAYER_COMMAND_WAIT_FOR_CALLBACK = 0;
    private static final String TAG = "AudioFocusManager";
    private static final float VOLUME_MULTIPLIER_DEFAULT = 1.0f;
    private static final float VOLUME_MULTIPLIER_DUCK = 0.2f;
    @Nullable
    private AudioAttributes audioAttributes;
    private AudioFocusRequest audioFocusRequest;
    /* access modifiers changed from: private */
    public int audioFocusState;
    @Nullable
    private final AudioManager audioManager;
    private int focusGain;
    private final AudioFocusListener focusListener;
    /* access modifiers changed from: private */
    public final PlayerControl playerControl;
    private boolean rebuildAudioFocusRequest;
    /* access modifiers changed from: private */
    public float volumeMultiplier = 1.0f;

    private class AudioFocusListener implements OnAudioFocusChangeListener {
        private AudioFocusListener() {
        }

        public void onAudioFocusChange(int i) {
            if (i != 1) {
                switch (i) {
                    case -3:
                        if (!AudioFocusManager.this.willPauseWhenDucked()) {
                            AudioFocusManager.this.audioFocusState = 3;
                            break;
                        } else {
                            AudioFocusManager.this.audioFocusState = 2;
                            break;
                        }
                    case -2:
                        AudioFocusManager.this.audioFocusState = 2;
                        break;
                    case -1:
                        AudioFocusManager.this.audioFocusState = -1;
                        break;
                    default:
                        String str = AudioFocusManager.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unknown focus change type: ");
                        sb.append(i);
                        Log.w(str, sb.toString());
                        return;
                }
            } else {
                AudioFocusManager.this.audioFocusState = 1;
            }
            switch (AudioFocusManager.this.audioFocusState) {
                case -1:
                    AudioFocusManager.this.playerControl.executePlayerCommand(-1);
                    AudioFocusManager.this.abandonAudioFocus(true);
                    break;
                case 0:
                case 3:
                    break;
                case 1:
                    AudioFocusManager.this.playerControl.executePlayerCommand(1);
                    break;
                case 2:
                    AudioFocusManager.this.playerControl.executePlayerCommand(0);
                    break;
                default:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unknown audio focus state: ");
                    sb2.append(AudioFocusManager.this.audioFocusState);
                    throw new IllegalStateException(sb2.toString());
            }
            float f = AudioFocusManager.this.audioFocusState == 3 ? AudioFocusManager.VOLUME_MULTIPLIER_DUCK : 1.0f;
            if (AudioFocusManager.this.volumeMultiplier != f) {
                AudioFocusManager.this.volumeMultiplier = f;
                AudioFocusManager.this.playerControl.setVolumeMultiplier(f);
            }
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerCommand {
    }

    public interface PlayerControl {
        void executePlayerCommand(int i);

        void setVolumeMultiplier(float f);
    }

    private int handleIdle(boolean z) {
        return z ? 1 : -1;
    }

    public AudioFocusManager(@Nullable Context context, PlayerControl playerControl2) {
        this.audioManager = context == null ? null : (AudioManager) context.getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.playerControl = playerControl2;
        this.focusListener = new AudioFocusListener();
        this.audioFocusState = 0;
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public int setAudioAttributes(@Nullable AudioAttributes audioAttributes2, boolean z, int i) {
        int i2;
        int i3 = 1;
        if (this.audioAttributes == null && audioAttributes2 == null) {
            if (!z) {
                i3 = -1;
            }
            return i3;
        }
        Assertions.checkNotNull(this.audioManager, "SimpleExoPlayer must be created with a context to handle audio focus.");
        if (!Util.areEqual(this.audioAttributes, audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            this.focusGain = convertAudioAttributesToFocusGain(audioAttributes2);
            int i4 = this.focusGain;
            Assertions.checkArgument(i4 == 1 || i4 == 0, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
            if (z && (i == 2 || i == 3)) {
                return requestAudioFocus();
            }
        }
        if (i == 1) {
            i2 = handleIdle(z);
        } else {
            i2 = handlePrepare(z);
        }
        return i2;
    }

    public int handlePrepare(boolean z) {
        if (this.audioManager == null) {
            return 1;
        }
        return z ? requestAudioFocus() : -1;
    }

    public int handleSetPlayWhenReady(boolean z, int i) {
        if (this.audioManager == null) {
            return 1;
        }
        if (!z) {
            abandonAudioFocus();
            return -1;
        }
        return i == 1 ? handleIdle(z) : requestAudioFocus();
    }

    public void handleStop() {
        if (this.audioManager != null) {
            abandonAudioFocus(true);
        }
    }

    private int requestAudioFocus() {
        int i;
        int i2 = 1;
        if (this.focusGain == 0) {
            if (this.audioFocusState != 0) {
                abandonAudioFocus(true);
            }
            return 1;
        }
        if (this.audioFocusState == 0) {
            if (Util.SDK_INT >= 26) {
                i = requestAudioFocusV26();
            } else {
                i = requestAudioFocusDefault();
            }
            this.audioFocusState = i == 1 ? 1 : 0;
        }
        int i3 = this.audioFocusState;
        if (i3 == 0) {
            return -1;
        }
        if (i3 == 2) {
            i2 = 0;
        }
        return i2;
    }

    private void abandonAudioFocus() {
        abandonAudioFocus(false);
    }

    /* access modifiers changed from: private */
    public void abandonAudioFocus(boolean z) {
        if (this.focusGain != 0 || this.audioFocusState != 0) {
            if (this.focusGain != 1 || this.audioFocusState == -1 || z) {
                if (Util.SDK_INT >= 26) {
                    abandonAudioFocusV26();
                } else {
                    abandonAudioFocusDefault();
                }
                this.audioFocusState = 0;
            }
        }
    }

    private int requestAudioFocusDefault() {
        return ((AudioManager) Assertions.checkNotNull(this.audioManager)).requestAudioFocus(this.focusListener, Util.getStreamTypeForAudioUsage(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).usage), this.focusGain);
    }

    @RequiresApi
    private int requestAudioFocusV26() {
        if (this.audioFocusRequest == null || this.rebuildAudioFocusRequest) {
            AudioFocusRequest audioFocusRequest2 = this.audioFocusRequest;
            this.audioFocusRequest = (audioFocusRequest2 == null ? new Builder(this.focusGain) : new Builder(audioFocusRequest2)).setAudioAttributes(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).getAudioAttributesV21()).setWillPauseWhenDucked(willPauseWhenDucked()).setOnAudioFocusChangeListener(this.focusListener).build();
            this.rebuildAudioFocusRequest = false;
        }
        return ((AudioManager) Assertions.checkNotNull(this.audioManager)).requestAudioFocus(this.audioFocusRequest);
    }

    private void abandonAudioFocusDefault() {
        ((AudioManager) Assertions.checkNotNull(this.audioManager)).abandonAudioFocus(this.focusListener);
    }

    @RequiresApi
    private void abandonAudioFocusV26() {
        if (this.audioFocusRequest != null) {
            ((AudioManager) Assertions.checkNotNull(this.audioManager)).abandonAudioFocusRequest(this.audioFocusRequest);
        }
    }

    /* access modifiers changed from: private */
    public boolean willPauseWhenDucked() {
        AudioAttributes audioAttributes2 = this.audioAttributes;
        return audioAttributes2 != null && audioAttributes2.contentType == 1;
    }

    private static int convertAudioAttributesToFocusGain(@Nullable AudioAttributes audioAttributes2) {
        if (audioAttributes2 == null) {
            return 0;
        }
        switch (audioAttributes2.usage) {
            case 0:
                Log.w(TAG, "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                return 1;
            case 1:
            case 14:
                return 1;
            case 2:
            case 4:
                return 2;
            case 3:
                return 0;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
                return 3;
            case 11:
                return audioAttributes2.contentType == 1 ? 2 : 3;
            case 16:
                if (Util.SDK_INT >= 19) {
                    return 4;
                }
                return 2;
            default:
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unidentified audio usage: ");
                sb.append(audioAttributes2.usage);
                Log.w(str, sb.toString());
                return 0;
        }
    }
}
