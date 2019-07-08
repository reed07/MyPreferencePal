package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnValueChangeListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;

public class NumberPickerDialogFragment extends CustomLayoutBaseDialogFragment {
    /* access modifiers changed from: private */
    public int currentSelection;

    public static NumberPickerDialogFragment newInstance(int i, int i2, int i3, int i4) {
        Bundle bundle = new Bundle();
        NumberPickerDialogFragment numberPickerDialogFragment = new NumberPickerDialogFragment();
        bundle.putInt("title", i);
        bundle.putInt(Extras.MIN_VALUE, i2);
        bundle.putInt(Extras.MAX_VALUE, i3);
        bundle.putInt(Extras.DEFAULT_VALUE, i4);
        numberPickerDialogFragment.setArguments(bundle);
        return numberPickerDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.number_picker_dialog, null);
        NumberPicker numberPicker = (NumberPicker) inflate.findViewById(R.id.number_picker);
        Bundle arguments = getArguments();
        this.currentSelection = arguments.getInt(Extras.DEFAULT_VALUE);
        numberPicker.setMinValue(arguments.getInt(Extras.MIN_VALUE));
        numberPicker.setMaxValue(arguments.getInt(Extras.MAX_VALUE));
        numberPicker.setValue(this.currentSelection);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setFormatter(new Formatter() {
            public final String format(int i) {
                return NumberPickerDialogFragment.lambda$onCreateDialog$0(NumberPickerDialogFragment.this, i);
            }
        });
        numberPicker.setOnValueChangedListener(new OnValueChangeListener() {
            public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
                NumberPickerDialogFragment.this.currentSelection = i2;
            }
        });
        return new MfpAlertDialogBuilder(getActivity()).setTitle(BundleUtils.getInt(arguments, "title")).setView(inflate).setPositiveButton((int) R.string.setBtn, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment) = (r1v0 'this' com.myfitnesspal.shared.ui.dialog.impl.-$$Lambda$NumberPickerDialogFragment$K15KDy2tPtuVExrkYB91HKht1K0 A[THIS]) com.myfitnesspal.shared.ui.dialog.impl.-$$Lambda$NumberPickerDialogFragment$K15KDy2tPtuVExrkYB91HKht1K0.f$0 com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment), (r2v0 'dialogInterface' android.content.DialogInterface), (r3v0 'i' int) com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment.lambda$onCreateDialog$2(com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment, android.content.DialogInterface, int):void type: STATIC in method: com.myfitnesspal.shared.ui.dialog.impl.-$$Lambda$NumberPickerDialogFragment$K15KDy2tPtuVExrkYB91HKht1K0.onClick(android.content.DialogInterface, int):void, dex: classes4.dex
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
                    com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment r0 = com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment), (r2 I:android.content.DialogInterface), (r3 I:int) com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment.lambda$onCreateDialog$2(com.myfitnesspal.shared.ui.dialog.impl.NumberPickerDialogFragment, android.content.DialogInterface, int):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.dialog.impl.$$Lambda$NumberPickerDialogFragment$K15KDy2tPtuVExrkYB91HKht1K0.onClick(android.content.DialogInterface, int):void");
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).create();
    }

    public static /* synthetic */ String lambda$onCreateDialog$0(NumberPickerDialogFragment numberPickerDialogFragment, int i) {
        if (i == 32) {
            return numberPickerDialogFragment.getResources().getString(R.string.last_day);
        }
        return Strings.toString(Integer.valueOf(i));
    }
}
