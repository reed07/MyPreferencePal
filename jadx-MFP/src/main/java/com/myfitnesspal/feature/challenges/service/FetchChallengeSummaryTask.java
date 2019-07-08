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

public class FetchChallengeSummaryTask extends EventedTaskBase<List<ChallengeSummary>, ApiException> {
    private static final String TASK_NAME_BASE = "GetChallengeSummaryList-";
    Lazy<ChallengesService> challengesService;
    final String type;

    public static class CompletedEvent extends TaskEventBase<List<ChallengeSummary>, Exception> {
        private String type;

        public CompletedEvent(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public FetchChallengeSummaryTask(Lazy<ChallengesService> lazy, String str) {
        super((TaskEventBase) new CompletedEvent(str));
        this.challengesService = lazy;
        this.type = str;
    }

    /* access modifiers changed from: protected */
    public List<ChallengeSummary> exec(Context context) throws ApiException {
        return ((ChallengesService) this.challengesService.get()).getChallenges(this.type);
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(this.type);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.CacheOnSuccess;
    }
}
