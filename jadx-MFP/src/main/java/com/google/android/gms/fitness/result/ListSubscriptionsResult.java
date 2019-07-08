package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "ListSubscriptionsResultCreator")
@Reserved({3, 1000})
public class ListSubscriptionsResult extends AbstractSafeParcelable implements Result {
    public static final Creator<ListSubscriptionsResult> CREATOR = new zzg();
    @Field(getter = "getStatus", id = 2)
    private final Status zzir;
    @Field(getter = "getSubscriptions", id = 1)
    private final List<Subscription> zziv;

    @Constructor
    public ListSubscriptionsResult(@Param(id = 1) List<Subscription> list, @Param(id = 2) Status status) {
        this.zziv = list;
        this.zzir = status;
    }

    public static ListSubscriptionsResult zzd(Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    public List<Subscription> getSubscriptions() {
        return this.zziv;
    }

    public List<Subscription> getSubscriptions(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription subscription : this.zziv) {
            if (dataType.equals(subscription.zzq())) {
                arrayList.add(subscription);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ListSubscriptionsResult) {
                ListSubscriptionsResult listSubscriptionsResult = (ListSubscriptionsResult) obj;
                if (this.zzir.equals(listSubscriptionsResult.zzir) && Objects.equal(this.zziv, listSubscriptionsResult.zziv)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zziv);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("subscriptions", this.zziv).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSubscriptions(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
