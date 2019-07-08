package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: context.kt */
public final class LazyJavaResolverContext {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaResolverContext.class), "defaultTypeQualifiers", "getDefaultTypeQualifiers()Lorg/jetbrains/kotlin/load/java/lazy/JavaTypeQualifiersByElementType;"))};
    @NotNull
    private final JavaResolverComponents components;
    @Nullable
    private final Lazy defaultTypeQualifiers$delegate = this.delegateForDefaultTypeQualifiers;
    @NotNull
    private final Lazy<JavaTypeQualifiersByElementType> delegateForDefaultTypeQualifiers;
    @NotNull
    private final TypeParameterResolver typeParameterResolver;
    @NotNull
    private final JavaTypeResolver typeResolver = new JavaTypeResolver(this, this.typeParameterResolver);

    @Nullable
    public final JavaTypeQualifiersByElementType getDefaultTypeQualifiers() {
        Lazy lazy = this.defaultTypeQualifiers$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (JavaTypeQualifiersByElementType) lazy.getValue();
    }

    public LazyJavaResolverContext(@NotNull JavaResolverComponents javaResolverComponents, @NotNull TypeParameterResolver typeParameterResolver2, @NotNull Lazy<JavaTypeQualifiersByElementType> lazy) {
        Intrinsics.checkParameterIsNotNull(javaResolverComponents, "components");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver2, "typeParameterResolver");
        Intrinsics.checkParameterIsNotNull(lazy, "delegateForDefaultTypeQualifiers");
        this.components = javaResolverComponents;
        this.typeParameterResolver = typeParameterResolver2;
        this.delegateForDefaultTypeQualifiers = lazy;
    }

    @NotNull
    public final JavaResolverComponents getComponents() {
        return this.components;
    }

    @NotNull
    public final TypeParameterResolver getTypeParameterResolver() {
        return this.typeParameterResolver;
    }

    @NotNull
    public final Lazy<JavaTypeQualifiersByElementType> getDelegateForDefaultTypeQualifiers$descriptors_jvm() {
        return this.delegateForDefaultTypeQualifiers;
    }

    @NotNull
    public final JavaTypeResolver getTypeResolver() {
        return this.typeResolver;
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.components.getStorageManager();
    }

    @NotNull
    public final ModuleDescriptor getModule() {
        return this.components.getModule();
    }
}
