package com.myfitnesspal.feature.recipes.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.model.IngredientMatchingAdapterItem;
import com.myfitnesspal.feature.recipes.model.IngredientsMatchingOverviewItem;
import com.myfitnesspal.feature.recipes.model.ItemMultiSelectContext;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;

public class IngredientMatchingAdapter extends Adapter<RecyclerViewHolder<IngredientMatchingAdapterItem>> {
    private final List<IngredientMatchingAdapterItem> adapterItems = new ArrayList();
    private final ItemMultiSelectContext<MfpIngredientItem> ingredientItemMultiSelectContext;
    private final boolean isImportingIngredients;
    private final IngredientItemClickListener itemClickListener;
    private final Lazy<UserEnergyService> userEnergyService;

    public interface IngredientItemClickListener {
        void onItemClick(MfpIngredientItem mfpIngredientItem, int i);
    }

    static class IngredientViewHolder extends RecyclerViewHolder<IngredientMatchingAdapterItem> {
        @BindView(2131361914)
        View arrowView;
        @BindView(2131362058)
        TextView caloriesTextView;
        @BindView(2131362132)
        CompoundButton checkBox;
        @BindView(2131362805)
        TextView infoTextView;
        private final ItemMultiSelectContext<MfpIngredientItem> ingredientItemMultiSelectContext;
        private final boolean isImportingIngredients;
        private final IngredientItemClickListener itemClickListener;
        @BindView(2131363002)
        ImageView matchIndicatorImageView;
        private final OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                IngredientViewHolder.this.onItemClick(compoundButton);
            }
        };
        private final OnClickListener onClickListener = new OnClickListener() {
            public void onClick(View view) {
                IngredientViewHolder.this.onItemClick(view);
            }
        };
        @BindView(2131363227)
        TextView originalTextView;
        @BindView(2131363833)
        TextView titleTextView;
        private final Lazy<UserEnergyService> userEnergyService;

        IngredientViewHolder(ViewGroup viewGroup, Lazy<UserEnergyService> lazy, IngredientItemClickListener ingredientItemClickListener, ItemMultiSelectContext<MfpIngredientItem> itemMultiSelectContext, boolean z) {
            super(R.layout.recipe_match_ingredient_item, viewGroup);
            this.userEnergyService = lazy;
            this.itemClickListener = ingredientItemClickListener;
            this.ingredientItemMultiSelectContext = itemMultiSelectContext;
            this.isImportingIngredients = z;
        }

        public void setData(IngredientMatchingAdapterItem ingredientMatchingAdapterItem, int i) {
            MfpIngredientItem mfpIngredientItem = (MfpIngredientItem) ingredientMatchingAdapterItem;
            boolean hasMatches = mfpIngredientItem.hasMatches();
            boolean isMultiSelectEnabled = this.ingredientItemMultiSelectContext.isMultiSelectEnabled();
            this.matchIndicatorImageView.setImageResource(hasMatches ? R.drawable.ic_check_green : R.drawable.ic_error);
            ViewUtils.setVisible(hasMatches && this.isImportingIngredients, this.originalTextView);
            ViewUtils.setVisible(hasMatches && !isMultiSelectEnabled, this.caloriesTextView);
            ViewUtils.setVisible(!hasMatches && !isMultiSelectEnabled, this.arrowView);
            ViewUtils.setVisible(isMultiSelectEnabled, this.checkBox);
            ViewUtils.increaseHitRectBy(getContext().getResources().getDimensionPixelSize(R.dimen.material_padding_16), this.checkBox);
            if (!hasMatches) {
                this.titleTextView.setText(this.context.getString(R.string.unmatched_ingredient_text, new Object[]{mfpIngredientItem.getIngredient().getText()}));
                this.infoTextView.setText(R.string.unmatched);
            } else {
                MfpIngredient primaryMatch = mfpIngredientItem.getPrimaryMatch();
                MfpServingSize servingSize = primaryMatch.getServingSize();
                double doubleValue = primaryMatch.getServings().doubleValue();
                this.titleTextView.setText(primaryMatch.getFood().getDescription());
                this.infoTextView.setText(String.format("%s %s", new Object[]{NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(doubleValue * servingSize.getValue().doubleValue()), servingSize.getUnit()}));
                this.originalTextView.setText(this.context.getString(mfpIngredientItem.wasManuallyAdded() ? R.string.added_manually : R.string.original_ingredient_text, new Object[]{getOriginalTextForMatchedIngredient(mfpIngredientItem)}));
                this.caloriesTextView.setText(LocalizedEnergy.getRoundedDisplayStringWithoutUnit(this.context, LocalizedEnergy.fromCalories(primaryMatch.getCaloriesValue()), ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit()));
            }
            this.checkBox.setTag(mfpIngredientItem);
            this.checkBox.setOnCheckedChangeListener(null);
            this.checkBox.setChecked(this.ingredientItemMultiSelectContext.hasItem(mfpIngredientItem));
            this.checkBox.setOnCheckedChangeListener(this.onCheckedChangeListener);
            this.itemView.setTag(mfpIngredientItem);
            this.itemView.setOnClickListener(this.onClickListener);
        }

        private String getOriginalTextForMatchedIngredient(MfpIngredientItem mfpIngredientItem) {
            String rawText = mfpIngredientItem.getIngredient().getRawText();
            if (Strings.isEmpty(rawText)) {
                rawText = mfpIngredientItem.getIngredient().getText();
            }
            return Strings.isEmpty(rawText) ? mfpIngredientItem.getPrimaryMatch().getFood().getDescription() : rawText;
        }

        /* access modifiers changed from: private */
        public void onItemClick(View view) {
            this.itemClickListener.onItemClick((MfpIngredientItem) view.getTag(), getLayoutPosition());
        }
    }

    public class IngredientViewHolder_ViewBinding implements Unbinder {
        private IngredientViewHolder target;

        @UiThread
        public IngredientViewHolder_ViewBinding(IngredientViewHolder ingredientViewHolder, View view) {
            this.target = ingredientViewHolder;
            ingredientViewHolder.matchIndicatorImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.match_indicator, "field 'matchIndicatorImageView'", ImageView.class);
            ingredientViewHolder.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'titleTextView'", TextView.class);
            ingredientViewHolder.infoTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.info, "field 'infoTextView'", TextView.class);
            ingredientViewHolder.originalTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.original_text, "field 'originalTextView'", TextView.class);
            ingredientViewHolder.caloriesTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.calories, "field 'caloriesTextView'", TextView.class);
            ingredientViewHolder.checkBox = (CompoundButton) Utils.findRequiredViewAsType(view, R.id.check_box, "field 'checkBox'", CompoundButton.class);
            ingredientViewHolder.arrowView = Utils.findRequiredView(view, R.id.arrow, "field 'arrowView'");
        }

        @CallSuper
        public void unbind() {
            IngredientViewHolder ingredientViewHolder = this.target;
            if (ingredientViewHolder != null) {
                this.target = null;
                ingredientViewHolder.matchIndicatorImageView = null;
                ingredientViewHolder.titleTextView = null;
                ingredientViewHolder.infoTextView = null;
                ingredientViewHolder.originalTextView = null;
                ingredientViewHolder.caloriesTextView = null;
                ingredientViewHolder.checkBox = null;
                ingredientViewHolder.arrowView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    static class OverviewViewHolder extends RecyclerViewHolder<IngredientMatchingAdapterItem> {
        @BindView(2131362071)
        TextView caloriesPerServingsTextView;
        @BindView(2131363188)
        TextView numServingsTextView;
        @BindView(2131363749)
        TextView subTextView;
        private final Lazy<UserEnergyService> userEnergyService;

        OverviewViewHolder(ViewGroup viewGroup, Lazy<UserEnergyService> lazy) {
            super(R.layout.review_ingredients_matching_overview_item, viewGroup);
            this.userEnergyService = lazy;
        }

        public void setData(IngredientMatchingAdapterItem ingredientMatchingAdapterItem, int i) {
            IngredientsMatchingOverviewItem ingredientsMatchingOverviewItem = (IngredientsMatchingOverviewItem) ingredientMatchingAdapterItem;
            this.subTextView.setText(ingredientsMatchingOverviewItem.areAllIngredientsMatched() ? R.string.review_all_ingredients : R.string.find_match_unmatched_ingredients);
            this.numServingsTextView.setText(this.context.getString(R.string.num_servings, new Object[]{NumberUtils.localeStringFromDouble(ingredientsMatchingOverviewItem.getServings())}));
            this.caloriesPerServingsTextView.setText(this.context.getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.calories_per_servings : R.string.kj_per_servings, new Object[]{LocalizedEnergy.getRoundedDisplayStringWithoutUnit(this.context, ingredientsMatchingOverviewItem.getLocalizedEnergyPerServing(), ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit())}));
        }
    }

    public class OverviewViewHolder_ViewBinding implements Unbinder {
        private OverviewViewHolder target;

        @UiThread
        public OverviewViewHolder_ViewBinding(OverviewViewHolder overviewViewHolder, View view) {
            this.target = overviewViewHolder;
            overviewViewHolder.subTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.subtext, "field 'subTextView'", TextView.class);
            overviewViewHolder.numServingsTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.num_servings, "field 'numServingsTextView'", TextView.class);
            overviewViewHolder.caloriesPerServingsTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_per_serving, "field 'caloriesPerServingsTextView'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            OverviewViewHolder overviewViewHolder = this.target;
            if (overviewViewHolder != null) {
                this.target = null;
                overviewViewHolder.subTextView = null;
                overviewViewHolder.numServingsTextView = null;
                overviewViewHolder.caloriesPerServingsTextView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public enum ViewType {
        Overview,
        Ingredient
    }

    public IngredientMatchingAdapter(Lazy<UserEnergyService> lazy, IngredientItemClickListener ingredientItemClickListener, ItemMultiSelectContext<MfpIngredientItem> itemMultiSelectContext, boolean z) {
        this.userEnergyService = lazy;
        this.itemClickListener = ingredientItemClickListener;
        this.ingredientItemMultiSelectContext = itemMultiSelectContext;
        this.isImportingIngredients = z;
    }

    public int getItemViewType(int i) {
        IngredientMatchingAdapterItem item = getItem(i);
        if (item instanceof IngredientsMatchingOverviewItem) {
            return ViewType.Overview.ordinal();
        }
        if (item instanceof MfpIngredientItem) {
            return ViewType.Ingredient.ordinal();
        }
        throw new IllegalStateException("Unhandled item in adapter");
    }

    public RecyclerViewHolder<IngredientMatchingAdapterItem> onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewType viewType = ViewType.values()[i];
        switch (viewType) {
            case Overview:
                return new OverviewViewHolder(viewGroup, this.userEnergyService);
            case Ingredient:
                IngredientViewHolder ingredientViewHolder = new IngredientViewHolder(viewGroup, this.userEnergyService, this.itemClickListener, this.ingredientItemMultiSelectContext, this.isImportingIngredients);
                return ingredientViewHolder;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unhandled view type: ");
                sb.append(viewType);
                throw new IllegalStateException(sb.toString());
        }
    }

    public void onBindViewHolder(RecyclerViewHolder<IngredientMatchingAdapterItem> recyclerViewHolder, int i) {
        recyclerViewHolder.setData(getItem(i), i);
    }

    public int getItemCount() {
        return this.adapterItems.size();
    }

    public void removeOverviewItem() {
        if (getItem(0) instanceof IngredientsMatchingOverviewItem) {
            this.adapterItems.remove(0);
            notifyItemRemoved(0);
        }
    }

    public void addOverviewItem(IngredientsMatchingOverviewItem ingredientsMatchingOverviewItem) {
        this.adapterItems.add(0, ingredientsMatchingOverviewItem);
        notifyItemInserted(0);
    }

    public void setItems(List<IngredientMatchingAdapterItem> list) {
        this.adapterItems.clear();
        this.adapterItems.addAll(list);
        notifyDataSetChanged();
    }

    private IngredientMatchingAdapterItem getItem(int i) {
        return (IngredientMatchingAdapterItem) this.adapterItems.get(i);
    }
}
