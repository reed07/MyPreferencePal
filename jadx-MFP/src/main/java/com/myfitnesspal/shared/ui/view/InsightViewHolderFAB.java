package com.myfitnesspal.shared.ui.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.squareup.otto.Bus;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class InsightViewHolderFAB extends InsightViewBinder {
    public InsightViewHolderFAB(View view, Lazy<FoodService> lazy, Bus bus, FoodEntry foodEntry) {
        super(view, lazy, bus, foodEntry);
    }

    /* access modifiers changed from: protected */
    public void update() {
        Type type;
        TextView textView = (TextView) this.view.findViewById(R.id.insightTitle);
        final View findViewById = this.view.findViewById(R.id.insight_container);
        ImageButton imageButton = (ImageButton) this.view.findViewById(R.id.positive);
        ImageButton imageButton2 = (ImageButton) this.view.findViewById(R.id.negative);
        boolean z = true;
        ViewUtils.setVisible(false, findViewById);
        if (this.foodAnalyzerData.isInsight() && this.foodAnalyzerData.getFoodInsight() != null && Strings.notEmpty(this.foodAnalyzerData.getFoodInsight().getDescription())) {
            ViewUtils.setVisible(false, imageButton);
            ViewUtils.setVisible(false, imageButton2);
            if (this.foodAnalyzerData.getFoodInsight().isPositive()) {
                type = Type.Positive;
            } else {
                type = Type.Negative;
            }
            textView.setText(this.foodAnalyzerData.getFoodInsight().getDescription());
        } else if (!this.foodAnalyzerData.isQuestion() || this.foodAnalyzerData.getFoodQuestion() == null || !Strings.notEmpty(this.foodAnalyzerData.getFoodQuestion().getQuestion())) {
            type = null;
            z = false;
        } else {
            ViewUtils.setVisible(true, imageButton);
            ViewUtils.setVisible(true, imageButton2);
            textView.setText(this.foodAnalyzerData.getFoodQuestion().getQuestion());
            Type type2 = Type.Question;
            imageButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    InsightViewHolderFAB.this.sendQuestionAnswer(true, findViewById);
                }
            });
            imageButton2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    InsightViewHolderFAB.this.sendQuestionAnswer(false, findViewById);
                }
            });
            type = type2;
        }
        if (type != null) {
            setTheming(findViewById, textView, type);
        }
        if (z) {
            animateInsightIn(findViewById);
        }
    }

    /* access modifiers changed from: protected */
    public void setTheming(View view, TextView textView, Type type) {
        int i;
        switch (type) {
            case Positive:
                i = R.color.dark_green;
                break;
            case Negative:
                i = R.color.dark_orange;
                break;
            case Question:
                i = R.color.black_text;
                break;
            default:
                i = 0;
                break;
        }
        textView.setTextColor(textView.getResources().getColor(i));
    }
}
