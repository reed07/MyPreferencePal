package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: annotationUtil.kt */
final class AnnotationUtilKt$createDeprecatedAnnotation$replaceWithAnnotation$1 extends Lambda implements Function1<ModuleDescriptor, SimpleType> {
    final /* synthetic */ KotlinBuiltIns $this_createDeprecatedAnnotation;

    AnnotationUtilKt$createDeprecatedAnnotation$replaceWithAnnotation$1(KotlinBuiltIns kotlinBuiltIns) {
        this.$this_createDeprecatedAnnotation = kotlinBuiltIns;
        super(1);
    }

    @NotNull
    public final SimpleType invoke(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        SimpleType arrayType = moduleDescriptor.getBuiltIns().getArrayType(Variance.INVARIANT, this.$this_createDeprecatedAnnotation.getStringType());
        Intrinsics.checkExpressionValueIsNotNull(arrayType, "module.builtIns.getArray…ce.INVARIANT, stringType)");
        return arrayType;
    }
}
