package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import android.os.Bundle;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil;
import com.myfitnesspal.shared.service.appindexer.AppIndexerUriUtil.Source;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public abstract class AppIndexerMixinBase extends DeepLinkMixinBase {
    private static final Map<Source, String> SOURCE_TO_EVENT_NAME_MAP = new HashMap();
    @Inject
    Lazy<AnalyticsService> analytics;
    @Inject
    Lazy<AppIndexerBot> appIndexerBot;

    public AppIndexerMixinBase(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        component().inject(this);
    }

    static {
        SOURCE_TO_EVENT_NAME_MAP.put(Source.Website, Events.APP_INDEXING_TAP_SEARCH_RESULT);
        SOURCE_TO_EVENT_NAME_MAP.put(Source.AutoComplete, Events.APP_INDEXING_TAP_AUTO_COMPLETE);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.APP_INDEXING_PAGE_VIEW);
    }

    /* access modifiers changed from: protected */
    public void onInvalidResponse(int i) {
        onInvalidResponse(getActivity().getString(i));
    }

    /* access modifiers changed from: protected */
    public void reportView(String str) {
        if (!((AppIndexerBot) this.appIndexerBot.get()).isActive()) {
            String str2 = (String) SOURCE_TO_EVENT_NAME_MAP.get(AppIndexerUriUtil.getSource(getActivity().getIntent()));
            if (Strings.notEmpty(str2)) {
                ((AnalyticsService) this.analytics.get()).reportEvent(str2, MapUtil.createMap(Attributes.ITEM_CODE, str));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidResponse(String str) {
        super.onInvalidResponse(str);
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.APP_INDEXING_INVALID_RESPONSE);
    }
}
