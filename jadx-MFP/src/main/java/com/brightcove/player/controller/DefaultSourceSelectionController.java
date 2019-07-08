package com.brightcove.player.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.EventUtil;
import java.util.Set;

@ListensFor(events = {"selectSource"})
@Emits(events = {"sourceNotFound"})
public class DefaultSourceSelectionController extends AbstractComponent implements SourceSelector, Component {
    public static final String TAG = "DefaultSourceSelectionController";
    @NonNull
    private SourceSelector sourceSelector = new BrightcoveSourceSelector();

    private class OnSelectSourceListener implements EventListener {
        private OnSelectSourceListener() {
        }

        @Default
        public void processEvent(Event event) {
            Video video = (Video) event.properties.get("video");
            try {
                event.properties.put("source", DefaultSourceSelectionController.this.selectSource(video));
                DefaultSourceSelectionController.this.eventEmitter.respond(event);
            } catch (NoSourceFoundException unused) {
                String str = DefaultSourceSelectionController.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("no usable Source could be found for Video: ");
                sb.append(video != null ? video.toString() : null);
                Log.e(str, sb.toString());
                EventUtil.emit(DefaultSourceSelectionController.this.eventEmitter, EventType.SOURCE_NOT_FOUND, video);
            }
        }
    }

    public DefaultSourceSelectionController(EventEmitter eventEmitter) {
        super(eventEmitter, DefaultSourceSelectionController.class);
        initializeListeners();
    }

    public void setSourceSelector(@NonNull SourceSelector sourceSelector2) {
        if (sourceSelector2 != null) {
            this.sourceSelector = sourceSelector2;
        }
    }

    @NonNull
    public SourceSelector getSourceSelector() {
        return this.sourceSelector;
    }

    @NonNull
    public Source selectSource(Video video) throws NoSourceFoundException {
        return this.sourceSelector.selectSource(video);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Source selectSource(@Nullable Set<Source> set) {
        return BrightcoveSourceSelector.selectSource(set);
    }

    public Source findBestSourceByBitRate(SourceCollection sourceCollection, Integer num) {
        return BrightcoveSourceSelector.findBestSourceByBitRate(sourceCollection, num);
    }

    /* access modifiers changed from: protected */
    public void initializeListeners() {
        addListener(EventType.SELECT_SOURCE, new OnSelectSourceListener());
    }
}
