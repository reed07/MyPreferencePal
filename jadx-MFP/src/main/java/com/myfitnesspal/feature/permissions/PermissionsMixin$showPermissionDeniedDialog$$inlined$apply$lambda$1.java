package com.myfitnesspal.feature.permissions;

import android.app.Activity;
import com.myfitnesspal.feature.permissions.PermissionsMixin.Companion;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onClick", "com/myfitnesspal/feature/permissions/PermissionsMixin$showPermissionDeniedDialog$df$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: PermissionsMixin.kt */
final class PermissionsMixin$showPermissionDeniedDialog$$inlined$apply$lambda$1<TData> implements DialogPositiveListener<Object> {
    final /* synthetic */ DialogNegativeListener $dialogNegativeListener$inlined;
    final /* synthetic */ int $message$inlined;
    final /* synthetic */ PermissionsMixin this$0;

    PermissionsMixin$showPermissionDeniedDialog$$inlined$apply$lambda$1(PermissionsMixin permissionsMixin, int i, DialogNegativeListener dialogNegativeListener) {
        this.this$0 = permissionsMixin;
        this.$message$inlined = i;
        this.$dialogNegativeListener$inlined = dialogNegativeListener;
    }

    public final void onClick(Object obj) {
        NavigationHelper navigationHelper = this.this$0.getOwner().getNavigationHelper();
        Companion companion = PermissionsMixin.Companion;
        Activity activity = this.this$0.getOwner().getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "owner.activity");
        navigationHelper.withIntent(companion.getSettingsIntent(activity)).startActivity(RequestCodes.OS_SETTINGS);
    }
}
