package com.myfitnesspal.feature.barcode.ui.view;

public interface LiveView {
    void initCamera();

    void isCameraEnabled(boolean z);

    void onStart();

    void onStop();

    void setOnBarcodeScannedListener(OnBarcodeScannedListener onBarcodeScannedListener);

    void setVisible();
}
