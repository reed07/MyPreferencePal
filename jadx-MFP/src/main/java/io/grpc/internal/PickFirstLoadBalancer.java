package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancer.Helper;
import io.grpc.LoadBalancer.PickResult;
import io.grpc.LoadBalancer.PickSubchannelArgs;
import io.grpc.LoadBalancer.Subchannel;
import io.grpc.LoadBalancer.SubchannelPicker;
import io.grpc.Status;
import java.util.List;

final class PickFirstLoadBalancer extends LoadBalancer {
    private final Helper helper;
    private Subchannel subchannel;

    private static final class Picker extends SubchannelPicker {
        private final PickResult result;

        Picker(PickResult pickResult) {
            this.result = (PickResult) Preconditions.checkNotNull(pickResult, "result");
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return this.result;
        }
    }

    private static final class RequestConnectionPicker extends SubchannelPicker {
        private final Subchannel subchannel;

        RequestConnectionPicker(Subchannel subchannel2) {
            this.subchannel = (Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel");
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            this.subchannel.requestConnection();
            return PickResult.withNoResult();
        }
    }

    PickFirstLoadBalancer(Helper helper2) {
        this.helper = (Helper) Preconditions.checkNotNull(helper2, "helper");
    }

    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        Subchannel subchannel2 = this.subchannel;
        if (subchannel2 == null) {
            this.subchannel = this.helper.createSubchannel(list, Attributes.EMPTY);
            this.helper.updateBalancingState(ConnectivityState.CONNECTING, new Picker(PickResult.withSubchannel(this.subchannel)));
            this.subchannel.requestConnection();
            return;
        }
        this.helper.updateSubchannelAddresses(subchannel2, list);
    }

    public void handleNameResolutionError(Status status) {
        Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
            this.subchannel = null;
        }
        this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(PickResult.withError(status)));
    }

    public void handleSubchannelState(Subchannel subchannel2, ConnectivityStateInfo connectivityStateInfo) {
        SubchannelPicker subchannelPicker;
        ConnectivityState state = connectivityStateInfo.getState();
        if (subchannel2 == this.subchannel && state != ConnectivityState.SHUTDOWN) {
            switch (state) {
                case IDLE:
                    subchannelPicker = new RequestConnectionPicker(subchannel2);
                    break;
                case CONNECTING:
                    subchannelPicker = new Picker(PickResult.withNoResult());
                    break;
                case READY:
                    subchannelPicker = new Picker(PickResult.withSubchannel(subchannel2));
                    break;
                case TRANSIENT_FAILURE:
                    subchannelPicker = new Picker(PickResult.withError(connectivityStateInfo.getStatus()));
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unsupported state:");
                    sb.append(state);
                    throw new IllegalArgumentException(sb.toString());
            }
            this.helper.updateBalancingState(state, subchannelPicker);
        }
    }

    public void shutdown() {
        Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
        }
    }
}
