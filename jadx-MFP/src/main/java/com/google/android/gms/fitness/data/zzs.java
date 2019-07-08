package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Goal.DurationObjective;
import com.google.android.gms.fitness.data.Goal.FrequencyObjective;
import com.google.android.gms.fitness.data.Goal.MetricObjective;
import com.google.android.gms.fitness.data.Goal.Recurrence;
import java.util.ArrayList;

public final class zzs implements Creator<Goal> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new Goal[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        long j = 0;
        Recurrence recurrence = null;
        MetricObjective metricObjective = null;
        DurationObjective durationObjective = null;
        FrequencyObjective frequencyObjective = null;
        int i = 0;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 3:
                    SafeParcelReader.readList(parcel, readHeader, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    recurrence = (Recurrence) SafeParcelReader.createParcelable(parcel, readHeader, Recurrence.CREATOR);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    metricObjective = (MetricObjective) SafeParcelReader.createParcelable(parcel, readHeader, MetricObjective.CREATOR);
                    break;
                case 7:
                    durationObjective = (DurationObjective) SafeParcelReader.createParcelable(parcel, readHeader, DurationObjective.CREATOR);
                    break;
                case 8:
                    frequencyObjective = (FrequencyObjective) SafeParcelReader.createParcelable(parcel, readHeader, FrequencyObjective.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        Goal goal = new Goal(j2, j, arrayList, recurrence, i, metricObjective, durationObjective, frequencyObjective);
        return goal;
    }
}
