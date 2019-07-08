package com.myfitnesspal.feature.search.ui.activity;

import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.AlertEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "resource", "Lcom/myfitnesspal/feature/consents/model/Resource;", "Ljava/lang/Void;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
final class FoodSearchActivityV2$onActivityResult$1 extends Lambda implements Function1<Resource<? extends Void>, Unit> {
    final /* synthetic */ FoodSearchActivityV2 this$0;

    FoodSearchActivityV2$onActivityResult$1(FoodSearchActivityV2 foodSearchActivityV2) {
        this.this$0 = foodSearchActivityV2;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Resource) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Resource<Void> resource) {
        Intrinsics.checkParameterIsNotNull(resource, "resource");
        if (resource instanceof Error) {
            this.this$0.postEvent(new AlertEvent(((Error) resource).getThrowable().getMessage()));
        } else if (resource instanceof Success) {
            this.this$0.getNavigationHelper().withClearTopAndSingleTop().withIntent(Diary.newStartIntentWithReferrer(this.this$0, Extras.REFERRER_DIARY_JUST_LOGGED)).startActivity();
        }
    }
}
