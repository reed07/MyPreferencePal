package com.myfitnesspal.feature.restaurantlogging.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class RestaurantLoggingInterstitial_ViewBinding implements Unbinder {
    private RestaurantLoggingInterstitial target;

    @UiThread
    public RestaurantLoggingInterstitial_ViewBinding(RestaurantLoggingInterstitial restaurantLoggingInterstitial) {
        this(restaurantLoggingInterstitial, restaurantLoggingInterstitial.getWindow().getDecorView());
    }

    @UiThread
    public RestaurantLoggingInterstitial_ViewBinding(RestaurantLoggingInterstitial restaurantLoggingInterstitial, View view) {
        this.target = restaurantLoggingInterstitial;
        restaurantLoggingInterstitial.browseButton = Utils.findRequiredView(view, R.id.rl_interstitial_button, "field 'browseButton'");
        restaurantLoggingInterstitial.notNowView = Utils.findRequiredView(view, R.id.rl_interstitial_not_now, "field 'notNowView'");
    }

    @CallSuper
    public void unbind() {
        RestaurantLoggingInterstitial restaurantLoggingInterstitial = this.target;
        if (restaurantLoggingInterstitial != null) {
            this.target = null;
            restaurantLoggingInterstitial.browseButton = null;
            restaurantLoggingInterstitial.notNowView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
