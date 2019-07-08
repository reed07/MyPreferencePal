package io.grpc;

import com.google.common.base.Preconditions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExperimentalApi
public final class EquivalentAddressGroup {
    private final List<SocketAddress> addrs;
    private final Attributes attrs;
    private final int hashCode;

    @ExperimentalApi
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Attr {
    }

    public EquivalentAddressGroup(List<SocketAddress> list) {
        this(list, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(List<SocketAddress> list, Attributes attributes) {
        Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
        this.addrs = Collections.unmodifiableList(new ArrayList(list));
        this.attrs = (Attributes) Preconditions.checkNotNull(attributes, "attrs");
        this.hashCode = this.addrs.hashCode();
    }

    public EquivalentAddressGroup(SocketAddress socketAddress) {
        this(socketAddress, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(SocketAddress socketAddress, Attributes attributes) {
        this(Collections.singletonList(socketAddress), attributes);
    }

    public List<SocketAddress> getAddresses() {
        return this.addrs;
    }

    public Attributes getAttributes() {
        return this.attrs;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.addrs);
        sb.append("/");
        sb.append(this.attrs);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EquivalentAddressGroup)) {
            return false;
        }
        EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) obj;
        if (this.addrs.size() != equivalentAddressGroup.addrs.size()) {
            return false;
        }
        for (int i = 0; i < this.addrs.size(); i++) {
            if (!((SocketAddress) this.addrs.get(i)).equals(equivalentAddressGroup.addrs.get(i))) {
                return false;
            }
        }
        if (!this.attrs.equals(equivalentAddressGroup.attrs)) {
            return false;
        }
        return true;
    }
}
