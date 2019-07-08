package com.myfitnesspal.feature.permissions;

import com.uacf.core.util.Ln;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "permission", "Lcom/myfitnesspal/feature/permissions/Permission;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: PermissionsMixin.kt */
final class PermissionsMixin$checkPermissions$9<T> implements Consumer<Permission> {
    final /* synthetic */ Function0 $deniedBlock;
    final /* synthetic */ Function0 $grantedBlock;

    PermissionsMixin$checkPermissions$9(Function0 function0, Function0 function02) {
        this.$grantedBlock = function0;
        this.$deniedBlock = function02;
    }

    public final void accept(Permission permission) {
        if (permission.getGranted()) {
            Ln.d("Permission granted", new Object[0]);
            this.$grantedBlock.invoke();
        } else if (!permission.getShouldShowRequestPermissionRationale()) {
            Ln.e("Permanently denied permission", new Object[0]);
            this.$deniedBlock.invoke();
        }
    }
}
