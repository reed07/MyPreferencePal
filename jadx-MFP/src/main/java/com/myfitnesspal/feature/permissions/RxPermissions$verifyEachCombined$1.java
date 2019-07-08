package com.myfitnesspal.feature.permissions;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001\"\u0004\b\u0000\u0010\u00042\u0014\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u0001H\u0004H\u00040\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/myfitnesspal/feature/permissions/Permission;", "kotlin.jvm.PlatformType", "T", "o", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: RxPermissions.kt */
final class RxPermissions$verifyEachCombined$1<Upstream, Downstream> implements ObservableTransformer<T, Permission> {
    final /* synthetic */ String[] $permissions;
    final /* synthetic */ RxPermissions this$0;

    RxPermissions$verifyEachCombined$1(RxPermissions rxPermissions, String[] strArr) {
        this.this$0 = rxPermissions;
        this.$permissions = strArr;
    }

    public final Observable<Permission> apply(@NotNull Observable<T> observable) {
        Intrinsics.checkParameterIsNotNull(observable, "o");
        RxPermissions rxPermissions = this.this$0;
        String[] strArr = this.$permissions;
        return rxPermissions.makeRequest(observable, (String[]) Arrays.copyOf(strArr, strArr.length)).buffer(this.$permissions.length).flatMap(AnonymousClass1.INSTANCE);
    }
}
