package com.mopub.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import com.facebook.appevents.AppEventsConstants;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastVideoConfig;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ExternalViewabilitySessionManager {
    @NonNull
    private final Set<ExternalViewabilitySession> mViewabilitySessions = new HashSet();

    public enum ViewabilityVendor {
        AVID,
        MOAT,
        ALL;

        public void disable() {
            switch (this) {
                case AVID:
                    AvidViewabilitySession.disable();
                    break;
                case MOAT:
                    MoatViewabilitySession.disable();
                    break;
                case ALL:
                    AvidViewabilitySession.disable();
                    MoatViewabilitySession.disable();
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Attempted to disable an invalid viewability vendor: ");
                    sb.append(this);
                    MoPubLog.d(sb.toString());
                    return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Disabled viewability for ");
            sb2.append(this);
            MoPubLog.d(sb2.toString());
        }

        @NonNull
        public static String getEnabledVendorKey() {
            boolean isEnabled = AvidViewabilitySession.isEnabled();
            boolean isEnabled2 = MoatViewabilitySession.isEnabled();
            String str = "0";
            if (isEnabled && isEnabled2) {
                return "3";
            }
            if (isEnabled) {
                return AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
            return isEnabled2 ? InternalAvidAdSessionContext.AVID_API_LEVEL : str;
        }

        @android.support.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor fromKey(@android.support.annotation.NonNull java.lang.String r1) {
            /*
                com.mopub.common.Preconditions.checkNotNull(r1)
                int r0 = r1.hashCode()
                switch(r0) {
                    case 49: goto L_0x001f;
                    case 50: goto L_0x0015;
                    case 51: goto L_0x000b;
                    default: goto L_0x000a;
                }
            L_0x000a:
                goto L_0x0029
            L_0x000b:
                java.lang.String r0 = "3"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0029
                r1 = 2
                goto L_0x002a
            L_0x0015:
                java.lang.String r0 = "2"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0029
                r1 = 1
                goto L_0x002a
            L_0x001f:
                java.lang.String r0 = "1"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0029
                r1 = 0
                goto L_0x002a
            L_0x0029:
                r1 = -1
            L_0x002a:
                switch(r1) {
                    case 0: goto L_0x0035;
                    case 1: goto L_0x0032;
                    case 2: goto L_0x002f;
                    default: goto L_0x002d;
                }
            L_0x002d:
                r1 = 0
                return r1
            L_0x002f:
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r1 = ALL
                return r1
            L_0x0032:
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r1 = MOAT
                return r1
            L_0x0035:
                com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor r1 = AVID
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor.fromKey(java.lang.String):com.mopub.common.ExternalViewabilitySessionManager$ViewabilityVendor");
        }
    }

    public ExternalViewabilitySessionManager(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        this.mViewabilitySessions.add(new AvidViewabilitySession());
        this.mViewabilitySessions.add(new MoatViewabilitySession());
        initialize(context);
    }

    private void initialize(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "initialize", externalViewabilitySession.initialize(context), false);
        }
    }

    public void invalidate() {
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "invalidate", externalViewabilitySession.invalidate(), false);
        }
    }

    public void createDisplaySession(@NonNull Context context, @NonNull WebView webView, boolean z) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webView);
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "start display session", externalViewabilitySession.createDisplaySession(context, webView, z), true);
        }
    }

    public void createDisplaySession(@NonNull Context context, @NonNull WebView webView) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webView);
        createDisplaySession(context, webView, false);
    }

    public void startDeferredDisplaySession(@NonNull Activity activity) {
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "record deferred session", externalViewabilitySession.startDeferredDisplaySession(activity), true);
        }
    }

    public void endDisplaySession() {
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "end display session", externalViewabilitySession.endDisplaySession(), true);
        }
    }

    public void createVideoSession(@NonNull Activity activity, @NonNull View view, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(vastVideoConfig);
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            HashSet hashSet = new HashSet();
            if (externalViewabilitySession instanceof AvidViewabilitySession) {
                hashSet.addAll(vastVideoConfig.getAvidJavascriptResources());
            } else if (externalViewabilitySession instanceof MoatViewabilitySession) {
                hashSet.addAll(vastVideoConfig.getMoatImpressionPixels());
            }
            logEvent(externalViewabilitySession, "start video session", externalViewabilitySession.createVideoSession(activity, view, hashSet, vastVideoConfig.getExternalViewabilityTrackers()), true);
        }
    }

    public void registerVideoObstruction(@NonNull View view) {
        Preconditions.checkNotNull(view);
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "register friendly obstruction", externalViewabilitySession.registerVideoObstruction(view), true);
        }
    }

    public void onVideoPrepared(@NonNull View view, int i) {
        Preconditions.checkNotNull(view);
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "on video prepared", externalViewabilitySession.onVideoPrepared(view, i), true);
        }
    }

    public void recordVideoEvent(@NonNull VideoEvent videoEvent, int i) {
        Preconditions.checkNotNull(videoEvent);
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            Boolean recordVideoEvent = externalViewabilitySession.recordVideoEvent(videoEvent, i);
            StringBuilder sb = new StringBuilder();
            sb.append("record video event (");
            sb.append(videoEvent.name());
            sb.append(")");
            logEvent(externalViewabilitySession, sb.toString(), recordVideoEvent, true);
        }
    }

    public void endVideoSession() {
        for (ExternalViewabilitySession externalViewabilitySession : this.mViewabilitySessions) {
            logEvent(externalViewabilitySession, "end video session", externalViewabilitySession.endVideoSession(), true);
        }
    }

    private void logEvent(@NonNull ExternalViewabilitySession externalViewabilitySession, @NonNull String str, @Nullable Boolean bool, boolean z) {
        Preconditions.checkNotNull(externalViewabilitySession);
        Preconditions.checkNotNull(str);
        if (bool != null) {
            String format = String.format(Locale.US, "%s viewability event: %s%s.", new Object[]{externalViewabilitySession.getName(), bool.booleanValue() ? "" : "failed to ", str});
            if (z) {
                MoPubLog.v(format);
            } else {
                MoPubLog.d(format);
            }
        }
    }
}
