package com.brightcove.player.offline;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.upstream.Loader.Loadable;
import java.io.File;

public class MediaPresentationDescriptionLoadable implements Loadable {
    private static final String TAG = "com.brightcove.player.offline.MediaPresentationDescriptionLoadable";
    private File downloadDirectory;
    private File manifestFile;
    private String manifestUrl;
    private DashManifest mediaPresentationDescription;
    private DashManifestParser mpdParser;

    public void cancelLoad() {
    }

    public MediaPresentationDescriptionLoadable(@NonNull DashManifestParser dashManifestParser, @NonNull String str, @NonNull File file) {
        this.mpdParser = dashManifestParser;
        this.downloadDirectory = file;
        this.manifestUrl = str;
    }

    @Nullable
    public DashManifest getResult() {
        return this.mediaPresentationDescription;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v6, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c5  */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load() throws java.io.IOException, java.lang.InterruptedException {
        /*
            r8 = this;
            java.lang.String r0 = r8.manifestUrl
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r1 = 0
            java.lang.String r2 = "http://"
            boolean r0 = r0.startsWith(r2)     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            if (r0 != 0) goto L_0x0028
            java.lang.String r0 = r8.manifestUrl     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r2 = "https://"
            boolean r0 = r0.startsWith(r2)     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            if (r0 == 0) goto L_0x0019
            goto L_0x0028
        L_0x0019:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r3 = r8.manifestUrl     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            r2 = r1
            r3 = r2
            goto L_0x0063
        L_0x0028:
            java.net.URL r0 = new java.net.URL     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r2 = r8.manifestUrl     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x00a7, all -> 0x00a3 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x009c, all -> 0x0096 }
            java.io.InputStream r3 = r0.getInputStream()     // Catch:{ IOException -> 0x009c, all -> 0x0096 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x009c, all -> 0x0096 }
            java.io.File r3 = r8.downloadDirectory     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            r3.mkdirs()     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            java.io.File r4 = r8.downloadDirectory     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            java.lang.String r5 = "master.mpd"
            r3.<init>(r4, r5)     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            r8.manifestFile = r3     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            java.io.File r3 = r8.manifestFile     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            com.brightcove.player.util.FileUtil.saveInputStream(r3, r2)     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            java.io.File r5 = r8.manifestFile     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0090, all -> 0x008a }
            r7 = r2
            r2 = r0
            r0 = r3
            r3 = r7
        L_0x0063:
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser r4 = r8.mpdParser     // Catch:{ IOException -> 0x0084, all -> 0x007c }
            java.lang.String r5 = r8.manifestUrl     // Catch:{ IOException -> 0x0084, all -> 0x007c }
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ IOException -> 0x0084, all -> 0x007c }
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r1 = r4.parse(r5, r0)     // Catch:{ IOException -> 0x0084, all -> 0x007c }
            if (r2 == 0) goto L_0x0074
            r2.disconnect()
        L_0x0074:
            com.google.android.exoplayer2.util.Util.closeQuietly(r0)
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            goto L_0x00bd
        L_0x007c:
            r1 = move-exception
            r7 = r3
            r3 = r0
            r0 = r1
            r1 = r2
            r2 = r7
            goto L_0x00c3
        L_0x0084:
            r4 = move-exception
            r7 = r3
            r3 = r0
            r0 = r4
            r4 = r7
            goto L_0x00ab
        L_0x008a:
            r3 = move-exception
            r7 = r1
            r1 = r0
            r0 = r3
            r3 = r7
            goto L_0x00c3
        L_0x0090:
            r3 = move-exception
            r4 = r2
            r2 = r0
            r0 = r3
            r3 = r1
            goto L_0x00ab
        L_0x0096:
            r2 = move-exception
            r3 = r1
            r1 = r0
            r0 = r2
            r2 = r3
            goto L_0x00c3
        L_0x009c:
            r2 = move-exception
            r3 = r1
            r4 = r3
            r7 = r2
            r2 = r0
            r0 = r7
            goto L_0x00ab
        L_0x00a3:
            r0 = move-exception
            r2 = r1
            r3 = r2
            goto L_0x00c3
        L_0x00a7:
            r0 = move-exception
            r2 = r1
            r3 = r2
            r4 = r3
        L_0x00ab:
            java.lang.String r5 = TAG     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = "Error parsing MediaPresentationDescription"
            android.util.Log.e(r5, r6, r0)     // Catch:{ all -> 0x00c0 }
            if (r2 == 0) goto L_0x00b7
            r2.disconnect()
        L_0x00b7:
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            com.google.android.exoplayer2.util.Util.closeQuietly(r4)
        L_0x00bd:
            r8.mediaPresentationDescription = r1
            return
        L_0x00c0:
            r0 = move-exception
            r1 = r2
            r2 = r4
        L_0x00c3:
            if (r1 == 0) goto L_0x00c8
            r1.disconnect()
        L_0x00c8:
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            com.google.android.exoplayer2.util.Util.closeQuietly(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.offline.MediaPresentationDescriptionLoadable.load():void");
    }

    public File getManifestFile() {
        return this.manifestFile;
    }
}
