package com.myfitnesspal.feature.permissions;

import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003¢\u0006\u0002\u0010\u0004B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\bHÆ\u0003J\t\u0010\u0012\u001a\u00020\bHÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/myfitnesspal/feature/permissions/Permission;", "", "permissions", "", "(Ljava/util/List;)V", "name", "", "granted", "", "shouldShowRequestPermissionRationale", "(Ljava/lang/String;ZZ)V", "getGranted", "()Z", "getName", "()Ljava/lang/String;", "getShouldShowRequestPermissionRationale", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: Permission.kt */
public final class Permission {
    public static final Companion Companion = new Companion(null);
    private final boolean granted;
    @NotNull
    private final String name;
    private final boolean shouldShowRequestPermissionRationale;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u0016\u0010\b\u001a\u00020\t2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u0016\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/permissions/Permission$Companion;", "", "()V", "combineGranted", "", "permissions", "", "Lcom/myfitnesspal/feature/permissions/Permission;", "combineName", "", "combineShouldShowRequestPermissionRationale", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Permission.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String combineName(List<Permission> list) {
            String sb = ((StringBuilder) Observable.fromIterable(list).map(Permission$Companion$combineName$1.INSTANCE).collectInto(new StringBuilder(), Permission$Companion$combineName$2.INSTANCE).blockingGet()).toString();
            Intrinsics.checkExpressionValueIsNotNull(sb, "Observable.fromIterable(….blockingGet().toString()");
            return sb;
        }

        /* access modifiers changed from: private */
        public final boolean combineGranted(List<Permission> list) {
            Object blockingGet = Observable.fromIterable(list).all(Permission$Companion$combineGranted$1.INSTANCE).blockingGet();
            Intrinsics.checkExpressionValueIsNotNull(blockingGet, "Observable.fromIterable(…?: false }).blockingGet()");
            return ((Boolean) blockingGet).booleanValue();
        }

        /* access modifiers changed from: private */
        public final boolean combineShouldShowRequestPermissionRationale(List<Permission> list) {
            Object blockingGet = Observable.fromIterable(list).any(Permission$Companion$combineShouldShowRequestPermissionRationale$1.INSTANCE).blockingGet();
            Intrinsics.checkExpressionValueIsNotNull(blockingGet, "Observable.fromIterable(…?: false }).blockingGet()");
            return ((Boolean) blockingGet).booleanValue();
        }
    }

    @NotNull
    public static /* synthetic */ Permission copy$default(Permission permission, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = permission.name;
        }
        if ((i & 2) != 0) {
            z = permission.granted;
        }
        if ((i & 4) != 0) {
            z2 = permission.shouldShowRequestPermissionRationale;
        }
        return permission.copy(str, z, z2);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    public final boolean component2() {
        return this.granted;
    }

    public final boolean component3() {
        return this.shouldShowRequestPermissionRationale;
    }

    @NotNull
    public final Permission copy(@NotNull String str, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        return new Permission(str, z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Permission) {
                Permission permission = (Permission) obj;
                if (Intrinsics.areEqual((Object) this.name, (Object) permission.name)) {
                    if (this.granted == permission.granted) {
                        if (this.shouldShowRequestPermissionRationale == permission.shouldShowRequestPermissionRationale) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.granted;
        if (z) {
            z = true;
        }
        int i = (hashCode + (z ? 1 : 0)) * 31;
        boolean z2 = this.shouldShowRequestPermissionRationale;
        if (z2) {
            z2 = true;
        }
        return i + (z2 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Permission(name=");
        sb.append(this.name);
        sb.append(", granted=");
        sb.append(this.granted);
        sb.append(", shouldShowRequestPermissionRationale=");
        sb.append(this.shouldShowRequestPermissionRationale);
        sb.append(")");
        return sb.toString();
    }

    public Permission(@NotNull String str, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        this.name = str;
        this.granted = z;
        this.shouldShowRequestPermissionRationale = z2;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final boolean getGranted() {
        return this.granted;
    }

    public /* synthetic */ Permission(String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        this(str, z, z2);
    }

    public final boolean getShouldShowRequestPermissionRationale() {
        return this.shouldShowRequestPermissionRationale;
    }

    public Permission(@NotNull List<Permission> list) {
        Intrinsics.checkParameterIsNotNull(list, "permissions");
        this(Companion.combineName(list), Companion.combineGranted(list), Companion.combineShouldShowRequestPermissionRationale(list));
    }
}
