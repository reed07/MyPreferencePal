package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

/* compiled from: signatureEnhancement.kt */
final class SignatureEnhancement$SignatureParts$toIndexed$1 extends Lambda implements Function2<KotlinType, LazyJavaResolverContext, Unit> {
    final /* synthetic */ ArrayList $list;

    SignatureEnhancement$SignatureParts$toIndexed$1(ArrayList arrayList) {
        this.$list = arrayList;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((KotlinType) obj, (LazyJavaResolverContext) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull KotlinType kotlinType, @NotNull LazyJavaResolverContext lazyJavaResolverContext) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "ownerContext");
        LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, kotlinType.getAnnotations());
        ArrayList arrayList = this.$list;
        JavaTypeQualifiersByElementType defaultTypeQualifiers = copyWithNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        arrayList.add(new TypeAndDefaultQualifiers(kotlinType, defaultTypeQualifiers != null ? defaultTypeQualifiers.get(QualifierApplicabilityType.TYPE_USE) : null));
        for (TypeProjection typeProjection : kotlinType.getArguments()) {
            if (typeProjection.isStarProjection()) {
                ArrayList arrayList2 = this.$list;
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "arg.type");
                arrayList2.add(new TypeAndDefaultQualifiers(type, null));
            } else {
                SignatureEnhancement$SignatureParts$toIndexed$1 signatureEnhancement$SignatureParts$toIndexed$1 = this;
                KotlinType type2 = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type2, "arg.type");
                signatureEnhancement$SignatureParts$toIndexed$1.invoke(type2, copyWithNewDefaultTypeQualifiers);
            }
        }
    }
}
