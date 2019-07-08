package com.myfitnesspal.feature.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import dagger.Lazy;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005J\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0005J\u0015\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0001¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0001¢\u0006\u0002\b\u0016J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J+\u0010\u001b\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002¢\u0006\u0002\u0010\"J+\u0010\u001b\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u001dH\u0001¢\u0006\u0004\b'\u0010(J\u001c\u0010)\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00052\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006,"}, d2 = {"Lcom/myfitnesspal/feature/permissions/PermissionsFragment;", "Lcom/myfitnesspal/shared/ui/fragment/MfpFragment;", "()V", "pendingPermissions", "Ljava/util/HashMap;", "", "Lio/reactivex/subjects/PublishSubject;", "Lcom/myfitnesspal/feature/permissions/Permission;", "permissionAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/permissions/PermissionAnalyticsHelper;", "getPermissionAnalyticsHelper", "()Ldagger/Lazy;", "setPermissionAnalyticsHelper", "(Ldagger/Lazy;)V", "containsByPermission", "", "permission", "getSubjectForPermission", "isGranted", "isGranted$app_googleRelease", "isRevoked", "isRevoked$app_googleRelease", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "shouldShowRequestPermissionRationale", "", "([Ljava/lang/String;[I[Z)V", "requestCode", "", "(I[Ljava/lang/String;[I)V", "requestPermissions", "requestPermissions$app_googleRelease", "([Ljava/lang/String;)V", "setPendingPermission", "subject", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PermissionsFragment.kt */
public final class PermissionsFragment extends MfpFragment {
    public static final Companion Companion = new Companion(null);
    public static final int PERMISSIONS_REQUEST_CODE = 55;
    private HashMap _$_findViewCache;
    private final HashMap<String, PublishSubject<Permission>> pendingPermissions = new HashMap<>();
    @Inject
    @NotNull
    public Lazy<PermissionAnalyticsHelper> permissionAnalyticsHelper;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/permissions/PermissionsFragment$Companion;", "", "()V", "PERMISSIONS_REQUEST_CODE", "", "newInstance", "Lcom/myfitnesspal/feature/permissions/PermissionsFragment;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: PermissionsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PermissionsFragment newInstance() {
            return new PermissionsFragment();
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public final Lazy<PermissionAnalyticsHelper> getPermissionAnalyticsHelper() {
        Lazy<PermissionAnalyticsHelper> lazy = this.permissionAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionAnalyticsHelper");
        }
        return lazy;
    }

    public final void setPermissionAnalyticsHelper(@NotNull Lazy<PermissionAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.permissionAnalyticsHelper = lazy;
    }

    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @TargetApi(23)
    public final void requestPermissions$app_googleRelease(@NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        requestPermissions(strArr, 55);
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int i, @NotNull String[] strArr, @NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 55) {
            boolean[] zArr = new boolean[strArr.length];
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                zArr[i2] = shouldShowRequestPermissionRationale(strArr[i2]);
            }
            onRequestPermissionsResult(strArr, iArr, zArr);
        }
    }

    private final void onRequestPermissionsResult(String[] strArr, int[] iArr, boolean[] zArr) {
        int length = strArr.length;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                PublishSubject publishSubject = (PublishSubject) this.pendingPermissions.get(strArr[i]);
                if (publishSubject != null) {
                    Intrinsics.checkExpressionValueIsNotNull(publishSubject, "pendingPermissions[permissions[i]] ?: return");
                    this.pendingPermissions.remove(strArr[i]);
                    if (iArr[i] != 0) {
                        z = false;
                    }
                    Permission permission = new Permission(strArr[i], z, zArr[i]);
                    if (permission.getGranted()) {
                        arrayList.add(permission.getName());
                    } else if (permission.getGranted() || !permission.getShouldShowRequestPermissionRationale()) {
                        arrayList3.add(permission.getName());
                    } else {
                        arrayList2.add(permission.getName());
                    }
                    publishSubject.onNext(permission);
                    publishSubject.onComplete();
                    i++;
                } else {
                    return;
                }
            } else {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    String analyticsScreenTag = ((MfpActivity) activity).getAnalyticsScreenTag();
                    Collection collection = arrayList;
                    if (!collection.isEmpty()) {
                        Lazy<PermissionAnalyticsHelper> lazy = this.permissionAnalyticsHelper;
                        if (lazy == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("permissionAnalyticsHelper");
                        }
                        PermissionAnalyticsHelper permissionAnalyticsHelper2 = (PermissionAnalyticsHelper) lazy.get();
                        Object[] array = collection.toArray(new String[0]);
                        if (array != null) {
                            String[] strArr2 = (String[]) array;
                            permissionAnalyticsHelper2.reportUserPermissionAllowed(analyticsScreenTag, (String[]) Arrays.copyOf(strArr2, strArr2.length));
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                    Collection collection2 = arrayList2;
                    if (!collection2.isEmpty()) {
                        Lazy<PermissionAnalyticsHelper> lazy2 = this.permissionAnalyticsHelper;
                        if (lazy2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("permissionAnalyticsHelper");
                        }
                        PermissionAnalyticsHelper permissionAnalyticsHelper3 = (PermissionAnalyticsHelper) lazy2.get();
                        Object[] array2 = collection2.toArray(new String[0]);
                        if (array2 != null) {
                            String[] strArr3 = (String[]) array2;
                            permissionAnalyticsHelper3.reportUserPermissionDenied(false, analyticsScreenTag, (String[]) Arrays.copyOf(strArr3, strArr3.length));
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                    Collection collection3 = arrayList3;
                    if (!collection3.isEmpty()) {
                        Lazy<PermissionAnalyticsHelper> lazy3 = this.permissionAnalyticsHelper;
                        if (lazy3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("permissionAnalyticsHelper");
                        }
                        PermissionAnalyticsHelper permissionAnalyticsHelper4 = (PermissionAnalyticsHelper) lazy3.get();
                        Object[] array3 = collection3.toArray(new String[0]);
                        if (array3 != null) {
                            String[] strArr4 = (String[]) array3;
                            permissionAnalyticsHelper4.reportUserPermissionDenied(true, analyticsScreenTag, (String[]) Arrays.copyOf(strArr4, strArr4.length));
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.ui.activity.MfpActivity");
            }
        }
    }

    public final void setPendingPermission(@NotNull String str, @NotNull PublishSubject<Permission> publishSubject) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        Intrinsics.checkParameterIsNotNull(publishSubject, "subject");
        this.pendingPermissions.put(str, publishSubject);
    }

    @Nullable
    public final PublishSubject<Permission> getSubjectForPermission(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        return (PublishSubject) this.pendingPermissions.get(str);
    }

    public final boolean containsByPermission(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        return this.pendingPermissions.containsKey(str);
    }

    @TargetApi(23)
    public final boolean isGranted$app_googleRelease(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Activity activity2 = activity;
            return (activity2 != null ? Integer.valueOf(activity2.checkSelfPermission(str)) : null).intValue() == 0;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    @TargetApi(23)
    public final boolean isRevoked$app_googleRelease(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Activity activity2 = activity;
            PackageManager packageManager = activity2 != null ? activity2.getPackageManager() : null;
            if (packageManager == null) {
                Intrinsics.throwNpe();
            }
            return packageManager.isPermissionRevokedByPolicy(str, activity2.getPackageName());
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }
}
