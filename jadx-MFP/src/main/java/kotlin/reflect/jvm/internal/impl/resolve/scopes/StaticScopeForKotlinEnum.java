package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: StaticScopeForKotlinEnum.kt */
public final class StaticScopeForKotlinEnum extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(StaticScopeForKotlinEnum.class), "functions", "getFunctions()Ljava/util/List;"))};
    /* access modifiers changed from: private */
    public final ClassDescriptor containingClass;
    private final NotNullLazyValue functions$delegate;

    private final List<SimpleFunctionDescriptor> getFunctions() {
        return (List) StorageKt.getValue(this.functions$delegate, (Object) this, $$delegatedProperties[0]);
    }

    @Nullable
    public Void getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return null;
    }

    public StaticScopeForKotlinEnum(@NotNull StorageManager storageManager, @NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "containingClass");
        this.containingClass = classDescriptor;
        boolean z = this.containingClass.getKind() == ClassKind.ENUM_CLASS;
        if (!_Assertions.ENABLED || z) {
            this.functions$delegate = storageManager.createLazyValue(new StaticScopeForKotlinEnum$functions$2(this));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Class should be an enum: ");
        sb.append(this.containingClass);
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public List<SimpleFunctionDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        return getFunctions();
    }

    @NotNull
    public ArrayList<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        Iterable functions = getFunctions();
        Collection arrayList = new ArrayList(1);
        for (Object next : functions) {
            if (Intrinsics.areEqual((Object) ((SimpleFunctionDescriptor) next).getName(), (Object) name)) {
                arrayList.add(next);
            }
        }
        return (ArrayList) arrayList;
    }
}
