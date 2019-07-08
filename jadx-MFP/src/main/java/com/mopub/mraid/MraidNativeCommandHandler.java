package com.mopub.mraid;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.exoplayer2.C;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Intents;
import com.mopub.common.util.ResponseHeader;
import com.mopub.common.util.Utils;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.constants.Constants.Reminder;
import java.io.File;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MraidNativeCommandHandler {
    public static final String ANDROID_CALENDAR_CONTENT_TYPE = "vnd.android.cursor.item/event";
    private static final String[] DATE_FORMATS = {"yyyy-MM-dd'T'HH:mm:ssZZZZZ", "yyyy-MM-dd'T'HH:mmZZZZZ"};

    @VisibleForTesting
    static class DownloadImageAsyncTask extends AsyncTask<String, Void, Boolean> {
        private final Context mContext;
        private final DownloadImageAsyncTaskListener mListener;

        interface DownloadImageAsyncTaskListener {
            void onFailure();

            void onSuccess();
        }

        public DownloadImageAsyncTask(@NonNull Context context, @NonNull DownloadImageAsyncTaskListener downloadImageAsyncTaskListener) {
            this.mContext = context.getApplicationContext();
            this.mListener = downloadImageAsyncTaskListener;
        }

        /* JADX WARNING: type inference failed for: r3v0 */
        /* JADX WARNING: type inference failed for: r4v0, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r3v1, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r8v4 */
        /* JADX WARNING: type inference failed for: r4v1 */
        /* JADX WARNING: type inference failed for: r3v2 */
        /* JADX WARNING: type inference failed for: r8v6, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r3v3, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r4v2 */
        /* JADX WARNING: type inference failed for: r8v7 */
        /* JADX WARNING: type inference failed for: r4v3 */
        /* JADX WARNING: type inference failed for: r4v4, types: [java.io.BufferedInputStream, java.io.Closeable, java.io.InputStream] */
        /* JADX WARNING: type inference failed for: r8v10 */
        /* JADX WARNING: type inference failed for: r3v4 */
        /* JADX WARNING: type inference failed for: r8v11 */
        /* JADX WARNING: type inference failed for: r8v15, types: [java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream] */
        /* JADX WARNING: type inference failed for: r3v5 */
        /* JADX WARNING: type inference failed for: r3v6 */
        /* JADX WARNING: type inference failed for: r3v7 */
        /* JADX WARNING: type inference failed for: r4v5 */
        /* JADX WARNING: type inference failed for: r8v16 */
        /* JADX WARNING: type inference failed for: r4v6 */
        /* JADX WARNING: type inference failed for: r4v7 */
        /* JADX WARNING: type inference failed for: r8v17 */
        /* JADX WARNING: type inference failed for: r8v18 */
        /* JADX WARNING: type inference failed for: r8v19 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v1
  assigns: []
  uses: []
  mth insns count: 62
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 10 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Boolean doInBackground(@android.support.annotation.NonNull java.lang.String[] r8) {
            /*
                r7 = this;
                r0 = 0
                if (r8 == 0) goto L_0x0084
                int r1 = r8.length
                if (r1 == 0) goto L_0x0084
                r1 = r8[r0]
                if (r1 != 0) goto L_0x000c
                goto L_0x0084
            L_0x000c:
                java.io.File r1 = r7.getPictureStoragePath()
                r1.mkdirs()
                r8 = r8[r0]
                java.net.URI r2 = java.net.URI.create(r8)
                r3 = 0
                java.net.HttpURLConnection r8 = com.mopub.common.MoPubHttpUrlConnection.getHttpUrlConnection(r8)     // Catch:{ Exception -> 0x006d, all -> 0x006a }
                java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x006d, all -> 0x006a }
                java.io.InputStream r5 = r8.getInputStream()     // Catch:{ Exception -> 0x006d, all -> 0x006a }
                r4.<init>(r5)     // Catch:{ Exception -> 0x006d, all -> 0x006a }
                com.mopub.common.util.ResponseHeader r5 = com.mopub.common.util.ResponseHeader.LOCATION     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                java.lang.String r5 = r5.getKey()     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                java.lang.String r5 = r8.getHeaderField(r5)     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                if (r6 != 0) goto L_0x003b
                java.net.URI r2 = java.net.URI.create(r5)     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
            L_0x003b:
                java.util.Map r8 = r8.getHeaderFields()     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                java.lang.String r8 = r7.getFileNameForUriAndHeaders(r2, r8)     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                r2.<init>(r1, r8)     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                r8.<init>(r2)     // Catch:{ Exception -> 0x0067, all -> 0x0065 }
                com.mopub.common.util.Streams.copyContent(r4, r8)     // Catch:{ Exception -> 0x0068, all -> 0x0063 }
                java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0068, all -> 0x0063 }
                r7.loadPictureIntoGalleryApp(r1)     // Catch:{ Exception -> 0x0068, all -> 0x0063 }
                r1 = 1
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0068, all -> 0x0063 }
                com.mopub.common.util.Streams.closeStream(r4)
                com.mopub.common.util.Streams.closeStream(r8)
                return r0
            L_0x0063:
                r0 = move-exception
                goto L_0x007b
            L_0x0065:
                r8 = move-exception
                goto L_0x007d
            L_0x0067:
                r8 = r3
            L_0x0068:
                r3 = r4
                goto L_0x006e
            L_0x006a:
                r8 = move-exception
                r4 = r3
                goto L_0x007d
            L_0x006d:
                r8 = r3
            L_0x006e:
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0079 }
                com.mopub.common.util.Streams.closeStream(r3)
                com.mopub.common.util.Streams.closeStream(r8)
                return r0
            L_0x0079:
                r0 = move-exception
                r4 = r3
            L_0x007b:
                r3 = r8
                r8 = r0
            L_0x007d:
                com.mopub.common.util.Streams.closeStream(r4)
                com.mopub.common.util.Streams.closeStream(r3)
                throw r8
            L_0x0084:
                java.lang.Boolean r8 = java.lang.Boolean.valueOf(r0)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mraid.MraidNativeCommandHandler.DownloadImageAsyncTask.doInBackground(java.lang.String[]):java.lang.Boolean");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            if (bool == null || !bool.booleanValue()) {
                this.mListener.onFailure();
            } else {
                this.mListener.onSuccess();
            }
        }

        @Nullable
        private String getFileNameForUriAndHeaders(@NonNull URI uri, @Nullable Map<String, List<String>> map) {
            Preconditions.checkNotNull(uri);
            String path = uri.getPath();
            if (path == null || map == null) {
                return null;
            }
            String name = new File(path).getName();
            List list = (List) map.get(ResponseHeader.CONTENT_TYPE.getKey());
            if (list != null && !list.isEmpty()) {
                int i = 0;
                if (list.get(0) != null) {
                    String[] split = ((String) list.get(0)).split(";");
                    int length = split.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        String str = split[i];
                        if (str.contains("image/")) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(".");
                            sb.append(str.split("/")[1]);
                            String sb2 = sb.toString();
                            if (!name.endsWith(sb2)) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(name);
                                sb3.append(sb2);
                                name = sb3.toString();
                            }
                        } else {
                            i++;
                        }
                    }
                    return name;
                }
            }
            return name;
        }

        private File getPictureStoragePath() {
            return new File(Environment.getExternalStorageDirectory(), "Pictures");
        }

        private void loadPictureIntoGalleryApp(String str) {
            MoPubMediaScannerConnectionClient moPubMediaScannerConnectionClient = new MoPubMediaScannerConnectionClient(str, null);
            MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.mContext, moPubMediaScannerConnectionClient);
            moPubMediaScannerConnectionClient.setMediaScannerConnection(mediaScannerConnection);
            mediaScannerConnection.connect();
        }
    }

    private static class MoPubMediaScannerConnectionClient implements MediaScannerConnectionClient {
        private final String mFilename;
        private MediaScannerConnection mMediaScannerConnection;
        private final String mMimeType;

        private MoPubMediaScannerConnectionClient(String str, String str2) {
            this.mFilename = str;
            this.mMimeType = str2;
        }

        /* access modifiers changed from: private */
        public void setMediaScannerConnection(MediaScannerConnection mediaScannerConnection) {
            this.mMediaScannerConnection = mediaScannerConnection;
        }

        public void onMediaScannerConnected() {
            MediaScannerConnection mediaScannerConnection = this.mMediaScannerConnection;
            if (mediaScannerConnection != null) {
                mediaScannerConnection.scanFile(this.mFilename, this.mMimeType);
            }
        }

        public void onScanCompleted(String str, Uri uri) {
            MediaScannerConnection mediaScannerConnection = this.mMediaScannerConnection;
            if (mediaScannerConnection != null) {
                mediaScannerConnection.disconnect();
            }
        }
    }

    interface MraidCommandFailureListener {
        void onFailure(MraidCommandException mraidCommandException);
    }

    /* access modifiers changed from: 0000 */
    public void createCalendarEvent(Context context, Map<String, String> map) throws MraidCommandException {
        if (isCalendarAvailable(context)) {
            try {
                Map translateJSParamsToAndroidCalendarEventMapping = translateJSParamsToAndroidCalendarEventMapping(map);
                Intent type = new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE);
                for (String str : translateJSParamsToAndroidCalendarEventMapping.keySet()) {
                    Object obj = translateJSParamsToAndroidCalendarEventMapping.get(str);
                    if (obj instanceof Long) {
                        type.putExtra(str, ((Long) obj).longValue());
                    } else if (obj instanceof Integer) {
                        type.putExtra(str, ((Integer) obj).intValue());
                    } else {
                        type.putExtra(str, (String) obj);
                    }
                }
                type.setFlags(C.ENCODING_PCM_MU_LAW);
                context.startActivity(type);
            } catch (ActivityNotFoundException unused) {
                MoPubLog.d("no calendar app installed");
                throw new MraidCommandException("Action is unsupported on this device - no calendar app installed");
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("create calendar: invalid parameters ");
                sb.append(e.getMessage());
                MoPubLog.d(sb.toString());
                throw new MraidCommandException((Throwable) e);
            } catch (Exception e2) {
                MoPubLog.d("could not create calendar event");
                throw new MraidCommandException((Throwable) e2);
            }
        } else {
            MoPubLog.d("unsupported action createCalendarEvent for devices pre-ICS");
            throw new MraidCommandException("Action is unsupported on this device (need Android version Ice Cream Sandwich or above)");
        }
    }

    /* access modifiers changed from: 0000 */
    public void storePicture(@NonNull Context context, @NonNull String str, @NonNull MraidCommandFailureListener mraidCommandFailureListener) throws MraidCommandException {
        if (!isStorePictureSupported(context)) {
            MoPubLog.d("Error downloading file - the device does not have an SD card mounted, or the Android permission is not granted.");
            throw new MraidCommandException("Error downloading file  - the device does not have an SD card mounted, or the Android permission is not granted.");
        } else if (context instanceof Activity) {
            showUserDialog(context, str, mraidCommandFailureListener);
        } else {
            Toast.makeText(context, "Downloading image to Picture gallery...", 0).show();
            downloadImage(context, str, mraidCommandFailureListener);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isTelAvailable(Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return Intents.deviceCanHandleIntent(context, intent);
    }

    /* access modifiers changed from: 0000 */
    public boolean isSmsAvailable(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return Intents.deviceCanHandleIntent(context, intent);
    }

    public static boolean isStorePictureSupported(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) && DeviceUtils.isPermissionGranted(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    static boolean isCalendarAvailable(Context context) {
        return Intents.deviceCanHandleIntent(context, new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE));
    }

    /* access modifiers changed from: 0000 */
    public boolean isInlineVideoAvailable(@NonNull Activity activity, @NonNull View view) {
        while (view.isHardwareAccelerated() && !Utils.bitMaskContainsFlag(view.getLayerType(), 1)) {
            if (!(view.getParent() instanceof View)) {
                Window window = activity.getWindow();
                if (window == null || !Utils.bitMaskContainsFlag(window.getAttributes().flags, C.DEFAULT_MUXED_BUFFER_SIZE)) {
                    return false;
                }
                return true;
            }
            view = (View) view.getParent();
        }
        return false;
    }

    private Map<String, Object> translateJSParamsToAndroidCalendarEventMapping(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (!map.containsKey("description") || !map.containsKey(TtmlNode.START)) {
            throw new IllegalArgumentException("Missing start and description fields");
        }
        hashMap.put("title", map.get("description"));
        if (!map.containsKey(TtmlNode.START) || map.get(TtmlNode.START) == null) {
            throw new IllegalArgumentException("Invalid calendar event: start is null.");
        }
        Date parseDate = parseDate((String) map.get(TtmlNode.START));
        if (parseDate != null) {
            hashMap.put("beginTime", Long.valueOf(parseDate.getTime()));
            if (map.containsKey("end") && map.get("end") != null) {
                Date parseDate2 = parseDate((String) map.get("end"));
                if (parseDate2 != null) {
                    hashMap.put(AbstractEvent.END_TIME, Long.valueOf(parseDate2.getTime()));
                } else {
                    throw new IllegalArgumentException("Invalid calendar event: end time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
                }
            }
            if (map.containsKey("location")) {
                hashMap.put("eventLocation", map.get("location"));
            }
            if (map.containsKey(Challenges.CHALLENGE_TAB_SUMMARY)) {
                hashMap.put("description", map.get(Challenges.CHALLENGE_TAB_SUMMARY));
            }
            if (map.containsKey("transparency")) {
                hashMap.put("availability", Integer.valueOf(((String) map.get("transparency")).equals("transparent") ? 1 : 0));
            }
            hashMap.put("rrule", parseRecurrenceRule(map));
            return hashMap;
        }
        throw new IllegalArgumentException("Invalid calendar event: start time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
    }

    private Date parseDate(String str) {
        String[] strArr = DATE_FORMATS;
        int length = strArr.length;
        Date date = null;
        int i = 0;
        while (i < length) {
            try {
                date = new SimpleDateFormat(strArr[i], Locale.US).parse(str);
                if (date != null) {
                    break;
                }
                i++;
            } catch (ParseException unused) {
            }
        }
        return date;
    }

    private String parseRecurrenceRule(Map<String, String> map) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (map.containsKey("frequency")) {
            String str = (String) map.get("frequency");
            int parseInt = map.containsKey("interval") ? Integer.parseInt((String) map.get("interval")) : -1;
            if (Reminder.DAILY_FREQUENCY.equals(str)) {
                sb.append("FREQ=DAILY;");
                if (parseInt != -1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("INTERVAL=");
                    sb2.append(parseInt);
                    sb2.append(";");
                    sb.append(sb2.toString());
                }
            } else if (Reminder.WEEKLY_FREQUENCY.equals(str)) {
                sb.append("FREQ=WEEKLY;");
                if (parseInt != -1) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("INTERVAL=");
                    sb3.append(parseInt);
                    sb3.append(";");
                    sb.append(sb3.toString());
                }
                if (map.containsKey("daysInWeek")) {
                    String translateWeekIntegersToDays = translateWeekIntegersToDays((String) map.get("daysInWeek"));
                    if (translateWeekIntegersToDays != null) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("BYDAY=");
                        sb4.append(translateWeekIntegersToDays);
                        sb4.append(";");
                        sb.append(sb4.toString());
                    } else {
                        throw new IllegalArgumentException("invalid ");
                    }
                }
            } else if (Reminder.MONTHLY_FREQUENCY.equals(str)) {
                sb.append("FREQ=MONTHLY;");
                if (parseInt != -1) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("INTERVAL=");
                    sb5.append(parseInt);
                    sb5.append(";");
                    sb.append(sb5.toString());
                }
                if (map.containsKey("daysInMonth")) {
                    String translateMonthIntegersToDays = translateMonthIntegersToDays((String) map.get("daysInMonth"));
                    if (translateMonthIntegersToDays != null) {
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("BYMONTHDAY=");
                        sb6.append(translateMonthIntegersToDays);
                        sb6.append(";");
                        sb.append(sb6.toString());
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } else {
                throw new IllegalArgumentException("frequency is only supported for daily, weekly, and monthly.");
            }
        }
        return sb.toString();
    }

    private String translateWeekIntegersToDays(String str) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[7];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            if (parseInt2 == 7) {
                parseInt2 = 0;
            }
            if (!zArr[parseInt2]) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(dayNumberToDayOfWeekString(parseInt2));
                sb2.append(",");
                sb.append(sb2.toString());
                zArr[parseInt2] = true;
            }
        }
        if (split.length != 0) {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        throw new IllegalArgumentException("must have at least 1 day of the week if specifying repeating weekly");
    }

    private String translateMonthIntegersToDays(String str) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[63];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            int i = parseInt2 + 31;
            if (!zArr[i]) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(dayNumberToDayOfMonthString(parseInt2));
                sb2.append(",");
                sb.append(sb2.toString());
                zArr[i] = true;
            }
        }
        if (split.length != 0) {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        throw new IllegalArgumentException("must have at least 1 day of the month if specifying repeating weekly");
    }

    private String dayNumberToDayOfWeekString(int i) throws IllegalArgumentException {
        switch (i) {
            case 0:
                return "SU";
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            case 6:
                return "SA";
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("invalid day of week ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private String dayNumberToDayOfMonthString(int i) throws IllegalArgumentException {
        if (i == 0 || i < -31 || i > 31) {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid day of month ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(i);
        return sb2.toString();
    }

    /* access modifiers changed from: 0000 */
    public void downloadImage(final Context context, String str, final MraidCommandFailureListener mraidCommandFailureListener) {
        AsyncTasks.safeExecuteOnExecutor(new DownloadImageAsyncTask(context, new DownloadImageAsyncTaskListener() {
            public void onSuccess() {
                MoPubLog.d("Image successfully saved.");
            }

            public void onFailure() {
                Toast.makeText(context, "Image failed to download.", 0).show();
                MoPubLog.d("Error downloading and saving image file.");
                mraidCommandFailureListener.onFailure(new MraidCommandException("Error downloading and saving image file."));
            }
        }), str);
    }

    private void showUserDialog(final Context context, final String str, final MraidCommandFailureListener mraidCommandFailureListener) {
        new Builder(context).setTitle("Save Image").setMessage("Download image to Picture gallery?").setNegativeButton("Cancel", null).setPositiveButton("Okay", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MraidNativeCommandHandler.this.downloadImage(context, str, mraidCommandFailureListener);
            }
        }).setCancelable(true).show();
    }
}
