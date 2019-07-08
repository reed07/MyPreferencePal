package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class JoinChallengePacket {
    @Expose
    private NewChallengeParticipant item;

    public JoinChallengePacket(NewChallengeParticipant newChallengeParticipant) {
        this.item = newChallengeParticipant;
    }

    public void setItem(NewChallengeParticipant newChallengeParticipant) {
        this.item = newChallengeParticipant;
    }

    public NewChallengeParticipant getItem() {
        return this.item;
    }
}
