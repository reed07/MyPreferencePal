package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.ConfigUtils;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/activity/PrivacyCenterActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "consentsService", "Lcom/myfitnesspal/feature/consents/service/ConsentsService;", "getConsentsService", "()Lcom/myfitnesspal/feature/consents/service/ConsentsService;", "setConsentsService", "(Lcom/myfitnesspal/feature/consents/service/ConsentsService;)V", "updatedTermsAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/registration/service/UpdatedTermsAnalyticsHelper;", "getUpdatedTermsAnalyticsHelper", "()Ldagger/Lazy;", "setUpdatedTermsAnalyticsHelper", "(Ldagger/Lazy;)V", "initUi", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PrivacyCenterActivity.kt */
public final class PrivacyCenterActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public ConsentsService consentsService;
    @Inject
    @NotNull
    public Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/activity/PrivacyCenterActivity$Companion;", "", "()V", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: PrivacyCenterActivity.kt */
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
            return new Intent(context, PrivacyCenterActivity.class);
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

    @NotNull
    public final ConsentsService getConsentsService() {
        ConsentsService consentsService2 = this.consentsService;
        if (consentsService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consentsService");
        }
        return consentsService2;
    }

    public final void setConsentsService(@NotNull ConsentsService consentsService2) {
        Intrinsics.checkParameterIsNotNull(consentsService2, "<set-?>");
        this.consentsService = consentsService2;
    }

    @NotNull
    public final Lazy<UpdatedTermsAnalyticsHelper> getUpdatedTermsAnalyticsHelper() {
        Lazy<UpdatedTermsAnalyticsHelper> lazy = this.updatedTermsAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updatedTermsAnalyticsHelper");
        }
        return lazy;
    }

    public final void setUpdatedTermsAnalyticsHelper(@NotNull Lazy<UpdatedTermsAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.updatedTermsAnalyticsHelper = lazy;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.privacy_center);
        initUi();
    }

    private final void initUi() {
        ((TextView) _$_findCachedViewById(R.id.tos)).setOnClickListener(new PrivacyCenterActivity$initUi$1(this));
        ((TextView) _$_findCachedViewById(R.id.privacy_policy)).setOnClickListener(new PrivacyCenterActivity$initUi$2(this));
        ((TextView) _$_findCachedViewById(R.id.sharing_and_privacy)).setOnClickListener(new PrivacyCenterActivity$initUi$3(this));
        ((TextView) _$_findCachedViewById(R.id.contact_support)).setOnClickListener(new PrivacyCenterActivity$initUi$4(this));
        ConsentsService consentsService2 = this.consentsService;
        if (consentsService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consentsService");
        }
        if (consentsService2.isUserAcceptedConsentsMatrix()) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.manage_consents);
            Intrinsics.checkExpressionValueIsNotNull(textView, "manage_consents");
            textView.setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.manage_consents)).setOnClickListener(new PrivacyCenterActivity$initUi$5(this));
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.manage_consents);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "manage_consents");
            textView2.setVisibility(8);
        }
        if (ConfigUtils.isAdConsentsSettingsEnabled(getConfigService())) {
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.personalization);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "personalization");
            textView3.setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.personalization)).setOnClickListener(new PrivacyCenterActivity$initUi$6(this));
            return;
        }
        TextView textView4 = (TextView) _$_findCachedViewById(R.id.personalization);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "personalization");
        textView4.setVisibility(8);
    }
}
