package com.myfitnesspal.feature.recipes.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.ui.fragment.RecipeFoodMealSortDialogFragment.OnSortOrderSelectedListener;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.ViewUtils;

public abstract class MyRecipesMealsFoodsBaseFragment<T> extends MfpEditableFragmentBase<T> {
    private static final String EXTRA_HIDE_MEAL_COLLECTIONS_CARD = "hide_meal_collections_card";
    private static final long FOOTER_SHOW_DELAY_MS = 500;
    protected boolean isMealCollectionsCardHidden = false;
    private OnSortOrderSelectedListener onSortOrderSelectedListener = new OnSortOrderSelectedListener() {
        public void onSortOrderSelected(MyItemsSortOrder myItemsSortOrder) {
            MyRecipesMealsFoodsBaseFragment.this.getLocalSettings().setMealRecipesAndFoodsSortOrder(myItemsSortOrder.toString());
            MyRecipesMealsFoodsBaseFragment.this.fetchData();
        }
    };
    private boolean refetchOnResume;

    /* access modifiers changed from: protected */
    public abstract int getAddItemButtonTextResId();

    /* access modifiers changed from: protected */
    public abstract LocalSettingsService getLocalSettings();

    /* access modifiers changed from: protected */
    public int getOptionsMenuShowActionFor(int i) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean wantsSortMenuItem() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.refetchOnResume = true;
        this.isMealCollectionsCardHidden = getActivity().getIntent().getBooleanExtra("hide_meal_collections_card", false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setupAddItemButton();
        if (showFooterButton()) {
            MaterialUtils.setFixedFooterScrollingBehavior(getActivity(), true);
        }
        if (this.isMealCollectionsCardHidden && getToolbar() != null) {
            getToolbar().setNavigationIcon((int) R.drawable.ic_close_white_24dp);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.refetchOnResume = true;
        super.onActivityResult(i, i2, intent);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (str.equals(tag(RecipeFoodMealSortDialogFragment.class))) {
            ((RecipeFoodMealSortDialogFragment) dialogFragment).setListener(this.onSortOrderSelectedListener);
        }
        return super.onRebindDialogFragment(dialogFragment, str);
    }

    public void onResume() {
        super.onResume();
        if (this.refetchOnResume) {
            fetchData();
            this.refetchOnResume = false;
        }
    }

    public void onSelectedInPagerAdapter() {
        if (hasResumed()) {
            fetchData();
        } else {
            invalidateData();
        }
    }

    public void invalidateData() {
        this.refetchOnResume = true;
    }

    /* access modifiers changed from: protected */
    public boolean wantsAddMenuItem() {
        return !showFooterButton();
    }

    /* access modifiers changed from: protected */
    public boolean showFooterButton() {
        return !this.isMealCollectionsCardHidden;
    }

    /* access modifiers changed from: protected */
    public void actionModeDestroyed() {
        super.actionModeDestroyed();
        if (showFooterButton()) {
            getListView().postDelayed(new Runnable() {
                public void run() {
                    MaterialUtils.applyPaddingToFixedFooter(MyRecipesMealsFoodsBaseFragment.this.getActivity());
                }
            }, FOOTER_SHOW_DELAY_MS);
        }
    }

    /* access modifiers changed from: protected */
    public void onActionSortClicked() {
        String tag = tag(RecipeFoodMealSortDialogFragment.class);
        if (getFragmentManager().findFragmentByTag(tag) == null) {
            RecipeFoodMealSortDialogFragment newInstance = RecipeFoodMealSortDialogFragment.newInstance(getSortOrder());
            newInstance.setListener(this.onSortOrderSelectedListener);
            newInstance.show(getFragmentManager(), tag);
        }
    }

    /* access modifiers changed from: protected */
    public MyItemsSortOrder getSortOrder() {
        return MyItemsSortOrder.fromString(getLocalSettings().getMealRecipesAndFoodsSortOrder());
    }

    private void setupAddItemButton() {
        ViewUtils.setVisible(showFooterButton(), this.addItemFooterButton);
        this.addItemFooterButton.setText(getAddItemButtonTextResId());
        this.addItemFooterButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyRecipesMealsFoodsBaseFragment.this.onActionAddClicked();
            }
        });
    }
}
