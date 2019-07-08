package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.gdprhelp.activity.GDPRHelpActivity;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.registration.model.UpdatedTermsInfo;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.ButtonName;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper.CountryTier;
import com.myfitnesspal.feature.registration.task.GetUpdateTermsInfoTask;
import com.myfitnesspal.feature.registration.task.GetUpdateTermsInfoTask.CompletedEvent;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Provider;

public class UpdatedTermsActivity extends MfpActivity implements OnMenuItemClickListener {
    private static Date LATEST_DATE_FOR_CACHED_TERMS;
    private final int MENU_HELP_ACTION = 1101;
    @BindView(2131361812)
    Button acceptBtn;
    @Inject
    Provider<MfpV2Api> apiProvider;
    @BindView(2131362041)
    View buttonContainer;
    @BindView(2131362211)
    View contentContainer;
    @Inject
    Lazy<CountryService> countryService;
    private State currentState;
    @BindView(2131364089)
    TextView disclaimerTextView;
    @BindView(2131362495)
    TextView errorDisclaimerTextView;
    @Inject
    Lazy<GDPRHelpAnalyticsHelper> gdprHelpAnalyticsHelper;
    @BindView(2131362943)
    View loadingView;
    private boolean needsToAcceptTOS;
    @BindView(2131364090)
    TextView summaryTextView;
    @BindView(2131364091)
    TextView titleTextView;
    @Inject
    Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;

    private enum State {
        Loading,
        Error,
        DisplayInfo
    }

    public int getCustomToolbarLayoutResId() {
        return R.layout.updated_terms_toolbar_layout;
    }

    public void onBackPressed() {
    }

    static {
        Calendar instance = Calendar.getInstance();
        instance.set(2, 3);
        instance.set(1, 2016);
        instance.set(5, 21);
        LATEST_DATE_FOR_CACHED_TERMS = instance.getTime();
    }

    public static Intent newStartIntent(Context context, Bundle bundle) {
        return new Intent(context, UpdatedTermsActivity.class).putExtra(Extras.ORIGINAL_EXTRAS, bundle);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.updated_terms);
        this.needsToAcceptTOS = ((CountryService) this.countryService.get()).needsToAcceptTOS(getSession().getUser().getLocationPreferences().getCountryCode());
        requestUpdatedTermsInfo();
        setupButton();
    }

    public void onUpPressed() {
        reportModalAcceptClickedEvent(ButtonName.Dismiss);
        onBackPressed();
    }

    @Subscribe
    public void onGetUpdatedTermsInfoTaskCompletedEvent(CompletedEvent completedEvent) {
        boolean z = completedEvent.getFailure() == null;
        reportModalDisplayedEvent(z);
        if (z) {
            setState(State.DisplayInfo, (UpdatedTermsInfo) completedEvent.getResult());
        } else {
            setState(State.Error);
        }
    }

    private void setState(State state) {
        setState(state, null);
    }

    private void setState(State state, UpdatedTermsInfo updatedTermsInfo) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        if (state != this.currentState) {
            switch (state) {
                case DisplayInfo:
                    displayContentStrings(updatedTermsInfo);
                    z = true;
                    z6 = true;
                    z5 = false;
                    z4 = true;
                    z3 = true;
                    z2 = false;
                    break;
                case Error:
                    boolean z7 = this.needsToAcceptTOS;
                    z6 = !z7;
                    boolean z8 = !z7;
                    displayErrorStrings();
                    z2 = z8;
                    z = true;
                    z5 = false;
                    z4 = false;
                    z3 = false;
                    break;
                case Loading:
                    z = false;
                    z6 = false;
                    z5 = true;
                    z4 = false;
                    z3 = false;
                    z2 = false;
                    break;
                default:
                    throw new IllegalStateException("Invalid state!!");
            }
            this.acceptBtn.setText(R.string.accept);
            ViewUtils.setVisible(z6, this.buttonContainer);
            ViewUtils.setVisible(z, this.contentContainer);
            ViewUtils.setVisible(z5, this.loadingView);
            ViewUtils.setVisible(z4, this.titleTextView);
            ViewUtils.setVisible(z3, this.disclaimerTextView);
            ViewUtils.setVisible(z2, this.errorDisclaimerTextView);
            setupToolbarUpButton();
            setMenuForToolbar();
            if (!this.needsToAcceptTOS && this.currentState != State.Loading) {
                setUserAcceptedTOS();
            }
            this.currentState = state;
        }
    }

    private void displayErrorStrings() {
        setupDisclaimerText(this.summaryTextView, this.needsToAcceptTOS ? R.string.updated_terms_error_eu : R.string.updated_terms_error_us);
    }

    private void displayContentStrings(UpdatedTermsInfo updatedTermsInfo) {
        setHeaderText(updatedTermsInfo.getHeader());
        setSummaryText(updatedTermsInfo.getSummary());
        setFooterText(updatedTermsInfo.getFooter());
    }

    private void setSummaryText(String str) {
        this.summaryTextView.setText(str);
    }

    private void setHeaderText(String str) {
        this.titleTextView.setText(str);
    }

    private void setFooterText(String str) {
        setupDisclaimerText(this.disclaimerTextView, str);
    }

    private void requestUpdatedTermsInfo() {
        if (DateTimeUtils.isDateInThePast(LATEST_DATE_FOR_CACHED_TERMS)) {
            new GetUpdateTermsInfoTask(this.apiProvider).run(getRunner());
            setState(State.Loading);
            return;
        }
        int i = this.needsToAcceptTOS ? R.string.updated_terms_disclaimer_eu : R.string.updated_terms_disclaimer_us;
        setupDisclaimerText(this.errorDisclaimerTextView, i);
        setState(State.DisplayInfo, new UpdatedTermsInfo(getString(R.string.updated_terms_summary), getString(R.string.updated_terms_title), getString(i)));
        reportModalDisplayedEvent(true);
    }

    private void reportModalDisplayedEvent(boolean z) {
        ((UpdatedTermsAnalyticsHelper) this.updatedTermsAnalyticsHelper.get()).reportModalViewedEvent(getCountryTier(), z);
    }

    private void reportModalAcceptClickedEvent(ButtonName buttonName) {
        ((UpdatedTermsAnalyticsHelper) this.updatedTermsAnalyticsHelper.get()).reportModalButtonClicked(getCountryTier(), buttonName);
    }

    private CountryTier getCountryTier() {
        return this.needsToAcceptTOS ? CountryTier.EU : CountryTier.US;
    }

    private void setupToolbarUpButton() {
        Toolbar toolbar = getToolbar();
        toolbar.setNavigationIcon((int) R.drawable.transparent_background);
        toolbar.setNavigationOnClickListener(null);
    }

    private void setupDisclaimerText(TextView textView, int i) {
        SignUpUtil.setupDisclaimerText(textView, getNavigationHelper(), i, this.updatedTermsAnalyticsHelper, getCountryTier(), UpdatedTermsAnalyticsHelper.TOS_UPDATE_MODAL_VIEWED);
    }

    private void setupDisclaimerText(TextView textView, String str) {
        SignUpUtil.setupDisclaimerText(textView, getNavigationHelper(), str, this.updatedTermsAnalyticsHelper, getCountryTier(), UpdatedTermsAnalyticsHelper.TOS_UPDATE_MODAL_VIEWED);
    }

    private void setupButton() {
        this.acceptBtn.setText(this.needsToAcceptTOS ? R.string.accept : R.string.continueBtn);
        this.acceptBtn.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                UpdatedTermsActivity.lambda$setupButton$0(UpdatedTermsActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$setupButton$0(UpdatedTermsActivity updatedTermsActivity, View view) {
        if (updatedTermsActivity.needsToAcceptTOS) {
            updatedTermsActivity.setUserAcceptedTOS();
        }
        updatedTermsActivity.reportModalAcceptClickedEvent(updatedTermsActivity.needsToAcceptTOS ? ButtonName.Accept : ButtonName.Continue);
        updatedTermsActivity.navigateToHome();
    }

    private void setUserAcceptedTOS() {
        User user = getSession().getUser();
        if (!user.hasAcceptedTermsAndPrivacy()) {
            user.markTOSAccepted();
            scheduleSync();
        }
    }

    private void navigateToHome() {
        Bundle bundle = ExtrasUtils.getBundle(getIntent(), Extras.ORIGINAL_EXTRAS);
        NavigationHelper withExtras = getNavigationHelper().finishActivityAfterNavigation().withExtras(bundle);
        if (this.currentState == State.Error && this.needsToAcceptTOS) {
            withExtras.withExtra(Extras.SKIP_PRIVACY_CHECK_ONCE, true);
        }
        withExtras.withExtras(bundle).withIntent(HomeActivity.newStartIntent(this)).startActivity();
    }

    private void setMenuForToolbar() {
        Toolbar toolbar = getToolbar();
        if (toolbar != null && toolbar.getMenu() != null) {
            Menu menu = toolbar.getMenu();
            menu.clear();
            menu.add(0, 1101, 0, R.string.menu_help).setIcon(R.drawable.ic_help_white).setEnabled(true).setShowAsAction(2);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return UpdatedTermsActivity.this.onMenuItemClick(menuItem);
                }
            });
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 1101) {
            return false;
        }
        ((GDPRHelpAnalyticsHelper) this.gdprHelpAnalyticsHelper.get()).reportHelpSee(UpdatedTermsAnalyticsHelper.TOS_UPDATE_MODAL_VIEWED);
        getNavigationHelper().withIntent(GDPRHelpActivity.newStartIntent(this, UpdatedTermsAnalyticsHelper.TOS_UPDATE_MODAL_VIEWED)).startActivity();
        return true;
    }
}
