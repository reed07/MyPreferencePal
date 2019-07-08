package com.myfitnesspal.feature.settings.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.DateTime;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import java.text.DateFormatSymbols;
import javax.inject.Inject;

public class WeeklyNutritionSettingsListFragment extends MfpFragment {
    private static final String ANALYTICS_SCREEN_TAG = "SCREEN_NutritionSettings";
    private static final String[] ANALYTICS_VALUE_DAY_OF_WEEK = {"7_days_ago", DateTime.SUNDAY, DateTime.MONDAY, DateTime.TUESDAY, DateTime.WEDNESDAY, DateTime.THURSDAY, DateTime.FRIDAY, DateTime.SATURDAY};
    private static final String ANALYTICS_VALUE_UNKNOWN_DAY = "unknown";
    @Inject
    Lazy<AnalyticsService> analytics;
    ListView dayList;
    @Inject
    LocalSettingsService localSettingsService;
    String[] settingsItems;

    public class WeeklyNutritionSettingsListAdapter extends BaseAdapter {
        String[] settings;

        public long getItemId(int i) {
            return (long) i;
        }

        public WeeklyNutritionSettingsListAdapter(String[] strArr) {
            this.settings = strArr;
        }

        public int getCount() {
            return this.settings.length;
        }

        public Object getItem(int i) {
            return this.settings[i];
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            boolean z = false;
            if (view == null) {
                view = LayoutInflater.from(WeeklyNutritionSettingsListFragment.this.getActivity()).inflate(R.layout.settings_item, viewGroup, false);
            }
            String str = this.settings[i];
            TextView textView = (TextView) view.findViewById(R.id.setting_name);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.enabled);
            checkBox.setClickable(false);
            TextView textView2 = (TextView) view.findViewById(R.id.selected_setting);
            textView.setText(str);
            checkBox.setVisibility(0);
            textView2.setVisibility(8);
            if (WeeklyNutritionSettingsListFragment.this.localSettingsService.getWeeklyStartDay() == i) {
                z = true;
            }
            checkBox.setChecked(z);
            return view;
        }
    }

    public static String getAnalyticsValueForDay(int i) {
        String[] strArr = ANALYTICS_VALUE_DAY_OF_WEEK;
        return strArr.length >= i ? strArr[i] : "unknown";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        if (bundle == null) {
            ((AnalyticsService) this.analytics.get()).reportEvent(ANALYTICS_SCREEN_TAG);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.weekly_nutrition_settings_fragment, viewGroup, false);
        String[] weekdays = new DateFormatSymbols().getWeekdays();
        this.settingsItems = new String[]{getString(R.string.seven_days_ago), weekdays[1], weekdays[2], weekdays[3], weekdays[4], weekdays[5], weekdays[6], weekdays[7]};
        WeeklyNutritionSettingsListAdapter weeklyNutritionSettingsListAdapter = new WeeklyNutritionSettingsListAdapter(this.settingsItems);
        this.dayList = (ListView) inflate.findViewById(R.id.day_list);
        this.dayList.setAdapter(weeklyNutritionSettingsListAdapter);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.dayList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                WeeklyNutritionSettingsListFragment.this.setDay((CheckBox) view.findViewById(R.id.enabled), i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setDay(CheckBox checkBox, int i) {
        checkBox.setChecked(!checkBox.isChecked());
        if (checkBox.isChecked()) {
            String analyticsValueForDay = getAnalyticsValueForDay(i);
            ((AnalyticsService) this.analytics.get()).reportEvent(Events.WEEK_STARTS_ON_CHANGE, MapUtil.createMap(Attributes.DAY_OF_THE_WEEK, analyticsValueForDay));
            this.localSettingsService.setWeeklyStartDay(i);
        }
        ((WeeklyNutritionSettingsListAdapter) this.dayList.getAdapter()).notifyDataSetChanged();
    }
}
