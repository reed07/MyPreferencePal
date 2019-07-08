package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: ReflectionTypes.kt */
final class ReflectionTypes$kotlinReflectScope$2 extends Lambda implements Function0<MemberScope> {
    final /* synthetic */ ModuleDescriptor $module;

    ReflectionTypes$kotlinReflectScope$2(ModuleDescriptor moduleDescriptor) {
        this.$module = moduleDescriptor;
        super(0);
    }

    @NotNull
    public final MemberScope invoke() {
        return this.$module.getPackage(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME()).getMemberScope();
    }
}
