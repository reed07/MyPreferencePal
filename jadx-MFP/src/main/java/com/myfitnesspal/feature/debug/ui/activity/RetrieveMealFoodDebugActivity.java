package com.myfitnesspal.feature.debug.ui.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.meals.task.RetrieveMealFoodTask;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.model.FoodSearchModel;
import com.myfitnesspal.feature.search.ui.task.LocalFoodSearchTask;
import com.myfitnesspal.feature.search.ui.task.LocalFoodSearchTask.CompletedEvent;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class RetrieveMealFoodDebugActivity extends MfpActivity {
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<FoodService> foodService;
    @BindView(2131363015)
    RecyclerView mealFoodsRecyclerView;
    @Inject
    Lazy<SearchService> searchService;

    class MealAdapter extends Adapter<RecyclerViewHolder<DiaryEntryCellModel>> {
        private final List<DiaryEntryCellModel> list;

        class ViewHolder extends RecyclerViewHolder<DiaryEntryCellModel> {
            @BindView(2131363809)
            TextView primaryTextView;
            @BindView(2131363810)
            TextView secondaryTextView;

            public ViewHolder(ViewGroup viewGroup) {
                super(R.layout.double_text_item_container, viewGroup);
            }

            public void setData(final DiaryEntryCellModel diaryEntryCellModel, int i) {
                ViewUtils.setGone(this.secondaryTextView);
                this.primaryTextView.setText(diaryEntryCellModel.summaryLine1());
                getParent().setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        new RetrieveMealFoodTask(RetrieveMealFoodDebugActivity.this.foodService, ((MealFood) diaryEntryCellModel).getUid(), false).run(RetrieveMealFoodDebugActivity.this.getRunner());
                    }
                });
            }
        }

        public class ViewHolder_ViewBinding implements Unbinder {
            private ViewHolder target;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.target = viewHolder;
                viewHolder.primaryTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.text_primary, "field 'primaryTextView'", TextView.class);
                viewHolder.secondaryTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.text_secondary, "field 'secondaryTextView'", TextView.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.target;
                if (viewHolder != null) {
                    this.target = null;
                    viewHolder.primaryTextView = null;
                    viewHolder.secondaryTextView = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        private MealAdapter(List<DiaryEntryCellModel> list2) {
            this.list = list2;
        }

        public RecyclerViewHolder<DiaryEntryCellModel> onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(viewGroup);
        }

        public void onBindViewHolder(RecyclerViewHolder<DiaryEntryCellModel> recyclerViewHolder, int i) {
            recyclerViewHolder.setData(getItem(i), i);
        }

        public int getItemCount() {
            return this.list.size();
        }

        public DiaryEntryCellModel getItem(int i) {
            return (DiaryEntryCellModel) this.list.get(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.retrieve_meal_food_debug);
        component().inject(this);
        this.mealFoodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mealFoodsRecyclerView.setHasFixedSize(true);
        LocalFoodSearchTask localFoodSearchTask = new LocalFoodSearchTask(this.searchService, null, FoodSearchTab.MEALS.getTabId(), Integer.MAX_VALUE, this.dbConnectionManager);
        localFoodSearchTask.run(getRunner());
    }

    @Subscribe
    public void onLocalFoodSearchTaskCompletedEvent(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            this.mealFoodsRecyclerView.setAdapter(new MealAdapter(((FoodSearchModel) completedEvent.getResult()).items));
        }
    }

    @Subscribe
    public void onRetrieveMealFoodTaskCompletedEvent(RetrieveMealFoodTask.CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            getNavigationHelper().withIntent(FoodEditorActivity.newMealItemEditorIntent(this, null, null, (MealFood) ((Tuple2) completedEvent.getResult()).getItem1(), "")).startActivity();
            return;
        }
        Ln.e(completedEvent.getFailure());
    }
}
