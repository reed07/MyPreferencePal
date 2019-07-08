package com.brightcove.player.display.tasks;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.event.RegisteringEventEmitter;
import com.brightcove.player.network.HttpService;
import com.brightcove.player.util.ErrorUtil;
import java.lang.ref.WeakReference;
import java.net.URI;

@ListensFor(events = {})
@Emits(events = {"didSetVideoStill"})
public class LoadImageTask extends AsyncTask<URI, Void, Bitmap> implements Component {
    public static final String TAG = "LoadImageTask";
    private EventEmitter eventEmitter;
    private HttpService httpService;
    private int imageNotLoadedResourceId = 17301532;
    private final WeakReference<ImageView> imageViewReference;
    private String successEventType;

    public LoadImageTask(ImageView imageView, EventEmitter eventEmitter2) {
        if (imageView == null || eventEmitter2 == null) {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.IMAGE_VIEW_AND_EVENT_EMITTER_REQUIRED));
        }
        this.eventEmitter = RegisteringEventEmitter.build(eventEmitter2, LoadImageTask.class);
        this.imageViewReference = new WeakReference<>(imageView);
        this.httpService = new HttpService();
    }

    public String getSuccessEventType() {
        return this.successEventType;
    }

    public void setSuccessEventType(String str) {
        this.successEventType = str;
    }

    /* access modifiers changed from: protected */
    public Bitmap doInBackground(URI... uriArr) {
        if (uriArr == null || uriArr.length == 0 || uriArr[0] == null) {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.URL_REQUIRED));
        }
        try {
            URI uri = uriArr[0];
            Point displaySize = getDisplaySize();
            if (displaySize != null) {
                return this.httpService.doImageGetRequest(uri, displaySize.x, displaySize.y);
            }
            return this.httpService.doImageGetRequest(uri);
        } catch (Exception e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("error encountered in loading image: ");
            sb.append(uriArr[0]);
            Log.e(str, sb.toString(), e);
            return null;
        }
    }

    @SuppressLint({"NewApi"})
    private Display getDisplay() {
        ImageView imageView = (ImageView) this.imageViewReference.get();
        if (imageView == null) {
            return null;
        }
        if (VERSION.SDK_INT >= 17) {
            return imageView.getDisplay();
        }
        return ((WindowManager) imageView.getContext().getSystemService("window")).getDefaultDisplay();
    }

    @SuppressLint({"NewApi"})
    private Point getDisplaySize() {
        if (VERSION.SDK_INT >= 13) {
            Display display = getDisplay();
            if (display != null) {
                Point point = new Point();
                display.getSize(point);
                return point;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 12 && bitmap != null) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onPostExecute: byte count = ");
            sb.append(getByteCount(bitmap));
            Log.v(str, sb.toString());
        }
        WeakReference<ImageView> weakReference = this.imageViewReference;
        if (weakReference != null) {
            ImageView imageView = (ImageView) weakReference.get();
            if (imageView == null) {
                return;
            }
            if (bitmap != null) {
                Drawable drawable = imageView.getDrawable();
                if (drawable != null && (drawable instanceof BitmapDrawable)) {
                    Bitmap bitmap2 = ((BitmapDrawable) drawable).getBitmap();
                    if (bitmap2 != null) {
                        bitmap2.recycle();
                    }
                }
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(0);
                imageView.requestLayout();
                String str2 = this.successEventType;
                if (str2 != null) {
                    this.eventEmitter.emit(str2);
                    return;
                }
                return;
            }
            imageView.setImageResource(this.imageNotLoadedResourceId);
        }
    }

    @SuppressLint({"NewApi"})
    private int getByteCount(Bitmap bitmap) {
        try {
            return bitmap.getByteCount();
        } catch (Exception unused) {
            return -1;
        }
    }
}
