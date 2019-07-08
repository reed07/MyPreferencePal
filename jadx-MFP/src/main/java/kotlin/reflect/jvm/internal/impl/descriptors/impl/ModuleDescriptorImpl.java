package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin._Assertions;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.InvalidModuleException;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.Capability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.MultiTargetPlatform;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ModuleDescriptorImpl.kt */
public final class ModuleDescriptorImpl extends DeclarationDescriptorImpl implements ModuleDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ModuleDescriptorImpl.class), "packageFragmentProviderForWholeModuleWithDependencies", "getPackageFragmentProviderForWholeModuleWithDependencies()Lorg/jetbrains/kotlin/descriptors/impl/CompositePackageFragmentProvider;"))};
    @NotNull
    private final KotlinBuiltIns builtIns;
    private final Map<Capability<? extends Object>, Object> capabilities;
    /* access modifiers changed from: private */
    public ModuleDependencies dependencies;
    private boolean isValid;
    /* access modifiers changed from: private */
    public PackageFragmentProvider packageFragmentProviderForModuleContent;
    private final Lazy packageFragmentProviderForWholeModuleWithDependencies$delegate;
    private final MemoizedFunctionToNotNull<FqName, PackageViewDescriptor> packages;
    @Nullable
    private final Name stableName;
    /* access modifiers changed from: private */
    public final StorageManager storageManager;

    @JvmOverloads
    public ModuleDescriptorImpl(@NotNull Name name, @NotNull StorageManager storageManager2, @NotNull KotlinBuiltIns kotlinBuiltIns, @Nullable MultiTargetPlatform multiTargetPlatform) {
        this(name, storageManager2, kotlinBuiltIns, multiTargetPlatform, null, null, 48, null);
    }

    private final CompositePackageFragmentProvider getPackageFragmentProviderForWholeModuleWithDependencies() {
        Lazy lazy = this.packageFragmentProviderForWholeModuleWithDependencies$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (CompositePackageFragmentProvider) lazy.getValue();
    }

    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptorVisitor, "visitor");
        return DefaultImpls.accept(this, declarationDescriptorVisitor, d);
    }

    @Nullable
    public DeclarationDescriptor getContainingDeclaration() {
        return DefaultImpls.getContainingDeclaration(this);
    }

    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    @JvmOverloads
    public /* synthetic */ ModuleDescriptorImpl(Name name, StorageManager storageManager2, KotlinBuiltIns kotlinBuiltIns, MultiTargetPlatform multiTargetPlatform, Map map, Name name2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, storageManager2, kotlinBuiltIns, (i & 8) != 0 ? null : multiTargetPlatform, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? null : name2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0035, code lost:
        if (r2 != null) goto L_0x003c;
     */
    @kotlin.jvm.JvmOverloads
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ModuleDescriptorImpl(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.Name r2, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.storage.StorageManager r3, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r4, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.resolve.MultiTargetPlatform r5, @org.jetbrains.annotations.NotNull java.util.Map<kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.Capability<?>, ? extends java.lang.Object> r6, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.name.Name r7) {
        /*
            r1 = this;
            java.lang.String r0 = "moduleName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "storageManager"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "builtIns"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "capabilities"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r0 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r0.getEMPTY()
            r1.<init>(r0, r2)
            r1.storageManager = r3
            r1.builtIns = r4
            r1.stableName = r7
            boolean r3 = r2.isSpecial()
            if (r3 == 0) goto L_0x0062
            if (r5 == 0) goto L_0x0038
            kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor$Capability<kotlin.reflect.jvm.internal.impl.resolve.MultiTargetPlatform> r2 = kotlin.reflect.jvm.internal.impl.resolve.MultiTargetPlatform.CAPABILITY
            kotlin.Pair r2 = kotlin.TuplesKt.to(r2, r5)
            java.util.Map r2 = kotlin.collections.MapsKt.mapOf(r2)
            if (r2 == 0) goto L_0x0038
            goto L_0x003c
        L_0x0038:
            java.util.Map r2 = kotlin.collections.MapsKt.emptyMap()
        L_0x003c:
            java.util.Map r2 = kotlin.collections.MapsKt.plus(r6, r2)
            r1.capabilities = r2
            r2 = 1
            r1.isValid = r2
            kotlin.reflect.jvm.internal.impl.storage.StorageManager r2 = r1.storageManager
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl$packages$1 r3 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl$packages$1
            r3.<init>(r1)
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull r2 = r2.createMemoizedFunction(r3)
            r1.packages = r2
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2 r2 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2
            r2.<init>(r1)
            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
            kotlin.Lazy r2 = kotlin.LazyKt.lazy(r2)
            r1.packageFragmentProviderForWholeModuleWithDependencies$delegate = r2
            return
        L_0x0062:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Module name must be special: "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r3.<init>(r2)
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl.<init>(kotlin.reflect.jvm.internal.impl.name.Name, kotlin.reflect.jvm.internal.impl.storage.StorageManager, kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns, kotlin.reflect.jvm.internal.impl.resolve.MultiTargetPlatform, java.util.Map, kotlin.reflect.jvm.internal.impl.name.Name):void");
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void assertValid() {
        if (!isValid()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Accessing invalid module descriptor ");
            sb.append(this);
            throw new InvalidModuleException(sb.toString());
        }
    }

    @NotNull
    public List<ModuleDescriptor> getExpectedByModules() {
        ModuleDependencies moduleDependencies = this.dependencies;
        if (moduleDependencies != null) {
            return moduleDependencies.getExpectedByDependencies();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Dependencies of module ");
        sb.append(getId());
        sb.append(" were not set");
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public PackageViewDescriptor getPackage(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        assertValid();
        return (PackageViewDescriptor) this.packages.invoke(fqName);
    }

    @NotNull
    public Collection<FqName> getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        assertValid();
        return getPackageFragmentProvider().getSubPackagesOf(fqName, function1);
    }

    /* access modifiers changed from: private */
    public final boolean isInitialized() {
        return this.packageFragmentProviderForModuleContent != null;
    }

    public final void setDependencies(@NotNull ModuleDependencies moduleDependencies) {
        Intrinsics.checkParameterIsNotNull(moduleDependencies, "dependencies");
        boolean z = this.dependencies == null;
        if (!_Assertions.ENABLED || z) {
            this.dependencies = moduleDependencies;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Dependencies of ");
        sb.append(getId());
        sb.append(" were already set");
        throw new AssertionError(sb.toString());
    }

    public final void setDependencies(@NotNull ModuleDescriptorImpl... moduleDescriptorImplArr) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptorImplArr, "descriptors");
        setDependencies(ArraysKt.toList((T[]) moduleDescriptorImplArr));
    }

    public final void setDependencies(@NotNull List<ModuleDescriptorImpl> list) {
        Intrinsics.checkParameterIsNotNull(list, "descriptors");
        setDependencies(list, SetsKt.emptySet());
    }

    public final void setDependencies(@NotNull List<ModuleDescriptorImpl> list, @NotNull Set<ModuleDescriptorImpl> set) {
        Intrinsics.checkParameterIsNotNull(list, "descriptors");
        Intrinsics.checkParameterIsNotNull(set, "friends");
        setDependencies((ModuleDependencies) new ModuleDependenciesImpl(list, set, CollectionsKt.emptyList()));
    }

    public boolean shouldSeeInternalsOf(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "targetModule");
        if (!Intrinsics.areEqual((Object) this, (Object) moduleDescriptor)) {
            ModuleDependencies moduleDependencies = this.dependencies;
            if (moduleDependencies == null) {
                Intrinsics.throwNpe();
            }
            if (!CollectionsKt.contains(moduleDependencies.getModulesWhoseInternalsAreVisible(), moduleDescriptor) && !getExpectedByModules().contains(moduleDescriptor)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final String getId() {
        String name = getName().toString();
        Intrinsics.checkExpressionValueIsNotNull(name, "name.toString()");
        return name;
    }

    public final void initialize(@NotNull PackageFragmentProvider packageFragmentProvider) {
        Intrinsics.checkParameterIsNotNull(packageFragmentProvider, "providerForModuleContent");
        boolean z = !isInitialized();
        if (!_Assertions.ENABLED || z) {
            this.packageFragmentProviderForModuleContent = packageFragmentProvider;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt to initialize module ");
        sb.append(getId());
        sb.append(" twice");
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public final PackageFragmentProvider getPackageFragmentProvider() {
        assertValid();
        return getPackageFragmentProviderForWholeModuleWithDependencies();
    }
}
