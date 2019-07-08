package com.myfitnesspal.android;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.myfitnesspal.android.databinding.ActivityAlexaInterstitialBindingImpl;
import com.myfitnesspal.android.databinding.ActivityAlexaInterstitialBindingLandImpl;
import com.myfitnesspal.android.databinding.ChangePasswordBindingImpl;
import com.myfitnesspal.android.databinding.OnboardingAnimatedFragmentBindingImpl;
import com.myfitnesspal.android.databinding.OnboardingAnimatedFragmentBindingSw600dpImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);
    private static final int LAYOUT_ACTIVITYALEXAINTERSTITIAL = 1;
    private static final int LAYOUT_CHANGEPASSWORD = 2;
    private static final int LAYOUT_ONBOARDINGANIMATEDFRAGMENT = 3;

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(5);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "textCreator");
            sKeys.put(2, "viewmodel");
            sKeys.put(3, "viewModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(5);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout-land/activity_alexa_interstitial_0", Integer.valueOf(R.layout.activity_alexa_interstitial));
            sKeys.put("layout/activity_alexa_interstitial_0", Integer.valueOf(R.layout.activity_alexa_interstitial));
            sKeys.put("layout/change_password_0", Integer.valueOf(R.layout.change_password));
            sKeys.put("layout-sw600dp/onboarding_animated_fragment_0", Integer.valueOf(R.layout.onboarding_animated_fragment));
            sKeys.put("layout/onboarding_animated_fragment_0", Integer.valueOf(R.layout.onboarding_animated_fragment));
        }
    }

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_alexa_interstitial, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.change_password, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.onboarding_animated_fragment, 3);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout-land/activity_alexa_interstitial_0".equals(tag)) {
                            return new ActivityAlexaInterstitialBindingLandImpl(dataBindingComponent, view);
                        }
                        if ("layout/activity_alexa_interstitial_0".equals(tag)) {
                            return new ActivityAlexaInterstitialBindingImpl(dataBindingComponent, view);
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("The tag for activity_alexa_interstitial is invalid. Received: ");
                        sb.append(tag);
                        throw new IllegalArgumentException(sb.toString());
                    case 2:
                        if ("layout/change_password_0".equals(tag)) {
                            return new ChangePasswordBindingImpl(dataBindingComponent, view);
                        }
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("The tag for change_password is invalid. Received: ");
                        sb2.append(tag);
                        throw new IllegalArgumentException(sb2.toString());
                    case 3:
                        if ("layout-sw600dp/onboarding_animated_fragment_0".equals(tag)) {
                            return new OnboardingAnimatedFragmentBindingSw600dpImpl(dataBindingComponent, view);
                        }
                        if ("layout/onboarding_animated_fragment_0".equals(tag)) {
                            return new OnboardingAnimatedFragmentBindingImpl(dataBindingComponent, view);
                        }
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("The tag for onboarding_animated_fragment is invalid. Received: ");
                        sb3.append(tag);
                        throw new IllegalArgumentException(sb3.toString());
                }
            } else {
                throw new RuntimeException("view must have a tag");
            }
        }
        return null;
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        int i = 0;
        if (str == null) {
            return 0;
        }
        Integer num = (Integer) InnerLayoutIdLookup.sKeys.get(str);
        if (num != null) {
            i = num.intValue();
        }
        return i;
    }

    public String convertBrIdToString(int i) {
        return (String) InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }
}
