package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class DiaryLogRequest {
    @Expose
    private List<DiaryEntryRequest> items = new ArrayList();

    public List<DiaryEntryRequest> getItems() {
        return this.items;
    }

    public void setItems(List<DiaryEntryRequest> list) {
        this.items = list;
    }

    public void addItem(DiaryEntryRequest diaryEntryRequest) {
        this.items.add(diaryEntryRequest);
    }
}
