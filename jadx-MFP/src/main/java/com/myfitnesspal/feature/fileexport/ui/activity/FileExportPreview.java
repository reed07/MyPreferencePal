package com.myfitnesspal.feature.fileexport.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.fileexport.ui.activity.FileExport.ExportMode;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import dagger.Lazy;
import javax.inject.Inject;

public class FileExportPreview extends MfpActivity {
    private OnClickListener ctaOnClickListener;
    @BindView(2131362537)
    View exportButton;
    @BindView(2131363295)
    View premiumCta;
    @Inject
    Lazy<PremiumService> premiumService;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, FileExportPreview.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.file_export_preview);
        this.ctaOnClickListener = new OnClickListener() {
            public final void onClick(View view) {
                FileExportPreview.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) FileExportPreview.this, PremiumFeature.FileExport)).startActivity(RequestCodes.PREMIUM_UPSELL);
            }
        };
        setListeners();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 208 && i2 == -1) {
            verifyIsUserSubscribed();
        }
    }

    private void verifyIsUserSubscribed() {
        if (((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
            getNavigationHelper().withIntent(FileExport.createIntentForFileExport(this, ExportMode.Normal)).startActivity();
            finish();
        }
    }

    private void setListeners() {
        this.exportButton.setOnClickListener(this.ctaOnClickListener);
        this.premiumCta.setOnClickListener(this.ctaOnClickListener);
    }
}
