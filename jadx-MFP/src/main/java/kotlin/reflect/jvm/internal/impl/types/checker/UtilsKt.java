package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: utils.kt */
public final class UtilsKt {
    @Nullable
    public static final KotlinType findCorrespondingSupertype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        boolean z;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(kotlinType, "subtype");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "supertype");
        Intrinsics.checkParameterIsNotNull(typeCheckingProcedureCallbacks, "typeCheckingProcedureCallbacks");
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(new SubtypePathNode(kotlinType, null));
        TypeConstructor constructor = kotlinType2.getConstructor();
        while (!arrayDeque.isEmpty()) {
            SubtypePathNode subtypePathNode = (SubtypePathNode) arrayDeque.poll();
            KotlinType type = subtypePathNode.getType();
            TypeConstructor constructor2 = type.getConstructor();
            if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor2, constructor)) {
                boolean isMarkedNullable = type.isMarkedNullable();
                for (SubtypePathNode previous = subtypePathNode.getPrevious(); previous != null; previous = previous.getPrevious()) {
                    KotlinType type2 = previous.getType();
                    Iterable arguments = type2.getArguments();
                    if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
                        Iterator it = arguments.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                            if (((TypeProjection) it.next()).getProjectionKind() != Variance.INVARIANT) {
                                z2 = true;
                                continue;
                            } else {
                                z2 = false;
                                continue;
                            }
                            if (z2) {
                                z = true;
                                break;
                            }
                        }
                    } else {
                        z = false;
                    }
                    if (z) {
                        KotlinType safeSubstitute = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(TypeConstructorSubstitution.Companion.create(type2), false, 1, null).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                        Intrinsics.checkExpressionValueIsNotNull(safeSubstitute, "TypeConstructorSubstitut…uted, Variance.INVARIANT)");
                        type = approximate(safeSubstitute);
                    } else {
                        type = TypeConstructorSubstitution.Companion.create(type2).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                        Intrinsics.checkExpressionValueIsNotNull(type, "TypeConstructorSubstitut…uted, Variance.INVARIANT)");
                    }
                    isMarkedNullable = isMarkedNullable || type2.isMarkedNullable();
                }
                TypeConstructor constructor3 = type.getConstructor();
                if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor)) {
                    return TypeUtils.makeNullableAsSpecified(type, isMarkedNullable);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Type constructors should be equals!\n");
                sb.append("substitutedSuperType: ");
                sb.append(debugInfo(constructor3));
                sb.append(", \n\n");
                sb.append("supertype: ");
                sb.append(debugInfo(constructor));
                sb.append(" \n");
                sb.append(typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor));
                throw new AssertionError(sb.toString());
            }
            for (KotlinType kotlinType3 : constructor2.getSupertypes()) {
                Intrinsics.checkExpressionValueIsNotNull(kotlinType3, "immediateSupertype");
                arrayDeque.add(new SubtypePathNode(kotlinType3, subtypePathNode));
            }
        }
        return null;
    }

    private static final KotlinType approximate(@NotNull KotlinType kotlinType) {
        return (KotlinType) CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType).getUpper();
    }

    private static final String debugInfo(@NotNull TypeConstructor typeConstructor) {
        StringBuilder sb = new StringBuilder();
        UtilsKt$debugInfo$1$1 utilsKt$debugInfo$1$1 = new UtilsKt$debugInfo$1$1(sb);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("type: ");
        sb2.append(typeConstructor);
        utilsKt$debugInfo$1$1.invoke(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("hashCode: ");
        sb3.append(typeConstructor.hashCode());
        utilsKt$debugInfo$1$1.invoke(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append("javaClass: ");
        sb4.append(typeConstructor.getClass().getCanonicalName());
        utilsKt$debugInfo$1$1.invoke(sb4.toString());
        for (DeclarationDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor(); declarationDescriptor != null; declarationDescriptor = declarationDescriptor.getContainingDeclaration()) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("fqName: ");
            sb5.append(DescriptorRenderer.FQ_NAMES_IN_TYPES.render(declarationDescriptor));
            utilsKt$debugInfo$1$1.invoke(sb5.toString());
            StringBuilder sb6 = new StringBuilder();
            sb6.append("javaClass: ");
            sb6.append(declarationDescriptor.getClass().getCanonicalName());
            utilsKt$debugInfo$1$1.invoke(sb6.toString());
        }
        String sb7 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb7, "StringBuilder().apply(builderAction).toString()");
        return sb7;
    }
}
