package com.myfitnesspal.feature.gdprhelp.activity;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelper;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelperImpl;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0017H\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0002R$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\tR$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\t¨\u0006&"}, d2 = {"Lcom/myfitnesspal/feature/gdprhelp/activity/GDPRHelpActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "deleteAccountAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/deleteaccount/service/DeleteAccountAnalyticsHelper;", "getDeleteAccountAnalyticsHelper", "()Ldagger/Lazy;", "setDeleteAccountAnalyticsHelper", "(Ldagger/Lazy;)V", "gdprHelpAnalyticsHelper", "Lcom/myfitnesspal/feature/gdprhelp/util/GDPRHelpAnalyticsHelper;", "getGdprHelpAnalyticsHelper", "setGdprHelpAnalyticsHelper", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "getPremiumService", "setPremiumService", "updatedTermsAnalyticsHelper", "Lcom/myfitnesspal/feature/registration/service/UpdatedTermsAnalyticsHelper;", "getUpdatedTermsAnalyticsHelper", "setUpdatedTermsAnalyticsHelper", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "setupClickListeners", "setupHelpText", "gdprHelpText", "Landroid/widget/TextView;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: GDPRHelpActivity.kt */
public final class GDPRHelpActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    private static final int MENU_LOGOUT_ACTION = 1102;
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public Lazy<DeleteAccountAnalyticsHelper> deleteAccountAnalyticsHelper;
    @Inject
    @NotNull
    public Lazy<GDPRHelpAnalyticsHelper> gdprHelpAnalyticsHelper;
    @Inject
    @NotNull
    public Lazy<PremiumService> premiumService;
    @Inject
    @NotNull
    public Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/myfitnesspal/feature/gdprhelp/activity/GDPRHelpActivity$Companion;", "", "()V", "MENU_LOGOUT_ACTION", "", "newStartIntent", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "entryPoint", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: GDPRHelpActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Intent newStartIntent(@NotNull Context context, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(str, "entryPoint");
            return new Intent(context, GDPRHelpActivity.class).putExtra("entry_point", str);
        }
    }

    @JvmStatic
    public static final Intent newStartIntent(@NotNull Context context, @NotNull String str) {
        return Companion.newStartIntent(context, str);
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
    public final Lazy<PremiumService> getPremiumService() {
        Lazy<PremiumService> lazy = this.premiumService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumService");
        }
        return lazy;
    }

    public final void setPremiumService(@NotNull Lazy<PremiumService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.premiumService = lazy;
    }

    @NotNull
    public final Lazy<DeleteAccountAnalyticsHelper> getDeleteAccountAnalyticsHelper() {
        Lazy<DeleteAccountAnalyticsHelper> lazy = this.deleteAccountAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deleteAccountAnalyticsHelper");
        }
        return lazy;
    }

    public final void setDeleteAccountAnalyticsHelper(@NotNull Lazy<DeleteAccountAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.deleteAccountAnalyticsHelper = lazy;
    }

    @NotNull
    public final Lazy<GDPRHelpAnalyticsHelper> getGdprHelpAnalyticsHelper() {
        Lazy<GDPRHelpAnalyticsHelper> lazy = this.gdprHelpAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gdprHelpAnalyticsHelper");
        }
        return lazy;
    }

    public final void setGdprHelpAnalyticsHelper(@NotNull Lazy<GDPRHelpAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.gdprHelpAnalyticsHelper = lazy;
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
        setContentView((int) R.layout.gdpr_help_activity);
        setupClickListeners();
        TextView textView = (TextView) _$_findCachedViewById(R.id.gdpr_help_text);
        Intrinsics.checkExpressionValueIsNotNull(textView, "gdpr_help_text");
        setupHelpText(textView);
    }

    public boolean onPrepareOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        menu.clear();
        menu.add(0, MENU_LOGOUT_ACTION, 0, R.string.settings_log_out).setIcon(R.drawable.ic_logout_white_24px).setEnabled(true).setShowAsAction(2);
        return true;
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != MENU_LOGOUT_ACTION) {
            return super.onOptionsItemSelected(menuItem);
        }
        new MfpAlertDialogBuilder(this).setTitle((int) R.string.logout_title).setMessage((int) R.string.logout_message).setPositiveButton((int) R.string.yesBtn, (OnClickListener) new GDPRHelpActivity$onOptionsItemSelected$1(this)).setNegativeButton((int) R.string.noBtn, (OnClickListener) GDPRHelpActivity$onOptionsItemSelected$2.INSTANCE).show();
        return true;
    }

    private final void setupClickListeners() {
        ((TextView) _$_findCachedViewById(R.id.aboutUs)).setOnClickListener(new GDPRHelpActivity$setupClickListeners$1(this));
        ((TextView) _$_findCachedViewById(R.id.faq)).setOnClickListener(new GDPRHelpActivity$setupClickListeners$2(this));
        ((TextView) _$_findCachedViewById(R.id.tos)).setOnClickListener(new GDPRHelpActivity$setupClickListeners$3(this));
        ((TextView) _$_findCachedViewById(R.id.delete_card_button)).setOnClickListener(new GDPRHelpActivity$setupClickListeners$4(this));
    }

    private final void setupHelpText(TextView textView) {
        NavigationHelper navigationHelper = getNavigationHelper();
        Lazy<UpdatedTermsAnalyticsHelper> lazy = this.updatedTermsAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updatedTermsAnalyticsHelper");
        }
        SignUpUtil.setupDisclaimerTextForGDPR(textView, navigationHelper, R.string.gdpr_help_text, lazy, GDPRHelpAnalyticsHelperImpl.HELP_SEE);
    }
}
