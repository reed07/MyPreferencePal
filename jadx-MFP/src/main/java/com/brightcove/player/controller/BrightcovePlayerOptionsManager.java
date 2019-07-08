package com.brightcove.player.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.brightcove.player.R;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.RegisteringEventEmitter;
import com.brightcove.player.mediacontroller.ShowHideController;

public class BrightcovePlayerOptionsManager {
    private static final BrightcovePlayerOptionsManager instance = new BrightcovePlayerOptionsManager();
    private RadioGroup audioTracksGroup;
    private TextView audioTracksTitle;
    private RadioGroup captionsGroup;
    private TextView captionsTitle;
    private View playerOptionsView;

    private BrightcovePlayerOptionsManager() {
    }

    public static BrightcovePlayerOptionsManager getInstance() {
        return instance;
    }

    public View initPlayerOptions(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return null;
        }
        this.playerOptionsView = viewGroup.findViewById(R.id.playerOptions);
        if (this.playerOptionsView == null) {
            this.playerOptionsView = ((LayoutInflater) viewGroup.getContext().getSystemService("layout_inflater")).inflate(R.layout.tv_player_options, viewGroup, false);
            View view = this.playerOptionsView;
            if (view != null) {
                viewGroup.addView(view);
                viewGroup.invalidate();
                this.audioTracksGroup = (RadioGroup) this.playerOptionsView.findViewById(R.id.audioTracksGroup);
                this.audioTracksTitle = (TextView) this.playerOptionsView.findViewById(R.id.audioTracksTitle);
                this.captionsGroup = (RadioGroup) this.playerOptionsView.findViewById(R.id.captionsGroup);
                this.captionsTitle = (TextView) this.playerOptionsView.findViewById(R.id.captionsTitle);
            }
        }
        return this.playerOptionsView;
    }

    public boolean isPlayerOptionsVisible() {
        View view = this.playerOptionsView;
        boolean z = false;
        if (view == null) {
            return false;
        }
        if (view.getVisibility() == 0) {
            z = true;
        }
        return z;
    }

    public void showPlayerOptions(EventEmitter eventEmitter) {
        View view = this.playerOptionsView;
        if (view != null) {
            view.setVisibility(0);
            emitEvent(eventEmitter, ShowHideController.HIDE_MEDIA_CONTROLS);
        }
    }

    public void hidePlayerOptions(EventEmitter eventEmitter) {
        View view = this.playerOptionsView;
        if (view != null) {
            view.setVisibility(8);
            emitEvent(eventEmitter, ShowHideController.SHOW_MEDIA_CONTROLS);
        }
    }

    public boolean isAudioTracksVisible() {
        boolean z = false;
        if (this.playerOptionsView != null) {
            RadioGroup radioGroup = this.audioTracksGroup;
            if (radioGroup != null) {
                if (radioGroup.getVisibility() == 0) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    public void hideAudioTracksOptions() {
        RadioGroup radioGroup = this.audioTracksGroup;
        if (radioGroup != null) {
            radioGroup.setVisibility(8);
        }
        TextView textView = this.audioTracksTitle;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public void showAudioTracksOptions() {
        RadioGroup radioGroup = this.audioTracksGroup;
        if (radioGroup != null) {
            radioGroup.setVisibility(0);
        }
        TextView textView = this.audioTracksTitle;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    public boolean isCaptionsVisible() {
        boolean z = false;
        if (this.playerOptionsView != null) {
            RadioGroup radioGroup = this.captionsGroup;
            if (radioGroup != null) {
                if (radioGroup.getVisibility() == 0) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    public void hideCaptionsOptions() {
        RadioGroup radioGroup = this.captionsGroup;
        if (radioGroup != null) {
            radioGroup.setVisibility(8);
        }
        TextView textView = this.captionsTitle;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public void showCaptionsOptions() {
        RadioGroup radioGroup = this.captionsGroup;
        if (radioGroup != null) {
            radioGroup.setVisibility(0);
        }
        TextView textView = this.captionsTitle;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    public View getPlayerOptionsView() {
        return this.playerOptionsView;
    }

    public TextView getAudioTracksTitle() {
        return this.audioTracksTitle;
    }

    public RadioGroup getAudioTracksGroup() {
        return this.audioTracksGroup;
    }

    public TextView getCaptionsTitle() {
        return this.captionsTitle;
    }

    public RadioGroup getCaptionsGroup() {
        return this.captionsGroup;
    }

    private void emitEvent(EventEmitter eventEmitter, String str) {
        if (eventEmitter instanceof RegisteringEventEmitter) {
            ((RegisteringEventEmitter) eventEmitter).getRootEmitter().emit(str);
        } else {
            eventEmitter.emit(str);
        }
    }
}
