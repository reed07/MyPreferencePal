package com.myfitnesspal.feature.settings.ui.activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.UnitDialogDismissedEvent;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel.Mode;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.help.ui.activity.EmailSettings;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.feature.settings.event.DialogTimezoneEvent;
import com.myfitnesspal.feature.settings.ui.dialog.CountryDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.GenderDialogFragment.GenderChangedEvent;
import com.myfitnesspal.feature.settings.ui.dialog.HeightDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.HeightDialogFragment.HeightChangedEvent;
import com.myfitnesspal.feature.settings.ui.dialog.PinCodeDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.TimezoneDialogFragment;
import com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment;
import com.myfitnesspal.feature.settings.util.CountryUpdateListener;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.SharedConstants;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.event.GoalsRecalculatedEvent;
import com.myfitnesspal.shared.event.StartCameraEvent;
import com.myfitnesspal.shared.event.StartMediaChooserEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.model.v1.UserImage;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v2.MfpUserProperties;
import com.myfitnesspal.shared.provider.FileProviderPrivateInternal;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.task.RecalculateNutrientGoalsTask;
import com.myfitnesspal.shared.task.RecalculateNutrientGoalsTask.CompletedEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.ImageChooserDialogFragment;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.AccountUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.Gender;
import com.myfitnesspal.shared.util.ImageCropHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.PincodeHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.IntentUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import kotlin.Unit;

public class EditProfile extends MfpActivity {
    private static final String AUTHORITY_INTERNAL_STORAGE = "com.myfitnesspal.android.fileproviderprivateinternal";
    private static final int DISPLAY_USERNAME = 0;
    private static final int EDIT_BIRTHDATE = 3;
    private static final int EDIT_COUNTRY = 8;
    private static final int EDIT_EMAIL_ADDRESS = 6;
    private static final int EDIT_GENDER = 2;
    private static final int EDIT_GOALS = 11;
    private static final int EDIT_HEIGHT = 1;
    private static final int EDIT_PIN_CODE = 10;
    private static final int EDIT_PROFILE_PHOTO = 5;
    private static final int EDIT_TIMEZONE = 9;
    private static final int EDIT_UNITS = 7;
    private static final String EXTRA_BACK_FROM_CROP_URI = "back_from_crop_uri";
    private static final String EXTRA_COUNTRY_SELECTED = "selected_country";
    private static final String EXTRA_IMAGE_CAPTURE_URI = "image_capture_uri";
    private static final String TEMP_IMAGE_FOLDER = "temp";
    private static final String TEMP_IMAGE_PREFIX = "temp_profile_image_to_crop";
    private static final int VIEW_TYPE_PHOTO = 1;
    private static final int VIEW_TYPE_TEXT = 0;
    private Uri backFromCropImageLocation;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    Lazy<ConsentsAnalyticsHelper> consentsAnalyticsHelper;
    @Inject
    Lazy<ConsentsService> consentsService;
    private CountryUpdateListener countryUpdateListener;
    private ImageChooserDialogFragment currentProfilePhotoDialog;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<DiaryService> diaryService;
    private Uri imageCaptureUri;
    private int importRequestCode;
    private ListView listView;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private MediaType mediaType = MediaType.CAMERA;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private String selectedCountry;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserDistanceService> userDistanceService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserHeightService> userHeightService;
    @Inject
    Lazy<UserImageService> userImageService;
    @Inject
    Lazy<UserPropertiesService> userPropertiesService;
    @Inject
    Lazy<UserWeightService> userWeightService;
    View verifyEmail;
    @Inject
    Lazy<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelper;

    enum MediaType {
        CAMERA,
        MEDIA
    }

    private static class ProfileListAdapter extends ArrayAdapter<Integer> {
        public ProfileListAdapter(EditProfile editProfile, int i, int i2, List<Integer> list) {
            super(editProfile, i, i2, list);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            Integer num = (Integer) getItem(i);
            EditProfile editProfile = (EditProfile) getContext();
            ((TextView) ViewUtils.findById(view2, 16908310)).setText(editProfile.getTitleForItem(num));
            editProfile.setupRootViewForItem(view2, num);
            editProfile.setupFrameForItem((ViewGroup) ViewUtils.findById(view2, 16908312), num);
            return view2;
        }
    }

    static /* synthetic */ void lambda$confirmUsernameChange$8(DialogInterface dialogInterface, int i) {
    }

    public String getAnalyticsScreenTag() {
        return Screens.EDIT_PROFILE_SCREEN;
    }

    public static Intent newStartIntent(Context context, Session session2) {
        return new Intent(context, EditProfile.class).putExtra("username", session2.getUser().getUsername());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.edit_profile);
        setupList();
        this.imageCaptureUri = (Uri) BundleUtils.getParcelable(bundle, EXTRA_IMAGE_CAPTURE_URI, Uri.class.getClassLoader());
        this.backFromCropImageLocation = (Uri) BundleUtils.getParcelable(bundle, EXTRA_BACK_FROM_CROP_URI, Uri.class.getClassLoader());
        this.selectedCountry = BundleUtils.getString(bundle, EXTRA_COUNTRY_SELECTED);
        setupCountryUpdateListener();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        refresh();
        scheduleSync();
        ((UserPropertiesService) this.userPropertiesService.get()).getUpdatedUserInfo(getSession().getUser().getUserId(), new Function1() {
            public final void execute(Object obj) {
                EditProfile.lambda$onResume$0(EditProfile.this, (MfpUserProperties) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onResume$0(EditProfile editProfile, MfpUserProperties mfpUserProperties) throws RuntimeException {
        if (editProfile.verifyEmail != null) {
            ViewUtils.setVisible(!mfpUserProperties.getAccount().getValidEmail().booleanValue(), editProfile.verifyEmail);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(EXTRA_IMAGE_CAPTURE_URI, this.imageCaptureUri);
        bundle.putParcelable(EXTRA_BACK_FROM_CROP_URI, this.backFromCropImageLocation);
        bundle.putString(EXTRA_COUNTRY_SELECTED, this.selectedCountry);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getMessageBus().post(new StartSyncEvent());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        recreateDOBDialog();
    }

    @Subscribe
    public void onSyncFinished(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
        if (uacfScheduleFinishedInfo.getScheduleGroup() == SyncType.Incremental) {
            refresh();
        }
    }

    private void recreateDOBDialog() {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(Fragments.BIRTHDATE);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            showDOBDialog();
        }
    }

    /* access modifiers changed from: protected */
    public String getTitleForItem(Integer num) {
        int i;
        switch (num.intValue()) {
            case 0:
                i = R.string.userNameTxt;
                break;
            case 1:
                i = R.string.HeightTxt;
                break;
            case 2:
                i = R.string.GenderTxt;
                break;
            case 3:
                i = R.string.dateofBirthTxt;
                break;
            case 5:
                i = R.string.editProfilePhoto;
                break;
            case 6:
                i = R.string.email_text;
                break;
            case 7:
                i = R.string.unitTxt;
                break;
            case 8:
                i = R.string.location;
                break;
            case 9:
                i = R.string.timeZoneTxt;
                break;
            case 10:
                i = R.string.zipCode;
                break;
            case 11:
                i = R.string.goals;
                break;
            default:
                i = 0;
                break;
        }
        return i > 0 ? getString(i) : "";
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        ((BaseAdapter) this.listView.getAdapter()).notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public String getValueForItem(Integer num) {
        String str = "";
        User user = getSession().getUser();
        UserProfile profile = user.getProfile();
        switch (num.intValue()) {
            case 0:
                return user.getUsername();
            case 1:
                return ((UserHeightService) this.userHeightService.get()).getDisplayableString();
            case 2:
                return getString(Strings.equalsIgnoreCase(profile.getGenderString(), Gender.Female) ? R.string.femaleTxt : R.string.maleTxt);
            case 3:
                return DateTimeUtils.getMediumLocaleFormattedDate(this, profile.getDateOfBirth());
            case 6:
                return user.getEmail();
            case 7:
                return Strings.join("\n", (T[]) new String[]{String.format("%s, %s,", new Object[]{((UserWeightService) this.userWeightService.get()).getCurrentWeightUnitString(), ((UserHeightService) this.userHeightService.get()).getCurrentHeightUnitString()}), String.format("%s, %s, %s", new Object[]{((UserDistanceService) this.userDistanceService.get()).getCurrentDistanceUnitString(), ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnitString(), LocalizedFluid.getCurrentWaterUnitString(this, getSession())})});
            case 8:
                return getCleanCountryName();
            case 9:
                return profile.getCurrentTimezone().getTimezone_name();
            case 10:
                return profile.getPostalCode();
            case 11:
                return "";
            default:
                return str;
        }
    }

    private String getCleanCountryName() {
        String countryName = getSession().getUser().getProfile().getCountryName();
        return Strings.equalsIgnoreCase(countryName, "Republic of Macedonia") ? getString(R.string.country_MK) : countryName;
    }

    private void setupList() {
        ListAdapter createAdapter = createAdapter();
        this.listView = (ListView) findById(16908298);
        this.listView.setAdapter(createAdapter);
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                EditProfile.this.showDialog(((Integer) adapterView.getItemAtPosition(i)).intValue());
            }
        });
    }

    /* access modifiers changed from: protected */
    public List<Integer> getItems() {
        return new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(5), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(7), Integer.valueOf(6), Integer.valueOf(11)}));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Bitmap bitmap;
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case RequestCodes.OS_SETTINGS /*205*/:
                checkPermissions();
                return;
            case RequestCodes.CONSENTS /*206*/:
                if (i2 == -1) {
                    updateCountry();
                    return;
                }
                return;
            default:
                switch (i) {
                    case 1002:
                        PincodeHelper.current().setEnteredApp(true);
                        PincodeHelper.current().setPincodeSuccess(true);
                        if (i2 != -1 || intent == null) {
                            Toast.makeText(this, getString(R.string.image_not_saved), 0).show();
                        } else {
                            Uri data = intent.getData();
                            try {
                                if (Strings.notEmpty((Object) data)) {
                                    bitmap = Media.getBitmap(getContentResolver(), data);
                                } else {
                                    bitmap = (Bitmap) ExtrasUtils.getParcelable(intent, "data", Bitmap.class.getClassLoader());
                                }
                                useCroppedBitmap(bitmap);
                            } catch (IOException e) {
                                Toast.makeText(this, getString(R.string.image_not_saved), 0).show();
                                Ln.e(e);
                            }
                        }
                        ImageCropHelper.cleanOnFinish(this.imageCaptureUri);
                        try {
                            getContentResolver().delete(this.imageCaptureUri, null, null);
                        } catch (Exception e2) {
                            Ln.e("Profile image deletion failed", e2);
                        }
                        ImageCropHelper.cleanOnFinish(this.backFromCropImageLocation);
                        return;
                    case SharedConstants.RequestCodes.PICK_FROM_CAMERA /*1003*/:
                    case 1004:
                        dismissProfilePhotoDialog();
                        PincodeHelper.current().setEnteredApp(true);
                        PincodeHelper.current().setPincodeSuccess(true);
                        if (i2 != -1) {
                            return;
                        }
                        if (IntentUtil.hasData(intent)) {
                            try {
                                Bitmap bitmap2 = Media.getBitmap(getContentResolver(), intent.getData());
                                File file = new File(getFilesDir(), TEMP_IMAGE_FOLDER);
                                if (file.exists() || file.mkdir()) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(TEMP_IMAGE_PREFIX);
                                    sb.append(Calendar.getInstance().getTime().toString());
                                    sb.append(".jpg");
                                    File file2 = new File(file, sb.toString());
                                    if (file2.createNewFile()) {
                                        FileOutputStream fileOutputStream = new FileOutputStream(file2, false);
                                        bitmap2.compress(CompressFormat.JPEG, 100, fileOutputStream);
                                        fileOutputStream.close();
                                        this.imageCaptureUri = FileProviderPrivateInternal.getUriForFile(this, AUTHORITY_INTERNAL_STORAGE, file2);
                                        doCrop();
                                        return;
                                    }
                                }
                                Ln.e("File creation failed", new Object[0]);
                                return;
                            } catch (IOException e3) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Exception at temp file creation");
                                sb2.append(e3.getMessage());
                                Ln.e(sb2.toString(), new Object[0]);
                                return;
                            }
                        } else {
                            doCrop();
                            return;
                        }
                    default:
                        ((DbConnectionManager) this.dbConnectionManager.get()).usersDbAdapter().saveUser(getSession().getUser().getUserV1());
                        refresh();
                        return;
                }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        clearDisposable();
        super.onDestroy();
    }

    public void clearDisposable() {
        if (!this.compositeDisposable.isDisposed()) {
            this.compositeDisposable.clear();
        }
    }

    private void useCroppedBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            UserImage.createNewProfileImageForCurrentUser((UserImageService) this.userImageService.get(), getSession(), bitmap);
            Toast.makeText(this, getString(R.string.image_saved_sucessfully), 0).show();
            refresh();
        }
    }

    private void doCrop() {
        ImageCropHelper imageCropHelper = new ImageCropHelper(getNavigationHelper());
        this.backFromCropImageLocation = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "tmp_crop_avatar_mfp.jpg"));
        imageCropHelper.cropImage(this, this.imageCaptureUri, this.backFromCropImageLocation, new Function1() {
            public final void execute(Object obj) {
                EditProfile.lambda$doCrop$2(EditProfile.this, (Bitmap) obj);
            }
        }, 256);
    }

    public static /* synthetic */ void lambda$doCrop$2(EditProfile editProfile, Bitmap bitmap) throws RuntimeException {
        if (bitmap != null) {
            editProfile.useCroppedBitmap(bitmap);
        }
        if (editProfile.imageCaptureUri != null) {
            try {
                editProfile.getContentResolver().delete(editProfile.imageCaptureUri, null, null);
            } catch (Exception e) {
                Ln.e("Profile image deletion failed", e);
            } catch (Throwable th) {
                editProfile.imageCaptureUri = null;
                throw th;
            }
            editProfile.imageCaptureUri = null;
        }
        ImageCropHelper.cleanOnFinish(editProfile.backFromCropImageLocation);
        editProfile.dismissProfilePhotoDialog();
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        switch (i) {
            case 0:
                confirmUsernameChange();
                break;
            case 1:
                confirmGoalsRecalculation(new Function0() {
                    public final void execute() {
                        HeightDialogFragment.newInstance().show(FragmentManager.this, Fragments.HEIGHT_DIALOG);
                    }
                });
                break;
            case 2:
                confirmGoalsRecalculation(new Function0() {
                    public final void execute() {
                        
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: android.support.v4.app.FragmentManager
                              0x0000: IGET  (r0v0 android.support.v4.app.FragmentManager) = (r1v0 'this' com.myfitnesspal.feature.settings.ui.activity.-$$Lambda$EditProfile$E5PrgBPHdsQz2h_cbNUt91Dir3I A[THIS]) com.myfitnesspal.feature.settings.ui.activity.-$$Lambda$EditProfile$E5PrgBPHdsQz2h_cbNUt91Dir3I.f$0 android.support.v4.app.FragmentManager) com.myfitnesspal.feature.settings.ui.activity.EditProfile.lambda$onCreateDialog$4(android.support.v4.app.FragmentManager):void type: STATIC in method: com.myfitnesspal.feature.settings.ui.activity.-$$Lambda$EditProfile$E5PrgBPHdsQz2h_cbNUt91Dir3I.execute():void, dex: classes4.dex
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
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:286)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:64)
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
                            	... 43 more
                            Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                            	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                            	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.Class.forName0(Native Method)
                            	at java.base/java.lang.Class.forName(Unknown Source)
                            	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                            	... 60 more
                            */
                        /*
                            this = this;
                            android.support.v4.app.FragmentManager r0 = android.support.v4.app.FragmentManager.this
                            
                            // error: 0x0002: INVOKE  (r0 I:android.support.v4.app.FragmentManager) com.myfitnesspal.feature.settings.ui.activity.EditProfile.lambda$onCreateDialog$4(android.support.v4.app.FragmentManager):void type: STATIC
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.ui.activity.$$Lambda$EditProfile$E5PrgBPHdsQz2h_cbNUt91Dir3I.execute():void");
                    }
                });
                break;
            case 3:
                confirmGoalsRecalculation(new Function0() {
                    public final void execute() {
                        EditProfile.this.showDOBDialog();
                    }
                });
                break;
            case 5:
                this.currentProfilePhotoDialog = ImageChooserDialogFragment.newInstance(R.string.editProfilePhoto);
                this.currentProfilePhotoDialog.show(supportFragmentManager, Fragments.PROFILE_PHOTO);
                break;
            case 6:
                getNavigationHelper().withIntent(EmailSettings.newStartIntent(this)).startActivity();
                break;
            case 7:
                UnitsDialogFragment.newInstance().show(supportFragmentManager, Fragments.UNITS_DIALOG);
                ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportWaterUnitChangeClick(WaterLoggingAnalyticsHelper.PROFILE_UNIT_PREF);
                break;
            case 8:
                ((ConsentsAnalyticsHelper) this.consentsAnalyticsHelper.get()).reportCountryChangeInitiated();
                new MfpAlertDialogBuilder(this).setMessage((int) R.string.change_country_confirmation).setPositiveButton((int) R.string.ok, (OnClickListener) new OnClickListener(supportFragmentManager) {
                    private final /* synthetic */ FragmentManager f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        EditProfile.this.showCountriesDialog(this.f$1);
                    }
                }).show();
                break;
            case 9:
                TimezoneDialogFragment.newInstance().show(supportFragmentManager, Fragments.TIMEZONE_PICKER_DIALOG);
                break;
            case 10:
                PinCodeDialogFragment.newInstance().show(supportFragmentManager, Fragments.PIN_CODE_PICKER_DIALOG);
                break;
            case 11:
                getAnalyticsService().reportEvent(Events.EDIT_PROFILE_SCREEN_GOALS_CLICK);
                getNavigationHelper().withIntent(Goals.newStartIntent(this)).startActivity();
                break;
        }
        return super.onCreateDialog(i);
    }

    /* access modifiers changed from: private */
    public void showDOBDialog() {
        DatePickerFragment.newInstance(getSession().getUser().getProfile().getDateOfBirth()).show(getSupportFragmentManager(), Fragments.BIRTHDATE);
    }

    private void startCameraImport() {
        try {
            this.importRequestCode = SharedConstants.RequestCodes.PICK_FROM_CAMERA;
            this.imageCaptureUri = createImageUri();
            getNavigationHelper().withIntent(SharedIntents.newImageCaptureIntent(this.imageCaptureUri)).startActivity(SharedConstants.RequestCodes.PICK_FROM_CAMERA);
            dismissProfilePhotoDialog();
        } catch (ActivityNotFoundException e) {
            Ln.e(e);
            Toast.makeText(this, getString(R.string.cannot_launch_camera_image_saved), 0).show();
        }
    }

    private void startFileImport() {
        try {
            this.importRequestCode = 1004;
            getNavigationHelper().withIntent(SharedIntents.newImageChooserIntent(getString(R.string.complete_action_using), CompressFormat.JPEG)).startActivity(1004);
            dismissProfilePhotoDialog();
        } catch (Exception unused) {
            Toast.makeText(this, getString(R.string.cannot_view_gallary_image_saved), 0).show();
        }
    }

    @Subscribe
    public void onBirthdayChanged(DialogCalendarEvent dialogCalendarEvent) {
        Calendar calendar = dialogCalendarEvent.getCalendar();
        if (AccountUtils.validateAge(calendar)) {
            User user = getSession().getUser();
            user.getProfile().setDateOfBirth(new Date(calendar.get(1) - 1900, calendar.get(2), calendar.get(5)));
            user.updatePropertyNamed(Basic.DATE_OF_BIRTH);
            recalculateGoals();
            return;
        }
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.underage_change_birthdate));
    }

    @Subscribe
    public void onGenderChanged(GenderChangedEvent genderChangedEvent) {
        recalculateGoals();
    }

    @Subscribe
    public void onHeightChanged(HeightChangedEvent heightChangedEvent) {
        recalculateGoals();
    }

    @Subscribe
    public void onDialogTimezoneEvent(DialogTimezoneEvent dialogTimezoneEvent) {
        User user = getSession().getUser();
        user.getProfile().setCurrentTimezone(dialogTimezoneEvent.getTimezone());
        user.updatePropertyNamed(Basic.TIMEZONE_IDENTIFIER);
        scheduleSync();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            refresh();
        }
    }

    @Subscribe
    public void onGoalsRecalculated(GoalsRecalculatedEvent goalsRecalculatedEvent) {
        refresh();
    }

    private void recalculateGoals() {
        setBusy(true);
        new RecalculateNutrientGoalsTask(this.nutrientGoalsUtil, this.diaryService, this.nutrientGoalService, this.session).run(getRunner());
    }

    @Subscribe
    public void onNutrientGoalsRecalculated(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            RecalculateNutrientGoalsTask.showErrorDialogIfFailed((MfpActivity) this, completedEvent);
        }
    }

    @Subscribe
    public void onStartCameraEvent(StartCameraEvent startCameraEvent) {
        this.mediaType = MediaType.CAMERA;
        checkPermissions();
    }

    @Subscribe
    public void onStartMediaChooserEvent(StartMediaChooserEvent startMediaChooserEvent) {
        this.mediaType = MediaType.MEDIA;
        checkPermissions();
    }

    /* access modifiers changed from: protected */
    public void setupFrameForItem(ViewGroup viewGroup, Integer num) {
        if (num.intValue() == 11) {
            hideFrameForItem(viewGroup, num);
        } else if (num.intValue() == 5) {
            MfpImageView mfpImageView = (MfpImageView) ViewUtils.findById(viewGroup, R.id.image);
            if (mfpImageView == null) {
                View.inflate(this, R.layout.profile_preference_image, viewGroup);
                mfpImageView = (MfpImageView) ViewUtils.findById(viewGroup, R.id.image);
            }
            String imageUrl = getSession().getUser().getImageUrl();
            if (Strings.notEmpty(imageUrl)) {
                mfpImageView.setUrl(imageUrl);
                return;
            }
            Bitmap fetchMostRecentUserImage = UserImage.fetchMostRecentUserImage((UserImageService) this.userImageService.get(), getSession(), (DbConnectionManager) this.dbConnectionManager.get());
            if (fetchMostRecentUserImage != null) {
                mfpImageView.setImageBitmap(fetchMostRecentUserImage);
            } else {
                mfpImageView.setUrl(null);
            }
        } else {
            TextView textView = (TextView) ViewUtils.findById(viewGroup, R.id.text);
            if (textView == null) {
                View.inflate(this, R.layout.preference_summary, viewGroup);
                textView = (TextView) ViewUtils.findById(viewGroup, R.id.text);
            }
            textView.setText(getValueForItem(num));
        }
    }

    /* access modifiers changed from: protected */
    public void hideFrameForItem(ViewGroup viewGroup, Integer num) {
        ViewUtils.setGone(viewGroup);
    }

    /* access modifiers changed from: protected */
    public ListAdapter createAdapter() {
        AnonymousClass1 r0 = new ProfileListAdapter(this, R.layout.preference_row, 16908310, getItems()) {
            public int getViewTypeCount() {
                return 2;
            }

            public int getItemViewType(int i) {
                return ((Integer) getItem(i)).intValue() == 5 ? 1 : 0;
            }
        };
        return r0;
    }

    private void dismissProfilePhotoDialog() {
        ImageChooserDialogFragment imageChooserDialogFragment = this.currentProfilePhotoDialog;
        if (imageChooserDialogFragment != null) {
            imageChooserDialogFragment.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void setupRootViewForItem(View view, Integer num) {
        ViewUtils.setVisible(num.intValue() == 11, ViewUtils.findById(view, R.id.subtitle));
        if (num.intValue() == 11) {
            ((TextView) ViewUtils.findById(view, R.id.subtitle)).setText(R.string.edit_goals_subtitle);
        }
    }

    private void confirmGoalsRecalculation(Function0 function0) {
        RecalculateNutrientGoalsTask.confirmRecalculation(this, (LocalizedStringsUtil) this.localizedStringsUtil.get(), (UserEnergyService) this.userEnergyService.get(), function0, null);
    }

    private void confirmUsernameChange() {
        new MfpAlertDialogBuilder(this).setTitle((int) R.string.are_you_sure).setCancelable(false).setMessage((int) R.string.change_username_question).setPositiveButton((int) R.string.yesBtn, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                EditProfile.lambda$confirmUsernameChange$7(EditProfile.this, dialogInterface, i);
            }
        }).setNegativeButton((int) R.string.noBtn, (OnClickListener) $$Lambda$EditProfile$XLsMQkRy6QZOWaYwiscbfTIcVRw.INSTANCE).show();
    }

    public static /* synthetic */ void lambda$confirmUsernameChange$7(EditProfile editProfile, DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.myfitnesspal.com/account/change_username"));
        if (intent.resolveActivity(editProfile.getPackageManager()) != null) {
            editProfile.startActivity(intent);
        }
    }

    @Subscribe
    public void onUnitDialogDismissedEvent(UnitDialogDismissedEvent unitDialogDismissedEvent) {
        if (!unitDialogDismissedEvent.isCancelled()) {
            ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportWaterUnitUpdated(WaterLoggingAnalyticsHelper.PROFILE_UNIT_PREF, LocalizedFluid.getUserCurrentWaterUnit(getSession()));
        }
    }

    private void checkPermissions() {
        boolean z = this.mediaType == MediaType.MEDIA;
        ((PermissionsMixin) mixin(PermissionsMixin.class)).checkPermissions(z ? new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"} : new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}, new kotlin.jvm.functions.Function0(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final Object invoke() {
                return EditProfile.lambda$checkPermissions$9(EditProfile.this, this.f$1);
            }
        }, new kotlin.jvm.functions.Function0() {
            public final Object invoke() {
                return EditProfile.this.showPermissionDeniedDialog();
            }
        });
    }

    public static /* synthetic */ Unit lambda$checkPermissions$9(EditProfile editProfile, boolean z) {
        if (z) {
            editProfile.startFileImport();
        } else {
            editProfile.startCameraImport();
        }
        return null;
    }

    public static /* synthetic */ void lambda$showPermissionDeniedDialog$11(EditProfile editProfile) {
        ((PermissionsMixin) editProfile.mixin(PermissionsMixin.class)).showPermissionDeniedDialog(editProfile.mediaType == MediaType.MEDIA ? R.string.permission_settings_storage : R.string.permission_settings_camera_storage, null);
    }

    /* access modifiers changed from: private */
    public void showPermissionDeniedDialog() {
        new Handler().post(new Runnable() {
            public final void run() {
                EditProfile.lambda$showPermissionDeniedDialog$11(EditProfile.this);
            }
        });
    }

    public Uri createImageUri() {
        ContentResolver contentResolver = getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()));
        return contentResolver.insert(Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    /* access modifiers changed from: private */
    public void showCountriesDialog(FragmentManager fragmentManager) {
        CountryDialogFragment.newInstance().show(fragmentManager, Fragments.COUNTRY_PICKER_DIALOG);
    }

    private void setupCountryUpdateListener() {
        this.countryUpdateListener = new CountryUpdateListener() {
            public final void onCountrySelected(String str) {
                EditProfile.lambda$setupCountryUpdateListener$12(EditProfile.this, str);
            }
        };
    }

    public static /* synthetic */ void lambda$setupCountryUpdateListener$12(EditProfile editProfile, String str) {
        editProfile.selectedCountry = str;
        editProfile.setBusy(true);
        editProfile.isConsentsRequired();
    }

    public CountryUpdateListener getCountryUpdateListener() {
        return this.countryUpdateListener;
    }

    private void updateCountry() {
        if (this.selectedCountry != null) {
            User user = getSession().getUser();
            user.getProfile().setCountryName(getCountryService().getCountryFromCountryCode(this.selectedCountry).getLongName());
            user.updatePropertyNamed(Basic.COUNTRY_NAME);
            ((ConsentsAnalyticsHelper) this.consentsAnalyticsHelper.get()).reportCountryChangeComplete();
            scheduleSync();
        }
    }

    public void isConsentsRequired() {
        clearDisposable();
        this.compositeDisposable.add(((ConsentsService) this.consentsService.get()).isConsentsRequired(this.selectedCountry).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                EditProfile.lambda$isConsentsRequired$13(EditProfile.this, (Boolean) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                EditProfile.this.setBusy(false);
            }
        }));
    }

    public static /* synthetic */ void lambda$isConsentsRequired$13(EditProfile editProfile, Boolean bool) throws Exception {
        editProfile.setBusy(false);
        if (bool.booleanValue()) {
            editProfile.startActivityForResult(ConsentsActivity.newStartIntent(editProfile, Mode.EXISTING_EDIT_COUNTRY, editProfile.selectedCountry, ""), RequestCodes.CONSENTS);
        } else {
            editProfile.updateCountry();
        }
    }
}
