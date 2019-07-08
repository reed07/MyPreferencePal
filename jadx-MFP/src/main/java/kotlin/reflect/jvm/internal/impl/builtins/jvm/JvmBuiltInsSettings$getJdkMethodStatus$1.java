package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class JvmBuiltInsSettings$getJdkMethodStatus$1<N> implements Neighbors<N> {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    JvmBuiltInsSettings$getJdkMethodStatus$1(JvmBuiltInsSettings jvmBuiltInsSettings) {
        this.this$0 = jvmBuiltInsSettings;
    }

    @NotNull
    public final List<LazyJavaClassDescriptor> getNeighbors(ClassDescriptor classDescriptor) {
        Intrinsics.checkExpressionValueIsNotNull(classDescriptor, "it");
        TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "it.typeConstructor");
        Collection supertypes = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "it.typeConstructor.supertypes");
        Iterable<KotlinType> iterable = supertypes;
        Collection arrayList = new ArrayList();
        for (KotlinType constructor : iterable) {
            ClassifierDescriptor declarationDescriptor = constructor.getConstructor().getDeclarationDescriptor();
            LazyJavaClassDescriptor lazyJavaClassDescriptor = null;
            ClassifierDescriptor original = declarationDescriptor != null ? declarationDescriptor.getOriginal() : null;
            if (!(original instanceof ClassDescriptor)) {
                original = null;
            }
            ClassDescriptor classDescriptor2 = (ClassDescriptor) original;
            if (classDescriptor2 != null) {
                lazyJavaClassDescriptor = this.this$0.getJavaAnalogue(classDescriptor2);
            }
            if (lazyJavaClassDescriptor != null) {
                arrayList.add(lazyJavaClassDescriptor);
            }
        }
        return (List) arrayList;
    }
}
