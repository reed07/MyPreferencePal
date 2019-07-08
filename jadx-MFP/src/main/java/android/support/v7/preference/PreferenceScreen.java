package android.support.v7.preference;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.preference.PreferenceManager.OnNavigateToScreenListener;
import android.util.AttributeSet;

public final class PreferenceScreen extends PreferenceGroup {
    private boolean mShouldUseGeneratedIds = true;

    /* access modifiers changed from: protected */
    public boolean isOnSameScreenAsChildren() {
        return false;
    }

    @RestrictTo
    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceScreenStyle, 16842891));
    }

    /* access modifiers changed from: protected */
    public void onClick() {
        if (getIntent() == null && getFragment() == null && getPreferenceCount() != 0) {
            OnNavigateToScreenListener onNavigateToScreenListener = getPreferenceManager().getOnNavigateToScreenListener();
            if (onNavigateToScreenListener != null) {
                onNavigateToScreenListener.onNavigateToScreen(this);
            }
        }
    }

    public boolean shouldUseGeneratedIds() {
        return this.mShouldUseGeneratedIds;
    }

    public void setShouldUseGeneratedIds(boolean z) {
        if (!isAttached()) {
            this.mShouldUseGeneratedIds = z;
            return;
        }
        throw new IllegalStateException("Cannot change the usage of generated IDs while attached to the preference hierarchy");
    }
}
