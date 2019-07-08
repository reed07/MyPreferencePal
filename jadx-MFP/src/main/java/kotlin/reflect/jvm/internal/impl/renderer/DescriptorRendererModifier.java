package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRenderer.kt */
public enum DescriptorRendererModifier {
    VISIBILITY(true),
    MODALITY(true),
    OVERRIDE(true),
    ANNOTATIONS(false),
    INNER(true),
    MEMBER_KIND(true),
    DATA(true),
    INLINE(true),
    EXPECT(true),
    ACTUAL(true);
    
    @NotNull
    @JvmField
    public static final Set<DescriptorRendererModifier> ALL = null;
    public static final Companion Companion = null;
    @NotNull
    @JvmField
    public static final Set<DescriptorRendererModifier> DEFAULTS = null;
    private final boolean includeByDefault;

    /* compiled from: DescriptorRenderer.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    protected DescriptorRendererModifier(boolean z) {
        this.includeByDefault = z;
    }

    static {
        int i;
        Companion = new Companion(null);
        DescriptorRendererModifier[] values = values();
        Collection arrayList = new ArrayList();
        for (DescriptorRendererModifier descriptorRendererModifier : values) {
            if (descriptorRendererModifier.includeByDefault) {
                arrayList.add(descriptorRendererModifier);
            }
        }
        DEFAULTS = CollectionsKt.toSet((List) arrayList);
        ALL = ArraysKt.toSet((T[]) values());
    }
}
