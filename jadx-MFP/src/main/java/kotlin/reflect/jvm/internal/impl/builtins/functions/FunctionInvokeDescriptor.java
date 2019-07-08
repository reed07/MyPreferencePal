package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl.CopyConfiguration;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.util.OperatorNameConventions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FunctionInvokeDescriptor.kt */
public final class FunctionInvokeDescriptor extends SimpleFunctionDescriptorImpl {
    public static final Factory Factory = new Factory(null);

    /* compiled from: FunctionInvokeDescriptor.kt */
    public static final class Factory {
        private Factory() {
        }

        public /* synthetic */ Factory(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FunctionInvokeDescriptor create(@NotNull FunctionClassDescriptor functionClassDescriptor, boolean z) {
            Intrinsics.checkParameterIsNotNull(functionClassDescriptor, "functionClass");
            List declaredTypeParameters = functionClassDescriptor.getDeclaredTypeParameters();
            FunctionInvokeDescriptor functionInvokeDescriptor = new FunctionInvokeDescriptor(functionClassDescriptor, null, Kind.DECLARATION, z, null);
            ReceiverParameterDescriptor thisAsReceiverParameter = functionClassDescriptor.getThisAsReceiverParameter();
            List emptyList = CollectionsKt.emptyList();
            Iterable iterable = declaredTypeParameters;
            ArrayList arrayList = new ArrayList();
            for (Object next : iterable) {
                if (!(((TypeParameterDescriptor) next).getVariance() == Variance.IN_VARIANCE)) {
                    break;
                }
                arrayList.add(next);
            }
            Iterable<IndexedValue> withIndex = CollectionsKt.withIndex(arrayList);
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(withIndex, 10));
            for (IndexedValue indexedValue : withIndex) {
                arrayList2.add(FunctionInvokeDescriptor.Factory.createValueParameter(functionInvokeDescriptor, indexedValue.getIndex(), (TypeParameterDescriptor) indexedValue.getValue()));
            }
            functionInvokeDescriptor.initialize((ReceiverParameterDescriptor) null, thisAsReceiverParameter, emptyList, (List) arrayList2, (KotlinType) ((TypeParameterDescriptor) CollectionsKt.last(declaredTypeParameters)).getDefaultType(), Modality.ABSTRACT, Visibilities.PUBLIC);
            functionInvokeDescriptor.setHasSynthesizedParameterNames(true);
            return functionInvokeDescriptor;
        }

        private final ValueParameterDescriptor createValueParameter(FunctionInvokeDescriptor functionInvokeDescriptor, int i, TypeParameterDescriptor typeParameterDescriptor) {
            String str;
            String asString = typeParameterDescriptor.getName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "typeParameter.name.asString()");
            int hashCode = asString.hashCode();
            if (hashCode != 69) {
                if (hashCode == 84 && asString.equals("T")) {
                    str = "instance";
                    CallableDescriptor callableDescriptor = functionInvokeDescriptor;
                    Annotations empty = Annotations.Companion.getEMPTY();
                    Name identifier = Name.identifier(str);
                    Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(name)");
                    SimpleType defaultType = typeParameterDescriptor.getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType, "typeParameter.defaultType");
                    KotlinType kotlinType = defaultType;
                    SourceElement sourceElement = SourceElement.NO_SOURCE;
                    Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
                    ValueParameterDescriptorImpl valueParameterDescriptorImpl = new ValueParameterDescriptorImpl(callableDescriptor, null, i, empty, identifier, kotlinType, false, false, false, null, sourceElement);
                    return valueParameterDescriptorImpl;
                }
            } else if (asString.equals("E")) {
                str = "receiver";
                CallableDescriptor callableDescriptor2 = functionInvokeDescriptor;
                Annotations empty2 = Annotations.Companion.getEMPTY();
                Name identifier2 = Name.identifier(str);
                Intrinsics.checkExpressionValueIsNotNull(identifier2, "Name.identifier(name)");
                SimpleType defaultType2 = typeParameterDescriptor.getDefaultType();
                Intrinsics.checkExpressionValueIsNotNull(defaultType2, "typeParameter.defaultType");
                KotlinType kotlinType2 = defaultType2;
                SourceElement sourceElement2 = SourceElement.NO_SOURCE;
                Intrinsics.checkExpressionValueIsNotNull(sourceElement2, "SourceElement.NO_SOURCE");
                ValueParameterDescriptorImpl valueParameterDescriptorImpl2 = new ValueParameterDescriptorImpl(callableDescriptor2, null, i, empty2, identifier2, kotlinType2, false, false, false, null, sourceElement2);
                return valueParameterDescriptorImpl2;
            }
            if (asString != null) {
                str = asString.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
                CallableDescriptor callableDescriptor22 = functionInvokeDescriptor;
                Annotations empty22 = Annotations.Companion.getEMPTY();
                Name identifier22 = Name.identifier(str);
                Intrinsics.checkExpressionValueIsNotNull(identifier22, "Name.identifier(name)");
                SimpleType defaultType22 = typeParameterDescriptor.getDefaultType();
                Intrinsics.checkExpressionValueIsNotNull(defaultType22, "typeParameter.defaultType");
                KotlinType kotlinType22 = defaultType22;
                SourceElement sourceElement22 = SourceElement.NO_SOURCE;
                Intrinsics.checkExpressionValueIsNotNull(sourceElement22, "SourceElement.NO_SOURCE");
                ValueParameterDescriptorImpl valueParameterDescriptorImpl22 = new ValueParameterDescriptorImpl(callableDescriptor22, null, i, empty22, identifier22, kotlinType22, false, false, false, null, sourceElement22);
                return valueParameterDescriptorImpl22;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    public boolean isExternal() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isTailrec() {
        return false;
    }

    public /* synthetic */ FunctionInvokeDescriptor(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionInvokeDescriptor functionInvokeDescriptor, @NotNull Kind kind, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(declarationDescriptor, functionInvokeDescriptor, kind, z);
    }

    private FunctionInvokeDescriptor(DeclarationDescriptor declarationDescriptor, FunctionInvokeDescriptor functionInvokeDescriptor, Kind kind, boolean z) {
        super(declarationDescriptor, functionInvokeDescriptor, Annotations.Companion.getEMPTY(), OperatorNameConventions.INVOKE, kind, SourceElement.NO_SOURCE);
        setOperator(true);
        setSuspend(z);
        setHasStableParameterNames(false);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public FunctionDescriptor doSubstitute(@NotNull CopyConfiguration copyConfiguration) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(copyConfiguration, "configuration");
        FunctionInvokeDescriptor functionInvokeDescriptor = (FunctionInvokeDescriptor) super.doSubstitute(copyConfiguration);
        if (functionInvokeDescriptor == null) {
            return null;
        }
        List valueParameters = functionInvokeDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "substituted.valueParameters");
        Iterable iterable = valueParameters;
        boolean z2 = true;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) it.next();
                Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it");
                KotlinType type = valueParameterDescriptor.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
                if (FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type) != null) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    z2 = false;
                    break;
                }
            }
        }
        if (z2) {
            return functionInvokeDescriptor;
        }
        List valueParameters2 = functionInvokeDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "substituted.valueParameters");
        Iterable<ValueParameterDescriptor> iterable2 = valueParameters2;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (ValueParameterDescriptor valueParameterDescriptor2 : iterable2) {
            Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor2, "it");
            KotlinType type2 = valueParameterDescriptor2.getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, "it.type");
            arrayList.add(FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type2));
        }
        return functionInvokeDescriptor.replaceParameterNames((List) arrayList);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "newOwner");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(sourceElement, "source");
        return new FunctionInvokeDescriptor(declarationDescriptor, (FunctionInvokeDescriptor) functionDescriptor, kind, isSuspend());
    }

    private final FunctionDescriptor replaceParameterNames(List<Name> list) {
        boolean z;
        int size = getValueParameters().size() - list.size();
        boolean z2 = false;
        boolean z3 = size == 0 || size == 1;
        if (!_Assertions.ENABLED || z3) {
            List valueParameters = getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "valueParameters");
            Iterable<ValueParameterDescriptor> iterable = valueParameters;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ValueParameterDescriptor valueParameterDescriptor : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it");
                Name name = valueParameterDescriptor.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                int index = valueParameterDescriptor.getIndex();
                int i = index - size;
                if (i >= 0) {
                    Name name2 = (Name) list.get(i);
                    if (name2 != null) {
                        name = name2;
                    }
                }
                arrayList.add(valueParameterDescriptor.copy(this, name, index));
            }
            List list2 = (List) arrayList;
            CopyConfiguration newCopyBuilder = newCopyBuilder(TypeSubstitutor.EMPTY);
            Iterable iterable2 = list;
            if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                Iterator it = iterable2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((Name) it.next()) == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            CopyConfiguration original = newCopyBuilder.setHasSynthesizedParameterNames(z2).setValueParameters(list2).setOriginal(getOriginal());
            Intrinsics.checkExpressionValueIsNotNull(original, "newCopyBuilder(TypeSubst…   .setOriginal(original)");
            FunctionDescriptor doSubstitute = super.doSubstitute(original);
            if (doSubstitute == null) {
                Intrinsics.throwNpe();
            }
            return doSubstitute;
        }
        throw new AssertionError("Assertion failed");
    }
}
