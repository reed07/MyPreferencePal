package com.myfitnesspal.feature.recipes.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.recipes.model.ItemMultiSelectContext;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class IngredientsContainer extends LinearLayout {
    /* access modifiers changed from: private */
    public IngredientActionListener ingredientActionListener;
    private final OnCheckedChangeListener onIngredientCheckedChangeListener;
    private final OnClickListener onIngredientItemClickListener;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public interface IngredientActionListener {
        void onItemCheckBoxToggled(MfpIngredient mfpIngredient);

        void onItemClick(MfpIngredient mfpIngredient, View view);
    }

    public IngredientsContainer(Context context) {
        this(context, null);
    }

    public IngredientsContainer(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IngredientsContainer(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onIngredientCheckedChangeListener = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (IngredientsContainer.this.ingredientActionListener != null) {
                    IngredientsContainer.this.ingredientActionListener.onItemCheckBoxToggled((MfpIngredient) compoundButton.getTag());
                }
            }
        };
        this.onIngredientItemClickListener = new OnClickListener() {
            public void onClick(View view) {
                IngredientsContainer.this.ingredientActionListener.onItemClick((MfpIngredient) view.getTag(), view);
            }
        };
        init();
    }

    public void showIngredients(List<MfpIngredient> list) {
        showIngredients(list, null);
    }

    public void showIngredients(List<MfpIngredient> list, ItemMultiSelectContext<MfpIngredient> itemMultiSelectContext) {
        removeAllViews();
        LayoutInflater from = LayoutInflater.from(getContext());
        for (MfpIngredient renderIngredientView : list) {
            addView(renderIngredientView(from, renderIngredientView, itemMultiSelectContext));
        }
    }

    public void addIngredientAt(MfpIngredient mfpIngredient, ItemMultiSelectContext<MfpIngredient> itemMultiSelectContext, int i) {
        addView(renderIngredientView(LayoutInflater.from(getContext()), mfpIngredient, itemMultiSelectContext), i);
    }

    public void removeIngredientAt(int i) {
        removeViewAt(i);
    }

    public void toggleCheckBoxOfItem(View view) {
        ((CompoundButton) ViewUtils.findById(view, R.id.multiSelectCheckBox)).toggle();
    }

    public void uncheckAllItems() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            CompoundButton compoundButton = (CompoundButton) ViewUtils.findById(getChildAt(i), R.id.multiSelectCheckBox);
            compoundButton.setOnCheckedChangeListener(null);
            compoundButton.setChecked(false);
            compoundButton.setOnCheckedChangeListener(this.onIngredientCheckedChangeListener);
        }
    }

    public void setIngredientActionListener(IngredientActionListener ingredientActionListener2) {
        this.ingredientActionListener = ingredientActionListener2;
    }

    private void init() {
        MyFitnessPalApp.getInstance().component().inject(this);
        setOrientation(1);
    }

    private View renderIngredientView(LayoutInflater layoutInflater, MfpIngredient mfpIngredient, ItemMultiSelectContext<MfpIngredient> itemMultiSelectContext) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_item_with_checkbox, this, false);
        TextView textView = (TextView) ViewUtils.findById(inflate, R.id.text_secondary);
        TextView textView2 = (TextView) ViewUtils.findById(inflate, R.id.txtCalories);
        CompoundButton compoundButton = (CompoundButton) ViewUtils.findById(inflate, R.id.multiSelectCheckBox);
        ((TextView) ViewUtils.findById(inflate, R.id.text_primary)).setText(mfpIngredient.getFood().getDescription());
        textView.setText(((UserEnergyService) this.userEnergyService.get()).getIngredientDescription(mfpIngredient));
        textView2.setText(LocalizedEnergy.getRoundedDisplayStringWithoutUnit(getContext(), LocalizedEnergy.fromCalories(mfpIngredient.getCaloriesValue()), ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit()));
        ViewUtils.increaseHitRectBy(getResources().getDimensionPixelSize(R.dimen.material_padding_16), compoundButton);
        ViewUtils.setVisible(textView2);
        if (itemMultiSelectContext == null) {
            ViewUtils.setGone(compoundButton);
        } else {
            ViewUtils.setVisible(compoundButton);
            compoundButton.setChecked(itemMultiSelectContext.hasItem(mfpIngredient));
            compoundButton.setTag(mfpIngredient);
            compoundButton.setOnCheckedChangeListener(this.onIngredientCheckedChangeListener);
        }
        if (this.ingredientActionListener != null) {
            inflate.setTag(mfpIngredient);
            inflate.setOnClickListener(this.onIngredientItemClickListener);
        }
        return inflate;
    }
}
