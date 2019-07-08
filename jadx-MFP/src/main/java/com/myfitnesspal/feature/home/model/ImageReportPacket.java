package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;

public class ImageReportPacket {
    @Expose
    private ReportImageData item;

    public ImageReportPacket(ReportImageData reportImageData) {
        this.item = reportImageData;
    }

    public void setItem(ReportImageData reportImageData) {
        this.item = reportImageData;
    }

    public ReportImageData getItem() {
        return this.item;
    }
}
