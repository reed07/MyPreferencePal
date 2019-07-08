package com.myfitnesspal.feature.barcode.ui.view;

public interface OnBarcodeScannedListener {
    void onBarcodeScanCompleted(String str, int i);

    void onBarcodeScanFailed(int i);
}
