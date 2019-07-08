package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeConstructor.kt */
public final class CapturedTypeConstructorKt {
    @NotNull
    public static final KotlinType createCapturedType(@NotNull TypeProjection typeProjection) {
        Intrinsics.checkParameterIsNotNull(typeProjection, "typeProjection");
        CapturedType capturedType = new CapturedType(typeProjection, null, false, null, 14, null);
        return capturedType;
    }

    public static final boolean isCaptured(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return kotlinType.getConstructor() instanceof CapturedTypeConstructor;
    }

    @NotNull
    public static /* synthetic */ TypeSubstitution wrapWithCapturingSubstitution$default(TypeSubstitution typeSubstitution, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return wrapWithCapturingSubstitution(typeSubstitution, z);
    }

    @NotNull
    public static final TypeSubstitution wrapWithCapturingSubstitution(@NotNull TypeSubstitution typeSubstitution, boolean z) {
        Intrinsics.checkParameterIsNotNull(typeSubstitution, "receiver$0");
        if (!(typeSubstitution instanceof IndexedParametersSubstitution)) {
            return new CapturedTypeConstructorKt$wrapWithCapturingSubstitution$2(typeSubstitution, z, typeSubstitution);
        }
        IndexedParametersSubstitution indexedParametersSubstitution = (IndexedParametersSubstitution) typeSubstitution;
        TypeParameterDescriptor[] parameters = indexedParametersSubstitution.getParameters();
        Iterable<Pair> zip = ArraysKt.zip((T[]) indexedParametersSubstitution.getArguments(), (R[]) indexedParametersSubstitution.getParameters());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(zip, 10));
        for (Pair pair : zip) {
            arrayList.add(createCapturedIfNeeded((TypeProjection) pair.getFirst(), (TypeParameterDescriptor) pair.getSecond()));
        }
        Object[] array = ((List) arrayList).toArray(new TypeProjection[0]);
        if (array != null) {
            return new IndexedParametersSubstitution(parameters, (TypeProjection[]) array, z);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* access modifiers changed from: private */
    public static final TypeProjection createCapturedIfNeeded(@NotNull TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        TypeProjection typeProjection2;
        if (typeParameterDescriptor == null || typeProjection.getProjectionKind() == Variance.INVARIANT) {
            return typeProjection;
        }
        if (typeParameterDescriptor.getVariance() != typeProjection.getProjectionKind()) {
            return new TypeProjectionImpl(createCapturedType(typeProjection));
        }
        if (typeProjection.isStarProjection()) {
            StorageManager storageManager = LockBasedStorageManager.NO_LOCKS;
            Intrinsics.checkExpressionValueIsNotNull(storageManager, "LockBasedStorageManager.NO_LOCKS");
            typeProjection2 = new TypeProjectionImpl(new LazyWrappedType(storageManager, new CapturedTypeConstructorKt$createCapturedIfNeeded$1(typeProjection)));
        } else {
            typeProjection2 = new TypeProjectionImpl(typeProjection.getType());
        }
        return typeProjection2;
    }
}
