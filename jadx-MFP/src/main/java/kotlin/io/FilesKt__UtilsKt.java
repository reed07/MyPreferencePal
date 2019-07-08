package kotlin.io;

import com.facebook.appevents.codeless.internal.Constants;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: Utils.kt */
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    @NotNull
    public static /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempDir(str, str2, file);
    }

    @NotNull
    public static final File createTempDir(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        createTempFile.delete();
        if (createTempFile.mkdir()) {
            Intrinsics.checkExpressionValueIsNotNull(createTempFile, "dir");
            return createTempFile;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to create temporary directory ");
        sb.append(createTempFile);
        sb.append('.');
        throw new IOException(sb.toString());
    }

    @NotNull
    public static /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempFile(str, str2, file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        Intrinsics.checkExpressionValueIsNotNull(createTempFile, "File.createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    @NotNull
    public static final String getExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        return StringsKt.substringAfterLast(name, '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        if (File.separatorChar != '/') {
            String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "path");
            return StringsKt.replace$default(path, File.separatorChar, '/', false, 4, (Object) null);
        }
        String path2 = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path2, "path");
        return path2;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        return StringsKt.substringBeforeLast$default(name, ".", (String) null, 2, (Object) null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return relativeStringOrNull$FilesKt__UtilsKt;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("this and base files have different roots: ");
        sb.append(file);
        sb.append(" and ");
        sb.append(file2);
        sb.append('.');
        throw new IllegalArgumentException(sb.toString());
    }

    @NotNull
    public static final File relativeTo(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        return new File(FilesKt.toRelativeString(file, file2));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        return relativeStringOrNull$FilesKt__UtilsKt != null ? new File(relativeStringOrNull$FilesKt__UtilsKt) : file;
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return new File(relativeStringOrNull$FilesKt__UtilsKt);
        }
        return null;
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(@NotNull File file, File file2) {
        FilePathComponents normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(FilesKt.toComponents(file));
        FilePathComponents normalize$FilesKt__UtilsKt2 = normalize$FilesKt__UtilsKt(FilesKt.toComponents(file2));
        if (!Intrinsics.areEqual((Object) normalize$FilesKt__UtilsKt.getRoot(), (Object) normalize$FilesKt__UtilsKt2.getRoot())) {
            return null;
        }
        int size = normalize$FilesKt__UtilsKt2.getSize();
        int size2 = normalize$FilesKt__UtilsKt.getSize();
        int i = 0;
        int min = Math.min(size2, size);
        while (i < min && Intrinsics.areEqual((Object) (File) normalize$FilesKt__UtilsKt.getSegments().get(i), (Object) (File) normalize$FilesKt__UtilsKt2.getSegments().get(i))) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = size - 1;
        if (i2 >= i) {
            while (!Intrinsics.areEqual((Object) ((File) normalize$FilesKt__UtilsKt2.getSegments().get(i2)).getName(), (Object) "..")) {
                sb.append("..");
                if (i2 != i) {
                    sb.append(File.separatorChar);
                }
                if (i2 != i) {
                    i2--;
                }
            }
            return null;
        }
        if (i < size2) {
            if (i < size) {
                sb.append(File.separatorChar);
            }
            Iterable drop = CollectionsKt.drop(normalize$FilesKt__UtilsKt.getSegments(), i);
            Appendable appendable = sb;
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            CollectionsKt.joinTo$default(drop, appendable, str, null, null, 0, null, null, PacketTypes.UnfriendUser, null);
        }
        return sb.toString();
    }

    @NotNull
    public static /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return FilesKt.copyTo(file, file2, z, i);
    }

    @NotNull
    public static final File copyTo(@NotNull File file, @NotNull File file2, boolean z, int i) {
        Closeable fileOutputStream;
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "target");
        if (file.exists()) {
            if (file2.exists()) {
                boolean z2 = true;
                if (z && file2.delete()) {
                    z2 = false;
                }
                if (z2) {
                    throw new FileAlreadyExistsException(file, file2, "The destination file already exists.");
                }
            }
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                Closeable fileInputStream = new FileInputStream(file);
                Throwable th = null;
                try {
                    FileInputStream fileInputStream2 = (FileInputStream) fileInputStream;
                    fileOutputStream = new FileOutputStream(file2);
                    Throwable th2 = null;
                    ByteStreamsKt.copyTo(fileInputStream2, (FileOutputStream) fileOutputStream, i);
                    CloseableKt.closeFinally(fileOutputStream, th2);
                    CloseableKt.closeFinally(fileInputStream, th);
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(fileInputStream, th4);
                        throw th5;
                    }
                }
            } else if (!file2.mkdirs()) {
                throw new FileSystemException(file, file2, "Failed to create target directory.");
            }
            return file2;
        }
        NoSuchFileException noSuchFileException = new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
        throw noSuchFileException;
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function2 = FilesKt__UtilsKt$copyRecursively$1.INSTANCE;
        }
        return FilesKt.copyRecursively(file, file2, z, function2);
    }

    public static final boolean copyRecursively(@NotNull File file, @NotNull File file2, boolean z, @NotNull Function2<? super File, ? super IOException, ? extends OnErrorAction> function2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "target");
        Intrinsics.checkParameterIsNotNull(function2, "onError");
        boolean z2 = true;
        if (!file.exists()) {
            NoSuchFileException noSuchFileException = new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
            if (((OnErrorAction) function2.invoke(file, noSuchFileException)) == OnErrorAction.TERMINATE) {
                z2 = false;
            }
            return z2;
        }
        try {
            Iterator it = FilesKt.walkTopDown(file).onFail(new FilesKt__UtilsKt$copyRecursively$2(function2)).iterator();
            while (it.hasNext()) {
                File file3 = (File) it.next();
                if (!file3.exists()) {
                    NoSuchFileException noSuchFileException2 = new NoSuchFileException(file3, null, "The source file doesn't exist.", 2, null);
                    if (((OnErrorAction) function2.invoke(file3, noSuchFileException2)) == OnErrorAction.TERMINATE) {
                        return false;
                    }
                } else {
                    File file4 = new File(file2, FilesKt.toRelativeString(file3, file));
                    if (file4.exists() && (!file3.isDirectory() || !file4.isDirectory())) {
                        boolean z3 = !z ? true : file4.isDirectory() ? !FilesKt.deleteRecursively(file4) : !file4.delete();
                        if (z3) {
                            if (((OnErrorAction) function2.invoke(file4, new FileAlreadyExistsException(file3, file4, "The destination file already exists."))) == OnErrorAction.TERMINATE) {
                                return false;
                            }
                        }
                    }
                    if (file3.isDirectory()) {
                        file4.mkdirs();
                    } else if (FilesKt.copyTo$default(file3, file4, z, 0, 4, null).length() != file3.length() && ((OnErrorAction) function2.invoke(file3, new IOException("Source file wasn't copied completely, length of destination file differs."))) == OnErrorAction.TERMINATE) {
                        return false;
                    }
                }
            }
            return true;
        } catch (TerminateException unused) {
            return false;
        }
    }

    public static final boolean deleteRecursively(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        boolean z = true;
        for (File file2 : FilesKt.walkBottomUp(file)) {
            z = (file2.delete() || !file2.exists()) && z;
        }
        return z;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        FilePathComponents components = FilesKt.toComponents(file);
        FilePathComponents components2 = FilesKt.toComponents(file2);
        boolean z = false;
        if (!Intrinsics.areEqual((Object) components.getRoot(), (Object) components2.getRoot())) {
            return false;
        }
        if (components.getSize() >= components2.getSize()) {
            z = components.getSegments().subList(0, components2.getSize()).equals(components2.getSegments());
        }
        return z;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return FilesKt.startsWith(file, new File(str));
    }

    public static final boolean endsWith(@NotNull File file, @NotNull File file2) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        FilePathComponents components = FilesKt.toComponents(file);
        FilePathComponents components2 = FilesKt.toComponents(file2);
        if (components2.isRooted()) {
            return Intrinsics.areEqual((Object) file, (Object) file2);
        }
        int size = components.getSize() - components2.getSize();
        if (size < 0) {
            z = false;
        } else {
            z = components.getSegments().subList(size, components.getSize()).equals(components2.getSegments());
        }
        return z;
    }

    public static final boolean endsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return FilesKt.endsWith(file, new File(str));
    }

    @NotNull
    public static final File normalize(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        FilePathComponents components = FilesKt.toComponents(file);
        File root = components.getRoot();
        Iterable normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(components.getSegments());
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        return FilesKt.resolve(root, CollectionsKt.joinToString$default(normalize$FilesKt__UtilsKt, str, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(@NotNull FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.getRoot(), normalize$FilesKt__UtilsKt(filePathComponents.getSegments()));
    }

    private static final List<File> normalize$FilesKt__UtilsKt(@NotNull List<? extends File> list) {
        List<File> arrayList = new ArrayList<>(list.size());
        for (File file : list) {
            String name = file.getName();
            if (name != null) {
                int hashCode = name.hashCode();
                if (hashCode != 46) {
                    if (hashCode == 1472 && name.equals("..")) {
                        if (arrayList.isEmpty() || !(!Intrinsics.areEqual((Object) ((File) CollectionsKt.last(arrayList)).getName(), (Object) ".."))) {
                            arrayList.add(file);
                        } else {
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                } else if (name.equals(".")) {
                }
            }
            arrayList.add(file);
        }
        return arrayList;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull File file2) {
        File file3;
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, Constants.PATH_TYPE_RELATIVE);
        if (FilesKt.isRooted(file2)) {
            return file2;
        }
        String file4 = file.toString();
        Intrinsics.checkExpressionValueIsNotNull(file4, "this.toString()");
        CharSequence charSequence = file4;
        if ((charSequence.length() == 0) || StringsKt.endsWith$default(charSequence, File.separatorChar, false, 2, (Object) null)) {
            StringBuilder sb = new StringBuilder();
            sb.append(file4);
            sb.append(file2);
            file3 = new File(sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(file4);
            sb2.append(File.separatorChar);
            sb2.append(file2);
            file3 = new File(sb2.toString());
        }
        return file3;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, Constants.PATH_TYPE_RELATIVE);
        return FilesKt.resolve(file, new File(str));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, Constants.PATH_TYPE_RELATIVE);
        FilePathComponents components = FilesKt.toComponents(file);
        return FilesKt.resolve(FilesKt.resolve(components.getRoot(), components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1)), file2);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, Constants.PATH_TYPE_RELATIVE);
        return FilesKt.resolveSibling(file, new File(str));
    }
}
