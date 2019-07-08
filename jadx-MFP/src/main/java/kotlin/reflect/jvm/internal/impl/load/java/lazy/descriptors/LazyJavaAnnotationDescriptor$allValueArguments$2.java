package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaAnnotationDescriptor.kt */
final class LazyJavaAnnotationDescriptor$allValueArguments$2 extends Lambda implements Function0<Map<Name, ? extends ConstantValue<?>>> {
    final /* synthetic */ LazyJavaAnnotationDescriptor this$0;

    LazyJavaAnnotationDescriptor$allValueArguments$2(LazyJavaAnnotationDescriptor lazyJavaAnnotationDescriptor) {
        this.this$0 = lazyJavaAnnotationDescriptor;
        super(0);
    }

    @NotNull
    public final Map<Name, ConstantValue<?>> invoke() {
        Iterable<JavaAnnotationArgument> arguments = this.this$0.javaAnnotation.getArguments();
        Collection arrayList = new ArrayList();
        for (JavaAnnotationArgument javaAnnotationArgument : arguments) {
            Name name = javaAnnotationArgument.getName();
            if (name == null) {
                name = JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME;
            }
            ConstantValue access$resolveAnnotationArgument = this.this$0.resolveAnnotationArgument(javaAnnotationArgument);
            Pair pair = access$resolveAnnotationArgument != null ? TuplesKt.to(name, access$resolveAnnotationArgument) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList);
    }
}
