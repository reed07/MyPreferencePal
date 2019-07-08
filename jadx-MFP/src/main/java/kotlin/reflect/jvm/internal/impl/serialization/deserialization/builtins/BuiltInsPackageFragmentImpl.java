package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.Closeable;
import java.io.InputStream;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment;
import kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsBinaryVersion;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragmentImpl;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInsPackageFragmentImpl.kt */
public final class BuiltInsPackageFragmentImpl extends DeserializedPackageFragmentImpl implements BuiltInsPackageFragment {
    public static final Companion Companion = new Companion(null);

    /* compiled from: BuiltInsPackageFragmentImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BuiltInsPackageFragmentImpl create(@NotNull FqName fqName, @NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull InputStream inputStream) {
            Throwable th;
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
            Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
            Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
            Closeable closeable = inputStream;
            Throwable th2 = null;
            try {
                InputStream inputStream2 = (InputStream) closeable;
                BuiltInsBinaryVersion readFrom = BuiltInsBinaryVersion.Companion.readFrom(inputStream2);
                if (readFrom == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("version");
                }
                if (readFrom.isCompatible()) {
                    PackageFragment parseFrom = PackageFragment.parseFrom(inputStream2, BuiltInSerializerProtocol.INSTANCE.getExtensionRegistry());
                    CloseableKt.closeFinally(closeable, th2);
                    Intrinsics.checkExpressionValueIsNotNull(parseFrom, "proto");
                    BuiltInsPackageFragmentImpl builtInsPackageFragmentImpl = new BuiltInsPackageFragmentImpl(fqName, storageManager, moduleDescriptor, parseFrom, readFrom, null);
                    return builtInsPackageFragmentImpl;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Kotlin built-in definition format version is not supported: ");
                sb.append("expected ");
                sb.append(BuiltInsBinaryVersion.INSTANCE);
                sb.append(", actual ");
                sb.append(readFrom);
                sb.append(". ");
                sb.append("Please update Kotlin");
                throw new UnsupportedOperationException(sb.toString());
            } catch (Throwable th3) {
                CloseableKt.closeFinally(closeable, th);
                throw th3;
            }
        }
    }

    public /* synthetic */ BuiltInsPackageFragmentImpl(@NotNull FqName fqName, @NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull PackageFragment packageFragment, @NotNull BuiltInsBinaryVersion builtInsBinaryVersion, DefaultConstructorMarker defaultConstructorMarker) {
        this(fqName, storageManager, moduleDescriptor, packageFragment, builtInsBinaryVersion);
    }

    private BuiltInsPackageFragmentImpl(FqName fqName, StorageManager storageManager, ModuleDescriptor moduleDescriptor, PackageFragment packageFragment, BuiltInsBinaryVersion builtInsBinaryVersion) {
        super(fqName, storageManager, moduleDescriptor, packageFragment, builtInsBinaryVersion, null);
    }
}
