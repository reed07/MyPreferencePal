package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/PackageViewDescriptor;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KPackageImpl.kt */
final class KPackageImpl$Data$descriptor$2 extends Lambda implements Function0<PackageViewDescriptor> {
    final /* synthetic */ Data this$0;

    KPackageImpl$Data$descriptor$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    @NotNull
    public final PackageViewDescriptor invoke() {
        RuntimeModuleData moduleData = this.this$0.getModuleData();
        ReflectKotlinClass access$getKotlinClass$p = this.this$0.getKotlinClass();
        if (access$getKotlinClass$p != null) {
            String packageModuleName = UtilKt.getPackageModuleName(access$getKotlinClass$p);
            if (packageModuleName != null) {
                moduleData.getPackagePartProvider().registerModule(packageModuleName);
            }
        }
        ModuleDescriptor module = moduleData.getModule();
        FqName packageFqName = ReflectClassUtilKt.getClassId(KPackageImpl.this.getJClass()).getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "jClass.classId.packageFqName");
        return module.getPackage(packageFqName);
    }
}
