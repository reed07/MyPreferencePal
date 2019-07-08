package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

public class ClassTypeConstructorImpl extends AbstractClassTypeConstructor implements TypeConstructor {
    private final ClassDescriptor classDescriptor;
    private final List<TypeParameterDescriptor> parameters;
    private final Collection<KotlinType> supertypes;

    public boolean isDenotable() {
        return true;
    }

    public ClassTypeConstructorImpl(@NotNull ClassDescriptor classDescriptor2, @NotNull List<? extends TypeParameterDescriptor> list, @NotNull Collection<KotlinType> collection, @NotNull StorageManager storageManager) {
        super(storageManager);
        this.classDescriptor = classDescriptor2;
        this.parameters = Collections.unmodifiableList(new ArrayList(list));
        this.supertypes = Collections.unmodifiableCollection(collection);
    }

    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return this.parameters;
    }

    public String toString() {
        return DescriptorUtils.getFqName(this.classDescriptor).asString();
    }

    @NotNull
    public ClassDescriptor getDeclarationDescriptor() {
        return this.classDescriptor;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Collection<KotlinType> computeSupertypes() {
        return this.supertypes;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SupertypeLoopChecker getSupertypeLoopChecker() {
        return EMPTY.INSTANCE;
    }
}
