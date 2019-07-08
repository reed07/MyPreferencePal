package com.brightcove.player.captioning.tasks;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.brightcove.player.captioning.TTMLParser;
import com.brightcove.player.captioning.WebVTTParser;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.event.RegisteringEventEmitter;
import com.brightcove.player.model.CaptionType;
import com.brightcove.player.model.TTMLDocument;
import com.brightcove.player.model.WebVTTDocument;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

@ListensFor(events = {})
@Emits(events = {"didLoadClosedCaptions", "closedCaptioningError"})
public class LoadCaptionsTask extends AsyncTask<Uri, Void, Void> implements Component {
    private static final String TAG = "LoadCaptionsTask";
    private CaptionType captionType;
    private ContentResolver contentResolver;
    /* access modifiers changed from: private */
    public TTMLDocument currentTTMLDocument;
    /* access modifiers changed from: private */
    public WebVTTDocument currentWebVTTDocument;
    private EventEmitter eventEmitter;
    private String lastError;
    private Exception lastException;

    public interface ResponseStreamListener {
        void onStreamReady(InputStream inputStream) throws Exception;
    }

    public LoadCaptionsTask(EventEmitter eventEmitter2, ContentResolver contentResolver2, CaptionType captionType2) {
        if (eventEmitter2 != null) {
            this.eventEmitter = RegisteringEventEmitter.build(eventEmitter2, LoadCaptionsTask.class);
            this.contentResolver = contentResolver2;
            this.captionType = captionType2;
            return;
        }
        throw new IllegalArgumentException("must provide an EventEmitter");
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Uri... uriArr) {
        if (uriArr.length != 1 || uriArr[0] == null) {
            throw new IllegalArgumentException("must provide a single URI argument");
        }
        this.lastError = null;
        switch (this.captionType) {
            case WEBVTT:
                try {
                    doGetRequestAsStream(uriArr[0], new ResponseStreamListener() {
                        public void onStreamReady(InputStream inputStream) throws Exception {
                            LoadCaptionsTask.this.currentWebVTTDocument = WebVTTParser.parse(inputStream, "UTF-8");
                        }
                    });
                    break;
                } catch (IOException e) {
                    this.lastError = "exception while issuing HTTP request";
                    this.lastException = e;
                    cancel(true);
                    break;
                } catch (Exception e2) {
                    this.lastError = "unexpected exception parsing WebVTT response";
                    this.lastException = e2;
                    cancel(true);
                    break;
                }
            case TTML:
                try {
                    doGetRequestAsStream(uriArr[0], new ResponseStreamListener() {
                        public void onStreamReady(InputStream inputStream) throws Exception {
                            LoadCaptionsTask.this.currentTTMLDocument = TTMLParser.parseXml(inputStream, null);
                        }
                    });
                    break;
                } catch (IOException e3) {
                    this.lastError = "exception while issuing HTTP request";
                    this.lastException = e3;
                    cancel(true);
                    break;
                } catch (XmlPullParserException e4) {
                    this.lastError = "exception parsing DFXP TTML response";
                    this.lastException = e4;
                    cancel(true);
                    break;
                } catch (Exception e5) {
                    this.lastError = "unexpected exception parsing DFXP TTML response";
                    this.lastException = e5;
                    cancel(true);
                    break;
                }
            default:
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected captions type: ");
                sb.append(this.captionType);
                Log.e(str, sb.toString());
                break;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doGetRequestAsStream(android.net.Uri r5, com.brightcove.player.captioning.tasks.LoadCaptionsTask.ResponseStreamListener r6) throws java.lang.Exception {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x005b
            if (r6 == 0) goto L_0x0053
            android.content.ContentResolver r0 = r4.contentResolver     // Catch:{ FileNotFoundException -> 0x000e }
            java.io.InputStream r0 = r0.openInputStream(r5)     // Catch:{ FileNotFoundException -> 0x000e }
            r6.onStreamReady(r0)     // Catch:{ FileNotFoundException -> 0x000e }
            goto L_0x0048
        L_0x000e:
            java.net.URI r0 = new java.net.URI
            java.lang.String r5 = r5.toString()
            r0.<init>(r5)
            java.net.URL r5 = r0.toURL()
            r0 = 0
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "issuing GET request: "
            r2.append(r3)
            java.lang.String r3 = r5.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
            java.net.URLConnection r5 = r5.openConnection()     // Catch:{ all -> 0x004b }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ all -> 0x004b }
            java.io.InputStream r0 = r5.getInputStream()     // Catch:{ all -> 0x0049 }
            r6.onStreamReady(r0)     // Catch:{ all -> 0x0049 }
            if (r5 == 0) goto L_0x0048
            r5.disconnect()
        L_0x0048:
            return
        L_0x0049:
            r6 = move-exception
            goto L_0x004d
        L_0x004b:
            r6 = move-exception
            r5 = r0
        L_0x004d:
            if (r5 == 0) goto L_0x0052
            r5.disconnect()
        L_0x0052:
            throw r6
        L_0x0053:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "must provide a listener to process the InputStream"
            r5.<init>(r6)
            throw r5
        L_0x005b:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "must provide a valid Uri"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.captioning.tasks.LoadCaptionsTask.doGetRequestAsStream(android.net.Uri, com.brightcove.player.captioning.tasks.LoadCaptionsTask$ResponseStreamListener):void");
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        HashMap hashMap = new HashMap();
        if (this.captionType == CaptionType.TTML) {
            TTMLDocument tTMLDocument = this.currentTTMLDocument;
            if (tTMLDocument != null) {
                hashMap.put(AbstractEvent.TTML_DOCUMENT, tTMLDocument);
            }
        } else if (this.captionType == CaptionType.WEBVTT) {
            WebVTTDocument webVTTDocument = this.currentWebVTTDocument;
            if (webVTTDocument != null) {
                hashMap.put(AbstractEvent.WEBVTT_DOCUMENT, webVTTDocument);
            }
        }
        this.eventEmitter.emit(EventType.DID_LOAD_CLOSED_CAPTIONS, hashMap);
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        String str = this.lastError;
        if (str != null) {
            emitErrorMessage(str);
        }
    }

    private void emitErrorMessage(String str) {
        Log.e(TAG, str, this.lastException);
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.ERROR_MESSAGE, str);
        Exception exc = this.lastException;
        if (exc != null) {
            hashMap.put("error", exc);
        }
        this.eventEmitter.emit(EventType.CLOSED_CAPTIONING_ERROR, hashMap);
    }
}
