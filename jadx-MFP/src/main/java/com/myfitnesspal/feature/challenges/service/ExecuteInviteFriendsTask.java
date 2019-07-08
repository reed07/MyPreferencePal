package com.myfitnesspal.feature.challenges.service;

import android.content.Context;
import com.myfitnesspal.feature.challenges.model.NewInvitation;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.EmailFriend;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;

public class ExecuteInviteFriendsTask extends EventedTaskBase<Boolean, ApiException> {
    private static final String TASK_NAME_BASE = "ExecuteInviteFriendsTask-";
    final String challengeId;
    Lazy<ChallengesService> challengesService;
    final NewInvitation newInvitation;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
        private EmailFriend emailFriend;

        public CompletedEvent(EmailFriend emailFriend2) {
            this.emailFriend = emailFriend2;
        }

        public EmailFriend getEmailFriend() {
            return this.emailFriend;
        }
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public ExecuteInviteFriendsTask(EmailFriend emailFriend, Lazy<ChallengesService> lazy, String str, NewInvitation newInvitation2) {
        super((TaskEventBase) new CompletedEvent(emailFriend));
        this.challengesService = lazy;
        this.challengeId = str;
        this.newInvitation = newInvitation2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        ((ChallengesService) this.challengesService.get()).sendInvitation(this.challengeId, this.newInvitation);
        return Boolean.valueOf(true);
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(this.newInvitation.getUserId());
        sb.append(this.challengeId);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.CacheOnSuccess;
    }
}
