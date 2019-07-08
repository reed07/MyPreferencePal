package com.myfitnesspal.feature.search.model;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SearchSource;", "", "title", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "LOCAL", "ONLINE", "BARCODE_SCAN", "BARCODE_MISS", "RESTAURANT_LOGGING", "RECIPE_PARSER", "RECIPE_DETAILS_SCREEN", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SearchSource.kt */
public enum SearchSource {
    LOCAL(Attributes.LOCAL_SEARCH),
    ONLINE("online_search"),
    BARCODE_SCAN("scan"),
    BARCODE_MISS(Attributes.BARCODE_MISS),
    RESTAURANT_LOGGING("restaurant_logging"),
    RECIPE_PARSER("recipe_parser"),
    RECIPE_DETAILS_SCREEN("recipe_details_screen");
    
    @NotNull
    private final String title;

    protected SearchSource(String str) {
        Intrinsics.checkParameterIsNotNull(str, "title");
        this.title = str;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }
}
