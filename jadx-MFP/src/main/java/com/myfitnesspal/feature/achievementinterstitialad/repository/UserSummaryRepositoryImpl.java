package com.myfitnesspal.feature.achievementinterstitialad.repository;

import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import dagger.Lazy;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\rH\u0016J\u0010\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\rH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/repository/UserSummaryRepositoryImpl;", "Lcom/myfitnesspal/feature/achievementinterstitialad/repository/UserSummaryRepository;", "session", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/session/Session;", "userSummaryService", "Lcom/myfitnesspal/shared/service/userdata/UserSummaryService;", "(Ldagger/Lazy;Ldagger/Lazy;)V", "cachedUserSummaryObject", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "clearUserSummary", "", "getUserSummary", "Lio/reactivex/Single;", "requestNewUserSummary", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: UserSummaryRepositoryImpl.kt */
public final class UserSummaryRepositoryImpl implements UserSummaryRepository {
    /* access modifiers changed from: private */
    public UserSummaryObject cachedUserSummaryObject;
    /* access modifiers changed from: private */
    public final Lazy<Session> session;
    /* access modifiers changed from: private */
    public final Lazy<UserSummaryService> userSummaryService;

    public UserSummaryRepositoryImpl(@NotNull Lazy<Session> lazy, @NotNull Lazy<UserSummaryService> lazy2) {
        Intrinsics.checkParameterIsNotNull(lazy, "session");
        Intrinsics.checkParameterIsNotNull(lazy2, "userSummaryService");
        this.session = lazy;
        this.userSummaryService = lazy2;
    }

    @NotNull
    public Single<UserSummaryObject> getUserSummary() {
        UserSummaryObject userSummaryObject = this.cachedUserSummaryObject;
        if (userSummaryObject == null) {
            return requestNewUserSummary();
        }
        Single<UserSummaryObject> just = Single.just(userSummaryObject);
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(cachedUserSummaryObject)");
        return just;
    }

    public void clearUserSummary() {
        this.cachedUserSummaryObject = null;
    }

    private final Single<UserSummaryObject> requestNewUserSummary() {
        Single<UserSummaryObject> map = Single.fromCallable(new UserSummaryRepositoryImpl$requestNewUserSummary$1(this)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new UserSummaryRepositoryImpl$requestNewUserSummary$2(this));
        Intrinsics.checkExpressionValueIsNotNull(map, "Single\n                .…Summary\n                }");
        return map;
    }
}
