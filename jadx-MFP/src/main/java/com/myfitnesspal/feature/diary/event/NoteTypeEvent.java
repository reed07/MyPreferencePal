package com.myfitnesspal.feature.diary.event;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/diary/event/NoteTypeEvent;", "", "noteType", "", "(I)V", "getNoteType", "()I", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: NoteTypeEvent.kt */
public final class NoteTypeEvent {
    private final int noteType;

    public NoteTypeEvent(int i) {
        this.noteType = i;
    }

    public final int getNoteType() {
        return this.noteType;
    }
}
