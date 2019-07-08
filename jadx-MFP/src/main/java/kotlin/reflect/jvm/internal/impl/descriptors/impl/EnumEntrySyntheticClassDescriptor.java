package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnumEntrySyntheticClassDescriptor extends ClassDescriptorBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Annotations annotations;
    /* access modifiers changed from: private */
    public final NotNullLazyValue<Set<Name>> enumMemberNames;
    private final MemberScope scope;
    private final TypeConstructor typeConstructor;

    private class EnumEntryScope extends MemberScopeImpl {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final NotNullLazyValue<Collection<DeclarationDescriptor>> allDescriptors;
        private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> functions;
        private final MemoizedFunctionToNotNull<Name, Collection<PropertyDescriptor>> properties;

        static {
            Class<EnumEntrySyntheticClassDescriptor> cls = EnumEntrySyntheticClassDescriptor.class;
        }

        public EnumEntryScope(StorageManager storageManager) {
            this.functions = storageManager.createMemoizedFunction(new Function1<Name, Collection<SimpleFunctionDescriptor>>(EnumEntrySyntheticClassDescriptor.this) {
                public Collection<SimpleFunctionDescriptor> invoke(Name name) {
                    return EnumEntryScope.this.computeFunctions(name);
                }
            });
            this.properties = storageManager.createMemoizedFunction(new Function1<Name, Collection<PropertyDescriptor>>(EnumEntrySyntheticClassDescriptor.this) {
                public Collection<PropertyDescriptor> invoke(Name name) {
                    return EnumEntryScope.this.computeProperties(name);
                }
            });
            this.allDescriptors = storageManager.createLazyValue(new Function0<Collection<DeclarationDescriptor>>(EnumEntrySyntheticClassDescriptor.this) {
                public Collection<DeclarationDescriptor> invoke() {
                    return EnumEntryScope.this.computeAllDeclarations();
                }
            });
        }

        @NotNull
        public Collection getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
            return (Collection) this.properties.invoke(name);
        }

        /* access modifiers changed from: private */
        @NotNull
        public Collection<PropertyDescriptor> computeProperties(@NotNull Name name) {
            return resolveFakeOverrides(name, getSupertypeScope().getContributedVariables(name, NoLookupLocation.FOR_NON_TRACKED_SCOPE));
        }

        @NotNull
        public Collection getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
            return (Collection) this.functions.invoke(name);
        }

        /* access modifiers changed from: private */
        @NotNull
        public Collection<SimpleFunctionDescriptor> computeFunctions(@NotNull Name name) {
            return resolveFakeOverrides(name, getSupertypeScope().getContributedFunctions(name, NoLookupLocation.FOR_NON_TRACKED_SCOPE));
        }

        @NotNull
        private MemberScope getSupertypeScope() {
            return ((KotlinType) EnumEntrySyntheticClassDescriptor.this.getTypeConstructor().getSupertypes().iterator().next()).getMemberScope();
        }

        @NotNull
        private <D extends CallableMemberDescriptor> Collection<D> resolveFakeOverrides(@NotNull Name name, @NotNull Collection<D> collection) {
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            OverridingUtil.generateOverridesInFunctionGroup(name, collection, Collections.emptySet(), EnumEntrySyntheticClassDescriptor.this, new NonReportingOverrideStrategy() {
                /* access modifiers changed from: protected */
                public void conflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2) {
                }

                public void addFakeOverride(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
                    OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor, null);
                    linkedHashSet.add(callableMemberDescriptor);
                }
            });
            return linkedHashSet;
        }

        @NotNull
        public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
            return (Collection) this.allDescriptors.invoke();
        }

        /* access modifiers changed from: private */
        @NotNull
        public Collection<DeclarationDescriptor> computeAllDeclarations() {
            HashSet hashSet = new HashSet();
            for (Name name : (Set) EnumEntrySyntheticClassDescriptor.this.enumMemberNames.invoke()) {
                hashSet.addAll(getContributedFunctions(name, NoLookupLocation.FOR_NON_TRACKED_SCOPE));
                hashSet.addAll(getContributedVariables(name, NoLookupLocation.FOR_NON_TRACKED_SCOPE));
            }
            return hashSet;
        }

        @NotNull
        public Set<Name> getFunctionNames() {
            return (Set) EnumEntrySyntheticClassDescriptor.this.enumMemberNames.invoke();
        }

        @NotNull
        public Set<Name> getVariableNames() {
            return (Set) EnumEntrySyntheticClassDescriptor.this.enumMemberNames.invoke();
        }
    }

    @Nullable
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    @Nullable
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    public boolean isActual() {
        return false;
    }

    public boolean isCompanionObject() {
        return false;
    }

    public boolean isData() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isInner() {
        return false;
    }

    @NotNull
    public static EnumEntrySyntheticClassDescriptor create(@NotNull StorageManager storageManager, @NotNull ClassDescriptor classDescriptor, @NotNull Name name, @NotNull NotNullLazyValue<Set<Name>> notNullLazyValue, @NotNull Annotations annotations2, @NotNull SourceElement sourceElement) {
        EnumEntrySyntheticClassDescriptor enumEntrySyntheticClassDescriptor = new EnumEntrySyntheticClassDescriptor(storageManager, classDescriptor, classDescriptor.getDefaultType(), name, notNullLazyValue, annotations2, sourceElement);
        return enumEntrySyntheticClassDescriptor;
    }

    private EnumEntrySyntheticClassDescriptor(@NotNull StorageManager storageManager, @NotNull ClassDescriptor classDescriptor, @NotNull KotlinType kotlinType, @NotNull Name name, @NotNull NotNullLazyValue<Set<Name>> notNullLazyValue, @NotNull Annotations annotations2, @NotNull SourceElement sourceElement) {
        super(storageManager, classDescriptor, name, sourceElement, false);
        this.annotations = annotations2;
        this.typeConstructor = new ClassTypeConstructorImpl(this, Collections.emptyList(), Collections.singleton(kotlinType), storageManager);
        this.scope = new EnumEntryScope(storageManager);
        this.enumMemberNames = notNullLazyValue;
    }

    @NotNull
    public MemberScope getUnsubstitutedMemberScope() {
        return this.scope;
    }

    @NotNull
    public MemberScope getStaticScope() {
        return Empty.INSTANCE;
    }

    @NotNull
    public Collection<ClassConstructorDescriptor> getConstructors() {
        return Collections.emptyList();
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @NotNull
    public ClassKind getKind() {
        return ClassKind.ENUM_ENTRY;
    }

    @NotNull
    public Modality getModality() {
        return Modality.FINAL;
    }

    @NotNull
    public Visibility getVisibility() {
        return Visibilities.PUBLIC;
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("enum entry ");
        sb.append(getName());
        return sb.toString();
    }

    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return Collections.emptyList();
    }

    @NotNull
    public Collection<ClassDescriptor> getSealedSubclasses() {
        return Collections.emptyList();
    }
}
