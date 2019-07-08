package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.Map.Entry;

class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    public DefaultCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
    }

    public boolean invoke(CreateReportRequest createReportRequest) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest), createReportRequest.report);
        StringBuilder sb = new StringBuilder();
        sb.append("Sending report to: ");
        sb.append(getUrl());
        Fabric.getLogger().d("CrashlyticsCore", sb.toString());
        int code = applyMultipartDataTo.code();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Create report request ID: ");
        sb2.append(applyMultipartDataTo.header("X-REQUEST-ID"));
        Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Result was: ");
        sb3.append(code);
        Fabric.getLogger().d("CrashlyticsCore", sb3.toString());
        return ResponseParser.parse(code) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        HttpRequest header = httpRequest.header("X-CRASHLYTICS-API-KEY", createReportRequest.apiKey).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
        for (Entry header2 : createReportRequest.report.getCustomHeaders().entrySet()) {
            header = header.header(header2);
        }
        return header;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, Report report) {
        File[] files;
        httpRequest.part("report[identifier]", report.getIdentifier());
        if (report.getFiles().length == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Adding single file ");
            sb.append(report.getFileName());
            sb.append(" to report ");
            sb.append(report.getIdentifier());
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            return httpRequest.part("report[file]", report.getFileName(), "application/octet-stream", report.getFile());
        }
        int i = 0;
        for (File file : report.getFiles()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Adding file ");
            sb2.append(file.getName());
            sb2.append(" to report ");
            sb2.append(report.getIdentifier());
            Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("report[file");
            sb3.append(i);
            sb3.append("]");
            httpRequest.part(sb3.toString(), file.getName(), "application/octet-stream", file);
            i++;
        }
        return httpRequest;
    }
}
