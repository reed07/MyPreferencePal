package com.brightcove.player.mediacontroller;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.brightcove.player.mediacontroller.buttons.ButtonController;
import com.brightcove.player.mediacontroller.buttons.ButtonState;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BrightcoveMediaControlRegistryImpl implements BrightcoveMediaControlRegistry {
    private static final String TAG = "BrightcoveMediaControlRegistryImpl";
    private final SparseArray<ButtonController> controllers = new SparseArray<>();
    private final SparseArray<View> mediaControls = new SparseArray<>();

    public void clear() {
        this.mediaControls.clear();
        this.controllers.clear();
    }

    public List<ButtonController> getButtonControllers() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.controllers.size(); i++) {
            arrayList.add(this.controllers.valueAt(i));
        }
        return arrayList;
    }

    public ButtonController getButtonController(int i) {
        return (ButtonController) this.controllers.get(i);
    }

    public View getView(int i) {
        return (View) this.mediaControls.get(i);
    }

    public View getView(String str) {
        if (str != null) {
            for (int i = 0; i < this.mediaControls.size(); i++) {
                View view = (View) this.mediaControls.valueAt(i);
                if (view.getContext().getResources().getResourceEntryName(view.getId()).equals(str.toLowerCase(Locale.US))) {
                    return view;
                }
            }
            return null;
        }
        Log.e(TAG, "Unexpected null resource tag!");
        return null;
    }

    public int getManagedState(int i) {
        if (this.controllers.get(i) != null) {
            return ((ButtonController) this.controllers.get(i)).getManagedState();
        }
        return -1;
    }

    public void register(ButtonController buttonController) {
        int id = buttonController.getId();
        if (buttonController.getButton() != null) {
            this.controllers.append(id, buttonController);
            this.mediaControls.put(id, buttonController.getButton());
            return;
        }
        Log.w(TAG, String.format("Could not register controller %s with id %s because its button is null", new Object[]{buttonController, Integer.valueOf(id)}));
    }

    public void register(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                register(viewGroup.getChildAt(i));
            }
            return;
        }
        this.mediaControls.put(view.getId(), view);
    }

    public int getId(View view) {
        for (int i = 0; i < this.mediaControls.size(); i++) {
            if (view == this.mediaControls.valueAt(i)) {
                return this.mediaControls.keyAt(i);
            }
        }
        return -1;
    }

    public List<ButtonState> getStateList(int i) {
        if (this.controllers.get(i) != null) {
            return ((ButtonController) this.controllers.get(i)).getStateList();
        }
        return null;
    }
}
