package com.myfitnesspal.feature.search.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.ui.view.ViewHolder;
import com.squareup.otto.Bus;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public class EmptyItemViewHolder extends ViewHolder<DiaryEntryCellModel> implements RequiresSearchQuery {
    private final Bus bus;
    private final Context context;
    @BindView(2131363304)
    TextView noResultFound;
    private String queryString;
    @BindView(2131363582)
    TextView searchAllFoods;

    public EmptyItemViewHolder(View view, Bus bus2) {
        super(view);
        ButterKnife.bind((Object) this, view);
        this.context = view.getContext().getApplicationContext();
        this.bus = bus2;
    }

    public void setSearchQuery(String str) {
        this.queryString = str;
    }

    public void setData(DiaryEntryCellModel diaryEntryCellModel, int i) {
        boolean notEmpty = Strings.notEmpty(this.queryString);
        setUIVisibility(notEmpty);
        if (notEmpty) {
            this.searchAllFoods.setText(this.context.getString(R.string.search_all_foods, new Object[]{this.queryString}));
            this.searchAllFoods.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder) = (r1v0 'this' com.myfitnesspal.feature.search.ui.adapter.holder.-$$Lambda$EmptyItemViewHolder$o1a1yVagBjfv6N-4si545yYFf9c A[THIS]) com.myfitnesspal.feature.search.ui.adapter.holder.-$$Lambda$EmptyItemViewHolder$o1a1yVagBjfv6N-4si545yYFf9c.f$0 com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder), (r2v0 'view' android.view.View) com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder.lambda$setData$0(com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder, android.view.View):void type: STATIC in method: com.myfitnesspal.feature.search.ui.adapter.holder.-$$Lambda$EmptyItemViewHolder$o1a1yVagBjfv6N-4si545yYFf9c.onClick(android.view.View):void, dex: classes4.dex
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
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                        	... 45 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 62 more
                        */
                    /*
                        this = this;
                        com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder r0 = com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder.this
                        
                        // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder), (r2 I:android.view.View) com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder.lambda$setData$0(com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder, android.view.View):void type: STATIC
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.adapter.holder.$$Lambda$EmptyItemViewHolder$o1a1yVagBjfv6N4si545yYFf9c.onClick(android.view.View):void");
                }
            });
        }
    }

    private void setUIVisibility(boolean z) {
        ViewUtils.setVisible(z, this.noResultFound);
        ViewUtils.setVisible(z, this.searchAllFoods);
    }
}
