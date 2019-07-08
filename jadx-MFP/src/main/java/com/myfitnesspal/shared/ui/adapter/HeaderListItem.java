package com.myfitnesspal.shared.ui.adapter;

public class HeaderListItem extends ListItemHeader {
    public static final int ITEM_VIEW_TYPE = HeaderListItem.class.getCanonicalName().hashCode();

    public HeaderListItem(String str) {
        super(str);
    }

    public int getViewType() {
        return ITEM_VIEW_TYPE;
    }
}
