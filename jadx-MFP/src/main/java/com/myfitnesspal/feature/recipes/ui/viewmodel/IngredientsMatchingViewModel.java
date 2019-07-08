package com.myfitnesspal.feature.recipes.ui.viewmodel;

import com.myfitnesspal.feature.recipes.model.IngredientsMatchingOverviewItem;
import com.myfitnesspal.feature.recipes.model.ItemMultiSelectContext;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.task.MatchIngredientsTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientsMatchingViewModel extends RunnerViewModel<String> {
    private int deletedIngredientsCount;
    private MfpFood foodForReplacing;
    private MfpIngredientItem ingredientItemToBeReplaced;
    private List<MfpIngredientItem> ingredientItems = new ArrayList();
    private final Lazy<RecipeService> recipeService;
    private final Set<String> replacedIngredients = new HashSet();

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int INGREDIENTS_MATCHED = ViewModelPropertyId.next();
        public static final int INGREDIENTS_MATCH_FAILED = ViewModelPropertyId.next();
        public static final int INGREDIENTS_MATCH_FAILED_NETWORK_ERROR = ViewModelPropertyId.next();
    }

    public IngredientsMatchingViewModel(Runner runner, Lazy<RecipeService> lazy) {
        super(runner);
        this.recipeService = lazy;
    }

    public void load(String... strArr) {
        setState(State.Loading);
        new MatchIngredientsTask(this.recipeService, strArr[0], strArr[1]).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), MatchIngredientsTask.class)) {
            if (taskDetails.successful()) {
                this.ingredientItems = (List) taskDetails.getResult();
                MfpFood mfpFood = this.foodForReplacing;
                if (mfpFood != null) {
                    replaceOrAddIngredient(mfpFood, this.ingredientItemToBeReplaced);
                }
                notifyPropertyChanged(Property.INGREDIENTS_MATCHED);
            } else if (((ApiException) taskDetails.getFailure()).getCause() instanceof IOException) {
                notifyPropertyChanged(Property.INGREDIENTS_MATCH_FAILED_NETWORK_ERROR);
            } else {
                notifyPropertyChanged(Property.INGREDIENTS_MATCH_FAILED);
            }
        }
        setState(State.Loaded);
    }

    public void replaceOrAddIngredient(MfpFood mfpFood, MfpIngredientItem mfpIngredientItem) {
        if (getState() == State.Loading) {
            this.foodForReplacing = mfpFood;
            this.ingredientItemToBeReplaced = mfpIngredientItem;
            return;
        }
        MfpIngredientItem newIngredientItem = getNewIngredientItem(mfpFood, mfpIngredientItem);
        if (mfpIngredientItem != null) {
            int i = 0;
            while (true) {
                if (i >= this.ingredientItems.size()) {
                    break;
                } else if (((MfpIngredientItem) this.ingredientItems.get(i)).equals(mfpIngredientItem)) {
                    this.ingredientItems.remove(i);
                    MfpIngredient ingredient = mfpIngredientItem.getIngredient();
                    this.replacedIngredients.add(String.format("%s:%s", new Object[]{ingredient.getText(), ingredient.getRawText()}));
                    break;
                } else {
                    i++;
                }
            }
        }
        this.ingredientItems.add(0, newIngredientItem);
        notifyPropertyChanged(Property.INGREDIENTS_MATCHED);
        if (mfpIngredientItem == null || mfpIngredientItem.wasManuallyAdded()) {
            newIngredientItem.setWasManuallyAdded(true);
        }
        this.foodForReplacing = null;
        this.ingredientItemToBeReplaced = null;
    }

    public void deleteSelectedIngredients(ItemMultiSelectContext<MfpIngredientItem> itemMultiSelectContext) {
        Set itemSet = itemMultiSelectContext.getItemSet();
        this.ingredientItems.removeAll(itemSet);
        this.deletedIngredientsCount += itemSet.size();
    }

    public boolean hasUnmatchedIngredients() {
        for (MfpIngredientItem hasMatches : this.ingredientItems) {
            if (!hasMatches.hasMatches()) {
                return true;
            }
        }
        return false;
    }

    public IngredientsMatchingOverviewItem getOverviewItem(double d) {
        double d2 = 0.0d;
        boolean z = false;
        for (MfpIngredientItem mfpIngredientItem : this.ingredientItems) {
            if (mfpIngredientItem.hasMatches()) {
                d2 += mfpIngredientItem.getPrimaryMatch().getCaloriesValue();
            } else {
                z = true;
            }
        }
        IngredientsMatchingOverviewItem ingredientsMatchingOverviewItem = new IngredientsMatchingOverviewItem(d, d2 / d, !z);
        return ingredientsMatchingOverviewItem;
    }

    public void setIngredientItems(List<MfpIngredientItem> list) {
        this.ingredientItems = list;
    }

    public List<MfpIngredientItem> getIngredientItems() {
        return this.ingredientItems;
    }

    public int getEditedIngredientCount() {
        return this.replacedIngredients.size();
    }

    public int getDeletedIngredientsCount() {
        return this.deletedIngredientsCount;
    }

    private MfpIngredientItem getNewIngredientItem(MfpFood mfpFood, MfpIngredientItem mfpIngredientItem) {
        MfpIngredient fromFood = MfpIngredient.fromFood(mfpFood);
        if (mfpIngredientItem != null) {
            MfpIngredient ingredient = mfpIngredientItem.getIngredient();
            fromFood.setText(ingredient.getText());
            fromFood.setRawText(ingredient.getRawText());
        }
        return new MfpIngredientItem(fromFood, null, Collections.singletonList(fromFood));
    }
}
