package com.myfitnesspal.feature.fileexport.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity;
import com.myfitnesspal.feature.fileexport.constant.ReportingPeriod;
import com.myfitnesspal.feature.fileexport.service.FileExportAnalyticsHelper;
import com.myfitnesspal.feature.fileexport.service.FileExportService;
import com.myfitnesspal.feature.fileexport.task.FileExportTask;
import com.myfitnesspal.feature.fileexport.task.FileExportTask.CompletedEvent;
import com.myfitnesspal.feature.fileexport.ui.dialog.FileExportReportingPeriodSelectionDialogFragment;
import com.myfitnesspal.feature.fileexport.ui.dialog.FileExportReportingPeriodSelectionDialogFragment.OnPeriodSelectedListener;
import com.myfitnesspal.feature.help.ui.activity.EmailSettings;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.SpanUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.ExportType;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

public class FileExport extends MfpActivity implements OnPeriodSelectedListener {
    private static final ReportingPeriod DEFAULT_REPORTING_PERIOD = ReportingPeriod.Last7Days;
    private static final String EMAIL_NOT_VERIFIED_DIALOG_TAG = "email_not_verified_dialog";
    private static final String EVENT_FILE_EXPORTED = "file_exported";
    private static final String EXTRA_CURRENT_VIEW_STATE = "current_view_state";
    private static final String EXTRA_EXPORT_MODE = "extra_export_mode";
    private static final String EXTRA_FROM_DATE = "from_date";
    private static final String EXTRA_SELECTED_REPORTING_PERIOD = "selected_reporting_period";
    private static final String EXTRA_TO_DATE = "to_date";
    private static final String KEY_REPORT_LENGTH = "report-length";
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    private static final String SCREEN_FILE_EXPORT = "file_export";
    @BindView(2131362124)
    TextView changeEmailButton;
    @BindView(2131362753)
    TextView changeEmailInfoTextView;
    @BindView(2131362133)
    TextView checkEmailTextView;
    private ViewState currentViewState;
    @BindView(2131364219)
    TextView dataSentInfoTextView;
    @BindView(2131362339)
    View divider;
    @Inject
    Lazy<UacfEmailVerificationManager> emailVerificationManager;
    @BindView(2131362450)
    View emailVerified;
    @BindView(2131362537)
    View exportButton;
    @BindView(2131362538)
    TextView exportConfirmationText;
    @BindView(2131362539)
    View exportImageView;
    @BindView(2131362541)
    TextView exportTextView;
    @Inject
    Lazy<FileExportAnalyticsHelper> fileExportAnalyticsHelper;
    @BindView(2131362574)
    View fileExportCompleteContainer;
    @BindView(2131362575)
    View fileExportInfoContainer;
    @BindView(2131362576)
    View fileExportLoadingContainer;
    @BindView(2131362577)
    View fileExportPremiumCta;
    @Inject
    Lazy<FileExportService> fileExportService;
    private Date fromDate;
    @BindView(2131362754)
    View howChangeEmailTitle;
    @Inject
    Lazy<UacfIdentitySdk> identitySdk;
    private boolean isDeleteAccount;
    private boolean isFileExportPreviewEnabled;
    private boolean isFileExportSubscribed;
    @BindView(2131362497)
    TextView messageTextView;
    private OnClickListener navigateToCustomerSupportClickListener = new OnClickListener() {
        public final void onClick(View view) {
            FileExport.lambda$new$9(FileExport.this, view);
        }
    };
    private OnClickListener navigateToEmailSettingsClickListener = new OnClickListener() {
        public final void onClick(View view) {
            FileExport.this.getNavigationHelper().withIntent(EmailSettings.newStartIntent(FileExport.this)).startActivity();
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;
    private ReportingPeriod reportingPeriod;
    @BindView(2131363455)
    View reportingPeriodContainer;
    @BindView(2131363456)
    TextView reportingPeriodTextView;
    private Date toDate;
    @Inject
    Lazy<UserPropertiesService> userPropertiesService;
    @BindView(2131364139)
    View verifyEmailContainer;
    @BindView(2131364140)
    TextView verifyEmailInfoTextView;

    public enum ExportMode {
        Normal,
        DeleteAccount
    }

    private enum ViewState {
        FileExportInfo,
        FileExportComplete,
        VerifyEmail,
        Loading
    }

    public String getAnalyticsScreenTag() {
        return SCREEN_FILE_EXPORT;
    }

    public static Intent createIntentForFileExport(Context context, ExportMode exportMode) {
        return new Intent(context, FileExport.class).putExtra("withinApp", Boolean.TRUE.toString()).putExtra(EXTRA_EXPORT_MODE, exportMode);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.file_export);
        this.isDeleteAccount = BundleUtils.getSerializable(getIntent().getExtras(), EXTRA_EXPORT_MODE, ExportMode.class.getClassLoader()) == ExportMode.DeleteAccount;
        if (this.isDeleteAccount) {
            setupForDeleteAccount();
        } else {
            this.reportingPeriod = DEFAULT_REPORTING_PERIOD;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.isFileExportPreviewEnabled = ConfigUtils.showFileExportFeaturePreview(getConfigService());
        this.isFileExportSubscribed = ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FileExport) || this.isDeleteAccount;
        if (!((UacfEmailVerificationManager) this.emailVerificationManager.get()).isUserInPhase0()) {
            ViewState viewState = this.currentViewState;
            if (viewState == null) {
                viewState = (getUser().isEmailValid() || this.isFileExportPreviewEnabled) ? ViewState.FileExportInfo : ViewState.VerifyEmail;
            }
            setViewState(viewState);
            return;
        }
        ViewState viewState2 = this.currentViewState;
        if (viewState2 == null) {
            viewState2 = ViewState.FileExportInfo;
        }
        setViewState(viewState2);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.reportingPeriod = ReportingPeriod.values()[BundleUtils.getInt(bundle, EXTRA_SELECTED_REPORTING_PERIOD, DEFAULT_REPORTING_PERIOD.ordinal())];
        this.currentViewState = ViewState.values()[BundleUtils.getInt(bundle, EXTRA_CURRENT_VIEW_STATE)];
        if (BundleUtils.containsKey(bundle, EXTRA_FROM_DATE)) {
            this.fromDate = new Date(BundleUtils.getLong(bundle, EXTRA_FROM_DATE));
        }
        if (BundleUtils.containsKey(bundle, EXTRA_TO_DATE)) {
            this.toDate = new Date(BundleUtils.getLong(bundle, EXTRA_TO_DATE));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_SELECTED_REPORTING_PERIOD, this.reportingPeriod.ordinal());
        bundle.putInt(EXTRA_CURRENT_VIEW_STATE, this.currentViewState.ordinal());
        Date date = this.fromDate;
        if (date != null) {
            bundle.putLong(EXTRA_FROM_DATE, date.getTime());
        }
        Date date2 = this.toDate;
        if (date2 != null) {
            bundle.putLong(EXTRA_TO_DATE, date2.getTime());
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(FileExportReportingPeriodSelectionDialogFragment.TAG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((FileExportReportingPeriodSelectionDialogFragment) dialogFragment).setOnPeriodSelectedListener(this);
        return true;
    }

    public void onPeriodSelected(ReportingPeriod reportingPeriod2, Date date, Date date2) {
        this.reportingPeriod = reportingPeriod2;
        this.fromDate = date;
        this.toDate = date2;
        setReportingPeriodText();
    }

    @Subscribe
    public void onFileExportCompletedEvent(CompletedEvent completedEvent) {
        if (completedEvent.getFailure() == null) {
            setViewState(ViewState.FileExportComplete);
            return;
        }
        setViewState(ViewState.FileExportInfo);
        showErrorText(R.string.export_failed);
    }

    @Subscribe
    public void onSyncFinishedEvent(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
        hideProgressDialog();
        if (!uacfScheduleFinishedInfo.isSuccessful()) {
            showErrorDialog(R.string.unknown_error);
        } else if (!uacfScheduleFinishedInfo.isSuccessful() || getUser().isEmailValid() || ((UacfEmailVerificationManager) this.emailVerificationManager.get()).isUserInPhase0()) {
            setViewState(ViewState.FileExportInfo);
        } else {
            showErrorDialog(R.string.email_not_verified_error);
        }
    }

    private void setViewState(ViewState viewState) {
        this.currentViewState = viewState;
        boolean z = viewState != ViewState.VerifyEmail;
        boolean z2 = viewState == ViewState.FileExportInfo;
        boolean z3 = viewState == ViewState.FileExportComplete;
        boolean z4 = viewState == ViewState.VerifyEmail;
        boolean z5 = (viewState == ViewState.FileExportComplete || viewState == ViewState.Loading) ? false : true;
        boolean z6 = viewState == ViewState.VerifyEmail;
        boolean z7 = viewState == ViewState.Loading;
        boolean z8 = z3 && this.isDeleteAccount;
        ViewUtils.setVisible(z, this.reportingPeriodContainer, this.divider);
        ViewUtils.setVisible(z2, this.fileExportInfoContainer);
        ViewUtils.setVisible(z3, this.fileExportCompleteContainer);
        ViewUtils.setVisible(z4, this.verifyEmailContainer);
        ViewUtils.setVisible(z5, this.exportButton);
        ViewUtils.setVisible(z6, this.changeEmailButton);
        ViewUtils.setGone(this.messageTextView);
        ViewUtils.setVisible(z7, this.fileExportLoadingContainer);
        ViewUtils.setVisible(this.isFileExportPreviewEnabled && !this.isFileExportSubscribed, this.fileExportPremiumCta);
        ViewUtils.setVisible(z5 && this.isFileExportPreviewEnabled && !this.isFileExportSubscribed, this.exportImageView);
        ViewUtils.setVisible(z8, this.exportConfirmationText);
        setReportingPeriodText();
        setPremiumCtaClickListener();
        switch (viewState) {
            case FileExportInfo:
                setupViewForFileExportInfo();
                break;
            case FileExportComplete:
                setupViewForFileExportComplete();
                break;
            case VerifyEmail:
                setupViewForVerifyEmail();
                break;
            case Loading:
                setupViewForLoading();
                break;
        }
        if (this.isDeleteAccount) {
            this.reportingPeriodContainer.setOnClickListener(null);
        }
    }

    private void addEmailToTextView(TextView textView, int i) {
        String email = getEmail();
        String string = getString(i, new Object[]{email});
        int indexOf = string.indexOf(email);
        SpannableString spannableString = new SpannableString(string);
        if (indexOf != -1) {
            spannableString.setSpan(new StyleSpan(1), indexOf, email.length() + indexOf, 33);
        }
        textView.setText(spannableString);
    }

    private void setReportingPeriodText() {
        this.reportingPeriodTextView.setText(this.reportingPeriod.getStringResId());
    }

    private User getUser() {
        return getSession().getUser();
    }

    private String getEmail() {
        return getUser().getEmail();
    }

    private void setupViewForFileExportInfo() {
        if (this.isDeleteAccount) {
            ((FileExportAnalyticsHelper) this.fileExportAnalyticsHelper.get()).reportExportDataSee(DeleteAccountActivity.DELETE_ACCOUNT_SEE);
        }
        addEmailToTextView(this.dataSentInfoTextView, this.isDeleteAccount ? R.string.email_will_be_sent_to : R.string.where_data_sent_info);
        this.exportTextView.setText(this.isFileExportPreviewEnabled ? R.string.export_my_information : R.string.export);
        setTextForChangeEmailInfo();
        this.exportButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FileExport.lambda$setupViewForFileExportInfo$0(FileExport.this, view);
            }
        });
        this.reportingPeriodContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FileExport.lambda$setupViewForFileExportInfo$1(FileExport.this, view);
            }
        });
        ViewUtils.setVisible(false, this.emailVerified);
    }

    public static /* synthetic */ void lambda$setupViewForFileExportInfo$0(FileExport fileExport, View view) {
        if (fileExport.isFileExportSubscribed) {
            UacfEmailVerificationManager uacfEmailVerificationManager = (UacfEmailVerificationManager) fileExport.emailVerificationManager.get();
            if (uacfEmailVerificationManager.shouldShowInterstitial()) {
                uacfEmailVerificationManager.showInterstitial(fileExport.getActivity(), ExportType.EXPORT_DIARY);
                return;
            }
            fileExport.initFromAndToDatesIfNeeded();
            fileExport.getAnalyticsService().reportEvent(EVENT_FILE_EXPORTED, MapUtil.createMap(KEY_REPORT_LENGTH, Long.toString(DateTimeUtils.getNumberOfDaysBetween(fileExport.fromDate, fileExport.toDate))));
            ((FileExportAnalyticsHelper) fileExport.fileExportAnalyticsHelper.get()).reportExportDataRequestmade();
            new FileExportTask(fileExport.fileExportService, fileExport.fromDate, fileExport.toDate).run(fileExport.getRunner());
            fileExport.setViewState(ViewState.Loading);
            return;
        }
        fileExport.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) fileExport, PremiumFeature.FileExport)).startActivity();
    }

    public static /* synthetic */ void lambda$setupViewForFileExportInfo$1(FileExport fileExport, View view) {
        FileExportReportingPeriodSelectionDialogFragment newInstance = FileExportReportingPeriodSelectionDialogFragment.newInstance(fileExport.reportingPeriod, fileExport.fromDate, fileExport.toDate);
        fileExport.showDialogFragment(newInstance, FileExportReportingPeriodSelectionDialogFragment.TAG);
        newInstance.setOnPeriodSelectedListener(fileExport);
    }

    private void initFromAndToDatesIfNeeded() {
        if (this.reportingPeriod != ReportingPeriod.CustomDates) {
            this.toDate = new Date();
            if (this.reportingPeriod == ReportingPeriod.AllTime) {
                try {
                    this.fromDate = Format.newIso8601DateTimeFormat().parse(getUser().getAccount().getCreatedAt());
                } catch (ParseException unused) {
                    this.fromDate = new Date();
                }
                return;
            }
            int numDays = this.reportingPeriod.getNumDays();
            Calendar instance = Calendar.getInstance();
            instance.add(6, -numDays);
            this.fromDate = instance.getTime();
        }
    }

    private void setTextForChangeEmailInfo() {
        if (this.isDeleteAccount) {
            SpanUtils.setSpannableTextOn(this.changeEmailInfoTextView, R.string.customer_support, R.string.export_change_email_text, this.navigateToCustomerSupportClickListener);
        } else {
            SpanUtils.setSpannableTextOn(this.changeEmailInfoTextView, R.string.email_settings, R.string.how_change_email_info, this.navigateToEmailSettingsClickListener);
        }
    }

    private void setupViewForFileExportComplete() {
        this.reportingPeriodContainer.setOnClickListener(null);
        addEmailToTextView(this.checkEmailTextView, R.string.check_email);
    }

    private void setupViewForVerifyEmail() {
        addEmailToTextView(this.verifyEmailInfoTextView, R.string.verify_email_info);
        this.exportTextView.setText(R.string.resend_verification_email);
        this.exportButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.fileexport.ui.activity.FileExport
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.fileexport.ui.activity.FileExport) = (r1v0 'this' com.myfitnesspal.feature.fileexport.ui.activity.-$$Lambda$FileExport$YKHb_Hvk-569fKKIC9xEtDvZzVs A[THIS]) com.myfitnesspal.feature.fileexport.ui.activity.-$$Lambda$FileExport$YKHb_Hvk-569fKKIC9xEtDvZzVs.f$0 com.myfitnesspal.feature.fileexport.ui.activity.FileExport), (r2v0 'view' android.view.View) com.myfitnesspal.feature.fileexport.ui.activity.FileExport.lambda$setupViewForVerifyEmail$4(com.myfitnesspal.feature.fileexport.ui.activity.FileExport, android.view.View):void type: STATIC in method: com.myfitnesspal.feature.fileexport.ui.activity.-$$Lambda$FileExport$YKHb_Hvk-569fKKIC9xEtDvZzVs.onClick(android.view.View):void, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 55 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.fileexport.ui.activity.FileExport r0 = com.myfitnesspal.feature.fileexport.ui.activity.FileExport.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.fileexport.ui.activity.FileExport), (r2 I:android.view.View) com.myfitnesspal.feature.fileexport.ui.activity.FileExport.lambda$setupViewForVerifyEmail$4(com.myfitnesspal.feature.fileexport.ui.activity.FileExport, android.view.View):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.fileexport.ui.activity.$$Lambda$FileExport$YKHb_Hvk569fKKIC9xEtDvZzVs.onClick(android.view.View):void");
            }
        });
        ViewUtils.setVisible(this.emailVerified);
        this.emailVerified.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FileExport.lambda$setupViewForVerifyEmail$5(FileExport.this, view);
            }
        });
        if (this.isDeleteAccount) {
            ((FileExportAnalyticsHelper) this.fileExportAnalyticsHelper.get()).reportVerifyEmailSee(DeleteAccountActivity.DELETE_ACCOUNT_SEE);
            this.changeEmailButton.setText(R.string.settings_contact_support);
            this.changeEmailButton.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    FileExport.lambda$setupViewForVerifyEmail$6(FileExport.this, view);
                }
            });
            return;
        }
        this.changeEmailButton.setOnClickListener(this.navigateToEmailSettingsClickListener);
    }

    public static /* synthetic */ void lambda$setupViewForVerifyEmail$5(FileExport fileExport, View view) {
        ((FileExportAnalyticsHelper) fileExport.fileExportAnalyticsHelper.get()).reportVerifyEmailRecheck();
        fileExport.showProgressDialog(R.string.please_wait);
        fileExport.scheduleSync();
    }

    public static /* synthetic */ void lambda$setupViewForVerifyEmail$6(FileExport fileExport, View view) {
        ((FileExportAnalyticsHelper) fileExport.fileExportAnalyticsHelper.get()).reportExportDataCustomerSupport();
        fileExport.getNavigationHelper().withIntent(Faq.newStartIntent(fileExport)).startActivity();
    }

    private void setupViewForLoading() {
        this.reportingPeriodContainer.setOnClickListener(null);
    }

    /* access modifiers changed from: private */
    public void showErrorText(int i) {
        ViewUtils.setVisible(this.messageTextView);
        this.messageTextView.setTextColor(getResources().getColor(R.color.dark_orange));
        this.messageTextView.setText(i);
    }

    /* access modifiers changed from: private */
    public void showMessageText(int i) {
        ViewUtils.setVisible(this.messageTextView);
        this.messageTextView.setTextColor(getResources().getColor(R.color.black_text));
        addEmailToTextView(this.messageTextView, i);
    }

    private void setPremiumCtaClickListener() {
        this.fileExportPremiumCta.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FileExport.lambda$setPremiumCtaClickListener$8(FileExport.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$setPremiumCtaClickListener$8(FileExport fileExport, View view) {
        if (fileExport.isFileExportPreviewEnabled && !fileExport.isFileExportSubscribed) {
            fileExport.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) fileExport, PremiumFeature.FileExport)).startActivity();
        }
    }

    public static /* synthetic */ void lambda$new$9(FileExport fileExport, View view) {
        ((FileExportAnalyticsHelper) fileExport.fileExportAnalyticsHelper.get()).reportExportDataCustomerSupport();
        fileExport.getNavigationHelper().withIntent(Faq.newStartIntent(fileExport)).startActivity();
    }

    private void setupForDeleteAccount() {
        setTitle(R.string.export_information);
        this.reportingPeriod = ReportingPeriod.AllTime;
        this.reportingPeriodTextView.setTextColor(getResources().getColor(R.color.black));
        ViewUtils.setVisible(false, this.howChangeEmailTitle);
        SpanUtils.setSpannableTextOn(this.exportConfirmationText, R.string.customer_support, R.string.delete_export_confirmation, this.navigateToCustomerSupportClickListener);
    }

    private void showProgressDialog(@StringRes int i) {
        if (((ProgressDialogFragment) getSupportFragmentManager().findFragmentByTag("progress_dialog")) == null) {
            showDialogFragment(ProgressDialogFragment.newInstance(i), "progress_dialog");
        }
    }

    private void hideProgressDialog() {
        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) getSupportFragmentManager().findFragmentByTag("progress_dialog");
        if (progressDialogFragment != null) {
            progressDialogFragment.dismiss();
        }
    }

    private void showErrorDialog(@StringRes int i) {
        showDialogFragment((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setMessage(i)).setPositiveText(R.string.ok, null), EMAIL_NOT_VERIFIED_DIALOG_TAG);
    }
}
