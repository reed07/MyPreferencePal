package com.myfitnesspal.feature.premium.util;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpAvailabilityDetails;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription.SubscriptionStatus;
import com.myfitnesspal.feature.payments.model.MfpPlatformDetails;
import com.myfitnesspal.feature.payments.model.MfpPlatformDetails.PlatformName;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpProductFeature;
import com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails;
import com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProductUtils {
    private static final int DAYS_PER_MONTH = 30;
    private static final int DAYS_PER_YEAR = 365;
    private static final String PRODUCT_AVAILABILITY_FORMAT = Format.newIso8601DateFormat().toPattern();

    /* renamed from: com.myfitnesspal.feature.premium.util.ProductUtils$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit = new int[FrequencyUnit.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails$FrequencyUnit[] r0 = com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit = r0
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails$FrequencyUnit r1 = com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit.DAY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit     // Catch:{ NoSuchFieldError -> 0x001f }
                com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails$FrequencyUnit r1 = com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit.WEEK     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit     // Catch:{ NoSuchFieldError -> 0x002a }
                com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails$FrequencyUnit r1 = com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit.MONTH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails$FrequencyUnit r1 = com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit.YEAR     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.premium.util.ProductUtils.AnonymousClass1.<clinit>():void");
        }
    }

    public static String formatSubscriptionDuration(Context context, MfpSubscriptionDetails mfpSubscriptionDetails) {
        return formatSubscriptionDuration(context, mfpSubscriptionDetails.getFrequencyUnit());
    }

    public static boolean isFeatureAvailable(String str, List<MfpProduct> list) {
        return isFeatureAvailable(PremiumFeature.getFeature(str), list);
    }

    public static boolean isFeatureAvailable(PremiumFeature premiumFeature, List<MfpProduct> list) {
        if (premiumFeature == null || CollectionUtils.isEmpty((Collection<?>) list)) {
            return false;
        }
        for (MfpProduct productFeatures : list) {
            Iterator it = productFeatures.getProductFeatures().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (premiumFeature.getFeatureId().equals(((MfpProductFeature) it.next()).getFeatureId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String formatSubscriptionDuration(Context context, MfpPaidSubscription mfpPaidSubscription) {
        return formatSubscriptionDuration(context, mfpPaidSubscription.getSubscriptionDetails().getFrequencyUnit());
    }

    public static String formatSubscriptionDuration(Context context, FrequencyUnit frequencyUnit) {
        int i = AnonymousClass1.$SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit[frequencyUnit.ordinal()];
        int i2 = R.string.daily_frequency;
        switch (i) {
            case 2:
                i2 = R.string.weekly_frequency;
                break;
            case 3:
                i2 = R.string.monthly_frequency;
                break;
            case 4:
                i2 = R.string.yearly_frequency;
                break;
        }
        return context.getResources().getString(i2);
    }

    public static List<MfpProduct> filterByAvailability(List<MfpProduct> list, GeoLocationService geoLocationService, PaymentService paymentService) {
        ArrayList arrayList = new ArrayList();
        for (MfpProduct mfpProduct : list) {
            if (isProductAvailableForPurchase(mfpProduct, geoLocationService, paymentService)) {
                arrayList.add(mfpProduct);
            }
        }
        return arrayList;
    }

    public static boolean isAvailableOnThisPlatform(PaymentService paymentService, MfpAvailabilityDetails mfpAvailabilityDetails) {
        if (mfpAvailabilityDetails == null) {
            return false;
        }
        return Enumerable.any(mfpAvailabilityDetails.getAvailablePlatforms(), new ReturningFunction1() {
            public final Object execute(Object obj) {
                return ProductUtils.lambda$isAvailableOnThisPlatform$0(PaymentService.this, (MfpPlatformDetails) obj);
            }
        });
    }

    static /* synthetic */ Boolean lambda$isAvailableOnThisPlatform$0(PaymentService paymentService, MfpPlatformDetails mfpPlatformDetails) throws RuntimeException {
        if (mfpPlatformDetails.getPlatformName() == PlatformName.ANDROID) {
            for (String isPaymentProviderAvailable : mfpPlatformDetails.getPaymentProviders()) {
                if (paymentService.isPaymentProviderAvailable(isPaymentProviderAvailable)) {
                    return Boolean.valueOf(true);
                }
            }
        }
        return Boolean.valueOf(false);
    }

    public static boolean isProductAvailableForPurchase(MfpProduct mfpProduct, GeoLocationService geoLocationService, PaymentService paymentService) {
        if (!"active".equals(mfpProduct.getProductStatus())) {
            return false;
        }
        List<MfpAvailabilityDetails> availabilityDetails = mfpProduct.getAvailabilityDetails();
        if (availabilityDetails == null) {
            Ln.e("isProductAvailableForPurchase had NULL availability details!", new Object[0]);
            return false;
        }
        if (CollectionUtils.notEmpty((Collection<?>) availabilityDetails)) {
            for (MfpAvailabilityDetails mfpAvailabilityDetails : availabilityDetails) {
                if (mfpAvailabilityDetails.getAvailablePlatforms() == null) {
                    Ln.e("isProductAvailableForPurchase had NULL product details!", new Object[0]);
                } else if (isAvailableOnThisPlatform(paymentService, mfpAvailabilityDetails)) {
                    Date parse = DateTimeUtils.parse(PRODUCT_AVAILABILITY_FORMAT, mfpAvailabilityDetails.getAvailabilityDate());
                    Date date = new Date();
                    boolean z = parse != null && (parse.before(date) || parse.equals(date));
                    boolean equals = geoLocationService.getCountryCode().equals(mfpAvailabilityDetails.getCountryCode());
                    if (z && equals) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    public static MfpPaidSubscription getMostRecentActiveSubscription(List<MfpPaidSubscription> list) {
        ArrayList<MfpPaidSubscription> arrayList = new ArrayList<>();
        for (MfpPaidSubscription mfpPaidSubscription : list) {
            if (mfpPaidSubscription.getSubscriptionStatus() == SubscriptionStatus.ACTIVE) {
                arrayList.add(mfpPaidSubscription);
            }
        }
        Collections.sort(arrayList, $$Lambda$ProductUtils$9BPgOwTWy_ZrUxMpbSuKaVeCYeo.INSTANCE);
        for (MfpPaidSubscription mfpPaidSubscription2 : arrayList) {
            if (mfpPaidSubscription2.getPaymentDetails().getPlatformDetails().getPlatformName() == PlatformName.ANDROID) {
                return mfpPaidSubscription2;
            }
        }
        return arrayList.size() > 0 ? (MfpPaidSubscription) arrayList.get(0) : null;
    }

    public static String getCurrencyForProduct(MfpProduct mfpProduct, GeoLocationService geoLocationService, String str) {
        if (mfpProduct != null && CollectionUtils.notEmpty((Collection<?>) mfpProduct.getAvailabilityDetails())) {
            for (MfpAvailabilityDetails mfpAvailabilityDetails : mfpProduct.getAvailabilityDetails()) {
                if (Strings.equals(geoLocationService.getCountryCode(), mfpAvailabilityDetails.getCountryCode())) {
                    return mfpAvailabilityDetails.getPricePoint().getCurrency();
                }
            }
        }
        return str;
    }

    public static void sortProductsBySubscriptionDurationAscending(List<MfpProduct> list) {
        Collections.sort(list, $$Lambda$ProductUtils$BIvjAMmufo0AFQSyHLbUuRFKFk.INSTANCE);
    }

    private static int getDurationCompareKey(MfpProduct mfpProduct) {
        MfpSubscriptionDetails subscriptionDetails = mfpProduct.getSubscriptionDetails();
        if (subscriptionDetails != null) {
            int frequencyInterval = subscriptionDetails.getFrequencyInterval();
            FrequencyUnit frequencyUnit = subscriptionDetails.getFrequencyUnit();
            if (frequencyUnit != null) {
                int i = AnonymousClass1.$SwitchMap$com$myfitnesspal$feature$payments$model$MfpSubscriptionDetails$FrequencyUnit[frequencyUnit.ordinal()];
                if (i == 1) {
                    return frequencyInterval;
                }
                switch (i) {
                    case 3:
                        return frequencyInterval * 30;
                    case 4:
                        return frequencyInterval * DAYS_PER_YEAR;
                }
            }
        }
        return 0;
    }
}
