package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedClassDataFinder.kt */
public final class DeserializedClassDataFinder implements ClassDataFinder {
    private final PackageFragmentProvider packageFragmentProvider;

    public DeserializedClassDataFinder(@NotNull PackageFragmentProvider packageFragmentProvider2) {
        Intrinsics.checkParameterIsNotNull(packageFragmentProvider2, "packageFragmentProvider");
        this.packageFragmentProvider = packageFragmentProvider2;
    }

    @Nullable
    public ClassData findClassData(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        PackageFragmentProvider packageFragmentProvider2 = this.packageFragmentProvider;
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        for (PackageFragmentDescriptor packageFragmentDescriptor : packageFragmentProvider2.getPackageFragments(packageFqName)) {
            if (packageFragmentDescriptor instanceof DeserializedPackageFragment) {
                ClassData findClassData = ((DeserializedPackageFragment) packageFragmentDescriptor).getClassDataFinder().findClassData(classId);
                if (findClassData != null) {
                    return findClassData;
                }
            }
        }
        return null;
    }
}
