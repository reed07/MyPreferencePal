package kotlin.reflect.jvm.internal.impl.protobuf;

import com.myfitnesspal.shared.constants.Constants.BusyStates;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;

public class ExtensionRegistryLite {
    private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);
    private static volatile boolean eagerlyParseMessageSets = false;
    private final Map<ObjectIntPair, GeneratedExtension<?, ?>> extensionsByNumber;

    private static final class ObjectIntPair {
        private final int number;
        private final Object object;

        ObjectIntPair(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public int hashCode() {
            return (System.identityHashCode(this.object) * BusyStates.ALL) + this.number;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair objectIntPair = (ObjectIntPair) obj;
            if (this.object == objectIntPair.object && this.number == objectIntPair.number) {
                z = true;
            }
            return z;
        }
    }

    public static ExtensionRegistryLite newInstance() {
        return new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        return EMPTY;
    }

    public <ContainingType extends MessageLite> GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingtype, int i) {
        return (GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(containingtype, i));
    }

    public final void add(GeneratedExtension<?, ?> generatedExtension) {
        this.extensionsByNumber.put(new ObjectIntPair(generatedExtension.getContainingTypeDefaultInstance(), generatedExtension.getNumber()), generatedExtension);
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    private ExtensionRegistryLite(boolean z) {
        this.extensionsByNumber = Collections.emptyMap();
    }
}
