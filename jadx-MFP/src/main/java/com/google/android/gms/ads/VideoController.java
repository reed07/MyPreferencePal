package com.google.android.gms.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzyp;
import com.google.android.gms.internal.ads.zzzv;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class VideoController {
    @KeepForSdk
    public static final int PLAYBACK_STATE_ENDED = 3;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PAUSED = 2;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PLAYING = 1;
    @KeepForSdk
    public static final int PLAYBACK_STATE_READY = 5;
    @KeepForSdk
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    private zzyp zzwb;
    @Nullable
    @GuardedBy("mLock")
    private VideoLifecycleCallbacks zzwc;

    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    public final void zza(zzyp zzyp) {
        synchronized (this.mLock) {
            this.zzwb = zzyp;
            if (this.zzwc != null) {
                setVideoLifecycleCallbacks(this.zzwc);
            }
        }
    }

    public final zzyp zzbc() {
        zzyp zzyp;
        synchronized (this.mLock) {
            zzyp = this.zzwb;
        }
        return zzyp;
    }

    public final void play() {
        synchronized (this.mLock) {
            if (this.zzwb != null) {
                try {
                    this.zzwb.play();
                } catch (RemoteException e) {
                    zzbbd.zzb("Unable to call play on video controller.", e);
                }
            }
        }
    }

    public final void pause() {
        synchronized (this.mLock) {
            if (this.zzwb != null) {
                try {
                    this.zzwb.pause();
                } catch (RemoteException e) {
                    zzbbd.zzb("Unable to call pause on video controller.", e);
                }
            }
        }
    }

    public final void mute(boolean z) {
        synchronized (this.mLock) {
            if (this.zzwb != null) {
                try {
                    this.zzwb.mute(z);
                } catch (RemoteException e) {
                    zzbbd.zzb("Unable to call mute on video controller.", e);
                }
            }
        }
    }

    public final boolean isMuted() {
        synchronized (this.mLock) {
            if (this.zzwb == null) {
                return true;
            }
            try {
                boolean isMuted = this.zzwb.isMuted();
                return isMuted;
            } catch (RemoteException e) {
                zzbbd.zzb("Unable to call isMuted on video controller.", e);
                return true;
            }
        }
    }

    @KeepForSdk
    public final int getPlaybackState() {
        synchronized (this.mLock) {
            if (this.zzwb == null) {
                return 0;
            }
            try {
                int playbackState = this.zzwb.getPlaybackState();
                return playbackState;
            } catch (RemoteException e) {
                zzbbd.zzb("Unable to call getPlaybackState on video controller.", e);
                return 0;
            }
        }
    }

    public final boolean isCustomControlsEnabled() {
        synchronized (this.mLock) {
            if (this.zzwb == null) {
                return false;
            }
            try {
                boolean isCustomControlsEnabled = this.zzwb.isCustomControlsEnabled();
                return isCustomControlsEnabled;
            } catch (RemoteException e) {
                zzbbd.zzb("Unable to call isUsingCustomPlayerControls.", e);
                return false;
            }
        }
    }

    public final boolean isClickToExpandEnabled() {
        synchronized (this.mLock) {
            if (this.zzwb == null) {
                return false;
            }
            try {
                boolean isClickToExpandEnabled = this.zzwb.isClickToExpandEnabled();
                return isClickToExpandEnabled;
            } catch (RemoteException e) {
                zzbbd.zzb("Unable to call isClickToExpandEnabled.", e);
                return false;
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        Preconditions.checkNotNull(videoLifecycleCallbacks, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.mLock) {
            this.zzwc = videoLifecycleCallbacks;
            if (this.zzwb != null) {
                try {
                    this.zzwb.zza(new zzzv(videoLifecycleCallbacks));
                } catch (RemoteException e) {
                    zzbbd.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
                }
            }
        }
    }

    @Nullable
    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.mLock) {
            videoLifecycleCallbacks = this.zzwc;
        }
        return videoLifecycleCallbacks;
    }

    public final boolean hasVideoContent() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzwb != null;
        }
        return z;
    }

    public final float getAspectRatio() {
        synchronized (this.mLock) {
            if (this.zzwb == null) {
                return BitmapDescriptorFactory.HUE_RED;
            }
            try {
                float aspectRatio = this.zzwb.getAspectRatio();
                return aspectRatio;
            } catch (RemoteException e) {
                zzbbd.zzb("Unable to call getAspectRatio on video controller.", e);
                return BitmapDescriptorFactory.HUE_RED;
            }
        }
    }
}
