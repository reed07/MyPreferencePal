package com.myfitnesspal.feature.dashboard.ui.view;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class TextNutrientDashboard_ViewBinding extends NutrientDashboardBase_ViewBinding {
    private TextNutrientDashboard target;

    @UiThread
    public TextNutrientDashboard_ViewBinding(TextNutrientDashboard textNutrientDashboard, View view) {
        super(textNutrientDashboard, view);
        this.target = textNutrientDashboard;
        textNutrientDashboard.remainingLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.remaining_label, "field 'remainingLabel'", TextView.class);
        textNutrientDashboard.remaining = (TextView) Utils.findRequiredViewAsType(view, R.id.remaining, "field 'remaining'", TextView.class);
        textNutrientDashboard.remainingDiary = (TextView) Utils.findOptionalViewAsType(view, R.id.remaining_diary, "field 'remainingDiary'", TextView.class);
        textNutrientDashboard.goal = (TextView) Utils.findRequiredViewAsType(view, R.id.goal, "field 'goal'", TextView.class);
        textNutrientDashboard.food = (TextView) Utils.findRequiredViewAsType(view, R.id.food, "field 'food'", TextView.class);
        textNutrientDashboard.exercise = (TextView) Utils.findRequiredViewAsType(view, R.id.exercise, "field 'exercise'", TextView.class);
        textNutrientDashboard.exerciseTextViews = view.findViewById(R.id.exerciseTextViews);
        textNutrientDashboard.label1 = (TextView) Utils.findOptionalViewAsType(view, R.id.tvLabel1, "field 'label1'", TextView.class);
        textNutrientDashboard.label2 = (TextView) Utils.findOptionalViewAsType(view, R.id.tvLabel2, "field 'label2'", TextView.class);
        textNutrientDashboard.label3 = (TextView) Utils.findOptionalViewAsType(view, R.id.tvLabel3, "field 'label3'", TextView.class);
        textNutrientDashboard.foodPlusMinus = view.findViewById(R.id.tvCalc1);
        textNutrientDashboard.equalSign = view.findViewById(R.id.tvCalc2);
        textNutrientDashboard.exercisePlusMinus = view.findViewById(R.id.exercisePlusMinus);
        textNutrientDashboard.f31net = (TextView) Utils.findOptionalViewAsType(view, R.id.f30net, "field 'net'", TextView.class);
    }

    public void unbind() {
        TextNutrientDashboard textNutrientDashboard = this.target;
        if (textNutrientDashboard != null) {
            this.target = null;
            textNutrientDashboard.remainingLabel = null;
            textNutrientDashboard.remaining = null;
            textNutrientDashboard.remainingDiary = null;
            textNutrientDashboard.goal = null;
            textNutrientDashboard.food = null;
            textNutrientDashboard.exercise = null;
            textNutrientDashboard.exerciseTextViews = null;
            textNutrientDashboard.label1 = null;
            textNutrientDashboard.label2 = null;
            textNutrientDashboard.label3 = null;
            textNutrientDashboard.foodPlusMinus = null;
            textNutrientDashboard.equalSign = null;
            textNutrientDashboard.exercisePlusMinus = null;
            textNutrientDashboard.f31net = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
