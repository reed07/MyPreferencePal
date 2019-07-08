package com.myfitnesspal.feature.consents.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.myfitnesspal.shared.model.FullScreenWebViewIntentExtras;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/consents/ui/activity/UAAndAffiliatesActivity;", "Lcom/myfitnesspal/shared/ui/activity/impl/FullScreenWebView;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: UAAndAffiliatesActivity.kt */
public final class UAAndAffiliatesActivity extends FullScreenWebView {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/consents/ui/activity/UAAndAffiliatesActivity$Companion;", "", "()V", "newStartIntent", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "url", "", "title", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UAAndAffiliatesActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Intent newStartIntent(@NotNull Context context, @NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(str, "url");
            Intrinsics.checkParameterIsNotNull(str2, "title");
            return new Intent(context, LearnMoreActivity.class).putExtra("web_view_intent_extras", new FullScreenWebViewIntentExtras().setTitle(str2).setUrl(str).setShowCloseAsBackButton(true).setHandleAllClicksExternally(false).setAddLanguageHeader(true).setLoadUrlWithAuthHeader(false));
        }
    }

    @JvmStatic
    public static final Intent newStartIntent(@NotNull Context context, @NotNull String str, @NotNull String str2) {
        return Companion.newStartIntent(context, str, str2);
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

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject((FullScreenWebView) this);
        super.onCreate(bundle);
    }
}
