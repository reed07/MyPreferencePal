package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberScope.kt */
public interface MemberScope extends ResolutionScope {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: MemberScope.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final Function1<Name, Boolean> ALL_NAME_FILTER = MemberScope$Companion$ALL_NAME_FILTER$1.INSTANCE;

        private Companion() {
        }

        @NotNull
        public final Function1<Name, Boolean> getALL_NAME_FILTER() {
            return ALL_NAME_FILTER;
        }
    }

    /* compiled from: MemberScope.kt */
    public static final class DefaultImpls {
        public static void recordLookup(MemberScope memberScope, @NotNull Name name, @NotNull LookupLocation lookupLocation) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
            kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls.recordLookup(memberScope, name, lookupLocation);
        }
    }

    /* compiled from: MemberScope.kt */
    public static final class Empty extends MemberScopeImpl {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @NotNull
        public Set<Name> getFunctionNames() {
            return SetsKt.emptySet();
        }

        @NotNull
        public Set<Name> getVariableNames() {
            return SetsKt.emptySet();
        }
    }

    @NotNull
    Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation);

    @NotNull
    Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation);

    @NotNull
    Set<Name> getFunctionNames();

    @NotNull
    Set<Name> getVariableNames();
}
