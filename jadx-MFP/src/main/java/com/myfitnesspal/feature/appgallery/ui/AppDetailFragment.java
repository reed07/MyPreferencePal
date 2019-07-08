package com.myfitnesspal.feature.appgallery.ui;

import android.content.ActivityNotFoundException;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.model.AppDetailViewModel;
import com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.Property;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.DisconnectAppTask;
import com.myfitnesspal.feature.appgallery.util.AppGalleryUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.event.GoogleFitConnectedEvent;
import com.myfitnesspal.feature.externalsync.impl.googlefit.event.GoogleFitDisabledEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthConnectionErrorEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthConnectionEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.mixin.SHealthMixin;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v2.MfpAppImage;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class AppDetailFragment extends MfpFragment {
    @BindView(2131361831)
    Button actionButton;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    private MfpActivity activity;
    @Inject
    Lazy<AppGalleryService> appGalleryService;
    @BindView(2131361905)
    MfpImageView appIcon;
    private Handler handler = new Handler();
    int lastPagerHeight = 0;
    @BindView(2131362897)
    View learnMoreButton;
    private SHealthMixin samsungHealth;
    @BindView(2131363545)
    ViewPager screenShotsPager;
    @BindView(2131361903)
    TextView txtAppDescription;
    @BindView(2131361906)
    TextView txtAppName;
    @BindView(2131361902)
    TextView txtCompanyName;
    private AppDetailViewModel viewModel;

    public static AppDetailFragment newInstance(MfpPlatformApp mfpPlatformApp) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Extras.PARTNER_APP, mfpPlatformApp);
        AppDetailFragment appDetailFragment = new AppDetailFragment();
        appDetailFragment.setArguments(bundle);
        return appDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.partner_app_details_view, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.activity = (MfpActivity) getActivity();
        this.samsungHealth = (SHealthMixin) this.activity.mixin(SHealthMixin.class);
        MaterialUtils.addDefaultToolbarElevation(getActivity());
        MfpPlatformApp mfpPlatformApp = (MfpPlatformApp) BundleUtils.getParcelable(getArguments(), Extras.PARTNER_APP, MfpPlatformApp.class.getClassLoader());
        if (mfpPlatformApp != null) {
            initializeViewModel(mfpPlatformApp);
            initializeUi();
            return;
        }
        postEvent(new AlertEvent(getString(R.string.unknown_error)));
    }

    public void onResume() {
        super.onResume();
        refreshViewModel();
        redrawScreenshots();
    }

    private void initializeViewModel(MfpPlatformApp mfpPlatformApp) {
        this.viewModel = (AppDetailViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (AppDetailViewModel) setViewModel(new AppDetailViewModel(this.activity, mfpPlatformApp, getRunner(), this.appGalleryService));
        }
    }

    private void initializeUi() {
        setUpListeners();
        recordLocalyticsEventNamed(Events.APP_DETAILS_VIEWED);
        getActivity().setTitle(this.viewModel.getActivityTitle());
        rebindUi();
        this.appIcon.setPlaceholderImageId(R.drawable.thumbnail_partner).setUrl(this.viewModel.getIconUri());
    }

    private void rebindUi() {
        this.txtAppName.setText(this.viewModel.getName());
        this.txtCompanyName.setText(this.viewModel.getCompanyName());
        this.txtAppDescription.setText(this.viewModel.getDescription());
        this.actionButton.setText(this.viewModel.getCtaText());
        ViewUtils.setVisible(this.viewModel.isPurchasable(), this.learnMoreButton);
    }

    private void refreshViewModel() {
        AppDetailViewModel appDetailViewModel = this.viewModel;
        if (appDetailViewModel != null) {
            appDetailViewModel.load(new Void[0]);
        }
    }

    private void setUpListeners() {
        MfpActivity mfpActivity = (MfpActivity) getActivity();
        this.screenShotsPager.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                AppDetailFragment.lambda$setUpListeners$1(AppDetailFragment.this, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        this.actionButton.setOnClickListener(new OnClickListener(mfpActivity) {
            private final /* synthetic */ MfpActivity f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                AppDetailFragment.lambda$setUpListeners$2(AppDetailFragment.this, this.f$1, view);
            }
        });
        this.learnMoreButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AppDetailFragment.lambda$setUpListeners$3(AppDetailFragment.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$setUpListeners$1(AppDetailFragment appDetailFragment, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        if (i9 != appDetailFragment.lastPagerHeight) {
            appDetailFragment.lastPagerHeight = i9;
            appDetailFragment.handler.post(new Runnable() {
                public final void run() {
                    AppDetailFragment.this.redrawScreenshots();
                }
            });
        }
    }

    public static /* synthetic */ void lambda$setUpListeners$2(AppDetailFragment appDetailFragment, MfpActivity mfpActivity, View view) {
        switch (appDetailFragment.viewModel.getCtaMode()) {
            case Connect:
                appDetailFragment.recordLocalyticsEventNamed(Events.APP_DETAILS_CONNECT_CLICK);
                appDetailFragment.trackEventWithAppName(Events.CONNECT_FEATURED_PARTNERGALLERY);
                AppGalleryUtil.launchAppConnect(mfpActivity, appDetailFragment.viewModel.getApp());
                return;
            case Install:
                appDetailFragment.trackEventWithAppName(Events.INSTALL_FEATURED_PARTNERGALLERY);
                AppGalleryUtil.launchAppInstall(mfpActivity, appDetailFragment.viewModel.getApp());
                return;
            case Disconnect:
                appDetailFragment.trackEventWithAppName(Events.DISCONNECT_PARTNER);
                MfpPlatformApp app = appDetailFragment.viewModel.getApp();
                if (AppGalleryUtil.isGoogleFit(app)) {
                    if (AppGalleryUtil.isGoogleFitConnected(mfpActivity)) {
                        mfpActivity.getGoogleFitClient().disable();
                        return;
                    }
                    return;
                } else if (AppGalleryUtil.isSHealth(app)) {
                    appDetailFragment.samsungHealth.getConnection().unpair();
                    return;
                } else {
                    new DisconnectAppTask(appDetailFragment.viewModel.getPartnerId(), appDetailFragment.appGalleryService).run(appDetailFragment.getRunner());
                    return;
                }
            default:
                return;
        }
    }

    public static /* synthetic */ void lambda$setUpListeners$3(AppDetailFragment appDetailFragment, View view) {
        if (appDetailFragment.viewModel.isPurchasable()) {
            appDetailFragment.recordLocalyticsEventNamed(Events.APP_DETAILS_LEARN_MORE_CLICK);
            appDetailFragment.navigateToUri(appDetailFragment.viewModel.getPurchaseUri());
        }
    }

    private void trackEventWithAppName(String str) {
        if (this.viewModel.isValid()) {
            HashMap hashMap = new HashMap();
            hashMap.put(Attributes.APP_NAME, this.viewModel.getName());
            ((ActionTrackingService) this.actionTrackingService.get()).registerAppendAndReportEvent(str, hashMap);
        }
    }

    private void recordLocalyticsEventNamed(String str) {
        if (this.viewModel.isValid()) {
            HashMap hashMap = new HashMap();
            hashMap.put(Attributes.PRODUCT_NAME, Strings.toString(this.viewModel.getName()));
            hashMap.put(Attributes.PARTNER_NAME, Strings.toString(this.viewModel.getPartnerName()));
            getAnalyticsService().reportEvent(str, (Map<String, String>) hashMap);
        }
    }

    private void navigateToUri(String str) {
        try {
            getNavigationHelper().withIntent(SharedIntents.newUriIntent(str)).startActivity();
        } catch (ActivityNotFoundException e) {
            Ln.e(e, "AppGallery URI", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void redrawScreenshots() {
        final ArrayList screenshots = this.viewModel.getScreenshots();
        if (CollectionUtils.isEmpty((Collection<?>) screenshots)) {
            this.screenShotsPager.setAdapter(null);
            return;
        }
        this.screenShotsPager.setAdapter(new PagerAdapter() {
            public int getItemPosition(Object obj) {
                return -2;
            }

            public float getPageWidth(int i) {
                return 0.5f;
            }

            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            public Parcelable saveState() {
                return null;
            }

            public Object instantiateItem(ViewGroup viewGroup, int i) {
                View inflate = LayoutInflater.from(AppDetailFragment.this.getContext()).inflate(R.layout.app_details_screenshot, viewGroup, false);
                ((MfpImageView) ViewUtils.findById(inflate, R.id.image)).setUrl(((MfpAppImage) screenshots.get(i)).getFilename());
                viewGroup.addView(inflate, 0);
                return inflate;
            }

            public int getCount() {
                return CollectionUtils.size((Collection<?>) screenshots);
            }

            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) obj);
            }
        });
        this.screenShotsPager.setCurrentItem(0);
    }

    @Subscribe
    public void onFitConnectedEvent(GoogleFitConnectedEvent googleFitConnectedEvent) {
        refreshViewModel();
    }

    @Subscribe
    public void onFitDisabledEvent(GoogleFitDisabledEvent googleFitDisabledEvent) {
        refreshViewModel();
    }

    @Subscribe
    public void onSHealthConnectionEvent(SHealthConnectionEvent sHealthConnectionEvent) {
        refreshViewModel();
        this.samsungHealth.onConnectionEvent(sHealthConnectionEvent);
    }

    @Subscribe
    public void onSHealthConnectionErrorEvent(SHealthConnectionErrorEvent sHealthConnectionErrorEvent) {
        this.samsungHealth.onConnectionErrorEvent(sHealthConnectionErrorEvent);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i == Property.CTA_ACTION) {
            rebindUi();
        }
    }
}
