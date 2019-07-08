package com.brightcove.player.mediacontroller.buttons;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.brightcove.player.mediacontroller.ShowHideController;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

class ButtonActionHandler implements OnClickListener {
    private static final String TAG = "ButtonActionHandler";
    private ButtonController controller;

    ButtonActionHandler(ButtonController buttonController) {
        this.controller = buttonController;
    }

    public void onClick(View view) {
        Log.d(TAG, "Processing a media controls button click...");
        if (view instanceof Button) {
            this.controller.getEventEmitter().emit(ShowHideController.SHOW_MEDIA_CONTROLS);
            Button button = (Button) view;
            int id = this.controller.getId();
            if (id != -1) {
                List stateList = this.controller.getStateList();
                int i = 0;
                if (stateList == null || stateList.size() != 1) {
                    Iterator it = stateList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ButtonState buttonState = (ButtonState) it.next();
                        if (buttonState.getText().equals(button.getText())) {
                            processButtonState(id, buttonState, i, view);
                            break;
                        }
                        i++;
                    }
                } else {
                    processButtonState(id, (ButtonState) stateList.get(0), 0, view);
                }
            }
        }
    }

    private void processButtonState(int i, ButtonState buttonState, int i2, View view) {
        Log.d(TAG, "processButtonState...");
        int managedState = this.controller.getManagedState();
        if (managedState == i2) {
            String eventType = buttonState.getEventType();
            if (eventType != null) {
                emitButtonEvent(i, managedState, eventType);
                return;
            }
            OnClickListener handler = buttonState.getHandler();
            if (handler != null) {
                handler.onClick(view);
                return;
            }
            Log.wtf(TAG, String.format(Locale.getDefault(), "No handler to invoke or event type to emit for button with id: %d.", new Object[]{Integer.valueOf(i)}));
            return;
        }
        Log.w(TAG, String.format(Locale.getDefault(), "The managed state (%d) is out of sync with the button state for the button with id: %d.", new Object[]{Integer.valueOf(managedState), Integer.valueOf(i)}));
        this.controller.syncStates();
    }

    private void emitButtonEvent(int i, int i2, String str) {
        if (this.controller.getManagedState() == i2) {
            Log.v(TAG, String.format(Locale.getDefault(), "Emitting event: %s", new Object[]{str}));
            this.controller.getEventEmitter().emit(str, this.controller.getProperties());
            return;
        }
        Log.wtf(TAG, "The button glyph and video view states are out of sync!  A re-sync will be attempted.");
        this.controller.syncStates();
    }
}
