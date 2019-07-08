package com.myfitnesspal.shared.ui.navigation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.exoplayer2.C;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.Serializable;

public class NavigationHelper<T extends NavigationHelper> {
    private static final int RESULT_NONE = 9999;
    private String action;
    private ActivityOptions activityOptions;
    private Context context;
    private boolean disableStartFromFragment;
    private int enterAnim = -1;
    private int excludeFlags;
    private int exitAnim = -1;
    private boolean finishActivityAfterNavigation;
    private Fragment fragment;
    private int includeFlags;
    private Intent intent;
    private boolean isContextActivity;
    private String packageName;
    private int resultCode = 9999;
    private Intent resultData;
    private Bundle withExtras = new Bundle();

    public T setContext(Context context2) {
        this.isContextActivity = context2 instanceof Activity;
        this.context = context2;
        return this;
    }

    public Context getContext() {
        return this.context;
    }

    public T withContext(Context context2) {
        setContext(context2);
        return this;
    }

    public T fromFragment(Fragment fragment2) {
        this.fragment = fragment2;
        return this;
    }

    public boolean startingFromFragment() {
        return this.fragment != null;
    }

    public T finishActivityAfterNavigation() {
        finishActivityAfterNavigation(true);
        return this;
    }

    public T finishActivityAfterNavigation(boolean z) {
        this.finishActivityAfterNavigation = z;
        return this;
    }

    public T setResultOk() {
        return setResult(-1);
    }

    public T setResultCanceled() {
        return setResult(0);
    }

    public T setResult(int i) {
        return setResult(i, new Intent());
    }

    public T setResult(int i, Intent intent2) {
        this.resultCode = i;
        this.resultData = intent2;
        return this;
    }

    public T withExtras(Bundle bundle) {
        if (bundle != null) {
            this.withExtras.putAll(bundle);
        }
        return this;
    }

    public T withExtra(String str, String str2) {
        extras().putString(str, str2);
        return this;
    }

    public T withExtra(String str, boolean z) {
        extras().putBoolean(str, z);
        return this;
    }

    public T withExtra(String str, int i) {
        extras().putInt(str, i);
        return this;
    }

    public T withExtra(String str, long j) {
        extras().putLong(str, j);
        return this;
    }

    public T withExtra(String str, float f) {
        extras().putFloat(str, f);
        return this;
    }

    public T withExtra(String str, Parcelable parcelable) {
        extras().putParcelable(str, parcelable);
        return this;
    }

    public T asTopLevelActivity() {
        return asTopLevelActivity(true);
    }

    public T asTopLevelActivity(boolean z) {
        extras().putBoolean(Extras.SHOW_AS_TOP_LEVEL_ACTIVITY, z);
        return this;
    }

    public T setPackage(String str) {
        this.packageName = str;
        return this;
    }

    public T withExtra(String str, Serializable serializable) {
        extras().putSerializable(str, serializable);
        return this;
    }

    public T withExtra(String str, Parcelable[] parcelableArr) {
        extras().putParcelableArray(str, parcelableArr);
        return this;
    }

    public T withIntent(Intent intent2) {
        this.intent = intent2;
        return this;
    }

    private Bundle extras() {
        if (this.withExtras == null) {
            this.withExtras = new Bundle();
        }
        return this.withExtras;
    }

    public T withFlags(int i, int i2) {
        this.includeFlags = i;
        this.excludeFlags = i2;
        return this;
    }

    public T withDisableStartFromFragment(boolean z) {
        this.disableStartFromFragment = z;
        return this;
    }

    public T withClearTopAndNewTask() {
        return withFlags(335544320, 0);
    }

    public T withClearTopAndSingleTop() {
        withExtra("withinApp", Strings.toString(Boolean.FALSE));
        withFlags(603979776, 0);
        return this;
    }

    public T withAction(String str) {
        this.action = str;
        return this;
    }

    public T withNoAnimations() {
        return withAnimations(-1, -1);
    }

    public T withAnimations(int i, int i2) {
        this.enterAnim = i;
        this.exitAnim = i2;
        return this;
    }

    public T withActivityOptions(@NonNull View view, @StringRes @NonNull int i) {
        if (this.isContextActivity) {
            Context context2 = this.context;
            this.activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context2, view, context2.getString(i));
        }
        return this;
    }

    public void done() {
        applyResult();
        ((Activity) this.context).finish();
        reset();
    }

    public void startActivity() {
        startActivity(-1);
    }

    public void startActivity(int i) {
        startActivity(this.intent, i);
    }

    /* access modifiers changed from: protected */
    public void startActivity(Intent intent2, int i) {
        if (this.context != null) {
            applyResult();
            intent2.putExtra("withinApp", Boolean.TRUE.toString());
            Bundle bundle = this.withExtras;
            if (bundle != null) {
                intent2.putExtras(bundle);
            }
            int i2 = this.includeFlags;
            if (i2 != 0) {
                intent2.addFlags(i2);
            }
            if (this.excludeFlags != 0) {
                intent2.setFlags(intent2.getFlags() & (~this.excludeFlags));
            }
            if (Strings.notEmpty(this.packageName)) {
                intent2.setPackage(this.packageName);
            }
            if (Strings.notEmpty(this.action)) {
                intent2.setAction(this.action);
            }
            if (!this.isContextActivity) {
                intent2.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            if (this.disableStartFromFragment || !startingFromFragment()) {
                if (!this.isContextActivity) {
                    Ln.w("context is not an Activity! cannot start for result", new Object[0]);
                    this.context.startActivity(intent2);
                } else if (isActivityOptionsSupported()) {
                    ((Activity) this.context).startActivityForResult(intent2, i, this.activityOptions.toBundle());
                    this.activityOptions = null;
                } else {
                    ((Activity) this.context).startActivityForResult(intent2, i);
                }
            } else if (!(this.context instanceof FragmentActivity)) {
                throw new RuntimeException("Cannot start for fragment if the context is not a FragmentActivity.");
            } else if (isActivityOptionsSupported()) {
                ((FragmentActivity) this.context).startActivityFromFragment(this.fragment, intent2, i, this.activityOptions.toBundle());
            } else {
                ((FragmentActivity) this.context).startActivityFromFragment(this.fragment, intent2, i);
            }
            if (this.isContextActivity) {
                if (!(this.enterAnim == -1 && this.exitAnim == -1)) {
                    ((Activity) this.context).overridePendingTransition(this.enterAnim, this.exitAnim);
                }
                if (this.finishActivityAfterNavigation) {
                    ((Activity) this.context).finish();
                }
            }
            reset();
            return;
        }
        throw new RuntimeException("Context cannot be null. Did you forget to call setContext() or withContext()?");
    }

    private void applyResult() {
        int i = this.resultCode;
        if (i != 9999) {
            Intent intent2 = this.resultData;
            if (intent2 != null) {
                ((Activity) this.context).setResult(i, intent2);
            }
        }
    }

    private void reset() {
        this.withExtras = new Bundle();
        this.includeFlags = 0;
        this.excludeFlags = 0;
        this.finishActivityAfterNavigation = false;
        this.resultCode = 9999;
        this.resultData = null;
        this.packageName = null;
        this.action = null;
        this.disableStartFromFragment = false;
        this.exitAnim = -1;
        this.enterAnim = -1;
    }

    private boolean isActivityOptionsSupported() {
        return this.activityOptions != null && VERSION.SDK_INT >= 21;
    }
}
