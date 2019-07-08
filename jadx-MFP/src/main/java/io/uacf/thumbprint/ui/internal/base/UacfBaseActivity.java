package io.uacf.thumbprint.ui.internal.base;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import io.uacf.thumbprint.ui.R;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkImpl;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintAppBarConfig;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintStatusBarConfig;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintUiConfig;
import java.util.LinkedList;

public abstract class UacfBaseActivity extends AppCompatActivity {
    private TextView customActionBarTitleTextView;
    private CoordinatorLayout rootLayout;
    protected Toolbar toolbar;
    protected UacfThumbprintUiConfig uiConfig;

    @LayoutRes
    public abstract int getLayoutResourceId();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.thumbprint_activity_base);
        this.rootLayout = (CoordinatorLayout) findViewById(R.id.thumbprint_root_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.thumbprint_content);
        this.uiConfig = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getUiConfig();
        configNavBar(this.uiConfig.getAppBarConfig());
        configStatusBar(this.uiConfig.getStatusBarConfig());
        int layoutResourceId = getLayoutResourceId();
        if (layoutResourceId != 0) {
            LayoutInflater.from(this).inflate(layoutResourceId, frameLayout, true);
        }
    }

    private void configStatusBar(UacfThumbprintStatusBarConfig uacfThumbprintStatusBarConfig) {
        if (uacfThumbprintStatusBarConfig != null && VERSION.SDK_INT >= 19) {
            getWindow().clearFlags(67108864);
            if (VERSION.SDK_INT >= 23) {
                if (uacfThumbprintStatusBarConfig.getSystemUiVisibility() != 0) {
                    getWindow().getDecorView().setSystemUiVisibility(uacfThumbprintStatusBarConfig.getSystemUiVisibility());
                }
                if (uacfThumbprintStatusBarConfig.getStatusBarColorResId() != 0) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getBaseContext(), uacfThumbprintStatusBarConfig.getStatusBarColorResId()));
                }
            }
        }
    }

    public void configNavBar(@NonNull UacfThumbprintAppBarConfig uacfThumbprintAppBarConfig) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.navbar_container);
        if (uacfThumbprintAppBarConfig == null || uacfThumbprintAppBarConfig.getAppBarLayout() <= 0) {
            ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.thumbprint_appbar, this.rootLayout, false);
            viewGroup.addView(viewGroup2);
            this.toolbar = (Toolbar) viewGroup2.findViewById(R.id.thumbprint_toolbar);
            this.customActionBarTitleTextView = (TextView) this.toolbar.findViewById(R.id.thumbprint_toolbar_title);
            setSupportActionBar(this.toolbar);
        } else {
            ViewGroup viewGroup3 = (ViewGroup) LayoutInflater.from(this).inflate(uacfThumbprintAppBarConfig.getAppBarLayout(), this.rootLayout, false);
            viewGroup.addView(viewGroup3);
            if (uacfThumbprintAppBarConfig.getToolbarId() > 0) {
                this.toolbar = (Toolbar) viewGroup3.findViewById(uacfThumbprintAppBarConfig.getToolbarId());
                Toolbar toolbar2 = this.toolbar;
                if (toolbar2 != null) {
                    setSupportActionBar(toolbar2);
                    int titleTextViewId = uacfThumbprintAppBarConfig.getTitleTextViewId();
                    if (titleTextViewId > 0) {
                        this.customActionBarTitleTextView = (TextView) this.toolbar.findViewById(titleTextViewId);
                    }
                } else {
                    throw new IllegalStateException("The nav bar layout provided in the UacfThumbprintUiConfig does not contain a Toolbar view with the toolbar id given to the UacfThumbprintUiAppBarConfig.");
                }
            } else {
                this.toolbar = getToolbarIfExists(viewGroup3);
                Toolbar toolbar3 = this.toolbar;
                if (toolbar3 != null) {
                    setSupportActionBar(toolbar3);
                } else {
                    throw new IllegalStateException("The nav bar layout provided in the UacfThumbprintUiConfig must contain a Toolbar view.");
                }
            }
        }
        if (uacfThumbprintAppBarConfig == null || uacfThumbprintAppBarConfig.isAppBarShadowEnabled()) {
            viewGroup.addView(LayoutInflater.from(this).inflate(R.layout.thumbprint_appbar_shadow, viewGroup, false));
        }
    }

    /* access modifiers changed from: protected */
    public void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (getCurrentFocus() != null) {
            inputMethodManager.toggleSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void setActionBarTitleText(@StringRes int i) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle((CharSequence) "");
        }
        TextView textView = this.customActionBarTitleTextView;
        if (textView != null) {
            textView.setText(i);
        } else {
            getSupportActionBar().setTitle(i);
        }
    }

    /* access modifiers changed from: protected */
    public void setActionBarTitleCentered(boolean z) {
        TextView textView = this.customActionBarTitleTextView;
        if (textView != null) {
            LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
            layoutParams.gravity = 17;
            this.customActionBarTitleTextView.setLayoutParams(layoutParams);
        }
    }

    private Toolbar getToolbarIfExists(View view) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(view);
        while (!linkedList.isEmpty()) {
            View view2 = (View) linkedList.poll();
            if (view2 instanceof Toolbar) {
                return (Toolbar) view2;
            }
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    linkedList.add(viewGroup.getChildAt(i));
                }
            }
        }
        return null;
    }
}
