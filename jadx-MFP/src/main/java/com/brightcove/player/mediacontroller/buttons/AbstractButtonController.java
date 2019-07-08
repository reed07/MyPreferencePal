package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.view.BaseVideoView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class AbstractButtonController extends AbstractComponent implements ButtonController, RemoteControlKeyState {
    public static final int DEFAULT_AUDIO_TRACKS_BUTTON_ID = R.id.audio_tracks;
    public static final int DEFAULT_CAPTIONS_BUTTON_ID = R.id.captions;
    public static final int DEFAULT_FAST_FORWARD_BUTTON_ID = R.id.fast_forward;
    public static final int DEFAULT_FULL_SCREEN_BUTTON_ID = R.id.full_screen;
    public static final int DEFAULT_LIVE_BUTTON_ID = R.id.live;
    public static final int DEFAULT_PLAY_BUTTON_ID = R.id.play;
    public static final int DEFAULT_REWIND_BUTTON_ID = R.id.rewind;
    public static final int DEFAULT_SEEK_BUTTON_ID = R.id.seek_bar;
    private static final String TAG = AbstractButtonController.class.getSimpleName();
    private Button button;
    private final Context context;
    private final Typeface font;
    private final int id;
    protected final Map<String, Object> properties = new HashMap();
    protected final List<ButtonState> stateList = new ArrayList();
    protected final BaseVideoView videoView;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    public int getVisibilityState() {
        return 0;
    }

    public boolean onBack(KeyEvent keyEvent) {
        return false;
    }

    public boolean onDpadCenter(KeyEvent keyEvent) {
        return false;
    }

    public boolean onDpadDown(KeyEvent keyEvent) {
        return false;
    }

    public boolean onDpadLeft(KeyEvent keyEvent) {
        return false;
    }

    public boolean onDpadRight(KeyEvent keyEvent) {
        return false;
    }

    public boolean onDpadUp(KeyEvent keyEvent) {
        return false;
    }

    public boolean onFastForward(KeyEvent keyEvent) {
        return false;
    }

    public boolean onHome(KeyEvent keyEvent) {
        return false;
    }

    public boolean onMenu(KeyEvent keyEvent) {
        return false;
    }

    public boolean onPause(KeyEvent keyEvent) {
        return false;
    }

    public boolean onPlay(KeyEvent keyEvent) {
        return false;
    }

    public boolean onPlayPause(KeyEvent keyEvent) {
        return false;
    }

    public boolean onRewind(KeyEvent keyEvent) {
        return false;
    }

    public boolean onSkipBackward(KeyEvent keyEvent) {
        return false;
    }

    public boolean onSkipForward(KeyEvent keyEvent) {
        return false;
    }

    public AbstractButtonController(Context context2, BaseVideoView baseVideoView, View view, int i, Typeface typeface) {
        super(baseVideoView.getEventEmitter());
        this.context = context2;
        this.videoView = baseVideoView;
        this.id = i;
        this.font = typeface;
        this.button = (Button) view.findViewById(i);
        Button button2 = this.button;
        if (button2 != null) {
            button2.setOnClickListener(new ButtonActionHandler(this));
            if (typeface != null) {
                this.button.setTypeface(typeface);
            }
        }
    }

    @Nullable
    public Typeface getFont() {
        return this.font;
    }

    @Nullable
    public Button getButton() {
        return this.button;
    }

    public void setVisibility(int i) {
        Button button2 = this.button;
        if (button2 != null) {
            button2.setVisibility(i);
        }
    }

    public EventEmitter getEventEmitter() {
        return this.eventEmitter;
    }

    public int getId() {
        return this.id;
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public List<ButtonState> getStateList() {
        return this.stateList;
    }

    public void syncStates() {
        int managedState = getManagedState();
        if (this.button == null) {
            return;
        }
        if (this.stateList.size() < managedState) {
            Log.wtf(TAG, String.format(Locale.getDefault(), "There is no, or insufficient, state information for the button with text: %s.", new Object[]{this.button.getText()}));
        } else if (this.stateList.size() > 0) {
            Log.v(TAG, String.format(Locale.getDefault(), "Start of sync update: text = %s; description = %s.", new Object[]{this.button.getText(), this.button.getContentDescription()}));
            ButtonState buttonState = (ButtonState) this.stateList.get(managedState);
            this.button.setContentDescription(buttonState.getContentDescription());
            Drawable image = buttonState.getImage();
            if (image != null) {
                this.button.setCompoundDrawablesWithIntrinsicBounds(image, null, null, null);
                this.button.setText("");
            } else {
                this.button.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                this.button.setText(buttonState.getText());
            }
            Log.v(TAG, String.format(Locale.getDefault(), "End of sync update: text = %s; description = %s.", new Object[]{this.button.getText(), this.button.getContentDescription()}));
        }
    }
}
