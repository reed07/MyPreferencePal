package kotlin.reflect.jvm.internal.components;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R6\u0010\u0005\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006j\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b`\tX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/PackagePartProvider;", "classLoader", "Ljava/lang/ClassLoader;", "(Ljava/lang/ClassLoader;)V", "packageParts", "Ljava/util/HashMap;", "", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/HashMap;", "visitedModules", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "findPackageParts", "", "packageFqName", "getAnnotationsOnBinaryModule", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "moduleName", "registerModule", "", "EmptyEnumeration", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: RuntimePackagePartProvider.kt */
public final class RuntimePackagePartProvider implements PackagePartProvider {
    private final ClassLoader classLoader;
    private final HashMap<String, LinkedHashSet<String>> packageParts = new HashMap<>();
    private final HashSet<String> visitedModules = new HashSet<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider$EmptyEnumeration;", "Ljava/util/Enumeration;", "", "()V", "hasMoreElements", "", "nextElement", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RuntimePackagePartProvider.kt */
    private static final class EmptyEnumeration implements Enumeration {
        public static final EmptyEnumeration INSTANCE = new EmptyEnumeration();

        public boolean hasMoreElements() {
            return false;
        }

        private EmptyEnumeration() {
        }

        @NotNull
        public Void nextElement() {
            throw new NoSuchElementException();
        }
    }

    public RuntimePackagePartProvider(@NotNull ClassLoader classLoader2) {
        Intrinsics.checkParameterIsNotNull(classLoader2, "classLoader");
        this.classLoader = classLoader2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = kotlin.reflect.jvm.internal.components.RuntimePackagePartProvider.EmptyEnumeration.INSTANCE;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void registerModule(@org.jetbrains.annotations.NotNull java.lang.String r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = "moduleName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)     // Catch:{ all -> 0x00bc }
            java.util.HashSet<java.lang.String> r0 = r8.visitedModules     // Catch:{ all -> 0x00bc }
            boolean r0 = r0.add(r9)     // Catch:{ all -> 0x00bc }
            if (r0 != 0) goto L_0x0010
            monitor-exit(r8)
            return
        L_0x0010:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bc }
            r0.<init>()     // Catch:{ all -> 0x00bc }
            java.lang.String r1 = "META-INF/"
            r0.append(r1)     // Catch:{ all -> 0x00bc }
            r0.append(r9)     // Catch:{ all -> 0x00bc }
            java.lang.String r9 = ".kotlin_module"
            r0.append(r9)     // Catch:{ all -> 0x00bc }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x00bc }
            java.lang.ClassLoader r0 = r8.classLoader     // Catch:{ IOException -> 0x002d }
            java.util.Enumeration r0 = r0.getResources(r9)     // Catch:{ IOException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            kotlin.reflect.jvm.internal.components.RuntimePackagePartProvider$EmptyEnumeration r0 = kotlin.reflect.jvm.internal.components.RuntimePackagePartProvider.EmptyEnumeration.INSTANCE     // Catch:{ all -> 0x00bc }
            java.util.Enumeration r0 = (java.util.Enumeration) r0     // Catch:{ all -> 0x00bc }
        L_0x0031:
            java.lang.String r1 = "resources"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)     // Catch:{ all -> 0x00bc }
            java.util.Iterator r0 = kotlin.collections.CollectionsKt.iterator(r0)     // Catch:{ all -> 0x00bc }
        L_0x003a:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x00bc }
            if (r1 == 0) goto L_0x00ba
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x00bc }
            java.net.URL r1 = (java.net.URL) r1     // Catch:{ all -> 0x00bc }
            java.io.InputStream r1 = r1.openStream()     // Catch:{ UnsupportedOperationException -> 0x00b6, Exception -> 0x003a }
            if (r1 == 0) goto L_0x003a
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ UnsupportedOperationException -> 0x00b6, Exception -> 0x003a }
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ UnsupportedOperationException -> 0x00b6, Exception -> 0x003a }
            r3 = r1
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ Throwable -> 0x00b0 }
            kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping$Companion r4 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.Companion     // Catch:{ Throwable -> 0x00b0 }
            byte[] r3 = kotlin.io.ByteStreamsKt.readBytes(r3)     // Catch:{ Throwable -> 0x00b0 }
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration$Default r5 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration.Default.INSTANCE     // Catch:{ Throwable -> 0x00b0 }
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration r5 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration) r5     // Catch:{ Throwable -> 0x00b0 }
            kotlin.reflect.jvm.internal.components.RuntimePackagePartProvider$registerModule$1$mapping$1 r6 = kotlin.reflect.jvm.internal.components.RuntimePackagePartProvider$registerModule$1$mapping$1.INSTANCE     // Catch:{ Throwable -> 0x00b0 }
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6     // Catch:{ Throwable -> 0x00b0 }
            kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping r3 = kotlin.reflect.jvm.internal.impl.load.kotlin.ModuleMappingUtilKt.loadModuleMapping(r4, r3, r9, r5, r6)     // Catch:{ Throwable -> 0x00b0 }
            java.util.Map r3 = r3.getPackageFqName2Parts()     // Catch:{ Throwable -> 0x00b0 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ Throwable -> 0x00b0 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x00b0 }
        L_0x0072:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x00b0 }
            if (r4 == 0) goto L_0x00a8
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x00b0 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Throwable -> 0x00b0 }
            java.lang.Object r5 = r4.getKey()     // Catch:{ Throwable -> 0x00b0 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x00b0 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ Throwable -> 0x00b0 }
            kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.PackageParts r4 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.PackageParts) r4     // Catch:{ Throwable -> 0x00b0 }
            java.util.HashMap<java.lang.String, java.util.LinkedHashSet<java.lang.String>> r6 = r8.packageParts     // Catch:{ Throwable -> 0x00b0 }
            java.util.Map r6 = (java.util.Map) r6     // Catch:{ Throwable -> 0x00b0 }
            java.lang.Object r7 = r6.get(r5)     // Catch:{ Throwable -> 0x00b0 }
            if (r7 != 0) goto L_0x009c
            java.util.LinkedHashSet r7 = new java.util.LinkedHashSet     // Catch:{ Throwable -> 0x00b0 }
            r7.<init>()     // Catch:{ Throwable -> 0x00b0 }
            r6.put(r5, r7)     // Catch:{ Throwable -> 0x00b0 }
        L_0x009c:
            java.util.LinkedHashSet r7 = (java.util.LinkedHashSet) r7     // Catch:{ Throwable -> 0x00b0 }
            java.util.Set r4 = r4.getParts()     // Catch:{ Throwable -> 0x00b0 }
            java.util.Collection r4 = (java.util.Collection) r4     // Catch:{ Throwable -> 0x00b0 }
            r7.addAll(r4)     // Catch:{ Throwable -> 0x00b0 }
            goto L_0x0072
        L_0x00a8:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00b0 }
            kotlin.io.CloseableKt.closeFinally(r1, r2)     // Catch:{ UnsupportedOperationException -> 0x00b6, Exception -> 0x003a }
            goto L_0x003a
        L_0x00ae:
            r3 = move-exception
            goto L_0x00b2
        L_0x00b0:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x00ae }
        L_0x00b2:
            kotlin.io.CloseableKt.closeFinally(r1, r2)     // Catch:{ UnsupportedOperationException -> 0x00b6, Exception -> 0x003a }
            throw r3     // Catch:{ UnsupportedOperationException -> 0x00b6, Exception -> 0x003a }
        L_0x00b6:
            r9 = move-exception
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ all -> 0x00bc }
            throw r9     // Catch:{ all -> 0x00bc }
        L_0x00ba:
            monitor-exit(r8)
            return
        L_0x00bc:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.components.RuntimePackagePartProvider.registerModule(java.lang.String):void");
    }

    @NotNull
    public synchronized List<String> findPackageParts(@NotNull String str) {
        List<String> list;
        Intrinsics.checkParameterIsNotNull(str, "packageFqName");
        LinkedHashSet linkedHashSet = (LinkedHashSet) this.packageParts.get(str);
        list = linkedHashSet != null ? CollectionsKt.toList(linkedHashSet) : null;
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        return list;
    }
}
