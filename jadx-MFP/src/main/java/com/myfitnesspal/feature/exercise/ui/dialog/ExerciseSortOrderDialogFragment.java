package com.myfitnesspal.feature.exercise.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.model.SortOrderCheckableListItem;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ExerciseSortOrderDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_EXERCISE_TYPE = "exercise_type";
    private static final String EXTRA_SELECTED_TAB = "selected_tab";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<SortOrderHelper> sortOrderHelper;
    private OnSortOrderSelectedListener sortOrderSelectedListener;

    private enum ExerciseSortOrder {
        RecentlyUsed(SortOrder.RECENTLY_USED, R.string.sort_recent),
        AlphabeticalAscending(SortOrder.ALPHABETICAL_ASCENDING, R.string.sort_asc),
        AlphabeticalDescending(SortOrder.ALPHABETICAL_DESCENDING, R.string.sort_desc);
        
        private final SortOrder sortOrder;
        private final int stringResId;

        private ExerciseSortOrder(SortOrder sortOrder2, int i) {
            this.sortOrder = sortOrder2;
            this.stringResId = i;
        }

        public SortOrder getSortOrder() {
            return this.sortOrder;
        }

        public int getStringResId() {
            return this.stringResId;
        }
    }

    public interface OnSortOrderSelectedListener {
        void onSortOrderSelected(SortOrder sortOrder);
    }

    private String getEventPageType(int i) {
        return i == 0 ? "cardio" : "strength";
    }

    private int getLableResIdForSelectedTab(int i) {
        switch (i) {
            case SearchTabs.TAB_MY_EXERCISES /*6006*/:
                return R.string.my_exercises_sort;
            case 6007:
                return R.string.all_exercises_sort;
            default:
                return R.string.most_used_sort;
        }
    }

    public static ExerciseSortOrderDialogFragment newInstance(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SELECTED_TAB, i);
        bundle.putInt("exercise_type", i2);
        ExerciseSortOrderDialogFragment exerciseSortOrderDialogFragment = new ExerciseSortOrderDialogFragment();
        exerciseSortOrderDialogFragment.setArguments(bundle);
        return exerciseSortOrderDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        int i = BundleUtils.getInt(arguments, EXTRA_SELECTED_TAB);
        int i2 = BundleUtils.getInt(arguments, "exercise_type");
        SortOrder currentSortOrderForTab = ((SortOrderHelper) this.sortOrderHelper.get()).getCurrentSortOrderForTab(i);
        ExerciseSortOrder[] values = ExerciseSortOrder.values();
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < values.length; i3++) {
            ExerciseSortOrder exerciseSortOrder = values[i3];
            SortOrder sortOrder = exerciseSortOrder.getSortOrder();
            if (i3 != 0 || i == 6005) {
                arrayList.add(new SortOrderCheckableListItem(getString(exerciseSortOrder.getStringResId()), currentSortOrderForTab == sortOrder, sortOrder));
            }
        }
        return new MfpAlertDialogBuilder(getActivity()).setTitle(getLableResIdForSelectedTab(i)).setSingleChoiceItems((List<? extends CheckableListItem>) arrayList, (OnItemClickListener) new OnItemClickListener(arrayList, i, i2) {
            private final /* synthetic */ List f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ int f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                ExerciseSortOrderDialogFragment.lambda$onCreateDialog$0(ExerciseSortOrderDialogFragment.this, this.f$1, this.f$2, this.f$3, adapterView, view, i, j);
            }
        }).create();
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(ExerciseSortOrderDialogFragment exerciseSortOrderDialogFragment, List list, int i, int i2, AdapterView adapterView, View view, int i3, long j) {
        SortOrder sortOrderId = ((SortOrderCheckableListItem) list.get(i3)).getSortOrderId();
        ((SortOrderHelper) exerciseSortOrderDialogFragment.sortOrderHelper.get()).setCurrentSortOrderForSelectorButton(i, sortOrderId);
        SortOrderHelper.reportSortOrderChangedEvent((AnalyticsService) exerciseSortOrderDialogFragment.analyticsService.get(), i, sortOrderId, exerciseSortOrderDialogFragment.getEventPageType(i2));
        exerciseSortOrderDialogFragment.sortOrderSelectedListener.onSortOrderSelected(sortOrderId);
    }

    public void setSortOrderSelectedListener(OnSortOrderSelectedListener onSortOrderSelectedListener) {
        this.sortOrderSelectedListener = onSortOrderSelectedListener;
    }
}
