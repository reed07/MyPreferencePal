package com.myfitnesspal.feature.permissions;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.Arrays;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/myfitnesspal/feature/permissions/Permission;", "it", "", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: RxPermissions.kt */
final class RxPermissions$makeRequest$1<T, R> implements Function<T, ObservableSource<? extends R>> {
    final /* synthetic */ String[] $permissions;
    final /* synthetic */ RxPermissions this$0;

    RxPermissions$makeRequest$1(RxPermissions rxPermissions, String[] strArr) {
        this.this$0 = rxPermissions;
        this.$permissions = strArr;
    }

    @NotNull
    public final Observable<Permission> apply(Object obj) {
        RxPermissions rxPermissions = this.this$0;
        String[] strArr = this.$permissions;
        return rxPermissions.makeActualRequest((String[]) Arrays.copyOf(strArr, strArr.length));
    }
}
