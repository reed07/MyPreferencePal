package com.myfitnesspal.feature.settings.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Partner;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.steps.StepService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.Collection;
import java.util.List;

public class StepsSettingsListAdapter extends ArrayAdapter<MfpStepSource> {
    private final DeviceInfo deviceInfo;
    private final List<MfpPlatformApp> mfpPlatformApps;
    private final StepService stepService;

    public StepsSettingsListAdapter(Context context, List<MfpStepSource> list, StepService stepService2, List<MfpPlatformApp> list2, DeviceInfo deviceInfo2) {
        super(context, R.layout.settings_list_item, R.id.setting_name, list);
        this.deviceInfo = deviceInfo2;
        this.stepService = stepService2;
        this.mfpPlatformApps = list2;
    }

    public int getCount() {
        return super.getCount() + 2;
    }

    public long getItemId(int i) {
        int count = super.getCount();
        if (i < count) {
            return (long) i;
        }
        return i == count ? -1 : -2;
    }

    public MfpStepSource getItem(int i) {
        if (i < super.getCount()) {
            return (MfpStepSource) super.getItem(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        RequestBuilder requestBuilder;
        boolean z;
        Context context = getContext();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.steps_settings_item, viewGroup, false);
        }
        long itemId = getItemId(i);
        TextView textView = (TextView) ViewUtils.findById(view, R.id.setting_name);
        TextView textView2 = (TextView) ViewUtils.findById(view, R.id.tv_steps_item_subtitle);
        CheckBox checkBox = (CheckBox) ViewUtils.findById(view, R.id.enabled);
        ImageView imageView = (ImageView) ViewUtils.findById(view, R.id.ivDeviceIcon);
        boolean z2 = itemId == -1;
        if (itemId == -2) {
            textView.setText(R.string.dont_track_steps);
            textView2.setText(R.string.dont_track_steps_subtitle);
            requestBuilder = Glide.with(context).load(Integer.valueOf(R.drawable.ic_steps_dont_track));
            z = !this.stepService.shouldTrackSteps();
        } else if (z2) {
            textView.setText(R.string.add_a_device);
            textView2.setText(R.string.add_device_subtitle);
            requestBuilder = Glide.with(context).load(Integer.valueOf(R.drawable.ic_steps_add_device));
            z = false;
        } else {
            MfpStepSource item = getItem(i);
            String clientId = item.getClientId();
            MfpPlatformApp platformAppForSource = getPlatformAppForSource(item, this.mfpPlatformApps);
            z = item.isPrimary();
            if (platformAppForSource != null) {
                textView.setText(platformAppForSource.getName());
                textView2.setText(context.getString(R.string.tracker_hardware_subtitle, new Object[]{platformAppForSource.getName()}));
                if (platformAppForSource.getIconImage() != null) {
                    requestBuilder = Glide.with(context).load(platformAppForSource.getIconImage().getFilename());
                } else {
                    requestBuilder = Glide.with(context).load(Integer.valueOf(R.drawable.ic_steps_missing));
                }
            } else if (Strings.equals(clientId, Extras.MFP_MOBILE_IOS)) {
                textView.setText(context.getString(R.string.iPhone));
                textView2.setText(context.getString(R.string.tracker_hardware_subtitle, new Object[]{context.getResources().getString(R.string.iPhone)}));
                requestBuilder = Glide.with(context).load(Integer.valueOf(R.drawable.ic_steps_missing));
            } else if (GoogleFitStepsUtils.isGoogleFitStepSource(item)) {
                textView.setText(context.getString(R.string.google_fit));
                textView2.setText(context.getString(R.string.tracker_hardware_subtitle_via_google_fit, new Object[]{context.getResources().getString(R.string.Android)}));
                requestBuilder = Glide.with(context).load(Integer.valueOf(R.drawable.ic_google_fit_steps));
            } else if (SHealthUtil.isSHealthStepsSource(item)) {
                textView.setText(context.getString(R.string.shealth_step_source));
                textView2.setText(context.getString(R.string.shealth_step_source_subtitle));
                requestBuilder = Glide.with(context).load(Integer.valueOf(R.drawable.ic_s_health_steps));
            } else {
                textView.setText(clientId);
                textView2.setText(R.string.tracker_software_subtitle);
                requestBuilder = Glide.with(context).load(Integer.valueOf(R.drawable.ic_steps_missing));
            }
        }
        checkBox.setClickable(false);
        checkBox.setChecked(z);
        ViewUtils.setVisible(z, 4, checkBox);
        int pixels = this.deviceInfo.toPixels(28);
        requestBuilder.apply(new RequestOptions().error(R.drawable.ic_steps_missing).override(pixels, pixels)).into(imageView);
        return view;
    }

    public void updateStepsSourceList(List<MfpStepSource> list) {
        clear();
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            addAll(list);
        }
        notifyDataSetChanged();
    }

    private MfpPlatformApp getPlatformAppForSource(MfpStepSource mfpStepSource, List<MfpPlatformApp> list) {
        if (mfpStepSource == null) {
            return null;
        }
        final String appId = mfpStepSource.getAppId();
        final String clientId = mfpStepSource.getClientId();
        if (Strings.notEmpty(appId)) {
            return (MfpPlatformApp) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, MfpPlatformApp>() {
                public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                    return Boolean.valueOf(Strings.equalsIgnoreCase(mfpPlatformApp.getAppId(), appId));
                }
            });
        }
        return Strings.equalsIgnoreCase(clientId, Partner.MAPMYFITNESS_CLIENT_ID) ? (MfpPlatformApp) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, MfpPlatformApp>() {
            public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                return Boolean.valueOf(Strings.equalsIgnoreCase(mfpPlatformApp.getClientId(), Partner.MAPMYFITNESS_CLIENT_ID) && Strings.equalsIgnoreCase(mfpPlatformApp.getName(), Partner.UNDER_ARMOUR_RECORD_NAME));
            }
        }) : (MfpPlatformApp) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, MfpPlatformApp>() {
            public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                return Boolean.valueOf(Strings.equalsIgnoreCase(mfpPlatformApp.getClientId(), clientId));
            }
        });
    }
}
