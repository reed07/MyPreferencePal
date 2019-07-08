package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "uso", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "kotlin.jvm.PlatformType", "execute"}, k = 3, mv = {1, 1, 13})
/* compiled from: UserSummaryServiceTestImpl.kt */
final class UserSummaryServiceTestImpl$fetchUserSummaryAsync$1<T> implements Function1<UserSummaryObject> {
    final /* synthetic */ Function1 $successCallback;
    final /* synthetic */ UserSummaryServiceTestImpl this$0;

    UserSummaryServiceTestImpl$fetchUserSummaryAsync$1(UserSummaryServiceTestImpl userSummaryServiceTestImpl, Function1 function1) {
        this.this$0 = userSummaryServiceTestImpl;
        this.$successCallback = function1;
    }

    public final void execute(UserSummaryObject userSummaryObject) {
        UserSummaryServiceTestImpl userSummaryServiceTestImpl = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(userSummaryObject, "uso");
        userSummaryServiceTestImpl.handleUserSummaryObject(userSummaryObject);
        this.this$0.setCache(userSummaryObject);
        FunctionUtils.invokeIfValid(this.$successCallback, userSummaryObject);
    }
}
