package io.uacf.rollouts.sdk;

import io.uacf.core.api.UacfApiException;
import io.uacf.rollouts.sdk.model.UacfVariant;
import java.util.List;

public interface UacfVariantSdk {
    void fetchVariants() throws UacfApiException;

    void forceFetchVariants() throws UacfApiException;

    UacfVariant getVariant(String str);

    List<UacfVariant> getVariants();
}
