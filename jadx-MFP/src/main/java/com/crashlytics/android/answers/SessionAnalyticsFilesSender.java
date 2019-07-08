package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.List;

class SessionAnalyticsFilesSender extends AbstractSpiCall implements FilesSender {
    private final String apiKey;

    public SessionAnalyticsFilesSender(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, String str3) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
        this.apiKey = str3;
    }

    public boolean send(List<File> list) {
        HttpRequest header = getHttpRequest().header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion()).header("X-CRASHLYTICS-API-KEY", this.apiKey);
        int i = 0;
        for (File file : list) {
            StringBuilder sb = new StringBuilder();
            sb.append("session_analytics_file_");
            sb.append(i);
            header.part(sb.toString(), file.getName(), "application/vnd.crashlytics.android.events", file);
            i++;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Sending ");
        sb2.append(list.size());
        sb2.append(" analytics files to ");
        sb2.append(getUrl());
        Fabric.getLogger().d("Answers", sb2.toString());
        int code = header.code();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Response code for analytics file send is ");
        sb3.append(code);
        Fabric.getLogger().d("Answers", sb3.toString());
        if (ResponseParser.parse(code) == 0) {
            return true;
        }
        return false;
    }
}
