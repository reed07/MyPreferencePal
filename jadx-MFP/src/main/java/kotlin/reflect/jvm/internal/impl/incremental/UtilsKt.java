package kotlin.reflect.jvm.internal.impl.incremental;

import com.myfitnesspal.shared.constants.SharedConstants.Params;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LocationInfo;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.incremental.components.Position;
import kotlin.reflect.jvm.internal.impl.incremental.components.ScopeKind;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import org.jetbrains.annotations.NotNull;

/* compiled from: utils.kt */
public final class UtilsKt {
    public static final void record(@NotNull LookupTracker lookupTracker, @NotNull LookupLocation lookupLocation, @NotNull ClassDescriptor classDescriptor, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(lookupTracker, "receiver$0");
        Intrinsics.checkParameterIsNotNull(lookupLocation, Params.FROM);
        Intrinsics.checkParameterIsNotNull(classDescriptor, "scopeOwner");
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (lookupTracker != DO_NOTHING.INSTANCE) {
            LocationInfo location = lookupLocation.getLocation();
            if (location != null) {
                Position position = lookupTracker.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION();
                String filePath = location.getFilePath();
                String asString = DescriptorUtils.getFqName(classDescriptor).asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "DescriptorUtils.getFqName(scopeOwner).asString()");
                ScopeKind scopeKind = ScopeKind.CLASSIFIER;
                String asString2 = name.asString();
                Intrinsics.checkExpressionValueIsNotNull(asString2, "name.asString()");
                lookupTracker.record(filePath, position, asString, scopeKind, asString2);
            }
        }
    }

    public static final void record(@NotNull LookupTracker lookupTracker, @NotNull LookupLocation lookupLocation, @NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(lookupTracker, "receiver$0");
        Intrinsics.checkParameterIsNotNull(lookupLocation, Params.FROM);
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor, "scopeOwner");
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = packageFragmentDescriptor.getFqName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "scopeOwner.fqName.asString()");
        String asString2 = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "name.asString()");
        recordPackageLookup(lookupTracker, lookupLocation, asString, asString2);
    }

    public static final void recordPackageLookup(@NotNull LookupTracker lookupTracker, @NotNull LookupLocation lookupLocation, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(lookupTracker, "receiver$0");
        Intrinsics.checkParameterIsNotNull(lookupLocation, Params.FROM);
        Intrinsics.checkParameterIsNotNull(str, "packageFqName");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        if (lookupTracker != DO_NOTHING.INSTANCE) {
            LocationInfo location = lookupLocation.getLocation();
            if (location != null) {
                lookupTracker.record(location.getFilePath(), lookupTracker.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION(), str, ScopeKind.PACKAGE, str2);
            }
        }
    }
}
