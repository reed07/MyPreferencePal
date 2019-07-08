package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.fileexport.service.FileExportAnalyticsHelper;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport.ExportMode;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExportPreview;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.ui.dialog.ChooseImageDialogFragment;
import com.myfitnesspal.feature.images.ui.dialog.ChooseImageDialogFragment.OnImportDeviceSelectedListener;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.progress.constants.GalleryDataMode;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.constants.GraphPeriod;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.constants.ImportSource;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.event.GraphPeriodChangeEvent;
import com.myfitnesspal.feature.progress.event.MeasurementTypeChangeEvent;
import com.myfitnesspal.feature.progress.event.MeasurementValueChangeEvent;
import com.myfitnesspal.feature.progress.model.ProgressEntryItem;
import com.myfitnesspal.feature.progress.model.StepsProgressModel;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics.TapTarget;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.task.GetImageCountForMeasurementTypeTask;
import com.myfitnesspal.feature.progress.task.GetImageCountForMeasurementTypeTask.CompletedEvent;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosGalleryActivity.ToolbarCta;
import com.myfitnesspal.feature.progress.ui.adapter.ProgressEntryAdapter;
import com.myfitnesspal.feature.progress.ui.adapter.ProgressStepsAdapter;
import com.myfitnesspal.feature.progress.ui.chart.MeasurementLineChartRenderer;
import com.myfitnesspal.feature.progress.ui.chart.StepsBarChartRenderer;
import com.myfitnesspal.feature.progress.ui.dialog.GraphPeriodDialogFragment;
import com.myfitnesspal.feature.progress.ui.dialog.MeasurementTypeDialogFragment;
import com.myfitnesspal.feature.progress.ui.dialog.MeasurementValueDialogFragment;
import com.myfitnesspal.feature.progress.ui.dialog.ProgressEntryLongPressDialogFragment;
import com.myfitnesspal.feature.progress.ui.viewmodel.ProgressViewModel;
import com.myfitnesspal.feature.progress.ui.viewmodel.ProgressViewModel.Property;
import com.myfitnesspal.feature.progress.util.ProgressPhotosUtil;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.AdsHelper.DfpAdsListener;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.Measurements;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

public class ProgressActivity extends MfpActivity implements DfpAdsListener {
    private static final int ADD_MEASUREMENT_ITEM = 1000;
    private static final String EDIT_PROGRESS_ENTRY_DIALOG_TAG = "edit_progress_entry_dialog";
    private static final String GRAPH_PERIOD_DIALOG_TAG = "graph_period_dialog";
    private static final String IMPORT_DEVICE_DIALOG_TAG = "import_device_dialog";
    private static final String MEASUREMENT_TYPE_DIALOG_TAG = "measurement_type_dialog";
    private static final int MENU_FILE_EXPORT = 1001;
    @Inject
    Lazy<AdsAnalyticsHelper> adsAnalyticsHelper;
    @Inject
    Lazy<AdsSettings> adsSettings;
    private ViewGroup chartContainer;
    @Inject
    Lazy<ConfigService> configService;
    private View entriesHeader;
    @BindView(2131362482)
    ListView entryListView;
    @Inject
    Lazy<FileExportAnalyticsHelper> fileExportAnalyticsHelper;
    private GraphPeriod graphPeriod = GraphPeriod.ThreeMonths;
    private boolean hasUserClickedFileExportOnce;
    @Inject
    Lazy<ImageService> imageService;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @BindView(2131362044)
    View measurementButton;
    @Inject
    Lazy<MeasurementLineChartRenderer> measurementLineChartRenderer;
    /* access modifiers changed from: private */
    public String measurementType = Measurement.WEIGHT;
    @Inject
    Lazy<MeasurementsService> measurementsService;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    /* access modifiers changed from: private */
    public OnImportDeviceSelectedListener onImportDeviceSelectedListener = new OnImportDeviceSelectedListener() {
        public void onRemovePhoto() {
        }

        public void onImportDeviceSelected(ImportDevice importDevice) {
            ProgressActivity.this.getNavigationHelper().withNoAnimations().withIntent(AddWeightActivity.newStartIntent(ProgressActivity.this, ProgressEntryPoint.ProgressActivity, importDevice)).startActivity(25);
        }
    };
    private OnItemClickListener onProgressEntryClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (Measurements.isWeight(ProgressActivity.this.measurementType)) {
                ProgressEntryViewModel progressEntryViewModel = (ProgressEntryViewModel) ((ProgressEntryItem) adapterView.getAdapter().getItem(i));
                if (progressEntryViewModel.getImageAssociationLocalId() != -1) {
                    ProgressActivity.this.startGalleryAndFocusImage(progressEntryViewModel);
                    ((ProgressAnalytics) ProgressActivity.this.progressAnalytics.get()).reportProgressPhotoView();
                } else if (DateTimeUtils.isDateToday(progressEntryViewModel.getDate())) {
                    ChooseImageDialogFragment newInstance = ChooseImageDialogFragment.newInstance(progressEntryViewModel.getDate());
                    newInstance.setOnImportDeviceSelectedListener(ProgressActivity.this.onImportDeviceSelectedListener);
                    ProgressActivity.this.showDialogFragment(newInstance, ProgressActivity.IMPORT_DEVICE_DIALOG_TAG);
                } else {
                    ProgressActivity.this.startPhotoImportForEntry(progressEntryViewModel);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final OnDismissListener onProgressEntryDialogDismissListener = new OnDismissListener() {
        public void onDismiss(DialogInterface dialogInterface) {
            ProgressActivity.this.reloadViewModel();
        }
    };
    private final OnItemLongClickListener onProgressEntryLongClickListener = new OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            ProgressEntryLongPressDialogFragment newInstance = ProgressEntryLongPressDialogFragment.newInstance(ProgressActivity.this.measurementType, (ProgressEntryViewModel) ((ProgressEntryItem) adapterView.getAdapter().getItem(i)));
            newInstance.setOnDismissListener(ProgressActivity.this.onProgressEntryDialogDismissListener);
            ProgressActivity.this.showDialogFragment(newInstance, ProgressActivity.EDIT_PROGRESS_ENTRY_DIALOG_TAG);
            return true;
        }
    };
    @BindView(2131362046)
    View periodButton;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    @Inject
    Lazy<ProgressService> progressService;
    @Inject
    Lazy<Session> session;
    private View shareButton;
    @Inject
    Lazy<StepsBarChartRenderer> stepsBarChartRenderer;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    private ProgressViewModel viewModel;
    @Inject
    Lazy<UserWeightService> weightService;

    public String getAnalyticsScreenTag() {
        return Screens.PROGRESS;
    }

    public void onAdFailedToLoad() {
    }

    public void onAdLoaded() {
    }

    public static Intent newStartIntent(Context context, String str) {
        if (Strings.isEmpty(str)) {
            str = Measurement.WEIGHT;
        }
        return new Intent(context, ProgressActivity.class).putExtra(Extras.MEASUREMENT_TYPE_NAME, str);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.progress_activity);
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this, this.entryListView);
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        this.measurementType = ExtrasUtils.getString(getIntent(), Extras.MEASUREMENT_TYPE_NAME, Measurement.WEIGHT);
        this.measurementType = BundleUtils.getString(bundle, Extras.MEASUREMENT_TYPE_NAME, this.measurementType);
        this.graphPeriod = GraphPeriod.getGraphPeriod(((LocalSettingsService) this.localSettingsService.get()).getCurrentMeasurementFilterSelection());
        initViews();
        updateValidGraphPeriodsBasedOnMeasurementType();
        updateGraphPeriod(this.graphPeriod);
        initViewModel();
        if (ExtrasUtils.getBoolean(getIntent(), Extras.ADD_OR_EDIT_ENTRY_ON_START, false) && bundle == null) {
            showAddMeasurementDialog();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(Extras.MEASUREMENT_TYPE_NAME, this.measurementType);
    }

    private void updateGraphPeriod(GraphPeriod graphPeriod2) {
        this.graphPeriod = graphPeriod2;
        updateHeaderButtons();
        reportDateDeltaEvents();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        reloadViewModel();
        this.hasUserClickedFileExportOnce = ((LocalSettingsService) this.localSettingsService.get()).hasUserClickedFileExportOnce();
        invalidateOptionsMenu();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (((PremiumService) this.premiumService.get()).isFeatureAvailable(PremiumFeature.FileExport)) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.file_export).setIcon(this.hasUserClickedFileExportOnce ? R.drawable.ic_export_data : R.drawable.ic_export_data_alert), 2);
        }
        if (!Measurements.isSteps(this.measurementType)) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1000, 0, getString(R.string.record_todays, new Object[]{((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedMeasurementName(this.measurementType)})).setIcon(R.drawable.ic_add_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1000) {
            getAnalyticsService().reportEvent(Events.PROGRESS_RECORDBTN_CLICK);
            showAddMeasurementDialog();
            return true;
        } else if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            boolean showFileExportFeaturePreview = ConfigUtils.showFileExportFeaturePreview(getConfigService());
            boolean isFeatureSubscribed = ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FileExport);
            boolean showFileExportNewUpdatePreview = ConfigUtils.showFileExportNewUpdatePreview(getConfigService());
            if (!this.hasUserClickedFileExportOnce) {
                ((LocalSettingsService) this.localSettingsService.get()).setUserHasClickedFileExportOnce();
            }
            if (showFileExportFeaturePreview || showFileExportNewUpdatePreview) {
                ((FileExportAnalyticsHelper) this.fileExportAnalyticsHelper.get()).reportFileExportIconClicked("progress", isFeatureSubscribed);
                if (!isFeatureSubscribed) {
                    ((FileExportAnalyticsHelper) this.fileExportAnalyticsHelper.get()).reportFileExportCtaViewed("progress");
                }
            }
            if (isFeatureSubscribed || showFileExportFeaturePreview) {
                startActivity(FileExport.createIntentForFileExport(this, ExportMode.Normal));
            } else if (showFileExportNewUpdatePreview) {
                startActivity(FileExportPreview.newStartIntent(this));
            } else {
                getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) this, PremiumFeature.FileExport)).startActivity();
            }
            return true;
        }
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.STEPS_ENTRY_DATA) {
            redrawStepsData(this.viewModel.getStepsEntryModel());
        } else if (i == Property.NORMAL_ENTRY_DATA) {
            redrawNormalEntryData(this.viewModel.getNormalEntryModel());
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (EDIT_PROGRESS_ENTRY_DIALOG_TAG.equals(str)) {
            ((ProgressEntryLongPressDialogFragment) dialogFragment).setOnDismissListener(this.onProgressEntryDialogDismissListener);
            return true;
        } else if (!IMPORT_DEVICE_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        } else {
            ((ChooseImageDialogFragment) dialogFragment).setOnImportDeviceSelectedListener(this.onImportDeviceSelectedListener);
            return true;
        }
    }

    @Subscribe
    public void onSyncFinished(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
        if (uacfScheduleFinishedInfo.getScheduleGroup() == SyncType.Incremental) {
            reloadViewModel();
        }
    }

    @Subscribe
    public void onMeasurementChanged(MeasurementValueChangeEvent measurementValueChangeEvent) {
        LegacyAlertMixin legacyAlertMixin = (LegacyAlertMixin) mixin(LegacyAlertMixin.class);
        switch (measurementValueChangeEvent.getState()) {
            case Valid:
                ((MeasurementsService) this.measurementsService.get()).insertOrUpdateMeasurementForToday(this.measurementType, measurementValueChangeEvent.getValue());
                postEvent(new StartSyncEvent());
                reloadViewModel();
                return;
            case Invalid:
                legacyAlertMixin.showAlertDialogWithTitle(getString(R.string.alert), getString(R.string.invalidMeasurement), getString(R.string.dismiss));
                return;
            case OutOfRange:
                legacyAlertMixin.showAlertDialogWithTitle(getString(R.string.alert), getString(R.string.outOfRangeMeasurement, new Object[]{measurementValueChangeEvent.getMeasurementType(), Integer.valueOf(Measurement.MIN_MEASUREMENT_VALUE), Integer.valueOf(99999)}), getString(R.string.dismiss));
                return;
            case Zero:
                legacyAlertMixin.showAlertDialogWithTitle(getString(R.string.alert), getString(R.string.zeroMeasurement), getString(R.string.dismiss));
                return;
            default:
                return;
        }
    }

    @Subscribe
    public void onMeasurementTypeChanged(MeasurementTypeChangeEvent measurementTypeChangeEvent) {
        this.measurementType = measurementTypeChangeEvent.getMeasurementTypeItem().getDescription();
        updateValidGraphPeriodsBasedOnMeasurementType();
        updateHeaderButtons();
        reloadViewModel();
        reportDateDeltaEvents();
        invalidateOptionsMenu();
    }

    @Subscribe
    public void onGraphPeriodChanged(GraphPeriodChangeEvent graphPeriodChangeEvent) {
        updateGraphPeriod(graphPeriodChangeEvent.getGraphPeriod());
        ((LocalSettingsService) this.localSettingsService.get()).setCurrentMeasurementFilterSelection(graphPeriodChangeEvent.getGraphPeriod().getStringId());
        reloadViewModel();
    }

    @Subscribe
    public void onGetImageCountForGalleryModeCompleted(CompletedEvent completedEvent) {
        int intValue = completedEvent.getResult() == null ? 0 : ((Integer) completedEvent.getResult()).intValue();
        GalleryViewMode galleryViewMode = intValue > 1 ? GalleryViewMode.Split : GalleryViewMode.Single;
        GalleryDataMode galleryDataMode = intValue == 0 ? GalleryDataMode.Import : GalleryDataMode.View;
        ((ProgressAnalytics) this.progressAnalytics.get()).reportViewTapped(TapTarget.ProgressShare);
        getNavigationHelper().withIntent(ProgressPhotosGalleryActivity.newStartIntent(this, galleryViewMode, galleryDataMode, ToolbarCta.NextText)).startActivity(RequestCodes.PROGRESS_PHOTOS_GALLERY);
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getProgressScreenAdUnitValue();
    }

    private void resetGraph() {
        ((MeasurementLineChartRenderer) this.measurementLineChartRenderer.get()).reset();
        ((MeasurementLineChartRenderer) this.measurementLineChartRenderer.get()).setActivityContext(this);
        ((StepsBarChartRenderer) this.stepsBarChartRenderer.get()).reset();
        ((StepsBarChartRenderer) this.stepsBarChartRenderer.get()).setActivityContext(this);
        this.chartContainer.removeAllViews();
    }

    private void redrawNormalEntryData(List<ProgressEntryViewModel> list) {
        if (list != null) {
            reportStartingWeightGraphEvent(list);
            ViewUtils.setVisible(ProgressEntryAdapter.hasAtLeastOneVisibleEntry(list), this.entriesHeader);
            ViewUtils.setVisible(ProgressPhotosUtil.measurementTypeSupportsImageAssociations(this.measurementType), this.shareButton);
            resetGraph();
            ((MeasurementLineChartRenderer) this.measurementLineChartRenderer.get()).renderLineChart(this.graphPeriod.getDaysBack(getSession()), list, this.chartContainer, getSession());
            updateListViewAdapter(list);
            this.entryListView.setOnItemClickListener(this.onProgressEntryClickListener);
            this.entryListView.setOnItemLongClickListener(null);
            if (ProgressEntryLongPressDialogFragment.supportsMeasurementType(this.measurementType)) {
                this.entryListView.setOnItemLongClickListener(this.onProgressEntryLongClickListener);
            }
        }
    }

    private void reportStartingWeightGraphEvent(List<ProgressEntryViewModel> list) {
        if (this.graphPeriod == GraphPeriod.StartingWeight) {
            String startingWeightDate = ((UserWeightService) this.weightService.get()).getStartingWeightDate();
            boolean z = false;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ProgressEntryViewModel progressEntryViewModel = (ProgressEntryViewModel) it.next();
                if (Strings.notEmpty(startingWeightDate) && DateTimeUtils.isSameDay(progressEntryViewModel.getDate(), DateTimeUtils.parse("yyyy-MM-dd", startingWeightDate))) {
                    z = true;
                    break;
                }
            }
            ((ProgressAnalytics) this.progressAnalytics.get()).reportProgressScreenSinceStartingWeightGraph(z);
        }
    }

    private void redrawStepsData(StepsProgressModel stepsProgressModel) {
        if (stepsProgressModel != null) {
            ViewUtils.setVisible(stepsProgressModel.getCount() > 0, this.entriesHeader);
            resetGraph();
            ((StepsBarChartRenderer) this.stepsBarChartRenderer.get()).renderStepsBarChart(stepsProgressModel.getGraphViewData(), stepsProgressModel.getMax(), stepsProgressModel.getStepGoal(), this.chartContainer);
            updateListViewAdapter(stepsProgressModel);
            this.entryListView.setOnItemClickListener(null);
            this.entryListView.setOnItemLongClickListener(null);
        }
    }

    /* access modifiers changed from: private */
    public void reloadViewModel() {
        GraphPeriod graphPeriod2;
        ProgressViewModel progressViewModel = this.viewModel;
        String str = this.measurementType;
        if (Measurement.WEIGHT.equals(str)) {
            graphPeriod2 = GraphPeriod.AllTime;
        } else {
            graphPeriod2 = this.graphPeriod;
        }
        progressViewModel.load(str, graphPeriod2.getDaysBack(getSession()));
    }

    private void updateHeaderButtons() {
        updateHeaderButtonAndText(this.measurementButton, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedMeasurementName(this.measurementType), R.drawable.ic_progress_graph);
        updateHeaderButtonAndText(this.periodButton, getString(this.graphPeriod.getStringId()), R.drawable.ic_progress_calendar);
        ViewUtils.setVisible(Measurements.isWeight(this.measurementType), this.shareButton);
    }

    private void updateValidGraphPeriodsBasedOnMeasurementType() {
        if (!Strings.equals(this.measurementType, Measurement.WEIGHT) && this.graphPeriod.getStringId() == R.string.since_starting_weight) {
            this.graphPeriod = GraphPeriod.ThreeMonths;
            ((LocalSettingsService) this.localSettingsService.get()).setCurrentMeasurementFilterSelection(this.graphPeriod.getStringId());
        }
    }

    private void updateHeaderButtonAndText(View view, String str, int i) {
        TextView textView = (TextView) ViewUtils.findById(view, R.id.label);
        ImageView imageView = (ImageView) ViewUtils.findById(view, R.id.icon);
        textView.setText(str);
        imageView.setImageResource(i);
    }

    private void initViewModel() {
        this.viewModel = (ProgressViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (ProgressViewModel) setViewModel(new ProgressViewModel(getRunner()));
        }
        if (this.viewModel.isLoaded()) {
            rebindView();
        }
    }

    private void initListViewHeaderViews() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View inflate = layoutInflater.inflate(R.layout.progress_entries_graph, null, false);
        View inflate2 = layoutInflater.inflate(R.layout.progress_entries_header, null, false);
        this.entriesHeader = inflate2.findViewById(R.id.entries_header_layout);
        this.chartContainer = (ViewGroup) ViewUtils.findById(inflate, R.id.chart_container);
        this.shareButton = ViewUtils.findById(inflate2, R.id.share_button);
        this.shareButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new GetImageCountForMeasurementTypeTask(ProgressActivity.this.progressService, ProgressActivity.this.measurementType).run(ProgressActivity.this.getRunner());
            }
        });
        this.entryListView.addHeaderView(inflate, null, false);
        this.entryListView.addHeaderView(inflate2, null, false);
    }

    private void initViews() {
        updateHeaderButtons();
        initListViewHeaderViews();
        this.measurementButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProgressActivity.this.showSelectMeasurementTypeDialog();
            }
        });
        this.periodButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProgressActivity.this.showSelectGraphPeriodDialog();
            }
        });
    }

    private void showAddMeasurementDialog() {
        if (!Strings.isEmpty(this.measurementType)) {
            if (Measurements.isWeight(this.measurementType)) {
                getNavigationHelper().withNoAnimations().withIntent(AddWeightActivity.newStartIntent(this, ProgressEntryPoint.ProgressActivity, ImportDevice.None)).startActivity(25);
            } else {
                MeasurementValueDialogFragment.newInstance(this.measurementType, ((MeasurementsService) this.measurementsService.get()).getMostRecentMeasurementValueBeforeDate(Calendar.getInstance().getTime(), getSession().getUser().getLocalId(), ((MeasurementsService) this.measurementsService.get()).getMeasurementTypeIdFromMeasurementTypeName(this.measurementType))).show(getSupportFragmentManager(), Fragments.MEASUREMENT_DIALOG);
            }
        }
    }

    private void reportDateDeltaEvents() {
        getAnalyticsService().reportEvent(Events.PROGRESS_REPORT_VIEWED, MapUtil.createMap("user_id", getSession().getUser().getUid(), "resource_type", this.measurementType, Attributes.DAYS_FROM_CURRENT_DAY, Strings.toString(this.graphPeriod)));
    }

    private void updateListViewAdapter(List<ProgressEntryViewModel> list) {
        ListAdapter currentAdapter = getCurrentAdapter();
        ProgressEntryAdapter progressEntryAdapter = currentAdapter instanceof ProgressEntryAdapter ? (ProgressEntryAdapter) currentAdapter : null;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        if (progressEntryAdapter == null || !progressEntryAdapter.getMeasurementType().equals(this.measurementType)) {
            ListView listView = this.entryListView;
            ProgressEntryAdapter progressEntryAdapter2 = new ProgressEntryAdapter(this, this.measurementType, this.weightService, this.imageService, arrayList);
            listView.setAdapter(progressEntryAdapter2);
            return;
        }
        progressEntryAdapter.setEntries(arrayList);
    }

    private void updateListViewAdapter(StepsProgressModel stepsProgressModel) {
        ListAdapter currentAdapter = getCurrentAdapter();
        if (currentAdapter instanceof ProgressStepsAdapter) {
            ((ProgressStepsAdapter) currentAdapter).setModel(stepsProgressModel);
        } else {
            this.entryListView.setAdapter(new ProgressStepsAdapter(this, stepsProgressModel));
        }
    }

    /* access modifiers changed from: private */
    public void showSelectMeasurementTypeDialog() {
        if (!isDialogVisible(MEASUREMENT_TYPE_DIALOG_TAG)) {
            showDialogFragment(MeasurementTypeDialogFragment.newInstance(this.measurementType), MEASUREMENT_TYPE_DIALOG_TAG);
        }
    }

    /* access modifiers changed from: private */
    public void showSelectGraphPeriodDialog() {
        if (!isDialogVisible(GRAPH_PERIOD_DIALOG_TAG)) {
            showDialogFragment(GraphPeriodDialogFragment.newInstance(this.graphPeriod, this.measurementType), GRAPH_PERIOD_DIALOG_TAG);
        }
    }

    private ListAdapter getCurrentAdapter() {
        if (this.entryListView.getAdapter() != null) {
            return ((HeaderViewListAdapter) this.entryListView.getAdapter()).getWrappedAdapter();
        }
        return null;
    }

    private boolean isDialogVisible(String str) {
        return getSupportFragmentManager().findFragmentByTag(str) != null;
    }

    /* access modifiers changed from: private */
    public void startGalleryAndFocusImage(ProgressEntryViewModel progressEntryViewModel) {
        long imageAssociationLocalId = progressEntryViewModel.getImageAssociationLocalId();
        getNavigationHelper().withIntent(ProgressPhotosGalleryActivity.newStartIntent(this, imageAssociationLocalId, imageAssociationLocalId == -1 ? GalleryViewMode.Split : GalleryViewMode.Single, GalleryDataMode.View, ToolbarCta.ShareIcon)).startActivity(RequestCodes.PROGRESS_PHOTOS_GALLERY);
    }

    /* access modifiers changed from: private */
    public void startPhotoImportForEntry(ProgressEntryViewModel progressEntryViewModel) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(progressEntryViewModel.getDate());
        getNavigationHelper().withIntent(ImportPhotoActivity.newStartIntent(this, ImportSource.ProgressScreen, progressEntryViewModel.getMeasurementId(), progressEntryViewModel.getMeasurementUid(), "measurement", this.measurementType, instance)).startActivity(180);
    }
}
