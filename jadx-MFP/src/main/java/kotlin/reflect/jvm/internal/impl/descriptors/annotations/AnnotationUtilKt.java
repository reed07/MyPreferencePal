package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import org.jetbrains.annotations.NotNull;

/* compiled from: annotationUtil.kt */
public final class AnnotationUtilKt {
    private static final Name DEPRECATED_LEVEL_NAME;
    private static final Name DEPRECATED_MESSAGE_NAME;
    private static final Name DEPRECATED_REPLACE_WITH_NAME;
    private static final FqName INLINE_ONLY_ANNOTATION_FQ_NAME = new FqName("kotlin.internal.InlineOnly");
    private static final Name REPLACE_WITH_EXPRESSION_NAME;
    private static final Name REPLACE_WITH_IMPORTS_NAME;

    @NotNull
    public static /* synthetic */ AnnotationDescriptor createDeprecatedAnnotation$default(KotlinBuiltIns kotlinBuiltIns, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "WARNING";
        }
        return createDeprecatedAnnotation(kotlinBuiltIns, str, str2, str3);
    }

    @NotNull
    public static final AnnotationDescriptor createDeprecatedAnnotation(@NotNull KotlinBuiltIns kotlinBuiltIns, @NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(kotlinBuiltIns, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(str2, "replaceWith");
        Intrinsics.checkParameterIsNotNull(str3, Param.LEVEL);
        FqName fqName = KotlinBuiltIns.FQ_NAMES.replaceWith;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.replaceWith");
        BuiltInAnnotationDescriptor builtInAnnotationDescriptor = new BuiltInAnnotationDescriptor(kotlinBuiltIns, fqName, MapsKt.mapOf(TuplesKt.to(REPLACE_WITH_EXPRESSION_NAME, new StringValue(str2)), TuplesKt.to(REPLACE_WITH_IMPORTS_NAME, new ArrayValue(CollectionsKt.emptyList(), new AnnotationUtilKt$createDeprecatedAnnotation$replaceWithAnnotation$1(kotlinBuiltIns)))));
        FqName fqName2 = KotlinBuiltIns.FQ_NAMES.deprecated;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.deprecated");
        Name name = DEPRECATED_LEVEL_NAME;
        ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.deprecationLevel);
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB…Q_NAMES.deprecationLevel)");
        Name identifier = Name.identifier(str3);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(level)");
        return new BuiltInAnnotationDescriptor(kotlinBuiltIns, fqName2, MapsKt.mapOf(TuplesKt.to(DEPRECATED_MESSAGE_NAME, new StringValue(str)), TuplesKt.to(DEPRECATED_REPLACE_WITH_NAME, new AnnotationValue(builtInAnnotationDescriptor)), TuplesKt.to(name, new EnumValue(classId, identifier))));
    }

    static {
        Name identifier = Name.identifier("message");
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"message\")");
        DEPRECATED_MESSAGE_NAME = identifier;
        Name identifier2 = Name.identifier("replaceWith");
        Intrinsics.checkExpressionValueIsNotNull(identifier2, "Name.identifier(\"replaceWith\")");
        DEPRECATED_REPLACE_WITH_NAME = identifier2;
        Name identifier3 = Name.identifier(Param.LEVEL);
        Intrinsics.checkExpressionValueIsNotNull(identifier3, "Name.identifier(\"level\")");
        DEPRECATED_LEVEL_NAME = identifier3;
        Name identifier4 = Name.identifier("expression");
        Intrinsics.checkExpressionValueIsNotNull(identifier4, "Name.identifier(\"expression\")");
        REPLACE_WITH_EXPRESSION_NAME = identifier4;
        Name identifier5 = Name.identifier("imports");
        Intrinsics.checkExpressionValueIsNotNull(identifier5, "Name.identifier(\"imports\")");
        REPLACE_WITH_IMPORTS_NAME = identifier5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        if (isInlineOnly(r2) == false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isInlineOnlyOrReifiable(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor r2) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            boolean r0 = r2 instanceof kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
            if (r0 == 0) goto L_0x0029
            r0 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            boolean r1 = isReifiable(r0)
            if (r1 != 0) goto L_0x0027
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.getDirectMember(r0)
            java.lang.String r1 = "DescriptorUtils.getDirectMember(this)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = isReifiable(r0)
            if (r0 != 0) goto L_0x0027
            boolean r2 = isInlineOnly(r2)
            if (r2 == 0) goto L_0x0029
        L_0x0027:
            r2 = 1
            goto L_0x002a
        L_0x0029:
            r2 = 0
        L_0x002a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt.isInlineOnlyOrReifiable(kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor):boolean");
    }

    public static final boolean isEffectivelyInlineOnly(@NotNull MemberDescriptor memberDescriptor) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(memberDescriptor, "receiver$0");
        if (isInlineOnlyOrReifiable(memberDescriptor)) {
            return true;
        }
        if (memberDescriptor instanceof FunctionDescriptor) {
            FunctionDescriptor functionDescriptor = (FunctionDescriptor) memberDescriptor;
            if (functionDescriptor.isSuspend() && functionDescriptor.isInline()) {
                List valueParameters = functionDescriptor.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "valueParameters");
                Iterable iterable = valueParameters;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (((ValueParameterDescriptor) it.next()).isCrossinline()) {
                                z = true;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
                if (z || Intrinsics.areEqual((Object) functionDescriptor.getVisibility(), (Object) Visibilities.PRIVATE)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001f, code lost:
        if (hasInlineOnlyAnnotation(r0) == false) goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isInlineOnly(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor r2) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            boolean r0 = r2 instanceof kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
            if (r0 == 0) goto L_0x004b
            r0 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            boolean r1 = hasInlineOnlyAnnotation(r0)
            if (r1 != 0) goto L_0x0022
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.getDirectMember(r0)
            java.lang.String r1 = "DescriptorUtils.getDirectMember(this)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = hasInlineOnlyAnnotation(r0)
            if (r0 != 0) goto L_0x0022
            goto L_0x004b
        L_0x0022:
            r0 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r0
            boolean r0 = r0.isInline()
            boolean r1 = kotlin._Assertions.ENABLED
            if (r1 == 0) goto L_0x0049
            if (r0 == 0) goto L_0x0030
            goto L_0x0049
        L_0x0030:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Function is not inline: "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>(r2)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0049:
            r2 = 1
            return r2
        L_0x004b:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt.isInlineOnly(kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor):boolean");
    }

    private static final boolean isReifiable(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        List typeParameters = callableMemberDescriptor.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "typeParameters");
        Iterable<TypeParameterDescriptor> iterable = typeParameters;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (TypeParameterDescriptor typeParameterDescriptor : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "it");
            if (typeParameterDescriptor.isReified()) {
                return true;
            }
        }
        return false;
    }

    private static final boolean hasInlineOnlyAnnotation(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        return callableMemberDescriptor.getAnnotations().hasAnnotation(INLINE_ONLY_ANNOTATION_FQ_NAME);
    }
}
