package com.myfitnesspal.feature.debug.util;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.debug.ui.activity.AdvancedDebuggingActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.VersionUtils;

public class AdvancedDebuggingUtilImpl implements AdvancedDebuggingUtil {
    private final BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
    private final Context context;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;

    public AdvancedDebuggingUtilImpl(Context context2, NavigationHelper navigationHelper2) {
        this.context = context2;
        this.navigationHelper = navigationHelper2;
    }

    public String getDisplayableVersionString() {
        Context context2 = this.context;
        Object[] objArr = new Object[3];
        objArr[0] = VersionUtils.getAppVersionName(context2);
        objArr[1] = Integer.valueOf(VersionUtils.getAppVersionCode(this.context));
        objArr[2] = this.buildConfiguration.isQABuild() ? " (QA)" : "";
        return context2.getString(R.string.version, objArr);
    }

    public void setUpVersionHandlerOn(final TextView textView) {
        if (textView != null) {
            this.navigationHelper.withContext(textView.getContext());
            StringBuilder sb = new StringBuilder();
            sb.append("Version ");
            sb.append(VersionUtils.getAppVersionName(textView.getContext()));
            textView.setText(sb.toString());
            if (shouldShowAdvancedDebuggingMenu()) {
                textView.setLongClickable(true);
                textView.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        AdvancedDebuggingUtilImpl.this.navigationHelper.withIntent(AdvancedDebuggingActivity.newStartIntent(textView.getContext())).startActivity();
                        return true;
                    }
                });
            }
        }
    }

    public boolean shouldShowAdvancedDebuggingMenu() {
        return this.buildConfiguration.isDebug() || this.buildConfiguration.isQABuild();
    }
}
