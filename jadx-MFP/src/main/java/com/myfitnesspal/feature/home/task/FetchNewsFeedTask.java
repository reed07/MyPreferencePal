package com.myfitnesspal.feature.home.task;

import android.content.Context;
import com.myfitnesspal.feature.home.model.NewsFeedRequestData;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel.NewsFeedType;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import dagger.Lazy;

public class FetchNewsFeedTask extends EventedTaskBase<MfpNewsFeedActivityEntryListContainer, ApiException> {
    private final Lazy<NewsFeedService> newsFeedService;
    private final NewsFeedType newsFeedType;
    private final NewsFeedRequestData requestData;

    public static class CompletedEvent extends TaskEventBase<MfpNewsFeedActivityEntryListContainer, ApiException> {
    }

    public FetchNewsFeedTask(Lazy<NewsFeedService> lazy, NewsFeedRequestData newsFeedRequestData, NewsFeedType newsFeedType2) {
        super(CompletedEvent.class);
        this.newsFeedService = lazy;
        this.requestData = newsFeedRequestData;
        this.newsFeedType = newsFeedType2;
    }

    /* access modifiers changed from: protected */
    public MfpNewsFeedActivityEntryListContainer exec(Context context) throws ApiException {
        return ((NewsFeedService) this.newsFeedService.get()).fetchFeedV2(this.requestData, this.newsFeedType);
    }
}
