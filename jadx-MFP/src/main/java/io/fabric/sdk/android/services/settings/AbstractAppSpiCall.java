package io.fabric.sdk.android.services.settings;

import android.content.res.Resources.NotFoundException;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.shared.constants.HttpConstants;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitInfo;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.InputStream;
import java.util.Locale;

abstract class AbstractAppSpiCall extends AbstractSpiCall {
    public AbstractAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public boolean invoke(AppRequestData appRequestData) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), appRequestData), appRequestData);
        StringBuilder sb = new StringBuilder();
        sb.append("Sending app info to ");
        sb.append(getUrl());
        Fabric.getLogger().d("Fabric", sb.toString());
        if (appRequestData.icon != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("App icon hash is ");
            sb2.append(appRequestData.icon.hash);
            Fabric.getLogger().d("Fabric", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("App icon size is ");
            sb3.append(appRequestData.icon.width);
            sb3.append(AvidJSONUtil.KEY_X);
            sb3.append(appRequestData.icon.height);
            Fabric.getLogger().d("Fabric", sb3.toString());
        }
        int code = applyMultipartDataTo.code();
        String str = HttpConstants.METHOD_POST.equals(applyMultipartDataTo.method()) ? "Create" : "Update";
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        sb4.append(" app request ID: ");
        sb4.append(applyMultipartDataTo.header("X-REQUEST-ID"));
        Fabric.getLogger().d("Fabric", sb4.toString());
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Result was ");
        sb5.append(code);
        Fabric.getLogger().d("Fabric", sb5.toString());
        return ResponseParser.parse(code) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        return httpRequest.header("X-CRASHLYTICS-API-KEY", appRequestData.apiKey).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        HttpRequest part = httpRequest.part("app[identifier]", appRequestData.appId).part("app[name]", appRequestData.name).part("app[display_version]", appRequestData.displayVersion).part("app[build_version]", appRequestData.buildVersion).part("app[source]", (Number) Integer.valueOf(appRequestData.source)).part("app[minimum_sdk_version]", appRequestData.minSdkVersion).part("app[built_sdk_version]", appRequestData.builtSdkVersion);
        if (!CommonUtils.isNullOrEmpty(appRequestData.instanceIdentifier)) {
            part.part("app[instance_identifier]", appRequestData.instanceIdentifier);
        }
        if (appRequestData.icon != null) {
            InputStream inputStream = null;
            try {
                inputStream = this.kit.getContext().getResources().openRawResource(appRequestData.icon.iconResourceId);
                part.part("app[icon][hash]", appRequestData.icon.hash).part("app[icon][data]", "icon.png", "application/octet-stream", inputStream).part("app[icon][width]", (Number) Integer.valueOf(appRequestData.icon.width)).part("app[icon][height]", (Number) Integer.valueOf(appRequestData.icon.height));
            } catch (NotFoundException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to find app icon with resource ID: ");
                sb.append(appRequestData.icon.iconResourceId);
                Fabric.getLogger().e("Fabric", sb.toString(), e);
            } catch (Throwable th) {
                CommonUtils.closeOrLog(inputStream, "Failed to close app icon InputStream.");
                throw th;
            }
            CommonUtils.closeOrLog(inputStream, "Failed to close app icon InputStream.");
        }
        if (appRequestData.sdkKits != null) {
            for (KitInfo kitInfo : appRequestData.sdkKits) {
                part.part(getKitVersionKey(kitInfo), kitInfo.getVersion());
                part.part(getKitBuildTypeKey(kitInfo), kitInfo.getBuildType());
            }
        }
        return part;
    }

    /* access modifiers changed from: 0000 */
    public String getKitVersionKey(KitInfo kitInfo) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", new Object[]{kitInfo.getIdentifier()});
    }

    /* access modifiers changed from: 0000 */
    public String getKitBuildTypeKey(KitInfo kitInfo) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", new Object[]{kitInfo.getIdentifier()});
    }
}
