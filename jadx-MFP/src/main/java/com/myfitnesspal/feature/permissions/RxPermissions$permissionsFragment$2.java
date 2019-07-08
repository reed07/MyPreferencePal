package com.myfitnesspal.feature.permissions;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/permissions/PermissionsFragment;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: RxPermissions.kt */
final class RxPermissions$permissionsFragment$2 extends Lambda implements Function0<PermissionsFragment> {
    final /* synthetic */ RxPermissions this$0;

    RxPermissions$permissionsFragment$2(RxPermissions rxPermissions) {
        this.this$0 = rxPermissions;
        super(0);
    }

    @NotNull
    public final PermissionsFragment invoke() {
        Activity activity = this.this$0.mixin.getOwner().getActivity();
        if (activity != null) {
            MfpActivity mfpActivity = (MfpActivity) activity;
            PermissionsFragment access$findPermissionsFragment = this.this$0.findPermissionsFragment(mfpActivity);
            if (access$findPermissionsFragment != null) {
                return access$findPermissionsFragment;
            }
            PermissionsFragment newInstance = PermissionsFragment.Companion.newInstance();
            FragmentManager supportFragmentManager = mfpActivity.getSupportFragmentManager();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            Intrinsics.checkExpressionValueIsNotNull(beginTransaction, "beginTransaction()");
            Fragment fragment = newInstance;
            RxPermissions.Request;
            beginTransaction.add(fragment, RxPermissions.TAG);
            beginTransaction.commitAllowingStateLoss();
            supportFragmentManager.executePendingTransactions();
            return newInstance;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.ui.activity.MfpActivity");
    }
}
