package com.myfitnesspal.feature.search.ui.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: AddItemBottomSheet.kt */
final class AddItemBottomSheet$onViewCreated$4 implements OnClickListener {
    final /* synthetic */ AddItemBottomSheet this$0;

    AddItemBottomSheet$onViewCreated$4(AddItemBottomSheet addItemBottomSheet) {
        this.this$0 = addItemBottomSheet;
    }

    public final void onClick(View view) {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: INVOKE  (wrap: com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet
              0x0000: IGET  (r2v1 com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet) = (r1v0 'this' com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$onViewCreated$4 A[THIS]) com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$onViewCreated$4.this$0 com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet), (wrap: com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$AddItemOption
              0x0002: SGET  (r0v0 com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$AddItemOption) =  com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet.AddItemOption.CREATE_MEAL com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$AddItemOption) com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet.access$triggerAndDismiss(com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet, com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$AddItemOption):void type: STATIC in method: com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$onViewCreated$4.onClick(android.view.View):void, dex: classes4.dex
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
            Caused by: java.util.ConcurrentModificationException
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
            com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet r2 = r1.this$0
            com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$AddItemOption r0 = com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet.AddItemOption.CREATE_MEAL
            r2.triggerAndDismiss(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet$onViewCreated$4.onClick(android.view.View):void");
    }
}
