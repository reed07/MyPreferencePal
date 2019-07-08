package com.brightcove.player.mediacontroller;

import android.view.View;
import com.brightcove.player.mediacontroller.buttons.ButtonController;
import com.brightcove.player.mediacontroller.buttons.ButtonState;
import java.util.List;

public interface BrightcoveMediaControlRegistry {
    void clear();

    ButtonController getButtonController(int i);

    List<ButtonController> getButtonControllers();

    int getId(View view);

    int getManagedState(int i);

    List<ButtonState> getStateList(int i);

    View getView(int i);

    View getView(String str);

    void register(View view);

    void register(ButtonController buttonController);
}
