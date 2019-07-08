package com.myfitnesspal.feature.search.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class OnlineFoodSearchFragment_ViewBinding implements Unbinder {
    private OnlineFoodSearchFragment target;

    @UiThread
    public OnlineFoodSearchFragment_ViewBinding(OnlineFoodSearchFragment onlineFoodSearchFragment, View view) {
        this.target = onlineFoodSearchFragment;
        onlineFoodSearchFragment.searchResultsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.onlineFoodSearchRecyclerView, "field 'searchResultsRecyclerView'", RecyclerView.class);
        onlineFoodSearchFragment.noResultsView = Utils.findRequiredView(view, R.id.noSearchResultsTextView, "field 'noResultsView'");
        onlineFoodSearchFragment.newDesignOnlineSearchStatusView = (TextView) Utils.findRequiredViewAsType(view, R.id.onlineSearchStatus, "field 'newDesignOnlineSearchStatusView'", TextView.class);
        onlineFoodSearchFragment.newDesignVerifiedOnlyButton = (CompoundButton) Utils.findRequiredViewAsType(view, R.id.verifiedOnlyButton, "field 'newDesignVerifiedOnlyButton'", CompoundButton.class);
        onlineFoodSearchFragment.newDesignEmptyState = (Group) Utils.findRequiredViewAsType(view, R.id.newSearchDesignEmptyState, "field 'newDesignEmptyState'", Group.class);
        onlineFoodSearchFragment.newDesignNoResultsMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.noResultsMessage, "field 'newDesignNoResultsMessage'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        OnlineFoodSearchFragment onlineFoodSearchFragment = this.target;
        if (onlineFoodSearchFragment != null) {
            this.target = null;
            onlineFoodSearchFragment.searchResultsRecyclerView = null;
            onlineFoodSearchFragment.noResultsView = null;
            onlineFoodSearchFragment.newDesignOnlineSearchStatusView = null;
            onlineFoodSearchFragment.newDesignVerifiedOnlyButton = null;
            onlineFoodSearchFragment.newDesignEmptyState = null;
            onlineFoodSearchFragment.newDesignNoResultsMessage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
