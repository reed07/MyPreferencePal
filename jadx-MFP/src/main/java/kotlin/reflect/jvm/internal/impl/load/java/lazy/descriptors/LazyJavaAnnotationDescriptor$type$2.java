package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaAnnotationDescriptor.kt */
final class LazyJavaAnnotationDescriptor$type$2 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ LazyJavaAnnotationDescriptor this$0;

    LazyJavaAnnotationDescriptor$type$2(LazyJavaAnnotationDescriptor lazyJavaAnnotationDescriptor) {
        this.this$0 = lazyJavaAnnotationDescriptor;
        super(0);
    }

    @NotNull
    public final SimpleType invoke() {
        FqName fqName = this.this$0.getFqName();
        if (fqName != null) {
            Intrinsics.checkExpressionValueIsNotNull(fqName, "fqName ?: return@createL…fqName: $javaAnnotation\")");
            ClassDescriptor mapJavaToKotlin$default = JavaToKotlinClassMap.mapJavaToKotlin$default(JavaToKotlinClassMap.INSTANCE, fqName, this.this$0.c.getModule().getBuiltIns(), null, 4, null);
            if (mapJavaToKotlin$default == null) {
                JavaClass resolve = this.this$0.javaAnnotation.resolve();
                mapJavaToKotlin$default = resolve != null ? this.this$0.c.getComponents().getModuleClassResolver().resolveClass(resolve) : null;
            }
            if (mapJavaToKotlin$default == null) {
                mapJavaToKotlin$default = this.this$0.createTypeForMissingDependencies(fqName);
            }
            return mapJavaToKotlin$default.getDefaultType();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No fqName: ");
        sb.append(this.this$0.javaAnnotation);
        return ErrorUtils.createErrorType(sb.toString());
    }
}
