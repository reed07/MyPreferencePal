package com.myfitnesspal.feature.progress.constants;

public enum GalleryViewMode {
    Single("single_pane"),
    Split("double_pane");
    
    private String analtyicsValue;

    private GalleryViewMode(String str) {
        this.analtyicsValue = str;
    }

    public String getAnaltyicsValue() {
        return this.analtyicsValue;
    }
}
