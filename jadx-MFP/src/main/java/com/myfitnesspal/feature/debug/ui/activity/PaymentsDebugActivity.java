package com.myfitnesspal.feature.debug.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.model.MfpPaymentResult;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpProductFeature;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.payments.ui.activity.PaymentConfirmation;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.service.UpsellService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumInterstitialActivity;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.ui.activity.SubscriptionStatus;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.model.v2.MfpLocalizedText;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.myfitnesspal.shared.util.Toaster;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class PaymentsDebugActivity extends MfpActivity {
    private static final int QUERY_PRODUCTS = 1;
    private static final int QUERY_SUBSCRIPTIONS = 2;
    private static final int SHOW_PAYMENT_CONFIRMATION = 6;
    private static final int SHOW_PREMIUM_INTERSTITIAL = 7;
    private static final int SHOW_SUBSCRIPTION_STATUS = 8;
    private static final int SHOW_TRIAL_CONFIRMATION = 9;
    private static final int SHOW_UPSELL_FEATURE_LIST = 4;
    private static final int SHOW_UPSELL_NORMAL = 3;
    private static final int SHOW_UPSELL_SIGN_UP = 5;
    private static final String TAG = "PaymentsDebugActivity";
    /* access modifiers changed from: private */
    public PaymentsDebugListAdapter adapter = new PaymentsDebugListAdapter();
    @BindView(2131362927)
    LinearLayoutListAdapterView listView;
    /* access modifiers changed from: private */
    public OnClickListener onRowClickListener = new OnClickListener() {
        public void onClick(View view) {
            PaymentsDebugActivity.this.getNavigationHelper().withIntent(((PaymentService) PaymentsDebugActivity.this.paymentService.get()).getStartIntent(PaymentsDebugActivity.this, ((ViewHolder) view.getTag()).product)).startActivity(140);
        }
    };
    @Inject
    Lazy<PaymentService> paymentService;
    @Inject
    Lazy<ProductService> productService;
    /* access modifiers changed from: private */
    public List<MfpProduct> products = new ArrayList();
    @Inject
    Lazy<SubscriptionService> subscriptionService;
    @Inject
    Lazy<UpsellService> upsellService;

    private class PaymentsDebugListAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        private PaymentsDebugListAdapter() {
        }

        public int getCount() {
            return PaymentsDebugActivity.this.products.size();
        }

        public Object getItem(int i) {
            return PaymentsDebugActivity.this.products.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(PaymentsDebugActivity.this, R.layout.upsell_row, null);
                view.setOnClickListener(PaymentsDebugActivity.this.onRowClickListener);
                ViewHolder viewHolder = new ViewHolder();
                ButterKnife.bind((Object) viewHolder, view);
                view.setTag(viewHolder);
            }
            MfpProduct mfpProduct = (MfpProduct) PaymentsDebugActivity.this.products.get(i);
            ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            viewHolder2.product = mfpProduct;
            viewHolder2.title.setText(PaymentsDebugActivity.buildDescription(mfpProduct.getProductDescriptions()));
            viewHolder2.desc1.setText(mfpProduct.getProductCategory());
            viewHolder2.desc2.setText(mfpProduct.getProductType());
            viewHolder2.sku.setText(mfpProduct.getProductId());
            return view;
        }
    }

    static class ViewHolder {
        @BindView(2131362298)
        TextView desc1;
        @BindView(2131362299)
        TextView desc2;
        MfpProduct product;
        @BindView(2131363309)
        TextView sku;
        @BindView(2131363833)
        TextView title;

        ViewHolder() {
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.title = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
            viewHolder.desc1 = (TextView) Utils.findRequiredViewAsType(view, R.id.description1, "field 'desc1'", TextView.class);
            viewHolder.desc2 = (TextView) Utils.findRequiredViewAsType(view, R.id.description2, "field 'desc2'", TextView.class);
            viewHolder.sku = (TextView) Utils.findRequiredViewAsType(view, R.id.product_id, "field 'sku'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.title = null;
                viewHolder.desc1 = null;
                viewHolder.desc2 = null;
                viewHolder.sku = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.payments_debug_activity);
        component().inject(this);
        initializeUi();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        queryProducts();
    }

    private void addMenuItem(Menu menu, int i, String str) {
        menu.add(0, i, 0, str).setShowAsAction(4);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        addMenuItem(menu, 1, "query products");
        addMenuItem(menu, 2, "query subscriptions");
        addMenuItem(menu, 3, "show upsell (normal)");
        addMenuItem(menu, 4, "show upsell (feature list)");
        addMenuItem(menu, 5, "show upsell (sign up)");
        addMenuItem(menu, 6, "show payment confirmation");
        addMenuItem(menu, 7, "show premium interstitial");
        addMenuItem(menu, 8, "show subscription status");
        addMenuItem(menu, 9, "show trial confirmation");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
                queryProducts();
                return true;
            case 2:
                querySubscriptions();
                return true;
            case 3:
                getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent(this)).startActivity();
                return true;
            case 4:
                getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) this, (String) null, UpsellDisplayMode.FeatureScreen)).startActivity();
                return true;
            case 5:
                getNavigationHelper().withAnimations(R.anim.slide_in_right_100_medium, R.anim.slide_out_left_100_medium).withIntent(PremiumUpsellActivity.newStartIntent((Context) this, (String) null, UpsellDisplayMode.SignUp)).startActivity();
                return true;
            case 6:
                showPaymentConfirmation(false);
                return true;
            case 7:
                showPremiumInterstitial();
                return true;
            case 8:
                showSubscriptionStatus();
                return true;
            case 9:
                showPaymentConfirmation(true);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void initializeUi() {
        this.listView.setAdapter(this.adapter);
        this.listView.setDivider((Drawable) new ColorDrawable(getResources().getColor(R.color.grey_divider)), 1.0f);
    }

    private void queryProducts() {
        ((ProductService) this.productService.get()).getProducts(new Function1<List<MfpProduct>>() {
            public void execute(List<MfpProduct> list) throws RuntimeException {
                PaymentsDebugActivity paymentsDebugActivity = PaymentsDebugActivity.this;
                if (list == null) {
                    list = new ArrayList<>();
                }
                paymentsDebugActivity.products = list;
                PaymentsDebugActivity.this.adapter.notifyDataSetChanged();
            }
        });
    }

    private void querySubscriptions() {
        Toaster.showShort((Activity) this, "querying subscriptions...");
        Ln.d("--------- QUERYING SUBSCRIPTIONS FROM BACKEND ---------", new Object[0]);
        ((SubscriptionService) this.subscriptionService.get()).syncWithBackend(new Function1<List<MfpPaidSubscription>>() {
            public void execute(List<MfpPaidSubscription> list) throws RuntimeException {
                Ln.d("--------- SUCCEEDED QUERYING SUBSCRIPTIONS ---------", new Object[0]);
                for (MfpPaidSubscription mfpPaidSubscription : list) {
                    Ln.d("---------- subscription: %s", mfpPaidSubscription.getSubscriptionId());
                    for (MfpProductFeature mfpProductFeature : mfpPaidSubscription.getSubscriptionFeatures()) {
                        Ln.d("----------    feature: %s - %s", mfpProductFeature.getFeatureId(), PaymentsDebugActivity.buildDescription(mfpProductFeature.getFeatureDescriptions()));
                    }
                }
                Toaster.showShort((Activity) PaymentsDebugActivity.this, "successful! check logcat output");
            }
        }, new Function1<List<ApiException>>() {
            public void execute(List<ApiException> list) throws RuntimeException {
                Ln.d("--------- FAILED QUERYING SUBSCRIPTIONS! ---------", new Object[0]);
                Toaster.showShort((Activity) PaymentsDebugActivity.this, "failed! check logcat output");
            }
        });
    }

    private void showPaymentConfirmation(final boolean z) {
        ((ProductService) this.productService.get()).getProducts(new Function1<List<MfpProduct>>() {
            public void execute(List<MfpProduct> list) throws RuntimeException {
                if (!CollectionUtils.isEmpty((Collection<?>) list)) {
                    MfpPaymentResult mfpPaymentResult = new MfpPaymentResult((MfpProduct) list.get(0), "DEBUG_SESSION_ID", 99.98999786376953d, "android", "xx_XX", "fake-receipt", "fake-order-id");
                    PaymentsDebugActivity.this.getNavigationHelper().withIntent(PaymentConfirmation.newStartIntent(PaymentsDebugActivity.this, mfpPaymentResult, z)).startActivity();
                }
            }
        });
    }

    private void showPremiumInterstitial() {
        getNavigationHelper().withIntent(PremiumInterstitialActivity.newStartIntent(this)).startActivity();
    }

    private void showSubscriptionStatus() {
        getNavigationHelper().withIntent(SubscriptionStatus.newStartIntent(this)).startActivity();
    }

    /* access modifiers changed from: private */
    public static String buildDescription(List<MfpLocalizedText> list) {
        String str = "";
        for (MfpLocalizedText mfpLocalizedText : list) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(mfpLocalizedText.getText());
            sb.append("\n");
            str = sb.toString();
        }
        return str;
    }
}
