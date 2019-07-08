package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

/* compiled from: RawType.kt */
final class RawTypeImpl$render$2 extends Lambda implements Function1<KotlinType, List<? extends String>> {
    final /* synthetic */ DescriptorRenderer $renderer;

    RawTypeImpl$render$2(DescriptorRenderer descriptorRenderer) {
        this.$renderer = descriptorRenderer;
        super(1);
    }

    @NotNull
    public final List<String> invoke(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        Iterable<TypeProjection> arguments = kotlinType.getArguments();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        for (TypeProjection renderTypeProjection : arguments) {
            arrayList.add(this.$renderer.renderTypeProjection(renderTypeProjection));
        }
        return (List) arrayList;
    }
}
