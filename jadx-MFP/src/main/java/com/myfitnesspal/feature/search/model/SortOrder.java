package com.myfitnesspal.feature.search.model;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SortOrder;", "", "anltValue", "", "shortName", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAnltValue", "()Ljava/lang/String;", "getShortName", "NONE", "ALPHABETICAL_ASCENDING", "ALPHABETICAL_DESCENDING", "RECENTLY_USED", "FREQUENTLY_USED", "DATE_ASCENDING", "DATE_DESCENDING", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SortOrder.kt */
public enum SortOrder {
    NONE("most_recent", null),
    ALPHABETICAL_ASCENDING("A_to_Z", "alpha_asc"),
    ALPHABETICAL_DESCENDING("Z_to_A", "alpha_desc"),
    RECENTLY_USED("most_recent", "recently_used"),
    FREQUENTLY_USED("frequently_used", "frequent"),
    DATE_ASCENDING("date_created", "date_asc"),
    DATE_DESCENDING("date_created", "date_desc");
    
    public static final Companion Companion = null;
    @NotNull
    private final String anltValue;
    @Nullable
    private final String shortName;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SortOrder$Companion;", "", "()V", "fromShortName", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "shortName", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SortOrder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SortOrder fromShortName(@Nullable String str) {
            SortOrder[] values;
            for (SortOrder sortOrder : SortOrder.values()) {
                if (Intrinsics.areEqual((Object) sortOrder.getShortName(), (Object) str)) {
                    return sortOrder;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
    }

    @JvmStatic
    @NotNull
    public static final SortOrder fromShortName(@Nullable String str) {
        return Companion.fromShortName(str);
    }

    protected SortOrder(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "anltValue");
        this.anltValue = str;
        this.shortName = str2;
    }

    @NotNull
    public final String getAnltValue() {
        return this.anltValue;
    }

    @Nullable
    public final String getShortName() {
        return this.shortName;
    }

    static {
        Companion = new Companion(null);
    }
}
