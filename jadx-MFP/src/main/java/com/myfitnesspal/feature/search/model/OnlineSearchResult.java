package com.myfitnesspal.feature.search.model;

import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\tHÆ\u0003J=\u0010\u0016\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/myfitnesspal/feature/search/model/OnlineSearchResult;", "", "items", "", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "requestId", "", "nextPageLink", "sponsoredFood", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/myfitnesspal/feature/search/model/SponsoredFood;)V", "getItems", "()Ljava/util/List;", "getNextPageLink", "()Ljava/lang/String;", "getRequestId", "getSponsoredFood", "()Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnlineSearchResult.kt */
public final class OnlineSearchResult {
    @NotNull
    private final List<MfpFoodSearchResult> items;
    @Nullable
    private final String nextPageLink;
    @Nullable
    private final String requestId;
    @Nullable
    private final SponsoredFood sponsoredFood;

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<com.myfitnesspal.shared.model.v2.MfpFoodSearchResult>, for r1v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.myfitnesspal.feature.search.model.OnlineSearchResult copy$default(com.myfitnesspal.feature.search.model.OnlineSearchResult r0, java.util.List<com.myfitnesspal.shared.model.v2.MfpFoodSearchResult> r1, java.lang.String r2, java.lang.String r3, com.myfitnesspal.feature.search.model.SponsoredFood r4, int r5, java.lang.Object r6) {
        /*
            r6 = r5 & 1
            if (r6 == 0) goto L_0x0006
            java.util.List<com.myfitnesspal.shared.model.v2.MfpFoodSearchResult> r1 = r0.items
        L_0x0006:
            r6 = r5 & 2
            if (r6 == 0) goto L_0x000c
            java.lang.String r2 = r0.requestId
        L_0x000c:
            r6 = r5 & 4
            if (r6 == 0) goto L_0x0012
            java.lang.String r3 = r0.nextPageLink
        L_0x0012:
            r5 = r5 & 8
            if (r5 == 0) goto L_0x0018
            com.myfitnesspal.feature.search.model.SponsoredFood r4 = r0.sponsoredFood
        L_0x0018:
            com.myfitnesspal.feature.search.model.OnlineSearchResult r0 = r0.copy(r1, r2, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.model.OnlineSearchResult.copy$default(com.myfitnesspal.feature.search.model.OnlineSearchResult, java.util.List, java.lang.String, java.lang.String, com.myfitnesspal.feature.search.model.SponsoredFood, int, java.lang.Object):com.myfitnesspal.feature.search.model.OnlineSearchResult");
    }

    @NotNull
    public final List<MfpFoodSearchResult> component1() {
        return this.items;
    }

    @Nullable
    public final String component2() {
        return this.requestId;
    }

    @Nullable
    public final String component3() {
        return this.nextPageLink;
    }

    @Nullable
    public final SponsoredFood component4() {
        return this.sponsoredFood;
    }

    @NotNull
    public final OnlineSearchResult copy(@NotNull List<MfpFoodSearchResult> list, @Nullable String str, @Nullable String str2, @Nullable SponsoredFood sponsoredFood2) {
        Intrinsics.checkParameterIsNotNull(list, "items");
        return new OnlineSearchResult(list, str, str2, sponsoredFood2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.sponsoredFood, (java.lang.Object) r3.sponsoredFood) != false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0033
            boolean r0 = r3 instanceof com.myfitnesspal.feature.search.model.OnlineSearchResult
            if (r0 == 0) goto L_0x0031
            com.myfitnesspal.feature.search.model.OnlineSearchResult r3 = (com.myfitnesspal.feature.search.model.OnlineSearchResult) r3
            java.util.List<com.myfitnesspal.shared.model.v2.MfpFoodSearchResult> r0 = r2.items
            java.util.List<com.myfitnesspal.shared.model.v2.MfpFoodSearchResult> r1 = r3.items
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.lang.String r0 = r2.requestId
            java.lang.String r1 = r3.requestId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.lang.String r0 = r2.nextPageLink
            java.lang.String r1 = r3.nextPageLink
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            com.myfitnesspal.feature.search.model.SponsoredFood r0 = r2.sponsoredFood
            com.myfitnesspal.feature.search.model.SponsoredFood r3 = r3.sponsoredFood
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r3 = 0
            return r3
        L_0x0033:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.model.OnlineSearchResult.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        List<MfpFoodSearchResult> list = this.items;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.requestId;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.nextPageLink;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        SponsoredFood sponsoredFood2 = this.sponsoredFood;
        if (sponsoredFood2 != null) {
            i = sponsoredFood2.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OnlineSearchResult(items=");
        sb.append(this.items);
        sb.append(", requestId=");
        sb.append(this.requestId);
        sb.append(", nextPageLink=");
        sb.append(this.nextPageLink);
        sb.append(", sponsoredFood=");
        sb.append(this.sponsoredFood);
        sb.append(")");
        return sb.toString();
    }

    public OnlineSearchResult(@NotNull List<MfpFoodSearchResult> list, @Nullable String str, @Nullable String str2, @Nullable SponsoredFood sponsoredFood2) {
        Intrinsics.checkParameterIsNotNull(list, "items");
        this.items = list;
        this.requestId = str;
        this.nextPageLink = str2;
        this.sponsoredFood = sponsoredFood2;
    }

    @NotNull
    public final List<MfpFoodSearchResult> getItems() {
        return this.items;
    }

    public /* synthetic */ OnlineSearchResult(List list, String str, String str2, SponsoredFood sponsoredFood2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        if ((i & 8) != 0) {
            sponsoredFood2 = null;
        }
        this(list, str, str2, sponsoredFood2);
    }

    @Nullable
    public final String getRequestId() {
        return this.requestId;
    }

    @Nullable
    public final String getNextPageLink() {
        return this.nextPageLink;
    }

    @Nullable
    public final SponsoredFood getSponsoredFood() {
        return this.sponsoredFood;
    }
}
