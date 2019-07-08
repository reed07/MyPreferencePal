package com.myfitnesspal.shared.model;

import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodImages {
    private Map<Long, List<MfpImage>> localIdToImages = new HashMap();
    private Map<String, List<MfpImage>> uidToImages = new HashMap();

    public void add(Food food, MfpImage mfpImage) {
        add(food.getLocalId(), food.getUid(), mfpImage);
    }

    public void add(MfpFood mfpFood, MfpImage mfpImage) {
        add(mfpFood.getLocalId(), mfpFood.getId(), mfpImage);
    }

    public List<MfpImage> getImages(Food food) {
        return getImages(food.getLocalId(), food.getUid());
    }

    public List<MfpImage> getImages(MfpFood mfpFood) {
        return getImages(mfpFood.getLocalId(), mfpFood.getId());
    }

    public MfpImage getImage(Food food) {
        return getImage(food.getLocalId(), food.getUid());
    }

    public MfpImage getImage(MfpFood mfpFood) {
        return getImage(mfpFood.getLocalId(), mfpFood.getId());
    }

    public void add(long j, String str, MfpImage mfpImage) {
        if (j > 0) {
            List list = (List) this.localIdToImages.get(Long.valueOf(j));
            if (list == null) {
                list = new ArrayList();
                this.localIdToImages.put(Long.valueOf(j), list);
            }
            list.add(mfpImage);
        }
        if (Strings.notEmpty(str)) {
            List list2 = (List) this.uidToImages.get(str);
            if (list2 == null) {
                list2 = new ArrayList();
                this.uidToImages.put(str, list2);
            }
            list2.add(mfpImage);
        }
    }

    public MfpImage getImage(long j, String str) {
        List images = getImages(j, str);
        if (CollectionUtils.notEmpty((Collection<?>) images)) {
            return (MfpImage) images.get(0);
        }
        return null;
    }

    public List<MfpImage> getImages(long j, String str) {
        List<MfpImage> list = j > 0 ? (List) this.localIdToImages.get(Long.valueOf(j)) : null;
        return (list != null || !Strings.notEmpty(str)) ? list : (List) this.uidToImages.get(str);
    }
}
