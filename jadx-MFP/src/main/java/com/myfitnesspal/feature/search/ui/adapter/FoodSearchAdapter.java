package com.myfitnesspal.feature.search.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.shared.constants.Constants.FoodSearch;
import com.myfitnesspal.shared.event.UpdateMultiAddStatusEvent;
import com.myfitnesspal.shared.model.FoodImages;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.squareup.otto.Bus;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple3;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodSearchAdapter extends ArrayAdapter<DiaryEntryCellModel> {
    /* access modifiers changed from: private */
    public final Bus bus;
    private final FoodSearchTab foodSearchTab;
    private final Lazy<ImageService> imageService;
    private FoodImages images;
    private final LayoutInflater inflater;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final Lazy<MealUtil> mealHelperUtil;
    private List<DiaryEntryCellModel> multiAddExtras;
    /* access modifiers changed from: private */
    public final Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    private OnCheckedChangeListener onCheckedChangeListener;
    /* access modifiers changed from: private */
    public String queryString;
    /* access modifiers changed from: private */
    public Set<DiaryEntryCellModel> selectedPairedFoods;
    private boolean showFoodImages;
    /* access modifiers changed from: private */
    public boolean showPairedFoods;
    private final boolean showPremiumQuickAdd;
    private final Lazy<UserEnergyService> userEnergyService;

    private enum ViewType {
        NORMAL_ITEM,
        SEARCH_ITEM,
        EMPTY_ITEM,
        BUTTON_ITEM
    }

    public FoodSearchAdapter(Context context, Lazy<ImageService> lazy, Lazy<UserEnergyService> lazy2, Lazy<MealUtil> lazy3, Lazy<LocalizedStringsUtil> lazy4, Lazy<MultiAddFoodHelper> lazy5, boolean z) {
        this(context, null, lazy, lazy2, lazy3, lazy4, lazy5, null, false, false, null);
        this.showPairedFoods = z;
        this.selectedPairedFoods = new HashSet();
    }

    public FoodSearchAdapter(Context context, FoodSearchTab foodSearchTab2, Lazy<ImageService> lazy, Lazy<UserEnergyService> lazy2, Lazy<MealUtil> lazy3, Lazy<LocalizedStringsUtil> lazy4, Lazy<MultiAddFoodHelper> lazy5, Bus bus2, boolean z, boolean z2, String str) {
        super(context, -1);
        this.showPairedFoods = false;
        this.showFoodImages = false;
        this.onCheckedChangeListener = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Tuple3 tuple3 = (Tuple3) compoundButton.getTag();
                DiaryEntryCellModel diaryEntryCellModel = (DiaryEntryCellModel) tuple3.getItem1();
                int intValue = ((Integer) tuple3.getItem2()).intValue();
                Food food = diaryEntryCellModel.isFoodEntry() ? ((FoodEntry) diaryEntryCellModel).getFood() : diaryEntryCellModel.isFood() ? (Food) diaryEntryCellModel : null;
                if (!FoodSearchAdapter.this.showPairedFoods) {
                    if (((MultiAddFoodHelper) FoodSearchAdapter.this.multiAddFoodHelper.get()).isMultiAddModeOn()) {
                        if (z) {
                            ((MultiAddFoodHelper) FoodSearchAdapter.this.multiAddFoodHelper.get()).addAndLogItem(diaryEntryCellModel, Builder.fromFood(food).searchTerm(FoodSearchAdapter.this.queryString).index(intValue).build());
                        } else {
                            ((MultiAddFoodHelper) FoodSearchAdapter.this.multiAddFoodHelper.get()).removeItemAndLog(diaryEntryCellModel, Builder.fromFood(food).build());
                        }
                    }
                    if (FoodSearchAdapter.this.bus != null) {
                        FoodSearchAdapter.this.bus.post(new UpdateMultiAddStatusEvent());
                    }
                } else if (z) {
                    FoodSearchAdapter.this.selectedPairedFoods.add(diaryEntryCellModel);
                } else {
                    FoodSearchAdapter.this.selectedPairedFoods.remove(diaryEntryCellModel);
                }
            }
        };
        this.foodSearchTab = foodSearchTab2;
        this.imageService = lazy;
        this.userEnergyService = lazy2;
        this.mealHelperUtil = lazy3;
        this.localizedStringsUtil = lazy4;
        this.multiAddFoodHelper = lazy5;
        this.bus = bus2;
        this.showPremiumQuickAdd = z;
        this.showFoodImages = z2;
        this.queryString = str;
        this.inflater = LayoutInflater.from(context);
    }

    public void notifyDataSetChanged() {
        updateMultiAddExtras();
        super.notifyDataSetChanged();
    }

    public void setImages(FoodImages foodImages) {
        this.images = foodImages;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public int getViewTypeCount() {
        return ViewType.values().length;
    }

    public int getCount() {
        return CollectionUtils.size((Collection<?>) this.multiAddExtras) + super.getCount();
    }

    public DiaryEntryCellModel getItem(int i) {
        int size = CollectionUtils.size((Collection<?>) this.multiAddExtras);
        if (i < size) {
            return (DiaryEntryCellModel) this.multiAddExtras.get(i);
        }
        return (DiaryEntryCellModel) super.getItem(i - size);
    }

    public int getItemViewType(int i) {
        ViewType viewType;
        DiaryEntryCellModel item = getItem(i);
        if (!isDummyType(item)) {
            viewType = ViewType.NORMAL_ITEM;
        } else {
            String description = ((Food) item).getDescription();
            if (Strings.equals(FoodSearch.EMPTY, description)) {
                viewType = ViewType.EMPTY_ITEM;
            } else if (Strings.equals(FoodSearch.FOOTER_FOOD_ITEM, description)) {
                viewType = ViewType.SEARCH_ITEM;
            } else {
                viewType = ViewType.BUTTON_ITEM;
            }
        }
        return viewType.ordinal();
    }

    public boolean isEnabled(int i) {
        boolean z = false;
        if (i >= getCount()) {
            return false;
        }
        ViewType viewType = ViewType.values()[getItemViewType(i)];
        if (viewType == ViewType.NORMAL_ITEM || viewType == ViewType.SEARCH_ITEM) {
            z = true;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [com.myfitnesspal.shared.ui.view.ViewHolder] */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: type inference failed for: r3v4, types: [com.myfitnesspal.shared.ui.view.ViewHolder] */
    /* JADX WARNING: type inference failed for: r3v7, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v0, types: [com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder] */
    /* JADX WARNING: type inference failed for: r7v1, types: [com.myfitnesspal.feature.search.ui.adapter.holder.ButtonItemViewHolder] */
    /* JADX WARNING: type inference failed for: r3v13, types: [com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder] */
    /* JADX WARNING: type inference failed for: r3v15, types: [com.myfitnesspal.feature.search.ui.adapter.holder.SearchItemViewHolder] */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r7v2, types: [com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder] */
    /* JADX WARNING: type inference failed for: r7v3, types: [com.myfitnesspal.feature.search.ui.adapter.holder.ButtonItemViewHolder] */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v16
  assigns: [?[OBJECT, ARRAY], com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder, com.myfitnesspal.feature.search.ui.adapter.holder.ButtonItemViewHolder]
  uses: [java.lang.Object, com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder, com.myfitnesspal.feature.search.ui.adapter.holder.ButtonItemViewHolder]
  mth insns count: 82
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 9 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r18, android.view.View r19, android.view.ViewGroup r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r20
            com.myfitnesspal.shared.model.v1.DiaryEntryCellModel r2 = r17.getItem(r18)
            com.myfitnesspal.feature.search.ui.adapter.FoodSearchAdapter$ViewType[] r3 = com.myfitnesspal.feature.search.ui.adapter.FoodSearchAdapter.ViewType.values()
            int r4 = r17.getItemViewType(r18)
            r3 = r3[r4]
            r4 = 0
            if (r19 != 0) goto L_0x0084
            int[] r5 = com.myfitnesspal.feature.search.ui.adapter.FoodSearchAdapter.AnonymousClass2.$SwitchMap$com$myfitnesspal$feature$search$ui$adapter$FoodSearchAdapter$ViewType
            int r3 = r3.ordinal()
            r3 = r5[r3]
            r5 = 0
            switch(r3) {
                case 1: goto L_0x005e;
                case 2: goto L_0x0045;
                case 3: goto L_0x0034;
                case 4: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            r1 = r19
            r3 = r4
            goto L_0x0080
        L_0x0025:
            android.view.LayoutInflater r3 = r0.inflater
            r6 = 2131558824(0x7f0d01a8, float:1.8742975E38)
            android.view.View r1 = r3.inflate(r6, r1, r5)
            com.myfitnesspal.feature.search.ui.adapter.holder.SearchItemViewHolder r3 = new com.myfitnesspal.feature.search.ui.adapter.holder.SearchItemViewHolder
            r3.<init>(r1)
            goto L_0x0080
        L_0x0034:
            android.view.LayoutInflater r3 = r0.inflater
            r6 = 2131558807(0x7f0d0197, float:1.874294E38)
            android.view.View r1 = r3.inflate(r6, r1, r5)
            com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder r3 = new com.myfitnesspal.feature.search.ui.adapter.holder.EmptyItemViewHolder
            com.squareup.otto.Bus r5 = r0.bus
            r3.<init>(r1, r5)
            goto L_0x0080
        L_0x0045:
            android.view.LayoutInflater r3 = r0.inflater
            r6 = 2131558544(0x7f0d0090, float:1.8742407E38)
            android.view.View r1 = r3.inflate(r6, r1, r5)
            com.myfitnesspal.feature.search.ui.adapter.holder.ButtonItemViewHolder r3 = new com.myfitnesspal.feature.search.ui.adapter.holder.ButtonItemViewHolder
            com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r9 = r0.foodSearchTab
            boolean r10 = r0.showPremiumQuickAdd
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r11 = r0.userEnergyService
            dagger.Lazy<com.myfitnesspal.shared.util.LocalizedStringsUtil> r12 = r0.localizedStringsUtil
            r7 = r3
            r8 = r1
            r7.<init>(r8, r9, r10, r11, r12)
            goto L_0x0080
        L_0x005e:
            android.view.LayoutInflater r3 = r0.inflater
            r6 = 2131558704(0x7f0d0130, float:1.8742731E38)
            android.view.View r1 = r3.inflate(r6, r1, r5)
            com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder r3 = new com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder
            dagger.Lazy<com.myfitnesspal.feature.images.service.ImageService> r9 = r0.imageService
            dagger.Lazy<com.myfitnesspal.shared.util.MultiAddFoodHelper> r10 = r0.multiAddFoodHelper
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r11 = r0.userEnergyService
            dagger.Lazy<com.myfitnesspal.feature.meals.util.MealUtil> r12 = r0.mealHelperUtil
            android.widget.CompoundButton$OnCheckedChangeListener r13 = r0.onCheckedChangeListener
            com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r14 = r0.foodSearchTab
            boolean r15 = r0.showPairedFoods
            boolean r5 = r0.showFoodImages
            r7 = r3
            r8 = r1
            r16 = r5
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16)
        L_0x0080:
            r1.setTag(r3)
            goto L_0x008d
        L_0x0084:
            java.lang.Object r1 = r19.getTag()
            r3 = r1
            com.myfitnesspal.shared.ui.view.ViewHolder r3 = (com.myfitnesspal.shared.ui.view.ViewHolder) r3
            r1 = r19
        L_0x008d:
            boolean r5 = r3 instanceof com.myfitnesspal.feature.search.ui.adapter.holder.RequiresSearchQuery
            if (r5 == 0) goto L_0x0099
            r5 = r3
            com.myfitnesspal.feature.search.ui.adapter.holder.RequiresSearchQuery r5 = (com.myfitnesspal.feature.search.ui.adapter.holder.RequiresSearchQuery) r5
            java.lang.String r6 = r0.queryString
            r5.setSearchQuery(r6)
        L_0x0099:
            boolean r5 = r0.showFoodImages
            if (r5 == 0) goto L_0x00c7
            boolean r5 = r3 instanceof com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder
            if (r5 == 0) goto L_0x00c4
            if (r2 == 0) goto L_0x00bb
            boolean r5 = r2 instanceof com.myfitnesspal.shared.model.v1.Food
            if (r5 == 0) goto L_0x00bb
            com.myfitnesspal.shared.model.FoodImages r5 = r0.images
            if (r5 == 0) goto L_0x00bb
            r4 = r3
            com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder r4 = (com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder) r4
            r6 = r2
            com.myfitnesspal.shared.model.v1.Food r6 = (com.myfitnesspal.shared.model.v1.Food) r6
            com.myfitnesspal.shared.model.v2.MfpImage r5 = r5.getImage(r6)
            r4.setImage(r5)
            r4 = r18
            goto L_0x00c9
        L_0x00bb:
            r5 = r3
            com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder r5 = (com.myfitnesspal.feature.search.ui.adapter.holder.NormalItemViewHolder) r5
            r5.setImage(r4)
            r4 = r18
            goto L_0x00c9
        L_0x00c4:
            r4 = r18
            goto L_0x00c9
        L_0x00c7:
            r4 = r18
        L_0x00c9:
            r3.setData(r2, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.adapter.FoodSearchAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    private static boolean isDummyType(DiaryEntryCellModel diaryEntryCellModel) {
        int i;
        if (diaryEntryCellModel.isMealEntries()) {
            i = diaryEntryCellModel.itemType();
        } else if (diaryEntryCellModel.isFood()) {
            i = ((Food) diaryEntryCellModel).getFoodType();
        } else {
            i = ((FoodEntry) diaryEntryCellModel).getFood().getFoodType();
        }
        return i == -1;
    }

    public Set<DiaryEntryCellModel> getSelectedPairedFoods() {
        return this.selectedPairedFoods;
    }

    private void updateMultiAddExtras() {
        this.multiAddExtras = (!((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn() || this.showPairedFoods) ? new ArrayList<>() : ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).getExternalItems();
    }
}
