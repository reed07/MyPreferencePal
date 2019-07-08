package com.myfitnesspal.feature.challenges.ui.viewmodel;

import android.content.Context;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Strings;
import java.text.DateFormat;

public class ChallengeSummaryViewModel extends BaseViewModel {
    private ChallengeSummary challengeSummary;
    private String listType;

    public ChallengeSummaryViewModel(ChallengeSummary challengeSummary2, String str) {
        this.challengeSummary = challengeSummary2;
        this.listType = str;
    }

    public String getListType() {
        return this.listType;
    }

    public ChallengeImageOutput getPromotedImage() {
        return this.challengeSummary.getPromotedImage();
    }

    public boolean isPromoted() {
        return this.challengeSummary.isPromoted();
    }

    public ChallengeImageOutput getThumbImage() {
        return this.challengeSummary.getPreviewImage();
    }

    public String getParticipants() {
        return Strings.toString(Integer.valueOf(this.challengeSummary.getParticipantCount()));
    }

    public String getStartDate() {
        return this.challengeSummary.getStartAt();
    }

    public String getEndDate() {
        return this.challengeSummary.getEndAt();
    }

    public String getTitle() {
        return this.challengeSummary.getTitle();
    }

    public String getSponsor() {
        return this.challengeSummary.getSponsor().getName();
    }

    public String getChallengeId() {
        return this.challengeSummary.getId();
    }

    public String getFormattedStartDate() {
        return DateFormat.getDateInstance().format(DateTimeUtils.parseDb(getStartDate()));
    }

    public String getFormattedEndDate() {
        return DateFormat.getDateInstance().format(DateTimeUtils.parseDb(getEndDate()));
    }

    public boolean hasChallengeStarted() {
        return ChallengesUtil.isChallengeStatusStarted(this.challengeSummary.getStatus());
    }

    public boolean hasChallengeEnded() {
        return DateTimeUtils.isDateInThePast(DateTimeUtils.parseGivenFormat(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), getEndDate()));
    }

    public String getChallengeName() {
        return this.challengeSummary.getTitle();
    }

    public String getTimeRemainingInChallenge(Context context) {
        return ChallengesUtil.getTimeRemainingString(context, getEndDate());
    }

    public String getChallengeGoal() {
        return this.challengeSummary.getChallengeGoal();
    }

    public boolean isUserInChallenge() {
        return hasChallengeStarted() && this.challengeSummary.getParticipant() != null;
    }
}
