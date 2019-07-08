package com.myfitnesspal.feature.diary.model;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.FetchFriendDiaryDayTask;
import com.myfitnesspal.feature.diary.ui.listener.OnDiaryDayFetchedListener;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;

public class FriendDiaryViewModel extends DiaryViewModelBase<FriendDiaryRequestData> {
    private final Lazy<UserSummaryService> userSummaryService;

    public FriendDiaryViewModel(Runner runner, Lazy<DiaryService> lazy, Lazy<NutrientGoalService> lazy2, OnDiaryDayFetchedListener onDiaryDayFetchedListener, Lazy<UserSummaryService> lazy3) {
        super(runner, lazy, lazy2, onDiaryDayFetchedListener);
        this.userSummaryService = lazy3;
    }

    public void load(FriendDiaryRequestData... friendDiaryRequestDataArr) {
        super.load(friendDiaryRequestDataArr);
        new FetchFriendDiaryDayTask(this.diaryService, this.userSummaryService, friendDiaryRequestDataArr[0]).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public boolean matchesTask(TaskDetails taskDetails) {
        return FetchFriendDiaryDayTask.matches(taskDetails);
    }
}
