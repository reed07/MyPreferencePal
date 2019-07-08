package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.fragment.MfpDateRestrictedFragment_ViewBinding;

public class GraphViewFragment_ViewBinding extends MfpDateRestrictedFragment_ViewBinding {
    private GraphViewFragment target;

    @UiThread
    public GraphViewFragment_ViewBinding(GraphViewFragment graphViewFragment, View view) {
        super(graphViewFragment, view);
        this.target = graphViewFragment;
        graphViewFragment.tvDailyOrWeekly = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDailyOrWeekly, "field 'tvDailyOrWeekly'", TextView.class);
        graphViewFragment.dateBarParentLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.date, "field 'dateBarParentLayout'", LinearLayout.class);
        graphViewFragment.dateTitleLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llDate, "field 'dateTitleLayout'", LinearLayout.class);
    }

    public void unbind() {
        GraphViewFragment graphViewFragment = this.target;
        if (graphViewFragment != null) {
            this.target = null;
            graphViewFragment.tvDailyOrWeekly = null;
            graphViewFragment.dateBarParentLayout = null;
            graphViewFragment.dateTitleLayout = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
