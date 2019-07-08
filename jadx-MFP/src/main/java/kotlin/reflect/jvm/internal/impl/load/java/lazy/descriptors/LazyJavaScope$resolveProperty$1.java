package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaScope.kt */
final class LazyJavaScope$resolveProperty$1 extends Lambda implements Function0<ConstantValue<?>> {
    final /* synthetic */ JavaField $field;
    final /* synthetic */ PropertyDescriptorImpl $propertyDescriptor;
    final /* synthetic */ LazyJavaScope this$0;

    LazyJavaScope$resolveProperty$1(LazyJavaScope lazyJavaScope, JavaField javaField, PropertyDescriptorImpl propertyDescriptorImpl) {
        this.this$0 = lazyJavaScope;
        this.$field = javaField;
        this.$propertyDescriptor = propertyDescriptorImpl;
        super(0);
    }

    @Nullable
    public final ConstantValue<?> invoke() {
        return this.this$0.getC().getComponents().getJavaPropertyInitializerEvaluator().getInitializerConstant(this.$field, this.$propertyDescriptor);
    }
}
