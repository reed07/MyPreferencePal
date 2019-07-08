package com.myfitnesspal.shared.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public final class FragmentUtil {
    public static boolean doesFragmentExist(FragmentManager fragmentManager, String str) {
        return fragmentManager.findFragmentByTag(str) != null;
    }

    public static <T> T getTargetFragmentOrParentActivity(Fragment fragment, Class<? extends T> cls) {
        T classInstanceFromObject = getClassInstanceFromObject(fragment.getTargetFragment(), cls);
        return classInstanceFromObject != null ? classInstanceFromObject : getClassInstanceFromObject(fragment.getActivity(), cls);
    }

    private static <T> T getClassInstanceFromObject(Object obj, Class<? extends T> cls) {
        if (obj == null || !cls.isAssignableFrom(obj.getClass())) {
            return null;
        }
        return obj;
    }

    public static boolean isFragmentVisible(Fragment fragment) {
        return fragment != null && fragment.isVisible();
    }

    public static Fragment getVisibleFragment(FragmentManager fragmentManager, int i) {
        return fragmentManager.findFragmentById(i);
    }

    public static <T> T find(FragmentManager fragmentManager, String str) {
        return fragmentManager.findFragmentByTag(str);
    }

    public static void replace(FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, int i, Fragment fragment, String str) {
        if (fragmentManager.findFragmentByTag(str) == null) {
            fragmentTransaction.replace(i, fragment, str);
        }
    }

    public static void replace(FragmentManager fragmentManager, int i, Fragment fragment, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        replace(fragmentManager, i, fragment, str);
        beginTransaction.commit();
    }
}
