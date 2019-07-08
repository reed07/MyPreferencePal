package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;

/* compiled from: predefinedEnhancementInfo.kt */
final class SignatureEnhancementBuilder {
    /* access modifiers changed from: private */
    public final Map<String, PredefinedFunctionEnhancementInfo> signatures = new LinkedHashMap();

    /* compiled from: predefinedEnhancementInfo.kt */
    public final class ClassEnhancementBuilder {
        @NotNull
        private final String className;
        final /* synthetic */ SignatureEnhancementBuilder this$0;

        /* compiled from: predefinedEnhancementInfo.kt */
        public final class FunctionEnhancementBuilder {
            @NotNull
            private final String functionName;
            private final List<Pair<String, TypeEnhancementInfo>> parameters = new ArrayList();
            private Pair<String, TypeEnhancementInfo> returnType = TuplesKt.to("V", null);
            final /* synthetic */ ClassEnhancementBuilder this$0;

            public FunctionEnhancementBuilder(ClassEnhancementBuilder classEnhancementBuilder, @NotNull String str) {
                Intrinsics.checkParameterIsNotNull(str, "functionName");
                this.this$0 = classEnhancementBuilder;
                this.functionName = str;
            }

            public final void parameter(@NotNull String str, @NotNull JavaTypeQualifiers... javaTypeQualifiersArr) {
                TypeEnhancementInfo typeEnhancementInfo;
                Intrinsics.checkParameterIsNotNull(str, "type");
                Intrinsics.checkParameterIsNotNull(javaTypeQualifiersArr, "qualifiers");
                Collection collection = this.parameters;
                if (javaTypeQualifiersArr.length == 0) {
                    typeEnhancementInfo = null;
                } else {
                    Iterable<IndexedValue> withIndex = ArraysKt.withIndex((T[]) javaTypeQualifiersArr);
                    Map linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(withIndex, 10)), 16));
                    for (IndexedValue indexedValue : withIndex) {
                        linkedHashMap.put(Integer.valueOf(indexedValue.getIndex()), (JavaTypeQualifiers) indexedValue.getValue());
                    }
                    typeEnhancementInfo = new TypeEnhancementInfo(linkedHashMap);
                }
                collection.add(TuplesKt.to(str, typeEnhancementInfo));
            }

            public final void returns(@NotNull String str, @NotNull JavaTypeQualifiers... javaTypeQualifiersArr) {
                Intrinsics.checkParameterIsNotNull(str, "type");
                Intrinsics.checkParameterIsNotNull(javaTypeQualifiersArr, "qualifiers");
                Iterable<IndexedValue> withIndex = ArraysKt.withIndex((T[]) javaTypeQualifiersArr);
                Map linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(withIndex, 10)), 16));
                for (IndexedValue indexedValue : withIndex) {
                    linkedHashMap.put(Integer.valueOf(indexedValue.getIndex()), (JavaTypeQualifiers) indexedValue.getValue());
                }
                this.returnType = TuplesKt.to(str, new TypeEnhancementInfo(linkedHashMap));
            }

            public final void returns(@NotNull JvmPrimitiveType jvmPrimitiveType) {
                Intrinsics.checkParameterIsNotNull(jvmPrimitiveType, "type");
                this.returnType = TuplesKt.to(jvmPrimitiveType.getDesc(), null);
            }

            @NotNull
            public final Pair<String, PredefinedFunctionEnhancementInfo> build() {
                SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
                String className = this.this$0.getClassName();
                String str = this.functionName;
                Iterable<Pair> iterable = this.parameters;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Pair first : iterable) {
                    arrayList.add((String) first.getFirst());
                }
                String signature = signatureBuildingComponents.signature(className, signatureBuildingComponents.jvmDescriptor(str, (List) arrayList, (String) this.returnType.getFirst()));
                TypeEnhancementInfo typeEnhancementInfo = (TypeEnhancementInfo) this.returnType.getSecond();
                Iterable<Pair> iterable2 = this.parameters;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (Pair second : iterable2) {
                    arrayList2.add((TypeEnhancementInfo) second.getSecond());
                }
                return TuplesKt.to(signature, new PredefinedFunctionEnhancementInfo(typeEnhancementInfo, (List) arrayList2));
            }
        }

        public ClassEnhancementBuilder(SignatureEnhancementBuilder signatureEnhancementBuilder, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "className");
            this.this$0 = signatureEnhancementBuilder;
            this.className = str;
        }

        @NotNull
        public final String getClassName() {
            return this.className;
        }

        public final void function(@NotNull String str, @NotNull Function1<? super FunctionEnhancementBuilder, Unit> function1) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            Intrinsics.checkParameterIsNotNull(function1, "block");
            Map access$getSignatures$p = this.this$0.signatures;
            FunctionEnhancementBuilder functionEnhancementBuilder = new FunctionEnhancementBuilder(this, str);
            function1.invoke(functionEnhancementBuilder);
            Pair build = functionEnhancementBuilder.build();
            access$getSignatures$p.put(build.getFirst(), build.getSecond());
        }
    }

    @NotNull
    public final Map<String, PredefinedFunctionEnhancementInfo> build() {
        return this.signatures;
    }
}
