package com.myfitnesspal.feature.search.ui.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageServiceUtil;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.shared.model.FoodImages;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.view.GlideHideProgressListener;
import com.myfitnesspal.shared.ui.view.StyledTextView;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 T2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003TUVBu\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000f\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f¢\u0006\u0002\u0010\u0017J\u0006\u0010K\u001a\u00020,J\b\u0010L\u001a\u00020\u001dH\u0016J\u0010\u0010M\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u001dH\u0016J\u0018\u0010N\u001a\u00020,2\u0006\u0010O\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u001dH\u0016J\u0018\u0010P\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u001dH\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R0\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R5\u0010&\u001a\u001d\u0012\u0013\u0012\u00110(¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,0'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R_\u00101\u001aG\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\r¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020,02X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109RJ\u0010:\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020,0;X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?RJ\u0010@\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020,0;X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010=\"\u0004\bB\u0010?R\u001a\u0010+\u001a\u00020(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\r8BX\u000e¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u000e\u0010J\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "items", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "foodImages", "Lcom/myfitnesspal/shared/model/FoodImages;", "context", "Landroid/content/Context;", "foodSearchTab", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "isShowingPairedFoods", "", "multiAddHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "userEnergyService", "Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;", "imageService", "Lcom/myfitnesspal/feature/images/service/ImageService;", "mealUtil", "Lcom/myfitnesspal/feature/meals/util/MealUtil;", "(Ljava/util/List;Lcom/myfitnesspal/shared/model/FoodImages;Landroid/content/Context;Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;ZLdagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;)V", "getFoodImages", "()Lcom/myfitnesspal/shared/model/FoodImages;", "setFoodImages", "(Lcom/myfitnesspal/shared/model/FoodImages;)V", "itemCount", "", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "value", "multiAddedItems", "getMultiAddedItems", "setMultiAddedItems", "onFooterClick", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "query", "", "getOnFooterClick", "()Lkotlin/jvm/functions/Function1;", "setOnFooterClick", "(Lkotlin/jvm/functions/Function1;)V", "onItemCheckedChange", "Lkotlin/Function3;", "item", "position", "isChecked", "getOnItemCheckedChange", "()Lkotlin/jvm/functions/Function3;", "setOnItemCheckedChange", "(Lkotlin/jvm/functions/Function3;)V", "onItemClick", "Lkotlin/Function2;", "getOnItemClick", "()Lkotlin/jvm/functions/Function2;", "setOnItemClick", "(Lkotlin/jvm/functions/Function2;)V", "onItemLongClick", "getOnItemLongClick", "setOnItemLongClick", "getQuery", "()Ljava/lang/String;", "setQuery", "(Ljava/lang/String;)V", "shouldShowFooter", "getShouldShowFooter", "()Z", "showFoodImages", "clearItems", "getItemCount", "getItemViewType", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "FoodViewHolder", "FooterViewHolder", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchAdapter.kt */
public final class LocalFoodSearchAdapter extends Adapter<ViewHolder> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final RequestOptions IMAGE_REQUEST_OPTIONS;
    private static final int VIEW_TYPE_FOOTER = 101;
    private static final int VIEW_TYPE_ITEM = 100;
    /* access modifiers changed from: private */
    public final Context context;
    @Nullable
    private FoodImages foodImages;
    /* access modifiers changed from: private */
    public final FoodSearchTab foodSearchTab;
    /* access modifiers changed from: private */
    public final Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public final boolean isShowingPairedFoods;
    private int itemCount;
    @NotNull
    private List<? extends DiaryEntryCellModel> items;
    /* access modifiers changed from: private */
    public final Lazy<MealUtil> mealUtil;
    /* access modifiers changed from: private */
    public final Lazy<MultiAddFoodHelper> multiAddHelper;
    @NotNull
    private List<? extends DiaryEntryCellModel> multiAddedItems;
    @NotNull
    private Function1<? super String, Unit> onFooterClick;
    @NotNull
    private Function3<? super DiaryEntryCellModel, ? super Integer, ? super Boolean, Unit> onItemCheckedChange;
    @NotNull
    private Function2<? super DiaryEntryCellModel, ? super Integer, Unit> onItemClick;
    @NotNull
    private Function2<? super DiaryEntryCellModel, ? super Integer, Unit> onItemLongClick;
    @NotNull
    private String query;
    private boolean shouldShowFooter;
    /* access modifiers changed from: private */
    public final boolean showFoodImages;
    /* access modifiers changed from: private */
    public final Lazy<UserEnergyService> userEnergyService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter$Companion;", "", "()V", "IMAGE_REQUEST_OPTIONS", "Lcom/bumptech/glide/request/RequestOptions;", "getIMAGE_REQUEST_OPTIONS", "()Lcom/bumptech/glide/request/RequestOptions;", "VIEW_TYPE_FOOTER", "", "VIEW_TYPE_ITEM", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LocalFoodSearchAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final RequestOptions getIMAGE_REQUEST_OPTIONS() {
            return LocalFoodSearchAdapter.IMAGE_REQUEST_OPTIONS;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter$FoodViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter;Landroid/view/View;)V", "imageLoadListener", "Lcom/myfitnesspal/shared/ui/view/GlideHideProgressListener;", "bindData", "", "item", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "loadImage", "Lcom/myfitnesspal/shared/model/v1/MealFood;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LocalFoodSearchAdapter.kt */
    public final class FoodViewHolder extends ViewHolder {
        private final GlideHideProgressListener imageLoadListener = new GlideHideProgressListener();
        final /* synthetic */ LocalFoodSearchAdapter this$0;

        public FoodViewHolder(LocalFoodSearchAdapter localFoodSearchAdapter, @NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
            this.this$0 = localFoodSearchAdapter;
            super(view);
            View view2 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
            View findViewById = view2.findViewById(R.id.generic_list_item_divider);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.generic_list_item_divider");
            findViewById.setVisibility(8);
            View view3 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view3, "itemView");
            StyledTextView styledTextView = (StyledTextView) view3.findViewById(R.id.txtCalories);
            Intrinsics.checkExpressionValueIsNotNull(styledTextView, "itemView.txtCalories");
            styledTextView.setVisibility(0);
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x006a  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x007d  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0098  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x00ab  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x00c1  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x00c3  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x00c7  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0174  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bindData(@org.jetbrains.annotations.NotNull com.myfitnesspal.shared.model.v1.DiaryEntryCellModel r10) {
            /*
                r9 = this;
                java.lang.String r0 = "item"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
                boolean r0 = r10 instanceof com.myfitnesspal.shared.model.v1.MealFood
                if (r0 == 0) goto L_0x0018
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r1 = r9.this$0
                boolean r1 = r1.showFoodImages
                if (r1 == 0) goto L_0x0018
                r1 = r10
                com.myfitnesspal.shared.model.v1.MealFood r1 = (com.myfitnesspal.shared.model.v1.MealFood) r1
                r9.loadImage(r1)
                goto L_0x0033
            L_0x0018:
                android.view.View r1 = r9.itemView
                java.lang.String r2 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                int r2 = com.myfitnesspal.android.R.id.image_container
                android.view.View r1 = r1.findViewById(r2)
                android.widget.FrameLayout r1 = (android.widget.FrameLayout) r1
                java.lang.String r2 = "itemView.image_container"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                android.view.View r1 = (android.view.View) r1
                r2 = 8
                r1.setVisibility(r2)
            L_0x0033:
                boolean r1 = r10.isFoodEntry()
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L_0x004f
                r1 = r10
                com.myfitnesspal.shared.model.v1.FoodEntry r1 = (com.myfitnesspal.shared.model.v1.FoodEntry) r1
                com.myfitnesspal.shared.model.v1.Food r1 = r1.getFood()
                java.lang.String r4 = "(item as FoodEntry).food"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                boolean r1 = r1.isVerified()
                if (r1 == 0) goto L_0x004f
                r1 = 1
                goto L_0x0050
            L_0x004f:
                r1 = 0
            L_0x0050:
                android.view.View r4 = r9.itemView
                java.lang.String r5 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
                int r5 = com.myfitnesspal.android.R.id.text_primary
                android.view.View r4 = r4.findViewById(r5)
                android.widget.TextView r4 = (android.widget.TextView) r4
                java.lang.String r5 = "itemView.text_primary"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
                boolean r5 = r10.isMealEntries()
                if (r5 == 0) goto L_0x007d
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r5 = r9.this$0
                dagger.Lazy r5 = r5.userEnergyService
                java.lang.Object r5 = r5.get()
                com.myfitnesspal.shared.service.userdata.UserEnergyService r5 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r5
                java.lang.String r5 = r5.getMealEntriesTitle(r10)
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5
                goto L_0x0083
            L_0x007d:
                java.lang.String r5 = r10.summaryLine1()
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            L_0x0083:
                r4.setText(r5)
                android.view.View r4 = r9.itemView
                java.lang.String r5 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
                int r5 = com.myfitnesspal.android.R.id.text_primary
                android.view.View r4 = r4.findViewById(r5)
                android.widget.TextView r4 = (android.widget.TextView) r4
                r5 = 0
                if (r1 == 0) goto L_0x00ab
                android.view.View r1 = r9.itemView
                java.lang.String r6 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                android.content.Context r1 = r1.getContext()
                r6 = 2131231406(0x7f0802ae, float:1.8078892E38)
                android.graphics.drawable.Drawable r1 = android.support.v4.content.ContextCompat.getDrawable(r1, r6)
                goto L_0x00ac
            L_0x00ab:
                r1 = r5
            L_0x00ac:
                r4.setCompoundDrawablesWithIntrinsicBounds(r5, r5, r1, r5)
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r1 = r9.this$0
                dagger.Lazy r1 = r1.multiAddHelper
                java.lang.Object r1 = r1.get()
                com.myfitnesspal.shared.util.MultiAddFoodHelper r1 = (com.myfitnesspal.shared.util.MultiAddFoodHelper) r1
                com.myfitnesspal.shared.model.v1.DiaryEntryCellModel r1 = r1.getItemWithSameId(r10)
                if (r1 == 0) goto L_0x00c3
                r4 = 1
                goto L_0x00c4
            L_0x00c3:
                r4 = 0
            L_0x00c4:
                if (r4 == 0) goto L_0x00c7
                goto L_0x00c8
            L_0x00c7:
                r1 = r10
            L_0x00c8:
                if (r0 == 0) goto L_0x011a
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r0 = r9.this$0
                com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r0 = r0.foodSearchTab
                com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r6 = com.myfitnesspal.feature.search.ui.constants.FoodSearchTab.MEALS
                if (r0 != r6) goto L_0x011a
                if (r1 == 0) goto L_0x0112
                r0 = r1
                com.myfitnesspal.shared.model.v1.MealFood r0 = (com.myfitnesspal.shared.model.v1.MealFood) r0
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r6 = r9.this$0
                dagger.Lazy r6 = r6.mealUtil
                java.lang.Object r6 = r6.get()
                com.myfitnesspal.feature.meals.util.MealUtil r6 = (com.myfitnesspal.feature.meals.util.MealUtil) r6
                com.myfitnesspal.shared.model.v2.MfpNutritionalContents r0 = r6.getNutritionalContents(r0)
                android.view.View r6 = r9.itemView
                java.lang.String r7 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
                int r7 = com.myfitnesspal.android.R.id.text_secondary
                android.view.View r6 = r6.findViewById(r7)
                android.widget.TextView r6 = (android.widget.TextView) r6
                java.lang.String r7 = "itemView.text_secondary"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
                android.view.View r7 = r9.itemView
                java.lang.String r8 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
                android.content.Context r7 = r7.getContext()
                java.lang.String r0 = com.myfitnesspal.shared.util.NutritionUtils.getNutritionalMacrosDetails(r7, r0)
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                r6.setText(r0)
                goto L_0x0143
            L_0x0112:
                kotlin.TypeCastException r10 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type com.myfitnesspal.shared.model.v1.MealFood"
                r10.<init>(r0)
                throw r10
            L_0x011a:
                android.view.View r0 = r9.itemView
                java.lang.String r6 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r6)
                int r6 = com.myfitnesspal.android.R.id.text_secondary
                android.view.View r0 = r0.findViewById(r6)
                android.widget.TextView r0 = (android.widget.TextView) r0
                java.lang.String r6 = "itemView.text_secondary"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r6)
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r6 = r9.this$0
                dagger.Lazy r6 = r6.userEnergyService
                java.lang.Object r6 = r6.get()
                com.myfitnesspal.shared.service.userdata.UserEnergyService r6 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r6
                java.lang.String r6 = r6.getDescription(r1, r3)
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                r0.setText(r6)
            L_0x0143:
                android.view.View r0 = r9.itemView
                java.lang.String r6 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r6)
                int r6 = com.myfitnesspal.android.R.id.txtCalories
                android.view.View r0 = r0.findViewById(r6)
                com.myfitnesspal.shared.ui.view.StyledTextView r0 = (com.myfitnesspal.shared.ui.view.StyledTextView) r0
                java.lang.String r6 = "itemView.txtCalories"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r6)
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r6 = r9.this$0
                dagger.Lazy r6 = r6.userEnergyService
                java.lang.Object r6 = r6.get()
                com.myfitnesspal.shared.service.userdata.UserEnergyService r6 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r6
                java.lang.String r1 = r6.getDisplayableEnergy(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setText(r1)
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r0 = r9.this$0
                boolean r0 = r0.isShowingPairedFoods
                if (r0 != 0) goto L_0x0195
                android.view.View r0 = r9.itemView
                java.lang.String r1 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                r1 = 2131165509(0x7f070145, float:1.7945237E38)
                float r0 = com.myfitnesspal.shared.extension.ViewExtKt.dp(r0, r1)
                int r0 = (int) r0
                android.view.View r1 = r9.itemView
                java.lang.String r6 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                int r6 = com.myfitnesspal.android.R.id.multiSelectCheckBox
                android.view.View r1 = r1.findViewById(r6)
                android.widget.CheckBox r1 = (android.widget.CheckBox) r1
                com.uacf.core.util.ViewUtils.increaseHitRectBy(r0, r1)
            L_0x0195:
                android.view.View r0 = r9.itemView
                java.lang.String r1 = "itemView"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                int r1 = com.myfitnesspal.android.R.id.multiSelectCheckBox
                android.view.View r0 = r0.findViewById(r1)
                android.widget.CheckBox r0 = (android.widget.CheckBox) r0
                r0.setOnCheckedChangeListener(r5)
                java.lang.String r1 = "this"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                r0.setChecked(r4)
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter$FoodViewHolder$bindData$$inlined$apply$lambda$1 r1 = new com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter$FoodViewHolder$bindData$$inlined$apply$lambda$1
                r1.<init>(r9, r4, r10)
                android.widget.CompoundButton$OnCheckedChangeListener r1 = (android.widget.CompoundButton.OnCheckedChangeListener) r1
                r0.setOnCheckedChangeListener(r1)
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r1 = r9.this$0
                dagger.Lazy r1 = r1.multiAddHelper
                java.lang.Object r1 = r1.get()
                java.lang.String r4 = "multiAddHelper.get()"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                com.myfitnesspal.shared.util.MultiAddFoodHelper r1 = (com.myfitnesspal.shared.util.MultiAddFoodHelper) r1
                boolean r1 = r1.isMultiAddModeOn()
                if (r1 != 0) goto L_0x01db
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter r1 = r9.this$0
                boolean r1 = r1.isShowingPairedFoods
                if (r1 == 0) goto L_0x01d9
                goto L_0x01db
            L_0x01d9:
                r1 = 0
                goto L_0x01dc
            L_0x01db:
                r1 = 1
            L_0x01dc:
                android.view.View[] r2 = new android.view.View[r2]
                android.view.View r0 = (android.view.View) r0
                r2[r3] = r0
                com.uacf.core.util.ViewUtils.setVisible(r1, r2)
                android.view.View r0 = r9.itemView
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter$FoodViewHolder$bindData$2 r1 = new com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter$FoodViewHolder$bindData$2
                r1.<init>(r9, r10)
                android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
                r0.setOnClickListener(r1)
                android.view.View r0 = r9.itemView
                com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter$FoodViewHolder$bindData$3 r1 = new com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter$FoodViewHolder$bindData$3
                r1.<init>(r9, r10)
                android.view.View$OnLongClickListener r1 = (android.view.View.OnLongClickListener) r1
                r0.setOnLongClickListener(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter.FoodViewHolder.bindData(com.myfitnesspal.shared.model.v1.DiaryEntryCellModel):void");
        }

        private final void loadImage(MealFood mealFood) {
            String str;
            FoodImages foodImages = this.this$0.getFoodImages();
            MfpImage image = foodImages != null ? foodImages.getImage((Food) mealFood) : null;
            if (image != null) {
                View view = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
                str = ImageServiceUtil.getImageThumbnailUri(view.getContext(), (ImageService) this.this$0.imageService.get(), image);
            } else {
                str = null;
            }
            View view2 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
            FrameLayout frameLayout = (FrameLayout) view2.findViewById(R.id.image_container);
            Intrinsics.checkExpressionValueIsNotNull(frameLayout, "itemView.image_container");
            boolean z = false;
            frameLayout.setVisibility(0);
            if (mealFood.isAutoGeneratedMeal()) {
                View view3 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view3, "itemView");
                int color = ContextCompat.getColor(view3.getContext(), R.color.meal_photo_placeholder_image_bg);
                View view4 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view4, "itemView");
                ImageView imageView = (ImageView) view4.findViewById(R.id.image_view);
                imageView.setImageResource(R.drawable.ic_meal_repeat);
                Intrinsics.checkExpressionValueIsNotNull(imageView, "it");
                imageView.setBackground(new ColorDrawable(color));
                GlideHideProgressListener glideHideProgressListener = this.imageLoadListener;
                View view5 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view5, "itemView");
                glideHideProgressListener.setLoaded((ProgressBar) view5.findViewById(R.id.progress), imageView);
                return;
            }
            View view6 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view6, "itemView");
            ImageView imageView2 = (ImageView) view6.findViewById(R.id.image_view);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "itemView.image_view");
            imageView2.setBackground(null);
            CharSequence charSequence = str;
            if (charSequence == null || charSequence.length() == 0) {
                z = true;
            }
            if (!z) {
                GlideHideProgressListener glideHideProgressListener2 = this.imageLoadListener;
                View view7 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view7, "itemView");
                ProgressBar progressBar = (ProgressBar) view7.findViewById(R.id.progress);
                View view8 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view8, "itemView");
                glideHideProgressListener2.setLoading(progressBar, (ImageView) view8.findViewById(R.id.image_view));
                View view9 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view9, "itemView");
                RequestBuilder apply = Glide.with(view9.getContext()).load(str).listener(this.imageLoadListener).apply(LocalFoodSearchAdapter.Companion.getIMAGE_REQUEST_OPTIONS());
                View view10 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view10, "itemView");
                Intrinsics.checkExpressionValueIsNotNull(apply.into((ImageView) view10.findViewById(R.id.image_view)), "Glide.with(itemView.cont…into(itemView.image_view)");
                return;
            }
            View view11 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view11, "itemView");
            ((ImageView) view11.findViewById(R.id.image_view)).setImageResource(R.drawable.ic_meal_photos_placeholder);
            GlideHideProgressListener glideHideProgressListener3 = this.imageLoadListener;
            View view12 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view12, "itemView");
            ProgressBar progressBar2 = (ProgressBar) view12.findViewById(R.id.progress);
            View view13 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view13, "itemView");
            glideHideProgressListener3.setLoaded(progressBar2, (ImageView) view13.findViewById(R.id.image_view));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter$FooterViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter;Landroid/view/View;)V", "setQueryString", "", "queryString", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LocalFoodSearchAdapter.kt */
    public final class FooterViewHolder extends ViewHolder {
        final /* synthetic */ LocalFoodSearchAdapter this$0;

        public FooterViewHolder(LocalFoodSearchAdapter localFoodSearchAdapter, @NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
            this.this$0 = localFoodSearchAdapter;
            super(view);
        }

        public final void setQueryString(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "queryString");
            Context access$getContext$p = this.this$0.context;
            if (access$getContext$p != null) {
                View view = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
                TextView textView = (TextView) view.findViewById(R.id.searchForTextView);
                Intrinsics.checkExpressionValueIsNotNull(textView, "itemView.searchForTextView");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = access$getContext$p.getString(R.string.search_all_foods_for);
                Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(R.string.search_all_foods_for)");
                Object[] objArr = {str};
                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                textView.setText(format);
                this.itemView.setOnClickListener(new LocalFoodSearchAdapter$FooterViewHolder$setQueryString$$inlined$let$lambda$1(this, str));
            }
        }
    }

    public /* synthetic */ LocalFoodSearchAdapter(List list, FoodImages foodImages2, Context context2, FoodSearchTab foodSearchTab2, boolean z, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? null : foodImages2, context2, foodSearchTab2, (i & 16) != 0 ? false : z, lazy, lazy2, lazy3, lazy4);
    }

    @NotNull
    public final List<DiaryEntryCellModel> getItems() {
        return this.items;
    }

    public final void setItems(@NotNull List<? extends DiaryEntryCellModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.items = list;
    }

    @Nullable
    public final FoodImages getFoodImages() {
        return this.foodImages;
    }

    public final void setFoodImages(@Nullable FoodImages foodImages2) {
        this.foodImages = foodImages2;
    }

    public LocalFoodSearchAdapter(@NotNull List<? extends DiaryEntryCellModel> list, @Nullable FoodImages foodImages2, @Nullable Context context2, @NotNull FoodSearchTab foodSearchTab2, boolean z, @NotNull Lazy<MultiAddFoodHelper> lazy, @NotNull Lazy<UserEnergyService> lazy2, @NotNull Lazy<ImageService> lazy3, @NotNull Lazy<MealUtil> lazy4) {
        Intrinsics.checkParameterIsNotNull(list, "items");
        Intrinsics.checkParameterIsNotNull(foodSearchTab2, "foodSearchTab");
        Intrinsics.checkParameterIsNotNull(lazy, "multiAddHelper");
        Intrinsics.checkParameterIsNotNull(lazy2, "userEnergyService");
        Intrinsics.checkParameterIsNotNull(lazy3, "imageService");
        Intrinsics.checkParameterIsNotNull(lazy4, "mealUtil");
        this.items = list;
        this.foodImages = foodImages2;
        this.context = context2;
        this.foodSearchTab = foodSearchTab2;
        this.isShowingPairedFoods = z;
        this.multiAddHelper = lazy;
        this.userEnergyService = lazy2;
        this.imageService = lazy3;
        this.mealUtil = lazy4;
        this.multiAddedItems = CollectionsKt.emptyList();
        this.query = "";
        this.onItemClick = LocalFoodSearchAdapter$onItemClick$1.INSTANCE;
        this.onItemLongClick = LocalFoodSearchAdapter$onItemLongClick$1.INSTANCE;
        this.onItemCheckedChange = LocalFoodSearchAdapter$onItemCheckedChange$1.INSTANCE;
        this.onFooterClick = LocalFoodSearchAdapter$onFooterClick$1.INSTANCE;
        this.showFoodImages = this.foodSearchTab == FoodSearchTab.MEALS;
    }

    @NotNull
    public final List<DiaryEntryCellModel> getMultiAddedItems() {
        return this.multiAddedItems;
    }

    public final void setMultiAddedItems(@NotNull List<? extends DiaryEntryCellModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "value");
        this.multiAddedItems = list;
        notifyDataSetChanged();
    }

    @NotNull
    public final String getQuery() {
        return this.query;
    }

    public final void setQuery(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.query = str;
    }

    private final boolean getShouldShowFooter() {
        return this.query.length() == 0;
    }

    @NotNull
    public final Function2<DiaryEntryCellModel, Integer, Unit> getOnItemClick() {
        return this.onItemClick;
    }

    public final void setOnItemClick(@NotNull Function2<? super DiaryEntryCellModel, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.onItemClick = function2;
    }

    @NotNull
    public final Function2<DiaryEntryCellModel, Integer, Unit> getOnItemLongClick() {
        return this.onItemLongClick;
    }

    public final void setOnItemLongClick(@NotNull Function2<? super DiaryEntryCellModel, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.onItemLongClick = function2;
    }

    @NotNull
    public final Function3<DiaryEntryCellModel, Integer, Boolean, Unit> getOnItemCheckedChange() {
        return this.onItemCheckedChange;
    }

    public final void setOnItemCheckedChange(@NotNull Function3<? super DiaryEntryCellModel, ? super Integer, ? super Boolean, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "<set-?>");
        this.onItemCheckedChange = function3;
    }

    @NotNull
    public final Function1<String, Unit> getOnFooterClick() {
        return this.onFooterClick;
    }

    public final void setOnFooterClick(@NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.onFooterClick = function1;
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        if (i == 101) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer_local_food_search, viewGroup, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…od_search, parent, false)");
            return new FooterViewHolder(this, inflate);
        }
        View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.generic_list_item_with_checkbox, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, "LayoutInflater.from(pare…_checkbox, parent, false)");
        return new FoodViewHolder(this, inflate2);
    }

    public int getItemCount() {
        this.itemCount = this.items.size() + this.multiAddedItems.size();
        return this.itemCount + (getShouldShowFooter() ^ true ? 1 : 0);
    }

    public int getItemViewType(int i) {
        return i < this.itemCount ? 100 : 101;
    }

    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        if (viewHolder instanceof FoodViewHolder) {
            if (i < this.multiAddedItems.size()) {
                ((FoodViewHolder) viewHolder).bindData((DiaryEntryCellModel) this.multiAddedItems.get(i));
            } else {
                ((FoodViewHolder) viewHolder).bindData((DiaryEntryCellModel) this.items.get(i - this.multiAddedItems.size()));
            }
        } else if (viewHolder instanceof FooterViewHolder) {
            ((FooterViewHolder) viewHolder).setQueryString(this.query);
        }
    }

    public final void clearItems() {
        this.items = CollectionsKt.emptyList();
        setMultiAddedItems(CollectionsKt.emptyList());
        notifyDataSetChanged();
    }

    static {
        RequestOptions dontAnimate = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_meal_photos_placeholder).placeholder((int) R.drawable.ic_meal_photos_placeholder).centerCrop().dontAnimate();
        Intrinsics.checkExpressionValueIsNotNull(dontAnimate, "RequestOptions()\n       …           .dontAnimate()");
        IMAGE_REQUEST_OPTIONS = dontAnimate;
    }
}
