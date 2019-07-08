package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TypeParameterDescriptorImpl extends AbstractTypeParameterDescriptor {
    private boolean initialized = false;
    @Nullable
    private final Function1<KotlinType, Void> reportCycleError;
    private final List<KotlinType> upperBounds = new ArrayList(1);

    @NotNull
    public static TypeParameterDescriptor createWithDefaultBound(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, boolean z, @NotNull Variance variance, @NotNull Name name, int i) {
        TypeParameterDescriptorImpl createForFurtherModification = createForFurtherModification(declarationDescriptor, annotations, z, variance, name, i, SourceElement.NO_SOURCE);
        createForFurtherModification.addUpperBound(DescriptorUtilsKt.getBuiltIns(declarationDescriptor).getDefaultBound());
        createForFurtherModification.setInitialized();
        return createForFurtherModification;
    }

    public static TypeParameterDescriptorImpl createForFurtherModification(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, boolean z, @NotNull Variance variance, @NotNull Name name, int i, @NotNull SourceElement sourceElement) {
        return createForFurtherModification(declarationDescriptor, annotations, z, variance, name, i, sourceElement, null, EMPTY.INSTANCE);
    }

    public static TypeParameterDescriptorImpl createForFurtherModification(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, boolean z, @NotNull Variance variance, @NotNull Name name, int i, @NotNull SourceElement sourceElement, @Nullable Function1<KotlinType, Void> function1, @NotNull SupertypeLoopChecker supertypeLoopChecker) {
        TypeParameterDescriptorImpl typeParameterDescriptorImpl = new TypeParameterDescriptorImpl(declarationDescriptor, annotations, z, variance, name, i, sourceElement, function1, supertypeLoopChecker);
        return typeParameterDescriptorImpl;
    }

    private TypeParameterDescriptorImpl(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, boolean z, @NotNull Variance variance, @NotNull Name name, int i, @NotNull SourceElement sourceElement, @Nullable Function1<KotlinType, Void> function1, @NotNull SupertypeLoopChecker supertypeLoopChecker) {
        super(LockBasedStorageManager.NO_LOCKS, declarationDescriptor, annotations, name, variance, z, i, sourceElement, supertypeLoopChecker);
        this.reportCycleError = function1;
    }

    private void checkInitialized() {
        if (!this.initialized) {
            StringBuilder sb = new StringBuilder();
            sb.append("Type parameter descriptor is not initialized: ");
            sb.append(nameForAssertions());
            throw new IllegalStateException(sb.toString());
        }
    }

    private void checkUninitialized() {
        if (this.initialized) {
            StringBuilder sb = new StringBuilder();
            sb.append("Type parameter descriptor is already initialized: ");
            sb.append(nameForAssertions());
            throw new IllegalStateException(sb.toString());
        }
    }

    private String nameForAssertions() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(" declared in ");
        sb.append(DescriptorUtils.getFqName(getContainingDeclaration()));
        return sb.toString();
    }

    public void setInitialized() {
        checkUninitialized();
        this.initialized = true;
    }

    public void addUpperBound(@NotNull KotlinType kotlinType) {
        checkUninitialized();
        doAddUpperBound(kotlinType);
    }

    private void doAddUpperBound(KotlinType kotlinType) {
        if (!KotlinTypeKt.isError(kotlinType)) {
            this.upperBounds.add(kotlinType);
        }
    }

    /* access modifiers changed from: protected */
    public void reportSupertypeLoopError(@NotNull KotlinType kotlinType) {
        Function1<KotlinType, Void> function1 = this.reportCycleError;
        if (function1 != null) {
            function1.invoke(kotlinType);
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<KotlinType> resolveUpperBounds() {
        checkInitialized();
        return this.upperBounds;
    }
}
