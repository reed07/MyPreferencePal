package com.myfitnesspal.feature.exercise.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.exercise.ui.activity.NewCardio;
import com.myfitnesspal.feature.exercise.ui.activity.NewStrength;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.settings.ui.activity.EditCardioExercise;
import com.myfitnesspal.feature.settings.ui.activity.EditStrengthExercise;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExercise.ExerciseTypes;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.EditListAdapter;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.RowViewHolder;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class MyExercisesFragment extends MfpEditableFragmentBase<MfpExercise> {
    @Inject
    Lazy<ExerciseMapper> exerciseMapper;
    @Inject
    Lazy<ExerciseService> exerciseService;
    private List<MfpExercise> exercises;
    private int type;

    /* access modifiers changed from: protected */
    public boolean addToFilteredList(MfpExercise mfpExercise, String str) {
        return true;
    }

    public OnClickListener getEmptyStatePrimaryButtonListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean wantsAddMenuItem() {
        return true;
    }

    public static MyExercisesFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        MyExercisesFragment myExercisesFragment = new MyExercisesFragment();
        myExercisesFragment.setArguments(bundle);
        return myExercisesFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public void onResume() {
        super.onResume();
        fetchData();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.type = BundleUtils.getInt(getArguments(), "type");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (!(i == 45 || i == 67)) {
            switch (i) {
                case 100:
                case 101:
                    break;
                default:
                    return;
            }
        }
        setupExerciseList();
    }

    public Type getEmptyStateViewType() {
        return this.type == 0 ? Type.Cardio : Type.Strength;
    }

    /* access modifiers changed from: protected */
    public EditListAdapter<MfpExercise> recreateAdapter() {
        return new EditListAdapter<MfpExercise>(this, getActivity()) {
            /* access modifiers changed from: protected */
            public void configureView(MfpExercise mfpExercise, RowViewHolder rowViewHolder, int i) {
                rowViewHolder.title.setText(Strings.toString(mfpExercise.getDescription()));
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onActionAddClicked() {
        super.onActionAddClicked();
        switch (this.type) {
            case 0:
                getNavigationHelper().withIntent(NewCardio.newStartIntent(getActivity(), false)).fromFragment(this).startActivity(45);
                return;
            case 1:
                getNavigationHelper().withIntent(NewStrength.newStartIntent(getActivity(), false)).fromFragment(this).startActivity(67);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onActionDeleteClicked(List<MfpExercise> list) {
        super.onActionDeleteClicked(list);
        for (MfpExercise mfpExercise : list) {
            if (mfpExercise.isPublic().booleanValue()) {
                postEvent(new AlertEvent(String.format("%s: %s", new Object[]{mfpExercise.getDescription(), getString(R.string.publicly_shared_exercise)})));
            } else {
                ((ExerciseService) this.exerciseService.get()).deleteExerciseLocally(mfpExercise, true);
            }
        }
        this.exercises.removeAll(list);
        refresh();
    }

    /* access modifiers changed from: protected */
    public void onItemClicked(MfpExercise mfpExercise) {
        if (mfpExercise.isPublic().booleanValue()) {
            postEvent(new AlertEvent(getString(R.string.publicly_shared_exercise)));
            return;
        }
        int value = ExerciseTypes.toValue(mfpExercise.getType());
        Exercise exercise = (Exercise) ((ExerciseMapper) this.exerciseMapper.get()).reverseMap(mfpExercise);
        if (value == 0) {
            getNavigationHelper().withIntent(EditCardioExercise.newStartIntent(getActivity(), exercise)).fromFragment(this).startActivity(100);
        } else {
            getNavigationHelper().withIntent(EditStrengthExercise.newStartIntent(getActivity(), exercise)).fromFragment(this).startActivity(101);
        }
    }

    /* access modifiers changed from: protected */
    public List<MfpExercise> getItems() {
        if (this.exercises == null) {
            setupExerciseList();
        }
        return this.exercises;
    }

    private void setupExerciseList() {
        this.exercises = ((ExerciseService) this.exerciseService.get()).getOwnedExercisesOfType(this.type, SortOrder.ALPHABETICAL_ASCENDING);
    }
}
