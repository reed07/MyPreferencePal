package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MethodAnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.Companion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
public final class AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1 implements MemberVisitor {
    final /* synthetic */ HashMap $memberAnnotations;
    final /* synthetic */ HashMap $propertyConstants;
    final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader this$0;

    /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    public final class AnnotationVisitorForMethod extends MemberAnnotationVisitor implements MethodAnnotationVisitor {
        final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1 this$0;

        public AnnotationVisitorForMethod(AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1 abstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1, @NotNull MemberSignature memberSignature) {
            Intrinsics.checkParameterIsNotNull(memberSignature, "signature");
            this.this$0 = abstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1;
            super(abstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1, memberSignature);
        }

        @Nullable
        public AnnotationArgumentVisitor visitParameterAnnotation(int i, @NotNull ClassId classId, @NotNull SourceElement sourceElement) {
            Intrinsics.checkParameterIsNotNull(classId, "classId");
            Intrinsics.checkParameterIsNotNull(sourceElement, "source");
            MemberSignature fromMethodSignatureAndParameterIndex = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(getSignature(), i);
            List list = (List) this.this$0.$memberAnnotations.get(fromMethodSignatureAndParameterIndex);
            if (list == null) {
                list = new ArrayList();
                this.this$0.$memberAnnotations.put(fromMethodSignatureAndParameterIndex, list);
            }
            return this.this$0.this$0.loadAnnotationIfNotSpecial(classId, sourceElement, list);
        }
    }

    /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    public class MemberAnnotationVisitor implements AnnotationVisitor {
        private final ArrayList<A> result = new ArrayList<>();
        @NotNull
        private final MemberSignature signature;
        final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1 this$0;

        public MemberAnnotationVisitor(AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1 abstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1, @NotNull MemberSignature memberSignature) {
            Intrinsics.checkParameterIsNotNull(memberSignature, "signature");
            this.this$0 = abstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1;
            this.signature = memberSignature;
        }

        /* access modifiers changed from: protected */
        @NotNull
        public final MemberSignature getSignature() {
            return this.signature;
        }

        @Nullable
        public AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement) {
            Intrinsics.checkParameterIsNotNull(classId, "classId");
            Intrinsics.checkParameterIsNotNull(sourceElement, "source");
            return this.this$0.this$0.loadAnnotationIfNotSpecial(classId, sourceElement, this.result);
        }

        public void visitEnd() {
            if (!this.result.isEmpty()) {
                this.this$0.$memberAnnotations.put(this.signature, this.result);
            }
        }
    }

    AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, HashMap hashMap, HashMap hashMap2) {
        this.this$0 = abstractBinaryClassAnnotationAndConstantLoader;
        this.$memberAnnotations = hashMap;
        this.$propertyConstants = hashMap2;
    }

    @Nullable
    public MethodAnnotationVisitor visitMethod(@NotNull Name name, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(str, "desc");
        Companion companion = MemberSignature.Companion;
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        return new AnnotationVisitorForMethod(this, companion.fromMethodNameAndDesc(asString, str));
    }

    @Nullable
    public AnnotationVisitor visitField(@NotNull Name name, @NotNull String str, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(str, "desc");
        Companion companion = MemberSignature.Companion;
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        MemberSignature fromFieldNameAndDesc = companion.fromFieldNameAndDesc(asString, str);
        if (obj != null) {
            Object loadConstant = this.this$0.loadConstant(str, obj);
            if (loadConstant != null) {
                this.$propertyConstants.put(fromFieldNameAndDesc, loadConstant);
            }
        }
        return new MemberAnnotationVisitor(this, fromFieldNameAndDesc);
    }
}
