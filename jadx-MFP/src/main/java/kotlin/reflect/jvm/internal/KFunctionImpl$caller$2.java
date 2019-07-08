package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaMethod;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinFunction;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller.CallMode;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller.Origin;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/calls/Caller;", "Ljava/lang/reflect/Member;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KFunctionImpl.kt */
final class KFunctionImpl$caller$2 extends Lambda implements Function0<Caller<? extends Member>> {
    final /* synthetic */ KFunctionImpl this$0;

    KFunctionImpl$caller$2(KFunctionImpl kFunctionImpl) {
        this.this$0 = kFunctionImpl;
        super(0);
    }

    @NotNull
    public final Caller<Member> invoke() {
        Member member;
        CallerImpl callerImpl;
        Method method;
        JvmFunctionSignature mapSignature = RuntimeTypeMapper.INSTANCE.mapSignature(this.this$0.getDescriptor());
        if (mapSignature instanceof KotlinConstructor) {
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
                AnnotationConstructorCaller annotationConstructorCaller = new AnnotationConstructorCaller(jClass, (List) arrayList, CallMode.POSITIONAL_CALL, Origin.KOTLIN, null, 16, null);
                return annotationConstructorCaller;
            }
            member = this.this$0.getContainer().findConstructorBySignature(((KotlinConstructor) mapSignature).getConstructorDesc(), UtilKt.isPublicInBytecode(this.this$0.getDescriptor()));
        } else if (mapSignature instanceof KotlinFunction) {
            KotlinFunction kotlinFunction = (KotlinFunction) mapSignature;
            member = this.this$0.getContainer().findMethodBySignature(kotlinFunction.getMethodName(), kotlinFunction.getMethodDesc(), UtilKt.isPublicInBytecode(this.this$0.getDescriptor()));
        } else if (mapSignature instanceof JavaMethod) {
            member = ((JavaMethod) mapSignature).getMethod();
        } else if (mapSignature instanceof JavaConstructor) {
            member = ((JavaConstructor) mapSignature).getConstructor();
        } else if (mapSignature instanceof FakeJavaAnnotationConstructor) {
            List methods = ((FakeJavaAnnotationConstructor) mapSignature).getMethods();
            Class jClass2 = this.this$0.getContainer().getJClass();
            Iterable<java.lang.reflect.Method> iterable = methods;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (java.lang.reflect.Method method2 : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(method2, "it");
                arrayList2.add(method2.getName());
            }
            AnnotationConstructorCaller annotationConstructorCaller2 = new AnnotationConstructorCaller(jClass2, (List) arrayList2, CallMode.POSITIONAL_CALL, Origin.JAVA, methods);
            return annotationConstructorCaller2;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (member instanceof Constructor) {
            callerImpl = this.this$0.createConstructorCaller((Constructor) member);
        } else if (member instanceof java.lang.reflect.Method) {
            java.lang.reflect.Method method3 = (java.lang.reflect.Method) member;
            if (!Modifier.isStatic(method3.getModifiers())) {
                method = this.this$0.createInstanceMethodCaller(method3);
            } else if (this.this$0.getDescriptor().getAnnotations().findAnnotation(UtilKt.getJVM_STATIC()) != null) {
                method = this.this$0.createJvmStaticInObjectCaller(method3);
            } else {
                method = this.this$0.createStaticMethodCaller(method3);
            }
            callerImpl = method;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not compute caller for function: ");
            sb.append(this.this$0.getDescriptor());
            sb.append(" (member = ");
            sb.append(member);
            sb.append(')');
            throw new KotlinReflectionInternalError(sb.toString());
        }
        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(callerImpl, this.this$0.getDescriptor(), false, 2, null);
    }
}
