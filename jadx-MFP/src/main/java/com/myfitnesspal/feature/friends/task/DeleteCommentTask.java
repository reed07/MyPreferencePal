package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import dagger.Lazy;

public class DeleteCommentTask extends EventedTaskBase<Boolean, ApiException> {
    private final String commentId;
    private final String entryId;
    private final Lazy<NewsFeedService> newsFeedService;
    private final int position;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
        /* access modifiers changed from: private */
        public int position;

        public int getPosition() {
            return this.position;
        }
    }

    public DeleteCommentTask(Lazy<NewsFeedService> lazy, String str, String str2, int i) {
        super(CompletedEvent.class);
        this.newsFeedService = lazy;
        this.entryId = str;
        this.commentId = str2;
        this.position = i;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        ((CompletedEvent) getEvent()).position = this.position;
        ((NewsFeedService) this.newsFeedService.get()).deleteComment(this.entryId, this.commentId);
        return Boolean.TRUE;
    }
}
