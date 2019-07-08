package com.myfitnesspal.feature.challenges.service;

import android.content.Context;
import com.myfitnesspal.feature.challenges.model.ChallengeParticipant;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;

public class ExecuteJoinChallengeTask extends EventedTaskBase<ChallengeParticipant, ApiException> {
    private static final String TASK_NAME_BASE = "ExecuteJoinChallengeTask-";
    final String challengeId;
    Lazy<ChallengesService> challengesService;
    final boolean isSharingEmail;

    public static class CompletedEvent extends TaskEventBase<ChallengeParticipant, ApiException> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public ExecuteJoinChallengeTask(Lazy<ChallengesService> lazy, String str, boolean z) {
        super((TaskEventBase) new CompletedEvent());
        this.challengesService = lazy;
        this.challengeId = str;
        this.isSharingEmail = z;
    }

    /* access modifiers changed from: protected */
    public ChallengeParticipant exec(Context context) throws ApiException {
        return ((ChallengesService) this.challengesService.get()).postJoinChallenge(this.challengeId, this.isSharingEmail);
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
