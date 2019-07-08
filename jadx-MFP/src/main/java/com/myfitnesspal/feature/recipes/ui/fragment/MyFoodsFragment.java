package com.myfitnesspal.feature.recipes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFood;
import com.myfitnesspal.feature.recipes.ui.adapter.EditableAdapter;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.settings.ui.activity.EditFood;
import com.myfitnesspal.feature.settings.ui.activity.ViewFoodActivity;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.EditListAdapter;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.RowViewHolder;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class MyFoodsFragment extends MyRecipesMealsFoodsBaseFragment<Food> {
    private static final int MAX_LIMIT = 1000;
    @Inject
    DbConnectionManager dbConnectionManager;
    private List<Food> foods = new ArrayList();
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private static class LoadTask extends EventedTaskBase<List<Food>, Exception> {
        private final DbConnectionManager dbConnectionManager;
        private final SortOrder sortOrder;

        static class CompletedEvent extends TaskEventBase<List<Food>, Exception> {
        }

        LoadTask(@Nonnull DbConnectionManager dbConnectionManager2, SortOrder sortOrder2) {
            super((TaskEventBase) new CompletedEvent());
            this.sortOrder = sortOrder2;
            this.dbConnectionManager = dbConnectionManager2;
        }

        /* access modifiers changed from: protected */
        public List<Food> exec(Context context) throws Exception {
            return this.dbConnectionManager.foodDbAdapter().fetchOwnedFoodsOfType(1, this.sortOrder, 1000, 0);
        }
    }

    /* access modifiers changed from: protected */
    public boolean disableGenericItemClickHandling() {
        return true;
    }

    /* access modifiers changed from: protected */
    public int getAddItemButtonTextResId() {
        return R.string.new_food;
    }

    /* access modifiers changed from: protected */
    public void onItemClicked(Food food) {
    }

    public static MyFoodsFragment newInstance() {
        return new MyFoodsFragment();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public EditListAdapter<Food> recreateAdapter() {
        return new EditableAdapter<Food>(getListView(), this, getActivity()) {
            /* access modifiers changed from: protected */
            public void configureView(Food food, RowViewHolder rowViewHolder, int i) {
                rowViewHolder.title.setText(Strings.toString(food.getDescription()));
                boolean notEmpty = Strings.notEmpty(food.getBrand());
                ViewUtils.setVisible(rowViewHolder.calories);
                rowViewHolder.calories.setText(((UserEnergyService) MyFoodsFragment.this.userEnergyService.get()).getDisplayableEnergy((DiaryEntryCellModel) food));
                ViewUtils.setVisible(notEmpty, rowViewHolder.summary);
                if (notEmpty) {
                    rowViewHolder.summary.setText(((UserEnergyService) MyFoodsFragment.this.userEnergyService.get()).getDescription(food, false));
                }
            }

            /* access modifiers changed from: protected */
            public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
                Food fetchFoodById = MyFoodsFragment.this.dbConnectionManager.foodDbAdapter().fetchFoodById(((Food) getItem(i)).getLocalId());
                if (fetchFoodById.isPublic()) {
                    MyFoodsFragment.this.getNavigationHelper().fromFragment(MyFoodsFragment.this).withIntent(ViewFoodActivity.newStartIntent(MyFoodsFragment.this.getActivity(), fetchFoodById)).startActivity(RequestCodes.VIEW_FOOD);
                } else {
                    MyFoodsFragment.this.getNavigationHelper().fromFragment(MyFoodsFragment.this).withIntent(EditFood.newStartIntent(MyFoodsFragment.this.getActivity(), fetchFoodById)).startActivity(RequestCodes.EDIT_FOOD);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public LocalSettingsService getLocalSettings() {
        return (LocalSettingsService) this.localSettingsService.get();
    }

    /* access modifiers changed from: protected */
    public void onActionAddClicked() {
        super.onActionAddClicked();
        navigateToCreateFood();
    }

    /* access modifiers changed from: protected */
    public boolean addToFilteredList(Food food, String str) {
        return food.getDescription().toLowerCase().contains(str);
    }

    public Type getEmptyStateViewType() {
        return Type.Food;
    }

    public OnClickListener getEmptyStatePrimaryButtonListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                MyFoodsFragment.this.navigateToCreateFood();
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onActionDeleteClicked(List<Food> list) {
        super.onActionDeleteClicked(list);
        for (Food food : list) {
            if (food.isPublic()) {
                postEvent(new AlertEvent(String.format("%s: %s", new Object[]{food.getDescription(), getString(R.string.publicly_shared_food)})));
            } else {
                this.dbConnectionManager.foodDbAdapter().deleteFood(food, true, true);
                this.foods.remove(food);
            }
        }
        fetchData();
        HashMap hashMap = new HashMap();
        hashMap.put("type", "food");
        hashMap.put(Attributes.NUM_DELETED, Strings.toString(Integer.valueOf(list.size())));
        getAnalyticsService().reportEvent(Events.FOOD_DELETED, (Map<String, String>) hashMap);
    }

    /* access modifiers changed from: protected */
    public List<Food> getItems() {
        return this.foods;
    }

    /* access modifiers changed from: protected */
    public void fetchData() {
        new LoadTask(this.dbConnectionManager, getSortOrder().queryConstant).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
    }

    @Subscribe
    public void onFoodsLoaded(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            this.foods = (List) completedEvent.getResult();
            refresh();
        }
    }

    /* access modifiers changed from: private */
    public void navigateToCreateFood() {
        getNavigationHelper().withIntent(AddFood.newStartIntent(getActivity())).withExtra(AddFood.EXTRA_CREATE_DIARY_ENTRY, false).fromFragment(this).startActivity(53);
    }
}
