package com.myfitnesspal.feature.friends.ui.viewmodel;

import android.content.Intent;
import com.myfitnesspal.feature.friends.task.FetchNewsFeedEntryTask;
import com.myfitnesspal.feature.friends.ui.activity.CommentsActivity;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;

public class CommentsViewModel extends RunnerViewModel<String> {
    private boolean hasRetriedAfterFetchingV2IdForV1IdOnce;
    private MfpNewsFeedActivityEntry newsFeedActivityEntry;
    private final Lazy<NewsFeedService> newsFeedService;
    private String parentContext;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int NEWS_FEED_ENTRY_FETCHED = ViewModelPropertyId.next();
        public static final int NEWS_FEED_ENTRY_FETCH_FAILED = ViewModelPropertyId.next();
    }

    public CommentsViewModel(Lazy<NewsFeedService> lazy, Intent intent, Runner runner) {
        super(runner);
        this.newsFeedService = lazy;
        this.parentContext = ExtrasUtils.getStringExtra(intent, CommentsActivity.EXTRA_PARENT_CONTEXT, "");
    }

    public void load(String... strArr) {
        new FetchNewsFeedEntryTask(this.newsFeedService, strArr[0]).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.matches(getRunner(), FetchNewsFeedEntryTask.class)) {
            return;
        }
        if (taskDetails.successful()) {
            this.newsFeedActivityEntry = (MfpNewsFeedActivityEntry) taskDetails.getResult();
            notifyPropertyChanged(Property.NEWS_FEED_ENTRY_FETCHED);
            return;
        }
        notifyPropertyChanged(Property.NEWS_FEED_ENTRY_FETCH_FAILED);
    }

    public void setNewsFeedActivityEntry(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        this.newsFeedActivityEntry = mfpNewsFeedActivityEntry;
    }

    public MfpNewsFeedActivityEntry getNewsFeedActivityEntry() {
        return this.newsFeedActivityEntry;
    }

    public String getNewsFeedActivityEntryType() {
        MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = this.newsFeedActivityEntry;
        return mfpNewsFeedActivityEntry != null ? mfpNewsFeedActivityEntry.getType() : "";
    }

    public String getParentContext() {
        return this.parentContext;
    }

    public void setHasRetriedAfterFetchingV2IdForV1IdOnce(boolean z) {
        this.hasRetriedAfterFetchingV2IdForV1IdOnce = z;
    }

    public boolean hasRetriedAfterFetchingV2IdForV1IdOnce() {
        return this.hasRetriedAfterFetchingV2IdForV1IdOnce;
    }
}
