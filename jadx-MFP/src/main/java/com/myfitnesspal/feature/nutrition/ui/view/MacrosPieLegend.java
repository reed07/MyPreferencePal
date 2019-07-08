package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.LegendData;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ViewUtils;
import java.util.Collection;
import java.util.List;

public class MacrosPieLegend extends CustomPieLegend {
    /* access modifiers changed from: private */
    public int clickedIndex;
    /* access modifiers changed from: private */
    public int currentIndex;
    private final LayoutInflater inflater;
    private boolean isWeekly;
    /* access modifiers changed from: private */
    public OnClickListener onGoalClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (MacrosPieLegend.this.pieLegendListener != null) {
                MacrosPieLegend.this.pieLegendListener.onGoalClicked(MacrosPieLegend.this.getNutrientIndexForMacroIndex(((Integer) view.getTag()).intValue()));
            }
        }
    };
    /* access modifiers changed from: private */
    public OnClickListener onLegendClickListener = new OnClickListener() {
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            if (MacrosPieLegend.this.currentIndex != intValue) {
                MacrosPieLegend.this.clickedIndex = intValue;
                MacrosPieLegend.this.setBackgrounds(intValue);
            }
            if (MacrosPieLegend.this.pieLegendListener != null) {
                MacrosPieLegend.this.pieLegendListener.onLegendClicked(MacrosPieLegend.this.getNutrientIndexForMacroIndex(intValue));
            }
        }
    };
    /* access modifiers changed from: private */
    public LegendListener pieLegendListener;
    private final ViewHolder[] viewHolders;

    private class ViewHolder {
        private TextView goalValueTextView;
        private TextView labelTextView;
        private TextView labelValueTextView;
        private View legendItem;
        private TextView totalValueTextView;

        private ViewHolder(View view, int i) {
            this.legendItem = view;
            this.labelTextView = (TextView) ViewUtils.findById(view, R.id.label);
            this.labelValueTextView = (TextView) ViewUtils.findById(view, R.id.label_value);
            this.totalValueTextView = (TextView) ViewUtils.findById(view, R.id.total_value);
            this.goalValueTextView = (TextView) ViewUtils.findById(view, R.id.goal_value);
            this.goalValueTextView.setOnClickListener(MacrosPieLegend.this.onGoalClickListener);
            this.legendItem.setOnClickListener(MacrosPieLegend.this.onLegendClickListener);
            this.legendItem.setTag(Integer.valueOf(i));
            this.goalValueTextView.setTag(Integer.valueOf(i));
        }

        /* access modifiers changed from: private */
        public void setLegendData(LegendData legendData) {
            setLabelText(legendData.getLabel());
            setLabelValueText(legendData.getActualData());
            setLabelDrawable(legendData.getLabelColor());
            setTotalValueText(MacrosPieLegend.this.getPercentageString((float) legendData.getValuePercentage()));
            setGoalValueText(MacrosPieLegend.this.getPercentageString((float) legendData.getGoalPercentage()));
        }

        private void setLabelText(String str) {
            this.labelTextView.setText(str);
        }

        private void setLabelValueText(String str) {
            this.labelValueTextView.setText(str);
        }

        private void setLabelDrawable(int i) {
            this.labelTextView.setCompoundDrawablesWithIntrinsicBounds(MacrosPieLegend.this.getLegendDrawable(i), null, null, null);
        }

        private void setTotalValueText(String str) {
            this.totalValueTextView.setText(str);
        }

        private void setGoalValueText(String str) {
            this.goalValueTextView.setText(str);
        }

        /* access modifiers changed from: private */
        public void setLegendItemBackgroundColor(int i) {
            this.legendItem.setBackgroundColor(i);
        }
    }

    /* access modifiers changed from: private */
    public int getNutrientIndexForMacroIndex(int i) {
        switch (i) {
            case 0:
                return 9;
            case 1:
                return 1;
            case 2:
                return 12;
            default:
                return -1;
        }
    }

    private int getSize() {
        return 3;
    }

    public MacrosPieLegend(Context context, boolean z, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.inflater.inflate(R.layout.nutrition_pie_legend, viewGroup, true);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.macro_legend_container_padding);
        viewGroup.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.viewHolders = new ViewHolder[getSize()];
        this.currentIndex = Integer.MIN_VALUE;
        this.clickedIndex = Integer.MIN_VALUE;
        this.isWeekly = z;
    }

    /* access modifiers changed from: private */
    public ViewHolder getViewHolder(int i) {
        return this.viewHolders[i];
    }

    public void registerListener(LegendListener legendListener) {
        this.pieLegendListener = legendListener;
    }

    public void setLegendData(List<LegendData> list) {
        TextView textView = (TextView) ViewUtils.findById(getContainer(), R.id.label_total);
        ViewGroup container = getContainer();
        textView.setText(this.isWeekly ? R.string.weekly_graph_average_token : R.string.totalTxt);
        for (int i = 0; i < CollectionUtils.size((Collection<?>) list); i++) {
            View inflate = this.inflater.inflate(R.layout.nutrition_macros_legend_item, container, false);
            initViews((LegendData) list.get(i), inflate, i);
            container.addView(inflate);
        }
    }

    private void initViews(LegendData legendData, View view, int i) {
        ViewHolder viewHolder = new ViewHolder(view, i);
        viewHolder.setLegendData(legendData);
        this.viewHolders[i] = viewHolder;
    }

    /* access modifiers changed from: private */
    public String getPercentageString(float f) {
        return getContext().getString(R.string.percent_value, new Object[]{Integer.valueOf(Math.round(f))});
    }

    /* access modifiers changed from: private */
    public void setBackgrounds(final int i) {
        this.currentIndex = i;
        iterateArraySizeAndExecuteFunction(new Function1<Integer>() {
            public void execute(Integer num) throws RuntimeException {
                MacrosPieLegend.this.getViewHolder(num.intValue()).setLegendItemBackgroundColor(MacrosPieLegend.this.getColorForRow(num.intValue(), i));
            }
        });
    }

    /* access modifiers changed from: private */
    public int getColorForRow(int i, int i2) {
        return getResources().getColor(i2 == i ? R.color.pie_legend_selected : R.color.white);
    }

    private void iterateArraySizeAndExecuteFunction(Function1<Integer> function1) {
        int size = getSize();
        for (int i = 0; i < size; i++) {
            FunctionUtils.invokeIfValid(function1, Integer.valueOf(i));
        }
    }

    public void onSeriesClicked(int i) {
        if (this.currentIndex != i) {
            setBackgrounds(i);
        }
    }

    public void onSeriesClear() {
        this.currentIndex = Integer.MIN_VALUE;
        final int color = getResources().getColor(R.color.white);
        iterateArraySizeAndExecuteFunction(new Function1<Integer>() {
            public void execute(Integer num) throws RuntimeException {
                MacrosPieLegend.this.getViewHolder(num.intValue()).setLegendItemBackgroundColor(color);
            }
        });
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public int getClickedIndex() {
        return this.clickedIndex;
    }
}
