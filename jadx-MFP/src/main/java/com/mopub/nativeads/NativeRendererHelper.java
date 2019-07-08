package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Drawables;
import java.util.Map;

public class NativeRendererHelper {
    public static void addTextView(@Nullable TextView textView, @Nullable String str) {
        if (textView == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attempted to add text (");
            sb.append(str);
            sb.append(") to null TextView.");
            MoPubLog.d(sb.toString());
            return;
        }
        textView.setText(null);
        if (str == null) {
            MoPubLog.d("Attempted to set TextView contents to null.");
        } else {
            textView.setText(str);
        }
    }

    public static void addPrivacyInformationIcon(ImageView imageView, String str, final String str2) {
        if (imageView != null) {
            if (str2 == null) {
                imageView.setImageDrawable(null);
                imageView.setOnClickListener(null);
                imageView.setVisibility(4);
                return;
            }
            final Context context = imageView.getContext();
            if (context != null) {
                if (str == null) {
                    imageView.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(context));
                } else {
                    NativeImageHelper.loadImageView(str, imageView);
                }
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        new Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).build().handleUrl(context, str2);
                    }
                });
                imageView.setVisibility(0);
            }
        }
    }

    public static void addCtaButton(@Nullable TextView textView, @Nullable final View view, @Nullable String str) {
        addTextView(textView, str);
        if (textView != null && view != null) {
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    view.performClick();
                }
            });
        }
    }

    public static void updateExtras(@Nullable View view, @NonNull Map<String, Integer> map, @NonNull Map<String, Object> map2) {
        if (view == null) {
            MoPubLog.w("Attempted to bind extras on a null main view.");
            return;
        }
        for (String str : map.keySet()) {
            View findViewById = view.findViewById(((Integer) map.get(str)).intValue());
            Object obj = map2.get(str);
            if (findViewById instanceof ImageView) {
                ImageView imageView = (ImageView) findViewById;
                imageView.setImageDrawable(null);
                Object obj2 = map2.get(str);
                if (obj2 != null && (obj2 instanceof String)) {
                    NativeImageHelper.loadImageView((String) obj2, imageView);
                }
            } else if (findViewById instanceof TextView) {
                TextView textView = (TextView) findViewById;
                textView.setText(null);
                if (obj instanceof String) {
                    addTextView(textView, (String) obj);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("View bound to ");
                sb.append(str);
                sb.append(" should be an instance of TextView or ImageView.");
                MoPubLog.d(sb.toString());
            }
        }
    }
}
