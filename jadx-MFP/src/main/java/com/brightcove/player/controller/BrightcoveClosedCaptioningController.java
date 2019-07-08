package com.brightcove.player.controller;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.CaptioningManager.CaptionStyle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.brightcove.player.R;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.captioning.BrightcoveCaptionPropertiesActivity;
import com.brightcove.player.captioning.LoadCaptionsService;
import com.brightcove.player.captioning.preferences.CaptionConstants;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.model.VideoFields;
import com.brightcove.player.view.BaseVideoView;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@ListensFor(events = {"didSetVideo", "stop", "enterTvMode", "hidePlayerOptions"})
@Emits(events = {"captionsDialogOk", "captionsDialogSettings", "toggleClosedCaptions", "captionsLanguages", "selectClosedCaptionTrack"})
public class BrightcoveClosedCaptioningController extends AbstractComponent {
    /* access modifiers changed from: private */
    public static final String TAG = "BrightcoveClosedCaptioningController";
    private List<String> availableLanguages;
    private CaptioningManager captioningManager;
    private final int captionsButtonId = R.id.captions;
    protected Context context;
    /* access modifiers changed from: private */
    public int currentLanguage = 0;
    /* access modifiers changed from: private */
    public Source currentSource;
    /* access modifiers changed from: private */
    public Video currentVideo;
    private EventListener didSetVideoListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            BrightcoveClosedCaptioningController.this.currentVideo = (Video) event.properties.get("video");
            BrightcoveClosedCaptioningController.this.currentSource = (Source) event.properties.get("source");
            BrightcoveClosedCaptioningController brightcoveClosedCaptioningController = BrightcoveClosedCaptioningController.this;
            brightcoveClosedCaptioningController.isOffline = brightcoveClosedCaptioningController.currentSource != null && BrightcoveClosedCaptioningController.this.currentSource.isLocal();
            String string = PreferenceManager.getDefaultSharedPreferences(BrightcoveClosedCaptioningController.this.context).getString(CaptionConstants.PREF_LOCALE, Locale.getDefault().getLanguage());
            BrightcoveClosedCaptioningController brightcoveClosedCaptioningController2 = BrightcoveClosedCaptioningController.this;
            List access$500 = brightcoveClosedCaptioningController2.getCaptionsListFromVideo(brightcoveClosedCaptioningController2.currentVideo);
            if (access$500 != null) {
                Iterator it = access$500.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Pair pair = (Pair) it.next();
                    if (!((Uri) pair.first).equals(Uri.EMPTY) && ((BrightcoveCaptionFormat) pair.second).language().equals(string)) {
                        BrightcoveClosedCaptioningController.this.loadCaptionsService.loadCaptions((Uri) pair.first, ((BrightcoveCaptionFormat) pair.second).type());
                        break;
                    }
                }
            }
            if (VERSION.SDK_INT >= 19 && BrightcoveClosedCaptioningController.this.shouldImportSystemSettings) {
                BrightcoveClosedCaptioningController.this.importSystemSettings();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean isOffline;
    /* access modifiers changed from: private */
    public boolean isTvMode;
    /* access modifiers changed from: private */
    public LoadCaptionsService loadCaptionsService;
    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioGroup.check(i);
            BrightcoveClosedCaptioningController.this.currentLanguage = radioGroup.indexOfChild((RadioButton) radioGroup.findViewById(i));
            BrightcoveClosedCaptioningController brightcoveClosedCaptioningController = BrightcoveClosedCaptioningController.this;
            brightcoveClosedCaptioningController.selectCaptions(brightcoveClosedCaptioningController.currentLanguage);
        }
    };
    private RadioGroup radioGroup;
    /* access modifiers changed from: private */
    public boolean shouldImportSystemSettings;
    private BaseVideoView videoView;

    public BrightcoveClosedCaptioningController(BaseVideoView baseVideoView, Context context2) {
        super(baseVideoView.getEventEmitter(), BrightcoveClosedCaptioningController.class);
        this.context = context2;
        this.videoView = baseVideoView;
        this.shouldImportSystemSettings = true;
        addListener(EventType.DID_SET_VIDEO, this.didSetVideoListener);
        this.loadCaptionsService = new LoadCaptionsService(this.eventEmitter, context2.getContentResolver());
        if (VERSION.SDK_INT >= 19) {
            initCaptioningManager();
        }
        addListener(EventType.ENTER_TV_MODE, new EventListener() {
            public void processEvent(Event event) {
                BrightcoveClosedCaptioningController.this.isTvMode = true;
            }
        });
        addListener(EventType.HIDE_PLAYER_OPTIONS, new EventListener() {
            public void processEvent(Event event) {
                BrightcovePlayerOptionsManager.getInstance().hidePlayerOptions(BrightcoveClosedCaptioningController.this.eventEmitter);
                BrightcoveClosedCaptioningController.this.hideCaptionsMenu();
            }
        });
    }

    public void setShouldImportSystemSettings(boolean z) {
        this.shouldImportSystemSettings = z;
    }

    public LoadCaptionsService getLoadCaptionsService() {
        return this.loadCaptionsService;
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void importSystemSettings() {
        Log.d(TAG, "Importing and saving caption settings from the system.");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        CaptionStyle userStyle = this.captioningManager.getUserStyle();
        Editor edit = defaultSharedPreferences.edit();
        edit.putInt(CaptionConstants.PREF_PRESET, -1);
        edit.putString(CaptionConstants.PREF_FONT_SIZE, Float.toString(this.captioningManager.getFontScale()));
        edit.putInt(CaptionConstants.PREF_FOREGROUND_COLOR, Color.rgb(Color.red(userStyle.foregroundColor), Color.green(userStyle.foregroundColor), Color.blue(userStyle.foregroundColor)));
        edit.putInt(CaptionConstants.PREF_EDGE_TYPE, userStyle.edgeType).putInt(CaptionConstants.PREF_EDGE_COLOR, userStyle.edgeColor);
        if (Color.alpha(userStyle.backgroundColor) == 0) {
            edit.putInt(CaptionConstants.PREF_BACKGROUND_COLOR, 0);
            edit.putInt(CaptionConstants.PREF_BACKGROUND_OPACITY, 0);
        } else {
            edit.putInt(CaptionConstants.PREF_BACKGROUND_COLOR, Color.rgb(Color.red(userStyle.backgroundColor), Color.green(userStyle.backgroundColor), Color.blue(userStyle.backgroundColor)));
            edit.putInt(CaptionConstants.PREF_BACKGROUND_OPACITY, Color.argb(Color.alpha(userStyle.backgroundColor), 255, 255, 255));
        }
        if (this.captioningManager.getLocale() != null) {
            edit.putString(CaptionConstants.PREF_LOCALE, this.captioningManager.getLocale().getLanguage());
        }
        Typeface typeface = userStyle.getTypeface();
        if (typeface != null) {
            if (typeface.equals(Typeface.DEFAULT)) {
                Log.v(TAG, "importSystemSettings: default");
                edit.putString(CaptionConstants.PREF_TYPEFACE, null);
            } else if (typeface.equals(Typeface.SANS_SERIF)) {
                Log.v(TAG, "importSystemSettings: sans serif");
                edit.putString(CaptionConstants.PREF_TYPEFACE, "sans-serif");
            } else if (typeface.equals(Typeface.SERIF)) {
                Log.v(TAG, "importSystemSettings: serif");
                edit.putString(CaptionConstants.PREF_TYPEFACE, C.SERIF_NAME);
            } else if (typeface.equals(Typeface.MONOSPACE)) {
                Log.v(TAG, "importSystemSettings: monospace");
                edit.putString(CaptionConstants.PREF_TYPEFACE, "monospace");
            } else {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected typeface: ");
                sb.append(typeface);
                Log.w(str, sb.toString());
            }
        }
        edit.apply();
        if (VERSION.SDK_INT >= 21) {
            updateLollipopOptions(defaultSharedPreferences, userStyle);
        }
    }

    @TargetApi(21)
    private void updateLollipopOptions(SharedPreferences sharedPreferences, CaptionStyle captionStyle) {
        Editor edit = sharedPreferences.edit();
        if (Color.alpha(captionStyle.windowColor) == 0) {
            edit.putInt(CaptionConstants.PREF_WINDOW_COLOR, 0);
            edit.putInt(CaptionConstants.PREF_WINDOW_OPACITY, 0);
        } else {
            edit.putInt(CaptionConstants.PREF_WINDOW_COLOR, Color.rgb(Color.red(captionStyle.windowColor), Color.green(captionStyle.windowColor), Color.blue(captionStyle.windowColor)));
            edit.putInt(CaptionConstants.PREF_WINDOW_OPACITY, Color.argb(Color.alpha(captionStyle.windowColor), 255, 255, 255));
        }
        edit.putInt(CaptionConstants.PREF_FOREGROUND_OPACITY, Color.argb(Color.alpha(captionStyle.foregroundColor), 255, 255, 255));
        edit.apply();
    }

    public boolean validateCaptionSourcesField(Object obj) {
        int i;
        if (obj instanceof List) {
            i = 0;
            for (Object obj2 : (List) obj) {
                if (!(obj2 instanceof Pair)) {
                    i++;
                }
            }
        } else {
            i = 0;
        }
        if (i == 0) {
            return true;
        }
        return false;
    }

    public boolean checkIfCaptionsExist(Video video) {
        if (video == null) {
            Log.e(TAG, "Got null video, cannot load captions.");
            return false;
        }
        Object obj = video.getProperties().get(Fields.CAPTION_SOURCES);
        if (validateCaptionSourcesField(obj)) {
            List list = (List) obj;
            if (list != null && list.size() > 0) {
                return true;
            }
        }
        return false;
    }

    private List<String> getAvailableLanguages() {
        ArrayList arrayList = new ArrayList();
        List<Pair> captionsListFromVideo = getCaptionsListFromVideo(this.currentVideo);
        if (captionsListFromVideo != null) {
            for (Pair pair : captionsListFromVideo) {
                if (pair.second != null) {
                    arrayList.add(((BrightcoveCaptionFormat) pair.second).language());
                }
            }
        }
        return arrayList;
    }

    private CharSequence[] getMenuList(List<String> list) {
        CharSequence[] charSequenceArr = new CharSequence[(list.size() + 1)];
        int i = 0;
        charSequenceArr[0] = this.context.getString(R.string.color_none);
        while (i < list.size()) {
            int i2 = i + 1;
            charSequenceArr[i2] = new Locale((String) list.get(i)).getDisplayLanguage();
            i = i2;
        }
        return charSequenceArr;
    }

    public void showCaptionsDialog() {
        this.availableLanguages = getAvailableLanguages();
        CharSequence[] menuList = getMenuList(this.availableLanguages);
        if (!isCaptioningEnabled()) {
            this.currentLanguage = 0;
        } else {
            String displayLanguage = new Locale(PreferenceManager.getDefaultSharedPreferences(this.context).getString(CaptionConstants.PREF_LOCALE, Locale.getDefault().getLanguage())).getDisplayLanguage();
            boolean z = true;
            int i = 1;
            while (true) {
                if (i >= menuList.length) {
                    z = false;
                    break;
                } else if (menuList[i].toString().equals(displayLanguage)) {
                    this.currentLanguage = i;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                this.currentLanguage = 0;
            }
        }
        if (this.isTvMode) {
            showCaptionsMenu();
        } else {
            new Builder(this.context).setTitle(R.string.brightcove_caption_selection).setSingleChoiceItems(menuList, this.currentLanguage, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BrightcoveClosedCaptioningController.this.currentLanguage = i;
                    BrightcoveClosedCaptioningController.this.selectCaptions(i);
                }
            }).setPositiveButton(17039370, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BrightcoveClosedCaptioningController.this.eventEmitter.emit(EventType.CAPTIONS_DIALOG_OK);
                    dialogInterface.dismiss();
                }
            }).setNeutralButton(R.string.brightcove_settings, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BrightcoveClosedCaptioningController.this.shouldImportSystemSettings = false;
                    Log.d(BrightcoveClosedCaptioningController.TAG, "Showing the captions preference activity.");
                    BrightcoveClosedCaptioningController.this.context.startActivity(new Intent(BrightcoveClosedCaptioningController.this.context, BrightcoveCaptionPropertiesActivity.class));
                    BrightcoveClosedCaptioningController.this.eventEmitter.emit(EventType.CAPTIONS_DIALOG_SETTINGS);
                }
            }).show();
        }
    }

    public boolean isCaptioningEnabled() {
        return PreferenceManager.getDefaultSharedPreferences(this.context).getBoolean(CaptionConstants.PREF_MASTER_SWITCH, false);
    }

    public void saveClosedCaptioningState(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.BOOLEAN, Boolean.valueOf(z));
        this.eventEmitter.emit(EventType.TOGGLE_CLOSED_CAPTIONS, hashMap);
        PreferenceManager.getDefaultSharedPreferences(this.context).edit().putBoolean(CaptionConstants.PREF_MASTER_SWITCH, z).apply();
    }

    @TargetApi(19)
    private void initCaptioningManager() {
        this.captioningManager = (CaptioningManager) this.context.getSystemService(VideoFields.CAPTIONING);
        CaptioningManager captioningManager2 = this.captioningManager;
        if (captioningManager2 != null) {
            saveClosedCaptioningState(captioningManager2.isEnabled());
        }
    }

    private Pair<Uri, BrightcoveCaptionFormat> getCaptionsForLanguageCode(String str) {
        List<Pair<Uri, BrightcoveCaptionFormat>> captionsListFromVideo = getCaptionsListFromVideo(this.currentVideo);
        if (captionsListFromVideo != null) {
            for (Pair<Uri, BrightcoveCaptionFormat> pair : captionsListFromVideo) {
                if (((BrightcoveCaptionFormat) pair.second).language().equals(str)) {
                    return pair;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public List<Pair<Uri, BrightcoveCaptionFormat>> getCaptionsListFromVideo(Video video) {
        Object obj = video.getProperties().get(Fields.CAPTION_SOURCES);
        if (!validateCaptionSourcesField(obj)) {
            return null;
        }
        List<Pair<Uri, BrightcoveCaptionFormat>> list = (List) obj;
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (Pair pair : list) {
                arrayList.add(((BrightcoveCaptionFormat) pair.second).language());
            }
            if (!this.isOffline) {
                HashMap hashMap = new HashMap();
                hashMap.put(AbstractEvent.LANGUAGES, arrayList);
                getEventEmitter().emit(EventType.CAPTIONS_LANGUAGES, hashMap);
            }
        }
        return list;
    }

    public void setLocaleCode(String str) {
        PreferenceManager.getDefaultSharedPreferences(this.context).edit().putString(CaptionConstants.PREF_LOCALE, str).apply();
    }

    /* access modifiers changed from: protected */
    public void initCaptionsMenu() {
        if (this.radioGroup == null) {
            BrightcovePlayerOptionsManager.getInstance().initPlayerOptions(this.videoView);
            this.radioGroup = BrightcovePlayerOptionsManager.getInstance().getCaptionsGroup();
            RadioGroup radioGroup2 = this.radioGroup;
            if (radioGroup2 != null) {
                radioGroup2.setOnCheckedChangeListener(this.onCheckedChangeListener);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showCaptionsMenu() {
        initCaptionsMenu();
        RadioGroup radioGroup2 = this.radioGroup;
        if (radioGroup2 != null && radioGroup2.getChildCount() <= 0) {
            LayoutInflater layoutInflater = (LayoutInflater) this.videoView.getContext().getSystemService("layout_inflater");
            if (layoutInflater != null) {
                for (CharSequence text : getMenuList(this.availableLanguages)) {
                    RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.tv_player_options_item, this.radioGroup, false);
                    radioButton.setText(text);
                    this.radioGroup.addView(radioButton);
                }
                BrightcovePlayerOptionsManager.getInstance().showPlayerOptions(this.eventEmitter);
                BrightcovePlayerOptionsManager.getInstance().showCaptionsOptions();
                RadioButton radioButton2 = (RadioButton) this.radioGroup.getChildAt(this.currentLanguage);
                if (!BrightcovePlayerOptionsManager.getInstance().isAudioTracksVisible()) {
                    radioButton2.requestFocus();
                }
                this.radioGroup.check(radioButton2.getId());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void hideCaptionsMenu() {
        if (this.radioGroup != null) {
            BrightcovePlayerOptionsManager.getInstance().hideAudioTracksOptions();
            this.radioGroup.removeAllViews();
        }
    }

    /* access modifiers changed from: protected */
    public void selectCaptions(int i) {
        if (i != 0) {
            List<String> list = this.availableLanguages;
            if (list != null && !list.isEmpty()) {
                saveClosedCaptioningState(true);
                String str = (String) this.availableLanguages.get(i - 1);
                setLocaleCode(str);
                if (this.loadCaptionsService != null) {
                    Pair captionsForLanguageCode = getCaptionsForLanguageCode(str);
                    if (!((Uri) captionsForLanguageCode.first).equals(Uri.EMPTY)) {
                        if (!((Uri) captionsForLanguageCode.first).toString().startsWith(BrightcoveCaptionFormat.BRIGHTCOVE_SCHEME)) {
                            this.loadCaptionsService.loadCaptions((Uri) captionsForLanguageCode.first, ((BrightcoveCaptionFormat) captionsForLanguageCode.second).type());
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put(AbstractEvent.CAPTION_FORMAT, captionsForLanguageCode.second);
                        hashMap.put(AbstractEvent.CAPTION_URI, captionsForLanguageCode.first);
                        this.eventEmitter.emit(EventType.SELECT_CLOSED_CAPTION_TRACK, hashMap);
                        return;
                    }
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put(AbstractEvent.BOOLEAN, Boolean.valueOf(true));
                    this.eventEmitter.emit(EventType.TOGGLE_CLOSED_CAPTIONS, hashMap2);
                    return;
                }
                return;
            }
        }
        saveClosedCaptioningState(false);
    }
}
