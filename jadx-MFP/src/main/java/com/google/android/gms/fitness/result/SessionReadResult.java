package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "SessionReadResultCreator")
@Reserved({4, 1000})
public class SessionReadResult extends AbstractSafeParcelable implements Result {
    public static final Creator<SessionReadResult> CREATOR = new zzh();
    @Field(getter = "getSessions", id = 1)
    private final List<Session> zzgn;
    @Field(getter = "getStatus", id = 3)
    private final Status zzir;
    @Field(getter = "getSessionDataSets", id = 2)
    private final List<zzae> zziw;

    @Constructor
    public SessionReadResult(@Param(id = 1) List<Session> list, @Param(id = 2) List<zzae> list2, @Param(id = 3) Status status) {
        this.zzgn = list;
        this.zziw = Collections.unmodifiableList(list2);
        this.zzir = status;
    }

    public static SessionReadResult zze(Status status) {
        return new SessionReadResult(new ArrayList(), new ArrayList(), status);
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        Preconditions.checkArgument(this.zzgn.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzae : this.zziw) {
            if (Objects.equal(session, zzae.getSession()) && dataType.equals(zzae.getDataSet().getDataType())) {
                arrayList.add(zzae.getDataSet());
            }
        }
        return arrayList;
    }

    public List<DataSet> getDataSet(Session session) {
        Preconditions.checkArgument(this.zzgn.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzae : this.zziw) {
            if (Objects.equal(session, zzae.getSession())) {
                arrayList.add(zzae.getDataSet());
            }
        }
        return arrayList;
    }

    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof SessionReadResult) {
                SessionReadResult sessionReadResult = (SessionReadResult) obj;
                if (this.zzir.equals(sessionReadResult.zzir) && Objects.equal(this.zzgn, sessionReadResult.zzgn) && Objects.equal(this.zziw, sessionReadResult.zziw)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzgn, this.zziw);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("sessions", this.zzgn).add("sessionDataSets", this.zziw).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSessions(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zziw, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
