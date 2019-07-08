package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DescriptorResolverUtils {
    @NotNull
    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForNonStaticMembers(@NotNull Name name, @NotNull Collection<D> collection, @NotNull Collection<D> collection2, @NotNull ClassDescriptor classDescriptor, @NotNull ErrorReporter errorReporter) {
        return resolveOverrides(name, collection, collection2, classDescriptor, errorReporter, false);
    }

    @NotNull
    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForStaticMembers(@NotNull Name name, @NotNull Collection<D> collection, @NotNull Collection<D> collection2, @NotNull ClassDescriptor classDescriptor, @NotNull ErrorReporter errorReporter) {
        return resolveOverrides(name, collection, collection2, classDescriptor, errorReporter, true);
    }

    @NotNull
    private static <D extends CallableMemberDescriptor> Collection<D> resolveOverrides(@NotNull Name name, @NotNull Collection<D> collection, @NotNull Collection<D> collection2, @NotNull ClassDescriptor classDescriptor, @NotNull final ErrorReporter errorReporter, final boolean z) {
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        OverridingUtil.generateOverridesInFunctionGroup(name, collection, collection2, classDescriptor, new NonReportingOverrideStrategy() {
            public void conflict(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallableMemberDescriptor callableMemberDescriptor2) {
            }

            public void addFakeOverride(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
                OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor, new Function1<CallableMemberDescriptor, Unit>() {
                    public Unit invoke(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
                        errorReporter.reportCannotInferVisibility(callableMemberDescriptor);
                        return Unit.INSTANCE;
                    }
                });
                linkedHashSet.add(callableMemberDescriptor);
            }

            public void setOverriddenDescriptors(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull Collection<? extends CallableMemberDescriptor> collection) {
                if (!z || callableMemberDescriptor.getKind() == Kind.FAKE_OVERRIDE) {
                    super.setOverriddenDescriptors(callableMemberDescriptor, collection);
                }
            }
        });
        return linkedHashSet;
    }

    @Nullable
    public static ValueParameterDescriptor getAnnotationParameterByName(@NotNull Name name, @NotNull ClassDescriptor classDescriptor) {
        Collection constructors = classDescriptor.getConstructors();
        if (constructors.size() != 1) {
            return null;
        }
        for (ValueParameterDescriptor valueParameterDescriptor : ((ClassConstructorDescriptor) constructors.iterator().next()).getValueParameters()) {
            if (valueParameterDescriptor.getName().equals(name)) {
                return valueParameterDescriptor;
            }
        }
        return null;
    }

    public static boolean isObjectMethodInInterface(@NotNull JavaMember javaMember) {
        return javaMember.getContainingClass().isInterface() && (javaMember instanceof JavaMethod) && isObjectMethod((JavaMethod) javaMember);
    }

    public static boolean isObjectMethod(@NotNull JavaMethod javaMethod) {
        String asString = javaMethod.getName().asString();
        if (asString.equals("toString") || asString.equals("hashCode")) {
            return javaMethod.getValueParameters().isEmpty();
        }
        if (asString.equals("equals")) {
            return isMethodWithOneParameterWithFqName(javaMethod, "java.lang.Object");
        }
        return false;
    }

    private static boolean isMethodWithOneParameterWithFqName(@NotNull JavaMethod javaMethod, @NotNull String str) {
        List valueParameters = javaMethod.getValueParameters();
        boolean z = true;
        if (valueParameters.size() == 1) {
            JavaType type = ((JavaValueParameter) valueParameters.get(0)).getType();
            if (type instanceof JavaClassifierType) {
                JavaClassifier classifier = ((JavaClassifierType) type).getClassifier();
                if (classifier instanceof JavaClass) {
                    FqName fqName = ((JavaClass) classifier).getFqName();
                    if (fqName == null || !fqName.asString().equals(str)) {
                        z = false;
                    }
                    return z;
                }
            }
        }
        return false;
    }
}
