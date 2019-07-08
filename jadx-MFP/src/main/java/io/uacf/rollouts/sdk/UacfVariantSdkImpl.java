package io.uacf.rollouts.sdk;

import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import io.uacf.core.api.UacfApiException;
import io.uacf.rollouts.internal.model.Variant;
import io.uacf.rollouts.internal.service.VariantService;
import io.uacf.rollouts.sdk.model.UacfVariant;
import io.uacf.rollouts.sdk.model.UacfVariant.Builder;
import java.util.Collection;
import java.util.List;

public class UacfVariantSdkImpl implements UacfVariantSdk {
    private final VariantService variantService;

    public UacfVariantSdkImpl(VariantService variantService2) {
        this.variantService = variantService2;
    }

    public UacfVariant getVariant(String str) {
        Variant variant = this.variantService.getVariant(str);
        if (variant == null) {
            return null;
        }
        return mapToUacfVariant(variant);
    }

    /* access modifiers changed from: private */
    public UacfVariant mapToUacfVariant(Variant variant) {
        return new Builder().withRolloutName(variant.getRolloutName()).withTrackEvent(variant.isVariantTracked().booleanValue()).withVersion(variant.getRolloutVersion().intValue()).withVariant(variant.getVariantName()).withVariantIndex(variant.getVariantIndex().intValue()).build();
    }

    public List<UacfVariant> getVariants() {
        return mapServiceVariantsToUacfVariants(this.variantService.getVariants());
    }

    public void fetchVariants() throws UacfApiException {
        this.variantService.fetchVariants();
    }

    public void forceFetchVariants() throws UacfApiException {
        this.variantService.forceFetchVariants();
    }

    private List<UacfVariant> mapServiceVariantsToUacfVariants(List<Variant> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1<UacfVariant, Variant>() {
            public UacfVariant execute(Variant variant) {
                return UacfVariantSdkImpl.this.mapToUacfVariant(variant);
            }
        });
    }
}
