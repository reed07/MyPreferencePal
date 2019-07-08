package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstUtil;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaPropertyDescriptor extends PropertyDescriptorImpl implements JavaCallableMemberDescriptor {
    private final boolean isStaticFinal;
    @Nullable
    private final Pair<UserDataKey<?>, ?> singleUserData;

    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    private JavaPropertyDescriptor(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Modality modality, @NotNull Visibility visibility, boolean z, @NotNull Name name, @NotNull SourceElement sourceElement, @Nullable PropertyDescriptor propertyDescriptor, @NotNull Kind kind, boolean z2, @Nullable Pair<UserDataKey<?>, ?> pair) {
        super(declarationDescriptor, propertyDescriptor, annotations, modality, visibility, z, name, kind, sourceElement, false, false, false, false, false, false);
        this.isStaticFinal = z2;
        this.singleUserData = pair;
    }

    @NotNull
    public static JavaPropertyDescriptor create(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Modality modality, @NotNull Visibility visibility, boolean z, @NotNull Name name, @NotNull SourceElement sourceElement, boolean z2) {
        JavaPropertyDescriptor javaPropertyDescriptor = new JavaPropertyDescriptor(declarationDescriptor, annotations, modality, visibility, z, name, sourceElement, null, Kind.DECLARATION, z2, null);
        return javaPropertyDescriptor;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public PropertyDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Modality modality, @NotNull Visibility visibility, @Nullable PropertyDescriptor propertyDescriptor, @NotNull Kind kind, @NotNull Name name) {
        JavaPropertyDescriptor javaPropertyDescriptor = new JavaPropertyDescriptor(declarationDescriptor, getAnnotations(), modality, visibility, isVar(), name, SourceElement.NO_SOURCE, propertyDescriptor, kind, this.isStaticFinal, this.singleUserData);
        return javaPropertyDescriptor;
    }

    @NotNull
    public JavaCallableMemberDescriptor enhance(@Nullable KotlinType kotlinType, @NotNull List<ValueParameterData> list, @NotNull KotlinType kotlinType2, @Nullable Pair<UserDataKey<?>, ?> pair) {
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        PropertySetterDescriptorImpl propertySetterDescriptorImpl;
        KotlinType kotlinType3 = kotlinType;
        KotlinType kotlinType4 = kotlinType2;
        ReceiverParameterDescriptor receiverParameterDescriptor = null;
        JavaPropertyDescriptor javaPropertyDescriptor = new JavaPropertyDescriptor(getContainingDeclaration(), getAnnotations(), getModality(), getVisibility(), isVar(), getName(), getSource(), getOriginal() == this ? null : getOriginal(), getKind(), this.isStaticFinal, pair);
        PropertyGetterDescriptorImpl getter = getGetter();
        if (getter != null) {
            propertyGetterDescriptorImpl = r3;
            PropertyGetterDescriptorImpl propertyGetterDescriptorImpl2 = new PropertyGetterDescriptorImpl(javaPropertyDescriptor, getter.getAnnotations(), getter.getModality(), getter.getVisibility(), getter.isDefault(), getter.isExternal(), getter.isInline(), getKind(), getter, getter.getSource());
            propertyGetterDescriptorImpl.setInitialSignatureDescriptor(getter.getInitialSignatureDescriptor());
            propertyGetterDescriptorImpl.initialize(kotlinType4);
        } else {
            propertyGetterDescriptorImpl = null;
        }
        PropertySetterDescriptor setter = getSetter();
        if (setter != null) {
            PropertySetterDescriptorImpl propertySetterDescriptorImpl2 = r3;
            PropertySetterDescriptorImpl propertySetterDescriptorImpl3 = new PropertySetterDescriptorImpl(javaPropertyDescriptor, setter.getAnnotations(), setter.getModality(), setter.getVisibility(), setter.isDefault(), setter.isExternal(), setter.isInline(), getKind(), setter, setter.getSource());
            propertySetterDescriptorImpl = propertySetterDescriptorImpl2;
            propertySetterDescriptorImpl.setInitialSignatureDescriptor(propertySetterDescriptorImpl2.getInitialSignatureDescriptor());
            propertySetterDescriptorImpl.initialize((ValueParameterDescriptor) setter.getValueParameters().get(0));
        } else {
            propertySetterDescriptorImpl = null;
        }
        javaPropertyDescriptor.initialize(propertyGetterDescriptorImpl, propertySetterDescriptorImpl, getBackingField(), getDelegateField());
        javaPropertyDescriptor.setSetterProjectedOut(isSetterProjectedOut());
        if (this.compileTimeInitializer != null) {
            javaPropertyDescriptor.setCompileTimeInitializer(this.compileTimeInitializer);
        }
        javaPropertyDescriptor.setOverriddenDescriptors(getOverriddenDescriptors());
        KotlinType kotlinType5 = kotlinType;
        if (kotlinType5 != null) {
            receiverParameterDescriptor = DescriptorFactory.createExtensionReceiverParameterForCallable(this, kotlinType5, Annotations.Companion.getEMPTY());
        }
        javaPropertyDescriptor.setType(kotlinType4, getTypeParameters(), getDispatchReceiverParameter(), receiverParameterDescriptor);
        return javaPropertyDescriptor;
    }

    public boolean isConst() {
        KotlinType type = getType();
        return this.isStaticFinal && ConstUtil.canBeUsedForConstVal(type) && (!TypeEnhancementKt.hasEnhancedNullability(type) || KotlinBuiltIns.isString(type));
    }

    @Nullable
    public <V> V getUserData(UserDataKey<V> userDataKey) {
        Pair<UserDataKey<?>, ?> pair = this.singleUserData;
        if (pair == null || !((UserDataKey) pair.getFirst()).equals(userDataKey)) {
            return null;
        }
        return this.singleUserData.getSecond();
    }
}
