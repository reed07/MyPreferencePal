package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.view.BaseVideoView;
import java.util.List;

@ListensFor(events = {"activityResumed", "audioTracks", "audioTracksDialogOk", "audioTracksDialogSettings", "fragmentResumed", "enterTvMode", "willChangeVideo"})
public class AudioTracksButtonController extends AbstractButtonController {
    /* access modifiers changed from: private */
    public static final String TAG = "AudioTracksButtonController";
    /* access modifiers changed from: private */
    public int activityResumedToken;
    /* access modifiers changed from: private */
    public int audioTracksDialogOkToken;
    /* access modifiers changed from: private */
    public int audioTracksDialogSettingsToken;
    /* access modifiers changed from: private */
    public int fragmentResumedToken;
    /* access modifiers changed from: private */
    public boolean hasAudioTracks;
    /* access modifiers changed from: private */
    public EventListener showButtonListener = new EventListener() {
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.ANDROID_VIEW);
            if (AudioTracksButtonController.this.hasAudioTracks && (obj instanceof View)) {
                ((ButtonState) AudioTracksButtonController.this.getStateList().get(AudioTracksButtonController.this.getManagedState())).getHandler().onClick((View) obj);
            }
        }
    };

    private class AudioTracksDialogLauncher implements OnClickListener {
        private AudioTracksDialogLauncher() {
        }

        public void onClick(View view) {
            Log.d(AudioTracksButtonController.TAG, "Showing the audio tracks dialog.");
            if (AudioTracksButtonController.this.videoView.isPlaying()) {
                AudioTracksButtonController.this.videoView.pause();
                AudioTracksButtonController audioTracksButtonController = AudioTracksButtonController.this;
                audioTracksButtonController.audioTracksDialogOkToken = audioTracksButtonController.eventEmitter.once(EventType.AUDIO_TRACKS_DIALOG_OK, new EventListener() {
                    public void processEvent(Event event) {
                        AudioTracksButtonController.this.videoView.start();
                        AudioTracksButtonController.this.eventEmitter.off(EventType.AUDIO_TRACKS_DIALOG_SETTINGS, AudioTracksButtonController.this.audioTracksDialogSettingsToken);
                    }
                });
                AudioTracksButtonController audioTracksButtonController2 = AudioTracksButtonController.this;
                audioTracksButtonController2.audioTracksDialogSettingsToken = audioTracksButtonController2.eventEmitter.once(EventType.AUDIO_TRACKS_DIALOG_SETTINGS, new EventListener() {
                    public void processEvent(Event event) {
                        AudioTracksButtonController.this.activityResumedToken = AudioTracksButtonController.this.eventEmitter.once(EventType.ACTIVITY_RESUMED, new EventListener() {
                            public void processEvent(Event event) {
                                AudioTracksButtonController.this.videoView.start();
                                AudioTracksButtonController.this.eventEmitter.off(EventType.FRAGMENT_RESUMED, AudioTracksButtonController.this.fragmentResumedToken);
                            }
                        });
                        AudioTracksButtonController.this.fragmentResumedToken = AudioTracksButtonController.this.eventEmitter.once(EventType.FRAGMENT_RESUMED, new EventListener() {
                            public void processEvent(Event event) {
                                AudioTracksButtonController.this.videoView.start();
                                AudioTracksButtonController.this.eventEmitter.off(EventType.ACTIVITY_RESUMED, AudioTracksButtonController.this.activityResumedToken);
                            }
                        });
                        AudioTracksButtonController.this.eventEmitter.off(EventType.AUDIO_TRACKS_DIALOG_OK, AudioTracksButtonController.this.audioTracksDialogOkToken);
                    }
                });
            }
            AudioTracksButtonController.this.videoView.getAudioTracksController().showAudioTracksDialog();
        }
    }

    public int getManagedState() {
        return 0;
    }

    public AudioTracksButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface, Bundle bundle) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.audio_tracks, typeface);
        AudioTracksDialogLauncher audioTracksDialogLauncher = new AudioTracksDialogLauncher();
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.AUDIO_TRACKS_IMAGE);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_audio_tracks, R.string.desc_audio_tracks, image, (OnClickListener) audioTracksDialogLauncher);
        list.add(buttonState);
        addListener(EventType.AUDIO_TRACKS, new EventListener() {
            @Default
            public void processEvent(Event event) {
                try {
                    List list = (List) event.properties.get(AbstractEvent.TRACKS);
                    AudioTracksButtonController audioTracksButtonController = AudioTracksButtonController.this;
                    boolean z = true;
                    if (list == null || list.size() <= 1) {
                        z = false;
                    }
                    audioTracksButtonController.hasAudioTracks = z;
                    AudioTracksButtonController.this.setVisibility(AudioTracksButtonController.this.getVisibilityState());
                } catch (ClassCastException e) {
                    AudioTracksButtonController.this.hasAudioTracks = false;
                    Log.w(AudioTracksButtonController.TAG, "Wrong type of TRACKS in AudioTracks event", e);
                }
            }
        });
        this.hasAudioTracks = bundle != null && bundle.containsKey(AbstractEvent.AUDIO_TRACKS_STATE) && bundle.getBoolean(AbstractEvent.AUDIO_TRACKS_STATE);
        addListener(EventType.ENTER_TV_MODE, new EventListener() {
            public void processEvent(Event event) {
                AudioTracksButtonController audioTracksButtonController = AudioTracksButtonController.this;
                audioTracksButtonController.addListener(EventType.SHOW_PLAYER_OPTIONS, audioTracksButtonController.showButtonListener);
            }
        });
        addListener(EventType.WILL_CHANGE_VIDEO, new EventListener() {
            public void processEvent(Event event) {
                AudioTracksButtonController.this.hasAudioTracks = false;
            }
        });
    }

    public int getVisibilityState() {
        return this.hasAudioTracks ? 0 : 8;
    }
}
