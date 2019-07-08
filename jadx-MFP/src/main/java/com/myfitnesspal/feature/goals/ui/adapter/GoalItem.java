package com.myfitnesspal.feature.goals.ui.adapter;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.model.CheckableListItem;

public class GoalItem extends CheckableListItem {
    public static final float FORMATTED_LOSE_025_KG = 0.25f;
    public static final float FORMATTED_LOSE_05_KG = 0.5f;
    public static final float FORMATTED_LOSE_075_KG = 0.75f;
    public static final float FORMATTED_LOSE_1_KG = 1.0f;
    public static final float GAIN_025_KG = -0.6f;
    public static final float GAIN_05_KG = -1.1f;
    public static final float GAIN_05_POUND = -0.5f;
    public static final float GAIN_1_POUND = -1.0f;
    public static final float LOSE_025_KG = 0.6f;
    public static final float LOSE_05_KG = 1.1f;
    public static final float LOSE_05_POUND = 0.5f;
    public static final float LOSE_075_KG = 1.7f;
    public static final float LOSE_15_POUNDS = 1.5f;
    public static final float LOSE_1_KG = 2.2f;
    public static final float LOSE_1_POUND = 1.0f;
    public static final float LOSE_2_POUNDS = 2.0f;
    private float value;

    public enum Weight {
        LOSE_TWO_POUNDS,
        LOSE_ONE_HALF_POUNDS,
        LOSE_ONE_POUND,
        LOSE_HALF_POUND,
        MAINTAIN_WEIGHT,
        GAIN_HALF_POUND,
        GAIN_ONE_POUND
    }

    public GoalItem(String str, boolean z, Weight weight, boolean z2) {
        super(str, z);
        this.value = getWeight(weight, z2, false);
    }

    public float getValue() {
        return this.value;
    }

    public static float getCurrentlySelectedWeight(float f, boolean z, boolean z2) {
        return getWeight(getCurrentlySelected(f), z, z2);
    }

    public static float getWeight(Weight weight, boolean z, boolean z2) {
        float f = 0.25f;
        float f2 = 1.0f;
        float f3 = 0.5f;
        switch (weight) {
            case LOSE_TWO_POUNDS:
                if (!z) {
                    f2 = 2.0f;
                } else if (!z2) {
                    f2 = 2.2f;
                }
                return f2;
            case LOSE_ONE_HALF_POUNDS:
                float f4 = z ? z2 ? 0.75f : 1.7f : 1.5f;
                return f4;
            case LOSE_ONE_POUND:
                if (z) {
                    f2 = z2 ? 0.5f : 1.1f;
                }
                return f2;
            case LOSE_HALF_POUND:
                if (!z) {
                    f = 0.5f;
                } else if (!z2) {
                    f = 0.6f;
                }
                return f;
            case MAINTAIN_WEIGHT:
                return BitmapDescriptorFactory.HUE_RED;
            case GAIN_HALF_POUND:
                if (!z) {
                    f = -0.5f;
                } else if (!z2) {
                    f = -0.6f;
                }
                return f;
            case GAIN_ONE_POUND:
                if (!z) {
                    f3 = -1.0f;
                } else if (!z2) {
                    f3 = -1.1f;
                }
                return f3;
            default:
                return BitmapDescriptorFactory.HUE_RED;
        }
    }

    public static Weight getCurrentlySelected(float f) {
        if (f == 2.2f || f == 2.0f) {
            return Weight.LOSE_TWO_POUNDS;
        }
        if (f == 1.7f || f == 1.5f) {
            return Weight.LOSE_ONE_HALF_POUNDS;
        }
        if (f == 1.1f || f == 1.0f) {
            return Weight.LOSE_ONE_POUND;
        }
        if (f == 0.6f || f == 0.5f) {
            return Weight.LOSE_HALF_POUND;
        }
        if (f == BitmapDescriptorFactory.HUE_RED) {
            return Weight.MAINTAIN_WEIGHT;
        }
        if (f == -0.6f || f == -0.5f) {
            return Weight.GAIN_HALF_POUND;
        }
        if (f == -1.1f || f == -1.0f) {
            return Weight.GAIN_ONE_POUND;
        }
        return Weight.LOSE_ONE_POUND;
    }
}
