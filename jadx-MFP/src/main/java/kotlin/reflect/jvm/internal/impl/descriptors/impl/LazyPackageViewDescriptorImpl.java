package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.LazyScopeAdapter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyPackageViewDescriptorImpl.kt */
public final class LazyPackageViewDescriptorImpl extends DeclarationDescriptorImpl implements PackageViewDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyPackageViewDescriptorImpl.class), "fragments", "getFragments()Ljava/util/List;"))};
    @NotNull
    private final FqName fqName;
    @NotNull
    private final NotNullLazyValue fragments$delegate;
    @NotNull
    private final MemberScope memberScope;
    @NotNull
    private final ModuleDescriptorImpl module;

    @NotNull
    public List<PackageFragmentDescriptor> getFragments() {
        return (List) StorageKt.getValue(this.fragments$delegate, (Object) this, $$delegatedProperties[0]);
    }

    public boolean isEmpty() {
        return DefaultImpls.isEmpty(this);
    }

    @NotNull
    public ModuleDescriptorImpl getModule() {
        return this.module;
    }

    @NotNull
    public FqName getFqName() {
        return this.fqName;
    }

    public LazyPackageViewDescriptorImpl(@NotNull ModuleDescriptorImpl moduleDescriptorImpl, @NotNull FqName fqName2, @NotNull StorageManager storageManager) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptorImpl, "module");
        Intrinsics.checkParameterIsNotNull(fqName2, "fqName");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        super(Annotations.Companion.getEMPTY(), fqName2.shortNameOrSpecial());
        this.module = moduleDescriptorImpl;
        this.fqName = fqName2;
        this.fragments$delegate = storageManager.createLazyValue(new LazyPackageViewDescriptorImpl$fragments$2(this));
        this.memberScope = new LazyScopeAdapter(storageManager.createLazyValue(new LazyPackageViewDescriptorImpl$memberScope$1(this)));
    }

    @NotNull
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    @Nullable
    public PackageViewDescriptor getContainingDeclaration() {
        if (getFqName().isRoot()) {
            return null;
        }
        ModuleDescriptorImpl module2 = getModule();
        FqName parent = getFqName().parent();
        Intrinsics.checkExpressionValueIsNotNull(parent, "fqName.parent()");
        return module2.getPackage(parent);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof PackageViewDescriptor)) {
            obj = null;
        }
        PackageViewDescriptor packageViewDescriptor = (PackageViewDescriptor) obj;
        boolean z = false;
        if (packageViewDescriptor == null) {
            return false;
        }
        if (Intrinsics.areEqual((Object) getFqName(), (Object) packageViewDescriptor.getFqName()) && Intrinsics.areEqual((Object) getModule(), (Object) packageViewDescriptor.getModule())) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (getModule().hashCode() * 31) + getFqName().hashCode();
    }

    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptorVisitor, "visitor");
        return declarationDescriptorVisitor.visitPackageViewDescriptor(this, d);
    }
}
