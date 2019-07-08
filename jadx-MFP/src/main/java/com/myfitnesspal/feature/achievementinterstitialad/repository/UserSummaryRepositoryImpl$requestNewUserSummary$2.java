package com.myfitnesspal.feature.achievementinterstitialad.repository;

import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "userSummary", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: UserSummaryRepositoryImpl.kt */
final class UserSummaryRepositoryImpl$requestNewUserSummary$2<T, R> implements Function<T, R> {
    final /* synthetic */ UserSummaryRepositoryImpl this$0;

    UserSummaryRepositoryImpl$requestNewUserSummary$2(UserSummaryRepositoryImpl userSummaryRepositoryImpl) {
        this.this$0 = userSummaryRepositoryImpl;
    }

    @NotNull
    public final UserSummaryObject apply(@NotNull UserSummaryObject userSummaryObject) {
        Intrinsics.checkParameterIsNotNull(userSummaryObject, "userSummary");
        this.this$0.cachedUserSummaryObject = userSummaryObject;
        return userSummaryObject;
    }
}
