package com.google.protobuf;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.protobuf.DescriptorProtos.DescriptorProto;
import com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange;
import com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRange;
import com.google.protobuf.DescriptorProtos.EnumDescriptorProto;
import com.google.protobuf.DescriptorProtos.EnumOptions;
import com.google.protobuf.DescriptorProtos.EnumValueDescriptorProto;
import com.google.protobuf.DescriptorProtos.EnumValueOptions;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label;
import com.google.protobuf.DescriptorProtos.FieldOptions;
import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.DescriptorProtos.FileOptions;
import com.google.protobuf.DescriptorProtos.MessageOptions;
import com.google.protobuf.DescriptorProtos.MethodDescriptorProto;
import com.google.protobuf.DescriptorProtos.MethodOptions;
import com.google.protobuf.DescriptorProtos.OneofDescriptorProto;
import com.google.protobuf.DescriptorProtos.OneofOptions;
import com.google.protobuf.DescriptorProtos.ServiceDescriptorProto;
import com.google.protobuf.DescriptorProtos.ServiceOptions;
import com.google.protobuf.FieldSet.FieldDescriptorLite;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.MessageLite.Builder;
import com.google.protobuf.TextFormat.InvalidEscapeSequenceException;
import com.google.protobuf.WireFormat.FieldType;
import com.myfitnesspal.shared.constants.Constants.BusyStates;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import kotlin.text.Typography;

public final class Descriptors {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(Descriptors.class.getName());

    public static final class Descriptor extends GenericDescriptor {
        private final Descriptor containingType;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final Descriptor[] nestedTypes;
        private final OneofDescriptor[] oneofs;
        private DescriptorProto proto;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public List<OneofDescriptor> getOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public boolean isExtensionNumber(int i) {
            for (ExtensionRange extensionRange : this.proto.getExtensionRangeList()) {
                if (extensionRange.getStart() <= i && i < extensionRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isReservedNumber(int i) {
            for (ReservedRange reservedRange : this.proto.getReservedRangeList()) {
                if (reservedRange.getStart() <= i && i < reservedRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isReservedName(String str) {
            Internal.checkNotNull(str);
            for (String equals : this.proto.getReservedNameList()) {
                if (equals.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isExtendable() {
            return this.proto.getExtensionRangeList().size() != 0;
        }

        public FieldDescriptor findFieldByName(String str) {
            DescriptorPool access$1400 = this.file.pool;
            StringBuilder sb = new StringBuilder();
            sb.append(this.fullName);
            sb.append('.');
            sb.append(str);
            GenericDescriptor findSymbol = access$1400.findSymbol(sb.toString());
            if (findSymbol == null || !(findSymbol instanceof FieldDescriptor)) {
                return null;
            }
            return (FieldDescriptor) findSymbol;
        }

        public FieldDescriptor findFieldByNumber(int i) {
            return (FieldDescriptor) this.file.pool.fieldsByNumber.get(new DescriptorIntPair(this, i));
        }

        public Descriptor findNestedTypeByName(String str) {
            DescriptorPool access$1400 = this.file.pool;
            StringBuilder sb = new StringBuilder();
            sb.append(this.fullName);
            sb.append('.');
            sb.append(str);
            GenericDescriptor findSymbol = access$1400.findSymbol(sb.toString());
            if (findSymbol == null || !(findSymbol instanceof Descriptor)) {
                return null;
            }
            return (Descriptor) findSymbol;
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            DescriptorPool access$1400 = this.file.pool;
            StringBuilder sb = new StringBuilder();
            sb.append(this.fullName);
            sb.append('.');
            sb.append(str);
            GenericDescriptor findSymbol = access$1400.findSymbol(sb.toString());
            if (findSymbol == null || !(findSymbol instanceof EnumDescriptor)) {
                return null;
            }
            return (EnumDescriptor) findSymbol;
        }

        Descriptor(String str) throws DescriptorValidationException {
            String str2;
            String str3;
            String str4 = "";
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                str3 = str.substring(lastIndexOf + 1);
                str2 = str.substring(0, lastIndexOf);
            } else {
                str2 = str4;
                str3 = str;
            }
            this.index = 0;
            this.proto = DescriptorProto.newBuilder().setName(str3).addExtensionRange(ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
            this.fullName = str;
            this.containingType = null;
            this.nestedTypes = new Descriptor[0];
            this.enumTypes = new EnumDescriptor[0];
            this.fields = new FieldDescriptor[0];
            this.extensions = new FieldDescriptor[0];
            this.oneofs = new OneofDescriptor[0];
            this.file = new FileDescriptor(str2, this);
        }

        private Descriptor(DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = descriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, descriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            this.oneofs = new OneofDescriptor[descriptorProto.getOneofDeclCount()];
            for (int i2 = 0; i2 < descriptorProto.getOneofDeclCount(); i2++) {
                OneofDescriptor[] oneofDescriptorArr = this.oneofs;
                OneofDescriptor oneofDescriptor = new OneofDescriptor(descriptorProto.getOneofDecl(i2), fileDescriptor, this, i2);
                oneofDescriptorArr[i2] = oneofDescriptor;
            }
            this.nestedTypes = new Descriptor[descriptorProto.getNestedTypeCount()];
            for (int i3 = 0; i3 < descriptorProto.getNestedTypeCount(); i3++) {
                this.nestedTypes[i3] = new Descriptor(descriptorProto.getNestedType(i3), fileDescriptor, this, i3);
            }
            this.enumTypes = new EnumDescriptor[descriptorProto.getEnumTypeCount()];
            for (int i4 = 0; i4 < descriptorProto.getEnumTypeCount(); i4++) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                EnumDescriptor enumDescriptor = new EnumDescriptor(descriptorProto.getEnumType(i4), fileDescriptor, this, i4);
                enumDescriptorArr[i4] = enumDescriptor;
            }
            this.fields = new FieldDescriptor[descriptorProto.getFieldCount()];
            for (int i5 = 0; i5 < descriptorProto.getFieldCount(); i5++) {
                FieldDescriptor[] fieldDescriptorArr = this.fields;
                FieldDescriptor fieldDescriptor = new FieldDescriptor(descriptorProto.getField(i5), fileDescriptor, this, i5, false);
                fieldDescriptorArr[i5] = fieldDescriptor;
            }
            this.extensions = new FieldDescriptor[descriptorProto.getExtensionCount()];
            for (int i6 = 0; i6 < descriptorProto.getExtensionCount(); i6++) {
                FieldDescriptor[] fieldDescriptorArr2 = this.extensions;
                FieldDescriptor fieldDescriptor2 = new FieldDescriptor(descriptorProto.getExtension(i6), fileDescriptor, this, i6, true);
                fieldDescriptorArr2[i6] = fieldDescriptor2;
            }
            for (int i7 = 0; i7 < descriptorProto.getOneofDeclCount(); i7++) {
                OneofDescriptor[] oneofDescriptorArr2 = this.oneofs;
                oneofDescriptorArr2[i7].fields = new FieldDescriptor[oneofDescriptorArr2[i7].getFieldCount()];
                this.oneofs[i7].fieldCount = 0;
            }
            for (int i8 = 0; i8 < descriptorProto.getFieldCount(); i8++) {
                OneofDescriptor containingOneof = this.fields[i8].getContainingOneof();
                if (containingOneof != null) {
                    containingOneof.fields[
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0103: APUT  (wrap: com.google.protobuf.Descriptors$FieldDescriptor[]
                          0x00f7: INVOKE  (r0v6 com.google.protobuf.Descriptors$FieldDescriptor[]) = (r13v26 'containingOneof' com.google.protobuf.Descriptors$OneofDescriptor) com.google.protobuf.Descriptors.OneofDescriptor.access$1800(com.google.protobuf.Descriptors$OneofDescriptor):com.google.protobuf.Descriptors$FieldDescriptor[] type: STATIC), (wrap: int
                          0x00fb: INVOKE  (r13v27 int) = (r13v26 'containingOneof' com.google.protobuf.Descriptors$OneofDescriptor) com.google.protobuf.Descriptors.OneofDescriptor.access$1908(com.google.protobuf.Descriptors$OneofDescriptor):int type: STATIC), (wrap: com.google.protobuf.Descriptors$FieldDescriptor
                          0x0101: AGET  (r1v1 com.google.protobuf.Descriptors$FieldDescriptor) = (wrap: com.google.protobuf.Descriptors$FieldDescriptor[]
                          0x00ff: IGET  (r1v0 com.google.protobuf.Descriptors$FieldDescriptor[]) = (r9v0 'this' com.google.protobuf.Descriptors$Descriptor A[THIS]) com.google.protobuf.Descriptors.Descriptor.fields com.google.protobuf.Descriptors$FieldDescriptor[]), (r12v4 'i8' int)) in method: com.google.protobuf.Descriptors.Descriptor.<init>(com.google.protobuf.DescriptorProtos$DescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int):void, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
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
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:209)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                        	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                        	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                        	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00fb: INVOKE  (r13v27 int) = (r13v26 'containingOneof' com.google.protobuf.Descriptors$OneofDescriptor) com.google.protobuf.Descriptors.OneofDescriptor.access$1908(com.google.protobuf.Descriptors$OneofDescriptor):int type: STATIC in method: com.google.protobuf.Descriptors.Descriptor.<init>(com.google.protobuf.DescriptorProtos$DescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int):void, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:404)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                        	... 32 more
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
                        	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                        	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                        	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                        	... 35 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 53 more
                        */
                    /*
                        this = this;
                        r9.<init>()
                        r9.index = r13
                        r9.proto = r10
                        java.lang.String r13 = r10.getName()
                        java.lang.String r13 = com.google.protobuf.Descriptors.computeFullName(r11, r12, r13)
                        r9.fullName = r13
                        r9.file = r11
                        r9.containingType = r12
                        int r12 = r10.getOneofDeclCount()
                        com.google.protobuf.Descriptors$OneofDescriptor[] r12 = new com.google.protobuf.Descriptors.OneofDescriptor[r12]
                        r9.oneofs = r12
                        r12 = 0
                        r13 = 0
                    L_0x001f:
                        int r0 = r10.getOneofDeclCount()
                        if (r13 >= r0) goto L_0x003a
                        com.google.protobuf.Descriptors$OneofDescriptor[] r6 = r9.oneofs
                        com.google.protobuf.Descriptors$OneofDescriptor r7 = new com.google.protobuf.Descriptors$OneofDescriptor
                        com.google.protobuf.DescriptorProtos$OneofDescriptorProto r1 = r10.getOneofDecl(r13)
                        r5 = 0
                        r0 = r7
                        r2 = r11
                        r3 = r9
                        r4 = r13
                        r0.<init>(r1, r2, r3, r4)
                        r6[r13] = r7
                        int r13 = r13 + 1
                        goto L_0x001f
                    L_0x003a:
                        int r13 = r10.getNestedTypeCount()
                        com.google.protobuf.Descriptors$Descriptor[] r13 = new com.google.protobuf.Descriptors.Descriptor[r13]
                        r9.nestedTypes = r13
                        r13 = 0
                    L_0x0043:
                        int r0 = r10.getNestedTypeCount()
                        if (r13 >= r0) goto L_0x0059
                        com.google.protobuf.Descriptors$Descriptor[] r0 = r9.nestedTypes
                        com.google.protobuf.Descriptors$Descriptor r1 = new com.google.protobuf.Descriptors$Descriptor
                        com.google.protobuf.DescriptorProtos$DescriptorProto r2 = r10.getNestedType(r13)
                        r1.<init>(r2, r11, r9, r13)
                        r0[r13] = r1
                        int r13 = r13 + 1
                        goto L_0x0043
                    L_0x0059:
                        int r13 = r10.getEnumTypeCount()
                        com.google.protobuf.Descriptors$EnumDescriptor[] r13 = new com.google.protobuf.Descriptors.EnumDescriptor[r13]
                        r9.enumTypes = r13
                        r13 = 0
                    L_0x0062:
                        int r0 = r10.getEnumTypeCount()
                        if (r13 >= r0) goto L_0x007d
                        com.google.protobuf.Descriptors$EnumDescriptor[] r6 = r9.enumTypes
                        com.google.protobuf.Descriptors$EnumDescriptor r7 = new com.google.protobuf.Descriptors$EnumDescriptor
                        com.google.protobuf.DescriptorProtos$EnumDescriptorProto r1 = r10.getEnumType(r13)
                        r5 = 0
                        r0 = r7
                        r2 = r11
                        r3 = r9
                        r4 = r13
                        r0.<init>(r1, r2, r3, r4)
                        r6[r13] = r7
                        int r13 = r13 + 1
                        goto L_0x0062
                    L_0x007d:
                        int r13 = r10.getFieldCount()
                        com.google.protobuf.Descriptors$FieldDescriptor[] r13 = new com.google.protobuf.Descriptors.FieldDescriptor[r13]
                        r9.fields = r13
                        r13 = 0
                    L_0x0086:
                        int r0 = r10.getFieldCount()
                        if (r13 >= r0) goto L_0x00a2
                        com.google.protobuf.Descriptors$FieldDescriptor[] r7 = r9.fields
                        com.google.protobuf.Descriptors$FieldDescriptor r8 = new com.google.protobuf.Descriptors$FieldDescriptor
                        com.google.protobuf.DescriptorProtos$FieldDescriptorProto r1 = r10.getField(r13)
                        r5 = 0
                        r6 = 0
                        r0 = r8
                        r2 = r11
                        r3 = r9
                        r4 = r13
                        r0.<init>(r1, r2, r3, r4, r5)
                        r7[r13] = r8
                        int r13 = r13 + 1
                        goto L_0x0086
                    L_0x00a2:
                        int r13 = r10.getExtensionCount()
                        com.google.protobuf.Descriptors$FieldDescriptor[] r13 = new com.google.protobuf.Descriptors.FieldDescriptor[r13]
                        r9.extensions = r13
                        r13 = 0
                    L_0x00ab:
                        int r0 = r10.getExtensionCount()
                        if (r13 >= r0) goto L_0x00c7
                        com.google.protobuf.Descriptors$FieldDescriptor[] r7 = r9.extensions
                        com.google.protobuf.Descriptors$FieldDescriptor r8 = new com.google.protobuf.Descriptors$FieldDescriptor
                        com.google.protobuf.DescriptorProtos$FieldDescriptorProto r1 = r10.getExtension(r13)
                        r5 = 1
                        r6 = 0
                        r0 = r8
                        r2 = r11
                        r3 = r9
                        r4 = r13
                        r0.<init>(r1, r2, r3, r4, r5)
                        r7[r13] = r8
                        int r13 = r13 + 1
                        goto L_0x00ab
                    L_0x00c7:
                        r13 = 0
                    L_0x00c8:
                        int r0 = r10.getOneofDeclCount()
                        if (r13 >= r0) goto L_0x00e7
                        com.google.protobuf.Descriptors$OneofDescriptor[] r0 = r9.oneofs
                        r1 = r0[r13]
                        r0 = r0[r13]
                        int r0 = r0.getFieldCount()
                        com.google.protobuf.Descriptors$FieldDescriptor[] r0 = new com.google.protobuf.Descriptors.FieldDescriptor[r0]
                        r1.fields = r0
                        com.google.protobuf.Descriptors$OneofDescriptor[] r0 = r9.oneofs
                        r0 = r0[r13]
                        r0.fieldCount = r12
                        int r13 = r13 + 1
                        goto L_0x00c8
                    L_0x00e7:
                        int r13 = r10.getFieldCount()
                        if (r12 >= r13) goto L_0x0108
                        com.google.protobuf.Descriptors$FieldDescriptor[] r13 = r9.fields
                        r13 = r13[r12]
                        com.google.protobuf.Descriptors$OneofDescriptor r13 = r13.getContainingOneof()
                        if (r13 == 0) goto L_0x0105
                        com.google.protobuf.Descriptors$FieldDescriptor[] r0 = r13.fields
                        int r13 = 
                        // error: 0x00fb: INVOKE  (r13 I:int) = (r13 I:com.google.protobuf.Descriptors$OneofDescriptor) com.google.protobuf.Descriptors.OneofDescriptor.access$1908(com.google.protobuf.Descriptors$OneofDescriptor):int type: STATIC
                        com.google.protobuf.Descriptors$FieldDescriptor[] r1 = r9.fields
                        r1 = r1[r12]
                        r0[r13] = r1
                    L_0x0105:
                        int r12 = r12 + 1
                        goto L_0x00e7
                    L_0x0108:
                        com.google.protobuf.Descriptors$DescriptorPool r10 = r11.pool
                        r10.addSymbol(r9)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.Descriptor.<init>(com.google.protobuf.DescriptorProtos$DescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int):void");
                }

                /* access modifiers changed from: private */
                public void crossLink() throws DescriptorValidationException {
                    for (Descriptor crossLink : this.nestedTypes) {
                        crossLink.crossLink();
                    }
                    for (FieldDescriptor access$900 : this.fields) {
                        access$900.crossLink();
                    }
                    for (FieldDescriptor access$9002 : this.extensions) {
                        access$9002.crossLink();
                    }
                }

                /* access modifiers changed from: private */
                public void setProto(DescriptorProto descriptorProto) {
                    this.proto = descriptorProto;
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        Descriptor[] descriptorArr = this.nestedTypes;
                        if (i2 >= descriptorArr.length) {
                            break;
                        }
                        descriptorArr[i2].setProto(descriptorProto.getNestedType(i2));
                        i2++;
                    }
                    int i3 = 0;
                    while (true) {
                        OneofDescriptor[] oneofDescriptorArr = this.oneofs;
                        if (i3 >= oneofDescriptorArr.length) {
                            break;
                        }
                        oneofDescriptorArr[i3].setProto(descriptorProto.getOneofDecl(i3));
                        i3++;
                    }
                    int i4 = 0;
                    while (true) {
                        EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                        if (i4 >= enumDescriptorArr.length) {
                            break;
                        }
                        enumDescriptorArr[i4].setProto(descriptorProto.getEnumType(i4));
                        i4++;
                    }
                    int i5 = 0;
                    while (true) {
                        FieldDescriptor[] fieldDescriptorArr = this.fields;
                        if (i5 >= fieldDescriptorArr.length) {
                            break;
                        }
                        fieldDescriptorArr[i5].setProto(descriptorProto.getField(i5));
                        i5++;
                    }
                    while (true) {
                        FieldDescriptor[] fieldDescriptorArr2 = this.extensions;
                        if (i < fieldDescriptorArr2.length) {
                            fieldDescriptorArr2[i].setProto(descriptorProto.getExtension(i));
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            }

            private static final class DescriptorPool {
                private boolean allowUnknownDependencies;
                private final Set<FileDescriptor> dependencies = new HashSet();
                private final Map<String, GenericDescriptor> descriptorsByName = new HashMap();
                /* access modifiers changed from: private */
                public final Map<DescriptorIntPair, EnumValueDescriptor> enumValuesByNumber = new HashMap();
                /* access modifiers changed from: private */
                public final Map<DescriptorIntPair, FieldDescriptor> fieldsByNumber = new HashMap();

                private static final class DescriptorIntPair {
                    private final GenericDescriptor descriptor;
                    private final int number;

                    DescriptorIntPair(GenericDescriptor genericDescriptor, int i) {
                        this.descriptor = genericDescriptor;
                        this.number = i;
                    }

                    public int hashCode() {
                        return (this.descriptor.hashCode() * BusyStates.ALL) + this.number;
                    }

                    public boolean equals(Object obj) {
                        boolean z = false;
                        if (!(obj instanceof DescriptorIntPair)) {
                            return false;
                        }
                        DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
                        if (this.descriptor == descriptorIntPair.descriptor && this.number == descriptorIntPair.number) {
                            z = true;
                        }
                        return z;
                    }
                }

                private static final class PackageDescriptor extends GenericDescriptor {
                    private final FileDescriptor file;
                    private final String fullName;
                    private final String name;

                    public Message toProto() {
                        return this.file.toProto();
                    }

                    public String getName() {
                        return this.name;
                    }

                    public String getFullName() {
                        return this.fullName;
                    }

                    public FileDescriptor getFile() {
                        return this.file;
                    }

                    PackageDescriptor(String str, String str2, FileDescriptor fileDescriptor) {
                        this.file = fileDescriptor;
                        this.fullName = str2;
                        this.name = str;
                    }
                }

                enum SearchFilter {
                    TYPES_ONLY,
                    AGGREGATES_ONLY,
                    ALL_SYMBOLS
                }

                DescriptorPool(FileDescriptor[] fileDescriptorArr, boolean z) {
                    this.allowUnknownDependencies = z;
                    for (int i = 0; i < fileDescriptorArr.length; i++) {
                        this.dependencies.add(fileDescriptorArr[i]);
                        importPublicDependencies(fileDescriptorArr[i]);
                    }
                    for (FileDescriptor fileDescriptor : this.dependencies) {
                        try {
                            addPackage(fileDescriptor.getPackage(), fileDescriptor);
                        } catch (DescriptorValidationException e) {
                            throw new AssertionError(e);
                        }
                    }
                }

                private void importPublicDependencies(FileDescriptor fileDescriptor) {
                    for (FileDescriptor fileDescriptor2 : fileDescriptor.getPublicDependencies()) {
                        if (this.dependencies.add(fileDescriptor2)) {
                            importPublicDependencies(fileDescriptor2);
                        }
                    }
                }

                /* access modifiers changed from: 0000 */
                public GenericDescriptor findSymbol(String str) {
                    return findSymbol(str, SearchFilter.ALL_SYMBOLS);
                }

                /* access modifiers changed from: 0000 */
                public GenericDescriptor findSymbol(String str, SearchFilter searchFilter) {
                    GenericDescriptor genericDescriptor = (GenericDescriptor) this.descriptorsByName.get(str);
                    if (genericDescriptor != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor))))) {
                        return genericDescriptor;
                    }
                    for (FileDescriptor access$1400 : this.dependencies) {
                        GenericDescriptor genericDescriptor2 = (GenericDescriptor) access$1400.pool.descriptorsByName.get(str);
                        if (genericDescriptor2 != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor2)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor2))))) {
                            return genericDescriptor2;
                        }
                    }
                    return null;
                }

                /* access modifiers changed from: 0000 */
                public boolean isType(GenericDescriptor genericDescriptor) {
                    return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor);
                }

                /* access modifiers changed from: 0000 */
                public boolean isAggregate(GenericDescriptor genericDescriptor) {
                    return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor) || (genericDescriptor instanceof PackageDescriptor) || (genericDescriptor instanceof ServiceDescriptor);
                }

                /* access modifiers changed from: 0000 */
                public GenericDescriptor lookupSymbol(String str, GenericDescriptor genericDescriptor, SearchFilter searchFilter) throws DescriptorValidationException {
                    GenericDescriptor genericDescriptor2;
                    String str2;
                    String str3;
                    if (str.startsWith(".")) {
                        str2 = str.substring(1);
                        genericDescriptor2 = findSymbol(str2, searchFilter);
                    } else {
                        int indexOf = str.indexOf(46);
                        if (indexOf == -1) {
                            str3 = str;
                        } else {
                            str3 = str.substring(0, indexOf);
                        }
                        StringBuilder sb = new StringBuilder(genericDescriptor.getFullName());
                        while (true) {
                            int lastIndexOf = sb.lastIndexOf(".");
                            if (lastIndexOf == -1) {
                                genericDescriptor2 = findSymbol(str, searchFilter);
                                str2 = str;
                                break;
                            }
                            int i = lastIndexOf + 1;
                            sb.setLength(i);
                            sb.append(str3);
                            GenericDescriptor findSymbol = findSymbol(sb.toString(), SearchFilter.AGGREGATES_ONLY);
                            if (findSymbol != null) {
                                if (indexOf != -1) {
                                    sb.setLength(i);
                                    sb.append(str);
                                    genericDescriptor2 = findSymbol(sb.toString(), searchFilter);
                                } else {
                                    genericDescriptor2 = findSymbol;
                                }
                                str2 = sb.toString();
                            } else {
                                sb.setLength(lastIndexOf);
                            }
                        }
                    }
                    if (genericDescriptor2 != null) {
                        return genericDescriptor2;
                    }
                    if (!this.allowUnknownDependencies || searchFilter != SearchFilter.TYPES_ONLY) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(Typography.quote);
                        sb2.append(str);
                        sb2.append("\" is not defined.");
                        throw new DescriptorValidationException(genericDescriptor, sb2.toString());
                    }
                    Logger access$100 = Descriptors.logger;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("The descriptor for message type \"");
                    sb3.append(str);
                    sb3.append("\" can not be found and a placeholder is created for it");
                    access$100.warning(sb3.toString());
                    Descriptor descriptor = new Descriptor(str2);
                    this.dependencies.add(descriptor.getFile());
                    return descriptor;
                }

                /* access modifiers changed from: 0000 */
                public void addSymbol(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
                    validateSymbolName(genericDescriptor);
                    String fullName = genericDescriptor.getFullName();
                    int lastIndexOf = fullName.lastIndexOf(46);
                    GenericDescriptor genericDescriptor2 = (GenericDescriptor) this.descriptorsByName.put(fullName, genericDescriptor);
                    if (genericDescriptor2 != null) {
                        this.descriptorsByName.put(fullName, genericDescriptor2);
                        if (genericDescriptor.getFile() != genericDescriptor2.getFile()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(Typography.quote);
                            sb.append(fullName);
                            sb.append("\" is already defined in file \"");
                            sb.append(genericDescriptor2.getFile().getName());
                            sb.append("\".");
                            throw new DescriptorValidationException(genericDescriptor, sb.toString());
                        } else if (lastIndexOf == -1) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(Typography.quote);
                            sb2.append(fullName);
                            sb2.append("\" is already defined.");
                            throw new DescriptorValidationException(genericDescriptor, sb2.toString());
                        } else {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(Typography.quote);
                            sb3.append(fullName.substring(lastIndexOf + 1));
                            sb3.append("\" is already defined in \"");
                            sb3.append(fullName.substring(0, lastIndexOf));
                            sb3.append("\".");
                            throw new DescriptorValidationException(genericDescriptor, sb3.toString());
                        }
                    }
                }

                /* access modifiers changed from: 0000 */
                public void addPackage(String str, FileDescriptor fileDescriptor) throws DescriptorValidationException {
                    String str2;
                    int lastIndexOf = str.lastIndexOf(46);
                    if (lastIndexOf == -1) {
                        str2 = str;
                    } else {
                        addPackage(str.substring(0, lastIndexOf), fileDescriptor);
                        str2 = str.substring(lastIndexOf + 1);
                    }
                    GenericDescriptor genericDescriptor = (GenericDescriptor) this.descriptorsByName.put(str, new PackageDescriptor(str2, str, fileDescriptor));
                    if (genericDescriptor != null) {
                        this.descriptorsByName.put(str, genericDescriptor);
                        if (!(genericDescriptor instanceof PackageDescriptor)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(Typography.quote);
                            sb.append(str2);
                            sb.append("\" is already defined (as something other than a package) in file \"");
                            sb.append(genericDescriptor.getFile().getName());
                            sb.append("\".");
                            throw new DescriptorValidationException(fileDescriptor, sb.toString());
                        }
                    }
                }

                /* access modifiers changed from: 0000 */
                public void addFieldByNumber(FieldDescriptor fieldDescriptor) throws DescriptorValidationException {
                    DescriptorIntPair descriptorIntPair = new DescriptorIntPair(fieldDescriptor.getContainingType(), fieldDescriptor.getNumber());
                    FieldDescriptor fieldDescriptor2 = (FieldDescriptor) this.fieldsByNumber.put(descriptorIntPair, fieldDescriptor);
                    if (fieldDescriptor2 != null) {
                        this.fieldsByNumber.put(descriptorIntPair, fieldDescriptor2);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Field number ");
                        sb.append(fieldDescriptor.getNumber());
                        sb.append(" has already been used in \"");
                        sb.append(fieldDescriptor.getContainingType().getFullName());
                        sb.append("\" by field \"");
                        sb.append(fieldDescriptor2.getName());
                        sb.append("\".");
                        throw new DescriptorValidationException((GenericDescriptor) fieldDescriptor, sb.toString());
                    }
                }

                /* access modifiers changed from: 0000 */
                public void addEnumValueByNumber(EnumValueDescriptor enumValueDescriptor) {
                    DescriptorIntPair descriptorIntPair = new DescriptorIntPair(enumValueDescriptor.getType(), enumValueDescriptor.getNumber());
                    EnumValueDescriptor enumValueDescriptor2 = (EnumValueDescriptor) this.enumValuesByNumber.put(descriptorIntPair, enumValueDescriptor);
                    if (enumValueDescriptor2 != null) {
                        this.enumValuesByNumber.put(descriptorIntPair, enumValueDescriptor2);
                    }
                }

                static void validateSymbolName(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
                    String name = genericDescriptor.getName();
                    if (name.length() != 0) {
                        boolean z = true;
                        for (int i = 0; i < name.length(); i++) {
                            char charAt = name.charAt(i);
                            if (charAt >= 128) {
                                z = false;
                            }
                            if (!Character.isLetter(charAt) && charAt != '_' && (!Character.isDigit(charAt) || i <= 0)) {
                                z = false;
                            }
                        }
                        if (!z) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(Typography.quote);
                            sb.append(name);
                            sb.append("\" is not a valid identifier.");
                            throw new DescriptorValidationException(genericDescriptor, sb.toString());
                        }
                        return;
                    }
                    throw new DescriptorValidationException(genericDescriptor, "Missing name.");
                }
            }

            public static class DescriptorValidationException extends Exception {
                private static final long serialVersionUID = 5750205775490483148L;
                private final String description;
                private final String name;
                private final Message proto;

                public String getProblemSymbolName() {
                    return this.name;
                }

                public Message getProblemProto() {
                    return this.proto;
                }

                public String getDescription() {
                    return this.description;
                }

                private DescriptorValidationException(GenericDescriptor genericDescriptor, String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(genericDescriptor.getFullName());
                    sb.append(": ");
                    sb.append(str);
                    super(sb.toString());
                    this.name = genericDescriptor.getFullName();
                    this.proto = genericDescriptor.toProto();
                    this.description = str;
                }

                private DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th) {
                    this(genericDescriptor, str);
                    initCause(th);
                }

                private DescriptorValidationException(FileDescriptor fileDescriptor, String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(fileDescriptor.getName());
                    sb.append(": ");
                    sb.append(str);
                    super(sb.toString());
                    this.name = fileDescriptor.getName();
                    this.proto = fileDescriptor.toProto();
                    this.description = str;
                }
            }

            public static final class EnumDescriptor extends GenericDescriptor implements EnumLiteMap<EnumValueDescriptor> {
                private final Descriptor containingType;
                private final FileDescriptor file;
                private final String fullName;
                private final int index;
                private EnumDescriptorProto proto;
                private final WeakHashMap<Integer, WeakReference<EnumValueDescriptor>> unknownValues;
                private EnumValueDescriptor[] values;

                public int getIndex() {
                    return this.index;
                }

                public EnumDescriptorProto toProto() {
                    return this.proto;
                }

                public String getName() {
                    return this.proto.getName();
                }

                public String getFullName() {
                    return this.fullName;
                }

                public FileDescriptor getFile() {
                    return this.file;
                }

                public Descriptor getContainingType() {
                    return this.containingType;
                }

                public EnumOptions getOptions() {
                    return this.proto.getOptions();
                }

                public List<EnumValueDescriptor> getValues() {
                    return Collections.unmodifiableList(Arrays.asList(this.values));
                }

                public EnumValueDescriptor findValueByName(String str) {
                    DescriptorPool access$1400 = this.file.pool;
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.fullName);
                    sb.append('.');
                    sb.append(str);
                    GenericDescriptor findSymbol = access$1400.findSymbol(sb.toString());
                    if (findSymbol == null || !(findSymbol instanceof EnumValueDescriptor)) {
                        return null;
                    }
                    return (EnumValueDescriptor) findSymbol;
                }

                public EnumValueDescriptor findValueByNumber(int i) {
                    return (EnumValueDescriptor) this.file.pool.enumValuesByNumber.get(new DescriptorIntPair(this, i));
                }

                public EnumValueDescriptor findValueByNumberCreatingIfUnknown(int i) {
                    EnumValueDescriptor findValueByNumber = findValueByNumber(i);
                    if (findValueByNumber != null) {
                        return findValueByNumber;
                    }
                    synchronized (this) {
                        Integer num = new Integer(i);
                        WeakReference weakReference = (WeakReference) this.unknownValues.get(num);
                        if (weakReference != null) {
                            findValueByNumber = (EnumValueDescriptor) weakReference.get();
                        }
                        if (findValueByNumber == null) {
                            findValueByNumber = new EnumValueDescriptor(this.file, this, num);
                            this.unknownValues.put(num, new WeakReference(findValueByNumber));
                        }
                    }
                    return findValueByNumber;
                }

                /* access modifiers changed from: 0000 */
                public int getUnknownEnumValueDescriptorCount() {
                    return this.unknownValues.size();
                }

                private EnumDescriptor(EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
                    this.unknownValues = new WeakHashMap<>();
                    this.index = i;
                    this.proto = enumDescriptorProto;
                    this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, enumDescriptorProto.getName());
                    this.file = fileDescriptor;
                    this.containingType = descriptor;
                    if (enumDescriptorProto.getValueCount() != 0) {
                        this.values = new EnumValueDescriptor[enumDescriptorProto.getValueCount()];
                        for (int i2 = 0; i2 < enumDescriptorProto.getValueCount(); i2++) {
                            EnumValueDescriptor[] enumValueDescriptorArr = this.values;
                            EnumValueDescriptor enumValueDescriptor = new EnumValueDescriptor(enumDescriptorProto.getValue(i2), fileDescriptor, this, i2);
                            enumValueDescriptorArr[i2] = enumValueDescriptor;
                        }
                        fileDescriptor.pool.addSymbol(this);
                        return;
                    }
                    throw new DescriptorValidationException((GenericDescriptor) this, "Enums must contain at least one value.");
                }

                /* access modifiers changed from: private */
                public void setProto(EnumDescriptorProto enumDescriptorProto) {
                    this.proto = enumDescriptorProto;
                    int i = 0;
                    while (true) {
                        EnumValueDescriptor[] enumValueDescriptorArr = this.values;
                        if (i < enumValueDescriptorArr.length) {
                            enumValueDescriptorArr[i].setProto(enumDescriptorProto.getValue(i));
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            }

            public static final class EnumValueDescriptor extends GenericDescriptor implements EnumLite {
                private final FileDescriptor file;
                private final String fullName;
                private final int index;
                private Integer number;
                private EnumValueDescriptorProto proto;
                private final EnumDescriptor type;

                public int getIndex() {
                    return this.index;
                }

                public EnumValueDescriptorProto toProto() {
                    return this.proto;
                }

                public String getName() {
                    return this.proto.getName();
                }

                public int getNumber() {
                    return this.proto.getNumber();
                }

                public String toString() {
                    return this.proto.getName();
                }

                public String getFullName() {
                    return this.fullName;
                }

                public FileDescriptor getFile() {
                    return this.file;
                }

                public EnumDescriptor getType() {
                    return this.type;
                }

                public EnumValueOptions getOptions() {
                    return this.proto.getOptions();
                }

                private EnumValueDescriptor(EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i) throws DescriptorValidationException {
                    this.index = i;
                    this.proto = enumValueDescriptorProto;
                    this.file = fileDescriptor;
                    this.type = enumDescriptor;
                    StringBuilder sb = new StringBuilder();
                    sb.append(enumDescriptor.getFullName());
                    sb.append('.');
                    sb.append(enumValueDescriptorProto.getName());
                    this.fullName = sb.toString();
                    fileDescriptor.pool.addSymbol(this);
                    fileDescriptor.pool.addEnumValueByNumber(this);
                }

                private EnumValueDescriptor(FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, Integer num) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("UNKNOWN_ENUM_VALUE_");
                    sb.append(enumDescriptor.getName());
                    sb.append("_");
                    sb.append(num);
                    EnumValueDescriptorProto build = EnumValueDescriptorProto.newBuilder().setName(sb.toString()).setNumber(num.intValue()).build();
                    this.index = -1;
                    this.proto = build;
                    this.file = fileDescriptor;
                    this.type = enumDescriptor;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(enumDescriptor.getFullName());
                    sb2.append('.');
                    sb2.append(build.getName());
                    this.fullName = sb2.toString();
                    this.number = num;
                }

                /* access modifiers changed from: private */
                public void setProto(EnumValueDescriptorProto enumValueDescriptorProto) {
                    this.proto = enumValueDescriptorProto;
                }
            }

            public static final class FieldDescriptor extends GenericDescriptor implements FieldDescriptorLite<FieldDescriptor>, Comparable<FieldDescriptor> {
                private static final FieldType[] table = FieldType.values();
                private OneofDescriptor containingOneof;
                private Descriptor containingType;
                private Object defaultValue;
                private EnumDescriptor enumType;
                private final Descriptor extensionScope;
                private final FileDescriptor file;
                private final String fullName;
                private final int index;
                private final String jsonName;
                private Descriptor messageType;
                private FieldDescriptorProto proto;
                private Type type;

                public enum JavaType {
                    INT(Integer.valueOf(0)),
                    LONG(Long.valueOf(0)),
                    FLOAT(Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
                    DOUBLE(Double.valueOf(0.0d)),
                    BOOLEAN(Boolean.valueOf(false)),
                    STRING(""),
                    BYTE_STRING(ByteString.EMPTY),
                    ENUM(null),
                    MESSAGE(null);
                    
                    /* access modifiers changed from: private */
                    public final Object defaultDefault;

                    private JavaType(Object obj) {
                        this.defaultDefault = obj;
                    }
                }

                public enum Type {
                    DOUBLE(JavaType.DOUBLE),
                    FLOAT(JavaType.FLOAT),
                    INT64(JavaType.LONG),
                    UINT64(JavaType.LONG),
                    INT32(JavaType.INT),
                    FIXED64(JavaType.LONG),
                    FIXED32(JavaType.INT),
                    BOOL(JavaType.BOOLEAN),
                    STRING(JavaType.STRING),
                    GROUP(JavaType.MESSAGE),
                    MESSAGE(JavaType.MESSAGE),
                    BYTES(JavaType.BYTE_STRING),
                    UINT32(JavaType.INT),
                    ENUM(JavaType.ENUM),
                    SFIXED32(JavaType.INT),
                    SFIXED64(JavaType.LONG),
                    SINT32(JavaType.INT),
                    SINT64(JavaType.LONG);
                    
                    private JavaType javaType;

                    private Type(JavaType javaType2) {
                        this.javaType = javaType2;
                    }

                    public com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type toProto() {
                        return com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
                    }

                    public JavaType getJavaType() {
                        return this.javaType;
                    }

                    public static Type valueOf(com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type type) {
                        return values()[type.getNumber() - 1];
                    }
                }

                public int getIndex() {
                    return this.index;
                }

                public FieldDescriptorProto toProto() {
                    return this.proto;
                }

                public String getName() {
                    return this.proto.getName();
                }

                public int getNumber() {
                    return this.proto.getNumber();
                }

                public String getFullName() {
                    return this.fullName;
                }

                public String getJsonName() {
                    return this.jsonName;
                }

                public JavaType getJavaType() {
                    return this.type.getJavaType();
                }

                public com.google.protobuf.WireFormat.JavaType getLiteJavaType() {
                    return getLiteType().getJavaType();
                }

                public FileDescriptor getFile() {
                    return this.file;
                }

                public Type getType() {
                    return this.type;
                }

                public FieldType getLiteType() {
                    return table[this.type.ordinal()];
                }

                public boolean needsUtf8Check() {
                    if (this.type != Type.STRING) {
                        return false;
                    }
                    if (!getContainingType().getOptions().getMapEntry() && getFile().getSyntax() != Syntax.PROTO3) {
                        return getFile().getOptions().getJavaStringCheckUtf8();
                    }
                    return true;
                }

                public boolean isMapField() {
                    return getType() == Type.MESSAGE && isRepeated() && getMessageType().getOptions().getMapEntry();
                }

                static {
                    if (Type.values().length != com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                        throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
                    }
                }

                public boolean isRequired() {
                    return this.proto.getLabel() == Label.LABEL_REQUIRED;
                }

                public boolean isOptional() {
                    return this.proto.getLabel() == Label.LABEL_OPTIONAL;
                }

                public boolean isRepeated() {
                    return this.proto.getLabel() == Label.LABEL_REPEATED;
                }

                public boolean isPacked() {
                    boolean z = false;
                    if (!isPackable()) {
                        return false;
                    }
                    if (getFile().getSyntax() == Syntax.PROTO2) {
                        return getOptions().getPacked();
                    }
                    if (!getOptions().hasPacked() || getOptions().getPacked()) {
                        z = true;
                    }
                    return z;
                }

                public boolean isPackable() {
                    return isRepeated() && getLiteType().isPackable();
                }

                public boolean hasDefaultValue() {
                    return this.proto.hasDefaultValue();
                }

                public Object getDefaultValue() {
                    if (getJavaType() != JavaType.MESSAGE) {
                        return this.defaultValue;
                    }
                    throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
                }

                public FieldOptions getOptions() {
                    return this.proto.getOptions();
                }

                public boolean isExtension() {
                    return this.proto.hasExtendee();
                }

                public Descriptor getContainingType() {
                    return this.containingType;
                }

                public OneofDescriptor getContainingOneof() {
                    return this.containingOneof;
                }

                public Descriptor getExtensionScope() {
                    if (isExtension()) {
                        return this.extensionScope;
                    }
                    throw new UnsupportedOperationException("This field is not an extension.");
                }

                public Descriptor getMessageType() {
                    if (getJavaType() == JavaType.MESSAGE) {
                        return this.messageType;
                    }
                    throw new UnsupportedOperationException("This field is not of message type.");
                }

                public EnumDescriptor getEnumType() {
                    if (getJavaType() == JavaType.ENUM) {
                        return this.enumType;
                    }
                    throw new UnsupportedOperationException("This field is not of enum type.");
                }

                public int compareTo(FieldDescriptor fieldDescriptor) {
                    if (fieldDescriptor.containingType == this.containingType) {
                        return getNumber() - fieldDescriptor.getNumber();
                    }
                    throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
                }

                public String toString() {
                    return getFullName();
                }

                private static String fieldNameToJsonName(String str) {
                    StringBuilder sb = new StringBuilder(str.length());
                    boolean z = false;
                    for (int i = 0; i < str.length(); i++) {
                        Character valueOf = Character.valueOf(str.charAt(i));
                        if (valueOf.charValue() == '_') {
                            z = true;
                        } else if (z) {
                            sb.append(Character.toUpperCase(valueOf.charValue()));
                            z = false;
                        } else {
                            sb.append(valueOf);
                        }
                    }
                    return sb.toString();
                }

                private FieldDescriptor(FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, boolean z) throws DescriptorValidationException {
                    this.index = i;
                    this.proto = fieldDescriptorProto;
                    this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, fieldDescriptorProto.getName());
                    this.file = fileDescriptor;
                    if (fieldDescriptorProto.hasJsonName()) {
                        this.jsonName = fieldDescriptorProto.getJsonName();
                    } else {
                        this.jsonName = fieldNameToJsonName(fieldDescriptorProto.getName());
                    }
                    if (fieldDescriptorProto.hasType()) {
                        this.type = Type.valueOf(fieldDescriptorProto.getType());
                    }
                    if (getNumber() > 0) {
                        if (z) {
                            if (fieldDescriptorProto.hasExtendee()) {
                                this.containingType = null;
                                if (descriptor != null) {
                                    this.extensionScope = descriptor;
                                } else {
                                    this.extensionScope = null;
                                }
                                if (!fieldDescriptorProto.hasOneofIndex()) {
                                    this.containingOneof = null;
                                } else {
                                    throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.oneof_index set for extension field.");
                                }
                            } else {
                                throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.extendee not set for extension field.");
                            }
                        } else if (!fieldDescriptorProto.hasExtendee()) {
                            this.containingType = descriptor;
                            if (!fieldDescriptorProto.hasOneofIndex()) {
                                this.containingOneof = null;
                            } else if (fieldDescriptorProto.getOneofIndex() < 0 || fieldDescriptorProto.getOneofIndex() >= descriptor.toProto().getOneofDeclCount()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("FieldDescriptorProto.oneof_index is out of range for type ");
                                sb.append(descriptor.getName());
                                throw new DescriptorValidationException((GenericDescriptor) this, sb.toString());
                            } else {
                                this.containingOneof = (OneofDescriptor) descriptor.getOneofs().get(fieldDescriptorProto.getOneofIndex());
                                
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x009f: INVOKE  (wrap: com.google.protobuf.Descriptors$OneofDescriptor
                                      0x009d: IGET  (r2v8 com.google.protobuf.Descriptors$OneofDescriptor) = (r1v0 'this' com.google.protobuf.Descriptors$FieldDescriptor A[THIS]) com.google.protobuf.Descriptors.FieldDescriptor.containingOneof com.google.protobuf.Descriptors$OneofDescriptor) com.google.protobuf.Descriptors.OneofDescriptor.access$1908(com.google.protobuf.Descriptors$OneofDescriptor):int type: STATIC in method: com.google.protobuf.Descriptors.FieldDescriptor.<init>(com.google.protobuf.DescriptorProtos$FieldDescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int, boolean):void, dex: classes3.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:148)
                                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:163)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:144)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:163)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:144)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
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
                                    	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
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
                                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                    	... 43 more
                                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                                    	at java.base/java.lang.Class.forName0(Native Method)
                                    	at java.base/java.lang.Class.forName(Unknown Source)
                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                                    	... 61 more
                                    */
                                /*
                                    this = this;
                                    r1.<init>()
                                    r1.index = r5
                                    r1.proto = r2
                                    java.lang.String r5 = r2.getName()
                                    java.lang.String r5 = com.google.protobuf.Descriptors.computeFullName(r3, r4, r5)
                                    r1.fullName = r5
                                    r1.file = r3
                                    boolean r5 = r2.hasJsonName()
                                    if (r5 == 0) goto L_0x0020
                                    java.lang.String r5 = r2.getJsonName()
                                    r1.jsonName = r5
                                    goto L_0x002a
                                L_0x0020:
                                    java.lang.String r5 = r2.getName()
                                    java.lang.String r5 = fieldNameToJsonName(r5)
                                    r1.jsonName = r5
                                L_0x002a:
                                    boolean r5 = r2.hasType()
                                    if (r5 == 0) goto L_0x003a
                                    com.google.protobuf.DescriptorProtos$FieldDescriptorProto$Type r5 = r2.getType()
                                    com.google.protobuf.Descriptors$FieldDescriptor$Type r5 = com.google.protobuf.Descriptors.FieldDescriptor.Type.valueOf(r5)
                                    r1.type = r5
                                L_0x003a:
                                    int r5 = r1.getNumber()
                                    r0 = 0
                                    if (r5 <= 0) goto L_0x00d2
                                    if (r6 == 0) goto L_0x006b
                                    boolean r5 = r2.hasExtendee()
                                    if (r5 == 0) goto L_0x0063
                                    r1.containingType = r0
                                    if (r4 == 0) goto L_0x0050
                                    r1.extensionScope = r4
                                    goto L_0x0052
                                L_0x0050:
                                    r1.extensionScope = r0
                                L_0x0052:
                                    boolean r2 = r2.hasOneofIndex()
                                    if (r2 != 0) goto L_0x005b
                                    r1.containingOneof = r0
                                    goto L_0x00c2
                                L_0x005b:
                                    com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                                    java.lang.String r3 = "FieldDescriptorProto.oneof_index set for extension field."
                                    r2.<init>(r1, r3)
                                    throw r2
                                L_0x0063:
                                    com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                                    java.lang.String r3 = "FieldDescriptorProto.extendee not set for extension field."
                                    r2.<init>(r1, r3)
                                    throw r2
                                L_0x006b:
                                    boolean r5 = r2.hasExtendee()
                                    if (r5 != 0) goto L_0x00ca
                                    r1.containingType = r4
                                    boolean r5 = r2.hasOneofIndex()
                                    if (r5 == 0) goto L_0x00be
                                    int r5 = r2.getOneofIndex()
                                    if (r5 < 0) goto L_0x00a3
                                    int r5 = r2.getOneofIndex()
                                    com.google.protobuf.DescriptorProtos$DescriptorProto r6 = r4.toProto()
                                    int r6 = r6.getOneofDeclCount()
                                    if (r5 >= r6) goto L_0x00a3
                                    java.util.List r4 = r4.getOneofs()
                                    int r2 = r2.getOneofIndex()
                                    java.lang.Object r2 = r4.get(r2)
                                    com.google.protobuf.Descriptors$OneofDescriptor r2 = (com.google.protobuf.Descriptors.OneofDescriptor) r2
                                    r1.containingOneof = r2
                                    com.google.protobuf.Descriptors$OneofDescriptor r2 = r1.containingOneof
                                    
                                    // error: 0x009f: INVOKE  (r2 I:com.google.protobuf.Descriptors$OneofDescriptor) com.google.protobuf.Descriptors.OneofDescriptor.access$1908(com.google.protobuf.Descriptors$OneofDescriptor):int type: STATIC
                                    goto L_0x00c0
                                L_0x00a3:
                                    com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                                    r3.<init>()
                                    java.lang.String r5 = "FieldDescriptorProto.oneof_index is out of range for type "
                                    r3.append(r5)
                                    java.lang.String r4 = r4.getName()
                                    r3.append(r4)
                                    java.lang.String r3 = r3.toString()
                                    r2.<init>(r1, r3)
                                    throw r2
                                L_0x00be:
                                    r1.containingOneof = r0
                                L_0x00c0:
                                    r1.extensionScope = r0
                                L_0x00c2:
                                    com.google.protobuf.Descriptors$DescriptorPool r2 = r3.pool
                                    r2.addSymbol(r1)
                                    return
                                L_0x00ca:
                                    com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                                    java.lang.String r3 = "FieldDescriptorProto.extendee set for non-extension field."
                                    r2.<init>(r1, r3)
                                    throw r2
                                L_0x00d2:
                                    com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                                    java.lang.String r3 = "Field numbers must be positive integers."
                                    r2.<init>(r1, r3)
                                    throw r2
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.FieldDescriptor.<init>(com.google.protobuf.DescriptorProtos$FieldDescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int, boolean):void");
                            }

                            /* access modifiers changed from: private */
                            public void crossLink() throws DescriptorValidationException {
                                if (this.proto.hasExtendee()) {
                                    GenericDescriptor lookupSymbol = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, SearchFilter.TYPES_ONLY);
                                    if (lookupSymbol instanceof Descriptor) {
                                        this.containingType = (Descriptor) lookupSymbol;
                                        if (!getContainingType().isExtensionNumber(getNumber())) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append(Typography.quote);
                                            sb.append(getContainingType().getFullName());
                                            sb.append("\" does not declare ");
                                            sb.append(getNumber());
                                            sb.append(" as an extension number.");
                                            throw new DescriptorValidationException((GenericDescriptor) this, sb.toString());
                                        }
                                    } else {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append(Typography.quote);
                                        sb2.append(this.proto.getExtendee());
                                        sb2.append("\" is not a message type.");
                                        throw new DescriptorValidationException((GenericDescriptor) this, sb2.toString());
                                    }
                                }
                                if (this.proto.hasTypeName()) {
                                    GenericDescriptor lookupSymbol2 = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, SearchFilter.TYPES_ONLY);
                                    if (!this.proto.hasType()) {
                                        if (lookupSymbol2 instanceof Descriptor) {
                                            this.type = Type.MESSAGE;
                                        } else if (lookupSymbol2 instanceof EnumDescriptor) {
                                            this.type = Type.ENUM;
                                        } else {
                                            StringBuilder sb3 = new StringBuilder();
                                            sb3.append(Typography.quote);
                                            sb3.append(this.proto.getTypeName());
                                            sb3.append("\" is not a type.");
                                            throw new DescriptorValidationException((GenericDescriptor) this, sb3.toString());
                                        }
                                    }
                                    if (getJavaType() == JavaType.MESSAGE) {
                                        if (lookupSymbol2 instanceof Descriptor) {
                                            this.messageType = (Descriptor) lookupSymbol2;
                                            if (this.proto.hasDefaultValue()) {
                                                throw new DescriptorValidationException((GenericDescriptor) this, "Messages can't have default values.");
                                            }
                                        } else {
                                            StringBuilder sb4 = new StringBuilder();
                                            sb4.append(Typography.quote);
                                            sb4.append(this.proto.getTypeName());
                                            sb4.append("\" is not a message type.");
                                            throw new DescriptorValidationException((GenericDescriptor) this, sb4.toString());
                                        }
                                    } else if (getJavaType() != JavaType.ENUM) {
                                        throw new DescriptorValidationException((GenericDescriptor) this, "Field with primitive type has type_name.");
                                    } else if (lookupSymbol2 instanceof EnumDescriptor) {
                                        this.enumType = (EnumDescriptor) lookupSymbol2;
                                    } else {
                                        StringBuilder sb5 = new StringBuilder();
                                        sb5.append(Typography.quote);
                                        sb5.append(this.proto.getTypeName());
                                        sb5.append("\" is not an enum type.");
                                        throw new DescriptorValidationException((GenericDescriptor) this, sb5.toString());
                                    }
                                } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
                                    throw new DescriptorValidationException((GenericDescriptor) this, "Field with message or enum type missing type_name.");
                                }
                                if (!this.proto.getOptions().getPacked() || isPackable()) {
                                    if (!this.proto.hasDefaultValue()) {
                                        if (!isRepeated()) {
                                            switch (getJavaType()) {
                                                case ENUM:
                                                    this.defaultValue = this.enumType.getValues().get(0);
                                                    break;
                                                case MESSAGE:
                                                    this.defaultValue = null;
                                                    break;
                                                default:
                                                    this.defaultValue = getJavaType().defaultDefault;
                                                    break;
                                            }
                                        } else {
                                            this.defaultValue = Collections.emptyList();
                                        }
                                    } else if (!isRepeated()) {
                                        try {
                                            switch (getType()) {
                                                case INT32:
                                                case SINT32:
                                                case SFIXED32:
                                                    this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
                                                    break;
                                                case UINT32:
                                                case FIXED32:
                                                    this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
                                                    break;
                                                case INT64:
                                                case SINT64:
                                                case SFIXED64:
                                                    this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
                                                    break;
                                                case UINT64:
                                                case FIXED64:
                                                    this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
                                                    break;
                                                case FLOAT:
                                                    if (!this.proto.getDefaultValue().equals("inf")) {
                                                        if (!this.proto.getDefaultValue().equals("-inf")) {
                                                            if (!this.proto.getDefaultValue().equals("nan")) {
                                                                this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                                                                break;
                                                            } else {
                                                                this.defaultValue = Float.valueOf(Float.NaN);
                                                                break;
                                                            }
                                                        } else {
                                                            this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                                                            break;
                                                        }
                                                    } else {
                                                        this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                                                        break;
                                                    }
                                                case DOUBLE:
                                                    if (!this.proto.getDefaultValue().equals("inf")) {
                                                        if (!this.proto.getDefaultValue().equals("-inf")) {
                                                            if (!this.proto.getDefaultValue().equals("nan")) {
                                                                this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                                                                break;
                                                            } else {
                                                                this.defaultValue = Double.valueOf(Double.NaN);
                                                                break;
                                                            }
                                                        } else {
                                                            this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                                                            break;
                                                        }
                                                    } else {
                                                        this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                                                        break;
                                                    }
                                                case BOOL:
                                                    this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                                                    break;
                                                case STRING:
                                                    this.defaultValue = this.proto.getDefaultValue();
                                                    break;
                                                case BYTES:
                                                    this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                                                    break;
                                                case ENUM:
                                                    this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
                                                    if (this.defaultValue != null) {
                                                        break;
                                                    } else {
                                                        StringBuilder sb6 = new StringBuilder();
                                                        sb6.append("Unknown enum default value: \"");
                                                        sb6.append(this.proto.getDefaultValue());
                                                        sb6.append(Typography.quote);
                                                        throw new DescriptorValidationException((GenericDescriptor) this, sb6.toString());
                                                    }
                                                case MESSAGE:
                                                case GROUP:
                                                    throw new DescriptorValidationException((GenericDescriptor) this, "Message type had default value.");
                                            }
                                        } catch (InvalidEscapeSequenceException e) {
                                            StringBuilder sb7 = new StringBuilder();
                                            sb7.append("Couldn't parse default value: ");
                                            sb7.append(e.getMessage());
                                            throw new DescriptorValidationException(this, sb7.toString(), e);
                                        } catch (NumberFormatException e2) {
                                            StringBuilder sb8 = new StringBuilder();
                                            sb8.append("Could not parse default value: \"");
                                            sb8.append(this.proto.getDefaultValue());
                                            sb8.append(Typography.quote);
                                            throw new DescriptorValidationException(this, sb8.toString(), e2);
                                        }
                                    } else {
                                        throw new DescriptorValidationException((GenericDescriptor) this, "Repeated fields cannot have default values.");
                                    }
                                    if (!isExtension()) {
                                        this.file.pool.addFieldByNumber(this);
                                    }
                                    Descriptor descriptor = this.containingType;
                                    if (descriptor != null && descriptor.getOptions().getMessageSetWireFormat()) {
                                        if (!isExtension()) {
                                            throw new DescriptorValidationException((GenericDescriptor) this, "MessageSets cannot have fields, only extensions.");
                                        } else if (!isOptional() || getType() != Type.MESSAGE) {
                                            throw new DescriptorValidationException((GenericDescriptor) this, "Extensions of MessageSets must be optional messages.");
                                        }
                                    }
                                } else {
                                    throw new DescriptorValidationException((GenericDescriptor) this, "[packed = true] can only be specified for repeated primitive fields.");
                                }
                            }

                            /* access modifiers changed from: private */
                            public void setProto(FieldDescriptorProto fieldDescriptorProto) {
                                this.proto = fieldDescriptorProto;
                            }

                            public Builder internalMergeFrom(Builder builder, MessageLite messageLite) {
                                return ((Message.Builder) builder).mergeFrom((Message) messageLite);
                            }
                        }

                        public static final class FileDescriptor extends GenericDescriptor {
                            private final FileDescriptor[] dependencies;
                            private final EnumDescriptor[] enumTypes;
                            private final FieldDescriptor[] extensions;
                            private final Descriptor[] messageTypes;
                            /* access modifiers changed from: private */
                            public final DescriptorPool pool;
                            private FileDescriptorProto proto;
                            private final FileDescriptor[] publicDependencies;
                            private final ServiceDescriptor[] services;

                            public interface InternalDescriptorAssigner {
                                ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor);
                            }

                            public enum Syntax {
                                UNKNOWN("unknown"),
                                PROTO2("proto2"),
                                PROTO3("proto3");
                                
                                /* access modifiers changed from: private */
                                public final String name;

                                private Syntax(String str) {
                                    this.name = str;
                                }
                            }

                            public FileDescriptor getFile() {
                                return this;
                            }

                            public FileDescriptorProto toProto() {
                                return this.proto;
                            }

                            public String getName() {
                                return this.proto.getName();
                            }

                            public String getFullName() {
                                return this.proto.getName();
                            }

                            public String getPackage() {
                                return this.proto.getPackage();
                            }

                            public FileOptions getOptions() {
                                return this.proto.getOptions();
                            }

                            public List<Descriptor> getMessageTypes() {
                                return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
                            }

                            public List<EnumDescriptor> getEnumTypes() {
                                return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
                            }

                            public List<ServiceDescriptor> getServices() {
                                return Collections.unmodifiableList(Arrays.asList(this.services));
                            }

                            public List<FieldDescriptor> getExtensions() {
                                return Collections.unmodifiableList(Arrays.asList(this.extensions));
                            }

                            public List<FileDescriptor> getDependencies() {
                                return Collections.unmodifiableList(Arrays.asList(this.dependencies));
                            }

                            public List<FileDescriptor> getPublicDependencies() {
                                return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
                            }

                            public Syntax getSyntax() {
                                if (Syntax.PROTO3.name.equals(this.proto.getSyntax())) {
                                    return Syntax.PROTO3;
                                }
                                return Syntax.PROTO2;
                            }

                            public Descriptor findMessageTypeByName(String str) {
                                if (str.indexOf(46) != -1) {
                                    return null;
                                }
                                if (getPackage().length() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(getPackage());
                                    sb.append('.');
                                    sb.append(str);
                                    str = sb.toString();
                                }
                                GenericDescriptor findSymbol = this.pool.findSymbol(str);
                                if (findSymbol == null || !(findSymbol instanceof Descriptor) || findSymbol.getFile() != this) {
                                    return null;
                                }
                                return (Descriptor) findSymbol;
                            }

                            public EnumDescriptor findEnumTypeByName(String str) {
                                if (str.indexOf(46) != -1) {
                                    return null;
                                }
                                if (getPackage().length() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(getPackage());
                                    sb.append('.');
                                    sb.append(str);
                                    str = sb.toString();
                                }
                                GenericDescriptor findSymbol = this.pool.findSymbol(str);
                                if (findSymbol == null || !(findSymbol instanceof EnumDescriptor) || findSymbol.getFile() != this) {
                                    return null;
                                }
                                return (EnumDescriptor) findSymbol;
                            }

                            public ServiceDescriptor findServiceByName(String str) {
                                if (str.indexOf(46) != -1) {
                                    return null;
                                }
                                if (getPackage().length() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(getPackage());
                                    sb.append('.');
                                    sb.append(str);
                                    str = sb.toString();
                                }
                                GenericDescriptor findSymbol = this.pool.findSymbol(str);
                                if (findSymbol == null || !(findSymbol instanceof ServiceDescriptor) || findSymbol.getFile() != this) {
                                    return null;
                                }
                                return (ServiceDescriptor) findSymbol;
                            }

                            public FieldDescriptor findExtensionByName(String str) {
                                if (str.indexOf(46) != -1) {
                                    return null;
                                }
                                if (getPackage().length() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(getPackage());
                                    sb.append('.');
                                    sb.append(str);
                                    str = sb.toString();
                                }
                                GenericDescriptor findSymbol = this.pool.findSymbol(str);
                                if (findSymbol == null || !(findSymbol instanceof FieldDescriptor) || findSymbol.getFile() != this) {
                                    return null;
                                }
                                return (FieldDescriptor) findSymbol;
                            }

                            public static FileDescriptor buildFrom(FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr) throws DescriptorValidationException {
                                return buildFrom(fileDescriptorProto, fileDescriptorArr, false);
                            }

                            public static FileDescriptor buildFrom(FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr, boolean z) throws DescriptorValidationException {
                                FileDescriptor fileDescriptor = new FileDescriptor(fileDescriptorProto, fileDescriptorArr, new DescriptorPool(fileDescriptorArr, z), z);
                                fileDescriptor.crossLink();
                                return fileDescriptor;
                            }

                            public static void internalBuildGeneratedFileFrom(String[] strArr, FileDescriptor[] fileDescriptorArr, InternalDescriptorAssigner internalDescriptorAssigner) {
                                StringBuilder sb = new StringBuilder();
                                for (String append : strArr) {
                                    sb.append(append);
                                }
                                byte[] bytes = sb.toString().getBytes(Internal.ISO_8859_1);
                                try {
                                    FileDescriptorProto parseFrom = FileDescriptorProto.parseFrom(bytes);
                                    try {
                                        FileDescriptor buildFrom = buildFrom(parseFrom, fileDescriptorArr, true);
                                        ExtensionRegistry assignDescriptors = internalDescriptorAssigner.assignDescriptors(buildFrom);
                                        if (assignDescriptors != null) {
                                            try {
                                                buildFrom.setProto(FileDescriptorProto.parseFrom(bytes, (ExtensionRegistryLite) assignDescriptors));
                                            } catch (InvalidProtocolBufferException e) {
                                                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                                            }
                                        }
                                    } catch (DescriptorValidationException e2) {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append("Invalid embedded descriptor for \"");
                                        sb2.append(parseFrom.getName());
                                        sb2.append("\".");
                                        throw new IllegalArgumentException(sb2.toString(), e2);
                                    }
                                } catch (InvalidProtocolBufferException e3) {
                                    throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e3);
                                }
                            }

                            public static void internalBuildGeneratedFileFrom(String[] strArr, Class<?> cls, String[] strArr2, String[] strArr3, InternalDescriptorAssigner internalDescriptorAssigner) {
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < strArr2.length; i++) {
                                    try {
                                        arrayList.add((FileDescriptor) cls.getClassLoader().loadClass(strArr2[i]).getField("descriptor").get(null));
                                    } catch (Exception unused) {
                                        Logger access$100 = Descriptors.logger;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Descriptors for \"");
                                        sb.append(strArr3[i]);
                                        sb.append("\" can not be found.");
                                        access$100.warning(sb.toString());
                                    }
                                }
                                FileDescriptor[] fileDescriptorArr = new FileDescriptor[arrayList.size()];
                                arrayList.toArray(fileDescriptorArr);
                                internalBuildGeneratedFileFrom(strArr, fileDescriptorArr, internalDescriptorAssigner);
                            }

                            public static void internalUpdateFileDescriptor(FileDescriptor fileDescriptor, ExtensionRegistry extensionRegistry) {
                                try {
                                    fileDescriptor.setProto(FileDescriptorProto.parseFrom(fileDescriptor.proto.toByteString(), (ExtensionRegistryLite) extensionRegistry));
                                } catch (InvalidProtocolBufferException e) {
                                    throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                                }
                            }

                            private FileDescriptor(FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr, DescriptorPool descriptorPool, boolean z) throws DescriptorValidationException {
                                this.pool = descriptorPool;
                                this.proto = fileDescriptorProto;
                                this.dependencies = (FileDescriptor[]) fileDescriptorArr.clone();
                                HashMap hashMap = new HashMap();
                                for (FileDescriptor fileDescriptor : fileDescriptorArr) {
                                    hashMap.put(fileDescriptor.getName(), fileDescriptor);
                                }
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < fileDescriptorProto.getPublicDependencyCount(); i++) {
                                    int publicDependency = fileDescriptorProto.getPublicDependency(i);
                                    if (publicDependency < 0 || publicDependency >= fileDescriptorProto.getDependencyCount()) {
                                        throw new DescriptorValidationException(this, "Invalid public dependency index.");
                                    }
                                    String dependency = fileDescriptorProto.getDependency(publicDependency);
                                    FileDescriptor fileDescriptor2 = (FileDescriptor) hashMap.get(dependency);
                                    if (fileDescriptor2 != null) {
                                        arrayList.add(fileDescriptor2);
                                    } else if (!z) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Invalid public dependency: ");
                                        sb.append(dependency);
                                        throw new DescriptorValidationException(this, sb.toString());
                                    }
                                }
                                this.publicDependencies = new FileDescriptor[arrayList.size()];
                                arrayList.toArray(this.publicDependencies);
                                descriptorPool.addPackage(getPackage(), this);
                                this.messageTypes = new Descriptor[fileDescriptorProto.getMessageTypeCount()];
                                for (int i2 = 0; i2 < fileDescriptorProto.getMessageTypeCount(); i2++) {
                                    Descriptor[] descriptorArr = this.messageTypes;
                                    Descriptor descriptor = new Descriptor(fileDescriptorProto.getMessageType(i2), this, null, i2);
                                    descriptorArr[i2] = descriptor;
                                }
                                this.enumTypes = new EnumDescriptor[fileDescriptorProto.getEnumTypeCount()];
                                for (int i3 = 0; i3 < fileDescriptorProto.getEnumTypeCount(); i3++) {
                                    EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                                    EnumDescriptor enumDescriptor = new EnumDescriptor(fileDescriptorProto.getEnumType(i3), this, null, i3);
                                    enumDescriptorArr[i3] = enumDescriptor;
                                }
                                this.services = new ServiceDescriptor[fileDescriptorProto.getServiceCount()];
                                for (int i4 = 0; i4 < fileDescriptorProto.getServiceCount(); i4++) {
                                    this.services[i4] = new ServiceDescriptor(fileDescriptorProto.getService(i4), this, i4);
                                }
                                this.extensions = new FieldDescriptor[fileDescriptorProto.getExtensionCount()];
                                for (int i5 = 0; i5 < fileDescriptorProto.getExtensionCount(); i5++) {
                                    FieldDescriptor[] fieldDescriptorArr = this.extensions;
                                    FieldDescriptor fieldDescriptor = new FieldDescriptor(fileDescriptorProto.getExtension(i5), this, null, i5, true);
                                    fieldDescriptorArr[i5] = fieldDescriptor;
                                }
                            }

                            FileDescriptor(String str, Descriptor descriptor) throws DescriptorValidationException {
                                this.pool = new DescriptorPool(new FileDescriptor[0], true);
                                FileDescriptorProto.Builder newBuilder = FileDescriptorProto.newBuilder();
                                StringBuilder sb = new StringBuilder();
                                sb.append(descriptor.getFullName());
                                sb.append(".placeholder.proto");
                                this.proto = newBuilder.setName(sb.toString()).setPackage(str).addMessageType(descriptor.toProto()).build();
                                this.dependencies = new FileDescriptor[0];
                                this.publicDependencies = new FileDescriptor[0];
                                this.messageTypes = new Descriptor[]{descriptor};
                                this.enumTypes = new EnumDescriptor[0];
                                this.services = new ServiceDescriptor[0];
                                this.extensions = new FieldDescriptor[0];
                                this.pool.addPackage(str, this);
                                this.pool.addSymbol(descriptor);
                            }

                            private void crossLink() throws DescriptorValidationException {
                                for (Descriptor access$700 : this.messageTypes) {
                                    access$700.crossLink();
                                }
                                for (ServiceDescriptor access$800 : this.services) {
                                    access$800.crossLink();
                                }
                                for (FieldDescriptor access$900 : this.extensions) {
                                    access$900.crossLink();
                                }
                            }

                            private void setProto(FileDescriptorProto fileDescriptorProto) {
                                this.proto = fileDescriptorProto;
                                int i = 0;
                                int i2 = 0;
                                while (true) {
                                    Descriptor[] descriptorArr = this.messageTypes;
                                    if (i2 >= descriptorArr.length) {
                                        break;
                                    }
                                    descriptorArr[i2].setProto(fileDescriptorProto.getMessageType(i2));
                                    i2++;
                                }
                                int i3 = 0;
                                while (true) {
                                    EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                                    if (i3 >= enumDescriptorArr.length) {
                                        break;
                                    }
                                    enumDescriptorArr[i3].setProto(fileDescriptorProto.getEnumType(i3));
                                    i3++;
                                }
                                int i4 = 0;
                                while (true) {
                                    ServiceDescriptor[] serviceDescriptorArr = this.services;
                                    if (i4 >= serviceDescriptorArr.length) {
                                        break;
                                    }
                                    serviceDescriptorArr[i4].setProto(fileDescriptorProto.getService(i4));
                                    i4++;
                                }
                                while (true) {
                                    FieldDescriptor[] fieldDescriptorArr = this.extensions;
                                    if (i < fieldDescriptorArr.length) {
                                        fieldDescriptorArr[i].setProto(fileDescriptorProto.getExtension(i));
                                        i++;
                                    } else {
                                        return;
                                    }
                                }
                            }

                            /* access modifiers changed from: 0000 */
                            public boolean supportsUnknownEnumValue() {
                                return getSyntax() == Syntax.PROTO3;
                            }
                        }

                        public static abstract class GenericDescriptor {
                            public abstract FileDescriptor getFile();

                            public abstract String getFullName();

                            public abstract String getName();

                            public abstract Message toProto();
                        }

                        public static final class MethodDescriptor extends GenericDescriptor {
                            private final FileDescriptor file;
                            private final String fullName;
                            private final int index;
                            private Descriptor inputType;
                            private Descriptor outputType;
                            private MethodDescriptorProto proto;
                            private final ServiceDescriptor service;

                            public int getIndex() {
                                return this.index;
                            }

                            public MethodDescriptorProto toProto() {
                                return this.proto;
                            }

                            public String getName() {
                                return this.proto.getName();
                            }

                            public String getFullName() {
                                return this.fullName;
                            }

                            public FileDescriptor getFile() {
                                return this.file;
                            }

                            public ServiceDescriptor getService() {
                                return this.service;
                            }

                            public Descriptor getInputType() {
                                return this.inputType;
                            }

                            public Descriptor getOutputType() {
                                return this.outputType;
                            }

                            public MethodOptions getOptions() {
                                return this.proto.getOptions();
                            }

                            private MethodDescriptor(MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i) throws DescriptorValidationException {
                                this.index = i;
                                this.proto = methodDescriptorProto;
                                this.file = fileDescriptor;
                                this.service = serviceDescriptor;
                                StringBuilder sb = new StringBuilder();
                                sb.append(serviceDescriptor.getFullName());
                                sb.append('.');
                                sb.append(methodDescriptorProto.getName());
                                this.fullName = sb.toString();
                                fileDescriptor.pool.addSymbol(this);
                            }

                            /* access modifiers changed from: private */
                            public void crossLink() throws DescriptorValidationException {
                                GenericDescriptor lookupSymbol = this.file.pool.lookupSymbol(this.proto.getInputType(), this, SearchFilter.TYPES_ONLY);
                                if (lookupSymbol instanceof Descriptor) {
                                    this.inputType = (Descriptor) lookupSymbol;
                                    GenericDescriptor lookupSymbol2 = this.file.pool.lookupSymbol(this.proto.getOutputType(), this, SearchFilter.TYPES_ONLY);
                                    if (lookupSymbol2 instanceof Descriptor) {
                                        this.outputType = (Descriptor) lookupSymbol2;
                                        return;
                                    }
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(Typography.quote);
                                    sb.append(this.proto.getOutputType());
                                    sb.append("\" is not a message type.");
                                    throw new DescriptorValidationException((GenericDescriptor) this, sb.toString());
                                }
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(Typography.quote);
                                sb2.append(this.proto.getInputType());
                                sb2.append("\" is not a message type.");
                                throw new DescriptorValidationException((GenericDescriptor) this, sb2.toString());
                            }

                            /* access modifiers changed from: private */
                            public void setProto(MethodDescriptorProto methodDescriptorProto) {
                                this.proto = methodDescriptorProto;
                            }
                        }

                        public static final class OneofDescriptor {
                            private Descriptor containingType;
                            /* access modifiers changed from: private */
                            public int fieldCount;
                            /* access modifiers changed from: private */
                            public FieldDescriptor[] fields;
                            private final FileDescriptor file;
                            private final String fullName;
                            private final int index;
                            private OneofDescriptorProto proto;

                            public int getIndex() {
                                return this.index;
                            }

                            public String getName() {
                                return this.proto.getName();
                            }

                            public FileDescriptor getFile() {
                                return this.file;
                            }

                            public String getFullName() {
                                return this.fullName;
                            }

                            public Descriptor getContainingType() {
                                return this.containingType;
                            }

                            public int getFieldCount() {
                                return this.fieldCount;
                            }

                            public OneofOptions getOptions() {
                                return this.proto.getOptions();
                            }

                            public List<FieldDescriptor> getFields() {
                                return Collections.unmodifiableList(Arrays.asList(this.fields));
                            }

                            public FieldDescriptor getField(int i) {
                                return this.fields[i];
                            }

                            /* access modifiers changed from: private */
                            public void setProto(OneofDescriptorProto oneofDescriptorProto) {
                                this.proto = oneofDescriptorProto;
                            }

                            private OneofDescriptor(OneofDescriptorProto oneofDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
                                this.proto = oneofDescriptorProto;
                                this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, oneofDescriptorProto.getName());
                                this.file = fileDescriptor;
                                this.index = i;
                                this.containingType = descriptor;
                                this.fieldCount = 0;
                            }
                        }

                        public static final class ServiceDescriptor extends GenericDescriptor {
                            private final FileDescriptor file;
                            private final String fullName;
                            private final int index;
                            private MethodDescriptor[] methods;
                            private ServiceDescriptorProto proto;

                            public int getIndex() {
                                return this.index;
                            }

                            public ServiceDescriptorProto toProto() {
                                return this.proto;
                            }

                            public String getName() {
                                return this.proto.getName();
                            }

                            public String getFullName() {
                                return this.fullName;
                            }

                            public FileDescriptor getFile() {
                                return this.file;
                            }

                            public ServiceOptions getOptions() {
                                return this.proto.getOptions();
                            }

                            public List<MethodDescriptor> getMethods() {
                                return Collections.unmodifiableList(Arrays.asList(this.methods));
                            }

                            public MethodDescriptor findMethodByName(String str) {
                                DescriptorPool access$1400 = this.file.pool;
                                StringBuilder sb = new StringBuilder();
                                sb.append(this.fullName);
                                sb.append('.');
                                sb.append(str);
                                GenericDescriptor findSymbol = access$1400.findSymbol(sb.toString());
                                if (findSymbol == null || !(findSymbol instanceof MethodDescriptor)) {
                                    return null;
                                }
                                return (MethodDescriptor) findSymbol;
                            }

                            private ServiceDescriptor(ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i) throws DescriptorValidationException {
                                this.index = i;
                                this.proto = serviceDescriptorProto;
                                this.fullName = Descriptors.computeFullName(fileDescriptor, null, serviceDescriptorProto.getName());
                                this.file = fileDescriptor;
                                this.methods = new MethodDescriptor[serviceDescriptorProto.getMethodCount()];
                                for (int i2 = 0; i2 < serviceDescriptorProto.getMethodCount(); i2++) {
                                    MethodDescriptor[] methodDescriptorArr = this.methods;
                                    MethodDescriptor methodDescriptor = new MethodDescriptor(serviceDescriptorProto.getMethod(i2), fileDescriptor, this, i2);
                                    methodDescriptorArr[i2] = methodDescriptor;
                                }
                                fileDescriptor.pool.addSymbol(this);
                            }

                            /* access modifiers changed from: private */
                            public void crossLink() throws DescriptorValidationException {
                                for (MethodDescriptor access$2900 : this.methods) {
                                    access$2900.crossLink();
                                }
                            }

                            /* access modifiers changed from: private */
                            public void setProto(ServiceDescriptorProto serviceDescriptorProto) {
                                this.proto = serviceDescriptorProto;
                                int i = 0;
                                while (true) {
                                    MethodDescriptor[] methodDescriptorArr = this.methods;
                                    if (i < methodDescriptorArr.length) {
                                        methodDescriptorArr[i].setProto(serviceDescriptorProto.getMethod(i));
                                        i++;
                                    } else {
                                        return;
                                    }
                                }
                            }
                        }

                        /* access modifiers changed from: private */
                        public static String computeFullName(FileDescriptor fileDescriptor, Descriptor descriptor, String str) {
                            if (descriptor != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(descriptor.getFullName());
                                sb.append('.');
                                sb.append(str);
                                return sb.toString();
                            } else if (fileDescriptor.getPackage().length() <= 0) {
                                return str;
                            } else {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(fileDescriptor.getPackage());
                                sb2.append('.');
                                sb2.append(str);
                                return sb2.toString();
                            }
                        }
                    }
