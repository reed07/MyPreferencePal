package com.myfitnesspal.feature.payments.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import butterknife.BindView;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpPaymentResult;
import com.myfitnesspal.shared.constants.Constants.Payments.GenericError;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;

public class MockPaymentActivity extends PaymentActivityBase {
    private static final String MAGIC_MOCK_PAYMENT_RECEIPT_TEXT = "test-token";
    private static final String MOCK_ORDER_ID = "mock_payment_order_id";
    private static final int SUCCESSFUL_RESPONSE_DELAY_MILLIS = 2000;
    @BindView(2131362040)
    View cancelButton;
    @BindView(2131362494)
    EditText errorCodeEdit;
    @BindView(2131362042)
    View failButton;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    @BindView(2131362045)
    View successButton;
    /* access modifiers changed from: private */
    public final Runnable successRunnable = new Runnable() {
        public void run() {
            MockPaymentActivity mockPaymentActivity = MockPaymentActivity.this;
            MfpPaymentResult mfpPaymentResult = new MfpPaymentResult(mockPaymentActivity.getProduct(), "MOCK_SESSION_ID", 0.99d, "google", MockPaymentActivity.this.getGeoLocationService().getCountryCode(), MockPaymentActivity.MAGIC_MOCK_PAYMENT_RECEIPT_TEXT, MockPaymentActivity.MOCK_ORDER_ID);
            mockPaymentActivity.setPaymentSuccess(mfpPaymentResult);
        }
    };

    public boolean showToolbar() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.mock_payment_activity);
        this.successButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MockPaymentActivity.this.startProgress();
                MockPaymentActivity.this.handler.postDelayed(MockPaymentActivity.this.successRunnable, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
            }
        });
        this.cancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MockPaymentActivity.this.startProgress();
                MockPaymentActivity.this.setPaymentFailed(GenericError.USER_CANCELED);
            }
        });
        this.failButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MockPaymentActivity.this.startProgress();
                MockPaymentActivity.this.setPaymentFailed(GenericError.fromErrorCode(NumberUtils.tryParseInt(Strings.toString(MockPaymentActivity.this.errorCodeEdit.getText()))));
            }
        });
    }

    /* access modifiers changed from: private */
    public void startProgress() {
        findById(R.id.button_bar).setVisibility(8);
        findById(R.id.progress_indicator).setVisibility(0);
    }
}
