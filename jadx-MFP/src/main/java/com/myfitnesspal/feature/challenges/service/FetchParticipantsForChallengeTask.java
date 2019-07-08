package com.myfitnesspal.feature.challenges.service;

import android.content.Context;
import com.myfitnesspal.feature.challenges.model.ChallengeParticipant;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import java.util.List;

public class FetchParticipantsForChallengeTask extends EventedTaskBase<List<ChallengeParticipant>, ApiException> {
    private static final String TASK_NAME_BASE = "FetchParticipantsForChallenge-";
    private final String challengeId;
    private Lazy<ChallengesService> challengesService;
    private final String relationshipType;

    public static class CompletedEvent extends TaskEventBase<List<ChallengeParticipant>, ApiException> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public FetchParticipantsForChallengeTask(Lazy<ChallengesService> lazy, String str, String str2) {
        super((TaskEventBase) new CompletedEvent());
        this.challengesService = lazy;
        this.challengeId = str;
        this.relationshipType = str2;
    }

    /* access modifiers changed from: protected */
    public List<ChallengeParticipant> exec(Context context) throws ApiException {
        return ((ChallengesService) this.challengesService.get()).getParticipantsForChallenge(this.challengeId, this.relationshipType);
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(this.challengeId);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.CacheOnSuccess;
    }
}
