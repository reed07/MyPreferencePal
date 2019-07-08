package com.myfitnesspal.feature.diary.service;

import android.content.Context;
import com.myfitnesspal.feature.diary.model.DiaryDayContext;
import com.myfitnesspal.feature.diary.model.FriendDiaryRequestData;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.util.Date;

public class FetchFriendDiaryDayTask extends EventedTaskBase<DiaryDayContext, ApiException> {
    private static final String TASK_NAME_BASE = "FetchFriendDiaryDayTask-";
    private final Lazy<DiaryService> diaryService;
    private final FriendDiaryRequestData friendDiaryRequestData;
    private final Lazy<UserSummaryService> userSummaryService;

    public static class CompletedEvent extends TaskEventBase<Tuple2<Long, DiaryDay>, ApiException> {
        private Date date;

        public Date getDate() {
            return this.date;
        }

        /* access modifiers changed from: private */
        public void setDate(Date date2) {
            this.date = date2;
        }
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public FetchFriendDiaryDayTask(Lazy<DiaryService> lazy, Lazy<UserSummaryService> lazy2, FriendDiaryRequestData friendDiaryRequestData2) {
        super(CompletedEvent.class);
        this.diaryService = lazy;
        this.userSummaryService = lazy2;
        this.friendDiaryRequestData = friendDiaryRequestData2;
    }

    /* access modifiers changed from: protected */
    public DiaryDayContext exec(Context context) throws ApiException {
        long j = Long.MIN_VALUE;
        if (this.friendDiaryRequestData.getUserMasterId() == Long.MIN_VALUE) {
            UserSummaryObject fetchUserSummary = ((UserSummaryService) this.userSummaryService.get()).fetchUserSummary(this.friendDiaryRequestData.getUsername(), this.friendDiaryRequestData.getUserUid());
            FriendDiaryRequestData friendDiaryRequestData2 = this.friendDiaryRequestData;
            if (fetchUserSummary != null) {
                j = fetchUserSummary.getMasterId();
            }
            friendDiaryRequestData2.setUserMasterId(j);
        }
        DiaryDay retrieveDiaryDayForOtherUser = ((DiaryService) this.diaryService.get()).retrieveDiaryDayForOtherUser(this.friendDiaryRequestData);
        ((CompletedEvent) getEvent()).setDate(this.friendDiaryRequestData.getDate());
        return new DiaryDayContext(retrieveDiaryDayForOtherUser, new MfpNutrientGoal());
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(DateTimeUtils.getDateStringFromDate(this.friendDiaryRequestData.getDate()));
        return sb.toString();
    }
}
