package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/shared/model/v2/CategoryMappingResponse;", "", "category", "", "(Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: CategoryMappingResponse.kt */
public final class CategoryMappingResponse {
    @Nullable
    @Expose
    private final String category;

    public CategoryMappingResponse(@Nullable String str) {
        this.category = str;
    }

    @Nullable
    public final String getCategory() {
        return this.category;
    }
}
