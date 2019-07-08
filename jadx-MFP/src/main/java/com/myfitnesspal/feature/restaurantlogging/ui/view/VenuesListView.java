package com.myfitnesspal.feature.restaurantlogging.ui.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.restaurantlogging.model.MenusActivityBundleData;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.MenusActivity;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity;
import com.myfitnesspal.feature.restaurantlogging.ui.adapter.VenueAdapter;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class VenuesListView extends ListView implements OnItemClickListener {
    private String flowId;
    private Intent intent;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    @Inject
    Lazy<UserDistanceService> userDistanceService;
    private VenueAdapter venueAdapter;

    public VenuesListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public VenuesListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public VenuesListView(Context context) {
        super(context);
        init();
    }

    private void init() {
        MyFitnessPalApp.getInstance().component().inject(this);
        this.venueAdapter = new VenueAdapter(getContext(), ((UserDistanceService) this.userDistanceService.get()).getUserCurrentDistanceUnit());
        setAdapter(this.venueAdapter);
        setOnItemClickListener(this);
    }

    public void setIntentAndFlowId(Intent intent2, String str) {
        this.intent = intent2;
        this.flowId = str;
    }

    public void setItems(List<Venue> list) {
        this.venueAdapter.setItems(list);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MenusActivityBundleData menusActivityBundleData = new MenusActivityBundleData((Venue) this.venueAdapter.getItem(i), ExtrasUtils.getString(this.intent, Extras.MEAL_NAME), new Date(ExtrasUtils.getLong(this.intent, "date")), this.flowId, "restaurant_logging", ExtrasUtils.getBoolean(this.intent, VenuesActivity.EXTRA_IS_CURRENTLY_IN_MEAL_CREATION_FLOW));
        ((NavigationHelper) this.navigationHelper.get()).withContext(getContext()).withIntent(MenusActivity.newStartIntent(getContext(), menusActivityBundleData)).startActivity(RequestCodes.MENUS);
    }
}
