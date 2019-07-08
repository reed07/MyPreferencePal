package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001&B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0019\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails;", "Landroid/os/Parcelable;", "frequencyInterval", "", "frequencyUnit", "Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails$FrequencyUnit;", "trialDetails", "Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails;", "(ILcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails$FrequencyUnit;Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails;)V", "getFrequencyInterval", "()I", "setFrequencyInterval", "(I)V", "getFrequencyUnit", "()Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails$FrequencyUnit;", "setFrequencyUnit", "(Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails$FrequencyUnit;)V", "getTrialDetails", "()Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails;", "setTrialDetails", "(Lcom/myfitnesspal/feature/payments/model/MfpTrialDetails;)V", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "FrequencyUnit", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpSubscriptionDetails.kt */
public final class MfpSubscriptionDetails implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    @Expose
    private int frequencyInterval;
    @Nullable
    @Expose
    private FrequencyUnit frequencyUnit;
    @Nullable
    @Expose
    private MfpTrialDetails trialDetails;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            FrequencyUnit frequencyUnit;
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            int readInt = parcel.readInt();
            MfpTrialDetails mfpTrialDetails = null;
            if (parcel.readInt() != 0) {
                frequencyUnit = (FrequencyUnit) Enum.valueOf(FrequencyUnit.class, parcel.readString());
            } else {
                frequencyUnit = null;
            }
            if (parcel.readInt() != 0) {
                mfpTrialDetails = (MfpTrialDetails) MfpTrialDetails.CREATOR.createFromParcel(parcel);
            }
            return new MfpSubscriptionDetails(readInt, frequencyUnit, mfpTrialDetails);
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpSubscriptionDetails[i];
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails$FrequencyUnit;", "", "(Ljava/lang/String;I)V", "DAY", "WEEK", "MONTH", "YEAR", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MfpSubscriptionDetails.kt */
    public enum FrequencyUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }

    public MfpSubscriptionDetails() {
        this(0, null, null, 7, null);
    }

    @NotNull
    public static /* synthetic */ MfpSubscriptionDetails copy$default(MfpSubscriptionDetails mfpSubscriptionDetails, int i, FrequencyUnit frequencyUnit2, MfpTrialDetails mfpTrialDetails, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = mfpSubscriptionDetails.frequencyInterval;
        }
        if ((i2 & 2) != 0) {
            frequencyUnit2 = mfpSubscriptionDetails.frequencyUnit;
        }
        if ((i2 & 4) != 0) {
            mfpTrialDetails = mfpSubscriptionDetails.trialDetails;
        }
        return mfpSubscriptionDetails.copy(i, frequencyUnit2, mfpTrialDetails);
    }

    public final int component1() {
        return this.frequencyInterval;
    }

    @Nullable
    public final FrequencyUnit component2() {
        return this.frequencyUnit;
    }

    @Nullable
    public final MfpTrialDetails component3() {
        return this.trialDetails;
    }

    @NotNull
    public final MfpSubscriptionDetails copy(int i, @Nullable FrequencyUnit frequencyUnit2, @Nullable MfpTrialDetails mfpTrialDetails) {
        return new MfpSubscriptionDetails(i, frequencyUnit2, mfpTrialDetails);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof MfpSubscriptionDetails) {
                MfpSubscriptionDetails mfpSubscriptionDetails = (MfpSubscriptionDetails) obj;
                if (!(this.frequencyInterval == mfpSubscriptionDetails.frequencyInterval) || !Intrinsics.areEqual((Object) this.frequencyUnit, (Object) mfpSubscriptionDetails.frequencyUnit) || !Intrinsics.areEqual((Object) this.trialDetails, (Object) mfpSubscriptionDetails.trialDetails)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.frequencyInterval) * 31;
        FrequencyUnit frequencyUnit2 = this.frequencyUnit;
        int i = 0;
        int hashCode2 = (hashCode + (frequencyUnit2 != null ? frequencyUnit2.hashCode() : 0)) * 31;
        MfpTrialDetails mfpTrialDetails = this.trialDetails;
        if (mfpTrialDetails != null) {
            i = mfpTrialDetails.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpSubscriptionDetails(frequencyInterval=");
        sb.append(this.frequencyInterval);
        sb.append(", frequencyUnit=");
        sb.append(this.frequencyUnit);
        sb.append(", trialDetails=");
        sb.append(this.trialDetails);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeInt(this.frequencyInterval);
        FrequencyUnit frequencyUnit2 = this.frequencyUnit;
        if (frequencyUnit2 != null) {
            parcel.writeInt(1);
            parcel.writeString(frequencyUnit2.name());
        } else {
            parcel.writeInt(0);
        }
        MfpTrialDetails mfpTrialDetails = this.trialDetails;
        if (mfpTrialDetails != null) {
            parcel.writeInt(1);
            mfpTrialDetails.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public MfpSubscriptionDetails(int i, @Nullable FrequencyUnit frequencyUnit2, @Nullable MfpTrialDetails mfpTrialDetails) {
        this.frequencyInterval = i;
        this.frequencyUnit = frequencyUnit2;
        this.trialDetails = mfpTrialDetails;
    }

    public final int getFrequencyInterval() {
        return this.frequencyInterval;
    }

    public final void setFrequencyInterval(int i) {
        this.frequencyInterval = i;
    }

    public /* synthetic */ MfpSubscriptionDetails(int i, FrequencyUnit frequencyUnit2, MfpTrialDetails mfpTrialDetails, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            frequencyUnit2 = null;
        }
        if ((i2 & 4) != 0) {
            mfpTrialDetails = null;
        }
        this(i, frequencyUnit2, mfpTrialDetails);
    }

    @Nullable
    public final FrequencyUnit getFrequencyUnit() {
        return this.frequencyUnit;
    }

    public final void setFrequencyUnit(@Nullable FrequencyUnit frequencyUnit2) {
        this.frequencyUnit = frequencyUnit2;
    }

    @Nullable
    public final MfpTrialDetails getTrialDetails() {
        return this.trialDetails;
    }

    public final void setTrialDetails(@Nullable MfpTrialDetails mfpTrialDetails) {
        this.trialDetails = mfpTrialDetails;
    }
}
