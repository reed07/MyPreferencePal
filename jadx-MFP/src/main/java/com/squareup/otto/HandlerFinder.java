package com.squareup.otto;

import java.util.Map;
import java.util.Set;

interface HandlerFinder {
    public static final HandlerFinder ANNOTATED = new HandlerFinder() {
        public Map<Class<?>, EventProducer> findAllProducers(Object obj) {
            return AnnotatedHandlerFinder.findAllProducers(obj);
        }

        public Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object obj) {
            return AnnotatedHandlerFinder.findAllSubscribers(obj);
        }
    };

    Map<Class<?>, EventProducer> findAllProducers(Object obj);

    Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object obj);
}
