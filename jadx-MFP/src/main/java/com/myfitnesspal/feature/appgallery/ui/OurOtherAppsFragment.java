package com.myfitnesspal.feature.appgallery.ui;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.databinding.Observable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.model.OurOtherAppsItemViewModel;
import com.myfitnesspal.feature.appgallery.model.OurOtherAppsViewModel;
import com.myfitnesspal.feature.appgallery.model.OurOtherAppsViewModel.Property;
import com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.CtaMode;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.DisconnectAppTask;
import com.myfitnesspal.feature.appgallery.service.DisconnectAppTask.CompletedEvent;
import com.myfitnesspal.feature.appgallery.ui.OurAppsItemListAdapter.OnCtaButtonClickedListener;
import com.myfitnesspal.feature.appgallery.util.AppGalleryUtil;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.BlueClickableSpanNoUnderline;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class OurOtherAppsFragment extends MfpFragment {
    private static final int HEADER_HEIGHT_PERCENT_OF_SCREEN = 20;
    private static final String OUR_APPS_STORE_LINK = "market://search?q=pub:MapMyFitness%2C%20Inc.";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<AppGalleryService> appGalleryService;
    @BindView(2131362729)
    View header;
    @BindView(2131361913)
    LinearLayoutListAdapterView listView;
    private OnCtaButtonClickedListener onCtaButtonClickedListener = new OnCtaButtonClickedListener() {
        public void onClicked(OurOtherAppsItemViewModel ourOtherAppsItemViewModel) {
            MfpActivity mfpActivity = (MfpActivity) OurOtherAppsFragment.this.getActivity();
            MfpPlatformApp app = ourOtherAppsItemViewModel.getApp();
            CtaMode ctaMode = ourOtherAppsItemViewModel.getCtaMode();
            switch (AnonymousClass3.$SwitchMap$com$myfitnesspal$feature$appgallery$model$ViewModelWithCtaButton$CtaMode[ctaMode.ordinal()]) {
                case 1:
                    AppGalleryUtil.launchAppConnect(mfpActivity, app);
                    break;
                case 2:
                    AppGalleryUtil.launchAppInstall(mfpActivity, app);
                    break;
                case 3:
                    mfpActivity.setBusy(true);
                    new DisconnectAppTask(app.getClientId(), OurOtherAppsFragment.this.appGalleryService).run(OurOtherAppsFragment.this.getRunner());
                    break;
            }
            ((AnalyticsService) OurOtherAppsFragment.this.analyticsService.get()).reportEvent(Events.OUR_OTHER_APPS, MapUtil.createMap(Attributes.APP_NAME, app.getName(), "state", ctaMode.name()));
        }
    };
    @BindView(2131363833)
    TextView titleText;
    private OurOtherAppsViewModel viewModel;

    /* renamed from: com.myfitnesspal.feature.appgallery.ui.OurOtherAppsFragment$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$appgallery$model$ViewModelWithCtaButton$CtaMode = new int[CtaMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton$CtaMode[] r0 = com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.CtaMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$myfitnesspal$feature$appgallery$model$ViewModelWithCtaButton$CtaMode = r0
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$appgallery$model$ViewModelWithCtaButton$CtaMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton$CtaMode r1 = com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.CtaMode.Connect     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$appgallery$model$ViewModelWithCtaButton$CtaMode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton$CtaMode r1 = com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.CtaMode.Install     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$appgallery$model$ViewModelWithCtaButton$CtaMode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton$CtaMode r1 = com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.CtaMode.Disconnect     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.appgallery.ui.OurOtherAppsFragment.AnonymousClass3.<clinit>():void");
        }
    }

    public static OurOtherAppsFragment newInstance() {
        return new OurOtherAppsFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.our_other_apps, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getImmHelper().hideSoftInput();
        initViewModel();
        int length = getString(R.string.our_apps).length();
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.our_apps));
        sb.append(" ");
        sb.append(getString(R.string.our_apps_link_text));
        String sb2 = sb.toString();
        SpannableString spannableString = new SpannableString(sb2);
        spannableString.setSpan(new BlueClickableSpanNoUnderline(getActivity()) {
            public void onClick(View view) {
                OurOtherAppsFragment.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent(OurOtherAppsFragment.OUR_APPS_STORE_LINK)).startActivity();
            }
        }, length, sb2.length(), 33);
        this.titleText.setText(spannableString);
        this.titleText.setMovementMethod(LinkMovementMethod.getInstance());
        this.listView.setDivider((Drawable) new ColorDrawable(getResources().getColor(R.color.light_divider)), 1.0f);
        updateHeaderHeight();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateHeaderHeight();
    }

    @SuppressLint({"RestrictedApi"})
    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.APP_LIST) {
            this.listView.setAdapter(new OurAppsItemListAdapter(getLayoutInflater(null), this.viewModel.getModelList(), this.onCtaButtonClickedListener, R.layout.our_other_apps_item));
        } else if (i == Property.LOAD_STATE) {
            setBusy(this.viewModel.getState() == State.Loading);
        }
    }

    @Subscribe
    public void onAppDisconnected(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            if (completedEvent.getFailure() == null) {
                this.viewModel.load(new Void[0]);
            } else {
                getMessageBus().post(new AlertEvent(getString(R.string.cannot_disconnect_app)));
            }
        }
    }

    private void initViewModel() {
        this.viewModel = (OurOtherAppsViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (OurOtherAppsViewModel) setViewModel(new OurOtherAppsViewModel(getActivity(), getRunner(), this.appGalleryService));
        }
    }

    private void updateHeaderHeight() {
        ViewUtils.updateHeightBasedOnScreenHeight(getActivity().getWindowManager(), this.header, 20);
    }
}
