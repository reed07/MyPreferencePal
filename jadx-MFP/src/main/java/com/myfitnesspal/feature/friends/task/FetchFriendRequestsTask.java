package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.TaskBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.service.friends.FriendService;
import java.util.List;

public class FetchFriendRequestsTask extends TaskBase<List<FriendRequestObject>, ApiException> {
    private static final int DEFAULT_MAX_ITEMS = 500;
    private static final int DEFAULT_OFFSET = 0;
    private FriendService friendService;

    public FetchFriendRequestsTask(FriendService friendService2) {
        this.friendService = friendService2;
    }

    /* access modifiers changed from: protected */
    public List<FriendRequestObject> exec(Context context) throws ApiException {
        return this.friendService.fetchRequests(1, 500, 0);
    }
}
