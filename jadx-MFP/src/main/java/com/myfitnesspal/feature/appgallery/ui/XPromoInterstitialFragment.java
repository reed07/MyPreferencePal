package com.myfitnesspal.feature.appgallery.ui;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.api.ExerciseTrackingAppRecommendation;
import com.myfitnesspal.feature.appgallery.model.XPromoViewModel;
import com.myfitnesspal.feature.appgallery.model.XPromoViewModel.Property;
import com.myfitnesspal.feature.appgallery.model.XPromoViewModel.ViewMode;
import com.myfitnesspal.feature.appgallery.util.AppGalleryUtil;
import com.myfitnesspal.feature.settings.model.XPromoSettings;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class XPromoInterstitialFragment extends MfpFragment {
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @BindView(2131361951)
    TextView body;
    @BindView(2131362243)
    Button cta;
    @BindView(2131362347)
    CheckBox dontShowAgain;
    @BindView(2131363162)
    TextView notNow;
    @BindView(2131363245)
    ImageView partnerIcon;
    @BindView(2131363833)
    TextView title;
    /* access modifiers changed from: private */
    public XPromoViewModel viewModel;
    @Inject
    XPromoSettings xpromoSettings;

    public static XPromoInterstitialFragment newInstance(ExerciseTrackingAppRecommendation exerciseTrackingAppRecommendation) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Extras.PARTNER_APP, exerciseTrackingAppRecommendation);
        XPromoInterstitialFragment xPromoInterstitialFragment = new XPromoInterstitialFragment();
        xPromoInterstitialFragment.setArguments(bundle);
        return xPromoInterstitialFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.xpromo_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        getImmHelper().hideSoftInput();
        initViewModel((ExerciseTrackingAppRecommendation) BundleUtils.getParcelable(getArguments(), Extras.PARTNER_APP, ExerciseTrackingAppRecommendation.class.getClassLoader()));
        rebindUi();
        setListeners();
        reportPageView();
    }

    public void onResume() {
        super.onResume();
        refreshViewModel();
        finishIfAlreadyConnected();
    }

    public void onDestroy() {
        super.onDestroy();
        reportDismissed();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i == Property.VIEW_MODE) {
            this.cta.setText(this.viewModel.getCtaLabel());
        }
    }

    private void finishIfAlreadyConnected() {
        if (this.viewModel.getViewMode() == ViewMode.Done) {
            getActivity().finish();
        }
    }

    private void initViewModel(ExerciseTrackingAppRecommendation exerciseTrackingAppRecommendation) {
        this.viewModel = (XPromoViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (XPromoViewModel) setViewModel(new XPromoViewModel(getActivity(), exerciseTrackingAppRecommendation));
        }
    }

    private void refreshViewModel() {
        XPromoViewModel xPromoViewModel = this.viewModel;
        if (xPromoViewModel != null) {
            xPromoViewModel.refresh();
        }
    }

    private void rebindUi() {
        setTitle(this.viewModel.getActivityTitle(), new Object[0]);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setTitleTextAppearance(getActivity(), R.style.ToolbarTitle_Wrap);
        Glide.with(getActivity()).load(this.viewModel.getIconUri()).apply(new RequestOptions().fitCenter().error(R.drawable.thumbnail_partner)).into(this.partnerIcon);
        this.title.setText(this.viewModel.getTitle());
        this.body.setText(this.viewModel.getBody());
        this.cta.setText(this.viewModel.getCtaLabel());
        this.cta.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MfpActivity mfpActivity = (MfpActivity) XPromoInterstitialFragment.this.getActivity();
                MfpPlatformApp app = XPromoInterstitialFragment.this.viewModel.getApp();
                if (XPromoInterstitialFragment.this.viewModel.getViewMode() == ViewMode.Install) {
                    AppGalleryUtil.launchAppInstall(mfpActivity, app);
                } else {
                    AppGalleryUtil.launchAppConnect(mfpActivity, app);
                    XPromoInterstitialFragment.this.getActivity().finish();
                }
                XPromoInterstitialFragment.this.reportCtaButtonClick();
            }
        });
    }

    private void reportPageView() {
        HashMap hashMap = new HashMap();
        hashMap.put("exercise_id", this.viewModel.getExerciseId());
        hashMap.put("app_name", this.viewModel.getAppName());
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.XPROMO_POSTWORKOUT_VIEW, (Map<String, String>) hashMap);
    }

    /* access modifiers changed from: private */
    public void reportCtaButtonClick() {
        String[] strArr = new String[6];
        strArr[0] = "exercise_id";
        strArr[1] = this.viewModel.getExerciseId();
        strArr[2] = "app_name";
        strArr[3] = this.viewModel.getAppName();
        strArr[4] = "type";
        strArr[5] = this.viewModel.getViewMode() == ViewMode.Install ? Attributes.XPROMO_DOWNLOAD : Attributes.XPROMO_CONNECT;
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.XPROMO_POSTWORKOUT_CTA_TAP, MapUtil.createMap(strArr));
    }

    private void reportDismissed() {
        if (this.viewModel != null) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.XPROMO_POSTWORKOUT_DISMISSED, MapUtil.createMap("exercise_id", this.viewModel.getExerciseId(), "app_name", this.viewModel.getAppName(), Attributes.DONT_REMIND_ME_AGAIN_CHECKED, Strings.toString(Boolean.valueOf(this.xpromoSettings.hasDontShowBeenSet()))));
        }
    }

    private void setListeners() {
        this.dontShowAgain.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                XPromoInterstitialFragment.this.xpromoSettings.setDontShow(z);
            }
        });
        this.notNow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                XPromoInterstitialFragment.this.getActivity().finish();
            }
        });
    }
}
