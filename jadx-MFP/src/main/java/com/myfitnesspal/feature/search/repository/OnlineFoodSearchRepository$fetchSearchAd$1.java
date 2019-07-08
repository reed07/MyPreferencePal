package com.myfitnesspal.feature.search.repository;

import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.feature.search.model.SponsoredFood.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd.Companion;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/SingleEmitter;", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchRepository.kt */
final class OnlineFoodSearchRepository$fetchSearchAd$1<T> implements SingleOnSubscribe<T> {
    final /* synthetic */ String $category;
    final /* synthetic */ OnlineFoodSearchRepository this$0;

    OnlineFoodSearchRepository$fetchSearchAd$1(OnlineFoodSearchRepository onlineFoodSearchRepository, String str) {
        this.this$0 = onlineFoodSearchRepository;
        this.$category = str;
    }

    public final void subscribe(@NotNull final SingleEmitter<SponsoredFood> singleEmitter) {
        Intrinsics.checkParameterIsNotNull(singleEmitter, AbstractEvent.EMITTER);
        new Builder(this.this$0.context, "/17729925/UACF_M/MFP/FoodSearch/FS_Results_DRD").forCustomTemplateAd("11760166", new OnCustomTemplateAdLoadedListener(this) {
            final /* synthetic */ OnlineFoodSearchRepository$fetchSearchAd$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd) {
                try {
                    Companion companion = SponsoredFoodSearchAd.Companion;
                    Intrinsics.checkExpressionValueIsNotNull(nativeCustomTemplateAd, "ad");
                    SponsoredFoodSearchAd fromCustomDfpAd = companion.fromCustomDfpAd(nativeCustomTemplateAd);
                    ApiJsonMapper apiJsonMapper = (ApiJsonMapper) this.this$0.this$0.foodMapper.getValue();
                    CharSequence text = nativeCustomTemplateAd.getText("FoodObject");
                    if (text != null) {
                        Object mapFrom = apiJsonMapper.mapFrom((String) text);
                        Intrinsics.checkExpressionValueIsNotNull(mapFrom, "foodMapper.value\n       …R_FOOD_OBJECT) as String)");
                        Object item = ((API_RESPONSE_MAPPER) mapFrom).getItem();
                        SponsoredFood sponsoredFood = (SponsoredFood) item;
                        sponsoredFood.setSponsoredFoodAd(fromCustomDfpAd);
                        sponsoredFood.setAdCategory(this.this$0.$category);
                        sponsoredFood.setDfpAd(nativeCustomTemplateAd);
                        SponsoredFood sponsoredFood2 = (SponsoredFood) item;
                        SingleEmitter singleEmitter = singleEmitter;
                        Intrinsics.checkExpressionValueIsNotNull(singleEmitter, AbstractEvent.EMITTER);
                        if (!singleEmitter.isDisposed()) {
                            nativeCustomTemplateAd.recordImpression();
                            singleEmitter.onSuccess(sponsoredFood2);
                            return;
                        }
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                } catch (Exception e) {
                    SingleEmitter singleEmitter2 = singleEmitter;
                    Intrinsics.checkExpressionValueIsNotNull(singleEmitter2, AbstractEvent.EMITTER);
                    if (!singleEmitter2.isDisposed()) {
                        singleEmitter.onError(e);
                    }
                }
            }
        }, AnonymousClass2.INSTANCE).withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                SingleEmitter singleEmitter = singleEmitter;
                Intrinsics.checkExpressionValueIsNotNull(singleEmitter, AbstractEvent.EMITTER);
                if (!singleEmitter.isDisposed()) {
                    SingleEmitter singleEmitter2 = singleEmitter;
                    StringBuilder sb = new StringBuilder();
                    sb.append("DFP ad load failed, code: ");
                    sb.append(i);
                    singleEmitter2.onError(new IllegalArgumentException(sb.toString()));
                }
            }
        }).build().loadAd(new PublisherAdRequest.Builder().addCustomTargeting("food_cat", this.$category).build());
    }
}
