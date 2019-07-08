package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.MiniUserInfoDto;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.model.v15.StatusUpdateObject;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import java.util.List;

public interface MiniUserInfoMapper {
    MiniUserInfoDto mapFrom(MiniUserInfo miniUserInfo);

    MiniUserInfo mapFrom(FriendRequestObject friendRequestObject);

    MiniUserInfo mapFrom(StatusUpdateObject statusUpdateObject);

    MiniUserInfo mapFrom(UserSummaryObject userSummaryObject);

    List<MiniUserInfo> mapFromList(List<UserSummaryObject> list);
}
