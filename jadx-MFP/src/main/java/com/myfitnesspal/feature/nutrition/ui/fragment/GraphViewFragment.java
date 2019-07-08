package com.myfitnesspal.feature.nutrition.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.NestedScrollView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.service.ChartRendererFactory;
import com.myfitnesspal.feature.nutrition.service.CoreChartRenderer;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphPreferenceService;
import com.myfitnesspal.feature.nutrition.ui.view.MiniFoodList;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpDateRestrictedFragment;
import com.myfitnesspal.shared.ui.fragment.MfpDateRestrictedFragment.ContentPagerAdapter;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.DateUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

public abstract class GraphViewFragment extends MfpDateRestrictedFragment {
    protected View btnChangeDate;
    protected View btnDay;
    protected View btnWeek;
    @Inject
    Lazy<ChartRendererFactory> chartRendererFactoryLazy;
    protected View chooserView;
    protected ViewGroup container;
    public CoreChartRenderer coreChartRenderer;
    @BindView(2131362272)
    LinearLayout dateBarParentLayout;
    @BindView(2131362931)
    LinearLayout dateTitleLayout;
    protected boolean isWeekly;
    protected View llChooserLayout;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    protected int nutrientIndex = -1;
    protected String nutrientName;
    @Inject
    Lazy<NutritionGraphAnalyticsHelper> nutritionGraphAnalyticsHelper;
    @Inject
    Lazy<NutritionGraphPreferenceService> nutritionGraphServiceLazy;
    private OnClickListener onDateClickListener = new OnClickListener() {
        public void onClick(View view) {
            GraphViewFragment.this.openChooserLayout();
        }
    };
    private OnClickListener onDateLayoutClickListener = new OnClickListener() {
        public void onClick(View view) {
            GraphViewFragment.this.showDateBar();
        }
    };
    private OnClickListener onDateTypeItemClickListener = new OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.btnChangeDate) {
                DatePickerFragment.newInstance((Calendar) GraphViewFragment.this.dateList.get(GraphViewFragment.this.contentPager.getCurrentItem())).show(GraphViewFragment.this.getActivity().getSupportFragmentManager(), DatePickerFragment.class.getName());
                GraphViewFragment.this.switchDateBarView(true);
            } else if (id == R.id.btnDay) {
                GraphViewFragment.this.toggleDayWeek(false);
            } else if (id == R.id.btnWeek) {
                GraphViewFragment.this.toggleDayWeek(true);
            }
        }
    };
    @Inject
    Lazy<PremiumService> premiumServiceLazy;
    @BindView(2131363905)
    TextView tvDailyOrWeekly;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    /* access modifiers changed from: protected */
    public abstract void addMiniFoodListView(ViewGroup viewGroup, Date date);

    /* access modifiers changed from: protected */
    public abstract boolean addScrollViewAsParent();

    public abstract String getAnalyticsEventId();

    /* access modifiers changed from: protected */
    public abstract String getAttribute();

    /* access modifiers changed from: protected */
    public void onSyncFinished(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
    }

    /* access modifiers changed from: protected */
    public boolean shouldReportAnalyticsEvent() {
        return true;
    }

    public static GraphViewFragment newInstance(String str, NutrientEntry nutrientEntry, String str2, int i) {
        return newInstance(str, 1, nutrientEntry, str2, i);
    }

    public static GraphViewFragment newInstance(String str, int i) {
        return newInstance(str, i, null, null, -1);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.myfitnesspal.feature.nutrition.ui.fragment.GraphViewFragment newInstance(java.lang.String r3, int r4, com.myfitnesspal.feature.nutrition.model.NutrientEntry r5, java.lang.String r6, int r7) {
        /*
            int r0 = r3.hashCode()
            switch(r0) {
                case -1997878713: goto L_0x0030;
                case -104321242: goto L_0x0026;
                case 875663799: goto L_0x001c;
                case 1737874764: goto L_0x0012;
                case 1933126767: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x003a
        L_0x0008:
            java.lang.String r0 = "SingleNutrient"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x003a
            r0 = 3
            goto L_0x003b
        L_0x0012:
            java.lang.String r0 = "Nutrients"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x003a
            r0 = 1
            goto L_0x003b
        L_0x001c:
            java.lang.String r0 = "FoodLists"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x003a
            r0 = 4
            goto L_0x003b
        L_0x0026:
            java.lang.String r0 = "Calories"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x003a
            r0 = 0
            goto L_0x003b
        L_0x0030:
            java.lang.String r0 = "Macros"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x003a
            r0 = 2
            goto L_0x003b
        L_0x003a:
            r0 = -1
        L_0x003b:
            switch(r0) {
                case 0: goto L_0x0058;
                case 1: goto L_0x0052;
                case 2: goto L_0x004c;
                case 3: goto L_0x0046;
                case 4: goto L_0x0040;
                default: goto L_0x003e;
            }
        L_0x003e:
            r0 = 0
            goto L_0x005d
        L_0x0040:
            com.myfitnesspal.feature.nutrition.ui.fragment.FoodListsFragment r0 = new com.myfitnesspal.feature.nutrition.ui.fragment.FoodListsFragment
            r0.<init>()
            goto L_0x005d
        L_0x0046:
            com.myfitnesspal.feature.nutrition.ui.fragment.SingleNutrientGraphFragment r0 = new com.myfitnesspal.feature.nutrition.ui.fragment.SingleNutrientGraphFragment
            r0.<init>()
            goto L_0x005d
        L_0x004c:
            com.myfitnesspal.feature.nutrition.ui.fragment.GraphsMacroViewFragment r0 = new com.myfitnesspal.feature.nutrition.ui.fragment.GraphsMacroViewFragment
            r0.<init>()
            goto L_0x005d
        L_0x0052:
            com.myfitnesspal.feature.nutrition.ui.fragment.GraphsNutrientViewFragment r0 = new com.myfitnesspal.feature.nutrition.ui.fragment.GraphsNutrientViewFragment
            r0.<init>()
            goto L_0x005d
        L_0x0058:
            com.myfitnesspal.feature.nutrition.ui.fragment.GraphsCalorieViewFragment r0 = new com.myfitnesspal.feature.nutrition.ui.fragment.GraphsCalorieViewFragment
            r0.<init>()
        L_0x005d:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r2 = "chart_type"
            r1.putString(r2, r3)
            java.lang.String r3 = "chart_sub_type"
            r1.putInt(r3, r4)
            java.lang.String r3 = "nutrient"
            r1.putParcelable(r3, r5)
            java.lang.String r3 = "event_id"
            r1.putString(r3, r6)
            java.lang.String r3 = "macro_index"
            r1.putInt(r3, r7)
            r0.setArguments(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.nutrition.ui.fragment.GraphViewFragment.newInstance(java.lang.String, int, com.myfitnesspal.feature.nutrition.model.NutrientEntry, java.lang.String, int):com.myfitnesspal.feature.nutrition.ui.fragment.GraphViewFragment");
    }

    public void setCoreChartRenderer(String str, int i) {
        this.coreChartRenderer = ((ChartRendererFactory) this.chartRendererFactoryLazy.get()).getChartRenderer(str);
        this.coreChartRenderer.setGraphSubType(i);
    }

    public boolean isWeekly() {
        return this.isWeekly;
    }

    /* access modifiers changed from: protected */
    public void setWeekly(boolean z) {
        this.isWeekly = z;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setCoreChartRenderer(getArguments().getString(Extras.CHART_TYPE), getArguments().getInt(Extras.CHART_SUB_TYPE));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.container = viewGroup;
        return layoutInflater.inflate(R.layout.graphs_view_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        setCoreChartRenderer(BundleUtils.getString(arguments, Extras.CHART_TYPE, Types.CALORIES), BundleUtils.getInt(arguments, Extras.CHART_SUB_TYPE, 1));
        if (this.chooserView == null) {
            this.chooserView = LayoutInflater.from(getActivity()).inflate(R.layout.day_week_changedate_options, this.container);
            this.llChooserLayout = ViewUtils.findById(this.chooserView, R.id.llChooserLayout);
            this.btnDay = ViewUtils.findById(this.chooserView, R.id.btnDay);
            this.btnWeek = ViewUtils.findById(this.chooserView, R.id.btnWeek);
            this.btnChangeDate = ViewUtils.findById(this.chooserView, R.id.btnChangeDate);
        }
        setDateButtonListeners();
    }

    private void refresh() {
        if (isAdded()) {
            this.coreChartRenderer.setActivityContext(getActivity());
            onContentPagerCreated();
        }
    }

    public void onStart() {
        super.onStart();
        toggleDayWeek(((NutritionGraphPreferenceService) this.nutritionGraphServiceLazy.get()).isWeekly(), false);
        refresh();
    }

    public boolean isDateBarHidden() {
        return this.llChooserLayout.getVisibility() == 0;
    }

    public void onBackPressed() {
        showDateBar();
    }

    public void onPause() {
        super.onPause();
        ((NutritionGraphPreferenceService) this.nutritionGraphServiceLazy.get()).setIsWeekly(this.isWeekly);
    }

    public void onDestroy() {
        super.onDestroy();
        reportAnalyticsEvent();
    }

    private void reportAnalyticsEvent() {
        if (shouldReportAnalyticsEvent()) {
            if (!isAdded() || getActivity().isFinishing()) {
                ((NutritionGraphAnalyticsHelper) this.nutritionGraphAnalyticsHelper.get()).reportEvents();
            }
        }
    }

    public void onContentPagerCreated() {
        if (this.contentPagerAdapter == null) {
            this.contentPagerAdapter = new ContentPagerAdapter();
            this.contentPager.setAdapter(this.contentPagerAdapter);
            this.contentPager.addOnPageChangeListener(new OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    GraphViewFragment.this.mCurrentSelection = i;
                    GraphViewFragment.this.incrementAnalyticAttribute();
                }
            });
            setContentPageToDefaultOrPersisted();
            return;
        }
        this.contentPagerAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void incrementAnalyticAttribute() {
        ((NutritionGraphAnalyticsHelper) this.nutritionGraphAnalyticsHelper.get()).incrementAttribute(getAnalyticsEventId(), getAttribute());
    }

    private void setDateButtonListeners() {
        this.dateTitleLayout.setOnClickListener(this.onDateClickListener);
        this.date.setOnClickListener(this.onDateClickListener);
        this.btnDay.setOnClickListener(this.onDateTypeItemClickListener);
        this.btnWeek.setOnClickListener(this.onDateTypeItemClickListener);
        this.btnChangeDate.setOnClickListener(this.onDateTypeItemClickListener);
        this.llChooserLayout.setOnClickListener(this.onDateLayoutClickListener);
    }

    /* access modifiers changed from: private */
    public void toggleDayWeek(boolean z) {
        toggleDayWeek(z, true);
    }

    private void toggleDayWeek(boolean z, boolean z2) {
        if (isWeekly() == z) {
            showDateBar();
            return;
        }
        setWeekly(z);
        toggleChooserLayoutButtonColors();
        switchDateBarView(z2);
    }

    /* access modifiers changed from: private */
    public void openChooserLayout() {
        this.dateBarParentLayout.setVisibility(4);
        this.llChooserLayout.setVisibility(0);
        this.llChooserLayout.bringToFront();
        this.llChooserLayout.invalidate();
        toggleChooserLayoutButtonColors();
    }

    private void toggleChooserLayoutButtonColors() {
        this.btnWeek.setSelected(isWeekly());
        this.btnDay.setSelected(!isWeekly());
    }

    /* access modifiers changed from: private */
    public void switchDateBarView(boolean z) {
        showDateBar();
        if (z) {
            if (this.mCurrentSelection == 5) {
                incrementAnalyticAttribute();
            }
            refreshContentToDate(getCurrentVisibleDate());
            return;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(getSession().getUser().getActiveDate());
        refreshDatesList(instance);
    }

    /* access modifiers changed from: private */
    public void showDateBar() {
        this.dateBarParentLayout.setVisibility(0);
        this.llChooserLayout.setVisibility(4);
    }

    /* access modifiers changed from: protected */
    public void loadDate(Calendar calendar) {
        String str;
        String str2;
        String str3;
        if (!isWeekly()) {
            this.tvDailyOrWeekly.setText(R.string.day_view);
            this.date.setTextColor(DateUtil.getMainDateColorGreyTheme(getActivity(), calendar));
            this.date.setText(DateUtil.getMainDateSimpleFormat(getActivity(), calendar));
            DateTimeUtils.getNumberOfDaysSince(calendar.getTime());
            return;
        }
        this.tvDailyOrWeekly.setText(R.string.week_view);
        this.date.setTextColor(getResources().getColor(DateTimeUtils.isDateWeekly(calendar, DateTimeUtils.mondayOnOrPriorTo(getSession().getUser().getActiveDate())) ? R.color.date_bar_text_black : R.color.other_date));
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        instance.setTime(DateTimeUtils.startDayOnOrPriorTo(calendar.getTime(), ((LocalSettingsService) this.localSettingsService.get()).getWeeklyStartDay()));
        DateTimeUtils.getNumberOfDaysSince(instance.getTime());
        int i2 = instance.get(2);
        Calendar calendar2 = (Calendar) instance.clone();
        calendar2.add(5, 6);
        boolean z = calendar2.get(1) == i;
        if (calendar2.get(2) == i2) {
            str2 = "MMMM d";
            str3 = z ? "d" : "d, yyyy";
            str = "%s-%s";
        } else {
            str2 = "MMM d";
            str3 = z ? "MMM d" : "MMM d, yyyy";
            str = "%s - %s";
        }
        this.date.setText(String.format(str, new Object[]{String.valueOf(DateFormat.format(str2, instance)), Strings.toString(DateFormat.format(str3, calendar2))}));
    }

    /* access modifiers changed from: protected */
    public View renderPageView(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.custom_chart_container, viewGroup, false);
        Calendar calendar = (Calendar) this.dateList.get(i);
        loadChartIntoContainer((ViewGroup) ViewUtils.findById(inflate, R.id.chart_container), calendar);
        if (((PremiumService) this.premiumServiceLazy.get()).isFeatureAvailable(PremiumFeature.FoodLists)) {
            addMiniFoodListView((ViewGroup) ViewUtils.findById(inflate, R.id.parent), calendar.getTime());
        }
        if (!addScrollViewAsParent()) {
            return inflate;
        }
        NestedScrollView nestedScrollView = new NestedScrollView(getActivity());
        nestedScrollView.setFillViewport(true);
        nestedScrollView.addView(inflate);
        return nestedScrollView;
    }

    private void loadChartIntoContainer(ViewGroup viewGroup, Calendar calendar) {
        Date offsetDate = DateTimeUtils.offsetDate(calendar.getTime(), 0);
        if (isWeekly()) {
            this.coreChartRenderer.addWeeklyChart(viewGroup, offsetDate, this.nutrientName, this.nutrientIndex, -1);
            return;
        }
        this.coreChartRenderer.addDailyChart(viewGroup, offsetDate, this.nutrientName, this.nutrientIndex, -1);
    }

    /* access modifiers changed from: protected */
    public void addMiniFoodListView(ViewGroup viewGroup, Date date, int i) {
        addMiniFoodListView(viewGroup, date, i, -1, true);
    }

    /* access modifiers changed from: protected */
    public void addMiniFoodListView(ViewGroup viewGroup, Date date, int i, int i2, boolean z) {
        new MiniFoodList(new NutrientEntry(i, NutritionalValues.simplifiedLabelForNutrientIndex(getActivity(), i, ((UserEnergyService) this.userEnergyService.get()).isCalories())), date, z, isWeekly()).addView(getActivity().getApplicationContext(), i2, viewGroup);
    }

    public int getGraphSubType() {
        return this.coreChartRenderer.getGraphSubType();
    }

    public void setGraphSubType(int i) {
        this.coreChartRenderer.setGraphSubType(i);
    }
}
