package com.myfitnesspal.feature.barcode.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.ogg.OggExtractor;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.barcode.service.BarcodeService;
import com.myfitnesspal.feature.barcode.service.BarcodeService.ScanResult;
import com.myfitnesspal.feature.barcode.ui.fragment.BarcodeScanDialogFragment;
import com.myfitnesspal.feature.barcode.ui.fragment.BarcodeScanDialogFragment.OnBarcodeScanDialogListener;
import com.myfitnesspal.feature.barcode.ui.view.LiveView;
import com.myfitnesspal.feature.barcode.ui.view.OnBarcodeScannedListener;
import com.myfitnesspal.feature.barcode.ui.view.Overlay;
import com.myfitnesspal.feature.barcode.util.BarcodeUtil;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.AudioUtils;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.visionsmarts.VSBarcodeReader;
import dagger.Lazy;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class BarcodeScannerActivity extends MfpActivity implements OnBarcodeScannedListener {
    private static final float BEEP_VOLUME = 0.5f;
    private static final int EAN13_LENGTH = 13;
    private static final String EXTRA_ANALYTICS_REPORTED = "BarcodeScanner.analytics_reported";
    public static final String EXTRA_NAVIGATION_ACTIVITY_TO_START = "extra_navigation_activity_to_start";
    private static final int SCAN_TIMEOUT_MILLIS = 60000;
    private static final int UPCA_LENGTH = 12;
    private static final int UPCE_LENGTH = 8;
    private boolean addToMealOnSuccess;
    @Inject
    AnalyticsService analyticsService;
    private String barcode;
    @Inject
    Lazy<BarcodeService> barcodeService;
    private SimpleExoPlayer beepMediaPlayer;
    private Date date;
    private OnBarcodeScanDialogListener dialogListener = new OnBarcodeScanDialogListener() {
        public void onDismiss(BarcodeScanDialogFragment barcodeScanDialogFragment) {
        }

        public void onSearchFinished(BarcodeScanDialogFragment barcodeScanDialogFragment) {
        }

        public void onSearchStarted(BarcodeScanDialogFragment barcodeScanDialogFragment) {
        }

        public void onShow(BarcodeScanDialogFragment barcodeScanDialogFragment) {
        }

        public void onErrorAcknowledged(BarcodeScanDialogFragment barcodeScanDialogFragment, int i) {
            if (BarcodeScannerActivity.this.type != Type.Entered) {
                BarcodeScannerActivity.this.finishWithFailure(i);
                return;
            }
            BarcodeScannerActivity.this.manualEntryEdit.requestFocus();
            BarcodeScannerActivity.this.manualEntryEdit.setSelection(BarcodeScannerActivity.this.manualEntryEdit.getText().length());
            BarcodeScannerActivity.this.getImmHelper().showSoftInput(BarcodeScannerActivity.this.manualEntryEdit);
        }
    };
    private BarcodeScanDialogFragment errorDialog;
    private Handler handler = new Handler();
    private Runnable mTimeoutRunnable = new Runnable() {
        public final void run() {
            BarcodeScannerActivity.lambda$new$3(BarcodeScannerActivity.this);
        }
    };
    @Nullable
    @BindView(2131362993)
    EditText manualEntryEdit;
    @Inject
    Lazy<MfpFoodMapper> mfpFoodMapper;
    private String referrer;
    @BindView(2131363538)
    View rootView;
    @BindView(2131363539)
    LiveView scannerLiveView;
    @BindView(2131363540)
    Overlay scannerOverlay;
    private boolean searchOnNoMatch;
    /* access modifiers changed from: private */
    public Type type = Type.Scanned;

    private static class LookupBarcodeTask extends EventedTaskBase<ScanResult, ApiException> {
        private String barcode;
        private String fallback;
        private Lazy<BarcodeService> service;

        static class Event extends TaskEventBase<ScanResult, ApiException> {
            private String analyticsEvent;
            private String barcode;

            public Event(String str, String str2) {
                this.analyticsEvent = str;
                this.barcode = str2;
            }

            /* access modifiers changed from: 0000 */
            public String getAnalyticsEvent() {
                return this.analyticsEvent;
            }

            public String getBarcode() {
                return this.barcode;
            }
        }

        LookupBarcodeTask(Lazy<BarcodeService> lazy, String str, String str2, String str3) {
            super((TaskEventBase) new Event(str, str2));
            this.barcode = str2;
            this.fallback = str3;
            this.service = lazy;
        }

        /* access modifiers changed from: protected */
        public ScanResult exec(Context context) throws ApiException {
            return ((BarcodeService) this.service.get()).searchBarcodeWithFallback(this.barcode, this.fallback);
        }
    }

    private enum Type {
        Scanned,
        Entered
    }

    static /* synthetic */ DataSource lambda$prepareBeepMediaPlayer$2(RawResourceDataSource rawResourceDataSource) {
        return rawResourceDataSource;
    }

    public String getAnalyticsScreenTag() {
        return Screens.BARCODE_SCANNER;
    }

    public boolean showToolbar() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return newStartIntent(context, null, new Date());
    }

    public static Intent newStartIntent(Context context, Date date2) {
        return newStartIntent(context, null, date2);
    }

    public static Intent newStartIntent(Context context, String str, Date date2) {
        Intent intent = new Intent(context, BarcodeScannerActivity.class);
        String str2 = "referrer";
        if (Strings.isEmpty(str)) {
            str = "unknown";
        }
        return intent.putExtra(str2, str).putExtra("date", date2);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        VSBarcodeReader.VSinit();
        setResult(0);
        setContentView((int) R.layout.barcode_reader);
        ViewUtils.setVisible(true, this.manualEntryEdit);
        Bundle extras = getIntent().getExtras();
        this.referrer = BundleUtils.getString(extras, "referrer", "unknown");
        this.addToMealOnSuccess = BundleUtils.getBoolean(extras, Extras.ADD_TO_MEAL_ON_SUCCESS);
        this.searchOnNoMatch = BundleUtils.getBoolean(extras, Extras.SEARCH_ON_NO_MATCH);
        this.date = (Date) BundleUtils.getSerializable(extras, "date", getSession().getUser().getActiveDate(), Date.class.getClassLoader());
        prepareBeepMediaPlayer();
        checkPermission();
        this.rootView.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                BarcodeScannerActivity.lambda$onCreate$0(BarcodeScannerActivity.this, view);
            }
        });
        EditText editText = this.manualEntryEdit;
        if (editText != null) {
            editText.setOnEditorActionListener(new OnEditorActionListener() {
                public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    return BarcodeScannerActivity.lambda$onCreate$1(BarcodeScannerActivity.this, textView, i, keyEvent);
                }
            });
        }
        if (!BundleUtils.getBoolean(bundle, EXTRA_ANALYTICS_REPORTED)) {
            this.analyticsService.reportEvent(Events.BARCODE_SCAN_START, new Builder().put("referrer", this.referrer).build());
            AnalyticsService analyticsService2 = this.analyticsService;
            StringBuilder sb = new StringBuilder();
            sb.append(Events.AUTO_FOCUS);
            sb.append(getPackageManager().hasSystemFeature("android.hardware.camera.autofocus"));
            analyticsService2.reportEvent(sb.toString());
        }
    }

    public static /* synthetic */ void lambda$onCreate$0(BarcodeScannerActivity barcodeScannerActivity, View view) {
        EditText editText = barcodeScannerActivity.manualEntryEdit;
        if (editText != null) {
            editText.clearFocus();
        }
        barcodeScannerActivity.rootView.requestFocus();
        barcodeScannerActivity.getImmHelper().hideSoftInput();
    }

    public static /* synthetic */ boolean lambda$onCreate$1(BarcodeScannerActivity barcodeScannerActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        barcodeScannerActivity.validateAndProcessManualEntry();
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_ANALYTICS_REPORTED, true);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        checkRestoreDialogListener();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.handler.removeCallbacks(this.mTimeoutRunnable);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.scannerOverlay.setBarcode("");
    }

    private void prepareBeepMediaPlayer() {
        try {
            Context applicationContext = getApplicationContext();
            Uri buildRawResourceUri = RawResourceDataSource.buildRawResourceUri(R.raw.beep);
            RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(applicationContext);
            rawResourceDataSource.open(new DataSpec(buildRawResourceUri));
            ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(buildRawResourceUri, new Factory() {
                public final DataSource createDataSource() {
                    return BarcodeScannerActivity.lambda$prepareBeepMediaPlayer$2(RawResourceDataSource.this);
                }
            }, OggExtractor.FACTORY, null, null);
            this.beepMediaPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext, (TrackSelector) new DefaultTrackSelector());
            this.beepMediaPlayer.setPlayWhenReady(false);
            this.beepMediaPlayer.prepare(extractorMediaSource);
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void lambda$new$3(BarcodeScannerActivity barcodeScannerActivity) {
        barcodeScannerActivity.setResult(0);
        barcodeScannerActivity.finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 205) {
            checkPermission();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.scannerLiveView.setOnBarcodeScannedListener(null);
        SimpleExoPlayer simpleExoPlayer = this.beepMediaPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
        }
    }

    public void onBarcodeScanCompleted(String str, int i) {
        this.handler.removeCallbacks(this.mTimeoutRunnable);
        if (!AudioUtils.deviceInSilentOrVibrateMode(this)) {
            SimpleExoPlayer simpleExoPlayer = this.beepMediaPlayer;
            if (simpleExoPlayer != null) {
                simpleExoPlayer.seekTo(0);
                this.beepMediaPlayer.setVolume(0.5f);
                this.beepMediaPlayer.setPlayWhenReady(true);
            }
        }
        if (i == 4) {
            str = BarcodeUtil.UPCEtoEAN13(str);
        }
        getAnalyticsService().reportEvent(Events.BARCODE_SCAN_READ);
        this.type = Type.Scanned;
        this.barcode = str;
        search(str, Events.BARCODE_SCAN_AUTOMATIC_MATCH);
    }

    public void onBarcodeScanFailed(int i) {
        int i2;
        this.handler.removeCallbacks(this.mTimeoutRunnable);
        switch (i) {
            case 0:
            case 1:
                i2 = Dialogs.ACQUIRE_CAMERA_FAILED;
                break;
            default:
                i2 = Dialogs.UNKNOWN_ERROR_DIALOG;
                break;
        }
        showDialogFragment(i2);
    }

    @Subscribe
    public void onBarcodeLookupFinished(Event event) {
        if (event.successful()) {
            ScanResult scanResult = (ScanResult) event.getResult();
            try {
                this.barcode = ((ScanResult) event.getResult()).barcode();
                finishWithSuccess(((MfpFoodMapper) this.mfpFoodMapper.get()).mapFromList(scanResult.foods()));
                this.analyticsService.reportEvent(event.getAnalyticsEvent());
            } catch (IOException unused) {
                showDialogFragment(Dialogs.UNKNOWN_ERROR_DIALOG);
            }
        } else {
            this.barcode = event.getBarcode();
            handleApiError(((ApiException) event.getFailure()).getStatusCode());
        }
    }

    private void validateAndProcessManualEntry() {
        getImmHelper().hideSoftInput((Activity) this);
        this.rootView.requestFocus();
        this.type = Type.Entered;
        String obj = this.manualEntryEdit.getText().toString();
        int length = obj.length();
        if (length == 8) {
            search(BarcodeUtil.UPCEtoEAN13(obj), obj, Events.BARCODE_SCAN_MANUAL_MATCH);
        } else if (length == 12) {
            search(BarcodeUtil.UPCAToEAN13(obj), Events.BARCODE_SCAN_MANUAL_MATCH);
        } else if (length == 13) {
            search(obj, Events.BARCODE_SCAN_MANUAL_MATCH);
        } else {
            showDialogFragment(Dialogs.MALFORMED_BARCODE_DIALOG);
            return;
        }
        this.handler.removeCallbacks(this.mTimeoutRunnable);
        this.barcode = obj;
    }

    private void search(String str, String str2) {
        search(str, null, str2);
    }

    private void search(String str, String str2, String str3) {
        if (Strings.isEmpty(str)) {
            showDialogFragment(Dialogs.UNKNOWN_ERROR_DIALOG);
        } else if (ConnectivityUtil.isOffline().booleanValue()) {
            showDialogFragment(Dialogs.DEVICE_OFFLINE_DIALOG);
        } else {
            showDialogFragment(6007);
            new LookupBarcodeTask(this.barcodeService, str3, str, str2).run(getRunner());
        }
    }

    private void showDialogFragment(int i) {
        showDialogFragment(i, 0);
    }

    private void showDialogFragment(int i, int i2) {
        if (hasResumed()) {
            BarcodeScanDialogFragment barcodeScanDialogFragment = this.errorDialog;
            if (barcodeScanDialogFragment != null) {
                barcodeScanDialogFragment.dismiss();
            }
            this.errorDialog = BarcodeScanDialogFragment.newInstance(i, i2);
            this.errorDialog.setOnBarcodeScanDialogListener(this.dialogListener);
            this.errorDialog.show(getSupportFragmentManager(), Fragments.BARCODE_SCAN_DIALOG);
        }
    }

    private void handleApiError(int i) {
        int i2;
        if (i == 257 || this.type == Type.Entered) {
            finishWithFailure(257);
            getAnalyticsService().reportEvent(Events.BARCODE_SCAN_NO_MATCH);
            return;
        }
        switch (i) {
            case 258:
                i2 = Dialogs.NONEXISTENT_FOOD_ID_DIALOG;
                break;
            case 259:
                i2 = Dialogs.MALFORMED_BARCODE_DIALOG;
                break;
            case 260:
                i2 = Dialogs.INVALID_BARCODE_CHECKSUM_DIALOG;
                break;
            default:
                i2 = Dialogs.UNKNOWN_ERROR_DIALOG;
                break;
        }
        showDialogFragment(i2, i);
    }

    private void finishWithSuccess(List<MfpFood> list) {
        Parcelable[] parcelableArr = (Parcelable[]) list.toArray(new Parcelable[0]);
        Intent intent = new Intent();
        intent.putExtra(Extras.FOOD_LIST, parcelableArr);
        intent.putExtra("barcode", this.barcode);
        setResult(-1, intent);
        if (this.addToMealOnSuccess) {
            BarcodeUtil.handleScanResult(this, getAnalyticsService(), FoodEditorType.DiaryFood, new Intent(this, Diary.class), -1, getSession(), intent, this.referrer, null, null, this.date);
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void finishWithFailure(int i) {
        int i2;
        Intent intent = (Intent) BundleUtils.getParcelable(EXTRA_NAVIGATION_ACTIVITY_TO_START, null, Intent.class.getClassLoader(), getIntent().getExtras());
        Intent intent2 = new Intent();
        intent2.putExtra("errorCode", i);
        intent2.putExtra("barcode", this.barcode);
        if (i == 257) {
            if (this.searchOnNoMatch) {
                BarcodeUtil.handleScanResult(this, getAnalyticsService(), FoodEditorType.DiaryFood, intent, 0, getSession(), intent2, this.referrer, null, null, this.date);
            } else {
                i2 = -1;
                intent2.putExtra(Extras.FOOD_LIST, new Parcelable[0]);
                setResult(i2, intent2);
                finish();
            }
        }
        i2 = 0;
        setResult(i2, intent2);
        finish();
    }

    private void checkRestoreDialogListener() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(Fragments.BARCODE_SCAN_DIALOG);
        if (findFragmentByTag != null) {
            ((BarcodeScanDialogFragment) findFragmentByTag).setOnBarcodeScanDialogListener(this.dialogListener);
        }
    }

    private void checkPermission() {
        ((PermissionsMixin) mixin(PermissionsMixin.class)).checkPermission("android.permission.CAMERA", new Function0() {
            public final Object invoke() {
                return BarcodeScannerActivity.lambda$checkPermission$4(BarcodeScannerActivity.this);
            }
        }, new Function0() {
            public final Object invoke() {
                return BarcodeScannerActivity.this.finish();
            }
        }, new Function0() {
            public final Object invoke() {
                return BarcodeScannerActivity.this.showPermissionDeniedDialog();
            }
        }, new Function0() {
            public final Object invoke() {
                return BarcodeScannerActivity.this.finish();
            }
        });
    }

    public static /* synthetic */ Unit lambda$checkPermission$4(BarcodeScannerActivity barcodeScannerActivity) {
        barcodeScannerActivity.scannerLiveView.setVisible();
        barcodeScannerActivity.scannerLiveView.isCameraEnabled(true);
        barcodeScannerActivity.scannerLiveView.setOnBarcodeScannedListener(barcodeScannerActivity);
        barcodeScannerActivity.handler.postDelayed(barcodeScannerActivity.mTimeoutRunnable, DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
        return null;
    }

    /* access modifiers changed from: private */
    public void showPermissionDeniedDialog() {
        new Handler().post(new Runnable() {
            public final void run() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity) = (r1v0 'this' com.myfitnesspal.feature.barcode.ui.activity.-$$Lambda$BarcodeScannerActivity$vRjSPj8GIxyYzRBC2k5UxxAb5vQ A[THIS]) com.myfitnesspal.feature.barcode.ui.activity.-$$Lambda$BarcodeScannerActivity$vRjSPj8GIxyYzRBC2k5UxxAb5vQ.f$0 com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity) com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity.lambda$showPermissionDeniedDialog$8(com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity):void type: STATIC in method: com.myfitnesspal.feature.barcode.ui.activity.-$$Lambda$BarcodeScannerActivity$vRjSPj8GIxyYzRBC2k5UxxAb5vQ.run():void, dex: classes3.dex
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
                    com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity r0 = com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity) com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity.lambda$showPermissionDeniedDialog$8(com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.barcode.ui.activity.$$Lambda$BarcodeScannerActivity$vRjSPj8GIxyYzRBC2k5UxxAb5vQ.run():void");
            }
        });
    }
}
