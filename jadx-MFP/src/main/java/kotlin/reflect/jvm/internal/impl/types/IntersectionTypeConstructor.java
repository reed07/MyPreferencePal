package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;
import org.jetbrains.annotations.NotNull;

public class IntersectionTypeConstructor implements TypeConstructor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int hashCode = this.intersectedTypes.hashCode();
    private final Set<KotlinType> intersectedTypes;

    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    public boolean isDenotable() {
        return false;
    }

    public IntersectionTypeConstructor(Collection<KotlinType> collection) {
        this.intersectedTypes = new LinkedHashSet(collection);
    }

    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        return Collections.emptyList();
    }

    @NotNull
    public Collection<KotlinType> getSupertypes() {
        return this.intersectedTypes;
    }

    public MemberScope createScopeForKotlinType() {
        StringBuilder sb = new StringBuilder();
        sb.append("member scope for intersection type ");
        sb.append(this);
        return TypeIntersectionScope.create(sb.toString(), this.intersectedTypes);
    }

    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        return ((KotlinType) this.intersectedTypes.iterator().next()).getConstructor().getBuiltIns();
    }

    public String toString() {
        return makeDebugNameForIntersectionType(this.intersectedTypes);
    }

    private static String makeDebugNameForIntersectionType(Iterable<KotlinType> iterable) {
        StringBuilder sb = new StringBuilder("{");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(((KotlinType) it.next()).toString());
            if (it.hasNext()) {
                sb.append(" & ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) obj;
        Set<KotlinType> set = this.intersectedTypes;
        return set == null ? intersectionTypeConstructor.intersectedTypes == null : set.equals(intersectionTypeConstructor.intersectedTypes);
    }

    public int hashCode() {
        return this.hashCode;
    }
}
