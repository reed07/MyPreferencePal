package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinFunction;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller.CallMode;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller.Origin;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/calls/Caller;", "Ljava/lang/reflect/Member;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KFunctionImpl.kt */
final class KFunctionImpl$defaultCaller$2 extends Lambda implements Function0<Caller<? extends Member>> {
    final /* synthetic */ KFunctionImpl this$0;

    KFunctionImpl$defaultCaller$2(KFunctionImpl kFunctionImpl) {
        this.this$0 = kFunctionImpl;
        super(0);
    }

    @Nullable
    public final Caller<Member> invoke() {
        Member member;
        CallerImpl callerImpl;
        Method method;
        JvmFunctionSignature mapSignature = RuntimeTypeMapper.INSTANCE.mapSignature(this.this$0.getDescriptor());
        Caller<Member> caller = null;
        if (mapSignature instanceof KotlinFunction) {
            KDeclarationContainerImpl container = this.this$0.getContainer();
            KotlinFunction kotlinFunction = (KotlinFunction) mapSignature;
            String methodName = kotlinFunction.getMethodName();
            String methodDesc = kotlinFunction.getMethodDesc();
            Member member2 = this.this$0.getCaller().getMember();
            if (member2 == null) {
                Intrinsics.throwNpe();
            }
            member = container.findDefaultMethod(methodName, methodDesc, !Modifier.isStatic(member2.getModifiers()), UtilKt.isPublicInBytecode(this.this$0.getDescriptor()));
        } else if (mapSignature instanceof KotlinConstructor) {
            if (this.this$0.isAnnotationConstructor()) {
                Class jClass = this.this$0.getContainer().getJClass();
                Iterable<KParameter> parameters = this.this$0.getParameters();
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
                for (KParameter name : parameters) {
                    String name2 = name.getName();
                    if (name2 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(name2);
                }
                AnnotationConstructorCaller annotationConstructorCaller = new AnnotationConstructorCaller(jClass, (List) arrayList, CallMode.CALL_BY_NAME, Origin.KOTLIN, null, 16, null);
                return annotationConstructorCaller;
            }
            member = this.this$0.getContainer().findDefaultConstructor(((KotlinConstructor) mapSignature).getConstructorDesc(), UtilKt.isPublicInBytecode(this.this$0.getDescriptor()));
        } else if (mapSignature instanceof FakeJavaAnnotationConstructor) {
            List methods = ((FakeJavaAnnotationConstructor) mapSignature).getMethods();
            Class jClass2 = this.this$0.getContainer().getJClass();
            Iterable<java.lang.reflect.Method> iterable = methods;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (java.lang.reflect.Method method2 : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(method2, "it");
                arrayList2.add(method2.getName());
            }
            AnnotationConstructorCaller annotationConstructorCaller2 = new AnnotationConstructorCaller(jClass2, (List) arrayList2, CallMode.CALL_BY_NAME, Origin.JAVA, methods);
            return annotationConstructorCaller2;
        } else {
            member = null;
        }
        if (member instanceof Constructor) {
            callerImpl = this.this$0.createConstructorCaller((Constructor) member);
        } else if (member instanceof java.lang.reflect.Method) {
            if (this.this$0.getDescriptor().getAnnotations().findAnnotation(UtilKt.getJVM_STATIC()) != null) {
                DeclarationDescriptor containingDeclaration = this.this$0.getDescriptor().getContainingDeclaration();
                if (containingDeclaration == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                } else if (!((ClassDescriptor) containingDeclaration).isCompanionObject()) {
                    method = this.this$0.createJvmStaticInObjectCaller((java.lang.reflect.Method) member);
                    callerImpl = method;
                }
            }
            method = this.this$0.createStaticMethodCaller((java.lang.reflect.Method) member);
            callerImpl = method;
        } else {
            callerImpl = null;
        }
        if (callerImpl != null) {
            caller = InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(callerImpl, this.this$0.getDescriptor(), true);
        }
        return caller;
    }
}
