package com.myfitnesspal.feature.settings.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.settings.task.LoadStepsSourcesTask;
import com.myfitnesspal.feature.settings.task.LoadStepsSourcesTask.Event;
import com.myfitnesspal.feature.settings.task.LoadStepsSourcesTask.LoadResult;
import com.myfitnesspal.feature.settings.ui.adapter.StepsSettingsListAdapter;
import com.myfitnesspal.framework.deeplink.DeepLinkRouter;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class StepsSettingsListFragment extends MfpFragment {
    public static final int ID_ADD_TRACKER = -1;
    public static final int ID_DONT_TRACK = -2;
    @Inject
    Lazy<ApiUrlProvider> apiUrlProvider;
    @Inject
    Lazy<AppGalleryService> appGalleryService;
    @Inject
    Lazy<AppSettings> appSettings;
    @Inject
    Lazy<AuthTokenProvider> authTokens;
    @Inject
    Lazy<DeviceInfo> deviceInfo;
    private ListView deviceList;
    @Inject
    GoogleFitClient googleFit;
    private MfpStepSource googleFitStepSource;
    @Inject
    Lazy<DeepLinkRouter> router;
    @Inject
    SHealthConnection samsungHealth;
    private MfpStepSource shealthStepSource;
    @Inject
    Lazy<StepService> stepsService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.googleFitStepSource = GoogleFitStepsUtils.createGoogleFitStepSource(getSession());
        this.shealthStepSource = SHealthUtil.createStepSource(getSession());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.steps_settings_fragment, viewGroup, false);
        setupViews(layoutInflater, inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setOnClickListeners();
    }

    public void onResume() {
        super.onResume();
        setBusy(true);
        LoadStepsSourcesTask loadStepsSourcesTask = new LoadStepsSourcesTask(getSession(), this.appGalleryService, this.stepsService, this.googleFit, this.samsungHealth);
        loadStepsSourcesTask.run(getRunner());
    }

    @Subscribe
    public void onStepSourcesLoaded(Event event) {
        if (!event.isFrom(getRunner())) {
            return;
        }
        if (event.successful()) {
            updateAdapter(((LoadResult) event.getResult()).stepSources, ((LoadResult) event.getResult()).apps);
        } else {
            showSyncErrorDialog();
        }
    }

    private void updateAdapter(List<MfpStepSource> list, List<MfpPlatformApp> list2) {
        if (isEnabled()) {
            ListView listView = this.deviceList;
            StepsSettingsListAdapter stepsSettingsListAdapter = new StepsSettingsListAdapter(getActivity(), removeDisabledClientSideStepSources(list), (StepService) this.stepsService.get(), list2, (DeviceInfo) this.deviceInfo.get());
            listView.setAdapter(stepsSettingsListAdapter);
            setBusy(false);
        }
    }

    /* access modifiers changed from: private */
    public void updateAdapter(List<MfpStepSource> list) {
        if (isEnabled()) {
            StepsSettingsListAdapter stepsSettingsListAdapter = (StepsSettingsListAdapter) ListViewUtils.getAdapter(this.deviceList, StepsSettingsListAdapter.class);
            if (stepsSettingsListAdapter != null) {
                stepsSettingsListAdapter.updateStepsSourceList(removeDisabledClientSideStepSources(list));
            }
        }
    }

    private void setupViews(LayoutInflater layoutInflater, View view) {
        this.deviceList = (ListView) view.findViewById(R.id.device_list);
        View inflate = layoutInflater.inflate(R.layout.steps_settings_footer, null);
        this.deviceList.addFooterView(inflate, null, false);
        WebView webView = (WebView) ViewUtils.findById(inflate, R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(100);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                DeepLinkRouter deepLinkRouter = (DeepLinkRouter) StepsSettingsListFragment.this.router.get();
                deepLinkRouter.setDeepLink(Uri.parse(str).buildUpon().appendQueryParameter("withinApp", "true").build());
                deepLinkRouter.route();
                return true;
            }
        });
        webView.loadUrl(Uri.parse(((ApiUrlProvider) this.apiUrlProvider.get()).getBaseUrlForWebsite(Constants.Uri.STEPS_PREMIUM_AD)).buildUpon().appendQueryParameter("user_id", Strings.toString(((AuthTokenProvider) this.authTokens.get()).getDomainUserId())).toString());
    }

    private void setOnClickListeners() {
        this.deviceList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (j == -1) {
                    StepsSettingsListFragment.this.getNavigationHelper().fromFragment(StepsSettingsListFragment.this).withIntent(AppGalleryActivity.newStartIntentForHardwareTrackers(StepsSettingsListFragment.this.getActivity())).startActivity();
                } else if (j == -2) {
                    StepsSettingsListFragment.this.updateStepSource(null);
                } else {
                    StepsSettingsListFragment.this.updateStepSource((MfpStepSource) adapterView.getItemAtPosition(i));
                }
            }
        });
    }

    private List<MfpStepSource> removeDisabledClientSideStepSources(List<MfpStepSource> list) {
        ArrayList arrayList = new ArrayList();
        if (!CollectionUtils.notEmpty((Collection<?>) list)) {
            return arrayList;
        }
        List<MfpStepSource> arrayList2 = new ArrayList<>(list);
        if (!this.googleFit.isEnabled()) {
            arrayList2 = ((StepService) this.stepsService.get()).removeClientSideStepSourceFromList(this.googleFitStepSource, list);
        }
        return !this.samsungHealth.isPaired() ? ((StepService) this.stepsService.get()).removeClientSideStepSourceFromList(this.shealthStepSource, arrayList2) : arrayList2;
    }

    /* access modifiers changed from: private */
    public void updateStepSource(MfpStepSource mfpStepSource) {
        boolean z = true;
        setBusy(true);
        ((StepService) this.stepsService.get()).setPrimaryStepSource(new Function1<List<MfpStepSource>>() {
            public void execute(List<MfpStepSource> list) {
                if (StepsSettingsListFragment.this.isEnabled()) {
                    StepsSettingsListFragment.this.setBusy(false);
                    StepsSettingsListFragment.this.updateAdapter(list);
                }
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                if (StepsSettingsListFragment.this.isEnabled()) {
                    StepsSettingsListFragment.this.setBusy(false);
                    Ln.e("failed to change steps source", new Object[0]);
                    StepsSettingsListFragment.this.showSyncErrorDialog();
                }
            }
        }, mfpStepSource);
        AppSettings appSettings2 = (AppSettings) this.appSettings.get();
        if (mfpStepSource == null) {
            z = false;
        }
        appSettings2.setShouldTrackSteps(z);
    }

    /* access modifiers changed from: private */
    public void showSyncErrorDialog() {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.error_occured)).setMessage((int) R.string.connection_failed)).setPositiveText(R.string.cancel, null);
        alertDialogFragment.setCancelable(false);
        alertDialogFragment.show(getFragmentManager(), StepsSettingsListFragment.class.getName());
    }
}
