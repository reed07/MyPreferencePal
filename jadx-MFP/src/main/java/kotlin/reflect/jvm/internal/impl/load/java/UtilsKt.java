package kotlin.reflect.jvm.internal.impl.load.java;

/* compiled from: utils.kt */
public final class UtilsKt {
    /* JADX WARNING: type inference failed for: r5v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v4, types: [java.lang.Double] */
    /* JADX WARNING: type inference failed for: r5v5, types: [java.lang.Float] */
    /* JADX WARNING: type inference failed for: r5v6, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r5v7, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r5v8, types: [java.lang.Short] */
    /* JADX WARNING: type inference failed for: r5v9, types: [java.lang.Byte] */
    /* JADX WARNING: type inference failed for: r5v11, types: [java.lang.Character] */
    /* JADX WARNING: type inference failed for: r5v12, types: [java.lang.Boolean] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 8 */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue lexicalCastFrom(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r4, @org.jetbrains.annotations.NotNull java.lang.String r5) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = r4.getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r0 = r0.getDeclarationDescriptor()
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            r2 = 0
            if (r1 == 0) goto L_0x004d
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r1 = r0.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r3 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ENUM_CLASS
            if (r1 != r3) goto L_0x004d
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r4 = r0.getUnsubstitutedInnerClassesScope()
            kotlin.reflect.jvm.internal.impl.name.Name r5 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r5)
            java.lang.String r0 = "Name.identifier(value)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation r0 = kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation.FROM_BACKEND
            kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation r0 = (kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation) r0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r4 = r4.getContributedClassifier(r5, r0)
            boolean r5 = r4 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r5 == 0) goto L_0x004c
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r4
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r5 = r4.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r0 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ENUM_ENTRY
            if (r5 != r0) goto L_0x004c
            kotlin.reflect.jvm.internal.impl.load.java.EnumEntry r5 = new kotlin.reflect.jvm.internal.impl.load.java.EnumEntry
            r5.<init>(r4)
            r2 = r5
            kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue r2 = (kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue) r2
        L_0x004c:
            return r2
        L_0x004d:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.makeNotNullable(r4)
            kotlin.reflect.jvm.internal.impl.utils.NumberWithRadix r0 = kotlin.reflect.jvm.internal.impl.utils.NumbersKt.extractRadix(r5)
            java.lang.String r1 = r0.component1()
            int r0 = r0.component2()
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isBoolean(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r3 == 0) goto L_0x006c
            boolean r4 = java.lang.Boolean.parseBoolean(r5)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x006c:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isChar(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r3 == 0) goto L_0x0079
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ IllegalArgumentException -> 0x00c4 }
            java.lang.Character r5 = kotlin.text.StringsKt.singleOrNull(r5)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x0079:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isByte(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r3 == 0) goto L_0x0084
            java.lang.Byte r5 = kotlin.text.StringsKt.toByteOrNull(r1, r0)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x0084:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isShort(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r3 == 0) goto L_0x008f
            java.lang.Short r5 = kotlin.text.StringsKt.toShortOrNull(r1, r0)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x008f:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isInt(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r3 == 0) goto L_0x009a
            java.lang.Integer r5 = kotlin.text.StringsKt.toIntOrNull(r1, r0)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x009a:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isLong(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r3 == 0) goto L_0x00a5
            java.lang.Long r5 = kotlin.text.StringsKt.toLongOrNull(r1, r0)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x00a5:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isFloat(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r0 == 0) goto L_0x00b0
            java.lang.Float r5 = kotlin.text.StringsKt.toFloatOrNull(r5)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x00b0:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isDouble(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r0 == 0) goto L_0x00bb
            java.lang.Double r5 = kotlin.text.StringsKt.toDoubleOrNull(r5)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            goto L_0x00c5
        L_0x00bb:
            boolean r4 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isString(r4)     // Catch:{ IllegalArgumentException -> 0x00c4 }
            if (r4 == 0) goto L_0x00c2
            goto L_0x00c5
        L_0x00c2:
            r5 = r2
            goto L_0x00c5
        L_0x00c4:
            r5 = r2
        L_0x00c5:
            if (r5 == 0) goto L_0x00cf
            kotlin.reflect.jvm.internal.impl.load.java.Constant r4 = new kotlin.reflect.jvm.internal.impl.load.java.Constant
            r4.<init>(r5)
            r2 = r4
            kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue r2 = (kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue) r2
        L_0x00cf:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.UtilsKt.lexicalCastFrom(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.lang.String):kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue");
    }
}
