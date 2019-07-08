package io.grpc;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class Metadata {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final AsciiMarshaller<String> ASCII_STRING_MARSHALLER = new AsciiMarshaller<String>() {
        public String parseAsciiString(String str) {
            return str;
        }

        public String toAsciiString(String str) {
            return str;
        }
    };
    static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = BaseEncoding.base64().omitPadding();
    public static final BinaryMarshaller<byte[]> BINARY_BYTE_MARSHALLER = new BinaryMarshaller<byte[]>() {
        public byte[] parseBytes(byte[] bArr) {
            return bArr;
        }

        public byte[] toBytes(byte[] bArr) {
            return bArr;
        }
    };
    private byte[][] namesAndValues;
    private int size;

    private static class AsciiKey<T> extends Key<T> {
        private final AsciiMarshaller<T> marshaller;

        private AsciiKey(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            super(str, z);
            Preconditions.checkArgument(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", (Object) str, (Object) "-bin");
            this.marshaller = (AsciiMarshaller) Preconditions.checkNotNull(asciiMarshaller, "marshaller");
        }

        /* access modifiers changed from: 0000 */
        public byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t).getBytes(Charsets.US_ASCII);
        }

        /* access modifiers changed from: 0000 */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(new String(bArr, Charsets.US_ASCII));
        }
    }

    public interface AsciiMarshaller<T> {
        T parseAsciiString(String str);

        String toAsciiString(T t);
    }

    private static class BinaryKey<T> extends Key<T> {
        private final BinaryMarshaller<T> marshaller;

        private BinaryKey(String str, BinaryMarshaller<T> binaryMarshaller) {
            boolean z = false;
            super(str, false);
            Preconditions.checkArgument(str.endsWith("-bin"), "Binary header is named %s. It must end with %s", (Object) str, (Object) "-bin");
            if (str.length() > 4) {
                z = true;
            }
            Preconditions.checkArgument(z, "empty key name");
            this.marshaller = (BinaryMarshaller) Preconditions.checkNotNull(binaryMarshaller, "marshaller is null");
        }

        /* access modifiers changed from: 0000 */
        public byte[] toBytes(T t) {
            return this.marshaller.toBytes(t);
        }

        /* access modifiers changed from: 0000 */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseBytes(bArr);
        }
    }

    public interface BinaryMarshaller<T> {
        T parseBytes(byte[] bArr);

        byte[] toBytes(T t);
    }

    @Immutable
    public static abstract class Key<T> {
        private static final BitSet VALID_T_CHARS = generateValidTChars();
        private final String name;
        private final byte[] nameBytes;
        private final String originalName;

        /* access modifiers changed from: 0000 */
        public abstract T parseBytes(byte[] bArr);

        /* access modifiers changed from: 0000 */
        public abstract byte[] toBytes(T t);

        public static <T> Key<T> of(String str, BinaryMarshaller<T> binaryMarshaller) {
            return new BinaryKey(str, binaryMarshaller);
        }

        public static <T> Key<T> of(String str, AsciiMarshaller<T> asciiMarshaller) {
            return of(str, false, asciiMarshaller);
        }

        static <T> Key<T> of(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            return new AsciiKey(str, z, asciiMarshaller);
        }

        static <T> Key<T> of(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            return new TrustedAsciiKey(str, z, trustedAsciiMarshaller);
        }

        private static BitSet generateValidTChars() {
            BitSet bitSet = new BitSet(Statements.GetOwnedFoodIdsDateDescending);
            bitSet.set(45);
            bitSet.set(95);
            bitSet.set(46);
            for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
                bitSet.set(c);
            }
            for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
                bitSet.set(c2);
            }
            return bitSet;
        }

        private static String validateName(String str, boolean z) {
            Preconditions.checkNotNull(str, "name");
            Preconditions.checkArgument(!str.isEmpty(), "token must have at least 1 tchar");
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!z || charAt != ':' || i != 0) {
                    Preconditions.checkArgument(VALID_T_CHARS.get(charAt), "Invalid character '%s' in key name '%s'", charAt, (Object) str);
                }
            }
            return str;
        }

        private Key(String str, boolean z) {
            this.originalName = (String) Preconditions.checkNotNull(str, "name");
            this.name = validateName(this.originalName.toLowerCase(Locale.ROOT), z);
            this.nameBytes = this.name.getBytes(Charsets.US_ASCII);
        }

        public final String name() {
            return this.name;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public byte[] asciiName() {
            return this.nameBytes;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.name.equals(((Key) obj).name);
        }

        public final int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Key{name='");
            sb.append(this.name);
            sb.append("'}");
            return sb.toString();
        }
    }

    private static final class TrustedAsciiKey<T> extends Key<T> {
        private final TrustedAsciiMarshaller<T> marshaller;

        private TrustedAsciiKey(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            super(str, z);
            Preconditions.checkArgument(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", (Object) str, (Object) "-bin");
            this.marshaller = (TrustedAsciiMarshaller) Preconditions.checkNotNull(trustedAsciiMarshaller, "marshaller");
        }

        /* access modifiers changed from: 0000 */
        public byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t);
        }

        /* access modifiers changed from: 0000 */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(bArr);
        }
    }

    @Immutable
    interface TrustedAsciiMarshaller<T> {
        T parseAsciiString(byte[] bArr);

        byte[] toAsciiString(T t);
    }

    Metadata(byte[]... bArr) {
        this(bArr.length / 2, bArr);
    }

    Metadata(int i, byte[]... bArr) {
        this.size = i;
        this.namesAndValues = bArr;
    }

    private byte[] name(int i) {
        return this.namesAndValues[i * 2];
    }

    private void name(int i, byte[] bArr) {
        this.namesAndValues[i * 2] = bArr;
    }

    private byte[] value(int i) {
        return this.namesAndValues[(i * 2) + 1];
    }

    private void value(int i, byte[] bArr) {
        this.namesAndValues[(i * 2) + 1] = bArr;
    }

    private int cap() {
        byte[][] bArr = this.namesAndValues;
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    private int len() {
        return this.size * 2;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public Metadata() {
    }

    /* access modifiers changed from: 0000 */
    public int headerCount() {
        return this.size;
    }

    @Nullable
    public <T> T get(Key<T> key) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return key.parseBytes(value(i));
            }
        }
        return null;
    }

    public <T> void put(Key<T> key, T t) {
        Preconditions.checkNotNull(key, IpcUtil.KEY_CODE);
        Preconditions.checkNotNull(t, "value");
        maybeExpand();
        name(this.size, key.asciiName());
        value(this.size, key.toBytes(t));
        this.size++;
    }

    private void maybeExpand() {
        if (len() == 0 || len() == cap()) {
            expand(Math.max(len() * 2, 8));
        }
    }

    private void expand(int i) {
        byte[][] bArr = new byte[i][];
        if (!isEmpty()) {
            System.arraycopy(this.namesAndValues, 0, bArr, 0, len());
        }
        this.namesAndValues = bArr;
    }

    @ExperimentalApi
    public <T> void discardAll(Key<T> key) {
        if (!isEmpty()) {
            int i = 0;
            for (int i2 = 0; i2 < this.size; i2++) {
                if (!bytesEqual(key.asciiName(), name(i2))) {
                    name(i, name(i2));
                    value(i, value(i2));
                    i++;
                }
            }
            Arrays.fill(this.namesAndValues, i * 2, len(), null);
            this.size = i;
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public byte[][] serialize() {
        if (len() == cap()) {
            return this.namesAndValues;
        }
        byte[][] bArr = new byte[len()][];
        System.arraycopy(this.namesAndValues, 0, bArr, 0, len());
        return bArr;
    }

    public void merge(Metadata metadata) {
        if (!metadata.isEmpty()) {
            int cap = cap() - len();
            if (isEmpty() || cap < metadata.len()) {
                expand(len() + metadata.len());
            }
            System.arraycopy(metadata.namesAndValues, 0, this.namesAndValues, len(), metadata.len());
            this.size += metadata.size;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata(");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                sb.append(',');
            }
            String str = new String(name(i), Charsets.US_ASCII);
            sb.append(str);
            sb.append('=');
            if (str.endsWith("-bin")) {
                sb.append(BASE64_ENCODING_OMIT_PADDING.encode(value(i)));
            } else {
                sb.append(new String(value(i), Charsets.US_ASCII));
            }
        }
        sb.append(')');
        return sb.toString();
    }

    private boolean bytesEqual(byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }
}
