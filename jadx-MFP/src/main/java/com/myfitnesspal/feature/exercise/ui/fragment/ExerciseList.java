package com.myfitnesspal.feature.exercise.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity.EditablePagerAdapter;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity.EditablePagerAdapter.FragmentEntry;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import java.util.Arrays;
import java.util.List;

public class ExerciseList extends MfpPagedEditableActivity {
    private static final String EXTRA_DEFAULT_TYPE = "extra_default_tab";

    private static class ExercisePagerAdapter extends EditablePagerAdapter {
        public ExercisePagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager, context);
        }

        /* access modifiers changed from: protected */
        public List<FragmentEntry> createFragmentList() {
            return Arrays.asList(new FragmentEntry[]{new FragmentEntry((MfpEditableFragmentBase) MyExercisesFragment.newInstance(0), (int) R.string.cardio), new FragmentEntry((MfpEditableFragmentBase) MyExercisesFragment.newInstance(1), (int) R.string.strength)});
        }
    }

    public String getAnalyticsScreenTag() {
        return Screens.EXERCISE_LIST;
    }

    /* access modifiers changed from: protected */
    public int getContentViewId() {
        return R.layout.exercise_list;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, ExerciseList.class);
    }

    public static Intent newStartIntent(Context context, int i) {
        return new Intent(context, ExerciseList.class).putExtra(EXTRA_DEFAULT_TYPE, i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null && getIntent().getIntExtra(EXTRA_DEFAULT_TYPE, 0) == 1) {
            getPager().setCurrentItem(1);
        }
    }

    /* access modifiers changed from: protected */
    public EditablePagerAdapter recreateAdapter() {
        return new ExercisePagerAdapter(getSupportFragmentManager(), this);
    }
}
