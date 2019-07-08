package com.myfitnesspal.feature.fileexport.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FileExportPreview_ViewBinding implements Unbinder {
    private FileExportPreview target;

    @UiThread
    public FileExportPreview_ViewBinding(FileExportPreview fileExportPreview) {
        this(fileExportPreview, fileExportPreview.getWindow().getDecorView());
    }

    @UiThread
    public FileExportPreview_ViewBinding(FileExportPreview fileExportPreview, View view) {
        this.target = fileExportPreview;
        fileExportPreview.exportButton = Utils.findRequiredView(view, R.id.export_button, "field 'exportButton'");
        fileExportPreview.premiumCta = Utils.findRequiredView(view, R.id.premium_cta_container, "field 'premiumCta'");
    }

    @CallSuper
    public void unbind() {
        FileExportPreview fileExportPreview = this.target;
        if (fileExportPreview != null) {
            this.target = null;
            fileExportPreview.exportButton = null;
            fileExportPreview.premiumCta = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
