package com.brightcove.player.event;

import android.util.Log;
import com.brightcove.player.util.ErrorUtil;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegisteringEventEmitter implements EventEmitter {
    private static String ANNOTATION_EVENTS_PROPERTY = "events";
    private String componentType;
    /* access modifiers changed from: private */
    public boolean debug = false;
    private List<String> emit;
    private EventEmitter emitter;
    private List<String> listenFor;

    public RegisteringEventEmitter(EventEmitter eventEmitter, Class<? extends Component> cls) throws IllegalArgumentException {
        if (eventEmitter == null || cls == null) {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.EVENT_EMITTER_AND_COMPONENT_REQUIRED));
        }
        this.emitter = eventEmitter;
        this.componentType = cls.getSimpleName();
        this.emit = convertEventsFromAnnotation(cls, Emits.class);
        this.listenFor = convertEventsFromAnnotation(cls, ListensFor.class);
        eventEmitter.on("debug", new EventListener() {
            public void processEvent(Event event) {
                RegisteringEventEmitter.this.debug = Boolean.parseBoolean(event.properties.get("debug").toString());
            }
        });
    }

    public List<String> getAllowedEmittedEvents() {
        return this.emit;
    }

    public List<String> getAllowedListenEvents() {
        return this.listenFor;
    }

    public int on(String str, EventListener eventListener) {
        if (this.listenFor.contains(str)) {
            return this.emitter.on(str, eventListener);
        }
        throw new IllegalArgumentException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.NOT_PERMITTED_TO_LISTEN), new Object[]{str}));
    }

    public int once(String str, EventListener eventListener) {
        if (this.listenFor.contains(str)) {
            return this.emitter.once(str, eventListener);
        }
        throw new IllegalArgumentException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.NOT_PERMITTED_TO_LISTEN), new Object[]{str}));
    }

    public void off() {
        this.emitter.off();
    }

    public void off(String str, int i) {
        this.emitter.off(str, i);
    }

    public void emit(String str) {
        Map map;
        if (this.debug) {
            map = Collections.singletonMap(AbstractEvent.EMITTER, this.componentType);
        } else {
            map = Collections.emptyMap();
        }
        if (this.emit.contains(str)) {
            this.emitter.emit(str, map);
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.NOT_PERMITTED_TO_EMIT), new Object[]{str}));
    }

    public void emit(String str, Map<String, Object> map) {
        if (this.emit.contains(str)) {
            this.emitter.emit(str, addEmitterIfDebugging(map));
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.NOT_PERMITTED_TO_EMIT), new Object[]{str}));
    }

    public EventEmitter getRootEmitter() {
        return this.emitter;
    }

    public static RegisteringEventEmitter build(EventEmitter eventEmitter, Class<? extends Component> cls) {
        if (eventEmitter instanceof RegisteringEventEmitter) {
            eventEmitter = ((RegisteringEventEmitter) eventEmitter).getRootEmitter();
        }
        return new RegisteringEventEmitter(eventEmitter, cls);
    }

    public void request(String str, EventListener eventListener) {
        if (this.emit.contains(str)) {
            this.emitter.request(str, eventListener);
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.NOT_PERMITTED_TO_EMIT), new Object[]{str}));
    }

    public void request(String str, Map<String, Object> map, EventListener eventListener) {
        if (this.emit.contains(str)) {
            this.emitter.request(str, map, eventListener);
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.NOT_PERMITTED_TO_EMIT), new Object[]{str}));
    }

    public void respond(Map<String, Object> map) {
        this.emitter.respond(map);
    }

    public void respond(Event event) {
        this.emitter.respond(event);
    }

    public void enable() {
        this.emitter.enable();
    }

    public void disable() {
        this.emitter.disable();
    }

    /* access modifiers changed from: protected */
    public List<String> convertEventsFromAnnotation(Class<? extends Component> cls, Class<? extends Annotation> cls2) {
        String[] strArr = new String[0];
        ArrayList arrayList = new ArrayList();
        List<Annotation> annotations = getAnnotations(cls, cls2);
        if (annotations == null || annotations.size() <= 0) {
            throw new RuntimeException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.ANNOTATION_REQUIRED), new Object[]{cls2.getName()}));
        }
        for (Annotation annotation : annotations) {
            try {
                strArr = (String[]) annotation.getClass().getMethod(ANNOTATION_EVENTS_PROPERTY, new Class[0]).invoke(annotation, new Object[0]);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error attempting to invoke ");
                sb.append(ANNOTATION_EVENTS_PROPERTY);
                sb.append(" on annotation ");
                sb.append(cls2.getName());
                sb.append(": ");
                sb.append(e.getMessage());
                Log.e("Component", sb.toString());
            }
            arrayList.addAll(Arrays.asList(strArr));
        }
        return arrayList;
    }

    private List<Annotation> getAnnotations(Class<? extends Object> cls, Class<? extends Annotation> cls2) {
        ArrayList arrayList = new ArrayList();
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            arrayList.addAll(getAnnotations(superclass, cls2));
        }
        Annotation annotation = cls.getAnnotation(cls2);
        if (annotation != null) {
            arrayList.add(annotation);
        }
        return arrayList;
    }

    private Map<String, Object> addEmitterIfDebugging(Map<String, Object> map) {
        if (!this.debug) {
            return map;
        }
        HashMap hashMap = new HashMap(map.size() + 1);
        hashMap.putAll(map);
        hashMap.put(AbstractEvent.EMITTER, this.componentType);
        return hashMap;
    }
}
