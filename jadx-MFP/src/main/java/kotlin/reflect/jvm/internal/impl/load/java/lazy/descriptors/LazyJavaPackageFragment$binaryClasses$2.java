package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaPackageFragment.kt */
final class LazyJavaPackageFragment$binaryClasses$2 extends Lambda implements Function0<Map<String, ? extends KotlinJvmBinaryClass>> {
    final /* synthetic */ LazyJavaPackageFragment this$0;

    LazyJavaPackageFragment$binaryClasses$2(LazyJavaPackageFragment lazyJavaPackageFragment) {
        this.this$0 = lazyJavaPackageFragment;
        super(0);
    }

    @NotNull
    public final Map<String, KotlinJvmBinaryClass> invoke() {
        PackagePartProvider packagePartProvider = this.this$0.c.getComponents().getPackagePartProvider();
        String asString = this.this$0.getFqName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.asString()");
        Iterable<String> findPackageParts = packagePartProvider.findPackageParts(asString);
        Collection arrayList = new ArrayList();
        for (String str : findPackageParts) {
            JvmClassName byInternalName = JvmClassName.byInternalName(str);
            Intrinsics.checkExpressionValueIsNotNull(byInternalName, "JvmClassName.byInternalName(partName)");
            ClassId classId = ClassId.topLevel(byInternalName.getFqNameForTopLevelClassMaybeWithDollars());
            Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(JvmClas…velClassMaybeWithDollars)");
            KotlinJvmBinaryClass findKotlinClass = this.this$0.c.getComponents().getKotlinClassFinder().findKotlinClass(classId);
            Pair pair = findKotlinClass != null ? TuplesKt.to(str, findKotlinClass) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList);
    }
}
