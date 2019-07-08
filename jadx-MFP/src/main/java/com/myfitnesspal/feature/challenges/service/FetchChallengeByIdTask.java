package com.myfitnesspal.feature.challenges.service;

import android.content.Context;
import com.myfitnesspal.feature.challenges.model.Challenge;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;

public class FetchChallengeByIdTask extends EventedTaskBase<Challenge, ApiException> {
    private static final String TASK_NAME_BASE = "GetChallengeById-";
    final String challengeId;
    Lazy<ChallengesService> challengesService;

    public static class CompletedEvent extends TaskEventBase<Challenge, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public FetchChallengeByIdTask(Lazy<ChallengesService> lazy, String str) {
        super((TaskEventBase) new CompletedEvent());
        this.challengesService = lazy;
        this.challengeId = str;
    }

    /* access modifiers changed from: protected */
    public Challenge exec(Context context) throws ApiException {
        return ((ChallengesService) this.challengesService.get()).getChallengeById(this.challengeId);
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
