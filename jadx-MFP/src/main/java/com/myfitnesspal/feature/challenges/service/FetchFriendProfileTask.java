package com.myfitnesspal.feature.challenges.service;

import android.content.Context;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import java.util.List;

public class FetchFriendProfileTask extends EventedTaskBase<List<ChallengeSummary>, ApiException> {
    private static final String TASK_NAME_BASE = "FetchFriendProfileTask-";
    Lazy<ChallengesService> challengesService;
    final String friendUserId;

    public static class CompletedEvent extends TaskEventBase<List<ChallengeSummary>, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public FetchFriendProfileTask(Lazy<ChallengesService> lazy, String str) {
        super((TaskEventBase) new CompletedEvent());
        this.challengesService = lazy;
        this.friendUserId = str;
    }

    /* access modifiers changed from: protected */
    public List<ChallengeSummary> exec(Context context) throws ApiException {
        return ((ChallengesService) this.challengesService.get()).getChallengesForFriend(this.friendUserId);
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(this.friendUserId);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.CacheOnSuccess;
    }
}
