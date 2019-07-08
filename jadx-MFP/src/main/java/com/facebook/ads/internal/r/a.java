package com.facebook.ads.internal.r;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.BuildConfig;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.logging.type.LogSeverity;
import com.mopub.common.Constants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static a a;
    private final SharedPreferences b;
    private final Context c;

    private a(Context context) {
        this.c = context.getApplicationContext();
        this.b = this.c.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("com.facebook.ads.FEATURE_CONFIG", context), 0);
    }

    @Nullable
    public static String A(Context context) {
        return af(context).a("adnw_wo_network_signal_url", "");
    }

    public static double B(Context context) {
        return af(context).a("adnw_wo_network_signal_sampling_rate", 0.0d);
    }

    public static boolean C(Context context) {
        return af(context).a("adnw_wo_network_signal_large_payload_enabled", false);
    }

    public static int D(Context context) {
        return af(context).a("adnw_wo_network_signal_large_payload_size", -1);
    }

    public static double E(Context context) {
        return af(context).a("adnw_wo_network_signal_large_payload_sampling_rate", -1.0d);
    }

    public static int F(Context context) {
        return af(context).a("minimum_elapsed_time_after_impression", -1);
    }

    public static int G(Context context) {
        return af(context).a("stack_trace_sample_rate", 0);
    }

    public static boolean H(Context context) {
        return af(context).a("adnw_top_activity_viewability", false);
    }

    public static boolean I(Context context) {
        return af(context).a("adnw_enhanced_viewability_area_check", false);
    }

    public static boolean J(Context context) {
        return af(context).a("adnw_purge_on_413_response", false);
    }

    public static boolean K(Context context) {
        return af(context).a("adnw_arrows_instead_of_x_skip_button", false);
    }

    public static boolean L(Context context) {
        return af(context).a("adnw_viewability_check_area_based", false);
    }

    @Nullable
    public static String M(Context context) {
        return af(context).a("adnw_logging_endpoint_prefix", "www");
    }

    public static boolean N(Context context) {
        return af(context).a("adnw_mapp_markup_impression_after_image_load", false);
    }

    public static boolean O(Context context) {
        return af(context).a("adnw_enable_inline_x_out_on_sdk", false);
    }

    public static boolean P(Context context) {
        return af(context).a("adnw_enable_inline_x_out_non_fullscreen_on_sdk", false);
    }

    public static boolean Q(Context context) {
        return af(context).a("adnw_unique_db_name_per_process", false);
    }

    public static boolean R(Context context) {
        return af(context).a("adnw_log_interstitial_cache_result", false);
    }

    public static boolean S(Context context) {
        return af(context).a("adnw_images_in_display_size", true);
    }

    public static boolean T(Context context) {
        return af(context).a("adnw_fail_ad_load_on_cache_failure", false);
    }

    public static boolean U(Context context) {
        return af(context).a("adnw_should_fail_on_cleartext_http_blocked", false);
    }

    public static boolean V(Context context) {
        return af(context).a("adnw_enable_multiprocess_support", false);
    }

    public static boolean W(Context context) {
        return af(context).a("adnw_request_first_ad_from_main_process", true);
    }

    public static boolean X(Context context) {
        return af(context).a("adnw_hide_error_dialog_for_ad_process", true);
    }

    public static boolean Y(Context context) {
        return af(context).a("adnw_enable_circular_process_binding", true);
    }

    public static boolean Z(Context context) {
        return af(context).a("adnw_enable_auto_destroy_leaks", true);
    }

    private static int a(Context context, String str, int i) {
        int a2 = af(context).a(str, i);
        return (a2 < 0 || a2 >= 101) ? i : a2;
    }

    public static boolean a(Context context) {
        return VERSION.SDK_INT >= 14 && c(BuildConfig.APPLICATION_ID, ExoPlayerLibraryInfo.TAG) && af(context).a("adnw_enable_exoplayer", false);
    }

    public static boolean aa(Context context) {
        return af(context).a("adnw_disable_dependencies_check", true);
    }

    public static boolean ab(Context context) {
        return af(context).a("adnw_enable_wrong_ad_states_check", true);
    }

    public static boolean ac(Context context) {
        return af(context).a("adnw_do_not_reload_interstitial_adapter", true);
    }

    public static int ad(Context context) {
        return af(context).a("adnw_time_to_wait_for_video_prepared_ms", 3000);
    }

    public static int ae(Context context) {
        return af(context).a("adnw_debug_log_file_size_limit_bytes", (int) Constants.TEN_MB);
    }

    public static a af(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    private void b(@Nullable String str, String str2) {
        String str3;
        if (str != null && !str.isEmpty() && !str.equals("[]")) {
            Editor edit = this.b.edit();
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str4 = (String) keys.next();
                if (str4.equals("accidental_clicks_config")) {
                    b(jSONObject.getString(str4), str4);
                } else {
                    if (str2 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append(".");
                        sb.append(str4);
                        str3 = sb.toString();
                    } else {
                        str3 = str4;
                    }
                    edit.putString(str3, jSONObject.getString(str4));
                }
            }
            edit.apply();
        }
    }

    public static boolean b(Context context) {
        return VERSION.SDK_INT >= 18 && af(context).a("adnw_enable_debug_overlay", false);
    }

    public static boolean c(Context context) {
        return af(context).a("adnw_enable_rage_shake", false);
    }

    private static boolean c(String str, String str2) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(".");
            sb.append(str2);
            Class.forName(sb.toString());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static int d(Context context) {
        return af(context).a("clickguard_time_ms", 0);
    }

    public static boolean e(Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirmation", false);
    }

    @Nullable
    public static String f(Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirmation_title", "Continue?");
    }

    @Nullable
    public static String g(Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirmation_body", "You will be taken to another destination.");
    }

    @Nullable
    public static String h(Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirm_button_text", "Continue");
    }

    @Nullable
    public static String i(Context context) {
        return af(context).a("accidental_clicks_config.two_step_cancel_button_text", "Cancel");
    }

    public static boolean j(Context context) {
        return af(context).a("adnw_block_lockscreen", false);
    }

    public static boolean k(Context context) {
        return af(context).a("adnw_block_cta_before_impression", false);
    }

    public static boolean l(Context context) {
        return af(context).a("adnw_android_memory_opt", false);
    }

    public static boolean m(Context context) {
        return af(context).a("adnw_android_disable_blur", false);
    }

    public static boolean n(Context context) {
        return af(context).a("adnw_android_disable_playable_precache", false);
    }

    public static boolean o(Context context) {
        return VERSION.SDK_INT >= 19 && af(context).a("adnw_enable_iab", false);
    }

    public static boolean p(Context context) {
        return af(context).a("adnw_debug_logging", false);
    }

    public static boolean q(Context context) {
        return af(context).a("adnw_text_in_native_carousel", false);
    }

    public static int r(Context context) {
        return af(context).a("adnw_native_carousel_compact_threshold", 225);
    }

    public static Set<String> s(Context context) {
        String a2 = af(context).a("additional_debug_logging_black_list", "");
        HashSet hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(a2);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
        } catch (JSONException unused) {
        }
        return hashSet;
    }

    public static int t(Context context) {
        return a(context, "additional_debug_logging_black_list_percentage", 0);
    }

    public static int u(Context context) {
        return a(context, "additional_debug_logging_sampling_percentage", 0);
    }

    public static long v(Context context) {
        return af(context).a("unified_logging_immediate_delay_ms", 500);
    }

    public static long w(Context context) {
        return ((long) af(context).a("unified_logging_dispatch_interval_seconds", (int) LogSeverity.NOTICE_VALUE)) * 1000;
    }

    public static int x(Context context) {
        return af(context).a("unified_logging_event_limit", -1);
    }

    public static boolean y(Context context) {
        return af(context).a("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }

    public static boolean z(Context context) {
        return af(context).a("adnw_wo_network_signal_enabled", false);
    }

    public double a(String str, double d) {
        String string = this.b.getString(str, String.valueOf(d));
        try {
            return string.equals("null") ? d : Double.valueOf(string).doubleValue();
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public int a(String str, int i) {
        String string = this.b.getString(str, String.valueOf(i));
        try {
            return string.equals("null") ? i : Integer.valueOf(string).intValue();
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public long a(String str, long j) {
        String string = this.b.getString(str, String.valueOf(j));
        try {
            return string.equals("null") ? j : Long.valueOf(string).longValue();
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    @Nullable
    public String a(String str, String str2) {
        String string = this.b.getString(str, str2);
        return (string == null || string.equals("null")) ? str2 : string;
    }

    public void a(@Nullable String str) {
        b(str, null);
    }

    public boolean a(String str, boolean z) {
        String string = this.b.getString(str, String.valueOf(z));
        return string.equals("null") ? z : Boolean.valueOf(string).booleanValue();
    }
}
