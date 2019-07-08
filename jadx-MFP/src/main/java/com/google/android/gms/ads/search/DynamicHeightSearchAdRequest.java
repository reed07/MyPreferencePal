package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.ads.zzyx;

public final class DynamicHeightSearchAdRequest {
    private final SearchAdRequest zzfco;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final com.google.android.gms.ads.search.SearchAdRequest.Builder zzfcp = new com.google.android.gms.ads.search.SearchAdRequest.Builder();
        private final Bundle zzfcq = new Bundle();

        public final Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zzfcp.addNetworkExtras(networkExtras);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzfcp.addNetworkExtrasBundle(cls, bundle);
            return this;
        }

        public final Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zzfcp.addCustomEventExtrasBundle(cls, bundle);
            return this;
        }

        public final Builder setPage(int i) {
            this.zzfcq.putString("csa_adPage", Integer.toString(i));
            return this;
        }

        public final Builder setNumber(int i) {
            this.zzfcq.putString("csa_number", Integer.toString(i));
            return this;
        }

        public final Builder setAdTest(boolean z) {
            this.zzfcq.putString("csa_adtest", z ? "on" : "off");
            return this;
        }

        public final Builder setChannel(String str) {
            this.zzfcq.putString("csa_channel", str);
            return this;
        }

        public final Builder setHostLanguage(String str) {
            this.zzfcq.putString("csa_hl", str);
            return this;
        }

        public final Builder setLocationColor(String str) {
            this.zzfcq.putString("csa_colorLocation", str);
            return this;
        }

        public final Builder setLocationFontSize(int i) {
            this.zzfcq.putString("csa_fontSizeLocation", Integer.toString(i));
            return this;
        }

        public final Builder setIsClickToCallEnabled(boolean z) {
            this.zzfcq.putString("csa_clickToCall", Boolean.toString(z));
            return this;
        }

        public final Builder setIsLocationEnabled(boolean z) {
            this.zzfcq.putString("csa_location", Boolean.toString(z));
            return this;
        }

        public final Builder setIsPlusOnesEnabled(boolean z) {
            this.zzfcq.putString("csa_plusOnes", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSellerRatingsEnabled(boolean z) {
            this.zzfcq.putString("csa_sellerRatings", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSiteLinksEnabled(boolean z) {
            this.zzfcq.putString("csa_siteLinks", Boolean.toString(z));
            return this;
        }

        public final Builder setCssWidth(int i) {
            this.zzfcq.putString("csa_width", Integer.toString(i));
            return this;
        }

        public final Builder setFontFamily(String str) {
            this.zzfcq.putString("csa_fontFamily", str);
            return this;
        }

        public final Builder setFontFamilyAttribution(String str) {
            this.zzfcq.putString("csa_fontFamilyAttribution", str);
            return this;
        }

        public final Builder setFontSizeAnnotation(int i) {
            this.zzfcq.putString("csa_fontSizeAnnotation", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeAttribution(int i) {
            this.zzfcq.putString("csa_fontSizeAttribution", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeDescription(int i) {
            this.zzfcq.putString("csa_fontSizeDescription", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeDomainLink(int i) {
            this.zzfcq.putString("csa_fontSizeDomainLink", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeTitle(int i) {
            this.zzfcq.putString("csa_fontSizeTitle", Integer.toString(i));
            return this;
        }

        public final Builder setColorAdBorder(String str) {
            this.zzfcq.putString("csa_colorAdBorder", str);
            return this;
        }

        public final Builder setColorAdSeparator(String str) {
            this.zzfcq.putString("csa_colorAdSeparator", str);
            return this;
        }

        public final Builder setColorAnnotation(String str) {
            this.zzfcq.putString("csa_colorAnnotation", str);
            return this;
        }

        public final Builder setColorAttribution(String str) {
            this.zzfcq.putString("csa_colorAttribution", str);
            return this;
        }

        public final Builder setColorBackground(String str) {
            this.zzfcq.putString("csa_colorBackground", str);
            return this;
        }

        public final Builder setColorBorder(String str) {
            this.zzfcq.putString("csa_colorBorder", str);
            return this;
        }

        public final Builder setColorDomainLink(String str) {
            this.zzfcq.putString("csa_colorDomainLink", str);
            return this;
        }

        public final Builder setColorText(String str) {
            this.zzfcq.putString("csa_colorText", str);
            return this;
        }

        public final Builder setColorTitleLink(String str) {
            this.zzfcq.putString("csa_colorTitleLink", str);
            return this;
        }

        public final Builder setAdBorderSelectors(String str) {
            this.zzfcq.putString("csa_adBorderSelectors", str);
            return this;
        }

        public final Builder setAdjustableLineHeight(int i) {
            this.zzfcq.putString("csa_adjustableLineHeight", Integer.toString(i));
            return this;
        }

        public final Builder setAttributionSpacingBelow(int i) {
            this.zzfcq.putString("csa_attributionSpacingBelow", Integer.toString(i));
            return this;
        }

        public final Builder setBorderSelections(String str) {
            this.zzfcq.putString("csa_borderSelections", str);
            return this;
        }

        public final Builder setIsTitleUnderlined(boolean z) {
            this.zzfcq.putString("csa_noTitleUnderline", Boolean.toString(!z));
            return this;
        }

        public final Builder setIsTitleBold(boolean z) {
            this.zzfcq.putString("csa_titleBold", Boolean.toString(z));
            return this;
        }

        public final Builder setVerticalSpacing(int i) {
            this.zzfcq.putString("csa_verticalSpacing", Integer.toString(i));
            return this;
        }

        public final Builder setDetailedAttribution(boolean z) {
            this.zzfcq.putString("csa_detailedAttribution", Boolean.toString(z));
            return this;
        }

        public final Builder setLongerHeadlines(boolean z) {
            this.zzfcq.putString("csa_longerHeadlines", Boolean.toString(z));
            return this;
        }

        public final Builder setAdvancedOptionValue(String str, String str2) {
            this.zzfcq.putString(str, str2);
            return this;
        }

        public final DynamicHeightSearchAdRequest build() {
            this.zzfcp.addNetworkExtrasBundle(AdMobAdapter.class, this.zzfcq);
            return new DynamicHeightSearchAdRequest(this);
        }

        public final Builder setQuery(String str) {
            this.zzfcp.setQuery(str);
            return this;
        }
    }

    private DynamicHeightSearchAdRequest(Builder builder) {
        this.zzfco = builder.zzfcp.build();
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzfco.getNetworkExtras(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzfco.getNetworkExtrasBundle(cls);
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzfco.getCustomEventExtrasBundle(cls);
    }

    public final String getQuery() {
        return this.zzfco.getQuery();
    }

    public final boolean isTestDevice(Context context) {
        return this.zzfco.isTestDevice(context);
    }

    /* access modifiers changed from: 0000 */
    public final zzyx zzaz() {
        return this.zzfco.zzaz();
    }
}
