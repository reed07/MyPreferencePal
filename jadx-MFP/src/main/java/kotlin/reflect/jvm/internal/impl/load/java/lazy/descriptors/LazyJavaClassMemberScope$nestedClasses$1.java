package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaClassMemberScope.kt */
final class LazyJavaClassMemberScope$nestedClasses$1 extends Lambda implements Function1<Name, ClassDescriptorBase> {
    final /* synthetic */ LazyJavaResolverContext $c;
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    LazyJavaClassMemberScope$nestedClasses$1(LazyJavaClassMemberScope lazyJavaClassMemberScope, LazyJavaResolverContext lazyJavaResolverContext) {
        this.this$0 = lazyJavaClassMemberScope;
        this.$c = lazyJavaResolverContext;
        super(1);
    }

    @Nullable
    public final ClassDescriptorBase invoke(@NotNull Name name) {
        LazyJavaClassDescriptor lazyJavaClassDescriptor;
        Intrinsics.checkParameterIsNotNull(name, "name");
        EnumEntrySyntheticClassDescriptor enumEntrySyntheticClassDescriptor = null;
        if (!((Set) this.this$0.nestedClassIndex.invoke()).contains(name)) {
            JavaField javaField = (JavaField) ((Map) this.this$0.enumEntryIndex.invoke()).get(name);
            if (javaField != null) {
                Name name2 = name;
                enumEntrySyntheticClassDescriptor = EnumEntrySyntheticClassDescriptor.create(this.$c.getStorageManager(), this.this$0.getOwnerDescriptor(), name2, this.$c.getStorageManager().createLazyValue(new LazyJavaClassMemberScope$nestedClasses$1$enumMemberNames$1(this)), LazyJavaAnnotationsKt.resolveAnnotations(this.$c, javaField), this.$c.getComponents().getSourceElementFactory().source(javaField));
            }
            return enumEntrySyntheticClassDescriptor;
        }
        JavaClassFinder finder = this.$c.getComponents().getFinder();
        ClassId classId = DescriptorUtilsKt.getClassId(this.this$0.getOwnerDescriptor());
        if (classId == null) {
            Intrinsics.throwNpe();
        }
        JavaClass findClass = finder.findClass(classId.createNestedClassId(name));
        if (findClass != null) {
            LazyJavaResolverContext lazyJavaResolverContext = this.$c;
            DeclarationDescriptor ownerDescriptor = this.this$0.getOwnerDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(findClass, "it");
            lazyJavaClassDescriptor = new LazyJavaClassDescriptor(lazyJavaResolverContext, ownerDescriptor, findClass, null, 8, null);
            this.$c.getComponents().getJavaClassesTracker().reportClass(lazyJavaClassDescriptor);
        } else {
            lazyJavaClassDescriptor = null;
        }
        return lazyJavaClassDescriptor;
    }
}
