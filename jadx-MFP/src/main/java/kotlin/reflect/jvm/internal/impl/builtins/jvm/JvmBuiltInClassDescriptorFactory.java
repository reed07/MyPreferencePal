package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JvmBuiltInClassDescriptorFactory.kt */
public final class JvmBuiltInClassDescriptorFactory implements ClassDescriptorFactory {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInClassDescriptorFactory.class), "cloneable", "getCloneable()Lorg/jetbrains/kotlin/descriptors/impl/ClassDescriptorImpl;"))};
    /* access modifiers changed from: private */
    @NotNull
    public static final ClassId CLONEABLE_CLASS_ID;
    /* access modifiers changed from: private */
    public static final Name CLONEABLE_NAME;
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final FqName KOTLIN_FQ_NAME = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME;
    private final NotNullLazyValue cloneable$delegate;
    /* access modifiers changed from: private */
    public final Function1<ModuleDescriptor, DeclarationDescriptor> computeContainingDeclaration;
    /* access modifiers changed from: private */
    public final ModuleDescriptor moduleDescriptor;

    /* compiled from: JvmBuiltInClassDescriptorFactory.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ClassId getCLONEABLE_CLASS_ID() {
            return JvmBuiltInClassDescriptorFactory.CLONEABLE_CLASS_ID;
        }
    }

    private final ClassDescriptorImpl getCloneable() {
        return (ClassDescriptorImpl) StorageKt.getValue(this.cloneable$delegate, (Object) this, $$delegatedProperties[0]);
    }

    public JvmBuiltInClassDescriptorFactory(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor2, @NotNull Function1<? super ModuleDescriptor, ? extends DeclarationDescriptor> function1) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor2, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(function1, "computeContainingDeclaration");
        this.moduleDescriptor = moduleDescriptor2;
        this.computeContainingDeclaration = function1;
        this.cloneable$delegate = storageManager.createLazyValue(new JvmBuiltInClassDescriptorFactory$cloneable$2(this, storageManager));
    }

    public /* synthetic */ JvmBuiltInClassDescriptorFactory(StorageManager storageManager, ModuleDescriptor moduleDescriptor2, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            function1 = AnonymousClass1.INSTANCE;
        }
        this(storageManager, moduleDescriptor2, function1);
    }

    public boolean shouldCreateClass(@NotNull FqName fqName, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        return Intrinsics.areEqual((Object) name, (Object) CLONEABLE_NAME) && Intrinsics.areEqual((Object) fqName, (Object) KOTLIN_FQ_NAME);
    }

    @Nullable
    public ClassDescriptor createClass(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        if (Intrinsics.areEqual((Object) classId, (Object) CLONEABLE_CLASS_ID)) {
            return getCloneable();
        }
        return null;
    }

    @NotNull
    public Collection<ClassDescriptor> getAllContributedClassesIfPossible(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
        if (Intrinsics.areEqual((Object) fqName, (Object) KOTLIN_FQ_NAME)) {
            return SetsKt.setOf(getCloneable());
        }
        return SetsKt.emptySet();
    }

    static {
        Name shortName = KotlinBuiltIns.FQ_NAMES.cloneable.shortName();
        Intrinsics.checkExpressionValueIsNotNull(shortName, "KotlinBuiltIns.FQ_NAMES.cloneable.shortName()");
        CLONEABLE_NAME = shortName;
        ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.cloneable.toSafe());
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB…NAMES.cloneable.toSafe())");
        CLONEABLE_CLASS_ID = classId;
    }
}
