package com.myfitnesspal.shared.db;

import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nonnull;

public class DatasetManager {
    private static DatasetManager currentManager;
    private ArrayList<Dataset> installedDatasets;

    private DatasetManager() {
    }

    public static DatasetManager current(@Nonnull DbConnectionManager dbConnectionManager) {
        if (currentManager == null) {
            currentManager = new DatasetManager();
            currentManager.loadInstalledDatasets(dbConnectionManager);
        }
        return currentManager;
    }

    public void loadInstalledDatasets(@Nonnull DbConnectionManager dbConnectionManager) {
        this.installedDatasets = dbConnectionManager.installedDatasetsDbAdapter().fetchInstalledDatasets();
        Collections.sort(this.installedDatasets);
    }

    public ArrayList<Dataset> getInstalledDatasets() {
        return this.installedDatasets;
    }

    public Dataset stockDataset() {
        return (Dataset) this.installedDatasets.get(0);
    }
}
