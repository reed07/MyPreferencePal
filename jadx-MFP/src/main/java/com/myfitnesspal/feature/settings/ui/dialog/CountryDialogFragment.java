package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.activity.EditProfile;
import com.myfitnesspal.feature.settings.util.CountryUpdateListener;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.dialog.SingleChoiceListDialogWithCustomAdapterAndButton;
import dagger.Lazy;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\n \u0010*\u0004\u0018\u00010\u00060\u0006H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0002H\u0014J\b\u0010\u0018\u001a\u00020\u0016H\u0014J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0002H\u0014R$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0000@\u0000X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/dialog/CountryDialogFragment;", "Lcom/myfitnesspal/shared/ui/dialog/SingleChoiceListDialogWithCustomAdapterAndButton;", "Lcom/myfitnesspal/shared/model/v1/Country;", "()V", "countryService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/install/CountryService;", "getCountryService$app_googleRelease", "()Ldagger/Lazy;", "setCountryService$app_googleRelease", "(Ldagger/Lazy;)V", "user", "Lcom/myfitnesspal/shared/model/User;", "getUser", "()Lcom/myfitnesspal/shared/model/User;", "getCountryService", "kotlin.jvm.PlatformType", "getInitialSelectedItemIndex", "", "getItems", "", "getTextFromItem", "", "item", "getTitle", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onOkClicked", "", "selectedItem", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: CountryDialogFragment.kt */
public final class CountryDialogFragment extends SingleChoiceListDialogWithCustomAdapterAndButton<Country> {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public Lazy<CountryService> countryService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/settings/ui/dialog/CountryDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/myfitnesspal/feature/settings/ui/dialog/CountryDialogFragment;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CountryDialogFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final CountryDialogFragment newInstance() {
            return new CountryDialogFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final CountryDialogFragment newInstance() {
        return Companion.newInstance();
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
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public final Lazy<CountryService> getCountryService$app_googleRelease() {
        Lazy<CountryService> lazy = this.countryService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryService");
        }
        return lazy;
    }

    public final void setCountryService$app_googleRelease(@NotNull Lazy<CountryService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.countryService = lazy;
    }

    private final User getUser() {
        return getSession().getUser();
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        component().inject(this);
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String getTitle() {
        String string = getString(R.string.location);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.location)");
        return string;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public List<Country> getItems() {
        return getCountryService().getAllSupportedCountries();
    }

    /* access modifiers changed from: protected */
    public int getInitialSelectedItemIndex() {
        CountryService countryService2 = getCountryService();
        UserProfile profile = getUser().getProfile();
        Intrinsics.checkExpressionValueIsNotNull(profile, "user.profile");
        String countryName = profile.getCountryName();
        Intrinsics.checkExpressionValueIsNotNull(countryName, "user.profile.countryName");
        return countryService2.getIndexOfLongName(countryName);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String getTextFromItem(@NotNull Country country) {
        Intrinsics.checkParameterIsNotNull(country, Attributes.ITEM);
        return getCountryService().getLocalizedLongCountryName(country);
    }

    /* access modifiers changed from: protected */
    public void onOkClicked(@NotNull Country country) {
        Intrinsics.checkParameterIsNotNull(country, "selectedItem");
        String shortName = country.getShortName();
        if (shortName != null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                CountryUpdateListener countryUpdateListener = ((EditProfile) activity).getCountryUpdateListener();
                if (countryUpdateListener != null) {
                    countryUpdateListener.onCountrySelected(shortName);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.feature.settings.ui.activity.EditProfile");
        }
    }

    private final CountryService getCountryService() {
        Lazy<CountryService> lazy = this.countryService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryService");
        }
        return (CountryService) lazy.get();
    }
}
