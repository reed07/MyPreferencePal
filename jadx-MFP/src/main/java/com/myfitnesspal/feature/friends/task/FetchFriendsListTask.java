package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.TaskBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.service.friends.FriendService;
import dagger.Lazy;
import java.util.List;

public class FetchFriendsListTask extends TaskBase<List<MiniUserInfo>, ApiException> {
    private final Lazy<FriendService> friendService;
    private final Lazy<MiniUserInfoMapper> miniUserInfoMapper;

    public FetchFriendsListTask(Lazy<FriendService> lazy, Lazy<MiniUserInfoMapper> lazy2) {
        this.friendService = lazy;
        this.miniUserInfoMapper = lazy2;
    }

    /* access modifiers changed from: protected */
    public List<MiniUserInfo> exec(Context context) throws ApiException {
        return ((MiniUserInfoMapper) this.miniUserInfoMapper.get()).mapFromList(((FriendService) this.friendService.get()).fetchFriends());
    }
}
