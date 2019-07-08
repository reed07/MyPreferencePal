package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import android.app.Activity;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras.ActionType;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.task.QueryFoodByIdTask;
import com.myfitnesspal.shared.task.QueryFoodByIdTask.CompletedEvent;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class AddFoodDeepLinkMixin extends AppIndexerMixinBase {
    private static final String ID_URL_PARAM = "id";
    private static final String VERSION_URL_PARAM = "version";
    @Inject
    Lazy<AnalyticsService> analytics;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<FoodMapper> foodMapper;
    @Inject
    Lazy<MfpFoodMapper> mfpFoodMapper;
    @Inject
    Lazy<SearchService> searchService;

    public AddFoodDeepLinkMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        component().inject(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getActivity().getIntent().getExtras();
        String string = BundleUtils.getString(extras, "id");
        String string2 = BundleUtils.getString(extras, "version");
        Ln.d("loading id=%s, version=%s", string, string2);
        if (Strings.isEmpty(string)) {
            onInvalidUrlParams(string, string2);
            return;
        }
        QueryFoodByIdTask queryFoodByIdTask = new QueryFoodByIdTask(((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter(), this.searchService, string, string2, this.mfpFoodMapper, this.foodMapper);
        queryFoodByIdTask.run(getRunner());
    }

    @Subscribe
    public void onFoodLookupFinished(CompletedEvent completedEvent) {
        Ln.e("registering: onFoodLookupFinished", new Object[0]);
        if (!completedEvent.isFrom(getRunner())) {
            return;
        }
        if (completedEvent.successful()) {
            proceedToAddFlow((MfpFood) completedEvent.getResult());
            return;
        }
        Ln.e(completedEvent.getFailure());
        onInvalidResponse();
    }

    private void onInvalidUrlParams(String str, String str2) {
        Ln.d("failed due to empty food id", new Object[0]);
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.APP_INDEXING_INVALID_PARAMS, MapUtil.createMap("id", str, "version", str2));
        onInvalidResponse((int) R.string.connection_failed);
    }

    private void onInvalidResponse() {
        Ln.d("failed due to api call error", new Object[0]);
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.APP_INDEXING_INVALID_RESPONSE);
        onInvalidResponse((int) R.string.connection_failed);
    }

    private void proceedToAddFlow(MfpFood mfpFood) {
        reportView(mfpFood.getId());
        Activity activity = getActivity();
        if (ConfigUtils.isNewAddFoodFlowOn((ConfigService) this.configService.get())) {
            FoodEditorExtras foodEditorExtras = new FoodEditorExtras();
            FoodEditorAnalyticsExtras foodEditorAnalyticsExtras = new FoodEditorAnalyticsExtras();
            foodEditorAnalyticsExtras.setResultsListPosition(0);
            foodEditorExtras.setActionType(ActionType.Create).setGetSuggestedServings(true).setStartedFromDeepLink(true).setDate(getComponentInterface().getSession().getUser().getActiveDate().getTime()).setSupportPairedFoods(true).setScreenTitle(activity.getString(R.string.addFood)).setFoodEditorAnalyticsExtras(foodEditorAnalyticsExtras);
            activity.startActivity(FoodEditorActivity.newDiaryFoodItemEditorIntent(activity, Diary.newStartIntent(activity), mfpFood, getComponentInterface().getSession().getUser().getActiveDate(), null, null, SearchSource.ONLINE, Extras.REFERRER_ADD_FOOD_DEEPLINK_MIXIN, false, foodEditorExtras));
        } else {
            getComponentInterface().getNavigationHelper().withIntent(AddFoodSummaryViewV2.newStartIntent(getActivity(), (AddFoodSummaryViewV2.Extras) ((AddFoodSummaryViewV2.Extras) ((AddFoodSummaryViewV2.Extras) ((AddFoodSummaryViewV2.Extras) new AddFoodSummaryViewV2.Extras().setFood(mfpFood).setTitle(activity.getString(R.string.addFood))).setDate(getComponentInterface().getSession().getUser().getActiveDate())).setPosition(0)).setSource("online_search"))).startActivity();
        }
        getActivity().finish();
    }
}
