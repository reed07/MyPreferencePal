package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: PackageFragmentProviderImpl.kt */
final class PackageFragmentProviderImpl$getSubPackagesOf$2 extends Lambda implements Function1<FqName, Boolean> {
    final /* synthetic */ FqName $fqName;

    PackageFragmentProviderImpl$getSubPackagesOf$2(FqName fqName) {
        this.$fqName = fqName;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((FqName) obj));
    }

    public final boolean invoke(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "it");
        return !fqName.isRoot() && Intrinsics.areEqual((Object) fqName.parent(), (Object) this.$fqName);
    }
}
