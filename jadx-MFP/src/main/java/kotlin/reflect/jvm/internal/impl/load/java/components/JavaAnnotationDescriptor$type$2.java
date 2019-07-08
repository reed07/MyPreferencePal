package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaAnnotationMapper.kt */
final class JavaAnnotationDescriptor$type$2 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ LazyJavaResolverContext $c;
    final /* synthetic */ JavaAnnotationDescriptor this$0;

    JavaAnnotationDescriptor$type$2(JavaAnnotationDescriptor javaAnnotationDescriptor, LazyJavaResolverContext lazyJavaResolverContext) {
        this.this$0 = javaAnnotationDescriptor;
        this.$c = lazyJavaResolverContext;
        super(0);
    }

    @NotNull
    public final SimpleType invoke() {
        ClassDescriptor builtInClassByFqName = this.$c.getModule().getBuiltIns().getBuiltInClassByFqName(this.this$0.getFqName());
        Intrinsics.checkExpressionValueIsNotNull(builtInClassByFqName, "c.module.builtIns.getBuiltInClassByFqName(fqName)");
        return builtInClassByFqName.getDefaultType();
    }
}
