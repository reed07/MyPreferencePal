package com.google.android.exoplayer2.offline;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.offline.DownloadHelper.Callback;
import com.google.android.exoplayer2.source.TrackGroupArray;
import java.io.IOException;
import java.util.List;

public abstract class DownloadHelper {

    public interface Callback {
        void onPrepareError(DownloadHelper downloadHelper, IOException iOException);

        void onPrepared(DownloadHelper downloadHelper);
    }

    public abstract DownloadAction getDownloadAction(@Nullable byte[] bArr, List<TrackKey> list);

    public abstract int getPeriodCount();

    public abstract DownloadAction getRemoveAction(@Nullable byte[] bArr);

    public abstract TrackGroupArray getTrackGroups(int i);

    /* access modifiers changed from: protected */
    public abstract void prepareInternal() throws IOException;

    public void prepare(final Callback callback) {
        final Handler handler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        new Thread() {
            public void run() {
                try {
                    DownloadHelper.this.prepareInternal();
                    handler.post(new Runnable(callback) {
                        private final /* synthetic */ Callback f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            this.f$1.onPrepared(DownloadHelper.this);
                        }
                    });
                } catch (IOException e) {
                    handler.post(new Runnable(callback, e) {
                        private final /* synthetic */ Callback f$1;
                        private final /* synthetic */ IOException f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            this.f$1.onPrepareError(DownloadHelper.this, this.f$2);
                        }
                    });
                }
            }
        }.start();
    }
}
