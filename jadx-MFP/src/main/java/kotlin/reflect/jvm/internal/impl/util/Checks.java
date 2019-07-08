package kotlin.reflect.jvm.internal.impl.util;

import java.util.Arrays;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.CheckResult.IllegalSignature;
import kotlin.reflect.jvm.internal.impl.util.CheckResult.SuccessCheck;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
public final class Checks {
    @NotNull
    private final Function1<FunctionDescriptor, String> additionalCheck;
    @NotNull
    private final Check[] checks;
    @Nullable
    private final Name name;
    @Nullable
    private final Collection<Name> nameList;
    @Nullable
    private final Regex regex;

    private Checks(Name name2, Regex regex2, Collection<Name> collection, Function1<? super FunctionDescriptor, String> function1, Check... checkArr) {
        this.name = name2;
        this.regex = regex2;
        this.nameList = collection;
        this.additionalCheck = function1;
        this.checks = checkArr;
    }

    public final boolean isApplicable(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        if (this.name != null && (!Intrinsics.areEqual((Object) functionDescriptor.getName(), (Object) this.name))) {
            return false;
        }
        if (this.regex != null) {
            String asString = functionDescriptor.getName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "functionDescriptor.name.asString()");
            if (!this.regex.matches(asString)) {
                return false;
            }
        }
        Collection<Name> collection = this.nameList;
        return collection == null || collection.contains(functionDescriptor.getName());
    }

    @NotNull
    public final CheckResult checkAll(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        for (Check invoke : this.checks) {
            String invoke2 = invoke.invoke(functionDescriptor);
            if (invoke2 != null) {
                return new IllegalSignature(invoke2);
            }
        }
        String str = (String) this.additionalCheck.invoke(functionDescriptor);
        if (str != null) {
            return new IllegalSignature(str);
        }
        return SuccessCheck.INSTANCE;
    }

    public /* synthetic */ Checks(Name name2, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            function1 = AnonymousClass2.INSTANCE;
        }
        this(name2, checkArr, function1);
    }

    public Checks(@NotNull Name name2, @NotNull Check[] checkArr, @NotNull Function1<? super FunctionDescriptor, String> function1) {
        Intrinsics.checkParameterIsNotNull(name2, "name");
        Intrinsics.checkParameterIsNotNull(checkArr, "checks");
        Intrinsics.checkParameterIsNotNull(function1, "additionalChecks");
        this(name2, (Regex) null, null, function1, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
    }

    public /* synthetic */ Checks(Regex regex2, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            function1 = AnonymousClass3.INSTANCE;
        }
        this(regex2, checkArr, function1);
    }

    public Checks(@NotNull Regex regex2, @NotNull Check[] checkArr, @NotNull Function1<? super FunctionDescriptor, String> function1) {
        Intrinsics.checkParameterIsNotNull(regex2, "regex");
        Intrinsics.checkParameterIsNotNull(checkArr, "checks");
        Intrinsics.checkParameterIsNotNull(function1, "additionalChecks");
        this((Name) null, regex2, null, function1, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
    }

    public /* synthetic */ Checks(Collection collection, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            function1 = AnonymousClass4.INSTANCE;
        }
        this(collection, checkArr, function1);
    }

    public Checks(@NotNull Collection<Name> collection, @NotNull Check[] checkArr, @NotNull Function1<? super FunctionDescriptor, String> function1) {
        Intrinsics.checkParameterIsNotNull(collection, "nameList");
        Intrinsics.checkParameterIsNotNull(checkArr, "checks");
        Intrinsics.checkParameterIsNotNull(function1, "additionalChecks");
        this((Name) null, (Regex) null, collection, function1, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
    }
}
