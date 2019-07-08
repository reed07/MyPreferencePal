package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaAnnotationMapper.kt */
public final class JavaRetentionAnnotationDescriptor extends JavaAnnotationDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JavaRetentionAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    @NotNull
    private final NotNullLazyValue allValueArguments$delegate;

    @NotNull
    public Map<Name, ConstantValue<?>> getAllValueArguments() {
        return (Map) StorageKt.getValue(this.allValueArguments$delegate, (Object) this, $$delegatedProperties[0]);
    }

    public JavaRetentionAnnotationDescriptor(@NotNull JavaAnnotation javaAnnotation, @NotNull LazyJavaResolverContext lazyJavaResolverContext) {
        Intrinsics.checkParameterIsNotNull(javaAnnotation, "annotation");
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        FqName fqName = KotlinBuiltIns.FQ_NAMES.retention;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.retention");
        super(lazyJavaResolverContext, javaAnnotation, fqName);
        this.allValueArguments$delegate = lazyJavaResolverContext.getStorageManager().createLazyValue(new JavaRetentionAnnotationDescriptor$allValueArguments$2(this));
    }
}
