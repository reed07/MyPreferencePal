package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaStaticScope.kt */
public abstract class LazyJavaStaticScope extends LazyJavaScope {
    /* access modifiers changed from: protected */
    public void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(collection, "result");
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Void getDispatchReceiverParameter() {
        return null;
    }

    public LazyJavaStaticScope(@NotNull LazyJavaResolverContext lazyJavaResolverContext) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        super(lazyJavaResolverContext);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public MethodSignatureData resolveMethodSignature(@NotNull JavaMethod javaMethod, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull KotlinType kotlinType, @NotNull List<? extends ValueParameterDescriptor> list2) {
        Intrinsics.checkParameterIsNotNull(javaMethod, Param.METHOD);
        Intrinsics.checkParameterIsNotNull(list, "methodTypeParameters");
        Intrinsics.checkParameterIsNotNull(kotlinType, "returnType");
        Intrinsics.checkParameterIsNotNull(list2, "valueParameters");
        MethodSignatureData methodSignatureData = new MethodSignatureData(kotlinType, null, list2, list, false, CollectionsKt.emptyList());
        return methodSignatureData;
    }
}
