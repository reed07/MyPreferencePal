package io.grpc;

import com.google.common.base.MoreObjects;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import com.myfitnesspal.shared.model.v2.MfpImage.Status;
import io.grpc.LoadBalancer.Factory;

@ExperimentalApi
public abstract class LoadBalancerProvider extends Factory {
    public abstract String getPolicyName();

    public abstract int getPriority();

    public abstract boolean isAvailable();

    public final String toString() {
        return MoreObjects.toStringHelper((Object) this).add("policy", (Object) getPolicyName()).add(Columns.PRIORITY, getPriority()).add(Status.AVAILABLE, isAvailable()).toString();
    }
}
