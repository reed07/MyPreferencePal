package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.TreeTraverser;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.myfitnesspal.shared.constants.Constants.Premium;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@GwtIncompatible
@Beta
public final class Files {
    private static final SuccessorsFunction<File> FILE_TREE = new SuccessorsFunction<File>() {
        public Iterable<File> successors(File file) {
            return Files.fileTreeChildren(file);
        }
    };
    private static final TreeTraverser<File> FILE_TREE_TRAVERSER = new TreeTraverser<File>() {
        public String toString() {
            return "Files.fileTreeTraverser()";
        }

        public Iterable<File> children(File file) {
            return Files.fileTreeChildren(file);
        }
    };
    private static final int TEMP_DIR_ATTEMPTS = 10000;

    private static final class FileByteSink extends ByteSink {
        private final File file;
        private final ImmutableSet<FileWriteMode> modes;

        private FileByteSink(File file2, FileWriteMode... fileWriteModeArr) {
            this.file = (File) Preconditions.checkNotNull(file2);
            this.modes = ImmutableSet.copyOf((E[]) fileWriteModeArr);
        }

        public FileOutputStream openStream() throws IOException {
            return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Files.asByteSink(");
            sb.append(this.file);
            sb.append(", ");
            sb.append(this.modes);
            sb.append(")");
            return sb.toString();
        }
    }

    private static final class FileByteSource extends ByteSource {
        private final File file;

        private FileByteSource(File file2) {
            this.file = (File) Preconditions.checkNotNull(file2);
        }

        public FileInputStream openStream() throws IOException {
            return new FileInputStream(this.file);
        }

        public Optional<Long> sizeIfKnown() {
            if (this.file.isFile()) {
                return Optional.of(Long.valueOf(this.file.length()));
            }
            return Optional.absent();
        }

        public long size() throws IOException {
            if (this.file.isFile()) {
                return this.file.length();
            }
            throw new FileNotFoundException(this.file.toString());
        }

        public byte[] read() throws IOException {
            Closer create = Closer.create();
            try {
                FileInputStream fileInputStream = (FileInputStream) create.register(openStream());
                byte[] byteArray = ByteStreams.toByteArray(fileInputStream, fileInputStream.getChannel().size());
                create.close();
                return byteArray;
            } catch (Throwable th) {
                create.close();
                throw th;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Files.asByteSource(");
            sb.append(this.file);
            sb.append(")");
            return sb.toString();
        }
    }

    private enum FilePredicate implements Predicate<File> {
        IS_DIRECTORY {
            public String toString() {
                return "Files.isDirectory()";
            }

            public boolean apply(File file) {
                return file.isDirectory();
            }
        },
        IS_FILE {
            public String toString() {
                return "Files.isFile()";
            }

            public boolean apply(File file) {
                return file.isFile();
            }
        }
    }

    private Files() {
    }

    public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file);
    }

    public static ByteSink asByteSink(File file, FileWriteMode... fileWriteModeArr) {
        return new FileByteSink(file, fileWriteModeArr);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode... fileWriteModeArr) {
        return asByteSink(file, fileWriteModeArr).asCharSink(charset);
    }

    public static byte[] toByteArray(File file) throws IOException {
        return asByteSource(file).read();
    }

    @Deprecated
    public static String toString(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).read();
    }

    public static void write(byte[] bArr, File file) throws IOException {
        asByteSink(file, new FileWriteMode[0]).write(bArr);
    }

    @Deprecated
    public static void write(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, new FileWriteMode[0]).write(charSequence);
    }

    public static void copy(File file, OutputStream outputStream) throws IOException {
        asByteSource(file).copyTo(outputStream);
    }

    public static void copy(File file, File file2) throws IOException {
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", (Object) file, (Object) file2);
        asByteSource(file).copyTo(asByteSink(file2, new FileWriteMode[0]));
    }

    @Deprecated
    public static void copy(File file, Charset charset, Appendable appendable) throws IOException {
        asCharSource(file, charset).copyTo(appendable);
    }

    @Deprecated
    public static void append(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, FileWriteMode.APPEND).write(charSequence);
    }

    public static boolean equal(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        if (file == file2 || file.equals(file2)) {
            return true;
        }
        long length = file.length();
        long length2 = file2.length();
        if (length == 0 || length2 == 0 || length == length2) {
            return asByteSource(file).contentEquals(asByteSource(file2));
        }
        return false;
    }

    public static File createTempDir() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append("-");
        String sb2 = sb.toString();
        for (int i = 0; i < 10000; i++) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(i);
            File file2 = new File(file, sb3.toString());
            if (file2.mkdir()) {
                return file2;
            }
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Failed to create directory within 10000 attempts (tried ");
        sb4.append(sb2);
        sb4.append("0 to ");
        sb4.append(sb2);
        sb4.append(Premium.MACROS_MAXIMUM);
        sb4.append(')');
        throw new IllegalStateException(sb4.toString());
    }

    public static void touch(File file) throws IOException {
        Preconditions.checkNotNull(file);
        if (!file.createNewFile() && !file.setLastModified(System.currentTimeMillis())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to update modification time of ");
            sb.append(file);
            throw new IOException(sb.toString());
        }
    }

    public static void createParentDirs(File file) throws IOException {
        Preconditions.checkNotNull(file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to create parent directories of ");
                sb.append(file);
                throw new IOException(sb.toString());
            }
        }
    }

    public static void move(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", (Object) file, (Object) file2);
        if (!file.renameTo(file2)) {
            copy(file, file2);
            if (file.delete()) {
                return;
            }
            if (!file2.delete()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to delete ");
                sb.append(file2);
                throw new IOException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to delete ");
            sb2.append(file);
            throw new IOException(sb2.toString());
        }
    }

    @Deprecated
    public static String readFirstLine(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).readFirstLine();
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        return (List) asCharSource(file, charset).readLines(new LineProcessor<List<String>>() {
            final List<String> result = Lists.newArrayList();

            public boolean processLine(String str) {
                this.result.add(str);
                return true;
            }

            public List<String> getResult() {
                return this.result;
            }
        });
    }

    @CanIgnoreReturnValue
    @Deprecated
    public static <T> T readLines(File file, Charset charset, LineProcessor<T> lineProcessor) throws IOException {
        return asCharSource(file, charset).readLines(lineProcessor);
    }

    @CanIgnoreReturnValue
    @Deprecated
    public static <T> T readBytes(File file, ByteProcessor<T> byteProcessor) throws IOException {
        return asByteSource(file).read(byteProcessor);
    }

    @Deprecated
    public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
        return asByteSource(file).hash(hashFunction);
    }

    public static MappedByteBuffer map(File file) throws IOException {
        Preconditions.checkNotNull(file);
        return map(file, MapMode.READ_ONLY);
    }

    public static MappedByteBuffer map(File file, MapMode mapMode) throws IOException {
        return mapInternal(file, mapMode, -1);
    }

    public static MappedByteBuffer map(File file, MapMode mapMode, long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "size (%s) may not be negative", j);
        return mapInternal(file, mapMode, j);
    }

    private static MappedByteBuffer mapInternal(File file, MapMode mapMode, long j) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mapMode);
        Closer create = Closer.create();
        try {
            FileChannel fileChannel = (FileChannel) create.register(((RandomAccessFile) create.register(new RandomAccessFile(file, mapMode == MapMode.READ_ONLY ? "r" : "rw"))).getChannel());
            if (j == -1) {
                j = fileChannel.size();
            }
            MappedByteBuffer map = fileChannel.map(mapMode, 0, j);
            create.close();
            return map;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        if (r3.equals(".") != false) goto L_0x0053;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0023 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String simplifyPath(java.lang.String r9) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r9)
            int r0 = r9.length()
            if (r0 != 0) goto L_0x000c
            java.lang.String r9 = "."
            return r9
        L_0x000c:
            r0 = 47
            com.google.common.base.Splitter r1 = com.google.common.base.Splitter.on(r0)
            com.google.common.base.Splitter r1 = r1.omitEmptyStrings()
            java.lang.Iterable r1 = r1.split(r9)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0023:
            boolean r3 = r1.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x0082
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            r5 = -1
            int r6 = r3.hashCode()
            r7 = 46
            r8 = 1
            if (r6 == r7) goto L_0x0049
            r4 = 1472(0x5c0, float:2.063E-42)
            if (r6 == r4) goto L_0x003f
            goto L_0x0052
        L_0x003f:
            java.lang.String r4 = ".."
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0052
            r4 = 1
            goto L_0x0053
        L_0x0049:
            java.lang.String r6 = "."
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r4 = -1
        L_0x0053:
            switch(r4) {
                case 0: goto L_0x0023;
                case 1: goto L_0x005a;
                default: goto L_0x0056;
            }
        L_0x0056:
            r2.add(r3)
            goto L_0x0023
        L_0x005a:
            int r3 = r2.size()
            if (r3 <= 0) goto L_0x007c
            int r3 = r2.size()
            int r3 = r3 - r8
            java.lang.Object r3 = r2.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = ".."
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x007c
            int r3 = r2.size()
            int r3 = r3 - r8
            r2.remove(r3)
            goto L_0x0023
        L_0x007c:
            java.lang.String r3 = ".."
            r2.add(r3)
            goto L_0x0023
        L_0x0082:
            com.google.common.base.Joiner r1 = com.google.common.base.Joiner.on(r0)
            java.lang.String r1 = r1.join(r2)
            char r9 = r9.charAt(r4)
            if (r9 != r0) goto L_0x00a1
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "/"
            r9.append(r0)
            r9.append(r1)
            java.lang.String r1 = r9.toString()
        L_0x00a1:
            java.lang.String r9 = "/../"
            boolean r9 = r1.startsWith(r9)
            if (r9 == 0) goto L_0x00af
            r9 = 3
            java.lang.String r1 = r1.substring(r9)
            goto L_0x00a1
        L_0x00af:
            java.lang.String r9 = "/.."
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x00ba
            java.lang.String r1 = "/"
            goto L_0x00c4
        L_0x00ba:
            java.lang.String r9 = ""
            boolean r9 = r9.equals(r1)
            if (r9 == 0) goto L_0x00c4
            java.lang.String r1 = "."
        L_0x00c4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.Files.simplifyPath(java.lang.String):java.lang.String");
    }

    public static String getFileExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    public static String getNameWithoutExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? name : name.substring(0, lastIndexOf);
    }

    @Deprecated
    static TreeTraverser<File> fileTreeTraverser() {
        return FILE_TREE_TRAVERSER;
    }

    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    /* access modifiers changed from: private */
    public static Iterable<File> fileTreeChildren(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                return Collections.unmodifiableList(Arrays.asList(listFiles));
            }
        }
        return Collections.emptyList();
    }

    public static Predicate<File> isDirectory() {
        return FilePredicate.IS_DIRECTORY;
    }

    public static Predicate<File> isFile() {
        return FilePredicate.IS_FILE;
    }
}