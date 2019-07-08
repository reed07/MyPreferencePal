package kotlin.reflect.jvm.internal.impl.metadata.builtins;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInsBinaryVersion.kt */
public final class BuiltInsBinaryVersion extends BinaryVersion {
    public static final Companion Companion = new Companion(null);
    @NotNull
    @JvmField
    public static final BuiltInsBinaryVersion INSTANCE = new BuiltInsBinaryVersion(1, 0, 6);
    @NotNull
    @JvmField
    public static final BuiltInsBinaryVersion INVALID_VERSION = new BuiltInsBinaryVersion(new int[0]);

    /* compiled from: BuiltInsBinaryVersion.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BuiltInsBinaryVersion readFrom(@NotNull InputStream inputStream) {
            Intrinsics.checkParameterIsNotNull(inputStream, "stream");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            Iterable intRange = new IntRange(1, dataInputStream.readInt());
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator it = intRange.iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                arrayList.add(Integer.valueOf(dataInputStream.readInt()));
            }
            int[] intArray = CollectionsKt.toIntArray((List) arrayList);
            return new BuiltInsBinaryVersion(Arrays.copyOf(intArray, intArray.length));
        }
    }

    public BuiltInsBinaryVersion(@NotNull int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "numbers");
        super(Arrays.copyOf(iArr, iArr.length));
    }

    public boolean isCompatible() {
        return isCompatibleTo(INSTANCE);
    }
}
