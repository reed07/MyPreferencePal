package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import dagger.Lazy;

public class FetchNewsFeedEntryTask extends EventedTaskBase<MfpNewsFeedActivityEntry, ApiException> {
    private final String entryId;
    private final Lazy<NewsFeedService> newsFeedService;

    public static class CompletedEvent extends TaskEventBase<MfpNewsFeedActivityEntry, ApiException> {
    }

    public FetchNewsFeedEntryTask(Lazy<NewsFeedService> lazy, String str) {
        super(CompletedEvent.class);
        this.newsFeedService = lazy;
        this.entryId = str;
    }

    /* access modifiers changed from: protected */
    public MfpNewsFeedActivityEntry exec(Context context) throws ApiException {
        return ((NewsFeedService) this.newsFeedService.get()).getNewsFeedEntry(this.entryId);
    }
}
