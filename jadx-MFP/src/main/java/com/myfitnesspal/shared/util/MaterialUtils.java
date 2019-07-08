package com.myfitnesspal.shared.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout.LayoutParams;
import android.support.design.widget.AppBarLayout.ScrollingViewBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.behavior.FixedFooterScrollingViewBehavior;
import com.uacf.core.util.VersionUtils;
import com.uacf.core.util.ViewUtils;

public final class MaterialUtils {
    private static final boolean IS_LOLLIPOP_OR_HIGHER = VersionUtils.isLollipopOrHigher();

    public static void showIndeterminateProgress(Context context, boolean z) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            View findViewById = activity.findViewById(R.id.progress_spinner);
            if (findViewById != null) {
                ViewUtils.setVisible(z, findViewById);
                activity.invalidateOptionsMenu();
            }
        }
    }

    public static void setToolbarDefaultHeight(Context context, View view) {
        if (view != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{16843499});
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            obtainStyledAttributes.recycle();
            setToolbarHeight(view, dimensionPixelSize);
        }
    }

    public static void setToolbarHeight(View view, int i) {
        if (view != null) {
            view.getLayoutParams().height = i;
        }
    }

    public static void removeDefaultToolbarElevation(Activity activity) {
        setDefaultToolbarElevation(activity, false);
    }

    public static void addDefaultToolbarElevation(Activity activity) {
        setDefaultToolbarElevation(activity, true);
    }

    private static void setDefaultToolbarElevation(Activity activity, boolean z) {
        ViewCompat.setElevation((ViewGroup) ViewUtils.findById(activity.findViewById(16908290), R.id.toolbar_container), z ? activity.getResources().getDimension(R.dimen.default_toolbar_elevation) : BitmapDescriptorFactory.HUE_RED);
    }

    public static void enableAppBarScrollIfLollipop(Activity activity) {
        enableAppBarScrollIfLollipop(activity, null);
    }

    @SuppressLint({"NewApi"})
    public static void enableAppBarScrollIfLollipop(Activity activity, ListView listView) {
        if (IS_LOLLIPOP_OR_HIGHER) {
            ((LayoutParams) activity.findViewById(R.id.toolbar).getLayoutParams()).setScrollFlags(21);
            enableListViewNestedScrolling(listView);
        }
    }

    @SuppressLint({"NewApi"})
    public static void enableListViewNestedScrolling(ListView listView) {
        if (IS_LOLLIPOP_OR_HIGHER && listView != null) {
            listView.setNestedScrollingEnabled(true);
        }
    }

    public static void setFixedFooterScrollingBehavior(Activity activity, boolean z) {
        if (activity != null) {
            setFixedFooterScrollingBehavior(activity, (CoordinatorLayout) activity.findViewById(R.id.content_parent), activity.findViewById(R.id.content_frame), z);
        }
    }

    public static void setFixedFooterScrollingBehavior(Activity activity, CoordinatorLayout coordinatorLayout, View view, boolean z) {
        if (activity != null && view != null && coordinatorLayout != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                ScrollingViewBehavior fixedFooterScrollingViewBehavior = z ? new FixedFooterScrollingViewBehavior() : new ScrollingViewBehavior();
                ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(fixedFooterScrollingViewBehavior);
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), 0);
                fixedFooterScrollingViewBehavior.onDependentViewChanged(coordinatorLayout, view, activity.findViewById(R.id.toolbar_container));
            }
        }
    }

    public static void applyPaddingToFixedFooter(Activity activity) {
        if (activity != null) {
            View findViewById = activity.findViewById(R.id.content_frame);
            if (findViewById != null) {
                ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
                    if (behavior != null) {
                        behavior.onDependentViewChanged((CoordinatorLayout) activity.findViewById(R.id.content_parent), findViewById, activity.findViewById(R.id.toolbar_container));
                    }
                }
            }
        }
    }

    public static void cleanActionModeDoneText(MfpActivity mfpActivity) {
        View findViewById = mfpActivity.findViewById(Resources.getSystem().getIdentifier("action_mode_close_button", "id", "android"));
        if (findViewById == null) {
            findViewById = mfpActivity.findViewById(R.id.action_mode_close_button);
        }
        if (findViewById != null && (findViewById instanceof LinearLayout)) {
            LinearLayout linearLayout = (LinearLayout) findViewById;
            if (linearLayout.getChildCount() > 1 && linearLayout.getChildAt(1) != null) {
                TextView textView = (TextView) linearLayout.getChildAt(1);
                if (textView != null) {
                    textView.setText(R.string.cancel);
                    textView.setTextColor(mfpActivity.getResources().getColor(R.color.white));
                }
            }
        }
    }

    public static void addDecoratorForRemovingFabOverlapOnLastItem(final RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView.getChildAdapterPosition(view) == state.getItemCount() - 1) {
                    rect.set(0, 0, 0, recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.bottom_padding_avoid_fab_overlap));
                } else {
                    rect.set(0, 0, 0, 0);
                }
            }
        });
    }

    public static void applyPaddingAndShowFabAfterDelay(FloatingActionButton floatingActionButton) {
        applyPaddingAndShowFabAfterDelay(floatingActionButton, 400);
    }

    private static void applyPaddingAndShowFabAfterDelay(final FloatingActionButton floatingActionButton, long j) {
        floatingActionButton.postDelayed(new Runnable() {
            public void run() {
                MaterialUtils.applyPaddingToFixedFooter((Activity) floatingActionButton.getContext());
                floatingActionButton.show();
            }
        }, j);
    }
}
