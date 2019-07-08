package com.myfitnesspal.shared.service.install;

import com.myfitnesspal.shared.model.MapOfStringString;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.FileUtils;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/shared/model/MapOfStringString;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: CountryServiceImpl.kt */
final class CountryServiceImpl$localizedLanguageNames$2 extends Lambda implements Function0<MapOfStringString> {
    final /* synthetic */ CountryServiceImpl this$0;

    CountryServiceImpl$localizedLanguageNames$2(CountryServiceImpl countryServiceImpl) {
        this.this$0 = countryServiceImpl;
        super(0);
    }

    public final MapOfStringString invoke() {
        return (MapOfStringString) new ApiJsonMapper().tryMapFrom(FileUtils.readAllLinesFromAsset(this.this$0.context, "localization/languages.json"), MapOfStringString.class);
    }
}
