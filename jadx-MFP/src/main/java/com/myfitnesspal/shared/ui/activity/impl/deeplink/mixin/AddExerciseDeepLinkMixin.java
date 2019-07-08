package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import android.content.Context;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.ui.activity.AddExerciseEntry;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.db.DatasetManager;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.task.QueryStockExerciseByMasterIdTask;
import com.myfitnesspal.shared.task.QueryStockExerciseByMasterIdTask.CompletedEvent;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import javax.inject.Inject;

public class AddExerciseDeepLinkMixin extends AppIndexerMixinBase {
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    ExerciseEntryMapper mapper;

    public AddExerciseDeepLinkMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        component().inject(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        long longValue = Long.valueOf(BundleUtils.getString(getActivity().getIntent().getExtras(), "master_id")).longValue();
        if (longValue == 0) {
            Ln.d("failed due to malformed url id", new Object[0]);
            onInvalidResponse((int) R.string.error_occured);
            return;
        }
        new QueryStockExerciseByMasterIdTask(longValue, (DbConnectionManager) this.dbConnectionManager.get(), DatasetManager.current((DbConnectionManager) this.dbConnectionManager.get())).run(getRunner());
    }

    @Subscribe
    public void onQueryCompleted(CompletedEvent completedEvent) {
        Exercise exercise = (Exercise) completedEvent.getResult();
        if (exercise != null) {
            proceed(exercise);
        } else {
            onInvalidResponse((int) R.string.error_occured);
        }
    }

    private void proceed(Exercise exercise) {
        ExerciseEntry exerciseEntry = new ExerciseEntry();
        exerciseEntry.setExercise(exercise);
        exerciseEntry.setDate(((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().getDate());
        MfpExerciseEntry mfpExerciseEntry = (MfpExerciseEntry) this.mapper.tryMapFrom(exerciseEntry);
        if (mfpExerciseEntry != null) {
            reportView(exercise.getUid());
            getComponentInterface().getNavigationHelper().withExtra(Extras.STARTED_FROM_DEEP_LINK, true).withIntent(AddExerciseEntry.newStartIntent((Context) getActivity(), mfpExerciseEntry)).startActivity();
        }
        getActivity().finish();
    }
}
