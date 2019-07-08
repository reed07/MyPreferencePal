package com.myfitnesspal.feature.debug.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EndpointActivity_ViewBinding implements Unbinder {
    private EndpointActivity target;

    @UiThread
    public EndpointActivity_ViewBinding(EndpointActivity endpointActivity) {
        this(endpointActivity, endpointActivity.getWindow().getDecorView());
    }

    @UiThread
    public EndpointActivity_ViewBinding(EndpointActivity endpointActivity, View view) {
        this.target = endpointActivity;
        endpointActivity.radioGroupIdm = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_idm, "field 'radioGroupIdm'", RadioGroup.class);
        endpointActivity.radioButtonIdmDev = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_idm_env_dev, "field 'radioButtonIdmDev'", RadioButton.class);
        endpointActivity.radioButtonIdmDevShortTtl = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_idm_env_dev_short_ttl, "field 'radioButtonIdmDevShortTtl'", RadioButton.class);
        endpointActivity.radioButtonIdmInteg = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_idm_env_integ, "field 'radioButtonIdmInteg'", RadioButton.class);
        endpointActivity.radioButtonIdmProd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_idm_env_prod, "field 'radioButtonIdmProd'", RadioButton.class);
        endpointActivity.radioGroupNis = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_nis, "field 'radioGroupNis'", RadioGroup.class);
        endpointActivity.radioButtonNisDev = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_nis_env_dev, "field 'radioButtonNisDev'", RadioButton.class);
        endpointActivity.radioButtonNisProd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_nis_env_prod, "field 'radioButtonNisProd'", RadioButton.class);
        endpointActivity.radioGroupSyncV2 = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_syncv2, "field 'radioGroupSyncV2'", RadioGroup.class);
        endpointActivity.radioButtonSyncV2Integ = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_syncv2_env_integ, "field 'radioButtonSyncV2Integ'", RadioButton.class);
        endpointActivity.radioButtonSyncV2Prod = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_syncv2_env_prod, "field 'radioButtonSyncV2Prod'", RadioButton.class);
        endpointActivity.radioGroupConsents = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_consents, "field 'radioGroupConsents'", RadioGroup.class);
        endpointActivity.radioButtonConsentsDev = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_consents_env_dev, "field 'radioButtonConsentsDev'", RadioButton.class);
        endpointActivity.radioButtonConsentsProd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_consents_env_prod, "field 'radioButtonConsentsProd'", RadioButton.class);
        endpointActivity.radioGroupV1 = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_v1, "field 'radioGroupV1'", RadioGroup.class);
        endpointActivity.radioButtonV1Integ = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v1_env_integ, "field 'radioButtonV1Integ'", RadioButton.class);
        endpointActivity.radioButtonV1Dev1 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v1_env_dev1, "field 'radioButtonV1Dev1'", RadioButton.class);
        endpointActivity.radioButtonV1Qa = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v1_env_qa, "field 'radioButtonV1Qa'", RadioButton.class);
        endpointActivity.radioButtonV1Preprod = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v1_env_preprod, "field 'radioButtonV1Preprod'", RadioButton.class);
        endpointActivity.radioButtonV1Prod = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v1_env_prod, "field 'radioButtonV1Prod'", RadioButton.class);
        endpointActivity.radioGroupV2 = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_v2, "field 'radioGroupV2'", RadioGroup.class);
        endpointActivity.radioButtonV2Integ = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v2_env_integ, "field 'radioButtonV2Integ'", RadioButton.class);
        endpointActivity.radioButtonV2Dev1 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v2_env_dev1, "field 'radioButtonV2Dev1'", RadioButton.class);
        endpointActivity.radioButtonV2Qa = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v2_env_qa, "field 'radioButtonV2Qa'", RadioButton.class);
        endpointActivity.radioButtonV2Preprod = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v2_env_preprod, "field 'radioButtonV2Preprod'", RadioButton.class);
        endpointActivity.radioButtonV2Prod = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_v2_env_prod, "field 'radioButtonV2Prod'", RadioButton.class);
        endpointActivity.radioGroupConfig = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_config, "field 'radioGroupConfig'", RadioGroup.class);
        endpointActivity.radioButtonConfigInteg = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_config_env_integ, "field 'radioButtonConfigInteg'", RadioButton.class);
        endpointActivity.radioButtonConfigQa = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_config_env_qa, "field 'radioButtonConfigQa'", RadioButton.class);
        endpointActivity.radioButtonConfigProd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_config_env_prod, "field 'radioButtonConfigProd'", RadioButton.class);
        endpointActivity.radioGroupWebsite = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_website, "field 'radioGroupWebsite'", RadioGroup.class);
        endpointActivity.radioButtonWebsiteInteg = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_website_env_integ, "field 'radioButtonWebsiteInteg'", RadioButton.class);
        endpointActivity.radioButtonWebsiteQa = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_website_env_qa, "field 'radioButtonWebsiteQa'", RadioButton.class);
        endpointActivity.radioButtonWebsitePreprod = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_website_env_preprod, "field 'radioButtonWebsitePreprod'", RadioButton.class);
        endpointActivity.radioButtonWebsiteProd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_website_env_prod, "field 'radioButtonWebsiteProd'", RadioButton.class);
        endpointActivity.radioGroupDeviceActivation = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_device_activation, "field 'radioGroupDeviceActivation'", RadioGroup.class);
        endpointActivity.radioButtonDeviceActivationInteg = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_device_activation_env_integ, "field 'radioButtonDeviceActivationInteg'", RadioButton.class);
        endpointActivity.radioButtonDeviceActivationProd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_device_activation_env_prod, "field 'radioButtonDeviceActivationProd'", RadioButton.class);
        endpointActivity.radioGroupBlog = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_blog, "field 'radioGroupBlog'", RadioGroup.class);
        endpointActivity.radioButtonBlogInteg = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_blog_env_integ, "field 'radioButtonBlogInteg'", RadioButton.class);
        endpointActivity.radioButtonBlogProd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_blog_env_prod, "field 'radioButtonBlogProd'", RadioButton.class);
        endpointActivity.useIntegButton = (Button) Utils.findRequiredViewAsType(view, R.id.use_integ_button, "field 'useIntegButton'", Button.class);
        endpointActivity.useProdButton = (Button) Utils.findRequiredViewAsType(view, R.id.use_prod_button, "field 'useProdButton'", Button.class);
    }

    @CallSuper
    public void unbind() {
        EndpointActivity endpointActivity = this.target;
        if (endpointActivity != null) {
            this.target = null;
            endpointActivity.radioGroupIdm = null;
            endpointActivity.radioButtonIdmDev = null;
            endpointActivity.radioButtonIdmDevShortTtl = null;
            endpointActivity.radioButtonIdmInteg = null;
            endpointActivity.radioButtonIdmProd = null;
            endpointActivity.radioGroupNis = null;
            endpointActivity.radioButtonNisDev = null;
            endpointActivity.radioButtonNisProd = null;
            endpointActivity.radioGroupSyncV2 = null;
            endpointActivity.radioButtonSyncV2Integ = null;
            endpointActivity.radioButtonSyncV2Prod = null;
            endpointActivity.radioGroupConsents = null;
            endpointActivity.radioButtonConsentsDev = null;
            endpointActivity.radioButtonConsentsProd = null;
            endpointActivity.radioGroupV1 = null;
            endpointActivity.radioButtonV1Integ = null;
            endpointActivity.radioButtonV1Dev1 = null;
            endpointActivity.radioButtonV1Qa = null;
            endpointActivity.radioButtonV1Preprod = null;
            endpointActivity.radioButtonV1Prod = null;
            endpointActivity.radioGroupV2 = null;
            endpointActivity.radioButtonV2Integ = null;
            endpointActivity.radioButtonV2Dev1 = null;
            endpointActivity.radioButtonV2Qa = null;
            endpointActivity.radioButtonV2Preprod = null;
            endpointActivity.radioButtonV2Prod = null;
            endpointActivity.radioGroupConfig = null;
            endpointActivity.radioButtonConfigInteg = null;
            endpointActivity.radioButtonConfigQa = null;
            endpointActivity.radioButtonConfigProd = null;
            endpointActivity.radioGroupWebsite = null;
            endpointActivity.radioButtonWebsiteInteg = null;
            endpointActivity.radioButtonWebsiteQa = null;
            endpointActivity.radioButtonWebsitePreprod = null;
            endpointActivity.radioButtonWebsiteProd = null;
            endpointActivity.radioGroupDeviceActivation = null;
            endpointActivity.radioButtonDeviceActivationInteg = null;
            endpointActivity.radioButtonDeviceActivationProd = null;
            endpointActivity.radioGroupBlog = null;
            endpointActivity.radioButtonBlogInteg = null;
            endpointActivity.radioButtonBlogProd = null;
            endpointActivity.useIntegButton = null;
            endpointActivity.useProdButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
