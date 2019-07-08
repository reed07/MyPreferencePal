package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.AnalyticsEvents;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u0000 S2\u00020\u0001:\u0003RSTB\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÂ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010?\u001a\u00020\rHÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0001\u0010A\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\r2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001J\t\u0010B\u001a\u00020CHÖ\u0001J\u0013\u0010D\u001a\u00020\r2\b\u0010E\u001a\u0004\u0018\u00010FHÖ\u0003J\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00120HJ\t\u0010I\u001a\u00020CHÖ\u0001J\u0016\u0010J\u001a\u00020K2\u000e\u0010L\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011J\t\u0010M\u001a\u00020\u0003HÖ\u0001J\u0019\u0010N\u001a\u00020K2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020CHÖ\u0001R\u001e\u0010\u000f\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R \u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R \u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010 \"\u0004\b*\u0010\"R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010 \"\u0004\b2\u0010\"R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010 \"\u0004\b4\u0010\"¨\u0006U"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription;", "Landroid/os/Parcelable;", "userId", "", "subscriptionId", "subscriptionType", "subscriptionStatus", "Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription$SubscriptionStatus;", "paymentDetails", "Lcom/myfitnesspal/feature/payments/model/MfpPaymentDetails;", "subscriptionStartDate", "subscriptionEndDate", "requestedCancellation", "", "requestedCancellationDate", "isTrial", "subscriptionFeatures", "", "Lcom/myfitnesspal/feature/payments/model/MfpProductFeature;", "subscriptionDetails", "Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription$SubscriptionStatus;Lcom/myfitnesspal/feature/payments/model/MfpPaymentDetails;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/util/List;Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails;)V", "()Z", "setTrial", "(Z)V", "getPaymentDetails", "()Lcom/myfitnesspal/feature/payments/model/MfpPaymentDetails;", "setPaymentDetails", "(Lcom/myfitnesspal/feature/payments/model/MfpPaymentDetails;)V", "getRequestedCancellation", "setRequestedCancellation", "getRequestedCancellationDate", "()Ljava/lang/String;", "setRequestedCancellationDate", "(Ljava/lang/String;)V", "getSubscriptionDetails", "()Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails;", "setSubscriptionDetails", "(Lcom/myfitnesspal/feature/payments/model/MfpSubscriptionDetails;)V", "getSubscriptionEndDate", "setSubscriptionEndDate", "getSubscriptionId", "setSubscriptionId", "getSubscriptionStartDate", "setSubscriptionStartDate", "getSubscriptionStatus", "()Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription$SubscriptionStatus;", "setSubscriptionStatus", "(Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription$SubscriptionStatus;)V", "getSubscriptionType", "setSubscriptionType", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "other", "", "getSubscriptionFeatures", "", "hashCode", "setSubscriptionFeatures", "", "features", "toString", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "API_RESPONSE_MAPPER", "Companion", "SubscriptionStatus", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpPaidSubscription.kt */
public final class MfpPaidSubscription implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    public static final Companion Companion = new Companion(null);
    public static final int VERSION = 1;
    @Expose
    private boolean isTrial;
    @Nullable
    @Expose
    private MfpPaymentDetails paymentDetails;
    @Expose
    private boolean requestedCancellation;
    @Nullable
    @Expose
    private String requestedCancellationDate;
    @Nullable
    @Expose
    private MfpSubscriptionDetails subscriptionDetails;
    @Nullable
    @Expose
    private String subscriptionEndDate;
    @Expose
    private List<MfpProductFeature> subscriptionFeatures;
    @Nullable
    @Expose
    private String subscriptionId;
    @Nullable
    @Expose
    private String subscriptionStartDate;
    @Nullable
    @Expose
    private SubscriptionStatus subscriptionStatus;
    @Nullable
    @Expose
    private String subscriptionType;
    @Nullable
    @Expose
    private String userId;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription$API_RESPONSE_MAPPER;", "Lcom/myfitnesspal/shared/api/ApiResponse;", "Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription;", "()V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MfpPaidSubscription.kt */
    public static final class API_RESPONSE_MAPPER extends ApiResponse<MfpPaidSubscription> {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u0006*\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription$Companion;", "", "()V", "VERSION", "", "isEqualTo", "", "Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription;", "otherSubscription", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MfpPaidSubscription.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final boolean isEqualTo(@Nullable MfpPaidSubscription mfpPaidSubscription, @Nullable MfpPaidSubscription mfpPaidSubscription2) {
            if (mfpPaidSubscription == null && mfpPaidSubscription2 == null) {
                return true;
            }
            if (mfpPaidSubscription != null && mfpPaidSubscription.equals(mfpPaidSubscription2)) {
                return true;
            }
            if (mfpPaidSubscription2 == null || !mfpPaidSubscription2.equals(mfpPaidSubscription)) {
                return false;
            }
            return true;
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Parcel parcel2 = parcel;
            Intrinsics.checkParameterIsNotNull(parcel2, "in");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            SubscriptionStatus subscriptionStatus = parcel.readInt() != 0 ? (SubscriptionStatus) Enum.valueOf(SubscriptionStatus.class, parcel.readString()) : null;
            MfpPaymentDetails mfpPaymentDetails = parcel.readInt() != 0 ? (MfpPaymentDetails) MfpPaymentDetails.CREATOR.createFromParcel(parcel2) : null;
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            boolean z = false;
            boolean z2 = parcel.readInt() != 0;
            String readString6 = parcel.readString();
            if (parcel.readInt() != 0) {
                z = true;
            }
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((MfpProductFeature) MfpProductFeature.CREATOR.createFromParcel(parcel2));
                readInt--;
            }
            MfpPaidSubscription mfpPaidSubscription = new MfpPaidSubscription(readString, readString2, readString3, subscriptionStatus, mfpPaymentDetails, readString4, readString5, z2, readString6, z, arrayList, parcel.readInt() != 0 ? (MfpSubscriptionDetails) MfpSubscriptionDetails.CREATOR.createFromParcel(parcel2) : null);
            return mfpPaidSubscription;
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpPaidSubscription[i];
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpPaidSubscription$SubscriptionStatus;", "", "(Ljava/lang/String;I)V", "value", "", "ACTIVE", "CANCELLED", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MfpPaidSubscription.kt */
    public enum SubscriptionStatus {
        ACTIVE,
        CANCELLED;

        @Nullable
        public final String value() {
            switch (this) {
                case ACTIVE:
                    return "active";
                case CANCELLED:
                    return AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }

    public MfpPaidSubscription() {
        this(null, null, null, null, null, null, null, false, null, false, null, null, 4095, null);
    }

    private final List<MfpProductFeature> component11() {
        return this.subscriptionFeatures;
    }

    @NotNull
    public static /* synthetic */ MfpPaidSubscription copy$default(MfpPaidSubscription mfpPaidSubscription, String str, String str2, String str3, SubscriptionStatus subscriptionStatus2, MfpPaymentDetails mfpPaymentDetails, String str4, String str5, boolean z, String str6, boolean z2, List list, MfpSubscriptionDetails mfpSubscriptionDetails, int i, Object obj) {
        MfpPaidSubscription mfpPaidSubscription2 = mfpPaidSubscription;
        int i2 = i;
        return mfpPaidSubscription.copy((i2 & 1) != 0 ? mfpPaidSubscription2.userId : str, (i2 & 2) != 0 ? mfpPaidSubscription2.subscriptionId : str2, (i2 & 4) != 0 ? mfpPaidSubscription2.subscriptionType : str3, (i2 & 8) != 0 ? mfpPaidSubscription2.subscriptionStatus : subscriptionStatus2, (i2 & 16) != 0 ? mfpPaidSubscription2.paymentDetails : mfpPaymentDetails, (i2 & 32) != 0 ? mfpPaidSubscription2.subscriptionStartDate : str4, (i2 & 64) != 0 ? mfpPaidSubscription2.subscriptionEndDate : str5, (i2 & 128) != 0 ? mfpPaidSubscription2.requestedCancellation : z, (i2 & 256) != 0 ? mfpPaidSubscription2.requestedCancellationDate : str6, (i2 & 512) != 0 ? mfpPaidSubscription2.isTrial : z2, (i2 & 1024) != 0 ? mfpPaidSubscription2.subscriptionFeatures : list, (i2 & 2048) != 0 ? mfpPaidSubscription2.subscriptionDetails : mfpSubscriptionDetails);
    }

    @JvmStatic
    public static final boolean isEqualTo(@Nullable MfpPaidSubscription mfpPaidSubscription, @Nullable MfpPaidSubscription mfpPaidSubscription2) {
        return Companion.isEqualTo(mfpPaidSubscription, mfpPaidSubscription2);
    }

    @Nullable
    public final String component1() {
        return this.userId;
    }

    public final boolean component10() {
        return this.isTrial;
    }

    @Nullable
    public final MfpSubscriptionDetails component12() {
        return this.subscriptionDetails;
    }

    @Nullable
    public final String component2() {
        return this.subscriptionId;
    }

    @Nullable
    public final String component3() {
        return this.subscriptionType;
    }

    @Nullable
    public final SubscriptionStatus component4() {
        return this.subscriptionStatus;
    }

    @Nullable
    public final MfpPaymentDetails component5() {
        return this.paymentDetails;
    }

    @Nullable
    public final String component6() {
        return this.subscriptionStartDate;
    }

    @Nullable
    public final String component7() {
        return this.subscriptionEndDate;
    }

    public final boolean component8() {
        return this.requestedCancellation;
    }

    @Nullable
    public final String component9() {
        return this.requestedCancellationDate;
    }

    @NotNull
    public final MfpPaidSubscription copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable SubscriptionStatus subscriptionStatus2, @Nullable MfpPaymentDetails mfpPaymentDetails, @Nullable String str4, @Nullable String str5, boolean z, @Nullable String str6, boolean z2, @NotNull List<MfpProductFeature> list, @Nullable MfpSubscriptionDetails mfpSubscriptionDetails) {
        List<MfpProductFeature> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "subscriptionFeatures");
        MfpPaidSubscription mfpPaidSubscription = new MfpPaidSubscription(str, str2, str3, subscriptionStatus2, mfpPaymentDetails, str4, str5, z, str6, z2, list2, mfpSubscriptionDetails);
        return mfpPaidSubscription;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof MfpPaidSubscription) {
                MfpPaidSubscription mfpPaidSubscription = (MfpPaidSubscription) obj;
                if (Intrinsics.areEqual((Object) this.userId, (Object) mfpPaidSubscription.userId) && Intrinsics.areEqual((Object) this.subscriptionId, (Object) mfpPaidSubscription.subscriptionId) && Intrinsics.areEqual((Object) this.subscriptionType, (Object) mfpPaidSubscription.subscriptionType) && Intrinsics.areEqual((Object) this.subscriptionStatus, (Object) mfpPaidSubscription.subscriptionStatus) && Intrinsics.areEqual((Object) this.paymentDetails, (Object) mfpPaidSubscription.paymentDetails) && Intrinsics.areEqual((Object) this.subscriptionStartDate, (Object) mfpPaidSubscription.subscriptionStartDate) && Intrinsics.areEqual((Object) this.subscriptionEndDate, (Object) mfpPaidSubscription.subscriptionEndDate)) {
                    if ((this.requestedCancellation == mfpPaidSubscription.requestedCancellation) && Intrinsics.areEqual((Object) this.requestedCancellationDate, (Object) mfpPaidSubscription.requestedCancellationDate)) {
                        if (!(this.isTrial == mfpPaidSubscription.isTrial) || !Intrinsics.areEqual((Object) this.subscriptionFeatures, (Object) mfpPaidSubscription.subscriptionFeatures) || !Intrinsics.areEqual((Object) this.subscriptionDetails, (Object) mfpPaidSubscription.subscriptionDetails)) {
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.userId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.subscriptionId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.subscriptionType;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        SubscriptionStatus subscriptionStatus2 = this.subscriptionStatus;
        int hashCode4 = (hashCode3 + (subscriptionStatus2 != null ? subscriptionStatus2.hashCode() : 0)) * 31;
        MfpPaymentDetails mfpPaymentDetails = this.paymentDetails;
        int hashCode5 = (hashCode4 + (mfpPaymentDetails != null ? mfpPaymentDetails.hashCode() : 0)) * 31;
        String str4 = this.subscriptionStartDate;
        int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.subscriptionEndDate;
        int hashCode7 = (hashCode6 + (str5 != null ? str5.hashCode() : 0)) * 31;
        boolean z = this.requestedCancellation;
        if (z) {
            z = true;
        }
        int i2 = (hashCode7 + (z ? 1 : 0)) * 31;
        String str6 = this.requestedCancellationDate;
        int hashCode8 = (i2 + (str6 != null ? str6.hashCode() : 0)) * 31;
        boolean z2 = this.isTrial;
        if (z2) {
            z2 = true;
        }
        int i3 = (hashCode8 + (z2 ? 1 : 0)) * 31;
        List<MfpProductFeature> list = this.subscriptionFeatures;
        int hashCode9 = (i3 + (list != null ? list.hashCode() : 0)) * 31;
        MfpSubscriptionDetails mfpSubscriptionDetails = this.subscriptionDetails;
        if (mfpSubscriptionDetails != null) {
            i = mfpSubscriptionDetails.hashCode();
        }
        return hashCode9 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpPaidSubscription(userId=");
        sb.append(this.userId);
        sb.append(", subscriptionId=");
        sb.append(this.subscriptionId);
        sb.append(", subscriptionType=");
        sb.append(this.subscriptionType);
        sb.append(", subscriptionStatus=");
        sb.append(this.subscriptionStatus);
        sb.append(", paymentDetails=");
        sb.append(this.paymentDetails);
        sb.append(", subscriptionStartDate=");
        sb.append(this.subscriptionStartDate);
        sb.append(", subscriptionEndDate=");
        sb.append(this.subscriptionEndDate);
        sb.append(", requestedCancellation=");
        sb.append(this.requestedCancellation);
        sb.append(", requestedCancellationDate=");
        sb.append(this.requestedCancellationDate);
        sb.append(", isTrial=");
        sb.append(this.isTrial);
        sb.append(", subscriptionFeatures=");
        sb.append(this.subscriptionFeatures);
        sb.append(", subscriptionDetails=");
        sb.append(this.subscriptionDetails);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeString(this.userId);
        parcel.writeString(this.subscriptionId);
        parcel.writeString(this.subscriptionType);
        SubscriptionStatus subscriptionStatus2 = this.subscriptionStatus;
        if (subscriptionStatus2 != null) {
            parcel.writeInt(1);
            parcel.writeString(subscriptionStatus2.name());
        } else {
            parcel.writeInt(0);
        }
        MfpPaymentDetails mfpPaymentDetails = this.paymentDetails;
        if (mfpPaymentDetails != null) {
            parcel.writeInt(1);
            mfpPaymentDetails.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.subscriptionStartDate);
        parcel.writeString(this.subscriptionEndDate);
        parcel.writeInt(this.requestedCancellation ? 1 : 0);
        parcel.writeString(this.requestedCancellationDate);
        parcel.writeInt(this.isTrial ? 1 : 0);
        List<MfpProductFeature> list = this.subscriptionFeatures;
        parcel.writeInt(list.size());
        for (MfpProductFeature writeToParcel : list) {
            writeToParcel.writeToParcel(parcel, 0);
        }
        MfpSubscriptionDetails mfpSubscriptionDetails = this.subscriptionDetails;
        if (mfpSubscriptionDetails != null) {
            parcel.writeInt(1);
            mfpSubscriptionDetails.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public MfpPaidSubscription(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable SubscriptionStatus subscriptionStatus2, @Nullable MfpPaymentDetails mfpPaymentDetails, @Nullable String str4, @Nullable String str5, boolean z, @Nullable String str6, boolean z2, @NotNull List<MfpProductFeature> list, @Nullable MfpSubscriptionDetails mfpSubscriptionDetails) {
        Intrinsics.checkParameterIsNotNull(list, "subscriptionFeatures");
        this.userId = str;
        this.subscriptionId = str2;
        this.subscriptionType = str3;
        this.subscriptionStatus = subscriptionStatus2;
        this.paymentDetails = mfpPaymentDetails;
        this.subscriptionStartDate = str4;
        this.subscriptionEndDate = str5;
        this.requestedCancellation = z;
        this.requestedCancellationDate = str6;
        this.isTrial = z2;
        this.subscriptionFeatures = list;
        this.subscriptionDetails = mfpSubscriptionDetails;
    }

    public /* synthetic */ MfpPaidSubscription(String str, String str2, String str3, SubscriptionStatus subscriptionStatus2, MfpPaymentDetails mfpPaymentDetails, String str4, String str5, boolean z, String str6, boolean z2, List list, MfpSubscriptionDetails mfpSubscriptionDetails, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = i;
        String str7 = (i2 & 1) != 0 ? null : str;
        String str8 = (i2 & 2) != 0 ? null : str2;
        String str9 = (i2 & 4) != 0 ? null : str3;
        SubscriptionStatus subscriptionStatus3 = (i2 & 8) != 0 ? null : subscriptionStatus2;
        MfpPaymentDetails mfpPaymentDetails2 = (i2 & 16) != 0 ? null : mfpPaymentDetails;
        String str10 = (i2 & 32) != 0 ? null : str4;
        String str11 = (i2 & 64) != 0 ? null : str5;
        boolean z3 = false;
        boolean z4 = (i2 & 128) != 0 ? false : z;
        String str12 = (i2 & 256) != 0 ? null : str6;
        if ((i2 & 512) == 0) {
            z3 = z2;
        }
        this(str7, str8, str9, subscriptionStatus3, mfpPaymentDetails2, str10, str11, z4, str12, z3, (i2 & 1024) != 0 ? new ArrayList() : list, (i2 & 2048) != 0 ? null : mfpSubscriptionDetails);
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(@Nullable String str) {
        this.userId = str;
    }

    @Nullable
    public final String getSubscriptionId() {
        return this.subscriptionId;
    }

    public final void setSubscriptionId(@Nullable String str) {
        this.subscriptionId = str;
    }

    @Nullable
    public final String getSubscriptionType() {
        return this.subscriptionType;
    }

    public final void setSubscriptionType(@Nullable String str) {
        this.subscriptionType = str;
    }

    @Nullable
    public final SubscriptionStatus getSubscriptionStatus() {
        return this.subscriptionStatus;
    }

    public final void setSubscriptionStatus(@Nullable SubscriptionStatus subscriptionStatus2) {
        this.subscriptionStatus = subscriptionStatus2;
    }

    @Nullable
    public final MfpPaymentDetails getPaymentDetails() {
        return this.paymentDetails;
    }

    public final void setPaymentDetails(@Nullable MfpPaymentDetails mfpPaymentDetails) {
        this.paymentDetails = mfpPaymentDetails;
    }

    @Nullable
    public final String getSubscriptionStartDate() {
        return this.subscriptionStartDate;
    }

    public final void setSubscriptionStartDate(@Nullable String str) {
        this.subscriptionStartDate = str;
    }

    @Nullable
    public final String getSubscriptionEndDate() {
        return this.subscriptionEndDate;
    }

    public final void setSubscriptionEndDate(@Nullable String str) {
        this.subscriptionEndDate = str;
    }

    public final boolean getRequestedCancellation() {
        return this.requestedCancellation;
    }

    public final void setRequestedCancellation(boolean z) {
        this.requestedCancellation = z;
    }

    @Nullable
    public final String getRequestedCancellationDate() {
        return this.requestedCancellationDate;
    }

    public final void setRequestedCancellationDate(@Nullable String str) {
        this.requestedCancellationDate = str;
    }

    public final boolean isTrial() {
        return this.isTrial;
    }

    public final void setTrial(boolean z) {
        this.isTrial = z;
    }

    @Nullable
    public final MfpSubscriptionDetails getSubscriptionDetails() {
        return this.subscriptionDetails;
    }

    public final void setSubscriptionDetails(@Nullable MfpSubscriptionDetails mfpSubscriptionDetails) {
        this.subscriptionDetails = mfpSubscriptionDetails;
    }

    @NotNull
    public final List<MfpProductFeature> getSubscriptionFeatures() {
        return this.subscriptionFeatures;
    }

    public final void setSubscriptionFeatures(@Nullable List<MfpProductFeature> list) {
        if (list == null) {
            this.subscriptionFeatures.clear();
        } else {
            this.subscriptionFeatures = list;
        }
    }
}
