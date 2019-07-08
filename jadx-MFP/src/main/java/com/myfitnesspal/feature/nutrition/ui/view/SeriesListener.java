package com.myfitnesspal.feature.nutrition.ui.view;

public interface SeriesListener {
    int getClickedIndex();

    int getCurrentIndex();

    void onSeriesClear();

    void onSeriesClicked(int i);
}
