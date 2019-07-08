package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.MiniUserInfoDto;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.model.v15.StatusUpdateObject;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import java.util.Collection;
import java.util.List;

public class MiniUserInfoMapperImpl implements MiniUserInfoMapper {
    public MiniUserInfo mapFrom(StatusUpdateObject statusUpdateObject) {
        MiniUserInfo miniUserInfo = new MiniUserInfo();
        miniUserInfo.setUsername(statusUpdateObject.getCreatingUserUsername());
        miniUserInfo.setImageUrl(statusUpdateObject.getCreatingUserImageUrl());
        miniUserInfo.setMasterDatabaseId(statusUpdateObject.getCreatingUserMasterId());
        miniUserInfo.setUid(statusUpdateObject.getCreatingUserUid());
        return miniUserInfo;
    }

    public MiniUserInfo mapFrom(FriendRequestObject friendRequestObject) {
        MiniUserInfo miniUserInfo = new MiniUserInfo();
        miniUserInfo.setMasterDatabaseId(friendRequestObject.getOtherPartyMasterId());
        miniUserInfo.setUsername(friendRequestObject.getOtherPartyUsername());
        miniUserInfo.setImageUrl(friendRequestObject.getOtherPartyImageUrl());
        return miniUserInfo;
    }

    public MiniUserInfo mapFrom(UserSummaryObject userSummaryObject) {
        MiniUserInfo miniUserInfo = new MiniUserInfo();
        miniUserInfo.setMasterDatabaseId(userSummaryObject.getMasterId());
        miniUserInfo.setUid(userSummaryObject.getUid());
        miniUserInfo.setUsername(userSummaryObject.getUsername());
        miniUserInfo.setImageUrl(userSummaryObject.getThumbnailImageUrl());
        miniUserInfo.setFullsizeImageURL(userSummaryObject.getFullSizeImageUrl());
        miniUserInfo.setLocalId(userSummaryObject.getLocalId());
        miniUserInfo.setPoundsLost(userSummaryObject.getPoundsLost());
        int diaryFlags = userSummaryObject.getDiaryFlags();
        int i = diaryFlags != 4 ? diaryFlags != 8 ? diaryFlags != 12 ? 0 : 3 : 2 : 1;
        miniUserInfo.setDiaryPrivacySetting(i);
        miniUserInfo.setCity(userSummaryObject.getCity());
        miniUserInfo.setFriendCount(userSummaryObject.getFriendCount());
        miniUserInfo.setLastLoginDate(userSummaryObject.getLastLoginTimestamp());
        miniUserInfo.setLoginStreak(userSummaryObject.getLoginStreak());
        miniUserInfo.setIsFriend(userSummaryObject.isFriendOfRequestor());
        miniUserInfo.setGender(userSummaryObject.isMale() ? 1 : 0);
        miniUserInfo.setProfileViewable(userSummaryObject.isProfileViewable());
        return miniUserInfo;
    }

    public List<MiniUserInfo> mapFromList(List<UserSummaryObject> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1<MiniUserInfo, UserSummaryObject>() {
            public MiniUserInfo execute(UserSummaryObject userSummaryObject) {
                return MiniUserInfoMapperImpl.this.mapFrom(userSummaryObject);
            }
        });
    }

    public MiniUserInfoDto mapFrom(MiniUserInfo miniUserInfo) {
        MiniUserInfoDto miniUserInfoDto = new MiniUserInfoDto();
        miniUserInfoDto.setUsername(miniUserInfo.getUsername());
        miniUserInfoDto.setImageURL(miniUserInfo.getImageUrl());
        miniUserInfoDto.setMasterId(miniUserInfo.getMasterDatabaseId());
        miniUserInfoDto.setUid(miniUserInfo.getUid());
        return miniUserInfoDto;
    }
}
