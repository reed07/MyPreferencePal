package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Contract;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeCheckerImpl;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OverridingUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final OverridingUtil DEFAULT = new OverridingUtil(new TypeConstructorEquality() {
        public boolean equals(@NotNull TypeConstructor typeConstructor, @NotNull TypeConstructor typeConstructor2) {
            return typeConstructor.equals(typeConstructor2);
        }
    });
    private static final List<ExternalOverridabilityCondition> EXTERNAL_CONDITIONS = CollectionsKt.toList(ServiceLoader.load(ExternalOverridabilityCondition.class, ExternalOverridabilityCondition.class.getClassLoader()));
    /* access modifiers changed from: private */
    public final TypeConstructorEquality equalityAxioms;

    public static class OverrideCompatibilityInfo {
        private static final OverrideCompatibilityInfo SUCCESS = new OverrideCompatibilityInfo(Result.OVERRIDABLE, "SUCCESS");
        private final String debugMessage;
        private final Result overridable;

        public enum Result {
            OVERRIDABLE,
            INCOMPATIBLE,
            CONFLICT
        }

        @NotNull
        public static OverrideCompatibilityInfo success() {
            return SUCCESS;
        }

        @NotNull
        public static OverrideCompatibilityInfo incompatible(@NotNull String str) {
            return new OverrideCompatibilityInfo(Result.INCOMPATIBLE, str);
        }

        @NotNull
        public static OverrideCompatibilityInfo conflict(@NotNull String str) {
            return new OverrideCompatibilityInfo(Result.CONFLICT, str);
        }

        public OverrideCompatibilityInfo(@NotNull Result result, @NotNull String str) {
            this.overridable = result;
            this.debugMessage = str;
        }

        @NotNull
        public Result getResult() {
            return this.overridable;
        }
    }

    @NotNull
    public static OverridingUtil createWithEqualityAxioms(@NotNull TypeConstructorEquality typeConstructorEquality) {
        return new OverridingUtil(typeConstructorEquality);
    }

    private OverridingUtil(TypeConstructorEquality typeConstructorEquality) {
        this.equalityAxioms = typeConstructorEquality;
    }

    @NotNull
    public static <D extends CallableDescriptor> Set<D> filterOutOverridden(@NotNull Set<D> set) {
        return filterOverrides(set, new Function2<D, D, Pair<CallableDescriptor, CallableDescriptor>>() {
            public Pair<CallableDescriptor, CallableDescriptor> invoke(D d, D d2) {
                return new Pair<>(d, d2);
            }
        });
    }

    @NotNull
    public static <D> Set<D> filterOverrides(@NotNull Set<D> set, @NotNull Function2<? super D, ? super D, Pair<CallableDescriptor, CallableDescriptor>> function2) {
        if (set.size() <= 1) {
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object next : set) {
            Iterator it = linkedHashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    linkedHashSet.add(next);
                    break;
                }
                Pair pair = (Pair) function2.invoke(next, it.next());
                CallableDescriptor callableDescriptor = (CallableDescriptor) pair.component1();
                CallableDescriptor callableDescriptor2 = (CallableDescriptor) pair.component2();
                if (overrides(callableDescriptor, callableDescriptor2)) {
                    it.remove();
                } else if (overrides(callableDescriptor2, callableDescriptor)) {
                    break;
                }
            }
        }
        return linkedHashSet;
    }

    public static <D extends CallableDescriptor> boolean overrides(@NotNull D d, @NotNull D d2) {
        if (!d.equals(d2) && DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(d.getOriginal(), d2.getOriginal())) {
            return true;
        }
        CallableDescriptor original = d2.getOriginal();
        for (CallableDescriptor areEquivalent : DescriptorUtils.getAllOverriddenDescriptors(d)) {
            if (DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(original, areEquivalent)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static Set<CallableMemberDescriptor> getOverriddenDeclarations(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectOverriddenDeclarations(callableMemberDescriptor, linkedHashSet);
        return linkedHashSet;
    }

    private static void collectOverriddenDeclarations(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull Set<CallableMemberDescriptor> set) {
        if (callableMemberDescriptor.getKind().isReal()) {
            set.add(callableMemberDescriptor);
        } else if (!callableMemberDescriptor.getOverriddenDescriptors().isEmpty()) {
            for (CallableMemberDescriptor collectOverriddenDeclarations : callableMemberDescriptor.getOverriddenDescriptors()) {
                collectOverriddenDeclarations(collectOverriddenDeclarations, set);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("No overridden descriptors found for (fake override) ");
            sb.append(callableMemberDescriptor);
            throw new IllegalStateException(sb.toString());
        }
    }

    @NotNull
    public OverrideCompatibilityInfo isOverridableBy(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2, @Nullable ClassDescriptor classDescriptor) {
        return isOverridableBy(callableDescriptor, callableDescriptor2, classDescriptor, false);
    }

    @NotNull
    public OverrideCompatibilityInfo isOverridableBy(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2, @Nullable ClassDescriptor classDescriptor, boolean z) {
        OverrideCompatibilityInfo isOverridableByWithoutExternalConditions = isOverridableByWithoutExternalConditions(callableDescriptor, callableDescriptor2, z);
        boolean z2 = isOverridableByWithoutExternalConditions.getResult() == Result.OVERRIDABLE;
        for (ExternalOverridabilityCondition externalOverridabilityCondition : EXTERNAL_CONDITIONS) {
            if (externalOverridabilityCondition.getContract() != Contract.CONFLICTS_ONLY && (!z2 || externalOverridabilityCondition.getContract() != Contract.SUCCESS_ONLY)) {
                switch (externalOverridabilityCondition.isOverridable(callableDescriptor, callableDescriptor2, classDescriptor)) {
                    case OVERRIDABLE:
                        z2 = true;
                        break;
                    case CONFLICT:
                        return OverrideCompatibilityInfo.conflict("External condition failed");
                    case INCOMPATIBLE:
                        return OverrideCompatibilityInfo.incompatible("External condition");
                }
            }
        }
        if (!z2) {
            return isOverridableByWithoutExternalConditions;
        }
        for (ExternalOverridabilityCondition externalOverridabilityCondition2 : EXTERNAL_CONDITIONS) {
            if (externalOverridabilityCondition2.getContract() == Contract.CONFLICTS_ONLY) {
                switch (externalOverridabilityCondition2.isOverridable(callableDescriptor, callableDescriptor2, classDescriptor)) {
                    case OVERRIDABLE:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Contract violation in ");
                        sb.append(externalOverridabilityCondition2.getClass().getName());
                        sb.append(" condition. It's not supposed to end with success");
                        throw new IllegalStateException(sb.toString());
                    case CONFLICT:
                        return OverrideCompatibilityInfo.conflict("External condition failed");
                    case INCOMPATIBLE:
                        return OverrideCompatibilityInfo.incompatible("External condition");
                }
            }
        }
        return OverrideCompatibilityInfo.success();
    }

    @NotNull
    public OverrideCompatibilityInfo isOverridableByWithoutExternalConditions(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2, boolean z) {
        OverrideCompatibilityInfo basicOverridabilityProblem = getBasicOverridabilityProblem(callableDescriptor, callableDescriptor2);
        if (basicOverridabilityProblem != null) {
            return basicOverridabilityProblem;
        }
        List compiledValueParameters = compiledValueParameters(callableDescriptor);
        List compiledValueParameters2 = compiledValueParameters(callableDescriptor2);
        List typeParameters = callableDescriptor.getTypeParameters();
        List typeParameters2 = callableDescriptor2.getTypeParameters();
        int i = 0;
        if (typeParameters.size() != typeParameters2.size()) {
            while (i < compiledValueParameters.size()) {
                if (!KotlinTypeChecker.DEFAULT.equalTypes((KotlinType) compiledValueParameters.get(i), (KotlinType) compiledValueParameters2.get(i))) {
                    return OverrideCompatibilityInfo.incompatible("Type parameter number mismatch");
                }
                i++;
            }
            return OverrideCompatibilityInfo.conflict("Type parameter number mismatch");
        }
        KotlinTypeChecker createTypeChecker = createTypeChecker(typeParameters, typeParameters2);
        for (int i2 = 0; i2 < typeParameters.size(); i2++) {
            if (!areTypeParametersEquivalent((TypeParameterDescriptor) typeParameters.get(i2), (TypeParameterDescriptor) typeParameters2.get(i2), createTypeChecker)) {
                return OverrideCompatibilityInfo.incompatible("Type parameter bounds mismatch");
            }
        }
        for (int i3 = 0; i3 < compiledValueParameters.size(); i3++) {
            if (!areTypesEquivalent((KotlinType) compiledValueParameters.get(i3), (KotlinType) compiledValueParameters2.get(i3), createTypeChecker)) {
                return OverrideCompatibilityInfo.incompatible("Value parameter type mismatch");
            }
        }
        if ((callableDescriptor instanceof FunctionDescriptor) && (callableDescriptor2 instanceof FunctionDescriptor) && ((FunctionDescriptor) callableDescriptor).isSuspend() != ((FunctionDescriptor) callableDescriptor2).isSuspend()) {
            return OverrideCompatibilityInfo.conflict("Incompatible suspendability");
        }
        if (z) {
            KotlinType returnType = callableDescriptor.getReturnType();
            KotlinType returnType2 = callableDescriptor2.getReturnType();
            if (!(returnType == null || returnType2 == null)) {
                if (KotlinTypeKt.isError(returnType2) && KotlinTypeKt.isError(returnType)) {
                    i = 1;
                }
                if (i == 0 && !createTypeChecker.isSubtypeOf(returnType2, returnType)) {
                    return OverrideCompatibilityInfo.conflict("Return type mismatch");
                }
            }
        }
        return OverrideCompatibilityInfo.success();
    }

    @Nullable
    public static OverrideCompatibilityInfo getBasicOverridabilityProblem(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2) {
        boolean z = callableDescriptor instanceof FunctionDescriptor;
        if (!z || (callableDescriptor2 instanceof FunctionDescriptor)) {
            boolean z2 = callableDescriptor instanceof PropertyDescriptor;
            if (!z2 || (callableDescriptor2 instanceof PropertyDescriptor)) {
                if (!z && !z2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("This type of CallableDescriptor cannot be checked for overridability: ");
                    sb.append(callableDescriptor);
                    throw new IllegalArgumentException(sb.toString());
                } else if (!callableDescriptor.getName().equals(callableDescriptor2.getName())) {
                    return OverrideCompatibilityInfo.incompatible("Name mismatch");
                } else {
                    OverrideCompatibilityInfo checkReceiverAndParameterCount = checkReceiverAndParameterCount(callableDescriptor, callableDescriptor2);
                    if (checkReceiverAndParameterCount != null) {
                        return checkReceiverAndParameterCount;
                    }
                    return null;
                }
            }
        }
        return OverrideCompatibilityInfo.incompatible("Member kind mismatch");
    }

    @NotNull
    private KotlinTypeChecker createTypeChecker(@NotNull List<TypeParameterDescriptor> list, @NotNull List<TypeParameterDescriptor> list2) {
        if (list.isEmpty()) {
            return KotlinTypeCheckerImpl.withAxioms(this.equalityAxioms);
        }
        final HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            hashMap.put(((TypeParameterDescriptor) list.get(i)).getTypeConstructor(), ((TypeParameterDescriptor) list2.get(i)).getTypeConstructor());
        }
        return KotlinTypeCheckerImpl.withAxioms(new TypeConstructorEquality() {
            public boolean equals(@NotNull TypeConstructor typeConstructor, @NotNull TypeConstructor typeConstructor2) {
                boolean z = true;
                if (OverridingUtil.this.equalityAxioms.equals(typeConstructor, typeConstructor2)) {
                    return true;
                }
                TypeConstructor typeConstructor3 = (TypeConstructor) hashMap.get(typeConstructor);
                TypeConstructor typeConstructor4 = (TypeConstructor) hashMap.get(typeConstructor2);
                if ((typeConstructor3 == null || !typeConstructor3.equals(typeConstructor2)) && (typeConstructor4 == null || !typeConstructor4.equals(typeConstructor))) {
                    z = false;
                }
                return z;
            }
        });
    }

    @Nullable
    private static OverrideCompatibilityInfo checkReceiverAndParameterCount(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        boolean z = true;
        boolean z2 = callableDescriptor.getExtensionReceiverParameter() == null;
        if (callableDescriptor2.getExtensionReceiverParameter() != null) {
            z = false;
        }
        if (z2 != z) {
            return OverrideCompatibilityInfo.incompatible("Receiver presence mismatch");
        }
        if (callableDescriptor.getValueParameters().size() != callableDescriptor2.getValueParameters().size()) {
            return OverrideCompatibilityInfo.incompatible("Value parameter number mismatch");
        }
        return null;
    }

    private static boolean areTypesEquivalent(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull KotlinTypeChecker kotlinTypeChecker) {
        if ((KotlinTypeKt.isError(kotlinType) && KotlinTypeKt.isError(kotlinType2)) || kotlinTypeChecker.equalTypes(kotlinType, kotlinType2)) {
            return true;
        }
        return false;
    }

    private static boolean areTypeParametersEquivalent(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull TypeParameterDescriptor typeParameterDescriptor2, @NotNull KotlinTypeChecker kotlinTypeChecker) {
        List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
        ArrayList arrayList = new ArrayList(typeParameterDescriptor2.getUpperBounds());
        if (upperBounds.size() != arrayList.size()) {
            return false;
        }
        for (KotlinType kotlinType : upperBounds) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (areTypesEquivalent(kotlinType, (KotlinType) listIterator.next(), kotlinTypeChecker)) {
                    listIterator.remove();
                }
            }
            return false;
        }
        return true;
    }

    private static List<KotlinType> compiledValueParameters(CallableDescriptor callableDescriptor) {
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        ArrayList arrayList = new ArrayList();
        if (extensionReceiverParameter != null) {
            arrayList.add(extensionReceiverParameter.getType());
        }
        for (ValueParameterDescriptor type : callableDescriptor.getValueParameters()) {
            arrayList.add(type.getType());
        }
        return arrayList;
    }

    public static void generateOverridesInFunctionGroup(@NotNull Name name, @NotNull Collection<? extends CallableMemberDescriptor> collection, @NotNull Collection<? extends CallableMemberDescriptor> collection2, @NotNull ClassDescriptor classDescriptor, @NotNull OverridingStrategy overridingStrategy) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        for (CallableMemberDescriptor extractAndBindOverridesForMember : collection2) {
            linkedHashSet.removeAll(extractAndBindOverridesForMember(extractAndBindOverridesForMember, collection, classDescriptor, overridingStrategy));
        }
        createAndBindFakeOverrides(classDescriptor, linkedHashSet, overridingStrategy);
    }

    public static boolean isVisibleForOverride(@NotNull MemberDescriptor memberDescriptor, @NotNull MemberDescriptor memberDescriptor2) {
        return !Visibilities.isPrivate(memberDescriptor2.getVisibility()) && Visibilities.isVisibleIgnoringReceiver(memberDescriptor2, memberDescriptor);
    }

    private static Collection<CallableMemberDescriptor> extractAndBindOverridesForMember(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull Collection<? extends CallableMemberDescriptor> collection, @NotNull ClassDescriptor classDescriptor, @NotNull OverridingStrategy overridingStrategy) {
        ArrayList arrayList = new ArrayList(collection.size());
        SmartSet create = SmartSet.create();
        for (CallableMemberDescriptor callableMemberDescriptor2 : collection) {
            Result result = DEFAULT.isOverridableBy(callableMemberDescriptor2, callableMemberDescriptor, classDescriptor).getResult();
            boolean isVisibleForOverride = isVisibleForOverride(callableMemberDescriptor, callableMemberDescriptor2);
            switch (result) {
                case OVERRIDABLE:
                    if (isVisibleForOverride) {
                        create.add(callableMemberDescriptor2);
                    }
                    arrayList.add(callableMemberDescriptor2);
                    break;
                case CONFLICT:
                    if (isVisibleForOverride) {
                        overridingStrategy.overrideConflict(callableMemberDescriptor2, callableMemberDescriptor);
                    }
                    arrayList.add(callableMemberDescriptor2);
                    break;
            }
        }
        overridingStrategy.setOverriddenDescriptors(callableMemberDescriptor, create);
        return arrayList;
    }

    private static boolean allHasSameContainingDeclaration(@NotNull Collection<CallableMemberDescriptor> collection) {
        if (collection.size() < 2) {
            return true;
        }
        final DeclarationDescriptor containingDeclaration = ((CallableMemberDescriptor) collection.iterator().next()).getContainingDeclaration();
        return CollectionsKt.all(collection, new Function1<CallableMemberDescriptor, Boolean>() {
            public Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(callableMemberDescriptor.getContainingDeclaration() == containingDeclaration);
            }
        });
    }

    private static void createAndBindFakeOverrides(@NotNull ClassDescriptor classDescriptor, @NotNull Collection<CallableMemberDescriptor> collection, @NotNull OverridingStrategy overridingStrategy) {
        if (allHasSameContainingDeclaration(collection)) {
            for (CallableMemberDescriptor singleton : collection) {
                createAndBindFakeOverride(Collections.singleton(singleton), classDescriptor, overridingStrategy);
            }
            return;
        }
        LinkedList linkedList = new LinkedList(collection);
        while (!linkedList.isEmpty()) {
            createAndBindFakeOverride(extractMembersOverridableInBothWays(VisibilityUtilKt.findMemberWithMaxVisibility(linkedList), linkedList, overridingStrategy), classDescriptor, overridingStrategy);
        }
    }

    public static boolean isMoreSpecific(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2) {
        KotlinType returnType = callableDescriptor.getReturnType();
        KotlinType returnType2 = callableDescriptor2.getReturnType();
        boolean z = false;
        if (!isVisibilityMoreSpecific(callableDescriptor, callableDescriptor2)) {
            return false;
        }
        if (callableDescriptor instanceof FunctionDescriptor) {
            return isReturnTypeMoreSpecific(callableDescriptor, returnType, callableDescriptor2, returnType2);
        }
        if (callableDescriptor instanceof PropertyDescriptor) {
            PropertyDescriptor propertyDescriptor = (PropertyDescriptor) callableDescriptor;
            PropertyDescriptor propertyDescriptor2 = (PropertyDescriptor) callableDescriptor2;
            if (!isAccessorMoreSpecific(propertyDescriptor.getSetter(), propertyDescriptor2.getSetter())) {
                return false;
            }
            if (propertyDescriptor.isVar() && propertyDescriptor2.isVar()) {
                return DEFAULT.createTypeChecker(callableDescriptor.getTypeParameters(), callableDescriptor2.getTypeParameters()).equalTypes(returnType, returnType2);
            }
            if ((propertyDescriptor.isVar() || !propertyDescriptor2.isVar()) && isReturnTypeMoreSpecific(callableDescriptor, returnType, callableDescriptor2, returnType2)) {
                z = true;
            }
            return z;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected callable: ");
        sb.append(callableDescriptor.getClass());
        throw new IllegalArgumentException(sb.toString());
    }

    private static boolean isVisibilityMoreSpecific(@NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility2) {
        Integer compare = Visibilities.compare(declarationDescriptorWithVisibility.getVisibility(), declarationDescriptorWithVisibility2.getVisibility());
        return compare == null || compare.intValue() >= 0;
    }

    private static boolean isAccessorMoreSpecific(@Nullable PropertyAccessorDescriptor propertyAccessorDescriptor, @Nullable PropertyAccessorDescriptor propertyAccessorDescriptor2) {
        if (propertyAccessorDescriptor == null || propertyAccessorDescriptor2 == null) {
            return true;
        }
        return isVisibilityMoreSpecific(propertyAccessorDescriptor, propertyAccessorDescriptor2);
    }

    private static boolean isMoreSpecificThenAllOf(@NotNull CallableDescriptor callableDescriptor, @NotNull Collection<CallableDescriptor> collection) {
        for (CallableDescriptor isMoreSpecific : collection) {
            if (!isMoreSpecific(callableDescriptor, isMoreSpecific)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isReturnTypeMoreSpecific(@NotNull CallableDescriptor callableDescriptor, @NotNull KotlinType kotlinType, @NotNull CallableDescriptor callableDescriptor2, @NotNull KotlinType kotlinType2) {
        return DEFAULT.createTypeChecker(callableDescriptor.getTypeParameters(), callableDescriptor2.getTypeParameters()).isSubtypeOf(kotlinType, kotlinType2);
    }

    @NotNull
    public static <H> H selectMostSpecificMember(@NotNull Collection<H> collection, @NotNull Function1<H, CallableDescriptor> function1) {
        if (collection.size() == 1) {
            return CollectionsKt.first((Iterable<? extends T>) collection);
        }
        ArrayList arrayList = new ArrayList(2);
        List map = CollectionsKt.map(collection, function1);
        H first = CollectionsKt.first((Iterable<? extends T>) collection);
        CallableDescriptor callableDescriptor = (CallableDescriptor) function1.invoke(first);
        for (H next : collection) {
            CallableDescriptor callableDescriptor2 = (CallableDescriptor) function1.invoke(next);
            if (isMoreSpecificThenAllOf(callableDescriptor2, map)) {
                arrayList.add(next);
            }
            if (isMoreSpecific(callableDescriptor2, callableDescriptor) && !isMoreSpecific(callableDescriptor, callableDescriptor2)) {
                first = next;
            }
        }
        if (arrayList.isEmpty()) {
            return first;
        }
        if (arrayList.size() == 1) {
            return CollectionsKt.first((Iterable<? extends T>) arrayList);
        }
        H h = null;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            H next2 = it.next();
            if (!FlexibleTypesKt.isFlexible(((CallableDescriptor) function1.invoke(next2)).getReturnType())) {
                h = next2;
                break;
            }
        }
        if (h != null) {
            return h;
        }
        return CollectionsKt.first((Iterable<? extends T>) arrayList);
    }

    private static void createAndBindFakeOverride(@NotNull Collection<CallableMemberDescriptor> collection, @NotNull ClassDescriptor classDescriptor, @NotNull OverridingStrategy overridingStrategy) {
        Collection<CallableMemberDescriptor> filterVisibleFakeOverrides = filterVisibleFakeOverrides(classDescriptor, collection);
        boolean isEmpty = filterVisibleFakeOverrides.isEmpty();
        if (!isEmpty) {
            collection = filterVisibleFakeOverrides;
        }
        CallableMemberDescriptor copy = ((CallableMemberDescriptor) selectMostSpecificMember(collection, new Function1<CallableMemberDescriptor, CallableDescriptor>() {
            public CallableMemberDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return callableMemberDescriptor;
            }
        })).copy(classDescriptor, determineModalityForFakeOverride(collection, classDescriptor), isEmpty ? Visibilities.INVISIBLE_FAKE : Visibilities.INHERITED, Kind.FAKE_OVERRIDE, false);
        overridingStrategy.setOverriddenDescriptors(copy, collection);
        overridingStrategy.addFakeOverride(copy);
    }

    @NotNull
    private static Modality determineModalityForFakeOverride(@NotNull Collection<CallableMemberDescriptor> collection, @NotNull ClassDescriptor classDescriptor) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (CallableMemberDescriptor modality : collection) {
            switch (modality.getModality()) {
                case FINAL:
                    return Modality.FINAL;
                case SEALED:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Member cannot have SEALED modality: ");
                    sb.append(modality);
                    throw new IllegalStateException(sb.toString());
                case OPEN:
                    z2 = true;
                    break;
                case ABSTRACT:
                    z3 = true;
                    break;
            }
        }
        if (!(!classDescriptor.isExpect() || classDescriptor.getModality() == Modality.ABSTRACT || classDescriptor.getModality() == Modality.SEALED)) {
            z = true;
        }
        if (z2 && !z3) {
            return Modality.OPEN;
        }
        if (z2 || !z3) {
            HashSet hashSet = new HashSet();
            for (CallableMemberDescriptor overriddenDeclarations : collection) {
                hashSet.addAll(getOverriddenDeclarations(overriddenDeclarations));
            }
            return getMinimalModality(filterOutOverridden(hashSet), z, classDescriptor.getModality());
        }
        return z ? classDescriptor.getModality() : Modality.ABSTRACT;
    }

    @NotNull
    private static Modality getMinimalModality(@NotNull Collection<CallableMemberDescriptor> collection, boolean z, @NotNull Modality modality) {
        Modality modality2 = Modality.ABSTRACT;
        for (CallableMemberDescriptor callableMemberDescriptor : collection) {
            Modality modality3 = (!z || callableMemberDescriptor.getModality() != Modality.ABSTRACT) ? callableMemberDescriptor.getModality() : modality;
            if (modality3.compareTo(modality2) < 0) {
                modality2 = modality3;
            }
        }
        return modality2;
    }

    @NotNull
    private static Collection<CallableMemberDescriptor> filterVisibleFakeOverrides(@NotNull final ClassDescriptor classDescriptor, @NotNull Collection<CallableMemberDescriptor> collection) {
        return CollectionsKt.filter(collection, new Function1<CallableMemberDescriptor, Boolean>() {
            public Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(!Visibilities.isPrivate(callableMemberDescriptor.getVisibility()) && Visibilities.isVisibleIgnoringReceiver(callableMemberDescriptor, classDescriptor));
            }
        });
    }

    @NotNull
    public static <H> Collection<H> extractMembersOverridableInBothWays(@NotNull H h, @NotNull Collection<H> collection, @NotNull Function1<H, CallableDescriptor> function1, @NotNull Function1<H, Unit> function12) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(h);
        CallableDescriptor callableDescriptor = (CallableDescriptor) function1.invoke(h);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            H next = it.next();
            CallableDescriptor callableDescriptor2 = (CallableDescriptor) function1.invoke(next);
            if (h == next) {
                it.remove();
            } else {
                Result bothWaysOverridability = getBothWaysOverridability(callableDescriptor, callableDescriptor2);
                if (bothWaysOverridability == Result.OVERRIDABLE) {
                    arrayList.add(next);
                    it.remove();
                } else if (bothWaysOverridability == Result.CONFLICT) {
                    function12.invoke(next);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public static Result getBothWaysOverridability(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        Result result = DEFAULT.isOverridableBy(callableDescriptor2, callableDescriptor, null).getResult();
        Result result2 = DEFAULT.isOverridableBy(callableDescriptor, callableDescriptor2, null).getResult();
        if (result == Result.OVERRIDABLE && result2 == Result.OVERRIDABLE) {
            return Result.OVERRIDABLE;
        }
        return (result == Result.CONFLICT || result2 == Result.CONFLICT) ? Result.CONFLICT : Result.INCOMPATIBLE;
    }

    @NotNull
    private static Collection<CallableMemberDescriptor> extractMembersOverridableInBothWays(@NotNull final CallableMemberDescriptor callableMemberDescriptor, @NotNull Queue<CallableMemberDescriptor> queue, @NotNull final OverridingStrategy overridingStrategy) {
        return extractMembersOverridableInBothWays(callableMemberDescriptor, queue, new Function1<CallableMemberDescriptor, CallableDescriptor>() {
            public CallableDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return callableMemberDescriptor;
            }
        }, new Function1<CallableMemberDescriptor, Unit>() {
            public Unit invoke(CallableMemberDescriptor callableMemberDescriptor) {
                overridingStrategy.inheritanceConflict(callableMemberDescriptor, callableMemberDescriptor);
                return Unit.INSTANCE;
            }
        });
    }

    public static void resolveUnknownVisibilityForMember(@NotNull CallableMemberDescriptor callableMemberDescriptor, @Nullable Function1<CallableMemberDescriptor, Unit> function1) {
        Visibility visibility;
        for (CallableMemberDescriptor callableMemberDescriptor2 : callableMemberDescriptor.getOverriddenDescriptors()) {
            if (callableMemberDescriptor2.getVisibility() == Visibilities.INHERITED) {
                resolveUnknownVisibilityForMember(callableMemberDescriptor2, function1);
            }
        }
        if (callableMemberDescriptor.getVisibility() == Visibilities.INHERITED) {
            Visibility computeVisibilityToInherit = computeVisibilityToInherit(callableMemberDescriptor);
            if (computeVisibilityToInherit == null) {
                if (function1 != null) {
                    function1.invoke(callableMemberDescriptor);
                }
                visibility = Visibilities.PUBLIC;
            } else {
                visibility = computeVisibilityToInherit;
            }
            if (callableMemberDescriptor instanceof PropertyDescriptorImpl) {
                ((PropertyDescriptorImpl) callableMemberDescriptor).setVisibility(visibility);
                for (PropertyAccessorDescriptor resolveUnknownVisibilityForMember : ((PropertyDescriptor) callableMemberDescriptor).getAccessors()) {
                    resolveUnknownVisibilityForMember(resolveUnknownVisibilityForMember, computeVisibilityToInherit == null ? null : function1);
                }
            } else if (callableMemberDescriptor instanceof FunctionDescriptorImpl) {
                ((FunctionDescriptorImpl) callableMemberDescriptor).setVisibility(visibility);
            } else {
                PropertyAccessorDescriptorImpl propertyAccessorDescriptorImpl = (PropertyAccessorDescriptorImpl) callableMemberDescriptor;
                propertyAccessorDescriptorImpl.setVisibility(visibility);
                if (visibility != propertyAccessorDescriptorImpl.getCorrespondingProperty().getVisibility()) {
                    propertyAccessorDescriptorImpl.setDefault(false);
                }
            }
        }
    }

    @Nullable
    private static Visibility computeVisibilityToInherit(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Collection<CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Visibility findMaxVisibility = findMaxVisibility(overriddenDescriptors);
        if (findMaxVisibility == null) {
            return null;
        }
        if (callableMemberDescriptor.getKind() != Kind.FAKE_OVERRIDE) {
            return findMaxVisibility.normalize();
        }
        for (CallableMemberDescriptor callableMemberDescriptor2 : overriddenDescriptors) {
            if (callableMemberDescriptor2.getModality() != Modality.ABSTRACT && !callableMemberDescriptor2.getVisibility().equals(findMaxVisibility)) {
                return null;
            }
        }
        return findMaxVisibility;
    }

    @Nullable
    public static Visibility findMaxVisibility(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
        if (collection.isEmpty()) {
            return Visibilities.DEFAULT_VISIBILITY;
        }
        Visibility visibility = null;
        for (CallableMemberDescriptor visibility2 : collection) {
            Visibility visibility3 = visibility2.getVisibility();
            if (visibility == null) {
                visibility = visibility3;
            } else {
                Integer compare = Visibilities.compare(visibility3, visibility);
                if (compare == null) {
                    visibility = null;
                } else if (compare.intValue() > 0) {
                    visibility = visibility3;
                }
            }
        }
        if (visibility == null) {
            return null;
        }
        for (CallableMemberDescriptor visibility4 : collection) {
            Integer compare2 = Visibilities.compare(visibility, visibility4.getVisibility());
            if (compare2 != null) {
                if (compare2.intValue() < 0) {
                }
            }
            return null;
        }
        return visibility;
    }
}
