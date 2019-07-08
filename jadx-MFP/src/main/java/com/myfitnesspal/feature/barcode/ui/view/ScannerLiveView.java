package com.myfitnesspal.feature.barcode.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.PointF;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.Ln;
import com.visionsmarts.VSBarcodeReader;
import com.visionsmarts.VSBarcodeReader.DecoderValues;
import java.util.List;
import java.util.regex.Pattern;

public class ScannerLiveView extends SurfaceView implements Callback, LiveView {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final int DESIRED_ZOOM_X10 = 27;
    public static final boolean OMNIDIRECTIONAL_BARCODE_DECODING = false;
    private static final int START_CAMERA_DEBOUNCE_MILLIS = 200;
    private boolean isEnabled;
    private int lastMeasureHeight = -1;
    private int lastMeasureWidth = -1;
    private MfpActivity mActivity;
    private AutoFocusCallback mAutoFocusCallback;
    /* access modifiers changed from: private */
    public volatile byte[] mCallbackBuffer;
    /* access modifiers changed from: private */
    public volatile Camera mCamera;
    private volatile int mCameraId;
    /* access modifiers changed from: private */
    public DecoderValues mDecoderValues = new DecoderValues();
    /* access modifiers changed from: private */
    public DecodingThread mDecodingThread;
    /* access modifiers changed from: private */
    public volatile boolean mFoundBarcode = false;
    /* access modifiers changed from: private */
    public volatile byte[] mFrameData = null;
    /* access modifiers changed from: private */
    public volatile boolean mFrameDataIsAutoFocusInProgress = false;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public volatile boolean mHasCameraAutoFocus;
    private SurfaceHolder mHolder;
    /* access modifiers changed from: private */
    public volatile boolean mIsAutoFocusInProgress = false;
    private volatile boolean mIsFrontFacingCamera;
    /* access modifiers changed from: private */
    public volatile boolean mIsPreviewStarted = false;
    /* access modifiers changed from: private */
    public byte[] mLandscapePreviewLine;
    private PointF mLineEnd = new PointF();
    private PointF mLineStart = new PointF();
    /* access modifiers changed from: private */
    public int mNumberConsecutiveFailedDecodingSinceFocusEnded = 0;
    /* access modifiers changed from: private */
    public OnBarcodeScannedListener mOnScannedListener;
    /* access modifiers changed from: private */
    public byte[] mPortraitPreviewLine;
    private PreviewCallback mPreviewCallback;
    /* access modifiers changed from: private */
    public volatile android.hardware.Camera.Size mPreviewSize;
    /* access modifiers changed from: private */
    public final ScannerHandler mScannerHandler = new ScannerHandler();
    /* access modifiers changed from: private */
    public ScannerOverlay mScannerOverlay;
    private int parentHeight = -1;
    private int parentWidth = -1;
    private Debouncer<Size> startCameraDebouncer = new Debouncer<Size>(200) {
        /* access modifiers changed from: protected */
        public void onDebounced(Size size) {
            try {
                ScannerLiveView.this.stopCameraPreview();
                ScannerLiveView.this.setCameraParameters(size.width, size.height);
                ScannerLiveView.this.startCameraPreview();
                ScannerLiveView.this.requestLayout();
            } catch (Exception e) {
                ScannerLiveView.this.mPreviewSize = null;
                Ln.e(e);
            }
        }
    };

    private class DecodingThread extends Thread {
        public DecodingThread() {
            setPriority(10);
            initialize();
        }

        /* access modifiers changed from: 0000 */
        public synchronized Handler getHandler() {
            if (ScannerLiveView.this.mHandler == null) {
                Ln.d("getHandler(): mHandler is null, wait up to 1 second to let thread set it", new Object[0]);
                try {
                    ScannerLiveView.this.mDecodingThread.wait(1000);
                } catch (InterruptedException e) {
                    Ln.e(e);
                }
                if (ScannerLiveView.this.mHandler == null) {
                    Ln.d("getHandler(): mHandler is still null", new Object[0]);
                }
            }
            return ScannerLiveView.this.mHandler;
        }

        public void initialize() {
            if (ScannerLiveView.this.mPreviewSize != null) {
                if (ScannerLiveView.this.mPortraitPreviewLine == null || ScannerLiveView.this.mPortraitPreviewLine.length != ScannerLiveView.this.mPreviewSize.height) {
                    ScannerLiveView scannerLiveView = ScannerLiveView.this;
                    scannerLiveView.mPortraitPreviewLine = new byte[scannerLiveView.mPreviewSize.height];
                }
                if (ScannerLiveView.this.mLandscapePreviewLine == null || ScannerLiveView.this.mLandscapePreviewLine.length != ScannerLiveView.this.mPreviewSize.width) {
                    ScannerLiveView scannerLiveView2 = ScannerLiveView.this;
                    scannerLiveView2.mLandscapePreviewLine = new byte[scannerLiveView2.mPreviewSize.width];
                }
            }
            ScannerLiveView.this.mNumberConsecutiveFailedDecodingSinceFocusEnded = 0;
            ScannerLiveView.this.mFoundBarcode = false;
            VSBarcodeReader.reset();
        }

        public void run() {
            Thread.currentThread().setName("ScannerLiveView.DecodingThread");
            Looper.prepare();
            synchronized (this) {
                ScannerLiveView.this.mHandler = new Handler() {
                    public void handleMessage(Message message) {
                        switch (message.what) {
                            case R.id.msg_decode /*2131363077*/:
                                if (ScannerLiveView.this.mIsPreviewStarted) {
                                    DecodingThread.this.decode();
                                    return;
                                }
                                return;
                            case R.id.msg_quit /*2131363078*/:
                                Looper.myLooper().quit();
                                return;
                            default:
                                return;
                        }
                    }
                };
                notify();
            }
            Looper.loop();
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
            com.visionsmarts.VSBarcodeReader.setBlurryAcceptanceThresholdWithAF(0.3d);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x005e, code lost:
            if (r0 == false) goto L_0x007d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r0 = java.lang.Math.round(com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1800(r10.this$0).getNormalizedLaserLinePosition() * ((float) r4));
            r5 = r3.length - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
            if (r5 < 0) goto L_0x009e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0075, code lost:
            r3[r5] = r6[r0];
            r0 = r0 + r4;
            r5 = r5 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x007d, code lost:
            java.lang.System.arraycopy(r6, java.lang.Math.round(com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1800(r10.this$0).getNormalizedLaserLinePosition() * ((float) r5)) * r4, r3, 0, r3.length);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0095, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0096, code lost:
            com.uacf.core.util.Ln.e(r0);
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1900(r10.this$0, 2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c7, code lost:
            r0 = com.visionsmarts.VSBarcodeReader.decodeNextImage(r3, com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2200(r10.this$0) ? 1 : 0, 7, com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0));
            r3 = (float) r3.length;
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1800(r10.this$0).setNormalizedBarcodeLocation(((float) com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).left) / r3, ((float) com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).right) / r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ff, code lost:
            if (com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).evaluationDays != 0) goto L_0x0109;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0101, code lost:
            com.uacf.core.util.Ln.d("Evaluation license has expired.", new java.lang.Object[0]);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0111, code lost:
            if (com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).evaluationDays <= 0) goto L_0x0136;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0113, code lost:
            r3 = new java.lang.StringBuilder();
            r3.append("Evaluation license will expire in ");
            r3.append(com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).evaluationDays);
            r3.append(" days.");
            com.uacf.core.util.Ln.d(r3.toString(), new java.lang.Object[0]);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x013e, code lost:
            if (com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).left >= 0) goto L_0x014b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0140, code lost:
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1800(r10.this$0).setBarcode("");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x014f, code lost:
            if (r0.length() <= 0) goto L_0x01b9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0151, code lost:
            r3 = new java.lang.StringBuilder();
            r3.append("decoded barcode: ");
            r3.append(r0);
            r3.append(" of type: ");
            r3.append(com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).type);
            com.uacf.core.util.Ln.d(r3.toString(), new java.lang.Object[0]);
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$402(r10.this$0, true);
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1602(r10.this$0, 0);
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1800(r10.this$0).setBarcode(com.visionsmarts.VSBarcodeReader.format(r0, com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).type));
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2400(r10.this$0).obtainMessage(com.myfitnesspal.android.R.id.msg_barcode_found, new java.lang.Object[]{r0, java.lang.Integer.valueOf(com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2300(r10.this$0).type)}).sendToTarget();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x01b9, code lost:
            if (r7 == false) goto L_0x01c1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x01bb, code lost:
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1602(r10.this$0, 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x01c1, code lost:
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1608(r10.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x01cc, code lost:
            if (com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2200(r10.this$0) == false) goto L_0x01ef;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x01d4, code lost:
            if (com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$100(r10.this$0) != false) goto L_0x01ef;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x01dd, code lost:
            if (com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1600(r10.this$0) < 4) goto L_0x01ef;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x01df, code lost:
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$2400(r10.this$0).obtainMessage(com.myfitnesspal.android.R.id.msg_auto_focus).sendToTarget();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x01ef, code lost:
            return;
         */
        @android.annotation.SuppressLint({"NewApi"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void decode() {
            /*
                r10 = this;
                java.lang.String r0 = "decode()"
                r1 = 0
                java.lang.Object[] r2 = new java.lang.Object[r1]
                com.uacf.core.util.Ln.d(r0, r2)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                android.content.Context r0 = r0.getContext()
                android.content.res.Resources r0 = r0.getResources()
                android.content.res.Configuration r0 = r0.getConfiguration()
                int r0 = r0.orientation
                r2 = 1
                if (r0 != r2) goto L_0x001d
                r0 = 1
                goto L_0x001e
            L_0x001d:
                r0 = 0
            L_0x001e:
                if (r0 == 0) goto L_0x0027
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                byte[] r3 = r3.mPortraitPreviewLine
                goto L_0x002d
            L_0x0027:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                byte[] r3 = r3.mLandscapePreviewLine
            L_0x002d:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r4 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                android.hardware.Camera$Size r4 = r4.mPreviewSize
                int r4 = r4.width
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r5 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                android.hardware.Camera$Size r5 = r5.mPreviewSize
                int r5 = r5.height
                monitor-enter(r10)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r6 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f3 }
                byte[] r6 = r6.mFrameData     // Catch:{ all -> 0x01f3 }
                if (r6 != 0) goto L_0x0048
                monitor-exit(r10)     // Catch:{ all -> 0x01f3 }
                return
            L_0x0048:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r6 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f3 }
                byte[] r6 = r6.mFrameData     // Catch:{ all -> 0x01f3 }
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r7 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f3 }
                boolean r7 = r7.mFrameDataIsAutoFocusInProgress     // Catch:{ all -> 0x01f3 }
                monitor-exit(r10)     // Catch:{ all -> 0x01f3 }
                r8 = 4599075939470750515(0x3fd3333333333333, double:0.3)
                com.visionsmarts.VSBarcodeReader.setBlurryAcceptanceThresholdWithAF(r8)
                r8 = 2
                if (r0 == 0) goto L_0x007d
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                com.myfitnesspal.feature.barcode.ui.view.ScannerOverlay r0 = r0.mScannerOverlay     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                float r0 = r0.getNormalizedLaserLinePosition()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                float r5 = (float) r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                float r0 = r0 * r5
                int r0 = java.lang.Math.round(r0)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                int r5 = r3.length     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                int r5 = r5 - r2
            L_0x0073:
                if (r5 < 0) goto L_0x009e
                byte r9 = r6[r0]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                r3[r5] = r9     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                int r0 = r0 + r4
                int r5 = r5 + -1
                goto L_0x0073
            L_0x007d:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                com.myfitnesspal.feature.barcode.ui.view.ScannerOverlay r0 = r0.mScannerOverlay     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                float r0 = r0.getNormalizedLaserLinePosition()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                float r5 = (float) r5     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                float r0 = r0 * r5
                int r0 = java.lang.Math.round(r0)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                int r0 = r0 * r4
                int r4 = r3.length     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                java.lang.System.arraycopy(r6, r0, r3, r1, r4)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0095 }
                goto L_0x009e
            L_0x0095:
                r0 = move-exception
                com.uacf.core.util.Ln.e(r0)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                r0.raiseError(r8)
            L_0x009e:
                monitor-enter(r10)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f0 }
                byte[] r0 = r0.mFrameData     // Catch:{ all -> 0x01f0 }
                if (r0 != 0) goto L_0x00a9
                monitor-exit(r10)     // Catch:{ all -> 0x01f0 }
                return
            L_0x00a9:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f0 }
                r4 = 0
                r0.mFrameData = r4     // Catch:{ all -> 0x01f0 }
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f0 }
                byte[] r0 = r0.mCallbackBuffer     // Catch:{ all -> 0x01f0 }
                if (r0 == 0) goto L_0x00c6
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f0 }
                android.hardware.Camera r0 = r0.mCamera     // Catch:{ all -> 0x01f0 }
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r4 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this     // Catch:{ all -> 0x01f0 }
                byte[] r4 = r4.mCallbackBuffer     // Catch:{ all -> 0x01f0 }
                r0.addCallbackBuffer(r4)     // Catch:{ all -> 0x01f0 }
            L_0x00c6:
                monitor-exit(r10)     // Catch:{ all -> 0x01f0 }
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                boolean r0 = r0.mHasCameraAutoFocus
                r4 = 7
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r5 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r5 = r5.mDecoderValues
                java.lang.String r0 = com.visionsmarts.VSBarcodeReader.decodeNextImage(r3, r0, r4, r5)
                int r3 = r3.length
                float r3 = (float) r3
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r4 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.myfitnesspal.feature.barcode.ui.view.ScannerOverlay r4 = r4.mScannerOverlay
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r5 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r5 = r5.mDecoderValues
                int r5 = r5.left
                float r5 = (float) r5
                float r5 = r5 / r3
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r6 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r6 = r6.mDecoderValues
                int r6 = r6.right
                float r6 = (float) r6
                float r6 = r6 / r3
                r4.setNormalizedBarcodeLocation(r5, r6)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r3 = r3.mDecoderValues
                int r3 = r3.evaluationDays
                if (r3 != 0) goto L_0x0109
                java.lang.String r3 = "Evaluation license has expired."
                java.lang.Object[] r4 = new java.lang.Object[r1]
                com.uacf.core.util.Ln.d(r3, r4)
                goto L_0x0136
            L_0x0109:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r3 = r3.mDecoderValues
                int r3 = r3.evaluationDays
                if (r3 <= 0) goto L_0x0136
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Evaluation license will expire in "
                r3.append(r4)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r4 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r4 = r4.mDecoderValues
                int r4 = r4.evaluationDays
                r3.append(r4)
                java.lang.String r4 = " days."
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                java.lang.Object[] r4 = new java.lang.Object[r1]
                com.uacf.core.util.Ln.d(r3, r4)
            L_0x0136:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r3 = r3.mDecoderValues
                int r3 = r3.left
                if (r3 >= 0) goto L_0x014b
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.myfitnesspal.feature.barcode.ui.view.ScannerOverlay r3 = r3.mScannerOverlay
                java.lang.String r4 = ""
                r3.setBarcode(r4)
            L_0x014b:
                int r3 = r0.length()
                if (r3 <= 0) goto L_0x01b9
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "decoded barcode: "
                r3.append(r4)
                r3.append(r0)
                java.lang.String r4 = " of type: "
                r3.append(r4)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r4 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r4 = r4.mDecoderValues
                int r4 = r4.type
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                java.lang.Object[] r4 = new java.lang.Object[r1]
                com.uacf.core.util.Ln.d(r3, r4)
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                r3.mFoundBarcode = r2
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                r3.mNumberConsecutiveFailedDecodingSinceFocusEnded = r1
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r3 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.myfitnesspal.feature.barcode.ui.view.ScannerOverlay r3 = r3.mScannerOverlay
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r4 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r4 = r4.mDecoderValues
                int r4 = r4.type
                java.lang.String r4 = com.visionsmarts.VSBarcodeReader.format(r0, r4)
                r3.setBarcode(r4)
                java.lang.Object[] r3 = new java.lang.Object[r8]
                r3[r1] = r0
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.visionsmarts.VSBarcodeReader$DecoderValues r0 = r0.mDecoderValues
                int r0 = r0.type
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                r3[r2] = r0
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView$ScannerHandler r0 = r0.mScannerHandler
                r1 = 2131363076(0x7f0a0504, float:1.834595E38)
                android.os.Message r0 = r0.obtainMessage(r1, r3)
                r0.sendToTarget()
                goto L_0x01ef
            L_0x01b9:
                if (r7 == 0) goto L_0x01c1
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                r0.mNumberConsecutiveFailedDecodingSinceFocusEnded = r1
                goto L_0x01ef
            L_0x01c1:
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                
                // error: 0x01c3: INVOKE  (r0 I:com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView) com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.access$1608(com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView):int type: STATIC
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                boolean r0 = r0.mHasCameraAutoFocus
                if (r0 == 0) goto L_0x01ef
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                boolean r0 = r0.mIsAutoFocusInProgress
                if (r0 != 0) goto L_0x01ef
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                int r0 = r0.mNumberConsecutiveFailedDecodingSinceFocusEnded
                r1 = 4
                if (r0 < r1) goto L_0x01ef
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView r0 = com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.this
                com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView$ScannerHandler r0 = r0.mScannerHandler
                r1 = 2131363075(0x7f0a0503, float:1.8345949E38)
                android.os.Message r0 = r0.obtainMessage(r1)
                r0.sendToTarget()
            L_0x01ef:
                return
            L_0x01f0:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x01f0 }
                throw r0
            L_0x01f3:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x01f3 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.DecodingThread.decode():void");
        }
    }

    public interface Error {
        public static final int COULD_NOT_ACQUIRE_CAMERA = 0;
        public static final int COULD_NOT_START_CAMERA_PREVIEW = 1;
        public static final int DECODE_FAILED = 2;
    }

    private class ScannerHandler extends Handler {
        private ScannerHandler() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case R.id.msg_auto_focus /*2131363075*/:
                    ScannerLiveView.this.requestAutoFocus();
                    return;
                case R.id.msg_barcode_found /*2131363076*/:
                    if (ScannerLiveView.this.mIsPreviewStarted && ScannerLiveView.this.mOnScannedListener != null) {
                        Object[] objArr = (Object[]) message.obj;
                        ScannerLiveView.this.mOnScannedListener.onBarcodeScanCompleted((String) objArr[0], ((Integer) objArr[1]).intValue());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private static class Size {
        int height;
        int width;

        public Size(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public ScannerLiveView(Context context) {
        super(context);
        initialize(context);
    }

    public ScannerLiveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context);
    }

    public ScannerLiveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context);
    }

    private void initialize(Context context) {
        Ln.d("initialize()", new Object[0]);
        this.mActivity = (MfpActivity) context;
        this.mHolder = getHolder();
        this.mHolder.addCallback(this);
        this.mHolder.setType(3);
        this.mHolder.setKeepScreenOn(true);
        this.mAutoFocusCallback = new AutoFocusCallback() {
            public void onAutoFocus(boolean z, Camera camera) {
                ScannerLiveView.this.mIsAutoFocusInProgress = false;
            }
        };
        this.mPreviewCallback = new PreviewCallback() {
            public synchronized void onPreviewFrame(byte[] bArr, Camera camera) {
                boolean z = false;
                Ln.d("onPreviewFrame()", new Object[0]);
                if (ScannerLiveView.this.mIsPreviewStarted && ScannerLiveView.this.mDecodingThread != null && !ScannerLiveView.this.mFoundBarcode) {
                    if (ScannerLiveView.this.mFrameData == null) {
                        z = true;
                    }
                    ScannerLiveView.this.mFrameData = bArr;
                    ScannerLiveView.this.mFrameDataIsAutoFocusInProgress = ScannerLiveView.this.mIsAutoFocusInProgress;
                    if (z) {
                        ScannerLiveView.this.mDecodingThread.getHandler().obtainMessage(R.id.msg_decode).sendToTarget();
                    }
                }
            }
        };
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.mCamera != null) {
            stopCameraPreview();
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.mCamera != null) {
            requestLayout();
        }
    }

    public void setVisible() {
        setVisibility(0);
    }

    /* access modifiers changed from: 0000 */
    public void onContainerSizeChanged(int i, int i2) {
        this.parentHeight = i2;
        this.parentWidth = i;
        if (this.mPreviewSize == null || this.parentWidth != this.lastMeasureWidth || this.parentHeight != this.lastMeasureHeight) {
            this.startCameraDebouncer.call(new Size(this.parentWidth, this.parentHeight));
            this.lastMeasureHeight = this.parentHeight;
            this.lastMeasureWidth = this.parentWidth;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.parentHeight <= 0 || this.parentWidth <= 0 || this.mCamera == null || this.mPreviewSize == null) {
            super.onMeasure(i, i2);
            return;
        }
        boolean z = this.parentHeight > this.parentWidth;
        double d = (double) (z ? this.mPreviewSize.height : this.mPreviewSize.width);
        double d2 = (double) (z ? this.mPreviewSize.width : this.mPreviewSize.height);
        double max = Math.max(((double) this.parentWidth) / d, ((double) this.parentHeight) / d2);
        setMeasuredDimension((int) (d * max), (int) (d2 * max));
    }

    private synchronized void startDecodingThread() {
        if (this.mDecodingThread == null) {
            this.mDecodingThread = new DecodingThread();
            this.mDecodingThread.start();
        }
    }

    private synchronized void stopDecodingThread() {
        if (this.mDecodingThread != null) {
            Message.obtain(this.mDecodingThread.getHandler(), R.id.msg_quit).sendToTarget();
            try {
                this.mDecodingThread.join();
            } catch (InterruptedException e) {
                Ln.e(e);
            }
            this.mDecodingThread.getHandler().removeMessages(R.id.msg_decode);
            this.mDecodingThread = null;
        }
    }

    @SuppressLint({"NewApi"})
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.isEnabled) {
            initCamera();
            startCameraPreview();
        }
    }

    public void onStart() {
        startCameraPreview();
    }

    public void onStop() {
        stopCameraPreview();
    }

    public void isCameraEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void initCamera() {
        SurfaceHolder holder = getHolder();
        if (holder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (this.mCamera == null && this.isEnabled) {
            initCameraHardware(holder);
        }
    }

    private void initCameraHardware(SurfaceHolder surfaceHolder) {
        try {
            this.mCamera = null;
            this.mIsFrontFacingCamera = false;
            try {
                CameraInfo cameraInfo = new CameraInfo();
                int numberOfCameras = Camera.getNumberOfCameras();
                int i = 0;
                while (true) {
                    if (i >= numberOfCameras) {
                        break;
                    }
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == 0) {
                        this.mCamera = Camera.open(i);
                        this.mCameraId = i;
                        break;
                    }
                    i++;
                }
            } catch (LinkageError e) {
                Ln.e(e);
            }
            if (this.mCamera == null) {
                try {
                    this.mCamera = Camera.open(0);
                    this.mIsFrontFacingCamera = true;
                } catch (LinkageError e2) {
                    Ln.e(e2);
                }
            }
            if (this.mCamera == null) {
                this.mCamera = Camera.open();
            }
            this.mCamera.setPreviewDisplay(surfaceHolder);
            this.mIsPreviewStarted = false;
        } catch (Exception unused) {
            raiseError(0);
        }
        try {
            this.mCamera.setPreviewDisplay(surfaceHolder);
            this.mIsPreviewStarted = false;
        } catch (Exception e3) {
            StringBuilder sb = new StringBuilder();
            sb.append("exception while setting preview camera preview display: ");
            sb.append(e3.getMessage());
            Ln.d(sb.toString(), new Object[0]);
            raiseError(1);
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void startCameraPreview() {
        try {
            Ln.d("startCamera()", new Object[0]);
            this.mScannerOverlay = (ScannerOverlay) this.mActivity.findViewById(R.id.scanner_overlay);
            this.mScannerOverlay.setNoBarcodeLocation();
            if (this.mCamera != null && !this.mIsPreviewStarted) {
                this.mIsAutoFocusInProgress = false;
                this.mFrameData = null;
                if (this.mDecodingThread != null) {
                    this.mDecodingThread.initialize();
                }
                if (this.mScannerOverlay != null) {
                    this.mScannerOverlay.setNoBarcodeLocation();
                }
                if (this.mCallbackBuffer != null) {
                    this.mCamera.setPreviewCallbackWithBuffer(this.mPreviewCallback);
                    this.mCamera.addCallbackBuffer(this.mCallbackBuffer);
                } else {
                    this.mCamera.setPreviewCallback(this.mPreviewCallback);
                }
                setCameraDisplayOrientation((Activity) getContext(), this.mCameraId, this.mCamera);
                this.mCamera.startPreview();
                this.mIsPreviewStarted = true;
                if (this.mIsPreviewStarted && this.mPreviewSize != null) {
                    startDecodingThread();
                }
                requestAutoFocus();
            }
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("startCamera() failed with error message : ");
            sb.append(e.getMessage());
            Ln.w(sb.toString(), new Object[0]);
            ((Activity) getContext()).finish();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:5|6|7|8|9|10|11) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stopCameraPreview() {
        /*
            r2 = this;
            android.hardware.Camera r0 = r2.mCamera
            if (r0 == 0) goto L_0x0035
            boolean r0 = r2.mIsPreviewStarted
            if (r0 == 0) goto L_0x0035
            monitor-enter(r2)
            android.hardware.Camera r0 = r2.mCamera     // Catch:{ all -> 0x0032 }
            r1 = 0
            r0.setPreviewCallback(r1)     // Catch:{ all -> 0x0032 }
            android.hardware.Camera r0 = r2.mCamera     // Catch:{ all -> 0x0032 }
            r0.stopPreview()     // Catch:{ all -> 0x0032 }
            r0 = 0
            r2.mIsPreviewStarted = r0     // Catch:{ all -> 0x0032 }
            android.hardware.Camera r0 = r2.mCamera     // Catch:{ Exception -> 0x001c }
            r0.cancelAutoFocus()     // Catch:{ Exception -> 0x001c }
        L_0x001c:
            r2.mFrameData = r1     // Catch:{ all -> 0x0032 }
            r2.mCallbackBuffer = r1     // Catch:{ all -> 0x0032 }
            monitor-exit(r2)     // Catch:{ all -> 0x0032 }
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView$ScannerHandler r0 = r2.mScannerHandler
            r1 = 2131363075(0x7f0a0503, float:1.8345949E38)
            r0.removeMessages(r1)
            com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView$ScannerHandler r0 = r2.mScannerHandler
            r1 = 2131363076(0x7f0a0504, float:1.834595E38)
            r0.removeMessages(r1)
            goto L_0x0035
        L_0x0032:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0032 }
            throw r0
        L_0x0035:
            r2.stopDecodingThread()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.barcode.ui.view.ScannerLiveView.stopCameraPreview():void");
    }

    private static void setCameraDisplayOrientation(Activity activity, int i, Camera camera) {
        int i2;
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        int i3 = 0;
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i3 = 90;
                break;
            case 2:
                i3 = 180;
                break;
            case 3:
                i3 = 270;
                break;
        }
        if (cameraInfo.facing == 1) {
            i2 = (360 - ((cameraInfo.orientation + i3) % 360)) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i3) + 360) % 360;
        }
        camera.setDisplayOrientation(i2);
    }

    public void setOnBarcodeScannedListener(OnBarcodeScannedListener onBarcodeScannedListener) {
        this.mOnScannedListener = onBarcodeScannedListener;
    }

    /* access modifiers changed from: private */
    public void raiseError(int i) {
        OnBarcodeScannedListener onBarcodeScannedListener = this.mOnScannedListener;
        if (onBarcodeScannedListener != null) {
            onBarcodeScannedListener.onBarcodeScanFailed(i);
        }
    }

    /* access modifiers changed from: private */
    public void requestAutoFocus() {
        if (this.mCamera != null && this.mHasCameraAutoFocus && this.mIsPreviewStarted && !this.mIsAutoFocusInProgress) {
            try {
                this.mCamera.autoFocus(this.mAutoFocusCallback);
                this.mIsAutoFocusInProgress = true;
            } catch (RuntimeException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Camera auto-focus failed: ");
                sb.append(e.getLocalizedMessage());
                Ln.e(sb.toString(), new Object[0]);
            }
        }
    }

    private void setAutoFocusCapability(Parameters parameters) {
        int i = VERSION.SDK_INT;
        if (i <= 3) {
            this.mHasCameraAutoFocus = true;
        } else if (i >= 5) {
            String str = parameters.get("focus-mode-values");
            if (str != null && str.contains("macro")) {
                parameters.set("focus-mode", "macro");
                this.mHasCameraAutoFocus = true;
            } else if (str == null || !str.contains("auto")) {
                this.mHasCameraAutoFocus = false;
            } else {
                parameters.set("focus-mode", "auto");
                this.mHasCameraAutoFocus = true;
            }
        } else {
            String lowerCase = Build.MODEL.toLowerCase();
            if (lowerCase.contains("devour") || lowerCase.contains("tattoo")) {
                this.mHasCameraAutoFocus = false;
            } else {
                this.mHasCameraAutoFocus = true;
            }
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void setCameraParameters(int i, int i2) {
        if (this.mCamera != null) {
            Parameters parameters = this.mCamera.getParameters();
            setOptimalPreviewSize(i, i2, parameters);
            setZoom(parameters);
            setAutoFocusCapability(parameters);
            this.mCamera.setParameters(parameters);
            this.mPreviewSize = parameters.getPreviewSize();
            try {
                this.mCallbackBuffer = new byte[(((this.mPreviewSize.width * this.mPreviewSize.height) * ImageFormat.getBitsPerPixel(parameters.getPreviewFormat())) / 8)];
            } catch (OutOfMemoryError unused) {
                this.mCallbackBuffer = null;
            }
        }
    }

    private void setOptimalPreviewSize(int i, int i2, Parameters parameters) {
        android.hardware.Camera.Size findBestPreviewSize = findBestPreviewSize(parameters.getSupportedPreviewSizes(), i, i2);
        if (findBestPreviewSize != null) {
            parameters.setPreviewSize(findBestPreviewSize.width, findBestPreviewSize.height);
        }
    }

    private android.hardware.Camera.Size findBestPreviewSize(List<android.hardware.Camera.Size> list, int i, int i2) {
        double d;
        double d2;
        int i3 = i;
        int i4 = i2;
        android.hardware.Camera.Size size = null;
        if (list == null) {
            return null;
        }
        boolean z = true;
        if (getContext().getResources().getConfiguration().orientation != 1) {
            z = false;
        }
        int i5 = z ? i4 : i3;
        if (z) {
            d = (double) i4;
            d2 = (double) i3;
        } else {
            d = (double) i3;
            d2 = (double) i4;
        }
        double d3 = d / d2;
        double d4 = Double.MAX_VALUE;
        double d5 = Double.MAX_VALUE;
        for (android.hardware.Camera.Size size2 : list) {
            if (Math.abs((((double) size2.width) / ((double) size2.height)) - d3) <= 0.1d && ((double) Math.abs(size2.height - i5)) < d5) {
                d5 = (double) Math.abs(size2.height - i5);
                size = size2;
            }
        }
        if (size == null) {
            for (android.hardware.Camera.Size size3 : list) {
                if (((double) Math.abs(size3.height - i5)) < d4) {
                    size = size3;
                    d4 = (double) Math.abs(size3.height - i5);
                }
            }
        }
        return size;
    }

    private void setZoom(Parameters parameters) {
        String str = parameters.get("zoom-supported");
        if (str == null || Boolean.parseBoolean(str)) {
            int i = 27;
            String str2 = parameters.get("max-zoom");
            if (str2 != null) {
                try {
                    int parseDouble = (int) (Double.parseDouble(str2) * 10.0d);
                    if (27 > parseDouble) {
                        i = parseDouble;
                    }
                } catch (NumberFormatException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Bad max-zoom: ");
                    sb.append(str2);
                    Ln.w(sb.toString(), new Object[0]);
                }
            }
            String str3 = parameters.get("taking-picture-zoom-max");
            if (str3 != null) {
                try {
                    int parseInt = Integer.parseInt(str3);
                    if (i > parseInt) {
                        i = parseInt;
                    }
                } catch (NumberFormatException unused2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Bad taking-picture-zoom-max: ");
                    sb2.append(str3);
                    Ln.w(sb2.toString(), new Object[0]);
                }
            }
            String str4 = parameters.get("mot-zoom-values");
            if (str4 != null) {
                i = findBestMotZoomX10(str4, i);
            }
            String str5 = parameters.get("mot-zoom-step");
            if (str5 != null) {
                try {
                    int parseDouble2 = (int) (Double.parseDouble(str5.trim()) * 10.0d);
                    if (parseDouble2 > 1) {
                        i -= i % parseDouble2;
                    }
                } catch (NumberFormatException unused3) {
                }
            }
            if (!(str2 == null && str4 == null)) {
                parameters.set("zoom", String.valueOf(((double) i) / 10.0d));
            }
            if (str3 != null) {
                parameters.set("taking-picture-zoom", i);
            }
        }
    }

    private static int findBestMotZoomX10(CharSequence charSequence, int i) {
        String[] split = COMMA_PATTERN.split(charSequence);
        int length = split.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            try {
                int parseDouble = (int) (Double.parseDouble(split[i2].trim()) * 10.0d);
                if (Math.abs(i - parseDouble) < Math.abs(i - i3)) {
                    i3 = parseDouble;
                }
                i2++;
            } catch (NumberFormatException unused) {
                return i;
            }
        }
        return i3;
    }
}
