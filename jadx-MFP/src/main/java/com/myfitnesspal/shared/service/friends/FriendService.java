package com.myfitnesspal.shared.service.friends;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import java.util.List;

public interface FriendService {
    void acceptFriendRequest(long j, String str) throws ApiException;

    List<UserSummaryObject> fetchFriends() throws ApiException;

    void fetchFriends(Function1<List<UserSummaryObject>> function1, ApiErrorCallback apiErrorCallback);

    List<FriendRequestObject> fetchRequests(int i, int i2, int i3) throws ApiException;

    void getFriendCount(Function1<Integer> function1);

    void rejectFriendRequest(long j, String str) throws ApiException;

    void sendInvitations(List<String> list, String str, String str2, Function0 function0, ApiErrorCallback apiErrorCallback);

    void unfriendUserId(long j, String str, Function0 function0, ApiErrorCallback apiErrorCallback);
}
