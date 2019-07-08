package com.myfitnesspal.feature.payments.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.payments.model.MfpAvailabilityDetails;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants.BillingResponse;
import com.myfitnesspal.feature.premium.util.ProductUtils;
import com.myfitnesspal.shared.constants.Constants.Payments.GenericError;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.premium.PaymentsLogger;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Holder;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public final class GooglePlayUtil {
    private static final String TAG = "GooglePlayUtil";

    public enum SkuQueryResult {
        Available,
        Unavailabile,
        BillingUnavailable,
        ServiceNotAvailable,
        ServiceDisconnected
    }

    public static List<Tuple2<String, String>> extractSessionsAndReceipts(Context context, IInAppBillingService iInAppBillingService) {
        ArrayList arrayList = new ArrayList();
        try {
            if (BillingResponse.fromResponseCode(iInAppBillingService.isBillingSupported(3, context.getPackageName(), "subs")) != BillingResponse.BILLING_RESPONSE_RESULT_OK) {
                return arrayList;
            }
            String str = null;
            do {
                Bundle purchases = iInAppBillingService.getPurchases(3, context.getPackageName(), "subs", str);
                ArrayList stringArrayList = BundleUtils.getStringArrayList(purchases, GooglePlayConstants.BILLING_EXTRA_INAPP_PURCHASE_DATA_LIST);
                if (CollectionUtils.notEmpty((Collection<?>) stringArrayList)) {
                    Iterator it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        Tuple2 extractSessionAndReceipt = extractSessionAndReceipt((String) it.next());
                        if (extractSessionAndReceipt != null) {
                            arrayList.add(extractSessionAndReceipt);
                        }
                    }
                }
                str = BundleUtils.getString(purchases, "INAPP_CONTINUATION_TOKEN");
                PaymentsLogger.e("next pagination token=%s", str);
            } while (str != null);
            return arrayList;
        } catch (RemoteException unused) {
            PaymentsLogger.e("failed to query IInAppBillingService for receipts", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public static boolean isBillingSupported(IInAppBillingService iInAppBillingService) {
        String packageName = MyFitnessPalApp.getInstance().getPackageName();
        boolean z = false;
        try {
            BillingResponse fromResponseCode = BillingResponse.fromResponseCode(iInAppBillingService.isBillingSupported(3, packageName, "subs"));
            BillingResponse fromResponseCode2 = BillingResponse.fromResponseCode(iInAppBillingService.isBillingSupported(3, packageName, "inapp"));
            if (fromResponseCode == BillingResponse.BILLING_RESPONSE_RESULT_OK || fromResponseCode2 == BillingResponse.BILLING_RESPONSE_RESULT_OK) {
                z = true;
            }
            return z;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public static void skusAreAvailable(List<MfpProduct> list, Function1<SkuQueryResult> function1) {
        MyFitnessPalApp instance = MyFitnessPalApp.getInstance();
        Intent intent = new Intent(GooglePlayConstants.GOOGLE_BILLING_BIND_INTENT);
        final Holder holder = new Holder();
        final Holder holder2 = new Holder();
        holder.setValue(Boolean.valueOf(false));
        holder2.setValue(Boolean.valueOf(false));
        final ArrayList arrayList = new ArrayList();
        for (MfpProduct productId : list) {
            arrayList.add(productId.getProductId());
        }
        intent.setPackage("com.android.vending");
        final Function1<SkuQueryResult> function12 = function1;
        final MyFitnessPalApp myFitnessPalApp = instance;
        AnonymousClass1 r0 = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
                holder2.setValue(Boolean.valueOf(true));
                new Thread() {
                    public void run() {
                        setName("GooglePlayUtil.skusAreAvailable");
                        IInAppBillingService asInterface = Stub.asInterface(iBinder);
                        if (!GooglePlayUtil.isBillingSupported(asInterface)) {
                            FunctionUtils.invokeIfValid(function12, SkuQueryResult.BillingUnavailable);
                        } else {
                            FunctionUtils.invokeIfValid(function12, CollectionUtils.notEmpty(GooglePlayUtil.getDisplayPriceForSkus(myFitnessPalApp, asInterface, arrayList)) ? SkuQueryResult.Available : SkuQueryResult.Unavailabile);
                        }
                        holder.setValue(Boolean.valueOf(true));
                        GooglePlayUtil.unbindService(myFitnessPalApp, this, holder2);
                    }
                }.start();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                if (!((Boolean) holder.getValue()).booleanValue()) {
                    holder.setValue(Boolean.valueOf(true));
                    FunctionUtils.invokeIfValid(function12, SkuQueryResult.ServiceDisconnected);
                }
                GooglePlayUtil.unbindService(myFitnessPalApp, this, holder2);
            }
        };
        if (!instance.bindService(intent, r0, 1)) {
            FunctionUtils.invokeIfValid(function1, SkuQueryResult.ServiceNotAvailable);
        }
    }

    public static Map<String, String> getDisplayPriceForSkus(final Context context, IInAppBillingService iInAppBillingService, ArrayList<String> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(GooglePlayConstants.BILLING_EXTRA_ITEM_ID_LIST, arrayList);
        final HashMap hashMap = new HashMap();
        try {
            Enumerable.forEach((Collection<T>) iInAppBillingService.getSkuDetails(3, context.getPackageName(), "subs", bundle).getStringArrayList(GooglePlayConstants.BILLING_EXTRA_DETAILS_LIST), (Function1<T>) new Function1<String>() {
                public void execute(String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String string = jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_PRODUCT_ID);
                        String string2 = jSONObject.getString("price");
                        String string3 = jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_PRICE_CURRENCY_CODE);
                        if (Strings.notEmpty(string) && Strings.notEmpty(string2)) {
                            if (string2.toLowerCase().contains(string3.toLowerCase())) {
                                hashMap.put(string, string2);
                                return;
                            }
                            hashMap.put(string, context.getString(R.string.payment_button_price_format, new Object[]{string2, string3}));
                        }
                    } catch (JSONException unused) {
                        PaymentsLogger.e("failed to parse SKU information JSON!", new Object[0]);
                    }
                }
            });
            return hashMap;
        } catch (RemoteException unused) {
            PaymentsLogger.e("failed to query Google Play service for pricing information", new Object[0]);
            return hashMap;
        }
    }

    private static Tuple2<String, String> extractSessionAndReceipt(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return Tuple.create(jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_DEVELOPER_PAYLOAD), jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_PURCHASE_TOKEN));
        } catch (JSONException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("failed to parse payload: ");
            sb.append(str);
            PaymentsLogger.e(sb.toString(), new Object[0]);
            return null;
        }
    }

    public static Tuple2<Integer, MfpAvailabilityDetails> getAvailabilityDetailsForSku(PaymentService paymentService, GeoLocationService geoLocationService, Bundle bundle, MfpProduct mfpProduct) {
        ArrayList stringArrayList = bundle.getStringArrayList(GooglePlayConstants.BILLING_EXTRA_DETAILS_LIST);
        if (CollectionUtils.notEmpty((Collection<?>) stringArrayList)) {
            PaymentsLogger.d("validateProduct: started. processing %d skus", Integer.valueOf(stringArrayList.size()));
            Iterator it = stringArrayList.iterator();
            while (it.hasNext()) {
                try {
                    if (mfpProduct.getProductId().equals(new JSONObject((String) it.next()).getString(GooglePlayConstants.BILLING_JSON_FIELD_PRODUCT_ID))) {
                        PaymentsLogger.d("validateProduct: found matching sku!", new Object[0]);
                        for (MfpAvailabilityDetails mfpAvailabilityDetails : mfpProduct.getAvailabilityDetails()) {
                            if (ProductUtils.isProductAvailableForPurchase(mfpProduct, geoLocationService, paymentService)) {
                                return Tuple2.create(Integer.valueOf(GenericError.NO_ERROR.getErrorCode()), mfpAvailabilityDetails);
                            }
                        }
                        PaymentsLogger.d("validateProduct: NOT available, continuing...", new Object[0]);
                    } else {
                        continue;
                    }
                } catch (JSONException unused) {
                }
            }
        }
        return Tuple2.create(Integer.valueOf(GenericError.ITEM_UNAVAILABLE.getErrorCode()), null);
    }

    public static String getResponseDesc(int i) {
        String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (i <= -1000) {
            int i2 = -1000 - i;
            if (i2 >= 0 && i2 < split2.length) {
                return split2[i2];
            }
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(i));
            sb.append(":Unknown IAB Helper Error");
            return sb.toString();
        } else if (i >= 0 && i < split.length) {
            return split[i];
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(String.valueOf(i));
            sb2.append(":Unknown");
            return sb2.toString();
        }
    }

    /* access modifiers changed from: private */
    public static void unbindService(@NotNull Context context, @NotNull ServiceConnection serviceConnection, @NotNull Holder<Boolean> holder) {
        if (((Boolean) holder.getValue()).booleanValue()) {
            PaymentsLogger.d("[%s] unbinding from IInAppBillingService service inside onServiceConnected", TAG);
            holder.setValue(Boolean.valueOf(false));
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException e) {
                Ln.e(e);
            }
        }
    }
}
