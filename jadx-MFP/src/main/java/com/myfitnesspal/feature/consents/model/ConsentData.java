package com.myfitnesspal.feature.consents.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/consents/model/ConsentData;", "", "matrixVersion", "", "acceptedCount", "", "(Ljava/lang/String;I)V", "getAcceptedCount", "()I", "getMatrixVersion", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentData.kt */
public final class ConsentData {
    @SerializedName("accepted_count")
    private final int acceptedCount;
    @SerializedName("matrix_version")
    @NotNull
    private final String matrixVersion;

    public ConsentData() {
        this(null, 0, 3, null);
    }

    @NotNull
    public static /* synthetic */ ConsentData copy$default(ConsentData consentData, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = consentData.matrixVersion;
        }
        if ((i2 & 2) != 0) {
            i = consentData.acceptedCount;
        }
        return consentData.copy(str, i);
    }

    @NotNull
    public final String component1() {
        return this.matrixVersion;
    }

    public final int component2() {
        return this.acceptedCount;
    }

    @NotNull
    public final ConsentData copy(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "matrixVersion");
        return new ConsentData(str, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ConsentData) {
                ConsentData consentData = (ConsentData) obj;
                if (Intrinsics.areEqual((Object) this.matrixVersion, (Object) consentData.matrixVersion)) {
                    if (this.acceptedCount == consentData.acceptedCount) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.matrixVersion;
        return ((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.acceptedCount);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConsentData(matrixVersion=");
        sb.append(this.matrixVersion);
        sb.append(", acceptedCount=");
        sb.append(this.acceptedCount);
        sb.append(")");
        return sb.toString();
    }

    public ConsentData(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "matrixVersion");
        this.matrixVersion = str;
        this.acceptedCount = i;
    }

    @NotNull
    public final String getMatrixVersion() {
        return this.matrixVersion;
    }

    public /* synthetic */ ConsentData(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 1) != 0) {
            str = "0";
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        this(str, i);
    }

    public final int getAcceptedCount() {
        return this.acceptedCount;
    }
}
