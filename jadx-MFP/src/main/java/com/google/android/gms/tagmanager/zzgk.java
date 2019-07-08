package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShowFirstParty
@VisibleForTesting
public final class zzgk extends zzgh {
    private static final String ID = zza.UNIVERSAL_ANALYTICS.toString();
    private static final String zzbhj = zzb.ACCOUNT.toString();
    private static final String zzbhk = zzb.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzbhl = zzb.ENABLE_ECOMMERCE.toString();
    private static final String zzbhm = zzb.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzbhn = zzb.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzbho = zzb.ANALYTICS_FIELDS.toString();
    private static final String zzbhp = zzb.TRACK_TRANSACTION.toString();
    private static final String zzbhq = zzb.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzbhr = zzb.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzbhs = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", "add", ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern zzbht = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzbhu = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzbhv;
    private static Map<String, String> zzbhw;
    private final DataLayer zzazr;
    private final Set<String> zzbhx;
    private final zzgf zzbhy;

    public zzgk(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgf(context));
    }

    @VisibleForTesting
    private zzgk(Context context, DataLayer dataLayer, zzgf zzgf) {
        super(ID, new String[0]);
        this.zzazr = dataLayer;
        this.zzbhy = zzgf;
        this.zzbhx = new HashSet();
        this.zzbhx.add("");
        this.zzbhx.add("0");
        this.zzbhx.add("false");
    }

    private static boolean zzc(Map<String, zzp> map, String str) {
        zzp zzp = (zzp) map.get(str);
        if (zzp == null) {
            return false;
        }
        return zzgj.zzg(zzp).booleanValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(java.util.Map<java.lang.String, com.google.android.gms.internal.measurement.zzp> r11) {
        /*
            r10 = this;
            com.google.android.gms.tagmanager.zzgf r0 = r10.zzbhy
            java.lang.String r1 = "_GTM_DEFAULT_TRACKER_"
            com.google.android.gms.analytics.Tracker r0 = r0.zzec(r1)
            java.lang.String r1 = "collect_adid"
            boolean r1 = zzc(r11, r1)
            r0.enableAdvertisingIdCollection(r1)
            java.lang.String r1 = zzbhl
            boolean r1 = zzc(r11, r1)
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L_0x02c6
            com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder r1 = new com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder
            r1.<init>()
            java.lang.String r4 = zzbho
            java.lang.Object r4 = r11.get(r4)
            com.google.android.gms.internal.measurement.zzp r4 = (com.google.android.gms.internal.measurement.zzp) r4
            java.util.Map r4 = r10.zzj(r4)
            r1.setAll(r4)
            java.lang.String r5 = zzbhm
            boolean r5 = zzc(r11, r5)
            if (r5 == 0) goto L_0x0048
            com.google.android.gms.tagmanager.DataLayer r11 = r10.zzazr
            java.lang.String r5 = "ecommerce"
            java.lang.Object r11 = r11.get(r5)
            boolean r5 = r11 instanceof java.util.Map
            if (r5 == 0) goto L_0x0046
            java.util.Map r11 = (java.util.Map) r11
            goto L_0x005c
        L_0x0046:
            r11 = r3
            goto L_0x005c
        L_0x0048:
            java.lang.String r5 = zzbhn
            java.lang.Object r11 = r11.get(r5)
            com.google.android.gms.internal.measurement.zzp r11 = (com.google.android.gms.internal.measurement.zzp) r11
            java.lang.Object r11 = com.google.android.gms.tagmanager.zzgj.zzh(r11)
            boolean r5 = r11 instanceof java.util.Map
            if (r5 == 0) goto L_0x005b
            java.util.Map r11 = (java.util.Map) r11
            goto L_0x005c
        L_0x005b:
            r11 = r3
        L_0x005c:
            if (r11 == 0) goto L_0x02be
            java.lang.String r5 = "&cu"
            java.lang.Object r4 = r4.get(r5)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L_0x0070
            java.lang.String r4 = "currencyCode"
            java.lang.Object r4 = r11.get(r4)
            java.lang.String r4 = (java.lang.String) r4
        L_0x0070:
            if (r4 == 0) goto L_0x0077
            java.lang.String r5 = "&cu"
            r1.set(r5, r4)
        L_0x0077:
            java.lang.String r4 = "impressions"
            java.lang.Object r4 = r11.get(r4)
            boolean r5 = r4 instanceof java.util.List
            if (r5 == 0) goto L_0x00c2
            java.util.List r4 = (java.util.List) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x0087:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00c2
            java.lang.Object r5 = r4.next()
            java.util.Map r5 = (java.util.Map) r5
            com.google.android.gms.analytics.ecommerce.Product r6 = zzg(r5)     // Catch:{ RuntimeException -> 0x00a3 }
            java.lang.String r7 = "list"
            java.lang.Object r5 = r5.get(r7)     // Catch:{ RuntimeException -> 0x00a3 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ RuntimeException -> 0x00a3 }
            r1.addImpression(r6, r5)     // Catch:{ RuntimeException -> 0x00a3 }
            goto L_0x0087
        L_0x00a3:
            r5 = move-exception
            java.lang.String r6 = "Failed to extract a product from DataLayer. "
            java.lang.String r5 = r5.getMessage()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r7 = r5.length()
            if (r7 == 0) goto L_0x00b9
            java.lang.String r5 = r6.concat(r5)
            goto L_0x00be
        L_0x00b9:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r6)
        L_0x00be:
            com.google.android.gms.tagmanager.zzdi.e(r5)
            goto L_0x0087
        L_0x00c2:
            java.lang.String r4 = "promoClick"
            boolean r4 = r11.containsKey(r4)
            if (r4 == 0) goto L_0x00db
            java.lang.String r3 = "promoClick"
            java.lang.Object r3 = r11.get(r3)
            java.util.Map r3 = (java.util.Map) r3
            java.lang.String r4 = "promotions"
            java.lang.Object r3 = r3.get(r4)
            java.util.List r3 = (java.util.List) r3
            goto L_0x00f3
        L_0x00db:
            java.lang.String r4 = "promoView"
            boolean r4 = r11.containsKey(r4)
            if (r4 == 0) goto L_0x00f3
            java.lang.String r3 = "promoView"
            java.lang.Object r3 = r11.get(r3)
            java.util.Map r3 = (java.util.Map) r3
            java.lang.String r4 = "promotions"
            java.lang.Object r3 = r3.get(r4)
            java.util.List r3 = (java.util.List) r3
        L_0x00f3:
            if (r3 == 0) goto L_0x0188
            java.util.Iterator r3 = r3.iterator()
        L_0x00f9:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0171
            java.lang.Object r4 = r3.next()
            java.util.Map r4 = (java.util.Map) r4
            com.google.android.gms.analytics.ecommerce.Promotion r5 = new com.google.android.gms.analytics.ecommerce.Promotion     // Catch:{ RuntimeException -> 0x0152 }
            r5.<init>()     // Catch:{ RuntimeException -> 0x0152 }
            java.lang.String r6 = "id"
            java.lang.Object r6 = r4.get(r6)     // Catch:{ RuntimeException -> 0x0152 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ RuntimeException -> 0x0152 }
            if (r6 == 0) goto L_0x011b
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ RuntimeException -> 0x0152 }
            r5.setId(r6)     // Catch:{ RuntimeException -> 0x0152 }
        L_0x011b:
            java.lang.String r6 = "name"
            java.lang.Object r6 = r4.get(r6)     // Catch:{ RuntimeException -> 0x0152 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ RuntimeException -> 0x0152 }
            if (r6 == 0) goto L_0x012c
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ RuntimeException -> 0x0152 }
            r5.setName(r6)     // Catch:{ RuntimeException -> 0x0152 }
        L_0x012c:
            java.lang.String r6 = "creative"
            java.lang.Object r6 = r4.get(r6)     // Catch:{ RuntimeException -> 0x0152 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ RuntimeException -> 0x0152 }
            if (r6 == 0) goto L_0x013d
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ RuntimeException -> 0x0152 }
            r5.setCreative(r6)     // Catch:{ RuntimeException -> 0x0152 }
        L_0x013d:
            java.lang.String r6 = "position"
            java.lang.Object r4 = r4.get(r6)     // Catch:{ RuntimeException -> 0x0152 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ RuntimeException -> 0x0152 }
            if (r4 == 0) goto L_0x014e
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ RuntimeException -> 0x0152 }
            r5.setPosition(r4)     // Catch:{ RuntimeException -> 0x0152 }
        L_0x014e:
            r1.addPromotion(r5)     // Catch:{ RuntimeException -> 0x0152 }
            goto L_0x00f9
        L_0x0152:
            r4 = move-exception
            java.lang.String r5 = "Failed to extract a promotion from DataLayer. "
            java.lang.String r4 = r4.getMessage()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r6 = r4.length()
            if (r6 == 0) goto L_0x0168
            java.lang.String r4 = r5.concat(r4)
            goto L_0x016d
        L_0x0168:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
        L_0x016d:
            com.google.android.gms.tagmanager.zzdi.e(r4)
            goto L_0x00f9
        L_0x0171:
            java.lang.String r3 = "promoClick"
            boolean r3 = r11.containsKey(r3)
            if (r3 == 0) goto L_0x0181
            java.lang.String r3 = "&promoa"
            java.lang.String r4 = "click"
            r1.set(r3, r4)
            goto L_0x0189
        L_0x0181:
            java.lang.String r2 = "&promoa"
            java.lang.String r3 = "view"
            r1.set(r2, r3)
        L_0x0188:
            r2 = 1
        L_0x0189:
            if (r2 == 0) goto L_0x02be
            java.util.List<java.lang.String> r2 = zzbhs
            java.util.Iterator r2 = r2.iterator()
        L_0x0191:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x02be
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = r11.containsKey(r3)
            if (r4 == 0) goto L_0x0191
            java.lang.Object r11 = r11.get(r3)
            java.util.Map r11 = (java.util.Map) r11
            java.lang.String r2 = "products"
            java.lang.Object r2 = r11.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x01ea
            java.util.Iterator r2 = r2.iterator()
        L_0x01b7:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x01ea
            java.lang.Object r4 = r2.next()
            java.util.Map r4 = (java.util.Map) r4
            com.google.android.gms.analytics.ecommerce.Product r4 = zzg(r4)     // Catch:{ RuntimeException -> 0x01cb }
            r1.addProduct(r4)     // Catch:{ RuntimeException -> 0x01cb }
            goto L_0x01b7
        L_0x01cb:
            r4 = move-exception
            java.lang.String r5 = "Failed to extract a product from DataLayer. "
            java.lang.String r4 = r4.getMessage()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r6 = r4.length()
            if (r6 == 0) goto L_0x01e1
            java.lang.String r4 = r5.concat(r4)
            goto L_0x01e6
        L_0x01e1:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
        L_0x01e6:
            com.google.android.gms.tagmanager.zzdi.e(r4)
            goto L_0x01b7
        L_0x01ea:
            java.lang.String r2 = "actionField"
            boolean r2 = r11.containsKey(r2)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r2 == 0) goto L_0x0297
            java.lang.String r2 = "actionField"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ RuntimeException -> 0x02a0 }
            java.util.Map r11 = (java.util.Map) r11     // Catch:{ RuntimeException -> 0x02a0 }
            com.google.android.gms.analytics.ecommerce.ProductAction r2 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x02a0 }
            r2.<init>(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            java.lang.String r3 = "id"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x020e
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setTransactionId(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x020e:
            java.lang.String r3 = "affiliation"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x021d
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setTransactionAffiliation(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x021d:
            java.lang.String r3 = "coupon"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x022c
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setTransactionCouponCode(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x022c:
            java.lang.String r3 = "list"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x023b
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setProductActionList(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x023b:
            java.lang.String r3 = "option"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x024a
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setCheckoutOptions(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x024a:
            java.lang.String r3 = "revenue"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x025d
            java.lang.Double r3 = zzn(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setTransactionRevenue(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x025d:
            java.lang.String r3 = "tax"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x0270
            java.lang.Double r3 = zzn(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setTransactionTax(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x0270:
            java.lang.String r3 = "shipping"
            java.lang.Object r3 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r3 == 0) goto L_0x0283
            java.lang.Double r3 = zzn(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            double r3 = r3.doubleValue()     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setTransactionShipping(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x0283:
            java.lang.String r3 = "step"
            java.lang.Object r11 = r11.get(r3)     // Catch:{ RuntimeException -> 0x02a0 }
            if (r11 == 0) goto L_0x029c
            java.lang.Integer r11 = zzo(r11)     // Catch:{ RuntimeException -> 0x02a0 }
            int r11 = r11.intValue()     // Catch:{ RuntimeException -> 0x02a0 }
            r2.setCheckoutStep(r11)     // Catch:{ RuntimeException -> 0x02a0 }
            goto L_0x029c
        L_0x0297:
            com.google.android.gms.analytics.ecommerce.ProductAction r2 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x02a0 }
            r2.<init>(r3)     // Catch:{ RuntimeException -> 0x02a0 }
        L_0x029c:
            r1.setProductAction(r2)     // Catch:{ RuntimeException -> 0x02a0 }
            goto L_0x02be
        L_0x02a0:
            r11 = move-exception
            java.lang.String r2 = "Failed to extract a product action from DataLayer. "
            java.lang.String r11 = r11.getMessage()
            java.lang.String r11 = java.lang.String.valueOf(r11)
            int r3 = r11.length()
            if (r3 == 0) goto L_0x02b6
            java.lang.String r11 = r2.concat(r11)
            goto L_0x02bb
        L_0x02b6:
            java.lang.String r11 = new java.lang.String
            r11.<init>(r2)
        L_0x02bb:
            com.google.android.gms.tagmanager.zzdi.e(r11)
        L_0x02be:
            java.util.Map r11 = r1.build()
            r0.send(r11)
            return
        L_0x02c6:
            java.lang.String r1 = zzbhk
            boolean r1 = zzc(r11, r1)
            if (r1 == 0) goto L_0x02de
            java.lang.String r1 = zzbho
            java.lang.Object r11 = r11.get(r1)
            com.google.android.gms.internal.measurement.zzp r11 = (com.google.android.gms.internal.measurement.zzp) r11
            java.util.Map r11 = r10.zzj(r11)
            r0.send(r11)
            return
        L_0x02de:
            java.lang.String r1 = zzbhp
            boolean r1 = zzc(r11, r1)
            if (r1 == 0) goto L_0x047c
            java.lang.String r1 = "transactionId"
            java.lang.String r1 = r10.zzeh(r1)
            if (r1 != 0) goto L_0x02f4
            java.lang.String r11 = "Cannot find transactionId in data layer."
            com.google.android.gms.tagmanager.zzdi.e(r11)
            return
        L_0x02f4:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.lang.String r5 = zzbho     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r5 = r11.get(r5)     // Catch:{ IllegalArgumentException -> 0x0475 }
            com.google.android.gms.internal.measurement.zzp r5 = (com.google.android.gms.internal.measurement.zzp) r5     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Map r5 = r10.zzj(r5)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r6 = "&t"
            java.lang.String r7 = "transaction"
            r5.put(r6, r7)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r6 = zzbhq     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r6 = r11.get(r6)     // Catch:{ IllegalArgumentException -> 0x0475 }
            com.google.android.gms.internal.measurement.zzp r6 = (com.google.android.gms.internal.measurement.zzp) r6     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r6 == 0) goto L_0x031b
            java.util.Map r6 = zzi(r6)     // Catch:{ IllegalArgumentException -> 0x0475 }
            goto L_0x0352
        L_0x031b:
            java.util.Map<java.lang.String, java.lang.String> r6 = zzbhv     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r6 != 0) goto L_0x0350
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ IllegalArgumentException -> 0x0475 }
            r6.<init>()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "transactionId"
            java.lang.String r8 = "&ti"
            r6.put(r7, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "transactionAffiliation"
            java.lang.String r8 = "&ta"
            r6.put(r7, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "transactionTax"
            java.lang.String r8 = "&tt"
            r6.put(r7, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "transactionShipping"
            java.lang.String r8 = "&ts"
            r6.put(r7, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "transactionTotal"
            java.lang.String r8 = "&tr"
            r6.put(r7, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "transactionCurrency"
            java.lang.String r8 = "&cu"
            r6.put(r7, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            zzbhv = r6     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x0350:
            java.util.Map<java.lang.String, java.lang.String> r6 = zzbhv     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x0352:
            java.util.Set r6 = r6.entrySet()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x035a:
            boolean r7 = r6.hasNext()     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r7 == 0) goto L_0x037a
            java.lang.Object r7 = r6.next()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r8 = r7.getValue()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r7 = r7.getKey()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = r10.zzeh(r7)     // Catch:{ IllegalArgumentException -> 0x0475 }
            zzd(r5, r8, r7)     // Catch:{ IllegalArgumentException -> 0x0475 }
            goto L_0x035a
        L_0x037a:
            r4.add(r5)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r5 = "transactionProducts"
            com.google.android.gms.tagmanager.DataLayer r6 = r10.zzazr     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r5 != 0) goto L_0x0388
            goto L_0x03ad
        L_0x0388:
            boolean r3 = r5 instanceof java.util.List     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r3 == 0) goto L_0x046d
            r3 = r5
            java.util.List r3 = (java.util.List) r3     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x0393:
            boolean r6 = r3.hasNext()     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r6 == 0) goto L_0x03aa
            java.lang.Object r6 = r3.next()     // Catch:{ IllegalArgumentException -> 0x0475 }
            boolean r6 = r6 instanceof java.util.Map     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r6 == 0) goto L_0x03a2
            goto L_0x0393
        L_0x03a2:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r0 = "Each element of transactionProducts should be of type Map."
            r11.<init>(r0)     // Catch:{ IllegalArgumentException -> 0x0475 }
            throw r11     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x03aa:
            r3 = r5
            java.util.List r3 = (java.util.List) r3     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x03ad:
            if (r3 == 0) goto L_0x0458
            java.util.Iterator r3 = r3.iterator()     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x03b3:
            boolean r5 = r3.hasNext()     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r5 == 0) goto L_0x0458
            java.lang.Object r5 = r3.next()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Map r5 = (java.util.Map) r5     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r6 = "name"
            java.lang.Object r6 = r5.get(r6)     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r6 != 0) goto L_0x03cd
            java.lang.String r11 = "Unable to send transaction item hit due to missing 'name' field."
            com.google.android.gms.tagmanager.zzdi.e(r11)     // Catch:{ IllegalArgumentException -> 0x0475 }
            return
        L_0x03cd:
            java.lang.String r6 = zzbho     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r6 = r11.get(r6)     // Catch:{ IllegalArgumentException -> 0x0475 }
            com.google.android.gms.internal.measurement.zzp r6 = (com.google.android.gms.internal.measurement.zzp) r6     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Map r6 = r10.zzj(r6)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "&t"
            java.lang.String r8 = "item"
            r6.put(r7, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = "&ti"
            r6.put(r7, r1)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r7 = zzbhr     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r7 = r11.get(r7)     // Catch:{ IllegalArgumentException -> 0x0475 }
            com.google.android.gms.internal.measurement.zzp r7 = (com.google.android.gms.internal.measurement.zzp) r7     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r7 == 0) goto L_0x03f4
            java.util.Map r7 = zzi(r7)     // Catch:{ IllegalArgumentException -> 0x0475 }
            goto L_0x042b
        L_0x03f4:
            java.util.Map<java.lang.String, java.lang.String> r7 = zzbhw     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r7 != 0) goto L_0x0429
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ IllegalArgumentException -> 0x0475 }
            r7.<init>()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = "name"
            java.lang.String r9 = "&in"
            r7.put(r8, r9)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = "sku"
            java.lang.String r9 = "&ic"
            r7.put(r8, r9)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = "category"
            java.lang.String r9 = "&iv"
            r7.put(r8, r9)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = "price"
            java.lang.String r9 = "&ip"
            r7.put(r8, r9)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = "quantity"
            java.lang.String r9 = "&iq"
            r7.put(r8, r9)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = "currency"
            java.lang.String r9 = "&cu"
            r7.put(r8, r9)     // Catch:{ IllegalArgumentException -> 0x0475 }
            zzbhw = r7     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x0429:
            java.util.Map<java.lang.String, java.lang.String> r7 = zzbhw     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x042b:
            java.util.Set r7 = r7.entrySet()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x0433:
            boolean r8 = r7.hasNext()     // Catch:{ IllegalArgumentException -> 0x0475 }
            if (r8 == 0) goto L_0x0453
            java.lang.Object r8 = r7.next()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r9 = r8.getValue()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r8 = r8.getKey()     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.Object r8 = r5.get(r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ IllegalArgumentException -> 0x0475 }
            zzd(r6, r9, r8)     // Catch:{ IllegalArgumentException -> 0x0475 }
            goto L_0x0433
        L_0x0453:
            r4.add(r6)     // Catch:{ IllegalArgumentException -> 0x0475 }
            goto L_0x03b3
        L_0x0458:
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ IllegalArgumentException -> 0x0475 }
            int r11 = r4.size()     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x045e:
            if (r2 >= r11) goto L_0x046c
            java.lang.Object r1 = r4.get(r2)     // Catch:{ IllegalArgumentException -> 0x0475 }
            int r2 = r2 + 1
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ IllegalArgumentException -> 0x0475 }
            r0.send(r1)     // Catch:{ IllegalArgumentException -> 0x0475 }
            goto L_0x045e
        L_0x046c:
            return
        L_0x046d:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0475 }
            java.lang.String r0 = "transactionProducts should be of type List."
            r11.<init>(r0)     // Catch:{ IllegalArgumentException -> 0x0475 }
            throw r11     // Catch:{ IllegalArgumentException -> 0x0475 }
        L_0x0475:
            r11 = move-exception
            java.lang.String r0 = "Unable to send transaction"
            com.google.android.gms.tagmanager.zzdi.zza(r0, r11)
            return
        L_0x047c:
            java.lang.String r11 = "Ignoring unknown tag."
            com.google.android.gms.tagmanager.zzdi.zzab(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgk.zze(java.util.Map):void");
    }

    private final String zzeh(String str) {
        Object obj = this.zzazr.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private static Product zzg(Map<String, Object> map) {
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get(Attributes.CATEGORY);
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get("coupon");
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(zzo(obj7).intValue());
        }
        Object obj8 = map.get("price");
        if (obj8 != null) {
            product.setPrice(zzn(obj8).doubleValue());
        }
        Object obj9 = map.get("quantity");
        if (obj9 != null) {
            product.setQuantity(zzo(obj9).intValue());
        }
        for (String str : map.keySet()) {
            Matcher matcher = zzbht.matcher(str);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str)));
                } catch (NumberFormatException unused) {
                    String str2 = "illegal number in custom dimension value: ";
                    String valueOf = String.valueOf(str);
                    zzdi.zzab(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            } else {
                Matcher matcher2 = zzbhu.matcher(str);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzo(map.get(str)).intValue());
                    } catch (NumberFormatException unused2) {
                        String str3 = "illegal number in custom metric value: ";
                        String valueOf2 = String.valueOf(str);
                        zzdi.zzab(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                    }
                }
            }
        }
        return product;
    }

    private static Map<String, String> zzi(zzp zzp) {
        Object zzh = zzgj.zzh(zzp);
        if (!(zzh instanceof Map)) {
            return null;
        }
        Map map = (Map) zzh;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private final Map<String, String> zzj(zzp zzp) {
        if (zzp == null) {
            return new HashMap();
        }
        Map<String, String> zzi = zzi(zzp);
        if (zzi == null) {
            return new HashMap();
        }
        String str = (String) zzi.get("&aip");
        if (str != null && this.zzbhx.contains(str.toLowerCase())) {
            zzi.remove("&aip");
        }
        return zzi;
    }

    private static Double zzn(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String str = "Cannot convert the object to Double: ";
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            String str2 = "Cannot convert the object to Double: ";
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
        }
    }

    private static Integer zzo(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String str = "Cannot convert the object to Integer: ";
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            String str2 = "Cannot convert the object to Integer: ";
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
        }
    }

    public final /* bridge */ /* synthetic */ zzp zzc(Map map) {
        return super.zzc(map);
    }

    public final /* bridge */ /* synthetic */ boolean zznk() {
        return super.zznk();
    }

    public final /* bridge */ /* synthetic */ Set zzou() {
        return super.zzou();
    }

    public final /* bridge */ /* synthetic */ String zzot() {
        return super.zzot();
    }
}
