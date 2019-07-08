package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpLocalizedText;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÂ\u0003J'\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005J\t\u0010\u0016\u001a\u00020\u0010HÖ\u0001J\u0016\u0010\u0017\u001a\u00020\u00182\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0010HÖ\u0001R\u001a\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpProductFeature;", "Landroid/os/Parcelable;", "featureId", "", "featureDescriptions", "", "Lcom/myfitnesspal/shared/model/v2/MfpLocalizedText;", "(Ljava/lang/String;Ljava/util/List;)V", "getFeatureId", "()Ljava/lang/String;", "setFeatureId", "(Ljava/lang/String;)V", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "getFeatureDescriptions", "hashCode", "setFeatureDescriptions", "", "descriptions", "toString", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpProductFeature.kt */
public final class MfpProductFeature implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    @Expose
    private List<MfpLocalizedText> featureDescriptions;
    @Nullable
    @Expose
    private String featureId;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            String readString = parcel.readString();
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                while (readInt != 0) {
                    arrayList.add((MfpLocalizedText) MfpLocalizedText.CREATOR.createFromParcel(parcel));
                    readInt--;
                }
            } else {
                arrayList = null;
            }
            return new MfpProductFeature(readString, arrayList);
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpProductFeature[i];
        }
    }

    public MfpProductFeature() {
        this(null, null, 3, null);
    }

    private final List<MfpLocalizedText> component2() {
        return this.featureDescriptions;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<com.myfitnesspal.shared.model.v2.MfpLocalizedText>, for r2v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.myfitnesspal.feature.payments.model.MfpProductFeature copy$default(com.myfitnesspal.feature.payments.model.MfpProductFeature r0, java.lang.String r1, java.util.List<com.myfitnesspal.shared.model.v2.MfpLocalizedText> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            java.lang.String r1 = r0.featureId
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            java.util.List<com.myfitnesspal.shared.model.v2.MfpLocalizedText> r2 = r0.featureDescriptions
        L_0x000c:
            com.myfitnesspal.feature.payments.model.MfpProductFeature r0 = r0.copy(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.model.MfpProductFeature.copy$default(com.myfitnesspal.feature.payments.model.MfpProductFeature, java.lang.String, java.util.List, int, java.lang.Object):com.myfitnesspal.feature.payments.model.MfpProductFeature");
    }

    @Nullable
    public final String component1() {
        return this.featureId;
    }

    @NotNull
    public final MfpProductFeature copy(@Nullable String str, @Nullable List<MfpLocalizedText> list) {
        return new MfpProductFeature(str, list);
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.featureDescriptions, (java.lang.Object) r3.featureDescriptions) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.myfitnesspal.feature.payments.model.MfpProductFeature
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.feature.payments.model.MfpProductFeature r3 = (com.myfitnesspal.feature.payments.model.MfpProductFeature) r3
            java.lang.String r0 = r2.featureId
            java.lang.String r1 = r3.featureId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.util.List<com.myfitnesspal.shared.model.v2.MfpLocalizedText> r0 = r2.featureDescriptions
            java.util.List<com.myfitnesspal.shared.model.v2.MfpLocalizedText> r3 = r3.featureDescriptions
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
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.model.MfpProductFeature.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.featureId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<MfpLocalizedText> list = this.featureDescriptions;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpProductFeature(featureId=");
        sb.append(this.featureId);
        sb.append(", featureDescriptions=");
        sb.append(this.featureDescriptions);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeString(this.featureId);
        List<MfpLocalizedText> list = this.featureDescriptions;
        if (list != null) {
            parcel.writeInt(1);
            parcel.writeInt(list.size());
            for (MfpLocalizedText writeToParcel : list) {
                writeToParcel.writeToParcel(parcel, 0);
            }
            return;
        }
        parcel.writeInt(0);
    }

    public MfpProductFeature(@Nullable String str, @Nullable List<MfpLocalizedText> list) {
        this.featureId = str;
        this.featureDescriptions = list;
    }

    public /* synthetic */ MfpProductFeature(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            list = new ArrayList();
        }
        this(str, list);
    }

    @Nullable
    public final String getFeatureId() {
        return this.featureId;
    }

    public final void setFeatureId(@Nullable String str) {
        this.featureId = str;
    }

    @Nullable
    public final List<MfpLocalizedText> getFeatureDescriptions() {
        return this.featureDescriptions;
    }

    public final void setFeatureDescriptions(@Nullable List<MfpLocalizedText> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.featureDescriptions = list;
    }
}
