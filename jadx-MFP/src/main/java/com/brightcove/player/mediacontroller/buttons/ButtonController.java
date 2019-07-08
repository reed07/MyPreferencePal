package com.brightcove.player.mediacontroller.buttons;

import android.widget.Button;
import com.brightcove.player.event.EventEmitter;
import java.util.List;
import java.util.Map;

public interface ButtonController {
    Button getButton();

    EventEmitter getEventEmitter();

    int getId();

    int getManagedState();

    Map<String, Object> getProperties();

    List<ButtonState> getStateList();

    int getVisibilityState();

    void removeListeners();

    void syncStates();
}
