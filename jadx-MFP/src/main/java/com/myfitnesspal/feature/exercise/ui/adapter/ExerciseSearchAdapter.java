package com.myfitnesspal.feature.exercise.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.exercise.constants.ExerciseSearchTab;
import com.myfitnesspal.feature.exercise.model.ExerciseSearchAdapterItem;
import com.myfitnesspal.feature.exercise.model.ExerciseSearchEmptyItem;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.MultiAddExerciseSelection;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;

public class ExerciseSearchAdapter extends Adapter<RecyclerViewHolder<ExerciseSearchAdapterItem>> {
    /* access modifiers changed from: private */
    public final ExerciseAdapterItemActionListener adapterItemActionListener;
    /* access modifiers changed from: private */
    public final ExerciseSearchTab exerciseSearchTab;
    private final Lazy<ExerciseStringService> exerciseStringService;
    /* access modifiers changed from: private */
    public String queryString;
    private final List<ExerciseSearchAdapterItem> searchAdapterItems;

    class EmptyPromptViewHolder extends RecyclerViewHolder<ExerciseSearchAdapterItem> {
        private final OnClickListener onSecondaryTextClickListener = new OnClickListener() {
            public void onClick(View view) {
                ExerciseSearchAdapter.this.adapterItemActionListener.onEmptyItemTextClick();
            }
        };
        @BindView(2131363304)
        TextView primaryEmptyTextView;
        @BindView(2131363582)
        TextView secondaryEmptyTextView;

        EmptyPromptViewHolder(ViewGroup viewGroup) {
            super(R.layout.new_empty_search_list_item, viewGroup);
            this.secondaryEmptyTextView.setOnClickListener(this.onSecondaryTextClickListener);
        }

        public void setData(ExerciseSearchAdapterItem exerciseSearchAdapterItem, int i) {
            ExerciseSearchEmptyItem exerciseSearchEmptyItem;
            int i2;
            String str = null;
            switch (((ExerciseSearchEmptyItem) exerciseSearchAdapterItem).getEmptyItemType()) {
                case NoOnlineItems:
                    i2 = R.string.noMatchFound;
                    break;
                case NoFilteredItems:
                    i2 = R.string.no_results_found_in_tab;
                    str = getContext().getString(R.string.search_all_exercises, new Object[]{ExerciseSearchAdapter.this.queryString});
                    break;
                case NoLocalItems:
                    i2 = ExerciseSearchAdapter.this.exerciseSearchTab.getEmptyListResId();
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled empty item type: ");
                    sb.append(exerciseSearchEmptyItem.getEmptyItemType());
                    throw new IllegalStateException(sb.toString());
            }
            if (i2 == -1) {
                i2 = R.string.noMatchFound;
            }
            this.primaryEmptyTextView.setText(i2);
            ViewUtils.setVisible(Strings.notEmpty(str), this.secondaryEmptyTextView);
            this.secondaryEmptyTextView.setText(str);
        }
    }

    public class EmptyPromptViewHolder_ViewBinding implements Unbinder {
        private EmptyPromptViewHolder target;

        @UiThread
        public EmptyPromptViewHolder_ViewBinding(EmptyPromptViewHolder emptyPromptViewHolder, View view) {
            this.target = emptyPromptViewHolder;
            emptyPromptViewHolder.primaryEmptyTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.primary_empty_text, "field 'primaryEmptyTextView'", TextView.class);
            emptyPromptViewHolder.secondaryEmptyTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.secondary_empty_text, "field 'secondaryEmptyTextView'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            EmptyPromptViewHolder emptyPromptViewHolder = this.target;
            if (emptyPromptViewHolder != null) {
                this.target = null;
                emptyPromptViewHolder.primaryEmptyTextView = null;
                emptyPromptViewHolder.secondaryEmptyTextView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public interface ExerciseAdapterItemActionListener {
        void onEmptyItemTextClick();

        void onExerciseItemCheckChange(MfpExerciseEntry mfpExerciseEntry, CompoundButton compoundButton, boolean z);

        void onExerciseItemClick(MfpExerciseEntry mfpExerciseEntry, int i);
    }

    static class ExerciseEntryViewHolder extends RecyclerViewHolder<ExerciseSearchAdapterItem> {
        /* access modifiers changed from: private */
        public final ExerciseAdapterItemActionListener adapterItemActionListener;
        @BindView(2131363809)
        TextView exerciseDescription;
        @BindView(2131363810)
        TextView exerciseDetails;
        private final Lazy<ExerciseStringService> exerciseStringService;
        @BindView(2131363082)
        CheckBox multiAddCheckBox;
        private final OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ExerciseEntryViewHolder.this.adapterItemActionListener.onExerciseItemCheckChange((MfpExerciseEntry) compoundButton.getTag(), compoundButton, z);
            }
        };
        private final OnClickListener onItemClickListener = new OnClickListener() {
            public void onClick(View view) {
                Tuple2 tuple2 = (Tuple2) view.getTag();
                ExerciseEntryViewHolder.this.adapterItemActionListener.onExerciseItemClick((MfpExerciseEntry) tuple2.getItem1(), ((Integer) tuple2.getItem2()).intValue());
            }
        };

        ExerciseEntryViewHolder(ViewGroup viewGroup, Lazy<ExerciseStringService> lazy, ExerciseAdapterItemActionListener exerciseAdapterItemActionListener) {
            super(R.layout.generic_list_item_with_checkbox, viewGroup);
            this.exerciseStringService = lazy;
            this.adapterItemActionListener = exerciseAdapterItemActionListener;
            this.itemView.setOnClickListener(this.onItemClickListener);
        }

        public void setData(ExerciseSearchAdapterItem exerciseSearchAdapterItem, int i) {
            MfpExerciseEntry mfpExerciseEntry = (MfpExerciseEntry) exerciseSearchAdapterItem;
            boolean isActive = MultiAddExerciseSelection.isActive();
            ViewUtils.setVisible(isActive, this.multiAddCheckBox);
            if (isActive) {
                MfpExerciseEntry exerciseEntryForExercise = MultiAddExerciseSelection.current().getExerciseEntryForExercise(mfpExerciseEntry.getExercise());
                if (exerciseEntryForExercise != null) {
                    mfpExerciseEntry = exerciseEntryForExercise;
                }
                this.multiAddCheckBox.setOnCheckedChangeListener(null);
                this.multiAddCheckBox.setChecked(MultiAddExerciseSelection.current().hasSelectedItem(mfpExerciseEntry));
                this.multiAddCheckBox.setOnCheckedChangeListener(this.onCheckedChangeListener);
            }
            this.exerciseDescription.setText(((ExerciseStringService) this.exerciseStringService.get()).getDescriptionName(mfpExerciseEntry.getExercise()));
            if (isEmptyExerciseEntryWrapper(mfpExerciseEntry)) {
                ViewUtils.setGone(this.exerciseDetails);
            } else {
                ViewUtils.setVisible(this.exerciseDetails);
                this.exerciseDetails.setText(((ExerciseStringService) this.exerciseStringService.get()).getSummaryDescription(mfpExerciseEntry));
            }
            this.multiAddCheckBox.setTag(mfpExerciseEntry);
            this.itemView.setTag(Tuple.create(mfpExerciseEntry, Integer.valueOf(i)));
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0031 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean isEmptyExerciseEntryWrapper(com.myfitnesspal.shared.model.v2.MfpExerciseEntry r6) {
            /*
                r5 = this;
                com.myfitnesspal.shared.model.v2.MfpExercise r0 = r6.getExercise()
                java.lang.String r0 = r0.getType()
                int r1 = r0.hashCode()
                r2 = -1367604170(0xffffffffae7c0436, float:-5.7301906E-11)
                r3 = 0
                r4 = 1
                if (r1 == r2) goto L_0x0023
                r2 = 1791316033(0x6ac55041, float:1.1926869E26)
                if (r1 == r2) goto L_0x0019
                goto L_0x002d
            L_0x0019:
                java.lang.String r1 = "strength"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x002d
                r0 = 1
                goto L_0x002e
            L_0x0023:
                java.lang.String r1 = "cardio"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x002d
                r0 = 0
                goto L_0x002e
            L_0x002d:
                r0 = -1
            L_0x002e:
                switch(r0) {
                    case 0: goto L_0x0046;
                    case 1: goto L_0x0032;
                    default: goto L_0x0031;
                }
            L_0x0031:
                return r4
            L_0x0032:
                com.myfitnesspal.shared.model.v2.MfpMeasuredValue r0 = r6.getWeightPerSet()
                if (r0 == 0) goto L_0x0044
                int r0 = r6.getSets()
                if (r0 <= 0) goto L_0x0044
                int r6 = r6.getRepsPerSet()
                if (r6 > 0) goto L_0x0045
            L_0x0044:
                r3 = 1
            L_0x0045:
                return r3
            L_0x0046:
                int r0 = r6.getDuration()
                if (r0 <= 0) goto L_0x0052
                com.myfitnesspal.shared.model.v2.MfpMeasuredValue r6 = r6.getEnergy()
                if (r6 != 0) goto L_0x0053
            L_0x0052:
                r3 = 1
            L_0x0053:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.exercise.ui.adapter.ExerciseSearchAdapter.ExerciseEntryViewHolder.isEmptyExerciseEntryWrapper(com.myfitnesspal.shared.model.v2.MfpExerciseEntry):boolean");
        }
    }

    public class ExerciseEntryViewHolder_ViewBinding implements Unbinder {
        private ExerciseEntryViewHolder target;

        @UiThread
        public ExerciseEntryViewHolder_ViewBinding(ExerciseEntryViewHolder exerciseEntryViewHolder, View view) {
            this.target = exerciseEntryViewHolder;
            exerciseEntryViewHolder.exerciseDescription = (TextView) Utils.findRequiredViewAsType(view, R.id.text_primary, "field 'exerciseDescription'", TextView.class);
            exerciseEntryViewHolder.exerciseDetails = (TextView) Utils.findRequiredViewAsType(view, R.id.text_secondary, "field 'exerciseDetails'", TextView.class);
            exerciseEntryViewHolder.multiAddCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.multiSelectCheckBox, "field 'multiAddCheckBox'", CheckBox.class);
        }

        @CallSuper
        public void unbind() {
            ExerciseEntryViewHolder exerciseEntryViewHolder = this.target;
            if (exerciseEntryViewHolder != null) {
                this.target = null;
                exerciseEntryViewHolder.exerciseDescription = null;
                exerciseEntryViewHolder.exerciseDetails = null;
                exerciseEntryViewHolder.multiAddCheckBox = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    private enum ViewType {
        ExerciseEntry,
        EmptyItem
    }

    public ExerciseSearchAdapter(List<ExerciseSearchAdapterItem> list, Lazy<ExerciseStringService> lazy, ExerciseAdapterItemActionListener exerciseAdapterItemActionListener, ExerciseSearchTab exerciseSearchTab2, String str) {
        this.searchAdapterItems = list;
        this.exerciseStringService = lazy;
        this.adapterItemActionListener = exerciseAdapterItemActionListener;
        this.exerciseSearchTab = exerciseSearchTab2;
        this.queryString = str;
    }

    public int getItemViewType(int i) {
        ExerciseSearchAdapterItem exerciseSearchAdapterItem = (ExerciseSearchAdapterItem) this.searchAdapterItems.get(i);
        if (exerciseSearchAdapterItem instanceof ExerciseSearchEmptyItem) {
            return ViewType.EmptyItem.ordinal();
        }
        if (exerciseSearchAdapterItem instanceof MfpExerciseEntry) {
            return ViewType.ExerciseEntry.ordinal();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unhandled item : ");
        sb.append(exerciseSearchAdapterItem);
        throw new IllegalStateException(sb.toString());
    }

    public int getItemCount() {
        return this.searchAdapterItems.size();
    }

    public RecyclerViewHolder<ExerciseSearchAdapterItem> onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewType viewType = ViewType.values()[i];
        switch (viewType) {
            case ExerciseEntry:
                return new ExerciseEntryViewHolder(viewGroup, this.exerciseStringService, this.adapterItemActionListener);
            case EmptyItem:
                return new EmptyPromptViewHolder(viewGroup);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unhandled view type : ");
                sb.append(viewType);
                throw new IllegalStateException(sb.toString());
        }
    }

    public void onBindViewHolder(RecyclerViewHolder<ExerciseSearchAdapterItem> recyclerViewHolder, int i) {
        recyclerViewHolder.setData(getItem(i), i);
    }

    public void setItems(List<ExerciseSearchAdapterItem> list) {
        this.searchAdapterItems.clear();
        this.searchAdapterItems.addAll(list);
        notifyDataSetChanged();
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    private ExerciseSearchAdapterItem getItem(int i) {
        return (ExerciseSearchAdapterItem) this.searchAdapterItems.get(i);
    }
}
