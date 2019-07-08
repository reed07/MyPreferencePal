package com.myfitnesspal.feature.home.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.event.NutrientEntryClickEvent;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.squareup.otto.Bus;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NutrientEntryAdapter extends ArrayAdapter<NutrientEntry> {
    /* access modifiers changed from: private */
    public static final Set<Integer> BOLDED_NUTRIENTS = new HashSet();
    private final Context context;
    private final boolean isPremium;
    /* access modifiers changed from: private */
    public final Lazy<Bus> messageBus;

    private class ViewHolder {
        private boolean isPremium;
        public TextView nutrientGoal;
        public TextView nutrientLabel;
        public TextView nutrientLeft;
        public ProgressBar nutrientProgressBar;
        public TextView nutrientTotal;

        public ViewHolder(View view, boolean z, boolean z2) {
            this.isPremium = z;
            this.nutrientLabel = (TextView) ViewUtils.findById(view, R.id.labelTxt);
            this.nutrientTotal = (TextView) ViewUtils.findById(view, R.id.totalTxt);
            this.nutrientGoal = (TextView) ViewUtils.findById(view, R.id.goalTxt);
            this.nutrientLeft = (TextView) ViewUtils.findById(view, R.id.leftTxt);
            if (z && !z2) {
                this.nutrientProgressBar = (ProgressBar) ViewUtils.findById(view, R.id.nutrition_progress_bar);
            }
        }

        public void setData(NutrientEntry nutrientEntry, Context context) {
            this.nutrientLabel.setText(nutrientEntry.getFieldLabel());
            if (this.isPremium) {
                boolean isSubordinateEntry = nutrientEntry.isSubordinateEntry();
                this.nutrientTotal.setText(nutrientEntry.getTotal());
                this.nutrientGoal.setText(nutrientEntry.getGoal());
                double tryParseDouble = NumberUtils.tryParseDouble(nutrientEntry.getGoal().replaceAll("\\D+", ""), 0.0d);
                double tryParseDouble2 = NumberUtils.tryParseDouble(nutrientEntry.getTotal().replaceAll("\\D+", ""), 0.0d);
                if (!isSubordinateEntry) {
                    this.nutrientProgressBar.setMax((int) tryParseDouble);
                    this.nutrientProgressBar.setProgress((int) tryParseDouble2);
                }
                this.nutrientLeft.setText(nutrientEntry.getLeft());
                this.nutrientLeft.setTextColor(NutrientEntryAdapter.this.getContext().getResources().getColor(tryParseDouble2 > tryParseDouble ? R.color.dark_orange : R.color.black_secondary_text));
                return;
            }
            this.nutrientTotal.setText(nutrientEntry.getTotal());
            this.nutrientGoal.setText(nutrientEntry.getGoal());
            this.nutrientLeft.setTextColor(context.getResources().getColor(nutrientEntry.getLeft().startsWith("-") ? R.color.balance_bright_color_negative : R.color.contents_text));
            this.nutrientLeft.setText(nutrientEntry.getLeft());
            setTypefaceBasedOnNutrientIndex(context, nutrientEntry);
        }

        private void setTypefaceBasedOnNutrientIndex(Context context, NutrientEntry nutrientEntry) {
            if (NutrientEntryAdapter.BOLDED_NUTRIENTS.contains(Integer.valueOf(nutrientEntry.getNutrientIndex()))) {
                this.nutrientLabel.setTypeface(null, 1);
            } else {
                this.nutrientLabel.setPadding(context.getResources().getDimensionPixelSize(R.dimen.nutrient_text_margin), 0, 0, 0);
            }
        }

        public void setBackground(int i) {
            this.nutrientLabel.setBackgroundColor(i);
            this.nutrientTotal.setBackgroundColor(i);
            this.nutrientGoal.setBackgroundColor(i);
            this.nutrientLeft.setBackgroundColor(i);
        }
    }

    static {
        BOLDED_NUTRIENTS.addAll(Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16)}));
    }

    public NutrientEntryAdapter(Context context2, List<NutrientEntry> list, Lazy<Bus> lazy, boolean z) {
        super(context2, R.layout.nutrient_item, list);
        this.context = context2;
        this.messageBus = lazy;
        this.isPremium = z;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final NutrientEntry nutrientEntry = (NutrientEntry) getItem(i);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
            boolean isSubordinateEntry = nutrientEntry.isSubordinateEntry();
            int i2 = this.isPremium ? isSubordinateEntry ? R.layout.nutrient_item_small : R.layout.nutrient_item_large : R.layout.nutrient_item;
            view = layoutInflater.inflate(i2, viewGroup, false);
            view.setTag(new ViewHolder(view, this.isPremium, isSubordinateEntry));
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.setData(nutrientEntry, this.context);
        if (this.isPremium) {
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((Bus) NutrientEntryAdapter.this.messageBus.get()).post(new NutrientEntryClickEvent(nutrientEntry));
                }
            });
        } else if (i % 2 != 0) {
            int color = this.context.getResources().getColor(R.color.mfp_background);
            viewHolder.setBackground(color);
            view.setBackgroundColor(color);
        }
        return view;
    }
}
