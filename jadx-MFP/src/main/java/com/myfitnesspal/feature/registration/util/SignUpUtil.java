package com.myfitnesspal.feature.registration.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.CountryTier;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity.Mode;
import com.myfitnesspal.feature.registration.ui.view.PrivacyLinkTextSpan;
import com.myfitnesspal.feature.registration.ui.view.PrivacyLinkTextSpan.LinkType;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class SignUpUtil {

    public enum PrivacyAndMarketingType {
        USPrivacyAndMarketing,
        USPrivacyAndEUMarketing,
        EUPrivacyAndMarketing
    }

    public static void setWeightOrHint(EditText editText, float f, Weight weight) {
        String displayString = LocalizedWeight.getDisplayString(editText.getContext(), LocalizedWeight.fromPounds((double) f), weight);
        if (f == BitmapDescriptorFactory.HUE_RED) {
            editText.setHint(displayString);
        } else {
            editText.setText(displayString);
        }
    }

    public static void setupDisclaimerText(TextView textView, NavigationHelper navigationHelper, int i) {
        setupDisclaimerText(textView, navigationHelper, i, null, (CountryTier) null, "");
    }

    public static void setupDisclaimerTextForGDPR(TextView textView, NavigationHelper navigationHelper, int i, Lazy<UpdatedTermsAnalyticsHelper> lazy, String str) {
        setupDisclaimerText(textView, navigationHelper, i, lazy, (CountryTier) null, str);
    }

    public static void setupDisclaimerText(TextView textView, NavigationHelper navigationHelper, int i, Lazy<UpdatedTermsAnalyticsHelper> lazy, CountryTier countryTier, String str) {
        setupDisclaimerText(textView, navigationHelper, i, lazy, countryTier, textView.getContext().getString(R.string.terms), LinkType.Terms, str);
    }

    public static void setupDisclaimerText(TextView textView, NavigationHelper navigationHelper, String str, Lazy<UpdatedTermsAnalyticsHelper> lazy, CountryTier countryTier, String str2) {
        setupDisclaimerText(textView, navigationHelper, str, lazy, countryTier, textView.getContext().getString(R.string.terms), LinkType.Terms, str2);
    }

    public static void setupCanadaDisclaimerText(TextView textView, NavigationHelper navigationHelper, int i) {
        setupDisclaimerText(textView, navigationHelper, i, null, (CountryTier) null, textView.getContext().getString(R.string.contact_us).toLowerCase(), LinkType.ContactUs, "");
    }

    private static void setupDisclaimerText(TextView textView, NavigationHelper navigationHelper, int i, Lazy<UpdatedTermsAnalyticsHelper> lazy, CountryTier countryTier, String str, LinkType linkType, String str2) {
        TextView textView2 = textView;
        Context context = textView.getContext();
        String string = context.getString(R.string.privacy_policy);
        int i2 = i;
        String string2 = context.getString(i, new Object[]{string, str});
        SpannableString spannableString = new SpannableString(string2);
        Context context2 = context;
        NavigationHelper navigationHelper2 = navigationHelper;
        SpannableString spannableString2 = spannableString;
        String str3 = string2;
        Lazy<UpdatedTermsAnalyticsHelper> lazy2 = lazy;
        CountryTier countryTier2 = countryTier;
        String str4 = str2;
        addPrivacyLinkSpan(context2, navigationHelper2, spannableString2, str3, string, LinkType.PrivacyPolicy, lazy2, countryTier2, str4);
        addPrivacyLinkSpan(context2, navigationHelper2, spannableString2, str3, str, linkType, lazy2, countryTier2, str4);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static void setupDisclaimerText(TextView textView, NavigationHelper navigationHelper, String str, Lazy<UpdatedTermsAnalyticsHelper> lazy, CountryTier countryTier, String str2, LinkType linkType, String str3) {
        TextView textView2 = textView;
        Context context = textView.getContext();
        String string = context.getString(R.string.privacy_policy);
        String str4 = str;
        String format = String.format(str, new Object[]{string, str2});
        SpannableString spannableString = new SpannableString(format);
        Context context2 = context;
        NavigationHelper navigationHelper2 = navigationHelper;
        SpannableString spannableString2 = spannableString;
        String str5 = format;
        Lazy<UpdatedTermsAnalyticsHelper> lazy2 = lazy;
        CountryTier countryTier2 = countryTier;
        String str6 = str3;
        addPrivacyLinkSpan(context2, navigationHelper2, spannableString2, str5, string, LinkType.PrivacyPolicy, lazy2, countryTier2, str6);
        addPrivacyLinkSpan(context2, navigationHelper2, spannableString2, str5, str2, linkType, lazy2, countryTier2, str6);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static PrivacyAndMarketingType getPrivacyAndMarketingType(CountryService countryService, SignUpModel signUpModel) {
        boolean z = true;
        boolean z2 = !countryService.needsToAcceptTOS(countryService.getShortNameFromLongName(signUpModel.getCountryName()));
        if (!Strings.equals(signUpModel.getRegion(), Country.UNITED_STATES_SHORT) && !Strings.equals(signUpModel.getCountryName(), Country.UNITED_STATES_LONG)) {
            z = false;
        }
        if (z2 && z) {
            return PrivacyAndMarketingType.USPrivacyAndMarketing;
        }
        if (z2 || z) {
            return PrivacyAndMarketingType.USPrivacyAndEUMarketing;
        }
        return PrivacyAndMarketingType.EUPrivacyAndMarketing;
    }

    private static void addPrivacyLinkSpan(Context context, NavigationHelper navigationHelper, SpannableString spannableString, String str, String str2, LinkType linkType, Lazy<UpdatedTermsAnalyticsHelper> lazy, CountryTier countryTier, String str3) {
        int indexOf = str.indexOf(str2);
        if (indexOf != -1) {
            PrivacyLinkTextSpan privacyLinkTextSpan = new PrivacyLinkTextSpan(context, navigationHelper, linkType, lazy, countryTier, str3);
            SpannableString spannableString2 = spannableString;
            spannableString.setSpan(privacyLinkTextSpan, indexOf, str2.length() + indexOf, 33);
        }
    }

    public static String getOnBoardingType(boolean z, Mode mode) {
        if (z) {
            return "fb";
        }
        return mode == Mode.Profile ? "sso" : "email";
    }
}
