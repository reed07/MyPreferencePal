package com.myfitnesspal.feature.debug.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/feature/debug/ui/activity/AdvancedDebuggingActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "shouldDisplayAds", "", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdvancedDebuggingActivity.kt */
public final class AdvancedDebuggingActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/debug/ui/activity/AdvancedDebuggingActivity$Companion;", "", "()V", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdvancedDebuggingActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent newStartIntent(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent(context, AdvancedDebuggingActivity.class);
        }
    }

    @JvmStatic
    @NotNull
    public static final Intent newStartIntent(@NotNull Context context) {
        return Companion.newStartIntent(context);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public boolean shouldDisplayAds() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.advanced_debugging_activity);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AdvancedDebuggingFragment.newInstance()).commit();
        }
    }
}
