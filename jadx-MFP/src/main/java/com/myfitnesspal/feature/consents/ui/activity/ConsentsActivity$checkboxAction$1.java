package com.myfitnesspal.feature.consents.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity.ExportMode;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountPremiumActivity;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import io.uacf.consentservices.sdk.UacfConsent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "consent", "Lio/uacf/consentservices/sdk/UacfConsent;", "view", "Landroid/view/View;", "isChecked", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsActivity.kt */
final class ConsentsActivity$checkboxAction$1 extends Lambda implements Function3<UacfConsent, View, Boolean, Unit> {
    final /* synthetic */ ConsentsActivity this$0;

    ConsentsActivity$checkboxAction$1(ConsentsActivity consentsActivity) {
        this.this$0 = consentsActivity;
        super(3);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((UacfConsent) obj, (View) obj2, ((Boolean) obj3).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull final UacfConsent uacfConsent, @NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(uacfConsent, "consent");
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        if (this.this$0.getViewModel().isEditMode()) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "view.checkBox");
            checkBox.setChecked(true);
            ConsentsAnalyticsHelper consentsAnalyticsHelper = (ConsentsAnalyticsHelper) this.this$0.getConsentsAnalyticsHelper().get();
            String id = uacfConsent.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "consent.id");
            consentsAnalyticsHelper.reportConsentWithdrawIntiated(id);
            AlertDialogFragment newInstance = AlertDialogFragment.Companion.newInstance();
            ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) newInstance.setTitle(R.string.are_you_sure)).setMessage((int) R.string.remove_consent_confirmation)).setPositiveText(R.string.delete_account, new DialogPositiveListener<Object>(this) {
                final /* synthetic */ ConsentsActivity$checkboxAction$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void onClick(Object obj) {
                    Intent intent;
                    ((ConsentsAnalyticsHelper) this.this$0.this$0.getConsentsAnalyticsHelper().get()).reportConsentWithdrawDeleteAccept(true);
                    ((DeleteAccountAnalyticsHelper) this.this$0.this$0.getDeleteAccountAnalyticsHelper().get()).reportAccountDeleteStarted(((ConsentsAnalyticsHelper) this.this$0.this$0.getConsentsAnalyticsHelper().get()).getScreenNameBasedOnMode(this.this$0.this$0.getViewModel().getMode()), ((ConsentsAnalyticsHelper) this.this$0.this$0.getConsentsAnalyticsHelper().get()).getScreenNameBasedOnMode(this.this$0.this$0.getViewModel().getMode()));
                    NavigationHelper navigationHelper = this.this$0.this$0.getNavigationHelper();
                    PremiumService premiumService = this.this$0.this$0.getPremiumService();
                    Intrinsics.checkExpressionValueIsNotNull(premiumService, "premiumService");
                    if (premiumService.isPremiumSubscribed()) {
                        intent = DeleteAccountPremiumActivity.newStartIntentFromManageConsents(this.this$0.this$0.getActivity(), ExportMode.Enabled, uacfConsent.getId());
                    } else {
                        intent = DeleteAccountActivity.newStartIntentFromManageConsents(this.this$0.this$0.getActivity(), ExportMode.Enabled, uacfConsent.getId());
                    }
                    navigationHelper.withIntent(intent).startActivity();
                }
            })).setNegativeText(R.string.cancel, new DialogNegativeListener(this) {
                final /* synthetic */ ConsentsActivity$checkboxAction$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void onClick() {
                    ((ConsentsAnalyticsHelper) this.this$0.this$0.getConsentsAnalyticsHelper().get()).reportConsentWithdrawDeleteAccept(false);
                }
            });
            this.this$0.showDialogFragment(newInstance, ConsentsActivity.MUST_DELETE_DIALOG_FRAGMENT);
            return;
        }
        this.this$0.getViewModel().markConsentAsChecked(uacfConsent, z);
    }
}
