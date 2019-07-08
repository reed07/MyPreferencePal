package com.myfitnesspal.feature.home.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.MealSelectedEvent;
import com.myfitnesspal.feature.walkthrough.event.SkipLoggingWalkthroughEvent;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.HideAnimationType;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.WalkthroughType;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItemWithKey;
import com.squareup.otto.Bus;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class WalkthroughHelper {
    private final Activity activity;
    private final Lazy<AnalyticsService> analyticsService;
    private View currentWalkthroughView;
    /* access modifiers changed from: private */
    public final Lazy<Bus> messageBus;
    private Function2<View, WalkthroughType> skipWalkthroughCallback = new Function2<View, WalkthroughType>() {
        public void execute(View view, WalkthroughType walkthroughType) throws RuntimeException {
            ((Bus) WalkthroughHelper.this.messageBus.get()).post(new SkipLoggingWalkthroughEvent(AnonymousClass2.$SwitchMap$com$myfitnesspal$feature$walkthrough$util$WalkthroughUtilImpl$WalkthroughType[walkthroughType.ordinal()] != 1 ? null : Events.SPOTLIGHT_MEAL_SELECT_SKIP));
        }
    };
    private final Lazy<WalkthroughUtil> walkthroughUtil;

    /* renamed from: com.myfitnesspal.feature.home.util.WalkthroughHelper$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$walkthrough$util$WalkthroughUtilImpl$WalkthroughType = new int[WalkthroughType.values().length];

        static {
            try {
                $SwitchMap$com$myfitnesspal$feature$walkthrough$util$WalkthroughUtilImpl$WalkthroughType[WalkthroughType.PickMeal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public WalkthroughHelper(Activity activity2, Lazy<WalkthroughUtil> lazy, Lazy<AnalyticsService> lazy2, Lazy<Bus> lazy3) {
        this.activity = activity2;
        this.walkthroughUtil = lazy;
        this.analyticsService = lazy2;
        this.messageBus = lazy3;
    }

    public void showWalkthrough() {
        if (!ViewUtils.isVisible(this.currentWalkthroughView)) {
            showWalkthroughStep3();
        }
    }

    private void showWalkthroughStep3() {
        this.currentWalkthroughView = addWalkthroughViewAndReportEvent(R.layout.new_walkthrough_meal_picker, Events.SPOTLIGHT_MEAL_SELECT_SEE);
        ((WalkthroughUtil) this.walkthroughUtil.get()).showNewMealPickerWalkthrough(this.currentWalkthroughView, this.skipWalkthroughCallback, new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                WalkthroughHelper.lambda$showWalkthroughStep3$0(WalkthroughHelper.this, adapterView, view, i, j);
            }
        });
    }

    public static /* synthetic */ void lambda$showWalkthroughStep3$0(WalkthroughHelper walkthroughHelper, AdapterView adapterView, View view, int i, long j) {
        ((Bus) walkthroughHelper.messageBus.get()).post(new MealSelectedEvent(((DialogListTextItemWithKey) adapterView.getItemAtPosition(i)).getKey()));
        walkthroughHelper.hideWalkthrough(walkthroughHelper.currentWalkthroughView);
    }

    private View addWalkthroughViewAndReportEvent(int i, String str) {
        if (str != null) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(str);
        }
        ViewGroup viewGroup = (ViewGroup) this.activity.findViewById(R.id.content_parent);
        View inflate = LayoutInflater.from(this.activity).inflate(i, viewGroup, false);
        viewGroup.addView(inflate);
        return inflate;
    }

    private void hideWalkthrough(View view) {
        hideWalkthrough(view, null);
    }

    private void hideWalkthrough(View view, Function1<View> function1) {
        this.currentWalkthroughView = null;
        ((WalkthroughUtil) this.walkthroughUtil.get()).hide(view, HideAnimationType.FadeOut, function1);
    }
}
