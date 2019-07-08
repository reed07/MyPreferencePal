package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryPackageSourceElement;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaPackageFragment.kt */
public final class LazyJavaPackageFragment extends PackageFragmentDescriptorImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "binaryClasses", "getBinaryClasses$descriptors_jvm()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "partToFacade", "getPartToFacade()Ljava/util/HashMap;"))};
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final NotNullLazyValue binaryClasses$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaPackageFragment$binaryClasses$2(this));
    /* access modifiers changed from: private */
    public final LazyJavaResolverContext c;
    /* access modifiers changed from: private */
    public final JavaPackage jPackage;
    private final NotNullLazyValue partToFacade$delegate;
    private final JvmPackageScope scope = new JvmPackageScope(this.c, this.jPackage, this);
    private final NotNullLazyValue<List<FqName>> subPackages = this.c.getStorageManager().createRecursionTolerantLazyValue(new LazyJavaPackageFragment$subPackages$1(this), CollectionsKt.emptyList());

    @NotNull
    public final Map<String, KotlinJvmBinaryClass> getBinaryClasses$descriptors_jvm() {
        return (Map) StorageKt.getValue(this.binaryClasses$delegate, (Object) this, $$delegatedProperties[0]);
    }

    public LazyJavaPackageFragment(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull JavaPackage javaPackage) {
        Annotations annotations2;
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "outerContext");
        Intrinsics.checkParameterIsNotNull(javaPackage, "jPackage");
        super(lazyJavaResolverContext.getModule(), javaPackage.getFqName());
        this.jPackage = javaPackage;
        this.c = ContextKt.childForClassOrPackage$default(lazyJavaResolverContext, this, null, 0, 6, null);
        if (this.c.getComponents().getAnnotationTypeQualifierResolver().getDisabled()) {
            annotations2 = Annotations.Companion.getEMPTY();
        } else {
            annotations2 = LazyJavaAnnotationsKt.resolveAnnotations(this.c, this.jPackage);
        }
        this.annotations = annotations2;
        this.partToFacade$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaPackageFragment$partToFacade$2(this));
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public final List<FqName> getSubPackageFqNames$descriptors_jvm() {
        return (List) this.subPackages.invoke();
    }

    @Nullable
    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(@NotNull JavaClass javaClass) {
        Intrinsics.checkParameterIsNotNull(javaClass, "jClass");
        return this.scope.getJavaScope$descriptors_jvm().findClassifierByJavaClass$descriptors_jvm(javaClass);
    }

    @NotNull
    public JvmPackageScope getMemberScope() {
        return this.scope;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lazy Java package fragment: ");
        sb.append(getFqName());
        return sb.toString();
    }

    @NotNull
    public SourceElement getSource() {
        return new KotlinJvmBinaryPackageSourceElement(this);
    }
}
