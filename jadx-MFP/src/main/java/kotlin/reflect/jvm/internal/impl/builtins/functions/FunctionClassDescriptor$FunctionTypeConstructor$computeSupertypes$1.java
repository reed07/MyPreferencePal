package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import org.jetbrains.annotations.NotNull;

/* compiled from: FunctionClassDescriptor.kt */
final class FunctionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1 extends Lambda implements Function2<PackageFragmentDescriptor, Name, Unit> {
    final /* synthetic */ ArrayList $result;
    final /* synthetic */ FunctionTypeConstructor this$0;

    FunctionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1(FunctionTypeConstructor functionTypeConstructor, ArrayList arrayList) {
        this.this$0 = functionTypeConstructor;
        this.$result = arrayList;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((PackageFragmentDescriptor) obj, (Name) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor, "packageFragment");
        Intrinsics.checkParameterIsNotNull(name, "name");
        ClassifierDescriptor contributedClassifier = packageFragmentDescriptor.getMemberScope().getContributedClassifier(name, NoLookupLocation.FROM_BUILTINS);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor != null) {
            TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "descriptor.typeConstructor");
            Iterable<TypeParameterDescriptor> takeLast = CollectionsKt.takeLast(this.this$0.getParameters(), typeConstructor.getParameters().size());
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(takeLast, 10));
            for (TypeParameterDescriptor defaultType : takeLast) {
                arrayList.add(new TypeProjectionImpl(defaultType.getDefaultType()));
            }
            this.$result.add(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), classDescriptor, (List) arrayList));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Class ");
        sb.append(name);
        sb.append(" not found in ");
        sb.append(packageFragmentDescriptor);
        throw new IllegalStateException(sb.toString().toString());
    }
}
