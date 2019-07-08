package com.myfitnesspal.feature.search.model;

import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult.DataTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SearchResultType;", "", "title", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "FOOD", "VENUE", "FOOD_AD", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SearchResultType.kt */
public enum SearchResultType {
    FOOD("food"),
    VENUE(DataTypes.VENUE),
    FOOD_AD("food_ad");
    
    @NotNull
    private final String title;

    protected SearchResultType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "title");
        this.title = str;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }
}
