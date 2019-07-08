package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaClassDescriptor.kt */
final class LazyJavaClassDescriptor$declaredParameters$1 extends Lambda implements Function0<List<? extends TypeParameterDescriptor>> {
    final /* synthetic */ LazyJavaClassDescriptor this$0;

    LazyJavaClassDescriptor$declaredParameters$1(LazyJavaClassDescriptor lazyJavaClassDescriptor) {
        this.this$0 = lazyJavaClassDescriptor;
        super(0);
    }

    @NotNull
    public final List<TypeParameterDescriptor> invoke() {
        Iterable<JavaTypeParameter> typeParameters = this.this$0.jClass.getTypeParameters();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        for (JavaTypeParameter javaTypeParameter : typeParameters) {
            TypeParameterDescriptor resolveTypeParameter = this.this$0.c.getTypeParameterResolver().resolveTypeParameter(javaTypeParameter);
            if (resolveTypeParameter != null) {
                arrayList.add(resolveTypeParameter);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Parameter ");
                sb.append(javaTypeParameter);
                sb.append(" surely belongs to class ");
                sb.append(this.this$0.jClass);
                sb.append(", so it must be resolved");
                throw new AssertionError(sb.toString());
            }
        }
        return (List) arrayList;
    }
}
