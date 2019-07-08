package com.myfitnesspal.feature.search.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.ui.view.StyledTextView;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.core.util.ViewUtils;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 E2\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00020\u0001:\u0005EFGHIB?\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0006\u0010<\u001a\u00020&J\b\u0010=\u001a\u00020#H\u0016J\u0010\u0010>\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0016J \u0010?\u001a\u00020&2\u000e\u0010@\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00022\u0006\u0010$\u001a\u00020#H\u0016J \u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00022\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020#H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000Ra\u0010\u001d\u001aI\u0012\u0015\u0012\u0013\u0018\u00010\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&0\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*RL\u0010+\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0004\u0012\u00020&0,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100RL\u00101\u001a4\u0012\u0015\u0012\u0013\u0018\u000102¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0004\u0012\u00020&0,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010.\"\u0004\b5\u00100RL\u00106\u001a4\u0012\u0015\u0012\u0013\u0018\u000107¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0004\u0012\u00020&0,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010.\"\u0004\b9\u00100R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/myfitnesspal/shared/ui/view/RecyclerViewHolder;", "", "activity", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "items", "", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "userEnergyService", "Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;", "multiAddFoodHelper", "Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "foodMapper", "Lcom/myfitnesspal/shared/model/mapper/impl/FoodMapper;", "shouldShowDividers", "", "(Lcom/myfitnesspal/shared/ui/activity/MfpActivity;Ljava/util/List;Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;Lcom/myfitnesspal/shared/model/mapper/impl/FoodMapper;Z)V", "value", "isFetchingNextPage", "()Z", "setFetchingNextPage", "(Z)V", "isMultiAddEnabled", "setMultiAddEnabled", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "onCheckedChange", "Lkotlin/Function3;", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "Lkotlin/ParameterName;", "name", "food", "", "position", "isChecked", "", "getOnCheckedChange", "()Lkotlin/jvm/functions/Function3;", "setOnCheckedChange", "(Lkotlin/jvm/functions/Function3;)V", "onFoodClick", "Lkotlin/Function2;", "getOnFoodClick", "()Lkotlin/jvm/functions/Function2;", "setOnFoodClick", "(Lkotlin/jvm/functions/Function2;)V", "onSponsoredFoodClick", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "venue", "getOnSponsoredFoodClick", "setOnSponsoredFoodClick", "onVenueClick", "Lcom/myfitnesspal/feature/restaurantlogging/model/Venue;", "getOnVenueClick", "setOnVenueClick", "user", "Lcom/myfitnesspal/shared/model/User;", "clear", "getItemCount", "getItemViewType", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "FoodViewHolder", "LoadingFooterViewHolder", "SponsoredFoodViewHolder", "VenueViewHolder", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchAdapter.kt */
public final class OnlineFoodSearchAdapter extends Adapter<RecyclerViewHolder<? extends Object>> {
    public static final int CHECKBOX_HIT_RECT_PADDING_PX = 30;
    public static final Companion Companion = new Companion(null);
    public static final int VIEW_TYPE_FOOD = 1;
    public static final int VIEW_TYPE_LOADING_FOOTER = 4;
    public static final int VIEW_TYPE_SPONSORED_FOOD = 3;
    public static final int VIEW_TYPE_VENUE = 2;
    /* access modifiers changed from: private */
    public final FoodMapper foodMapper;
    private boolean isFetchingNextPage;
    private boolean isMultiAddEnabled;
    @NotNull
    private List<MfpFoodSearchResult> items;
    /* access modifiers changed from: private */
    public final MultiAddFoodHelper multiAddFoodHelper;
    @NotNull
    private Function3<? super MfpFood, ? super Integer, ? super Boolean, Unit> onCheckedChange;
    @NotNull
    private Function2<? super MfpFood, ? super Integer, Unit> onFoodClick;
    @NotNull
    private Function2<? super SponsoredFood, ? super Integer, Unit> onSponsoredFoodClick;
    @NotNull
    private Function2<? super Venue, ? super Integer, Unit> onVenueClick;
    /* access modifiers changed from: private */
    public final boolean shouldShowDividers;
    /* access modifiers changed from: private */
    public final User user;
    /* access modifiers changed from: private */
    public final UserEnergyService userEnergyService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter$Companion;", "", "()V", "CHECKBOX_HIT_RECT_PADDING_PX", "", "VIEW_TYPE_FOOD", "VIEW_TYPE_LOADING_FOOTER", "VIEW_TYPE_SPONSORED_FOOD", "VIEW_TYPE_VENUE", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnlineFoodSearchAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter$FoodViewHolder;", "Lcom/myfitnesspal/shared/ui/view/RecyclerViewHolder;", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "parentView", "Landroid/view/ViewGroup;", "(Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter;Landroid/view/ViewGroup;)V", "hasIncreasedHitRect", "", "setData", "", "foodResult", "position", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnlineFoodSearchAdapter.kt */
    private final class FoodViewHolder extends RecyclerViewHolder<MfpFoodSearchResult> {
        private boolean hasIncreasedHitRect;

        public FoodViewHolder(ViewGroup viewGroup) {
            super(R.layout.generic_list_item_with_checkbox, viewGroup);
        }

        public void setData(@Nullable MfpFoodSearchResult mfpFoodSearchResult, int i) {
            Drawable drawable;
            if (mfpFoodSearchResult != null) {
                MfpFood mfpFood = (MfpFood) mfpFoodSearchResult.getSearchResultItem();
                if (mfpFood != null) {
                    View view = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
                    TextView textView = (TextView) view.findViewById(R.id.text_primary);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "itemView.text_primary");
                    textView.setText(mfpFood.getDescription());
                    View view2 = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
                    TextView textView2 = (TextView) view2.findViewById(R.id.text_secondary);
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "itemView.text_secondary");
                    textView2.setText(OnlineFoodSearchAdapter.this.userEnergyService.getFoodDescription(mfpFood, false));
                    View view3 = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view3, "itemView");
                    StyledTextView styledTextView = (StyledTextView) view3.findViewById(R.id.txtCalories);
                    Intrinsics.checkExpressionValueIsNotNull(styledTextView, "itemView.txtCalories");
                    styledTextView.setVisibility(0);
                    View view4 = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view4, "itemView");
                    StyledTextView styledTextView2 = (StyledTextView) view4.findViewById(R.id.txtCalories);
                    Intrinsics.checkExpressionValueIsNotNull(styledTextView2, "itemView.txtCalories");
                    styledTextView2.setText(OnlineFoodSearchAdapter.this.userEnergyService.getDisplayableEnergy(mfpFood));
                    View view5 = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view5, "itemView");
                    View findViewById = view5.findViewById(R.id.generic_list_item_divider);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.generic_list_item_divider");
                    findViewById.setVisibility(OnlineFoodSearchAdapter.this.shouldShowDividers ? 0 : 8);
                    View view6 = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view6, "itemView");
                    TextView textView3 = (TextView) view6.findViewById(R.id.text_primary);
                    if (mfpFood.getVerified()) {
                        Context context = this.context;
                        Intrinsics.checkExpressionValueIsNotNull(context, "context");
                        drawable = context.getResources().getDrawable(R.drawable.ic_verified_foods_small, null);
                    } else {
                        drawable = null;
                    }
                    textView3.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                    if (OnlineFoodSearchAdapter.this.isMultiAddEnabled()) {
                        View view7 = this.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view7, "itemView");
                        ViewUtils.setVisible((CheckBox) view7.findViewById(R.id.multiSelectCheckBox));
                        if (!this.hasIncreasedHitRect) {
                            View view8 = this.itemView;
                            Intrinsics.checkExpressionValueIsNotNull(view8, "itemView");
                            ViewUtils.increaseHitRectBy(30, (CheckBox) view8.findViewById(R.id.multiSelectCheckBox));
                            this.hasIncreasedHitRect = true;
                        }
                        boolean containsItem = OnlineFoodSearchAdapter.this.multiAddFoodHelper.containsItem(OnlineFoodSearchAdapter.this.foodMapper.mapFromMfpFood(mfpFood, OnlineFoodSearchAdapter.this.user));
                        View view9 = this.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view9, "itemView");
                        ((CheckBox) view9.findViewById(R.id.multiSelectCheckBox)).setOnCheckedChangeListener(null);
                        View view10 = this.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view10, "itemView");
                        CheckBox checkBox = (CheckBox) view10.findViewById(R.id.multiSelectCheckBox);
                        Intrinsics.checkExpressionValueIsNotNull(checkBox, "itemView.multiSelectCheckBox");
                        checkBox.setChecked(containsItem);
                        View view11 = this.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view11, "itemView");
                        ((CheckBox) view11.findViewById(R.id.multiSelectCheckBox)).setOnCheckedChangeListener(new OnlineFoodSearchAdapter$FoodViewHolder$setData$1(this, mfpFood));
                    } else {
                        View view12 = this.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view12, "itemView");
                        ViewUtils.setGone((CheckBox) view12.findViewById(R.id.multiSelectCheckBox));
                    }
                    getParent().setOnClickListener(new OnlineFoodSearchAdapter$FoodViewHolder$setData$2(this, mfpFood));
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u001f\u0010\u0006\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter$LoadingFooterViewHolder;", "Lcom/myfitnesspal/shared/ui/view/RecyclerViewHolder;", "", "parentView", "Landroid/view/ViewGroup;", "(Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter;Landroid/view/ViewGroup;)V", "setData", "item", "position", "", "(Lkotlin/Unit;I)V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnlineFoodSearchAdapter.kt */
    private final class LoadingFooterViewHolder extends RecyclerViewHolder<Unit> {
        public void setData(@Nullable Unit unit, int i) {
        }

        public LoadingFooterViewHolder(ViewGroup viewGroup) {
            super(R.layout.search_footer_row, viewGroup);
            View view = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
            ViewUtils.setVisible((ProgressBar) view.findViewById(R.id.footerRowProgressBar));
            View view2 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
            ((StyledTextView) view2.findViewById(R.id.footerRowProgressText)).setText(R.string.loading);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter$SponsoredFoodViewHolder;", "Lcom/myfitnesspal/shared/ui/view/RecyclerViewHolder;", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "parentView", "Landroid/view/ViewGroup;", "(Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter;Landroid/view/ViewGroup;)V", "setData", "", "foodResult", "position", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnlineFoodSearchAdapter.kt */
    private final class SponsoredFoodViewHolder extends RecyclerViewHolder<MfpFoodSearchResult> {
        public SponsoredFoodViewHolder(ViewGroup viewGroup) {
            super(R.layout.item_sponsored_food, viewGroup);
        }

        public void setData(@Nullable MfpFoodSearchResult mfpFoodSearchResult, int i) {
            if (mfpFoodSearchResult != null) {
                SponsoredFood sponsoredFood = (SponsoredFood) mfpFoodSearchResult.getSearchResultItem();
                if (sponsoredFood != null) {
                    SponsoredFoodSearchAd sponsoredFoodAd = sponsoredFood.getSponsoredFoodAd();
                    if (sponsoredFoodAd != null) {
                        View view = this.itemView;
                        TextView textView = (TextView) view.findViewById(R.id.itemTitleView);
                        Intrinsics.checkExpressionValueIsNotNull(textView, "itemTitleView");
                        textView.setText(sponsoredFoodAd.getProductName());
                        TextView textView2 = (TextView) view.findViewById(R.id.itemDescriptionView);
                        Intrinsics.checkExpressionValueIsNotNull(textView2, "itemDescriptionView");
                        textView2.setText(sponsoredFoodAd.getProductTagline());
                        TextView textView3 = (TextView) view.findViewById(R.id.itemPortionView);
                        Intrinsics.checkExpressionValueIsNotNull(textView3, "itemPortionView");
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        StringBuilder sb = new StringBuilder();
                        sb.append(sponsoredFoodAd.getSponsoredCallout());
                        sb.append(" • ");
                        sb.append(sponsoredFoodAd.getFoodDescription());
                        sb.append(", ");
                        sb.append(sponsoredFoodAd.getMeasurement());
                        Object[] objArr = new Object[0];
                        String format = String.format(sb.toString(), Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        textView3.setText(format);
                        TextView textView4 = (TextView) view.findViewById(R.id.caloriesView);
                        Intrinsics.checkExpressionValueIsNotNull(textView4, "caloriesView");
                        textView4.setText(OnlineFoodSearchAdapter.this.userEnergyService.getDisplayableEnergy(sponsoredFoodAd.getCalories()));
                        View findViewById = view.findViewById(R.id.sponsoredFoodDivider);
                        Intrinsics.checkExpressionValueIsNotNull(findViewById, "sponsoredFoodDivider");
                        findViewById.setVisibility(OnlineFoodSearchAdapter.this.shouldShowDividers ? 0 : 8);
                        ((MfpImageView) view.findViewById(R.id.imageView)).setPlaceholderImageId(17170445);
                        ((MfpImageView) view.findViewById(R.id.imageView)).setUrl(sponsoredFoodAd.getProductThumbnailImagePath());
                        if (OnlineFoodSearchAdapter.this.isMultiAddEnabled()) {
                            ViewUtils.setVisible((CheckBox) view.findViewById(R.id.checkBox));
                            ((CheckBox) view.findViewById(R.id.checkBox)).setOnCheckedChangeListener(new OnlineFoodSearchAdapter$SponsoredFoodViewHolder$setData$$inlined$let$lambda$1(sponsoredFoodAd, this, sponsoredFood));
                        } else {
                            ViewUtils.setGone((CheckBox) view.findViewById(R.id.checkBox));
                        }
                    }
                    getParent().setOnClickListener(new OnlineFoodSearchAdapter$SponsoredFoodViewHolder$setData$2(this, sponsoredFood, i));
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter$VenueViewHolder;", "Lcom/myfitnesspal/shared/ui/view/RecyclerViewHolder;", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "parentView", "Landroid/view/ViewGroup;", "(Lcom/myfitnesspal/feature/search/ui/adapter/OnlineFoodSearchAdapter;Landroid/view/ViewGroup;)V", "setData", "", "venueResult", "position", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnlineFoodSearchAdapter.kt */
    private final class VenueViewHolder extends RecyclerViewHolder<MfpFoodSearchResult> {
        public VenueViewHolder(ViewGroup viewGroup) {
            super(R.layout.venue_search_result_item, viewGroup);
        }

        public void setData(@Nullable MfpFoodSearchResult mfpFoodSearchResult, int i) {
            if (mfpFoodSearchResult != null) {
                Venue venue = (Venue) mfpFoodSearchResult.getSearchResultItem();
                if (venue != null) {
                    View view = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
                    TextView textView = (TextView) view.findViewById(R.id.text_primary);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "itemView.text_primary");
                    int i2 = 0;
                    textView.setText(this.context.getString(R.string.venue_name_dash, new Object[]{venue.getName()}));
                    View view2 = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
                    TextView textView2 = (TextView) view2.findViewById(R.id.text_secondary);
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "itemView.text_secondary");
                    textView2.setText(this.context.getString(R.string.meal_items_new, new Object[]{Integer.valueOf(venue.getMenuItemCount())}));
                    View view3 = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view3, "itemView");
                    View findViewById = view3.findViewById(R.id.venueItemDivider);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.venueItemDivider");
                    if (!OnlineFoodSearchAdapter.this.shouldShowDividers) {
                        i2 = 8;
                    }
                    findViewById.setVisibility(i2);
                    getParent().setOnClickListener(new OnlineFoodSearchAdapter$VenueViewHolder$setData$$inlined$let$lambda$1(venue, this, i));
                }
            }
        }
    }

    @NotNull
    public final List<MfpFoodSearchResult> getItems() {
        return this.items;
    }

    public final void setItems(@NotNull List<MfpFoodSearchResult> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.items = list;
    }

    public /* synthetic */ OnlineFoodSearchAdapter(MfpActivity mfpActivity, List list, UserEnergyService userEnergyService2, MultiAddFoodHelper multiAddFoodHelper2, FoodMapper foodMapper2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mfpActivity, list, userEnergyService2, multiAddFoodHelper2, foodMapper2, (i & 32) != 0 ? true : z);
    }

    public OnlineFoodSearchAdapter(@Nullable MfpActivity mfpActivity, @NotNull List<MfpFoodSearchResult> list, @NotNull UserEnergyService userEnergyService2, @NotNull MultiAddFoodHelper multiAddFoodHelper2, @NotNull FoodMapper foodMapper2, boolean z) {
        User user2;
        Intrinsics.checkParameterIsNotNull(list, "items");
        Intrinsics.checkParameterIsNotNull(userEnergyService2, "userEnergyService");
        Intrinsics.checkParameterIsNotNull(multiAddFoodHelper2, "multiAddFoodHelper");
        Intrinsics.checkParameterIsNotNull(foodMapper2, "foodMapper");
        this.items = list;
        this.userEnergyService = userEnergyService2;
        this.multiAddFoodHelper = multiAddFoodHelper2;
        this.foodMapper = foodMapper2;
        this.shouldShowDividers = z;
        this.onCheckedChange = OnlineFoodSearchAdapter$onCheckedChange$1.INSTANCE;
        this.onFoodClick = OnlineFoodSearchAdapter$onFoodClick$1.INSTANCE;
        this.onVenueClick = OnlineFoodSearchAdapter$onVenueClick$1.INSTANCE;
        this.onSponsoredFoodClick = OnlineFoodSearchAdapter$onSponsoredFoodClick$1.INSTANCE;
        if (mfpActivity != null) {
            Session session = mfpActivity.getSession();
            if (session != null) {
                user2 = session.getUser();
                this.user = user2;
            }
        }
        user2 = null;
        this.user = user2;
    }

    @NotNull
    public final Function3<MfpFood, Integer, Boolean, Unit> getOnCheckedChange() {
        return this.onCheckedChange;
    }

    public final void setOnCheckedChange(@NotNull Function3<? super MfpFood, ? super Integer, ? super Boolean, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "<set-?>");
        this.onCheckedChange = function3;
    }

    @NotNull
    public final Function2<MfpFood, Integer, Unit> getOnFoodClick() {
        return this.onFoodClick;
    }

    public final void setOnFoodClick(@NotNull Function2<? super MfpFood, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.onFoodClick = function2;
    }

    @NotNull
    public final Function2<Venue, Integer, Unit> getOnVenueClick() {
        return this.onVenueClick;
    }

    public final void setOnVenueClick(@NotNull Function2<? super Venue, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.onVenueClick = function2;
    }

    @NotNull
    public final Function2<SponsoredFood, Integer, Unit> getOnSponsoredFoodClick() {
        return this.onSponsoredFoodClick;
    }

    public final void setOnSponsoredFoodClick(@NotNull Function2<? super SponsoredFood, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.onSponsoredFoodClick = function2;
    }

    public final boolean isMultiAddEnabled() {
        return this.isMultiAddEnabled;
    }

    public final void setMultiAddEnabled(boolean z) {
        this.isMultiAddEnabled = z;
    }

    public final boolean isFetchingNextPage() {
        return this.isFetchingNextPage;
    }

    public final void setFetchingNextPage(boolean z) {
        this.isFetchingNextPage = z;
        notifyDataSetChanged();
    }

    @NotNull
    public RecyclerViewHolder<? extends Object> onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        switch (i) {
            case 2:
                return new VenueViewHolder<>(viewGroup);
            case 3:
                return new SponsoredFoodViewHolder<>(viewGroup);
            case 4:
                return new LoadingFooterViewHolder<>(viewGroup);
            default:
                return new FoodViewHolder<>(viewGroup);
        }
    }

    public int getItemCount() {
        if (this.isFetchingNextPage) {
            return this.items.size() + 1;
        }
        return this.items.size();
    }

    public int getItemViewType(int i) {
        int i2 = 1;
        if (this.isFetchingNextPage && i == getItemCount() - 1) {
            return 4;
        }
        MfpFoodSearchResult mfpFoodSearchResult = (MfpFoodSearchResult) this.items.get(i);
        if (mfpFoodSearchResult.getSearchResultItem() instanceof SponsoredFood) {
            i2 = 3;
        } else if (mfpFoodSearchResult.getSearchResultItem() instanceof Venue) {
            i2 = 2;
        }
        return i2;
    }

    public void onBindViewHolder(@NotNull RecyclerViewHolder<? extends Object> recyclerViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(recyclerViewHolder, "holder");
        if (recyclerViewHolder instanceof FoodViewHolder) {
            ((FoodViewHolder) recyclerViewHolder).setData((MfpFoodSearchResult) this.items.get(i), i);
        } else if (recyclerViewHolder instanceof SponsoredFoodViewHolder) {
            ((SponsoredFoodViewHolder) recyclerViewHolder).setData((MfpFoodSearchResult) this.items.get(i), i);
        } else if (recyclerViewHolder instanceof VenueViewHolder) {
            ((VenueViewHolder) recyclerViewHolder).setData((MfpFoodSearchResult) this.items.get(i), i);
        }
    }

    public final void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }
}
