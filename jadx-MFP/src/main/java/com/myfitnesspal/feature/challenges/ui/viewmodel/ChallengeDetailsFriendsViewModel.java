package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.model.ChallengeParticipant;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import java.util.List;

public class ChallengeDetailsFriendsViewModel extends BaseViewModel {
    private final String challengeId;
    private final String challengeName;
    private final List<ChallengeParticipant> friends;
    private final State state;

    public ChallengeDetailsFriendsViewModel(State state2, String str, String str2, List<ChallengeParticipant> list) {
        this.state = state2;
        this.challengeId = str;
        this.challengeName = str2;
        this.friends = list;
    }

    public String getChallengeId() {
        return this.challengeId;
    }

    public String getChallengeName() {
        return this.challengeName;
    }

    public List<ChallengeParticipant> getFriends() {
        return this.friends;
    }

    public State getState() {
        return this.state;
    }
}
