package com.myfitnesspal.feature.diary.event;

public class PasswordEnteredEvent {
    private String password;

    public PasswordEnteredEvent(String str) {
        this.password = str;
    }

    public String getPassword() {
        return this.password;
    }
}
