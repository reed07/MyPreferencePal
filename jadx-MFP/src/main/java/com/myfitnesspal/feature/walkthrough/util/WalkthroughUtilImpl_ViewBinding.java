package com.myfitnesspal.feature.walkthrough.util;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class WalkthroughUtilImpl_ViewBinding implements Unbinder {
    private WalkthroughUtilImpl target;

    @UiThread
    public WalkthroughUtilImpl_ViewBinding(WalkthroughUtilImpl walkthroughUtilImpl, View view) {
        this.target = walkthroughUtilImpl;
        walkthroughUtilImpl.title = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
        walkthroughUtilImpl.desc = (TextView) Utils.findRequiredViewAsType(view, R.id.description, "field 'desc'", TextView.class);
        walkthroughUtilImpl.skip = (Button) Utils.findRequiredViewAsType(view, R.id.skip, "field 'skip'", Button.class);
        walkthroughUtilImpl.arrowRight = view.findViewById(R.id.arrow_right);
        walkthroughUtilImpl.arrowLeft = view.findViewById(R.id.arrow_left);
        walkthroughUtilImpl.searchMessageContainer = view.findViewById(R.id.search_results_walkthrough_container);
        walkthroughUtilImpl.searchMessagePadding = view.findViewById(R.id.message_box_padding);
    }

    @CallSuper
    public void unbind() {
        WalkthroughUtilImpl walkthroughUtilImpl = this.target;
        if (walkthroughUtilImpl != null) {
            this.target = null;
            walkthroughUtilImpl.title = null;
            walkthroughUtilImpl.desc = null;
            walkthroughUtilImpl.skip = null;
            walkthroughUtilImpl.arrowRight = null;
            walkthroughUtilImpl.arrowLeft = null;
            walkthroughUtilImpl.searchMessageContainer = null;
            walkthroughUtilImpl.searchMessagePadding = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
