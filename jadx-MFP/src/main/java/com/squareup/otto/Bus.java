package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Bus {
    public static final String DEFAULT_IDENTIFIER = "default";
    private final ThreadEnforcer enforcer;
    private final ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>> eventsToDispatch;
    private final Map<Class<?>, Set<Class<?>>> flattenHierarchyCache;
    private final HandlerFinder handlerFinder;
    private final ConcurrentMap<Class<?>, Set<EventHandler>> handlersByType;
    private final String identifier;
    private final ThreadLocal<Boolean> isDispatching;
    private final ConcurrentMap<Class<?>, EventProducer> producersByType;

    static class EventWithHandler {
        final Object event;
        final EventHandler handler;

        public EventWithHandler(Object obj, EventHandler eventHandler) {
            this.event = obj;
            this.handler = eventHandler;
        }
    }

    public Bus() {
        this("default");
    }

    public Bus(String str) {
        this(ThreadEnforcer.MAIN, str);
    }

    public Bus(ThreadEnforcer threadEnforcer) {
        this(threadEnforcer, "default");
    }

    public Bus(ThreadEnforcer threadEnforcer, String str) {
        this(threadEnforcer, str, HandlerFinder.ANNOTATED);
    }

    Bus(ThreadEnforcer threadEnforcer, String str, HandlerFinder handlerFinder2) {
        this.handlersByType = new ConcurrentHashMap();
        this.producersByType = new ConcurrentHashMap();
        this.eventsToDispatch = new ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>>() {
            /* access modifiers changed from: protected */
            public ConcurrentLinkedQueue<EventWithHandler> initialValue() {
                return new ConcurrentLinkedQueue<>();
            }
        };
        this.isDispatching = new ThreadLocal<Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean initialValue() {
                return Boolean.valueOf(false);
            }
        };
        this.flattenHierarchyCache = new HashMap();
        this.enforcer = threadEnforcer;
        this.identifier = str;
        this.handlerFinder = handlerFinder2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Bus \"");
        sb.append(this.identifier);
        sb.append("\"]");
        return sb.toString();
    }

    public void register(Object obj) {
        if (obj != null) {
            this.enforcer.enforce(this);
            Map findAllProducers = this.handlerFinder.findAllProducers(obj);
            for (Class cls : findAllProducers.keySet()) {
                EventProducer eventProducer = (EventProducer) findAllProducers.get(cls);
                EventProducer eventProducer2 = (EventProducer) this.producersByType.putIfAbsent(cls, eventProducer);
                if (eventProducer2 == null) {
                    Set<EventHandler> set = (Set) this.handlersByType.get(cls);
                    if (set != null && !set.isEmpty()) {
                        for (EventHandler dispatchProducerResultToHandler : set) {
                            dispatchProducerResultToHandler(dispatchProducerResultToHandler, eventProducer);
                        }
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Producer method for type ");
                    sb.append(cls);
                    sb.append(" found on type ");
                    sb.append(eventProducer.target.getClass());
                    sb.append(", but already registered by type ");
                    sb.append(eventProducer2.target.getClass());
                    sb.append(".");
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            Map findAllSubscribers = this.handlerFinder.findAllSubscribers(obj);
            for (Class cls2 : findAllSubscribers.keySet()) {
                Set set2 = (Set) this.handlersByType.get(cls2);
                if (set2 == null) {
                    set2 = new CopyOnWriteArraySet();
                    Set set3 = (Set) this.handlersByType.putIfAbsent(cls2, set2);
                    if (set3 != null) {
                        set2 = set3;
                    }
                }
                if (!set2.addAll((Set) findAllSubscribers.get(cls2))) {
                    throw new IllegalArgumentException("Object already registered.");
                }
            }
            for (Entry entry : findAllSubscribers.entrySet()) {
                EventProducer eventProducer3 = (EventProducer) this.producersByType.get((Class) entry.getKey());
                if (eventProducer3 != null && eventProducer3.isValid()) {
                    for (EventHandler eventHandler : (Set) entry.getValue()) {
                        if (!eventProducer3.isValid()) {
                            break;
                        } else if (eventHandler.isValid()) {
                            dispatchProducerResultToHandler(eventHandler, eventProducer3);
                        }
                    }
                }
            }
            return;
        }
        throw new NullPointerException("Object to register must not be null.");
    }

    private void dispatchProducerResultToHandler(EventHandler eventHandler, EventProducer eventProducer) {
        Object obj;
        try {
            obj = eventProducer.produceEvent();
        } catch (InvocationTargetException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Producer ");
            sb.append(eventProducer);
            sb.append(" threw an exception.");
            throwRuntimeException(sb.toString(), e);
            obj = null;
        }
        if (obj != null) {
            dispatch(obj, eventHandler);
        }
    }

    public void unregister(Object obj) {
        if (obj != null) {
            this.enforcer.enforce(this);
            for (Entry entry : this.handlerFinder.findAllProducers(obj).entrySet()) {
                Class cls = (Class) entry.getKey();
                EventProducer producerForEventType = getProducerForEventType(cls);
                EventProducer eventProducer = (EventProducer) entry.getValue();
                if (eventProducer == null || !eventProducer.equals(producerForEventType)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Missing event producer for an annotated method. Is ");
                    sb.append(obj.getClass());
                    sb.append(" registered?");
                    throw new IllegalArgumentException(sb.toString());
                }
                ((EventProducer) this.producersByType.remove(cls)).invalidate();
            }
            for (Entry entry2 : this.handlerFinder.findAllSubscribers(obj).entrySet()) {
                Set<EventHandler> handlersForEventType = getHandlersForEventType((Class) entry2.getKey());
                Collection collection = (Collection) entry2.getValue();
                if (handlersForEventType == null || !handlersForEventType.containsAll(collection)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Missing event handler for an annotated method. Is ");
                    sb2.append(obj.getClass());
                    sb2.append(" registered?");
                    throw new IllegalArgumentException(sb2.toString());
                }
                for (EventHandler eventHandler : handlersForEventType) {
                    if (collection.contains(eventHandler)) {
                        eventHandler.invalidate();
                    }
                }
                handlersForEventType.removeAll(collection);
            }
            return;
        }
        throw new NullPointerException("Object to unregister must not be null.");
    }

    public void post(Object obj) {
        if (obj != null) {
            this.enforcer.enforce(this);
            boolean z = false;
            for (Class handlersForEventType : flattenHierarchy(obj.getClass())) {
                Set<EventHandler> handlersForEventType2 = getHandlersForEventType(handlersForEventType);
                if (handlersForEventType2 != null && !handlersForEventType2.isEmpty()) {
                    z = true;
                    for (EventHandler enqueueEvent : handlersForEventType2) {
                        enqueueEvent(obj, enqueueEvent);
                    }
                }
            }
            if (!z && !(obj instanceof DeadEvent)) {
                post(new DeadEvent(this, obj));
            }
            dispatchQueuedEvents();
            return;
        }
        throw new NullPointerException("Event to post must not be null.");
    }

    /* access modifiers changed from: protected */
    public void enqueueEvent(Object obj, EventHandler eventHandler) {
        ((ConcurrentLinkedQueue) this.eventsToDispatch.get()).offer(new EventWithHandler(obj, eventHandler));
    }

    /* access modifiers changed from: protected */
    public void dispatchQueuedEvents() {
        if (!((Boolean) this.isDispatching.get()).booleanValue()) {
            this.isDispatching.set(Boolean.valueOf(true));
            while (true) {
                try {
                    EventWithHandler eventWithHandler = (EventWithHandler) ((ConcurrentLinkedQueue) this.eventsToDispatch.get()).poll();
                    if (eventWithHandler != null) {
                        if (eventWithHandler.handler.isValid()) {
                            dispatch(eventWithHandler.event, eventWithHandler.handler);
                        }
                    } else {
                        return;
                    }
                } finally {
                    this.isDispatching.set(Boolean.valueOf(false));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatch(Object obj, EventHandler eventHandler) {
        try {
            eventHandler.handleEvent(obj);
        } catch (InvocationTargetException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not dispatch event: ");
            sb.append(obj.getClass());
            sb.append(" to handler ");
            sb.append(eventHandler);
            throwRuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public EventProducer getProducerForEventType(Class<?> cls) {
        return (EventProducer) this.producersByType.get(cls);
    }

    /* access modifiers changed from: 0000 */
    public Set<EventHandler> getHandlersForEventType(Class<?> cls) {
        return (Set) this.handlersByType.get(cls);
    }

    /* access modifiers changed from: 0000 */
    public Set<Class<?>> flattenHierarchy(Class<?> cls) {
        Set<Class<?>> set = (Set) this.flattenHierarchyCache.get(cls);
        if (set != null) {
            return set;
        }
        Set<Class<?>> classesFor = getClassesFor(cls);
        this.flattenHierarchyCache.put(cls, classesFor);
        return classesFor;
    }

    private Set<Class<?>> getClassesFor(Class<?> cls) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        linkedList.add(cls);
        while (!linkedList.isEmpty()) {
            Class cls2 = (Class) linkedList.remove(0);
            hashSet.add(cls2);
            Class superclass = cls2.getSuperclass();
            if (superclass != null) {
                linkedList.add(superclass);
            }
        }
        return hashSet;
    }

    private static void throwRuntimeException(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": ");
            sb.append(cause.getMessage());
            throw new RuntimeException(sb.toString(), cause);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(": ");
        sb2.append(invocationTargetException.getMessage());
        throw new RuntimeException(sb2.toString(), invocationTargetException);
    }
}
