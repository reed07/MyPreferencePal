package com.brightcove.ima;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.VideoView;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.view.BaseVideoView;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ListensFor(events = {"completed", "didPause", "didPlay", "didStop", "error", "willInterruptContent", "willResumeContent"})
@SuppressLint({"ViewConstructor"})
public class GoogleIMAVideoAdPlayer extends VideoView implements OnCompletionListener, OnErrorListener, OnPreparedListener, VideoAdPlayer {
    private static final String TAG = "GoogleIMAVideoAdPlayer";
    private String adId;
    private String adTitle;
    private List<VideoAdPlayerCallback> callbacks = new ArrayList();
    private int completedToken;
    private int didPauseToken;
    private int didPlayToken;
    private int didStopToken;
    private int errorToken;
    private EventEmitter eventEmitter;
    private boolean isPlayingAd = false;
    /* access modifiers changed from: private */
    public int previousSeekPosition;
    private int seekToToken;
    /* access modifiers changed from: private */
    public PlaybackState state = PlaybackState.STOPPED;
    private String url;

    private enum PlaybackState {
        STOPPED,
        PAUSED,
        PLAYING
    }

    GoogleIMAVideoAdPlayer(BaseVideoView baseVideoView) {
        super(baseVideoView.getContext());
        super.setOnCompletionListener(this);
        super.setOnErrorListener(this);
        super.setOnPreparedListener(this);
        this.eventEmitter = baseVideoView.getEventEmitter();
        enableListeners();
        this.eventEmitter.on(EventType.WILL_INTERRUPT_CONTENT, new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.disableListeners();
                if (GoogleIMAVideoAdPlayer.this.state == PlaybackState.PLAYING) {
                    GoogleIMAVideoAdPlayer.this.state = PlaybackState.PAUSED;
                }
            }
        });
        this.eventEmitter.on(EventType.WILL_RESUME_CONTENT, new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.enableListeners();
            }
        });
        setOnTouchListener(new OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (GoogleIMAVideoAdPlayer.this.state == PlaybackState.PLAYING) {
                    GoogleIMAVideoAdPlayer.this.pause();
                } else {
                    GoogleIMAVideoAdPlayer.this.start();
                }
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void enableListeners() {
        this.didPlayToken = this.eventEmitter.on(EventType.DID_PLAY, new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.didPlay();
            }
        });
        this.didPauseToken = this.eventEmitter.on(EventType.DID_PAUSE, new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.didPause();
            }
        });
        this.seekToToken = this.eventEmitter.on(EventType.SEEK_TO, new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.previousSeekPosition = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
            }
        });
        this.didStopToken = this.eventEmitter.on(EventType.DID_STOP, new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.didEnd();
            }
        });
        this.completedToken = this.eventEmitter.on("completed", new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.didEnd();
            }
        });
        this.errorToken = this.eventEmitter.on("error", new EventListener() {
            public void processEvent(Event event) {
                GoogleIMAVideoAdPlayer.this.didError();
            }
        });
    }

    /* access modifiers changed from: private */
    public void disableListeners() {
        this.eventEmitter.off(EventType.DID_PLAY, this.didPlayToken);
        this.eventEmitter.off(EventType.DID_PAUSE, this.didPauseToken);
        this.eventEmitter.off(EventType.DID_STOP, this.didStopToken);
        this.eventEmitter.off("completed", this.completedToken);
        this.eventEmitter.off("error", this.errorToken);
        this.eventEmitter.off(EventType.SEEK_TO, this.seekToToken);
    }

    public void playAd() {
        Log.v(TAG, "playAd");
        start();
    }

    /* access modifiers changed from: 0000 */
    public void loadAd() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("loadAd: url = ");
        sb.append(this.url);
        Log.v(str, sb.toString());
        String str2 = this.url;
        if (str2 != null) {
            setVideoPath(str2);
        }
    }

    public void loadAd(String str) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("loadAd: ");
        sb.append(str);
        sb.append(", state = ");
        sb.append(this.state);
        Log.v(str2, sb.toString());
        this.url = str;
        setVideoPath(str);
    }

    public void pauseAd() {
        Log.v(TAG, "pauseAd");
        pause();
    }

    public void resumeAd() {
        Log.v(TAG, "resumeAd");
        start();
        HashMap hashMap = new HashMap(2);
        hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(getCurrentPosition()));
        hashMap.put("duration", Integer.valueOf(getDuration()));
        hashMap.put(AbstractEvent.AD_ID, this.adId);
        hashMap.put("adTitle", this.adTitle);
        this.eventEmitter.emit(EventType.AD_RESUMED, hashMap);
    }

    public void start() {
        Log.v(TAG, TtmlNode.START);
        super.start();
        this.isPlayingAd = true;
        didPlay();
    }

    /* access modifiers changed from: private */
    public void didPlay() {
        PlaybackState playbackState = this.state;
        this.state = PlaybackState.PLAYING;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("didPlay: oldState = ");
        sb.append(playbackState);
        Log.v(str, sb.toString());
        switch (playbackState) {
            case STOPPED:
                for (VideoAdPlayerCallback onPlay : this.callbacks) {
                    onPlay.onPlay();
                }
                return;
            case PAUSED:
                for (VideoAdPlayerCallback onResume : this.callbacks) {
                    onResume.onResume();
                }
                return;
            default:
                return;
        }
    }

    public void pause() {
        Log.v(TAG, EventType.PAUSE);
        super.pause();
        didPause();
    }

    /* access modifiers changed from: private */
    public void didPause() {
        Log.v(TAG, EventType.DID_PAUSE);
        if (this.isPlayingAd) {
            this.state = PlaybackState.PAUSED;
        }
        for (VideoAdPlayerCallback onPause : this.callbacks) {
            onPause.onPause();
        }
    }

    public void addCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("addCallback: ");
        sb.append(videoAdPlayerCallback);
        Log.v(str, sb.toString());
        this.callbacks.add(videoAdPlayerCallback);
    }

    public void removeCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("removeCallback: ");
        sb.append(videoAdPlayerCallback);
        Log.v(str, sb.toString());
    }

    public void removeCallbacks() {
        Log.v(TAG, "removeCallbacks");
        this.callbacks.clear();
    }

    public void stopAd() {
        Log.v(TAG, "stopAd");
        stopPlayback();
    }

    public void stopPlayback() {
        Log.v(TAG, "stopPlayback");
        super.stopPlayback();
        this.isPlayingAd = false;
        this.state = PlaybackState.STOPPED;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.v(TAG, "onCompletion");
        if (mediaPlayer != null) {
            mediaPlayer.setDisplay(null);
            mediaPlayer.reset();
            mediaPlayer.setDisplay(getHolder());
        }
        this.state = PlaybackState.STOPPED;
        this.isPlayingAd = false;
        this.url = null;
        didEnd();
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.v(TAG, "onPrepared");
    }

    /* access modifiers changed from: private */
    public void didEnd() {
        this.state = PlaybackState.STOPPED;
        for (VideoAdPlayerCallback onEnded : this.callbacks) {
            onEnded.onEnded();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.state = PlaybackState.STOPPED;
        didError();
        this.isPlayingAd = false;
        return true;
    }

    /* access modifiers changed from: private */
    public void didError() {
        Log.v(TAG, "didError");
        this.state = PlaybackState.STOPPED;
        for (VideoAdPlayerCallback onError : this.callbacks) {
            onError.onError();
        }
    }

    public VideoProgressUpdate getAdProgress() {
        VideoProgressUpdate videoProgressUpdate = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        if (this.isPlayingAd) {
            int duration = getDuration();
            if (duration > 0) {
                HashMap hashMap = new HashMap(2);
                hashMap.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(getCurrentPosition()));
                hashMap.put("duration", Integer.valueOf(duration));
                hashMap.put(AbstractEvent.AD_ID, this.adId);
                hashMap.put("adTitle", this.adTitle);
                this.eventEmitter.emit(EventType.AD_PROGRESS, hashMap);
                videoProgressUpdate = new VideoProgressUpdate((long) getCurrentPosition(), (long) duration);
            }
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getAdProgress: isPlayingAd = ");
        sb.append(this.isPlayingAd);
        sb.append(", previousSeekPosition = ");
        sb.append(this.previousSeekPosition);
        sb.append(", result = ");
        sb.append(videoProgressUpdate);
        Log.v(str, sb.toString());
        return videoProgressUpdate;
    }

    public String getAdId() {
        return this.adId;
    }

    public void setAdId(String str) {
        this.adId = str;
    }

    public String getAdTitle() {
        return this.adTitle;
    }

    public void setAdTitle(String str) {
        this.adTitle = str;
    }

    public int getVolume() {
        AudioManager audioManager = (AudioManager) getContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            return 0;
        }
        double streamVolume = (double) audioManager.getStreamVolume(3);
        double streamMaxVolume = (double) audioManager.getStreamMaxVolume(3);
        if (streamMaxVolume <= 0.0d) {
            return 0;
        }
        return (int) ((streamVolume / streamMaxVolume) * 100.0d);
    }
}
