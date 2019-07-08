package com.myfitnesspal.shared.event;

@Deprecated
public class ShowProgressDialogEvent {
    private boolean cancelable;
    private boolean indeterminate;
    private String message;
    private String title;

    @Deprecated
    public ShowProgressDialogEvent(String str, String str2, boolean z, boolean z2) {
        this.title = str;
        this.message = str2;
        this.cancelable = z;
        this.indeterminate = z2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public boolean isCancelable() {
        return this.cancelable;
    }

    public void setCancelable(boolean z) {
        this.cancelable = z;
    }

    public boolean isIndeterminate() {
        return this.indeterminate;
    }

    public void setIndeterminate(boolean z) {
        this.indeterminate = z;
    }
}
