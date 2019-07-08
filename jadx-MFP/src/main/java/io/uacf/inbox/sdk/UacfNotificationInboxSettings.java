package io.uacf.inbox.sdk;

public class UacfNotificationInboxSettings {
    private boolean limitPriorityToOnePerCategory;
    private int maxPriorityCount;

    UacfNotificationInboxSettings(int i, boolean z) {
        this.maxPriorityCount = i;
        this.limitPriorityToOnePerCategory = z;
    }

    public UacfNotificationInboxSettings setLimitPriorityToOnePerCategory(boolean z) {
        this.limitPriorityToOnePerCategory = z;
        return this;
    }

    public boolean getLimitPriorityToOnePerCategory() {
        return this.limitPriorityToOnePerCategory;
    }

    public UacfNotificationInboxSettings setMaxPriorityCount(int i) {
        this.maxPriorityCount = i;
        return this;
    }

    public int getMaxPriorityCount() {
        return this.maxPriorityCount;
    }
}
