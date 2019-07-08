package com.myfitnesspal.feature.restaurantlogging.service;

import com.myfitnesspal.feature.restaurantlogging.model.FoodGroup;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatch;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatchData;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMultiAddMenuItem;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiPostData;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Http;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

public class MenuServiceImpl implements MenuService {
    private final Provider<MfpV2Api> mfpV2ApiProvider;

    public MenuServiceImpl(Provider<MfpV2Api> provider) {
        this.mfpV2ApiProvider = provider;
    }

    public List<MfpMenuItem> getMenuItemsForMenuIdAndItemIds(String str, List<String> list) throws ApiException {
        if (CollectionUtils.isEmpty((Collection<?>) list) || Strings.isEmpty(str)) {
            return null;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(Http.IDS);
            arrayList.add(list.get(i));
        }
        arrayList.add("max_items");
        arrayList.add(Strings.toString(Integer.valueOf(size)));
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.mfpV2ApiProvider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(String.format(Uri.MENU_ITEMS, new Object[]{str}), strArr)).getItems();
    }

    public MfpMenuItemMatch createMenuItemMatch(MfpMenuItemMatch mfpMenuItemMatch, String str, String str2) throws ApiException {
        if (mfpMenuItemMatch != null) {
            String[] strArr = {Http.LOG_OBJECT_REQUIRED, Boolean.toString(true)};
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(mfpMenuItemMatch);
            List items = ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.mfpV2ApiProvider.get()).withJsonBody(new ApiPostData((List<T>) arrayList))).withOutputType(MfpMenuItemMatch.API_RESPONSE_MAPPER.class)).post(String.format(Uri.MENU_MATCHES, new Object[]{str, str2}), (Object[]) strArr)).getItems();
            if (items.isEmpty()) {
                return null;
            }
            return (MfpMenuItemMatch) items.get(0);
        }
        throw new IllegalArgumentException("match cannot be null");
    }

    public List<MfpMultiAddMenuItem> createMenuItemMatches(List<MfpMenuItem> list) throws ApiException {
        List<MfpMultiAddMenuItem> list2;
        if (!CollectionUtils.isEmpty((Collection<?>) list)) {
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            ArrayList arrayList2 = new ArrayList();
            HashMap hashMap = new HashMap(size);
            populateListsAndMapForCreatingMenuItemMatches(list, arrayList, arrayList2, hashMap);
            if (CollectionUtils.isEmpty((Collection<?>) arrayList)) {
                list2 = new ArrayList<>();
            } else {
                String[] strArr = {Http.LOG_OBJECT_REQUIRED, Boolean.toString(true)};
                list2 = ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.mfpV2ApiProvider.get()).withJsonBody(new ApiPostData((List<T>) arrayList))).withOutputType(MfpMultiAddMenuItem.API_RESPONSE_MAPPER.class)).post(Uri.MENU_ITEM_MATCHES, (Object[]) strArr)).getItems();
            }
            list2.addAll(arrayList2);
            setServingSizeIndexAndAmountForLogObjects(list2, hashMap);
            return list2;
        }
        throw new IllegalArgumentException("List cannot be empty or null");
    }

    private void populateListsAndMapForCreatingMenuItemMatches(List<MfpMenuItem> list, List<MfpMultiAddMenuItem> list2, List<MfpMultiAddMenuItem> list3, Map<String, MfpMenuItemMatch> map) {
        for (MfpMenuItem mfpMenuItem : list) {
            List matches = mfpMenuItem.getMatches();
            if (!CollectionUtils.isEmpty((Collection<?>) matches)) {
                String id = mfpMenuItem.getId();
                MfpMenuItemMatch mfpMenuItemMatch = (MfpMenuItemMatch) matches.get(0);
                MfpMultiAddMenuItem mfpMultiAddMenuItem = new MfpMultiAddMenuItem(id, mfpMenuItem.getMenuId(), mfpMenuItemMatch);
                MfpMenuItemMatchData logMatchData = mfpMenuItemMatch.getLogMatchData();
                if (logMatchData == null) {
                    list2.add(mfpMultiAddMenuItem);
                } else if (logMatchData instanceof MfpRecipe) {
                    MfpFood mfpFood = ((MfpRecipe) logMatchData).getMfpFood();
                    if (mfpFood == null || mfpFood.getNutritionalContents() == null) {
                        list2.add(mfpMultiAddMenuItem);
                    } else {
                        list3.add(mfpMultiAddMenuItem);
                    }
                } else {
                    list3.add(mfpMultiAddMenuItem);
                }
                map.put(id, mfpMenuItemMatch);
            }
        }
    }

    private void setServingSizeIndexAndAmountForLogObjects(List<MfpMultiAddMenuItem> list, Map<String, MfpMenuItemMatch> map) {
        for (MfpMultiAddMenuItem mfpMultiAddMenuItem : list) {
            MfpMenuItemMatch mfpMenuItemMatch = (MfpMenuItemMatch) map.get(mfpMultiAddMenuItem.getItemId());
            if (mfpMenuItemMatch != null) {
                MfpMenuItemMatchData basedOnMatchData = mfpMenuItemMatch.getBasedOnMatchData();
                MfpMenuItemMatchData logMatchData = mfpMultiAddMenuItem.getMatch().getLogMatchData();
                int i = 0;
                float f = 1.0f;
                if (basedOnMatchData instanceof MfpFood) {
                    MfpFood mfpFood = (MfpFood) basedOnMatchData;
                    i = mfpFood.getSelectedServingSizeIndex();
                    f = mfpFood.getSelectedServingAmount();
                } else if (basedOnMatchData instanceof FoodGroup) {
                    FoodGroup foodGroup = (FoodGroup) basedOnMatchData;
                    i = foodGroup.getSelectedFoodIndex();
                    f = foodGroup.getSelectedFood().getSelectedServingAmount();
                } else if (basedOnMatchData instanceof MfpRecipe) {
                    f = ((MfpRecipe) basedOnMatchData).getServings().floatValue();
                }
                if (logMatchData instanceof MfpFood) {
                    MfpFood mfpFood2 = (MfpFood) logMatchData;
                    mfpFood2.setSelectedServingSizeIndex(i);
                    mfpFood2.setSelectedServingAmount(f);
                } else if (logMatchData instanceof FoodGroup) {
                    FoodGroup foodGroup2 = (FoodGroup) logMatchData;
                    foodGroup2.setSelectedFoodIndex(i);
                    foodGroup2.getSelectedFood().setSelectedServingAmount(f);
                } else if (logMatchData instanceof MfpRecipe) {
                    ((MfpRecipe) logMatchData).setServings(Double.valueOf((double) f));
                }
            }
        }
    }
}
