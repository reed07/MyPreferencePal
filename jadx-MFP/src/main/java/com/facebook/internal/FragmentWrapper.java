package com.facebook.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import com.brightcove.player.event.AbstractEvent;

public class FragmentWrapper {
    private Fragment nativeFragment;
    private android.support.v4.app.Fragment supportFragment;

    public FragmentWrapper(android.support.v4.app.Fragment fragment) {
        Validate.notNull(fragment, AbstractEvent.FRAGMENT);
        this.supportFragment = fragment;
    }

    public FragmentWrapper(Fragment fragment) {
        Validate.notNull(fragment, AbstractEvent.FRAGMENT);
        this.nativeFragment = fragment;
    }

    public Fragment getNativeFragment() {
        return this.nativeFragment;
    }

    public android.support.v4.app.Fragment getSupportFragment() {
        return this.supportFragment;
    }

    public void startActivityForResult(Intent intent, int i) {
        android.support.v4.app.Fragment fragment = this.supportFragment;
        if (fragment != null) {
            fragment.startActivityForResult(intent, i);
        } else {
            this.nativeFragment.startActivityForResult(intent, i);
        }
    }

    public final Activity getActivity() {
        android.support.v4.app.Fragment fragment = this.supportFragment;
        if (fragment != null) {
            return fragment.getActivity();
        }
        return this.nativeFragment.getActivity();
    }
}
