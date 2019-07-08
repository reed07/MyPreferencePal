package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001fB!\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÂ\u0003J%\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016J\t\u0010\u0017\u001a\u00020\u0010HÖ\u0001J\u0016\u0010\u0018\u001a\u00020\u00192\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001J\u0019\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0010HÖ\u0001R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006 "}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails;", "Landroid/os/Parcelable;", "platformName", "Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails$PlatformName;", "paymentProviders", "", "", "(Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails$PlatformName;Ljava/util/List;)V", "getPlatformName", "()Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails$PlatformName;", "setPlatformName", "(Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails$PlatformName;)V", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "getPaymentProviders", "", "hashCode", "setPaymentProviders", "", "toString", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "PlatformName", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpPlatformDetails.kt */
public final class MfpPlatformDetails implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    @Expose
    private List<String> paymentProviders;
    @Nullable
    @Expose
    private PlatformName platformName;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            PlatformName platformName;
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            if (parcel.readInt() != 0) {
                platformName = (PlatformName) Enum.valueOf(PlatformName.class, parcel.readString());
            } else {
                platformName = null;
            }
            return new MfpPlatformDetails(platformName, parcel.createStringArrayList());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpPlatformDetails[i];
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails$PlatformName;", "", "(Ljava/lang/String;I)V", "ANDROID", "IOS", "WEB", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MfpPlatformDetails.kt */
    public enum PlatformName {
        ANDROID,
        IOS,
        WEB
    }

    public MfpPlatformDetails() {
        this(null, null, 3, null);
    }

    private final List<String> component2() {
        return this.paymentProviders;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.String>, for r2v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.myfitnesspal.feature.payments.model.MfpPlatformDetails copy$default(com.myfitnesspal.feature.payments.model.MfpPlatformDetails r0, com.myfitnesspal.feature.payments.model.MfpPlatformDetails.PlatformName r1, java.util.List<java.lang.String> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            com.myfitnesspal.feature.payments.model.MfpPlatformDetails$PlatformName r1 = r0.platformName
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            java.util.List<java.lang.String> r2 = r0.paymentProviders
        L_0x000c:
            com.myfitnesspal.feature.payments.model.MfpPlatformDetails r0 = r0.copy(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.model.MfpPlatformDetails.copy$default(com.myfitnesspal.feature.payments.model.MfpPlatformDetails, com.myfitnesspal.feature.payments.model.MfpPlatformDetails$PlatformName, java.util.List, int, java.lang.Object):com.myfitnesspal.feature.payments.model.MfpPlatformDetails");
    }

    @Nullable
    public final PlatformName component1() {
        return this.platformName;
    }

    @NotNull
    public final MfpPlatformDetails copy(@Nullable PlatformName platformName2, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "paymentProviders");
        return new MfpPlatformDetails(platformName2, list);
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.paymentProviders, (java.lang.Object) r3.paymentProviders) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.myfitnesspal.feature.payments.model.MfpPlatformDetails
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.feature.payments.model.MfpPlatformDetails r3 = (com.myfitnesspal.feature.payments.model.MfpPlatformDetails) r3
            com.myfitnesspal.feature.payments.model.MfpPlatformDetails$PlatformName r0 = r2.platformName
            com.myfitnesspal.feature.payments.model.MfpPlatformDetails$PlatformName r1 = r3.platformName
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.util.List<java.lang.String> r0 = r2.paymentProviders
            java.util.List<java.lang.String> r3 = r3.paymentProviders
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.model.MfpPlatformDetails.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        PlatformName platformName2 = this.platformName;
        int i = 0;
        int hashCode = (platformName2 != null ? platformName2.hashCode() : 0) * 31;
        List<String> list = this.paymentProviders;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpPlatformDetails(platformName=");
        sb.append(this.platformName);
        sb.append(", paymentProviders=");
        sb.append(this.paymentProviders);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        PlatformName platformName2 = this.platformName;
        if (platformName2 != null) {
            parcel.writeInt(1);
            parcel.writeString(platformName2.name());
        } else {
            parcel.writeInt(0);
        }
        parcel.writeStringList(this.paymentProviders);
    }

    public MfpPlatformDetails(@Nullable PlatformName platformName2, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "paymentProviders");
        this.platformName = platformName2;
        this.paymentProviders = list;
    }

    public /* synthetic */ MfpPlatformDetails(PlatformName platformName2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            platformName2 = null;
        }
        if ((i & 2) != 0) {
            list = new ArrayList();
        }
        this(platformName2, list);
    }

    @Nullable
    public final PlatformName getPlatformName() {
        return this.platformName;
    }

    public final void setPlatformName(@Nullable PlatformName platformName2) {
        this.platformName = platformName2;
    }

    @NotNull
    public final List<String> getPaymentProviders() {
        return this.paymentProviders;
    }

    public final void setPaymentProviders(@Nullable List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.paymentProviders = list;
    }
}
