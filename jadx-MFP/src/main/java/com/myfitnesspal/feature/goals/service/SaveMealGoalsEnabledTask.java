package com.myfitnesspal.feature.goals.service;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;

public class SaveMealGoalsEnabledTask extends EventedTaskBase<Boolean, Exception> {
    private static final String TASK_NAME_BASE = "SaveMealGoalsEnabledTask";
    private DiaryService diaryService;
    private Session session;
    private SyncService syncService;
    private boolean value;

    public static class CompletedEvent extends TaskEventBase<Boolean, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public SaveMealGoalsEnabledTask(Session session2, DiaryService diaryService2, SyncService syncService2, boolean z) {
        super(CompletedEvent.class);
        this.session = session2;
        this.diaryService = diaryService2;
        this.syncService = syncService2;
        this.value = z;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws Exception {
        User user = this.session.getUser();
        user.setIsMealGoalsEnabled(this.value);
        user.updatePropertyNamed(Basic.MEAL_GOALS_ENABLED);
        this.syncService.enqueueAndWait(SyncType.Incremental);
        this.diaryService.markDiaryDayCacheStale();
        return Boolean.valueOf(true);
    }
}
