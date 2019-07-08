package com.myfitnesspal.feature.restaurantlogging.listener;

import android.widget.CompoundButton;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;

public interface OnMenuItemActionListener {
    void onItemCheckToggled(CompoundButton compoundButton, MfpMenuItem mfpMenuItem, boolean z);

    void onItemClicked(MfpMenuItem mfpMenuItem);
}
