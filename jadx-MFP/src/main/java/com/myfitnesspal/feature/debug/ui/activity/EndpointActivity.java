package com.myfitnesspal.feature.debug.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.constants.Environment.Blog;
import com.myfitnesspal.shared.constants.Environment.Config;
import com.myfitnesspal.shared.constants.Environment.DeviceActivation;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ViewUtils;
import javax.inject.Inject;

public class EndpointActivity extends MfpActivity {
    @Inject
    MfpApiSettings apiSettings;
    @BindView(2131363371)
    RadioButton radioButtonBlogInteg;
    @BindView(2131363372)
    RadioButton radioButtonBlogProd;
    @BindView(2131363374)
    RadioButton radioButtonConfigInteg;
    @BindView(2131363375)
    RadioButton radioButtonConfigProd;
    @BindView(2131363376)
    RadioButton radioButtonConfigQa;
    @BindView(2131363377)
    RadioButton radioButtonConsentsDev;
    @BindView(2131363378)
    RadioButton radioButtonConsentsProd;
    @BindView(2131363379)
    RadioButton radioButtonDeviceActivationInteg;
    @BindView(2131363380)
    RadioButton radioButtonDeviceActivationProd;
    @BindView(2131363391)
    RadioButton radioButtonIdmDev;
    @BindView(2131363392)
    RadioButton radioButtonIdmDevShortTtl;
    @BindView(2131363393)
    RadioButton radioButtonIdmInteg;
    @BindView(2131363394)
    RadioButton radioButtonIdmProd;
    @BindView(2131363395)
    RadioButton radioButtonNisDev;
    @BindView(2131363396)
    RadioButton radioButtonNisProd;
    @BindView(2131363397)
    RadioButton radioButtonSyncV2Integ;
    @BindView(2131363398)
    RadioButton radioButtonSyncV2Prod;
    @BindView(2131363399)
    RadioButton radioButtonV1Dev1;
    @BindView(2131363400)
    RadioButton radioButtonV1Integ;
    @BindView(2131363401)
    RadioButton radioButtonV1Preprod;
    @BindView(2131363402)
    RadioButton radioButtonV1Prod;
    @BindView(2131363403)
    RadioButton radioButtonV1Qa;
    @BindView(2131363404)
    RadioButton radioButtonV2Dev1;
    @BindView(2131363405)
    RadioButton radioButtonV2Integ;
    @BindView(2131363406)
    RadioButton radioButtonV2Preprod;
    @BindView(2131363407)
    RadioButton radioButtonV2Prod;
    @BindView(2131363408)
    RadioButton radioButtonV2Qa;
    @BindView(2131363409)
    RadioButton radioButtonWebsiteInteg;
    @BindView(2131363410)
    RadioButton radioButtonWebsitePreprod;
    @BindView(2131363411)
    RadioButton radioButtonWebsiteProd;
    @BindView(2131363412)
    RadioButton radioButtonWebsiteQa;
    @BindView(2131363381)
    RadioGroup radioGroupBlog;
    @BindView(2131363382)
    RadioGroup radioGroupConfig;
    @BindView(2131363383)
    RadioGroup radioGroupConsents;
    @BindView(2131363384)
    RadioGroup radioGroupDeviceActivation;
    @BindView(2131363385)
    RadioGroup radioGroupIdm;
    @BindView(2131363386)
    RadioGroup radioGroupNis;
    @BindView(2131363387)
    RadioGroup radioGroupSyncV2;
    @BindView(2131363388)
    RadioGroup radioGroupV1;
    @BindView(2131363389)
    RadioGroup radioGroupV2;
    @BindView(2131363390)
    RadioGroup radioGroupWebsite;
    @BindView(2131364111)
    Button useIntegButton;
    @BindView(2131364112)
    Button useProdButton;

    public static void start(Context context) {
        context.startActivity(new Intent(context, EndpointActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.auth_debug_activity);
        initIdm();
        initNis();
        initSyncV2();
        initConsents();
        initV1();
        initV2();
        initConfig();
        initWebsite();
        initDeviceActivation();
        initBlog();
        setUseIntegButtonListener();
        setUseProdButtonListener();
    }

    private void setUseProdButtonListener() {
        this.useProdButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                EndpointActivity.lambda$setUseProdButtonListener$0(EndpointActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$setUseProdButtonListener$0(EndpointActivity endpointActivity, View view) {
        endpointActivity.radioButtonIdmProd.setChecked(true);
        endpointActivity.radioButtonNisProd.setChecked(true);
        endpointActivity.radioButtonSyncV2Prod.setChecked(true);
        endpointActivity.radioButtonConsentsProd.setChecked(true);
        endpointActivity.radioButtonV1Prod.setChecked(true);
        endpointActivity.radioButtonV2Prod.setChecked(true);
        endpointActivity.radioButtonConfigProd.setChecked(true);
        endpointActivity.radioButtonWebsiteProd.setChecked(true);
        endpointActivity.radioButtonDeviceActivationProd.setChecked(true);
        endpointActivity.radioButtonBlogProd.setChecked(true);
    }

    private void setUseIntegButtonListener() {
        this.useIntegButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                EndpointActivity.lambda$setUseIntegButtonListener$1(EndpointActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$setUseIntegButtonListener$1(EndpointActivity endpointActivity, View view) {
        endpointActivity.radioButtonIdmDev.setChecked(true);
        endpointActivity.radioButtonNisDev.setChecked(true);
        endpointActivity.radioButtonSyncV2Integ.setChecked(true);
        endpointActivity.radioButtonConsentsDev.setChecked(true);
        endpointActivity.radioButtonV1Integ.setChecked(true);
        endpointActivity.radioButtonV2Integ.setChecked(true);
        endpointActivity.radioButtonConfigInteg.setChecked(true);
        endpointActivity.radioButtonWebsiteInteg.setChecked(true);
        endpointActivity.radioButtonDeviceActivationInteg.setChecked(true);
        endpointActivity.radioButtonBlogInteg.setChecked(true);
    }

    private void initIdm() {
        this.radioButtonIdmDev.setTag("dev");
        this.radioButtonIdmDevShortTtl.setTag(SSO.DEV_SHORT_TTL);
        this.radioButtonIdmInteg.setTag("integ");
        this.radioButtonIdmProd.setTag("prod");
        String idmEndpoint = this.apiSettings.getIdmEndpoint();
        if ("dev".equals(idmEndpoint)) {
            this.radioButtonIdmDev.setChecked(true);
        } else if (SSO.DEV_SHORT_TTL.equals(idmEndpoint)) {
            this.radioButtonIdmDevShortTtl.setChecked(true);
        } else if ("integ".equals(idmEndpoint)) {
            this.radioButtonIdmInteg.setChecked(true);
        } else {
            this.radioButtonIdmProd.setChecked(true);
        }
        this.radioGroupIdm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initIdm$2(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initIdm$2(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setIdmEndpoint((String) radioButton.getTag());
            try {
                SSO.getSdk().logout();
            } catch (Exception unused) {
            }
        }
    }

    private void initNis() {
        this.radioButtonNisDev.setTag("dev");
        this.radioButtonNisProd.setTag("prod");
        if ("dev".equals(this.apiSettings.getNISEndpoint())) {
            this.radioButtonNisDev.setChecked(true);
        } else {
            this.radioButtonNisProd.setChecked(true);
        }
        this.radioGroupNis.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initNis$3(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initNis$3(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setNISEndpoint((String) radioButton.getTag());
        }
    }

    private void initSyncV2() {
        this.radioButtonSyncV2Integ.setTag("integ");
        this.radioButtonSyncV2Prod.setTag("prod");
        if ("integ".equals(this.apiSettings.getSyncV2Endpoint())) {
            this.radioButtonSyncV2Integ.setChecked(true);
        } else {
            this.radioButtonSyncV2Prod.setChecked(true);
        }
        this.radioGroupSyncV2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initSyncV2$4(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initSyncV2$4(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setSyncV2Endpoint((String) radioButton.getTag());
        }
    }

    private void initConsents() {
        this.radioButtonConsentsDev.setTag("dev");
        this.radioButtonConsentsProd.setTag("prod");
        if ("dev".equals(this.apiSettings.getConsentsEndpoint())) {
            this.radioButtonConsentsDev.setChecked(true);
        } else {
            this.radioButtonConsentsProd.setChecked(true);
        }
        this.radioGroupConsents.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initConsents$5(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initConsents$5(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setConsentsEndpoint((String) radioButton.getTag());
        }
    }

    private void initV1() {
        this.radioButtonV1Integ.setTag("https://integ.myfitnesspal.com");
        this.radioButtonV1Dev1.setTag("https://dev1.myfitnesspal.com");
        this.radioButtonV1Qa.setTag("https://qa.myfitnesspal.com");
        this.radioButtonV1Preprod.setTag("https://preprod.myfitnesspal.com");
        this.radioButtonV1Prod.setTag("https://sync.myfitnesspal.com");
        String v1Endpoint = this.apiSettings.getV1Endpoint();
        if (v1Endpoint.startsWith("https://integ.myfitnesspal.com")) {
            this.radioButtonV1Integ.setChecked(true);
        } else if (v1Endpoint.startsWith("https://dev1.myfitnesspal.com")) {
            this.radioButtonV1Dev1.setChecked(true);
        } else if (v1Endpoint.startsWith("https://qa.myfitnesspal.com")) {
            this.radioButtonV1Qa.setChecked(true);
        } else if (v1Endpoint.startsWith("https://preprod.myfitnesspal.com")) {
            this.radioButtonV1Preprod.setChecked(true);
        } else {
            this.radioButtonV1Prod.setChecked(true);
        }
        this.radioGroupV1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initV1$6(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initV1$6(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setV1Endpoint((String) radioButton.getTag());
        }
    }

    private void initV2() {
        this.radioButtonV2Integ.setTag("https://integ.myfitnesspal.com");
        this.radioButtonV2Dev1.setTag("https://dev1.myfitnesspal.com");
        this.radioButtonV2Qa.setTag("https://qa.myfitnesspal.com");
        this.radioButtonV2Preprod.setTag("https://preprod.myfitnesspal.com");
        this.radioButtonV2Prod.setTag("https://api.myfitnesspal.com");
        String v2Endpoint = this.apiSettings.getV2Endpoint();
        if (v2Endpoint.startsWith("https://integ.myfitnesspal.com")) {
            this.radioButtonV2Integ.setChecked(true);
        } else if (v2Endpoint.startsWith("https://dev1.myfitnesspal.com")) {
            this.radioButtonV2Dev1.setChecked(true);
        } else if (v2Endpoint.startsWith("https://qa.myfitnesspal.com")) {
            this.radioButtonV2Qa.setChecked(true);
        } else if (v2Endpoint.startsWith("https://preprod.myfitnesspal.com")) {
            this.radioButtonV2Preprod.setChecked(true);
        } else {
            this.radioButtonV2Prod.setChecked(true);
        }
        this.radioGroupV2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initV2$7(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initV2$7(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setV2Endpoint((String) radioButton.getTag());
        }
    }

    private void initConfig() {
        this.radioButtonConfigInteg.setTag("https://integ.myfitnesspal.com");
        this.radioButtonConfigQa.setTag("https://qa.myfitnesspal.com");
        this.radioButtonConfigProd.setTag(Config.PROD);
        String configEndpoint = this.apiSettings.getConfigEndpoint();
        if (configEndpoint.startsWith("https://integ.myfitnesspal.com")) {
            this.radioButtonConfigInteg.setChecked(true);
        } else if (configEndpoint.startsWith("https://qa.myfitnesspal.com")) {
            this.radioButtonConfigQa.setChecked(true);
        } else {
            this.radioButtonConfigProd.setChecked(true);
        }
        this.radioGroupConfig.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initConfig$8(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initConfig$8(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setConfigEndpoint((String) radioButton.getTag());
        }
    }

    private void initWebsite() {
        this.radioButtonWebsiteInteg.setTag("https://integ.myfitnesspal.com");
        this.radioButtonWebsiteQa.setTag("https://qa.myfitnesspal.com");
        this.radioButtonWebsitePreprod.setTag("https://preprod.myfitnesspal.com");
        this.radioButtonWebsiteProd.setTag("https://www.myfitnesspal.com");
        String websiteEndpoint = this.apiSettings.getWebsiteEndpoint();
        if (websiteEndpoint.startsWith("https://integ.myfitnesspal.com")) {
            this.radioButtonWebsiteInteg.setChecked(true);
        } else if (websiteEndpoint.startsWith("https://qa.myfitnesspal.com")) {
            this.radioButtonWebsiteQa.setChecked(true);
        } else if (websiteEndpoint.startsWith("https://preprod.myfitnesspal.com")) {
            this.radioButtonWebsitePreprod.setChecked(true);
        } else {
            this.radioButtonWebsiteProd.setChecked(true);
        }
        this.radioGroupWebsite.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initWebsite$9(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initWebsite$9(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setWebsiteEndpoint((String) radioButton.getTag());
        }
    }

    private void initDeviceActivation() {
        this.radioButtonDeviceActivationInteg.setTag(DeviceActivation.INTEG);
        this.radioButtonDeviceActivationProd.setTag(DeviceActivation.PROD);
        if (DeviceActivation.INTEG.equals(this.apiSettings.getDeviceActivationEnvironment())) {
            this.radioButtonDeviceActivationInteg.setChecked(true);
        } else {
            this.radioButtonDeviceActivationProd.setChecked(true);
        }
        this.radioGroupDeviceActivation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initDeviceActivation$10(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initDeviceActivation$10(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setDeviceActivationEnvironment((String) radioButton.getTag());
        }
    }

    private void initBlog() {
        this.radioButtonBlogInteg.setTag(Blog.INTEG);
        this.radioButtonBlogProd.setTag("http://blog.myfitnesspal.com");
        if (this.apiSettings.getBlogEndpoint().startsWith(Blog.INTEG)) {
            this.radioButtonBlogInteg.setChecked(true);
        } else {
            this.radioButtonBlogProd.setChecked(true);
        }
        this.radioGroupBlog.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                EndpointActivity.lambda$initBlog$11(EndpointActivity.this, radioGroup, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initBlog$11(EndpointActivity endpointActivity, RadioGroup radioGroup, int i) {
        RadioButton radioButton = (RadioButton) ViewUtils.findById(radioGroup, i);
        if (radioButton.isChecked()) {
            endpointActivity.apiSettings.setBlogEndpoint((String) radioButton.getTag());
        }
    }
}
