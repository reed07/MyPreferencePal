package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class ProductServiceOp extends UacfScheduleOpBase {
    private final Lazy<ProductService> productsService;
    boolean retriedOnce = false;

    public ProductServiceOp(Lazy<ProductService> lazy) {
        this.productsService = lazy;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            ((ProductService) this.productsService.get()).refreshProductsIfCacheExpired();
        } catch (ApiException e) {
            if (!this.retriedOnce) {
                this.retriedOnce = true;
                return Result.retry(new UacfScheduleException(e));
            }
        }
        return Result.completed();
    }
}
