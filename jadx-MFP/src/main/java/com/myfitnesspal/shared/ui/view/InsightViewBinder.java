package com.myfitnesspal.shared.ui.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.InsightQuestionAnsweredEvent;
import com.myfitnesspal.shared.event.MfpEventBase;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.squareup.otto.Bus;
import com.uacf.core.util.Function0;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.HashSet;
import java.util.Set;

public abstract class InsightViewBinder {
    /* access modifiers changed from: private */
    public static Set<Long> foodEntriesWhoseInsightsHaveBeenAnimated = new HashSet();
    /* access modifiers changed from: private */
    public final Bus bus;
    protected FoodAnalyzerResponseData foodAnalyzerData;
    private final FoodEntry foodEntry;
    private final Lazy<FoodService> foodService;
    private boolean shouldAnimate;
    protected final View view;

    protected enum Type {
        Positive,
        Negative,
        Question
    }

    /* access modifiers changed from: protected */
    public abstract void update();

    public InsightViewBinder(View view2, Lazy<FoodService> lazy, Bus bus2, FoodEntry foodEntry2) {
        this.view = view2;
        this.foodService = lazy;
        this.bus = bus2;
        this.foodEntry = foodEntry2;
    }

    public FoodAnalyzerResponseData getFoodAnalyzerData() {
        return this.foodAnalyzerData;
    }

    public InsightViewBinder setFoodAnalyzerData(FoodAnalyzerResponseData foodAnalyzerResponseData, boolean z) {
        this.foodAnalyzerData = foodAnalyzerResponseData;
        this.shouldAnimate = z && !foodEntriesWhoseInsightsHaveBeenAnimated.contains(Long.valueOf(foodAnalyzerResponseData.getAssociatedFoodEntryLocalId()));
        update();
        return this;
    }

    public void hideInsightsContainer() {
        View findViewById = this.view.findViewById(R.id.insight_container);
        if (findViewById != null) {
            ViewUtils.setVisible(false, findViewById);
        }
    }

    /* access modifiers changed from: protected */
    public void sendQuestionAnswer(boolean z, final View view2) {
        final int id = this.foodAnalyzerData.getFoodQuestion().getId();
        final View view3 = this.view;
        ((FoodService) this.foodService.get()).postFoodQuestionAnswer(view3.getContext(), this.foodEntry, id, z, new Function0() {
            public void execute() {
                InsightViewBinder.this.bus.post(new AlertEvent(view3.getContext().getString(R.string.thanks_answered_question)).asToast());
                InsightViewBinder.this.animateInsightOut(view2, new InsightQuestionAnsweredEvent(id));
                InsightViewBinder.this.foodAnalyzerData.setFoodQuestion(null);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void animateInsightIn(View view2) {
        if (this.shouldAnimate) {
            ViewUtils.setVisible(true, view2);
            Animation loadAnimation = AnimationUtils.loadAnimation(view2.getContext(), R.anim.slide_in_left_100_short);
            loadAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    InsightViewBinder.foodEntriesWhoseInsightsHaveBeenAnimated.add(Long.valueOf(InsightViewBinder.this.foodAnalyzerData.getAssociatedFoodEntryLocalId()));
                }
            });
            view2.startAnimation(loadAnimation);
            return;
        }
        ViewUtils.setVisible(true, view2);
    }

    /* access modifiers changed from: private */
    public void animateInsightOut(final View view2, final MfpEventBase mfpEventBase) {
        if (!this.shouldAnimate) {
            ViewUtils.setVisible(false, view2);
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(view2.getContext(), R.anim.slide_out_left_100_short);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ViewUtils.setVisible(false, view2);
                if (mfpEventBase != null) {
                    InsightViewBinder.this.bus.post(mfpEventBase);
                }
            }
        });
        view2.startAnimation(loadAnimation);
    }
}
