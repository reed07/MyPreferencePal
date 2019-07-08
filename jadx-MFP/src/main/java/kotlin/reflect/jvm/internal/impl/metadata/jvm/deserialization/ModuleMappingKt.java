package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.text.StringsKt;

/* compiled from: ModuleMapping.kt */
public final class ModuleMappingKt {
    /* access modifiers changed from: private */
    public static final String internalNameOf(String str, String str2) {
        if (str.length() == 0) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(StringsKt.replace$default(str, '.', '/', false, 4, (Object) null));
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }
}
