package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import com.brightcove.player.event.EventType;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementBuilder.ClassEnhancementBuilder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import org.jetbrains.annotations.NotNull;

/* compiled from: predefinedEnhancementInfo.kt */
public final class PredefinedEnhancementInfoKt {
    /* access modifiers changed from: private */
    public static final JavaTypeQualifiers NOT_NULLABLE;
    /* access modifiers changed from: private */
    public static final JavaTypeQualifiers NOT_PLATFORM;
    /* access modifiers changed from: private */
    public static final JavaTypeQualifiers NULLABLE;
    @NotNull
    private static final Map<String, PredefinedFunctionEnhancementInfo> PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;

    static {
        JavaTypeQualifiers javaTypeQualifiers = new JavaTypeQualifiers(NullabilityQualifier.NULLABLE, null, false, false, 8, null);
        NULLABLE = javaTypeQualifiers;
        JavaTypeQualifiers javaTypeQualifiers2 = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, false, false, 8, null);
        NOT_PLATFORM = javaTypeQualifiers2;
        JavaTypeQualifiers javaTypeQualifiers3 = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, true, false, 8, null);
        NOT_NULLABLE = javaTypeQualifiers3;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String javaLang = signatureBuildingComponents.javaLang("Object");
        String javaFunction = signatureBuildingComponents.javaFunction("Predicate");
        String javaFunction2 = signatureBuildingComponents.javaFunction("Function");
        String javaFunction3 = signatureBuildingComponents.javaFunction("Consumer");
        String javaFunction4 = signatureBuildingComponents.javaFunction("BiFunction");
        String javaFunction5 = signatureBuildingComponents.javaFunction("BiConsumer");
        String javaFunction6 = signatureBuildingComponents.javaFunction("UnaryOperator");
        String javaUtil = signatureBuildingComponents.javaUtil("stream/Stream");
        String javaUtil2 = signatureBuildingComponents.javaUtil("Optional");
        SignatureEnhancementBuilder signatureEnhancementBuilder = new SignatureEnhancementBuilder();
        SignatureBuildingComponents signatureBuildingComponents2 = signatureBuildingComponents;
        String str = javaFunction3;
        String str2 = javaFunction;
        String str3 = javaFunction;
        String str4 = javaUtil;
        String str5 = javaFunction3;
        ClassEnhancementBuilder classEnhancementBuilder = new ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("Iterator"));
        String str6 = javaFunction6;
        String str7 = javaFunction6;
        SignatureEnhancementBuilder signatureEnhancementBuilder2 = signatureEnhancementBuilder;
        String str8 = javaFunction5;
        String str9 = javaUtil2;
        String str10 = javaLang;
        String str11 = javaFunction4;
        String str12 = javaFunction2;
        String str13 = str9;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$1 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$1 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$1(signatureBuildingComponents2, str, str2, str4, str6, str8, str10, str11, str12, str13);
        classEnhancementBuilder.function("forEachRemaining", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$1);
        String str14 = str5;
        String str15 = str3;
        String str16 = str7;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$2 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$2 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$2(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        new ClassEnhancementBuilder(signatureEnhancementBuilder2, signatureBuildingComponents.javaLang("Iterable")).function("spliterator", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$2);
        ClassEnhancementBuilder classEnhancementBuilder2 = new ClassEnhancementBuilder(signatureEnhancementBuilder2, signatureBuildingComponents.javaUtil("Collection"));
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder2.function("removeIf", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$4 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$4 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$4(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder2.function("stream", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$4);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$5 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$5 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$5(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder2.function("parallelStream", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$5);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$6 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$6 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$6(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        new ClassEnhancementBuilder(signatureEnhancementBuilder2, signatureBuildingComponents.javaUtil("List")).function("replaceAll", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$6);
        ClassEnhancementBuilder classEnhancementBuilder3 = new ClassEnhancementBuilder(signatureEnhancementBuilder2, signatureBuildingComponents.javaUtil("Map"));
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$7 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$7 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$7(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function("forEach", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$7);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$8 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$8 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$8(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function("putIfAbsent", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$8);
        String str17 = MealEditorMixin.EXTRA_REPLACE;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$9 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$9 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$9(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function(str17, predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$9);
        String str18 = MealEditorMixin.EXTRA_REPLACE;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$10 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$10 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$10(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function(str18, predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$10);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$11 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$11 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$11(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function("replaceAll", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$11);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$12 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$12 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$12(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function("compute", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$12);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$13 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$13 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$13(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function("computeIfAbsent", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$13);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$14 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$14 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$14(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function("computeIfPresent", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$14);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$15 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$15 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$15(signatureBuildingComponents2, str14, str15, str4, str16, str8, str10, str11, str12, str13);
        classEnhancementBuilder3.function("merge", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$15);
        String str19 = str9;
        ClassEnhancementBuilder classEnhancementBuilder4 = new ClassEnhancementBuilder(signatureEnhancementBuilder2, str19);
        SignatureEnhancementBuilder signatureEnhancementBuilder3 = signatureEnhancementBuilder2;
        String str20 = javaLang;
        String str21 = str19;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$16 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$16 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$16(signatureBuildingComponents2, str14, str15, str4, str16, str8, str20, str11, str12, str21);
        classEnhancementBuilder4.function("empty", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$16);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$17 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$17 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$17(signatureBuildingComponents2, str14, str15, str4, str16, str8, str20, str11, str12, str21);
        classEnhancementBuilder4.function("of", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$17);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$18 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$18 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$18(signatureBuildingComponents2, str14, str15, str4, str16, str8, str20, str11, str12, str21);
        classEnhancementBuilder4.function("ofNullable", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$18);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$19 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$19 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$19(signatureBuildingComponents2, str14, str15, str4, str16, str8, str20, str11, str12, str21);
        classEnhancementBuilder4.function("get", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$19);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$20 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$20 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$20(signatureBuildingComponents2, str14, str15, str4, str16, str8, str20, str11, str12, str21);
        classEnhancementBuilder4.function("ifPresent", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$20);
        SignatureEnhancementBuilder signatureEnhancementBuilder4 = signatureEnhancementBuilder3;
        String str22 = javaFunction5;
        SignatureEnhancementBuilder signatureEnhancementBuilder5 = signatureEnhancementBuilder4;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$21 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$21 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$21(signatureBuildingComponents2, str14, str15, str4, str16, str8, javaLang, str11, str12, str21);
        new ClassEnhancementBuilder(signatureEnhancementBuilder4, signatureBuildingComponents.javaLang("ref/Reference")).function("get", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$21);
        String str23 = str3;
        ClassEnhancementBuilder classEnhancementBuilder5 = new ClassEnhancementBuilder(signatureEnhancementBuilder5, str23);
        String str24 = str23;
        String str25 = str22;
        String str26 = EventType.TEST;
        String str27 = javaLang;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$22 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$22 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$22(signatureBuildingComponents2, str14, str24, str4, str16, str25, str27, str11, str12, str21);
        classEnhancementBuilder5.function(str26, predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$22);
        ClassEnhancementBuilder classEnhancementBuilder6 = new ClassEnhancementBuilder(signatureEnhancementBuilder5, signatureBuildingComponents.javaFunction("BiPredicate"));
        String str28 = EventType.TEST;
        String str29 = str3;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$23 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$23 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$23(signatureBuildingComponents2, str14, str29, str4, str16, str25, str27, str11, str12, str21);
        classEnhancementBuilder6.function(str28, predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$23);
        String str30 = str5;
        String str31 = str30;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$24 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$24 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$24(signatureBuildingComponents2, str31, str29, str4, str16, str25, javaLang, str11, str12, str21);
        new ClassEnhancementBuilder(signatureEnhancementBuilder5, str30).function("accept", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$24);
        String str32 = str22;
        String str33 = str5;
        String str34 = str32;
        String str35 = javaLang;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$25 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$25 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$25(signatureBuildingComponents2, str33, str29, str4, str16, str34, str35, str11, str12, str21);
        new ClassEnhancementBuilder(signatureEnhancementBuilder5, str32).function("accept", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$25);
        String str36 = str22;
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$26 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$26 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$26(signatureBuildingComponents2, str33, str29, str4, str16, str36, str35, str11, str12, str21);
        new ClassEnhancementBuilder(signatureEnhancementBuilder5, javaFunction2).function("apply", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$26);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$27 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$27 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$27(signatureBuildingComponents2, str33, str29, str4, str16, str36, str35, str11, str12, str21);
        new ClassEnhancementBuilder(signatureEnhancementBuilder5, javaFunction4).function("apply", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$27);
        PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$28 predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$28 = new PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$28(signatureBuildingComponents2, str33, str29, str4, str16, str36, str35, str11, str12, str21);
        new ClassEnhancementBuilder(signatureEnhancementBuilder5, signatureBuildingComponents.javaFunction("Supplier")).function("get", predefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$28);
        PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE = signatureEnhancementBuilder5.build();
    }

    @NotNull
    public static final Map<String, PredefinedFunctionEnhancementInfo> getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE() {
        return PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    }
}
