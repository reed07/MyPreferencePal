package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collections;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ReceiverParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DescriptorFactory {

    private static class DefaultClassConstructorDescriptor extends ClassConstructorDescriptorImpl {
        public DefaultClassConstructorDescriptor(@NotNull ClassDescriptor classDescriptor, @NotNull SourceElement sourceElement) {
            super(classDescriptor, null, Annotations.Companion.getEMPTY(), true, Kind.DECLARATION, sourceElement);
            initialize(Collections.emptyList(), DescriptorUtils.getDefaultConstructorVisibility(classDescriptor));
        }
    }

    @NotNull
    public static PropertySetterDescriptorImpl createDefaultSetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Annotations annotations2) {
        return createSetter(propertyDescriptor, annotations, annotations2, true, false, false, propertyDescriptor.getSource());
    }

    @NotNull
    public static PropertySetterDescriptorImpl createSetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Annotations annotations2, boolean z, boolean z2, boolean z3, @NotNull SourceElement sourceElement) {
        return createSetter(propertyDescriptor, annotations, annotations2, z, z2, z3, propertyDescriptor.getVisibility(), sourceElement);
    }

    @NotNull
    public static PropertySetterDescriptorImpl createSetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, @NotNull Annotations annotations2, boolean z, boolean z2, boolean z3, @NotNull Visibility visibility, @NotNull SourceElement sourceElement) {
        PropertySetterDescriptorImpl propertySetterDescriptorImpl = new PropertySetterDescriptorImpl(propertyDescriptor, annotations, propertyDescriptor.getModality(), visibility, z, z2, z3, Kind.DECLARATION, null, sourceElement);
        Annotations annotations3 = annotations2;
        propertySetterDescriptorImpl.initialize(PropertySetterDescriptorImpl.createSetterParameter(propertySetterDescriptorImpl, propertyDescriptor.getType(), annotations2));
        return propertySetterDescriptorImpl;
    }

    @NotNull
    public static PropertyGetterDescriptorImpl createDefaultGetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations) {
        return createGetter(propertyDescriptor, annotations, true, false, false);
    }

    @NotNull
    public static PropertyGetterDescriptorImpl createGetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, boolean z, boolean z2, boolean z3) {
        return createGetter(propertyDescriptor, annotations, z, z2, z3, propertyDescriptor.getSource());
    }

    @NotNull
    public static PropertyGetterDescriptorImpl createGetter(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Annotations annotations, boolean z, boolean z2, boolean z3, @NotNull SourceElement sourceElement) {
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl = new PropertyGetterDescriptorImpl(propertyDescriptor, annotations, propertyDescriptor.getModality(), propertyDescriptor.getVisibility(), z, z2, z3, Kind.DECLARATION, null, sourceElement);
        return propertyGetterDescriptorImpl;
    }

    @NotNull
    public static ClassConstructorDescriptorImpl createPrimaryConstructorForObject(@NotNull ClassDescriptor classDescriptor, @NotNull SourceElement sourceElement) {
        return new DefaultClassConstructorDescriptor(classDescriptor, sourceElement);
    }

    @NotNull
    public static SimpleFunctionDescriptor createEnumValuesMethod(@NotNull ClassDescriptor classDescriptor) {
        return SimpleFunctionDescriptorImpl.create(classDescriptor, Annotations.Companion.getEMPTY(), DescriptorUtils.ENUM_VALUES, Kind.SYNTHESIZED, classDescriptor.getSource()).initialize((ReceiverParameterDescriptor) null, (ReceiverParameterDescriptor) null, Collections.emptyList(), Collections.emptyList(), (KotlinType) DescriptorUtilsKt.getBuiltIns(classDescriptor).getArrayType(Variance.INVARIANT, classDescriptor.getDefaultType()), Modality.FINAL, Visibilities.PUBLIC);
    }

    @NotNull
    public static SimpleFunctionDescriptor createEnumValueOfMethod(@NotNull ClassDescriptor classDescriptor) {
        SimpleFunctionDescriptorImpl create = SimpleFunctionDescriptorImpl.create(classDescriptor, Annotations.Companion.getEMPTY(), DescriptorUtils.ENUM_VALUE_OF, Kind.SYNTHESIZED, classDescriptor.getSource());
        ValueParameterDescriptorImpl valueParameterDescriptorImpl = new ValueParameterDescriptorImpl(create, null, 0, Annotations.Companion.getEMPTY(), Name.identifier("value"), DescriptorUtilsKt.getBuiltIns(classDescriptor).getStringType(), false, false, false, null, classDescriptor.getSource());
        return create.initialize((ReceiverParameterDescriptor) null, (ReceiverParameterDescriptor) null, Collections.emptyList(), Collections.singletonList(valueParameterDescriptorImpl), (KotlinType) classDescriptor.getDefaultType(), Modality.FINAL, Visibilities.PUBLIC);
    }

    public static boolean isEnumValuesMethod(@NotNull FunctionDescriptor functionDescriptor) {
        return functionDescriptor.getName().equals(DescriptorUtils.ENUM_VALUES) && isEnumSpecialMethod(functionDescriptor);
    }

    public static boolean isEnumValueOfMethod(@NotNull FunctionDescriptor functionDescriptor) {
        return functionDescriptor.getName().equals(DescriptorUtils.ENUM_VALUE_OF) && isEnumSpecialMethod(functionDescriptor);
    }

    private static boolean isEnumSpecialMethod(@NotNull FunctionDescriptor functionDescriptor) {
        return functionDescriptor.getKind() == Kind.SYNTHESIZED && DescriptorUtils.isEnumClass(functionDescriptor.getContainingDeclaration());
    }

    @Nullable
    public static ReceiverParameterDescriptor createExtensionReceiverParameterForCallable(@NotNull CallableDescriptor callableDescriptor, @Nullable KotlinType kotlinType, @NotNull Annotations annotations) {
        if (kotlinType == null) {
            return null;
        }
        return new ReceiverParameterDescriptorImpl(callableDescriptor, new ExtensionReceiver(callableDescriptor, kotlinType, null), annotations);
    }
}
