package com.myfitnesspal.shared.model.v2;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\rHÆ\u0003J]\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0007HÖ\u0001R \u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016R \u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0014\"\u0004\b&\u0010\u0016¨\u00065"}, d2 = {"Lcom/myfitnesspal/shared/model/v2/MfpNotificationActionItem;", "", "id", "Lcom/myfitnesspal/shared/model/v2/MfpNotificationActionId;", "iconId", "Lcom/myfitnesspal/shared/model/v2/MfpNotificationIconId;", "title", "", "deepLink", "url", "method", "Lcom/myfitnesspal/shared/model/v2/MFPNotificationMethod;", "body", "Lcom/google/gson/JsonObject;", "(Lcom/myfitnesspal/shared/model/v2/MfpNotificationActionId;Lcom/myfitnesspal/shared/model/v2/MfpNotificationIconId;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/myfitnesspal/shared/model/v2/MFPNotificationMethod;Lcom/google/gson/JsonObject;)V", "getBody", "()Lcom/google/gson/JsonObject;", "setBody", "(Lcom/google/gson/JsonObject;)V", "getDeepLink", "()Ljava/lang/String;", "setDeepLink", "(Ljava/lang/String;)V", "getIconId", "()Lcom/myfitnesspal/shared/model/v2/MfpNotificationIconId;", "setIconId", "(Lcom/myfitnesspal/shared/model/v2/MfpNotificationIconId;)V", "getId", "()Lcom/myfitnesspal/shared/model/v2/MfpNotificationActionId;", "setId", "(Lcom/myfitnesspal/shared/model/v2/MfpNotificationActionId;)V", "getMethod", "()Lcom/myfitnesspal/shared/model/v2/MFPNotificationMethod;", "setMethod", "(Lcom/myfitnesspal/shared/model/v2/MFPNotificationMethod;)V", "getTitle", "setTitle", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: MfpNotificationActionItem.kt */
public final class MfpNotificationActionItem {
    @Nullable
    @Expose
    private JsonObject body;
    @Nullable
    @Expose
    private String deepLink;
    @Nullable
    @Expose
    private MfpNotificationIconId iconId;
    @Nullable
    @Expose
    private MfpNotificationActionId id;
    @Nullable
    @Expose
    private MFPNotificationMethod method;
    @Nullable
    @Expose
    private String title;
    @Nullable
    @Expose
    private String url;

    public MfpNotificationActionItem() {
        this(null, null, null, null, null, null, null, Statements.GetOwnedFoodIdsDateDescending, null);
    }

    @NotNull
    public static /* synthetic */ MfpNotificationActionItem copy$default(MfpNotificationActionItem mfpNotificationActionItem, MfpNotificationActionId mfpNotificationActionId, MfpNotificationIconId mfpNotificationIconId, String str, String str2, String str3, MFPNotificationMethod mFPNotificationMethod, JsonObject jsonObject, int i, Object obj) {
        if ((i & 1) != 0) {
            mfpNotificationActionId = mfpNotificationActionItem.id;
        }
        if ((i & 2) != 0) {
            mfpNotificationIconId = mfpNotificationActionItem.iconId;
        }
        MfpNotificationIconId mfpNotificationIconId2 = mfpNotificationIconId;
        if ((i & 4) != 0) {
            str = mfpNotificationActionItem.title;
        }
        String str4 = str;
        if ((i & 8) != 0) {
            str2 = mfpNotificationActionItem.deepLink;
        }
        String str5 = str2;
        if ((i & 16) != 0) {
            str3 = mfpNotificationActionItem.url;
        }
        String str6 = str3;
        if ((i & 32) != 0) {
            mFPNotificationMethod = mfpNotificationActionItem.method;
        }
        MFPNotificationMethod mFPNotificationMethod2 = mFPNotificationMethod;
        if ((i & 64) != 0) {
            jsonObject = mfpNotificationActionItem.body;
        }
        return mfpNotificationActionItem.copy(mfpNotificationActionId, mfpNotificationIconId2, str4, str5, str6, mFPNotificationMethod2, jsonObject);
    }

    @Nullable
    public final MfpNotificationActionId component1() {
        return this.id;
    }

    @Nullable
    public final MfpNotificationIconId component2() {
        return this.iconId;
    }

    @Nullable
    public final String component3() {
        return this.title;
    }

    @Nullable
    public final String component4() {
        return this.deepLink;
    }

    @Nullable
    public final String component5() {
        return this.url;
    }

    @Nullable
    public final MFPNotificationMethod component6() {
        return this.method;
    }

    @Nullable
    public final JsonObject component7() {
        return this.body;
    }

    @NotNull
    public final MfpNotificationActionItem copy(@Nullable MfpNotificationActionId mfpNotificationActionId, @Nullable MfpNotificationIconId mfpNotificationIconId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable MFPNotificationMethod mFPNotificationMethod, @Nullable JsonObject jsonObject) {
        MfpNotificationActionItem mfpNotificationActionItem = new MfpNotificationActionItem(mfpNotificationActionId, mfpNotificationIconId, str, str2, str3, mFPNotificationMethod, jsonObject);
        return mfpNotificationActionItem;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.body, (java.lang.Object) r3.body) != false) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0051
            boolean r0 = r3 instanceof com.myfitnesspal.shared.model.v2.MfpNotificationActionItem
            if (r0 == 0) goto L_0x004f
            com.myfitnesspal.shared.model.v2.MfpNotificationActionItem r3 = (com.myfitnesspal.shared.model.v2.MfpNotificationActionItem) r3
            com.myfitnesspal.shared.model.v2.MfpNotificationActionId r0 = r2.id
            com.myfitnesspal.shared.model.v2.MfpNotificationActionId r1 = r3.id
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            com.myfitnesspal.shared.model.v2.MfpNotificationIconId r0 = r2.iconId
            com.myfitnesspal.shared.model.v2.MfpNotificationIconId r1 = r3.iconId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.title
            java.lang.String r1 = r3.title
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.deepLink
            java.lang.String r1 = r3.deepLink
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = r2.url
            java.lang.String r1 = r3.url
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            com.myfitnesspal.shared.model.v2.MFPNotificationMethod r0 = r2.method
            com.myfitnesspal.shared.model.v2.MFPNotificationMethod r1 = r3.method
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x004f
            com.google.gson.JsonObject r0 = r2.body
            com.google.gson.JsonObject r3 = r3.body
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x004f
            goto L_0x0051
        L_0x004f:
            r3 = 0
            return r3
        L_0x0051:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v2.MfpNotificationActionItem.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        MfpNotificationActionId mfpNotificationActionId = this.id;
        int i = 0;
        int hashCode = (mfpNotificationActionId != null ? mfpNotificationActionId.hashCode() : 0) * 31;
        MfpNotificationIconId mfpNotificationIconId = this.iconId;
        int hashCode2 = (hashCode + (mfpNotificationIconId != null ? mfpNotificationIconId.hashCode() : 0)) * 31;
        String str = this.title;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.deepLink;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.url;
        int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        MFPNotificationMethod mFPNotificationMethod = this.method;
        int hashCode6 = (hashCode5 + (mFPNotificationMethod != null ? mFPNotificationMethod.hashCode() : 0)) * 31;
        JsonObject jsonObject = this.body;
        if (jsonObject != null) {
            i = jsonObject.hashCode();
        }
        return hashCode6 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpNotificationActionItem(id=");
        sb.append(this.id);
        sb.append(", iconId=");
        sb.append(this.iconId);
        sb.append(", title=");
        sb.append(this.title);
        sb.append(", deepLink=");
        sb.append(this.deepLink);
        sb.append(", url=");
        sb.append(this.url);
        sb.append(", method=");
        sb.append(this.method);
        sb.append(", body=");
        sb.append(this.body);
        sb.append(")");
        return sb.toString();
    }

    public MfpNotificationActionItem(@Nullable MfpNotificationActionId mfpNotificationActionId, @Nullable MfpNotificationIconId mfpNotificationIconId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable MFPNotificationMethod mFPNotificationMethod, @Nullable JsonObject jsonObject) {
        this.id = mfpNotificationActionId;
        this.iconId = mfpNotificationIconId;
        this.title = str;
        this.deepLink = str2;
        this.url = str3;
        this.method = mFPNotificationMethod;
        this.body = jsonObject;
    }

    public /* synthetic */ MfpNotificationActionItem(MfpNotificationActionId mfpNotificationActionId, MfpNotificationIconId mfpNotificationIconId, String str, String str2, String str3, MFPNotificationMethod mFPNotificationMethod, JsonObject jsonObject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            mfpNotificationActionId = null;
        }
        MfpNotificationIconId mfpNotificationIconId2 = (i & 2) != 0 ? null : mfpNotificationIconId;
        this(mfpNotificationActionId, mfpNotificationIconId2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : mFPNotificationMethod, (i & 64) != 0 ? null : jsonObject);
    }

    @Nullable
    public final MfpNotificationActionId getId() {
        return this.id;
    }

    public final void setId(@Nullable MfpNotificationActionId mfpNotificationActionId) {
        this.id = mfpNotificationActionId;
    }

    @Nullable
    public final MfpNotificationIconId getIconId() {
        return this.iconId;
    }

    public final void setIconId(@Nullable MfpNotificationIconId mfpNotificationIconId) {
        this.iconId = mfpNotificationIconId;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    @Nullable
    public final String getDeepLink() {
        return this.deepLink;
    }

    public final void setDeepLink(@Nullable String str) {
        this.deepLink = str;
    }

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(@Nullable String str) {
        this.url = str;
    }

    @Nullable
    public final MFPNotificationMethod getMethod() {
        return this.method;
    }

    public final void setMethod(@Nullable MFPNotificationMethod mFPNotificationMethod) {
        this.method = mFPNotificationMethod;
    }

    @Nullable
    public final JsonObject getBody() {
        return this.body;
    }

    public final void setBody(@Nullable JsonObject jsonObject) {
        this.body = jsonObject;
    }
}
