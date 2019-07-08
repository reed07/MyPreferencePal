package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.UnitDialogDismissedEvent;
import com.myfitnesspal.feature.tizen.service.MfpGearMessageBridge;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

public class UnitsDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    UserDistanceService userDistanceService;
    @Inject
    UserEnergyService userEnergyService;
    @Inject
    UserHeightService userHeightService;
    @Inject
    UserWeightService userWeightService;

    static class UnitsSpinnerAdapter<T> extends ArrayAdapter<Tuple2<T, Integer>> {
        public UnitsSpinnerAdapter(Context context, List<Tuple2<T, Integer>> list) {
            super(context, R.layout.alert_dialog_spinner_item, list);
            setDropDownViewResource(R.layout.alert_dialog_spinner_dropdown_item);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            getText(i, view2);
            return view2;
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View dropDownView = super.getDropDownView(i, view, viewGroup);
            getText(i, dropDownView);
            return dropDownView;
        }

        private void getText(int i, View view) {
            ((TextView) ViewUtils.findById(view, 16908308)).setText(((Integer) ((Tuple2) getItem(i)).getItem2()).intValue());
        }
    }

    public static UnitsDialogFragment newInstance() {
        return new UnitsDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        LayoutInflater from = LayoutInflater.from(getDialogContextThemeWrapper());
        component().inject(this);
        View inflate = from.inflate(R.layout.units_dialog, null, false);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.spinnerWeight);
        List asList = Arrays.asList(new Tuple2[]{Tuple.create(Weight.POUNDS, Integer.valueOf(R.string.lbs_setting)), Tuple.create(Weight.KILOGRAMS, Integer.valueOf(R.string.kg_setting)), Tuple.create(Weight.STONES_POUNDS, Integer.valueOf(R.string.stones_settings))});
        spinner.setAdapter(new UnitsSpinnerAdapter(getDialogContextThemeWrapper(), asList));
        spinner.setSelection(Enumerable.indexOf(asList, this.userWeightService.getUserCurrentWeightUnit(), $$Lambda$UnitsDialogFragment$vnYlwrQ4Qk4SmJfMBpQZ55vcPDs.INSTANCE));
        Spinner spinner2 = (Spinner) inflate.findViewById(R.id.spinnerHeight);
        List asList2 = Arrays.asList(new Tuple2[]{Tuple.create(Length.FEET_INCHES, Integer.valueOf(R.string.feet_inches_setting)), Tuple.create(Length.CENTIMETERS, Integer.valueOf(R.string.cm_setting))});
        spinner2.setAdapter(new UnitsSpinnerAdapter(getDialogContextThemeWrapper(), asList2));
        spinner2.setSelection(Enumerable.indexOf(asList2, this.userHeightService.getUserCurrentHeightUnit(), $$Lambda$UnitsDialogFragment$73y6CBjrD11euFCh2Th2Vgm72XA.INSTANCE));
        Spinner spinner3 = (Spinner) inflate.findViewById(R.id.spinnerDistance);
        List asList3 = Arrays.asList(new Tuple2[]{Tuple.create(Length.MILES, Integer.valueOf(R.string.miles_setting)), Tuple.create(Length.KILOMETERS, Integer.valueOf(R.string.kilometers_setting))});
        spinner3.setAdapter(new UnitsSpinnerAdapter(getDialogContextThemeWrapper(), asList3));
        spinner3.setSelection(Enumerable.indexOf(asList3, this.userDistanceService.getUserCurrentDistanceUnit(), $$Lambda$UnitsDialogFragment$m41xLgcBoiWZvRERXEQj0Qbjo.INSTANCE));
        Spinner spinner4 = (Spinner) inflate.findViewById(R.id.spinnerEnergy);
        List asList4 = Arrays.asList(new Tuple2[]{Tuple.create(Energy.CALORIES, Integer.valueOf(R.string.calories_setting)), Tuple.create(Energy.KILOJOULES, Integer.valueOf(R.string.kilojoules_setting))});
        spinner4.setAdapter(new UnitsSpinnerAdapter(getDialogContextThemeWrapper(), asList4));
        spinner4.setSelection(Enumerable.indexOf(asList4, this.userEnergyService.getUserCurrentEnergyUnit(), $$Lambda$UnitsDialogFragment$hC6AuzEsJrmhwdSfvClotdDTiGI.INSTANCE));
        View findViewById = inflate.findViewById(R.id.water_row);
        Spinner spinner5 = (Spinner) inflate.findViewById(R.id.spinnerWater);
        List asList5 = Arrays.asList(new Tuple2[]{Tuple.create(Water.MILLILITERS, Integer.valueOf(R.string.milliliters_setting)), Tuple.create(Water.FL_OZ, Integer.valueOf(R.string.fl_oz_setting)), Tuple.create(Water.CUPS, Integer.valueOf(R.string.cups_setting))});
        spinner5.setAdapter(new UnitsSpinnerAdapter(getDialogContextThemeWrapper(), asList5));
        spinner5.setSelection(Enumerable.indexOf(asList5, LocalizedFluid.getUserCurrentWaterUnit((Session) this.session.get()), $$Lambda$UnitsDialogFragment$er_MrRpim9MvlCHA0dCFn0hb74.INSTANCE));
        ViewUtils.setVisible(findViewById);
        MfpAlertDialogBuilder view = new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle((int) R.string.unitTxt).setView(inflate);
        $$Lambda$UnitsDialogFragment$blN0aDS2QsGXD5iKjHxZqHgiaeE r2 = new OnClickListener(spinner4, spinner3, spinner2, spinner, spinner5) {
            private final /* synthetic */ Spinner f$1;
            private final /* synthetic */ Spinner f$2;
            private final /* synthetic */ Spinner f$3;
            private final /* synthetic */ Spinner f$4;
            private final /* synthetic */ Spinner f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                UnitsDialogFragment.lambda$onCreateDialog$5(UnitsDialogFragment.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, dialogInterface, i);
            }
        };
        return view.setPositiveButton((int) R.string.ok, (OnClickListener) r2).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment) = (r1v0 'this' com.myfitnesspal.feature.settings.ui.dialog.-$$Lambda$UnitsDialogFragment$z5gOqs67THexMJWQCt697DdE1j8 A[THIS]) com.myfitnesspal.feature.settings.ui.dialog.-$$Lambda$UnitsDialogFragment$z5gOqs67THexMJWQCt697DdE1j8.f$0 com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment), (r2v0 'dialogInterface' android.content.DialogInterface), (r3v0 'i' int) com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment.lambda$onCreateDialog$6(com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment, android.content.DialogInterface, int):void type: STATIC in method: com.myfitnesspal.feature.settings.ui.dialog.-$$Lambda$UnitsDialogFragment$z5gOqs67THexMJWQCt697DdE1j8.onClick(android.content.DialogInterface, int):void, dex: classes4.dex
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
                    	... 46 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 63 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment r0 = com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment), (r2 I:android.content.DialogInterface), (r3 I:int) com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment.lambda$onCreateDialog$6(com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment, android.content.DialogInterface, int):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.ui.dialog.$$Lambda$UnitsDialogFragment$z5gOqs67THexMJWQCt697DdE1j8.onClick(android.content.DialogInterface, int):void");
            }
        }).create();
    }

    static /* synthetic */ Weight lambda$onCreateDialog$0(Tuple2 tuple2) throws RuntimeException {
        return (Weight) tuple2.getItem1();
    }

    static /* synthetic */ Length lambda$onCreateDialog$1(Tuple2 tuple2) throws RuntimeException {
        return (Length) tuple2.getItem1();
    }

    static /* synthetic */ Length lambda$onCreateDialog$2(Tuple2 tuple2) throws RuntimeException {
        return (Length) tuple2.getItem1();
    }

    static /* synthetic */ Energy lambda$onCreateDialog$3(Tuple2 tuple2) throws RuntimeException {
        return (Energy) tuple2.getItem1();
    }

    static /* synthetic */ Water lambda$onCreateDialog$4(Tuple2 tuple2) throws RuntimeException {
        return (Water) tuple2.getItem1();
    }

    public static /* synthetic */ void lambda$onCreateDialog$5(UnitsDialogFragment unitsDialogFragment, Spinner spinner, Spinner spinner2, Spinner spinner3, Spinner spinner4, Spinner spinner5, DialogInterface dialogInterface, int i) {
        unitsDialogFragment.userEnergyService.setUseCurrentEnergyUnit((Energy) ((Tuple2) spinner.getSelectedItem()).getItem1());
        unitsDialogFragment.userDistanceService.setUseCurrentDistanceUnit((Length) ((Tuple2) spinner2.getSelectedItem()).getItem1());
        unitsDialogFragment.userHeightService.setUseCurrentHeightUnit((Length) ((Tuple2) spinner3.getSelectedItem()).getItem1());
        unitsDialogFragment.userWeightService.setUseCurrentWeightUnit((Weight) ((Tuple2) spinner4.getSelectedItem()).getItem1());
        LocalizedFluid.setUseCurrentWaterUnit((Water) ((Tuple2) spinner5.getSelectedItem()).getItem1(), (Session) unitsDialogFragment.session.get());
        MfpGearMessageBridge.notifyDiaryContentsUpdated(unitsDialogFragment.getActivity());
        unitsDialogFragment.messageBus.post(new UnitDialogDismissedEvent(false));
    }
}
