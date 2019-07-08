package com.myfitnesspal.feature.achievementinterstitialad.repository;

import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import io.reactivex.Single;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/repository/UserSummaryRepository;", "", "clearUserSummary", "", "getUserSummary", "Lio/reactivex/Single;", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: UserSummaryRepository.kt */
public interface UserSummaryRepository {
    void clearUserSummary();

    @NotNull
    Single<UserSummaryObject> getUserSummary();
}
