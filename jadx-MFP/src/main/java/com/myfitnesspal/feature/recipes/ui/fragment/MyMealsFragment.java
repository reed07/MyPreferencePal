package com.myfitnesspal.feature.recipes.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageServiceUtil;
import com.myfitnesspal.feature.meals.model.MealFoodLoggedInfo;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods.TabType;
import com.myfitnesspal.feature.recipes.ui.adapter.EditableAdapter;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.FoodImages;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.RowViewHolder;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class MyMealsFragment extends MyRecipesMealsFoodsBaseFragment<MealFood> {
    private static final String MEALS_ATTRIBUTE = "meals";
    private static final RequestOptions REQUEST_OPTIONS = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_meal_photos_placeholder).placeholder((int) R.drawable.ic_meal_photos_placeholder).dontAnimate();
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<ImageService> imageService;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<MealUtil> mealUtil;
    private ItemsModel model;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private static class LoadModelTask extends EventedTaskBase<ItemsModel, Exception> {
        private final DbConnectionManager dbConnectionManager;
        private final Session session;
        private final SortOrder sortOrder;

        static class CompletedEvent extends TaskEventBase<ItemsModel, Exception> {
        }

        static class ItemsModel {
            public List<MealFood> foods;
            public FoodImages images;

            ItemsModel() {
            }
        }

        LoadModelTask(Session session2, SortOrder sortOrder2, @Nonnull DbConnectionManager dbConnectionManager2) {
            super((TaskEventBase) new CompletedEvent());
            this.session = session2;
            this.sortOrder = sortOrder2;
            this.dbConnectionManager = dbConnectionManager2;
        }

        /* access modifiers changed from: protected */
        public ItemsModel exec(Context context) throws Exception {
            int countOwnedItemsOfType = this.dbConnectionManager.genericDbAdapter().countOwnedItemsOfType(3, this.session.getUser().getLocalId());
            ItemsModel itemsModel = new ItemsModel();
            itemsModel.foods = this.dbConnectionManager.foodDbAdapter().fetchOwnedFoodsOfType(3, this.sortOrder, countOwnedItemsOfType, 0);
            itemsModel.images = this.dbConnectionManager.foodDbAdapter().fetchImagesForFoodsOfType(3);
            return itemsModel;
        }
    }

    /* access modifiers changed from: protected */
    public boolean disableGenericItemClickHandling() {
        return true;
    }

    /* access modifiers changed from: protected */
    public int getAddItemButtonTextResId() {
        return R.string.create_new_meal;
    }

    public static MyMealsFragment newInstance() {
        return new MyMealsFragment();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public EditableAdapter<MealFood> recreateAdapter() {
        return new EditableAdapter<MealFood>(getListView(), this, getActivity()) {
            /* access modifiers changed from: protected */
            public void configureView(MealFood mealFood, RowViewHolder rowViewHolder, int i) {
                rowViewHolder.title.setText(Strings.toString(mealFood.getDescription()));
                rowViewHolder.summary.setText(NutritionUtils.getNutritionalMacrosDetails(MyMealsFragment.this.getActivity(), ((MealUtil) MyMealsFragment.this.mealUtil.get()).getNutritionalContents(mealFood)));
                rowViewHolder.calories.setText(((UserEnergyService) MyMealsFragment.this.userEnergyService.get()).getDisplayableEnergy((DiaryEntryCellModel) mealFood));
                ViewUtils.setVisible(rowViewHolder.summary);
                ViewUtils.setVisible(rowViewHolder.calories);
                MyMealsFragment.this.loadMealPhoto(mealFood, rowViewHolder);
            }

            /* access modifiers changed from: protected */
            public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
                MealFood mealFood = (MealFood) getItem(i);
                ((MealUtil) MyMealsFragment.this.mealUtil.get()).loadMealFoodLazy(mealFood);
                Intent newMealItemEditorIntent = FoodEditorActivity.newMealItemEditorIntent(MyMealsFragment.this.getActivity(), null, null, mealFood, MealAnalyticsHelper.VALUE_MEAL_RECIPES_FOODS);
                MealFoodLoggedInfo mealFoodLoggedInfo = new MealFoodLoggedInfo("", 0, mealFood.getUid(), "", "meals");
                MyMealsFragment.this.getNavigationHelper().withExtra(MealEditorMixin.EXTRA_MEAL_FOOD_LOGGED_INFO, (Parcelable) mealFoodLoggedInfo).withIntent(newMealItemEditorIntent).startActivity(RequestCodes.FOOD_EDITOR);
            }
        };
    }

    /* access modifiers changed from: protected */
    public LocalSettingsService getLocalSettings() {
        return (LocalSettingsService) this.localSettingsService.get();
    }

    /* access modifiers changed from: protected */
    public boolean addToFilteredList(MealFood mealFood, String str) {
        return mealFood.getDescription().toLowerCase().contains(str);
    }

    public Type getEmptyStateViewType() {
        return Type.Meal;
    }

    public OnClickListener getEmptyStatePrimaryButtonListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                MyMealsFragment.this.navigateToCreateMealFood();
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onActionAddClicked() {
        super.onActionAddClicked();
        navigateToCreateMealFood();
    }

    /* access modifiers changed from: protected */
    public void onActionDeleteClicked(List<MealFood> list) {
        super.onActionDeleteClicked(list);
        for (MealFood deleteFood : list) {
            ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().deleteFood(deleteFood, true, true);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("type", "meal");
        hashMap.put(Attributes.NUM_DELETED, Strings.toString(Integer.valueOf(list.size())));
        getAnalyticsService().reportEvent(Events.FOOD_DELETED, (Map<String, String>) hashMap);
        fetchData();
    }

    /* access modifiers changed from: protected */
    public void fetchData() {
        setLoading(true);
        new LoadModelTask(getSession(), getSortOrder().queryConstant, (DbConnectionManager) this.dbConnectionManager.get()).setDedupeMode(DedupeMode.CancelExisting).setCacheMode(CacheMode.None).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public List<MealFood> getItems() {
        ItemsModel itemsModel = this.model;
        if (itemsModel != null) {
            return itemsModel.foods;
        }
        return new ArrayList();
    }

    @Subscribe
    public void onLoadModelTaskCompleted(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            this.model = (ItemsModel) completedEvent.getResult();
            refresh();
        }
        setLoading(false);
    }

    /* access modifiers changed from: private */
    public void loadMealPhoto(MealFood mealFood, RowViewHolder rowViewHolder) {
        rowViewHolder.imageContainer.setVisibility(0);
        MfpImage image = this.model.images.getImage((Food) mealFood);
        if (image != null) {
            String imageThumbnailUri = ImageServiceUtil.getImageThumbnailUri(getContext(), (ImageService) this.imageService.get(), image);
            if (Strings.notEmpty(imageThumbnailUri)) {
                rowViewHolder.imageLoadListener.setLoading(rowViewHolder.progress, rowViewHolder.image);
                Glide.with(getContext()).load(imageThumbnailUri).apply(REQUEST_OPTIONS).listener(rowViewHolder.imageLoadListener).into(rowViewHolder.image);
                return;
            }
        }
        rowViewHolder.image.setImageResource(R.drawable.ic_meal_photos_placeholder);
        rowViewHolder.imageLoadListener.setLoaded(rowViewHolder.progress, rowViewHolder.image);
    }

    /* access modifiers changed from: private */
    public void navigateToCreateMealFood() {
        getNavigationHelper().withIntent(FoodEditorActivity.newMealItemEditorIntent(getActivity(), RecipesAndFoods.newStartIntent(getActivity(), TabType.Meals), null, null, MealAnalyticsHelper.VALUE_MEAL_RECIPES_FOODS)).startActivity(RequestCodes.FOOD_EDITOR);
    }
}
