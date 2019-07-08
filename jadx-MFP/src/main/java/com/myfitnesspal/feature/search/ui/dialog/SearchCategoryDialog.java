package com.myfitnesspal.feature.search.ui.dialog;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.exercise.ui.activity.ExerciseSearchActivity;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras;
import com.myfitnesspal.feature.search.ui.adapter.SearchCategory;
import com.myfitnesspal.feature.search.ui.adapter.SearchCategoryItemAdapter;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.constants.Constants.Exercise.ExerciseTypeName;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import javax.inject.Inject;

public class SearchCategoryDialog extends ListActivity {
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    @Inject
    NavigationHelper navigationHelper;
    private ArrayList<SearchCategory> searchCategories;
    private SearchCategoryItemAdapter searchCategoryItemAdapter;
    @Inject
    Session session;

    public boolean onSearchRequested() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, SearchCategoryDialog.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.searchCategories = new ArrayList<>();
        setContentView(R.layout.search_category);
        MyFitnessPalApp.getInstance().component().inject(this);
        this.searchCategoryItemAdapter = new SearchCategoryItemAdapter(this, R.layout.search_category_item, this.searchCategories);
        setListAdapter(this.searchCategoryItemAdapter);
        getSearchableItems();
        this.navigationHelper.setContext(this);
    }

    private void getSearchableItems() {
        try {
            MealNames mealNames = this.session.getUser().getMealNames();
            if (mealNames.notEmpty()) {
                for (String searchCategory : mealNames.getNames()) {
                    this.searchCategories.add(new SearchCategory(searchCategory, "meal"));
                }
            }
            for (String searchCategory2 : new String[]{ExerciseTypeName.CARDIOVASCULAR, ExerciseTypeName.STRENGTH}) {
                this.searchCategories.add(new SearchCategory(searchCategory2, "exercise"));
            }
            this.searchCategoryItemAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* access modifiers changed from: protected */
    public void onListItemClick(ListView listView, View view, int i, long j) {
        SearchCategory searchCategory = (SearchCategory) listView.getItemAtPosition(i);
        if (Strings.equalsIgnoreCase(searchCategory.getType(), "meal")) {
            this.navigationHelper.finishActivityAfterNavigation().withIntent(((FoodSearchActivityFactory) this.foodSearchRouter.get()).getFoodSearchActivityIntent(this.navigationHelper.getContext(), new Extras().setMealName(searchCategory.getTitle()))).startActivity(49);
        } else if (Strings.equalsIgnoreCase(searchCategory.getType(), "exercise")) {
            boolean z = !Strings.equalsIgnoreCase(searchCategory.getTitle(), ExerciseTypeName.CARDIOVASCULAR);
            this.navigationHelper.finishActivityAfterNavigation().withExtra(Constants.Extras.FOCUS_SEARCH, true).withIntent(ExerciseSearchActivity.newStartIntentForExerciseType(this, z ? 1 : 0)).startActivity(!z ? 47 : 48);
        }
    }
}
