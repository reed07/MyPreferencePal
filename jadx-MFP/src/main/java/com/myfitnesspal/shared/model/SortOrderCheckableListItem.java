package com.myfitnesspal.shared.model;

import com.myfitnesspal.feature.search.model.SortOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/shared/model/SortOrderCheckableListItem;", "Lcom/myfitnesspal/shared/model/CheckableListItem;", "description", "", "state", "", "sortOrderId", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "(Ljava/lang/String;ZLcom/myfitnesspal/feature/search/model/SortOrder;)V", "getSortOrderId", "()Lcom/myfitnesspal/feature/search/model/SortOrder;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SortOrderCheckableListItem.kt */
public final class SortOrderCheckableListItem extends CheckableListItem {
    @NotNull
    private final SortOrder sortOrderId;

    @NotNull
    public final SortOrder getSortOrderId() {
        return this.sortOrderId;
    }

    public SortOrderCheckableListItem(@NotNull String str, boolean z, @NotNull SortOrder sortOrder) {
        Intrinsics.checkParameterIsNotNull(str, "description");
        Intrinsics.checkParameterIsNotNull(sortOrder, "sortOrderId");
        super(str, z);
        this.sortOrderId = sortOrder;
    }
}
