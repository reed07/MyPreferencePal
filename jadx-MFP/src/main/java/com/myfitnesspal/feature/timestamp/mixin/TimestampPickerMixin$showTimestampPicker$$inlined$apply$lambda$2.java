package com.myfitnesspal.feature.timestamp.mixin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onDismiss", "com/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$showTimestampPicker$2$2"}, k = 3, mv = {1, 1, 13})
/* compiled from: TimestampPickerMixin.kt */
final class TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$2 implements OnDismissListener {
    final /* synthetic */ TimestampPickerMixin this$0;

    TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$2(TimestampPickerMixin timestampPickerMixin) {
        this.this$0 = timestampPickerMixin;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0003: INVOKE  (wrap: com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin
              0x0000: IGET  (r2v1 com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin) = (r1v0 'this' com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$2 A[THIS]) com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$2.this$0 com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin), false com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin.access$setShowingTimePicker$p(com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin, boolean):void type: STATIC in method: com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$2.onDismiss(android.content.DialogInterface):void, dex: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
            	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
            	at jadx.core.ProcessClass.process(ProcessClass.java:36)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
            Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: length -1 is negative
            	at java.base/java.util.ArrayList.shiftTailOverGap(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:86)
            	at jadx.core.utils.InsnRemover.unbindArgUsage(InsnRemover.java:90)
            	at jadx.core.dex.nodes.InsnNode.replaceArg(InsnNode.java:130)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:892)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            */
        /*
            this = this;
            com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin r2 = r1.this$0
            r0 = 0
            r2.isShowingTimePicker = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$2.onDismiss(android.content.DialogInterface):void");
    }
}
