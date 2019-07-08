package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.model.v15.WaterEntryObject;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class WaterEntryMapperImpl implements WaterEntryMapper {
    public WaterEntry reverseMap(WaterEntryObject waterEntryObject) {
        WaterEntry waterEntry = new WaterEntry();
        waterEntry.setLocalId(waterEntryObject.getLocalId());
        waterEntry.setMasterDatabaseId(waterEntryObject.getMasterId());
        waterEntry.setCups((float) ((int) waterEntryObject.getCups()));
        waterEntry.setEntryDate(waterEntryObject.getEntryDate());
        waterEntry.setMilliliters(waterEntryObject.getMilliliters());
        return waterEntry;
    }

    public WaterEntryObject mapFrom(WaterEntry waterEntry) throws IOException {
        WaterEntryObject waterEntryObject = new WaterEntryObject();
        waterEntryObject.setLocalId(waterEntry.getLocalId());
        waterEntryObject.setMasterId(waterEntry.getMasterDatabaseId());
        waterEntryObject.setCups(waterEntry.getCups());
        waterEntryObject.setEntryDate(waterEntry.getEntryDate());
        waterEntryObject.setMillileters(waterEntry.getMilliliters());
        return waterEntryObject;
    }

    public WaterEntryObject tryMapFrom(WaterEntry waterEntry) {
        try {
            return mapFrom(waterEntry);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
