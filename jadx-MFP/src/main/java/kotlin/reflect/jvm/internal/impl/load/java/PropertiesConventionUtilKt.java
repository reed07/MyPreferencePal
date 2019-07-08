package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: propertiesConventionUtil.kt */
public final class PropertiesConventionUtilKt {
    @Nullable
    public static final Name propertyNameByGetMethodName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "methodName");
        Name propertyNameFromAccessorMethodName$default = propertyNameFromAccessorMethodName$default(name, "get", false, null, 12, null);
        if (propertyNameFromAccessorMethodName$default != null) {
            return propertyNameFromAccessorMethodName$default;
        }
        return propertyNameFromAccessorMethodName$default(name, "is", false, null, 8, null);
    }

    @Nullable
    public static final Name propertyNameBySetMethodName(@NotNull Name name, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "methodName");
        return propertyNameFromAccessorMethodName$default(name, "set", false, z ? "is" : null, 4, null);
    }

    @NotNull
    public static final List<Name> propertyNamesBySetMethodName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "methodName");
        return CollectionsKt.filterNotNull(CollectionsKt.listOf(propertyNameBySetMethodName(name, false), propertyNameBySetMethodName(name, true)));
    }

    static /* synthetic */ Name propertyNameFromAccessorMethodName$default(Name name, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        return propertyNameFromAccessorMethodName(name, str, z, str2);
    }

    private static final Name propertyNameFromAccessorMethodName(Name name, String str, boolean z, String str2) {
        if (name.isSpecial()) {
            return null;
        }
        String identifier = name.getIdentifier();
        Intrinsics.checkExpressionValueIsNotNull(identifier, "methodName.identifier");
        if (!StringsKt.startsWith$default(identifier, str, false, 2, null) || identifier.length() == str.length()) {
            return null;
        }
        char charAt = identifier.charAt(str.length());
        if ('a' <= charAt && 'z' >= charAt) {
            return null;
        }
        if (str2 != null) {
            if (!_Assertions.ENABLED || z) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(StringsKt.removePrefix(identifier, (CharSequence) str));
                return Name.identifier(sb.toString());
            }
            throw new AssertionError("Assertion failed");
        } else if (!z) {
            return name;
        } else {
            String decapitalizeSmart = CapitalizeDecapitalizeKt.decapitalizeSmart(StringsKt.removePrefix(identifier, (CharSequence) str), true);
            if (!Name.isValidIdentifier(decapitalizeSmart)) {
                return null;
            }
            return Name.identifier(decapitalizeSmart);
        }
    }

    @NotNull
    public static final List<Name> getPropertyNamesCandidatesByAccessorName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        if (JvmAbi.isGetterName(asString)) {
            return CollectionsKt.listOfNotNull(propertyNameByGetMethodName(name));
        }
        if (JvmAbi.isSetterName(asString)) {
            return propertyNamesBySetMethodName(name);
        }
        return BuiltinSpecialProperties.INSTANCE.getPropertyNameCandidatesBySpecialGetterName(name);
    }
}
