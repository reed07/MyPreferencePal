package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinTypeFactory.kt */
public final class KotlinTypeFactory {
    public static final KotlinTypeFactory INSTANCE = new KotlinTypeFactory();

    private KotlinTypeFactory() {
    }

    private final MemberScope computeMemberScope(TypeConstructor typeConstructor, List<? extends TypeProjection> list) {
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (declarationDescriptor instanceof TypeParameterDescriptor) {
            return declarationDescriptor.getDefaultType().getMemberScope();
        }
        if (declarationDescriptor instanceof ClassDescriptor) {
            if (list.isEmpty()) {
                return ((ClassDescriptor) declarationDescriptor).getDefaultType().getMemberScope();
            }
            MemberScope memberScope = ((ClassDescriptor) declarationDescriptor).getMemberScope(TypeConstructorSubstitution.Companion.create(typeConstructor, list));
            Intrinsics.checkExpressionValueIsNotNull(memberScope, "descriptor.getMemberScop…(constructor, arguments))");
            return memberScope;
        } else if (declarationDescriptor instanceof TypeAliasDescriptor) {
            StringBuilder sb = new StringBuilder();
            sb.append("Scope for abbreviation: ");
            sb.append(((TypeAliasDescriptor) declarationDescriptor).getName());
            MemberScope createErrorScope = ErrorUtils.createErrorScope(sb.toString(), true);
            Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…{descriptor.name}\", true)");
            return createErrorScope;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unsupported classifier: ");
            sb2.append(declarationDescriptor);
            sb2.append(" for constructor: ");
            sb2.append(typeConstructor);
            throw new IllegalStateException(sb2.toString());
        }
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleType(@NotNull Annotations annotations, @NotNull TypeConstructor typeConstructor, @NotNull List<? extends TypeProjection> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        if (!annotations.isEmpty() || !list.isEmpty() || z || typeConstructor.getDeclarationDescriptor() == null) {
            return simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, list, z, INSTANCE.computeMemberScope(typeConstructor, list));
        }
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "constructor.declarationDescriptor!!");
        SimpleType defaultType = declarationDescriptor.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "constructor.declarationDescriptor!!.defaultType");
        return defaultType;
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(@NotNull Annotations annotations, @NotNull TypeConstructor typeConstructor, @NotNull List<? extends TypeProjection> list, boolean z, @NotNull MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(typeConstructor, list, z, memberScope);
        if (annotations.isEmpty()) {
            return simpleTypeImpl;
        }
        return new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleNotNullType(@NotNull Annotations annotations, @NotNull ClassDescriptor classDescriptor, @NotNull List<? extends TypeProjection> list) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "descriptor.typeConstructor");
        return simpleType(annotations, typeConstructor, list, false);
    }

    @JvmStatic
    @NotNull
    public static final UnwrappedType flexibleType(@NotNull SimpleType simpleType, @NotNull SimpleType simpleType2) {
        Intrinsics.checkParameterIsNotNull(simpleType, "lowerBound");
        Intrinsics.checkParameterIsNotNull(simpleType2, "upperBound");
        if (Intrinsics.areEqual((Object) simpleType, (Object) simpleType2)) {
            return simpleType;
        }
        return new FlexibleTypeImpl(simpleType, simpleType2);
    }
}
