package com.myfitnesspal.feature.challenges.service;

import com.myfitnesspal.feature.challenges.model.Challenge;
import com.myfitnesspal.feature.challenges.model.ChallengeParticipant;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.model.NewInvitation;
import com.myfitnesspal.shared.api.ApiException;
import io.reactivex.Completable;
import java.util.List;

public interface ChallengesService {
    Challenge getChallengeById(String str) throws ApiException;

    List<ChallengeSummary> getChallenges(String str) throws ApiException;

    List<ChallengeSummary> getChallengesForFriend(String str) throws ApiException;

    List<ChallengeParticipant> getParticipantsForChallenge(String str, String str2) throws ApiException;

    Completable leaveChallenge(String str, String str2);

    ChallengeParticipant postJoinChallenge(String str, boolean z) throws ApiException;

    void sendInvitation(String str, NewInvitation newInvitation) throws ApiException;
}
