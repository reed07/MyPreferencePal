package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.uacf.core.util.Function1;
import dagger.Lazy;

public class PostCommentLikeTask extends EventedTaskBase<MfpNewsFeedLikeDetails, ApiException> {
    private final String action;
    private final String commentId;
    private final String entryId;
    private final Function1<Integer> likePostSuccessCallback;
    private final Lazy<NewsFeedService> newsFeedService;

    public static class CompletedEvent extends TaskEventBase<MfpNewsFeedLikeDetails, ApiException> {
        /* access modifiers changed from: private */
        public Function1<Integer> likePostSuccessCallback;

        public Function1<Integer> getLikePostSuccessCallback() {
            return this.likePostSuccessCallback;
        }
    }

    public PostCommentLikeTask(Lazy<NewsFeedService> lazy, String str, String str2, String str3, Function1<Integer> function1) {
        super(CompletedEvent.class);
        this.newsFeedService = lazy;
        this.entryId = str;
        this.commentId = str2;
        this.action = str3;
        this.likePostSuccessCallback = function1;
    }

    /* access modifiers changed from: protected */
    public MfpNewsFeedLikeDetails exec(Context context) throws ApiException {
        ((CompletedEvent) getEvent()).likePostSuccessCallback = this.likePostSuccessCallback;
        return ((NewsFeedService) this.newsFeedService.get()).postLikeForConversationEntryComment(this.entryId, this.commentId, this.action);
    }
}
