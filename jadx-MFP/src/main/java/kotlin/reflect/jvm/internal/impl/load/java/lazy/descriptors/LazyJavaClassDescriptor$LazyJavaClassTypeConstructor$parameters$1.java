package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaClassDescriptor.kt */
final class LazyJavaClassDescriptor$LazyJavaClassTypeConstructor$parameters$1 extends Lambda implements Function0<List<? extends TypeParameterDescriptor>> {
    final /* synthetic */ LazyJavaClassTypeConstructor this$0;

    LazyJavaClassDescriptor$LazyJavaClassTypeConstructor$parameters$1(LazyJavaClassTypeConstructor lazyJavaClassTypeConstructor) {
        this.this$0 = lazyJavaClassTypeConstructor;
        super(0);
    }

    @NotNull
    public final List<TypeParameterDescriptor> invoke() {
        return TypeParameterUtilsKt.computeConstructorTypeParameters(LazyJavaClassDescriptor.this);
    }
}
