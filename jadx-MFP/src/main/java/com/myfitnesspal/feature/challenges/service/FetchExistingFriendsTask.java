package com.myfitnesspal.feature.challenges.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.EmailFriend;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.uacf.taskrunner.Runner.CacheMode;
import java.util.ArrayList;
import java.util.List;

public class FetchExistingFriendsTask extends EventedTaskBase<List<EmailFriend>, ApiException> {
    private final FriendService friendService;

    public static class CompletedEvent extends TaskEventBase<List<EmailFriend>, ApiException> {
    }

    public FetchExistingFriendsTask(FriendService friendService2) {
        super((TaskEventBase) new CompletedEvent());
        this.friendService = friendService2;
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.CacheOnSuccess;
    }

    /* access modifiers changed from: protected */
    public List<EmailFriend> exec(Context context) throws ApiException {
        List<UserSummaryObject> fetchFriends = this.friendService.fetchFriends();
        ArrayList arrayList = new ArrayList();
        for (UserSummaryObject userSummaryObject : fetchFriends) {
            EmailFriend emailFriend = new EmailFriend();
            emailFriend.setName(userSummaryObject.getUsername());
            emailFriend.setThumbnailUrl(userSummaryObject.getThumbnailImageUrl());
            emailFriend.setHasBeenInvited(false);
            emailFriend.setId(userSummaryObject.getUid());
            emailFriend.setMfpUsername(userSummaryObject.getUsername());
            arrayList.add(emailFriend);
        }
        return arrayList;
    }
}
