package com.myfitnesspal.feature.search.ui;

import android.content.Context;
import android.content.Intent;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity.Extras;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/FoodSearchActivityFactory;", "", "configService", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "(Lcom/myfitnesspal/shared/service/config/ConfigService;)V", "getFoodSearchActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "extras", "Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivity$Extras;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityFactory.kt */
public final class FoodSearchActivityFactory {
    private final ConfigService configService;

    @Inject
    public FoodSearchActivityFactory(@NotNull ConfigService configService2) {
        Intrinsics.checkParameterIsNotNull(configService2, "configService");
        this.configService = configService2;
    }

    @NotNull
    public final Intent getFoodSearchActivityIntent(@NotNull Context context, @NotNull Extras extras) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(extras, "extras");
        if (ConfigUtils.isFoodSearchPhase1Enabled(this.configService)) {
            return FoodSearchActivityV2.Companion.newStartIntent(context, extras.toV2());
        }
        Intent newStartIntent = FoodSearchActivity.newStartIntent(context, extras);
        Intrinsics.checkExpressionValueIsNotNull(newStartIntent, "FoodSearchActivity.newStartIntent(context, extras)");
        return newStartIntent;
    }
}
