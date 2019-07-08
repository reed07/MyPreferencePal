package io.requery.async;

import io.requery.EntityStore;
import java.util.concurrent.CompletionStage;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface CompletionStageEntityStore<T> extends EntityStore<T, CompletionStage<?>> {
}
