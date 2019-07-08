package com.myfitnesspal.feature.achievementinterstitialad.repository;

import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: UserSummaryRepositoryImpl.kt */
final class UserSummaryRepositoryImpl$requestNewUserSummary$1<V> implements Callable<T> {
    final /* synthetic */ UserSummaryRepositoryImpl this$0;

    UserSummaryRepositoryImpl$requestNewUserSummary$1(UserSummaryRepositoryImpl userSummaryRepositoryImpl) {
        this.this$0 = userSummaryRepositoryImpl;
    }

    @Nullable
    public final UserSummaryObject call() {
        UserSummaryObject userSummaryObject = null;
        MiniUserInfo forCurrentUser = MiniUserInfo.forCurrentUser((Session) this.this$0.session.get());
        return forCurrentUser != null ? ((UserSummaryService) this.this$0.userSummaryService.get()).fetchUserSummary(forCurrentUser.getUsername(), forCurrentUser.getUid()) : userSummaryObject;
    }
}
