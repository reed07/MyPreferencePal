package com.myfitnesspal.shared.model;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import com.brightcove.player.event.AbstractEvent;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.search.model.SearchResultType;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u0000 62\u00020\u0001:\u0003567B\u0001\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\t\u0010 \u001a\u00020\u0007HÂ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\nHÂ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010%\u001a\u00020\u0007HÂ\u0003J\u0001\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010'\u001a\u00020\u0007HÖ\u0001J\u0013\u0010(\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\b\u0010+\u001a\u00020\u0007H\u0016J\u000e\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020.J\t\u0010/\u001a\u00020\u0003HÖ\u0001J\u0019\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0007HÖ\u0001R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0002X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0002X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00078\u0002X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0002X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0002X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\t\u001a\u00020\n8\u0002X\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/myfitnesspal/shared/model/FoodV2Logging;", "Landroid/os/Parcelable;", "searchTerm", "", "foodId", "foodVersionId", "index", "", "servingSizeIndex", "verified", "", "source", "type", "Lcom/myfitnesspal/feature/search/model/SearchResultType;", "indexWithAd", "requestId", "listType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZLjava/lang/String;Lcom/myfitnesspal/feature/search/model/SearchResultType;ILjava/lang/String;Ljava/lang/String;)V", "getSearchTerm", "()Ljava/lang/String;", "getServingSizeIndex", "()I", "setServingSizeIndex", "(I)V", "getSource", "getType", "()Lcom/myfitnesspal/feature/search/model/SearchResultType;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "matchesMfpFood", "selectedFood", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Builder", "Companion", "LIST_MAPPER", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: FoodV2Logging.kt */
public final class FoodV2Logging implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    public static final Companion Companion = new Companion(null);
    @Expose
    private final String foodId;
    @Expose
    private final String foodVersionId;
    @Expose
    private final int index;
    @Expose
    private final int indexWithAd;
    @Expose
    private final String listType;
    @Expose
    private final String requestId;
    @Nullable
    @Expose
    private final String searchTerm;
    @Expose
    private int servingSizeIndex;
    @Nullable
    @Expose
    private final String source;
    @Nullable
    @Expose
    private final SearchResultType type;
    @Expose
    private final boolean verified;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 22\u00020\u0001:\u00012B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00100\u001a\u000201J\u0010\u0010\u0003\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\t\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0004J\u0010\u0010\u0018\u001a\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\rJ\u0010\u0010!\u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010\u0004J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R\u001c\u0010!\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001c\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u00063"}, d2 = {"Lcom/myfitnesspal/shared/model/FoodV2Logging$Builder;", "", "()V", "foodId", "", "getFoodId", "()Ljava/lang/String;", "setFoodId", "(Ljava/lang/String;)V", "foodVersionId", "getFoodVersionId", "setFoodVersionId", "index", "", "getIndex", "()I", "setIndex", "(I)V", "indexWithAd", "getIndexWithAd", "setIndexWithAd", "listType", "getListType", "setListType", "requestId", "getRequestId", "setRequestId", "searchTerm", "getSearchTerm", "setSearchTerm", "servingSizeIndex", "getServingSizeIndex", "setServingSizeIndex", "source", "getSource", "setSource", "type", "Lcom/myfitnesspal/feature/search/model/SearchResultType;", "getType", "()Lcom/myfitnesspal/feature/search/model/SearchResultType;", "setType", "(Lcom/myfitnesspal/feature/search/model/SearchResultType;)V", "verified", "", "getVerified", "()Z", "setVerified", "(Z)V", "build", "Lcom/myfitnesspal/shared/model/FoodV2Logging;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodV2Logging.kt */
    public static final class Builder {
        public static final Companion Companion = new Companion(null);
        @Nullable
        private String foodId;
        @Nullable
        private String foodVersionId;
        private int index;
        private int indexWithAd = this.index;
        @Nullable
        private String listType;
        @Nullable
        private String requestId;
        @Nullable
        private String searchTerm;
        private int servingSizeIndex;
        @Nullable
        private String source;
        @Nullable
        private SearchResultType type = SearchResultType.FOOD;
        private boolean verified;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/shared/model/FoodV2Logging$Builder$Companion;", "", "()V", "fromFood", "Lcom/myfitnesspal/shared/model/FoodV2Logging$Builder;", "food", "Lcom/myfitnesspal/shared/model/v1/Food;", "fromMfpFood", "mfpFood", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
        /* compiled from: FoodV2Logging.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            @NotNull
            public final Builder fromFood(@Nullable Food food) {
                Builder builder = new Builder();
                if (food != null) {
                    builder.setFoodId(food.getOriginalUid());
                    builder.setFoodVersionId(food.getUid());
                    builder.setVerified(food.isVerified());
                }
                return builder;
            }

            @JvmStatic
            @NotNull
            public final Builder fromMfpFood(@Nullable MfpFood mfpFood) {
                Builder builder = new Builder();
                if (mfpFood != null) {
                    builder.setFoodId(mfpFood.getId());
                    builder.setFoodVersionId(mfpFood.getVersion());
                    builder.setVerified(mfpFood.getVerified());
                }
                return builder;
            }
        }

        @JvmStatic
        @NotNull
        public static final Builder fromFood(@Nullable Food food) {
            return Companion.fromFood(food);
        }

        @JvmStatic
        @NotNull
        public static final Builder fromMfpFood(@Nullable MfpFood mfpFood) {
            return Companion.fromMfpFood(mfpFood);
        }

        @Nullable
        public final String getSearchTerm() {
            return this.searchTerm;
        }

        public final void setSearchTerm(@Nullable String str) {
            this.searchTerm = str;
        }

        @Nullable
        public final String getFoodId() {
            return this.foodId;
        }

        public final void setFoodId(@Nullable String str) {
            this.foodId = str;
        }

        @Nullable
        public final String getFoodVersionId() {
            return this.foodVersionId;
        }

        public final void setFoodVersionId(@Nullable String str) {
            this.foodVersionId = str;
        }

        public final int getIndex() {
            return this.index;
        }

        public final void setIndex(int i) {
            this.index = i;
        }

        public final int getServingSizeIndex() {
            return this.servingSizeIndex;
        }

        public final void setServingSizeIndex(int i) {
            this.servingSizeIndex = i;
        }

        public final boolean getVerified() {
            return this.verified;
        }

        public final void setVerified(boolean z) {
            this.verified = z;
        }

        @Nullable
        public final String getSource() {
            return this.source;
        }

        public final void setSource(@Nullable String str) {
            this.source = str;
        }

        @Nullable
        public final SearchResultType getType() {
            return this.type;
        }

        public final void setType(@Nullable SearchResultType searchResultType) {
            this.type = searchResultType;
        }

        public final int getIndexWithAd() {
            return this.indexWithAd;
        }

        public final void setIndexWithAd(int i) {
            this.indexWithAd = i;
        }

        @Nullable
        public final String getRequestId() {
            return this.requestId;
        }

        public final void setRequestId(@Nullable String str) {
            this.requestId = str;
        }

        @Nullable
        public final String getListType() {
            return this.listType;
        }

        public final void setListType(@Nullable String str) {
            this.listType = str;
        }

        @NotNull
        public final Builder searchTerm(@Nullable String str) {
            Builder builder = this;
            builder.searchTerm = str;
            return builder;
        }

        @NotNull
        public final Builder foodId(@Nullable String str) {
            Builder builder = this;
            builder.foodId = str;
            return builder;
        }

        @NotNull
        public final Builder foodVersionId(@Nullable String str) {
            Builder builder = this;
            builder.foodVersionId = str;
            return builder;
        }

        @NotNull
        public final Builder index(int i) {
            Builder builder = this;
            builder.index = i;
            return builder;
        }

        @NotNull
        public final Builder indexWithAd(int i) {
            Builder builder = this;
            builder.indexWithAd = i;
            return builder;
        }

        @NotNull
        public final Builder servingSizeIndex(int i) {
            Builder builder = this;
            builder.servingSizeIndex = i;
            return builder;
        }

        @NotNull
        public final Builder verified(boolean z) {
            Builder builder = this;
            builder.verified = z;
            return builder;
        }

        @NotNull
        public final Builder source(@Nullable String str) {
            Builder builder = this;
            builder.source = str;
            return builder;
        }

        @NotNull
        public final Builder type(@NotNull SearchResultType searchResultType) {
            Intrinsics.checkParameterIsNotNull(searchResultType, "type");
            Builder builder = this;
            builder.type = searchResultType;
            return builder;
        }

        @NotNull
        public final Builder requestId(@Nullable String str) {
            Builder builder = this;
            builder.requestId = str;
            return builder;
        }

        @NotNull
        public final Builder listType(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "listType");
            Builder builder = this;
            builder.listType = str;
            return builder;
        }

        @NotNull
        public final FoodV2Logging build() {
            FoodV2Logging foodV2Logging = new FoodV2Logging(this.searchTerm, this.foodId, this.foodVersionId, this.index, this.servingSizeIndex, this.verified, this.source, this.type, this.indexWithAd, this.requestId, this.listType);
            return foodV2Logging;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/shared/model/FoodV2Logging$Companion;", "", "()V", "listContainsAdFood", "", "list", "", "Lcom/myfitnesspal/shared/model/FoodV2Logging;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodV2Logging.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final boolean listContainsAdFood(@NotNull List<FoodV2Logging> list) {
            boolean z;
            Intrinsics.checkParameterIsNotNull(list, AbstractEvent.LIST);
            Iterable<FoodV2Logging> iterable = list;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return false;
            }
            for (FoodV2Logging type : iterable) {
                if (type.getType() == SearchResultType.FOOD_AD) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    return true;
                }
            }
            return false;
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            FoodV2Logging foodV2Logging = new FoodV2Logging(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readString(), parcel.readInt() != 0 ? (SearchResultType) Enum.valueOf(SearchResultType.class, parcel.readString()) : null, parcel.readInt(), parcel.readString(), parcel.readString());
            return foodV2Logging;
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new FoodV2Logging[i];
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/shared/model/FoodV2Logging$LIST_MAPPER;", "Ljava/util/ArrayList;", "Lcom/myfitnesspal/shared/model/FoodV2Logging;", "()V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodV2Logging.kt */
    public static final class LIST_MAPPER extends ArrayList<FoodV2Logging> {
        public /* bridge */ boolean contains(FoodV2Logging foodV2Logging) {
            return super.contains(foodV2Logging);
        }

        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof FoodV2Logging) {
                return contains((FoodV2Logging) obj);
            }
            return false;
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ int indexOf(FoodV2Logging foodV2Logging) {
            return super.indexOf(foodV2Logging);
        }

        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof FoodV2Logging) {
                return indexOf((FoodV2Logging) obj);
            }
            return -1;
        }

        public /* bridge */ int lastIndexOf(FoodV2Logging foodV2Logging) {
            return super.lastIndexOf(foodV2Logging);
        }

        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof FoodV2Logging) {
                return lastIndexOf((FoodV2Logging) obj);
            }
            return -1;
        }

        public final /* bridge */ FoodV2Logging remove(int i) {
            return removeAt(i);
        }

        public /* bridge */ boolean remove(FoodV2Logging foodV2Logging) {
            return super.remove(foodV2Logging);
        }

        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof FoodV2Logging) {
                return remove((FoodV2Logging) obj);
            }
            return false;
        }

        public /* bridge */ FoodV2Logging removeAt(int i) {
            return (FoodV2Logging) super.remove(i);
        }

        public final /* bridge */ int size() {
            return getSize();
        }
    }

    @JvmOverloads
    public FoodV2Logging() {
        this(null, null, null, 0, 0, false, null, null, 0, null, null, 2047, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str) {
        this(str, null, null, 0, 0, false, null, null, 0, null, null, 2046, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2) {
        this(str, str2, null, 0, 0, false, null, null, 0, null, null, 2044, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this(str, str2, str3, 0, 0, false, null, null, 0, null, null, 2040, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i) {
        this(str, str2, str3, i, 0, false, null, null, 0, null, null, 2032, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2) {
        this(str, str2, str3, i, i2, false, null, null, 0, null, null, 2016, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2, boolean z) {
        this(str, str2, str3, i, i2, z, null, null, 0, null, null, 1984, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2, boolean z, @Nullable String str4) {
        this(str, str2, str3, i, i2, z, str4, null, 0, null, null, 1920, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2, boolean z, @Nullable String str4, @Nullable SearchResultType searchResultType) {
        this(str, str2, str3, i, i2, z, str4, searchResultType, 0, null, null, 1792, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2, boolean z, @Nullable String str4, @Nullable SearchResultType searchResultType, int i3) {
        this(str, str2, str3, i, i2, z, str4, searchResultType, i3, null, null, 1536, null);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2, boolean z, @Nullable String str4, @Nullable SearchResultType searchResultType, int i3, @Nullable String str5) {
        this(str, str2, str3, i, i2, z, str4, searchResultType, i3, str5, null, 1024, null);
    }

    private final String component10() {
        return this.requestId;
    }

    private final String component11() {
        return this.listType;
    }

    private final String component2() {
        return this.foodId;
    }

    private final String component3() {
        return this.foodVersionId;
    }

    private final int component4() {
        return this.index;
    }

    private final boolean component6() {
        return this.verified;
    }

    private final int component9() {
        return this.indexWithAd;
    }

    @NotNull
    public static /* synthetic */ FoodV2Logging copy$default(FoodV2Logging foodV2Logging, String str, String str2, String str3, int i, int i2, boolean z, String str4, SearchResultType searchResultType, int i3, String str5, String str6, int i4, Object obj) {
        FoodV2Logging foodV2Logging2 = foodV2Logging;
        int i5 = i4;
        return foodV2Logging.copy((i5 & 1) != 0 ? foodV2Logging2.searchTerm : str, (i5 & 2) != 0 ? foodV2Logging2.foodId : str2, (i5 & 4) != 0 ? foodV2Logging2.foodVersionId : str3, (i5 & 8) != 0 ? foodV2Logging2.index : i, (i5 & 16) != 0 ? foodV2Logging2.servingSizeIndex : i2, (i5 & 32) != 0 ? foodV2Logging2.verified : z, (i5 & 64) != 0 ? foodV2Logging2.source : str4, (i5 & 128) != 0 ? foodV2Logging2.type : searchResultType, (i5 & 256) != 0 ? foodV2Logging2.indexWithAd : i3, (i5 & 512) != 0 ? foodV2Logging2.requestId : str5, (i5 & 1024) != 0 ? foodV2Logging2.listType : str6);
    }

    @JvmStatic
    public static final boolean listContainsAdFood(@NotNull List<FoodV2Logging> list) {
        return Companion.listContainsAdFood(list);
    }

    @Nullable
    public final String component1() {
        return this.searchTerm;
    }

    public final int component5() {
        return this.servingSizeIndex;
    }

    @Nullable
    public final String component7() {
        return this.source;
    }

    @Nullable
    public final SearchResultType component8() {
        return this.type;
    }

    @NotNull
    public final FoodV2Logging copy(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2, boolean z, @Nullable String str4, @Nullable SearchResultType searchResultType, int i3, @Nullable String str5, @Nullable String str6) {
        FoodV2Logging foodV2Logging = new FoodV2Logging(str, str2, str3, i, i2, z, str4, searchResultType, i3, str5, str6);
        return foodV2Logging;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof FoodV2Logging) {
                FoodV2Logging foodV2Logging = (FoodV2Logging) obj;
                if (Intrinsics.areEqual((Object) this.searchTerm, (Object) foodV2Logging.searchTerm) && Intrinsics.areEqual((Object) this.foodId, (Object) foodV2Logging.foodId) && Intrinsics.areEqual((Object) this.foodVersionId, (Object) foodV2Logging.foodVersionId)) {
                    if (this.index == foodV2Logging.index) {
                        if (this.servingSizeIndex == foodV2Logging.servingSizeIndex) {
                            if ((this.verified == foodV2Logging.verified) && Intrinsics.areEqual((Object) this.source, (Object) foodV2Logging.source) && Intrinsics.areEqual((Object) this.type, (Object) foodV2Logging.type)) {
                                if (!(this.indexWithAd == foodV2Logging.indexWithAd) || !Intrinsics.areEqual((Object) this.requestId, (Object) foodV2Logging.requestId) || !Intrinsics.areEqual((Object) this.listType, (Object) foodV2Logging.listType)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FoodV2Logging(searchTerm=");
        sb.append(this.searchTerm);
        sb.append(", foodId=");
        sb.append(this.foodId);
        sb.append(", foodVersionId=");
        sb.append(this.foodVersionId);
        sb.append(", index=");
        sb.append(this.index);
        sb.append(", servingSizeIndex=");
        sb.append(this.servingSizeIndex);
        sb.append(", verified=");
        sb.append(this.verified);
        sb.append(", source=");
        sb.append(this.source);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", indexWithAd=");
        sb.append(this.indexWithAd);
        sb.append(", requestId=");
        sb.append(this.requestId);
        sb.append(", listType=");
        sb.append(this.listType);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeString(this.searchTerm);
        parcel.writeString(this.foodId);
        parcel.writeString(this.foodVersionId);
        parcel.writeInt(this.index);
        parcel.writeInt(this.servingSizeIndex);
        parcel.writeInt(this.verified ? 1 : 0);
        parcel.writeString(this.source);
        SearchResultType searchResultType = this.type;
        if (searchResultType != null) {
            parcel.writeInt(1);
            parcel.writeString(searchResultType.name());
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.indexWithAd);
        parcel.writeString(this.requestId);
        parcel.writeString(this.listType);
    }

    @JvmOverloads
    public FoodV2Logging(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2, boolean z, @Nullable String str4, @Nullable SearchResultType searchResultType, int i3, @Nullable String str5, @Nullable String str6) {
        this.searchTerm = str;
        this.foodId = str2;
        this.foodVersionId = str3;
        this.index = i;
        this.servingSizeIndex = i2;
        this.verified = z;
        this.source = str4;
        this.type = searchResultType;
        this.indexWithAd = i3;
        this.requestId = str5;
        this.listType = str6;
    }

    @JvmOverloads
    public /* synthetic */ FoodV2Logging(String str, String str2, String str3, int i, int i2, boolean z, String str4, SearchResultType searchResultType, int i3, String str5, String str6, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        int i5 = i4;
        String str7 = (i5 & 1) != 0 ? null : str;
        String str8 = (i5 & 2) != 0 ? null : str2;
        String str9 = (i5 & 4) != 0 ? null : str3;
        boolean z2 = false;
        int i6 = (i5 & 8) != 0 ? 0 : i;
        int i7 = (i5 & 16) != 0 ? 0 : i2;
        if ((i5 & 32) == 0) {
            z2 = z;
        }
        this(str7, str8, str9, i6, i7, z2, (i5 & 64) != 0 ? null : str4, (i5 & 128) != 0 ? SearchResultType.FOOD : searchResultType, (i5 & 256) != 0 ? i6 : i3, (i5 & 512) != 0 ? null : str5, (i5 & 1024) != 0 ? null : str6);
    }

    @Nullable
    public final String getSearchTerm() {
        return this.searchTerm;
    }

    public final int getServingSizeIndex() {
        return this.servingSizeIndex;
    }

    public final void setServingSizeIndex(int i) {
        this.servingSizeIndex = i;
    }

    @Nullable
    public final String getSource() {
        return this.source;
    }

    @Nullable
    public final SearchResultType getType() {
        return this.type;
    }

    public int hashCode() {
        if (VERSION.SDK_INT > 23) {
            return super.hashCode();
        }
        String str = this.searchTerm;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.foodId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.foodVersionId;
        int hashCode3 = (((((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.index) * 31) + this.servingSizeIndex) * 31) + (this.verified ? 1 : 0)) * 31;
        String str4 = this.source;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        SearchResultType searchResultType = this.type;
        int hashCode5 = (((hashCode4 + (searchResultType != null ? searchResultType.hashCode() : 0)) * 31) + this.indexWithAd) * 31;
        String str5 = this.requestId;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.listType;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return hashCode6 + i;
    }

    public final boolean matchesMfpFood(@NotNull MfpFood mfpFood) {
        Intrinsics.checkParameterIsNotNull(mfpFood, "selectedFood");
        return Strings.equals(this.foodId, mfpFood.getId()) && Strings.equals(this.foodVersionId, mfpFood.getVersion());
    }
}
