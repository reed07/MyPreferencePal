package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
public final class JvmBuiltInsSettings$getJdkMethodStatus$2 extends AbstractNodeHandler<ClassDescriptor, JDKMemberStatus> {
    final /* synthetic */ String $jvmDescriptor;
    final /* synthetic */ ObjectRef $result;

    JvmBuiltInsSettings$getJdkMethodStatus$2(String str, ObjectRef objectRef) {
        this.$jvmDescriptor = str;
        this.$result = objectRef;
    }

    public boolean beforeChildren(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "javaClassDescriptor");
        String signature = SignatureBuildingComponents.INSTANCE.signature(classDescriptor, this.$jvmDescriptor);
        if (JvmBuiltInsSettings.Companion.getBLACK_LIST_METHOD_SIGNATURES().contains(signature)) {
            this.$result.element = JDKMemberStatus.BLACK_LIST;
        } else if (JvmBuiltInsSettings.Companion.getWHITE_LIST_METHOD_SIGNATURES().contains(signature)) {
            this.$result.element = JDKMemberStatus.WHITE_LIST;
        } else if (JvmBuiltInsSettings.Companion.getDROP_LIST_METHOD_SIGNATURES().contains(signature)) {
            this.$result.element = JDKMemberStatus.DROP;
        }
        return ((JDKMemberStatus) this.$result.element) == null;
    }

    @NotNull
    public JDKMemberStatus result() {
        JDKMemberStatus jDKMemberStatus = (JDKMemberStatus) this.$result.element;
        return jDKMemberStatus != null ? jDKMemberStatus : JDKMemberStatus.NOT_CONSIDERED;
    }
}
