package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityComment;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import dagger.Lazy;

public class PostCommentTask extends EventedTaskBase<MfpNewsFeedActivityComment, ApiException> {
    private final String commentText;
    private final String entryId;
    private final Lazy<NewsFeedService> newsFeedService;
    private final String userId;

    public static class CompletedEvent extends TaskEventBase<MfpNewsFeedActivityComment, ApiException> {
    }

    public PostCommentTask(Lazy<NewsFeedService> lazy, String str, String str2, String str3) {
        super(CompletedEvent.class);
        this.newsFeedService = lazy;
        this.entryId = str;
        this.userId = str2;
        this.commentText = str3;
    }

    /* access modifiers changed from: protected */
    public MfpNewsFeedActivityComment exec(Context context) throws ApiException {
        return ((NewsFeedService) this.newsFeedService.get()).postComment(this.entryId, this.commentText, this.userId);
    }
}
