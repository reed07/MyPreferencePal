package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.TaskBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.service.friends.FriendService;

public class DeclineFriendRequestTask extends TaskBase<Boolean, ApiException> {
    private final FriendService friendService;
    private final long requestMasterId;
    private final String requestUid;

    public DeclineFriendRequestTask(FriendService friendService2, long j, String str) {
        this.friendService = friendService2;
        this.requestMasterId = j;
        this.requestUid = str;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        this.friendService.rejectFriendRequest(this.requestMasterId, this.requestUid);
        return Boolean.valueOf(true);
    }
}
