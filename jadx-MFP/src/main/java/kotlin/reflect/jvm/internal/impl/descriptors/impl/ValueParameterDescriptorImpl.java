package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ValueParameterDescriptorImpl.kt */
public class ValueParameterDescriptorImpl extends VariableDescriptorImpl implements ValueParameterDescriptor {
    public static final Companion Companion = new Companion(null);
    private final boolean declaresDefaultValue;
    private final int index;
    private final boolean isCrossinline;
    private final boolean isNoinline;
    private final ValueParameterDescriptor original;
    @Nullable
    private final KotlinType varargElementType;

    /* compiled from: ValueParameterDescriptorImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Nullable
    public Void getCompileTimeInitializer() {
        return null;
    }

    public boolean isVar() {
        return false;
    }

    public boolean isLateInit() {
        return DefaultImpls.isLateInit(this);
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isCrossinline() {
        return this.isCrossinline;
    }

    public boolean isNoinline() {
        return this.isNoinline;
    }

    @Nullable
    public KotlinType getVarargElementType() {
        return this.varargElementType;
    }

    public ValueParameterDescriptorImpl(@NotNull CallableDescriptor callableDescriptor, @Nullable ValueParameterDescriptor valueParameterDescriptor, int i, @NotNull Annotations annotations, @NotNull Name name, @NotNull KotlinType kotlinType, boolean z, boolean z2, boolean z3, @Nullable KotlinType kotlinType2, @NotNull SourceElement sourceElement) {
        CallableDescriptor callableDescriptor2 = callableDescriptor;
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "containingDeclaration");
        Annotations annotations2 = annotations;
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Name name2 = name;
        Intrinsics.checkParameterIsNotNull(name, "name");
        KotlinType kotlinType3 = kotlinType;
        Intrinsics.checkParameterIsNotNull(kotlinType, "outType");
        SourceElement sourceElement2 = sourceElement;
        Intrinsics.checkParameterIsNotNull(sourceElement2, "source");
        super(callableDescriptor2, annotations2, name2, kotlinType3, sourceElement2);
        this.index = i;
        this.declaresDefaultValue = z;
        this.isCrossinline = z2;
        this.isNoinline = z3;
        this.varargElementType = kotlinType2;
        this.original = valueParameterDescriptor != null ? valueParameterDescriptor : this;
    }

    @NotNull
    public CallableDescriptor getContainingDeclaration() {
        DeclarationDescriptor containingDeclaration = super.getContainingDeclaration();
        if (containingDeclaration != null) {
            return (CallableDescriptor) containingDeclaration;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
    }

    public boolean declaresDefaultValue() {
        if (this.declaresDefaultValue) {
            CallableDescriptor containingDeclaration = getContainingDeclaration();
            if (containingDeclaration != null) {
                Kind kind = ((CallableMemberDescriptor) containingDeclaration).getKind();
                Intrinsics.checkExpressionValueIsNotNull(kind, "(containingDeclaration a…bleMemberDescriptor).kind");
                if (kind.isReal()) {
                    return true;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableMemberDescriptor");
            }
        }
        return false;
    }

    @NotNull
    public ValueParameterDescriptor getOriginal() {
        ValueParameterDescriptor valueParameterDescriptor = this.original;
        return valueParameterDescriptor == this ? this : valueParameterDescriptor.getOriginal();
    }

    @NotNull
    public ValueParameterDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        Intrinsics.checkParameterIsNotNull(typeSubstitutor, "substitutor");
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        throw new UnsupportedOperationException();
    }

    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptorVisitor, "visitor");
        return declarationDescriptorVisitor.visitValueParameterDescriptor(this, d);
    }

    @NotNull
    public ValueParameterDescriptor copy(@NotNull CallableDescriptor callableDescriptor, @NotNull Name name, int i) {
        CallableDescriptor callableDescriptor2 = callableDescriptor;
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "newOwner");
        Name name2 = name;
        Intrinsics.checkParameterIsNotNull(name, "newName");
        Annotations annotations = getAnnotations();
        Intrinsics.checkExpressionValueIsNotNull(annotations, "annotations");
        KotlinType type = getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "type");
        boolean declaresDefaultValue2 = declaresDefaultValue();
        boolean isCrossinline2 = isCrossinline();
        boolean isNoinline2 = isNoinline();
        KotlinType varargElementType2 = getVarargElementType();
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        ValueParameterDescriptorImpl valueParameterDescriptorImpl = new ValueParameterDescriptorImpl(callableDescriptor2, null, i, annotations, name2, type, declaresDefaultValue2, isCrossinline2, isNoinline2, varargElementType2, sourceElement);
        return valueParameterDescriptorImpl;
    }

    @NotNull
    public Visibility getVisibility() {
        Visibility visibility = Visibilities.LOCAL;
        Intrinsics.checkExpressionValueIsNotNull(visibility, "Visibilities.LOCAL");
        return visibility;
    }

    @NotNull
    public Collection<ValueParameterDescriptor> getOverriddenDescriptors() {
        Collection overriddenDescriptors = getContainingDeclaration().getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "containingDeclaration.overriddenDescriptors");
        Iterable<CallableDescriptor> iterable = overriddenDescriptors;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (CallableDescriptor callableDescriptor : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(callableDescriptor, "it");
            arrayList.add((ValueParameterDescriptor) callableDescriptor.getValueParameters().get(getIndex()));
        }
        return (List) arrayList;
    }
}
