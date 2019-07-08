package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.functions.Function0;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.InnerClassesScopeWrapper;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.SubstitutingScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractClassDescriptor implements ClassDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final NotNullLazyValue<SimpleType> defaultType;
    private final Name name;
    private final NotNullLazyValue<ReceiverParameterDescriptor> thisAsReceiverParameter;
    private final NotNullLazyValue<MemberScope> unsubstitutedInnerClassesScope;

    @NotNull
    public ClassDescriptor getOriginal() {
        return this;
    }

    public AbstractClassDescriptor(@NotNull StorageManager storageManager, @NotNull Name name2) {
        this.name = name2;
        this.defaultType = storageManager.createLazyValue(new Function0<SimpleType>() {
            public SimpleType invoke() {
                AbstractClassDescriptor abstractClassDescriptor = AbstractClassDescriptor.this;
                return TypeUtils.makeUnsubstitutedType(abstractClassDescriptor, abstractClassDescriptor.getUnsubstitutedMemberScope());
            }
        });
        this.unsubstitutedInnerClassesScope = storageManager.createLazyValue(new Function0<MemberScope>() {
            public MemberScope invoke() {
                return new InnerClassesScopeWrapper(AbstractClassDescriptor.this.getUnsubstitutedMemberScope());
            }
        });
        this.thisAsReceiverParameter = storageManager.createLazyValue(new Function0<ReceiverParameterDescriptor>() {
            public ReceiverParameterDescriptor invoke() {
                return new LazyClassReceiverParameterDescriptor(AbstractClassDescriptor.this);
            }
        });
    }

    @NotNull
    public Name getName() {
        return this.name;
    }

    @NotNull
    public MemberScope getUnsubstitutedInnerClassesScope() {
        return (MemberScope) this.unsubstitutedInnerClassesScope.invoke();
    }

    @NotNull
    public ReceiverParameterDescriptor getThisAsReceiverParameter() {
        return (ReceiverParameterDescriptor) this.thisAsReceiverParameter.invoke();
    }

    @NotNull
    public MemberScope getMemberScope(@NotNull TypeSubstitution typeSubstitution) {
        if (typeSubstitution.isEmpty()) {
            return getUnsubstitutedMemberScope();
        }
        return new SubstitutingScope(getUnsubstitutedMemberScope(), TypeSubstitutor.create(typeSubstitution));
    }

    @NotNull
    public ClassDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        return new LazySubstitutingClassDescriptor(this, typeSubstitutor);
    }

    @NotNull
    public SimpleType getDefaultType() {
        return (SimpleType) this.defaultType.invoke();
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitClassDescriptor(this, d);
    }
}
