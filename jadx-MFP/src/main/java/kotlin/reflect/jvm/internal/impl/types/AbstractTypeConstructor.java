package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractTypeConstructor.kt */
public abstract class AbstractTypeConstructor implements TypeConstructor {
    private final NotNullLazyValue<Supertypes> supertypes;

    /* compiled from: AbstractTypeConstructor.kt */
    private static final class Supertypes {
        @NotNull
        private final Collection<KotlinType> allSupertypes;
        @NotNull
        private List<? extends KotlinType> supertypesWithoutCycles = CollectionsKt.listOf(ErrorUtils.ERROR_TYPE_FOR_LOOP_IN_SUPERTYPES);

        public Supertypes(@NotNull Collection<? extends KotlinType> collection) {
            Intrinsics.checkParameterIsNotNull(collection, "allSupertypes");
            this.allSupertypes = collection;
        }

        @NotNull
        public final Collection<KotlinType> getAllSupertypes() {
            return this.allSupertypes;
        }

        @NotNull
        public final List<KotlinType> getSupertypesWithoutCycles() {
            return this.supertypesWithoutCycles;
        }

        public final void setSupertypesWithoutCycles(@NotNull List<? extends KotlinType> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.supertypesWithoutCycles = list;
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Collection<KotlinType> computeSupertypes();

    /* access modifiers changed from: protected */
    @Nullable
    public KotlinType defaultSupertypeIfEmpty() {
        return null;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract SupertypeLoopChecker getSupertypeLoopChecker();

    /* access modifiers changed from: protected */
    public void reportScopesLoopError(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
    }

    /* access modifiers changed from: protected */
    public void reportSupertypeLoopError(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
    }

    public AbstractTypeConstructor(@NotNull StorageManager storageManager) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        this.supertypes = storageManager.createLazyValueWithPostCompute(new AbstractTypeConstructor$supertypes$1(this), AbstractTypeConstructor$supertypes$2.INSTANCE, new AbstractTypeConstructor$supertypes$3(this));
    }

    @NotNull
    public List<KotlinType> getSupertypes() {
        return ((Supertypes) this.supertypes.invoke()).getSupertypesWithoutCycles();
    }

    /* access modifiers changed from: private */
    public final Collection<KotlinType> computeNeighbours(@NotNull TypeConstructor typeConstructor, boolean z) {
        AbstractTypeConstructor abstractTypeConstructor = (AbstractTypeConstructor) (!(typeConstructor instanceof AbstractTypeConstructor) ? null : typeConstructor);
        if (abstractTypeConstructor != null) {
            List plus = CollectionsKt.plus(((Supertypes) abstractTypeConstructor.supertypes.invoke()).getAllSupertypes(), (Iterable<? extends T>) abstractTypeConstructor.getAdditionalNeighboursInSupertypeGraph(z));
            if (plus != null) {
                return plus;
            }
        }
        Collection<KotlinType> supertypes2 = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes2, "supertypes");
        return supertypes2;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean z) {
        return CollectionsKt.emptyList();
    }
}
