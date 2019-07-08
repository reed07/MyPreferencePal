package android.support.v7.preference;

import android.support.annotation.IdRes;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;

public class PreferenceViewHolder extends ViewHolder {
    private final SparseArray<View> mCachedViews = new SparseArray<>(4);
    private boolean mDividerAllowedAbove;
    private boolean mDividerAllowedBelow;

    PreferenceViewHolder(View view) {
        super(view);
        this.mCachedViews.put(16908310, view.findViewById(16908310));
        this.mCachedViews.put(16908304, view.findViewById(16908304));
        this.mCachedViews.put(16908294, view.findViewById(16908294));
        this.mCachedViews.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
        this.mCachedViews.put(AndroidResources.ANDROID_R_ICON_FRAME, view.findViewById(AndroidResources.ANDROID_R_ICON_FRAME));
    }

    @RestrictTo
    public static PreferenceViewHolder createInstanceForTests(View view) {
        return new PreferenceViewHolder(view);
    }

    public View findViewById(@IdRes int i) {
        View view = (View) this.mCachedViews.get(i);
        if (view != null) {
            return view;
        }
        View findViewById = this.itemView.findViewById(i);
        if (findViewById != null) {
            this.mCachedViews.put(i, findViewById);
        }
        return findViewById;
    }

    public boolean isDividerAllowedAbove() {
        return this.mDividerAllowedAbove;
    }

    public void setDividerAllowedAbove(boolean z) {
        this.mDividerAllowedAbove = z;
    }

    public boolean isDividerAllowedBelow() {
        return this.mDividerAllowedBelow;
    }

    public void setDividerAllowedBelow(boolean z) {
        this.mDividerAllowedBelow = z;
    }
}
