package com.facebook.stetho.inspector.elements.android;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import com.brightcove.player.C;
import com.facebook.stetho.common.android.AccessibilityUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;

public final class AccessibilityNodeInfoWrapper {
    public static AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        ViewCompat.onInitializeAccessibilityNodeInfo(view, obtain);
        return obtain;
    }

    public static boolean getIsAccessibilityFocused(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        boolean isAccessibilityFocused = createNodeInfoFromView.isAccessibilityFocused();
        createNodeInfoFromView.recycle();
        return isAccessibilityFocused;
    }

    public static boolean getIgnored(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2 || importantForAccessibility == 4) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return true;
            }
        }
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            if (!createNodeInfoFromView.isVisibleToUser()) {
                return true;
            }
            if (AccessibilityUtil.isAccessibilityFocusable(createNodeInfoFromView, view)) {
                if (createNodeInfoFromView.getChildCount() <= 0) {
                    createNodeInfoFromView.recycle();
                    return false;
                } else if (AccessibilityUtil.isSpeakingNode(createNodeInfoFromView, view)) {
                    createNodeInfoFromView.recycle();
                    return false;
                } else {
                    createNodeInfoFromView.recycle();
                    return true;
                }
            } else if (AccessibilityUtil.hasFocusableAncestor(createNodeInfoFromView, view) || !AccessibilityUtil.hasText(createNodeInfoFromView)) {
                createNodeInfoFromView.recycle();
                return true;
            } else {
                createNodeInfoFromView.recycle();
                return false;
            }
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    public static String getIgnoredReasons(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2) {
            return "View has importantForAccessibility set to 'NO'.";
        }
        if (importantForAccessibility == 4) {
            return "View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return "An ancestor View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
            }
        }
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            if (!createNodeInfoFromView.isVisibleToUser()) {
                return "View is not visible.";
            }
            if (AccessibilityUtil.isAccessibilityFocusable(createNodeInfoFromView, view)) {
                String str = "View is actionable, but has no description.";
                createNodeInfoFromView.recycle();
                return str;
            } else if (AccessibilityUtil.hasText(createNodeInfoFromView)) {
                String str2 = "View is not actionable, and an ancestor View has co-opted its description.";
                createNodeInfoFromView.recycle();
                return str2;
            } else {
                String str3 = "View is not actionable and has no description.";
                createNodeInfoFromView.recycle();
                return str3;
            }
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getFocusableReasons(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            boolean hasText = AccessibilityUtil.hasText(createNodeInfoFromView);
            boolean isCheckable = createNodeInfoFromView.isCheckable();
            boolean hasNonActionableSpeakingDescendants = AccessibilityUtil.hasNonActionableSpeakingDescendants(createNodeInfoFromView, view);
            if (AccessibilityUtil.isActionableForAccessibility(createNodeInfoFromView)) {
                if (createNodeInfoFromView.getChildCount() <= 0) {
                    return "View is actionable and has no children.";
                }
                if (hasText) {
                    String str = "View is actionable and has a description.";
                    createNodeInfoFromView.recycle();
                    return str;
                } else if (isCheckable) {
                    String str2 = "View is actionable and checkable.";
                    createNodeInfoFromView.recycle();
                    return str2;
                } else if (hasNonActionableSpeakingDescendants) {
                    String str3 = "View is actionable and has non-actionable descendants with descriptions.";
                    createNodeInfoFromView.recycle();
                    return str3;
                }
            }
            if (AccessibilityUtil.isTopLevelScrollItem(createNodeInfoFromView, view)) {
                if (hasText) {
                    String str4 = "View is a direct child of a scrollable container and has a description.";
                    createNodeInfoFromView.recycle();
                    return str4;
                } else if (isCheckable) {
                    String str5 = "View is a direct child of a scrollable container and is checkable.";
                    createNodeInfoFromView.recycle();
                    return str5;
                } else if (hasNonActionableSpeakingDescendants) {
                    String str6 = "View is a direct child of a scrollable container and has non-actionable descendants with descriptions.";
                    createNodeInfoFromView.recycle();
                    return str6;
                }
            }
            if (hasText) {
                String str7 = "View has a description and is not actionable, but has no actionable ancestor.";
                createNodeInfoFromView.recycle();
                return str7;
            }
            createNodeInfoFromView.recycle();
            return null;
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getActions(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            StringBuilder sb = new StringBuilder();
            for (AccessibilityActionCompat accessibilityActionCompat : createNodeInfoFromView.getActionList()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                switch (accessibilityActionCompat.getId()) {
                    case 1:
                        sb.append("focus");
                        break;
                    case 2:
                        sb.append("clear-focus");
                        break;
                    case 4:
                        sb.append(Attributes.SELECT);
                        break;
                    case 8:
                        sb.append("clear-selection");
                        break;
                    case 16:
                        sb.append("click");
                        break;
                    case 32:
                        sb.append("long-click");
                        break;
                    case 64:
                        sb.append("accessibility-focus");
                        break;
                    case 128:
                        sb.append("clear-accessibility-focus");
                        break;
                    case 256:
                        sb.append("next-at-movement-granularity");
                        break;
                    case 512:
                        sb.append("previous-at-movement-granularity");
                        break;
                    case 1024:
                        sb.append("next-html-element");
                        break;
                    case 2048:
                        sb.append("previous-html-element");
                        break;
                    case 4096:
                        sb.append("scroll-forward");
                        break;
                    case 8192:
                        sb.append("scroll-backward");
                        break;
                    case C.DASH_ROLE_CAPTION_FLAG /*16384*/:
                        sb.append("copy");
                        break;
                    case 32768:
                        sb.append("paste");
                        break;
                    case 65536:
                        sb.append("cut");
                        break;
                    case 131072:
                        sb.append("set-selection");
                        break;
                    default:
                        CharSequence label = accessibilityActionCompat.getLabel();
                        if (label == null) {
                            sb.append("unknown");
                            break;
                        } else {
                            sb.append(label);
                            break;
                        }
                }
            }
            return sb.length() > 0 ? sb.toString() : null;
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static CharSequence getDescription(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            CharSequence contentDescription = createNodeInfoFromView.getContentDescription();
            CharSequence text = createNodeInfoFromView.getText();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = view instanceof EditText;
            if (!TextUtils.isEmpty(contentDescription) && (!z2 || !z)) {
                return contentDescription;
            }
            if (z) {
                createNodeInfoFromView.recycle();
                return text;
            }
            String str = null;
            if (view instanceof ViewGroup) {
                StringBuilder sb = new StringBuilder();
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
                    ViewCompat.onInitializeAccessibilityNodeInfo(childAt, obtain);
                    CharSequence description = (!AccessibilityUtil.isSpeakingNode(obtain, childAt) || AccessibilityUtil.isAccessibilityFocusable(obtain, childAt)) ? null : getDescription(childAt);
                    if (!TextUtils.isEmpty(description)) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(description);
                    }
                    obtain.recycle();
                }
                if (sb.length() > 0) {
                    str = sb.toString();
                }
                createNodeInfoFromView.recycle();
                return str;
            }
            createNodeInfoFromView.recycle();
            return null;
        } finally {
            createNodeInfoFromView.recycle();
        }
    }
}
