package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: resolvers.kt */
final class LazyJavaTypeParameterResolver$resolve$1 extends Lambda implements Function1<JavaTypeParameter, LazyJavaTypeParameterDescriptor> {
    final /* synthetic */ LazyJavaTypeParameterResolver this$0;

    LazyJavaTypeParameterResolver$resolve$1(LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver) {
        this.this$0 = lazyJavaTypeParameterResolver;
        super(1);
    }

    @Nullable
    public final LazyJavaTypeParameterDescriptor invoke(@NotNull JavaTypeParameter javaTypeParameter) {
        Intrinsics.checkParameterIsNotNull(javaTypeParameter, "typeParameter");
        Integer num = (Integer) this.this$0.typeParameters.get(javaTypeParameter);
        if (num == null) {
            return null;
        }
        return new LazyJavaTypeParameterDescriptor(ContextKt.child(this.this$0.c, this.this$0), javaTypeParameter, this.this$0.typeParametersIndexOffset + num.intValue(), this.this$0.containingDeclaration);
    }
}
