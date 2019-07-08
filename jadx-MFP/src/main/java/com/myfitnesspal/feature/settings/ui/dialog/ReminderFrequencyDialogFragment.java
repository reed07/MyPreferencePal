package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Reminder;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.ArrayUtil;
import java.util.ArrayList;

public class ReminderFrequencyDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final int DAILY_FREQUENCY_INDEX = 0;
    private static final int MONTHLY_FREQUENCY_INDEX = 2;
    private static final int WEEKLY_FREQUENCY_INDEX = 1;

    private String getFrequencyAsString(int i) {
        switch (i) {
            case 0:
                return Reminder.DAILY_FREQUENCY;
            case 1:
                return Reminder.WEEKLY_FREQUENCY;
            case 2:
                return Reminder.MONTHLY_FREQUENCY;
            default:
                return Reminder.WEEKLY_FREQUENCY;
        }
    }

    public static ReminderFrequencyDialogFragment newInstance() {
        return new ReminderFrequencyDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String[] stringArray = getResources().getStringArray(R.array.frequency_array);
        ArrayList arrayList = new ArrayList(ArrayUtil.size(stringArray));
        for (String dialogListTextItem : stringArray) {
            arrayList.add(new DialogListTextItem(dialogListTextItem));
        }
        return new MfpAlertDialogBuilder(getActivity()).setItems(arrayList, new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: INVOKE  (wrap: com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment) = (r6v0 'this' com.myfitnesspal.feature.settings.ui.dialog.-$$Lambda$ReminderFrequencyDialogFragment$fOolueRtnVmDg2bFd7aoAtNcX5o A[THIS]) com.myfitnesspal.feature.settings.ui.dialog.-$$Lambda$ReminderFrequencyDialogFragment$fOolueRtnVmDg2bFd7aoAtNcX5o.f$0 com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment), (r7v0 'adapterView' android.widget.AdapterView), (r8v0 'view' android.view.View), (r9v0 'i' int), (r10v0 'j' long) com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment.lambda$onCreateDialog$0(com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment, android.widget.AdapterView, android.view.View, int, long):void type: STATIC in method: com.myfitnesspal.feature.settings.ui.dialog.-$$Lambda$ReminderFrequencyDialogFragment$fOolueRtnVmDg2bFd7aoAtNcX5o.onItemClick(android.widget.AdapterView, android.view.View, int, long):void, dex: classes4.dex
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
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
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
                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 51 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 68 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment r0 = com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment.this
                    r1 = r7
                    r2 = r8
                    r3 = r9
                    r4 = r10
                    
                    // error: 0x0006: INVOKE  (r0 I:com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment), (r1 I:android.widget.AdapterView), (r2 I:android.view.View), (r3 I:int), (r4 I:long) com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment.lambda$onCreateDialog$0(com.myfitnesspal.feature.settings.ui.dialog.ReminderFrequencyDialogFragment, android.widget.AdapterView, android.view.View, int, long):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.ui.dialog.$$Lambda$ReminderFrequencyDialogFragment$fOolueRtnVmDg2bFd7aoAtNcX5o.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        }).setTitle((int) R.string.frequency).create();
    }
}