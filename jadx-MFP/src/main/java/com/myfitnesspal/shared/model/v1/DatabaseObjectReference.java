package com.myfitnesspal.shared.model.v1;

public class DatabaseObjectReference extends DatabaseObject {
    Exercise exercise;
    Food food;
    public boolean isDestroyed;
    Object item;
    int itemType;
    long referenceId;

    /* access modifiers changed from: 0000 */
    public Food food() {
        return (Food) this.item;
    }

    /* access modifiers changed from: 0000 */
    public Exercise exercise() {
        return (Exercise) this.item;
    }

    public int getItemType() {
        return this.itemType;
    }

    public long getReferenceId() {
        return this.referenceId;
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    public void setReferenceId(long j) {
        this.referenceId = j;
    }

    public void setDestroyed(boolean z) {
        this.isDestroyed = z;
    }
}
