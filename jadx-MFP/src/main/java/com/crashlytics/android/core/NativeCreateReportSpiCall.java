package com.crashlytics.android.core;

import com.myfitnesspal.shared.constants.SharedConstants.Http;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;

class NativeCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    public NativeCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
    }

    public boolean invoke(CreateReportRequest createReportRequest) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest.apiKey), createReportRequest.report);
        StringBuilder sb = new StringBuilder();
        sb.append("Sending report to: ");
        sb.append(getUrl());
        Fabric.getLogger().d("CrashlyticsCore", sb.toString());
        int code = applyMultipartDataTo.code();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Result was: ");
        sb2.append(code);
        Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
        return ResponseParser.parse(code) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Crashlytics Android SDK/");
        sb.append(this.kit.getVersion());
        httpRequest.header("User-Agent", sb.toString()).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion()).header("X-CRASHLYTICS-API-KEY", str);
        return httpRequest;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, Report report) {
        File[] files;
        httpRequest.part("report_id", report.getIdentifier());
        for (File file : report.getFiles()) {
            if (file.getName().equals("minidump")) {
                httpRequest.part("minidump_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals(TtmlNode.TAG_METADATA)) {
                httpRequest.part("crash_meta_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals("binaryImages")) {
                httpRequest.part("binary_images_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals("session")) {
                httpRequest.part("session_meta_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals("app")) {
                httpRequest.part("app_meta_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals("device")) {
                httpRequest.part("device_meta_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals(Http.OS)) {
                httpRequest.part("os_meta_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals("user")) {
                httpRequest.part("user_meta_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals("logs")) {
                httpRequest.part("logs_file", file.getName(), "application/octet-stream", file);
            } else if (file.getName().equals("keys")) {
                httpRequest.part("keys_file", file.getName(), "application/octet-stream", file);
            }
        }
        return httpRequest;
    }
}
