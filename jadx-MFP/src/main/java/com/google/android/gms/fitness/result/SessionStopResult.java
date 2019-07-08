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
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;

@Class(creator = "SessionStopResultCreator")
@Reserved({4, 1000})
public class SessionStopResult extends AbstractSafeParcelable implements Result {
    public static final Creator<SessionStopResult> CREATOR = new zzi();
    @Field(getter = "getSessions", id = 3)
    private final List<Session> zzgn;
    @Field(getter = "getStatus", id = 2)
    private final Status zzir;

    @Constructor
    public SessionStopResult(@Param(id = 2) Status status, @Param(id = 3) List<Session> list) {
        this.zzir = status;
        this.zzgn = Collections.unmodifiableList(list);
    }

    public Status getStatus() {
        return this.zzir;
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof SessionStopResult) {
                SessionStopResult sessionStopResult = (SessionStopResult) obj;
                if (this.zzir.equals(sessionStopResult.zzir) && Objects.equal(this.zzgn, sessionStopResult.zzgn)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzgn);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("sessions", this.zzgn).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getSessions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
