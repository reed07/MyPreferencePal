package com.facebook.appevents.codeless.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import com.brightcove.player.C;
import com.brightcove.player.util.StringUtil;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.Utility;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewHierarchy {
    private static final int ADAPTER_VIEW_ITEM_BITMASK = 9;
    private static final int BUTTON_BITMASK = 2;
    private static final int CHECKBOX_BITMASK = 15;
    private static final String CHILDREN_VIEW_KEY = "childviews";
    private static final String CLASS_NAME_KEY = "classname";
    private static final String CLASS_RCTROOTVIEW = "com.facebook.react.ReactRootView";
    private static final String CLASS_RCTTEXTVIEW = "com.facebook.react.views.view.ReactTextView";
    private static final String CLASS_RCTVIEWGROUP = "com.facebook.react.views.view.ReactViewGroup";
    private static final String CLASS_TOUCHTARGETHELPER = "com.facebook.react.uimanager.TouchTargetHelper";
    private static final String CLASS_TYPE_BITMASK_KEY = "classtypebitmask";
    private static final int CLICKABLE_VIEW_BITMASK = 5;
    private static final String DESC_KEY = "description";
    private static final String DIMENSION_HEIGHT_KEY = "height";
    private static final String DIMENSION_KEY = "dimension";
    private static final String DIMENSION_LEFT_KEY = "left";
    private static final String DIMENSION_SCROLL_X_KEY = "scrollx";
    private static final String DIMENSION_SCROLL_Y_KEY = "scrolly";
    private static final String DIMENSION_TOP_KEY = "top";
    private static final String DIMENSION_VISIBILITY_KEY = "visibility";
    private static final String DIMENSION_WIDTH_KEY = "width";
    private static final String GET_ACCESSIBILITY_METHOD = "getAccessibilityDelegate";
    private static final String HINT_KEY = "hint";
    private static final String ICON_BITMAP = "icon_image";
    private static final int ICON_MAX_EDGE_LENGTH = 44;
    private static final String ID_KEY = "id";
    private static final int IMAGEVIEW_BITMASK = 1;
    private static final int INPUT_BITMASK = 11;
    private static final int LABEL_BITMASK = 10;
    private static final String METHOD_FIND_TOUCHTARGET_VIEW = "findTouchTargetView";
    private static final int PICKER_BITMASK = 12;
    private static final int RADIO_GROUP_BITMASK = 14;
    private static final int RATINGBAR_BITMASK = 16;
    private static WeakReference<View> RCTRootViewReference = new WeakReference<>(null);
    private static final int REACT_NATIVE_BUTTON_BITMASK = 6;
    private static final int SWITCH_BITMASK = 13;
    private static final String TAG = ViewHierarchy.class.getCanonicalName();
    private static final String TAG_KEY = "tag";
    private static final int TEXTVIEW_BITMASK = 0;
    private static final String TEXT_IS_BOLD = "is_bold";
    private static final String TEXT_IS_ITALIC = "is_italic";
    private static final String TEXT_KEY = "text";
    private static final String TEXT_SIZE = "font_size";
    private static final String TEXT_STYLE = "text_style";
    @Nullable
    private static Method methodFindTouchTargetView = null;

    @Nullable
    public static ViewGroup getParentOfView(View view) {
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) parent;
    }

    public static List<View> getChildrenOfView(View view) {
        ArrayList arrayList = new ArrayList();
        if (view != null && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }

    public static JSONObject setBasicInfoOfView(View view, JSONObject jSONObject) {
        try {
            String textOfView = getTextOfView(view);
            String hintOfView = getHintOfView(view);
            Object tag = view.getTag();
            CharSequence contentDescription = view.getContentDescription();
            jSONObject.put(CLASS_NAME_KEY, view.getClass().getCanonicalName());
            jSONObject.put(CLASS_TYPE_BITMASK_KEY, getClassTypeBitmask(view));
            jSONObject.put("id", view.getId());
            if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                jSONObject.put("text", textOfView);
            } else {
                jSONObject.put("text", "");
            }
            jSONObject.put(HINT_KEY, hintOfView);
            if (tag != null) {
                jSONObject.put("tag", tag.toString());
            }
            if (contentDescription != null) {
                jSONObject.put("description", contentDescription.toString());
            }
            jSONObject.put(DIMENSION_KEY, getDimensionOfView(view));
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
        return jSONObject;
    }

    public static JSONObject setAppearanceOfView(View view, JSONObject jSONObject, float f) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                Typeface typeface = textView.getTypeface();
                if (typeface != null) {
                    jSONObject2.put(TEXT_SIZE, (double) textView.getTextSize());
                    jSONObject2.put(TEXT_IS_BOLD, typeface.isBold());
                    jSONObject2.put(TEXT_IS_ITALIC, typeface.isItalic());
                    jSONObject.put(TEXT_STYLE, jSONObject2);
                }
            }
            if (view instanceof ImageView) {
                Drawable drawable = ((ImageView) view).getDrawable();
                if ((drawable instanceof BitmapDrawable) && ((float) view.getHeight()) / f <= 44.0f && ((float) view.getWidth()) / f <= 44.0f) {
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                    jSONObject.put(ICON_BITMAP, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                }
            }
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
        return jSONObject;
    }

    public static JSONObject getDictionaryOfView(View view) {
        if (view.getClass().getName().equals(CLASS_RCTROOTVIEW)) {
            RCTRootViewReference = new WeakReference<>(view);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = setBasicInfoOfView(view, jSONObject);
            JSONArray jSONArray = new JSONArray();
            List childrenOfView = getChildrenOfView(view);
            for (int i = 0; i < childrenOfView.size(); i++) {
                jSONArray.put(getDictionaryOfView((View) childrenOfView.get(i)));
            }
            jSONObject.put(CHILDREN_VIEW_KEY, jSONArray);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for view.", e);
        }
        return jSONObject;
    }

    private static int getClassTypeBitmask(View view) {
        int i = view instanceof ImageView ? 2 : 0;
        if (isClickableView(view)) {
            i |= 32;
        }
        if (isAdapterViewItem(view)) {
            i |= 512;
        }
        if (view instanceof TextView) {
            int i2 = i | 1024 | 1;
            if (view instanceof Button) {
                i2 |= 4;
                if (view instanceof Switch) {
                    i2 |= 8192;
                } else if (view instanceof CheckBox) {
                    i2 |= 32768;
                }
            }
            if (view instanceof EditText) {
                return i2 | 2048;
            }
            return i2;
        } else if ((view instanceof Spinner) || (view instanceof DatePicker)) {
            return i | 4096;
        } else {
            if (view instanceof RatingBar) {
                return i | 65536;
            }
            if (view instanceof RadioGroup) {
                return i | C.DASH_ROLE_CAPTION_FLAG;
            }
            return (!(view instanceof ViewGroup) || !isRCTButton(view, (View) RCTRootViewReference.get())) ? i : i | 64;
        }
    }

    public static boolean isClickableView(View view) {
        boolean z = false;
        try {
            Field declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(view);
            if (obj == null) {
                return false;
            }
            OnClickListener onClickListener = null;
            Field declaredField2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
            if (declaredField2 != null) {
                onClickListener = (OnClickListener) declaredField2.get(obj);
            }
            if (onClickListener != null) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            Log.e(TAG, "Failed to check if the view is clickable.", e);
            return false;
        }
    }

    private static boolean isAdapterViewItem(View view) {
        ViewParent parent = view.getParent();
        return parent != null && ((parent instanceof AdapterView) || (parent instanceof NestedScrollingChild));
    }

    public static String getTextOfView(View view) {
        Object obj = null;
        if (view instanceof TextView) {
            obj = ((TextView) view).getText();
            if (view instanceof Switch) {
                obj = ((Switch) view).isChecked() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0";
            }
        } else if (view instanceof Spinner) {
            Object selectedItem = ((Spinner) view).getSelectedItem();
            if (selectedItem != null) {
                obj = selectedItem.toString();
            }
        } else {
            int i = 0;
            if (view instanceof DatePicker) {
                DatePicker datePicker = (DatePicker) view;
                obj = String.format("%04d-%02d-%02d", new Object[]{Integer.valueOf(datePicker.getYear()), Integer.valueOf(datePicker.getMonth()), Integer.valueOf(datePicker.getDayOfMonth())});
            } else if (view instanceof TimePicker) {
                TimePicker timePicker = (TimePicker) view;
                obj = String.format(StringUtil.SHORT_TIME_FORMAT, new Object[]{Integer.valueOf(timePicker.getCurrentHour().intValue()), Integer.valueOf(timePicker.getCurrentMinute().intValue())});
            } else if (view instanceof RadioGroup) {
                RadioGroup radioGroup = (RadioGroup) view;
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                int childCount = radioGroup.getChildCount();
                while (true) {
                    if (i >= childCount) {
                        break;
                    }
                    View childAt = radioGroup.getChildAt(i);
                    if (childAt.getId() == checkedRadioButtonId && (childAt instanceof RadioButton)) {
                        obj = ((RadioButton) childAt).getText();
                        break;
                    }
                    i++;
                }
            } else if (view instanceof RatingBar) {
                obj = String.valueOf(((RatingBar) view).getRating());
            }
        }
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static String getHintOfView(View view) {
        CharSequence charSequence = view instanceof TextView ? ((TextView) view).getHint() : view instanceof EditText ? ((EditText) view).getHint() : null;
        if (charSequence == null) {
            return "";
        }
        return charSequence.toString();
    }

    private static JSONObject getDimensionOfView(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("top", view.getTop());
            jSONObject.put("left", view.getLeft());
            jSONObject.put("width", view.getWidth());
            jSONObject.put("height", view.getHeight());
            jSONObject.put(DIMENSION_SCROLL_X_KEY, view.getScrollX());
            jSONObject.put(DIMENSION_SCROLL_Y_KEY, view.getScrollY());
            jSONObject.put(DIMENSION_VISIBILITY_KEY, view.getVisibility());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for dimension.", e);
        }
        return jSONObject;
    }

    @Nullable
    public static AccessibilityDelegate getExistingDelegate(View view) {
        try {
            return (AccessibilityDelegate) view.getClass().getMethod(GET_ACCESSIBILITY_METHOD, new Class[0]).invoke(view, new Object[0]);
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (NullPointerException unused2) {
            return null;
        } catch (SecurityException unused3) {
            return null;
        } catch (IllegalAccessException unused4) {
            return null;
        } catch (InvocationTargetException unused5) {
            return null;
        }
    }

    @Nullable
    public static OnTouchListener getExistingOnTouchListener(View view) {
        OnTouchListener onTouchListener;
        try {
            Field declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(view);
            if (obj == null) {
                return null;
            }
            Field declaredField2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener");
            if (declaredField2 != null) {
                declaredField2.setAccessible(true);
                onTouchListener = (OnTouchListener) declaredField2.get(obj);
            } else {
                onTouchListener = null;
            }
            return onTouchListener;
        } catch (NoSuchFieldException e) {
            Utility.logd(TAG, (Exception) e);
            return null;
        } catch (ClassNotFoundException e2) {
            Utility.logd(TAG, (Exception) e2);
            return null;
        } catch (IllegalAccessException e3) {
            Utility.logd(TAG, (Exception) e3);
            return null;
        }
    }

    @Nullable
    public static View getTouchReactView(float[] fArr, @Nullable View view) {
        initTouchTargetHelperMethods();
        Method method = methodFindTouchTargetView;
        if (method == null || view == null) {
            return null;
        }
        try {
            View view2 = (View) method.invoke(null, new Object[]{fArr, view});
            if (view2 != null && view2.getId() > 0) {
                View view3 = (View) view2.getParent();
                if (view3 != null) {
                    return view3;
                }
            }
        } catch (IllegalAccessException e) {
            Utility.logd(TAG, (Exception) e);
        } catch (InvocationTargetException e2) {
            Utility.logd(TAG, (Exception) e2);
        }
        return null;
    }

    public static boolean isRCTButton(View view, @Nullable View view2) {
        boolean z = false;
        if (!view.getClass().getName().equals(CLASS_RCTVIEWGROUP)) {
            return false;
        }
        View touchReactView = getTouchReactView(getViewLocationOnScreen(view), view2);
        if (touchReactView != null && touchReactView.getId() == view.getId()) {
            z = true;
        }
        return z;
    }

    public static boolean isRCTRootView(View view) {
        return view.getClass().getName().equals(CLASS_RCTROOTVIEW);
    }

    public static boolean isRCTTextView(View view) {
        return view.getClass().getName().equals(CLASS_RCTTEXTVIEW);
    }

    public static boolean isRCTViewGroup(View view) {
        return view.getClass().getName().equals(CLASS_RCTVIEWGROUP);
    }

    @Nullable
    public static View findRCTRootView(View view) {
        while (view != null) {
            if (!isRCTRootView(view)) {
                ViewParent parent = view.getParent();
                if (parent == null || !(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } else {
                return view;
            }
        }
        return null;
    }

    private static float[] getViewLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new float[]{(float) iArr[0], (float) iArr[1]};
    }

    private static void initTouchTargetHelperMethods() {
        if (methodFindTouchTargetView == null) {
            try {
                methodFindTouchTargetView = Class.forName(CLASS_TOUCHTARGETHELPER).getDeclaredMethod(METHOD_FIND_TOUCHTARGET_VIEW, new Class[]{float[].class, ViewGroup.class});
                methodFindTouchTargetView.setAccessible(true);
            } catch (ClassNotFoundException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, (Exception) e2);
            }
        }
    }
}
