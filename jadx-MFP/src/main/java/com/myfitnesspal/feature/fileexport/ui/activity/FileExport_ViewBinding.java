package com.myfitnesspal.feature.fileexport.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FileExport_ViewBinding implements Unbinder {
    private FileExport target;

    @UiThread
    public FileExport_ViewBinding(FileExport fileExport) {
        this(fileExport, fileExport.getWindow().getDecorView());
    }

    @UiThread
    public FileExport_ViewBinding(FileExport fileExport, View view) {
        this.target = fileExport;
        fileExport.reportingPeriodContainer = Utils.findRequiredView(view, R.id.reporting_period_container, "field 'reportingPeriodContainer'");
        fileExport.divider = Utils.findRequiredView(view, R.id.divider, "field 'divider'");
        fileExport.fileExportInfoContainer = Utils.findRequiredView(view, R.id.file_export_info_container, "field 'fileExportInfoContainer'");
        fileExport.dataSentInfoTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.where_data_sent_info, "field 'dataSentInfoTextView'", TextView.class);
        fileExport.changeEmailInfoTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.how_change_email_info, "field 'changeEmailInfoTextView'", TextView.class);
        fileExport.fileExportCompleteContainer = Utils.findRequiredView(view, R.id.file_export_complete_container, "field 'fileExportCompleteContainer'");
        fileExport.checkEmailTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.check_email, "field 'checkEmailTextView'", TextView.class);
        fileExport.verifyEmailContainer = Utils.findRequiredView(view, R.id.verify_email_container, "field 'verifyEmailContainer'");
        fileExport.verifyEmailInfoTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.verify_email_info, "field 'verifyEmailInfoTextView'", TextView.class);
        fileExport.exportButton = Utils.findRequiredView(view, R.id.export_button, "field 'exportButton'");
        fileExport.exportTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.export_text_view, "field 'exportTextView'", TextView.class);
        fileExport.exportImageView = Utils.findRequiredView(view, R.id.export_image_view, "field 'exportImageView'");
        fileExport.changeEmailButton = (TextView) Utils.findRequiredViewAsType(view, R.id.change_email, "field 'changeEmailButton'", TextView.class);
        fileExport.messageTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.error_text, "field 'messageTextView'", TextView.class);
        fileExport.fileExportLoadingContainer = Utils.findRequiredView(view, R.id.file_export_loading_container, "field 'fileExportLoadingContainer'");
        fileExport.reportingPeriodTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.reporting_period_text, "field 'reportingPeriodTextView'", TextView.class);
        fileExport.fileExportPremiumCta = Utils.findRequiredView(view, R.id.file_export_premium_cta, "field 'fileExportPremiumCta'");
        fileExport.howChangeEmailTitle = Utils.findRequiredView(view, R.id.how_change_email_title, "field 'howChangeEmailTitle'");
        fileExport.exportConfirmationText = (TextView) Utils.findRequiredViewAsType(view, R.id.export_confirmation_text, "field 'exportConfirmationText'", TextView.class);
        fileExport.emailVerified = Utils.findRequiredView(view, R.id.email_verified, "field 'emailVerified'");
    }

    @CallSuper
    public void unbind() {
        FileExport fileExport = this.target;
        if (fileExport != null) {
            this.target = null;
            fileExport.reportingPeriodContainer = null;
            fileExport.divider = null;
            fileExport.fileExportInfoContainer = null;
            fileExport.dataSentInfoTextView = null;
            fileExport.changeEmailInfoTextView = null;
            fileExport.fileExportCompleteContainer = null;
            fileExport.checkEmailTextView = null;
            fileExport.verifyEmailContainer = null;
            fileExport.verifyEmailInfoTextView = null;
            fileExport.exportButton = null;
            fileExport.exportTextView = null;
            fileExport.exportImageView = null;
            fileExport.changeEmailButton = null;
            fileExport.messageTextView = null;
            fileExport.fileExportLoadingContainer = null;
            fileExport.reportingPeriodTextView = null;
            fileExport.fileExportPremiumCta = null;
            fileExport.howChangeEmailTitle = null;
            fileExport.exportConfirmationText = null;
            fileExport.emailVerified = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
