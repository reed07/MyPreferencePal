package com.twitter.sdk.android.core.internal.persistence;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public interface PreferenceStore {
    Editor edit();

    SharedPreferences get();

    boolean save(Editor editor);
}
