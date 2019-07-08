package com.myfitnesspal.feature.search.ui.activity;

import android.app.Activity;
import android.view.View;
import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlight;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightSequence;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"createHighlightSequence", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightSequence;", "firstStepView", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$showExistingUserWalkthrough$1 extends Lambda implements Function1<View, FeatureHighlightSequence> {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$showExistingUserWalkthrough$1(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
        super(1);
    }

    @NotNull
    public final FeatureHighlightSequence invoke(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "firstStepView");
        Activity activity = this.this$0.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, AbstractEvent.ACTIVITY);
        LinkedList linkedList = new LinkedList();
        String string = this.this$0.getString(R.string.food_search_walkthrough_step_1_content);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.food_…lkthrough_step_1_content)");
        String string2 = this.this$0.getString(R.string.food_search_walkthrough_step_1_title);
        String string3 = this.this$0.getString(R.string.next);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.next)");
        if (string3 != null) {
            String upperCase = string3.toUpperCase();
            String str = upperCase;
            Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
            FeatureHighlight featureHighlight = r2;
            Activity activity2 = activity;
            LinkedList linkedList2 = linkedList;
            FeatureHighlight featureHighlight2 = new FeatureHighlight(view, string, string2, 0, 0, 0, 0, str, 0, null, 0, 0, null, null, false, null, 48984, null);
            linkedList2.add(featureHighlight);
            View findViewById = this.this$0.findViewById(R.id.searchTitle);
            if (findViewById != null) {
                String string4 = this.this$0.getString(R.string.food_search_walkthrough_step_2_content);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.food_…lkthrough_step_2_content)");
                String string5 = this.this$0.getString(R.string.food_search_walkthrough_step_2_title);
                String string6 = this.this$0.getString(R.string.next);
                Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.next)");
                if (string6 != null) {
                    String upperCase2 = string6.toUpperCase();
                    String str2 = upperCase2;
                    Intrinsics.checkExpressionValueIsNotNull(upperCase2, "(this as java.lang.String).toUpperCase()");
                    FeatureHighlight featureHighlight3 = new FeatureHighlight(findViewById, string4, string5, 0, 0, 0, 0, str2, 0, null, 0, 0, null, null, false, null, 48984, null);
                    linkedList2.add(featureHighlight3);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            if (this.this$0.findViewById(R.id.sortOrderButton) != null) {
                View findViewById2 = this.this$0.findViewById(R.id.sortOrderButton);
                Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.sortOrderButton)");
                String string7 = this.this$0.getString(R.string.food_search_walkthrough_step_3_content);
                Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.food_…lkthrough_step_3_content)");
                String string8 = this.this$0.getString(R.string.food_search_walkthrough_step_3_title);
                String string9 = this.this$0.getString(R.string.done);
                Intrinsics.checkExpressionValueIsNotNull(string9, "getString(R.string.done)");
                if (string9 != null) {
                    String upperCase3 = string9.toUpperCase();
                    String str3 = upperCase3;
                    Intrinsics.checkExpressionValueIsNotNull(upperCase3, "(this as java.lang.String).toUpperCase()");
                    FeatureHighlight featureHighlight4 = new FeatureHighlight(findViewById2, string7, string8, 0, 0, 0, 0, str3, 0, null, 0, 0, null, null, false, null, 48984, null);
                    linkedList2.add(featureHighlight4);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            FeatureHighlightSequence featureHighlightSequence = new FeatureHighlightSequence(activity2, linkedList2, true, new Function1<Integer, Unit>(this) {
                final /* synthetic */ FoodSearchActivityV2$showExistingUserWalkthrough$1 this$0;

                {
                    this.this$0 = r1;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    FoodSearchActivityV2.access$getViewModel$p(this.this$0.this$0).reportExistingUserWalkthroughStepShowed(i);
                }
            }, new Function0<Unit>(this) {
                final /* synthetic */ FoodSearchActivityV2$showExistingUserWalkthrough$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void invoke() {
                    FoodSearchActivityV2.access$getViewModel$p(this.this$0.this$0).reportExistingUserWalkthroughStepShowed(3);
                }
            }, null, 32, null);
            return featureHighlightSequence;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
