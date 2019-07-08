package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;

/* compiled from: predefinedEnhancementInfo.kt */
final class PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3 extends Lambda implements Function1<FunctionEnhancementBuilder, Unit> {
    final /* synthetic */ String $JFBiConsumer$inlined;
    final /* synthetic */ String $JFBiFunction$inlined;
    final /* synthetic */ String $JFConsumer$inlined;
    final /* synthetic */ String $JFFunction$inlined;
    final /* synthetic */ String $JFPredicate$inlined;
    final /* synthetic */ String $JFUnaryOperator$inlined;
    final /* synthetic */ String $JLObject$inlined;
    final /* synthetic */ String $JUOptional$inlined;
    final /* synthetic */ String $JUStream$inlined;
    final /* synthetic */ SignatureBuildingComponents $this_signatures$inlined;

    PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3(SignatureBuildingComponents signatureBuildingComponents, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.$this_signatures$inlined = signatureBuildingComponents;
        this.$JFConsumer$inlined = str;
        this.$JFPredicate$inlined = str2;
        this.$JUStream$inlined = str3;
        this.$JFUnaryOperator$inlined = str4;
        this.$JFBiConsumer$inlined = str5;
        this.$JLObject$inlined = str6;
        this.$JFBiFunction$inlined = str7;
        this.$JFFunction$inlined = str8;
        this.$JUOptional$inlined = str9;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FunctionEnhancementBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull FunctionEnhancementBuilder functionEnhancementBuilder) {
        Intrinsics.checkParameterIsNotNull(functionEnhancementBuilder, "receiver$0");
        functionEnhancementBuilder.parameter(this.$JFPredicate$inlined, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
        functionEnhancementBuilder.returns(JvmPrimitiveType.BOOLEAN);
    }
}
