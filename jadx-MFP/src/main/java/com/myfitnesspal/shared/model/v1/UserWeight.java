package com.myfitnesspal.shared.model.v1;

public class UserWeight extends DatabaseObject {
    private float pounds;

    public UserWeight(float f) {
        setPounds(f);
    }

    public void setPounds(float f) {
        this.pounds = f;
    }

    public float getPounds() {
        return this.pounds;
    }

    public float getKilograms() {
        return (float) (((double) this.pounds) / 2.20462262d);
    }
}
