package com.myfitnesspal.shared.service.device;

import com.myfitnesspal.shared.util.HttpUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class UserAgentProviderImpl implements UserAgentProvider {
    private final Map<String, String> data;
    private String userAgent;

    public static final class Params {
        public static final String API_CLIENT_ID = "api_client_id";
        public static final String APP_NAME = "app_name";
        public static final String BRAND = "brand";
        public static final String CLIENT_ID_BASE = "clientidbase";
        public static final String DEVICE = "device";
        public static final String LOCALE = "locale";
        public static final String MANUFACTURER = "manufacturer";
        public static final String MODEL = "model";
        public static final String PRELOAD = "preload";
        public static final String RELEASE_NAME = "release_name";
        public static final String SIM_NAME = "sim_name";
        public static final String VERSION_NAME = "version_name";
    }

    public UserAgentProviderImpl(Map<String, String> map) {
        this.data = map;
    }

    public String get() {
        if (this.userAgent == null) {
            synchronized (UserAgentProviderImpl.class) {
                if (this.userAgent == null) {
                    ArrayList arrayList = new ArrayList();
                    StringBuilder sb = new StringBuilder();
                    sb.append("preload=");
                    sb.append(fixString(this.data.get(Params.PRELOAD)));
                    arrayList.add(sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("locale=");
                    sb2.append(fixString(this.data.get("locale")));
                    arrayList.add(sb2.toString());
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("clientidbase=");
                    sb3.append(fixString(this.data.get(Params.CLIENT_ID_BASE)));
                    arrayList.add(sb3.toString());
                    this.userAgent = String.format("%s/%s (%s) (Android %s; %s %s / %s %s; %s) (%s)", new Object[]{fixString(this.data.get("app_name")), fixString(this.data.get(Params.VERSION_NAME)), fixString(this.data.get(Params.API_CLIENT_ID)), fixString(this.data.get(Params.RELEASE_NAME)), fixString(this.data.get("manufacturer")), fixString(this.data.get("device")), fixString(this.data.get("brand")), fixString(this.data.get("model")), fixString(this.data.get(Params.SIM_NAME)), fixString(Strings.join(";", (Collection<T>) arrayList))});
                    this.userAgent = HttpUtils.sanitizeHeaderValue(this.userAgent);
                    Ln.d("USERAGENT: created %s", this.userAgent);
                }
            }
        }
        return this.userAgent;
    }

    private String fixString(Object obj) {
        return Strings.trimmed(obj).replace(10, ' ').replace(13, ' ').replace("(", "").replace(")", "");
    }
}
