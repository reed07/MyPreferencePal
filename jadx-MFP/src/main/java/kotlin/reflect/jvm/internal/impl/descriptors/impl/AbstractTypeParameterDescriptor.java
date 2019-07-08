package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.LazyScopeAdapter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractTypeParameterDescriptor extends DeclarationDescriptorNonRootImpl implements TypeParameterDescriptor {
    private final NotNullLazyValue<SimpleType> defaultType;
    private final int index;
    private final boolean reified;
    private final NotNullLazyValue<TypeConstructor> typeConstructor;
    private final Variance variance;

    private class TypeParameterTypeConstructor extends AbstractTypeConstructor {
        private final SupertypeLoopChecker supertypeLoopChecker;

        public boolean isDenotable() {
            return true;
        }

        public TypeParameterTypeConstructor(StorageManager storageManager, @NotNull SupertypeLoopChecker supertypeLoopChecker2) {
            super(storageManager);
            this.supertypeLoopChecker = supertypeLoopChecker2;
        }

        /* access modifiers changed from: protected */
        @NotNull
        public Collection<KotlinType> computeSupertypes() {
            return AbstractTypeParameterDescriptor.this.resolveUpperBounds();
        }

        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return Collections.emptyList();
        }

        @NotNull
        public ClassifierDescriptor getDeclarationDescriptor() {
            return AbstractTypeParameterDescriptor.this;
        }

        @NotNull
        public KotlinBuiltIns getBuiltIns() {
            return DescriptorUtilsKt.getBuiltIns(AbstractTypeParameterDescriptor.this);
        }

        public String toString() {
            return AbstractTypeParameterDescriptor.this.getName().toString();
        }

        /* access modifiers changed from: protected */
        @NotNull
        public SupertypeLoopChecker getSupertypeLoopChecker() {
            return this.supertypeLoopChecker;
        }

        /* access modifiers changed from: protected */
        public void reportSupertypeLoopError(@NotNull KotlinType kotlinType) {
            AbstractTypeParameterDescriptor.this.reportSupertypeLoopError(kotlinType);
        }

        /* access modifiers changed from: protected */
        @Nullable
        public KotlinType defaultSupertypeIfEmpty() {
            return ErrorUtils.createErrorType("Cyclic upper bounds");
        }
    }

    public boolean isCapturedFromOuterDeclaration() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void reportSupertypeLoopError(@NotNull KotlinType kotlinType);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract List<KotlinType> resolveUpperBounds();

    protected AbstractTypeParameterDescriptor(@NotNull final StorageManager storageManager, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull final Name name, @NotNull Variance variance2, boolean z, int i, @NotNull SourceElement sourceElement, @NotNull final SupertypeLoopChecker supertypeLoopChecker) {
        super(declarationDescriptor, annotations, name, sourceElement);
        this.variance = variance2;
        this.reified = z;
        this.index = i;
        this.typeConstructor = storageManager.createLazyValue(new Function0<TypeConstructor>() {
            public TypeConstructor invoke() {
                return new TypeParameterTypeConstructor(storageManager, supertypeLoopChecker);
            }
        });
        this.defaultType = storageManager.createLazyValue(new Function0<SimpleType>() {
            public SimpleType invoke() {
                return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), AbstractTypeParameterDescriptor.this.getTypeConstructor(), Collections.emptyList(), false, new LazyScopeAdapter(storageManager.createLazyValue(new Function0<MemberScope>() {
                    public MemberScope invoke() {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Scope for type parameter ");
                        sb.append(name.asString());
                        return TypeIntersectionScope.create(sb.toString(), AbstractTypeParameterDescriptor.this.getUpperBounds());
                    }
                })));
            }
        });
    }

    @NotNull
    public Variance getVariance() {
        return this.variance;
    }

    public boolean isReified() {
        return this.reified;
    }

    public int getIndex() {
        return this.index;
    }

    @NotNull
    public List<KotlinType> getUpperBounds() {
        return ((TypeParameterTypeConstructor) getTypeConstructor()).getSupertypes();
    }

    @NotNull
    public final TypeConstructor getTypeConstructor() {
        return (TypeConstructor) this.typeConstructor.invoke();
    }

    @NotNull
    public SimpleType getDefaultType() {
        return (SimpleType) this.defaultType.invoke();
    }

    @NotNull
    public TypeParameterDescriptor getOriginal() {
        return (TypeParameterDescriptor) super.getOriginal();
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitTypeParameterDescriptor(this, d);
    }
}
