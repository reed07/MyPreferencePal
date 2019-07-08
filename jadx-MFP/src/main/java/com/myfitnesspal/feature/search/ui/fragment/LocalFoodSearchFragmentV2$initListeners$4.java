package com.myfitnesspal.feature.search.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResImageItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "item", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "position", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initListeners$4 extends Lambda implements Function2<DiaryEntryCellModel, Integer, Unit> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initListeners$4(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((DiaryEntryCellModel) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull final DiaryEntryCellModel diaryEntryCellModel, final int i) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, Attributes.ITEM);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialogListTextResImageItem(R.string.deleteEntry, R.drawable.ic_delete_black_24dp));
        new MfpAlertDialogBuilder(this.this$0.getActivity()).setTitle((int) R.string.recent_foods).setDismissOnItemClick(true).setItems(arrayList, new OnItemClickListener(this) {
            final /* synthetic */ LocalFoodSearchFragmentV2$initListeners$4 this$0;

            {
                this.this$0 = r1;
            }

            public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0.this$0).deleteItemFromRecents(diaryEntryCellModel, i);
                LocalFoodSearchFragmentV2.access$getFoodAdapter$p(this.this$0.this$0).notifyItemRemoved(i);
            }
        }).create().show();
    }
}
