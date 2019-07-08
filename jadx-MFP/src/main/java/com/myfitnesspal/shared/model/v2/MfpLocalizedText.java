package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010HÖ\u0001R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/myfitnesspal/shared/model/v2/MfpLocalizedText;", "Landroid/os/Parcelable;", "text", "", "language", "(Ljava/lang/String;Ljava/lang/String;)V", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "getText", "setText", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpLocalizedText.kt */
public final class MfpLocalizedText implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    @Nullable
    @Expose
    private String language;
    @Nullable
    @Expose
    private String text;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new MfpLocalizedText(parcel.readString(), parcel.readString());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpLocalizedText[i];
        }
    }

    public MfpLocalizedText() {
        this(null, null, 3, null);
    }

    @NotNull
    public static /* synthetic */ MfpLocalizedText copy$default(MfpLocalizedText mfpLocalizedText, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mfpLocalizedText.text;
        }
        if ((i & 2) != 0) {
            str2 = mfpLocalizedText.language;
        }
        return mfpLocalizedText.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.text;
    }

    @Nullable
    public final String component2() {
        return this.language;
    }

    @NotNull
    public final MfpLocalizedText copy(@Nullable String str, @Nullable String str2) {
        return new MfpLocalizedText(str, str2);
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.language, (java.lang.Object) r3.language) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.myfitnesspal.shared.model.v2.MfpLocalizedText
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.shared.model.v2.MfpLocalizedText r3 = (com.myfitnesspal.shared.model.v2.MfpLocalizedText) r3
            java.lang.String r0 = r2.text
            java.lang.String r1 = r3.text
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = r2.language
            java.lang.String r3 = r3.language
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
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v2.MfpLocalizedText.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.text;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.language;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpLocalizedText(text=");
        sb.append(this.text);
        sb.append(", language=");
        sb.append(this.language);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeString(this.text);
        parcel.writeString(this.language);
    }

    public MfpLocalizedText(@Nullable String str, @Nullable String str2) {
        this.text = str;
        this.language = str2;
    }

    public /* synthetic */ MfpLocalizedText(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        this(str, str2);
    }

    @Nullable
    public final String getText() {
        return this.text;
    }

    public final void setText(@Nullable String str) {
        this.text = str;
    }

    @Nullable
    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(@Nullable String str) {
        this.language = str;
    }
}
