package com.myfitnesspal.feature.addentry.util;

import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;

public interface EditableServingV2 {
    MfpFood getFood();

    MfpServingSize getServingSize();

    float getServings();

    void hideSoftInput();

    void populateFoodData(float f);

    void setServingSize(MfpServingSize mfpServingSize);

    void setServingSizeIndex(int i);

    void showDialogFragment(int i);
}
