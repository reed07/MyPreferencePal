package io.uacf.rollouts.internal.service;

import io.uacf.core.api.UacfApiException;
import io.uacf.rollouts.internal.model.Variant;
import java.util.List;

public interface VariantService {
    void fetchVariants() throws UacfApiException;

    void forceFetchVariants() throws UacfApiException;

    Variant getVariant(String str);

    List<Variant> getVariants();
}
