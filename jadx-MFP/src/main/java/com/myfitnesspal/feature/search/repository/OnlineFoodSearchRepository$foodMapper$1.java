package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.search.model.SponsoredFood.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/shared/model/mapper/ApiJsonMapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchRepository.kt */
final class OnlineFoodSearchRepository$foodMapper$1 extends Lambda implements Function0<ApiJsonMapper> {
    public static final OnlineFoodSearchRepository$foodMapper$1 INSTANCE = new OnlineFoodSearchRepository$foodMapper$1();

    OnlineFoodSearchRepository$foodMapper$1() {
        super(0);
    }

    public final ApiJsonMapper invoke() {
        return new ApiJsonMapper().withType(API_RESPONSE_MAPPER.class);
    }
}
