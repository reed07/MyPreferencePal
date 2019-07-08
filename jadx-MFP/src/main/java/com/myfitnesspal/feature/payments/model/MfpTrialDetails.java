package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001fB\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails;", "Landroid/os/Parcelable;", "durationInterval", "", "durationUnit", "Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails$DurationUnit;", "(ILcom/myfitnesspal/feature/payments/model/MfpTrialDetails$DurationUnit;)V", "getDurationInterval", "()I", "setDurationInterval", "(I)V", "getDurationUnit", "()Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails$DurationUnit;", "setDurationUnit", "(Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails$DurationUnit;)V", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "DurationUnit", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpTrialDetails.kt */
public final class MfpTrialDetails implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    @Expose
    private int durationInterval;
    @NotNull
    @Expose
    private DurationUnit durationUnit;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new MfpTrialDetails(parcel.readInt(), (DurationUnit) Enum.valueOf(DurationUnit.class, parcel.readString()));
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpTrialDetails[i];
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails$DurationUnit;", "", "(Ljava/lang/String;I)V", "value", "", "DAY", "WEEK", "YEAR", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MfpTrialDetails.kt */
    public enum DurationUnit {
        DAY,
        WEEK,
        YEAR;

        @NotNull
        public final String value() {
            switch (this) {
                case DAY:
                    return "day";
                case WEEK:
                    return "week";
                case YEAR:
                    return "year";
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }

    public MfpTrialDetails() {
        this(0, null, 3, null);
    }

    @NotNull
    public static /* synthetic */ MfpTrialDetails copy$default(MfpTrialDetails mfpTrialDetails, int i, DurationUnit durationUnit2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = mfpTrialDetails.durationInterval;
        }
        if ((i2 & 2) != 0) {
            durationUnit2 = mfpTrialDetails.durationUnit;
        }
        return mfpTrialDetails.copy(i, durationUnit2);
    }

    public final int component1() {
        return this.durationInterval;
    }

    @NotNull
    public final DurationUnit component2() {
        return this.durationUnit;
    }

    @NotNull
    public final MfpTrialDetails copy(int i, @NotNull DurationUnit durationUnit2) {
        Intrinsics.checkParameterIsNotNull(durationUnit2, "durationUnit");
        return new MfpTrialDetails(i, durationUnit2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof MfpTrialDetails) {
                MfpTrialDetails mfpTrialDetails = (MfpTrialDetails) obj;
                if (!(this.durationInterval == mfpTrialDetails.durationInterval) || !Intrinsics.areEqual((Object) this.durationUnit, (Object) mfpTrialDetails.durationUnit)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.durationInterval) * 31;
        DurationUnit durationUnit2 = this.durationUnit;
        return hashCode + (durationUnit2 != null ? durationUnit2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpTrialDetails(durationInterval=");
        sb.append(this.durationInterval);
        sb.append(", durationUnit=");
        sb.append(this.durationUnit);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeInt(this.durationInterval);
        parcel.writeString(this.durationUnit.name());
    }

    public MfpTrialDetails(int i, @NotNull DurationUnit durationUnit2) {
        Intrinsics.checkParameterIsNotNull(durationUnit2, "durationUnit");
        this.durationInterval = i;
        this.durationUnit = durationUnit2;
    }

    public final int getDurationInterval() {
        return this.durationInterval;
    }

    public final void setDurationInterval(int i) {
        this.durationInterval = i;
    }

    public /* synthetic */ MfpTrialDetails(int i, DurationUnit durationUnit2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            durationUnit2 = DurationUnit.DAY;
        }
        this(i, durationUnit2);
    }

    @NotNull
    public final DurationUnit getDurationUnit() {
        return this.durationUnit;
    }

    public final void setDurationUnit(@NotNull DurationUnit durationUnit2) {
        Intrinsics.checkParameterIsNotNull(durationUnit2, "<set-?>");
        this.durationUnit = durationUnit2;
    }
}
