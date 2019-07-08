package com.myfitnesspal.feature.profile.ui.viewmodel;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.DedupeMode;
import com.uacf.taskrunner.Tasks.Blocking.Unchecked;
import dagger.Lazy;
import javax.inject.Inject;

public class MyItemsViewModel extends RunnerViewModel<Void> {
    private Context context;
    private DomainModel model = new DomainModel();

    private static class DomainModel {
        int cardioCount;
        int foodCount;
        int mealCount;
        int recipeCount;
        int strengthCount;

        private DomainModel() {
        }
    }

    public static class MyItemsTask extends Unchecked<DomainModel> {
        public static final String NAME = MyItemsTask.class.getCanonicalName();
        @Inject
        Lazy<DbConnectionManager> dbConnectionManager;
        @Inject
        Lazy<ExerciseService> exerciseService;
        @Inject
        Lazy<Session> session;

        MyItemsTask() {
            MyFitnessPalApp.getInstance().component().inject(this);
        }

        /* access modifiers changed from: protected */
        public DomainModel exec(Context context) {
            DomainModel domainModel = new DomainModel();
            domainModel.cardioCount = ((ExerciseService) this.exerciseService.get()).getCountForOwnedExercisesOfType(0);
            domainModel.strengthCount = ((ExerciseService) this.exerciseService.get()).getCountForOwnedExercisesOfType(1);
            domainModel.mealCount = ((DbConnectionManager) this.dbConnectionManager.get()).genericDbAdapter().countOwnedItemsOfType(3, ((Session) this.session.get()).getUser().getLocalId());
            domainModel.recipeCount = ((DbConnectionManager) this.dbConnectionManager.get()).recipeBoxItemsDBAdapter().fetchCountForRecipeBoxItems();
            domainModel.foodCount = ((DbConnectionManager) this.dbConnectionManager.get()).genericDbAdapter().countOwnedItemsOfType(1, ((Session) this.session.get()).getUser().getLocalId());
            return domainModel;
        }
    }

    public MyItemsViewModel(Context context2, Runner runner) {
        super(runner);
        this.context = context2.getApplicationContext();
    }

    public void load(Void... voidArr) {
        if (!isLoading()) {
            setState(State.Loading);
            getRunner().run(MyItemsTask.NAME, new MyItemsTask(), DedupeMode.CancelExisting);
        }
    }

    public Spanned getMealCount() {
        return getPluralSpanned(R.plurals.me_my_meals_count, this.model.mealCount);
    }

    public Spanned getFoodCount() {
        return getPluralSpanned(R.plurals.me_my_foods_count, this.model.foodCount);
    }

    public Spanned getRecipesCount() {
        return getPluralSpanned(R.plurals.me_my_recipes_count, this.model.recipeCount);
    }

    public Spanned getCardioCount() {
        return getPluralSpanned(R.plurals.me_my_cardio_count, this.model.cardioCount);
    }

    public Spanned getStrengthCount() {
        return getPluralSpanned(R.plurals.me_my_strength_count, this.model.strengthCount);
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && MyItemsTask.NAME.equals(taskDetails.getTaskName())) {
            if (taskDetails.successful()) {
                this.model = (DomainModel) taskDetails.getResult();
            }
            setState(taskDetails.successful() ? State.Loaded : State.Error);
        }
    }

    private Spanned getPluralSpanned(int i, int i2) {
        return Html.fromHtml(String.format(this.context.getResources().getQuantityString(i, i2).replace("%1$d", "<b>%1$d</b>"), new Object[]{Integer.valueOf(i2)}));
    }
}
