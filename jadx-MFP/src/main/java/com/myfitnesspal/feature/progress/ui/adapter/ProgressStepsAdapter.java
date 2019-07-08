package com.myfitnesspal.feature.progress.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.model.StepsProgressModel;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Tuple2;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProgressStepsAdapter extends BaseAdapter {
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
    private static final SimpleDateFormat DAY_DATE_FORMAT = new SimpleDateFormat("EEEE");
    private final Context context;
    private List<Tuple2<Date, Integer>> steps;

    /* renamed from: com.myfitnesspal.feature.progress.ui.adapter.ProgressStepsAdapter$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$progress$ui$adapter$ProgressStepsAdapter$ViewType = new int[ViewType.values().length];

        static {
            try {
                $SwitchMap$com$myfitnesspal$feature$progress$ui$adapter$ProgressStepsAdapter$ViewType[ViewType.Steps.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private static class StepsViewHolder extends ViewHolder {
        TextView dateView;
        TextView dayView;
        TextView valueView;

        StepsViewHolder(View view) {
            super(null);
            this.dateView = (TextView) view.findViewById(R.id.entry_date);
            this.dayView = (TextView) view.findViewById(R.id.entry_day);
            this.valueView = (TextView) view.findViewById(R.id.value);
        }
    }

    private static class ViewHolder {
        private ViewHolder() {
        }

        /* synthetic */ ViewHolder(AnonymousClass1 r1) {
            this();
        }
    }

    private enum ViewType {
        Steps
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ProgressStepsAdapter(Context context2, StepsProgressModel stepsProgressModel) {
        this.context = context2;
        this.steps = stepsProgressModel.getListViewData();
    }

    public void setModel(StepsProgressModel stepsProgressModel) {
        this.steps = stepsProgressModel.getListViewData();
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.steps.size();
    }

    public Object getItem(int i) {
        return this.steps.get(i);
    }

    public int getItemViewType(int i) {
        return ViewType.Steps.ordinal();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewType viewType = ViewType.values()[getItemViewType(i)];
        View inflate = View.inflate(this.context, getLayoutIdBasedOnViewType(viewType), null);
        inflate.setTag(new StepsViewHolder(inflate));
        ViewHolder viewHolder = (ViewHolder) inflate.getTag();
        if (AnonymousClass1.$SwitchMap$com$myfitnesspal$feature$progress$ui$adapter$ProgressStepsAdapter$ViewType[viewType.ordinal()] == 1) {
            bindSteps((StepsViewHolder) viewHolder, (Tuple2) getItem(i));
        }
        return inflate;
    }

    private void bindSteps(StepsViewHolder stepsViewHolder, Tuple2<Date, Integer> tuple2) {
        Date date = (Date) tuple2.getItem1();
        String localeStringFromFloat = NumberUtils.localeStringFromFloat((float) ((Integer) tuple2.getItem2()).intValue(), true);
        stepsViewHolder.dateView.setText(DATE_FORMAT.format(date));
        stepsViewHolder.dayView.setText(DAY_DATE_FORMAT.format(date));
        stepsViewHolder.valueView.setText(localeStringFromFloat);
    }

    private int getLayoutIdBasedOnViewType(ViewType viewType) {
        return AnonymousClass1.$SwitchMap$com$myfitnesspal$feature$progress$ui$adapter$ProgressStepsAdapter$ViewType[viewType.ordinal()] != 1 ? R.layout.progress_photos_entry_list_item_with_text : R.layout.progress_photos_entry_list_item_with_text;
    }
}
