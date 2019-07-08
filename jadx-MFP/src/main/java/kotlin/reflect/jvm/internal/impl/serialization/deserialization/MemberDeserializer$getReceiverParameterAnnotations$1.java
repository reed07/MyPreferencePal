package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberDeserializer.kt */
final class MemberDeserializer$getReceiverParameterAnnotations$1 extends Lambda implements Function0<List<? extends AnnotationDescriptor>> {
    final /* synthetic */ AnnotatedCallableKind $kind;
    final /* synthetic */ MessageLite $proto;
    final /* synthetic */ MemberDeserializer this$0;

    MemberDeserializer$getReceiverParameterAnnotations$1(MemberDeserializer memberDeserializer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        this.this$0 = memberDeserializer;
        this.$proto = messageLite;
        this.$kind = annotatedCallableKind;
        super(0);
    }

    @NotNull
    public final List<AnnotationDescriptor> invoke() {
        MemberDeserializer memberDeserializer = this.this$0;
        ProtoContainer access$asProtoContainer = 
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000a: INVOKE  (r0v1 'access$asProtoContainer' kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer) = (r0v0 'memberDeserializer' kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer), (wrap: kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
              0x0006: INVOKE  (r1v1 kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) = (wrap: kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext
              0x0002: INVOKE  (r1v0 kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext) = (r0v0 'memberDeserializer' kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer) kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.access$getC$p(kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer):kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext type: STATIC) kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.getContainingDeclaration():kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor type: VIRTUAL) kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.access$asProtoContainer(kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor):kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer type: STATIC in method: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$getReceiverParameterAnnotations$1.invoke():java.util.List<kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor>, dex: classes5.dex
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
            	at jadx.core.dex.nodes.InsnNode.replaceArg(InsnNode.java:134)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:892)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            */
        /*
            this = this;
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r0 = r4.this$0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r1.getContainingDeclaration()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer r0 = r0.asProtoContainer(r1)
            if (r0 == 0) goto L_0x0027
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r1 = r4.this$0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r1.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r1 = r1.getComponents()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader r1 = r1.getAnnotationAndConstantLoader()
            kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r2 = r4.$proto
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r3 = r4.$kind
            java.util.List r0 = r1.loadExtensionReceiverParameterAnnotations(r0, r2, r3)
            goto L_0x0028
        L_0x0027:
            r0 = 0
        L_0x0028:
            if (r0 == 0) goto L_0x002b
            goto L_0x002f
        L_0x002b:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$getReceiverParameterAnnotations$1.invoke():java.util.List");
    }
}
