package com.myfitnesspal.feature.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build.VERSION;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 /2\u00020\u0001:\u0001/B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J'\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014H\u0003¢\u0006\u0002\u0010\u001cJ3\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u00182\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014H\u0002¢\u0006\u0002\u0010\u001fJ&\u0010 \u001a\u0006\u0012\u0002\b\u00030\u00182\f\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0002J%\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u00182\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014H\u0002¢\u0006\u0002\u0010\u001cJ%\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00120\u00182\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014¢\u0006\u0002\u0010\u001cJ%\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014¢\u0006\u0002\u0010\u001cJ%\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014¢\u0006\u0002\u0010\u001cJ\u001b\u0010&\u001a\u00020'2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u001bH\u0003¢\u0006\u0002\u0010(J3\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020\u00120*\"\u0004\b\u0000\u0010+2\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014H\u0002¢\u0006\u0002\u0010,J3\u0010-\u001a\u000e\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020\u00190*\"\u0004\b\u0000\u0010+2\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014H\u0002¢\u0006\u0002\u0010,J3\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u00020\u00190*\"\u0004\b\u0000\u0010+2\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001b\"\u00020\u0014H\u0002¢\u0006\u0002\u0010,R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u00060"}, d2 = {"Lcom/myfitnesspal/feature/permissions/RxPermissions;", "", "mixin", "Lcom/myfitnesspal/feature/permissions/PermissionsMixin;", "permissionAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/permissions/PermissionAnalyticsHelper;", "(Lcom/myfitnesspal/feature/permissions/PermissionsMixin;Ldagger/Lazy;)V", "permissionsFragment", "Lcom/myfitnesspal/feature/permissions/PermissionsFragment;", "getPermissionsFragment", "()Lcom/myfitnesspal/feature/permissions/PermissionsFragment;", "permissionsFragment$delegate", "Lkotlin/Lazy;", "findPermissionsFragment", "activity", "Landroid/app/Activity;", "isGranted", "", "permission", "", "isMarshmallow", "isRevoked", "makeActualRequest", "Lio/reactivex/Observable;", "Lcom/myfitnesspal/feature/permissions/Permission;", "permissions", "", "([Ljava/lang/String;)Lio/reactivex/Observable;", "makeRequest", "requestObject", "(Lio/reactivex/Observable;[Ljava/lang/String;)Lio/reactivex/Observable;", "oneOf", "trigger", "pending", "request", "requestEach", "requestEachCombined", "requestPermissionsFromFragment", "", "([Ljava/lang/String;)V", "verify", "Lio/reactivex/ObservableTransformer;", "T", "([Ljava/lang/String;)Lio/reactivex/ObservableTransformer;", "verifyEach", "verifyEachCombined", "Request", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: RxPermissions.kt */
public final class RxPermissions {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(RxPermissions.class), "permissionsFragment", "getPermissionsFragment()Lcom/myfitnesspal/feature/permissions/PermissionsFragment;"))};
    @Deprecated
    public static final Request Request = new Request(null);
    @NotNull
    public static final String TAG = "RxPermissions";
    /* access modifiers changed from: private */
    public final PermissionsMixin mixin;
    private final Lazy<PermissionAnalyticsHelper> permissionAnalyticsHelper;
    private final kotlin.Lazy permissionsFragment$delegate = LazyKt.lazy(new RxPermissions$permissionsFragment$2(this));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/permissions/RxPermissions$Request;", "", "()V", "TAG", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RxPermissions.kt */
    private static final class Request {
        private Request() {
        }

        public /* synthetic */ Request(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final PermissionsFragment getPermissionsFragment() {
        kotlin.Lazy lazy = this.permissionsFragment$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (PermissionsFragment) lazy.getValue();
    }

    public RxPermissions(@NotNull PermissionsMixin permissionsMixin, @NotNull Lazy<PermissionAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(permissionsMixin, "mixin");
        Intrinsics.checkParameterIsNotNull(lazy, "permissionAnalyticsHelper");
        this.mixin = permissionsMixin;
        this.permissionAnalyticsHelper = lazy;
    }

    /* access modifiers changed from: private */
    public final PermissionsFragment findPermissionsFragment(Activity activity) {
        Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag(TAG);
        if (!(findFragmentByTag instanceof PermissionsFragment)) {
            findFragmentByTag = null;
        }
        return (PermissionsFragment) findFragmentByTag;
    }

    @NotNull
    public final Observable<Boolean> request(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Observable<Boolean> compose = Observable.just(Request).compose(verify((String[]) Arrays.copyOf(strArr, strArr.length)));
        Intrinsics.checkExpressionValueIsNotNull(compose, "Observable.just(RxPermis…ose(verify(*permissions))");
        return compose;
    }

    @NotNull
    public final Observable<Permission> requestEach(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Observable<Permission> compose = Observable.just(Request).compose(verifyEach((String[]) Arrays.copyOf(strArr, strArr.length)));
        Intrinsics.checkExpressionValueIsNotNull(compose, "Observable.just<Any>(RxP…yEach<Any>(*permissions))");
        return compose;
    }

    @NotNull
    public final Observable<Permission> requestEachCombined(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Observable<Permission> compose = Observable.just(Request).compose(verifyEachCombined((String[]) Arrays.copyOf(strArr, strArr.length)));
        Intrinsics.checkExpressionValueIsNotNull(compose, "Observable.just<Any>(RxP…bined<Any>(*permissions))");
        return compose;
    }

    private final <T> ObservableTransformer<T, Boolean> verify(String... strArr) {
        return new RxPermissions$verify$1<>(this, strArr);
    }

    private final <T> ObservableTransformer<T, Permission> verifyEach(String... strArr) {
        return new RxPermissions$verifyEach$1<>(this, strArr);
    }

    private final <T> ObservableTransformer<T, Permission> verifyEachCombined(String... strArr) {
        return new RxPermissions$verifyEachCombined$1<>(this, strArr);
    }

    /* access modifiers changed from: private */
    public final Observable<Permission> makeRequest(Observable<?> observable, String... strArr) {
        if (strArr != null) {
            if (!(strArr.length == 0)) {
                Observable<Permission> flatMap = oneOf(observable, pending((String[]) Arrays.copyOf(strArr, strArr.length))).flatMap(new RxPermissions$makeRequest$1(this, strArr));
                Intrinsics.checkExpressionValueIsNotNull(flatMap, "oneOf(requestObject, pen…lRequest(*permissions) })");
                return flatMap;
            }
        }
        throw new IllegalArgumentException("Permissions request requires at least one input permission");
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    public final Observable<Permission> makeActualRequest(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        ArrayList arrayList2 = new ArrayList();
        for (String str : strArr) {
            if (isGranted(str)) {
                arrayList.add(Observable.just(new Permission(str, true, false)));
            } else if (isRevoked(str)) {
                arrayList.add(Observable.just(new Permission(str, false, false)));
            } else {
                PublishSubject subjectForPermission = getPermissionsFragment().getSubjectForPermission(str);
                if (subjectForPermission == null) {
                    arrayList2.add(str);
                    subjectForPermission = PublishSubject.create();
                    PermissionsFragment permissionsFragment = getPermissionsFragment();
                    if (subjectForPermission == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(subjectForPermission, "subject!!");
                    permissionsFragment.setPendingPermission(str, subjectForPermission);
                }
                arrayList.add(subjectForPermission);
            }
        }
        if (!arrayList2.isEmpty()) {
            Object[] array = arrayList2.toArray(new String[0]);
            if (array != null) {
                String[] strArr2 = (String[]) array;
                requestPermissionsFromFragment(strArr2);
                PermissionAnalyticsHelper permissionAnalyticsHelper2 = (PermissionAnalyticsHelper) this.permissionAnalyticsHelper.get();
                Activity activity = this.mixin.getOwner().getActivity();
                if (activity != null) {
                    permissionAnalyticsHelper2.reportUserPermissionDisplayed(((MfpActivity) activity).getAnalyticsScreenTag(), (String[]) Arrays.copyOf(strArr2, strArr2.length));
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.ui.activity.MfpActivity");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        Observable<Permission> concat = Observable.concat(Observable.fromIterable(arrayList));
        Intrinsics.checkExpressionValueIsNotNull(concat, "Observable.concat(Observable.fromIterable(list))");
        return concat;
    }

    @TargetApi(23)
    private final void requestPermissionsFromFragment(String[] strArr) {
        getPermissionsFragment().requestPermissions$app_googleRelease(strArr);
    }

    private final Observable<?> pending(String... strArr) {
        for (String containsByPermission : strArr) {
            if (!getPermissionsFragment().containsByPermission(containsByPermission)) {
                Observable<?> empty = Observable.empty();
                Intrinsics.checkExpressionValueIsNotNull(empty, "Observable.empty<Any>()");
                return empty;
            }
        }
        Observable<?> just = Observable.just(Request);
        Intrinsics.checkExpressionValueIsNotNull(just, "Observable.just<Any>(RxPermissions.Request)");
        return just;
    }

    private final Observable<?> oneOf(Observable<?> observable, Observable<?> observable2) {
        if (observable == null) {
            Observable<?> just = Observable.just(Request);
            Intrinsics.checkExpressionValueIsNotNull(just, "Observable.just<Any>(RxPermissions.Request)");
            return just;
        }
        Observable<?> merge = Observable.merge(observable, observable2);
        Intrinsics.checkExpressionValueIsNotNull(merge, "Observable.merge(trigger, pending)");
        return merge;
    }

    private final boolean isGranted(String str) {
        return !isMarshmallow() || getPermissionsFragment().isGranted$app_googleRelease(str);
    }

    private final boolean isRevoked(String str) {
        return isMarshmallow() && getPermissionsFragment().isRevoked$app_googleRelease(str);
    }

    private final boolean isMarshmallow() {
        return VERSION.SDK_INT >= 23;
    }
}
