package com.myfitnesspal.shared.ui.activity.busymanager;

public interface SupportsBusyStates {
    public static final int ALL = -1;
    public static final int NONE = 0;

    boolean isBusy();

    boolean isBusy(int i);

    void setBusy(int i, boolean z);

    void setBusy(boolean z);
}
