package com.myfitnesspal.feature.help.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.Calendar;
import javax.inject.Inject;

public class AboutUs extends MfpActivity {
    @Inject
    Lazy<AdvancedDebuggingUtil> advancedDebuggingUtil;
    int color;
    @BindView(2131362219)
    TextView copyright;
    int textHighlight;

    public String getAnalyticsScreenTag() {
        return Screens.ABOUT_US;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AboutUs.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.about_us_header);
        this.color = getResources().getColor(R.color.mfp_blue);
        this.textHighlight = getResources().getColor(R.color.mfp_orange);
        ((TextView) findById(R.id.version)).setText(((AdvancedDebuggingUtil) this.advancedDebuggingUtil.get()).getDisplayableVersionString());
        TextView textView = (TextView) findById(R.id.txtRate);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getString(R.string.rate));
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                try {
                    AboutUs.this.performHighlight(view, spannableStringBuilder);
                    AboutUs.this.getNavigationHelper().withIntent(SharedIntents.newUriIntent("market://details?id=com.myfitnesspal.android")).startActivity();
                } catch (Exception e) {
                    Ln.e(e);
                }
            }
        }, 0, getString(R.string.rate).length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.color), 0, getString(R.string.rate).length(), 33);
        textView.setText(spannableStringBuilder);
        this.copyright.setText(getString(R.string.copyright, new Object[]{Integer.valueOf(Calendar.getInstance().get(1))}));
    }

    /* access modifiers changed from: private */
    public void performHighlight(View view, SpannableStringBuilder spannableStringBuilder) {
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.textHighlight), 0, spannableStringBuilder.length(), 33);
        TextView textView = (TextView) view;
        textView.setText(spannableStringBuilder);
        new Thread(new Runnable(spannableStringBuilder, textView) {
            private final /* synthetic */ SpannableStringBuilder f$1;
            private final /* synthetic */ TextView f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                AboutUs.lambda$performHighlight$1(AboutUs.this, this.f$1, this.f$2);
            }
        }).start();
    }

    public static /* synthetic */ void lambda$performHighlight$1(AboutUs aboutUs, SpannableStringBuilder spannableStringBuilder, TextView textView) {
        try {
            Thread.sleep(500);
            aboutUs.runOnUiThread(new Runnable(spannableStringBuilder, textView) {
                private final /* synthetic */ SpannableStringBuilder f$1;
                private final /* synthetic */ TextView f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    AboutUs.lambda$null$0(AboutUs.this, this.f$1, this.f$2);
                }
            });
        } catch (InterruptedException e) {
            Ln.e(e);
        }
    }

    public static /* synthetic */ void lambda$null$0(AboutUs aboutUs, SpannableStringBuilder spannableStringBuilder, TextView textView) {
        spannableStringBuilder.setSpan(new ForegroundColorSpan(aboutUs.color), 0, spannableStringBuilder.length(), 33);
        textView.setText(spannableStringBuilder);
    }
}
