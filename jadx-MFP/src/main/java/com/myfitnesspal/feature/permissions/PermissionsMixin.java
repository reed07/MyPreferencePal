package com.myfitnesspal.feature.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import dagger.Lazy;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 &2\u00020\u0001:\u0001&B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007JN\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015J?\u0010\u0019\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u001a\"\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015¢\u0006\u0002\u0010\u001bJ_\u0010\u0019\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u001a\"\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015¢\u0006\u0002\u0010\u001cJ\u0012\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0011H\u0016J\u001a\u0010!\u001a\u00020\u00112\b\b\u0001\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/myfitnesspal/feature/permissions/PermissionsMixin;", "Lcom/myfitnesspal/framework/mixin/RunnerLifecycleMixin;", "owner", "Lcom/myfitnesspal/shared/ui/component/MfpUiComponentInterface;", "permissionAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/permissions/PermissionAnalyticsHelper;", "(Lcom/myfitnesspal/shared/ui/component/MfpUiComponentInterface;Ldagger/Lazy;)V", "getOwner", "()Lcom/myfitnesspal/shared/ui/component/MfpUiComponentInterface;", "getPermissionAnalyticsHelper", "()Ldagger/Lazy;", "permissionDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "rxPermissions", "Lcom/myfitnesspal/feature/permissions/RxPermissions;", "checkPermission", "", "permissionType", "", "grantedBlock", "Lkotlin/Function0;", "showRationalBlock", "deniedBlock", "errorBlock", "checkPermissions", "", "([Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "([Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "showPermissionDeniedDialog", "message", "", "dialogNegativeListener", "Lcom/myfitnesspal/shared/ui/dialog/AlertDialogFragmentBase$DialogNegativeListener;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PermissionsMixin.kt */
public final class PermissionsMixin extends RunnerLifecycleMixin {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String PERMISSION_DIALOG_FRAGMENT = "permission_dialog_fragment";
    @NotNull
    private final MfpUiComponentInterface owner;
    @NotNull
    private final Lazy<PermissionAnalyticsHelper> permissionAnalyticsHelper;
    private CompositeDisposable permissionDisposable;
    private RxPermissions rxPermissions;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/feature/permissions/PermissionsMixin$Companion;", "", "()V", "PERMISSION_DIALOG_FRAGMENT", "", "getSettingsIntent", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: PermissionsMixin.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Intent getSettingsIntent(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", context.getPackageName(), null));
        }
    }

    @JvmStatic
    public static final Intent getSettingsIntent(@NotNull Context context) {
        return Companion.getSettingsIntent(context);
    }

    @NotNull
    public final MfpUiComponentInterface getOwner() {
        return this.owner;
    }

    public PermissionsMixin(@NotNull MfpUiComponentInterface mfpUiComponentInterface, @NotNull Lazy<PermissionAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(mfpUiComponentInterface, "owner");
        Intrinsics.checkParameterIsNotNull(lazy, "permissionAnalyticsHelper");
        super(mfpUiComponentInterface);
        this.owner = mfpUiComponentInterface;
        this.permissionAnalyticsHelper = lazy;
    }

    @NotNull
    public final Lazy<PermissionAnalyticsHelper> getPermissionAnalyticsHelper() {
        return this.permissionAnalyticsHelper;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.permissionDisposable = new CompositeDisposable();
        this.rxPermissions = new RxPermissions(this, this.permissionAnalyticsHelper);
    }

    public void onDestroy() {
        CompositeDisposable compositeDisposable = this.permissionDisposable;
        if (compositeDisposable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionDisposable");
        }
        compositeDisposable.dispose();
        super.onDestroy();
    }

    public static /* synthetic */ void checkPermission$default(PermissionsMixin permissionsMixin, String str, Function0 function0, Function0 function02, Function0 function03, Function0 function04, int i, Object obj) {
        Function0 function05 = (i & 2) != 0 ? PermissionsMixin$checkPermission$1.INSTANCE : function0;
        if ((i & 4) != 0) {
            function02 = PermissionsMixin$checkPermission$2.INSTANCE;
        }
        Function0 function06 = function02;
        if ((i & 8) != 0) {
            function03 = PermissionsMixin$checkPermission$3.INSTANCE;
        }
        Function0 function07 = function03;
        if ((i & 16) != 0) {
            function04 = PermissionsMixin$checkPermission$4.INSTANCE;
        }
        permissionsMixin.checkPermission(str, function05, function06, function07, function04);
    }

    public final void checkPermission(@NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function0<Unit> function02, @NotNull Function0<Unit> function03, @NotNull Function0<Unit> function04) {
        Intrinsics.checkParameterIsNotNull(str, "permissionType");
        Intrinsics.checkParameterIsNotNull(function0, "grantedBlock");
        Intrinsics.checkParameterIsNotNull(function02, "showRationalBlock");
        Intrinsics.checkParameterIsNotNull(function03, "deniedBlock");
        Intrinsics.checkParameterIsNotNull(function04, "errorBlock");
        CompositeDisposable compositeDisposable = this.permissionDisposable;
        if (compositeDisposable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionDisposable");
        }
        RxPermissions rxPermissions2 = this.rxPermissions;
        if (rxPermissions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rxPermissions");
        }
        compositeDisposable.add(rxPermissions2.requestEach(str).subscribe(new PermissionsMixin$checkPermission$5(function0, function02, function03), new PermissionsMixin$checkPermission$6(function04)));
    }

    public static /* synthetic */ void checkPermissions$default(PermissionsMixin permissionsMixin, String[] strArr, Function0 function0, Function0 function02, Function0 function03, Function0 function04, int i, Object obj) {
        Function0 function05 = (i & 2) != 0 ? PermissionsMixin$checkPermissions$1.INSTANCE : function0;
        if ((i & 4) != 0) {
            function02 = PermissionsMixin$checkPermissions$2.INSTANCE;
        }
        Function0 function06 = function02;
        if ((i & 8) != 0) {
            function03 = PermissionsMixin$checkPermissions$3.INSTANCE;
        }
        Function0 function07 = function03;
        if ((i & 16) != 0) {
            function04 = PermissionsMixin$checkPermissions$4.INSTANCE;
        }
        permissionsMixin.checkPermissions(strArr, function05, function06, function07, function04);
    }

    public final void checkPermissions(@NotNull String[] strArr, @NotNull Function0<Unit> function0, @NotNull Function0<Unit> function02, @NotNull Function0<Unit> function03, @NotNull Function0<Unit> function04) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissionType");
        Intrinsics.checkParameterIsNotNull(function0, "grantedBlock");
        Intrinsics.checkParameterIsNotNull(function02, "showRationalBlock");
        Intrinsics.checkParameterIsNotNull(function03, "deniedBlock");
        Intrinsics.checkParameterIsNotNull(function04, "errorBlock");
        CompositeDisposable compositeDisposable = this.permissionDisposable;
        if (compositeDisposable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionDisposable");
        }
        RxPermissions rxPermissions2 = this.rxPermissions;
        if (rxPermissions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rxPermissions");
        }
        compositeDisposable.add(rxPermissions2.requestEachCombined((String[]) Arrays.copyOf(strArr, strArr.length)).subscribe(new PermissionsMixin$checkPermissions$5(function0, function02, function03), new PermissionsMixin$checkPermissions$6(function04)));
    }

    public static /* synthetic */ void checkPermissions$default(PermissionsMixin permissionsMixin, String[] strArr, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = PermissionsMixin$checkPermissions$7.INSTANCE;
        }
        if ((i & 4) != 0) {
            function02 = PermissionsMixin$checkPermissions$8.INSTANCE;
        }
        permissionsMixin.checkPermissions(strArr, function0, function02);
    }

    public final void checkPermissions(@NotNull String[] strArr, @NotNull Function0<Unit> function0, @NotNull Function0<Unit> function02) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissionType");
        Intrinsics.checkParameterIsNotNull(function0, "grantedBlock");
        Intrinsics.checkParameterIsNotNull(function02, "deniedBlock");
        CompositeDisposable compositeDisposable = this.permissionDisposable;
        if (compositeDisposable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionDisposable");
        }
        RxPermissions rxPermissions2 = this.rxPermissions;
        if (rxPermissions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rxPermissions");
        }
        compositeDisposable.add(rxPermissions2.requestEachCombined((String[]) Arrays.copyOf(strArr, strArr.length)).subscribe(new PermissionsMixin$checkPermissions$9(function0, function02), PermissionsMixin$checkPermissions$10.INSTANCE));
    }

    public final void showPermissionDeniedDialog(@StringRes int i, @Nullable DialogNegativeListener dialogNegativeListener) {
        Activity activity = this.owner.getActivity();
        if (activity != null) {
            FragmentManager supportFragmentManager = ((AppCompatActivity) activity).getSupportFragmentManager();
            AlertDialogFragment alertDialogFragment = (AlertDialogFragment) supportFragmentManager.findFragmentByTag(PERMISSION_DIALOG_FRAGMENT);
            if (alertDialogFragment != null) {
                supportFragmentManager.beginTransaction().remove(alertDialogFragment).commitNow();
            }
            AlertDialogFragment newInstance = AlertDialogFragment.Companion.newInstance();
            newInstance.setMessage(i);
            ((AlertDialogFragment) newInstance.setPositiveText(R.string.settings, new PermissionsMixin$showPermissionDeniedDialog$$inlined$apply$lambda$1(this, i, dialogNegativeListener))).setNegativeText(R.string.cancel, dialogNegativeListener);
            newInstance.setCancelable(false);
            this.owner.showDialogFragment(newInstance, PERMISSION_DIALOG_FRAGMENT);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
    }
}
