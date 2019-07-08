package com.myfitnesspal.shared.task;

import android.content.Context;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.io.IOException;

public class QueryFoodByIdTask extends EventedTaskBase<MfpFood, ApiException> {
    public static final String NAME = "FoodLookup";
    private final FoodDBAdapter foodDbAdapter;
    private final Lazy<FoodMapper> foodMapper;
    private final String id;
    private final Lazy<MfpFoodMapper> mfpFoodMapper;
    private final Lazy<SearchService> searchService;
    private final String version;

    public static class CompletedEvent extends TaskEventBase<MfpFood, ApiException> {
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return NAME;
    }

    public QueryFoodByIdTask(FoodDBAdapter foodDBAdapter, Lazy<SearchService> lazy, String str, String str2, Lazy<MfpFoodMapper> lazy2, Lazy<FoodMapper> lazy3) {
        super(CompletedEvent.class);
        this.foodDbAdapter = foodDBAdapter;
        this.searchService = lazy;
        this.id = str;
        this.version = str2;
        this.mfpFoodMapper = lazy2;
        this.foodMapper = lazy3;
    }

    /* access modifiers changed from: protected */
    public MfpFood exec(Context context) throws ApiException {
        Food fetchFoodByOriginalUid = this.foodDbAdapter.fetchFoodByOriginalUid(this.id);
        if (fetchFoodByOriginalUid != null) {
            try {
                MfpFood mfpFood = (MfpFood) ((MfpFoodMapper) this.mfpFoodMapper.get()).mapFrom((FoodObject) ((FoodMapper) this.foodMapper.get()).mapFrom(fetchFoodByOriginalUid));
                if (mfpFood != null && Strings.notEmpty(mfpFood.getId())) {
                    return mfpFood;
                }
            } catch (IOException unused) {
            }
        }
        return ((SearchService) this.searchService.get()).findByIdAndVersion(this.id, this.version);
    }
}
