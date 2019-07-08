package com.myfitnesspal.feature.debug.ui.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.progress.constants.GalleryDataMode;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.Message;
import com.myfitnesspal.feature.progress.ui.activity.AddWeightActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressCongratsActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosGalleryActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosGalleryActivity.ToolbarCta;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosInterstitialActivity;
import com.myfitnesspal.framework.taskrunner.TaskBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.ImageAssociationsTable;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v1.Measurement;
import com.myfitnesspal.shared.service.imagesync.ImageSyncMode;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import com.uacf.sync.engine.UacfSchedulerEngine;
import com.uacf.sync.engine.UacfSchedulerEngine.Callbacks;
import dagger.Lazy;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class ProgressPhotosDebugActivity extends MfpActivity {
    private static final String IMAGE_FILENAME = "/sdcard/mario.jpg";
    private static final String RESOURCE_TYPE = "measurement";
    @BindView(2131362198)
    View congratsActivity;
    @BindView(2131362230)
    View createLocal;
    @BindView(2131362287)
    View delete;
    @BindView(2131362704)
    View getMessages;
    @Inject
    Lazy<ImageAssociationService> imageAssociationService;
    @Inject
    Lazy<UacfSchedulerEngine<ImageSyncMode>> imageSyncService;
    @BindView(2131362837)
    View introActivity;
    @Inject
    Lazy<LocalSettingsService> localSettings;
    @BindView(2131362998)
    View markMessages;
    @Inject
    Lazy<MeasurementsService> measurementsService;
    /* access modifiers changed from: private */
    public List<Message> messages;
    @Inject
    Lazy<ProgressCongratsService> progressCongrats;
    @BindView(2131363474)
    View resetMessages;
    @BindView(2131363656)
    View showGalleryImport;
    @BindView(2131363657)
    View showGalleryView;
    @BindView(2131363660)
    View showProgress;
    @BindView(2131363661)
    View showWeightPicker;
    @BindView(2131362789)
    View sync;

    private class DisassociateAllTask extends TaskBase<Boolean, ApiException> {
        private String userId;

        public DisassociateAllTask(String str) {
            this.userId = str;
        }

        /* access modifiers changed from: protected */
        public Boolean exec(Context context) throws ApiException {
            SQLiteDatabaseWrapper db = DbConnectionManager.getDb(context);
            ImagesTable imagesTable = new ImagesTable(db);
            ContentValues contentValues = new ContentValues();
            contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(1));
            imagesTable.updateData(contentValues, "user_id=?", this.userId);
            ImageAssociationsTable imageAssociationsTable = new ImageAssociationsTable(db);
            contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(3));
            imageAssociationsTable.updateData(contentValues, "user_id=?", this.userId);
            ProgressPhotosDebugActivity progressPhotosDebugActivity = ProgressPhotosDebugActivity.this;
            StringBuilder sb = new StringBuilder();
            sb.append("marked all images and associations for deletions for userId=");
            sb.append(this.userId);
            progressPhotosDebugActivity.toast(sb.toString());
            return Boolean.valueOf(true);
        }
    }

    private Measurement fetchMostRecentWeightMeasurement() {
        MeasurementsService measurementsService2 = (MeasurementsService) this.measurementsService.get();
        long measurementTypeIdFromMeasurementTypeName = measurementsService2.getMeasurementTypeIdFromMeasurementTypeName(Constants.Measurement.WEIGHT);
        Cursor mostRecentMeasurementBeforeDate = measurementsService2.getMostRecentMeasurementBeforeDate(new Date(), getSession().getUser().getLocalId(), measurementTypeIdFromMeasurementTypeName);
        mostRecentMeasurementBeforeDate.moveToFirst();
        long j = mostRecentMeasurementBeforeDate.getLong(mostRecentMeasurementBeforeDate.getColumnIndex("id"));
        mostRecentMeasurementBeforeDate.close();
        return measurementsService2.getMeasurementByLocalId(j);
    }

    /* access modifiers changed from: private */
    public void toast(String str) {
        postEvent(new AlertEvent(str).asToast());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.progress_photos_debug);
        component().inject(this);
        this.messages = ((ProgressCongratsService) this.progressCongrats.get()).getPendingMessages();
        this.createLocal.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.lambda$onCreate$0(ProgressPhotosDebugActivity.this, view);
            }
        });
        this.sync.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.lambda$onCreate$1(ProgressPhotosDebugActivity.this, view);
            }
        });
        this.showWeightPicker.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.this.getNavigationHelper().withNoAnimations().withIntent(AddWeightActivity.newStartIntent(ProgressPhotosDebugActivity.this, ProgressEntryPoint.ProgressActivity, ImportDevice.None)).startActivity(25);
            }
        });
        this.delete.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.activity.-$$Lambda$ProgressPhotosDebugActivity$kGQS_XJG251ZBpC0roPiRk1Ip60 A[THIS]) com.myfitnesspal.feature.debug.ui.activity.-$$Lambda$ProgressPhotosDebugActivity$kGQS_XJG251ZBpC0roPiRk1Ip60.f$0 com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity), (r2v0 'view' android.view.View) com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity.lambda$onCreate$3(com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity, android.view.View):void type: STATIC in method: com.myfitnesspal.feature.debug.ui.activity.-$$Lambda$ProgressPhotosDebugActivity$kGQS_XJG251ZBpC0roPiRk1Ip60.onClick(android.view.View):void, dex: classes3.dex
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
                    com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity r0 = com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity), (r2 I:android.view.View) com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity.lambda$onCreate$3(com.myfitnesspal.feature.debug.ui.activity.ProgressPhotosDebugActivity, android.view.View):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.activity.$$Lambda$ProgressPhotosDebugActivity$kGQS_XJG251ZBpC0roPiRk1Ip60.onClick(android.view.View):void");
            }
        });
        this.showGalleryView.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.this.navigateToGallery(GalleryViewMode.Split, GalleryDataMode.View);
            }
        });
        this.showGalleryImport.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.this.navigateToGallery(GalleryViewMode.Split, GalleryDataMode.Import);
            }
        });
        this.getMessages.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.this.messages = ((ProgressCongratsService) ProgressPhotosDebugActivity.this.progressCongrats.get()).getPendingMessages();
            }
        });
        this.markMessages.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.lambda$onCreate$7(ProgressPhotosDebugActivity.this, view);
            }
        });
        this.resetMessages.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.lambda$onCreate$8(ProgressPhotosDebugActivity.this, view);
            }
        });
        this.introActivity.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.this.getNavigationHelper().withIntent(ProgressPhotosInterstitialActivity.newStartIntent(ProgressPhotosDebugActivity.this)).startActivity();
            }
        });
        this.congratsActivity.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.this.getNavigationHelper().withIntent(ProgressCongratsActivity.newStartIntent(ProgressPhotosDebugActivity.this)).startActivity();
            }
        });
        this.showProgress.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                ProgressPhotosDebugActivity.this.getNavigationHelper().withIntent(ProgressActivity.newStartIntent(ProgressPhotosDebugActivity.this, Constants.Measurement.WEIGHT)).startActivity();
            }
        });
    }

    public static /* synthetic */ void lambda$onCreate$0(ProgressPhotosDebugActivity progressPhotosDebugActivity, View view) {
        Measurement fetchMostRecentWeightMeasurement = progressPhotosDebugActivity.fetchMostRecentWeightMeasurement();
        progressPhotosDebugActivity.toast(String.format("fn=%s, id=%d, uid=%s, success=%s", new Object[]{IMAGE_FILENAME, Long.valueOf(fetchMostRecentWeightMeasurement.getLocalId()), fetchMostRecentWeightMeasurement.getUid(), Boolean.valueOf(((ImageAssociationService) progressPhotosDebugActivity.imageAssociationService.get()).associate(IMAGE_FILENAME, "measurement", fetchMostRecentWeightMeasurement.getLocalId(), fetchMostRecentWeightMeasurement.getUid()))}));
    }

    public static /* synthetic */ void lambda$onCreate$1(ProgressPhotosDebugActivity progressPhotosDebugActivity, View view) {
        ((UacfSchedulerEngine) progressPhotosDebugActivity.imageSyncService.get()).enqueue(ImageSyncMode.All, new Callbacks<ImageSyncMode>() {
            public void onProgress(UacfScheduleProgressInfo<ImageSyncMode> uacfScheduleProgressInfo) {
            }

            public void onCompleted(UacfScheduleFinishedInfo<ImageSyncMode> uacfScheduleFinishedInfo) {
                ProgressPhotosDebugActivity.this.toast(String.format("sync finished! success=%s", new Object[]{Boolean.valueOf(uacfScheduleFinishedInfo.isSuccessful())}));
            }
        });
        progressPhotosDebugActivity.toast("enqueued a sync");
    }

    public static /* synthetic */ void lambda$onCreate$7(ProgressPhotosDebugActivity progressPhotosDebugActivity, View view) {
        for (int i = 0; i < progressPhotosDebugActivity.messages.size(); i++) {
            ((ProgressCongratsService) progressPhotosDebugActivity.progressCongrats.get()).markMessageConsumed((Message) progressPhotosDebugActivity.messages.get(i));
        }
    }

    public static /* synthetic */ void lambda$onCreate$8(ProgressPhotosDebugActivity progressPhotosDebugActivity, View view) {
        ((LocalSettingsService) progressPhotosDebugActivity.localSettings.get()).setProgressPhotosIntroDisplayed(false);
        ((ProgressCongratsService) progressPhotosDebugActivity.progressCongrats.get()).reset();
    }

    /* access modifiers changed from: private */
    public void navigateToGallery(GalleryViewMode galleryViewMode, GalleryDataMode galleryDataMode) {
        getNavigationHelper().withIntent(ProgressPhotosGalleryActivity.newStartIntent(this, galleryViewMode, galleryDataMode, ToolbarCta.ShareIcon)).startActivity(RequestCodes.PROGRESS_PHOTOS_GALLERY);
    }
}
