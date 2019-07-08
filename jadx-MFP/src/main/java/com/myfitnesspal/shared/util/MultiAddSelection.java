package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiAddSelection {
    private List<MfpExerciseEntry> entries;
    private Map<Long, MfpExerciseEntry> localIdToEntryMap;
    private Map<Long, MfpExerciseEntry> masterIdToEntryMap;
    private Map<String, MfpExerciseEntry> versionToEntryMap;

    public MultiAddSelection() {
        setEntries(new ArrayList());
        setLocalIdToEntryMap(new HashMap());
        setMasterIdToEntryMap(new HashMap());
        setVersionToEntryMap(new HashMap());
    }

    public int selectedItemCount() {
        return CollectionUtils.size((Collection<?>) this.entries);
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty((Collection<?>) this.entries);
    }

    public List<MfpExerciseEntry> getEntries() {
        return this.entries;
    }

    public boolean hasSelectedItem(MfpExerciseEntry mfpExerciseEntry) {
        return (mfpExerciseEntry == null || getExerciseEntryForExercise(mfpExerciseEntry.getExercise()) == null) ? false : true;
    }

    public void selectExerciseEntry(MfpExerciseEntry mfpExerciseEntry) {
        if (mfpExerciseEntry != null) {
            if (!hasSelectedItem(mfpExerciseEntry)) {
                this.entries.add(mfpExerciseEntry);
            }
            MfpExercise exercise = mfpExerciseEntry.getExercise();
            if (exercise != null) {
                if (exercise.hasLocalId()) {
                    this.localIdToEntryMap.put(Long.valueOf(exercise.getLocalId()), mfpExerciseEntry);
                } else if (exercise.hasMasterId()) {
                    this.masterIdToEntryMap.put(Long.valueOf(exercise.getMasterId()), mfpExerciseEntry);
                } else if (exercise.hasVersion()) {
                    this.versionToEntryMap.put(exercise.getVersion(), mfpExerciseEntry);
                }
            }
        }
    }

    private void deselectItem(MfpExercise mfpExercise) {
        if (mfpExercise != null) {
            ArrayList arrayList = new ArrayList(this.entries.size());
            if (!isEmpty()) {
                for (MfpExerciseEntry mfpExerciseEntry : this.entries) {
                    MfpExercise exercise = mfpExerciseEntry.getExercise();
                    if (exercise == null || ((!exercise.hasLocalId() || !mfpExercise.hasLocalId() || exercise.getLocalId() != mfpExercise.getLocalId()) && ((!exercise.hasMasterId() || !mfpExercise.hasMasterId() || exercise.getMasterId() != mfpExercise.getMasterId()) && (!exercise.hasVersion() || !mfpExercise.hasVersion() || !Strings.equals(exercise.getVersion(), mfpExercise.getVersion()))))) {
                        arrayList.add(mfpExerciseEntry);
                    }
                }
            }
            setEntries(arrayList);
            if (mfpExercise.hasLocalId()) {
                this.localIdToEntryMap.remove(Long.valueOf(mfpExercise.getLocalId()));
            }
            if (mfpExercise.hasMasterId()) {
                this.masterIdToEntryMap.remove(Long.valueOf(mfpExercise.getMasterId()));
            } else if (mfpExercise.hasVersion()) {
                this.versionToEntryMap.remove(mfpExercise.getVersion());
            }
        }
    }

    public void deselectExerciseEntry(MfpExerciseEntry mfpExerciseEntry) {
        if (mfpExerciseEntry != null) {
            deselectItem(mfpExerciseEntry.getExercise());
        }
    }

    public MfpExerciseEntry getExerciseEntryForExercise(MfpExercise mfpExercise) {
        MfpExerciseEntry mfpExerciseEntry = null;
        if (mfpExercise == null) {
            return null;
        }
        if (mfpExercise.hasLocalId()) {
            mfpExerciseEntry = (MfpExerciseEntry) this.localIdToEntryMap.get(Long.valueOf(mfpExercise.getLocalId()));
        } else if (mfpExercise.hasMasterId()) {
            mfpExerciseEntry = (MfpExerciseEntry) this.masterIdToEntryMap.get(Long.valueOf(mfpExercise.getMasterId()));
        } else if (mfpExercise.hasVersion()) {
            mfpExerciseEntry = (MfpExerciseEntry) this.versionToEntryMap.get(Strings.toString(mfpExercise.getVersion()));
        }
        return mfpExerciseEntry;
    }

    private void setEntries(List<MfpExerciseEntry> list) {
        this.entries = list;
    }

    private void setLocalIdToEntryMap(Map<Long, MfpExerciseEntry> map) {
        this.localIdToEntryMap = map;
    }

    private void setMasterIdToEntryMap(Map<Long, MfpExerciseEntry> map) {
        this.masterIdToEntryMap = map;
    }

    private void setVersionToEntryMap(Map<String, MfpExerciseEntry> map) {
        this.versionToEntryMap = map;
    }
}
