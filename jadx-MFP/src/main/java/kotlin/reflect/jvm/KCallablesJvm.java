package kotlin.reflect.jvm;

import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\",\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"value", "", "isAccessible", "Lkotlin/reflect/KCallable;", "(Lkotlin/reflect/KCallable;)Z", "setAccessible", "(Lkotlin/reflect/KCallable;Z)V", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "KCallablesJvm")
/* compiled from: KCallablesJvm.kt */
public final class KCallablesJvm {
    /* JADX WARNING: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isAccessible(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r5) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            boolean r0 = r5 instanceof kotlin.reflect.KMutableProperty
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x003d
            r0 = r5
            kotlin.reflect.KProperty r0 = (kotlin.reflect.KProperty) r0
            java.lang.reflect.Field r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r3 == 0) goto L_0x0019
            boolean r3 = r3.isAccessible()
            goto L_0x001a
        L_0x0019:
            r3 = 1
        L_0x001a:
            if (r3 == 0) goto L_0x0101
            java.lang.reflect.Method r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r0)
            if (r0 == 0) goto L_0x0027
            boolean r0 = r0.isAccessible()
            goto L_0x0028
        L_0x0027:
            r0 = 1
        L_0x0028:
            if (r0 == 0) goto L_0x0101
            kotlin.reflect.KMutableProperty r5 = (kotlin.reflect.KMutableProperty) r5
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaSetter(r5)
            if (r5 == 0) goto L_0x0037
            boolean r5 = r5.isAccessible()
            goto L_0x0038
        L_0x0037:
            r5 = 1
        L_0x0038:
            if (r5 == 0) goto L_0x0101
            r1 = 1
            goto L_0x0101
        L_0x003d:
            boolean r0 = r5 instanceof kotlin.reflect.KProperty
            if (r0 == 0) goto L_0x0062
            kotlin.reflect.KProperty r5 = (kotlin.reflect.KProperty) r5
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r5)
            if (r0 == 0) goto L_0x004e
            boolean r0 = r0.isAccessible()
            goto L_0x004f
        L_0x004e:
            r0 = 1
        L_0x004f:
            if (r0 == 0) goto L_0x0101
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r5)
            if (r5 == 0) goto L_0x005c
            boolean r5 = r5.isAccessible()
            goto L_0x005d
        L_0x005c:
            r5 = 1
        L_0x005d:
            if (r5 == 0) goto L_0x0101
            r1 = 1
            goto L_0x0101
        L_0x0062:
            boolean r0 = r5 instanceof kotlin.reflect.KProperty.Getter
            if (r0 == 0) goto L_0x008e
            r0 = r5
            kotlin.reflect.KProperty$Getter r0 = (kotlin.reflect.KProperty.Getter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 == 0) goto L_0x0078
            boolean r0 = r0.isAccessible()
            goto L_0x0079
        L_0x0078:
            r0 = 1
        L_0x0079:
            if (r0 == 0) goto L_0x0101
            kotlin.reflect.KFunction r5 = (kotlin.reflect.KFunction) r5
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r5)
            if (r5 == 0) goto L_0x0088
            boolean r5 = r5.isAccessible()
            goto L_0x0089
        L_0x0088:
            r5 = 1
        L_0x0089:
            if (r5 == 0) goto L_0x0101
            r1 = 1
            goto L_0x0101
        L_0x008e:
            boolean r0 = r5 instanceof kotlin.reflect.KMutableProperty.Setter
            if (r0 == 0) goto L_0x00b9
            r0 = r5
            kotlin.reflect.KMutableProperty$Setter r0 = (kotlin.reflect.KMutableProperty.Setter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 == 0) goto L_0x00a4
            boolean r0 = r0.isAccessible()
            goto L_0x00a5
        L_0x00a4:
            r0 = 1
        L_0x00a5:
            if (r0 == 0) goto L_0x0101
            kotlin.reflect.KFunction r5 = (kotlin.reflect.KFunction) r5
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r5)
            if (r5 == 0) goto L_0x00b4
            boolean r5 = r5.isAccessible()
            goto L_0x00b5
        L_0x00b4:
            r5 = 1
        L_0x00b5:
            if (r5 == 0) goto L_0x0101
            r1 = 1
            goto L_0x0101
        L_0x00b9:
            boolean r0 = r5 instanceof kotlin.reflect.KFunction
            if (r0 == 0) goto L_0x0102
            r0 = r5
            kotlin.reflect.KFunction r0 = (kotlin.reflect.KFunction) r0
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r0)
            if (r3 == 0) goto L_0x00cb
            boolean r3 = r3.isAccessible()
            goto L_0x00cc
        L_0x00cb:
            r3 = 1
        L_0x00cc:
            if (r3 == 0) goto L_0x0101
            kotlin.reflect.jvm.internal.KCallableImpl r5 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r5)
            r3 = 0
            if (r5 == 0) goto L_0x00e0
            kotlin.reflect.jvm.internal.calls.Caller r5 = r5.getDefaultCaller()
            if (r5 == 0) goto L_0x00e0
            java.lang.reflect.Member r5 = r5.getMember()
            goto L_0x00e1
        L_0x00e0:
            r5 = r3
        L_0x00e1:
            boolean r4 = r5 instanceof java.lang.reflect.AccessibleObject
            if (r4 != 0) goto L_0x00e6
            r5 = r3
        L_0x00e6:
            java.lang.reflect.AccessibleObject r5 = (java.lang.reflect.AccessibleObject) r5
            if (r5 == 0) goto L_0x00ef
            boolean r5 = r5.isAccessible()
            goto L_0x00f0
        L_0x00ef:
            r5 = 1
        L_0x00f0:
            if (r5 == 0) goto L_0x0101
            java.lang.reflect.Constructor r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaConstructor(r0)
            if (r5 == 0) goto L_0x00fd
            boolean r5 = r5.isAccessible()
            goto L_0x00fe
        L_0x00fd:
            r5 = 1
        L_0x00fe:
            if (r5 == 0) goto L_0x0101
            r1 = 1
        L_0x0101:
            return r1
        L_0x0102:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown callable: "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = " ("
            r1.append(r2)
            java.lang.Class r5 = r5.getClass()
            r1.append(r5)
            r5 = 41
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.KCallablesJvm.isAccessible(kotlin.reflect.KCallable):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void setAccessible(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r3, boolean r4) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            boolean r0 = r3 instanceof kotlin.reflect.KMutableProperty
            if (r0 == 0) goto L_0x002b
            r0 = r3
            kotlin.reflect.KProperty r0 = (kotlin.reflect.KProperty) r0
            java.lang.reflect.Field r1 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r1 == 0) goto L_0x0015
            r1.setAccessible(r4)
        L_0x0015:
            java.lang.reflect.Method r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r0)
            if (r0 == 0) goto L_0x001e
            r0.setAccessible(r4)
        L_0x001e:
            kotlin.reflect.KMutableProperty r3 = (kotlin.reflect.KMutableProperty) r3
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaSetter(r3)
            if (r3 == 0) goto L_0x00be
            r3.setAccessible(r4)
            goto L_0x00be
        L_0x002b:
            boolean r0 = r3 instanceof kotlin.reflect.KProperty
            if (r0 == 0) goto L_0x0045
            kotlin.reflect.KProperty r3 = (kotlin.reflect.KProperty) r3
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r3)
            if (r0 == 0) goto L_0x003a
            r0.setAccessible(r4)
        L_0x003a:
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r3)
            if (r3 == 0) goto L_0x00be
            r3.setAccessible(r4)
            goto L_0x00be
        L_0x0045:
            boolean r0 = r3 instanceof kotlin.reflect.KProperty.Getter
            if (r0 == 0) goto L_0x0065
            r0 = r3
            kotlin.reflect.KProperty$Getter r0 = (kotlin.reflect.KProperty.Getter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 == 0) goto L_0x0059
            r0.setAccessible(r4)
        L_0x0059:
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r3)
            if (r3 == 0) goto L_0x00be
            r3.setAccessible(r4)
            goto L_0x00be
        L_0x0065:
            boolean r0 = r3 instanceof kotlin.reflect.KMutableProperty.Setter
            if (r0 == 0) goto L_0x0085
            r0 = r3
            kotlin.reflect.KMutableProperty$Setter r0 = (kotlin.reflect.KMutableProperty.Setter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 == 0) goto L_0x0079
            r0.setAccessible(r4)
        L_0x0079:
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r3)
            if (r3 == 0) goto L_0x00be
            r3.setAccessible(r4)
            goto L_0x00be
        L_0x0085:
            boolean r0 = r3 instanceof kotlin.reflect.KFunction
            if (r0 == 0) goto L_0x00bf
            r0 = r3
            kotlin.reflect.KFunction r0 = (kotlin.reflect.KFunction) r0
            java.lang.reflect.Method r1 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r0)
            if (r1 == 0) goto L_0x0095
            r1.setAccessible(r4)
        L_0x0095:
            kotlin.reflect.jvm.internal.KCallableImpl r3 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r3)
            r1 = 0
            if (r3 == 0) goto L_0x00a7
            kotlin.reflect.jvm.internal.calls.Caller r3 = r3.getDefaultCaller()
            if (r3 == 0) goto L_0x00a7
            java.lang.reflect.Member r3 = r3.getMember()
            goto L_0x00a8
        L_0x00a7:
            r3 = r1
        L_0x00a8:
            boolean r2 = r3 instanceof java.lang.reflect.AccessibleObject
            if (r2 != 0) goto L_0x00ad
            r3 = r1
        L_0x00ad:
            java.lang.reflect.AccessibleObject r3 = (java.lang.reflect.AccessibleObject) r3
            if (r3 == 0) goto L_0x00b5
            r1 = 1
            r3.setAccessible(r1)
        L_0x00b5:
            java.lang.reflect.Constructor r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaConstructor(r0)
            if (r3 == 0) goto L_0x00be
            r3.setAccessible(r4)
        L_0x00be:
            return
        L_0x00bf:
            java.lang.UnsupportedOperationException r4 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown callable: "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = " ("
            r0.append(r1)
            java.lang.Class r3 = r3.getClass()
            r0.append(r3)
            r3 = 41
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4.<init>(r3)
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.KCallablesJvm.setAccessible(kotlin.reflect.KCallable, boolean):void");
    }
}
