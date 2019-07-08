package com.myfitnesspal.feature.profile.ui.behavior;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;

public class ProfileHeaderBehavior extends Behavior<ViewGroup> {
    /* access modifiers changed from: private */
    public View appBarLayout;
    /* access modifiers changed from: private */
    public ViewGroup detailsContainer;
    private View detailsContent;
    private View detailsImage;
    private OnLayoutChangeListener detailsLayoutListener = new OnLayoutChangeListener() {
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (i4 - i2 > 0 && ProfileHeaderBehavior.this.updateAppBarLayoutHeight()) {
                ProfileHeaderBehavior.this.redraw(ProfileHeaderBehavior.this.appBarLayout.getLayoutParams().height);
                ProfileHeaderBehavior.this.detailsContainer.removeOnLayoutChangeListener(this);
            }
        }
    };
    private int imageLeftMargin = 0;
    private int imageTopMargin = 0;
    private int largeImageSize = 0;
    private int smallImageSize = 0;
    private View toolbarViews;

    public ProfileHeaderBehavior() {
    }

    public ProfileHeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, ViewGroup viewGroup, View view) {
        return view instanceof AppBarLayout;
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, ViewGroup viewGroup, View view) {
        this.appBarLayout = view;
        this.detailsContainer = viewGroup;
        redraw();
        return true;
    }

    private void redraw() {
        redraw(-1);
    }

    /* access modifiers changed from: private */
    public void redraw(int i) {
        View view = this.appBarLayout;
        if (view != null) {
            ViewGroup viewGroup = this.detailsContainer;
            if (viewGroup != null) {
                if (this.imageTopMargin == 0) {
                    init(viewGroup, (AppBarLayout) view);
                }
                if (this.appBarLayout.getLayoutParams().height < 0) {
                    updateAppBarLayoutHeight();
                }
                if (i <= 0) {
                    i = (this.appBarLayout.getHeight() + ((int) this.appBarLayout.getY())) - this.imageTopMargin;
                }
                int min = Math.min(this.largeImageSize, Math.max(this.smallImageSize, i));
                if (min <= this.smallImageSize) {
                    setLargeViewsVisible(false);
                } else {
                    int i2 = 1;
                    setLargeViewsVisible(true);
                    int i3 = this.largeImageSize - this.smallImageSize;
                    if (i3 != 0) {
                        i2 = i3;
                    }
                    float f = ((float) (((this.largeImageSize - min) * 100) / i2)) / 100.0f;
                    this.toolbarViews.setAlpha(f);
                    this.detailsContent.setAlpha(1.0f - f);
                    this.detailsContent.setTranslationY((float) (-Math.max(0, this.detailsContent.getHeight() - i)));
                    this.detailsContainer.setX((float) this.imageLeftMargin);
                    this.detailsContainer.setY((float) this.imageTopMargin);
                    LayoutParams layoutParams = this.detailsImage.getLayoutParams();
                    layoutParams.height = min;
                    layoutParams.width = min;
                    this.detailsContainer.requestLayout();
                }
            }
        }
    }

    private void setLargeViewsVisible(boolean z) {
        int i = 0;
        this.detailsImage.setVisibility(z ? 0 : 8);
        View view = this.detailsContent;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        this.toolbarViews.setAlpha(1.0f);
        this.detailsContent.setAlpha(BitmapDescriptorFactory.HUE_RED);
    }

    public void onLayoutChanged() {
        if (this.detailsContainer != null) {
            updateAppBarLayoutHeight();
            this.detailsContainer.addOnLayoutChangeListener(this.detailsLayoutListener);
        }
    }

    /* access modifiers changed from: private */
    public boolean updateAppBarLayoutHeight() {
        ViewGroup viewGroup = this.detailsContainer;
        if (viewGroup != null && viewGroup.getHeight() > 0) {
            int height = this.detailsContainer.getHeight() + this.imageTopMargin;
            LayoutParams layoutParams = this.appBarLayout.getLayoutParams();
            if (layoutParams.height != height) {
                layoutParams.height = height;
                this.appBarLayout.requestLayout();
                return true;
            }
        }
        return false;
    }

    private void init(ViewGroup viewGroup, AppBarLayout appBarLayout2) {
        Resources resources = appBarLayout2.getContext().getResources();
        this.imageTopMargin = resources.getDimensionPixelSize(R.dimen.profile_large_image_y_margin);
        this.imageLeftMargin = resources.getDimensionPixelOffset(R.dimen.profile_large_image_x_margin);
        this.largeImageSize = resources.getDimensionPixelSize(R.dimen.profile_large_image_size);
        this.smallImageSize = resources.getDimensionPixelSize(R.dimen.profile_small_image_size);
        this.toolbarViews = appBarLayout2.findViewById(R.id.toolbar_subview_container);
        this.detailsImage = viewGroup.findViewById(R.id.large_profile_image);
        this.detailsContent = viewGroup.findViewById(R.id.large_profile_content);
        this.detailsContainer.addOnLayoutChangeListener(this.detailsLayoutListener);
    }
}
