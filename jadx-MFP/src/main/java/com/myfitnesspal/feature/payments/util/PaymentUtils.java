package com.myfitnesspal.feature.payments.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.myfitnesspal.shared.constants.Constants.Payments.Providers;
import com.uacf.core.util.Ln;

public class PaymentUtils {
    private static final Uri GOOGLE_PLAY_MANAGE_SUBSCRIPTION = Uri.parse("https://play.google.com/store/account/subscriptions");
    private static final Uri GOOGLE_PLAY_MANAGE_URI = Uri.parse("market://details?id=com.myfitnesspal.android");

    public static boolean managePayment(Activity activity, String str, String str2) {
        return managePayment(activity, -1, str, str2);
    }

    public static boolean managePayment(Activity activity, int i, String str, String str2) {
        Intent intent = "google".equals(str) ? new Intent("android.intent.action.VIEW").setData(GOOGLE_PLAY_MANAGE_URI) : Providers.MOCK.equals(str) ? new Intent("android.intent.action.VIEW").setData(GOOGLE_PLAY_MANAGE_URI) : null;
        if (intent == null) {
            Ln.e("Error starting payment management Activity! no start intent could be resolved", new Object[0]);
        } else {
            try {
                activity.startActivityForResult(intent, i);
                return true;
            } catch (Exception e) {
                Ln.e("Error starting payment management Activity!", new Object[0]);
                Ln.e(e);
            }
        }
        return false;
    }

    public static void manageSubscription(@NonNull Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", GOOGLE_PLAY_MANAGE_SUBSCRIPTION));
        } catch (ActivityNotFoundException e) {
            Ln.e("Unable to open Google Play subscription details", e);
        }
    }
}
