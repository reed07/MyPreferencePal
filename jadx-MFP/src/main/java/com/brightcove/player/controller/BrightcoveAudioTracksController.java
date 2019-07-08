package com.brightcove.player.controller;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.view.BaseVideoView;
import java.util.HashMap;
import java.util.List;

@ListensFor(events = {"audioTracks", "enterTvMode", "hidePlayerOptions"})
@Emits(events = {"audioTracksDialogOk", "selectAudioTrack"})
public class BrightcoveAudioTracksController extends AbstractComponent {
    /* access modifiers changed from: private */
    public static final String TAG = "BrightcoveAudioTracksController";
    private final int audioTracksButtonId = R.id.audio_tracks;
    protected Context context;
    /* access modifiers changed from: private */
    public int currentTrack = 0;
    /* access modifiers changed from: private */
    public boolean isTvMode;
    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioGroup.check(i);
            BrightcoveAudioTracksController.this.currentTrack = radioGroup.indexOfChild((RadioButton) radioGroup.findViewById(i));
            BrightcoveAudioTracksController brightcoveAudioTracksController = BrightcoveAudioTracksController.this;
            brightcoveAudioTracksController.selectAudioTrack(brightcoveAudioTracksController.currentTrack);
        }
    };
    private RadioGroup radioGroup;
    /* access modifiers changed from: private */
    public List<String> tracks;
    private BaseVideoView videoView;

    public BrightcoveAudioTracksController(BaseVideoView baseVideoView, Context context2) {
        super(baseVideoView.getEventEmitter(), BrightcoveAudioTracksController.class);
        this.videoView = baseVideoView;
        this.context = context2;
        addListener(EventType.AUDIO_TRACKS, new EventListener() {
            @Default
            public void processEvent(Event event) {
                List list = (List) event.properties.get(AbstractEvent.TRACKS);
                BrightcoveAudioTracksController.this.tracks = list;
                String access$100 = BrightcoveAudioTracksController.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("tracks = ");
                sb.append(list);
                Log.v(access$100, sb.toString());
                String str = (String) event.properties.get(AbstractEvent.SELECTED_TRACK);
                if (str != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (((String) list.get(i)).equals(str)) {
                            BrightcoveAudioTracksController.this.currentTrack = i;
                            return;
                        }
                    }
                }
            }
        });
        addListener(EventType.ENTER_TV_MODE, new EventListener() {
            public void processEvent(Event event) {
                BrightcoveAudioTracksController.this.isTvMode = true;
            }
        });
        addListener(EventType.HIDE_PLAYER_OPTIONS, new EventListener() {
            public void processEvent(Event event) {
                BrightcovePlayerOptionsManager.getInstance().hidePlayerOptions(BrightcoveAudioTracksController.this.eventEmitter);
                BrightcoveAudioTracksController.this.hideAudioTracksMenu();
            }
        });
    }

    public void showAudioTracksDialog() {
        CharSequence[] charSequenceArr = new CharSequence[this.tracks.size()];
        for (int i = 0; i < this.tracks.size(); i++) {
            charSequenceArr[i] = (CharSequence) this.tracks.get(i);
        }
        if (this.isTvMode) {
            showAudioTracksMenu();
        } else {
            new Builder(this.context).setTitle(R.string.brightcove_audio_track_selection).setSingleChoiceItems(charSequenceArr, this.currentTrack, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BrightcoveAudioTracksController.this.currentTrack = i;
                    String access$100 = BrightcoveAudioTracksController.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onClick: which = ");
                    sb.append(i);
                    Log.v(access$100, sb.toString());
                    BrightcoveAudioTracksController.this.selectAudioTrack(i);
                }
            }).setPositiveButton(17039370, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BrightcoveAudioTracksController.this.eventEmitter.emit(EventType.AUDIO_TRACKS_DIALOG_OK);
                    dialogInterface.dismiss();
                }
            }).show();
        }
    }

    /* access modifiers changed from: protected */
    public void selectAudioTrack(int i) {
        String str = (String) this.tracks.get(i);
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.SELECTED_TRACK, str);
        this.eventEmitter.emit(EventType.SELECT_AUDIO_TRACK, hashMap);
    }

    /* access modifiers changed from: protected */
    public void initAudioTracksLayout() {
        if (this.radioGroup == null) {
            BrightcovePlayerOptionsManager.getInstance().initPlayerOptions(this.videoView);
            this.radioGroup = BrightcovePlayerOptionsManager.getInstance().getAudioTracksGroup();
            RadioGroup radioGroup2 = this.radioGroup;
            if (radioGroup2 != null) {
                radioGroup2.setOnCheckedChangeListener(this.onCheckedChangeListener);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showAudioTracksMenu() {
        initAudioTracksLayout();
        RadioGroup radioGroup2 = this.radioGroup;
        if (radioGroup2 != null && radioGroup2.getChildCount() <= 0) {
            LayoutInflater layoutInflater = (LayoutInflater) this.videoView.getContext().getSystemService("layout_inflater");
            if (layoutInflater != null) {
                for (String text : this.tracks) {
                    RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.tv_player_options_item, this.radioGroup, false);
                    radioButton.setText(text);
                    this.radioGroup.addView(radioButton);
                }
                BrightcovePlayerOptionsManager.getInstance().showPlayerOptions(this.eventEmitter);
                BrightcovePlayerOptionsManager.getInstance().showAudioTracksOptions();
                RadioButton radioButton2 = (RadioButton) this.radioGroup.getChildAt(this.currentTrack);
                radioButton2.requestFocus();
                this.radioGroup.check(radioButton2.getId());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void hideAudioTracksMenu() {
        if (this.radioGroup != null) {
            BrightcovePlayerOptionsManager.getInstance().hideAudioTracksOptions();
            this.radioGroup.removeAllViews();
        }
    }
}
