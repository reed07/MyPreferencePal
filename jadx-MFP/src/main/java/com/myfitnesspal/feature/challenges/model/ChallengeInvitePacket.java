package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class ChallengeInvitePacket {
    @Expose
    private NewInvitation item;

    public ChallengeInvitePacket(NewInvitation newInvitation) {
        this.item = newInvitation;
    }

    public void setItem(NewInvitation newInvitation) {
        this.item = newInvitation;
    }

    public NewInvitation getItem() {
        return this.item;
    }
}
