package com.myfitnesspal.feature.registration.ui.view;

import android.content.Context;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.CountryTier;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.BlueClickableSpanNoUnderline;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class PrivacyLinkTextSpan extends BlueClickableSpanNoUnderline {
    private final CountryTier countryTier;
    private final LinkType linkType;
    private final NavigationHelper navigationHelper;
    private final String source;
    private final Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;

    public enum LinkType {
        PrivacyPolicy,
        Terms,
        ContactUs
    }

    public PrivacyLinkTextSpan(Context context, NavigationHelper navigationHelper2, LinkType linkType2, Lazy<UpdatedTermsAnalyticsHelper> lazy, CountryTier countryTier2, String str) {
        super(context);
        this.navigationHelper = navigationHelper2;
        this.linkType = linkType2;
        this.updatedTermsAnalyticsHelper = lazy;
        this.countryTier = countryTier2;
        this.source = str;
    }

    public void onClick(View view) {
        String str;
        com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.LinkType linkType2;
        int i;
        switch (this.linkType) {
            case PrivacyPolicy:
                String str2 = URLs.UA_PRIVACY_POLICY;
                i = R.string.privacy_policy;
                linkType2 = com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.LinkType.PP;
                str = str2;
                break;
            case Terms:
                String str3 = URLs.UA_TERMS;
                i = R.string.terms;
                linkType2 = com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.LinkType.ToS;
                str = str3;
                break;
            case ContactUs:
                String str4 = URLs.UA_CONTACT;
                i = R.string.contact_us;
                linkType2 = com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.LinkType.ContactUs;
                str = str4;
                break;
            default:
                throw new IllegalArgumentException("Invalid Link Type!");
        }
        Lazy<UpdatedTermsAnalyticsHelper> lazy = this.updatedTermsAnalyticsHelper;
        if (!(lazy == null || this.countryTier == null)) {
            ((UpdatedTermsAnalyticsHelper) lazy.get()).reportModalLinkClicked(this.countryTier, linkType2);
        }
        reportTOSOrPPLinkSee(this.linkType);
        this.navigationHelper.withIntent(FullScreenWebView.newStartIntent(getContext(), str, getContext().getString(i), false, true, false)).startActivity();
    }

    private void reportTOSOrPPLinkSee(LinkType linkType2) {
        if (this.updatedTermsAnalyticsHelper != null && !Strings.isEmpty(this.source)) {
            switch (linkType2) {
                case PrivacyPolicy:
                    ((UpdatedTermsAnalyticsHelper) this.updatedTermsAnalyticsHelper.get()).reportPPSee(this.source);
                    return;
                case Terms:
                    ((UpdatedTermsAnalyticsHelper) this.updatedTermsAnalyticsHelper.get()).reportTOSSee(this.source);
                    return;
                default:
                    return;
            }
        }
    }
}
