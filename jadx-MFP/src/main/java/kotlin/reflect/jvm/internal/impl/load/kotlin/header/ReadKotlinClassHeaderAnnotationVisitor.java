package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.ClassLiteralId;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmBytecodeBinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReadKotlinClassHeaderAnnotationVisitor implements AnnotationVisitor {
    private static final Map<ClassId, Kind> HEADER_KINDS = new HashMap();
    private static final boolean IGNORE_OLD_METADATA = "true".equals(System.getProperty("kotlin.ignore.old.metadata"));
    /* access modifiers changed from: private */
    public JvmBytecodeBinaryVersion bytecodeVersion = null;
    /* access modifiers changed from: private */
    public String[] data = null;
    /* access modifiers changed from: private */
    public int extraInt = 0;
    /* access modifiers changed from: private */
    public String extraString = null;
    /* access modifiers changed from: private */
    public Kind headerKind = null;
    private String[] incompatibleData = null;
    /* access modifiers changed from: private */
    public int[] metadataVersionArray = null;
    /* access modifiers changed from: private */
    public String packageName = null;
    /* access modifiers changed from: private */
    public String[] strings = null;

    private static abstract class CollectStringArrayAnnotationVisitor implements AnnotationArrayArgumentVisitor {
        private final List<String> strings = new ArrayList();

        public void visitClassLiteral(@NotNull ClassLiteralId classLiteralId) {
        }

        /* access modifiers changed from: protected */
        public abstract void visitEnd(@NotNull String[] strArr);

        public void visitEnum(@NotNull ClassId classId, @NotNull Name name) {
        }

        public void visit(@Nullable Object obj) {
            if (obj instanceof String) {
                this.strings.add((String) obj);
            }
        }

        public void visitEnd() {
            List<String> list = this.strings;
            visitEnd((String[]) list.toArray(new String[list.size()]));
        }
    }

    private class KotlinMetadataArgumentVisitor implements AnnotationArgumentVisitor {
        @Nullable
        public AnnotationArgumentVisitor visitAnnotation(@NotNull Name name, @NotNull ClassId classId) {
            return null;
        }

        public void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralId classLiteralId) {
        }

        public void visitEnd() {
        }

        public void visitEnum(@NotNull Name name, @NotNull ClassId classId, @NotNull Name name2) {
        }

        private KotlinMetadataArgumentVisitor() {
        }

        public void visit(@Nullable Name name, @Nullable Object obj) {
            if (name != null) {
                String asString = name.asString();
                if ("k".equals(asString)) {
                    if (obj instanceof Integer) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.headerKind = Kind.getById(((Integer) obj).intValue());
                    }
                } else if ("mv".equals(asString)) {
                    if (obj instanceof int[]) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersionArray = (int[]) obj;
                    }
                } else if ("bv".equals(asString)) {
                    if (obj instanceof int[]) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.bytecodeVersion = new JvmBytecodeBinaryVersion((int[]) obj);
                    }
                } else if ("xs".equals(asString)) {
                    if (obj instanceof String) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.extraString = (String) obj;
                    }
                } else if ("xi".equals(asString)) {
                    if (obj instanceof Integer) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.extraInt = ((Integer) obj).intValue();
                    }
                } else if ("pn".equals(asString) && (obj instanceof String)) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.packageName = (String) obj;
                }
            }
        }

        @Nullable
        public AnnotationArrayArgumentVisitor visitArray(@NotNull Name name) {
            String asString = name.asString();
            if ("d1".equals(asString)) {
                return dataArrayVisitor();
            }
            if ("d2".equals(asString)) {
                return stringsArrayVisitor();
            }
            return null;
        }

        @NotNull
        private AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                /* access modifiers changed from: protected */
                public void visitEnd(@NotNull String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = strArr;
                }
            };
        }

        @NotNull
        private AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                /* access modifiers changed from: protected */
                public void visitEnd(@NotNull String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = strArr;
                }
            };
        }
    }

    private class OldDeprecatedAnnotationArgumentVisitor implements AnnotationArgumentVisitor {
        @Nullable
        public AnnotationArgumentVisitor visitAnnotation(@NotNull Name name, @NotNull ClassId classId) {
            return null;
        }

        public void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralId classLiteralId) {
        }

        public void visitEnd() {
        }

        public void visitEnum(@NotNull Name name, @NotNull ClassId classId, @NotNull Name name2) {
        }

        private OldDeprecatedAnnotationArgumentVisitor() {
        }

        public void visit(@Nullable Name name, @Nullable Object obj) {
            if (name != null) {
                String asString = name.asString();
                if ("version".equals(asString)) {
                    if (obj instanceof int[]) {
                        int[] iArr = (int[]) obj;
                        ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersionArray = iArr;
                        if (ReadKotlinClassHeaderAnnotationVisitor.this.bytecodeVersion == null) {
                            ReadKotlinClassHeaderAnnotationVisitor.this.bytecodeVersion = new JvmBytecodeBinaryVersion(iArr);
                        }
                    }
                } else if ("multifileClassName".equals(asString)) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.extraString = obj instanceof String ? (String) obj : null;
                }
            }
        }

        @Nullable
        public AnnotationArrayArgumentVisitor visitArray(@NotNull Name name) {
            String asString = name.asString();
            if ("data".equals(asString) || "filePartClassNames".equals(asString)) {
                return dataArrayVisitor();
            }
            if ("strings".equals(asString)) {
                return stringsArrayVisitor();
            }
            return null;
        }

        @NotNull
        private AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                /* access modifiers changed from: protected */
                public void visitEnd(@NotNull String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = strArr;
                }
            };
        }

        @NotNull
        private AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                /* access modifiers changed from: protected */
                public void visitEnd(@NotNull String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = strArr;
                }
            };
        }
    }

    public void visitEnd() {
    }

    static {
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinClass")), Kind.CLASS);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinFileFacade")), Kind.FILE_FACADE);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClass")), Kind.MULTIFILE_CLASS);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClassPart")), Kind.MULTIFILE_CLASS_PART);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinSyntheticClass")), Kind.SYNTHETIC_CLASS);
    }

    @Nullable
    public KotlinClassHeader createHeader() {
        if (this.headerKind != null) {
            int[] iArr = this.metadataVersionArray;
            if (iArr != null) {
                JvmMetadataVersion jvmMetadataVersion = new JvmMetadataVersion(iArr, (this.extraInt & 8) != 0);
                if (!jvmMetadataVersion.isCompatible()) {
                    this.incompatibleData = this.data;
                    this.data = null;
                } else if (shouldHaveData() && this.data == null) {
                    return null;
                }
                Kind kind = this.headerKind;
                JvmBytecodeBinaryVersion jvmBytecodeBinaryVersion = this.bytecodeVersion;
                if (jvmBytecodeBinaryVersion == null) {
                    jvmBytecodeBinaryVersion = JvmBytecodeBinaryVersion.INVALID_VERSION;
                }
                KotlinClassHeader kotlinClassHeader = new KotlinClassHeader(kind, jvmMetadataVersion, jvmBytecodeBinaryVersion, this.data, this.incompatibleData, this.strings, this.extraString, this.extraInt, this.packageName);
                return kotlinClassHeader;
            }
        }
        return null;
    }

    private boolean shouldHaveData() {
        return this.headerKind == Kind.CLASS || this.headerKind == Kind.FILE_FACADE || this.headerKind == Kind.MULTIFILE_CLASS_PART;
    }

    @Nullable
    public AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement) {
        if (classId.asSingleFqName().equals(JvmAnnotationNames.METADATA_FQ_NAME)) {
            return new KotlinMetadataArgumentVisitor();
        }
        if (IGNORE_OLD_METADATA || this.headerKind != null) {
            return null;
        }
        Kind kind = (Kind) HEADER_KINDS.get(classId);
        if (kind == null) {
            return null;
        }
        this.headerKind = kind;
        return new OldDeprecatedAnnotationArgumentVisitor();
    }
}
